package com.samsung.android.gallery.module.graphics;

import A.a;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import com.samsung.android.gallery.support.utils.FileUtils;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AndroidRegionDecoder implements ImageRegionDecoder {
    final BitmapRegionDecoder mRegionDecoder;

    public AndroidRegionDecoder(BitmapRegionDecoder bitmapRegionDecoder) {
        this.mRegionDecoder = bitmapRegionDecoder;
    }

    public static AndroidRegionDecoder newInstance(String str, boolean z) {
        try {
            requireNotOverflow(str);
            BitmapRegionDecoder newInstance = BitmapRegionDecoder.newInstance(str, z);
            if (newInstance != null) {
                return new AndroidRegionDecoder(newInstance);
            }
            return null;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("newInstance failed. e="), "AndroidRegionDecoder");
            return null;
        }
    }

    public static void requireNotOverflow(String str) {
        if (FileUtils.length(str) >= 2147483647L) {
            throw new IllegalArgumentException("image file size overflow " + FileUtils.info(str));
        }
    }

    public Bitmap decodeRegion(Rect rect, BitmapOptions bitmapOptions) {
        return this.mRegionDecoder.decodeRegion(rect, bitmapOptions);
    }

    public int getHeight() {
        return this.mRegionDecoder.getHeight();
    }

    public int getWidth() {
        return this.mRegionDecoder.getWidth();
    }

    public final boolean isRecycled() {
        return this.mRegionDecoder.isRecycled();
    }

    public void recycle() {
        this.mRegionDecoder.recycle();
    }

    public static AndroidRegionDecoder newInstance(InputStream inputStream, boolean z) {
        try {
            BitmapRegionDecoder newInstance = BitmapRegionDecoder.newInstance(inputStream, z);
            if (newInstance != null) {
                return new AndroidRegionDecoder(newInstance);
            }
            return null;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("newInstance(stream) failed. e="), "AndroidRegionDecoder");
            return null;
        }
    }

    public static AndroidRegionDecoder newInstance(byte[] bArr, int i2, int i7, boolean z) {
        try {
            BitmapRegionDecoder newInstance = BitmapRegionDecoder.newInstance(bArr, i2, i7, z);
            if (newInstance != null) {
                return new AndroidRegionDecoder(newInstance);
            }
            return null;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("newInstance(byte) failed. e="), "AndroidRegionDecoder");
            return null;
        }
    }
}
