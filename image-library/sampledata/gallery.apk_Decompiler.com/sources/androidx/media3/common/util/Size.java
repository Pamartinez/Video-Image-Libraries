package androidx.media3.common.util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Size {
    public static final Size UNKNOWN = new Size(-1, -1);
    public static final Size ZERO = new Size(0, 0);
    private final int height;
    private final int width;

    public Size(int i2, int i7) {
        boolean z;
        if ((i2 == -1 || i2 >= 0) && (i7 == -1 || i7 >= 0)) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z);
        this.width = i2;
        this.height = i7;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.width == size.width && this.height == size.height) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int i2 = this.height;
        int i7 = this.width;
        return ((i7 >>> 16) | (i7 << 16)) ^ i2;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }
}
