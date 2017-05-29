package eu.ydp.empiria.gwtflashmedia.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public abstract class FlashMediaVolumeEvent<H extends EventHandler> extends GwtEvent<H> {

	private int volume;
	private boolean mute;
	
	public FlashMediaVolumeEvent(int volume, boolean mute){
		this.volume = volume;
		this.mute = mute;
	}

	public int getVolume() {
		return volume;
	}

	public boolean isMute() {
		return mute;
	}

}
