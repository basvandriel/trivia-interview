package com.bas.trivia;

public class QuestionResponse {

    private Question[] results;

    public QuestionResponse(Question[] results) {
        this.results = results;
    }

    public QuestionResponse() {
    }

    public Question[] getResults() {
        return results;
    }

    public void setResults(Question[] results) {
        this.results = results;
    }
}
