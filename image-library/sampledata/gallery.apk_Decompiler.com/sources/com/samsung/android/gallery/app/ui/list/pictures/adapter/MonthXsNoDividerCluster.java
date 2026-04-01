package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.module.dataset.tables.SpecProvider;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MonthXsNoDividerCluster extends NoDividerCluster {
    private int mColCount;
    private final ConcurrentHashMap<Integer, ArrayList<Integer>> mFilteredDataMap;
    private int mMaxWidth;
    private int mRowCount;

    public MonthXsNoDividerCluster(MediaData mediaData, SpecProvider specProvider) {
        this(mediaData, specProvider.getMonthXsColumnCount(), specProvider.getWidthSpec(specProvider.getColumnCount()));
    }

    private void adjustViewInfo() {
        int i2;
        int i7 = this.mCount;
        int i8 = this.mColCount;
        int i10 = i7 / i8;
        int i11 = i7 % i8;
        if (i11 == 0) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        this.mRowCount = i10 + i2;
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i12 = 0; i12 < this.mRowCount; i12++) {
            this.mFilteredDataMap.put(Integer.valueOf(i12), createDataPositionList(atomicInteger, this.mCount - (this.mColCount * i12)));
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

    public int[] findDataPositionRange(MediaItem mediaItem) {
        return new int[]{0, this.mCount - 1};
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

    public int getItemHeight(int i2, int i7) {
        return this.mMaxWidth / i7;
    }

    public int getItemViewType(int i2) {
        return 3;
    }

    public int getMaxScroll(int i2, float f, int i7, int i8) {
        int i10 = this.mRowCount;
        if (i10 > 0) {
            return (int) (((float) i10) * f);
        }
        return -1;
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

    public void onGridSizeChanged(int i2) {
        if (i2 != this.mColCount) {
            this.mColCount = i2;
            adjustViewInfo();
        }
    }

    public void recalculatePosition(int i2) {
        if (i2 != this.mMaxWidth) {
            this.mMaxWidth = i2;
            adjustViewInfo();
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("\nRow,Col[");
        sb2.append(this.mRowCount);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mColCount);
        sb2.append("] of Total[");
        sb2.append(this.mCount);
        sb2.append("]\nmMaxWidth:");
        return C0086a.l(sb2, this.mMaxWidth, "\n");
    }

    public MonthXsNoDividerCluster(MediaData mediaData, int i2, int i7) {
        super(mediaData);
        this.mFilteredDataMap = new ConcurrentHashMap<>();
        this.mColCount = i2;
        this.mMaxWidth = i7;
        adjustViewInfo();
    }

    public int getDataPosition(int i2, float f, float f5, int i7) {
        ArrayList<Integer> dataPositionList = getDataPositionList(i2);
        if (dataPositionList.isEmpty()) {
            return 0;
        }
        return dataPositionList.get(Math.min(dataPositionList.size() - 1, (int) Math.max(0.0f, f / (((float) this.mMaxWidth) / ((float) i7))))).intValue();
    }

    public void recalculatePosition(int i2, int i7) {
        if (i2 != this.mColCount || i7 != this.mMaxWidth) {
            this.mColCount = i2;
            this.mMaxWidth = i7;
            adjustViewInfo();
        }
    }
}
