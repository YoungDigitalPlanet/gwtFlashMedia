package eu.ydp.empiria.gwtflashmedia.client.event;


public class FlashMediaLoadedEvent extends FlashMediaOperationEvent<FlashMediaLoadedHandler> {


	public FlashMediaLoadedEvent() {
		super(0);
	}

	private static Type<FlashMediaLoadedHandler> TYPE = new Type<FlashMediaLoadedHandler>();
	
	public static Type<FlashMediaLoadedHandler> getType(){
		return TYPE;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashMediaLoadedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FlashMediaLoadedHandler handler) {
		handler.onFlashSoundLoaded(this);
	}

}
