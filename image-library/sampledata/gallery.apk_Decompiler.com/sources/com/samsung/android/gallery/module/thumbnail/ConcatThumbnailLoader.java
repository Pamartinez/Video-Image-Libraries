package com.samsung.android.gallery.module.thumbnail;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.cache.AbsCacheMgr$EvictListener;
import com.samsung.android.gallery.module.cache.BitmapCacheMgr;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConcatThumbnailLoader {
    private static final ConcatThumbnailLoader sInstance = new ConcatThumbnailLoader();
    private Bitmap mBrokenPieceBitmap;
    private final BitmapCacheMgr<String> mConcatBitmapCacheMgr = new BitmapCacheMgr<>(50, (AbsCacheMgr$EvictListener) null);
    private boolean mIsRTL = Features.isEnabled(Features.IS_RTL);
    private final ConcurrentHashMap<String, Bitmap> mPartialConcatBitmapMemCache = new ConcurrentHashMap<>();

    public static ConcatThumbnailLoader getInstance() {
        return sInstance;
    }

    public void clearConcatCache() {
        this.mConcatBitmapCacheMgr.clearCache();
    }

    public void clearPartialConcatCache() {
        this.mPartialConcatBitmapMemCache.clear();
    }

    public Bitmap getBrokenPieceBitmap(int i2) {
        if (this.mBrokenPieceBitmap == null) {
            this.mBrokenPieceBitmap = new BitmapOperator(ThumbnailLoader.getInstance().getReplacedThumbnail(AppResources.getAppContext(), R$drawable.gallery_ic_timeview_error, R$color.cloud_only_image_bg)).resize(i2).apply();
        }
        return this.mBrokenPieceBitmap;
    }

    public Bitmap getConcatBitmap(String str) {
        if (this.mIsRTL != Features.isEnabled(Features.IS_RTL)) {
            clearConcatCache();
            clearPartialConcatCache();
            this.mIsRTL = !this.mIsRTL;
        }
        return (Bitmap) this.mConcatBitmapCacheMgr.getCache(str);
    }

    public Bitmap getPartialConcatBitmap(String str) {
        return this.mPartialConcatBitmapMemCache.get(str);
    }

    public void putConcatBitmap(String str, Bitmap bitmap) {
        this.mConcatBitmapCacheMgr.addCache(str, bitmap);
        this.mPartialConcatBitmapMemCache.remove(str);
    }

    public void putPartialConcatBitmap(String str, Bitmap bitmap) {
        this.mPartialConcatBitmapMemCache.put(str, bitmap);
    }

    public String toString() {
        return "\nmConcatBitmapCacheMgr:" + this.mConcatBitmapCacheMgr + "\nmPartialConcatBitmapMemCache size::" + this.mPartialConcatBitmapMemCache.size();
    }
}
