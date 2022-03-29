package com.bas.trivia;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Bas
 */
public class Question {

    /**
     * The category of the question
     */
    private String category;

    /**
     * The type
     */
    private String type;

    /**
     * Easy, medium or hard. Could be an enum later
     */
    private String difficulty;

    /**
     * The value of the question
     */
    private String question;

    /**
     * The one and only correct answer
     */
    private String correct_answer;

    /**
     * All the incorrect answers
     */
    private String[] incorrect_answers;

    /**
     *
     * @param category
     * @param type
     * @param difficulty
     * @param question
     * @param correct_answer
     * @param incorrect_answers
     */
    public Question(
        @JsonProperty("category") String category,
        @JsonProperty("type") String type, 
        @JsonProperty("difficulty") String difficulty, 
        @JsonProperty("question")  String question,
        @JsonProperty("correct_answer") String correct_answer, 
        @JsonProperty("incorrect_answers") String[] incorrect_answers
    ) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String[] getIncorrect_answers() {
        return incorrect_answers;
    }
}
