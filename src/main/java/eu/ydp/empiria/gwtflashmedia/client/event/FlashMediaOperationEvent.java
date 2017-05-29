package eu.ydp.empiria.gwtflashmedia.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public abstract class FlashMediaOperationEvent<H extends EventHandler> extends GwtEvent<H> {

	private int playheadTime;

	public FlashMediaOperationEvent(int playheadTime){
		this.playheadTime = playheadTime;
	}
	
	public int getPlayheadTime(){
		return playheadTime;
	}
	

}
