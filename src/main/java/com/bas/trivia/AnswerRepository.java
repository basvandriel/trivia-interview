package com.bas.trivia;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Bas
 */

public interface AnswerRepository extends CrudRepository<Answer, Long> {}
