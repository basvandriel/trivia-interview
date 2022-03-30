package com.bas.trivia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Bas
 */
@Entity
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The answer to a linked question
     */
    @Column(name = "value")
    private String text;

    /**
     *
     */
    private String question;
    
    /**
     * Bad.
     */
    public Answer() {}
    
    public Answer(String text, String question) {
        this.text = text;
        this.question = question;
    }

    /**
     * We only need the value here
     * 
     * @return 
     */
    @Override
    public String toString() {
        return text;
    }
    
    /**
     * Simple ID
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
    
    public String getQuestion() {
        return question;
    }
}
