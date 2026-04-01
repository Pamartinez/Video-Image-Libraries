package com.samsung.android.gallery.module.dataset.tables;

import A.a;
import N2.j;
import android.database.StaleDataException;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RealRatioIndexer implements Closeable {
    private static final boolean DEBUG_LOGGABLE = false;
    protected final String TAG = tag();
    int mBaseHeight;
    private int[] mClusterHeight;
    int mColumnCount;
    int mDataPosition;
    private DataTable mDataTable;
    private ArrayList<Integer> mDividerRow;
    boolean mHasTooSmall;
    protected ItemInfo[] mItemInfo;
    int mLastRowAddedInScroll;
    int mLastRowHeight;
    int mLoadedDataCount;
    int mMaxColumnCount;
    int mMaxHeight;
    int mMaxWidth;
    int mMinHeight;
    RealRatioTable mRealRatioTable;
    float mRefVolume;
    int mRowIndex;
    int mRowWidth;
    int mScrollRealRatio;
    private int mTimelineCount;
    private ConcurrentHashMap<Integer, ClusterItem> mTimelineItemMap;
    int mViewPosition;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ItemInfo {
        /* access modifiers changed from: private */
        public int column;
        protected int height;
        protected float ratioData = 1.0f;
        /* access modifiers changed from: private */
        public int row;
        /* access modifiers changed from: private */
        public int startSpan;
        protected int width;
    }

    public RealRatioIndexer(DataTable dataTable, ConcurrentHashMap<Integer, ClusterItem> concurrentHashMap, int i2, int i7) {
        this.mDataTable = dataTable;
        onConstruct(concurrentHashMap, i2, i7);
    }

    private int getExtraWidth(int i2, int i7, int i8) {
        if (i2 == i7) {
            return 0;
        }
        int i10 = i2;
        int i11 = 0;
        while (i10 <= i7) {
            float f = (float) i8;
            try {
                ItemInfo itemInfo = this.mItemInfo[i10];
                i11 += (int) ((f / ((float) itemInfo.height)) * ((float) itemInfo.width));
                i10++;
            } catch (ArithmeticException e) {
                Log.e(this.TAG, "getExtraWidth : " + e.getMessage());
                return 0;
            }
        }
        return (this.mMaxWidth - i11) / ((i7 - i2) + 1);
    }

    public static float getRectRatio(int i2, int i7, int i8) {
        float f;
        if (i8 % MOCRLang.KHMER == 0) {
            f = ((float) i2) / ((float) i7);
        } else {
            f = ((float) i7) / ((float) i2);
        }
        return ((float) ((int) (f * 100.0f))) / 100.0f;
    }

    private boolean hasEnoughShrinkRowWidth() {
        if (this.mRowWidth < this.mMaxWidth * 2) {
            return true;
        }
        return false;
    }

    private boolean hasOneItemInRow(int i2) {
        if (i2 <= 1) {
            return true;
        }
        return false;
    }

    private boolean isShrinkNearerThanExpandToBaseHeight(int[] iArr) {
        if (Math.abs(iArr[0] - this.mBaseHeight) < Math.abs(iArr[1] - this.mBaseHeight)) {
            return true;
        }
        return false;
    }

    private boolean processItem(int i2) {
        boolean z;
        int i7;
        ThreadUtil.assertUiThread("RR Proc");
        Log.d(this.TAG, "processItem {limit=" + i2 + "}");
        this.mDividerRow = new ArrayList<>();
        this.mScrollRealRatio = 0;
        this.mRowIndex = 0;
        this.mLastRowAddedInScroll = -1;
        this.mLastRowHeight = 0;
        this.mRowWidth = 0;
        this.mColumnCount = 0;
        this.mHasTooSmall = false;
        this.mDataPosition = 0;
        this.mViewPosition = 0;
        if (this.mTimelineItemMap.size() > 0) {
            z = true;
        } else {
            z = false;
        }
        while (true) {
            int i8 = this.mDataPosition;
            if (i8 >= i2) {
                return true;
            }
            int i10 = this.mViewPosition;
            int i11 = i10 - i8;
            int[] iArr = this.mClusterHeight;
            if (i11 < iArr.length) {
                iArr[i10 - i8] = this.mScrollRealRatio;
            }
            int i12 = this.mRowIndex;
            this.mRowIndex = i12 + 1;
            this.mLastRowAddedInScroll = i12;
            this.mDividerRow.add(Integer.valueOf(i12));
            this.mRowWidth = 0;
            this.mColumnCount = 0;
            this.mHasTooSmall = false;
            ConcurrentHashMap<Integer, ClusterItem> concurrentHashMap = this.mTimelineItemMap;
            int i13 = this.mViewPosition;
            this.mViewPosition = i13 + 1;
            ClusterItem clusterItem = concurrentHashMap.get(Integer.valueOf(i13));
            if (clusterItem != null) {
                i7 = clusterItem.getCount();
            } else {
                i7 = i2 - this.mDataPosition;
                this.mViewPosition--;
                if (z) {
                    a.B(i7, "processItem wrong cluster > add remained items=", this.TAG);
                }
            }
            int i14 = i8 + i7;
            int min = Math.min(i14, i2);
            int processItemInCluster = processItemInCluster(min, i14);
            if (DEBUG_LOGGABLE) {
                String str = this.TAG;
                StringBuilder h5 = a.h(i10, i8, "cluster[", "] dataPosition=", " dataCount=");
                j.x(h5, i7, " dataBound=", min, " procCount=");
                h5.append(processItemInCluster);
                h5.append(" (");
                h5.append(this.mViewPosition);
                h5.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                h5.append(this.mDataPosition);
                h5.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                h5.append(i2);
                h5.append(")");
                Log.d(str, h5.toString());
            }
        }
    }

    private int processItemInCluster(int i2, int i7) {
        int i8;
        int i10 = i7 - 1;
        int i11 = 0;
        while (this.mDataPosition < i2) {
            float f = this.mItemInfo[this.mViewPosition].ratioData;
            int width = getWidth(f);
            int height = getHeight(width, f);
            if (isTooSmall(width)) {
                width = this.mMinHeight;
                this.mHasTooSmall = true;
            }
            this.mRowWidth += width;
            if (isWidthOver() || isLastItem() || this.mDataPosition == i10 || reachMaxItems()) {
                if (isSingleColumn()) {
                    int shrinkHeightForSingleColumn = getShrinkHeightForSingleColumn();
                    int i12 = this.mMaxHeight;
                    if (shrinkHeightForSingleColumn > i12) {
                        float f5 = (((float) i12) / ((float) height)) * ((float) width);
                        float f8 = ((float) this.mMaxWidth) / 3.0f;
                        if (f5 > f8) {
                            i8 = (int) f5;
                        } else {
                            int i13 = (int) f8;
                            i12 = Math.min((int) (((float) i13) / f), i12);
                            i8 = i13;
                        }
                        putColumnSize(this.mViewPosition, i8, i12);
                    } else {
                        putColumnSize(this.mViewPosition, this.mMaxWidth, shrinkHeightForSingleColumn);
                    }
                    this.mItemInfo[this.mViewPosition].startSpan = 0;
                } else {
                    putColumnSize(this.mViewPosition, width, height);
                    resizeAllColumnsInRow(width, reachMaxItems());
                }
                this.mColumnCount = 0;
                this.mRowWidth = 0;
            } else {
                putColumnSize(this.mViewPosition, width, height);
                this.mColumnCount++;
            }
            int i14 = this.mRowIndex;
            if (i14 == this.mLastRowAddedInScroll) {
                int i15 = this.mItemInfo[this.mViewPosition].height;
                int i16 = this.mLastRowHeight;
                if (i15 != i16) {
                    this.mScrollRealRatio = (this.mScrollRealRatio + i15) - i16;
                    this.mLastRowHeight = i15;
                }
            } else {
                int i17 = this.mItemInfo[this.mViewPosition].height;
                this.mLastRowHeight = i17;
                this.mScrollRealRatio += i17;
                this.mLastRowAddedInScroll = i14;
            }
            if (this.mRowWidth == 0) {
                this.mRowIndex = i14 + 1;
            }
            this.mDataPosition++;
            this.mViewPosition++;
            i11++;
        }
        return i11;
    }

    private boolean reachMaxItems() {
        if (this.mColumnCount == this.mMaxColumnCount - 1) {
            return true;
        }
        return false;
    }

    private boolean shouldShrinkRowToFit(int[] iArr, int i2) {
        if (!hasEnoughShrinkRowWidth()) {
            return false;
        }
        if (hasOneItemInRow(i2) || isShrinkNearerThanExpandToBaseHeight(iArr)) {
            return true;
        }
        return false;
    }

    public void adjustItemDimensions(int i2, int i7, int i8) {
        int i10;
        int extraWidth = getExtraWidth(i2, i7, i8);
        while (extraWidth < 0) {
            int i11 = this.mMaxWidth;
            i8 = (int) ((((float) i11) / ((float) (i11 - (((i7 - i2) + 1) * extraWidth)))) * ((float) i8));
            extraWidth = getExtraWidth(i2, i7, i8);
        }
        int i12 = this.mMaxHeight;
        if (i8 > i12) {
            extraWidth = (int) ((((float) i12) / ((float) i12)) * ((float) extraWidth));
            i8 = i12;
        }
        int i13 = 0;
        for (int i14 = i2; i14 <= i7; i14++) {
            ItemInfo itemInfo = this.mItemInfo[i14];
            int i15 = (int) (((((float) i8) / ((float) itemInfo.height)) * ((float) itemInfo.width)) + ((float) extraWidth));
            itemInfo.startSpan = i13;
            ItemInfo itemInfo2 = this.mItemInfo[i14];
            if (i14 != i7 || i14 == i2) {
                i10 = i15;
            } else {
                i10 = fillRemainWidth(i13, this.mMaxWidth, i15);
            }
            itemInfo2.width = i10;
            ItemInfo itemInfo3 = this.mItemInfo[i14];
            itemInfo3.height = i8;
            itemInfo3.column = i14 - i2;
            i13 += i15;
        }
    }

    public void close() {
        Log.v(this.TAG, "close");
        this.mRealRatioTable = null;
        this.mDataTable = null;
        ConcurrentHashMap<Integer, ClusterItem> concurrentHashMap = this.mTimelineItemMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
            this.mTimelineItemMap = null;
        }
        ArrayList<Integer> arrayList = this.mDividerRow;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.mItemInfo = null;
    }

    public final int fillRemainWidth(int i2, int i7, int i8) {
        int i10 = this.mMaxWidth - i2;
        if (i10 > i7 || i10 <= 0) {
            return i8;
        }
        return i10;
    }

    public int[] getClusterHeight() {
        return this.mClusterHeight;
    }

    public int getColumn(int i2) {
        ItemInfo[] itemInfoArr = this.mItemInfo;
        if (itemInfoArr == null || i2 >= itemInfoArr.length) {
            return 0;
        }
        return itemInfoArr[i2].column;
    }

    public ArrayList<Integer> getDividerRow() {
        return this.mDividerRow;
    }

    public int getHeight(int i2, float f) {
        if (isInvalidRatio(f) || f >= 0.4f) {
            return this.mBaseHeight;
        }
        return Math.min((int) (((float) i2) / f), this.mMaxHeight);
    }

    public int[] getHeightInfo(int i2, boolean z) {
        int i7;
        float f = this.mRefVolume;
        int i8 = this.mRowWidth;
        int i10 = (int) (f / ((float) i8));
        int i11 = (int) (f / ((float) (i8 - i2)));
        if (i8 >= this.mMaxWidth || !z) {
            i7 = 0;
        } else {
            i7 = this.mMaxHeight;
        }
        return new int[]{i10, i11, i7};
    }

    public int getItemHeight(int i2) {
        ItemInfo[] itemInfoArr = this.mItemInfo;
        if (itemInfoArr == null || i2 >= itemInfoArr.length) {
            return Math.max(this.mMinHeight, 1);
        }
        return itemInfoArr[i2].height;
    }

    public int getItemWidth(int i2) {
        ItemInfo[] itemInfoArr = this.mItemInfo;
        if (itemInfoArr == null || i2 >= itemInfoArr.length) {
            return Math.max(this.mMinHeight, 1);
        }
        return itemInfoArr[i2].width;
    }

    public int getMaxScroll() {
        return this.mScrollRealRatio;
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getRow(int i2) {
        ItemInfo[] itemInfoArr = this.mItemInfo;
        if (itemInfoArr == null || i2 >= itemInfoArr.length) {
            return 0;
        }
        return itemInfoArr[i2].row;
    }

    public boolean[] getShrinkExpandInfo(int[] iArr, int i2, boolean z) {
        boolean z3;
        boolean isValidHeight = isValidHeight(iArr[0]);
        boolean isValidHeight2 = isValidHeight(iArr[1]);
        if (isValidHeight || isValidHeight2 || !isValidHeight(iArr[2])) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (!isValidHeight && !isValidHeight2 && z) {
            iArr[1] = this.mMaxHeight;
            isValidHeight2 = true;
        }
        if (isValidHeight && isValidHeight2) {
            isValidHeight = shouldShrinkRowToFit(iArr, i2);
            isValidHeight2 = !isValidHeight;
        }
        return new boolean[]{isValidHeight, isValidHeight2, z3};
    }

    public int getShrinkHeightForSingleColumn() {
        int i2 = (int) (this.mRefVolume / ((float) this.mRowWidth));
        if (isTooSmall(i2)) {
            return this.mMinHeight;
        }
        return i2;
    }

    public int getStartSpan(int i2) {
        ItemInfo[] itemInfoArr = this.mItemInfo;
        if (itemInfoArr == null || i2 >= itemInfoArr.length) {
            return 0;
        }
        return itemInfoArr[i2].startSpan;
    }

    public int getWidth(float f) {
        if (isInvalidRatio(f)) {
            f = 1.0f;
        }
        return (int) (f * ((float) this.mBaseHeight));
    }

    public boolean hasNoData() {
        if (this.mDataTable == null && this.mRealRatioTable == null) {
            return true;
        }
        return false;
    }

    public void init(SpecProvider specProvider, int i2) {
        ThreadUtil.assertUiThread("RR init");
        if (hasNoData()) {
            Log.e(this.TAG, "closed. init fail");
            return;
        }
        loadOriginalWidth(this.mLoadedDataCount);
        int[] heightSpec = specProvider.getHeightSpec();
        if (i2 == -1) {
            i2 = specProvider.getWidthSpec(1);
        }
        this.mMaxWidth = i2;
        this.mBaseHeight = heightSpec[0];
        this.mMinHeight = heightSpec[1];
        this.mMaxHeight = heightSpec[2];
        this.mMaxColumnCount = specProvider.getRealRatioMaxColumnCount();
        this.mRefVolume = (float) (this.mMaxWidth * this.mBaseHeight);
        TimeTickLog timeTickLog = new TimeTickLog("RealRatio process");
        try {
            processItem(this.mLoadedDataCount);
        } catch (NullPointerException unused) {
            if (hasNoData()) {
                Log.e(this.TAG, "closed. ignore NPE. init fail");
            } else {
                new InternalException("closed. but non null. " + this + ArcCommonLog.TAG_COMMA + this.mDataTable + ArcCommonLog.TAG_COMMA + this.mRealRatioTable).post();
            }
        } catch (Exception e) {
            if (hasNoData()) {
                a.s(e, new StringBuilder("closed. ignore exception. init fail e="), this.TAG);
                return;
            }
            throw e;
        }
        timeTickLog.tock(100);
    }

    public void initItemInfo(int i2) {
        this.mItemInfo = new ItemInfo[i2];
        int i7 = 0;
        while (true) {
            ItemInfo[] itemInfoArr = this.mItemInfo;
            if (i7 < itemInfoArr.length) {
                itemInfoArr[i7] = new ItemInfo();
                i7++;
            } else {
                return;
            }
        }
    }

    public boolean isInvalidRatio(float f) {
        if (f == 0.0f || Float.isNaN(f) || Float.isInfinite(f)) {
            return true;
        }
        return false;
    }

    public boolean isLastItem() {
        if (this.mDataPosition == this.mDataTable.getRealCount() - 1) {
            return true;
        }
        return false;
    }

    public final boolean isPicture(int i2) {
        try {
            return !this.mTimelineItemMap.containsKey(Integer.valueOf(i2));
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public boolean isSingleColumn() {
        if (this.mColumnCount == 0) {
            return true;
        }
        return false;
    }

    public boolean isTooSmall(int i2) {
        if (i2 < this.mMinHeight) {
            return true;
        }
        return false;
    }

    public boolean isValidHeight(int i2) {
        if (i2 < this.mMinHeight || i2 > this.mMaxHeight) {
            return false;
        }
        return true;
    }

    public boolean isWidthOver() {
        if (this.mRowWidth > this.mMaxWidth) {
            return true;
        }
        return false;
    }

    public boolean loadOriginalWidth(int i2) {
        if (this.mDataTable == null) {
            Log.e(this.TAG, "mRealRatioTable = " + this.mRealRatioTable);
            new InternalException("no data table").post();
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int i7 = 0;
        int i8 = 0;
        while (i7 < i2) {
            if (isPicture(i8)) {
                try {
                    MediaItem loadAndGetPrimitive = this.mDataTable.loadAndGetPrimitive(i7);
                    if (loadAndGetPrimitive != null) {
                        this.mItemInfo[i8].ratioData = getRectRatio(loadAndGetPrimitive.getWidth(), loadAndGetPrimitive.getHeight(), loadAndGetPrimitive.getOrientation());
                    }
                    i7++;
                } catch (StaleDataException | IllegalStateException | NullPointerException unused) {
                    Log.e(this.TAG, "RealRatio load failed. cursor closed during calculation");
                    return false;
                }
            }
            i8++;
        }
        a.x(C0086a.o(i2, "RealRatio load {", "} +"), currentTimeMillis, this.TAG);
        return true;
    }

    public void onConstruct(ConcurrentHashMap<Integer, ClusterItem> concurrentHashMap, int i2, int i7) {
        initItemInfo(i2 + i7);
        this.mClusterHeight = new int[i2];
        this.mTimelineItemMap = concurrentHashMap;
        this.mTimelineCount = i2;
        this.mLoadedDataCount = i7;
    }

    public void putColumnSize(int i2, int i7, int i8) {
        ItemInfo itemInfo = this.mItemInfo[i2];
        itemInfo.width = i7;
        itemInfo.height = i8;
        itemInfo.row = this.mRowIndex;
        this.mItemInfo[i2].column = this.mColumnCount;
    }

    public void resizeAllColumnsInRow(int i2, boolean z) {
        int[] heightInfo = getHeightInfo(i2, z);
        int resizeRowDimensions = resizeRowDimensions(getShrinkExpandInfo(heightInfo, this.mColumnCount, this.mHasTooSmall), heightInfo, this.mViewPosition, this.mColumnCount);
        this.mDataPosition += resizeRowDimensions;
        this.mViewPosition += resizeRowDimensions;
        this.mHasTooSmall = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0027 A[LOOP:0: B:15:0x0027->B:19:0x0037, LOOP_START, PHI: r0 r9 
      PHI: (r0v1 int) = (r0v0 int), (r0v2 int) binds: [B:14:0x0024, B:19:0x0037] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r9v2 int) = (r9v1 int), (r9v3 int) binds: [B:14:0x0024, B:19:0x0037] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int resizeRowDimensions(boolean[] r6, int[] r7, int r8, int r9) {
        /*
            r5 = this;
            int r9 = r8 - r9
            r0 = 0
            boolean r1 = r6[r0]
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x000d
            r7 = r7[r0]
        L_0x000b:
            r4 = r8
            goto L_0x001c
        L_0x000d:
            boolean r4 = r6[r2]
            if (r4 == 0) goto L_0x0014
            r7 = r7[r2]
            goto L_0x000b
        L_0x0014:
            r7 = r7[r3]
            boolean r4 = r6[r3]
            if (r4 == 0) goto L_0x000b
            int r4 = r8 + -1
        L_0x001c:
            if (r1 != 0) goto L_0x0044
            boolean r1 = r6[r3]
            if (r1 != 0) goto L_0x0044
            boolean r6 = r6[r2]
            if (r6 == 0) goto L_0x0027
            goto L_0x0044
        L_0x0027:
            if (r9 > r4) goto L_0x0047
            com.samsung.android.gallery.module.dataset.tables.RealRatioIndexer$ItemInfo[] r6 = r5.mItemInfo
            r6 = r6[r9]
            int r7 = r6.width
            int r7 = r7 + r0
            int r1 = r5.mMaxWidth
            if (r7 <= r1) goto L_0x0037
            int r4 = r9 + -1
            goto L_0x0047
        L_0x0037:
            r6.startSpan = r0
            com.samsung.android.gallery.module.dataset.tables.RealRatioIndexer$ItemInfo[] r6 = r5.mItemInfo
            r6 = r6[r9]
            int r6 = r6.width
            int r0 = r0 + r6
            int r9 = r9 + 1
            goto L_0x0027
        L_0x0044:
            r5.adjustItemDimensions(r9, r4, r7)
        L_0x0047:
            int r4 = r4 - r8
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.dataset.tables.RealRatioIndexer.resizeRowDimensions(boolean[], int[], int, int):int");
    }

    public String tag() {
        return "RealRatioIndexer";
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.TAG);
        sb2.append(com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR);
        sb2.append(Integer.toHexString(hashCode()));
        sb2.append("{");
        sb2.append(this.mTimelineCount);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mLoadedDataCount);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mMaxWidth);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mMaxHeight);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0086a.l(sb2, this.mBaseHeight, "}");
    }

    public RealRatioIndexer(RealRatioTable realRatioTable, ConcurrentHashMap<Integer, ClusterItem> concurrentHashMap, int i2, int i7) {
        this.mRealRatioTable = realRatioTable;
        onConstruct(concurrentHashMap, i2, i7);
    }

    public RealRatioIndexer(ArrayList<MediaItem> arrayList) {
    }

    public void init(SpecProvider specProvider) {
        int widthSpec = specProvider.getWidthSpec(1);
        if (getMaxWidth() != widthSpec) {
            init(specProvider, widthSpec);
        }
    }
}
