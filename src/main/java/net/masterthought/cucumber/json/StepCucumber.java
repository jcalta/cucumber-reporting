package net.masterthought.cucumber.json;

import net.masterthought.cucumber.json.Step;
import net.masterthought.cucumber.json.ResultCucumber;
import net.masterthought.cucumber.util.Util;

public class StepCucumber extends Step {

    private ResultCucumber result;

    public Util.Status getStatus() {
        if (result == null) {
            System.out.println("[WARNING] Line " + line + " : " + "Step is missing Result: " + keyword + " : " + name);
            return Util.Status.MISSING;
        } else {
            return Util.resultMap.get(result.getStatus());
        }
    }

    public Long getDuration() {
        if (result == null) {
            return 1L;
        } else {
            return result.getDuration();
        }
    }

    public String getName() {
        String content = "";
        if (getStatus() == Util.Status.FAILED) {
            String errorMessage = result.getErrorMessage();
            if (getStatus() == Util.Status.SKIPPED) {
                errorMessage = "Mode: Skipped causes Failure<br/><span class=\"skipped\">This step was skipped</span>";
            }
            if (getStatus() == Util.Status.UNDEFINED) {
                errorMessage = "Mode: Not Implemented causes Failure<br/><span class=\"undefined\">This step is not yet implemented</span>";
            }
            content = Util.result(getStatus()) + "<span class=\"step-keyword\">" + keyword + " </span><span class=\"step-name\">" + name + "</span><span class=\"step-duration\">" + Util.formatDuration(result.getDuration()) + "</span><div class=\"step-error-message\"><pre>" + formatError(errorMessage) + "</pre></div>" + Util.closeDiv() + getImageTags();
        } else if (getStatus() == Util.Status.MISSING) {
            String errorMessage = "<span class=\"missing\">Result was missing for this step</span>";
            content = Util.result(getStatus()) + "<span class=\"step-keyword\">" + keyword + " </span><span class=\"step-name\">" + name + "</span><span class=\"step-duration\">" + Util.formatDuration(result.getDuration()) + "</span><div class=\"step-error-message\"><pre>" + formatError(errorMessage) + "</pre></div>" + Util.closeDiv();
        } else {
            content = getNameAndDuration();
        }
        return content;
    }

    protected String getNameAndDuration() {
        String content = Util.result(getStatus())
                + "<span class=\"step-keyword\">" + keyword
                + " </span><span class=\"step-name\">" + name + "</span>"
                + "<span class=\"step-duration\">" + Util.formatDuration(result.getDuration()) + "</span>"
                + Util.closeDiv() + getImageTags();

        return content;
    }

}