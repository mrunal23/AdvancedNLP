package com.nlp.dataobject;

import java.util.ArrayList;
import java.util.HashMap;

public class Sentence {

	private HashMap<Integer,Word> sent;
	private boolean isAConversation;
		
	public Sentence() {
		// TODO Auto-generated constructor stub
		sent = new HashMap<Integer,Word>();
		isAConversation = false;
	}
	
	public boolean isAConversation() {
		return isAConversation;
	}

	public void setAConversation(boolean isAConversation) {
		this.isAConversation = isAConversation;
	}	

	public HashMap<Integer,Word> getSent() {
		return sent;
	}

	public void setSent(HashMap<Integer,Word> sent) {
		this.sent = sent;
	}	
	
}
