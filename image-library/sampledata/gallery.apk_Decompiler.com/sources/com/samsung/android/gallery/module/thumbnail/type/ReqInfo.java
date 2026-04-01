package com.samsung.android.gallery.module.thumbnail.type;

import W.a;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Handler;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ReqInfo {
    public Bitmap bitmap;
    public final byte[] fileCacheKey;
    public int imageHeight;
    public int imageWidth;
    public final boolean isHeif;
    public final ThumbnailInterface item;
    public final boolean localVideo;
    public byte[] mCacheData;
    public String mDecodeInfo = "not decoded";
    public int mDecodeStatus = 0;
    public int mDiskCacheId;
    public CopyOnWriteArrayList<ReturnData> mExtraListener;
    public final long mInitTime = System.currentTimeMillis();
    public InputStream mInputStream;
    public ThumbnailInterrupter mInterruptChecker;
    public ThumbnailLoadedListener mListener;
    public UniqueKey mRequestKey;
    public WeakReference<Handler> mWorkingHandler;
    public final String path;
    public boolean preferAndroid;
    private boolean rotateAndCropCenter;
    public final int targetSize;
    public final ThumbKind thumbKind;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ReturnData {
        public UniqueKey mKeyObj;
        public ThumbnailLoadedListener mListener;
        public ThumbKind mThumbKind;

        public ReturnData(ThumbKind thumbKind, UniqueKey uniqueKey, ThumbnailLoadedListener thumbnailLoadedListener) {
            this.mThumbKind = thumbKind;
            this.mKeyObj = uniqueKey;
            this.mListener = thumbnailLoadedListener;
        }
    }

    public ReqInfo(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind2, UniqueKey uniqueKey, ThumbnailLoadedListener thumbnailLoadedListener, ThumbnailInterrupter thumbnailInterrupter) {
        boolean z;
        byte[] bArr;
        boolean z3;
        boolean z7 = false;
        this.thumbKind = thumbKind2;
        this.item = thumbnailInterface;
        this.mRequestKey = uniqueKey;
        this.mListener = thumbnailLoadedListener;
        this.mInterruptChecker = thumbnailInterrupter;
        this.path = thumbnailInterface.getPath();
        this.imageWidth = thumbnailInterface.getWidth();
        this.imageHeight = thumbnailInterface.getHeight();
        this.targetSize = getTargetSize(thumbnailInterface, thumbKind2);
        if (thumbKind2 == ThumbKind.SMALL_CROP_KIND) {
            z = true;
        } else {
            z = false;
        }
        this.rotateAndCropCenter = z;
        if (z) {
            bArr = generateCacheKeySmallCrop(thumbnailInterface);
        } else {
            bArr = generateCacheKey(thumbnailInterface);
        }
        this.fileCacheKey = bArr;
        if (!thumbnailInterface.isLocal() || !thumbnailInterface.isHeif()) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.isHeif = z3;
        if (thumbnailInterface.isVideo() && isLocalVideo()) {
            z7 = true;
        }
        this.localVideo = z7;
    }

    public static byte[] generateCacheKey(ThumbnailInterface thumbnailInterface) {
        return CacheManager.generateKey(thumbnailInterface.getDiskCacheKey(), thumbnailInterface.getDateModified());
    }

    public static byte[] generateCacheKeySmallCrop(ThumbnailInterface thumbnailInterface) {
        return CacheManager.generateSmallCropKey(thumbnailInterface.getDiskCacheKey(), thumbnailInterface.getCropRectRatio(), thumbnailInterface.getDateModified());
    }

    private float getEnlargeRatioForFace(ThumbnailInterface thumbnailInterface, int i2, ThumbKind thumbKind2) {
        float width = (float) thumbnailInterface.getWidth();
        if (width == 0.0f) {
            if (Logger.THUMBNAIL) {
                Log.v("ReqInfo", "Item width: 0 -> Some images like HEIF can have 0 width");
            }
            return 1.0f;
        }
        int size = thumbKind2.size();
        RectF cropRectRatio = thumbnailInterface.getCropRectRatio();
        if (cropRectRatio != null) {
            int width2 = (int) (((float) ((int) (cropRectRatio.width() * width))) * (((float) size) / width));
            if (size > width2) {
                return Math.max(1.0f, ((float) i2) / ((float) width2));
            }
            return 0.0f;
        }
        Log.e("ReqInfo", "rectF is null ");
        return 0.0f;
    }

    private boolean isLocalVideo() {
        if (this.item.getVideoThumbStartTime() > 0 || this.item.isNonMovieClip()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$addExtraListener$0() {
        return false;
    }

    private boolean useFirstFrameAsThumbnail() {
        if (PreferenceFeatures.VIDEO_THUMBNAIL_WITH_FIRST_FRAME || this.item.isStories()) {
            return true;
        }
        return false;
    }

    public void addDecodeStatus(int i2) {
        this.mDecodeStatus = i2 | this.mDecodeStatus;
    }

    public void addExtraListener(ThumbKind thumbKind2, UniqueKey uniqueKey, ThumbnailLoadedListener thumbnailLoadedListener) {
        if (this.mExtraListener == null) {
            this.mExtraListener = new CopyOnWriteArrayList<>();
        }
        this.mExtraListener.add(new ReturnData(thumbKind2, uniqueKey, thumbnailLoadedListener));
        this.mInterruptChecker = new a(20);
    }

    public byte[] getAlterFileCacheKey() {
        if (this.thumbKind == ThumbKind.SMALL_CROP_KIND) {
            return generateCacheKey(this.item);
        }
        return this.fileCacheKey;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - this.mInitTime;
    }

    public ThumbnailInterface getItem() {
        return this.item;
    }

    public int getTargetSize(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind2) {
        if (!thumbnailInterface.isCreature()) {
            return thumbKind2.lowerBound();
        }
        int size = thumbKind2.size();
        return (int) (((float) ((int) (((float) size) * getEnlargeRatioForFace(thumbnailInterface, size, thumbKind2)))) * 0.75f);
    }

    public ThumbKind getThumbKind() {
        return this.thumbKind;
    }

    public long getVideoFrameTime() {
        if (this.localVideo) {
            return this.item.getVideoThumbStartTime();
        }
        if (!useFirstFrameAsThumbnail() && this.item.getFileDuration() >= 15000) {
            return 15000000;
        }
        return 0;
    }

    public String getWorkingKey() {
        return this.item.getPath() + this.thumbKind;
    }

    public boolean isDecodeExif() {
        if ((this.mDecodeStatus & 256) != 0) {
            return true;
        }
        return false;
    }

    public boolean isEnoughCache() {
        if ((this.mDecodeStatus & 4) != 0) {
            return true;
        }
        return false;
    }

    public boolean isExifDecodable() {
        if ((this.mDecodeStatus & 8) == 0) {
            return true;
        }
        return false;
    }

    public boolean isFileCacheableFromDecodeStatus() {
        int i2 = this.mDecodeStatus;
        if ((i2 & 240) != 0) {
            return false;
        }
        if ((i2 & 1) == 0 || (i2 & 4) != 0) {
            return true;
        }
        return false;
    }

    public boolean isMemCacheable() {
        if ((this.mDecodeStatus & 48) == 0) {
            return true;
        }
        return false;
    }

    public boolean isMoreDecodingRequired() {
        if ((this.mDecodeStatus & 240) != 0) {
            return true;
        }
        return false;
    }

    public boolean isScantCache() {
        if ((this.mDecodeStatus & 2) != 0) {
            return true;
        }
        return false;
    }

    public void removeDecodeStatus(int i2) {
        this.mDecodeStatus = (~i2) & this.mDecodeStatus;
    }

    public String toString() {
        Integer num;
        StringBuilder sb2 = new StringBuilder("ReqInfo{mRequestKey=");
        UniqueKey uniqueKey = this.mRequestKey;
        if (uniqueKey != null) {
            num = Integer.valueOf(uniqueKey.getKey());
        } else {
            num = null;
        }
        sb2.append(num);
        sb2.append(", mDiskCacheId=");
        sb2.append(this.mDiskCacheId);
        sb2.append(", mDecodeStatus=");
        sb2.append(this.mDecodeStatus);
        sb2.append(", mDecodeInfo='");
        sb2.append(this.mDecodeInfo);
        sb2.append("'}, ");
        sb2.append(this.item);
        return sb2.toString();
    }
}
