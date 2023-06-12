package org.example.Builder;

import org.example.models.CodeBaseRequirements;

/*
* class that will serve as our class that builds the code base.
* this needs to be extended to call azure
*/
public class CodeBaseBuilder {
    private CodeBaseRequirements codeBaseRequirements;

    public CodeBaseBuilder(CodeBaseRequirements codeBaseRequirements) {
        this.codeBaseRequirements = codeBaseRequirements;
    }

    public void build(){

    }
}
