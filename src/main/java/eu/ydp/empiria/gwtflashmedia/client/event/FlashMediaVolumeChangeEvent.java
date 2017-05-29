package eu.ydp.empiria.gwtflashmedia.client.event;


public class FlashMediaVolumeChangeEvent extends FlashMediaVolumeEvent<FlashMediaVolumeChangeHandler> {

	public FlashMediaVolumeChangeEvent(int volume, boolean mute) {
		super(volume, mute);
	}

	private static Type<FlashMediaVolumeChangeHandler> TYPE = new Type<FlashMediaVolumeChangeHandler>();

	public static Type<FlashMediaVolumeChangeHandler> getType(){
		return TYPE;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashMediaVolumeChangeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FlashMediaVolumeChangeHandler handler) {
		handler.onFlashSoundVolumeChange(this);
	}

}
