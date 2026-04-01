package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.StaleDataException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import c0.C0086a;
import com.samsung.android.gallery.module.cache.MemoryCacheMgr;
import com.samsung.android.gallery.module.concurrent.RwLock;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.AbstractSorter;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.providers.GmpCompat;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataCursor extends MediaDataRef {
    protected final HashMap<Integer, MediaItem> mBackupCache = new HashMap<>();
    protected ClusterTable[] mClusterTables;
    protected Cursor[] mCursors;
    final RwLock mLock = new RwLock();
    protected MemoryCacheMgr<Integer, MediaItem> mRecentCache = new MemoryCacheMgr<>(MOCRLang.GURMUKHI, new C0614t(this));
    AbstractSorter mSorter;
    final boolean mUseClustering;

    public MediaDataCursor(Blackboard blackboard, String str) {
        super(blackboard, str);
        boolean z;
        if (str.contains("/fileList") || LocationKey.isTimeline(str)) {
            z = true;
        } else {
            z = false;
        }
        this.mUseClustering = z;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$loadClusterTables$3(Cursor[] cursorArr, ClusterTable[] clusterTableArr, CountDownLatch countDownLatch, ThreadPool.JobContext jobContext) {
        Cursor cursor = cursorArr[1];
        if (cursor != null) {
            ClusterTable clusterTable = new ClusterTable(cursor);
            clusterTableArr[0] = clusterTable;
            clusterTable.init();
        }
        countDownLatch.countDown();
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$loadClusterTables$4(Cursor[] cursorArr, ClusterTable[] clusterTableArr, CountDownLatch countDownLatch, ThreadPool.JobContext jobContext) {
        Cursor cursor = cursorArr[2];
        if (cursor != null) {
            ClusterTable clusterTable = new ClusterTable(cursor);
            clusterTableArr[1] = clusterTable;
            clusterTable.init();
        }
        countDownLatch.countDown();
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$readAsync$0(int i2, MediaData.OnDataReadListener onDataReadListener, int i7) {
        if (i2 == getDataVersion()) {
            onDataReadListener.onDataReadCompleted(loadInternal(i7));
        } else {
            Log.e(this.TAG, "fail to readAsync. data changed");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$1(Cursor[] cursorArr, Cursor[] cursorArr2, MemoryCacheMgr memoryCacheMgr, ClusterTable[] clusterTableArr, long j2, MemoryCacheMgr memoryCacheMgr2) {
        if (this.mLock.acquireWriteLock()) {
            if (isDataCursorAvailable(cursorArr)) {
                synchronized (cursorArr[0]) {
                    swapInternal(cursorArr2, memoryCacheMgr, clusterTableArr);
                }
            } else {
                swapInternal(cursorArr2, memoryCacheMgr, clusterTableArr);
            }
            Log.d(this.TAG, C0086a.j(j2, this.mLocationKey, ") +", new StringBuilder("swap (")));
            notifyChanged(true);
            memoryCacheMgr2.clearCache(false);
            closeCursors(cursorArr);
            this.mLock.releaseWriteLock();
        }
    }

    private ClusterTable[] loadClusterTables(CountDownLatch countDownLatch, Cursor[] cursorArr) {
        ClusterTable[] clusterTableArr = {null, null};
        if (cursorArr.length > 1) {
            getThreadPool().submit(new C0615u(1, cursorArr, clusterTableArr, countDownLatch));
        }
        if (cursorArr.length > 2) {
            getThreadPool().submit(new C0615u(0, cursorArr, clusterTableArr, countDownLatch));
        }
        return clusterTableArr;
    }

    /* access modifiers changed from: private */
    public void onCacheEvicted(Integer num, MediaItem mediaItem) {
        if (!this.mBackupCache.containsKey(num)) {
            this.mBackupCache.put(num, mediaItem);
        }
    }

    private MediaItem readInternal(int i2) {
        MediaItem cache = this.mRecentCache.getCache(Integer.valueOf(i2));
        if (cache != null) {
            return cache;
        }
        MediaItem mediaItem = this.mBackupCache.get(Integer.valueOf(i2));
        if (mediaItem != null) {
            this.mRecentCache.addCache(Integer.valueOf(i2), mediaItem);
        }
        return mediaItem;
    }

    private void removeInternal(int i2) {
        this.mRecentCache.removeCache(Integer.valueOf(i2));
        this.mBackupCache.remove(Integer.valueOf(i2));
    }

    private boolean supportClustering() {
        return this.mUseClustering;
    }

    private void swapInternal(Cursor[] cursorArr, MemoryCacheMgr<Integer, MediaItem> memoryCacheMgr, ClusterTable[] clusterTableArr) {
        this.mCursors = cursorArr;
        this.mDataCount = getCursorCount(cursorArr[0]);
        this.mClusterTables = clusterTableArr;
        this.mRecentCache = memoryCacheMgr;
        this.mBackupCache.clear();
    }

    public boolean acquireWriteLock(String str) {
        return this.mLock.acquireWriteLock();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo(DataKey.DATA_CURSOR(this.mLocationKey), new C0617w(this, 0)).setTriggerPreloadedData());
        if (useGmpOnly()) {
            arrayList.add(new SubscriberInfo("command://gmp/files/changed", new C0617w(this, 1)));
            arrayList.add(new SubscriberInfo("command://gmp/location/changed", new C0617w(this, 2)));
        } else {
            arrayList.add(new SubscriberInfo("command://event/DataChanged", new C0617w(this, 3)));
            if (PocFeatures.isEnabled(PocFeatures.GmpAll)) {
                arrayList.add(new SubscriberInfo("command://gmp/files/changed", new C0617w(this, 4)));
            }
        }
        arrayList.add(new SubscriberInfo("command://event/DataDirty", new C0617w(this, 5)));
    }

    public ArrayList<MediaItem> getAllData() {
        int count = getCount();
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < count; i2++) {
            arrayList.add(read(i2));
        }
        return arrayList;
    }

    public int getPosition(int i2) {
        AbstractSorter abstractSorter = this.mSorter;
        if (abstractSorter != null) {
            return abstractSorter.getPosition(i2);
        }
        return i2;
    }

    public int getPreloadThumbnailCount() {
        return 12;
    }

    public boolean isDataAvailable() {
        Cursor[] cursorArr = this.mCursors;
        if (cursorArr == null || cursorArr[0] == null || !super.isDataAvailable()) {
            return false;
        }
        return true;
    }

    public boolean isDataCursorAvailable(Cursor[] cursorArr) {
        if (cursorArr == null || cursorArr.length <= 0 || cursorArr[0] == null) {
            return false;
        }
        return true;
    }

    public boolean isFilteredEvent(EventMessage eventMessage) {
        if (!isStoryChanged((Uri) eventMessage.obj) || isStory()) {
            return false;
        }
        return true;
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        if (eventMessage.what == 101) {
            return true;
        }
        return false;
    }

    public MediaItem loadInternal(int i2) {
        try {
            MediaItem loadMediaItem = loadMediaItem(this.mCursors[0], i2);
            if (loadMediaItem == null) {
                return loadMediaItem;
            }
            this.mRecentCache.addCache(Integer.valueOf(i2), loadMediaItem);
            return loadMediaItem;
        } catch (CursorIndexOutOfBoundsException | StaleDataException | ArrayIndexOutOfBoundsException | IllegalStateException | NullPointerException e) {
            Log.e((CharSequence) this.TAG, "loadInternal failed", e);
            return null;
        }
    }

    public MediaItem loadMediaItem(Cursor cursor, int i2) {
        if (this.mLock.acquireReadLock("loadMediaItem")) {
            try {
                return MediaItemLoader.loadMediaItem(cursor, i2);
            } catch (CursorIndexOutOfBoundsException | StaleDataException | ArrayIndexOutOfBoundsException | IllegalStateException | NumberFormatException e) {
                e.printStackTrace();
            } finally {
                this.mLock.releaseReadLock("loadMediaItem");
            }
        }
        Log.e(this.TAG, "fail to get lock");
        return null;
    }

    public void notifyChanged(boolean z) {
        if (z) {
            notifyChanged();
        }
    }

    public void onDataCursorChanged(Object obj, Bundle bundle) {
        Cursor[] cursorArr = (Cursor[]) obj;
        if (isInvalidCursors(cursorArr)) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "onDataCursorChanged failed. invalid cursors " + this);
            return;
        }
        try {
            StringCompat stringCompat2 = this.TAG;
            Log.i(stringCompat2, "onDataCursorChanged {" + this.mLocationKey + ',' + cursorArr.length + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getCursorCount(cursorArr[0]) + '}');
            swap(cursorArr);
        } catch (Exception e) {
            StringCompat stringCompat3 = this.TAG;
            Log.e(stringCompat3, "onDataCursorChanged failed {" + this.mLocationKey + "} e=" + e.getMessage());
            if (!isDataAvailable()) {
                Log.w(this.TAG, "ignore");
                return;
            }
            throw e;
        }
    }

    public void onDestroy() {
        this.mBackupCache.clear();
        this.mRecentCache.clearCache(false);
        closeCursors(this.mCursors);
        this.mCursors = null;
        this.mLock.releaseWriteLock();
        super.onDestroy();
    }

    public void onFilesDataChanged(Object obj, Bundle bundle) {
        GmpCompat.GmpDataChangeDetails gmpDataChangeDetails = (GmpCompat.GmpDataChangeDetails) obj;
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onFilesDataChanged : " + gmpDataChangeDetails);
        if (gmpDataChangeDetails.isRemoved()) {
            requestData(this.mLocationReference);
        } else {
            Log.d(this.TAG, "ignore update/insert noti for virtual album location");
        }
    }

    public void onLocationDataChanged(Object obj, Bundle bundle) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onLocationDataChanged : " + ((GmpCompat.GmpDataChangeDetails) obj));
        requestData(this.mLocationReference);
    }

    public boolean preload(MemoryCacheMgr<Integer, MediaItem> memoryCacheMgr, Cursor cursor) {
        MediaItem loadMediaItem;
        boolean z = true;
        if (cursor == null) {
            Log.e(this.TAG, "preload null");
            return true;
        } else if (cursor.isClosed()) {
            Log.e(this.TAG, "preload closed : " + cursor);
            return true;
        } else {
            if (this.mRecentCache.getSize() != 0 || cursor.getCount() == 0) {
                z = false;
            }
            int min = Math.min(128, cursor.getCount());
            for (int i2 = 0; i2 < min; i2++) {
                MediaItem loadMediaItem2 = loadMediaItem(cursor, i2);
                if (loadMediaItem2 != null) {
                    memoryCacheMgr.addCache(Integer.valueOf(i2), loadMediaItem2);
                }
            }
            int count = cursor.getCount();
            for (Map.Entry<Integer, MediaItem> key : this.mRecentCache.snapshot().entrySet()) {
                Integer num = (Integer) key.getKey();
                int intValue = num.intValue();
                if (intValue < count && intValue >= 128 && (loadMediaItem = loadMediaItem(cursor, intValue)) != null) {
                    memoryCacheMgr.addCache(num, loadMediaItem);
                }
            }
            return z;
        }
    }

    public void preloadThumbnail(MemoryCacheMgr<Integer, MediaItem> memoryCacheMgr) {
        ThumbKind thumbKind;
        int i2;
        this.mBlackboard.publishIfEmpty("lifecycle://on_thumbnail_load_start", Long.valueOf(System.currentTimeMillis()));
        boolean equals = "location://story/albums".equals(this.mLocationKey);
        if (equals) {
            thumbKind = ThumbKind.LARGE_KIND;
        } else {
            thumbKind = ThumbKind.MEDIUM_KIND;
        }
        if (equals) {
            i2 = 3;
        } else {
            i2 = getPreloadThumbnailCount();
        }
        C0612q qVar = new C0612q(1);
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        int min = Math.min(memoryCacheMgr.getSize(), i2);
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "preloadThumbnail INFO{" + thumbKind + "#" + min + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2 + "}");
        for (int i7 = 0; i7 < min; i7++) {
            MediaItem cache = memoryCacheMgr.getCache(Integer.valueOf(Math.min(getPosition(i7), memoryCacheMgr.getSize() - 1)));
            if (cache != null) {
                instance.loadThumbnail(cache, thumbKind, qVar);
            }
        }
    }

    public MediaItem read(int i2) {
        if (i2 >= 0 && i2 < this.mDataCount) {
            MediaItem readInternal = readInternal(i2);
            if (readInternal != null) {
                return readInternal;
            }
            return loadInternal(i2);
        } else if (this.mDataCount < 0) {
            return null;
        } else {
            StringCompat stringCompat = this.TAG;
            StringBuilder o2 = C0086a.o(i2, "read wrong position {", "/");
            o2.append(this.mDataCount);
            o2.append("}");
            Log.e(stringCompat, o2.toString());
            return null;
        }
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        if (i2 >= this.mDataCount) {
            StringCompat stringCompat = this.TAG;
            StringBuilder o2 = C0086a.o(i2, "readAsync wrong position, return null : ", "/");
            o2.append(this.mDataCount);
            Log.e(stringCompat, o2.toString());
            return;
        }
        MediaItem readInternal = readInternal(i2);
        if (readInternal != null) {
            onDataReadListener.onDataReadCompleted(readInternal);
        } else {
            ThreadUtil.postOnBgThread(new C0616v(getDataVersion(), i2, onDataReadListener, this));
        }
    }

    public MediaItem readById(long j2) {
        if (!isDataAvailable()) {
            return null;
        }
        for (Map.Entry next : this.mRecentCache.snapshot().entrySet()) {
            if (((long) ((MediaItem) next.getValue()).getAlbumID()) == j2) {
                return (MediaItem) next.getValue();
            }
        }
        return null;
    }

    public MediaItem readCache(int i2) {
        return this.mRecentCache.getCache(Integer.valueOf(i2));
    }

    public void releaseWriteLock(String str) {
        this.mLock.releaseWriteLock();
    }

    public void removeItemAt(int i2) {
        if (i2 < 0 || i2 >= this.mDataCount) {
            StringCompat stringCompat = this.TAG;
            StringBuilder o2 = C0086a.o(i2, "remove wrong position : ", "/");
            o2.append(this.mDataCount);
            Log.e(stringCompat, o2.toString());
            return;
        }
        removeInternal(i2);
    }

    public void requestData(String str) {
        if (BlackboardUtils.publishDataRequestForce(this.mBlackboard, str)) {
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("requestData {");
            if (str.equals(this.mLocationReference)) {
                str = ArgumentsUtil.removeArgs(str);
            }
            sb2.append(str);
            sb2.append("}");
            Log.i(stringCompat, sb2.toString());
            return;
        }
        StringCompat stringCompat2 = this.TAG;
        Log.e(stringCompat2, "requestData skip duplicated {" + ArgumentsUtil.removeArgs(str) + "}");
    }

    public void sort(Cursor[] cursorArr) {
        AbstractSorter abstractSorter = this.mSorter;
        if (abstractSorter != null) {
            abstractSorter.sort(cursorArr[0]);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
        r14 = r0;
        r3 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0061, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        com.samsung.android.gallery.support.utils.Log.i(r13.TAG, "swap > interrupted (" + r13.mLocationKey + ") e=" + r0.getMessage());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e A[ExcHandler: StaleDataException | IllegalStateException (r0v19 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:6:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004b A[SYNTHETIC, Splitter:B:19:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008a A[SYNTHETIC, Splitter:B:33:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void swap(android.database.Cursor[] r14) {
        /*
            r13 = this;
            java.lang.String r1 = "swap > interrupted ("
            com.samsung.android.gallery.module.concurrent.RwLock r0 = r13.mLock     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            boolean r0 = r0.acquireWriteLock()     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            if (r0 == 0) goto L_0x00a9
            android.database.Cursor[] r4 = r13.mCursors     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            com.samsung.android.gallery.module.cache.MemoryCacheMgr<java.lang.Integer, com.samsung.android.gallery.module.data.MediaItem> r10 = r13.mRecentCache     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            boolean r0 = r13.supportClustering()     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            if (r0 == 0) goto L_0x0033
            int r0 = r14.length     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            r2 = 1
            if (r0 <= r2) goto L_0x0033
            java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            int r2 = r14.length     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            r0.<init>(r2)     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            com.samsung.android.gallery.module.dataset.tables.ClusterTable[] r2 = r13.loadClusterTables(r0, r14)     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            r7 = r2
            goto L_0x0035
        L_0x0029:
            r0 = move-exception
            r14 = r0
            r3 = r13
            goto L_0x00b3
        L_0x002e:
            r0 = move-exception
            r14 = r0
            r3 = r13
            goto L_0x00c6
        L_0x0033:
            r0 = 0
            r7 = r0
        L_0x0035:
            com.samsung.android.gallery.module.cache.MemoryCacheMgr r6 = new com.samsung.android.gallery.module.cache.MemoryCacheMgr     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            com.samsung.android.gallery.module.dataset.t r2 = new com.samsung.android.gallery.module.dataset.t     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            r2.<init>(r13)     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            r3 = 160(0xa0, float:2.24E-43)
            r6.<init>(r3, r2)     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            r2 = 0
            r3 = r14[r2]     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            r13.preload(r6, r3)     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            r13.mForceSwap = r2     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            if (r0 == 0) goto L_0x0081
            r0.countDown()     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0061, StaleDataException | IllegalStateException -> 0x002e }
            r11 = 30
            boolean r0 = r0.await(r11, r2)     // Catch:{ InterruptedException -> 0x0061, StaleDataException | IllegalStateException -> 0x002e }
            if (r0 == 0) goto L_0x0059
            goto L_0x0081
        L_0x0059:
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch:{ InterruptedException -> 0x0061, StaleDataException | IllegalStateException -> 0x002e }
            java.lang.String r2 = "fail get doneSignal in 30sec"
            r0.<init>(r2)     // Catch:{ InterruptedException -> 0x0061, StaleDataException | IllegalStateException -> 0x002e }
            throw r0     // Catch:{ InterruptedException -> 0x0061, StaleDataException | IllegalStateException -> 0x002e }
        L_0x0061:
            r0 = move-exception
            com.samsung.android.gallery.support.utils.StringCompat r2 = r13.TAG     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            r3.<init>(r1)     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            java.lang.String r1 = r13.mLocationKey     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            r3.append(r1)     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            java.lang.String r1 = ") e="
            r3.append(r1)     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            r3.append(r0)     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            java.lang.String r0 = r3.toString()     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
            com.samsung.android.gallery.support.utils.Log.i(r2, r0)     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
        L_0x0081:
            r13.sort(r14)     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            boolean r0 = r13.isDataCursorAvailable(r4)     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            if (r0 != 0) goto L_0x008d
            r13.preloadThumbnail(r6)     // Catch:{ StaleDataException | IllegalStateException -> 0x002e, NullPointerException -> 0x0029 }
        L_0x008d:
            com.samsung.android.gallery.module.concurrent.RwLock r0 = r13.mLock     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            r0.releaseWriteLock()     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            com.samsung.android.gallery.module.dataset.x r2 = new com.samsung.android.gallery.module.dataset.x     // Catch:{ StaleDataException | IllegalStateException -> 0x00a6, NullPointerException -> 0x00a3 }
            r3 = r13
            r5 = r14
            r2.<init>(r3, r4, r5, r6, r7, r8, r10)     // Catch:{ StaleDataException | IllegalStateException -> 0x00a0, NullPointerException -> 0x009d }
            r3.runOnUiThread(r2)     // Catch:{ StaleDataException | IllegalStateException -> 0x00a0, NullPointerException -> 0x009d }
            goto L_0x00e3
        L_0x009d:
            r0 = move-exception
        L_0x009e:
            r14 = r0
            goto L_0x00b3
        L_0x00a0:
            r0 = move-exception
        L_0x00a1:
            r14 = r0
            goto L_0x00c6
        L_0x00a3:
            r0 = move-exception
            r3 = r13
            goto L_0x009e
        L_0x00a6:
            r0 = move-exception
            r3 = r13
            goto L_0x00a1
        L_0x00a9:
            r3 = r13
            com.samsung.android.gallery.support.utils.StringCompat r13 = r3.TAG     // Catch:{ StaleDataException | IllegalStateException -> 0x00a0, NullPointerException -> 0x009d }
            java.lang.String r14 = "swap > fail to get lock for swap"
            com.samsung.android.gallery.support.utils.Log.e(r13, r14)     // Catch:{ StaleDataException | IllegalStateException -> 0x00a0, NullPointerException -> 0x009d }
            goto L_0x00e3
        L_0x00b3:
            android.database.Cursor[] r13 = r3.mCursors
            if (r13 != 0) goto L_0x00c5
            com.samsung.android.gallery.support.utils.StringCompat r13 = r3.TAG
            java.lang.String r14 = "swap > fail by null cursor"
            com.samsung.android.gallery.support.utils.Log.e(r13, r14)
            com.samsung.android.gallery.module.concurrent.RwLock r13 = r3.mLock
            r13.releaseWriteLock()
            goto L_0x00e3
        L_0x00c5:
            throw r14
        L_0x00c6:
            com.samsung.android.gallery.support.utils.StringCompat r13 = r3.TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "swap > fail by destroy e="
            r0.<init>(r1)
            java.lang.String r14 = r14.getMessage()
            r0.append(r14)
            java.lang.String r14 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.e(r13, r14)
            com.samsung.android.gallery.module.concurrent.RwLock r13 = r3.mLock
            r13.releaseWriteLock()
        L_0x00e3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataCursor.swap(android.database.Cursor[]):void");
    }

    public String toString() {
        return super.toString();
    }

    public boolean useGmpOnly() {
        return PocFeatures.isEnabled(PocFeatures.GmpLocOnly);
    }

    public ClusterTable getClusterTable(int i2) {
        ClusterTable[] clusterTableArr = this.mClusterTables;
        if (clusterTableArr == null || clusterTableArr.length <= i2) {
            return null;
        }
        return clusterTableArr[i2];
    }

    public ArrayList<Long> getFileIds() {
        if (this.mLock.acquireReadLock("MDC.getFileIds")) {
            try {
                Cursor[] cursorArr = this.mCursors;
                Cursor cursor = (cursorArr == null || cursorArr.length <= 0) ? null : cursorArr[0];
                if (cursor != null) {
                    ArrayList<Long> arrayList = new ArrayList<>();
                    if (cursor.moveToFirst()) {
                        int columnIndex = cursor.getColumnIndex("__absID");
                        do {
                            arrayList.add(Long.valueOf(cursor.getLong(columnIndex)));
                        } while (cursor.moveToNext());
                    }
                    this.mLock.acquireReadLock("MDC.getFileIds");
                    return arrayList;
                }
                this.mLock.acquireReadLock("MDC.getFileIds");
            } catch (Throwable th) {
                this.mLock.acquireReadLock("MDC.getFileIds");
                throw th;
            }
        } else {
            Log.e(this.TAG, "fail to get lock - file ids");
        }
        return new ArrayList<>();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$preloadThumbnail$2(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
    }
}
