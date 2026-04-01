package androidx.media3.exoplayer;

import androidx.media3.common.Format;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface RendererCapabilities {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
    }

    static int create(int i2, int i7, int i8, int i10, int i11, int i12) {
        return i2 | i7 | i8 | i10 | i11 | i12;
    }

    static int getAdaptiveSupport(int i2) {
        return i2 & 24;
    }

    static int getAudioOffloadSupport(int i2) {
        return i2 & 3584;
    }

    static int getDecoderSupport(int i2) {
        return i2 & 384;
    }

    static int getFormatSupport(int i2) {
        return i2 & 7;
    }

    static int getHardwareAccelerationSupport(int i2) {
        return i2 & 64;
    }

    static int getTunnelingSupport(int i2) {
        return i2 & 32;
    }

    static boolean isFormatSupported(int i2, boolean z) {
        int formatSupport = getFormatSupport(i2);
        if (formatSupport == 4) {
            return true;
        }
        if (!z || formatSupport != 3) {
            return false;
        }
        return true;
    }

    void clearListener();

    String getName();

    int getTrackType();

    void setListener(Listener listener);

    int supportsFormat(Format format);

    int supportsMixedMimeTypeAdaptation();

    static int create(int i2) {
        return create(i2, 0, 0, 0);
    }

    static int create(int i2, int i7, int i8, int i10) {
        return create(i2, i7, i8, 0, 128, i10);
    }
}
