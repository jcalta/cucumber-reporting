package net.masterthought.cucumber.json;

import net.masterthought.cucumber.json.Step;
import net.masterthought.cucumber.json.ResultBehave;

public class StepBehave extends Step {

    private ResultBehave result;

    protected Result getResult() {
        return result;
    }
}