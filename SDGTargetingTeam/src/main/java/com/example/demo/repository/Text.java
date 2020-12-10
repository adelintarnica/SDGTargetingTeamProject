package com.example.demo.repository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Text {

	@Id
	private String sentence;
	private String wordCounter;
		
	public Text() {
		super();
	}
	
	public Text(String sentence, String wordCounter) {
		this.sentence = sentence;
		this.wordCounter = wordCounter;
	}
	
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public void setWordCounter(String wordCounter) {
		this.wordCounter = wordCounter;
	}
	
	public String getSentence() {
		return sentence;
	}
	
	public String getWordCounter() {
		return wordCounter;
	}

	@Override
	public String toString() {
		return "Text [sentence=" + sentence + ", wordCounter=" + wordCounter + "]";
	}
}
