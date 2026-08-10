package com.quiz.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.model.QuestionWrapper;
import com.quiz.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

	@GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionforQuiz(
            @RequestParam("category") String category,
            @RequestParam("numQ") Integer numQ);
	
	@PostMapping("question/getQuestions")
	public  ResponseEntity<List<QuestionWrapper>> getQuestionsId(@RequestBody List<Integer> questionsIds);
	
	@PostMapping("question/score")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
