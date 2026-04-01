package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SinglePeriodTimeline extends Timeline {
    private static final MediaItem MEDIA_ITEM = new MediaItem.Builder().setMediaId("SinglePeriodTimeline").setUri(Uri.EMPTY).build();
    private static final Object UID = new Object();
    private final long elapsedRealtimeEpochOffsetMs;
    private final boolean isDynamic;
    private final boolean isSeekable;
    private final MediaItem.LiveConfiguration liveConfiguration;
    private final Object manifest;
    private final MediaItem mediaItem;
    private final long periodDurationUs;
    private final long presentationStartTimeMs;
    private final boolean suppressPositionProjection;
    private final long windowDefaultStartPositionUs;
    private final long windowDurationUs;
    private final long windowPositionInPeriodUs;
    private final long windowStartTimeMs;

    public SinglePeriodTimeline(long j2, boolean z, boolean z3, boolean z7, Object obj, MediaItem mediaItem2) {
        this(j2, j2, 0, 0, z, z3, z7, obj, mediaItem2);
    }

    public int getIndexOfPeriod(Object obj) {
        if (UID.equals(obj)) {
            return 0;
        }
        return -1;
    }

    public Timeline.Period getPeriod(int i2, Timeline.Period period, boolean z) {
        Object obj;
        Assertions.checkIndex(i2, 0, 1);
        if (z) {
            obj = UID;
        } else {
            obj = null;
        }
        return period.set((Object) null, obj, 0, this.periodDurationUs, -this.windowPositionInPeriodUs);
    }

    public int getPeriodCount() {
        return 1;
    }

    public Object getUidOfPeriod(int i2) {
        Assertions.checkIndex(i2, 0, 1);
        return UID;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        if (r1 > r3) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media3.common.Timeline.Window getWindow(int r25, androidx.media3.common.Timeline.Window r26, long r27) {
        /*
            r24 = this;
            r0 = r24
            r1 = 0
            r2 = 1
            r3 = r25
            androidx.media3.common.util.Assertions.checkIndex(r3, r1, r2)
            long r1 = r0.windowDefaultStartPositionUs
            boolean r14 = r0.isDynamic
            if (r14 == 0) goto L_0x002e
            boolean r3 = r0.suppressPositionProjection
            if (r3 != 0) goto L_0x002e
            r3 = 0
            int r3 = (r27 > r3 ? 1 : (r27 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x002e
            long r3 = r0.windowDurationUs
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0027
        L_0x0024:
            r16 = r5
            goto L_0x0030
        L_0x0027:
            long r1 = r1 + r27
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x002e
            goto L_0x0024
        L_0x002e:
            r16 = r1
        L_0x0030:
            java.lang.Object r4 = androidx.media3.common.Timeline.Window.SINGLE_WINDOW_UID
            androidx.media3.common.MediaItem r5 = r0.mediaItem
            java.lang.Object r6 = r0.manifest
            long r7 = r0.presentationStartTimeMs
            long r9 = r0.windowStartTimeMs
            long r11 = r0.elapsedRealtimeEpochOffsetMs
            boolean r13 = r0.isSeekable
            androidx.media3.common.MediaItem$LiveConfiguration r15 = r0.liveConfiguration
            long r1 = r0.windowDurationUs
            r21 = 0
            r18 = r1
            long r0 = r0.windowPositionInPeriodUs
            r20 = 0
            r3 = r26
            r22 = r0
            androidx.media3.common.Timeline$Window r0 = r3.set(r4, r5, r6, r7, r9, r11, r13, r14, r15, r16, r18, r20, r21, r22)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.SinglePeriodTimeline.getWindow(int, androidx.media3.common.Timeline$Window, long):androidx.media3.common.Timeline$Window");
    }

    public int getWindowCount() {
        return 1;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SinglePeriodTimeline(long r22, long r24, long r26, long r28, boolean r30, boolean r31, boolean r32, java.lang.Object r33, androidx.media3.common.MediaItem r34) {
        /*
            r21 = this;
            r0 = r34
            if (r32 == 0) goto L_0x0009
            androidx.media3.common.MediaItem$LiveConfiguration r1 = r0.liveConfiguration
        L_0x0006:
            r20 = r1
            goto L_0x000b
        L_0x0009:
            r1 = 0
            goto L_0x0006
        L_0x000b:
            r1 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r17 = 0
            r7 = r22
            r9 = r24
            r11 = r26
            r13 = r28
            r15 = r30
            r16 = r31
            r18 = r33
            r19 = r0
            r0 = r21
            r0.<init>(r1, r3, r5, r7, r9, r11, r13, r15, r16, r17, r18, r19, r20)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.SinglePeriodTimeline.<init>(long, long, long, long, boolean, boolean, boolean, java.lang.Object, androidx.media3.common.MediaItem):void");
    }

    public SinglePeriodTimeline(long j2, long j3, long j8, long j10, long j11, long j12, long j13, boolean z, boolean z3, boolean z7, Object obj, MediaItem mediaItem2, MediaItem.LiveConfiguration liveConfiguration2) {
        this.presentationStartTimeMs = j2;
        this.windowStartTimeMs = j3;
        this.elapsedRealtimeEpochOffsetMs = j8;
        this.periodDurationUs = j10;
        this.windowDurationUs = j11;
        this.windowPositionInPeriodUs = j12;
        this.windowDefaultStartPositionUs = j13;
        this.isSeekable = z;
        this.isDynamic = z3;
        this.suppressPositionProjection = z7;
        this.manifest = obj;
        this.mediaItem = (MediaItem) Assertions.checkNotNull(mediaItem2);
        this.liveConfiguration = liveConfiguration2;
    }
}
