package eu.ydp.empiria.gwtflashmedia.client;

import com.google.gwt.user.client.ui.HasWidgets;

import eu.ydp.empiria.gwtflashmedia.client.event.HasFlashMediaHandlers;

public interface FlashVideo extends FlashMedia, HasFlashMediaHandlers {

	public void setParent(HasWidgets parent);
	public void setSize(int width, int height);

	public int getVideoWidth();
	public int getVideoHeight();
}
