package com.samsung.android.sdk.sgpl.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Gainmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import c0.C0086a;
import com.samsung.android.ocr.MOCRLang;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BitmapToolkit {
    static boolean DEBUG = false;
    static final String TAG = "BitmapToolkit";
    static CodecBitmapPool bitmapPool = new CodecBitmapPool() {
    };

    public static void applyBitmap(BitmapFactory.Options options) {
        bitmapPool.apply(options);
    }

    public static Bitmap createBitmap(int i2, int i7, Bitmap bitmap) {
        return bitmapPool.create(i2, i7, bitmap);
    }

    public static void putBitmap(Bitmap bitmap) {
        bitmapPool.put(bitmap);
    }

    public static Bitmap resize(Bitmap bitmap, int i2) {
        Gainmap f;
        int max = bitmap == null ? 0 : Math.max(bitmap.getWidth(), bitmap.getHeight());
        if (max == 0 || max <= i2) {
            return bitmap;
        }
        long currentTimeMillis = DEBUG ? System.currentTimeMillis() : 0;
        Bitmap resize = resize(bitmap, ((float) i2) / ((float) max));
        if (Build.VERSION.SDK_INT >= 34 && (f = bitmap.getGainmap()) != null) {
            resize.setGainmap(f);
        }
        if (DEBUG) {
            Log.v(TAG, C0086a.j(currentTimeMillis, Log.vars(resize), " +", new StringBuilder("resize")));
        }
        return resize;
    }

    public static Bitmap rotate(Bitmap bitmap, int i2) {
        return rotate(bitmap, i2, 0, 0);
    }

    public static Bitmap rotate(Bitmap bitmap, int i2, int i7) {
        return rotate(bitmap, i2, 0, i7);
    }

    public static Bitmap rotate(Bitmap bitmap, int i2, int i7, int i8) {
        int i10 = i2 % MOCRLang.KHMER;
        int width = i10 == 0 ? bitmap.getWidth() : bitmap.getHeight();
        int height = i10 == 0 ? bitmap.getHeight() : bitmap.getWidth();
        if (i8 > 0) {
            float max = ((float) i8) / ((float) Math.max(width, height));
            if (max < 1.0f) {
                width = Math.round(((float) width) * max);
                height = Math.round(((float) height) * max);
            }
        }
        Matrix build = new MatrixBuilder(bitmap, width, height).setOrientation(i2, i7).build();
        Bitmap createBitmap = createBitmap(width, height, bitmap);
        new Canvas(createBitmap).drawBitmap(bitmap, build, new Paint(6));
        if (createBitmap != bitmap) {
            putBitmap(bitmap);
        }
        return createBitmap;
    }

    public static Bitmap resize(Bitmap bitmap, float f) {
        Bitmap createBitmap = createBitmap(Math.round(((float) bitmap.getWidth()) * f), Math.round(((float) bitmap.getHeight()) * f), bitmap);
        Canvas canvas = new Canvas(createBitmap);
        canvas.scale(f, f);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(6));
        if (bitmap != createBitmap) {
            putBitmap(bitmap);
        }
        return createBitmap;
    }
}
