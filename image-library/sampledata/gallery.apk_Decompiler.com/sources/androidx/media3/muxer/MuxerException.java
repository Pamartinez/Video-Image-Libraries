package androidx.media3.muxer;

import androidx.media3.common.MediaLibraryInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MuxerException extends Exception {
    static {
        MediaLibraryInfo.registerModule("media3.muxer");
    }

    public MuxerException(String str, Throwable th) {
        super(str, th);
    }
}
