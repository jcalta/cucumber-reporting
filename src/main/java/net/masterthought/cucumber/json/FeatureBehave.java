package net.masterthought.cucumber.json;

import org.apache.commons.lang.StringUtils;

import net.masterthought.cucumber.util.Util;
import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.ElementBehave; 

public class FeatureBehave extends Feature {

    private String[] description;
    private ElementBehave[] elements;

    public String getDescriptionString() {
        return StringUtils.join(description);
    }

    protected Element[] getElementList() {
        return elements;
    }

}
