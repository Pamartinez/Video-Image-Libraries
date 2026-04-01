package com.samsung.o3dp.lib3dphotography.graphics;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.o3dp.lib3dphotography.nativelib.NativeInterface;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Texture extends NativeInterface {
    private static final String TAG = "Texture";
    public long textureObj;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum FitMode {
        FIT_FULL,
        FIT_BEST
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Orientation {
        ORI_NONE,
        ORI_FLIP_Y
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ScaleParams {
        public Rect dst;
        public Rect src;
    }

    public Texture() {
        this.textureObj = 0;
    }

    public static ScaleParams calculateScaling(Rect rect, Rect rect2, boolean z, FitMode fitMode) {
        int i2;
        int width = rect.width();
        int height = rect.height();
        int i7 = rect2.left;
        int i8 = rect2.top;
        int width2 = rect2.width();
        int height2 = rect2.height();
        if (fitMode == FitMode.FIT_BEST) {
            float f = (float) width2;
            float f5 = (float) height2;
            float max = Math.max(((float) width) / f, ((float) height) / f5);
            width2 = Math.round(f * max);
            height2 = Math.round(f5 * max);
        }
        if (z) {
            float f8 = (float) width2;
            float f10 = (float) height2;
            float f11 = f8 / f10;
            if (f11 > ((float) width) / ((float) height)) {
                height2 = Math.round(f8 / f11);
            } else {
                width2 = Math.round(f10 * f11);
            }
        }
        int i10 = 0;
        if (rect2.width() > width2) {
            i7 = ((rect2.width() - width2) >> 1) + rect2.left;
            i2 = 0;
        } else {
            i2 = (width2 - rect2.width()) >> 1;
        }
        if (rect2.height() > height2) {
            i8 = rect2.top + ((rect2.height() - height2) >> 1);
        } else {
            i10 = (height2 - rect2.height()) >> 1;
        }
        ScaleParams scaleParams = new ScaleParams();
        scaleParams.src = new Rect(i2, i10, (width + i2) - 1, (height + i2) - 1);
        scaleParams.dst = new Rect(i7, i8, (width2 + i7) - 1, (height2 + i8) - 1);
        return scaleParams;
    }

    public static Texture create(int i2, int i7, int i8, int i10) {
        return new Texture(nativeCreateTexture(0, 0, i2, i7, i8, i10, 0));
    }

    private static native void nativeBlitToFBO(long j2, int i2, int i7, int i8, int i10, int i11, boolean z, int i12, int i13);

    private static native void nativeCopyToTexture(long j2, int i2, int i7, int i8, int i10, int i11);

    private static native long nativeCreateTexture(int i2, int i7, int i8, int i10, int i11, int i12, int i13);

    private static native Bitmap nativeDownload(long j2);

    private static native float[] nativeDownloadDepth(long j2);

    private static native float[] nativeDownloadFloat(long j2);

    private static native int nativeFormat(long j2);

    private static native int nativeGetID(long j2);

    private static native int nativeHeight(long j2);

    private static native void nativeReleaseTexture(long j2);

    private static native void nativeResize(long j2, int i2, int i7);

    private static native int nativeType(long j2);

    private static native void nativeUpload(long j2, Bitmap bitmap);

    private static native void nativeUploadFloat(long j2, float[] fArr);

    private static native int nativeWidth(long j2);

    public static Texture wrap(int i2, int i7, int i8, int i10, int i11, int i12, int i13) {
        return new Texture(nativeCreateTexture(i7, i8, i10, i11, i12, i13, i2));
    }

    public void blitToFBO(int i2, int i7, int i8, int i10, int i11, boolean z, Orientation orientation, FitMode fitMode) {
        nativeBlitToFBO(this.textureObj, i2, i7, i8, i10, i11, z, orientation.ordinal(), fitMode.ordinal());
    }

    public Texture clear() {
        if (this.textureObj != 0) {
            Bitmap createBitmap = Bitmap.createBitmap(width(), height(), Bitmap.Config.ARGB_8888);
            createBitmap.eraseColor(-16777216);
            nativeUpload(this.textureObj, createBitmap);
        }
        return this;
    }

    public void copyToTexture(int i2, int i7, int i8, Orientation orientation, FitMode fitMode) {
        nativeCopyToTexture(this.textureObj, i2, i7, i8, orientation.ordinal(), fitMode.ordinal());
    }

    public Bitmap download() {
        return nativeDownload(this.textureObj);
    }

    public float[] downloadDepth() {
        return nativeDownloadDepth(this.textureObj);
    }

    public float[] downloadFloat() {
        return nativeDownloadFloat(this.textureObj);
    }

    public int format() {
        return nativeFormat(this.textureObj);
    }

    public int getID() {
        return nativeGetID(this.textureObj);
    }

    public Rect getRect() {
        if (this.textureObj != 0) {
            return new Rect(0, 0, width(), height());
        }
        LogUtil.e(TAG, "getRect() : textureObj is 0");
        return new Rect(0, 0, 0, 0);
    }

    public int height() {
        return nativeHeight(this.textureObj);
    }

    public void release() {
        long j2 = this.textureObj;
        if (j2 != 0) {
            nativeReleaseTexture(j2);
            this.textureObj = 0;
        }
    }

    public Texture resize(int i2, int i7) {
        long j2 = this.textureObj;
        if (j2 != 0) {
            nativeResize(j2, i2, i7);
        }
        return this;
    }

    public int type() {
        return nativeType(this.textureObj);
    }

    public Texture upload(Bitmap bitmap) {
        long j2 = this.textureObj;
        if (j2 != 0) {
            nativeUpload(j2, bitmap);
        }
        return this;
    }

    public Texture uploadTextureFloat(float[] fArr) {
        nativeUploadFloat(this.textureObj, fArr);
        return this;
    }

    public int width() {
        return nativeWidth(this.textureObj);
    }

    public static void copyToTexture(long j2, int i2, int i7, int i8, Orientation orientation, FitMode fitMode) {
        nativeCopyToTexture(j2, i2, i7, i8, orientation.ordinal(), fitMode.ordinal());
    }

    public static Texture create(int i2, int i7, int i8, int i10, int i11, int i12) {
        return new Texture(nativeCreateTexture(i2, i7, i8, i10, i11, i12, 0));
    }

    public Texture(Texture texture) {
        this.textureObj = 0;
        this.textureObj = texture.textureObj;
    }

    public Texture(long j2) {
        this.textureObj = j2;
    }
}
