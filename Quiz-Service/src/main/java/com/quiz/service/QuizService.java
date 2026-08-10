package com.quiz.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.quiz.model.QuestionWrapper;
import com.quiz.model.Response;

public interface QuizService {

	ResponseEntity<String> createQuiz(String category, int numQ, String title);
	ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);
	ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses);
	
	
}
