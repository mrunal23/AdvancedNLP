package com.nlp.posobject;

import java.util.HashMap;

public class POSTag {
	
	HashMap<String, Integer> NounCount;
	HashMap<String, Integer> LemmaCount;
	
	public POSTag() {
		// TODO Auto-generated constructor stub
		NounCount = new HashMap<>();
	}

	public HashMap<String, Integer> getNounCount() {
		return NounCount;
	}

	public void setPOSCount(HashMap<String, Integer> NounCount) {
		this.NounCount = NounCount;
	}

	public HashMap<String, Integer> getLemmaCount() {
		return LemmaCount;
	}

	public void setLemmaCount(HashMap<String, Integer> lemmaCount) {
		LemmaCount = lemmaCount;
	}	
	
}
