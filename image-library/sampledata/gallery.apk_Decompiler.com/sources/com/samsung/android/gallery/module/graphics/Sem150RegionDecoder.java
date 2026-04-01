package com.samsung.android.gallery.module.graphics;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.media.SemBitmapRegionDecoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem150RegionDecoder implements ImageRegionDecoder {
    final SemBitmapRegionDecoder mRegionDecoder;

    public Sem150RegionDecoder(SemBitmapRegionDecoder semBitmapRegionDecoder) {
        this.mRegionDecoder = semBitmapRegionDecoder;
    }

    public static ImageRegionDecoder newInstance(String str) {
        SemBitmapRegionDecoder newInstance = SemBitmapRegionDecoder.newInstance(str);
        if (newInstance != null) {
            return new Sem150RegionDecoder(newInstance);
        }
        return null;
    }

    public Bitmap decodeRegion(Rect rect, BitmapOptions bitmapOptions) {
        if (bitmapOptions.inPreferredHdr) {
            return BitmapUtils.requireCorrectGainmapRatio(this.mRegionDecoder.decodeRegion(rect, bitmapOptions));
        }
        return this.mRegionDecoder.decodeRegion(rect, bitmapOptions);
    }

    public int getHeight() {
        return this.mRegionDecoder.getHeight();
    }

    public int getWidth() {
        return this.mRegionDecoder.getWidth();
    }

    public boolean isRecycled() {
        return this.mRegionDecoder.isRecycled();
    }

    public void recycle() {
        this.mRegionDecoder.recycle();
    }

    public static ImageRegionDecoder newInstance(byte[] bArr, int i2, int i7) {
        SemBitmapRegionDecoder newInstance = SemBitmapRegionDecoder.newInstance(bArr, i2, i7);
        if (newInstance != null) {
            return new Sem150RegionDecoder(newInstance);
        }
        return null;
    }
}
