package com.hackathon.vuclip.controller;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import com.hackathon.vuclip.util.AppConfig;
import com.hackathon.vuclip.util.SRTGenerateTaskRunner;
import com.hackathon.vuclip.util.UploadFileTaskRunenr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		 ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
		 UploadFileTaskRunenr uploadFileTask =  (UploadFileTaskRunenr) context.getBean("uploadFileTaskRunenr");
		 uploadFileTask.setName("uploadFileTask");
		 uploadFileTask.setFileName(file);
		 taskExecutor.execute(uploadFileTask);
		 
		 SRTGenerateTaskRunner srtGenerateTask = (SRTGenerateTaskRunner) context.getBean("sRTGenerateTaskRunner");
		 srtGenerateTask.setName("srtGenerateTask");
		 taskExecutor.execute(srtGenerateTask);
		 
		 System.out.println("out side of task");

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
