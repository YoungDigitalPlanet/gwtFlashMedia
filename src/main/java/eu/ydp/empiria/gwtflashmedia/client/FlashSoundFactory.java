package eu.ydp.empiria.gwtflashmedia.client;

import com.google.gwt.core.client.GWT;

public class FlashSoundFactory {

	public static FlashSound createSound(String src) {
		FlashSound flashSound = GWT.create(FlashSound.class);
		flashSound.setSrc(src);
		flashSound.load();
		return flashSound;
	}
	
	public static void init(){
		((FlashSoundInitializer)GWT.create(FlashSoundInitializer.class)).init();
	}
}
