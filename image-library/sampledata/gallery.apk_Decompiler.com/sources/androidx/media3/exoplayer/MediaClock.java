package androidx.media3.exoplayer;

import androidx.media3.common.PlaybackParameters;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaClock {
    PlaybackParameters getPlaybackParameters();

    long getPositionUs();

    boolean hasSkippedSilenceSinceLastCall() {
        return false;
    }

    void setPlaybackParameters(PlaybackParameters playbackParameters);
}
