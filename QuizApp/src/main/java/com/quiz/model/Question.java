package com.quiz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String questionTitle;
	private String options1;
	private String options2;
	private String options3;
	private String options4;
	private String rightAnswer;
	private String difficultyLevel;
	private String category;
	
}
