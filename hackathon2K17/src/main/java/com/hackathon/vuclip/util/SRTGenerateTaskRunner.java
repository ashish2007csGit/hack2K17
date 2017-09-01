package com.hackathon.vuclip.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hackathon.vuclip.constants.AppConstants;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.result.WordResult;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;

@Component
@Scope("prototype")
public class SRTGenerateTaskRunner implements Runnable {

	@Bean(name = "sRTGenerateTaskRunner")
	public SRTGenerateTaskRunner helloWorld() {
		return new SRTGenerateTaskRunner();
	}

	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file2) {
		this.file = file2;
	}

	public String getName() {
		return name;
	}

	byte[] fileBytes;

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	String name;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void run() {

		System.out.println(name + " is running");

		try {
			Thread.sleep(5000);
			String originalFilename = file.getOriginalFilename();
			File file = new File(AppConstants.UPLOADED_FOLDER +originalFilename );
			//File convertedFile = 
			File audioFile = extractAudioFromVideo(file);
			Configuration configuration = new Configuration();
			String videoFileName = file.getName();

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

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(name + " is stopped");

	}

	public File extractAudioFromVideo(File file2) {
		File audioFile = new File(file2.getAbsolutePath().substring(0, file2.getAbsolutePath().length() - 4) + ".wav");
System.out.println("audioFile "+audioFile.getAbsolutePath());
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec(AppConstants.AUDIO_CODEC);
		audio.setBitRate(AppConstants.BIT_RATE);
		audio.setChannels(AppConstants.CHANNELS);
		audio.setSamplingRate(AppConstants.SAMPLING_RATE);

		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat(AppConstants.AUDIO_FORMAT);
		attrs.setAudioAttributes(audio);
		Encoder encoder = new Encoder();

		try {
			encoder.encode(file2, audioFile, attrs);
		} catch (IllegalArgumentException | EncoderException e) {
			e.printStackTrace();
		}

		return audioFile;
	}

	private static String formatTime(long n) {
		return DurationFormatUtils.formatDuration(n, "HH:mm:ss,SSS");
	}

	/*public File convert(MultipartFile file) throws IllegalStateException, IOException
	{
		Path rootLocation = Paths.get(AppConstants.UPLOADED_FOLDER_For_SINGLE_FILE);
		Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()));
		
		return new File (Files.);
	}*/

}
