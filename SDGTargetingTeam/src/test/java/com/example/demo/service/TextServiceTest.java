package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class TextServiceTest {
	
	@Test
	public  void getWordCounterHashMapTest() {
		String regex = "[\\p{L}\\p{M}\\p{N}]+(?:\\p{P}[\\p{L}\\p{M}\\p{N}]+)*|[\\p{C}]";
		Matcher matcher = Pattern.compile(regex).matcher("Ana are are mere, mere, mere, bere, bere si pere. @#%$^&^%&$#%^&%^$ ana".toLowerCase()); 
		HashMap<String, Integer> wordCounterHashMap = new HashMap<String, Integer>();
		
		while(matcher.find()) {
			if(wordCounterHashMap.containsKey(matcher.group())) {
				wordCounterHashMap.put(matcher.group(), wordCounterHashMap.get(matcher.group())+1);
			} else {
				wordCounterHashMap.put(matcher.group(), 1);
			}
		}

		assertEquals(wordCounterHashMap.toString().equals("{ana=2, are=2, si=1, mere=3, bere=2, pere=1}"), true);
	}
	
	@Test
	public void getWordCounterHashMapEmptyTest() {
		String regex = "[\\p{L}\\p{M}\\p{N}]+(?:\\p{P}[\\p{L}\\p{M}\\p{N}]+)*|[\\p{C}]";
		Matcher matcher = Pattern.compile(regex).matcher("".toLowerCase()); 
		HashMap<String, Integer> wordCounterHashMap = new HashMap<String, Integer>();
		
		while(matcher.find()) {
			if(wordCounterHashMap.containsKey(matcher.group())) {
				wordCounterHashMap.put(matcher.group(), wordCounterHashMap.get(matcher.group())+1);
			} else {
				wordCounterHashMap.put(matcher.group(), 1);
			}
		}

		assertEquals(wordCounterHashMap.toString().equals("{}"), true);
	}

}
