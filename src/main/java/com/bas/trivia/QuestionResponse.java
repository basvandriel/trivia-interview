package com.bas.trivia;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionResponse {

    /**
     * It's default
     */
    private int response_code;

    /**
     * The questions from the result
     */
    private Question[] results;

    /**
     * Empty constructor solution (https://stackoverflow.com/a/53192674/2989034) would make a bad practice 
     * which can lead to broken objects. Use @JsonProperty
     * 
     * @param response_code 
     * @param results The questions from the result
     */
    public QuestionResponse(@JsonProperty("response_code") int response_code, @JsonProperty("results") Question[] results) {
        this.response_code = response_code;
        this.results = results;
    }

    public int getResponseCode() {
        return this.response_code;
    }

    public Question[] getResults() {
        return results;
    }

    public void setResponseCode(int responseCode) {
        this.response_code = responseCode;
    }

    public void setResults(Question[] results) {
        this.results = results;
    }
}
