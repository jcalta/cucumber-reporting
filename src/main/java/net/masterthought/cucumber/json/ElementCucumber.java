package net.masterthought.cucumber.json;

import net.masterthought.cucumber.json.Element;
import net.masterthought.cucumber.json.StepCucumber;

public class ElementCucumber extends Element {

    private StepCucumber[] steps;
    private Tag[] tags;    

    protected Step[] getStepArray() {
        return steps;
    }

    protected Tag[] getTagArray() {
        return tags;
    }
}