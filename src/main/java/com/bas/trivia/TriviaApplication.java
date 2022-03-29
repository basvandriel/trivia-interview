package com.bas.trivia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String questions() {
        return "List f questions belongs here";
    }
    
    @PostMapping("/checkanswer")
    public String check(){
        return "Yeah!";
    }
}
