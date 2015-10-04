package com.nlp.dataobject;

public class Word {

	private String NER;
	private String POS;
	private String Lemma;
	
	public Word(String NER,String POS,String Lemma) {
		// TODO Auto-generated constructor stub
		this.NER = NER;
		this.POS = POS;
		this.Lemma = Lemma;
	}
	
	public Word() {
		// TODO Auto-generated constructor stub
	}

	public String getNER() {
		return NER;
	}
	public void setNER(String nER) {
		NER = nER;
	}
	public String getPOS() {
		return POS;
	}
	public void setPOS(String pOS) {
		POS = pOS;
	}
	public String getLemma() {
		return Lemma;
	}
	public void setLemma(String lemma) {
		Lemma = lemma;
	}	
	
}
