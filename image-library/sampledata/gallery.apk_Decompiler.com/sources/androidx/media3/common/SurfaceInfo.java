package androidx.media3.common;

import android.view.Surface;
import androidx.media3.common.util.Assertions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SurfaceInfo {
    public final int height;
    public final boolean isEncoderInputSurface;
    public final int orientationDegrees;
    public final Surface surface;
    public final int width;

    public SurfaceInfo(Surface surface2, int i2, int i7, int i8, boolean z) {
        boolean z3;
        if (i8 == 0 || i8 == 90 || i8 == 180 || i8 == 270) {
            z3 = true;
        } else {
            z3 = false;
        }
        Assertions.checkArgument(z3, "orientationDegrees must be 0, 90, 180, or 270");
        this.surface = surface2;
        this.width = i2;
        this.height = i7;
        this.orientationDegrees = i8;
        this.isEncoderInputSurface = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SurfaceInfo)) {
            return false;
        }
        SurfaceInfo surfaceInfo = (SurfaceInfo) obj;
        if (this.width == surfaceInfo.width && this.height == surfaceInfo.height && this.orientationDegrees == surfaceInfo.orientationDegrees && this.isEncoderInputSurface == surfaceInfo.isEncoderInputSurface && this.surface.equals(surfaceInfo.surface)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.surface.hashCode() * 31) + this.width) * 31) + this.height) * 31) + this.orientationDegrees) * 31) + (this.isEncoderInputSurface ? 1 : 0);
    }
}
