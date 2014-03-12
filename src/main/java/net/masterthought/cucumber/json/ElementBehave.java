package net.masterthought.cucumber.json;

import net.masterthought.cucumber.util.Util;
import net.masterthought.cucumber.json.Element;
import net.masterthought.cucumber.json.StepBehave;

public class ElementBehave extends Element {

    private StepBehave[] steps;
    private String[] tags;
    private Tag[] tagArray;

    protected Step[] getStepArray() {
        return steps;
    }

    protected Tag[] getTagArray(){
        if(! Util.itemExists(tagArray)) {
            tagArray = new Tag[tags.length];
            for(int i = 0; i < tags.length; i = i++) {
                tagArray[i] = new Tag(tags[i]);
            }
        }
        return tagArray;
    }
}