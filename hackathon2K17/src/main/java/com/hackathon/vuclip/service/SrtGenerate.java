/**
 * 
 */
package com.hackathon.vuclip.service;

import java.io.File;

import org.springframework.stereotype.Service;

/**
 * @author abhave
 *
 */
@Service
public interface SrtGenerate {
	void generateSrtFile(File audioFile, String videoFileLocation);
	File extractAudioFromVideo(File videoFile);

}
