package net.masterthought.cucumber.json;

import net.masterthought.cucumber.ConfigurationOptions;
import net.masterthought.cucumber.json.Element;
import net.masterthought.cucumber.json.StepBehave;

public class ElementBehave extends Element {

    private StepBehave[] steps;

    protected Step[] getStepList() {
        return steps;
    }
}