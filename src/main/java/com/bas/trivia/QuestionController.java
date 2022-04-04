package com.bas.trivia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Bas
 */
@CrossOrigin(maxAge = 3600)
@RestController
public class QuestionController {

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private QuestionRepository repository;

    /**
     * Retrieves 5 multi-choice questions
     *
     * @return
     */
    @GetMapping("/questions")
    public Question[] questions() {
        ResponseEntity<OpenTDBResponse> response = restTemplate.getForEntity(Constants.OPENTDB_URI, OpenTDBResponse.class, Constants.QUESTION_AMOUNT);
        Question[] questions = response.getBody().getResults();
        
        // todo null check for response.getBody()
        // For now, iterate through the question and create answer objects
        // from the results. Those can be buffered. Later in a TTL redis-store
        for (Question question : questions) {            
            repository.save(question);
        }
        return questions;
    }

    /**
     *
     * @param payload
     * @return
     */
    @RequestMapping(value = "/checkanswer", method = RequestMethod.POST, headers = "Accept=application/json")
    public boolean check(@RequestBody CheckAnswerRequestPayload payload) {
        String question = payload.getQuestion();
        String answer = payload.getAnswer();

        // If both don't exist, no match
        if (answer == null || question == null) {
            return false;
        }
        
        // The answer object we need to check against
        final Question checksum = repository.findFirstByQuestion(question);

        // And see if it exists
        if (checksum == null) return false;
        
        // Don't bother upper/lower case for matching        
        return answer.equalsIgnoreCase(checksum.getCorrectAnswer());
    }
}
