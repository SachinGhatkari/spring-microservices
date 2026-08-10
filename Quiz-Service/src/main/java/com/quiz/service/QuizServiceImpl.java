package com.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.feign.QuizInterface;
import com.quiz.model.QuestionWrapper;
import com.quiz.model.Quiz;
import com.quiz.model.Response;
import com.quiz.repo.QuizRepo;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	QuizRepo quizRepo;
	
	@Autowired
	QuizInterface quizInterface;
	 
	
	@Override
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
//		RestTemplate
//		List<Integer> question = // call generate URL -- Rest Template http;//localhost:8080/question/generate
		List<Integer> questions= quizInterface.getQuestionforQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizRepo.save(quiz);
		return new ResponseEntity<>("created Quiz successfully",HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Quiz quiz=quizRepo.findById(id).get(); // to handle null value use optional class 
		List<Integer> questionsFromDb= quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionsId(questionsFromDb);
		return questions;
	}

	@Override
	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

		int right =quizInterface.getScore(responses).getBody();
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

	
}
