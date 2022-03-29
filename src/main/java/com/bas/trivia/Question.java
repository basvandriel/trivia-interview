package com.bas.trivia;

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
    public Question(String category, String type, String difficulty, String question, String correct_answer, String[] incorrect_answers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }
    
    /**
     * Cheap hax
     */
    public Question() {}

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
