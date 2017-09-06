package com.hackathon.vuclip.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hackathon.vuclip.constants.AppConstants;

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
	public @ModelAttribute("videoList") String home(Map<String, Object> model) {
		
		File folder = new File(AppConstants.UPLOADED_FOLDER);
		File[] files = folder.listFiles();
		
		List<String> filesList = new ArrayList<>();
		
		for(int i=0; i<files.length; i++) {
			if(files[i].getName().endsWith(".mp4")) {
				
				String filenameToAdd = files[i].getName().substring(0, files[i].getName().length() - 4);
				filesList.add(filenameToAdd);
				
			}
		}
		
		model.put("videos", filesList);
		
		return "home";
	}
	
	@RequestMapping("/upload")
	public String upload(Map<String, Object> model) {
		return "upload";
	}
	
	@RequestMapping(value = "/play", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String play(@RequestParam(name = "id") Integer id) {
		System.out.println("To player");
		System.out.println(id);
		

		List<String> resultList = new ArrayList<>();
		String videoFileName = getVideoFile(id);
		resultList.add(videoFileName);
		
		if(Files.exists(Paths.get(AppConstants.UPLOADED_FOLDER_SUBTITLE + videoFileName + "_EN.vtt"))) {
			resultList.add(videoFileName + "_EN");
		} else {
			resultList.add("NOTEXISTING");
		}
		if(Files.exists(Paths.get(AppConstants.UPLOADED_FOLDER_SUBTITLE + videoFileName + "_HI.vtt"))) {
			resultList.add(videoFileName + "_HI");
		} else {
			resultList.add("NOTEXISTING");
		}
		if(Files.exists(Paths.get(AppConstants.UPLOADED_FOLDER_SUBTITLE + videoFileName + "_FR.vtt"))) {
			resultList.add(videoFileName + "_FR");
		} else {
			resultList.add("NOTEXISTING");
		}
		if(Files.exists(Paths.get(AppConstants.UPLOADED_FOLDER_SUBTITLE + videoFileName + "_GE.vtt"))) {
			resultList.add(videoFileName + "_GE");
		} else {
			resultList.add("NOTEXISTING");
		}
		if(Files.exists(Paths.get(AppConstants.UPLOADED_FOLDER_SUBTITLE + videoFileName + "_IT.vtt"))) {
			resultList.add(videoFileName + "_IT");
		} else {
			resultList.add("NOTEXISTING");
		}
		return new Gson().toJson(resultList);
		
		//return videoFileName;
	}
	
	@RequestMapping("/player")
	public String player(Map<String, Object> model) {
		return "player";
	}
	
	public String getVideoFile(int index) {
		File folder = new File(AppConstants.UPLOADED_FOLDER);
		File[] files = folder.listFiles();
		
		List<String> filesList = new ArrayList<>();
		
		for(int i=0; i<files.length; i++) {
			if(files[i].getName().endsWith(".mp4")) {
				filesList.add(files[i].getName().substring(0, files[i].getName().length() - 4));
			}
		}
		
		return filesList.get(index - 1);
	}

}
