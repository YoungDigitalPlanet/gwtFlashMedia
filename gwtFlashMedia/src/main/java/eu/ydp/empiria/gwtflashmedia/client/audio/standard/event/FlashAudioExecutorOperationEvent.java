package eu.ydp.empiria.gwtflashmedia.client.audio.standard.event;

public class FlashAudioExecutorOperationEvent extends FlashAudioExecutorEventBase {

	private int position;
	private FlashAudioExecutorOperationType type;
	private int length;

	public FlashAudioExecutorOperationEvent(int id, FlashAudioExecutorOperationType type, int position, int length){
		super(id);
		this.position = position;
		this.type = type;
		this.length = length;
	}


	public int getPosition() {
		return position;
	}
	
	public int getLength() {
		return length;
	}
	
	public FlashAudioExecutorOperationType getType(){
		return type;
	}
}
