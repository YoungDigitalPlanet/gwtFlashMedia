package eu.ydp.empiria.gwtflashmedia.client.event;


public class FlashMediaMuteChangeEvent extends FlashMediaVolumeEvent<FlashMediaMuteChangeHandler> {

	public FlashMediaMuteChangeEvent(int volume, boolean mute) {
		super(volume, mute);
	}
	private static Type<FlashMediaMuteChangeHandler> TYPE = new Type<FlashMediaMuteChangeHandler>();

	public static Type<FlashMediaMuteChangeHandler> getType(){
		return TYPE;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashMediaMuteChangeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FlashMediaMuteChangeHandler handler) {
		handler.onFlashSoundMuteChange(this);
	}

}
