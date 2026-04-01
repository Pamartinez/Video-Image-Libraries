package com.samsung.android.sdk.sgpl.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface CodecBitmapPool {
    Bitmap create(int i2, int i7, Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        ColorSpace colorSpace = bitmap.getColorSpace();
        Bitmap bitmap2 = get(i2, i7, config, colorSpace);
        if (colorSpace != null) {
            try {
                bitmap2.setColorSpace(colorSpace);
                return bitmap2;
            } catch (IllegalArgumentException unused) {
                String simpleName = getClass().getSimpleName();
                Log.w(simpleName, "setColorSpace failed {" + bitmap2.getColorSpace() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + colorSpace + "}");
            }
        }
        return bitmap2;
    }

    Bitmap get(int i2, int i7, Bitmap.Config config) {
        if (config == null || config == Bitmap.Config.HARDWARE) {
            config = Bitmap.Config.ARGB_8888;
        }
        return Bitmap.createBitmap(i2, i7, config);
    }

    void trim(int i2) {
        clear();
    }

    Bitmap get(int i2, int i7, Bitmap.Config config, ColorSpace colorSpace) {
        if (config == null || config == Bitmap.Config.HARDWARE) {
            config = Bitmap.Config.ARGB_8888;
        }
        return Bitmap.createBitmap(i2, i7, config, true, colorSpace);
    }

    Bitmap get(int i2, int i7, Bitmap.Config config, int i8) {
        Bitmap bitmap = get(i2, i7, config);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(i8);
        canvas.drawRect(0.0f, 0.0f, (float) i2, (float) i7, paint);
        return bitmap;
    }

    void clear() {
    }

    void close() {
    }

    void init() {
    }

    void open() {
    }

    void release() {
    }

    void apply(BitmapFactory.Options options) {
    }

    void put(Bitmap bitmap) {
    }
}
