package net.masterthought.cucumber.json;

import org.apache.commons.lang.StringUtils;

import net.masterthought.cucumber.util.Util;
import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.ElementBehave; 

public class FeatureBehave extends Feature {

    private String[] description;
    private ElementBehave[] elements;
    private String[] tags;
    private Tag[] tagArray;

    public String getDescriptionString() {
        return StringUtils.join(description);
    }

    protected Element[] getElementArray() {
        return elements;
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
