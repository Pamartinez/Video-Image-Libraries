package com.samsung.android.gallery.module.thumbnail;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.thumbnail.util.ThumbnailUtil;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.utils.Log;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CacheHandler extends Handler {
    private final CacheManager mCacheManager = CacheManager.getInstance();
    private final int mThreadId;
    private final ThumbnailLoader mThumbnailLoader;

    public CacheHandler(Looper looper, int i2, ThumbnailLoader thumbnailLoader) {
        super(looper);
        this.mThreadId = i2;
        this.mThumbnailLoader = thumbnailLoader;
    }

    private void findAltCache(ReqInfo reqInfo) {
        int i2;
        for (int i7 : getAltDiskCacheArray(reqInfo.thumbKind)) {
            if (i7 != reqInfo.mDiskCacheId) {
                InputStream inputStream = this.mCacheManager.getInputStream(i7, reqInfo.getAlterFileCacheKey());
                reqInfo.mInputStream = inputStream;
                if (inputStream != null) {
                    if (i7 == 5 && reqInfo.thumbKind == ThumbKind.MEDIUM_KIND) {
                        i2 = 2;
                    } else {
                        i2 = 4;
                    }
                    reqInfo.addDecodeStatus(i2);
                    return;
                }
            }
        }
    }

    private void findAltMiniCache(ReqInfo reqInfo) {
        InputStream inputStream = this.mCacheManager.getInputStream(ThumbKind.SMALL_CROP_KIND.cacheId(), ReqInfo.generateCacheKeySmallCrop(reqInfo.item));
        if (inputStream == null) {
            inputStream = this.mCacheManager.getInputStream(ThumbKind.MEDIUM_KIND.cacheId(), reqInfo.fileCacheKey);
        }
        reqInfo.mInputStream = inputStream;
    }

    public static int[] getAltDiskCacheArray(ThumbKind thumbKind) {
        if (thumbKind == ThumbKind.MEDIUM_KIND) {
            return new int[]{2, 5};
        }
        if (thumbKind == ThumbKind.SMALL_CROP_KIND) {
            return new int[]{0, 2};
        }
        if (thumbKind == ThumbKind.XSMALL_KIND) {
            return new int[]{5, 0};
        }
        return new int[0];
    }

    private void onHandleGetCache() {
        while (true) {
            ReqInfo pollLast = this.mThumbnailLoader.mReqCacheQueue.pollLast();
            if (pollLast == null) {
                break;
            } else if (!pollLast.mInterruptChecker.isInterrupted()) {
                ThumbnailInterface thumbnailInterface = pollLast.item;
                Bitmap memCache = this.mThumbnailLoader.getMemCache(thumbnailInterface, pollLast.thumbKind);
                if (memCache != null) {
                    if (pollLast.thumbKind == ThumbKind.MINI_KIND) {
                        memCache = ThumbnailUtil.getMiniCropFromBitmap(memCache, pollLast.item);
                        this.mThumbnailLoader.addToRecycler(memCache);
                    }
                    pollLast.mListener.onLoaded(memCache, pollLast.mRequestKey, pollLast.thumbKind);
                } else {
                    this.mThumbnailLoader.mMonitor.start(this.mThreadId, pollLast);
                    pollLast.removeDecodeStatus(15);
                    int cacheId = this.mThumbnailLoader.mThumbnailLogic.getCacheId(thumbnailInterface, pollLast.getThumbKind());
                    pollLast.mDiskCacheId = cacheId;
                    InputStream inputStream = this.mCacheManager.getInputStream(cacheId, pollLast.fileCacheKey);
                    pollLast.mInputStream = inputStream;
                    if (inputStream == null) {
                        if (pollLast.thumbKind == ThumbKind.MINI_KIND) {
                            findAltMiniCache(pollLast);
                        } else {
                            findAltCache(pollLast);
                        }
                    }
                    if (pollLast.mInputStream != null) {
                        pollLast.addDecodeStatus(1);
                    }
                    requestDecode(pollLast);
                    this.mThumbnailLoader.mMonitor.stop(this.mThreadId);
                }
            }
        }
        if (!this.mThumbnailLoader.mPutCacheQueue.isEmpty()) {
            sendMessage(obtainMessage(200));
        }
    }

    private void onHandlePutCache() {
        ReqInfo poll;
        while (this.mThumbnailLoader.mReqCacheQueue.isEmpty() && (poll = this.mThumbnailLoader.mPutCacheQueue.poll()) != null) {
            this.mThumbnailLoader.mMonitor.start(this.mThreadId, poll);
            this.mCacheManager.commit(poll.mDiskCacheId, poll.fileCacheKey);
            this.mThumbnailLoader.mMonitor.stop(this.mThreadId);
        }
    }

    private void requestDecode(ReqInfo reqInfo) {
        int i2;
        ReqInfo reqInfo2;
        ThumbnailLoadedListener thumbnailLoadedListener;
        UniqueKey uniqueKey = reqInfo.mRequestKey;
        if (uniqueKey != null) {
            i2 = uniqueKey.getKey();
        } else {
            i2 = reqInfo.item.hashCode();
        }
        if (reqInfo.thumbKind == ThumbKind.MINI_KIND) {
            reqInfo2 = this.mThumbnailLoader.mReqMiniDecodeQueue.put(Integer.valueOf(i2), reqInfo);
        } else {
            reqInfo2 = this.mThumbnailLoader.mReqDecodeQueue.put(Integer.valueOf(i2), reqInfo);
        }
        if (!(reqInfo2 == null || reqInfo2.item.getFileId() != reqInfo.item.getFileId() || (thumbnailLoadedListener = reqInfo2.mListener) == reqInfo.mListener)) {
            reqInfo.addExtraListener(reqInfo2.thumbKind, reqInfo2.mRequestKey, thumbnailLoadedListener);
        }
        ThumbnailHandler workableHandler = this.mThumbnailLoader.getWorkableHandler(reqInfo);
        if (workableHandler != null && !workableHandler.hasMessages(1000)) {
            reqInfo.mWorkingHandler = new WeakReference<>(workableHandler);
            workableHandler.sendEmptyMessage(1000);
        }
    }

    public void handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 100) {
            onHandleGetCache();
        } else if (i2 == 200) {
            onHandlePutCache();
        } else if (i2 == 9999) {
            Log.d("CacheHandler", "CacheHandler started");
        }
    }
}
