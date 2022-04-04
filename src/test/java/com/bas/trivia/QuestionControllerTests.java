package com.bas.trivia;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    QuestionRepository repository;

    @Test
    void testQuestions() throws Exception {
        ResultActions actions = mvc.perform(get("/questions"));
        actions.andExpect(status().isOk());
        actions.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        // $ means root
        actions.andExpect(jsonPath("$").isArray());
        actions.andExpect(jsonPath("$", hasSize(5)));

        assertThat(repository.count() == 5);
    }

    @Test
    void testCheckAnswerWithNullValues() throws Exception {
        CheckAnswerRequestPayload payload = new CheckAnswerRequestPayload(null, null);
        
        String content = new Gson().toJson(payload);
        ResultActions actions = mvc.perform(post("/checkanswer").content(content).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));

        actions.andExpect(status().isOk());
        actions.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));   
        actions.andExpect(jsonPath("$").value("false"));
    }
    
    @Test
    void testCheckAnswerWithNonExistantValues() throws Exception {
        CheckAnswerRequestPayload payload = new CheckAnswerRequestPayload("Een vraag", "Een antwoord");
        
        String content = new Gson().toJson(payload);
        ResultActions actions = mvc.perform(post("/checkanswer").content(content).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));

        actions.andExpect(status().isOk());
        actions.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));   
        actions.andExpect(jsonPath("$").value("false"));
    }
    
    @Test
    void checkAnswerWithMatch() throws Exception {
        Question question = new Question();
        question.setCorrect_answer("Het antwoord");
        question.setQuestion("De vraag");
        
        repository.save(question);
        
        CheckAnswerRequestPayload payload = new CheckAnswerRequestPayload(
            question.getQuestion(),
            question.getCorrectAnswer()
        );
        
        String content = new Gson().toJson(payload);
        ResultActions actions = mvc.perform(post("/checkanswer").content(content).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));

        actions.andExpect(status().isOk());
        actions.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));   
        actions.andExpect(jsonPath("$").value("true"));
    }
}
