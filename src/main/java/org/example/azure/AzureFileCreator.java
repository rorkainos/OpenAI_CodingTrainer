package org.example.azure;

import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import org.example.exceptions.InvalidResponseException;
import org.example.models.CodeBaseRequirements;
import org.example.properties.AzureProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AzureFileCreator extends AzureCaller {

    private final String SYSTEM_PROMPT = "Assistant is an intelligent chatbot designed to help users create programming training materials. " +
            "Assistant will provide the project's file structure, functional code and unit tests in multiple replies.";
    private final String INCREASE_DIFFICULTY_PROMPT = "Could you make the code example more difficult?\n";
    private List<ChatMessage> chatMessages = new ArrayList<>();

    public AzureFileCreator(AzureProperties azureProperties) {
        super(azureProperties);
    }

    public AzureProgramDetails getProgramDetails(CodeBaseRequirements codeBase) throws InvalidResponseException {

        String prompt = "The area of interest for the training material is " + codeBase.topic() + ". " +
                "Could you please provide me with incorrect code in " + codeBase.language() + ", followed by a corresponding unit test that will fail? " +
                "Then provide corrected code that will pass the unit test. " +
                "There should be 3 code snippets, one for the incorrect code, one for the unit test and one for the corrected code. " +
                "For each code snippet, provide appropriate file name within an absolute file path. The correct and incorrect code snippets should be in separate files." +
                "The file paths should be structured such that the files adhere to the best practices of a project in " + codeBase.language()  + ". " +
                "Surround the file paths with the symbols '£'. Example: £src/main/index.html£. " +
                "There should be no explanations of the code. ";

        ChatMessage systemChatMessage = new ChatMessage(ChatRole.SYSTEM);
        systemChatMessage.setContent(SYSTEM_PROMPT);
        chatMessages.add(systemChatMessage);

        String response = getChatCompletion(chatMessages, prompt).get(0);

        // This logic could use some improvement
        ChatMessage promptChatMessage = new ChatMessage(ChatRole.USER);
        promptChatMessage.setContent(prompt);
        chatMessages.add(promptChatMessage);
        ChatMessage responseChatMessage = new ChatMessage(ChatRole.ASSISTANT);
        responseChatMessage.setContent(response);
        chatMessages.add(responseChatMessage);

        for (int i = 1; i <= codeBase.difficulty(); i++) {
            response = getChatCompletion(chatMessages, INCREASE_DIFFICULTY_PROMPT).get(0);
            promptChatMessage.setContent(INCREASE_DIFFICULTY_PROMPT);
            chatMessages.add(promptChatMessage);
            responseChatMessage.setContent(response);
            chatMessages.add(responseChatMessage);
        }

        List<String> filesWithAbsolutePath = filterResponseByRegex(response, "£(.*?)£");
        List<String> fileContents = filterResponseByRegex(response, "\\`\\`\\`(.+?)\\`\\`\\`");

        if(!codeBase.readMeTopics().equalsIgnoreCase("no")) {
            String readmeResponse = getReadmeFile();
            filesWithAbsolutePath.add("Readme.md");
            fileContents.add(filterResponseByRegex(readmeResponse, "\\$\\$(.*?)\\$\\$").get(0));
        }

        if(fileContents.isEmpty() || filesWithAbsolutePath.isEmpty() || filesWithAbsolutePath.size() != fileContents.size()) {
            throw new InvalidResponseException();
        }

        return new AzureProgramDetails(filesWithAbsolutePath, fileContents);
    }

    public List<String> filterResponseByRegex(String response, String regex) {
        List<String> filteredText = new ArrayList<>();

        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(response);

        while (matcher.find()) {
            String text = matcher.group(1);
            filteredText.add(text);
        }

        return filteredText;
    }

    public String getReadmeFile() {
        String readmePrompt = "Could you create a separate README.md file that addresses the code generated?\n" +
                "Surround the contents of the README.md file with '$$'";

        String readmeResponse = getChatCompletion(chatMessages, readmePrompt).get(0);

        return readmeResponse;
    }
}
