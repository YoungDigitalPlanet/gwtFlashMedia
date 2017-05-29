package eu.ydp.empiria.gwtflashmedia.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface FlashMediaLoadedHandler extends EventHandler {

	public void onFlashSoundLoaded(FlashMediaLoadedEvent event);
}
