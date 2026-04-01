package androidx.media3.exoplayer.source;

import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ClippingMediaSource extends WrappingMediaSource {
    private final boolean allowDynamicClippingUpdates;
    private final boolean allowUnseekableMedia;
    private IllegalClippingException clippingError;
    private ClippingTimeline clippingTimeline;
    private final boolean enableInitialDiscontinuity;
    private final long endUs;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private long periodEndUs;
    private long periodStartUs;
    private final boolean relativeToDefaultPosition;
    private final long startUs;
    private final Timeline.Window window;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean allowDynamicClippingUpdates;
        /* access modifiers changed from: private */
        public boolean allowUnseekableMedia;
        private boolean buildCalled;
        /* access modifiers changed from: private */
        public boolean enableInitialDiscontinuity = true;
        /* access modifiers changed from: private */
        public long endPositionUs = Long.MIN_VALUE;
        /* access modifiers changed from: private */
        public final MediaSource mediaSource;
        /* access modifiers changed from: private */
        public boolean relativeToDefaultPosition;
        /* access modifiers changed from: private */
        public long startPositionUs;

        public Builder(MediaSource mediaSource2) {
            this.mediaSource = (MediaSource) Assertions.checkNotNull(mediaSource2);
        }

        public ClippingMediaSource build() {
            this.buildCalled = true;
            return new ClippingMediaSource(this);
        }

        public Builder setAllowDynamicClippingUpdates(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.allowDynamicClippingUpdates = z;
            return this;
        }

        public Builder setAllowUnseekableMedia(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.allowUnseekableMedia = z;
            return this;
        }

        public Builder setEnableInitialDiscontinuity(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.enableInitialDiscontinuity = z;
            return this;
        }

        public Builder setEndPositionUs(long j2) {
            Assertions.checkState(!this.buildCalled);
            this.endPositionUs = j2;
            return this;
        }

        public Builder setRelativeToDefaultPosition(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.relativeToDefaultPosition = z;
            return this;
        }

        public Builder setStartPositionUs(long j2) {
            boolean z;
            if (j2 >= 0) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            Assertions.checkState(!this.buildCalled);
            this.startPositionUs = j2;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ClippingTimeline extends ForwardingTimeline {
        private final long durationUs;
        private final long endUs;
        private final boolean isDynamic;
        private final long startUs;

        public ClippingTimeline(Timeline timeline, long j2, long j3, boolean z) {
            super(timeline);
            long j8;
            long j10;
            int i2 = (j3 > Long.MIN_VALUE ? 1 : (j3 == Long.MIN_VALUE ? 0 : -1));
            if (i2 == 0 || j3 >= j2) {
                boolean z3 = false;
                if (timeline.getPeriodCount() == 1) {
                    Timeline.Window window = timeline.getWindow(0, new Timeline.Window());
                    long max = Math.max(0, j2);
                    if (z || window.isPlaceholder || max == 0 || window.isSeekable) {
                        if (i2 == 0) {
                            j8 = window.durationUs;
                        } else {
                            j8 = Math.max(0, j3);
                        }
                        long j11 = window.durationUs;
                        if (j11 != -9223372036854775807L) {
                            j8 = j8 > j11 ? j11 : j8;
                            if (max > j8) {
                                max = j8;
                            }
                        }
                        this.startUs = max;
                        this.endUs = j8;
                        int i7 = (j8 > -9223372036854775807L ? 1 : (j8 == -9223372036854775807L ? 0 : -1));
                        if (i7 == 0) {
                            j10 = -9223372036854775807L;
                        } else {
                            j10 = j8 - max;
                        }
                        this.durationUs = j10;
                        if (window.isDynamic && (i7 == 0 || (j11 != -9223372036854775807L && j8 == j11))) {
                            z3 = true;
                        }
                        this.isDynamic = z3;
                        return;
                    }
                    throw new IllegalClippingException(1);
                }
                throw new IllegalClippingException(0);
            }
            throw new IllegalClippingException(2, j2, j3);
        }

        public Timeline.Period getPeriod(int i2, Timeline.Period period, boolean z) {
            this.timeline.getPeriod(0, period, z);
            long positionInWindowUs = period.getPositionInWindowUs() - this.startUs;
            long j2 = this.durationUs;
            long j3 = -9223372036854775807L;
            if (j2 != -9223372036854775807L) {
                j3 = j2 - positionInWindowUs;
            }
            return period.set(period.id, period.uid, 0, j3, positionInWindowUs);
        }

        public Timeline.Window getWindow(int i2, Timeline.Window window, long j2) {
            this.timeline.getWindow(0, window, 0);
            long j3 = window.positionInFirstPeriodUs;
            long j8 = this.startUs;
            window.positionInFirstPeriodUs = j3 + j8;
            window.durationUs = this.durationUs;
            window.isDynamic = this.isDynamic;
            long j10 = window.defaultPositionUs;
            if (j10 != -9223372036854775807L) {
                long max = Math.max(j10, j8);
                window.defaultPositionUs = max;
                long j11 = this.endUs;
                if (j11 != -9223372036854775807L) {
                    max = Math.min(max, j11);
                }
                window.defaultPositionUs = max - this.startUs;
            }
            long usToMs = Util.usToMs(this.startUs);
            long j12 = window.presentationStartTimeMs;
            if (j12 != -9223372036854775807L) {
                window.presentationStartTimeMs = j12 + usToMs;
            }
            long j13 = window.windowStartTimeMs;
            if (j13 != -9223372036854775807L) {
                window.windowStartTimeMs = j13 + usToMs;
            }
            return window;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IllegalClippingException extends IOException {
        public final int reason;

        public IllegalClippingException(int i2) {
            this(i2, -9223372036854775807L, -9223372036854775807L);
        }

        private static String getReasonDescription(int i2, long j2, long j3) {
            if (i2 == 0) {
                return "invalid period count";
            }
            boolean z = true;
            if (i2 == 1) {
                return "not seekable to start";
            }
            if (i2 != 2) {
                return "unknown";
            }
            if (j2 == -9223372036854775807L || j3 == -9223372036854775807L) {
                z = false;
            }
            Assertions.checkState(z);
            return "start exceeds end. Start time: " + j2 + ", End time: " + j3;
        }

        public IllegalClippingException(int i2, long j2, long j3) {
            super("Illegal clipping: " + getReasonDescription(i2, j2, j3));
            this.reason = i2;
        }
    }

    private void refreshClippedTimeline(Timeline timeline) {
        long j2;
        Timeline timeline2 = timeline;
        timeline.getWindow(0, this.window);
        long positionInFirstPeriodUs = this.window.getPositionInFirstPeriodUs();
        long j3 = Long.MIN_VALUE;
        if (this.clippingTimeline == null || this.mediaPeriods.isEmpty() || this.allowDynamicClippingUpdates) {
            j2 = this.startUs;
            long j8 = this.endUs;
            if (this.relativeToDefaultPosition) {
                long defaultPositionUs = this.window.getDefaultPositionUs();
                j2 += defaultPositionUs;
                j8 += defaultPositionUs;
            }
            this.periodStartUs = positionInFirstPeriodUs + j2;
            if (this.endUs != Long.MIN_VALUE) {
                j3 = positionInFirstPeriodUs + j8;
            }
            this.periodEndUs = j3;
            int size = this.mediaPeriods.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mediaPeriods.get(i2).updateClipping(this.periodStartUs, this.periodEndUs);
            }
            j3 = j8;
        } else {
            j2 = this.periodStartUs - positionInFirstPeriodUs;
            if (this.endUs != Long.MIN_VALUE) {
                j3 = this.periodEndUs - positionInFirstPeriodUs;
            }
        }
        try {
            ClippingTimeline clippingTimeline2 = new ClippingTimeline(timeline2, j2, j3, this.allowUnseekableMedia);
            this.clippingTimeline = clippingTimeline2;
            refreshSourceInfo(clippingTimeline2);
        } catch (IllegalClippingException e) {
            this.clippingError = e;
            for (int i7 = 0; i7 < this.mediaPeriods.size(); i7++) {
                this.mediaPeriods.get(i7).setClippingError(this.clippingError);
            }
        }
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(this.mediaSource.createPeriod(mediaPeriodId, allocator, j2), this.enableInitialDiscontinuity, this.periodStartUs, this.periodEndUs);
        this.mediaPeriods.add(clippingMediaPeriod);
        return clippingMediaPeriod;
    }

    public void maybeThrowSourceInfoRefreshError() {
        IllegalClippingException illegalClippingException = this.clippingError;
        if (illegalClippingException == null) {
            super.maybeThrowSourceInfoRefreshError();
            return;
        }
        throw illegalClippingException;
    }

    public void onChildSourceInfoRefreshed(Timeline timeline) {
        if (this.clippingError == null) {
            refreshClippedTimeline(timeline);
        }
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        Assertions.checkState(this.mediaPeriods.remove(mediaPeriod));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) mediaPeriod).mediaPeriod);
        if (this.mediaPeriods.isEmpty() && !this.allowDynamicClippingUpdates) {
            refreshClippedTimeline(((ClippingTimeline) Assertions.checkNotNull(this.clippingTimeline)).timeline);
        }
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.clippingError = null;
        this.clippingTimeline = null;
    }

    private ClippingMediaSource(Builder builder) {
        super(builder.mediaSource);
        this.startUs = builder.startPositionUs;
        this.endUs = builder.endPositionUs;
        this.enableInitialDiscontinuity = builder.enableInitialDiscontinuity;
        this.allowDynamicClippingUpdates = builder.allowDynamicClippingUpdates;
        this.relativeToDefaultPosition = builder.relativeToDefaultPosition;
        this.allowUnseekableMedia = builder.allowUnseekableMedia;
        this.mediaPeriods = new ArrayList<>();
        this.window = new Timeline.Window();
    }
}
