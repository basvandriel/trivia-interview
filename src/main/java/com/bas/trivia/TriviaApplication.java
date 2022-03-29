package com.bas.trivia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@RestController
public class TriviaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TriviaApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Hi!";
    }

    @GetMapping("/questions")
    public Question[] questions() {
        RestTemplate restTemplete = new RestTemplate();
        ResponseEntity<QuestionResponse> response = restTemplete.getForEntity("https://opentdb.com/api.php?amount=5", QuestionResponse.class);
        QuestionResponse body = response.getBody();
        
        return body.getResults();
    }

    @PostMapping("/checkanswer")
    public String check() {
        return "Yeah!";
    }
}
