package org.example.DataGeneration;

import org.example.exceptions.DuplicatedFileException;
import org.example.service.FileService;

import java.io.File;
import java.io.IOException;

public class GenerateFiles {
    public static String createHTMLFileString(){
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>\n");
        htmlBuilder.append("<html>\n");
        htmlBuilder.append("<head>\n");
        htmlBuilder.append("    <meta charset=\"UTF-8\">\n");
        htmlBuilder.append("    <title>Hello, World!</title>\n");
        htmlBuilder.append("    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n");
        htmlBuilder.append("</head>\n");
        htmlBuilder.append("<body>\n");
        htmlBuilder.append("    <h1 id=\"message\">Hello, World!</h1>\n");
        htmlBuilder.append("    <script src=\"script.js\"></script>\n");
        htmlBuilder.append("</body>\n");
        htmlBuilder.append("</html>");
        return htmlBuilder.toString();
    }

    public static String createIncorrectHTMLFileString_DocTypeWrong(){
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOYPE html>\n");
        htmlBuilder.append("<html>\n");
        htmlBuilder.append("<head>\n");
        htmlBuilder.append("    <meta charset=\"UTF-8\">\n");
        htmlBuilder.append("    <title>Hello, World!</title>\n");
        htmlBuilder.append("    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n");
        htmlBuilder.append("</head>\n");
        htmlBuilder.append("<body>\n");
        htmlBuilder.append("    <h1 id=\"message\">Hello, World!</h1>\n");
        htmlBuilder.append("    <script src=\"script.js\"></script>\n");
        htmlBuilder.append("</body>\n");
        htmlBuilder.append("</html>");
        return htmlBuilder.toString();
    }

    public static String createIncorrectHTMLFileString_ClosingBodyTagMissing(){
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>\n");
        htmlBuilder.append("<html>\n");
        htmlBuilder.append("<head>\n");
        htmlBuilder.append("    <meta charset=\"UTF-8\">\n");
        htmlBuilder.append("    <title>Hello, World!</title>\n");
        htmlBuilder.append("    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n");
        htmlBuilder.append("</head>\n");
        htmlBuilder.append("<body>\n");
        htmlBuilder.append("    <h1 id=\"message\">Hello, World!</h1>\n");
        htmlBuilder.append("    <script src=\"script.js\"></script>\n");
        htmlBuilder.append("</html>");
        return htmlBuilder.toString();
    }

    public static String createCSSFileString(){
        StringBuilder cssBuilder = new StringBuilder();
        cssBuilder.append("h1 {\n");
        cssBuilder.append("    color: blue;\n");
        cssBuilder.append("}");

        return cssBuilder.toString();
    }

    public static String createJSONFileString(){
        StringBuilder packageBuilder = new StringBuilder();
        packageBuilder.append("{\n");
        packageBuilder.append("  \"name\": \"hello-world-app\",\n");
        packageBuilder.append("  \"version\": \"1.0.0\",\n");
        packageBuilder.append("  \"description\": \"A simple Hello, World! application\",\n");
        packageBuilder.append("  \"main\": \"index.html\",\n");
        packageBuilder.append("  \"scripts\": {\n");
        packageBuilder.append("    \"start\": \"echo \\\"Open index.html in a browser to run the application.\\\"\"\n");
        packageBuilder.append("  }\n");
        packageBuilder.append("}");

        return packageBuilder.toString();
    }

    public static String createJSFileString(){
        StringBuilder scriptBuilder = new StringBuilder();
        scriptBuilder.append("window.addEventListener('DOMContentLoaded', (event) => {\n");
        scriptBuilder.append("    const messageElement = document.getElementById('message');\n");
        scriptBuilder.append("    messageElement.textContent = 'Hello, World!';\n");
        scriptBuilder.append("});");

        return scriptBuilder.toString();
    }

    public static String createIncorrectJSFileString(){
        StringBuilder scriptBuilder = new StringBuilder();
        scriptBuilder.append("window.addEveener('DOMContentLoaded', (event) => {\n");
        scriptBuilder.append("    const messageElement = document.getElementById('message');\n");
        scriptBuilder.append("    messageElement.textContent = 'Hello, World!';\n");
        scriptBuilder.append("});");

        return scriptBuilder.toString();
    }
}
