package eu.ydp.empiria.gwtflashmedia.client.audio.standard.event;

public class FlashAudioExecutorErrorEvent extends FlashAudioExecutorEventBase {

	private String message;
	private FlashAudioExecutorErrorType type;

	public FlashAudioExecutorErrorEvent(FlashAudioExecutorErrorType type, int id, String message) {
		super(id);
		this.message = message;
		this.type = type;
	}
	
	public String getMessage(){
		return message;
	}
	
	public FlashAudioExecutorErrorType getType(){
		return type;
	}

}
