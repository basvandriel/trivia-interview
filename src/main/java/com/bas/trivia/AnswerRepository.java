package com.bas.trivia;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Bas
 */

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    /**
     * 
     * @param question
     * @return 
     */
    Answer findFirstByQuestion(String question);
}
