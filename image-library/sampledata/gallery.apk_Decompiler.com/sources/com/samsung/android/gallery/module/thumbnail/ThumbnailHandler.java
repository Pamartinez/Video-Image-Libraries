package com.samsung.android.gallery.module.thumbnail;

import A.a;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dal.local.recovery.RecoverCollector;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.graphics.ImageDecoder;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.util.ThumbnailUtil;
import com.samsung.android.gallery.support.cache.BytesBuffer;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.FileLogger;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ThumbnailHandler extends Handler {
    private final Object LOCK = new Object();
    private final int mId;
    private final ThumbnailLoader mThumbnailLoader;

    public ThumbnailHandler(Looper looper, int i2, ThumbnailLoader thumbnailLoader) {
        super(looper);
        this.mId = i2;
        this.mThumbnailLoader = thumbnailLoader;
    }

    private boolean checkInterrupted(ReqInfo reqInfo, Bitmap bitmap) {
        if (!reqInfo.mInterruptChecker.isInterrupted()) {
            return false;
        }
        if (Logger.THUMBNAIL) {
            Log.v("ThumbnailHandler", "skip by interrupt");
        }
        this.mThumbnailLoader.recycle((String) null, bitmap);
        return true;
    }

    private ReqInfo getNextDecodeRequest() {
        Enumeration<Integer> keys = this.mThumbnailLoader.mReqDecodeQueue.keys();
        if (keys.hasMoreElements()) {
            return this.mThumbnailLoader.mReqDecodeQueue.remove(keys.nextElement());
        }
        Enumeration<Integer> keys2 = this.mThumbnailLoader.mReqMiniDecodeQueue.keys();
        if (keys2.hasMoreElements()) {
            return this.mThumbnailLoader.mReqMiniDecodeQueue.remove(keys2.nextElement());
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0072, code lost:
        if (r1 == null) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007a, code lost:
        if (r1.mInterruptChecker.isInterrupted() == false) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007d, code lost:
        monitorStart(r1);
        r4.mThumbnailLoader.mDecoderWorkingQueue.put(r1.getWorkingKey(), r1);
        processThumbReq(r1);
        r4.mThumbnailLoader.mDecoderWorkingQueue.remove(r1.getWorkingKey());
        monitorStop();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleRequestDecode() {
        /*
            r4 = this;
        L_0x0000:
            java.lang.Object r0 = r4.LOCK
            monitor-enter(r0)
            com.samsung.android.gallery.module.thumbnail.type.ReqInfo r1 = r4.getNextDecodeRequest()     // Catch:{ all -> 0x0018 }
            if (r1 != 0) goto L_0x006c
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r1 = r4.mThumbnailLoader     // Catch:{ all -> 0x0018 }
            java.util.concurrent.ConcurrentLinkedQueue<com.samsung.android.gallery.module.thumbnail.type.ReqInfo> r1 = r1.mReqDecodeOriginQueue     // Catch:{ all -> 0x0018 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x001b
            r4.requestFileWrite()     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            goto L_0x0031
        L_0x0018:
            r4 = move-exception
            goto L_0x009e
        L_0x001b:
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r1 = r4.mThumbnailLoader     // Catch:{ all -> 0x0018 }
            boolean r2 = r1.mPostponed     // Catch:{ all -> 0x0018 }
            if (r2 != 0) goto L_0x0053
            r1 = 1000(0x3e8, float:1.401E-42)
            r4.removeMessages(r1)     // Catch:{ all -> 0x0018 }
            r2 = 10
            r4.sendEmptyMessageDelayed(r1, r2)     // Catch:{ all -> 0x0018 }
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r1 = r4.mThumbnailLoader     // Catch:{ all -> 0x0018 }
            r2 = 1
            r1.mPostponed = r2     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
        L_0x0031:
            boolean r0 = com.samsung.android.gallery.support.utils.Logger.THUMBNAIL
            if (r0 == 0) goto L_0x003c
            java.lang.String r0 = "ThumbnailHandler"
            java.lang.String r1 = "no more Thumb Request"
            com.samsung.android.gallery.support.utils.Log.v(r0, r1)
        L_0x003c:
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r0 = r4.mThumbnailLoader
            java.util.concurrent.atomic.AtomicInteger r0 = r0.mRefCount
            int r0 = r0.get()
            if (r0 != 0) goto L_0x0052
            java.lang.String r0 = "ThumbnailHandler"
            java.lang.String r1 = "mRefCount is zero. stop self"
            com.samsung.android.gallery.support.utils.Log.e(r0, r1)
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r4 = r4.mThumbnailLoader
            r4.destroy()
        L_0x0052:
            return
        L_0x0053:
            r4.trimOriginalDecodingQueue(r1)     // Catch:{ all -> 0x0018 }
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r1 = r4.mThumbnailLoader     // Catch:{ all -> 0x0018 }
            java.util.concurrent.ConcurrentLinkedQueue<com.samsung.android.gallery.module.thumbnail.type.ReqInfo> r1 = r1.mReqDecodeOriginQueue     // Catch:{ all -> 0x0018 }
            java.lang.Object r1 = r1.poll()     // Catch:{ all -> 0x0018 }
            com.samsung.android.gallery.module.thumbnail.type.ReqInfo r1 = (com.samsung.android.gallery.module.thumbnail.type.ReqInfo) r1     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x0071
            boolean r2 = com.samsung.android.gallery.support.utils.PerformanceLog.isEnabled()     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x0071
            r4.logDelayedOriginalDecode(r1)     // Catch:{ all -> 0x0018 }
            goto L_0x0071
        L_0x006c:
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r2 = r4.mThumbnailLoader     // Catch:{ all -> 0x0018 }
            r3 = 0
            r2.mPostponed = r3     // Catch:{ all -> 0x0018 }
        L_0x0071:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x0000
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterrupter r0 = r1.mInterruptChecker
            boolean r0 = r0.isInterrupted()
            if (r0 == 0) goto L_0x007d
            goto L_0x0000
        L_0x007d:
            r4.monitorStart(r1)
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r0 = r4.mThumbnailLoader
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.samsung.android.gallery.module.thumbnail.type.ReqInfo> r0 = r0.mDecoderWorkingQueue
            java.lang.String r2 = r1.getWorkingKey()
            r0.put(r2, r1)
            r4.processThumbReq(r1)
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r0 = r4.mThumbnailLoader
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.samsung.android.gallery.module.thumbnail.type.ReqInfo> r0 = r0.mDecoderWorkingQueue
            java.lang.String r1 = r1.getWorkingKey()
            r0.remove(r1)
            r4.monitorStop()
            goto L_0x0000
        L_0x009e:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.thumbnail.ThumbnailHandler.handleRequestDecode():void");
    }

    private void handleRequestFileWrite() {
        ReqInfo poll;
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        while (instance.mReqDecodeQueue.isEmpty() && instance.mReqCacheQueue.isEmpty() && (poll = instance.mReqFileWriteQueue.poll()) != null) {
            monitorStart(poll);
            CacheManager.getInstance().writeToFile(poll.mDiskCacheId, poll.fileCacheKey);
            monitorStop();
        }
    }

    private boolean isFileCacheable(ReqInfo reqInfo, Bitmap bitmap) {
        ThumbKind thumbKind;
        if (!reqInfo.isFileCacheableFromDecodeStatus() || (thumbKind = reqInfo.thumbKind) == null || thumbKind.cacheId() == 99 || reqInfo.item.isUriItem() || reqInfo.item.getStorageType() == StorageType.RemoteItem || bitmap.getByteCount() >= 104857600) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.util.function.Predicate, java.lang.Object] */
    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$trimOriginalDecodingQueue$1(ThumbnailLoader thumbnailLoader, CountDownLatch countDownLatch) {
        int size = thumbnailLoader.mReqDecodeOriginQueue.size();
        thumbnailLoader.mReqDecodeOriginQueue.removeIf(new Object());
        countDownLatch.countDown();
        int size2 = size - thumbnailLoader.mReqDecodeOriginQueue.size();
        if (size2 > 0) {
            a.k(size2, "trim size=", "ThumbnailHandler");
        }
    }

    private void monitorStart(ReqInfo reqInfo) {
        int i2 = this.mId;
        if (i2 > -1) {
            this.mThumbnailLoader.mMonitor.start(i2, reqInfo);
        }
    }

    private void monitorStop() {
        int i2 = this.mId;
        if (i2 > -1) {
            this.mThumbnailLoader.mMonitor.stop(i2);
        }
    }

    private void notifyListeners(ReqInfo reqInfo, Bitmap bitmap) {
        reqInfo.mListener.onLoaded(bitmap, reqInfo.mRequestKey, reqInfo.thumbKind);
        CopyOnWriteArrayList<ReqInfo.ReturnData> copyOnWriteArrayList = reqInfo.mExtraListener;
        if (copyOnWriteArrayList != null) {
            Iterator<ReqInfo.ReturnData> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ReqInfo.ReturnData next = it.next();
                if (next != null) {
                    this.mThumbnailLoader.addToRecycler(bitmap);
                    next.mListener.onLoaded(bitmap, next.mKeyObj, next.mThumbKind);
                }
            }
        }
    }

    private void postProcessThumbRequest(ReqInfo reqInfo, Bitmap bitmap) {
        if (this.mThumbnailLoader.mRefCount.get() == 0) {
            Log.e("ThumbnailHandler", "mRefCount is zero, app already destroyed. try to stop self");
            this.mThumbnailLoader.destroy();
            return;
        }
        if (bitmap != null) {
            this.mThumbnailLoader.addToRecycler(bitmap);
            if (!checkInterrupted(reqInfo, bitmap)) {
                if (reqInfo.isMemCacheable()) {
                    this.mThumbnailLoader.updateMemCache(reqInfo.item, reqInfo.getThumbKind(), bitmap);
                    this.mThumbnailLoader.updatePppMemCache(bitmap, reqInfo.item, reqInfo.getThumbKind());
                }
            } else {
                return;
            }
        } else if (Logger.THUMBNAIL) {
            Log.v("ThumbnailHandler", "bmp = null");
        }
        if (!checkInterrupted(reqInfo, bitmap)) {
            if (bitmap == null) {
                addDecodeFailLog(reqInfo.item);
            }
            notifyListeners(reqInfo, bitmap);
            ThumbnailLoader thumbnailLoader = this.mThumbnailLoader;
            if (thumbnailLoader.mEndTime != -1) {
                thumbnailLoader.mEndTime = System.currentTimeMillis();
            }
            reqInfo.mWorkingHandler = null;
        }
    }

    private void requestCachePut(ReqInfo reqInfo, Bitmap bitmap) {
        Bitmap.CompressFormat compressFormat;
        int i2;
        if (reqInfo.mDiskCacheId != 0 || bitmap.getWidth() > 128 || bitmap.getHeight() > 128 || reqInfo.item.getWidth() == 0 || reqInfo.item.getHeight() == 0 || (reqInfo.item.getWidth() == bitmap.getWidth() && reqInfo.item.getHeight() == bitmap.getHeight())) {
            if (!reqInfo.item.isTransparent() || !BitmapUtils.hasTransparency(bitmap)) {
                compressFormat = Bitmap.CompressFormat.JPEG;
            } else {
                compressFormat = Bitmap.CompressFormat.WEBP;
            }
            if (reqInfo.thumbKind == ThumbKind.SMALL_CROP_KIND) {
                i2 = 98;
            } else {
                i2 = 95;
            }
            reqInfo.mCacheData = BitmapUtils.compressToBytes(bitmap, i2, compressFormat);
            CacheManager.getInstance().add(reqInfo.mDiskCacheId, reqInfo.fileCacheKey, reqInfo.mCacheData);
            this.mThumbnailLoader.mReqFileWriteQueue.add(reqInfo);
            reqInfo.bitmap = null;
            return;
        }
        Log.e("ThumbnailHandler", "wrong cache (" + bitmap.getWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + bitmap.getHeight() + ") " + reqInfo);
    }

    private void requestFileWrite() {
        ThumbnailHandler fileWriteHandler;
        if (!this.mThumbnailLoader.mReqFileWriteQueue.isEmpty() && (fileWriteHandler = ThumbnailLoader.getInstance().getFileWriteHandler()) != null && !fileWriteHandler.hasMessages(2000)) {
            fileWriteHandler.sendEmptyMessage(2000);
        }
    }

    private void trimOriginalDecodingQueue(ThumbnailLoader thumbnailLoader) {
        if (thumbnailLoader.mReqDecodeOriginQueue.size() > thumbnailLoader.mMaxOriginalDecodeQueueSize) {
            TimeTickLog timeTickLog = new TimeTickLog("trimOriginalDecodingQueue");
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ThreadUtil.postOnUiThread(new a(thumbnailLoader, countDownLatch));
            try {
                countDownLatch.await(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeTickLog.tock(20);
        }
    }

    public void addDecodeFailLog(FileItemInterface fileItemInterface) {
        String str;
        int i2;
        boolean exists = FileUtils.exists(fileItemInterface.getPath());
        String str2 = Logger.getEncodedString(fileItemInterface.getPath()) + "/" + fileItemInterface.getFileId() + "/" + exists;
        if (FileUtils.isInRemovableStorage(fileItemInterface.getPath())) {
            str = StorageInfo.getRemovable().getCardId();
        } else {
            str = null;
        }
        ThumbnailLoader thumbnailLoader = this.mThumbnailLoader;
        if (thumbnailLoader.mDebugLogger == null) {
            thumbnailLoader.mDebugLogger = DebugLogger.getDecodeInstance();
        }
        DebugLogger debugLogger = this.mThumbnailLoader.mDebugLogger;
        if (fileItemInterface.getPath() == null) {
            i2 = str2.hashCode();
        } else {
            i2 = fileItemInterface.getPath().hashCode();
        }
        debugLogger.insertLog("Thumbnail", str2, str, String.valueOf(i2));
        ThumbnailLoader thumbnailLoader2 = this.mThumbnailLoader;
        if (thumbnailLoader2.mRecoverCollector == null) {
            thumbnailLoader2.mRecoverCollector = RecoverCollector.getInstance();
        }
        this.mThumbnailLoader.mRecoverCollector.collect(fileItemInterface, exists);
    }

    public Bitmap decodeInputStream(ReqInfo reqInfo) {
        Bitmap bitmap;
        InputStream inputStream;
        BitmapOptions bitmapOptions;
        ReqInfo reqInfo2 = reqInfo;
        InputStream inputStream2 = reqInfo2.mInputStream;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            BytesBuffer bytesBuffer = new BytesBuffer();
            int available = inputStream2.available();
            byte[] byteBuffer = ThumbnailLoader.getByteBuffer(available);
            bytesBuffer.data = byteBuffer;
            if (inputStream2.read(byteBuffer) > 0) {
                bytesBuffer.offset = 0;
                bytesBuffer.length = available;
            } else {
                Log.e("ThumbnailHandler", "get: read failed");
            }
            if (reqInfo2.isEnoughCache()) {
                bitmapOptions = BitmapOptionsFactory.parse(bytesBuffer.data, 0, bytesBuffer.length);
                bitmapOptions.inSampleSize = this.mThumbnailLoader.mThumbnailLogic.getInSampleSize(reqInfo2.getThumbKind(), bitmapOptions.outWidth, bitmapOptions.outHeight, reqInfo2.thumbKind.lowerBound());
            } else {
                bitmapOptions = new BitmapOptions();
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            if (reqInfo2.preferAndroid) {
                bitmap = BitmapFactory.decodeByteArray(bytesBuffer.data, 0, bytesBuffer.length, bitmapOptions);
            } else {
                bitmap = ImageDecoder.decodeByteArray(bytesBuffer.data, 0, bytesBuffer.length, bitmapOptions);
            }
            try {
                long currentTimeMillis3 = System.currentTimeMillis();
                if (currentTimeMillis3 - currentTimeMillis > 100) {
                    Log.w("ThumbnailHandler", "Low decode cache performance - b:" + available + ", r:" + (currentTimeMillis2 - currentTimeMillis) + ", d:" + (currentTimeMillis3 - currentTimeMillis2) + ", q:" + this.mThumbnailLoader.mReqDecodeQueue.size() + ",from req=" + reqInfo2.getElapsedTime());
                }
                if (bitmap != null && reqInfo2.isScantCache()) {
                    reqInfo2.addDecodeStatus(16);
                }
                inputStream = null;
            } catch (Exception e) {
                e = e;
                try {
                    Log.w((CharSequence) "ThumbnailHandler", "decodeInputStream failed", (Throwable) e);
                    inputStream = null;
                    reqInfo2.mInputStream = inputStream;
                    Utils.closeSilently(inputStream2);
                    return bitmap;
                } catch (Throwable th) {
                    reqInfo2.mInputStream = null;
                    Utils.closeSilently(inputStream2);
                    throw th;
                }
            }
        } catch (Exception e7) {
            e = e7;
            bitmap = null;
            Log.w((CharSequence) "ThumbnailHandler", "decodeInputStream failed", (Throwable) e);
            inputStream = null;
            reqInfo2.mInputStream = inputStream;
            Utils.closeSilently(inputStream2);
            return bitmap;
        }
        reqInfo2.mInputStream = inputStream;
        Utils.closeSilently(inputStream2);
        return bitmap;
    }

    public Bitmap getBitmapFromFileCache(ReqInfo reqInfo) {
        Bitmap decodeInputStream;
        ByteArrayInputStream byteArrayInputStream;
        if (reqInfo.mInputStream == null) {
            return null;
        }
        try {
            Trace.beginSection("decodeCacheThumb");
            decodeInputStream = decodeInputStream(reqInfo);
            if (decodeInputStream == null) {
                CacheManager.getInstance().remove(reqInfo.mDiskCacheId, reqInfo.fileCacheKey);
                Log.e("ThumbnailHandler", "fail to read cache. remove cache and try decode : ");
            } else if (decodeInputStream.getByteCount() > 104857600) {
                Log.w("ThumbnailHandler", "remove too big bitmap from cache (" + decodeInputStream.getWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + decodeInputStream.getHeight() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + decodeInputStream.getByteCount() + ") " + reqInfo);
                CacheManager.getInstance().remove(reqInfo.mDiskCacheId, reqInfo.fileCacheKey);
                Trace.endSection();
                return null;
            } else {
                if (reqInfo.item.isStream() && (reqInfo.item.getWidth() <= 0 || reqInfo.item.getHeight() <= 0)) {
                    byteArrayInputStream = new ByteArrayInputStream(reqInfo.item.getByteArray());
                    ThumbnailUtil.updateImageInfo(reqInfo.item, (InputStream) byteArrayInputStream);
                    byteArrayInputStream.close();
                }
                reqInfo.mDecodeInfo = "from cache";
            }
        } catch (Error | Exception e) {
            Log.e("ThumbnailHandler", "stream update info failed. e=" + e.getMessage());
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
        Trace.endSection();
        return decodeInputStream;
        throw th;
    }

    public void handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 1000) {
            handleRequestDecode();
        } else if (i2 == 2000) {
            handleRequestFileWrite();
        }
    }

    public void logDelayedOriginalDecode(ReqInfo reqInfo) {
        long elapsedTime = reqInfo.getElapsedTime();
        boolean isInterrupted = reqInfo.mInterruptChecker.isInterrupted();
        if (elapsedTime > 500 && !isInterrupted) {
            int size = this.mThumbnailLoader.mReqDecodeOriginQueue.size();
            FileLogger.add("[ORG_DECODE_QUEUE] waiting time=" + elapsedTime + ArcCommonLog.TAG_COMMA + reqInfo + ", queueSize=" + size);
        }
    }

    public void processThumbReq(ReqInfo reqInfo) {
        try {
            reqInfo.removeDecodeStatus(240);
            Bitmap bitmapFromFileCache = getBitmapFromFileCache(reqInfo);
            reqInfo.bitmap = null;
            if (bitmapFromFileCache == null && reqInfo.item.isVideo()) {
                ThumbKind thumbKind = reqInfo.thumbKind;
                if (thumbKind == ThumbKind.MEDIUM_KIND) {
                    bitmapFromFileCache = this.mThumbnailLoader.getMediumMemCache(reqInfo.item.getThumbCacheKey());
                } else if (ThumbKind.isSmallKind(thumbKind)) {
                    bitmapFromFileCache = this.mThumbnailLoader.getSmallMemCache(reqInfo.item.getThumbCacheKey(), reqInfo.item.getCropRectRatio());
                } else if (ThumbKind.isXSmallKind(reqInfo.thumbKind)) {
                    bitmapFromFileCache = this.mThumbnailLoader.getXSmallMemCache(reqInfo.item.getThumbCacheKey(), reqInfo.item.getCropRectRatio());
                }
                if (bitmapFromFileCache != null) {
                    reqInfo.addDecodeStatus(1);
                }
            }
            if (bitmapFromFileCache == null) {
                Trace.beginSection("decodeOrg");
                bitmapFromFileCache = this.mThumbnailLoader.mThumbnailLogic.load(reqInfo, reqInfo.getThumbKind());
                Trace.endSection();
                reqInfo.bitmap = null;
            }
            if (reqInfo.thumbKind == ThumbKind.MINI_KIND) {
                if (bitmapFromFileCache != null) {
                    bitmapFromFileCache = ThumbnailUtil.getMiniCropFromBitmap(bitmapFromFileCache, reqInfo.item);
                    this.mThumbnailLoader.addToRecycler(bitmapFromFileCache);
                }
                notifyListeners(reqInfo, bitmapFromFileCache);
                return;
            }
            if (bitmapFromFileCache != null) {
                if (bitmapFromFileCache.getByteCount() > 104857600) {
                    bitmapFromFileCache = resizeTooBigThumbnail(reqInfo, bitmapFromFileCache);
                }
                ThumbKind thumbKind2 = reqInfo.thumbKind;
                if (thumbKind2 == ThumbKind.XSMALL_KIND || thumbKind2 == ThumbKind.SMALL_CROP_KIND) {
                    bitmapFromFileCache = ThumbnailUtil.getSmallCropFromBitmap(bitmapFromFileCache, reqInfo.item, thumbKind2.size());
                }
                if (isFileCacheable(reqInfo, bitmapFromFileCache)) {
                    requestCachePut(reqInfo, bitmapFromFileCache);
                }
                if (reqInfo.isMoreDecodingRequired()) {
                    reqInfo.removeDecodeStatus(15);
                    if (SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || reqInfo.item.getWidth() * reqInfo.item.getHeight() <= 600000000) {
                        reqInfo.addDecodeStatus(8);
                        this.mThumbnailLoader.mReqDecodeOriginQueue.add(reqInfo);
                    } else {
                        Log.e((CharSequence) "ThumbnailHandler", "skip high resolution original decoding", Long.valueOf(reqInfo.item.getFileId()), Integer.valueOf(reqInfo.item.getWidth()), Integer.valueOf(reqInfo.item.getHeight()));
                    }
                }
            }
            postProcessThumbRequest(reqInfo, bitmapFromFileCache);
        } catch (Exception e) {
            Log.e("ThumbnailHandler", "processThumbReq failed :" + reqInfo);
            throw e;
        }
    }

    public Bitmap requestDiskCachePut(ReqInfo reqInfo, Bitmap bitmap) {
        if (isFileCacheable(reqInfo, bitmap)) {
            ThumbKind thumbKind = reqInfo.thumbKind;
            if (thumbKind == ThumbKind.SMALL_CROP_KIND) {
                bitmap = ThumbnailUtil.getSmallCropFromBitmap(bitmap, reqInfo.item, thumbKind.size());
            }
            requestCachePut(reqInfo, bitmap);
        }
        return bitmap;
    }

    public Bitmap resizeTooBigThumbnail(ReqInfo reqInfo, Bitmap bitmap) {
        float f;
        Log.e("ThumbnailHandler", "resize too big bitmap (" + bitmap.getWidth() + "x" + bitmap.getHeight() + ") " + reqInfo);
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        if (min < 10) {
            f = 1.0f / ((float) min);
        } else {
            f = 0.1f;
        }
        Bitmap resize = BitmapUtils.resize(bitmap, f);
        if (resize == null || resize.getByteCount() > 104857600) {
            Log.e("ThumbnailHandler", "fail resize big thumb. crop : " + resize);
            Bitmap resizeAndCropCenter = BitmapUtils.resizeAndCropCenter(bitmap, reqInfo.getThumbKind().size());
            if (resizeAndCropCenter != resize) {
                if (resize != null) {
                    BitmapUtils.putBitmap(resize);
                }
                resize = resizeAndCropCenter;
            }
        }
        if (resize != bitmap) {
            this.mThumbnailLoader.recycle((String) null, bitmap);
            this.mThumbnailLoader.removeCache(reqInfo.item);
            bitmap = resize;
        }
        new InternalException("too big thumbnail").post();
        return bitmap;
    }
}
