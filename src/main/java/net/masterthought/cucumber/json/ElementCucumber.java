package net.masterthought.cucumber.json;

import net.masterthought.cucumber.json.Element;
import net.masterthought.cucumber.json.StepCucumber;

public class ElementCucumber extends Element {

    private StepCucumber[] steps;

    protected Step[] getStepList() {
        return steps;
    }
}