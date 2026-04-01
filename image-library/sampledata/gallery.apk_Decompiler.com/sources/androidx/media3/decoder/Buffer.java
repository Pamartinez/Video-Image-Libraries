package androidx.media3.decoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Buffer {
    private int flags;

    public final void addFlag(int i2) {
        this.flags = i2 | this.flags;
    }

    public void clear() {
        this.flags = 0;
    }

    public final boolean getFlag(int i2) {
        if ((this.flags & i2) == i2) {
            return true;
        }
        return false;
    }

    public final boolean hasSupplementalData() {
        return getFlag(268435456);
    }

    public final boolean isEndOfStream() {
        return getFlag(4);
    }

    public final boolean isKeyFrame() {
        return getFlag(1);
    }

    public final void setFlags(int i2) {
        this.flags = i2;
    }
}
