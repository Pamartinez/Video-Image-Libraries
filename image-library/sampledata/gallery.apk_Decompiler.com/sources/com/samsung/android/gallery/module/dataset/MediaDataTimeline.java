package com.samsung.android.gallery.module.dataset;

import A.a;
import N2.j;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.StaleDataException;
import android.os.Bundle;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.R$dimen;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataEntire;
import com.samsung.android.gallery.module.dataset.tables.ClusterIndexer;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.dataset.tables.DataTable;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.module.dataset.tables.RealRatioIndexer;
import com.samsung.android.gallery.module.dataset.tables.RealRatioIndexerCompat;
import com.samsung.android.gallery.module.dataset.tables.RealRatioTable;
import com.samsung.android.gallery.module.dataset.tables.SpanIndexer;
import com.samsung.android.gallery.module.dataset.tables.SpanTable;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.module.utils.AppbarInfo;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.providers.GmpCompat;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceAnalytics;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import i.C0212a;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataTimeline extends MediaDataEntire {
    private static final boolean ENABLE_REAL_RATIO = Features.isEnabled(Features.SUPPORT_REAL_RATIO);
    protected final ClusterTable[] mClusterTables = new ClusterTable[2];
    private int mDataMisMatchingRetry = 3;
    protected boolean mDisableDayMerge;
    protected Closeable[] mExtraIndexers = new Closeable[getExtraTableCount()];
    protected DefaultTable[] mExtraTables = new DefaultTable[getExtraTableCount()];
    protected int mLoadedCount;
    protected boolean mTimelinePictures = "location://timeline".equals(this.mLocationKey);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Chunk {
        int count = 1;
        String date;
        String month;
        String to;

        public Chunk(MediaItem mediaItem) {
            String dateRaw = mediaItem.getDateRaw();
            this.date = dateRaw;
            if (dateRaw == null) {
                this.date = "";
            }
        }

        public void append(MediaItem mediaItem) {
            this.count++;
        }

        public boolean equalsDay(String str) {
            return this.date.equals(str);
        }

        public boolean equalsMonth(Chunk chunk) {
            return getMonth().equals(chunk.getMonth());
        }

        public String getMonth() {
            if (this.month == null) {
                this.month = this.date.substring(0, 7);
            }
            return this.month;
        }

        public String toString() {
            char c5;
            StringBuilder sb2 = new StringBuilder("Chunk{");
            sb2.append(this.date);
            if (this.to == null) {
                c5 = ',';
            } else {
                c5 = C0086a.m(new StringBuilder("~"), this.to, ',');
            }
            sb2.append(c5);
            return j.e(sb2, this.count, '}');
        }

        public void append(Chunk chunk) {
            this.to = chunk.date;
            this.count += chunk.count;
        }

        public boolean equalsDay(MediaItem mediaItem) {
            return this.date.equals(mediaItem.getDateRaw());
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CountCalculator {
        private final int mColumnCount;
        private int mCount;
        private final int mDividerHeaderHeight;
        private final int mDividerHeight;
        private final int mGridSize;
        protected int mHeight;

        public CountCalculator(Context context, int i2, int i7, boolean z, boolean z3, boolean z7) {
            int i8;
            int i10;
            int i11;
            System.currentTimeMillis();
            Resources resources = context.getResources();
            this.mColumnCount = i2;
            this.mGridSize = i7;
            int i12 = 0;
            if (z) {
                i8 = resources.getDimensionPixelSize(R$dimen.date_location_body_divider_height);
            } else {
                i8 = 0;
            }
            this.mDividerHeight = i8;
            if (z) {
                i10 = resources.getDimensionPixelSize(R$dimen.date_location_header_divider_height);
            } else {
                i10 = 0;
            }
            this.mDividerHeaderHeight = i10;
            int windowHeight = ResourceCompat.getWindowHeight(context);
            if (z7) {
                i11 = AppbarInfo.getAppbarHeight(context);
            } else {
                i11 = resources.getDimensionPixelSize(R$dimen.toolbar_height);
            }
            this.mHeight = resources.getDimensionPixelSize(R$dimen.toolbar_bottom_margin_for_no_extend) + ((windowHeight - i11) - (z3 ? resources.getDimensionPixelSize(R$dimen.bottom_bar_floating_height) : i12));
        }

        public int getCount() {
            return this.mCount;
        }

        public boolean hasSpace() {
            if (this.mHeight > 0) {
                return true;
            }
            return false;
        }

        public void updateCluster(String str, int i2) {
            int i7;
            int i8 = this.mHeight;
            if (this.mCount == 0) {
                i7 = this.mDividerHeaderHeight;
            } else {
                i7 = this.mDividerHeight;
            }
            this.mHeight = i8 - i7;
            while (i2 > 0 && hasSpace()) {
                this.mHeight -= this.mGridSize;
                this.mCount = Math.min(i2, this.mColumnCount) + this.mCount;
                i2 -= this.mColumnCount;
            }
        }
    }

    public MediaDataTimeline(Blackboard blackboard, String str) {
        super(blackboard, str);
        this.mDisableDayMerge = Boolean.parseBoolean(ArgumentsUtil.getArgValue(str, "disable_day_merge", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE));
    }

    /* access modifiers changed from: private */
    /* renamed from: close */
    public void lambda$notifySwapDone$8(List<Closeable> list) {
        for (Closeable closeSilently : list) {
            Utils.closeSilently(closeSilently);
        }
    }

    private void createRealRatioIndexer(Closeable[] closeableArr, int i2, ClusterTable[] clusterTableArr, DefaultTable[] defaultTableArr, DataTable dataTable) {
        closeableArr[0] = createRealRatioIndexer(i2, clusterTableArr, defaultTableArr[0], dataTable);
    }

    private ClusterIndexer getDayClusterIndexer() {
        DataTable dataTable = this.mDataTable;
        if (dataTable != null) {
            return dataTable.getClusterIndexer(0);
        }
        return null;
    }

    private int getGridCountInView(Context context, int i2, int i7, int i8) {
        boolean isTimelineMonth = GridHelper.isTimelineMonth(i2);
        CountCalculator countCalculator = new CountCalculator(context, i7, i8, true, true, false);
        if (isTimelineMonth || !PreferenceFeatures.isEnabled(PreferenceFeatures.DayMerge) || this.mDisableDayMerge) {
            String str = null;
            int i10 = 0;
            for (int i11 = 0; i11 < 120 && countCalculator.hasSpace(); i11++) {
                MediaItem temp = this.mDataTable.getTemp(i11);
                if (temp == null) {
                    break;
                }
                String dateRaw = temp.getDateRaw();
                if (isTimelineMonth) {
                    dateRaw = dateRaw.substring(0, 7);
                }
                if (str != null) {
                    if (str.equals(dateRaw)) {
                        i10++;
                    } else {
                        countCalculator.updateCluster(str, i10);
                    }
                }
                str = dateRaw;
                i10 = 1;
            }
            if (i10 > 0 && countCalculator.hasSpace()) {
                countCalculator.updateCluster(str, i10);
            }
            return countCalculator.getCount();
        }
        Iterator<Chunk> it = makeDayClusterList(120).iterator();
        while (it.hasNext()) {
            Chunk next = it.next();
            countCalculator.updateCluster(next.date, next.count);
            if (!countCalculator.hasSpace()) {
                break;
            }
        }
        return countCalculator.getCount();
    }

    private DefaultTable.OnLoadDoneListener getOnLoadDoneListener(int i2) {
        return new C0(this, i2);
    }

    private String getTableLog() {
        try {
            ClusterTable clusterTable = this.mClusterTables[0];
            if (clusterTable != null) {
                return clusterTable.getLog();
            }
            return (String) Optional.ofNullable(getDayClusterIndexer()).map(new K(4)).orElse("{null}");
        } catch (Exception unused) {
            return "null";
        }
    }

    private void initClusterTable(int i2, Cursor[] cursorArr, CountDownLatch countDownLatch) {
        CountDownLatch countDownLatch2;
        this.mClusterTables[i2] = createClusterTable(i2, cursorArr, getOnLoadDoneListener(i2));
        if (this.mClusterTables[i2].init() && (countDownLatch2 = this.mFullLoadLatch) != null) {
            countDownLatch2.countDown();
        }
        countDownLatch.countDown();
    }

    private boolean isDayCursorAvailable() {
        return this.mCursorValidity[1];
    }

    private boolean isMonthCursorAvailable() {
        return this.mCursorValidity[1];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getOnLoadDoneListener$5(int i2) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "initClusterTable onLoadDone : " + i2);
        DataTable dataTable = this.mDataTable;
        if (dataTable != null) {
            this.mClusterTables[i2].makeClusterIndex(dataTable.getRealCount());
        } else {
            Log.e(this.TAG, "mDataTable is null. destroyed");
        }
        CountDownLatch countDownLatch = this.mFullLoadLatch;
        if (countDownLatch != null) {
            countDownLatch.countDown();
            notifyFullLoadDone();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getTableLog$14(ClusterIndexer clusterIndexer) {
        return "{DAY(" + clusterIndexer.getLog() + ")}";
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$initExtraTable$1(Cursor[] cursorArr, CountDownLatch countDownLatch, ThreadPool.JobContext jobContext) {
        initClusterTable(0, cursorArr, countDownLatch);
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$initExtraTable$2(Cursor[] cursorArr, CountDownLatch countDownLatch, ThreadPool.JobContext jobContext) {
        initClusterTable(1, cursorArr, countDownLatch);
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$initExtraTable$3(Cursor[] cursorArr, CountDownLatch countDownLatch, ThreadPool.JobContext jobContext) {
        CountDownLatch countDownLatch2;
        this.mExtraTables[0] = new RealRatioTable(cursorArr[3], new DefaultTable.OnLoadDoneListener() {
            public void onLoadDone() {
                CountDownLatch countDownLatch = MediaDataTimeline.this.mFullLoadLatch;
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                    MediaDataTimeline.this.notifyFullLoadDone();
                }
            }
        });
        if (this.mExtraTables[0].init() && (countDownLatch2 = this.mFullLoadLatch) != null) {
            countDownLatch2.countDown();
        }
        countDownLatch.countDown();
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$initExtraTable$4(Cursor[] cursorArr, CountDownLatch countDownLatch, ThreadPool.JobContext jobContext) {
        CountDownLatch countDownLatch2;
        this.mExtraTables[1] = new SpanTable(cursorArr[4], new DefaultTable.OnLoadDoneListener() {
            public void onLoadDone() {
                CountDownLatch countDownLatch = MediaDataTimeline.this.mFullLoadLatch;
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                    MediaDataTimeline.this.notifyFullLoadDone();
                }
            }
        });
        if (this.mExtraTables[1].init() && (countDownLatch2 = this.mFullLoadLatch) != null) {
            countDownLatch2.countDown();
        }
        countDownLatch.countDown();
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$6(Cursor[] cursorArr, Object obj, Bundle bundle) {
        closeCursors(cursorArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$swapClusterTables$10(Cursor[] cursorArr, ClusterTable[] clusterTableArr, CountDownLatch countDownLatch, ThreadPool.JobContext jobContext) {
        swapClusterTable(1, cursorArr, clusterTableArr, countDownLatch);
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$swapClusterTables$11(DefaultTable[] defaultTableArr, Cursor[] cursorArr, CountDownLatch countDownLatch, ThreadPool.JobContext jobContext) {
        try {
            RealRatioTable realRatioTable = new RealRatioTable(cursorArr[3]);
            defaultTableArr[0] = realRatioTable;
            realRatioTable.init();
        } catch (StaleDataException unused) {
            Log.e(this.TAG, "fail swap real ratio");
        }
        countDownLatch.countDown();
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$swapClusterTables$12(DefaultTable[] defaultTableArr, Cursor[] cursorArr, CountDownLatch countDownLatch, ThreadPool.JobContext jobContext) {
        SpanTable spanTable = new SpanTable(cursorArr[4]);
        defaultTableArr[1] = spanTable;
        spanTable.init();
        countDownLatch.countDown();
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$swapClusterTables$9(Cursor[] cursorArr, ClusterTable[] clusterTableArr, CountDownLatch countDownLatch, ThreadPool.JobContext jobContext) {
        swapClusterTable(0, cursorArr, clusterTableArr, countDownLatch);
        return null;
    }

    private ArrayList<Chunk> makeDayClusterList(int i2) {
        Chunk chunk;
        String isoToday = TimeUtil.getIsoToday();
        ArrayList arrayList = new ArrayList();
        Chunk chunk2 = null;
        for (int i7 = 0; i7 < i2; i7++) {
            MediaItem temp = this.mDataTable.getTemp(i7);
            if (temp == null) {
                break;
            }
            if (chunk2 == null || !chunk2.equalsDay(temp)) {
                chunk2 = new Chunk(temp);
                arrayList.add(chunk2);
            } else {
                chunk2.append(temp);
            }
        }
        ArrayList<Chunk> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        loop1:
        while (true) {
            chunk = null;
            while (it.hasNext()) {
                Chunk chunk3 = (Chunk) it.next();
                if (chunk == null || chunk3.count != 1 || !chunk.equalsMonth(chunk3)) {
                    if (chunk != null) {
                        arrayList2.add(chunk);
                    }
                    if (chunk3.count != 1 || chunk3.equalsDay(isoToday)) {
                        arrayList2.add(chunk3);
                    } else {
                        chunk = chunk3;
                    }
                } else {
                    chunk.append(chunk3);
                }
            }
            break loop1;
        }
        if (chunk != null) {
            arrayList2.add(chunk);
        }
        return arrayList2;
    }

    /* access modifiers changed from: private */
    /* renamed from: notifySwapDone */
    public void lambda$swap$7(long j2, DataTable dataTable, ClusterTable[] clusterTableArr, DefaultTable[] defaultTableArr, Closeable[] closeableArr, List<Closeable> list, MediaDataEntire.Candidates candidates) {
        boolean z;
        boolean z3;
        String str;
        List<Closeable> list2 = list;
        Blackboard blackboard = this.mBlackboard;
        if (blackboard == null || blackboard.read("shrink_animation_started") == null) {
            z = false;
        } else {
            z = true;
        }
        if (this.mRwLock.acquireWriteLock()) {
            try {
                DataTable dataTable2 = this.mDataTable;
                if (dataTable2 == null || !dataTable2.isPartialLoaded()) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                int i2 = this.mLoadedCount;
                DataTable dataTable3 = this.mDataTable;
                if (dataTable3 != null) {
                    str = dataTable3.getDataStamp();
                } else {
                    str = null;
                }
                this.mDataTable = dataTable;
                this.mDataCount = dataTable.getRealCount();
                ClusterTable[] clusterTableArr2 = this.mClusterTables;
                clusterTableArr2[0] = clusterTableArr[0];
                clusterTableArr2[1] = clusterTableArr[1];
                this.mExtraTables = defaultTableArr;
                this.mExtraIndexers = closeableArr;
                Log.d(this.TAG, "createRealRatioIndexer swap done. " + this.mExtraIndexers[0]);
                this.mLoadedCount = this.mDataTable.getLoadedCount();
                onSwapDone(candidates);
                int i7 = this.mLoadedCount - i2;
                if (!z3 || i7 <= 0) {
                    Log.d(this.TAG, "swap > notify " + toSimpleString() + ", CHANGE(0," + this.mLoadedCount + "), old=" + str + " +" + (System.currentTimeMillis() - j2));
                    notifyChanged();
                    this.mRwLock.releaseWriteLock();
                } else {
                    Log.d(this.TAG, "swap > notify " + toSimpleString() + ", INSERT(" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i7 + ") +" + (System.currentTimeMillis() - j2));
                    notifyDataRangeInserted(i2, i7);
                    this.mRwLock.releaseWriteLock();
                }
            } catch (NullPointerException e) {
                NullPointerException nullPointerException = e;
                Log.e((CharSequence) this.TAG, "swap > failed e=" + nullPointerException.getMessage(), (Throwable) nullPointerException);
            } catch (Throwable th) {
                Throwable th2 = th;
                this.mRwLock.releaseWriteLock();
                throw th2;
            }
        }
        if (z) {
            Log.st(this.TAG, "swap during shrink animation, give delay for close.");
            ThreadUtil.postOnUiThreadDelayed(new B(4, this, list2), 1000);
        } else {
            lambda$notifySwapDone$8(list2);
        }
        terminateSwapProcessing();
    }

    private boolean refreshOnResumeIfPause() {
        String str = (String) this.mBlackboard.read("lifecycle://last_activity_lifecycle");
        if (str == null || !str.equals("lifecycle://on_activity_pause")) {
            return false;
        }
        BlackboardUtils.publishRefreshOnResumeToAllActivities(true);
        Log.w(this.TAG, "skip in pause state. refresh on resume");
        return true;
    }

    private void removeInternal(int i2) {
        try {
            acquireReadLock("removeInternal");
            this.mDataTable.remove(i2);
        } finally {
            releaseReadLock("removeInternal");
        }
    }

    private void swapClusterTable(int i2, Cursor[] cursorArr, ClusterTable[] clusterTableArr, CountDownLatch countDownLatch) {
        if (cursorArr[i2 + 1] != null) {
            ClusterTable createClusterTable = createClusterTable(i2, cursorArr);
            clusterTableArr[i2] = createClusterTable;
            createClusterTable.init();
        }
        countDownLatch.countDown();
    }

    /* access modifiers changed from: private */
    /* renamed from: updateDataRange */
    public void lambda$updateDataRange$13(int i2, int i7) {
        if (ThreadUtil.isMainThread()) {
            notifyDataRangeInserted(i2, i7);
        } else {
            ThreadUtil.postOnUiThread(new D0(this, i2, i7));
        }
    }

    public boolean checkDataMismatching(Cursor[] cursorArr, DataTable dataTable, ClusterTable[] clusterTableArr, MediaDataEntire.Candidates candidates) {
        ClusterTable clusterTable = clusterTableArr[0];
        if (clusterTable == null || clusterTable.makeClusterIndex(dataTable.getLoadedCount())) {
            ClusterTable clusterTable2 = clusterTableArr[1];
            if (clusterTable2 == null || clusterTable2.makeClusterIndex(dataTable.getLoadedCount())) {
                return false;
            }
            handleDataMisMatching("month", clusterTableArr[1]);
            return true;
        }
        handleDataMisMatching("day", clusterTableArr[0]);
        return true;
    }

    public boolean compareDataTable(DataTable dataTable) {
        DataTable dataTable2 = this.mDataTable;
        if (dataTable2 == null || !dataTable2.equals(dataTable)) {
            return false;
        }
        return true;
    }

    public ClusterTable createClusterTable(int i2, Cursor[] cursorArr, DefaultTable.OnLoadDoneListener onLoadDoneListener) {
        return new ClusterTable(cursorArr[i2 + 1], onLoadDoneListener);
    }

    public DataTable createDataTable(Cursor cursor) {
        return new DataTable(cursor);
    }

    public void createExtraIndexers(Closeable[] closeableArr, int i2, ClusterTable[] clusterTableArr, DefaultTable[] defaultTableArr, DataTable dataTable) {
        createRealRatioIndexer(closeableArr, i2, clusterTableArr, defaultTableArr, dataTable);
        createSecondExtraIndexer(closeableArr, i2, defaultTableArr, dataTable);
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://event/dateTimeChanged", new x0(this, 0)));
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://event/TimelineDataDirty", new x0(this, 1)));
        arrayList.add(new SubscriberInfo(DataKey.CACHED_DATA_CURSOR(this.mLocationKey), new x0(this, 2)).setTriggerPreloadedData().setWorkingCurrent());
        if (useGmpOnly()) {
            arrayList.add(new SubscriberInfo("command://gmp/files/changed", new x0(this, 3)));
            arrayList.add(new SubscriberInfo("command://gmp/location/changed", new x0(this, 4)));
        }
        arrayList.add(new SubscriberInfo("debug://DumpRealRatioData", new x0(this, 5)).setWorkingCurrent());
        if (LocationKey.isQuickView(getLocationKey())) {
            subscribePppCompleted(arrayList);
        }
    }

    public ClusterIndexer getClusterIndexer(int i2) {
        return this.mDataTable.createClusterIndexer(i2);
    }

    public int getCount() {
        return this.mLoadedCount;
    }

    public String getDataHash() {
        DataTable dataTable = this.mDataTable;
        if (dataTable == null || dataTable.getDataStamp() == null) {
            return null;
        }
        return this.mDataTable.getDataStamp();
    }

    public int getExtraTableCount() {
        return 2;
    }

    public int getPreloadCount(Context context, int i2, int i7, int i8) {
        if (this.mTimelinePictures) {
            if (i7 == 1) {
                return 6;
            }
            try {
                return getGridCountInView(context, i2, i7, i8);
            } catch (Exception e) {
                a.r(e, new StringBuilder("getPreloadCount failed e="), this.TAG);
            }
        }
        return super.getPreloadCount(context, i2, i7, i8);
    }

    public int getRealCount() {
        return this.mDataCount;
    }

    public RealRatioIndexer getRealRatioIndexer() {
        return (RealRatioIndexer) this.mExtraIndexers[0];
    }

    public SpanIndexer getSpanIndexer() {
        return (SpanIndexer) this.mExtraIndexers[1];
    }

    public void handleDataMisMatching(String str, ClusterTable clusterTable) {
        String str2;
        StringCompat stringCompat = this.TAG;
        String l = C0212a.l("swap > fail. Data mismatching ", str);
        if (clusterTable != null) {
            str2 = clusterTable.getLog();
        } else {
            str2 = "";
        }
        Log.e((CharSequence) stringCompat, l, str2);
        if (this.mDataMisMatchingRetry > 0) {
            requestData(this.mLocationReference);
            this.mDataMisMatchingRetry--;
        }
        terminateSwapProcessing();
    }

    public void initExtraTable(Cursor[] cursorArr, CountDownLatch countDownLatch, MediaDataEntire.Candidates candidates) {
        int length = cursorArr.length;
        if (length > 1 && cursorArr[1] != null) {
            getThreadPool().submit(new y0(this, cursorArr, countDownLatch, 2));
        }
        if (length > 2 && cursorArr[2] != null) {
            getThreadPool().submit(new y0(this, cursorArr, countDownLatch, 3));
        }
        if (length > 3 && cursorArr[3] != null) {
            getThreadPool().submit(new y0(this, cursorArr, countDownLatch, 0));
        }
        if (length > 4 && cursorArr[4] != null) {
            getThreadPool().submit(new y0(this, cursorArr, countDownLatch, 1));
        }
    }

    public boolean isGroupByDate() {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            String argValue = ArgumentsUtil.getArgValue(this.mLocationReference, "mergedAlbumId");
            if (!TextUtils.isEmpty(argValue)) {
                return SortByType.isGroupByDate(argValue);
            }
        }
        return SortByType.isGroupByDate(ArgumentsUtil.getArgValue(this.mLocationReference, "id"));
    }

    public boolean isTimelineDisabled() {
        return SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equalsIgnoreCase(ArgumentsUtil.getArgValue(this.mLocationReference, "disableTimeline"));
    }

    public void notifyIndexingDone() {
        notifyChanged();
    }

    public synchronized void onCachedDataCursorChanged(Object obj, Bundle bundle) {
        boolean z = true;
        if (getCount() >= 1) {
            z = false;
        }
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "onCachedDataCursorChanged : " + z);
        if (z) {
            onDataCursorChanged(obj, bundle);
        }
    }

    public void onDataDirtyTimeline(Object obj, Bundle bundle) {
        if (this.mTimelinePictures) {
            this.mForceSwap = true;
        }
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "onDataDirtyTimeline : swap set as " + this.mForceSwap + " / " + this.mLocationKey);
    }

    public void onDateTimeChanged(Object obj, Bundle bundle) {
        boolean supportDayCluster = supportDayCluster();
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onDateTimeChanged {" + supportDayCluster + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mLoadedCount + "}");
        if (supportDayCluster && this.mLoadedCount > 0) {
            Optional.ofNullable(this.mClusterTables[0]).ifPresent(new C0596d(5));
            notifyChanged();
        }
    }

    public void onDestroy() {
        ClusterTable clusterTable;
        this.mLoadedCount = 0;
        this.mDataMisMatchingRetry = 3;
        Utils.closeSilently(this.mExtraIndexers[0]);
        this.mExtraIndexers[0] = null;
        Utils.closeSilently(this.mExtraTables[0]);
        this.mExtraTables[0] = null;
        Utils.closeSilently(this.mExtraIndexers[1]);
        this.mExtraIndexers[1] = null;
        Utils.closeSilently(this.mExtraTables[1]);
        this.mExtraTables[1] = null;
        if (this.mTimelinePictures && (clusterTable = this.mClusterTables[0]) != null) {
            PreferenceAnalytics.ClusterCount.setInteger(clusterTable.getRealCount());
        }
        Utils.closeSilently(this.mClusterTables[0]);
        ClusterTable[] clusterTableArr = this.mClusterTables;
        clusterTableArr[0] = null;
        Utils.closeSilently(clusterTableArr[1]);
        this.mClusterTables[1] = null;
        super.onDestroy();
    }

    public void onDumpRealRatioData(Object obj, Bundle bundle) {
        int min = Math.min(120, this.mDataCount);
        for (int i2 = 0; i2 < min; i2++) {
            MediaItem read = read(i2);
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, i2 + "] " + read);
        }
    }

    public void onFilesDataChanged(Object obj, Bundle bundle) {
        GmpCompat.GmpDataChangeDetails gmpDataChangeDetails = (GmpCompat.GmpDataChangeDetails) obj;
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onFilesDataChanged : " + gmpDataChangeDetails);
        if (gmpDataChangeDetails.isRemoved()) {
            requestData(this.mLocationReference);
        } else {
            Log.d(this.TAG, "ignore update/insert noti for location search pictures");
        }
    }

    public void onInitDone() {
        DataTable dataTable = this.mDataTable;
        if (dataTable != null) {
            this.mLoadedCount = dataTable.getLoadedCount();
            for (int i2 = 0; i2 < getExtraTableCount(); i2++) {
                Utils.closeSilently(this.mExtraIndexers[i2]);
            }
            Closeable[] closeableArr = new Closeable[getExtraTableCount()];
            this.mExtraIndexers = closeableArr;
            createExtraIndexers(closeableArr, this.mLoadedCount, this.mClusterTables, this.mExtraTables, this.mDataTable);
            Log.d(this.TAG, "createRealRatioIndexer init done. " + this.mExtraIndexers[0]);
            this.mBlackboard.publishIfEmpty("debug://TimeDoneFakeLoad", Long.valueOf(System.currentTimeMillis()));
        }
    }

    public void onLocationDataChanged(Object obj, Bundle bundle) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onLocationDataChanged : " + ((GmpCompat.GmpDataChangeDetails) obj));
        requestData(this.mLocationReference);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:26|27|28|29) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00c2, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        com.samsung.android.gallery.support.utils.Log.e(r2.TAG, "fail onNotifyFullLoaded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00d0, code lost:
        r2.releaseReadLock("onNotifyFullLoaded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d3, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x00c9 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onNotifyFullLoaded() {
        /*
            r8 = this;
            java.lang.String r1 = "onNotifyFullLoaded"
            r8.acquireReadLock(r1)     // Catch:{ NullPointerException -> 0x00c8, all -> 0x00c5 }
            com.samsung.android.gallery.module.dataset.tables.DataTable r0 = r8.mDataTable     // Catch:{ NullPointerException -> 0x00c8, all -> 0x00c5 }
            int r4 = r0.getLoadedCount()     // Catch:{ NullPointerException -> 0x00c8, all -> 0x00c5 }
            int r0 = r8.getExtraTableCount()     // Catch:{ NullPointerException -> 0x00c8, all -> 0x00c5 }
            java.io.Closeable[] r3 = new java.io.Closeable[r0]     // Catch:{ NullPointerException -> 0x00c8, all -> 0x00c5 }
            com.samsung.android.gallery.module.dataset.tables.ClusterTable[] r5 = r8.mClusterTables     // Catch:{ NullPointerException -> 0x00c8, all -> 0x00c5 }
            com.samsung.android.gallery.module.dataset.tables.DefaultTable[] r6 = r8.mExtraTables     // Catch:{ NullPointerException -> 0x00c8, all -> 0x00c5 }
            com.samsung.android.gallery.module.dataset.tables.DataTable r7 = r8.mDataTable     // Catch:{ NullPointerException -> 0x00c8, all -> 0x00c5 }
            r2 = r8
            r2.createExtraIndexers(r3, r4, r5, r6, r7)     // Catch:{ NullPointerException -> 0x00c9 }
            r2.releaseReadLock(r1)
            com.samsung.android.gallery.module.concurrent.RwLock r8 = r2.mRwLock
            boolean r8 = r8.acquireWriteLock()
            if (r8 == 0) goto L_0x00c1
            r8 = 0
            r0 = r8
        L_0x0028:
            int r1 = r2.getExtraTableCount()
            if (r0 >= r1) goto L_0x003f
            com.samsung.android.gallery.module.dataset.tables.DefaultTable[] r1 = r2.mExtraTables
            r1 = r1[r0]
            if (r1 == 0) goto L_0x0037
            r1.close()
        L_0x0037:
            com.samsung.android.gallery.module.dataset.tables.DefaultTable[] r1 = r2.mExtraTables
            r5 = 0
            r1[r0] = r5
            int r0 = r0 + 1
            goto L_0x0028
        L_0x003f:
            r2.mExtraIndexers = r3
            com.samsung.android.gallery.support.utils.StringCompat r0 = r2.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "createExtraIndexers full done = "
            r1.<init>(r3)
            java.io.Closeable[] r3 = r2.mExtraIndexers
            r8 = r3[r8]
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            com.samsung.android.gallery.support.utils.Log.d(r0, r8)
            com.samsung.android.gallery.support.blackboard.Blackboard r8 = r2.mBlackboard
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            java.lang.String r1 = "debug://TimeDoneFullLoad"
            r8.publishIfEmpty(r1, r0)
            int r8 = r2.mLoadedCount
            int r0 = r4 - r8
            java.lang.String r1 = "init > notify fullLoad. "
            if (r0 <= 0) goto L_0x009f
            r2.mLoadedCount = r4
            com.samsung.android.gallery.support.utils.StringCompat r3 = r2.TAG
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r1)
            java.lang.String r1 = r2.toSimpleString()
            r4.append(r1)
            java.lang.String r1 = ", INSERT("
            r4.append(r1)
            r4.append(r8)
            java.lang.String r1 = ","
            r4.append(r1)
            r4.append(r0)
            java.lang.String r1 = ")"
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.samsung.android.gallery.support.utils.Log.d(r3, r1)
            r2.lambda$updateDataRange$13(r8, r0)
            goto L_0x00bc
        L_0x009f:
            com.samsung.android.gallery.support.utils.StringCompat r8 = r2.TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
            java.lang.String r1 = r2.toSimpleString()
            r0.append(r1)
            java.lang.String r1 = ", UPDATE"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.d(r8, r0)
            r2.notifyChanged()
        L_0x00bc:
            com.samsung.android.gallery.module.concurrent.RwLock r8 = r2.mRwLock
            r8.releaseWriteLock()
        L_0x00c1:
            return
        L_0x00c2:
            r0 = move-exception
        L_0x00c3:
            r8 = r0
            goto L_0x00d4
        L_0x00c5:
            r0 = move-exception
            r2 = r8
            goto L_0x00c3
        L_0x00c8:
            r2 = r8
        L_0x00c9:
            com.samsung.android.gallery.support.utils.StringCompat r8 = r2.TAG     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = "fail onNotifyFullLoaded"
            com.samsung.android.gallery.support.utils.Log.e(r8, r0)     // Catch:{ all -> 0x00c2 }
            r2.releaseReadLock(r1)
            return
        L_0x00d4:
            r2.releaseReadLock(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataTimeline.onNotifyFullLoaded():void");
    }

    public void prepareSwap(MediaDataEntire.Candidates candidates, DataTable dataTable, int i2) {
        if (this.mDataTable != null && LocationKey.isSearchKeyword(getLocationKey())) {
            long currentTimeMillis = System.currentTimeMillis();
            int min = Math.min(128, dataTable.getLoadedCount());
            for (int i7 = 0; i7 < min; i7++) {
                dataTable.loadAndGet(i7);
            }
            StringCompat stringCompat = this.TAG;
            C0212a.z(new Object[]{getLocationKey(), Integer.valueOf(min), Integer.valueOf(dataTable.getLoadedCount()), Long.valueOf(currentTimeMillis)}, new StringBuilder("prepareSwap"), stringCompat);
        }
    }

    public synchronized void reInit(String str) {
        Utils.closeSilently(this.mClusterTables[0]);
        ClusterTable[] clusterTableArr = this.mClusterTables;
        clusterTableArr[0] = null;
        Utils.closeSilently(clusterTableArr[1]);
        this.mClusterTables[1] = null;
        super.reInit(str);
    }

    public void removeItemAt(int i2) {
        if (i2 < this.mDataCount && this.mDataTable != null) {
            removeInternal(i2);
            this.mLoadedCount--;
        }
    }

    public void replaceItemAt(int i2, MediaItem mediaItem) {
        if (i2 < this.mDataCount && this.mDataTable != null) {
            try {
                acquireReadLock("replaceItemAt");
                this.mDataTable.set(i2, mediaItem);
            } finally {
                releaseReadLock("replaceItemAt");
            }
        }
    }

    public void setLocationKey(String str) {
        super.setLocationKey(str);
    }

    public void setPartialLoaded(DataTable dataTable) {
        if (this.mTimelinePictures && dataTable != null && dataTable.getRealCount() == 3000) {
            dataTable.setPartialLoaded(true);
        }
    }

    public boolean supportDayCluster() {
        if (this.mTimelinePictures) {
            return !isTimelineDisabled();
        }
        if (!isTimelineDisabled() && isDayCursorAvailable() && supportTimeline(this.mLocationKey)) {
            return true;
        }
        return false;
    }

    public boolean supportMonthCluster() {
        if (this.mTimelinePictures) {
            return !isTimelineDisabled();
        }
        if (!isTimelineDisabled() && isMonthCursorAvailable() && supportTimeline(this.mLocationKey)) {
            return true;
        }
        return false;
    }

    public boolean supportMonthXsCluster() {
        if (!PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL) {
            return false;
        }
        if (LocationKey.isTimeline(this.mLocationKey) || LocationKey.isAlbumPictures(this.mLocationKey) || LocationKey.isVirtualPictures(this.mLocationKey)) {
            return true;
        }
        return false;
    }

    public boolean supportTimeline(String str) {
        if (LocationKey.isAlbumPictures(str)) {
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.AlbumTimeline) || isGroupByDate()) {
                return true;
            }
            return false;
        } else if (LocationKey.isSearchPictures(str)) {
            return true;
        } else {
            if (LocationKey.isVideoPictures(str) || LocationKey.isFavoritePictures(str)) {
                return isGroupByDate();
            }
            if ((!PocFeatures.SUPPORT_PRIVATE_ALBUM || !LocationKey.isPrivateAlbum(str)) && !LocationKey.isAlbumViewPictures(str) && !LocationKey.isRepairPictures(str) && !LocationKey.isMapCluster(str)) {
                return false;
            }
            return true;
        }
    }

    public boolean supportYearCluster() {
        if (!PreferenceFeatures.OneUi30.YEAR_CLUSTERING || !this.mTimelinePictures || isTimelineDisabled() || !TextUtils.isEmpty(ArgumentsUtil.getArgValue(this.mLocationReference, "filterMediaType", (String) null))) {
            return false;
        }
        return true;
    }

    public final void swap(Cursor[] cursorArr) {
        Cursor[] cursorArr2 = cursorArr;
        if (!beginSwapProcessing()) {
            Log.w(this.TAG, "fail begin swap");
            return;
        }
        MediaDataEntire.Candidates candidates = new MediaDataEntire.Candidates();
        preprocessCursors(cursorArr2, candidates);
        if (cursorArr2[0] == null) {
            Log.e(this.TAG, "swap > skip(null cursor)");
            terminateSwapProcessing();
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        DataTable createDataTable = createDataTable(cursorArr2[0]);
        if (this.mForceSwap) {
            Log.d(this.TAG, "swap > force by flag");
            this.mForceSwap = false;
            clearPartialLoaded(this.mDataTable);
        } else {
            DataTable dataTable = this.mDataTable;
            if (dataTable != null && dataTable.hasPostProcessing()) {
                Log.w(this.TAG, "swap > force by PPP");
                clearPartialLoaded(this.mDataTable);
            } else if (compareDataTable(createDataTable)) {
                clearPartialLoaded(this.mDataTable);
                closeCursors(cursorArr);
                if (this.mDataTable == null) {
                    Log.e(this.TAG, "swap > skip(null data table)");
                } else if (createDataTable.isVideoTouched()) {
                    StringCompat stringCompat = this.TAG;
                    Log.i(stringCompat, "swap > skip(only video touched) " + toSimpleString());
                } else {
                    StringCompat stringCompat2 = this.TAG;
                    Log.i(stringCompat2, "swap > skip(same data) " + toSimpleString());
                    refreshOnResumeIfPause();
                }
                terminateSwapProcessing();
                return;
            } else {
                Log.i(this.TAG, "swap > start");
            }
        }
        this.mBlackboard.publish("command://ForceRefreshOnResume", Boolean.FALSE);
        ClusterTable[] clusterTableArr = new ClusterTable[2];
        DefaultTable[] defaultTableArr = new DefaultTable[getExtraTableCount()];
        int length = cursorArr2.length;
        CountDownLatch countDownLatch = new CountDownLatch(getLockSize(cursorArr));
        swapClusterTables(cursorArr2, clusterTableArr, defaultTableArr, length, countDownLatch);
        try {
            createDataTable.fillDataRecords(this.mDataTable);
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "swap > fill data failed", (Throwable) e);
        }
        countDownLatch.countDown();
        try {
            if (!countDownLatch.await(30, TimeUnit.SECONDS)) {
                if (getDataVersion() == 0) {
                    Log.e(this.TAG, "swap > skip. ignore lock fail due to app destroyed");
                    terminateSwapProcessing();
                    return;
                } else if (refreshOnResumeIfPause()) {
                    Log.w(this.TAG, "swap > skip. fail in pause, refresh on resume. close cursor on next pause");
                    subscribeInstant("lifecycle://on_activity_pause", new z0(this, cursorArr2));
                    terminateSwapProcessing();
                    return;
                } else {
                    new InternalException("fail to init data tables in 30sec").post();
                    terminateSwapProcessing();
                    return;
                }
            }
        } catch (InterruptedException e7) {
            Log.e((CharSequence) this.TAG, "swap > latch interrupted", (Throwable) e7);
        }
        if (!checkDataMismatching(cursorArr2, createDataTable, clusterTableArr, candidates)) {
            StringCompat stringCompat3 = this.TAG;
            Log.d(stringCompat3, "swap > completed +" + (System.currentTimeMillis() - currentTimeMillis));
            DataTable dataTable2 = createDataTable;
            ArrayList arrayList = new ArrayList();
            DataTable dataTable3 = this.mDataTable;
            ClusterTable[] clusterTableArr2 = this.mClusterTables;
            arrayList.addAll(Arrays.asList(new DefaultTable[]{dataTable3, clusterTableArr2[0], clusterTableArr2[1]}));
            arrayList.addAll(Arrays.asList(this.mExtraTables));
            arrayList.addAll(Arrays.asList(this.mExtraIndexers));
            Closeable[] closeableArr = new Closeable[getExtraTableCount()];
            ClusterTable[] clusterTableArr3 = clusterTableArr;
            DefaultTable[] defaultTableArr2 = defaultTableArr;
            ClusterTable[] clusterTableArr4 = clusterTableArr3;
            createExtraIndexers(closeableArr, dataTable2.getLoadedCount(), clusterTableArr4, defaultTableArr2, dataTable2);
            ClusterTable[] clusterTableArr5 = clusterTableArr4;
            DefaultTable[] defaultTableArr3 = defaultTableArr2;
            prepareSwap(candidates, dataTable2, this.mDataTable.getLastUsedIndex());
            Closeable[] closeableArr2 = closeableArr;
            long j2 = currentTimeMillis;
            runOnUiThread(new A0(this, j2, dataTable2, clusterTableArr5, defaultTableArr3, closeableArr2, arrayList, candidates));
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.Serializable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void swapClusterTables(android.database.Cursor[] r8, com.samsung.android.gallery.module.dataset.tables.ClusterTable[] r9, com.samsung.android.gallery.module.dataset.tables.DefaultTable[] r10, int r11, java.util.concurrent.CountDownLatch r12) {
        /*
            r7 = this;
            r0 = 1
            if (r11 <= r0) goto L_0x0018
            r0 = r8[r0]
            if (r0 == 0) goto L_0x0018
            com.samsung.android.gallery.support.threadpool.ThreadPool r6 = r7.getThreadPool()
            com.samsung.android.gallery.module.dataset.B0 r0 = new com.samsung.android.gallery.module.dataset.B0
            r5 = 0
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r12
            r0.<init>(r1, r2, r3, r4, r5)
            r6.submit(r0)
        L_0x0018:
            r0 = 2
            if (r11 <= r0) goto L_0x0030
            r0 = r8[r0]
            if (r0 == 0) goto L_0x0030
            com.samsung.android.gallery.support.threadpool.ThreadPool r6 = r7.getThreadPool()
            com.samsung.android.gallery.module.dataset.B0 r0 = new com.samsung.android.gallery.module.dataset.B0
            r5 = 1
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r12
            r0.<init>(r1, r2, r3, r4, r5)
            r6.submit(r0)
        L_0x0030:
            r0 = 3
            if (r11 <= r0) goto L_0x0048
            r0 = r8[r0]
            if (r0 == 0) goto L_0x0048
            com.samsung.android.gallery.support.threadpool.ThreadPool r6 = r7.getThreadPool()
            com.samsung.android.gallery.module.dataset.q0 r0 = new com.samsung.android.gallery.module.dataset.q0
            r1 = 1
            r3 = r7
            r2 = r8
            r4 = r10
            r5 = r12
            r0.<init>(r1, r2, r3, r4, r5)
            r6.submit(r0)
        L_0x0048:
            r0 = 4
            if (r11 <= r0) goto L_0x005b
            r0 = r8[r0]
            if (r0 == 0) goto L_0x005b
            com.samsung.android.gallery.support.threadpool.ThreadPool r0 = r7.getThreadPool()
            com.samsung.android.gallery.module.dataset.u r1 = new com.samsung.android.gallery.module.dataset.u
            r1.<init>(r10, r8, r12)
            r0.submit(r1)
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.MediaDataTimeline.swapClusterTables(android.database.Cursor[], com.samsung.android.gallery.module.dataset.tables.ClusterTable[], com.samsung.android.gallery.module.dataset.tables.DefaultTable[], int, java.util.concurrent.CountDownLatch):void");
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.toString());
        sb2.append(" ");
        return C0212a.p(sb2, getTableLog(), "]");
    }

    private RealRatioIndexer createRealRatioIndexer(int i2, ClusterTable[] clusterTableArr, RealRatioTable realRatioTable, DataTable dataTable) {
        if (!ENABLE_REAL_RATIO) {
            Log.d(this.TAG, "not support realratio");
            return null;
        }
        ConcurrentHashMap<Integer, ClusterItem> concurrentHashMap = new ConcurrentHashMap<>();
        boolean isTimeline = LocationKey.isTimeline(getLocationKey());
        int i7 = 0;
        ClusterTable clusterTable = clusterTableArr[0];
        if (clusterTable != null) {
            concurrentHashMap = clusterTable.getClusterIndexer(i2).getTimelineItemMap();
            i7 = clusterTableArr[0].getLoadedCount();
        } else if (isTimeline) {
            ClusterIndexer createClusterIndexer = dataTable.createClusterIndexer(0);
            concurrentHashMap = createClusterIndexer.getTimelineItemMap();
            i7 = createClusterIndexer.getCount();
        }
        if (realRatioTable != null) {
            return new RealRatioIndexerCompat(realRatioTable, concurrentHashMap, i7, i2);
        }
        return new RealRatioIndexer(dataTable, concurrentHashMap, i7, i2);
    }

    public ClusterTable createClusterTable(int i2, Cursor[] cursorArr) {
        return new ClusterTable(cursorArr[i2 + 1]);
    }

    public ClusterTable getClusterTable(int i2) {
        if (i2 == 0) {
            return this.mClusterTables[0];
        }
        if (i2 == 1) {
            return this.mClusterTables[1];
        }
        throw new AssertionError(C0086a.i(i2, "wrong idx : "));
    }

    public void onSwapDone(MediaDataEntire.Candidates candidates) {
    }

    public void createSecondExtraIndexer(Closeable[] closeableArr, int i2, DefaultTable[] defaultTableArr, DataTable dataTable) {
    }
}
