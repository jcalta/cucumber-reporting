package net.masterthought.cucumber.json;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Sequence;
import com.googlecode.totallylazy.Sequences;
import net.masterthought.cucumber.util.Util;
import org.apache.commons.lang.StringUtils;

import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.ElementCucumber;

import java.util.ArrayList;
import java.util.List;

public class FeatureCucumber extends Feature {

    private String description;
    private ElementCucumber[] elements;
    private Tag[] tags;

    public String getDescriptionString() {
        return description;
    }

    protected Element[] getElementArray() {
        return elements;
    }
    protected Tag[] getTagArray() {
        return tags;
    }
}
