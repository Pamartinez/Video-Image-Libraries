package androidx.media3.exoplayer;

import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.source.MediaSource;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class MediaPeriodInfo {
    public final long durationUs;
    public final long endPositionUs;
    public final MediaSource.MediaPeriodId id;
    public final boolean isFinal;
    public final boolean isFollowedByTransitionToSameStream;
    public final boolean isLastInTimelinePeriod;
    public final boolean isLastInTimelineWindow;
    public final boolean isPrecededByTransitionFromSameStream;
    public final long requestedContentPositionUs;
    public final long startPositionUs;

    public MediaPeriodInfo(MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3, long j8, long j10, boolean z, boolean z3, boolean z7, boolean z9, boolean z10) {
        boolean z11;
        boolean z12;
        boolean z13 = z3;
        boolean z14 = z7;
        boolean z15 = z9;
        boolean z16 = z10;
        boolean z17 = true;
        if (!z16 || z14) {
            z11 = true;
        } else {
            z11 = false;
        }
        Assertions.checkArgument(z11);
        if (!z15 || z14) {
            z12 = true;
        } else {
            z12 = false;
        }
        Assertions.checkArgument(z12);
        if (z13 && (z14 || z15 || z16)) {
            z17 = false;
        }
        Assertions.checkArgument(z17);
        this.id = mediaPeriodId;
        this.startPositionUs = j2;
        this.requestedContentPositionUs = j3;
        this.endPositionUs = j8;
        this.durationUs = j10;
        this.isPrecededByTransitionFromSameStream = z;
        this.isFollowedByTransitionToSameStream = z13;
        this.isLastInTimelinePeriod = z14;
        this.isLastInTimelineWindow = z15;
        this.isFinal = z16;
    }

    public MediaPeriodInfo copyWithRequestedContentPositionUs(long j2) {
        if (j2 == this.requestedContentPositionUs) {
            return this;
        }
        return new MediaPeriodInfo(this.id, this.startPositionUs, j2, this.endPositionUs, this.durationUs, this.isPrecededByTransitionFromSameStream, this.isFollowedByTransitionToSameStream, this.isLastInTimelinePeriod, this.isLastInTimelineWindow, this.isFinal);
    }

    public MediaPeriodInfo copyWithStartPositionUs(long j2) {
        if (j2 == this.startPositionUs) {
            return this;
        }
        return new MediaPeriodInfo(this.id, j2, this.requestedContentPositionUs, this.endPositionUs, this.durationUs, this.isPrecededByTransitionFromSameStream, this.isFollowedByTransitionToSameStream, this.isLastInTimelinePeriod, this.isLastInTimelineWindow, this.isFinal);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && MediaPeriodInfo.class == obj.getClass()) {
            MediaPeriodInfo mediaPeriodInfo = (MediaPeriodInfo) obj;
            if (this.startPositionUs == mediaPeriodInfo.startPositionUs && this.requestedContentPositionUs == mediaPeriodInfo.requestedContentPositionUs && this.endPositionUs == mediaPeriodInfo.endPositionUs && this.durationUs == mediaPeriodInfo.durationUs && this.isPrecededByTransitionFromSameStream == mediaPeriodInfo.isPrecededByTransitionFromSameStream && this.isFollowedByTransitionToSameStream == mediaPeriodInfo.isFollowedByTransitionToSameStream && this.isLastInTimelinePeriod == mediaPeriodInfo.isLastInTimelinePeriod && this.isLastInTimelineWindow == mediaPeriodInfo.isLastInTimelineWindow && this.isFinal == mediaPeriodInfo.isFinal && Objects.equals(this.id, mediaPeriodInfo.id)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((((((((this.id.hashCode() + 527) * 31) + ((int) this.startPositionUs)) * 31) + ((int) this.requestedContentPositionUs)) * 31) + ((int) this.endPositionUs)) * 31) + ((int) this.durationUs)) * 31) + (this.isPrecededByTransitionFromSameStream ? 1 : 0)) * 31) + (this.isFollowedByTransitionToSameStream ? 1 : 0)) * 31) + (this.isLastInTimelinePeriod ? 1 : 0)) * 31) + (this.isLastInTimelineWindow ? 1 : 0)) * 31) + (this.isFinal ? 1 : 0);
    }
}
