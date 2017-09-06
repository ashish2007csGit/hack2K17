package com.hackathon.vuclip.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hackathon.vuclip.constants.AppConstants;
import com.hackathon.vuclip.util.AppConfig;
import com.hackathon.vuclip.util.SubtitleGeneraterTaskRunner;

@Controller
public class VideoUploadController {
	
	

	@GetMapping("/upload")
	public String index() {
		return "upload";
	}

	@PostMapping("/uploadVideo")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("checkboxName") String [] languages, RedirectAttributes redirectAttributes) throws InterruptedException {
		

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
	   
	 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	   ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
	   
		 
		for (int i = 0; i < languages.length; i++) {
			System.out.println("language from UI is  "+languages[i]);
			Thread.sleep(5000);
			
			switch (languages[i]) {
			case AppConstants.EN:
				taskRunner(file, languages, context, taskExecutor, i);
				break;
			case AppConstants.HI:
				taskRunner(file, languages, context, taskExecutor, i);
				break;
			case AppConstants.FR:
				taskRunner(file, languages, context, taskExecutor, i);
				break;
			case AppConstants.GE:
				taskRunner(file, languages, context, taskExecutor, i);
				break;
			case AppConstants.RU:
				taskRunner(file, languages, context, taskExecutor, i);
				break;

			default:
				break;
			}
		}
		
		/*System.out.println("cb_hindi "+cb_hindi);
		System.out.println("cb_french "+cb_french);
		System.out.println("cb_german "+cb_german);
		System.out.println("cb_russian "+cb_russian);
		*/
		// Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		/*
		 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		 ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
		 UploadFileTaskRunenr uploadFileTask =  (UploadFileTaskRunenr) context.getBean("uploadFileTaskRunenr");
		 
		 uploadFileTask.setName("uploadFileTask");
		 try {
			Path path = Paths.get(AppConstants.UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, file.getBytes());
			 
			 SRTGenerateTaskRunner srtGenerateTask = (SRTGenerateTaskRunner) context.getBean("sRTGenerateTaskRunner");
			 srtGenerateTask.setName("srtGenerateTask");
			 srtGenerateTask.setFile(file);
			 taskExecutor.execute(srtGenerateTask);
			 
			 System.out.println("out side of task");
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded '" + file.getOriginalFilename() + "'");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		return "redirect:/home";
	}

	private void taskRunner(MultipartFile file, String[] languages, AnnotationConfigApplicationContext context,
			ThreadPoolTaskExecutor taskExecutor, int i) {
		SubtitleGeneraterTaskRunner englishSubtitleGeneraterTaskBean = (SubtitleGeneraterTaskRunner) context.getBean("subtitleGeneraterTask");
		englishSubtitleGeneraterTaskBean.setName("subtitleGenerateTask for "+languages[i]);
		englishSubtitleGeneraterTaskBean.setFile(file);
		englishSubtitleGeneraterTaskBean.setLanguage(languages[i]);
		taskExecutor.execute(englishSubtitleGeneraterTaskBean);
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}

}
