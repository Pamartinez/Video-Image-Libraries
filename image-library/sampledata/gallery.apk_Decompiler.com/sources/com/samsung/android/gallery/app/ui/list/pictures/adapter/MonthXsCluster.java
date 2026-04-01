package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import A4.S;
import A8.C0545b;
import T8.C0578a;
import c0.C0086a;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.module.dataset.tables.ClusterIndexer;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.module.dataset.tables.SpecProvider;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.scsp.media.api.d;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MonthXsCluster implements Cluster {
    private int mColCount;
    private int mCount;
    private final ConcurrentHashMap<Integer, ArrayList<Integer>> mFilteredDataMap;
    private final ConcurrentHashMap<Integer, ClusterItem> mFilteredDividerMap;
    private int mMaxWidth;
    private int mRowCount;
    private int[] mScrollIndex;
    private String[] mScrollIndexDates;
    private ScrollText[] mScrollIndexTag;
    private ArrayList<Integer> mTimelineIdxList;
    private ConcurrentHashMap<Integer, ClusterItem> mTimelineItemMapReadOnly;
    private final int mTotalItemCount;

    public MonthXsCluster(ClusterIndexer clusterIndexer, int i2, SpecProvider specProvider) {
        this(clusterIndexer, i2, specProvider.getMonthXsColumnCount(), specProvider.getWidthSpec(specProvider.getColumnCount()));
    }

    private void adjustIndexerValue() {
        int i2;
        if (!this.mTimelineIdxList.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            int i7 = 0;
            AtomicInteger atomicInteger = new AtomicInteger(0);
            AtomicInteger atomicInteger2 = new AtomicInteger(0);
            this.mTimelineIdxList.forEach(new S(this, arrayList, atomicInteger, atomicInteger2, 15));
            int intValue = (this.mTotalItemCount - (((Integer) C0212a.i(this.mTimelineIdxList, 1)).intValue() - arrayList.size())) - 1;
            int i8 = this.mColCount;
            int i10 = intValue / i8;
            if (intValue % i8 == 0) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            int i11 = i10 + i2;
            int intValue2 = ((Integer) C0212a.i(arrayList, 1)).intValue();
            for (int i12 = 0; i12 < i11; i12++) {
                this.mFilteredDataMap.put(Integer.valueOf(intValue2 + 1 + i12), createDataPositionList(atomicInteger2, intValue - (this.mColCount * i12)));
            }
            int i13 = this.mColCount;
            int intValue3 = (intValue / i13) + ((Integer) C0212a.i(arrayList, 1)).intValue() + 1;
            if (intValue % i13 != 0) {
                i7 = 1;
            }
            this.mRowCount = intValue3 + i7;
            this.mScrollIndex = arrayList.stream().mapToInt(new C0545b(25)).toArray();
        }
    }

    private ArrayList<Integer> createDataPositionList(AtomicInteger atomicInteger, int i2) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i7 = 0; i7 < Math.min(this.mColCount, i2); i7++) {
            arrayList.add(Integer.valueOf(atomicInteger.get() + i7));
        }
        atomicInteger.set(((Integer) C0212a.i(arrayList, 1)).intValue() + 1);
        return arrayList;
    }

    private int getDataEndPosition(int i2) {
        ArrayList<Integer> dataPositionList = getDataPositionList(i2);
        if (dataPositionList.isEmpty()) {
            return this.mTotalItemCount - 1;
        }
        return ((Integer) C0212a.i(dataPositionList, 1)).intValue();
    }

    private int getDataStartPosition(int i2) {
        ArrayList<Integer> dataPositionList = getDataPositionList(i2);
        if (dataPositionList.isEmpty()) {
            return 0;
        }
        return dataPositionList.get(0).intValue();
    }

    private void init(ClusterIndexer clusterIndexer) {
        this.mCount = clusterIndexer.getCount();
        this.mTimelineIdxList = clusterIndexer.getTimelineIdxList();
        this.mTimelineItemMapReadOnly = clusterIndexer.getTimelineItemMap();
        this.mScrollIndexTag = clusterIndexer.getScrollIndexTag();
        adjustIndexerValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$adjustIndexerValue$1(ArrayList arrayList, AtomicInteger atomicInteger, AtomicInteger atomicInteger2, Integer num) {
        int i2;
        if (num.intValue() == 0) {
            arrayList.add(num);
        } else {
            int intValue = (num.intValue() - atomicInteger.get()) - 1;
            int i7 = this.mColCount;
            int i8 = intValue / i7;
            if (intValue % i7 == 0) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            int i10 = i8 + i2;
            int intValue2 = ((Integer) C0212a.i(arrayList, 1)).intValue();
            for (int i11 = 0; i11 < i10; i11++) {
                this.mFilteredDataMap.put(Integer.valueOf(intValue2 + 1 + i11), createDataPositionList(atomicInteger2, intValue - (this.mColCount * i11)));
            }
            arrayList.add(Integer.valueOf(intValue2 + i10 + 1));
        }
        this.mFilteredDividerMap.put((Integer) C0212a.i(arrayList, 1), this.mTimelineItemMapReadOnly.get(num));
        atomicInteger.set(num.intValue());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$getScrollIndexDates$0(int i2) {
        return new String[i2];
    }

    public int[] findDataPositionRange(MediaItem mediaItem) {
        try {
            int dividerIndex = getDividerIndex(mediaItem.getDateTaken());
            int i2 = this.mScrollIndex[dividerIndex];
            int i7 = i2 + 1;
            int dataStartPosition = getDataStartPosition(i7);
            int i8 = i2 + 2;
            if (this.mRowCount != i8) {
                if (!isDivider(i8)) {
                    i7 = this.mScrollIndex[dividerIndex + 1] - 1;
                }
            }
            return new int[]{dataStartPosition, getDataEndPosition(i7)};
        } catch (Exception e) {
            Log.e((CharSequence) "MonthXsCluster", "findDataPositionRange failed", (Throwable) e);
            return new int[]{0, 1};
        }
    }

    public ClusterItem getClusterItem(int i2) {
        return this.mFilteredDividerMap.get(Integer.valueOf(i2));
    }

    public int getColumnSpan(int i2, int i7) {
        return this.mMaxWidth;
    }

    public int getCount() {
        return this.mCount;
    }

    public int getDataPosition(int i2) {
        ArrayList<Integer> dataPositionList = getDataPositionList(i2);
        if (dataPositionList.isEmpty()) {
            return 0;
        }
        return dataPositionList.get(0).intValue();
    }

    public ArrayList<Integer> getDataPositionList(int i2) {
        ArrayList<Integer> arrayList = this.mFilteredDataMap.get(Integer.valueOf(i2));
        if (arrayList == null) {
            return new ArrayList<>();
        }
        return arrayList;
    }

    public int getDividerIndex(long j2) {
        return getDividerIndex(TimeUtil.toLocalDate(j2, "YM"));
    }

    public ArrayList<Integer> getDividers() {
        return (ArrayList) Arrays.stream(this.mScrollIndex).boxed().collect(Collectors.toList());
    }

    public int getExtraOffset(int i2, int i7, int i8) {
        return -1;
    }

    public int getItemHeight(int i2, int i7) {
        return this.mMaxWidth / i7;
    }

    public int getItemViewType(int i2) {
        if (!isDivider(i2)) {
            return 3;
        }
        if (i2 == 0) {
            return -1;
        }
        return -2;
    }

    public int getMaxScroll(int i2, float f, int i7, int i8) {
        int i10 = this.mRowCount;
        if (i10 <= 0) {
            return -1;
        }
        int[] iArr = this.mScrollIndex;
        int length = iArr.length * i7;
        if (i8 != 0) {
            length += i8 - i7;
        }
        return ((int) (((float) (i10 - iArr.length)) * f)) + length;
    }

    public String[] getScrollIndexDates() {
        if (this.mScrollIndexDates == null) {
            this.mScrollIndexDates = (String[]) Arrays.stream(this.mScrollIndexTag).map(new d(13)).toArray(new C0578a(15));
        }
        return this.mScrollIndexDates;
    }

    public ScrollText getScrollText(int i2) {
        ScrollText[] scrollTextArr;
        int dividerIndex = getDividerIndex(i2);
        if (dividerIndex < 0 || (scrollTextArr = this.mScrollIndexTag) == null) {
            return null;
        }
        return scrollTextArr[dividerIndex];
    }

    public SpanInfo getSpanInfo(int i2, int i7) {
        return new SpanInfo(i2 + 1, 0, getItemViewType(i2));
    }

    public int getStartSpan(int i2, int i7) {
        return 0;
    }

    public int getViewCount() {
        return this.mRowCount;
    }

    public int getViewPosition(int i2) {
        for (Map.Entry next : this.mFilteredDataMap.entrySet()) {
            if (((ArrayList) next.getValue()).contains(Integer.valueOf(i2))) {
                return ((Integer) next.getKey()).intValue();
            }
        }
        return 0;
    }

    public boolean isDivider(int i2) {
        if (Arrays.binarySearch(this.mScrollIndex, i2) >= 0) {
            return true;
        }
        return false;
    }

    public void onGridSizeChanged(int i2) {
        if (i2 != this.mColCount) {
            this.mColCount = i2;
            adjustIndexerValue();
        }
    }

    public void recalculatePosition(int i2) {
        if (i2 != this.mMaxWidth) {
            this.mMaxWidth = i2;
            adjustIndexerValue();
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Row,Col[");
        sb2.append(this.mRowCount);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mColCount);
        sb2.append("] of Total[");
        sb2.append(this.mTotalItemCount);
        sb2.append("]\nmMaxWidth:");
        return C0086a.l(sb2, this.mMaxWidth, "\n");
    }

    public MonthXsCluster(ClusterIndexer clusterIndexer, int i2, int i7, int i8) {
        this.mFilteredDividerMap = new ConcurrentHashMap<>();
        this.mFilteredDataMap = new ConcurrentHashMap<>();
        this.mTotalItemCount = i2;
        this.mColCount = i7;
        this.mMaxWidth = i8;
        init(clusterIndexer);
    }

    public int getDividerIndex(int i2) {
        int binarySearch = Arrays.binarySearch(this.mScrollIndex, i2);
        return binarySearch < 0 ? (-binarySearch) - 2 : binarySearch;
    }

    public int getDataPosition(int i2, float f, float f5, int i7) {
        ArrayList<Integer> dataPositionList = getDataPositionList(i2);
        if (dataPositionList.isEmpty()) {
            return 0;
        }
        return dataPositionList.get(Math.min(dataPositionList.size() - 1, (int) Math.max(0.0f, f / (((float) this.mMaxWidth) / ((float) i7))))).intValue();
    }

    public int getDividerIndex(String str) {
        for (Map.Entry next : this.mFilteredDividerMap.entrySet()) {
            if (((ClusterItem) next.getValue()).getDate().equalsIgnoreCase(str)) {
                return getDividerIndex(((Integer) next.getKey()).intValue());
            }
        }
        return 0;
    }

    public void recalculatePosition(int i2, int i7) {
        if (i2 != this.mColCount || i7 != this.mMaxWidth) {
            this.mColCount = i2;
            this.mMaxWidth = i7;
            adjustIndexerValue();
        }
    }
}
