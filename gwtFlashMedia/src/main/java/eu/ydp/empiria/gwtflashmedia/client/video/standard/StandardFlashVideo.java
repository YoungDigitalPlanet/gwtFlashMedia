package eu.ydp.empiria.gwtflashmedia.client.video.standard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;

import eu.ydp.empiria.gwtflashmedia.client.FlashMediaBase;
import eu.ydp.empiria.gwtflashmedia.client.FlashVideo;
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

public class StandardFlashVideo extends FlashMediaBase implements FlashVideo, StandardFlashVideoContainerListener {

	private String src; 
	private int width = 320;
	private int height = 240;
	private HasWidgets parent;
	private Panel widgetToReplace;
	private Element movieElement;
	private StandardFlashVideoContainer container;

	private String callbackObjectName = "flashVideoExecutor_callbackObject";
	private int callbackObjectId;
	private String movieObjectId;
	
	public StandardFlashVideo(){
		callbackObjectId = registerCallbacksNative();
		callbackObjectName = "flashVideoExecutor_callbackObject[" + String.valueOf(callbackObjectId) + "]";
	}
	
	@Override
	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height; 
	}

	@Override
	public void setParent(HasWidgets parent) {
		this.parent = parent;
	}
	
	// --------------------------- LOAD & EMBED ---------------------------

	@Override
	public void load() {
		widgetToReplace = new FlowPanel();
		widgetToReplace.getElement().setId(Document.get().createUniqueId());
		parent.add(widgetToReplace);
		
		String swfSrc = GWT.getModuleBaseURL() + "flashVideoExecutor/FlashVideoExecutor.swf";
		String installSrc = GWT.getModuleBaseURL() + "swfobject/expressInstall.swf";
		
		movieObjectId = Document.get().createUniqueId();
		
		container = new StandardFlashVideoContainer(swfSrc, installSrc, src, widgetToReplace.getElement().getId(), String.valueOf(width), String.valueOf(height), callbackObjectName, movieObjectId, this);
		
		widgetToReplace.add(container);
	}

	@Override
	public void onLoad() {
		finalizeEmbed();
	}
	
	private void finalizeEmbed(){		
		movieElement = Document.get().getElementById(movieObjectId);
	}
	

	// --------------------------- CALLBACKS ---------------------------
	
	private native int registerCallbacksNative() /*-{
		var instance = this;
		if (!$doc.flashVideoExecutor_callbackObject)
			$doc.flashVideoExecutor_callbackObject = new Array();
			
		var coid = $doc.flashVideoExecutor_callbackObject.length;
		$doc.flashVideoExecutor_callbackObject[coid] = {};
		
		$doc.flashVideoExecutor_callbackObject[coid].onVideoLoad = function (){
			instance.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::onVideoLoad()();
		}
		$doc.flashVideoExecutor_callbackObject[coid].onVideoMetadata = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::onVideoMetadata(III)(params.duration, params.videoWidth, params.videoHeight);
		}
		$doc.flashVideoExecutor_callbackObject[coid].onVideoPlay = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::onVideoPlay(I)(params.position);
		}
		$doc.flashVideoExecutor_callbackObject[coid].onVideoPause = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::onVideoPause(I)(params.position);
		}
		$doc.flashVideoExecutor_callbackObject[coid].onVideoStop = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::onVideoStop(I)(params.position);
		}
		$doc.flashVideoExecutor_callbackObject[coid].onVideoComplete = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::onVideoComplete(I)(params.position);
		}
		$doc.flashVideoExecutor_callbackObject[coid].onVideoVolumeChange = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::onVideoVolumeChange(IZ)(params.volume, params.mute);
		}
		$doc.flashVideoExecutor_callbackObject[coid].onVideoMuteChange = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::onVideoMuteChange(IZ)(params.volume, params.mute);
		}
		$doc.flashVideoExecutor_callbackObject[coid].onPlayheadUpdate = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::onVideoPlayheadUpdate(I)(params.position);
		}
		$doc.flashVideoExecutor_callbackObject[coid].onVideoError = function (params){
			instance.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::onVideoError()();
		}
		return coid;
		
	}-*/;

	private void onVideoLoad(){
		fireEvent(new FlashMediaLoadedEvent());
	}
	
	private void onVideoError(){
		fireEvent(new FlashMediaLoadErrorEvent("Connection error"));
	}

	private void onVideoMetadata(int duration, int videoWidth, int videoHeight){
		fireEvent(new FlashMediaMetadataEvent(duration, videoWidth, videoHeight));
	}
	
	private void onVideoPlay(int position){
		fireEvent(new FlashMediaPlayEvent(position));
	}
	
	private void onVideoPause(int position){
		fireEvent(new FlashMediaPauseEvent(position));
	}
	
	private void onVideoStop(int position){
		fireEvent(new FlashMediaStopEvent(position));
	}
	
	private void onVideoComplete(int position){
		fireEvent(new FlashMediaCompleteEvent(position));
	}
	
	private void onVideoVolumeChange(int volume, boolean mute){
		fireEvent(new FlashMediaVolumeChangeEvent(volume, mute));
	}
	
	private void onVideoMuteChange(int volume, boolean mute){
		fireEvent(new FlashMediaMuteChangeEvent(volume, mute));
	}

	private void onVideoPlayheadUpdate(int position){
		fireEvent(new FlashMediaPlayheadUpdateEvent(position));
	}
	
	// --------------------------- CALLS ---------------------------

	@Override
	public native void play()/*-{
		this.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::movieElement.playVideo();
	}-*/;

	@Override
	public native void stop() /*-{
		this.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::movieElement.stopVideo();
	}-*/;

	@Override
	public native void pause() /*-{
		this.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::movieElement.pauseVideo();
	}-*/;

	@Override
	public native void setPlayheadTime(int position) /*-{
		this.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::movieElement.setPosition(position);
	}-*/;

	@Override
	public native int getPlayheadTime() /*-{
		return this.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::movieElement.getPosition();
	}-*/;

	@Override
	public native int getDuration() /*-{
		return this.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::movieElement.getDuration();
	}-*/;

	@Override
	public native void setVolume(int value) /*-{
		this.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::movieElement.setVolume(value);
	}-*/;

	@Override
	public native int getVolume() /*-{
		return this.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::movieElement.getVolume();
	}-*/;

	@Override
	public native void setMute(boolean value) /*-{
		this.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::movieElement.setMute(value);
	}-*/;

	@Override
	public native boolean getMute()/*-{
		return this.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::movieElement.getMute();
	}-*/;

	@Override
	public void free() {

	}

	@Override
	public native int getVideoWidth() /*-{
		return this.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::movieElement.getOriginalVideoWidth();
	}-*/;

	@Override
	public native int getVideoHeight() /*-{
		return this.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideo::movieElement.getOriginalVideoHeight();
	}-*/;

}
