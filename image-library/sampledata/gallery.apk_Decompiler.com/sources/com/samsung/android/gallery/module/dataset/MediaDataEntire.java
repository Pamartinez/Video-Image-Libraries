package com.samsung.android.gallery.module.dataset;

import A.a;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Debug;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.module.R$dimen;
import com.samsung.android.gallery.module.concurrent.RwLock;
import com.samsung.android.gallery.module.data.DataStamp;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.comparator.ComparatorEx;
import com.samsung.android.gallery.module.dataset.tables.DataTable;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.module.dataset.tables.SortedDataTable;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MediaDataEntire extends MediaDataRef {
    private static final boolean LOCK_LOGGABLE = false;
    private final Semaphore SWAP_LOCK = new Semaphore(1);
    protected final boolean[] mCursorValidity = new boolean[4];
    DataTable mDataTable;
    CountDownLatch mFullLoadLatch;
    final RwLock mRwLock = new RwLock();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Candidates {
        String dataHash = null;
        ArrayList<Long> listFileIds = new ArrayList<>();
    }

    public MediaDataEntire(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private void closeTables(Closeable[] closeableArr) {
        for (Closeable closeSilently : closeableArr) {
            Utils.closeSilently(closeSilently);
        }
    }

    private String getLatchInfo(CountDownLatch countDownLatch) {
        if (countDownLatch == null) {
            return "null";
        }
        return Logger.getSimpleName((Object) countDownLatch) + "(" + countDownLatch.getCount() + ")";
    }

    private int[] getListViewInfo(Context context) {
        if (context != null) {
            try {
                GridHelper gridHelper = getGridHelper();
                int gridDepth = gridHelper.getGridDepth();
                int columnSize = gridHelper.getColumnSize(context, gridDepth);
                return new int[]{gridDepth, columnSize, ResourceCompat.getWindowWidth(context) / columnSize};
            } catch (Exception e) {
                a.r(e, new StringBuilder("getListViewInfo failed e="), this.TAG);
            }
        }
        return new int[]{1, 4, ThumbKind.MEDIUM_KIND.size()};
    }

    private Closeable[] getOldTables() {
        return new Closeable[]{this.mDataTable};
    }

    private boolean isOnSwapProcessing() {
        if (this.SWAP_LOCK.availablePermits() == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$readAsync$0(java.util.function.BooleanSupplier r1, int r2, int r3, com.samsung.android.gallery.module.dataset.MediaData.OnDataReadListener r4) {
        /*
            r0 = this;
            if (r1 == 0) goto L_0x0011
            boolean r1 = r1.getAsBoolean()
            if (r1 == 0) goto L_0x0011
            com.samsung.android.gallery.support.utils.StringCompat r0 = r0.TAG
            java.lang.String r1 = "skip load media item"
            com.samsung.android.gallery.support.utils.Log.local(r0, r1)
            return
        L_0x0011:
            int r1 = r0.getDataVersion()
            if (r2 != r1) goto L_0x001c
            com.samsung.android.gallery.module.data.MediaItem r1 = r0.loadInternal(r3)     // Catch:{ NullPointerException -> 0x001c }
            goto L_0x001d
        L_0x001c:
            r1 = 0
        L_0x001d:
            if (r1 == 0) goto L_0x0023
            r4.onDataReadCompleted(r1)
            goto L_0x002a
        L_0x0023:
            com.samsung.android.gallery.support.utils.StringCompat r0 = r0.TAG
            java.lang.String r1 = "readAsync failed. data changed"
            com.samsung.android.gallery.support.utils.Log.e(r0, r1)
        L_0x002a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataEntire.lambda$readAsync$0(java.util.function.BooleanSupplier, int, int, com.samsung.android.gallery.module.dataset.MediaData$OnDataReadListener):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$2(Cursor[] cursorArr, DataTable dataTable) {
        swapTable(cursorArr, dataTable);
        closeTables(getOldTables());
        terminateSwapProcessing();
    }

    private MediaItem loadInternal(int i2) {
        try {
            acquireReadLock("loadInternal");
            return this.mDataTable.loadAndGet(i2);
        } finally {
            releaseReadLock("loadInternal");
        }
    }

    private void preloadThumbnail() {
        ThumbKind thumbKind;
        Activity readActivity = BlackboardUtils.readActivity(this.mBlackboard);
        if (readActivity == null) {
            Log.v(this.TAG, "stop preload thumb");
            return;
        }
        this.mBlackboard.publishIfEmpty("lifecycle://on_thumbnail_load_start", Long.valueOf(System.currentTimeMillis()));
        int[] listViewInfo = getListViewInfo(readActivity);
        int i2 = listViewInfo[0];
        int i7 = listViewInfo[1];
        int i8 = listViewInfo[2];
        if (i7 > 20) {
            StringCompat stringCompat = this.TAG;
            StringBuilder h5 = a.h(i2, i7, "preloadThumbnail skip year {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            h5.append(i8);
            h5.append("}");
            Log.w(stringCompat, h5.toString());
            return;
        }
        try {
            acquireReadLock("preloadThumbnail");
            if (DeviceInfo.isDexMode(readActivity)) {
                thumbKind = ThumbKind.MEDIUM_KIND;
            } else {
                thumbKind = ThumbKind.getOptimalThumbKind(readActivity, i8);
            }
            int preloadCount = getPreloadCount(readActivity, i2, i7, i8);
            int min = Math.min(this.mDataTable.getLoadedCount(), preloadCount);
            this.mBlackboard.publish("data://preload_count", Integer.valueOf(min));
            StringCompat stringCompat2 = this.TAG;
            Log.d(stringCompat2, "preloadThumbnail INFO{" + thumbKind + "#" + min + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i7 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i8 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + preloadCount + "}");
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            C0612q qVar = new C0612q(2);
            for (int i10 = 0; i10 < min; i10++) {
                MediaItem temp = this.mDataTable.getTemp(i10);
                if (temp != null) {
                    instance.loadThumbnail(temp, thumbKind, qVar);
                }
            }
            releaseReadLock("preloadThumbnail");
        } catch (NoSuchMethodError | NullPointerException e) {
            Log.e((CharSequence) this.TAG, "preloadThumbnail failed", e);
            releaseReadLock("preloadThumbnail");
        } catch (Throwable th) {
            releaseReadLock("preloadThumbnail");
            throw th;
        }
    }

    private MediaItem readInternal(int i2) {
        try {
            acquireReadLock("readInternal");
            return this.mDataTable.get(i2);
        } finally {
            releaseReadLock("readInternal");
        }
    }

    public void acquireReadLock(String str) {
        if (LOCK_LOGGABLE) {
            StringCompat stringCompat = this.TAG;
            Log.v(stringCompat, "acquireReadLock{" + str + "}");
        }
        this.mRwLock.acquireReadLock(str);
    }

    public boolean beginSwapProcessing() {
        this.SWAP_LOCK.acquire();
        return true;
    }

    public void clearPartialLoaded(DataTable dataTable) {
        if (dataTable != null && dataTable.isPartialLoaded()) {
            dataTable.setPartialLoaded(false);
        }
    }

    public DataTable createDataTable(Cursor cursor) {
        return new DataTable(cursor);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo(DataKey.DATA_CURSOR(this.mLocationKey), new C0619y(this, 0)).setTriggerPreloadedData());
        if (!useGmpOnly()) {
            if (PocFeatures.isEnabled(PocFeatures.GmpAll)) {
                arrayList.add(new SubscriberInfo("command://gmp/files/changed", new C0619y(this, 1)));
            }
            arrayList.add(new SubscriberInfo("command://event/DataChanged", new C0619y(this, 2)));
        }
        arrayList.add(new SubscriberInfo("command://event/DataDirty", new C0619y(this, 3)));
    }

    public int findPosition(long j2) {
        DataTable dataTable = this.mDataTable;
        if (dataTable != null) {
            return dataTable.findPosition(j2);
        }
        return -1;
    }

    public int findPositionByFileId(long j2) {
        DataTable dataTable = this.mDataTable;
        if (dataTable != null) {
            return dataTable.findPositionByFileId(j2);
        }
        return -1;
    }

    public ArrayList<MediaItem> getAllData() {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        try {
            acquireReadLock("getAllData");
            for (int i2 = 0; i2 < this.mDataCount; i2++) {
                arrayList.add(read(i2));
            }
            return arrayList;
        } catch (Exception e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "getAllData failed. e=" + e.getMessage());
            return arrayList;
        } finally {
            releaseReadLock("getAllData");
        }
    }

    public abstract int getCount();

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        releaseReadLock("getFileIds");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        return java.util.Collections.EMPTY_LIST;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.Long> getFileIds() {
        /*
            r2 = this;
            java.lang.String r0 = "getFileIds"
            r2.acquireReadLock(r0)     // Catch:{ NullPointerException -> 0x0011 }
            com.samsung.android.gallery.module.dataset.tables.DataTable r1 = r2.mDataTable     // Catch:{ NullPointerException -> 0x0011 }
            java.util.List r1 = r1.readAllFileId()     // Catch:{ NullPointerException -> 0x0011 }
            r2.releaseReadLock(r0)
            return r1
        L_0x000f:
            r1 = move-exception
            goto L_0x0017
        L_0x0011:
            java.util.List r1 = java.util.Collections.EMPTY_LIST     // Catch:{ all -> 0x000f }
            r2.releaseReadLock(r0)
            return r1
        L_0x0017:
            r2.releaseReadLock(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataEntire.getFileIds():java.util.List");
    }

    public int getLockSize(Cursor[] cursorArr) {
        int i2 = 0;
        for (Cursor cursor : cursorArr) {
            if (cursor != null) {
                i2++;
            }
        }
        return i2;
    }

    public int getPreloadCount(Context context, int i2, int i7, int i8) {
        if (i7 == 1) {
            return 6;
        }
        if (context == null) {
            return 28;
        }
        try {
            return ((int) Math.ceil((double) (((float) ((ResourceCompat.getWindowHeight(context) - DeviceInfo.getStatusBarHeight()) - context.getResources().getDimensionPixelOffset(R$dimen.toolbar_height))) / ((float) i8)))) * i7;
        } catch (Exception e) {
            a.r(e, new StringBuilder("getPreloadCount failed e="), this.TAG);
            return 28;
        }
    }

    public int getRealCount() {
        if (!isDataAvailable()) {
            return 0;
        }
        try {
            acquireReadLock("getRealCount");
            return this.mDataTable.getRealCount();
        } finally {
            releaseReadLock("getRealCount");
        }
    }

    public ComparatorEx<MediaItem> getSorter() {
        return null;
    }

    public final void init(Cursor[] cursorArr) {
        int i2;
        CountDownLatch countDownLatch;
        long currentTimeMillis = System.currentTimeMillis();
        Log.i(this.TAG, "init > start");
        Candidates candidates = new Candidates();
        preprocessCursors(cursorArr, candidates);
        int lockSize = getLockSize(cursorArr);
        CountDownLatch countDownLatch2 = new CountDownLatch(lockSize);
        this.mFullLoadLatch = new CountDownLatch(lockSize);
        if (!this.mRwLock.acquireWriteLock()) {
            Log.e(this.TAG, "init > fail to acquire write lock" + toDebugString());
            return;
        }
        if (useSortedTable()) {
            if (getSorter() != null) {
                this.mDataTable = new SortedDataTable(cursorArr[0], getSorter());
            } else {
                throw new AssertionError("no sorter for using sorted table");
            }
        } else if (ArgumentsUtil.getArgValue(this.mLocationReference, "presentation_view", false)) {
            this.mDataTable = createDataTable(cursorArr[0]);
        } else {
            this.mDataTable = createDataTable(cursorArr[0], new DefaultTable.OnLoadDoneListener() {
                public void onLoadDone() {
                    MediaDataEntire mediaDataEntire = MediaDataEntire.this;
                    CountDownLatch countDownLatch = mediaDataEntire.mFullLoadLatch;
                    if (countDownLatch != null) {
                        countDownLatch.countDown();
                        Log.d(MediaDataEntire.this.TAG, "DataTable > notify full load", Long.valueOf(countDownLatch.getCount()));
                        MediaDataEntire.this.notifyFullLoadDone();
                        return;
                    }
                    Log.w(mediaDataEntire.TAG, "init > notify full load skip by null latch");
                }
            }, this.mFakeLoadingCount);
        }
        initExtraTable(cursorArr, countDownLatch2, candidates);
        if (initDataTable() && (countDownLatch = this.mFullLoadLatch) != null) {
            countDownLatch.countDown();
        }
        countDownLatch2.countDown();
        try {
            if (Debug.isDebuggerConnected()) {
                i2 = 99999;
            } else {
                i2 = 10;
            }
            while (true) {
                if (!countDownLatch2.await(1, TimeUnit.SECONDS)) {
                    if (this.mDataTable != null) {
                        i2--;
                        if (i2 != 0) {
                            if (i2 <= 0) {
                                break;
                            }
                        } else {
                            Log.e(this.TAG, "fail to init data until 10 seconds");
                            this.mRwLock.releaseWriteLock();
                            return;
                        }
                    } else {
                        Log.e(this.TAG, "init > stop loading. table destroyed " + toDebugString());
                        this.mRwLock.releaseWriteLock();
                        return;
                    }
                } else {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onInitDone();
        this.mDataCount = this.mDataTable.getRealCount();
        preloadMediaItemOnInit();
        setPartialLoaded(this.mDataTable);
        this.mRwLock.releaseWriteLock();
        MediaItem mediaItem = this.mDataTable.get(0);
        if (mediaItem != null) {
            String dataStamp = mediaItem.getDataStamp();
            if (!TextUtils.isEmpty(dataStamp)) {
                this.mLastDataStamp = new DataStamp(dataStamp);
                this.mBlackboard.post("data://DataStamp", dataStamp);
            }
            C0212a.z(new Object[]{dataStamp, mediaItem, toSimpleString(), Long.valueOf(currentTimeMillis)}, new StringBuilder("init > notify "), this.TAG);
        } else {
            C0212a.z(new Object[]{toSimpleString(), Long.valueOf(currentTimeMillis)}, new StringBuilder("init > notify "), this.TAG);
        }
        notifyChanged();
        preloadThumbnail();
    }

    public boolean initDataTable() {
        return this.mDataTable.init();
    }

    public boolean isDataAvailable() {
        if (this.mDataTable == null || this.mDataCount < 0) {
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

    public boolean isFirstFakeLoaded() {
        DataTable dataTable = this.mDataTable;
        if (dataTable == null || dataTable.getLastUsedIndex() != 0 || getCount() > 120) {
            return false;
        }
        return true;
    }

    public boolean isFullyLoaded() {
        DataTable dataTable = this.mDataTable;
        if (dataTable == null || !dataTable.isFullLoaded()) {
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

    public synchronized void notifyFullLoadDone() {
        boolean z;
        try {
            CountDownLatch countDownLatch = this.mFullLoadLatch;
            if (countDownLatch != null) {
                if (countDownLatch.getCount() <= 0) {
                    if (this.mDataTable == null) {
                        Log.d(this.TAG, "init > ignore fullLoad. media data destroyed");
                    } else if (isOnSwapProcessing()) {
                        Log.d(this.TAG, "init > ignore fullLoad. swap is on processing");
                    } else {
                        onNotifyFullLoaded();
                    }
                    this.mFullLoadLatch = null;
                    return;
                }
            }
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("init > ignore fullLoad. null or waiting latch ");
            if (countDownLatch == null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            Log.d(stringCompat, sb2.toString());
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        com.samsung.android.gallery.support.utils.Log.i(r4.TAG, "onDataCursorChanged {" + r4.mLocationKey + ',' + r5.length + ',' + getCursorCount(r5[0]) + '}');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006b, code lost:
        if (r4.mDataTable != null) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006d, code lost:
        init(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0071, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        swap(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0077, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        com.samsung.android.gallery.support.utils.Log.e(r4.TAG, "swap > interrupted. " + toDebugString());
        r5.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009b, code lost:
        if (getDataVersion() != 0) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009f, code lost:
        if (r4.mDataTable == null) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a2, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a3, code lost:
        r4.mRwLock.releaseWriteLock();
        r5.printStackTrace();
        com.samsung.android.gallery.support.utils.Log.e(r4.TAG, "swap > failed. MediaData destroyed during init/swap. " + toDebugString());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onDataCursorChanged(java.lang.Object r5, android.os.Bundle r6) {
        /*
            r4 = this;
            java.lang.String r6 = "onDataCursorChanged failed. invalid cursors "
            monitor-enter(r4)
            android.database.Cursor[] r5 = (android.database.Cursor[]) r5     // Catch:{ all -> 0x001e }
            boolean r0 = r4.isInvalidCursors(r5)     // Catch:{ all -> 0x001e }
            if (r0 == 0) goto L_0x0021
            com.samsung.android.gallery.support.utils.StringCompat r5 = r4.TAG     // Catch:{ all -> 0x001e }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x001e }
            r0.<init>(r6)     // Catch:{ all -> 0x001e }
            r0.append(r4)     // Catch:{ all -> 0x001e }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x001e }
            com.samsung.android.gallery.support.utils.Log.e(r5, r6)     // Catch:{ all -> 0x001e }
            monitor-exit(r4)
            return
        L_0x001e:
            r5 = move-exception
            goto L_0x00c8
        L_0x0021:
            r6 = 0
            r0 = r6
        L_0x0023:
            boolean[] r1 = r4.mCursorValidity     // Catch:{ all -> 0x001e }
            int r2 = r1.length     // Catch:{ all -> 0x001e }
            if (r0 >= r2) goto L_0x0037
            int r2 = r5.length     // Catch:{ all -> 0x001e }
            if (r2 <= r0) goto L_0x0031
            r2 = r5[r0]     // Catch:{ all -> 0x001e }
            if (r2 == 0) goto L_0x0031
            r2 = 1
            goto L_0x0032
        L_0x0031:
            r2 = r6
        L_0x0032:
            r1[r0] = r2     // Catch:{ all -> 0x001e }
            int r0 = r0 + 1
            goto L_0x0023
        L_0x0037:
            com.samsung.android.gallery.support.utils.StringCompat r0 = r4.TAG     // Catch:{ all -> 0x001e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x001e }
            r1.<init>()     // Catch:{ all -> 0x001e }
            java.lang.String r2 = "onDataCursorChanged {"
            r1.append(r2)     // Catch:{ all -> 0x001e }
            java.lang.String r2 = r4.mLocationKey     // Catch:{ all -> 0x001e }
            r1.append(r2)     // Catch:{ all -> 0x001e }
            r2 = 44
            r1.append(r2)     // Catch:{ all -> 0x001e }
            int r3 = r5.length     // Catch:{ all -> 0x001e }
            r1.append(r3)     // Catch:{ all -> 0x001e }
            r1.append(r2)     // Catch:{ all -> 0x001e }
            r6 = r5[r6]     // Catch:{ all -> 0x001e }
            int r6 = r4.getCursorCount(r6)     // Catch:{ all -> 0x001e }
            r1.append(r6)     // Catch:{ all -> 0x001e }
            r6 = 125(0x7d, float:1.75E-43)
            r1.append(r6)     // Catch:{ all -> 0x001e }
            java.lang.String r6 = r1.toString()     // Catch:{ all -> 0x001e }
            com.samsung.android.gallery.support.utils.Log.i(r0, r6)     // Catch:{ all -> 0x001e }
            com.samsung.android.gallery.module.dataset.tables.DataTable r6 = r4.mDataTable     // Catch:{ Exception -> 0x0071 }
            if (r6 != 0) goto L_0x0073
            r4.init(r5)     // Catch:{ Exception -> 0x0071 }
            goto L_0x00c6
        L_0x0071:
            r5 = move-exception
            goto L_0x0097
        L_0x0073:
            r4.swap(r5)     // Catch:{ InterruptedException -> 0x0077 }
            goto L_0x00c6
        L_0x0077:
            r5 = move-exception
            com.samsung.android.gallery.support.utils.StringCompat r6 = r4.TAG     // Catch:{ Exception -> 0x0071 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0071 }
            r0.<init>()     // Catch:{ Exception -> 0x0071 }
            java.lang.String r1 = "swap > interrupted. "
            r0.append(r1)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r1 = r4.toDebugString()     // Catch:{ Exception -> 0x0071 }
            r0.append(r1)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0071 }
            com.samsung.android.gallery.support.utils.Log.e(r6, r0)     // Catch:{ Exception -> 0x0071 }
            r5.printStackTrace()     // Catch:{ Exception -> 0x0071 }
            goto L_0x00c6
        L_0x0097:
            int r6 = r4.getDataVersion()     // Catch:{ all -> 0x001e }
            if (r6 == 0) goto L_0x00a3
            com.samsung.android.gallery.module.dataset.tables.DataTable r6 = r4.mDataTable     // Catch:{ all -> 0x001e }
            if (r6 != 0) goto L_0x00a2
            goto L_0x00a3
        L_0x00a2:
            throw r5     // Catch:{ all -> 0x001e }
        L_0x00a3:
            com.samsung.android.gallery.module.concurrent.RwLock r6 = r4.mRwLock     // Catch:{ all -> 0x001e }
            r6.releaseWriteLock()     // Catch:{ all -> 0x001e }
            r5.printStackTrace()     // Catch:{ all -> 0x001e }
            com.samsung.android.gallery.support.utils.StringCompat r5 = r4.TAG     // Catch:{ all -> 0x001e }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x001e }
            r6.<init>()     // Catch:{ all -> 0x001e }
            java.lang.String r0 = "swap > failed. MediaData destroyed during init/swap. "
            r6.append(r0)     // Catch:{ all -> 0x001e }
            java.lang.String r0 = r4.toDebugString()     // Catch:{ all -> 0x001e }
            r6.append(r0)     // Catch:{ all -> 0x001e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x001e }
            com.samsung.android.gallery.support.utils.Log.e(r5, r6)     // Catch:{ all -> 0x001e }
        L_0x00c6:
            monitor-exit(r4)
            return
        L_0x00c8:
            monitor-exit(r4)     // Catch:{ all -> 0x001e }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataEntire.onDataCursorChanged(java.lang.Object, android.os.Bundle):void");
    }

    public void onDestroy() {
        Utils.closeSilently(this.mDataTable);
        this.mDataTable = null;
        this.mRwLock.releaseWriteLock();
        this.SWAP_LOCK.release();
        super.onDestroy();
    }

    public abstract void onInitDone();

    public void onNotifyFullLoaded() {
        this.mBlackboard.publishIfEmpty("debug://TimeDoneFullLoad", Long.valueOf(System.currentTimeMillis()));
        if (this.mRwLock.acquireWriteLock()) {
            Log.d(this.TAG, "init > notify fullLoad");
            notifyChanged();
            this.mRwLock.releaseWriteLock();
        }
    }

    public synchronized void reInit(String str) {
        Log.d(this.TAG, "reInit. set data table null");
        Utils.closeSilently(this.mDataTable);
        this.mDataTable = null;
        setLocationKey(str);
        requestData(str);
    }

    public MediaItem read(int i2) {
        if (i2 >= this.mDataCount || this.mDataTable == null) {
            return null;
        }
        MediaItem readInternal = readInternal(i2);
        if (readInternal == null) {
            return loadInternal(i2);
        }
        return readInternal;
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        if (i2 >= this.mDataCount || this.mDataTable == null) {
            onDataReadListener.onDataReadCompleted((MediaItem) null);
            return;
        }
        MediaItem readInternal = readInternal(i2);
        if (readInternal != null) {
            onDataReadListener.onDataReadCompleted(readInternal);
            return;
        }
        ThreadUtil.postOnBgThread(new C0611p(this, booleanSupplier, getDataVersion(), i2, onDataReadListener));
    }

    public MediaItem readCache(int i2) {
        if (i2 >= this.mDataCount || this.mDataTable == null) {
            return null;
        }
        return readInternal(i2);
    }

    public void releaseReadLock(String str) {
        if (LOCK_LOGGABLE) {
            StringCompat stringCompat = this.TAG;
            Log.v(stringCompat, "releaseReadLock{" + str + "}");
        }
        this.mRwLock.releaseReadLock(str);
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
            Log.d(stringCompat, sb2.toString());
            return;
        }
        StringCompat stringCompat2 = this.TAG;
        Log.w(stringCompat2, "requestData skip duplicated {" + ArgumentsUtil.removeArgs(str) + "}");
    }

    public void swap(Cursor[] cursorArr) {
        if (this.mDataTable.isDuplicated(cursorArr[0])) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "swap > skipped by same cursor (" + this.mLocationKey + ")");
            return;
        }
        StringCompat stringCompat2 = this.TAG;
        Log.i(stringCompat2, "swap > start " + cursorArr.length);
        DataTable createDataTable = createDataTable(cursorArr[0]);
        if (this.mForceSwap) {
            Log.d(this.TAG, "swap > forceSwap. set by data change event arg1 == 1");
            this.mForceSwap = false;
        } else if (this.mDataTable.equals(createDataTable)) {
            StringCompat stringCompat3 = this.TAG;
            Log.d(stringCompat3, "swap > skip by same data (" + this.mLocationKey + ")");
            closeCursors(cursorArr);
            return;
        }
        beginSwapProcessing();
        createDataTable.fillDataRecords(this.mDataTable);
        runOnUiThread(new C0620z(this, cursorArr, createDataTable, 0));
    }

    public void swapTable(Cursor[] cursorArr, DataTable dataTable) {
        if (this.mRwLock.acquireWriteLock()) {
            this.mDataTable = dataTable;
            this.mDataCount = dataTable.getRealCount();
            StringCompat stringCompat = this.TAG;
            Log.i(stringCompat, "swap > notify (" + this.mLocationKey + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getDataVersion() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mDataTable.getDataStamp() + ")");
            notifyChanged();
            this.mRwLock.releaseWriteLock();
        }
    }

    public void terminateSwapProcessing() {
        this.SWAP_LOCK.release();
    }

    public String toDebugString() {
        return toString() + " DEBUG{," + this.mDataTable + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mListener + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mRwLock + ",SwapLockPermits=" + this.SWAP_LOCK.availablePermits() + "}";
    }

    public String toSimpleString() {
        String str;
        DataTable dataTable = this.mDataTable;
        StringBuilder sb2 = new StringBuilder("MediaData{");
        sb2.append(this.mLocationKey);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getDataVersion());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (dataTable != null) {
            str = dataTable.getDataStamp();
        } else {
            str = "null";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mDataCount);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0086a.l(sb2, getCount(), "}");
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(hashTag());
        sb2.append("{");
        sb2.append(this.mLocationKey);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mRefCount);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getDataVersion());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getCount());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mForceSwap);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0212a.p(sb2, getLatchInfo(this.mFullLoadLatch), "}");
    }

    public boolean useSortedTable() {
        return false;
    }

    public DataTable createDataTable(Cursor cursor, DefaultTable.OnLoadDoneListener onLoadDoneListener, int i2) {
        return new DataTable(cursor, onLoadDoneListener, i2);
    }

    public void preloadMediaItemOnInit() {
    }

    public void setPartialLoaded(DataTable dataTable) {
    }

    public void preprocessCursors(Cursor[] cursorArr, Candidates candidates) {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$preloadThumbnail$1(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
    }

    public void initExtraTable(Cursor[] cursorArr, CountDownLatch countDownLatch, Candidates candidates) {
    }
}
