package net.masterthought.cucumber.json;

import net.masterthought.cucumber.json.Step;
import net.masterthought.cucumber.json.ResultCucumber;

public class StepCucumber extends Step {

    private ResultCucumber result;

    protected Result getResult() {
        return result;
    }
}