package eu.ydp.empiria.gwtflashmedia.client.event;


public class FlashMediaPlayheadUpdateEvent extends FlashMediaOperationEvent<FlashMediaPlayheadUpdateHandler> {

	public FlashMediaPlayheadUpdateEvent(int position) {
		super(position);
	}

	private static Type<FlashMediaPlayheadUpdateHandler> TYPE = new Type<FlashMediaPlayheadUpdateHandler>();
	
	public static Type<FlashMediaPlayheadUpdateHandler> getType(){
		return TYPE;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashMediaPlayheadUpdateHandler> getAssociatedType() {
		return TYPE;
	}
	
	@Override
	protected void dispatch(FlashMediaPlayheadUpdateHandler handler) {
		handler.onFlashSoundPositionChange(this);	
	}

}
