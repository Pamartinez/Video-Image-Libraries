package com.samsung.android.gallery.module.graphics;

import android.graphics.Bitmap;
import com.samsung.android.gallery.support.cache.BytesBuffer;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.cache.Crc;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BitmapCache {
    public static String getCacheKey(String str, long j2) {
        return String.valueOf(Crc.getCrc64Long(CacheManager.generateKey(str, j2)));
    }

    public static Bitmap getDiskCache(int i2, byte[] bArr) {
        return getDiskCache(i2, bArr, new BitmapOptions());
    }

    public static void putDiskCache(int i2, byte[] bArr, Bitmap bitmap) {
        putDiskCache(i2, bArr, bitmap, false);
    }

    public static Bitmap getDiskCache(int i2, byte[] bArr, BitmapOptions bitmapOptions) {
        BytesBuffer bytesBuffer = new BytesBuffer();
        if (CacheManager.getInstance().get(i2, bArr, bytesBuffer)) {
            return ImageDecoder.decodeByteArray(bytesBuffer.data, 0, bytesBuffer.length, bitmapOptions);
        }
        return null;
    }

    public static void putDiskCache(int i2, byte[] bArr, Bitmap bitmap, boolean z) {
        byte[] bArr2;
        if (z) {
            try {
                bArr2 = BitmapUtils.compressToBytes(bitmap, 90, Bitmap.CompressFormat.WEBP);
            } catch (Exception e) {
                Log.w("BitmapCache", "fail put diskCache : " + bitmap + "\n" + e);
                return;
            }
        } else {
            bArr2 = BitmapUtils.compressToBytes(bitmap, 90);
        }
        CacheManager.getInstance().add(i2, bArr, bArr2);
        CacheManager.getInstance().writeToFile(i2, bArr);
        CacheManager.getInstance().commit(i2, bArr);
    }
}
