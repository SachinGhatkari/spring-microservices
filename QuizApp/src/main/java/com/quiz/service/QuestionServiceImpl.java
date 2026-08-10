package com.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.model.Question;
import com.quiz.repo.QuestionRepo;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepo questionRepo;
	
	@Override
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;
	}

	@Override
	public ResponseEntity<List<Question>> findQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK)  ;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;
	
	}

	@Override
	public ResponseEntity<String> addQuestions(Question question) {
		
		try {
			questionRepo.save(question);
			return new ResponseEntity<>("success",HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST) ;
		
	}

	@Override
	public ResponseEntity<String> deleteQuestionsById(Integer id) {
		questionRepo.deleteById(id);
		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}

}
