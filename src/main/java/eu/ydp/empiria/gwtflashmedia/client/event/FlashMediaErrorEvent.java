package eu.ydp.empiria.gwtflashmedia.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public abstract class FlashMediaErrorEvent<H extends EventHandler> extends GwtEvent<H> {

	private String message;

	public FlashMediaErrorEvent(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}

}
