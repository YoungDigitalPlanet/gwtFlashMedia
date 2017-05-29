package eu.ydp.empiria.gwtflashmedia.client.event;


public class FlashMediaCompleteEvent extends FlashMediaOperationEvent<FlashMediaCompleteHandler> {
	
	public FlashMediaCompleteEvent(int position) {
		super(position);
	}


	private static Type<FlashMediaCompleteHandler> TYPE = new Type<FlashMediaCompleteHandler>();
	
	public static Type<FlashMediaCompleteHandler> getType(){
		return TYPE;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashMediaCompleteHandler> getAssociatedType() {
		return TYPE;
	}


	@Override
	protected void dispatch(FlashMediaCompleteHandler handler) {
		handler.onFlashSoundComplete(this);
	}

}
