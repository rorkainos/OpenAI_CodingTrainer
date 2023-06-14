import openai
import re
import json

def call_gpt(language = 'Node.js', topic = 'web development', difficulty = 'intermediate'):
    openai.api_type = "azure"
    openai.api_version = "2023-05-15" 
    openai.api_base = "https://gpt4resource.openai.azure.com/"
    openai.api_key = "618bf90290544e54b47bcfc2dba743da"
    engine = 'testdeploy'

    system_message = 'Assistant is an intelligent chatbot designed to help users create programming training materials'
    user_message = f"""Could you please provide me with incorrect code in {language}, followed by a corresponding unit test that will fail? 
                            Then provide corrected code that will pass the unit test. 
                            For each code snippet, provide an appropriate file name. 
                            The area of interest is {topic}. 
                            The difficulty should be {difficulty}."""
                        
    response = openai.ChatCompletion.create(
        engine=engine,
        messages=[
            {"role": "system", "content": system_message},
            {"role": "user", "content": user_message}
        ],
        temperature = 0
    )
   
    content = response['choices'][0]['message']['content']
    return content

def extract_languages(text):
    pattern = r"```(.*)\n"
    matches = re.findall(pattern, text)
    languages = [match for match in matches if match != '']
    return languages

def extract_filenames(text):
    pattern = r": `(.*)`\n"
    matches = re.findall(pattern, text)
    matches.pop()
    matches.append('corrected-' + matches[0].split('.')[0] + '.' + matches[0].split('.')[1])
    return matches

def remove_before_second_newline(text):
    lines = text.split('\n')
    new_text = '\n'.join(lines[2:])
    if new_text[0] == '\n':
        return new_text[1:]
    else:
        return new_text

def extract_code(text):
    pattern = r"```([\s\S]*?)```"
    matches = re.findall(pattern, text)
    code = []
    for match in matches:
        code.append(remove_before_second_newline(match))
    return code

def output_json(languages, filenames, code):
    data = {
        'labels': ['Incorrect code', 'Unit test', 'Corrected code'],
        'languages': languages,
        'filenames': filenames,
        'code': code
    }

    json_filename = 'label_lang_file_code.json'
    with open(json_filename, 'w') as file:
        json.dump(data, file)

def main():
    content = call_gpt()
    
    languages = extract_languages(content)
    filenames = extract_filenames(content)
    code = extract_code(content)
    
    output_json(languages, filenames, code)

if __name__ == "__main__":
    main()