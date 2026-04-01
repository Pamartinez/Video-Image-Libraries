package com.samsung.android.gallery.module.thumbnail;

import Za.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dal.local.recovery.RecoverCollector;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.cache.AbsCacheMgr$EvictListener;
import com.samsung.android.gallery.module.cache.BitmapCacheMgr;
import com.samsung.android.gallery.module.graphics.BitmapCache;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.logic.AbsThumbnailLoaderImpl;
import com.samsung.android.gallery.module.thumbnail.logic.CategoryThumbnailLoaderImpl;
import com.samsung.android.gallery.module.thumbnail.logic.CloudThumbnailLoaderImpl;
import com.samsung.android.gallery.module.thumbnail.logic.ContactThumbnailLoaderImpl;
import com.samsung.android.gallery.module.thumbnail.logic.LocalThumbnailLoaderImpl;
import com.samsung.android.gallery.module.thumbnail.logic.MtpThumbnailLoaderImpl;
import com.samsung.android.gallery.module.thumbnail.logic.PeopleThumbnailLoaderImpl;
import com.samsung.android.gallery.module.thumbnail.logic.RemoteThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.logic.RemoteThumbnailLoaderImpl;
import com.samsung.android.gallery.module.thumbnail.logic.SharingThumbnailLoaderImpl;
import com.samsung.android.gallery.module.thumbnail.logic.StreamThumbnailLoaderImpl;
import com.samsung.android.gallery.module.thumbnail.logic.UriThumbnailLoaderImpl;
import com.samsung.android.gallery.module.thumbnail.logic.WebThumbnailLoaderImpl;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterrupter;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.thumbnail.util.ThumbnailUtil;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SharedByteBufferPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.chain.ChainBuilder;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.scs.base.StatusCodes;
import e5.d;
import i.C0212a;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import o6.p;
import q5.i;
import q6.e;
import qa.C0705a;
import qa.b;
import qa.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThumbnailLoader {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f3091a = 0;
    private static final SharedByteBufferPool sBufferPool = new SharedByteBufferPool(153600);
    private static final ThumbnailInterrupter sDefaultInterruptChecker = new d(10);
    private static volatile ThumbnailLoader sInstance;
    CacheHandler mCacheHandler;
    final HandlerThread mCacheHandlerThread = new CacheHandlerThread("CacheHandlerThread");
    DebugLogger mDebugLogger;
    final ConcurrentHashMap<String, ReqInfo> mDecoderWorkingQueue = new ConcurrentHashMap<>();
    long mEndTime;
    final AbsCacheMgr$EvictListener<String, Bitmap> mEvictListener;
    private int mHandlerIndex;
    private final BitmapCacheMgr<String> mLargeMemoryCacheMgr;
    private final BitmapCacheMgr<String> mLargeNcMemoryCacheMgr;
    private final Object mLockWriteHandler;
    volatile int mMaxOriginalDecodeQueueSize;
    private final BitmapCacheMgr<String> mMediumMemoryCacheMgr;
    final ThumbMonitor mMonitor;
    ThumbnailHandler mMtpThumbnailHandler;
    boolean mPostponed;
    final ConcurrentLinkedQueue<ReqInfo> mPutCacheQueue = new ConcurrentLinkedQueue<>();
    RecoverCollector mRecoverCollector;
    private ThumbnailRecycler mRecycler;
    final AtomicInteger mRefCount;
    final ConcurrentHashMap<Integer, Bitmap> mReplacedThumbnailMap;
    final ConcurrentLinkedDeque<ReqInfo> mReqCacheQueue = new ConcurrentLinkedDeque<>();
    final ConcurrentLinkedQueue<ReqInfo> mReqDecodeOriginQueue = new ConcurrentLinkedQueue<>();
    final ConcurrentHashMap<Integer, ReqInfo> mReqDecodeQueue = new ConcurrentHashMap<>();
    final ConcurrentLinkedQueue<ReqInfo> mReqFileWriteQueue = new ConcurrentLinkedQueue<>();
    final ConcurrentHashMap<Integer, ReqInfo> mReqMiniDecodeQueue = new ConcurrentHashMap<>();
    final ConcurrentHashMap<String, ReqInfo> mReqSyncDecodeQueue = new ConcurrentHashMap<>();
    private final BitmapCacheMgr<String> mSmallMemoryCacheMgr;
    long mStartTime;
    final ThumbnailHandler[] mThumbnailHandler = new ThumbnailHandler[3];
    final AbsThumbnailLoaderImpl mThumbnailLogic;
    final HandlerThread[] mThumbnailThread = new HandlerThread[3];
    private int mWriteHandlerIndex;
    private final BitmapCacheMgr<String> mXSmallMemoryCacheMgr;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class CacheHandlerThread extends HandlerThread {
        public CacheHandlerThread(String str) {
            super(str, 10);
        }

        public void onLooperPrepared() {
            ThumbnailLoader.this.mCacheHandler = new CacheHandler(getLooper(), 3, ThumbnailLoader.this);
            CacheHandler cacheHandler = ThumbnailLoader.this.mCacheHandler;
            cacheHandler.sendMessage(cacheHandler.obtainMessage(9999));
            if (!ThumbnailLoader.this.mReqCacheQueue.isEmpty()) {
                Log.d("ThumbnailLoader", "send pending cache request");
                CacheHandler cacheHandler2 = ThumbnailLoader.this.mCacheHandler;
                cacheHandler2.sendMessage(cacheHandler2.obtainMessage(100));
            }
        }
    }

    public ThumbnailLoader() {
        p pVar = new p(8, this);
        this.mEvictListener = pVar;
        this.mMediumMemoryCacheMgr = new BitmapCacheMgr<>(128, pVar);
        this.mSmallMemoryCacheMgr = new BitmapCacheMgr<>(StatusCodes.INPUT_MISSING, pVar);
        this.mXSmallMemoryCacheMgr = new BitmapCacheMgr<>(1000, pVar);
        this.mLargeMemoryCacheMgr = new BitmapCacheMgr<>(10, pVar);
        this.mLargeNcMemoryCacheMgr = new BitmapCacheMgr<>(100, pVar);
        this.mRefCount = new AtomicInteger(0);
        this.mReplacedThumbnailMap = new ConcurrentHashMap<>();
        this.mStartTime = 0;
        this.mEndTime = 0;
        this.mMonitor = new ThumbMonitor(ThreadUtil.createBackgroundThreadLooper("thumbMon"));
        this.mMaxOriginalDecodeQueueSize = 60;
        this.mHandlerIndex = 0;
        this.mWriteHandlerIndex = 0;
        this.mLockWriteHandler = new Object();
        ChainBuilder append = new ChainBuilder().append(new PeopleThumbnailLoaderImpl()).append(new CategoryThumbnailLoaderImpl()).append(new LocalThumbnailLoaderImpl()).append(new CloudThumbnailLoaderImpl()).append(new SharingThumbnailLoaderImpl()).append(new MtpThumbnailLoaderImpl()).append(new UriThumbnailLoaderImpl()).append(new WebThumbnailLoaderImpl()).append(new RemoteThumbnailLoaderImpl()).append(new ContactThumbnailLoaderImpl());
        append.append(new StreamThumbnailLoaderImpl());
        this.mThumbnailLogic = (AbsThumbnailLoaderImpl) append.build();
        this.mRecycler = new ThumbnailRecycler();
    }

    /* access modifiers changed from: private */
    /* renamed from: addCacheRequest */
    public void lambda$postponeRequest$1(ReqInfo reqInfo) {
        this.mReqCacheQueue.add(reqInfo);
        CacheHandler cacheHandler = this.mCacheHandler;
        if (cacheHandler == null) {
            Log.e("ThumbnailLoader", "cache handler not prepared");
        } else if (!cacheHandler.hasMessages(100)) {
            CacheHandler cacheHandler2 = this.mCacheHandler;
            cacheHandler2.sendMessage(cacheHandler2.obtainMessage(100));
        }
    }

    private void addLargeNcSizeMemCache(String str, Bitmap bitmap) {
        this.mLargeNcMemoryCacheMgr.addCache(str, bitmap);
    }

    private void addLargeSizeMemCache(String str, Bitmap bitmap) {
        this.mLargeMemoryCacheMgr.addCache(str, bitmap);
    }

    private void addMediumSizeMemCache(String str, Bitmap bitmap) {
        this.mMediumMemoryCacheMgr.addCache(str, bitmap);
    }

    private void addSmallMemCache(String str, Bitmap bitmap, RectF rectF) {
        this.mSmallMemoryCacheMgr.addCache(getCropRectThumbnailCacheKey(str, rectF), bitmap);
    }

    private void addXSmallMemCache(String str, Bitmap bitmap, RectF rectF) {
        this.mXSmallMemoryCacheMgr.addCache(getCropRectThumbnailCacheKey(str, rectF), bitmap);
    }

    public static byte[] getByteBuffer(int i2) {
        return sBufferPool.getByteBuffer(i2);
    }

    private String getCropRectThumbnailCacheKey(String str, RectF rectF) {
        String str2;
        StringBuilder s = C0212a.s(str);
        if (rectF != null) {
            str2 = "/" + rectF;
        } else {
            str2 = "";
        }
        s.append(str2);
        return s.toString();
    }

    public static ThumbnailLoader getInstance() {
        if (sInstance == null) {
            synchronized (ThumbnailLoader.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new ThumbnailLoader();
                        sInstance.initThreads();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private boolean handleWorkingRequest(ReqInfo reqInfo) {
        ReqInfo reqInfo2 = this.mDecoderWorkingQueue.get(reqInfo.getWorkingKey());
        if (reqInfo2 == null || reqInfo2.mInterruptChecker.isInterrupted()) {
            return false;
        }
        return postponeRequest(reqInfo, reqInfo2);
    }

    private void initThreads() {
        for (int i2 = 0; i2 < 3; i2++) {
            this.mThumbnailThread[i2] = new HandlerThread(C0086a.i(i2, "thumbThread"), 10);
            this.mThumbnailThread[i2].start();
            Looper looper = this.mThumbnailThread[i2].getLooper();
            if (looper != null) {
                this.mThumbnailHandler[i2] = new ThumbnailHandler(looper, i2, this);
            }
        }
        try {
            this.mCacheHandlerThread.start();
        } catch (IllegalThreadStateException unused) {
            Log.e("ThumbnailLoader", "fail to start thread. already started : ");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$changeMemoryCacheKey$2(String str, Bitmap bitmap) {
        this.mMediumMemoryCacheMgr.addCache(str, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$changeMemoryCacheKey$3(String str, RectF rectF, Bitmap bitmap) {
        this.mSmallMemoryCacheMgr.addCache(getCropRectThumbnailCacheKey(str, rectF), bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$changeMemoryCacheKey$4(String str, Bitmap bitmap) {
        this.mLargeMemoryCacheMgr.addCache(str, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$changeMemoryCacheKey$5(String str, RectF rectF, Bitmap bitmap) {
        this.mXSmallMemoryCacheMgr.addCache(getCropRectThumbnailCacheKey(str, rectF), bitmap);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$dump$10(String str, Bitmap bitmap) {
        if (bitmap == null || Math.max(bitmap.getWidth(), bitmap.getHeight()) <= 320) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$dump$11(String str, Bitmap bitmap) {
        if (str.startsWith("content://") || bitmap == null || Math.max(bitmap.getWidth(), bitmap.getHeight()) <= 320) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$dump$9(String str, Bitmap bitmap) {
        if (bitmap == null || Math.max(bitmap.getWidth(), bitmap.getHeight()) <= 320) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$0() {
        return false;
    }

    private void trimMemoryCacheBackground() {
        this.mMediumMemoryCacheMgr.trimCache(32);
        this.mLargeMemoryCacheMgr.clearCache();
        this.mSmallMemoryCacheMgr.clearCache();
        this.mLargeNcMemoryCacheMgr.clearCache();
        this.mXSmallMemoryCacheMgr.clearCache();
    }

    public void addToRecycler(Bitmap bitmap) {
        ThumbnailRecycler thumbnailRecycler;
        if (bitmap != null && (thumbnailRecycler = this.mRecycler) != null) {
            thumbnailRecycler.add(bitmap);
        }
    }

    public void changeMemoryCacheKey(String str, String str2, RectF rectF) {
        Optional.ofNullable((Bitmap) this.mMediumMemoryCacheMgr.getCache(str)).ifPresent(new b(this, str2, 0));
        Optional.ofNullable((Bitmap) this.mSmallMemoryCacheMgr.getCache(getCropRectThumbnailCacheKey(str, rectF))).ifPresent(new c(this, str2, rectF, 0));
        Optional.ofNullable((Bitmap) this.mLargeMemoryCacheMgr.getCache(str)).ifPresent(new b(this, str2, 1));
        Optional.ofNullable((Bitmap) this.mXSmallMemoryCacheMgr.getCache(getCropRectThumbnailCacheKey(str, rectF))).ifPresent(new c(this, str2, rectF, 1));
    }

    public void clearMemoryCache(ThumbKind thumbKind) {
        if (thumbKind == ThumbKind.LARGE_NC_KIND) {
            this.mLargeNcMemoryCacheMgr.clearCache();
        } else if (thumbKind == ThumbKind.LARGE_KIND) {
            this.mLargeMemoryCacheMgr.clearCache();
        } else if (thumbKind == ThumbKind.MEDIUM_KIND) {
            this.mMediumMemoryCacheMgr.clearCache();
        } else if (ThumbKind.isSmallKind(thumbKind)) {
            this.mSmallMemoryCacheMgr.clearCache();
        } else if (ThumbKind.isXSmallKind(thumbKind)) {
            this.mXSmallMemoryCacheMgr.clearCache();
        }
    }

    public void clearMiniDecodeQueue() {
        Log.i("ThumbnailLoader", "clearMiniDecodeQueue " + this.mReqMiniDecodeQueue.size());
        this.mReqMiniDecodeQueue.clear();
    }

    public void close() {
        if (this.mRefCount.get() == 0) {
            Log.e("ThumbnailLoader", "close skip");
        } else if (this.mRefCount.decrementAndGet() == 0) {
            destroy();
            Log.d("ThumbnailLoader", "close");
        }
    }

    public synchronized void destroy() {
        this.mStartTime = 0;
        this.mEndTime = 0;
        this.mRecycler = null;
        clearMemoryCache();
        sBufferPool.clear();
        this.mThumbnailLogic.clear();
        this.mReqDecodeQueue.clear();
        this.mReqMiniDecodeQueue.clear();
        this.mReqCacheQueue.clear();
        this.mReqDecodeOriginQueue.clear();
        this.mPutCacheQueue.clear();
        this.mReqFileWriteQueue.clear();
        this.mReplacedThumbnailMap.clear();
    }

    public String dump() {
        String str;
        String str2;
        String dumpIf = this.mLargeNcMemoryCacheMgr.dumpIf(new a(3));
        String dumpIf2 = this.mLargeMemoryCacheMgr.dumpIf(new a(4));
        String dumpIf3 = this.mMediumMemoryCacheMgr.dumpIf(new a(5));
        StringBuilder sb2 = new StringBuilder("== thumbnail cache ==\n");
        String str3 = "";
        if (TextUtils.isEmpty(dumpIf)) {
            str = str3;
        } else {
            str = C0212a.m("LargeNC\n", dumpIf, "\n");
        }
        sb2.append(str);
        if (TextUtils.isEmpty(dumpIf2)) {
            str2 = str3;
        } else {
            str2 = C0212a.m("LargeCache\n", dumpIf2, "\n");
        }
        sb2.append(str2);
        if (!TextUtils.isEmpty(dumpIf3)) {
            str3 = C0212a.m("MediumCache\n", dumpIf3, "\n");
        }
        return C0212a.p(sb2, str3, "\n");
    }

    public Bitmap getBitmapFromDiskCache(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind) {
        Bitmap diskCache;
        byte[] generateKey = CacheManager.generateKey(thumbnailInterface.getDiskCacheKey(), thumbnailInterface.getDateModified());
        if (thumbKind.cacheId() != 99 && (diskCache = BitmapCache.getDiskCache(thumbKind.cacheId(), generateKey)) != null) {
            return diskCache;
        }
        int[] altDiskCacheArray = CacheHandler.getAltDiskCacheArray(thumbKind);
        int length = altDiskCacheArray.length;
        int i2 = 0;
        while (i2 < length) {
            Bitmap diskCache2 = BitmapCache.getDiskCache(altDiskCacheArray[i2], generateKey);
            if (diskCache2 == null) {
                i2++;
            } else if (thumbKind.size() <= ThumbKind.SMALL_CROP_KIND.size()) {
                return ThumbnailUtil.getSmallCropFromBitmap(diskCache2, thumbnailInterface, thumbKind.size());
            } else {
                return diskCache2;
            }
        }
        return null;
    }

    public Bitmap getDefaultAlbumImage(boolean z) {
        if (!PreferenceFeatures.OneUi8x.MX_ALBUM_BLUR || z) {
            return getReplacedThumbnail((Context) null, R$drawable.gallery_ic_album_no_pic, R$color.album_no_pic_background_color);
        }
        return getReplacedThumbnail(R$color.album_no_pic_background_color);
    }

    public Bitmap getDefaultReplacedThumbnail() {
        return getReplacedThumbnail((Context) null, R$drawable.gallery_ic_timeview_error, R$color.cloud_only_image_bg);
    }

    public long getElapsedTime() {
        long j2 = this.mEndTime - this.mStartTime;
        this.mEndTime = -1;
        return j2;
    }

    public Bitmap getEmptyFolderImage(Context context) {
        return getReplacedThumbnail(context, R$drawable.gallery_thumbnail_group_empty, R$color.transparent);
    }

    public ThumbnailHandler getFileWriteHandler() {
        ThumbnailHandler thumbnailHandler;
        synchronized (this.mLockWriteHandler) {
            try {
                ThumbnailHandler[] thumbnailHandlerArr = this.mThumbnailHandler;
                int i2 = this.mWriteHandlerIndex;
                int i7 = i2 + 1;
                this.mWriteHandlerIndex = i7;
                thumbnailHandler = thumbnailHandlerArr[i2];
                if (i7 >= thumbnailHandlerArr.length - 1) {
                    this.mWriteHandlerIndex = 0;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return thumbnailHandler;
    }

    public Bitmap getLargeMemCache(String str) {
        return (Bitmap) this.mLargeMemoryCacheMgr.getCache(str);
    }

    public Bitmap getLargeNcMemCache(String str) {
        return (Bitmap) this.mLargeNcMemoryCacheMgr.getCache(str);
    }

    public Bitmap getLockedAlbumImage() {
        return getReplacedThumbnail((Context) null, R$drawable.album_ic_locked, R$color.album_no_pic_background_color);
    }

    public Bitmap getMediumMemCache(String str) {
        return (Bitmap) this.mMediumMemoryCacheMgr.getCache(str);
    }

    public Bitmap getMemCache(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind) {
        if (thumbnailInterface != null) {
            return getMemCache(thumbnailInterface.getThumbCacheKey(), thumbKind, thumbnailInterface.getCropRectRatio());
        }
        return null;
    }

    public Bitmap getMtpAlbumImage() {
        return getReplacedThumbnail((Context) null, R$drawable.gallery_ic_album_mtp, R$color.album_no_pic_background_color);
    }

    public void getOrLoad(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind, UniqueKey uniqueKey, ThumbnailLoadedListener thumbnailLoadedListener) {
        getOrLoad(thumbnailInterface, thumbKind, uniqueKey, thumbnailLoadedListener, sDefaultInterruptChecker);
    }

    public Bitmap getReplacedThumbnail(int i2) {
        return this.mReplacedThumbnailMap.computeIfAbsent(Integer.valueOf(i2), new Vb.b(i2, 3));
    }

    public Bitmap getSmallMemCache(String str, RectF rectF) {
        return (Bitmap) this.mSmallMemoryCacheMgr.getCache(getCropRectThumbnailCacheKey(str, rectF));
    }

    public Bitmap getVirtualAlbumCoverImage() {
        return getReplacedThumbnail(R$color.virtual_album_first_layer_background_color);
    }

    public ThumbnailHandler getWorkableHandler(ReqInfo reqInfo) {
        if (reqInfo.item.isMtp()) {
            if (this.mMtpThumbnailHandler == null) {
                initMtpThread();
            }
            return this.mMtpThumbnailHandler;
        }
        ThumbnailHandler[] thumbnailHandlerArr = this.mThumbnailHandler;
        int i2 = this.mHandlerIndex;
        int i7 = i2 + 1;
        this.mHandlerIndex = i7;
        ThumbnailHandler thumbnailHandler = thumbnailHandlerArr[i2];
        if (i7 >= thumbnailHandlerArr.length) {
            this.mHandlerIndex = 0;
        }
        return thumbnailHandler;
    }

    public Bitmap getXSmallMemCache(String str, RectF rectF) {
        return (Bitmap) this.mXSmallMemoryCacheMgr.getCache(getCropRectThumbnailCacheKey(str, rectF));
    }

    public boolean handleSyncWorkingRequest(ReqInfo reqInfo) {
        ReqInfo reqInfo2 = this.mReqSyncDecodeQueue.get(reqInfo.getWorkingKey());
        if (reqInfo2 == null) {
            return false;
        }
        Log.d("ThumbnailLoader", "duplicated request on sync-working " + reqInfo.item.getFileId());
        reqInfo2.addExtraListener(reqInfo.thumbKind, reqInfo.mRequestKey, reqInfo.mListener);
        return true;
    }

    public void increaseMemCache(ThumbKind thumbKind) {
        if (thumbKind == null) {
            return;
        }
        if (thumbKind.size() >= ThumbKind.LARGE_KIND.size()) {
            this.mLargeMemoryCacheMgr.resizeCache(40);
        } else if (thumbKind.size() >= ThumbKind.MEDIUM_KIND.size()) {
            this.mMediumMemoryCacheMgr.resizeCache(MOCRLang.GURMUKHI);
        } else if (thumbKind.size() >= ThumbKind.SMALL_CROP_KIND.size()) {
            this.mSmallMemoryCacheMgr.resizeCache(400);
        } else if (thumbKind.size() >= ThumbKind.XSMALL_KIND.size()) {
            this.mXSmallMemoryCacheMgr.resizeCache(400);
        }
    }

    public void initMtpThread() {
        HandlerThread handlerThread = new HandlerThread("thumbThreadmtp", 10);
        handlerThread.start();
        this.mMtpThumbnailHandler = new ThumbnailHandler(handlerThread.getLooper(), -1, this);
    }

    public boolean isReplacedThumbnail(Bitmap bitmap) {
        return this.mReplacedThumbnailMap.containsValue(bitmap);
    }

    public Bitmap loadPreview(ThumbnailInterface thumbnailInterface, ThumbnailLoadedListener thumbnailLoadedListener) {
        return loadPreview(thumbnailInterface, thumbnailLoadedListener, sDefaultInterruptChecker);
    }

    public void loadThumbnail(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind, ThumbnailLoadedListener thumbnailLoadedListener) {
        Objects.requireNonNull(thumbnailInterface);
        loadThumbnail(thumbnailInterface, thumbKind, new i(thumbnailInterface, 2), thumbnailLoadedListener);
    }

    public void loadThumbnailSync(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind, Consumer<Bitmap> consumer, boolean z) {
        Bitmap memCache = getMemCache(thumbnailInterface, thumbKind);
        if (memCache != null) {
            consumer.accept(memCache);
            updatePppMemCache(memCache, thumbnailInterface, thumbKind);
            return;
        }
        ThumbnailInterface thumbnailInterface2 = thumbnailInterface;
        ThumbKind thumbKind2 = thumbKind;
        ReqInfo reqInfo = new ReqInfo(thumbnailInterface2, thumbKind2, (UniqueKey) null, (ThumbnailLoadedListener) null, sDefaultInterruptChecker);
        reqInfo.mDiskCacheId = this.mThumbnailLogic.getCacheId(thumbnailInterface2, thumbKind2);
        Bitmap bitmapFromDiskCache = getBitmapFromDiskCache(thumbnailInterface2, thumbKind2);
        if (bitmapFromDiskCache != null) {
            addToRecycler(bitmapFromDiskCache);
            updateMemCacheSync(reqInfo, bitmapFromDiskCache);
            consumer.accept(bitmapFromDiskCache);
            return;
        }
        String workingKey = reqInfo.getWorkingKey();
        this.mReqSyncDecodeQueue.put(workingKey, reqInfo);
        Bitmap load = this.mThumbnailLogic.load(reqInfo, thumbKind2);
        if (load != null) {
            load = BitmapUtils.resizeForMaxBitmapSize(load);
            ThumbnailHandler fileWriteHandler = getFileWriteHandler();
            if (fileWriteHandler != null) {
                load = fileWriteHandler.requestDiskCachePut(reqInfo, load);
                fileWriteHandler.sendEmptyMessage(2000);
            }
            addToRecycler(load);
            if (reqInfo.isMemCacheable()) {
                updateMemCacheSync(reqInfo, load);
            }
            if (z && reqInfo.item.isImage() && !reqInfo.isDecodeExif()) {
                Log.e("ThumbnailLoader", "loadThumbnailSync without exif");
            }
        }
        this.mReqSyncDecodeQueue.remove(workingKey);
        consumer.accept(load);
        CopyOnWriteArrayList<ReqInfo.ReturnData> copyOnWriteArrayList = reqInfo.mExtraListener;
        if (copyOnWriteArrayList != null) {
            Iterator<ReqInfo.ReturnData> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ReqInfo.ReturnData next = it.next();
                if (next != null) {
                    Log.d("ThumbnailLoader", "loadThumbnailSync extra-notify " + Logger.getAnonymousName(next.mListener));
                    next.mListener.onLoaded(load, next.mKeyObj, next.mThumbKind);
                }
            }
        }
    }

    public void onActivityPause() {
        this.mMonitor.setActive(false);
    }

    public void onActivityResume() {
        this.mMonitor.setActive(true);
    }

    public void open() {
        if (this.mRefCount.getAndIncrement() == 0) {
            Log.d("ThumbnailLoader", "open");
        }
    }

    public boolean postponeRequest(ReqInfo reqInfo, ReqInfo reqInfo2) {
        Handler handler;
        WeakReference<Handler> weakReference = reqInfo2.mWorkingHandler;
        if (weakReference != null) {
            handler = weakReference.get();
        } else {
            handler = null;
        }
        if (handler == null) {
            return false;
        }
        handler.post(new e(4, this, reqInfo));
        Log.w("ThumbnailLoader", "duplicated request on working. postpone request");
        return true;
    }

    public void recycle(String str, Bitmap bitmap) {
        ThumbnailRecycler thumbnailRecycler;
        if (bitmap != null && (thumbnailRecycler = this.mRecycler) != null) {
            thumbnailRecycler.tryRecycle(bitmap);
        }
    }

    public void removeCache(ThumbnailInterface thumbnailInterface) {
        if (thumbnailInterface != null) {
            String thumbCacheKey = thumbnailInterface.getThumbCacheKey();
            RectF cropRectRatio = thumbnailInterface.getCropRectRatio();
            this.mLargeMemoryCacheMgr.removeCache(thumbCacheKey);
            this.mMediumMemoryCacheMgr.removeCache(thumbCacheKey);
            this.mSmallMemoryCacheMgr.removeCache(getCropRectThumbnailCacheKey(thumbCacheKey, cropRectRatio));
            this.mXSmallMemoryCacheMgr.removeCache(getCropRectThumbnailCacheKey(thumbCacheKey, cropRectRatio));
            removeDiskCache(thumbnailInterface);
        }
    }

    public void removeDiskCache(FileItemInterface fileItemInterface) {
        if (fileItemInterface != null) {
            String diskCacheKey = fileItemInterface.getDiskCacheKey();
            CacheManager.getInstance().remove(diskCacheKey, fileItemInterface.getDateModified());
            CacheManager.getInstance().removeCrop(diskCacheKey, fileItemInterface.getDateModified(), fileItemInterface.getCropRectRatio());
        }
    }

    public void removeMemCache(ThumbnailInterface thumbnailInterface) {
        String thumbCacheKey = thumbnailInterface.getThumbCacheKey();
        RectF cropRectRatio = thumbnailInterface.getCropRectRatio();
        this.mMediumMemoryCacheMgr.removeCache(thumbCacheKey);
        this.mSmallMemoryCacheMgr.removeCache(getCropRectThumbnailCacheKey(thumbCacheKey, cropRectRatio));
        this.mLargeMemoryCacheMgr.removeCache(thumbCacheKey);
        this.mXSmallMemoryCacheMgr.removeCache(getCropRectThumbnailCacheKey(thumbCacheKey, cropRectRatio));
    }

    public void removePppMemCache(ThumbnailInterface thumbnailInterface) {
        this.mMediumMemoryCacheMgr.removeCache("ppp/" + thumbnailInterface.getFileId());
    }

    public void replaceCache(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind, Bitmap bitmap, int i2) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] generateKey = CacheManager.generateKey(thumbnailInterface.getDiskCacheKey(), thumbnailInterface.getDateModified());
        CacheManager instance = CacheManager.getInstance();
        String thumbCacheKey = thumbnailInterface.getThumbCacheKey();
        if (thumbKind == ThumbKind.MEDIUM_KIND) {
            instance.remove(0, generateKey);
            this.mMediumMemoryCacheMgr.removeCache(thumbCacheKey);
        } else if (thumbKind == ThumbKind.SMALL_CROP_KIND) {
            this.mSmallMemoryCacheMgr.removeCache(getCropRectThumbnailCacheKey(thumbCacheKey, thumbnailInterface.getCropRectRatio()));
            instance.remove(5, generateKey);
        } else if (thumbKind == ThumbKind.LARGE_KIND) {
            this.mLargeMemoryCacheMgr.removeCache(thumbCacheKey);
            instance.remove(2, generateKey);
        }
        updateMemCache(thumbnailInterface, thumbKind, bitmap);
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(200000);
            bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
            int cacheId = thumbKind.cacheId();
            instance.remove(cacheId, generateKey);
            instance.add(cacheId, generateKey, byteArrayOutputStream.toByteArray());
            instance.writeToFile(cacheId, generateKey);
            instance.commit(cacheId, generateKey);
            byteArrayOutputStream.close();
            return;
        } catch (Error | Exception e) {
            Log.e((CharSequence) "ThumbnailLoader", "replaceCache failed", e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void saveToMemCache(String str, ThumbKind thumbKind, Bitmap bitmap, RectF rectF) {
        if (str != null && thumbKind != null) {
            if (thumbKind == ThumbKind.LARGE_NC_KIND || thumbKind == ThumbKind.XLARGE_NC_KIND) {
                addLargeNcSizeMemCache(str, bitmap);
            } else if (thumbKind.size() >= ThumbKind.LARGE_KIND.size()) {
                addLargeSizeMemCache(str, bitmap);
            } else if (thumbKind.size() >= ThumbKind.MEDIUM_KIND.size()) {
                addMediumSizeMemCache(str, bitmap);
            } else if (thumbKind == ThumbKind.XSMALL_KIND) {
                addXSmallMemCache(str, bitmap, rectF);
            } else {
                addSmallMemCache(str, bitmap, rectF);
            }
        }
    }

    public void setMaxOriginalDecodeQueueSize(int i2) {
        this.mMaxOriginalDecodeQueueSize = i2;
    }

    public void setRemoteThumbnailLoader(RemoteThumbnailLoader remoteThumbnailLoader) {
        this.mThumbnailLogic.setRemoteThumbnailLoader(remoteThumbnailLoader);
    }

    public void trimMemory(int i2) {
        if (i2 >= 60) {
            clearMemoryCache();
        } else if (i2 >= 40) {
            trimMemoryCacheBackground();
        }
        sBufferPool.clear();
    }

    public void updateEffectMemCache(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind) {
        Bitmap memCache = getMemCache(thumbnailInterface, thumbKind);
        if (memCache != null && !isReplacedThumbnail(memCache)) {
            if (thumbnailInterface.getOrientation() != 0) {
                memCache = BitmapUtils.rotateBitmap(memCache, thumbnailInterface.getOrientation());
            }
            addToRecycler(memCache);
            saveToMemCache("effect/" + thumbnailInterface.getFileId(), thumbKind, memCache, thumbnailInterface.getCropRectRatio());
        }
    }

    public void updateMemCache(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind, Bitmap bitmap) {
        addToRecycler(bitmap);
        saveToMemCache(thumbnailInterface.getThumbCacheKey(), thumbKind, bitmap, thumbnailInterface.getCropRectRatio());
    }

    public void updateMemCacheSync(ReqInfo reqInfo, Bitmap bitmap) {
        updateMemCache(reqInfo.item, reqInfo.thumbKind, bitmap);
        updatePppMemCache(bitmap, reqInfo.item, reqInfo.thumbKind);
    }

    public void updatePppMemCache(Bitmap bitmap, ThumbnailInterface thumbnailInterface, ThumbKind thumbKind) {
        if (thumbnailInterface.getSefFileType() == 2928) {
            addToRecycler(bitmap);
            saveToMemCache("ppp/" + thumbnailInterface.getFileId(), thumbKind, bitmap, thumbnailInterface.getCropRectRatio());
        }
    }

    public Bitmap getMemCache(String str, ThumbKind thumbKind, RectF rectF) {
        Bitmap bitmap = null;
        if (str == null) {
            return null;
        }
        if (thumbKind == ThumbKind.FREE_KIND) {
            Bitmap largeMemCache = getLargeMemCache(str);
            if (largeMemCache == null) {
                largeMemCache = getMediumMemCache(str);
            }
            if (largeMemCache == null) {
                largeMemCache = getSmallMemCache(str, rectF);
            }
            bitmap = largeMemCache;
            if (bitmap == null) {
                bitmap = getXSmallMemCache(str, rectF);
            }
        } else if (thumbKind == ThumbKind.LARGE_NC_KIND || thumbKind == ThumbKind.XLARGE_NC_KIND) {
            bitmap = getLargeNcMemCache(str);
        } else if (thumbKind != ThumbKind.FULL_NC_KIND) {
            if (thumbKind.size() >= ThumbKind.LARGE_KIND.size()) {
                bitmap = getLargeMemCache(str);
            } else if (thumbKind.size() >= ThumbKind.MEDIUM_KIND.size()) {
                bitmap = getMediumMemCache(str);
                if (bitmap == null) {
                    bitmap = getLargeMemCache(str);
                }
            } else if (thumbKind == ThumbKind.XSMALL_KIND) {
                bitmap = getXSmallMemCache(str, rectF);
            } else {
                bitmap = getSmallMemCache(str, rectF);
            }
        }
        if (bitmap != null) {
            addToRecycler(bitmap);
        }
        return bitmap;
    }

    public void getOrLoad(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind, UniqueKey uniqueKey, ThumbnailLoadedListener thumbnailLoadedListener, ThumbnailInterrupter thumbnailInterrupter) {
        if (thumbnailInterface.isBroken()) {
            thumbnailLoadedListener.onLoaded((Bitmap) null, uniqueKey, thumbKind);
            return;
        }
        Bitmap memCache = getMemCache(thumbnailInterface, thumbKind);
        if (memCache != null) {
            thumbnailLoadedListener.onLoaded(memCache, uniqueKey, thumbKind);
        } else {
            loadThumbnail(thumbnailInterface, thumbKind, uniqueKey, thumbnailLoadedListener, thumbnailInterrupter);
        }
    }

    public Bitmap getReplacedThumbnail(Context context, int i2, int i7) {
        return this.mReplacedThumbnailMap.computeIfAbsent(Integer.valueOf(i2), new C0705a(i2, i7));
    }

    public Bitmap loadPreview(ThumbnailInterface thumbnailInterface, ThumbnailLoadedListener thumbnailLoadedListener, ThumbnailInterrupter thumbnailInterrupter) {
        ThumbKind thumbKind = BitmapUtils.isPanoramic(thumbnailInterface.getWidth(), thumbnailInterface.getHeight()) ? ThumbKind.LARGE_KIND : ThumbKind.MEDIUM_KIND;
        Bitmap memCache = getMemCache(thumbnailInterface, thumbKind);
        if (memCache == null) {
            loadThumbnail(thumbnailInterface, thumbKind, new i(thumbnailInterface, 1), thumbnailLoadedListener, thumbnailInterrupter);
        }
        return memCache;
    }

    public void loadThumbnail(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind, UniqueKey uniqueKey, ThumbnailLoadedListener thumbnailLoadedListener) {
        loadThumbnail(thumbnailInterface, thumbKind, uniqueKey, thumbnailLoadedListener, sDefaultInterruptChecker);
    }

    public Bitmap getReplacedThumbnail(Context context, int i2, ThumbKind thumbKind) {
        return this.mReplacedThumbnailMap.computeIfAbsent(Integer.valueOf(i2), new U6.d(i2, thumbKind));
    }

    /* JADX INFO: finally extract failed */
    public synchronized void loadThumbnail(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind, UniqueKey uniqueKey, ThumbnailLoadedListener thumbnailLoadedListener, ThumbnailInterrupter thumbnailInterrupter) {
        try {
            if (thumbnailInterface.getPath() == null) {
                Log.e("ThumbnailLoader", "path is null");
                thumbnailLoadedListener.onLoaded((Bitmap) null, uniqueKey, thumbKind);
                return;
            }
            if (this.mStartTime == 0) {
                this.mStartTime = System.currentTimeMillis();
            }
            ThumbnailInterface thumbnailInterface2 = thumbnailInterface;
            UniqueKey uniqueKey2 = uniqueKey;
            ReqInfo reqInfo = new ReqInfo(thumbnailInterface2, thumbKind, uniqueKey2, thumbnailLoadedListener, thumbnailInterrupter);
            if (uniqueKey2.preferAndroid()) {
                reqInfo.preferAndroid = true;
            }
            if (!thumbnailInterface2.isCreature() && !thumbnailInterface2.isCategory() && handleWorkingRequest(reqInfo)) {
                return;
            }
            if (!thumbnailInterface2.isVideo() || !handleSyncWorkingRequest(reqInfo)) {
                lambda$postponeRequest$1(reqInfo);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private void clearMemoryCache() {
        this.mLargeMemoryCacheMgr.clearCache();
        this.mMediumMemoryCacheMgr.clearCache();
        this.mSmallMemoryCacheMgr.clearCache();
        this.mLargeNcMemoryCacheMgr.clearCache();
        this.mXSmallMemoryCacheMgr.clearCache();
    }

    public Bitmap loadThumbnailSync(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind) {
        return loadThumbnailSync(thumbnailInterface, thumbKind, false);
    }

    public Bitmap loadThumbnailSync(ThumbnailInterface thumbnailInterface, ThumbKind thumbKind, boolean z) {
        Bitmap memCache = getMemCache(thumbnailInterface, thumbKind);
        if (memCache != null) {
            return memCache;
        }
        ThumbnailInterface thumbnailInterface2 = thumbnailInterface;
        ThumbKind thumbKind2 = thumbKind;
        ReqInfo reqInfo = new ReqInfo(thumbnailInterface2, thumbKind2, (UniqueKey) null, (ThumbnailLoadedListener) null, sDefaultInterruptChecker);
        reqInfo.mDiskCacheId = this.mThumbnailLogic.getCacheId(thumbnailInterface2, thumbKind2);
        Bitmap bitmapFromDiskCache = getBitmapFromDiskCache(thumbnailInterface2, thumbKind2);
        if (bitmapFromDiskCache != null) {
            addToRecycler(bitmapFromDiskCache);
            updateMemCacheSync(reqInfo, bitmapFromDiskCache);
            return bitmapFromDiskCache;
        }
        Bitmap load = this.mThumbnailLogic.load(reqInfo, thumbKind2);
        if (load != null) {
            load = BitmapUtils.resizeForMaxBitmapSize(load);
            ThumbnailHandler fileWriteHandler = getFileWriteHandler();
            if (fileWriteHandler != null) {
                load = fileWriteHandler.requestDiskCachePut(reqInfo, load);
                fileWriteHandler.sendEmptyMessage(2000);
            }
            addToRecycler(load);
            if (reqInfo.isMemCacheable()) {
                updateMemCacheSync(reqInfo, load);
            }
            if (z && reqInfo.item.isImage() && !reqInfo.isDecodeExif()) {
                Log.e("ThumbnailLoader", "loadThumbnailSync without exif");
            }
        }
        return load;
    }
}
