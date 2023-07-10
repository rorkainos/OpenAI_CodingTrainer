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
    private final String INCREASE_COMPLEXITY_PROMPT = "Could you make the code example more difficult?\n";
    private List<ChatMessage> chatMessages = new ArrayList<>();

    public AzureFileCreator(AzureProperties azureProperties) {
        super(azureProperties);
    }

    public AzureProgramDetails getProgramDetails(CodeBaseRequirements codeBase) throws InvalidResponseException {

        String prompt = "The area of interest for the training material is " + codeBase.topic() + ". " +
                "Could you please provide me with incorrect code in " + codeBase.language() + ", followed by a corresponding unit test that will fail? " +
                "Then provide corrected code that will pass the unit test. " +
                "There should be 3 code snippets, one for the incorrect code, one for the unit test and one for the corrected code. " +
                "For each code snippet, provide appropriate file name within an absolute file path. The correct and incorrect code snippets should be in separate file paths." +
                "The file paths should be structured such that the files adhere to the best practices of a project in " + codeBase.language()  + ". " +
                "Surround the file paths with the symbols '£'. Example: £src/main/index.html£. " +
                "There should be no explanations of the code. ";

        List<String> filesWithAbsolutePath = new ArrayList<>();
        List<String> fileContents = new ArrayList<>();

        ChatMessage systemChatMessage = new ChatMessage(ChatRole.SYSTEM);
        systemChatMessage.setContent(SYSTEM_PROMPT);
        chatMessages.add(systemChatMessage);

        String response = getChatCompletion(chatMessages, prompt).get(0);

        ChatMessage promptChatMessage = new ChatMessage(ChatRole.USER);
        promptChatMessage.setContent(prompt);
        chatMessages.add(promptChatMessage);
        ChatMessage responseChatMessage = new ChatMessage(ChatRole.ASSISTANT);
        responseChatMessage.setContent(response);
        chatMessages.add(responseChatMessage);

        if(codeBase.complexity() != 0) {
            response = getIncreasedComplexity(codeBase);
        }

        filesWithAbsolutePath = filterResponseByRegex(response, "£(.*?)£");
        fileContents = filterResponseByRegex(response, "\\`\\`\\`(.+?)\\`\\`\\`");

        addConfigurationFile(filesWithAbsolutePath, fileContents);

        if(!codeBase.readMeTopics().equalsIgnoreCase("no")) {
                String readmeResponse = getReadmeFile();
                filesWithAbsolutePath.add("Readme.md");
                fileContents.add(filterResponseByRegex(readmeResponse, "\\$\\$(.*?)\\$\\$").get(0));
        }

        if(fileContents.isEmpty() ||
                filesWithAbsolutePath.isEmpty() ||
                filesWithAbsolutePath.size() != fileContents.size()) {
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

    public String getIncreasedComplexity(CodeBaseRequirements codeBase) {
        String finalResponse = "";

        ChatMessage promptChatMessage = new ChatMessage(ChatRole.USER);
        ChatMessage responseChatMessage = new ChatMessage(ChatRole.ASSISTANT);
        promptChatMessage.setContent(INCREASE_COMPLEXITY_PROMPT);

        for (int i = 1; i <= codeBase.complexity(); i++) {
            finalResponse = getChatCompletion(chatMessages, INCREASE_COMPLEXITY_PROMPT).get(0);
            chatMessages.add(promptChatMessage);
            responseChatMessage.setContent(finalResponse);
            chatMessages.add(responseChatMessage);
        }

        return finalResponse;
    }

    public String getReadmeFile() {
        String readmePrompt = "Could you create a separate README.md file that addresses the code generated, " +
                "including instructions on how to run the code?\nSurround the contents of the README.md file with '$$'";

        String readmeResponse = getChatCompletion(chatMessages, readmePrompt).get(0);

        return readmeResponse;
    }

    private void addConfigurationFile(List<String> filesWithAbsolutePaths, List<String> fileContents) {
        String prompt = "You have already provided the generated code. Please provide a configuration file for the " +
                "generated code using the most appropriate package manager or runtime. Include any dependencies " +
                "needed to run the project locally and outside of the browser. Surround the configuration file " +
                "relative path with '£' and the file contents exclusively with '```'.";

        String configFileInformation = getChatCompletion(chatMessages, prompt).get(0);
        String configFileName = filterResponseByRegex(configFileInformation, "£(.*?)£").get(0);
        String configFileContents = filterResponseByRegex(configFileInformation, "```(.+?)```").get(0);

        if (configFileName != null && !configFileName.isBlank() &&
                configFileContents != null && !configFileContents.isBlank()) {
            filesWithAbsolutePaths.add(configFileName);
            fileContents.add(configFileContents);
        }
    }
}
