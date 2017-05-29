package eu.ydp.empiria.gwtflashmedia.client.event;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasFlashMediaHandlers {

	HandlerRegistration addFlashMediaLoadedHandler(FlashMediaLoadedHandler handler);
	HandlerRegistration addFlashMediaLoadErrorHandler(FlashMediaLoadErrorHandler handler);
	HandlerRegistration addFlashMediaPlayHandler(FlashMediaPlayHandler handler);
	HandlerRegistration addFlashMediaStopHandler(FlashMediaStopHandler handler);
	HandlerRegistration addFlashMediaPauseHandler(FlashMediaPauseHandler handler);
	HandlerRegistration addFlashMediaPositionChangeHandler(FlashMediaPlayheadUpdateHandler handler);
	HandlerRegistration addFlashMediaCompleteHandler(FlashMediaCompleteHandler handler);
	HandlerRegistration addFlashMediaVolumeChangeHandler(FlashMediaVolumeChangeHandler handler);
	HandlerRegistration addFlashMediaMuteChangeHandler(FlashMediaMuteChangeHandler handler);
	HandlerRegistration addFlashMediaMetadataHandler(FlashMediaMetadataHandler handler);
}
