package com.question.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.question.model.Question;
import com.question.model.QuestionWrapper;
import com.question.model.Response;
import com.question.repo.QuestionRepo;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepo questionRepo;

	@Override
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<List<Question>> findQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

	}

	@Override
	public ResponseEntity<String> addQuestions(Question question) {

		try {
			questionRepo.save(question);
			return new ResponseEntity<>("success", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);

	}

	@Override
	public ResponseEntity<String> deleteQuestionsById(Integer id) {
		questionRepo.deleteById(id);
		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Integer>> createQuestionForQuiz(String category, Integer numQ) {
		List<Integer> question = questionRepo.findRandomQuestionsByCategry(category, numQ);

		return new ResponseEntity<>(question, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionsIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		for (Integer id : questionsIds) {
//				Question question = questionRepo.findById(id)
//		                .orElseThrow(() ->
//		                        new RuntimeException("Question not found with id: " + id));
//
//		        questions.add(question);
			questions.add(questionRepo.findById(id).get());

		}

		for (Question q : questions) {
			QuestionWrapper questionWrapper = new QuestionWrapper();
			questionWrapper.setId(q.getId());
			questionWrapper.setQuestionTitle(q.getQuestionTitle());
			questionWrapper.setOptions1(q.getOptions1());
			questionWrapper.setOptions2(q.getOptions2());
			questionWrapper.setOptions3(q.getOptions3());
			questionWrapper.setOptions4(q.getOptions4());
			wrappers.add(questionWrapper);
			
		}
		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int right = 0;
		
		
		for (Response response : responses) {
			Question question = questionRepo.findById(response.getId()).get();
			if (response.getResponse().equals(question.getRightAnswer()))
				right++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

}
