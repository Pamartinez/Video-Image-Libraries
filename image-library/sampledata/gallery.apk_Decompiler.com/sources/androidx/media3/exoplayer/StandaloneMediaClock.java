package androidx.media3.exoplayer;

import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class StandaloneMediaClock implements MediaClock {
    private long baseElapsedMs;
    private long baseUs;
    private final Clock clock;
    private PlaybackParameters playbackParameters = PlaybackParameters.DEFAULT;
    private boolean started;

    public StandaloneMediaClock(Clock clock2) {
        this.clock = clock2;
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    public long getPositionUs() {
        long mediaTimeUsForPlayoutTimeMs;
        long j2 = this.baseUs;
        if (!this.started) {
            return j2;
        }
        long elapsedRealtime = this.clock.elapsedRealtime() - this.baseElapsedMs;
        PlaybackParameters playbackParameters2 = this.playbackParameters;
        if (playbackParameters2.speed == 1.0f) {
            mediaTimeUsForPlayoutTimeMs = Util.msToUs(elapsedRealtime);
        } else {
            mediaTimeUsForPlayoutTimeMs = playbackParameters2.getMediaTimeUsForPlayoutTimeMs(elapsedRealtime);
        }
        return mediaTimeUsForPlayoutTimeMs + j2;
    }

    public void resetPosition(long j2) {
        this.baseUs = j2;
        if (this.started) {
            this.baseElapsedMs = this.clock.elapsedRealtime();
        }
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters2) {
        if (this.started) {
            resetPosition(getPositionUs());
        }
        this.playbackParameters = playbackParameters2;
    }

    public void start() {
        if (!this.started) {
            this.baseElapsedMs = this.clock.elapsedRealtime();
            this.started = true;
        }
    }

    public void stop() {
        if (this.started) {
            resetPosition(getPositionUs());
            this.started = false;
        }
    }
}
