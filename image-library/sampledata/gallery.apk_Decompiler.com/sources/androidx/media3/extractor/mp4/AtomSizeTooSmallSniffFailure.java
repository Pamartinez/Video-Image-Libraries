package androidx.media3.extractor.mp4;

import androidx.media3.extractor.SniffFailure;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AtomSizeTooSmallSniffFailure implements SniffFailure {
    public final long atomSize;
    public final int atomType;
    public final int minimumHeaderSize;

    public AtomSizeTooSmallSniffFailure(int i2, long j2, int i7) {
        this.atomType = i2;
        this.atomSize = j2;
        this.minimumHeaderSize = i7;
    }
}
