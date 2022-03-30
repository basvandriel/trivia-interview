package com.bas.trivia;

//import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
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
     * The answer to a random question
     */
    private String text;
    
    /**
     * Bad.
     */
    public Answer() {}
    
    public Answer(String text) {
        this.text = text;
    }

    /**
     * Simple ID
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
}
