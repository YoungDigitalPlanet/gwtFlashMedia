package eu.ydp.empiria.gwtflashmedia.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;

public final class FlashVideoFactory {

	public static FlashVideo createVideo(String src, HasWidgets parent, int width, int height) {
		FlashVideo flashVideo = GWT.create(FlashVideo.class);
		flashVideo.setSrc(src);
		flashVideo.setParent(parent);
		flashVideo.setSize(width, height);
		flashVideo.load();
		return flashVideo;
	}
}
