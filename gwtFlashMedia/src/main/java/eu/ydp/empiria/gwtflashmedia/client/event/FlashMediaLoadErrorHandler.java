package eu.ydp.empiria.gwtflashmedia.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface FlashMediaLoadErrorHandler extends EventHandler {

	public void onFlashSoundLoadError(FlashMediaLoadErrorEvent event);
	
}
