package eu.ydp.empiria.gwtflashmedia.client.audio.standard;

import eu.ydp.empiria.gwtflashmedia.client.FlashSoundInitializer;

public class StandardFlashSoundInitializer implements FlashSoundInitializer {

	public void init(){
		FlashAudioExecutorMovie.getInstance();
	}
}
