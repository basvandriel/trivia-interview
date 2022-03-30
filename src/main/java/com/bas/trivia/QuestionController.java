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

        // For now, iterate through the question and create answer objects
        // from the results. Those can be buffered. Later in a TTL redis-store
        for (int i = 0; i < questions.length; i++) {
            Answer answer = new Answer(questions[i].getCorrectAnswer(), questions[i].getQuestion());
            repository.save(answer);
        }
        return questions;
    }

    @PostMapping("/checkanswer")
    public String check() {
        return "Yeah!";
    }
}
