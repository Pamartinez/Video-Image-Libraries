package androidx.media3.exoplayer.source;

import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MaskingMediaSource extends WrappingMediaSource {
    private boolean hasRealTimeline;
    private boolean hasStartedPreparing;
    private boolean isPrepared;
    private final Timeline.Period period;
    private MaskingTimeline timeline;
    private MaskingMediaPeriod unpreparedMaskingMediaPeriod;
    private final boolean useLazyPreparation;
    private final Timeline.Window window;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MaskingTimeline extends ForwardingTimeline {
        public static final Object MASKING_EXTERNAL_PERIOD_UID = new Object();
        /* access modifiers changed from: private */
        public final Object replacedInternalPeriodUid;
        private final Object replacedInternalWindowUid;

        private MaskingTimeline(Timeline timeline, Object obj, Object obj2) {
            super(timeline);
            this.replacedInternalWindowUid = obj;
            this.replacedInternalPeriodUid = obj2;
        }

        public static MaskingTimeline createWithPlaceholderTimeline(MediaItem mediaItem) {
            return new MaskingTimeline(new PlaceholderTimeline(mediaItem), Timeline.Window.SINGLE_WINDOW_UID, MASKING_EXTERNAL_PERIOD_UID);
        }

        public static MaskingTimeline createWithRealTimeline(Timeline timeline, Object obj, Object obj2) {
            return new MaskingTimeline(timeline, obj, obj2);
        }

        public MaskingTimeline cloneWithUpdatedTimeline(Timeline timeline) {
            return new MaskingTimeline(timeline, this.replacedInternalWindowUid, this.replacedInternalPeriodUid);
        }

        public int getIndexOfPeriod(Object obj) {
            Object obj2;
            Timeline timeline = this.timeline;
            if (MASKING_EXTERNAL_PERIOD_UID.equals(obj) && (obj2 = this.replacedInternalPeriodUid) != null) {
                obj = obj2;
            }
            return timeline.getIndexOfPeriod(obj);
        }

        public Timeline.Period getPeriod(int i2, Timeline.Period period, boolean z) {
            this.timeline.getPeriod(i2, period, z);
            if (Objects.equals(period.uid, this.replacedInternalPeriodUid) && z) {
                period.uid = MASKING_EXTERNAL_PERIOD_UID;
            }
            return period;
        }

        public Object getUidOfPeriod(int i2) {
            Object uidOfPeriod = this.timeline.getUidOfPeriod(i2);
            if (Objects.equals(uidOfPeriod, this.replacedInternalPeriodUid)) {
                return MASKING_EXTERNAL_PERIOD_UID;
            }
            return uidOfPeriod;
        }

        public Timeline.Window getWindow(int i2, Timeline.Window window, long j2) {
            this.timeline.getWindow(i2, window, j2);
            if (Objects.equals(window.uid, this.replacedInternalWindowUid)) {
                window.uid = Timeline.Window.SINGLE_WINDOW_UID;
            }
            return window;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PlaceholderTimeline extends Timeline {
        private final MediaItem mediaItem;

        public PlaceholderTimeline(MediaItem mediaItem2) {
            this.mediaItem = mediaItem2;
        }

        public int getIndexOfPeriod(Object obj) {
            if (obj == MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID) {
                return 0;
            }
            return -1;
        }

        public Timeline.Period getPeriod(int i2, Timeline.Period period, boolean z) {
            Integer num;
            Object obj = null;
            if (z) {
                num = 0;
            } else {
                num = null;
            }
            if (z) {
                obj = MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
            }
            Timeline.Period period2 = period;
            period2.set(num, obj, 0, -9223372036854775807L, 0, AdPlaybackState.NONE, true);
            return period2;
        }

        public int getPeriodCount() {
            return 1;
        }

        public Object getUidOfPeriod(int i2) {
            return MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
        }

        public Timeline.Window getWindow(int i2, Timeline.Window window, long j2) {
            Timeline.Window window2 = window;
            window2.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, (Object) null, -9223372036854775807L, -9223372036854775807L, -9223372036854775807L, false, true, (MediaItem.LiveConfiguration) null, 0, -9223372036854775807L, 0, 0, 0);
            window2.isPlaceholder = true;
            return window2;
        }

        public int getWindowCount() {
            return 1;
        }
    }

    public MaskingMediaSource(MediaSource mediaSource, boolean z) {
        super(mediaSource);
        boolean z3;
        if (!z || !mediaSource.isSingleWindow()) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.useLazyPreparation = z3;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        Timeline initialTimeline = mediaSource.getInitialTimeline();
        if (initialTimeline != null) {
            this.timeline = MaskingTimeline.createWithRealTimeline(initialTimeline, (Object) null, (Object) null);
            this.hasRealTimeline = true;
            return;
        }
        this.timeline = MaskingTimeline.createWithPlaceholderTimeline(mediaSource.getMediaItem());
    }

    private Object getExternalPeriodUid(Object obj) {
        if (this.timeline.replacedInternalPeriodUid == null || !this.timeline.replacedInternalPeriodUid.equals(obj)) {
            return obj;
        }
        return MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID;
    }

    private Object getInternalPeriodUid(Object obj) {
        if (this.timeline.replacedInternalPeriodUid == null || !obj.equals(MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID)) {
            return obj;
        }
        return this.timeline.replacedInternalPeriodUid;
    }

    private boolean setPreparePositionOverrideToUnpreparedMaskingPeriod(long j2) {
        MaskingMediaPeriod maskingMediaPeriod = this.unpreparedMaskingMediaPeriod;
        int indexOfPeriod = this.timeline.getIndexOfPeriod(maskingMediaPeriod.id.periodUid);
        if (indexOfPeriod == -1) {
            return false;
        }
        long j3 = this.timeline.getPeriod(indexOfPeriod, this.period).durationUs;
        if (j3 != -9223372036854775807L && j2 >= j3) {
            j2 = Math.max(0, j3 - 1);
        }
        maskingMediaPeriod.overridePreparePositionUs(j2);
        return true;
    }

    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        return mediaPeriodId.copyWithPeriodUid(getExternalPeriodUid(mediaPeriodId.periodUid));
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onChildSourceInfoRefreshed(androidx.media3.common.Timeline r15) {
        /*
            r14 = this;
            boolean r0 = r14.isPrepared
            if (r0 == 0) goto L_0x0019
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r14.timeline
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r15 = r0.cloneWithUpdatedTimeline(r15)
            r14.timeline = r15
            androidx.media3.exoplayer.source.MaskingMediaPeriod r15 = r14.unpreparedMaskingMediaPeriod
            if (r15 == 0) goto L_0x00b1
            long r0 = r15.getPreparePositionOverrideUs()
            r14.setPreparePositionOverrideToUnpreparedMaskingPeriod(r0)
            goto L_0x00b1
        L_0x0019:
            boolean r0 = r15.isEmpty()
            if (r0 == 0) goto L_0x0036
            boolean r0 = r14.hasRealTimeline
            if (r0 == 0) goto L_0x002a
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r14.timeline
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r15 = r0.cloneWithUpdatedTimeline(r15)
            goto L_0x0032
        L_0x002a:
            java.lang.Object r0 = androidx.media3.common.Timeline.Window.SINGLE_WINDOW_UID
            java.lang.Object r1 = androidx.media3.exoplayer.source.MaskingMediaSource.MaskingTimeline.MASKING_EXTERNAL_PERIOD_UID
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r15 = androidx.media3.exoplayer.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r15, r0, r1)
        L_0x0032:
            r14.timeline = r15
            goto L_0x00b1
        L_0x0036:
            androidx.media3.common.Timeline$Window r0 = r14.window
            r1 = 0
            r15.getWindow(r1, r0)
            androidx.media3.common.Timeline$Window r0 = r14.window
            long r2 = r0.getDefaultPositionUs()
            androidx.media3.common.Timeline$Window r0 = r14.window
            java.lang.Object r0 = r0.uid
            androidx.media3.exoplayer.source.MaskingMediaPeriod r4 = r14.unpreparedMaskingMediaPeriod
            if (r4 == 0) goto L_0x0074
            long r4 = r4.getPreparePositionUs()
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r6 = r14.timeline
            androidx.media3.exoplayer.source.MaskingMediaPeriod r7 = r14.unpreparedMaskingMediaPeriod
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r7 = r7.id
            java.lang.Object r7 = r7.periodUid
            androidx.media3.common.Timeline$Period r8 = r14.period
            r6.getPeriodByUid(r7, r8)
            androidx.media3.common.Timeline$Period r6 = r14.period
            long r6 = r6.getPositionInWindowUs()
            long r6 = r6 + r4
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r4 = r14.timeline
            androidx.media3.common.Timeline$Window r5 = r14.window
            androidx.media3.common.Timeline$Window r1 = r4.getWindow(r1, r5)
            long r4 = r1.getDefaultPositionUs()
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0074
            r12 = r6
            goto L_0x0075
        L_0x0074:
            r12 = r2
        L_0x0075:
            androidx.media3.common.Timeline$Window r9 = r14.window
            androidx.media3.common.Timeline$Period r10 = r14.period
            r11 = 0
            r8 = r15
            android.util.Pair r15 = r8.getPeriodPositionUs(r9, r10, r11, r12)
            java.lang.Object r1 = r15.first
            java.lang.Object r15 = r15.second
            java.lang.Long r15 = (java.lang.Long) r15
            long r2 = r15.longValue()
            boolean r15 = r14.hasRealTimeline
            if (r15 == 0) goto L_0x0094
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r15 = r14.timeline
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r15 = r15.cloneWithUpdatedTimeline(r8)
            goto L_0x0098
        L_0x0094:
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r15 = androidx.media3.exoplayer.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r8, r0, r1)
        L_0x0098:
            r14.timeline = r15
            androidx.media3.exoplayer.source.MaskingMediaPeriod r15 = r14.unpreparedMaskingMediaPeriod
            if (r15 == 0) goto L_0x00b1
            boolean r0 = r14.setPreparePositionOverrideToUnpreparedMaskingPeriod(r2)
            if (r0 == 0) goto L_0x00b1
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r15 = r15.id
            java.lang.Object r0 = r15.periodUid
            java.lang.Object r0 = r14.getInternalPeriodUid(r0)
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r15 = r15.copyWithPeriodUid(r0)
            goto L_0x00b2
        L_0x00b1:
            r15 = 0
        L_0x00b2:
            r0 = 1
            r14.hasRealTimeline = r0
            r14.isPrepared = r0
            androidx.media3.exoplayer.source.MaskingMediaSource$MaskingTimeline r0 = r14.timeline
            r14.refreshSourceInfo(r0)
            if (r15 == 0) goto L_0x00c9
            androidx.media3.exoplayer.source.MaskingMediaPeriod r14 = r14.unpreparedMaskingMediaPeriod
            java.lang.Object r14 = androidx.media3.common.util.Assertions.checkNotNull(r14)
            androidx.media3.exoplayer.source.MaskingMediaPeriod r14 = (androidx.media3.exoplayer.source.MaskingMediaPeriod) r14
            r14.createPeriod(r15)
        L_0x00c9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.MaskingMediaSource.onChildSourceInfoRefreshed(androidx.media3.common.Timeline):void");
    }

    public void prepareSourceInternal() {
        if (!this.useLazyPreparation) {
            this.hasStartedPreparing = true;
            prepareChildSource();
        }
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((MaskingMediaPeriod) mediaPeriod).releasePeriod();
        if (mediaPeriod == this.unpreparedMaskingMediaPeriod) {
            this.unpreparedMaskingMediaPeriod = null;
        }
    }

    public void releaseSourceInternal() {
        this.isPrepared = false;
        this.hasStartedPreparing = false;
        super.releaseSourceInternal();
    }

    public void updateMediaItem(MediaItem mediaItem) {
        if (this.hasRealTimeline) {
            this.timeline = this.timeline.cloneWithUpdatedTimeline(new TimelineWithUpdatedMediaItem(this.timeline.timeline, mediaItem));
        } else {
            this.timeline = MaskingTimeline.createWithPlaceholderTimeline(mediaItem);
        }
        this.mediaSource.updateMediaItem(mediaItem);
    }

    public MaskingMediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        MaskingMediaPeriod maskingMediaPeriod = new MaskingMediaPeriod(mediaPeriodId, allocator, j2);
        maskingMediaPeriod.setMediaSource(this.mediaSource);
        if (this.isPrepared) {
            maskingMediaPeriod.createPeriod(mediaPeriodId.copyWithPeriodUid(getInternalPeriodUid(mediaPeriodId.periodUid)));
            return maskingMediaPeriod;
        }
        this.unpreparedMaskingMediaPeriod = maskingMediaPeriod;
        if (!this.hasStartedPreparing) {
            this.hasStartedPreparing = true;
            prepareChildSource();
        }
        return maskingMediaPeriod;
    }
}
