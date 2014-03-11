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

    public String getDescription() {
        String result = "";
        if (Util.itemExists(description)) {
            String content = description.replaceFirst("As an", "<span class=\"feature-role\">As an</span>");
            content = content.replaceFirst("I want to", "<span class=\"feature-action\">I want to</span>");
            content = content.replaceFirst("So that", "<span class=\"feature-value\">So that</span>");
            content = content.replaceAll("\n", "<br/>");
            result = "<div class=\"feature-description\">" + content + "</div>";
        }
        return result;
    }

}
