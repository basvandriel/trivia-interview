package com.bas.trivia;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author Bas
 */
@Entity
public class Question implements Serializable {
    /**
     * Unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * The category of the question
     */
    @Transient
    private String category;

    /**
     * The type
     */
    @Transient
    private String type;

    /**
     * Easy, medium or hard. Could be an enumeration later
     */
    @Transient
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
    @Transient
    private String[] incorrect_answers;

    /**
     * 
     */
    public Question() {}

    /**
     * 
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

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }
    
    
}
