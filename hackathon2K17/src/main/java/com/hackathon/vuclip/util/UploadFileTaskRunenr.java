package com.hackathon.vuclip.util;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hackathon.vuclip.constants.AppConstants;

@Component
@Scope("prototype")
public class UploadFileTaskRunenr implements Runnable{
	// Save the uploaded file to this folder
	String name;
	MultipartFile file;
	byte[] fileBytes;
	

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	public MultipartFile getFileName() {
		return file;
	}

	public void setFileName(MultipartFile file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	@Override
	public void run() {

		System.out.println(name + " is running");

		try {
			Thread.sleep(5000);
			// Get the file and save it somewhere
			//byte[] bytes = file.getBytes();
			Path path = Paths.get(AppConstants.UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, fileBytes);
			/*redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");*/

		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}

		System.out.println(name + " is running");

	}

}
