package eu.ydp.empiria.gwtflashmedia.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface FlashMediaPlayheadUpdateHandler extends EventHandler {

	public void onFlashSoundPositionChange(FlashMediaPlayheadUpdateEvent event);
}
