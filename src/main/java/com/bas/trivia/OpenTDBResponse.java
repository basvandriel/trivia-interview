package com.bas.trivia;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenTDBResponse {
    /**
     * ...
     */
    private int response_code;

    /**
     * ...
     */
    private Question[] results;

    /**
     * Empty constructor solution (https://stackoverflow.com/a/53192674/2989034) would make a bad practice 
     * which can lead to broken objects. Use @JsonProperty
     * 
     * @param response_code 
     * @param results The questions from the result
     */
    public OpenTDBResponse(@JsonProperty("response_code") int response_code, @JsonProperty("results") Question[] results) {
        this.response_code = response_code;
        this.results = results;
    }

    public int getResponseCode() {
        return this.response_code;
    }

    public Question[] getResults() {
        return results;
    }
}
