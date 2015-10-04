package com.nlp.XMLParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.nlp.dataobject.*;

public class XMLToObject extends DefaultHandler{
	public static int counter = 1;
	private ArrayList<Sentence> text;
	private Sentence tempSentence;
	private Word tempWord;
	
	private boolean lemma;
	private boolean pos;
	private boolean ner;
	
	public XMLToObject() {
		// TODO Auto-generated constructor stub		
		text = new ArrayList<>();
		tempSentence = null;
		tempWord = null;
		lemma = false;
		ner = false;
		pos = false;
	}
	
	public ArrayList<Sentence> getText() {
		return text;
	}

	public void setText(ArrayList<Sentence> text) {
		this.text = text;
	}	
	
	public Sentence getTempSentence() {
		return tempSentence;
	}

	public void setTempSentence(Sentence tempSentence) {
		this.tempSentence = tempSentence;
	}

	public Word getTempWord() {
		return tempWord;
	}

	public void setTempWord(Word tempWord) {
		this.tempWord = tempWord;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes){
		
		if(qName.equalsIgnoreCase("Sentence")){
			tempSentence = new Sentence();
		}else if(qName.equalsIgnoreCase("token")){
			tempWord=new Word();
		}else if(qName.equalsIgnoreCase("lemma")){
			lemma = true;
		}else if(qName.equalsIgnoreCase("pos")){
			pos = true;
		}else if(qName.equalsIgnoreCase("ner")){
			ner = true;
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName){
		if(qName.equalsIgnoreCase("token")){
			if(tempWord.getLemma()!=""){
				tempSentence.getSent().put(counter, tempWord);
				counter++;
			}
		}else if(qName.equalsIgnoreCase("sentence")){			
			if(tempWord.getLemma()!="")	
				text.add(tempSentence);
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length){
		if(lemma){
			String str = new String(ch,start,length);
			
			if(str!=null){
				tempWord.setLemma(str);			
				lemma=false;
			}
		}
		else if(pos){
			String str = new String(ch,start,length);
			if(str!=null){			
				tempWord.setPOS(str);			
				pos = false;
			}
		}else if(ner){
			String str = new String(ch,start,length);
			if(str!=null){
				tempWord.setNER(str);			
				ner = false;
			}
		}
	}
}