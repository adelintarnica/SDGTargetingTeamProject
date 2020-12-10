package com.example.demo.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.Text;
import com.example.demo.repository.TextRepository;

@Service
public class TextService {
	private static String filePath = "src/main/resources/records.txt";
	
	@Autowired
    private TextRepository repo;
	
	public TextRepository getRepo() {
		return repo;
	}
	
	public HashMap<String, Integer> getWordCounterHashMap(String sentence) {
		String regex = "[\\p{L}\\p{M}\\p{N}]+(?:\\p{P}[\\p{L}\\p{M}\\p{N}]+)*|[\\p{C}]";
		Matcher matcher = Pattern.compile(regex).matcher(sentence.toLowerCase()); 
		HashMap<String, Integer> wordCounterHashMap = new HashMap<String, Integer>();
		
		while(matcher.find()) {
			if(wordCounterHashMap.containsKey(matcher.group())) {
				wordCounterHashMap.put(matcher.group(), wordCounterHashMap.get(matcher.group())+1);
			} else {
				wordCounterHashMap.put(matcher.group(), 1);
			}
		}
		
		return wordCounterHashMap;
	}
	
	public JSONObject toJSONObject(Text text) {
		JSONObject json = new JSONObject();
		json.put("text", text.getSentence());
		json.put("words", getWordCounterHashMap(text.getSentence()));
		return json;
	}
	
	public void uploadFromRecords() {
		try
		{
			Scanner fileInput = new Scanner(new File(filePath));
			fileInput.useDelimiter("[|\n]");
			while(fileInput.hasNext()) {
				repo.save(new Text(fileInput.next().trim(), fileInput.next().trim()));
			}
			fileInput.close();
		} catch (Exception e) {
			
		}
	}
	
	public void saveToRecords(Text text) {
		String sentence = text.getSentence();
		String wordCounter = text.getWordCounter();
		
		try
		{
			FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println(sentence+"|"+wordCounter);
			
			pw.flush();
			pw.close();
		} catch (Exception e) {
			
		}
	}
}
