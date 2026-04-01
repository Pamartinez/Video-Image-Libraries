package androidx.media3.exoplayer;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import i.C0212a;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExoPlaybackException extends PlaybackException {
    final boolean isRecoverable;
    public final MediaSource.MediaPeriodId mediaPeriodId;
    public final Format rendererFormat;
    public final int rendererFormatSupport;
    public final int rendererIndex;
    public final String rendererName;
    public final int type;

    private ExoPlaybackException(int i2, Throwable th, int i7) {
        this(i2, th, (String) null, i7, (String) null, -1, (Format) null, 4, (MediaSource.MediaPeriodId) null, false);
    }

    public static ExoPlaybackException createForSource(IOException iOException, int i2) {
        return new ExoPlaybackException(0, iOException, i2);
    }

    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException, int i2) {
        return new ExoPlaybackException(2, runtimeException, i2);
    }

    private static String deriveMessage(int i2, String str, String str2, int i7, Format format, int i8) {
        String str3;
        if (i2 == 0) {
            str3 = "Source error";
        } else if (i2 == 1) {
            str3 = str2 + " error, index=" + i7 + ", format=" + format + ", format_supported=" + Util.getFormatSupportString(i8);
        } else if (i2 != 3) {
            str3 = "Unexpected runtime error";
        } else {
            str3 = "Remote error";
        }
        if (!TextUtils.isEmpty(str)) {
            return C0212a.B(str3, ": ", str);
        }
        return str3;
    }

    public ExoPlaybackException copyWithMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId2) {
        return new ExoPlaybackException((String) Util.castNonNull(getMessage()), getCause(), this.errorCode, this.type, this.rendererName, this.rendererIndex, this.rendererFormat, this.rendererFormatSupport, mediaPeriodId2, this.timestampMs, this.isRecoverable);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private ExoPlaybackException(int r14, java.lang.Throwable r15, java.lang.String r16, int r17, java.lang.String r18, int r19, androidx.media3.common.Format r20, int r21, androidx.media3.exoplayer.source.MediaSource.MediaPeriodId r22, boolean r23) {
        /*
            r13 = this;
            r0 = r14
            r1 = r16
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = r21
            java.lang.String r1 = deriveMessage(r0, r1, r2, r3, r4, r5)
            long r10 = android.os.SystemClock.elapsedRealtime()
            r0 = r13
            r4 = r14
            r2 = r15
            r3 = r17
            r5 = r18
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r12 = r23
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlaybackException.<init>(int, java.lang.Throwable, java.lang.String, int, java.lang.String, int, androidx.media3.common.Format, int, androidx.media3.exoplayer.source.MediaSource$MediaPeriodId, boolean):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ExoPlaybackException(String str, Throwable th, int i2, int i7, String str2, int i8, Format format, int i10, MediaSource.MediaPeriodId mediaPeriodId2, long j2, boolean z) {
        super(str, th, i2, Bundle.EMPTY, j2);
        boolean z3 = z;
        boolean z7 = false;
        Assertions.checkArgument(!z3 || i7 == 1);
        Assertions.checkArgument((th != null || i7 == 3) ? true : z7);
        this.type = i7;
        this.rendererName = str2;
        this.rendererIndex = i8;
        this.rendererFormat = format;
        this.rendererFormatSupport = i10;
        this.mediaPeriodId = mediaPeriodId2;
        this.isRecoverable = z3;
    }
}
