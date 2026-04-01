package androidx.media3.exoplayer.source;

import android.os.Handler;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.extractor.text.SubtitleParser;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaSource {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MediaPeriodId {
        public final int adGroupIndex;
        public final int adIndexInAdGroup;
        public final int nextAdGroupIndex;
        public final Object periodUid;
        public final long windowSequenceNumber;

        public MediaPeriodId(Object obj) {
            this(obj, -1);
        }

        public MediaPeriodId copyWithPeriodUid(Object obj) {
            if (this.periodUid.equals(obj)) {
                return this;
            }
            return new MediaPeriodId(obj, this.adGroupIndex, this.adIndexInAdGroup, this.windowSequenceNumber, this.nextAdGroupIndex);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaPeriodId)) {
                return false;
            }
            MediaPeriodId mediaPeriodId = (MediaPeriodId) obj;
            if (this.periodUid.equals(mediaPeriodId.periodUid) && this.adGroupIndex == mediaPeriodId.adGroupIndex && this.adIndexInAdGroup == mediaPeriodId.adIndexInAdGroup && this.windowSequenceNumber == mediaPeriodId.windowSequenceNumber && this.nextAdGroupIndex == mediaPeriodId.nextAdGroupIndex) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ((((((((this.periodUid.hashCode() + 527) * 31) + this.adGroupIndex) * 31) + this.adIndexInAdGroup) * 31) + ((int) this.windowSequenceNumber)) * 31) + this.nextAdGroupIndex;
        }

        public boolean isAd() {
            if (this.adGroupIndex != -1) {
                return true;
            }
            return false;
        }

        public MediaPeriodId(Object obj, long j2) {
            this(obj, -1, -1, j2, -1);
        }

        public MediaPeriodId(Object obj, long j2, int i2) {
            this(obj, -1, -1, j2, i2);
        }

        public MediaPeriodId(Object obj, int i2, int i7, long j2) {
            this(obj, i2, i7, j2, -1);
        }

        private MediaPeriodId(Object obj, int i2, int i7, long j2, int i8) {
            this.periodUid = obj;
            this.adGroupIndex = i2;
            this.adIndexInAdGroup = i7;
            this.windowSequenceNumber = j2;
            this.nextAdGroupIndex = i8;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface MediaSourceCaller {
        void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline);
    }

    void addDrmEventListener(Handler handler, DrmSessionEventListener drmSessionEventListener);

    void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener);

    MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator, long j2);

    void disable(MediaSourceCaller mediaSourceCaller);

    void enable(MediaSourceCaller mediaSourceCaller);

    Timeline getInitialTimeline() {
        return null;
    }

    MediaItem getMediaItem();

    boolean isSingleWindow() {
        return true;
    }

    void maybeThrowSourceInfoRefreshError();

    void prepareSource(MediaSourceCaller mediaSourceCaller, TransferListener transferListener, PlayerId playerId);

    void releasePeriod(MediaPeriod mediaPeriod);

    void releaseSource(MediaSourceCaller mediaSourceCaller);

    void removeDrmEventListener(DrmSessionEventListener drmSessionEventListener);

    void removeEventListener(MediaSourceEventListener mediaSourceEventListener);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
        public static final Factory UNSUPPORTED = MediaSourceFactory.UNSUPPORTED;

        MediaSource createMediaSource(MediaItem mediaItem);

        Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider);

        Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy);

        @Deprecated
        Factory experimentalParseSubtitlesDuringExtraction(boolean z) {
            return this;
        }

        Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int i2) {
            return this;
        }

        Factory setSubtitleParserFactory(SubtitleParser.Factory factory) {
            return this;
        }
    }

    void updateMediaItem(MediaItem mediaItem) {
    }
}
