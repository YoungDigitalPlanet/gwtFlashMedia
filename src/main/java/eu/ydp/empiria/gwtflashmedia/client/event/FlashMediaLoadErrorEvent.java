package eu.ydp.empiria.gwtflashmedia.client.event;


public class FlashMediaLoadErrorEvent extends FlashMediaErrorEvent<FlashMediaLoadErrorHandler> {

	public FlashMediaLoadErrorEvent(String message) {
		super(message);
	}

	private static final Type<FlashMediaLoadErrorHandler> TYPE = new Type<FlashMediaLoadErrorHandler>();
	
	public static Type<FlashMediaLoadErrorHandler> getType(){
		return TYPE;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashMediaLoadErrorHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FlashMediaLoadErrorHandler handler) {
		handler.onFlashSoundLoadError(this);
	}

}
