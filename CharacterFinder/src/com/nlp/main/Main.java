package com.nlp.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import com.nlp.XMLParser.XMLToObject;
import com.nlp.dataobject.Sentence;
import com.nlp.dataobject.Word;

public class Main {

	public static void main(String args[]){
		System.out.println("entering file");
		String filename = "M:\\Fall-2015\\Advanced_NLP\\Assignment3\\newinput.txt.xml";
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		
		HashMap<Integer,String> actors = new HashMap<Integer,String>();
		boolean containsActor = false;
		
		try {
			
			boolean isEmtpy=false;
			
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XMLToObject handler = new XMLToObject();			
			saxParser.parse(new File(filename), handler);
			ArrayList<Sentence> sentences = handler.getText();
			HashMap<Integer, Sentence> sentencesInText =  new HashMap<Integer,Sentence>();
			int counter = 1;
			
			for(Sentence temp : sentences){								
				for(Map.Entry<Integer, Word> w : temp.getSent().entrySet()){
					if(w.getValue().getLemma()!=""){
						isEmtpy=true;
						if(w.getValue().getLemma().matches("\'\'|\"") || w.getValue().getLemma().matches("/?/!")){
							temp.setAConversation(true);
						}
					}								
				}
				if(isEmtpy){
					sentencesInText.put(counter,temp);
					counter++;
					isEmtpy=false;
				}
			}		
			counter = 1;
			
			for(Map.Entry<Integer,Sentence> temp : sentencesInText.entrySet()){
				String actor1 = "",actor2 = "";
				if(temp.getValue().isAConversation()){
										
					for(Map.Entry<Integer, Word> w : temp.getValue().getSent().entrySet()){
						
						if(w.getValue().getNER().equalsIgnoreCase("person")){
							if(actor1==""){
								actor1=w.getValue().getLemma();
							}else{
								actor2=w.getValue().getLemma();
							}
						}
						if( w.getValue().getLemma().contains("say") || w.getValue().getLemma().contains("reply") || w.getValue().getLemma().contains("tell") ||
								w.getValue().getLemma().contains("exclaim") || w.getValue().getLemma().contains("cry")){
							containsActor = true;
						}
						if(containsActor==true && actor1!="" && actor2 != "" ){
							actors.put(counter,actor1 + " :: " + actor2);							
							counter++;							
							actor1="";
							actor2="";
						}
					}								
				}			
			}	
			
			for(Map.Entry<Integer, String> key : actors.entrySet()){
				System.out.println("Conversation number " + key.getKey() + " and actors are " + key.getValue());
			}		
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
