package com.samsung.android.sdk.ocr;

import android.graphics.Bitmap;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataConverter {
    private static final String TAG = "DataConverter";
    private final int mMaxImageWidthOrHeight;
    private float mScale = 1.0f;

    public DataConverter(int i2) {
        this.mMaxImageWidthOrHeight = i2;
    }

    public boolean convertResultScale(OCRResult oCRResult) {
        float f = this.mScale;
        if (f != 1.0f) {
            return oCRResult.scale(1.0f / f);
        }
        return true;
    }

    public Bitmap resizeBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int max = Math.max(width, height);
        int i2 = this.mMaxImageWidthOrHeight;
        if (max > i2) {
            float f = ((float) i2) / ((float) max);
            this.mScale = f;
            int i7 = (int) (((float) width) * f);
            int i8 = (int) (((float) height) * f);
            Log.i(TAG, String.format("Resizing: (%d, %d) => (%d, %d) (scale=%.1f)", new Object[]{Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(i7), Integer.valueOf(i8), Float.valueOf(this.mScale)}));
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i7, i8, true);
            bitmap.recycle();
            return createScaledBitmap;
        }
        this.mScale = 1.0f;
        Log.i(TAG, String.format("Resizing: (%d, %d) => No need to resize", new Object[]{Integer.valueOf(width), Integer.valueOf(height)}));
        return bitmap;
    }
}
