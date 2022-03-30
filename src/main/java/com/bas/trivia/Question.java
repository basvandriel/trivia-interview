package com.bas.trivia;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Bas
 */
public class Question {

    /**
     * The category of the question
     */
    private final String category;

    /**
     * The type
     */
    private final String type;

    /**
     * Easy, medium or hard. Could be an enumeration later
     */
    private final String difficulty;

    /**
     * The value of the question
     */
    private final String question;

    /**
     * The one and only correct answer
     */
    private final String correct_answer;

    /**
     * All the incorrect answers
     */
    private final String[] incorrect_answers;

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
        @JsonProperty("question") String question,
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

    @JsonProperty("possible_answers")
    public String[] getPossibleAnswers() {
        List<String> list = new ArrayList(Arrays.asList(this.getIncorrectAnswers()));
        list.add(this.correct_answer);
        
        Collections.shuffle(list);
        
        // String cast
        return list.toArray(new String[0]);
    }

    @JsonIgnore
    public String getCorrectAnswer() {
        return correct_answer;
    }

    @JsonIgnore
    public String[] getIncorrectAnswers() {
        return incorrect_answers;
    }
}
