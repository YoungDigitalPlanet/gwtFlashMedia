package eu.ydp.empiria.gwtflashmedia.client.audio.standard;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

import eu.ydp.empiria.gwtflashmedia.client.audio.standard.event.FlashAudioExecutorErrorEvent;
import eu.ydp.empiria.gwtflashmedia.client.audio.standard.event.FlashAudioExecutorErrorType;
import eu.ydp.empiria.gwtflashmedia.client.audio.standard.event.FlashAudioExecutorEventsListener;
import eu.ydp.empiria.gwtflashmedia.client.audio.standard.event.FlashAudioExecutorOperationEvent;
import eu.ydp.empiria.gwtflashmedia.client.audio.standard.event.FlashAudioExecutorOperationType;
import eu.ydp.empiria.gwtflashmedia.client.audio.standard.event.FlashAudioExecutorVolumeEvent;
import eu.ydp.empiria.gwtflashmedia.client.audio.standard.event.FlashAudioExecutorVolumeType;

public class FlashAudioExecutorMovie  {

	private static FlashAudioExecutorMovie instance;
	private static final String CALLBACK_OBJECT_NAME = "flashAudioExecutor_callbackObject";
	private static final String MOVIE_OBJECT_ID = "flashAudioExecutor_movie";

	private boolean isReady = false;
	private String movieId;
	private Element movieElement;

	private List<FlashAudioExecutorEventsListener> listeners;
	private List<String> pendingSounds;

	private FlashAudioExecutorMovie(){
		listeners = new ArrayList<FlashAudioExecutorEventsListener>();
		pendingSounds = new ArrayList<String>();

		FlowPanel mainPanel = new FlowPanel();
		mainPanel.setWidth("0px");
		mainPanel.setHeight("0px");
		mainPanel.getElement().getStyle().setOverflow(Overflow.HIDDEN);
		FlowPanel moviePanel = new FlowPanel();
		movieId = Document.get().createUniqueId();
		moviePanel.getElement().setId(movieId);
		
		mainPanel.add(moviePanel);
		
		RootPanel.get().add(mainPanel, -1000, -1000);

		registerCallbacksNative();
		embedMovie();
	}

	public static FlashAudioExecutorMovie getInstance(){
		if (instance == null){
			instance = new FlashAudioExecutorMovie() ;
		}
		return instance;
	}

	private void embedMovie() {

		String swfSrc = GWT.getModuleBaseURL() + "flashAudioExecutor/FlashAudioExecutor.swf";
		String installSrc = GWT.getModuleBaseURL() + "swfobject/expressInstall.swf";
		
		embedMovieNative(swfSrc, installSrc, movieId, CALLBACK_OBJECT_NAME, MOVIE_OBJECT_ID);
	}
	
	private native void embedMovieNative(String swfSrc, String installSrc, String id, String callbackObjectName, String movieId)/*-{
		var flashvars = {name:callbackObjectName};
		var params = {}
		var attributes = {id:movieId};
		var instance = this;
		$wnd.swfobject.embedSWF(swfSrc, id, 100, 100, "9", installSrc, flashvars, params, attributes, function () {
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::finalizeEmbed()();
		});
	}-*/;
	
	private void finalizeEmbed(){		
		movieElement = Document.get().getElementById(MOVIE_OBJECT_ID);
	}

	// --------------------------- CALLS ---------------------------
	
	public int createSound(String src, FlashAudioExecutorEventsListener listener){
		listeners.add(listener);
		int soundId = listeners.size() - 1; 
		if (isReady){
			doCreateSound(soundId, src);
		} else {
			pendingSounds.add(src);
		}
		return soundId;
	}
	
	private void doCreateSound(int id, String src){
		createSoundNative(movieElement, id, src);
	}
	
	private native void createSoundNative(Element element, int id, String src)/*-{
		element.createSound(id, src, false);
	}-*/;
	
	public void freeSound(int id){
		freeSoundNative(movieElement, id);
	}
	
	private native void freeSoundNative(Element element, int id)/*-{
		element.freeSound(id);
	}-*/;
	
	public void playSound(int id){
		playSoundNative(movieElement, id);
	}
	
	private native void playSoundNative(Element element, int id)/*-{
		element.playSound(id);
	}-*/;
	
	public void pauseSound(int id){
		pauseSoundNative(movieElement, id);
	}
	
	private native void pauseSoundNative(Element element, int id)/*-{
		element.pauseSound(id);
	}-*/;
	
	public void stopSound(int id){
		stopSoundNative(movieElement, id);
	}
	
	private native void stopSoundNative(Element element, int id)/*-{
		element.stopSound(id);
	}-*/;
	
	public void setPosition(int id, int position){
		setPositionNative(movieElement, id, position);
	}
	
	private native void setPositionNative(Element element, int id, int position)/*-{
		element.setPosition(id, position);
	}-*/;
	
	public int getPosition(int id){
		return getPositionNative(movieElement, id);
	}
	
	private native int getPositionNative(Element element, int id)/*-{
		return element.getPosition(id);
	}-*/;

	public int getLength(int id) {
		return getLengthNative(movieElement, id);
	}

	public native int getLengthNative(Element element, int id)/*-{
		return element.getLength(id);		
	}-*/;


	public void setVolume(int id, int value) {
		setVolumeNative(movieElement, id, value);
	}
	
	private native void setVolumeNative(Element element, int id, int value)/*-{
		element.setVolume(id, value);
	}-*/;

	public int getVolume(int id) {
		return getVolumeNative(movieElement, id);
	}
	
	private native int getVolumeNative(Element element, int id)/*-{
		return element.getVolume(id);
	}-*/;

	public void setMute(int id, boolean value) {
		setMuteNative(movieElement, id, value);
	}
	
	private native void setMuteNative(Element element, int id, boolean value)/*-{
		element.setMute(id, value);
	}-*/;

	public boolean getMute(int id) {
		return getMuteNative(movieElement, id);
	}
	
	private native boolean getMuteNative(Element element, int id)/*-{
		return element.getMute(id);
	}-*/;
	
	private boolean checkId(int id ){
		return (id >= 0  &&  id < listeners.size()  &&  listeners.get(id) != null);
	}
	
	// --------------------------- CALLBACKS ---------------------------
	
	private native void registerCallbacksNative() /*-{
		var instance = this;
		$doc.flashAudioExecutor_callbackObject = {};
		$doc.flashAudioExecutor_callbackObject.onReady = function (){
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::onReady()();
		}
		$doc.flashAudioExecutor_callbackObject.onSoundLoaded = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::onSoundOperationEvent(Ljava/lang/String;III)("LOADED",params.id, params.position, params.length);
		}
		$doc.flashAudioExecutor_callbackObject.onSoundLoadError = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::onSoundErrorEvent(ILjava/lang/String;)(params.id, params.meesage);
		}
		
		$doc.flashAudioExecutor_callbackObject.onSoundPlay = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::onSoundOperationEvent(Ljava/lang/String;III)("PLAY",params.id, params.position, params.length);
		}
		$doc.flashAudioExecutor_callbackObject.onSoundStop = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::onSoundOperationEvent(Ljava/lang/String;III)("STOP",params.id, params.position, params.length);
		}
		$doc.flashAudioExecutor_callbackObject.onSoundPause = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::onSoundOperationEvent(Ljava/lang/String;III)("PAUSE",params.id, params.position, params.length);
		}
		
		$doc.flashAudioExecutor_callbackObject.onSoundPlayheadUpdate = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::onSoundOperationEvent(Ljava/lang/String;III)("PLAYHEAD_UPDATE",params.id, params.position, params.length);
		}
		$doc.flashAudioExecutor_callbackObject.onSoundComplete = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::onSoundOperationEvent(Ljava/lang/String;III)("COMPLETE",params.id, params.position, params.length);
		}
		$doc.flashAudioExecutor_callbackObject.onSoundLoadProgress = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::onSoundOperationEvent(Ljava/lang/String;III)("LOAD_PROGRESS",params.id, params.position, params.length);
		}
		$doc.flashAudioExecutor_callbackObject.onSoundLoadComplete = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::onSoundOperationEvent(Ljava/lang/String;III)("LOAD_COMPLETE",params.id, params.position, params.length);
		}
		$doc.flashAudioExecutor_callbackObject.onVolumeChange = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::onSoundVolumeEvent(Ljava/lang/String;IIZ)("VOLUME_CHANGE", params.id, params.volume, params.mute);
		}
		$doc.flashAudioExecutor_callbackObject.onMuteChange = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.audio.standard.FlashAudioExecutorMovie::onSoundVolumeEvent(Ljava/lang/String;IIZ)("MUTE_CHANGE", params.id, params.volume, params.mute);
		}
		
	}-*/;
	
	private void onReady(){
		isReady = true;
		loadPendingSounds();
	}
	
	private void loadPendingSounds(){
		for (int s = 0 ; s < pendingSounds.size() ; s ++){
			doCreateSound(s, pendingSounds.get(s));
		}
	}


	private void onSoundOperationEvent(String type, int id, int position, int length){
		if (checkId(id)){
			listeners.get(id).onOperation(new FlashAudioExecutorOperationEvent(id, FlashAudioExecutorOperationType.valueOf(type), position, length));
		}
	}

	private void onSoundVolumeEvent(String type, int id, int volume, boolean mute){
		if (checkId(id)){
			listeners.get(id).onVolume(new FlashAudioExecutorVolumeEvent(id, FlashAudioExecutorVolumeType.valueOf(type), volume, mute));
		}
	}
	
	private void onSoundErrorEvent(int id, String message){
		if (checkId(id)){
			listeners.get(id).onError(new FlashAudioExecutorErrorEvent(FlashAudioExecutorErrorType.LOAD_ERROR, id, message));
		}
	}
	

}
