package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface LoadControl {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Parameters {
        public final long bufferedDurationUs;
        public final long lastRebufferRealtimeMs;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final boolean playWhenReady;
        public final long playbackPositionUs;
        public final float playbackSpeed;
        public final PlayerId playerId;
        public final boolean rebuffering;
        public final long targetLiveOffsetUs;
        public final Timeline timeline;

        public Parameters(PlayerId playerId2, Timeline timeline2, MediaSource.MediaPeriodId mediaPeriodId2, long j2, long j3, float f, boolean z, boolean z3, long j8, long j10) {
            this.playerId = playerId2;
            this.timeline = timeline2;
            this.mediaPeriodId = mediaPeriodId2;
            this.playbackPositionUs = j2;
            this.bufferedDurationUs = j3;
            this.playbackSpeed = f;
            this.playWhenReady = z;
            this.rebuffering = z3;
            this.targetLiveOffsetUs = j8;
            this.lastRebufferRealtimeMs = j10;
        }
    }

    Allocator getAllocator();

    long getBackBufferDurationUs(PlayerId playerId);

    void onPrepared(PlayerId playerId);

    void onReleased(PlayerId playerId);

    void onStopped(PlayerId playerId);

    void onTracksSelected(Parameters parameters, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr);

    boolean retainBackBufferFromKeyframe(PlayerId playerId);

    boolean shouldContinueLoading(Parameters parameters);

    boolean shouldContinuePreloading(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j2);

    boolean shouldStartPlayback(Parameters parameters);
}
