package com.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.question.model.Question;
import com.question.model.QuestionWrapper;
import com.question.model.Response;
import com.question.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	Environment environment;
	


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
	
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> getQuestionforQuiz(@RequestParam String category, @RequestParam Integer numQ){
		return	questionService.createQuestionForQuiz(category,numQ);
		
	}
	
	@PostMapping("/getQuestions")
	public  ResponseEntity<List<QuestionWrapper>> getQuestionsId(@RequestBody List<Integer> questionsIds){
		System.out.println(	environment.getProperty("local.server.port"));
		return questionService.getQuestionsFromId(questionsIds);
	}
	
	@PostMapping("/score")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.getScore(responses);
		
	}
	
	//generte
	// getquestionbyQuestion ID 
	// getScore 
}
