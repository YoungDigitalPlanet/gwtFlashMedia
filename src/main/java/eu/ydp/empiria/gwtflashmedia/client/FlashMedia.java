package eu.ydp.empiria.gwtflashmedia.client;

public interface FlashMedia {

	public void setSrc(String src);
	public void load();
	
	public void play();	
	public void stop();
	public void pause();
	public void setPlayheadTime(int playheadTime);
	public int getPlayheadTime();
	public void setVolume(int value);
	public int getVolume();
	public void setMute(boolean value);
	public boolean getMute();

	public int getDuration();

	public void free();
}
