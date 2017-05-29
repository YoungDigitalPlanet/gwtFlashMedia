package eu.ydp.empiria.gwtflashmedia.client.audio.standard.event;

public class FlashAudioExecutorVolumeEvent extends FlashAudioExecutorEventBase {

	private FlashAudioExecutorVolumeType type;
	private int volume;
	private boolean mute;

	public FlashAudioExecutorVolumeEvent(int id, FlashAudioExecutorVolumeType type, int volume, boolean mute) {
		super(id);
		this.type = type;
		this.volume = volume;
		this.mute = mute;
	}

	public FlashAudioExecutorVolumeType getType() {
		return type;
	}

	public int getVolume() {
		return volume;
	}

	public boolean isMute() {
		return mute;
	}

	
}
