package com.samsung.android.gallery.module.thumbnail;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.utils.Log;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThumbnailRecycler {
    private static final boolean DEBUG_LOGGABLE = false;
    private final ConcurrentHashMap<Integer, AtomicInteger> mBitmapReferList = new ConcurrentHashMap<>();

    public void add(Bitmap bitmap) {
        AtomicInteger atomicInteger = this.mBitmapReferList.get(Integer.valueOf(bitmap.hashCode()));
        if (atomicInteger != null) {
            atomicInteger.incrementAndGet();
            if (DEBUG_LOGGABLE) {
                Log.d("ThumbnailRecycler", "increase Thumbnail refer : " + bitmap);
                return;
            }
            return;
        }
        this.mBitmapReferList.put(Integer.valueOf(bitmap.hashCode()), new AtomicInteger(1));
        if (DEBUG_LOGGABLE) {
            Log.d("ThumbnailRecycler", "new Thumbnail refer : " + bitmap);
        }
    }

    public void tryRecycle(Bitmap bitmap) {
        int i2;
        if (bitmap != null) {
            AtomicInteger atomicInteger = this.mBitmapReferList.get(Integer.valueOf(bitmap.hashCode()));
            if (atomicInteger != null) {
                i2 = atomicInteger.decrementAndGet();
            } else {
                i2 = 0;
            }
            if (i2 == 0) {
                if (DEBUG_LOGGABLE) {
                    Log.d("ThumbnailRecycler", "remove Thumbnail refer : " + bitmap);
                }
                this.mBitmapReferList.remove(Integer.valueOf(bitmap.hashCode()));
                BitmapUtils.putBitmap(bitmap);
            } else if (DEBUG_LOGGABLE) {
                Log.d("ThumbnailRecycler", "reduce Thumbnail refer : " + bitmap);
            }
        }
    }
}
