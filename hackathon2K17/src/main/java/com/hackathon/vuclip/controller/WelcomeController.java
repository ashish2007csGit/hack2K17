package com.hackathon.vuclip.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}
	
	@RequestMapping("/home")
	public String home(Map<String, Object> model) {
		//model.put("message", this.message);
		return "home";
	}
	
	@RequestMapping("/upload")
	public String upload(Map<String, Object> model) {
		//model.put("message", this.message);
		return "upload";
	}

}
