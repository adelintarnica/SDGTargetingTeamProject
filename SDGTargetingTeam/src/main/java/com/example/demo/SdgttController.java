package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.Text;
import com.example.demo.service.TextService;

@Controller
public class SdgttController {
	private boolean first = true;
	
	@Autowired
	TextService service;
	
	@GetMapping("/")
	public String getOccurencies() {
		if(first) {
			service.uploadFromRecords();
			first = false;
		} 
		
		return "sdgtt";
	}
	
	@PostMapping("/details")
	public String insertText(@RequestParam("atext") String atext, ModelMap modelMap) {
		Text txt = new Text(atext, service.getWordCounterHashMap(atext).toString());

		if(!service.getRepo().existsById(txt.getSentence())) {
			service.getRepo().save(txt);
			service.saveToRecords(txt);
		}
		
		modelMap.put("atext",txt.getSentence());
		modelMap.put("aWordCountMap",service.getWordCounterHashMap(txt.getSentence()));
		modelMap.put("ajson", service.toJSONObject(txt));
		
		return "details";
	}
	
	@RequestMapping("/shutdown") 
	public void shutdownSdgtt() {
		System.exit(0);
	}
	
}
