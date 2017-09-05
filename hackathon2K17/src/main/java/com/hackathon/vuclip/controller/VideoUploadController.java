package com.hackathon.vuclip.controller;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.hackathon.vuclip.constants.AppConstants;
import com.hackathon.vuclip.util.AppConfig;
import com.hackathon.vuclip.util.SRTGenerateTaskRunner;
import com.hackathon.vuclip.util.UploadFileTaskRunenr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Controller
public class VideoUploadController {
	

	@GetMapping("/upload")
	public String index() {
		return "upload";
	}

	@PostMapping("/uploadVideo") // //new annotation since 4.3
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		// Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		
		 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		 ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
		 UploadFileTaskRunenr uploadFileTask =  (UploadFileTaskRunenr) context.getBean("uploadFileTaskRunenr");
		 
		 uploadFileTask.setName("uploadFileTask");
		 try {
			Path path = Paths.get(AppConstants.UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, file.getBytes());
			//byte[] bytes = file.getBytes();
			//uploadFileTask.setFileName(file);
			//uploadFileTask.setFileBytes(bytes);
			// taskExecutor.execute(uploadFileTask);
			 
			 SRTGenerateTaskRunner srtGenerateTask = (SRTGenerateTaskRunner) context.getBean("sRTGenerateTaskRunner");
			 srtGenerateTask.setName("srtGenerateTask");
			 srtGenerateTask.setFile(file);
			 taskExecutor.execute(srtGenerateTask);
			 
			 System.out.println("out side of task");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

/*	// Get the file and save it somewhere
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		Files.write(path, bytes);*/

		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded '" + file.getOriginalFilename() + "'");

		return "redirect:/uploadStatus";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}

}
