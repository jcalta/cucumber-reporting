package net.masterthought.cucumber.json;

import net.masterthought.cucumber.json.Result;

public class ResultBehave extends Result{

    private Double duration;

    public Long getDuration() {
        return duration == null ? 0L : (long) (duration * 1000000);
    }
}
