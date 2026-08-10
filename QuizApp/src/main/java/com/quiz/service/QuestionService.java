package com.quiz.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.quiz.model.Question;

public interface QuestionService {

	ResponseEntity<List<Question>> getAllQuestions();

	ResponseEntity<List<Question>> findQuestionByCategory(String category);

	ResponseEntity<String> addQuestions(Question question);
	
	ResponseEntity<String> deleteQuestionsById(Integer id);
}
