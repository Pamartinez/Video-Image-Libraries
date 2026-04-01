package androidx.media3.muxer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BufferInfo {
    public final int flags;
    public final long presentationTimeUs;
    public final int size;

    public BufferInfo(long j2, int i2, int i7) {
        this.presentationTimeUs = j2;
        this.size = i2;
        this.flags = i7;
    }
}
