import sys
import openai
import re
import json

def call_gpt(language, topic, difficulty):
    openai.api_type = "azure"
    openai.api_version = "2023-05-15" 
    openai.api_base = "https://gpt4resource.openai.azure.com/"
    openai.api_key = "618bf90290544e54b47bcfc2dba743da"
    engine = 'testdeploy'

    system_message = 'Assistant is an intelligent chatbot designed to help users create programming training materials'
    user_message = f"""Could you please provide me with incorrect code in {language}, followed by a corresponding unit test that will fail? 
                            Then provide corrected code that will pass the unit test.
                            There should be 3 code snippets, one for the incorrect code, one for the unit test and one for the corrected code. 
                            For each code snippet, provide an appropriate file name.
                            Surround the file name with the symbols '££'.
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
    languages = [match for match in matches if match.strip() != '']
    return languages

def extract_filenames(text):
    pattern = r"££(.*)££"
    matches = re.findall(pattern, text)
    matches = [match for match in matches if match != '`']
    matches[-1] = 'corrected-' + matches[0].split('.')[0] + '.' + matches[0].split('.')[1]
    return matches

def remove_before_second_newline(text):
    lines = text.split('\n')
    new_text = '\n'.join(lines[1:])
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

    json_filename = 'label_language_filename_code.json'
    with open(json_filename, 'w') as file:
        json.dump(data, file)
        
def run_checks():
    pass

def main():
    try:
        #The first variable in the list (index 0) is the script name itself
        variables = sys.argv

        content = call_gpt(variables[1], variables[2], variables[3])
        
        languages = extract_languages(content)
        print(f'LANGUAGES ({len(languages)}): {languages}')
        
        filenames = extract_filenames(content)
        print(f'FILENAMES ({len(filenames)}): {filenames}')
        
        code = extract_code(content)
        print(f'CODE ({len(code)}): {code}')
        for i in code:
            print('-'*100)
            print(i)
        print('-'*100)

        output_json(languages, filenames, code)
        
        print('SCRIPT COMPLETE - JSON OUTPUT CREATED.')
    except Exception as e:
        print(e)

if __name__ == "__main__":
    main()