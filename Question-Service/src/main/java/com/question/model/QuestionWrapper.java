package com.question.model;

import lombok.Data;
import lombok.NoArgsConstructor;

// we are sending this class filed to the client in the main question model
// we have right answer but we should not send it 
// so using this class to elimanate and write code according to the requirement

@Data
@NoArgsConstructor
public class QuestionWrapper {
	private Integer id;
	private String questionTitle;
	private String options1;
	private String options2;
	private String options3;
	private String options4;
	
	
	
	
}
