package com.bas.trivia;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

class CheckAnswerRequestPayload {

    private String question;

    private String answer;

    public CheckAnswerRequestPayload() {
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}

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
        ResponseEntity<OpenTDBResponse> response = restTemplate.getForEntity("https://opentdb.com/api.php?amount=5&type=multiple", OpenTDBResponse.class);
        Question[] questions = response.getBody().getResults();

        // For now, iterate through the question and create answer objects
        // from the results. Those can be buffered. Later in a TTL redis-store
        for (int i = 0; i < questions.length; i++) {
            Answer answer = new Answer(questions[i].getCorrectAnswer(), questions[i].getQuestion());
            repository.save(answer);
        }
        return questions;
    }

    @RequestMapping(value = "/checkanswer", method = RequestMethod.POST, headers = "Accept=application/json")
    public boolean check(@RequestBody CheckAnswerRequestPayload payload) {
        String question = payload.getQuestion();
        String answer = payload.getAnswer();

        // If both don't exist, no match
        if (answer == null || question == null) {
            return false;
        }
        
        // The answer object we need to check against
        final Answer checksum = repository.findFirstByQuestion(question);

        // And see if it exists
        if (checksum == null) return false;
        
        // Don't bother upper/lower case for matching        
        return answer.equalsIgnoreCase(checksum.toString());

    }
}
