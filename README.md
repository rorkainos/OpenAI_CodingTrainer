# OpenAI_CodingTrainer

## Before all
1. Create a file named `.properties` in the `src/main/properties` folder and add your apiKey, deploymentName and endpoint from Azure's OpenAI.
Use the following format:
`apiKey=exampleApiKey
deploymentName=exampleDeployment
endpoint=exampleEndpoint`


## Compiling the code

1. Navigate to the src/main/java directory using `cd src/main/java`
2. Run the command `javac org/example/App.java`
3. The .class file will be created in the same directory as the App.java file

## Running the program

1. Navigate to the src/main/java directory using `cd src/main/java`
2. Run the command `java org.example.App --input "<prompt here>"` replacing \<prompt here\> with your prompt
3. The program will output your prompt in the console 