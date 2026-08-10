package com.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.model.Question;
import com.quiz.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;

	@GetMapping("/allquestion")
	public ResponseEntity<List<Question>> getQuestion() {
		return  questionService.getAllQuestions();
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
		return questionService.findQuestionByCategory(category);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestions(@RequestBody Question question) {
		return questionService.addQuestions(question);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id) {
		return	questionService.deleteQuestionsById(id);
		
	}
}
