package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import A.a;
import E3.f;
import T8.C0578a;
import android.database.Cursor;
import android.util.Pair;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.module.dataset.tables.ClusterIndexer;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.module.dataset.tables.SpecProvider;
import com.samsung.android.gallery.module.list.YearQueryCache;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.o3dp.lib3dphotography.i;
import com.samsung.scsp.media.api.d;
import e5.C0451a;
import e6.C0453a;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class YearQueryCluster implements Cluster {
    private final Object YEAR_CURSOR_LOCK = new Object();
    private volatile boolean mClosed = false;
    protected int mCount;
    private final String mDataHash;
    private long[] mDateTaken;
    private final HashMap<Integer, ArrayList<Integer>> mFilteredDataPositionList = new HashMap<>();
    private final boolean mFullyLoaded;
    private int mGridSize = -1;
    private HashMap<Integer, Integer> mItemHeightList;
    private int mMaxWidth;
    private int mRowCount = -1;
    private int[] mScrollIndex;
    private String[] mScrollIndexDates;
    private ScrollText[] mScrollIndexTag;
    private ArrayList<Integer> mTimelineIdxList;
    private ConcurrentHashMap<Integer, ClusterItem> mTimelineItemMapReadOnly;
    private int mTotalItemCount;
    private final ArrayList<Integer> mYearCountList = new ArrayList<>();
    private final HashMap<Integer, Integer> mYearCountMap = new HashMap<>();
    private volatile Cursor mYearCursor;
    Runnable onLoadDone;

    public YearQueryCluster(ClusterIndexer clusterIndexer, int i2, SpecProvider specProvider, boolean z, String str, Runnable runnable) {
        int widthSpec = specProvider.getWidthSpec(specProvider.getYearColumnCount());
        this.mFullyLoaded = z;
        this.mDataHash = str;
        this.onLoadDone = runnable;
        init(clusterIndexer, i2, widthSpec);
    }

    private void doFiltering() {
        int min = Math.min(getViewCount(), this.mYearCountList.size() * 2);
        for (int i2 = 1; i2 < min; i2 += 2) {
            Pair<Integer, Integer> clusterDataRange = getClusterDataRange(i2);
            filterDataPositions(i2, ((Integer) clusterDataRange.first).intValue(), ((Integer) clusterDataRange.second).intValue());
        }
    }

    private void filterDataPositions(int i2, int i7, int i8) {
        ArrayList arrayList = new ArrayList();
        int intValue = this.mYearCountList.get(i2 / 2).intValue();
        for (int i10 = i7; i10 < (i7 + intValue) - 1; i10++) {
            arrayList.add(Integer.valueOf(i10));
        }
        arrayList.add(Integer.valueOf(i8));
        this.mFilteredDataPositionList.put(Integer.valueOf(i2), arrayList);
    }

    private Pair<Integer, Integer> getClusterDataRange(int i2) {
        int i7;
        int i8 = i2 / 2;
        int intValue = this.mTimelineIdxList.get(i8).intValue() - i8;
        int i10 = i8 + 1;
        if (i10 == this.mTimelineIdxList.size()) {
            i7 = this.mTotalItemCount - 1;
        } else {
            i7 = this.mTimelineIdxList.get(i10).intValue() - (i8 + 2);
        }
        return new Pair<>(Integer.valueOf(intValue), Integer.valueOf(i7));
    }

    private int getDataPositionInternal(int i2, int i7) {
        Object obj;
        ArrayList arrayList = this.mFilteredDataPositionList.get(Integer.valueOf(i2));
        if (arrayList == null) {
            return 0;
        }
        if (arrayList.size() <= i7) {
            obj = C0212a.i(arrayList, 1);
        } else {
            obj = arrayList.get(Math.max(0, i7));
        }
        return ((Integer) obj).intValue();
    }

    private int getItemHeightInternal(int i2, int i7) {
        if (i2 < 0 || i2 % 2 == 0) {
            return 0;
        }
        synchronized (this.YEAR_CURSOR_LOCK) {
            try {
                if (this.mYearCursor != null) {
                    if (!this.mClosed) {
                        ClusterItem clusterItem = getClusterItem(i2 - 1);
                        if (clusterItem == null) {
                            Log.d("YearQueryCluster", "getItemHeightInternal {" + i2 + "} skip. no data");
                            return 0;
                        }
                        int rowCount = (getRowCount(i7, getYearCount(UnsafeCast.toInt(clusterItem.getDateRaw().split("-")[0]))) * this.mMaxWidth) / i7;
                        this.mItemHeightList.put(Integer.valueOf(i2), Integer.valueOf(rowCount));
                        return rowCount;
                    }
                }
                Log.d("YearQueryCluster", "getItemHeightInternal {" + i2 + "} skip. no cursor");
                return 0;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private int getRowCount(int i2, int i7) {
        int i8 = 0;
        if (i7 < 0) {
            return 0;
        }
        int i10 = i7 / i2;
        if (i7 % i2 != 0) {
            i8 = 1;
        }
        return i10 + i8;
    }

    private int getTimelineDelta(int i2) {
        int binarySearch = Arrays.binarySearch(this.mScrollIndex, i2);
        if (binarySearch < 0) {
            return (-binarySearch) - 1;
        }
        return 0;
    }

    private int getViewPositionFromCoordinates(float f, float f5, int i2) {
        int i7;
        float f8 = (float) (this.mMaxWidth / i2);
        float f10 = (f / f8) + ((f5 / f8) * ((float) i2));
        if (f % f8 == 0.0f) {
            i7 = 0;
        } else {
            i7 = 1;
        }
        return (int) (f10 + ((float) i7));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0065, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized int getYearCount(int r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            android.database.Cursor r0 = r6.mYearCursor     // Catch:{ all -> 0x000f }
            r1 = 0
            if (r0 != 0) goto L_0x0011
            java.lang.String r7 = "YearQueryCluster"
            java.lang.String r0 = "getYearCount failed"
            com.samsung.android.gallery.support.utils.Log.e(r7, r0)     // Catch:{ all -> 0x000f }
            monitor-exit(r6)
            return r1
        L_0x000f:
            r7 = move-exception
            goto L_0x0066
        L_0x0011:
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r0 = r6.mYearCountMap     // Catch:{ all -> 0x000f }
            int r0 = r0.size()     // Catch:{ all -> 0x000f }
            if (r0 != 0) goto L_0x0052
            android.database.Cursor r0 = r6.mYearCursor     // Catch:{ all -> 0x000f }
            java.lang.String r2 = "__year"
            int r0 = r0.getColumnIndex(r2)     // Catch:{ all -> 0x000f }
            android.database.Cursor r2 = r6.mYearCursor     // Catch:{ all -> 0x000f }
            java.lang.String r3 = "__count"
            int r2 = r2.getColumnIndex(r3)     // Catch:{ all -> 0x000f }
            android.database.Cursor r3 = r6.mYearCursor     // Catch:{ all -> 0x000f }
            boolean r3 = r3.moveToFirst()     // Catch:{ all -> 0x000f }
            if (r3 == 0) goto L_0x0052
        L_0x0031:
            android.database.Cursor r3 = r6.mYearCursor     // Catch:{ all -> 0x000f }
            int r3 = r3.getInt(r0)     // Catch:{ all -> 0x000f }
            android.database.Cursor r4 = r6.mYearCursor     // Catch:{ all -> 0x000f }
            int r4 = r4.getInt(r2)     // Catch:{ all -> 0x000f }
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r5 = r6.mYearCountMap     // Catch:{ all -> 0x000f }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x000f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x000f }
            r5.put(r3, r4)     // Catch:{ all -> 0x000f }
            android.database.Cursor r3 = r6.mYearCursor     // Catch:{ all -> 0x000f }
            boolean r3 = r3.moveToNext()     // Catch:{ all -> 0x000f }
            if (r3 != 0) goto L_0x0031
        L_0x0052:
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r0 = r6.mYearCountMap     // Catch:{ all -> 0x000f }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x000f }
            java.lang.Object r7 = r0.get(r7)     // Catch:{ all -> 0x000f }
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ all -> 0x000f }
            if (r7 == 0) goto L_0x0064
            int r1 = r7.intValue()     // Catch:{ all -> 0x000f }
        L_0x0064:
            monitor-exit(r6)
            return r1
        L_0x0066:
            monitor-exit(r6)     // Catch:{ all -> 0x000f }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.pictures.adapter.YearQueryCluster.getYearCount(int):int");
    }

    private void init(ClusterIndexer clusterIndexer, int i2, int i7) {
        this.mScrollIndex = clusterIndexer.getScrollIndex();
        this.mScrollIndexTag = clusterIndexer.getScrollIndexTag();
        this.mDateTaken = clusterIndexer.getDateTaken();
        this.mTimelineIdxList = clusterIndexer.getTimelineIdxList();
        this.mTimelineItemMapReadOnly = clusterIndexer.getTimelineItemMap();
        this.mCount = clusterIndexer.getCount();
        this.mTotalItemCount = i2;
        this.mMaxWidth = i7;
        this.mItemHeightList = new HashMap<>();
        SimpleThreadPool.getInstance().execute(new C0451a(11, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getMaxScroll$4(int i2, Integer num, ArrayList arrayList) {
        this.mRowCount += getRowCount(i2, arrayList.size());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$getScrollIndexDates$3(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$1(YearQueryCache yearQueryCache) {
        yearQueryCache.sync(this.mDataHash, this.onLoadDone);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$2() {
        synchronized (this.YEAR_CURSOR_LOCK) {
            try {
                if (this.mYearCursor == null) {
                    YearQueryCache instance = YearQueryCache.getInstance();
                    synchronized (YearQueryCache.LOCK) {
                        this.mYearCursor = instance.get(this.mFullyLoaded, this.mTimelineIdxList.size());
                        if (this.mYearCursor == null) {
                            this.mTimelineItemMapReadOnly.values().forEach(new C0453a(6));
                            SimpleThreadPool.getInstance().execute(new i(14, this, instance));
                        }
                    }
                }
                if (!this.mClosed) {
                    if (this.mYearCursor != null && this.mYearCursor.moveToFirst()) {
                        int columnIndex = this.mYearCursor.getColumnIndex("__year");
                        int columnIndex2 = this.mYearCursor.getColumnIndex("__count");
                        do {
                            int i2 = this.mYearCursor.getInt(columnIndex);
                            int i7 = this.mYearCursor.getInt(columnIndex2);
                            this.mYearCountList.add(Integer.valueOf(i7));
                            this.mYearCountMap.put(Integer.valueOf(i2), Integer.valueOf(i7));
                            if (this.mClosed) {
                                break;
                            }
                        } while (!this.mYearCursor.moveToNext());
                    }
                    if (!this.mClosed) {
                        doFiltering();
                    }
                }
            } catch (Error | Exception e) {
                if (!this.mClosed) {
                    throw e;
                }
            } catch (Throwable th) {
                throw th;
            }
            this.YEAR_CURSOR_LOCK.notifyAll();
        }
    }

    public void clear() {
        this.mScrollIndexTag = null;
        HashMap<Integer, Integer> hashMap = this.mItemHeightList;
        if (hashMap != null) {
            hashMap.clear();
        }
        synchronized (this.YEAR_CURSOR_LOCK) {
            try {
                if (this.mYearCursor != null) {
                    this.mYearCursor.close();
                    this.mYearCursor = null;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.mClosed = true;
    }

    public ClusterItem getClusterItem(int i2) {
        return this.mTimelineItemMapReadOnly.get(Integer.valueOf(i2));
    }

    public int getColumnSpan(int i2, int i7) {
        return this.mMaxWidth;
    }

    public int getCount() {
        return this.mCount;
    }

    public int getDataPosition(int i2) {
        int timelineDelta = i2 - getTimelineDelta(i2);
        if (timelineDelta >= 0 || i2 < 0) {
            return timelineDelta;
        }
        StringBuilder h5 = a.h(i2, timelineDelta, "getDataPosition {view=", ",data=", ",length=");
        h5.append(this.mScrollIndex.length);
        h5.append("}");
        Log.e("YearQueryCluster", h5.toString());
        throw new AssertionError("DATA INVALID. assert!!");
    }

    public ArrayList<Integer> getDataPositionList(int i2) {
        return this.mFilteredDataPositionList.get(Integer.valueOf(i2));
    }

    public int getDividerIndex(long j2) {
        int binarySearch = Arrays.binarySearch(this.mDateTaken, j2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        int length = (this.mDateTaken.length - 1) - binarySearch;
        return length < 0 ? length : length * 2;
    }

    public ArrayList<Pair<String, Integer>> getDividerScroll(int i2, int i7, int i8, int i10, int i11) {
        int i12;
        int yearInt;
        ArrayList<Pair<String, Integer>> arrayList = new ArrayList<>();
        int i13 = -1;
        for (int i14 = 0; i14 < this.mScrollIndex.length; i14++) {
            int itemHeight = getItemHeight((i14 * 2) - 1, i2) + i11;
            ClusterItem clusterItem = this.mTimelineItemMapReadOnly.get(Integer.valueOf(this.mScrollIndex[i14]));
            if (!(clusterItem == null || (yearInt = TimeUtil.getYearInt(clusterItem.getDateTaken())) == i13)) {
                arrayList.add(new Pair(TimeUtil.getYearString(clusterItem.getDateTaken()), Integer.valueOf(itemHeight)));
                i13 = yearInt;
            }
            if (i14 == 0) {
                i12 = i10;
            } else {
                i12 = i8;
            }
            i11 = i12 + itemHeight;
        }
        return arrayList;
    }

    public ArrayList<Integer> getDividers() {
        return this.mTimelineIdxList;
    }

    public int getExtraOffset(int i2, int i7, int i8) {
        return -1;
    }

    public int getItemHeight(int i2, int i7) {
        Integer num = this.mItemHeightList.get(Integer.valueOf(i2));
        if (num == null) {
            return getItemHeightInternal(i2, i7);
        }
        return num.intValue();
    }

    public int getItemViewType(int i2) {
        if (!isDivider(i2)) {
            return 4;
        }
        if (i2 == 0) {
            return -1;
        }
        return -2;
    }

    public int getMaxScroll(int i2, float f, int i7, int i8) {
        if (i2 != this.mGridSize) {
            this.mRowCount = 0;
            this.mFilteredDataPositionList.forEach(new f(this, i2, 1));
            this.mGridSize = i2;
        }
        int i10 = this.mRowCount;
        if (i10 <= 0) {
            return -1;
        }
        int size = (this.mTimelineIdxList.size() * i7) + ((int) (((float) i10) * f));
        if (i8 != 0) {
            return (i8 - i7) + size;
        }
        return size;
    }

    public int getScrollIndex(int i2) {
        int binarySearch = Arrays.binarySearch(this.mScrollIndex, i2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        return Math.max(binarySearch, 0);
    }

    public String[] getScrollIndexDates() {
        if (this.mScrollIndexDates == null) {
            this.mScrollIndexDates = (String[]) Arrays.stream(this.mScrollIndexTag).map(new d(13)).toArray(new C0578a(16));
        }
        return this.mScrollIndexDates;
    }

    public ScrollText getScrollText(int i2) {
        ScrollText[] scrollTextArr;
        int binarySearch = Arrays.binarySearch(this.mScrollIndex, i2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        if (binarySearch < 0 || (scrollTextArr = this.mScrollIndexTag) == null) {
            return null;
        }
        return scrollTextArr[binarySearch];
    }

    public SpanInfo getSpanInfo(int i2, int i7) {
        return new SpanInfo(i2 + 1, 0, getItemViewType(i2));
    }

    public int getStartSpan(int i2, int i7) {
        return 0;
    }

    public int getViewCount() {
        return this.mTimelineIdxList.size() * 2;
    }

    public int getViewPosition(int i2) {
        a.k(i2, "getViewPosition : ", "YearQueryCluster");
        Iterator<Integer> it = this.mTimelineIdxList.iterator();
        int i7 = 0;
        while (it.hasNext() && i2 >= it.next().intValue() - i7) {
            i7++;
        }
        return ((i7 - 1) * 2) + 1;
    }

    public ArrayList<MediaItem> getYearItemList(int i2) {
        long j2;
        ArrayList<MediaItem> arrayList;
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13 = i2;
        synchronized (this.YEAR_CURSOR_LOCK) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                ArrayList<MediaItem> arrayList2 = new ArrayList<>();
                if (this.mYearCursor == null || this.mClosed) {
                    ArrayList<MediaItem> arrayList3 = arrayList2;
                    Log.d("YearQueryCluster", "getYearItemList fail. " + i13 + "");
                    return arrayList3;
                }
                if (i13 > 9999) {
                    Log.w("YearQueryCluster", "getYearItemList with wrong year " + i13);
                    i13 = 9999;
                }
                if (this.mYearCursor != null) {
                    int columnIndex = this.mYearCursor.getColumnIndex("__year");
                    int columnIndex2 = this.mYearCursor.getColumnIndex("__count");
                    int columnIndex3 = this.mYearCursor.getColumnIndex("__absID");
                    int columnIndex4 = this.mYearCursor.getColumnIndex("__absPath");
                    int columnIndex5 = this.mYearCursor.getColumnIndex("__width");
                    int columnIndex6 = this.mYearCursor.getColumnIndex("__height");
                    int columnIndex7 = this.mYearCursor.getColumnIndex("__orientation");
                    int columnIndex8 = this.mYearCursor.getColumnIndex("__mimeType");
                    int columnIndex9 = this.mYearCursor.getColumnIndex("__dateModified");
                    j2 = currentTimeMillis;
                    int columnIndex10 = this.mYearCursor.getColumnIndex("__mediaType");
                    int columnIndex11 = this.mYearCursor.getColumnIndex("__storageType");
                    ArrayList<MediaItem> arrayList4 = arrayList2;
                    int columnIndex12 = this.mYearCursor.getColumnIndex("__size");
                    int columnIndex13 = this.mYearCursor.getColumnIndex("__dateTaken");
                    if (this.mYearCursor.moveToFirst()) {
                        while (true) {
                            int i14 = this.mYearCursor.getInt(columnIndex);
                            int i15 = columnIndex;
                            int i16 = this.mYearCursor.getInt(columnIndex2);
                            if (i14 != i13) {
                                i7 = columnIndex2;
                                i10 = columnIndex10;
                                i11 = columnIndex11;
                                i8 = columnIndex3;
                                i12 = columnIndex12;
                            } else {
                                i7 = columnIndex2;
                                int i17 = i14;
                                String[] split = this.mYearCursor.getString(columnIndex3).split("\\|");
                                i8 = columnIndex3;
                                String[] split2 = this.mYearCursor.getString(columnIndex4).split("\\|");
                                String[] split3 = this.mYearCursor.getString(columnIndex5).split("\\|");
                                String[] split4 = this.mYearCursor.getString(columnIndex6).split("\\|");
                                String[] split5 = this.mYearCursor.getString(columnIndex7).split("\\|");
                                String[] split6 = this.mYearCursor.getString(columnIndex9).split("\\|");
                                String[] split7 = this.mYearCursor.getString(columnIndex10).split("\\|");
                                i10 = columnIndex10;
                                String[] split8 = this.mYearCursor.getString(columnIndex11).split("\\|");
                                String[] split9 = this.mYearCursor.getString(columnIndex8).split("\\|");
                                i11 = columnIndex11;
                                i12 = columnIndex12;
                                String[] split10 = this.mYearCursor.getString(i12).split("\\|");
                                String[] split11 = this.mYearCursor.getString(columnIndex13).split("\\|");
                                int i18 = 0;
                                while (i18 < i16) {
                                    int i19 = i16;
                                    MediaItem create = MediaItemBuilder.create(UnsafeCast.toLong(split[i18]), split2[i18], UnsafeCast.toInt(split3[i18]), UnsafeCast.toInt(split4[i18]), UnsafeCast.toInt(split5[i18]), UnsafeCast.toLong(split6[i18]), i17, UnsafeCast.toInt(split8[i18]), UnsafeCast.toInt(split7[i18]), split9[i18], UnsafeCast.toLong(split11[i18]), UnsafeCast.toLong(split10[i18]));
                                    String[] strArr = split11;
                                    ArrayList<MediaItem> arrayList5 = arrayList4;
                                    arrayList5.add(create);
                                    i18++;
                                    arrayList4 = arrayList5;
                                    split11 = strArr;
                                    i16 = i19;
                                }
                            }
                            arrayList = arrayList4;
                            if (!this.mYearCursor.moveToNext()) {
                                break;
                            }
                            arrayList4 = arrayList;
                            columnIndex12 = i12;
                            columnIndex = i15;
                            columnIndex2 = i7;
                            columnIndex3 = i8;
                            columnIndex10 = i10;
                            columnIndex11 = i11;
                        }
                    } else {
                        arrayList = arrayList4;
                    }
                } else {
                    j2 = currentTimeMillis;
                    arrayList = arrayList2;
                }
                Log.d("YearQueryCluster", "getYearItemList {" + i13 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + arrayList.size() + "} +" + (System.currentTimeMillis() - j2));
                return arrayList;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean isDivider(int i2) {
        if (Arrays.binarySearch(this.mScrollIndex, i2) >= 0) {
            return true;
        }
        return false;
    }

    public void recalculatePosition(int i2) {
        this.mMaxWidth = i2;
        HashMap<Integer, Integer> hashMap = this.mItemHeightList;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public int getDividerIndex(int i2) {
        int binarySearch = Arrays.binarySearch(this.mScrollIndex, i2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        if (binarySearch < 0) {
            return 0;
        }
        return binarySearch * 2;
    }

    public int getDividerIndex(MediaItem mediaItem) {
        return getDividerIndex(this.mTimelineItemMapReadOnly, mediaItem.getDate());
    }

    public int getDataPosition(int i2, float f, float f5, int i7) {
        return getDataPositionInternal(i2, getViewPositionFromCoordinates(f, f5, i7));
    }
}
