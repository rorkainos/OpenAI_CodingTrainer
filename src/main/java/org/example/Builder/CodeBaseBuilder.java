package org.example.Builder;

import org.example.models.CodeBaseRequirements;

/*
* class that will service as our class that builds the program.
* this needs to be extended by florian to call azure
*/
public class CodeBaseBuilder {
    private CodeBaseRequirements codeBaseRequirements;

    public CodeBaseBuilder(CodeBaseRequirements codeBaseRequirements) {
        this.codeBaseRequirements = codeBaseRequirements;
    }

    public void build(){

    }
}
