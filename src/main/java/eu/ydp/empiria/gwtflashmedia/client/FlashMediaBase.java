package eu.ydp.empiria.gwtflashmedia.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;

import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaCompleteEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaCompleteHandler;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaLoadErrorEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaLoadErrorHandler;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaLoadedEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaLoadedHandler;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaMetadataEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaMetadataHandler;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaMuteChangeEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaMuteChangeHandler;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaPauseEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaPauseHandler;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaPlayEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaPlayHandler;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaPlayheadUpdateEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaPlayheadUpdateHandler;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaStopEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaStopHandler;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaVolumeChangeEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaVolumeChangeHandler;
import eu.ydp.empiria.gwtflashmedia.client.event.HasFlashMediaHandlers;

public abstract class FlashMediaBase implements HasFlashMediaHandlers, FlashMedia {
	
	
	protected EventBus eventBus = new SimpleEventBus();

	@Override
	public HandlerRegistration addFlashMediaLoadedHandler(FlashMediaLoadedHandler handler) {
		return eventBus.addHandler(FlashMediaLoadedEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addFlashMediaLoadErrorHandler(FlashMediaLoadErrorHandler handler) {
		return eventBus.addHandler(FlashMediaLoadErrorEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addFlashMediaPlayHandler(FlashMediaPlayHandler handler) {
		return eventBus.addHandler(FlashMediaPlayEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addFlashMediaStopHandler(FlashMediaStopHandler handler) {
		return eventBus.addHandler(FlashMediaStopEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addFlashMediaPauseHandler(FlashMediaPauseHandler handler) {
		return eventBus.addHandler(FlashMediaPauseEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addFlashMediaPositionChangeHandler(FlashMediaPlayheadUpdateHandler handler) {
		return eventBus.addHandler(FlashMediaPlayheadUpdateEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addFlashMediaCompleteHandler(FlashMediaCompleteHandler handler) {
		return eventBus.addHandler(FlashMediaCompleteEvent.getType(), handler);
	}


	@Override
	public HandlerRegistration addFlashMediaVolumeChangeHandler(FlashMediaVolumeChangeHandler handler) {
		return eventBus.addHandler(FlashMediaVolumeChangeEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addFlashMediaMuteChangeHandler(FlashMediaMuteChangeHandler handler) {
		return eventBus.addHandler(FlashMediaMuteChangeEvent.getType(), handler);
	}

	@Override
	public HandlerRegistration addFlashMediaMetadataHandler(FlashMediaMetadataHandler handler) {
		return eventBus.addHandler(FlashMediaMetadataEvent.getType(), handler);
	}
	
	protected void fireEvent(GwtEvent<?> event) {
		eventBus.fireEvent(event);
	}
	
}
