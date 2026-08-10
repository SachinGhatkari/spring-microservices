package com.question.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.question.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String Category);

	
//	@Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
//                                                                  mistakes made here the space between the Limit and NUMQ
	
	@Query(value = "select q.id from question q where q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	List<Integer> findRandomQuestionsByCategry(String category, int numQ);
}
