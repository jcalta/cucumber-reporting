package net.masterthought.cucumber.json;

import net.masterthought.cucumber.json.Result;

public class ResultCucumber extends Result {

    private Long duration;

    public Long getDuration() {
        return duration == null ? 0L : duration;
    }
}
