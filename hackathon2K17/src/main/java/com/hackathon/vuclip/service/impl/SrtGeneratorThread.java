/**
 * 
 */
package com.hackathon.vuclip.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.context.annotation.Bean;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.result.WordResult;

/**
 * @author abhave
 *
 */
public class SrtGeneratorThread extends Thread {

	File audioFile;
	String videoFileName;
	
	@Bean
	public SrtGeneratorThread srtGeneratorThread() {
		return new SrtGeneratorThread();
	}
	
	@Override
	public void run() {
		System.out.println("Thread created");
		super.run();
		
		Configuration configuration = new Configuration();

		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		// configuration.setSampleRate(8000);

		StreamSpeechRecognizer recognizer;
		try {
			recognizer = new StreamSpeechRecognizer(configuration);
			InputStream stream = new FileInputStream(audioFile);

			recognizer.startRecognition(stream);
			SpeechResult result;

			int line_number = 1;
			PrintWriter writer = new PrintWriter(videoFileName.substring(0, videoFileName.length() - 4) + ".srt",
					"UTF-8");

			while ((result = recognizer.getResult()) != null) {

				List<WordResult> list = result.getWords();

				if (!list.isEmpty()) {
					int numOfWords = list.size();
					writer.println(line_number);
					writer.println(formatTime(list.get(0).getTimeFrame().getStart()) + " --> "
							+ formatTime(list.get(numOfWords - 1).getTimeFrame().getEnd()));
					writer.println(result.getHypothesis());
					writer.println();
					line_number++;
				}

			}
			writer.close();
			recognizer.stopRecognition();

			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String formatTime(long n) {
		return DurationFormatUtils.formatDuration(n, "HH:mm:ss,SSS");
	}
	
}
