package com.bas.trivia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@EnableAutoConfiguration
public class TriviaApplication {
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        SpringApplication.run(TriviaApplication.class, args);
    }
}
