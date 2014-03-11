package net.masterthought.cucumber.json;

public abstract class Result {

    private String status;
    private String error_message;

    public Result() {

    }

    public abstract Long getDuration();

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return error_message;
    }
}
