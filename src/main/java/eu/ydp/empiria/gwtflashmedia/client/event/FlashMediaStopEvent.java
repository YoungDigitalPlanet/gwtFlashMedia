package eu.ydp.empiria.gwtflashmedia.client.event;


public class FlashMediaStopEvent extends FlashMediaOperationEvent<FlashMediaStopHandler> {

	public FlashMediaStopEvent(int position) {
		super(position);
	}

	private static Type<FlashMediaStopHandler> TYPE = new Type<FlashMediaStopHandler>();
	
	public static Type<FlashMediaStopHandler> getType(){
		return TYPE;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashMediaStopHandler> getAssociatedType() {
		return TYPE;
	}
	
	@Override
	protected void dispatch(FlashMediaStopHandler handler) {
		handler.onFlashSoundStop(this);
	}

}
