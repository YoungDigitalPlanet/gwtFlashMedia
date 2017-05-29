package eu.ydp.empiria.gwtflashmedia.client.event;


public class FlashMediaPauseEvent extends FlashMediaOperationEvent<FlashMediaPauseHandler> {

	public FlashMediaPauseEvent(int position) {
		super(position);
	}
	
	private static Type<FlashMediaPauseHandler> TYPE = new Type<FlashMediaPauseHandler>();
	
	public static Type<FlashMediaPauseHandler> getType(){
		return TYPE;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashMediaPauseHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FlashMediaPauseHandler handler) {
		handler.onFlashSoundPause(this);
	}

}
