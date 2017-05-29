package eu.ydp.empiria.gwtflashmedia.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface FlashMediaVolumeChangeHandler extends EventHandler {

	public void onFlashSoundVolumeChange(FlashMediaVolumeChangeEvent event);
}
