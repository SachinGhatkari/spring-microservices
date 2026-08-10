package com.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.model.Question;
import com.quiz.model.QuestionWrapper;
import com.quiz.model.Quiz;
import com.quiz.model.Response;
import com.quiz.repo.QuestionRepo;
import com.quiz.repo.QuizRepo;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	QuizRepo quizRepo;
	
	@Autowired
	QuestionRepo questionRepo;
	
	@Override
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> question = questionRepo.findRandomQuestionsByCategry(category,numQ); 
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(question);
		quizRepo.save(quiz);
		return new ResponseEntity<>("created Quiz successfully",HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz=quizRepo.findById(id); // to handle null value use optional class 
		// get the questions based on that id 
		List<Question> questionsFromDb=quiz.get().getQuestions();
		
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		for(Question q : questionsFromDb) {
			// Iterating the for each loop and add the questions to Questions Wrapper which we are sending to User  so thats why we have use QuestionWRapper
			QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOptions1(), q.getOptions2(), q.getOptions3(), q.getOptions4());
			questionsForUser.add(questionWrapper);
		}
		return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz = quizRepo.findById(id).get();
		List<Question> quizQuestions = quiz.getQuestions();
		Integer right=0;
		int i=0;
		for(Response response : responses) {
			if(response.getResponse().equals(quizQuestions.get(i).getRightAnswer()))
				right++;
				
				 i++; 
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

	
}
