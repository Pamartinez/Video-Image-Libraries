package androidx.media3.common;

import androidx.media3.common.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoSize {
    private static final String FIELD_HEIGHT = Util.intToStringMaxRadix(1);
    private static final String FIELD_PIXEL_WIDTH_HEIGHT_RATIO = Util.intToStringMaxRadix(3);
    private static final String FIELD_WIDTH = Util.intToStringMaxRadix(0);
    public static final VideoSize UNKNOWN = new VideoSize(0, 0);
    public final int height;
    public final float pixelWidthHeightRatio;
    @Deprecated
    public final int unappliedRotationDegrees;
    public final int width;

    public VideoSize(int i2, int i7) {
        this(i2, i7, 1.0f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof VideoSize) {
            VideoSize videoSize = (VideoSize) obj;
            if (this.width == videoSize.width && this.height == videoSize.height && this.pixelWidthHeightRatio == videoSize.pixelWidthHeightRatio) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToRawIntBits(this.pixelWidthHeightRatio) + ((((217 + this.width) * 31) + this.height) * 31);
    }

    public VideoSize(int i2, int i7, float f) {
        this.width = i2;
        this.height = i7;
        this.unappliedRotationDegrees = 0;
        this.pixelWidthHeightRatio = f;
    }
}
