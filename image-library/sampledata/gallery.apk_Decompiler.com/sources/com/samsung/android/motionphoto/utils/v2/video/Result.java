package com.samsung.android.motionphoto.utils.v2.video;

import android.media.MediaFormat;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001BE\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\u0016\u001a\u00020\u0017J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\nHÆ\u0003JG\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010\u001f\u001a\u00020\u00172\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020$HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006%"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/Result;", "", "status", "Lcom/samsung/android/motionphoto/utils/v2/video/ExportEvent;", "transferTimeMs", "", "transcodingTimeMs", "startOffsetTimeUs", "durationUs", "format", "Landroid/media/MediaFormat;", "<init>", "(Lcom/samsung/android/motionphoto/utils/v2/video/ExportEvent;JJJJLandroid/media/MediaFormat;)V", "getStatus", "()Lcom/samsung/android/motionphoto/utils/v2/video/ExportEvent;", "getTransferTimeMs", "()J", "getTranscodingTimeMs", "getStartOffsetTimeUs", "getDurationUs", "getFormat", "()Landroid/media/MediaFormat;", "isOk", "", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Result {
    private final long durationUs;
    private final MediaFormat format;
    private final long startOffsetTimeUs;
    private final ExportEvent status;
    private final long transcodingTimeMs;
    private final long transferTimeMs;

    public Result() {
        this((ExportEvent) null, 0, 0, 0, 0, (MediaFormat) null, 63, (e) null);
    }

    public static /* synthetic */ Result copy$default(Result result, ExportEvent exportEvent, long j2, long j3, long j8, long j10, MediaFormat mediaFormat, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            exportEvent = result.status;
        }
        if ((i2 & 2) != 0) {
            j2 = result.transferTimeMs;
        }
        if ((i2 & 4) != 0) {
            j3 = result.transcodingTimeMs;
        }
        if ((i2 & 8) != 0) {
            j8 = result.startOffsetTimeUs;
        }
        if ((i2 & 16) != 0) {
            j10 = result.durationUs;
        }
        if ((i2 & 32) != 0) {
            mediaFormat = result.format;
        }
        MediaFormat mediaFormat2 = mediaFormat;
        long j11 = j10;
        long j12 = j8;
        long j13 = j3;
        return result.copy(exportEvent, j2, j13, j12, j11, mediaFormat2);
    }

    public final ExportEvent component1() {
        return this.status;
    }

    public final long component2() {
        return this.transferTimeMs;
    }

    public final long component3() {
        return this.transcodingTimeMs;
    }

    public final long component4() {
        return this.startOffsetTimeUs;
    }

    public final long component5() {
        return this.durationUs;
    }

    public final MediaFormat component6() {
        return this.format;
    }

    public final Result copy(ExportEvent exportEvent, long j2, long j3, long j8, long j10, MediaFormat mediaFormat) {
        j.e(exportEvent, "status");
        return new Result(exportEvent, j2, j3, j8, j10, mediaFormat);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Result)) {
            return false;
        }
        Result result = (Result) obj;
        if (this.status == result.status && this.transferTimeMs == result.transferTimeMs && this.transcodingTimeMs == result.transcodingTimeMs && this.startOffsetTimeUs == result.startOffsetTimeUs && this.durationUs == result.durationUs && j.a(this.format, result.format)) {
            return true;
        }
        return false;
    }

    public final long getDurationUs() {
        return this.durationUs;
    }

    public final MediaFormat getFormat() {
        return this.format;
    }

    public final long getStartOffsetTimeUs() {
        return this.startOffsetTimeUs;
    }

    public final ExportEvent getStatus() {
        return this.status;
    }

    public final long getTranscodingTimeMs() {
        return this.transcodingTimeMs;
    }

    public final long getTransferTimeMs() {
        return this.transferTimeMs;
    }

    public int hashCode() {
        int i2;
        int c5 = C0212a.c(C0212a.c(C0212a.c(C0212a.c(this.status.hashCode() * 31, 31, this.transferTimeMs), 31, this.transcodingTimeMs), 31, this.startOffsetTimeUs), 31, this.durationUs);
        MediaFormat mediaFormat = this.format;
        if (mediaFormat == null) {
            i2 = 0;
        } else {
            i2 = mediaFormat.hashCode();
        }
        return c5 + i2;
    }

    public final boolean isOk() {
        if (this.status == ExportEvent.EXECUTE_COMPLETE) {
            return true;
        }
        return false;
    }

    public String toString() {
        ExportEvent exportEvent = this.status;
        long j2 = this.transferTimeMs;
        long j3 = this.transcodingTimeMs;
        long j8 = this.startOffsetTimeUs;
        long j10 = this.durationUs;
        MediaFormat mediaFormat = this.format;
        return "Result(status=" + exportEvent + ", transferTimeMs=" + j2 + ", transcodingTimeMs=" + j3 + ", startOffsetTimeUs=" + j8 + ", durationUs=" + j10 + ", format=" + mediaFormat + ")";
    }

    public Result(ExportEvent exportEvent, long j2, long j3, long j8, long j10, MediaFormat mediaFormat) {
        j.e(exportEvent, "status");
        this.status = exportEvent;
        this.transferTimeMs = j2;
        this.transcodingTimeMs = j3;
        this.startOffsetTimeUs = j8;
        this.durationUs = j10;
        this.format = mediaFormat;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Result(com.samsung.android.motionphoto.utils.v2.video.ExportEvent r3, long r4, long r6, long r8, long r10, android.media.MediaFormat r12, int r13, kotlin.jvm.internal.e r14) {
        /*
            r2 = this;
            r14 = r13 & 1
            if (r14 == 0) goto L_0x0006
            com.samsung.android.motionphoto.utils.v2.video.ExportEvent r3 = com.samsung.android.motionphoto.utils.v2.video.ExportEvent.EXECUTE_COMPLETE
        L_0x0006:
            r14 = r13 & 2
            r0 = -1
            if (r14 == 0) goto L_0x000d
            r4 = r0
        L_0x000d:
            r14 = r13 & 4
            if (r14 == 0) goto L_0x0012
            r6 = r0
        L_0x0012:
            r14 = r13 & 8
            if (r14 == 0) goto L_0x0017
            r8 = r0
        L_0x0017:
            r14 = r13 & 16
            if (r14 == 0) goto L_0x001c
            r10 = r0
        L_0x001c:
            r13 = r13 & 32
            if (r13 == 0) goto L_0x0021
            r12 = 0
        L_0x0021:
            r14 = r12
            r12 = r10
            r10 = r8
            r8 = r6
            r6 = r4
            r4 = r2
            r5 = r3
            r4.<init>(r5, r6, r8, r10, r12, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.video.Result.<init>(com.samsung.android.motionphoto.utils.v2.video.ExportEvent, long, long, long, long, android.media.MediaFormat, int, kotlin.jvm.internal.e):void");
    }
}
