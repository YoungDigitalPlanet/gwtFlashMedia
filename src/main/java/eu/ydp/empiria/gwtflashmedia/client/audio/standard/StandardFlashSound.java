package eu.ydp.empiria.gwtflashmedia.client.audio.standard;

import eu.ydp.empiria.gwtflashmedia.client.FlashMediaBase;
import eu.ydp.empiria.gwtflashmedia.client.FlashSound;
import eu.ydp.empiria.gwtflashmedia.client.audio.standard.event.FlashAudioExecutorErrorEvent;
import eu.ydp.empiria.gwtflashmedia.client.audio.standard.event.FlashAudioExecutorEventsListener;
import eu.ydp.empiria.gwtflashmedia.client.audio.standard.event.FlashAudioExecutorOperationEvent;
import eu.ydp.empiria.gwtflashmedia.client.audio.standard.event.FlashAudioExecutorOperationType;
import eu.ydp.empiria.gwtflashmedia.client.audio.standard.event.FlashAudioExecutorVolumeEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaCompleteEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaLoadErrorEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaLoadedEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaMetadataEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaMuteChangeEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaPauseEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaPlayEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaPlayheadUpdateEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaStopEvent;
import eu.ydp.empiria.gwtflashmedia.client.event.FlashMediaVolumeChangeEvent;

public class StandardFlashSound extends FlashMediaBase implements FlashSound, FlashAudioExecutorEventsListener {

	private String src; 
	private boolean loaded = false;
	private boolean playAfterLoad = false;
	private int positionAfterLoad = 0;
	private int id;
	private FlashAudioExecutorMovie executor;
	
	@Override
	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public void load() {
		executor = FlashAudioExecutorMovie.getInstance();
		id = executor.createSound(src, this);
	}

	@Override
	public void play() {
		if (checkLoaded()){
			executor.playSound(id);
		} else {
			playAfterLoad = true;
		}
	}

	@Override
	public void stop() {
		if (checkLoaded()){
			executor.stopSound(id);
		} else {
			playAfterLoad = false;
		}
	}

	@Override
	public void pause() {
		if (checkLoaded()){
			executor.pauseSound(id);
		} else {
			playAfterLoad = false;
		}
	}

	@Override
	public void setPlayheadTime(int playheadTime) {
		if (checkLoaded()){
			executor.setPosition(id, playheadTime);
		} else {
			positionAfterLoad = playheadTime;
		}
	}

	@Override
	public int getPlayheadTime() {
		if (checkLoaded()){
			return executor.getPosition(id);
		}
		return 0;
	}
	
	@Override
	public void free() {
		executor.freeSound(id);
	}

	@Override
	public int getDuration() {
		return executor.getLength(id);
	}

	@Override
	public void setVolume(int value) {
		executor.setVolume(id, value);
	}

	@Override
	public int getVolume() {
		return executor.getVolume(id);
	}

	@Override
	public void setMute(boolean value) {
		executor.setMute(id, value);
	}

	@Override
	public boolean getMute() {
		return executor.getMute(id);
	}
	
	private boolean checkLoaded(){
		return loaded;
	}


	@Override
	public void onOperation(FlashAudioExecutorOperationEvent event) {
		if (event.getType() == FlashAudioExecutorOperationType.LOADED){
			loaded = true;
		}
		switch (event.getType()){
			case LOADED:
				fireEvent(new FlashMediaLoadedEvent());
				break;
			case PLAY:
				fireEvent(new FlashMediaPlayEvent(event.getPosition()));
				break;
			case STOP:
				fireEvent(new FlashMediaStopEvent(event.getPosition()));
				break;
			case PAUSE:
				fireEvent(new FlashMediaPauseEvent(event.getPosition()));
				break;
			case COMPLETE:
				fireEvent(new FlashMediaCompleteEvent(event.getPosition()));
				break;
			case PLAYHEAD_UPDATE:
				fireEvent(new FlashMediaPlayheadUpdateEvent(event.getPosition()));
				break;
			case LOAD_PROGRESS:
				if (event.getLength() > 0)
					fireEvent(new FlashMediaMetadataEvent(event.getLength(), 0, 0));
				break;
			case LOAD_COMPLETE:
				fireEvent(new FlashMediaMetadataEvent(event.getLength(), 0, 0));
				break;
		}
		

		if (event.getType() == FlashAudioExecutorOperationType.LOADED  &&  playAfterLoad){
			if (positionAfterLoad > 0){
				setPlayheadTime(positionAfterLoad);
				play();
			} else {
				play();
			}
		}
	}

	@Override
	public void onVolume(FlashAudioExecutorVolumeEvent event) {
		switch (event.getType()){
		case VOLUME_CHANGE:
			fireEvent(new FlashMediaVolumeChangeEvent(event.getVolume(), event.isMute()));
			break;
		case MUTE_CHANGE:
			fireEvent(new FlashMediaMuteChangeEvent(event.getVolume(), event.isMute()));
			break;
		}
		
	}

	@Override
	public void onError(FlashAudioExecutorErrorEvent event) {
		switch (event.getType()){
			case LOAD_ERROR:
				fireEvent(new FlashMediaLoadErrorEvent(event.getMessage()));
				break;
		}
		
	}
	

}
