package com.bas.trivia;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is what comes in JSON format from the 
 * /checkanswer endpoint
 * 
 * @author Bas
 */
class CheckAnswerRequestPayload {
    /**
     * The question that got answered by the the user
     */
    private String question;

    /**
     * The answer to the question
     */
    private String answer;
    
    /**
     * Initialze the JSON properties
     * 
     * @param question
     * @param answer 
     */
    public CheckAnswerRequestPayload(
        @JsonProperty("question") String question,
        @JsonProperty("answer") String answer
    ) {
        this.question = question;
        this.answer = answer;
    }

    
    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }
}