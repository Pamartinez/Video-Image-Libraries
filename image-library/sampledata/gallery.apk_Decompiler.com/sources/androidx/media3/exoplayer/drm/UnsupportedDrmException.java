package androidx.media3.exoplayer.drm;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class UnsupportedDrmException extends Exception {
    public final int reason;

    public UnsupportedDrmException(int i2) {
        this.reason = i2;
    }

    public UnsupportedDrmException(int i2, Exception exc) {
        super(exc);
        this.reason = i2;
    }
}
