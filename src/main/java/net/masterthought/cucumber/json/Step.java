package net.masterthought.cucumber.json;

import com.google.common.base.Joiner;
import com.google.gson.internal.LinkedTreeMap;
import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Sequences;
import com.googlecode.totallylazy.predicates.LogicalPredicate;
import net.masterthought.cucumber.ConfigurationOptions;
import net.masterthought.cucumber.util.Util;

import java.util.List;
import java.util.UUID;

import static com.googlecode.totallylazy.Option.option;
import static org.apache.commons.lang.StringUtils.EMPTY;

public abstract class Step {

    protected String name;
    protected String keyword;
    protected String line;
    protected Row[] rows;
    protected Match match;
    protected Object[] embeddings;
    protected String[] output;
    protected DocString doc_string;

    public Step() {

    }

    public abstract Util.Status getStatus();
    public abstract String getName();
    protected abstract String getNameAndDuration();

    public DocString getDocString() {
        return doc_string;
    }

    public Row[] getRows() {
        return rows;
    }

    public String getOutput() {
        List<String> outputList = Sequences.sequence(option(output).getOrElse(new String[]{})).realise().toList();
        return Joiner.on("").skipNulls().join(outputList);
    }

    public Match getMatch() {
        return match;
    }

    public Object[] getEmbeddings() {
        return embeddings;
    }

    public boolean hasRows() {
        boolean result = false;
        if (rows != null) {
            if (rows.length > 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * @return - Returns true if has a sub doc-string, and that doc-string has a value
     */
    public boolean hasDocString() {
        return doc_string != null && doc_string.hasValue();
    }

    public String getDataTableClass() {
        String content = "";
        Util.Status status = getStatus();
        if (status == Util.Status.FAILED) {
            content = "failed";
        } else if (status == Util.Status.PASSED) {
            content = "passed";
        } else if (status == Util.Status.SKIPPED) {
            content = "skipped";
        } else {
            content = "";
        }
        return content;
    }

    public String getRawName() {
        return name;
    }

    /**
     * Returns a formatted doc-string section.
     * This is formatted w.r.t the parent Step element.
     * To preserve whitespace in example, line breaks and whitespace are preserved
     *
     * @return string of html
     */
    public String getDocStringOrNothing() {
        if (!hasDocString()) {
            return "";
        }
        return Util.result(getStatus()) +
                "<div class=\"doc-string\">" +
                getDocString().getEscapedValue() +
                Util.closeDiv() +
                Util.closeDiv();
    }

    protected String formatError(String errorMessage) {
        String result = errorMessage;
        if (errorMessage != null && !errorMessage.isEmpty()) {
            result = errorMessage.replaceAll("\\\\n", "<br/>");
        }
        return result;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getImageTags() {
        if (noEmbeddedScreenshots()) return EMPTY;

        String links = EMPTY;
        int index = 1;
        for (Object image : embeddings) {
            if (image != null) {
                String mimeEncodedImage = mimeEncodeEmbededImage(image);
                String imageId = UUID.nameUUIDFromBytes(mimeEncodedImage.getBytes()).toString();
                links = links + "<a href=\"\" onclick=\"img=document.getElementById('" + imageId + "'); img.style.display = (img.style.display == 'none' ? 'block' : 'none');return false\">Screenshot " + index++ + "</a>" +
                        "<img id='" + imageId + "' style='display:none' src='" + mimeEncodedImage + "'>\n";
            }
        }
        return links;
    }

    protected boolean noEmbeddedScreenshots() {
        return getEmbeddings() == null;
    }


    public static String mimeEncodeEmbededImage(Object image) {
        return "data:image/png;base64," + ((LinkedTreeMap) image).get("data");

    }

    public static String uuidForImage(Object image) {
        return UUID.nameUUIDFromBytes(mimeEncodeEmbededImage(image).getBytes()).toString();
    }

    public static class functions {
        public static Function1<Step, Util.Status> status() {
            return new Function1<Step, Util.Status>() {
                @Override
                public Util.Status call(Step step) throws Exception {
                    return step.getStatus();
                }
            };
        }
    }

    public static class predicates {

        public static LogicalPredicate<Step> hasStatus(final Util.Status status) {
            return new LogicalPredicate<Step>() {
                @Override
                public boolean matches(Step step) {
                    return step.getStatus().equals(status);
                }
            };
        }


        public static Function1<Step, Util.Status> status() {
            return new Function1<Step, Util.Status>() {
                @Override
                public Util.Status call(Step step) throws Exception {
                    return step.getStatus();
                }
            };
        }
    }

}
