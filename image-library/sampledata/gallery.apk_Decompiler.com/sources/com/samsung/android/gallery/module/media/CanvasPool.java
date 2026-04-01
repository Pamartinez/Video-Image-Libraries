package com.samsung.android.gallery.module.media;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.samsung.android.gallery.module.graphics.BitmapUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CanvasPool {
    private final Bitmap[] mBitmap;
    private final Canvas[] mCanvas;
    private int mRef;

    public CanvasPool(int i2, int i7) {
        this(i2, i7, true);
    }

    public synchronized Bitmap updateAndGet(GifAnimation gifAnimation) {
        Bitmap bitmap;
        Bitmap[] bitmapArr = this.mBitmap;
        int i2 = this.mRef;
        bitmap = bitmapArr[i2];
        gifAnimation.drawFrame(bitmap, this.mCanvas[i2]);
        this.mRef = (this.mRef + 1) % 4;
        return bitmap;
    }

    public CanvasPool(int i2, int i7, boolean z) {
        this.mBitmap = new Bitmap[4];
        this.mCanvas = new Canvas[4];
        for (int i8 = 0; i8 < 4; i8++) {
            this.mBitmap[i8] = BitmapUtils.createBitmap(i2, i7, Bitmap.Config.ARGB_8888);
            if (z) {
                this.mCanvas[i8] = new Canvas(this.mBitmap[i8]);
            }
        }
    }
}
