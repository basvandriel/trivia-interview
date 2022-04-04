package com.bas.trivia;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Bas
 */
public interface QuestionRepository extends CrudRepository<Question, Long>  {
    /**
     * 
     * @param question
     * @return 
     */
    Question findFirstByQuestion(String question);
}
