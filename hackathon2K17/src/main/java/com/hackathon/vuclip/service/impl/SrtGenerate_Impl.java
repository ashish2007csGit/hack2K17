/**
 * 
 */
package com.hackathon.vuclip.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.hackathon.vuclip.constants.SrtConstants;
import com.hackathon.vuclip.service.SrtGenerate;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;


/**
 * @author abhave
 *
 */
public class SrtGenerate_Impl implements SrtGenerate {

	@Bean
	public SrtGenerate_Impl srtGenerate_Impl() {
		return new SrtGenerate_Impl();
	}
	
	@Autowired
	SrtGeneratorThread srtThread;
	
	@Override
	public void generateSrtFile(File audioFile, String videoFileLocation) {
		srtThread.start();

	}

	@Override
	public File extractAudioFromVideo(File videoFile) {

		File audioFile = new File(
				videoFile.getAbsolutePath().substring(0, videoFile.getAbsolutePath().length() - 4) + ".wav");

		AudioAttributes audio = new AudioAttributes();
		audio.setCodec(SrtConstants.AUDIO_CODEC);
		audio.setBitRate(SrtConstants.BIT_RATE);
		audio.setChannels(SrtConstants.CHANNELS);
		audio.setSamplingRate(SrtConstants.SAMPLING_RATE);

		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat(SrtConstants.AUDIO_FORMAT);
		attrs.setAudioAttributes(audio);
		Encoder encoder = new Encoder();
		
		try {
			encoder.encode(videoFile, audioFile, attrs);
		} catch (IllegalArgumentException | EncoderException e) {
			e.printStackTrace();
		}
		
		return audioFile;
	}

}
