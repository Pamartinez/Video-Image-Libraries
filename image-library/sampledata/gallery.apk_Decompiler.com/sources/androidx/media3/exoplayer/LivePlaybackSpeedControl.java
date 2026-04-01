package androidx.media3.exoplayer;

import androidx.media3.common.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface LivePlaybackSpeedControl {
    float getAdjustedPlaybackSpeed(long j2, long j3);

    long getTargetLiveOffsetUs();

    void notifyRebuffer();

    void setLiveConfiguration(MediaItem.LiveConfiguration liveConfiguration);

    void setTargetLiveOffsetOverrideUs(long j2);
}
