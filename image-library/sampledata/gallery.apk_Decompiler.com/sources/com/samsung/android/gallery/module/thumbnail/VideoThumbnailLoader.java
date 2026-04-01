package com.samsung.android.gallery.module.thumbnail;

import A.a;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.graphics.BitmapCache;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sum.core.functional.g;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoThumbnailLoader {
    private static final String CACHE_VERSION;
    static final boolean USE_ASYNC_LOADER;
    private final boolean mEnableCache;
    private final int mMode;
    private long mStartTime;

    static {
        String str;
        boolean atLeast = SdkConfig.atLeast(SdkConfig.SEM.T_MR1);
        USE_ASYNC_LOADER = atLeast;
        if (atLeast) {
            str = "v2";
        } else {
            str = "v1";
        }
        CACHE_VERSION = str;
    }

    public VideoThumbnailLoader(boolean z, int i2) {
        this.mEnableCache = z;
        this.mMode = i2;
    }

    private static boolean getCache(List<Bitmap> list, byte[] bArr) {
        Bitmap diskCache = BitmapCache.getDiskCache(6, bArr);
        int size = list.size();
        if (diskCache == null || size <= 0) {
            return false;
        }
        int width = diskCache.getWidth() / size;
        int i2 = 0;
        while (i2 < size) {
            try {
                list.set(i2, BitmapUtils.createBitmap(diskCache, width * i2, 0, width, diskCache.getHeight()));
                i2++;
            } catch (Exception e) {
                Log.e("VideoThumbLoader", "getCache fail " + e.getMessage());
                return list.isEmpty();
            }
        }
        Log.d("VideoThumbLoader", "cache hit", Integer.valueOf(size));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0(List list, byte[] bArr, Boolean bool) {
        String str;
        Object obj;
        if (!bool.booleanValue()) {
            Log.w("VideoThumbLoader", "error");
        } else if (list.size() > 0 && !list.contains((Object) null)) {
            putCache(list, bArr);
        }
        StringBuilder sb2 = new StringBuilder("loadAsync");
        if (this.mEnableCache) {
            str = "C";
        } else {
            str = "NC";
        }
        int i2 = this.mMode;
        if (i2 == 0) {
            obj = "I";
        } else if (i2 == 1) {
            obj = "P";
        } else if (i2 == 2) {
            obj = "IP";
        } else {
            obj = Integer.valueOf(i2);
        }
        a.A(new Object[]{str, obj, Long.valueOf(this.mStartTime)}, sb2, "VideoThumbLoader");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$1(List list, byte[] bArr, ThumbnailInterface thumbnailInterface, int i2) {
        if (!new VideoThumbnailAsyncLoader(this.mMode, new g(this, list, bArr, 10)).getThumbnail(thumbnailInterface, list, i2)) {
            Log.w("VideoThumbLoader", "failed");
        }
    }

    private static void putCache(List<Bitmap> list, byte[] bArr) {
        long currentTimeMillis = System.currentTimeMillis();
        int size = list.size();
        Bitmap mergeBitmap = BitmapUtils.mergeBitmap(list, size);
        if (mergeBitmap != null) {
            BitmapCache.putDiskCache(6, bArr, mergeBitmap);
            a.A(new Object[]{Integer.valueOf(size), Logger.toSimpleString(mergeBitmap), Long.valueOf(currentTimeMillis)}, new StringBuilder("cache update"), "VideoThumbLoader");
        }
    }

    public void load(ThumbnailInterface thumbnailInterface, List<Bitmap> list, int i2) {
        String str;
        Object obj;
        ThreadUtil.assertBgThread("VideoThumbLoader");
        this.mStartTime = System.currentTimeMillis();
        byte[] bytes = (CACHE_VERSION + list.size() + thumbnailInterface.getDiskCacheKey() + thumbnailInterface.getFileSize() + thumbnailInterface.getDateModified()).getBytes();
        if (this.mEnableCache && getCache(list, bytes)) {
            return;
        }
        if (USE_ASYNC_LOADER) {
            SimpleThreadPool.getInstance().execute(new D7.a(this, list, bytes, thumbnailInterface, i2, 4));
            return;
        }
        List<Bitmap> list2 = list;
        if (new VideoThumbnailSyncLoader(this.mMode).getThumbnail(thumbnailInterface, list2, i2)) {
            putCache(list2, bytes);
        }
        StringBuilder sb2 = new StringBuilder("load");
        if (this.mEnableCache) {
            str = "C";
        } else {
            str = "NC";
        }
        int i7 = this.mMode;
        if (i7 == 0) {
            obj = "I";
        } else if (i7 == 1) {
            obj = "P";
        } else if (i7 == 2) {
            obj = "IP";
        } else {
            obj = Integer.valueOf(i7);
        }
        a.A(new Object[]{str, obj, Long.valueOf(this.mStartTime)}, sb2, "VideoThumbLoader");
    }
}
