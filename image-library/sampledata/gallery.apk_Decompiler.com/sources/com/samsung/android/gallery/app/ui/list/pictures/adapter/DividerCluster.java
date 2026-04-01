package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import A.a;
import T8.C0578a;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.scsp.media.api.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DividerCluster implements Cluster {
    protected final String TAG = getClass().getSimpleName();
    protected int mCount;
    protected ArrayList<Integer> mDividerIndexList;
    protected ConcurrentHashMap<Integer, ClusterItem> mDividerItemMapReadOnly;
    protected final HashMap<Integer, ArrayList<Integer>> mDividerRowMap = new HashMap<>();
    private int mGridSize = -1;
    private int mRowCount = -1;
    protected int[] mScrollIndex;
    private String[] mScrollIndexDates;
    protected ScrollText[] mScrollIndexTag;
    protected final int mTotalItemCount;

    public DividerCluster(int i2) {
        this.mTotalItemCount = i2;
    }

    private void calculateDividerRow(int i2) {
        ArrayList arrayList = new ArrayList();
        int i7 = 0;
        arrayList.add(0);
        int i8 = this.mTotalItemCount;
        Iterator<Integer> it = this.mDividerIndexList.iterator();
        int i10 = i8;
        int i11 = 0;
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue != i7) {
                int i12 = (intValue - i7) - 1;
                if (i12 >= 0) {
                    int rowCount = getRowCount(i2, i12) + i11;
                    arrayList.add(Integer.valueOf(arrayList.size() + rowCount));
                    i11 = rowCount;
                }
                i10 -= i12;
                i7 = intValue;
            }
        }
        if (i10 >= 0) {
            arrayList.add(Integer.valueOf(arrayList.size() + getRowCount(i2, i10) + i11));
        }
        this.mDividerRowMap.put(Integer.valueOf(i2), arrayList);
    }

    private int getDividerDelta(int i2) {
        int binarySearch = Arrays.binarySearch(this.mScrollIndex, i2);
        if (binarySearch < 0) {
            return (-binarySearch) - 1;
        }
        return 0;
    }

    private int getRow(int i2, int i7) {
        return getRowSumBefore(i7, i2) + getRowInCluster(i2, i7);
    }

    private int getRowInCluster(int i2, int i7) {
        int i8;
        int dividerIndex = i2 - getDividerIndex(i2);
        int i10 = dividerIndex / i7;
        if (dividerIndex % i7 == 0) {
            i8 = 0;
        } else {
            i8 = 1;
        }
        return i10 + i8;
    }

    private int getRowSumBefore(int i2, int i7) {
        int scrollIndex = getScrollIndex(i7);
        ArrayList<Integer> dividerRowList = getDividerRowList(i2);
        if (dividerRowList == null || dividerRowList.size() <= scrollIndex) {
            return 0;
        }
        return dividerRowList.get(scrollIndex).intValue();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$getScrollIndexDates$0(int i2) {
        return new String[i2];
    }

    public void calculateRowCount(int i2) {
        ArrayList arrayList = new ArrayList();
        int i7 = 0;
        arrayList.add(0);
        this.mRowCount = 0;
        int i8 = this.mTotalItemCount;
        Iterator<Integer> it = this.mDividerIndexList.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue != i7) {
                int i10 = (intValue - i7) - 1;
                if (i10 >= 0) {
                    int rowCount = getRowCount(i2, i10) + this.mRowCount;
                    this.mRowCount = rowCount;
                    arrayList.add(Integer.valueOf(arrayList.size() + rowCount));
                }
                i8 -= i10;
                i7 = intValue;
            }
        }
        if (i8 >= 0) {
            int rowCount2 = getRowCount(i2, i8) + this.mRowCount;
            this.mRowCount = rowCount2;
            arrayList.add(Integer.valueOf(arrayList.size() + rowCount2));
        }
        this.mGridSize = i2;
        this.mDividerRowMap.put(Integer.valueOf(i2), arrayList);
    }

    public void clear() {
        this.mDividerRowMap.clear();
    }

    public ClusterItem getClusterItem(int i2) {
        return this.mDividerItemMapReadOnly.get(Integer.valueOf(i2));
    }

    public int getColumnSpan(int i2, int i7) {
        if (isDivider(i2)) {
            return i7;
        }
        return 1;
    }

    public int getCount() {
        return this.mCount;
    }

    public int getDataPosition(int i2) {
        int dividerDelta = i2 - getDividerDelta(i2);
        if (dividerDelta >= 0 || i2 < 0) {
            return dividerDelta;
        }
        String str = this.TAG;
        StringBuilder h5 = a.h(i2, dividerDelta, "getDataPosition {view=", ",data=", ",length=");
        h5.append(this.mScrollIndex.length);
        h5.append("}");
        Log.e(str, h5.toString());
        return 0;
    }

    public int getDividerIndex(long j2) {
        return getDividerIndexInternal(j2);
    }

    public abstract int getDividerIndexInternal(long j2);

    public ArrayList<Integer> getDividerRowList(int i2) {
        return this.mDividerRowMap.get(Integer.valueOf(i2));
    }

    public ArrayList<Integer> getDividers() {
        return this.mDividerIndexList;
    }

    public int getItemViewType(int i2) {
        if (!isDivider(i2)) {
            return 0;
        }
        if (i2 == 0) {
            return -1;
        }
        return -2;
    }

    public int getMaxScroll(int i2, float f, int i7, int i8) {
        if (i2 != this.mGridSize) {
            calculateRowCount(i2);
        }
        int i10 = this.mRowCount;
        if (i10 <= 0) {
            return -1;
        }
        int size = (this.mDividerIndexList.size() * i7) + ((int) (((float) i10) * f));
        if (i8 != 0) {
            return (i8 - i7) + size;
        }
        return size;
    }

    public int getNextDividerIndex(int i2) {
        int scrollIndex = getScrollIndex(i2) + 1;
        if (scrollIndex > this.mDividerIndexList.size() - 1) {
            return i2 + 1;
        }
        return this.mDividerIndexList.get(scrollIndex).intValue();
    }

    public int getPrevDividerIndex(int i2) {
        int scrollIndex = getScrollIndex(i2) - 1;
        if (scrollIndex < 0) {
            return -1;
        }
        return this.mDividerIndexList.get(scrollIndex).intValue();
    }

    public int getRowCount(int i2, int i7) {
        int i8;
        int i10 = i7 / i2;
        if (i7 % i2 == 0) {
            i8 = 0;
        } else {
            i8 = 1;
        }
        return i10 + i8;
    }

    public final int getScrollIndex(int i2) {
        int binarySearch = Arrays.binarySearch(this.mScrollIndex, i2);
        if (binarySearch < -1) {
            return (-binarySearch) - 2;
        }
        return Math.max(binarySearch, 0);
    }

    public String[] getScrollIndexDates() {
        if (this.mScrollIndexDates == null) {
            this.mScrollIndexDates = (String[]) Arrays.stream(this.mScrollIndexTag).map(new d(13)).toArray(new C0578a(14));
        }
        return this.mScrollIndexDates;
    }

    public ScrollText getScrollText(int i2) {
        ScrollText[] scrollTextArr;
        int scrollIndex = getScrollIndex(i2);
        if (scrollIndex < 0 || (scrollTextArr = this.mScrollIndexTag) == null || scrollTextArr.length == 0) {
            return null;
        }
        return scrollTextArr[scrollIndex];
    }

    public SpanInfo getSpanInfo(int i2, int i7) {
        if (this.mDividerRowMap.get(Integer.valueOf(i7)) == null) {
            calculateDividerRow(i7);
        }
        return new SpanInfo(getRow(i2, i7), getStartSpanInternal(i2, i7), getItemViewType(i2));
    }

    public final int getStartSpan(int i2, int i7) {
        return getStartSpanInternal(i2, i7);
    }

    public abstract int getStartSpanInternal(int i2, int i7);

    public int getViewPosition(int i2) {
        Iterator<Integer> it = this.mDividerIndexList.iterator();
        int i7 = 0;
        while (it.hasNext() && i2 >= it.next().intValue() - i7) {
            i7++;
        }
        return i2 + i7;
    }

    public boolean isDivider(int i2) {
        return this.mDividerItemMapReadOnly.containsKey(Integer.valueOf(i2));
    }

    public int getDividerIndex(int i2) {
        return this.mDividerIndexList.get(getScrollIndex(i2)).intValue();
    }
}
