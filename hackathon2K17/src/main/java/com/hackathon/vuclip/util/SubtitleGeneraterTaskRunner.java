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
public class SubtitleGeneraterTaskRunner implements Runnable {

	@Bean(name = "subtitleGeneraterTask")
	public SubtitleGeneraterTaskRunner helloWorld() {
		return new SubtitleGeneraterTaskRunner();
	}

	MultipartFile file;
	
	String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

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
			String videoFileName = AppConstants.UPLOADED_FOLDER_SUBTITLE + file.getName();

			//english
			/*configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
			configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
			configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");*/

			//hindi
			/*configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/hindi/hindi.cd_cont_1000");
			configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/hindi/etc/hindi.dic");
			configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/hindi/etc/lm/hindi.dmp");*/
			
			//french
			/*configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/french/cmusphinx-fr-ptm-5.2");
			configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/french/fr.dict");
			configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/french/fr-small.lm.bin");*/
			
			switch (getLanguage()) {
			case AppConstants.EN:
				setConfigurartionLanguage(configuration, AppConstants.PATH_ACCCOUSTIC_EN, AppConstants.PATH_DICT_EN, AppConstants.PATH_LANGUAGE_MODEL_EN);
				break;
			case AppConstants.HI:
				setConfigurartionLanguage(configuration, AppConstants.PATH_ACCOUSITC_HI, AppConstants.PATH_DICT_HI, AppConstants.PATH_LANGUAGE_MODEL_HI);
				break;
			case AppConstants.FR:
				setConfigurartionLanguage(configuration, AppConstants.PATH_ACCOUSITC_FR, AppConstants.PATH_DICT_FR, AppConstants.PATH_LANGUAGE_MODEL_FR);
				break;
			case AppConstants.GE:
				setConfigurartionLanguage(configuration, AppConstants.PATH_ACCOUSITC_GE, AppConstants.PATH_DICT_GE, AppConstants.PATH_LANGUAGE_MODEL_GE);
				break;
			case AppConstants.RU:
				setConfigurartionLanguage(configuration, AppConstants.PATH_ACCOUSITC_RU, AppConstants.PATH_DICT_RU, AppConstants.PATH_LANGUAGE_MODEL_RU);
				break;

			default:
				break;
			}
			
			StreamSpeechRecognizer recognizer;
			try {
				recognizer = new StreamSpeechRecognizer(configuration);
				InputStream stream = new FileInputStream(audioFile);

				recognizer.startRecognition(stream);
				SpeechResult result;

				int line_number = 1;
				PrintWriter writer = new PrintWriter(videoFileName.substring(0, videoFileName.length() - 4) +"_"+getLanguage()+ ".vtt",
						"UTF-8");
				writer.println("WEBVTT");
				writer.println();
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

	private void setConfigurartionLanguage(Configuration configuration, String pathAcccousticEn, String pathDictEn, String pathLanguageModelEn) {
		configuration.setAcousticModelPath(pathAcccousticEn);
		configuration.setDictionaryPath(pathDictEn);
		configuration.setLanguageModelPath(pathLanguageModelEn);
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
		return DurationFormatUtils.formatDuration(n, "HH:mm:ss.SSS");
	}

	/*public File convert(MultipartFile file) throws IllegalStateException, IOException
	{
		Path rootLocation = Paths.get(AppConstants.UPLOADED_FOLDER_For_SINGLE_FILE);
		Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()));
		
		return new File (Files.);
	}*/

}
