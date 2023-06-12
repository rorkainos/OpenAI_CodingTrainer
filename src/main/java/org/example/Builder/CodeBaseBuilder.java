package org.example.Builder;

import org.example.AzureCaller;
import org.example.models.CodeBaseRequirements;

/*
* class that will serve as our class that builds the code base.
* this needs to be extended to call azure
*/
public class CodeBaseBuilder {
    private CodeBaseRequirements codeBaseRequirements;
    private AzureCaller aiCaller;

    public CodeBaseBuilder(CodeBaseRequirements codeBaseRequirements, AzureCaller aiCaller) {
        this.codeBaseRequirements = codeBaseRequirements;
        this.aiCaller = aiCaller;
    }

    public void build(){

    }
}
