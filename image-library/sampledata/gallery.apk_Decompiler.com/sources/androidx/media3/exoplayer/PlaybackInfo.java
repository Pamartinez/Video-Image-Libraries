package androidx.media3.exoplayer;

import F2.G;
import F2.U;
import F2.y0;
import android.os.SystemClock;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class PlaybackInfo {
    private static final MediaSource.MediaPeriodId PLACEHOLDER_MEDIA_PERIOD_ID = new MediaSource.MediaPeriodId(new Object());
    public volatile long bufferedPositionUs;
    public final long discontinuityStartPositionUs;
    public final boolean isLoading;
    public final MediaSource.MediaPeriodId loadingMediaPeriodId;
    public final MediaSource.MediaPeriodId periodId;
    public final boolean playWhenReady;
    public final int playWhenReadyChangeReason;
    public final ExoPlaybackException playbackError;
    public final PlaybackParameters playbackParameters;
    public final int playbackState;
    public final int playbackSuppressionReason;
    public volatile long positionUpdateTimeMs;
    public volatile long positionUs;
    public final long requestedContentPositionUs;
    public final boolean sleepingForOffload;
    public final List<Metadata> staticMetadata;
    public final Timeline timeline;
    public volatile long totalBufferedDurationUs;
    public final TrackGroupArray trackGroups;
    public final TrackSelectorResult trackSelectorResult;

    public PlaybackInfo(Timeline timeline2, MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3, int i2, ExoPlaybackException exoPlaybackException, boolean z, TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult2, List<Metadata> list, MediaSource.MediaPeriodId mediaPeriodId2, boolean z3, int i7, int i8, PlaybackParameters playbackParameters2, long j8, long j10, long j11, long j12, boolean z7) {
        this.timeline = timeline2;
        this.periodId = mediaPeriodId;
        this.requestedContentPositionUs = j2;
        this.discontinuityStartPositionUs = j3;
        this.playbackState = i2;
        this.playbackError = exoPlaybackException;
        this.isLoading = z;
        this.trackGroups = trackGroupArray;
        this.trackSelectorResult = trackSelectorResult2;
        this.staticMetadata = list;
        this.loadingMediaPeriodId = mediaPeriodId2;
        this.playWhenReady = z3;
        this.playWhenReadyChangeReason = i7;
        this.playbackSuppressionReason = i8;
        this.playbackParameters = playbackParameters2;
        this.bufferedPositionUs = j8;
        this.totalBufferedDurationUs = j10;
        this.positionUs = j11;
        this.positionUpdateTimeMs = j12;
        this.sleepingForOffload = z7;
    }

    public static PlaybackInfo createDummy(TrackSelectorResult trackSelectorResult2) {
        Timeline timeline2 = Timeline.EMPTY;
        MediaSource.MediaPeriodId mediaPeriodId = PLACEHOLDER_MEDIA_PERIOD_ID;
        TrackGroupArray trackGroupArray = TrackGroupArray.EMPTY;
        G g = U.e;
        return new PlaybackInfo(timeline2, mediaPeriodId, -9223372036854775807L, 0, 1, (ExoPlaybackException) null, false, trackGroupArray, trackSelectorResult2, y0.f278h, mediaPeriodId, false, 1, 0, PlaybackParameters.DEFAULT, 0, 0, 0, 0, false);
    }

    public static MediaSource.MediaPeriodId getDummyPeriodForEmptyTimeline() {
        return PLACEHOLDER_MEDIA_PERIOD_ID;
    }

    public PlaybackInfo copyWithEstimatedPosition() {
        Timeline timeline2 = this.timeline;
        MediaSource.MediaPeriodId mediaPeriodId = this.periodId;
        long j2 = this.requestedContentPositionUs;
        long j3 = this.discontinuityStartPositionUs;
        int i2 = this.playbackState;
        ExoPlaybackException exoPlaybackException = this.playbackError;
        boolean z = this.isLoading;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
        List<Metadata> list = this.staticMetadata;
        MediaSource.MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        boolean z3 = this.playWhenReady;
        int i7 = this.playWhenReadyChangeReason;
        int i8 = this.playbackSuppressionReason;
        return new PlaybackInfo(timeline2, mediaPeriodId, j2, j3, i2, exoPlaybackException, z, trackGroupArray, trackSelectorResult2, list, mediaPeriodId2, z3, i7, i8, this.playbackParameters, this.bufferedPositionUs, this.totalBufferedDurationUs, getEstimatedPositionUs(), SystemClock.elapsedRealtime(), this.sleepingForOffload);
    }

    public PlaybackInfo copyWithIsLoading(boolean z) {
        Timeline timeline2 = this.timeline;
        MediaSource.MediaPeriodId mediaPeriodId = this.periodId;
        long j2 = this.requestedContentPositionUs;
        long j3 = this.discontinuityStartPositionUs;
        int i2 = this.playbackState;
        ExoPlaybackException exoPlaybackException = this.playbackError;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
        List<Metadata> list = this.staticMetadata;
        MediaSource.MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        boolean z3 = this.playWhenReady;
        int i7 = this.playWhenReadyChangeReason;
        Timeline timeline3 = timeline2;
        int i8 = this.playbackSuppressionReason;
        PlaybackParameters playbackParameters2 = this.playbackParameters;
        long j8 = this.bufferedPositionUs;
        long j10 = this.totalBufferedDurationUs;
        long j11 = this.positionUs;
        boolean z7 = z;
        long j12 = this.positionUpdateTimeMs;
        return new PlaybackInfo(timeline3, mediaPeriodId, j2, j3, i2, exoPlaybackException, z7, trackGroupArray, trackSelectorResult2, list, mediaPeriodId2, z3, i7, i8, playbackParameters2, j8, j10, j11, j12, this.sleepingForOffload);
    }

    public PlaybackInfo copyWithLoadingMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        Timeline timeline2 = this.timeline;
        MediaSource.MediaPeriodId mediaPeriodId2 = this.periodId;
        long j2 = this.requestedContentPositionUs;
        long j3 = this.discontinuityStartPositionUs;
        int i2 = this.playbackState;
        ExoPlaybackException exoPlaybackException = this.playbackError;
        boolean z = this.isLoading;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
        List<Metadata> list = this.staticMetadata;
        boolean z3 = this.playWhenReady;
        int i7 = this.playWhenReadyChangeReason;
        Timeline timeline3 = timeline2;
        int i8 = this.playbackSuppressionReason;
        PlaybackParameters playbackParameters2 = this.playbackParameters;
        long j8 = this.bufferedPositionUs;
        long j10 = this.totalBufferedDurationUs;
        long j11 = this.positionUs;
        MediaSource.MediaPeriodId mediaPeriodId3 = mediaPeriodId;
        long j12 = this.positionUpdateTimeMs;
        return new PlaybackInfo(timeline3, mediaPeriodId2, j2, j3, i2, exoPlaybackException, z, trackGroupArray, trackSelectorResult2, list, mediaPeriodId3, z3, i7, i8, playbackParameters2, j8, j10, j11, j12, this.sleepingForOffload);
    }

    public PlaybackInfo copyWithNewPosition(MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3, long j8, long j10, TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult2, List<Metadata> list) {
        Timeline timeline2 = this.timeline;
        int i2 = this.playbackState;
        ExoPlaybackException exoPlaybackException = this.playbackError;
        boolean z = this.isLoading;
        MediaSource.MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        boolean z3 = this.playWhenReady;
        int i7 = this.playWhenReadyChangeReason;
        int i8 = this.playbackSuppressionReason;
        PlaybackParameters playbackParameters2 = this.playbackParameters;
        long j11 = j2;
        long j12 = j10;
        int i10 = i8;
        return new PlaybackInfo(timeline2, mediaPeriodId, j3, j8, i2, exoPlaybackException, z, trackGroupArray, trackSelectorResult2, list, mediaPeriodId2, z3, i7, i10, playbackParameters2, this.bufferedPositionUs, j12, j11, SystemClock.elapsedRealtime(), this.sleepingForOffload);
    }

    public PlaybackInfo copyWithPlayWhenReady(boolean z, int i2, int i7) {
        Timeline timeline2 = this.timeline;
        MediaSource.MediaPeriodId mediaPeriodId = this.periodId;
        long j2 = this.requestedContentPositionUs;
        long j3 = this.discontinuityStartPositionUs;
        int i8 = this.playbackState;
        ExoPlaybackException exoPlaybackException = this.playbackError;
        boolean z3 = this.isLoading;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
        List<Metadata> list = this.staticMetadata;
        MediaSource.MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        PlaybackParameters playbackParameters2 = this.playbackParameters;
        Timeline timeline3 = timeline2;
        PlaybackParameters playbackParameters3 = playbackParameters2;
        return new PlaybackInfo(timeline3, mediaPeriodId, j2, j3, i8, exoPlaybackException, z3, trackGroupArray, trackSelectorResult2, list, mediaPeriodId2, z, i2, i7, playbackParameters3, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs, this.positionUpdateTimeMs, this.sleepingForOffload);
    }

    public PlaybackInfo copyWithPlaybackError(ExoPlaybackException exoPlaybackException) {
        Timeline timeline2 = this.timeline;
        MediaSource.MediaPeriodId mediaPeriodId = this.periodId;
        long j2 = this.requestedContentPositionUs;
        long j3 = this.discontinuityStartPositionUs;
        int i2 = this.playbackState;
        boolean z = this.isLoading;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
        List<Metadata> list = this.staticMetadata;
        MediaSource.MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        boolean z3 = this.playWhenReady;
        int i7 = this.playWhenReadyChangeReason;
        Timeline timeline3 = timeline2;
        int i8 = this.playbackSuppressionReason;
        PlaybackParameters playbackParameters2 = this.playbackParameters;
        long j8 = this.bufferedPositionUs;
        long j10 = this.totalBufferedDurationUs;
        long j11 = this.positionUs;
        ExoPlaybackException exoPlaybackException2 = exoPlaybackException;
        long j12 = this.positionUpdateTimeMs;
        return new PlaybackInfo(timeline3, mediaPeriodId, j2, j3, i2, exoPlaybackException2, z, trackGroupArray, trackSelectorResult2, list, mediaPeriodId2, z3, i7, i8, playbackParameters2, j8, j10, j11, j12, this.sleepingForOffload);
    }

    public PlaybackInfo copyWithPlaybackParameters(PlaybackParameters playbackParameters2) {
        Timeline timeline2 = this.timeline;
        MediaSource.MediaPeriodId mediaPeriodId = this.periodId;
        long j2 = this.requestedContentPositionUs;
        long j3 = this.discontinuityStartPositionUs;
        int i2 = this.playbackState;
        ExoPlaybackException exoPlaybackException = this.playbackError;
        boolean z = this.isLoading;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
        List<Metadata> list = this.staticMetadata;
        MediaSource.MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        boolean z3 = this.playWhenReady;
        int i7 = this.playWhenReadyChangeReason;
        Timeline timeline3 = timeline2;
        int i8 = this.playbackSuppressionReason;
        long j8 = this.bufferedPositionUs;
        long j10 = this.totalBufferedDurationUs;
        long j11 = this.positionUs;
        int i10 = i8;
        Timeline timeline4 = timeline3;
        PlaybackParameters playbackParameters3 = playbackParameters2;
        long j12 = this.positionUpdateTimeMs;
        return new PlaybackInfo(timeline4, mediaPeriodId, j2, j3, i2, exoPlaybackException, z, trackGroupArray, trackSelectorResult2, list, mediaPeriodId2, z3, i7, i10, playbackParameters3, j8, j10, j11, j12, this.sleepingForOffload);
    }

    public PlaybackInfo copyWithPlaybackState(int i2) {
        Timeline timeline2 = this.timeline;
        MediaSource.MediaPeriodId mediaPeriodId = this.periodId;
        long j2 = this.requestedContentPositionUs;
        long j3 = this.discontinuityStartPositionUs;
        ExoPlaybackException exoPlaybackException = this.playbackError;
        boolean z = this.isLoading;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
        List<Metadata> list = this.staticMetadata;
        MediaSource.MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        boolean z3 = this.playWhenReady;
        int i7 = this.playWhenReadyChangeReason;
        Timeline timeline3 = timeline2;
        int i8 = this.playbackSuppressionReason;
        PlaybackParameters playbackParameters2 = this.playbackParameters;
        long j8 = this.bufferedPositionUs;
        long j10 = this.totalBufferedDurationUs;
        long j11 = this.positionUs;
        int i10 = i2;
        long j12 = this.positionUpdateTimeMs;
        return new PlaybackInfo(timeline3, mediaPeriodId, j2, j3, i10, exoPlaybackException, z, trackGroupArray, trackSelectorResult2, list, mediaPeriodId2, z3, i7, i8, playbackParameters2, j8, j10, j11, j12, this.sleepingForOffload);
    }

    public PlaybackInfo copyWithSleepingForOffload(boolean z) {
        Timeline timeline2 = this.timeline;
        MediaSource.MediaPeriodId mediaPeriodId = this.periodId;
        long j2 = this.requestedContentPositionUs;
        long j3 = this.discontinuityStartPositionUs;
        int i2 = this.playbackState;
        ExoPlaybackException exoPlaybackException = this.playbackError;
        boolean z3 = this.isLoading;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
        List<Metadata> list = this.staticMetadata;
        MediaSource.MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        boolean z7 = this.playWhenReady;
        int i7 = this.playWhenReadyChangeReason;
        int i8 = this.playbackSuppressionReason;
        PlaybackParameters playbackParameters2 = this.playbackParameters;
        long j8 = this.bufferedPositionUs;
        return new PlaybackInfo(timeline2, mediaPeriodId, j2, j3, i2, exoPlaybackException, z3, trackGroupArray, trackSelectorResult2, list, mediaPeriodId2, z7, i7, i8, playbackParameters2, j8, this.totalBufferedDurationUs, this.positionUs, this.positionUpdateTimeMs, z);
    }

    public PlaybackInfo copyWithTimeline(Timeline timeline2) {
        MediaSource.MediaPeriodId mediaPeriodId = this.periodId;
        long j2 = this.requestedContentPositionUs;
        long j3 = this.discontinuityStartPositionUs;
        int i2 = this.playbackState;
        ExoPlaybackException exoPlaybackException = this.playbackError;
        boolean z = this.isLoading;
        TrackGroupArray trackGroupArray = this.trackGroups;
        TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
        List<Metadata> list = this.staticMetadata;
        MediaSource.MediaPeriodId mediaPeriodId2 = this.loadingMediaPeriodId;
        boolean z3 = this.playWhenReady;
        int i7 = this.playWhenReadyChangeReason;
        int i8 = this.playbackSuppressionReason;
        PlaybackParameters playbackParameters2 = this.playbackParameters;
        long j8 = this.bufferedPositionUs;
        long j10 = this.totalBufferedDurationUs;
        long j11 = this.positionUs;
        int i10 = i8;
        PlaybackParameters playbackParameters3 = playbackParameters2;
        long j12 = this.positionUpdateTimeMs;
        return new PlaybackInfo(timeline2, mediaPeriodId, j2, j3, i2, exoPlaybackException, z, trackGroupArray, trackSelectorResult2, list, mediaPeriodId2, z3, i7, i10, playbackParameters3, j8, j10, j11, j12, this.sleepingForOffload);
    }

    public long getEstimatedPositionUs() {
        long j2;
        long j3;
        if (!isPlaying()) {
            return this.positionUs;
        }
        do {
            j2 = this.positionUpdateTimeMs;
            j3 = this.positionUs;
        } while (j2 != this.positionUpdateTimeMs);
        return Util.msToUs(Util.usToMs(j3) + ((long) (((float) (SystemClock.elapsedRealtime() - j2)) * this.playbackParameters.speed)));
    }

    public boolean isPlaying() {
        if (this.playbackState == 3 && this.playWhenReady && this.playbackSuppressionReason == 0) {
            return true;
        }
        return false;
    }

    public void updatePositionUs(long j2) {
        this.positionUs = j2;
        this.positionUpdateTimeMs = SystemClock.elapsedRealtime();
    }
}
