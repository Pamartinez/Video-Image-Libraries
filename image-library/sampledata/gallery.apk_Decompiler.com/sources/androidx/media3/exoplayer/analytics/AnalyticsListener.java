package androidx.media3.exoplayer.analytics;

import android.util.SparseArray;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;
import java.io.IOException;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface AnalyticsListener {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EventTime {
        public final MediaSource.MediaPeriodId currentMediaPeriodId;
        public final long currentPlaybackPositionMs;
        public final Timeline currentTimeline;
        public final int currentWindowIndex;
        public final long eventPlaybackPositionMs;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final long realtimeMs;
        public final Timeline timeline;
        public final long totalBufferedDurationMs;
        public final int windowIndex;

        public EventTime(long j2, Timeline timeline2, int i2, MediaSource.MediaPeriodId mediaPeriodId2, long j3, Timeline timeline3, int i7, MediaSource.MediaPeriodId mediaPeriodId3, long j8, long j10) {
            this.realtimeMs = j2;
            this.timeline = timeline2;
            this.windowIndex = i2;
            this.mediaPeriodId = mediaPeriodId2;
            this.eventPlaybackPositionMs = j3;
            this.currentTimeline = timeline3;
            this.currentWindowIndex = i7;
            this.currentMediaPeriodId = mediaPeriodId3;
            this.currentPlaybackPositionMs = j8;
            this.totalBufferedDurationMs = j10;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && EventTime.class == obj.getClass()) {
                EventTime eventTime = (EventTime) obj;
                if (this.realtimeMs == eventTime.realtimeMs && this.windowIndex == eventTime.windowIndex && this.eventPlaybackPositionMs == eventTime.eventPlaybackPositionMs && this.currentWindowIndex == eventTime.currentWindowIndex && this.currentPlaybackPositionMs == eventTime.currentPlaybackPositionMs && this.totalBufferedDurationMs == eventTime.totalBufferedDurationMs && Objects.equals(this.timeline, eventTime.timeline) && Objects.equals(this.mediaPeriodId, eventTime.mediaPeriodId) && Objects.equals(this.currentTimeline, eventTime.currentTimeline) && Objects.equals(this.currentMediaPeriodId, eventTime.currentMediaPeriodId)) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Long.valueOf(this.realtimeMs), this.timeline, Integer.valueOf(this.windowIndex), this.mediaPeriodId, Long.valueOf(this.eventPlaybackPositionMs), this.currentTimeline, Integer.valueOf(this.currentWindowIndex), this.currentMediaPeriodId, Long.valueOf(this.currentPlaybackPositionMs), Long.valueOf(this.totalBufferedDurationMs)});
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Events {
        private final SparseArray<EventTime> eventTimes;
        private final FlagSet flags;

        public Events(FlagSet flagSet, SparseArray<EventTime> sparseArray) {
            this.flags = flagSet;
            SparseArray<EventTime> sparseArray2 = new SparseArray<>(flagSet.size());
            for (int i2 = 0; i2 < flagSet.size(); i2++) {
                int i7 = flagSet.get(i2);
                sparseArray2.append(i7, (EventTime) Assertions.checkNotNull(sparseArray.get(i7)));
            }
            this.eventTimes = sparseArray2;
        }

        public boolean contains(int i2) {
            return this.flags.contains(i2);
        }

        public int get(int i2) {
            return this.flags.get(i2);
        }

        public EventTime getEventTime(int i2) {
            return (EventTime) Assertions.checkNotNull(this.eventTimes.get(i2));
        }

        public int size() {
            return this.flags.size();
        }
    }

    void onBandwidthEstimate(EventTime eventTime, int i2, long j2, long j3);

    void onDownstreamFormatChanged(EventTime eventTime, MediaLoadData mediaLoadData);

    @Deprecated
    void onDrmSessionAcquired(EventTime eventTime) {
    }

    void onEvents(Player player, Events events);

    void onLoadError(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z);

    @Deprecated
    void onLoadStarted(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    void onPlayerError(EventTime eventTime, PlaybackException playbackException);

    @Deprecated
    void onPositionDiscontinuity(EventTime eventTime, int i2) {
    }

    void onPositionDiscontinuity(EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i2);

    void onDrmSessionAcquired(EventTime eventTime, int i2) {
    }

    void onLoadStarted(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i2) {
    }

    void onDrmKeysLoaded(EventTime eventTime) {
    }

    void onDrmKeysRemoved(EventTime eventTime) {
    }

    void onDrmKeysRestored(EventTime eventTime) {
    }

    void onDrmSessionReleased(EventTime eventTime) {
    }

    void onPlayerReleased(EventTime eventTime) {
    }

    void onAudioSessionIdChanged(EventTime eventTime, int i2) {
    }

    void onAvailableCommandsChanged(EventTime eventTime, Player.Commands commands) {
    }

    void onDeviceInfoChanged(EventTime eventTime, DeviceInfo deviceInfo) {
    }

    void onDrmSessionManagerError(EventTime eventTime, Exception exc) {
    }

    void onIsLoadingChanged(EventTime eventTime, boolean z) {
    }

    void onIsPlayingChanged(EventTime eventTime, boolean z) {
    }

    @Deprecated
    void onLoadingChanged(EventTime eventTime, boolean z) {
    }

    void onMediaMetadataChanged(EventTime eventTime, MediaMetadata mediaMetadata) {
    }

    void onPlaybackParametersChanged(EventTime eventTime, PlaybackParameters playbackParameters) {
    }

    void onPlaybackStateChanged(EventTime eventTime, int i2) {
    }

    void onPlaybackSuppressionReasonChanged(EventTime eventTime, int i2) {
    }

    void onPlayerErrorChanged(EventTime eventTime, PlaybackException playbackException) {
    }

    void onTimelineChanged(EventTime eventTime, int i2) {
    }

    void onTracksChanged(EventTime eventTime, Tracks tracks) {
    }

    void onDeviceVolumeChanged(EventTime eventTime, int i2, boolean z) {
    }

    void onLoadCanceled(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    void onLoadCompleted(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    void onMediaItemTransition(EventTime eventTime, MediaItem mediaItem, int i2) {
    }

    void onPlayWhenReadyChanged(EventTime eventTime, boolean z, int i2) {
    }

    @Deprecated
    void onPlayerStateChanged(EventTime eventTime, boolean z, int i2) {
    }

    void onSurfaceSizeChanged(EventTime eventTime, int i2, int i7) {
    }

    void onRendererReadyChanged(EventTime eventTime, int i2, int i7, boolean z) {
    }
}
