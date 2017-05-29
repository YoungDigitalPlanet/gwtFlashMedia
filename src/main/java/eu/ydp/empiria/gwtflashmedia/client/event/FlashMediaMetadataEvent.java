package eu.ydp.empiria.gwtflashmedia.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class FlashMediaMetadataEvent extends GwtEvent<FlashMediaMetadataHandler> {

	private int duration;
	private int videoWidth;
	private int videoHeight;

	public FlashMediaMetadataEvent(int duration, int videoWidth, int videoHeight){
		this.duration = duration;
		this.videoWidth = videoWidth;
		this.videoHeight = videoHeight;
	}
	

	public int getDuration() {
		return duration;
	}

	public int getVideoWidth() {
		return videoWidth;
	}

	public int getVideoHeight() {
		return videoHeight;
	}


	private static Type<FlashMediaMetadataHandler> TYPE = new Type<FlashMediaMetadataHandler>();
	
	public static Type<FlashMediaMetadataHandler> getType(){
		return TYPE;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashMediaMetadataHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FlashMediaMetadataHandler handler) {
		handler.onFlashMediaMetadataEvent(this);
	}

}
