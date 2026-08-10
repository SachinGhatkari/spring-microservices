package com.quiz.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quiz.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String Category);

	@Query(value = "select * from question q where q.category =:category ORDER BY RAND() LIMIT:numQ",nativeQuery = true)
//	@Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
//                                                                  mistakes made here the space between the Limit and NUMQ
	List<Question> findRandomQuestionsByCategry(String category, int numQ);
}
