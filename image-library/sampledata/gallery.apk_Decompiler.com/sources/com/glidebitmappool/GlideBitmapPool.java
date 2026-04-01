package com.glidebitmappool;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.glidebitmappool.internal.BitmapPool;
import com.glidebitmappool.internal.LruBitmapPool;
import com.samsung.android.gallery.support.library.SeApiCompat;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GlideBitmapPool {
    private static GlideBitmapPool sInstance;
    private final BitmapPool bitmapPool;

    private GlideBitmapPool(int i2) {
        this.bitmapPool = new LruBitmapPool((long) i2);
    }

    public static void clearMemory() {
        getInstance().bitmapPool.clearMemory();
    }

    public static Bitmap getBitmap(int i2, int i7, Bitmap.Config config) {
        return getInstance().bitmapPool.get(i2, i7, config);
    }

    private static GlideBitmapPool getInstance() {
        if (sInstance == null) {
            sInstance = new GlideBitmapPool(6291456);
        }
        return sInstance;
    }

    public static void initialize(int i2, Set<Bitmap.Config> set) {
        sInstance = new GlideBitmapPool(i2, set);
    }

    public static void putBitmap(Bitmap bitmap) {
        SeApiCompat.clearBitmapTag(bitmap);
        getInstance().bitmapPool.put(bitmap);
    }

    public static void trimMemory(int i2) {
        getInstance().bitmapPool.trimMemory(i2);
    }

    public static Bitmap getBitmap(int i2, int i7, Bitmap.Config config, ColorSpace colorSpace) {
        return getInstance().bitmapPool.get(i2, i7, config, colorSpace);
    }

    private GlideBitmapPool(int i2, Set<Bitmap.Config> set) {
        this.bitmapPool = new LruBitmapPool((long) i2, set);
    }
}
