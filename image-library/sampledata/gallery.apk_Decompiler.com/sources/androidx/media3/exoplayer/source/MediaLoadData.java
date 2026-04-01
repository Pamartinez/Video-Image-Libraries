package androidx.media3.exoplayer.source;

import androidx.media3.common.Format;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaLoadData {
    public final int dataType;
    public final long mediaEndTimeMs;
    public final long mediaStartTimeMs;
    public final Format trackFormat;
    public final Object trackSelectionData;
    public final int trackSelectionReason;
    public final int trackType;

    public MediaLoadData(int i2) {
        this(i2, -1, (Format) null, 0, (Object) null, -9223372036854775807L, -9223372036854775807L);
    }

    public MediaLoadData(int i2, int i7, Format format, int i8, Object obj, long j2, long j3) {
        this.dataType = i2;
        this.trackType = i7;
        this.trackFormat = format;
        this.trackSelectionReason = i8;
        this.trackSelectionData = obj;
        this.mediaStartTimeMs = j2;
        this.mediaEndTimeMs = j3;
    }
}
