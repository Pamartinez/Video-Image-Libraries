package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Renderer extends PlayerMessage.Target {
    void disable();

    void enable(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j2, boolean z, boolean z3, long j3, long j8, MediaSource.MediaPeriodId mediaPeriodId);

    RendererCapabilities getCapabilities();

    long getDurationToProgressUs(long j2, long j3) {
        if (getState() != 1) {
            return 10000;
        }
        if (isReady() || isEnded()) {
            return 1000000;
        }
        return 10000;
    }

    MediaClock getMediaClock();

    long getReadingPositionUs();

    int getState();

    SampleStream getStream();

    int getTrackType();

    boolean hasReadStreamToEnd();

    void init(int i2, PlayerId playerId, Clock clock);

    boolean isCurrentStreamFinal();

    boolean isEnded();

    boolean isReady();

    void maybeThrowStreamError();

    void release();

    void render(long j2, long j3);

    void replaceStream(Format[] formatArr, SampleStream sampleStream, long j2, long j3, MediaSource.MediaPeriodId mediaPeriodId);

    void reset();

    void resetPosition(long j2);

    void setCurrentStreamFinal();

    void setTimeline(Timeline timeline);

    void start();

    void stop();

    void enableMayRenderStartOfStream() {
    }

    void setPlaybackSpeed(float f, float f5) {
    }
}
