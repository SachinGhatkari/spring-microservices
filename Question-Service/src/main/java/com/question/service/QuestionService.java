package com.question.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.question.model.Question;
import com.question.model.QuestionWrapper;
import com.question.model.Response;

public interface QuestionService {

	ResponseEntity<List<Question>> getAllQuestions();

	ResponseEntity<List<Question>> findQuestionByCategory(String category);

	ResponseEntity<String> addQuestions(Question question);
	
	ResponseEntity<String> deleteQuestionsById(Integer id);

	ResponseEntity<List<Integer>> createQuestionForQuiz(String category, Integer numQ);

	ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionsIds);

	ResponseEntity<Integer> getScore(List<Response> responses);
}
