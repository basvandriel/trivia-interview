package com.bas.trivia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Bas
 */
@RestController
public class QuestionController {
    @Autowired
    private RestTemplate restTemplate;
    
    
    @Autowired
    private AnswerRepository repository;
    
    /**
     * Retrieves 5 multi-choice questions 
     * 
     * @return 
     */
    @GetMapping("/questions")
    public Question[] questions() {
        ResponseEntity<QuestionResponse> response = restTemplate.getForEntity("https://opentdb.com/api.php?amount=5&type=multiple", QuestionResponse.class);
        Question[] questions = response.getBody().getResults();
        
        return questions;
    }
    
    @PostMapping("/checkanswer")
    public String check() {
        return "Yeah!";
    }
    
    @PostMapping("/add")
    public boolean add() {
        Answer a = new Answer("Hallo!");
        a.setId(10L);
        
        repository.save(a);
        
        
        Iterable<Answer> answers = repository.findAll();
        
        return true;
    }
}
