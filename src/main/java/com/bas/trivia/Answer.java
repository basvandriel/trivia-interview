package com.bas.trivia;

//import javax.persistence.Entity;
import java.io.Serializable;
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
