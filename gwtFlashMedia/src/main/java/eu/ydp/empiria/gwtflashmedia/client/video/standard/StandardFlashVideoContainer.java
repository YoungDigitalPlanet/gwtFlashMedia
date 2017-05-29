package eu.ydp.empiria.gwtflashmedia.client.video.standard;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

public class StandardFlashVideoContainer extends Composite {
	
	private String swfSrc;
	private String installSrc;
	private String videoSrc;
	private String height;
	private String id;
	private String width;
	private String movieId;
	private String callbackObjectName;
	private StandardFlashVideoContainerListener listener;

	public StandardFlashVideoContainer(String swfSrc, String installSrc, String videoSrc, String id, String width, String height, String callbackObjectName, String movieId, StandardFlashVideoContainerListener listener){
		this.swfSrc = swfSrc;
		this.installSrc = installSrc;
		this.videoSrc = videoSrc;
		this.id = id;
		this.width = width;
		this.height = height;
		this.callbackObjectName = callbackObjectName;
		this.movieId = movieId;
		this.listener = listener;
		initWidget(new FlowPanel());
	}
	
	@Override
	public void onLoad(){
		embedFlashMovie(swfSrc, installSrc, videoSrc, id, width, height, callbackObjectName, movieId);
	}
		
	private native void embedFlashMovie(String swfSrc, String installSrc, String videoSrc, String id, String width, String height, String callbackObjectName, String movieId)/*-{
		var flashvars = {name : callbackObjectName , 
			source : videoSrc , 
			width : width , 
			height : height};
		var params = {};
		var attributes = {id : movieId};
		var instance = this;
		$wnd.swfobject.embedSWF(swfSrc, id, width, height, "9", installSrc, flashvars, params, attributes, function () {
			instance.@eu.ydp.empiria.gwtflashmedia.client.video.standard.StandardFlashVideoContainer::finalizeEmbed()();
		});
	}-*/;

	
	private void finalizeEmbed(){		
		listener.onLoad();
	}

}
