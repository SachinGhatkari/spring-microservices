package com.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// we are sending this class filed to the client in the main question model
// we have right answer but we should not send it 
// so using this class to elimanate and write code according to the requirement

@Data
public class QuestionWrapper {
	private Integer id;
	private String questionTitle;
	private String options1;
	private String options2;
	private String options3;
	private String options4;
	public QuestionWrapper(Integer id, String questionTitle, String options1, String options2, String options3,
			String options4) {
		super();
		this.id = id;
		this.questionTitle = questionTitle;
		this.options1 = options1;
		this.options2 = options2;
		this.options3 = options3;
		this.options4 = options4;
	} 
	
	
}
