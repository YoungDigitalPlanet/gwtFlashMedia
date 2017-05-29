package eu.ydp.empiria.gwtflashmedia.client.event;


public class FlashMediaPlayEvent extends FlashMediaOperationEvent<FlashMediaPlayHandler> {

	public FlashMediaPlayEvent(int position) {
		super(position);
	}

	private static Type<FlashMediaPlayHandler> TYPE = new Type<FlashMediaPlayHandler>();
	
	public static Type<FlashMediaPlayHandler> getType(){
		return TYPE;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<FlashMediaPlayHandler> getAssociatedType() {
		return TYPE;
	}
	
	@Override
	protected void dispatch(FlashMediaPlayHandler handler) {
		handler.onFlashSoundPlay(this);
	}

}
