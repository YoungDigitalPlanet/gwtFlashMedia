package eu.ydp.empiria.gwtflashmedia.client.audio.standard.event;

public interface FlashAudioExecutorEventsListener {

	public void onOperation(FlashAudioExecutorOperationEvent event);
	public void onVolume(FlashAudioExecutorVolumeEvent event);
	public void onError(FlashAudioExecutorErrorEvent event);
}
