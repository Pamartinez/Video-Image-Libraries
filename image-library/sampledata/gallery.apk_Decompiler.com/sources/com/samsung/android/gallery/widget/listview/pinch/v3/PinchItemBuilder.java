package com.samsung.android.gallery.widget.listview.pinch.v3;

import A4.A;
import A4.C0371f;
import A4.C0387w;
import A8.C0545b;
import Ad.C0720a;
import Fa.C0571z;
import Gb.a;
import Jb.b;
import android.graphics.RectF;
import android.util.SparseArray;
import android.view.View;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchItemBuilder {
    private BitmapCache bitmapCache;
    private int mBaseGrid = -1;
    private SparseArray<Integer> mDividerGroup = new SparseArray<>();
    private final int mFirstVisibleViewPosition;
    private final PinchLayoutManager mLayoutManager;
    private final IPinchRecyclerView mListView;
    private final HashMap<Integer, PinchAnimInfo> mPinchAnimInfo;

    public PinchItemBuilder(PinchLayoutManager pinchLayoutManager, IPinchRecyclerView iPinchRecyclerView, HashMap<Integer, PinchAnimInfo> hashMap) {
        this.mLayoutManager = pinchLayoutManager;
        this.mListView = iPinchRecyclerView;
        this.mPinchAnimInfo = hashMap;
        this.mFirstVisibleViewPosition = findFirstVisibleViewPosition();
    }

    private void calculateExtraSpace(RelativeRange relativeRange) {
        this.mPinchAnimInfo.values().stream().iterator().forEachRemaining(new A(24, (Object) this, (Object) relativeRange));
    }

    private PinchItem createDataItem(int i2, int i7, BitmapCache bitmapCache2) {
        DataItem dataItem = new DataItem(bitmapCache2);
        dataItem.setFakeView(isFakeView(getRow(i2, getCurrentSpanCount(), true), i7));
        for (Integer intValue : (List) this.mPinchAnimInfo.keySet().stream().sorted().collect(Collectors.toList())) {
            int intValue2 = intValue.intValue();
            updateDataItem(dataItem, intValue2, getRow(i2, intValue2, true), i7);
            if (intValue2 >= this.mBaseGrid) {
                dataItem.updateBaseRectGrid(intValue2);
            }
        }
        return dataItem;
    }

    private ArrayList<PinchItem> createDataItems(BitmapCache bitmapCache2) {
        ArrayList<PinchItem> arrayList = new ArrayList<>();
        RelativeRange relativeRange = new RelativeRange(true, (PinchRange[]) this.mPinchAnimInfo.values().stream().map(new a(23)).toArray(new C0387w(20)));
        calculateExtraSpace(relativeRange);
        for (int i2 = 0; i2 < relativeRange.getRowCount(); i2++) {
            int row = relativeRange.getRow(i2);
            for (int i7 = 0; i7 < relativeRange.getColumnCount(i2); i7++) {
                PinchItem createDataItem = createDataItem(row, i7, bitmapCache2);
                if (createDataItem.getViewType() != null) {
                    arrayList.add(createDataItem);
                }
            }
        }
        return arrayList;
    }

    private void createDividerItem(ArrayList<PinchItem> arrayList, int i2, int i7, int i8) {
        PinchItem findDivider = findDivider(i2, arrayList, i8);
        if (findDivider == null) {
            if (i8 >= 0) {
                findDivider = createDivider(i8);
                arrayList.add(findDivider);
            } else {
                return;
            }
        }
        if (findDivider != null) {
            updateFakeView(i2, i7, findDivider);
            updateDividerItem(findDivider, i2, i7, i8);
        }
    }

    private ArrayList<PinchItem> createDividerItems() {
        ArrayList arrayList = new ArrayList();
        RelativeRange relativeRange = new RelativeRange(false, (PinchRange[]) this.mPinchAnimInfo.values().stream().map(new a(23)).toArray(new C0387w(19)));
        calculateExtraSpace(relativeRange);
        this.mPinchAnimInfo.keySet().stream().sorted(Comparator.reverseOrder()).iterator().forEachRemaining(new C0371f((Object) this, (Object) relativeRange, (Object) arrayList, 5));
        updateHeaderViewRect(arrayList);
        return (ArrayList) arrayList.stream().filter(new C0571z(22)).collect(Collectors.toCollection(new C0720a(1)));
    }

    private PinchItem findDivider(int i2, ArrayList<PinchItem> arrayList, int i7) {
        Iterator<PinchItem> it = arrayList.iterator();
        while (it.hasNext()) {
            PinchItem next = it.next();
            if (next.isSameGroup(i7, i2, this.mDividerGroup)) {
                return next;
            }
        }
        return null;
    }

    private int findFirstVisibleViewPosition() {
        View childAt = this.mLayoutManager.getChildAt(0);
        if (childAt != null) {
            return this.mListView.getChildViewHolder(childAt).getAbsoluteAdapterPosition();
        }
        return this.mLayoutManager.findFirstVisibleItemPosition();
    }

    private RectF findTopRect(Integer num) {
        PinchAnimInfo pinchAnimInfo = this.mPinchAnimInfo.get(num);
        if (pinchAnimInfo != null) {
            return pinchAnimInfo.getRectMap().getTopRect();
        }
        return null;
    }

    private ListViewHolder getChildViewHolder(int i2) {
        View childAt = this.mLayoutManager.getChildAt(i2 - this.mFirstVisibleViewPosition);
        if (childAt != null) {
            return (ListViewHolder) this.mListView.getChildViewHolder(childAt);
        }
        return null;
    }

    private int getCurrentSpanCount() {
        if (!this.mListView.getGridSpans().hasRealRatio) {
            return this.mLayoutManager.getSpanCount();
        }
        if (this.mLayoutManager.getHintSpanCount(1) == this.mLayoutManager.getSpanCount()) {
            return 1;
        }
        return this.mLayoutManager.getSpanCount();
    }

    private int getRow(int i2, int i7, boolean z) {
        PinchRange pinchRange;
        PinchAnimInfo pinchAnimInfo = this.mPinchAnimInfo.get(Integer.valueOf(i7));
        if (pinchAnimInfo != null) {
            pinchRange = pinchAnimInfo.getRange();
        } else {
            pinchRange = null;
        }
        if (pinchRange != null) {
            return pinchRange.getRow(i2, z);
        }
        return -1;
    }

    private boolean isFakeView(int i2, int i7) {
        int currentSpanCount = getCurrentSpanCount();
        return !isShowing(i2, i7, getStartOffset(currentSpanCount), currentSpanCount);
    }

    private boolean isHeaderPosition(int i2) {
        if (!this.mLayoutManager.hasHeader() || i2 != 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$calculateExtraSpace$1(RelativeRange relativeRange, PinchAnimInfo pinchAnimInfo) {
        pinchAnimInfo.getRange().calculateExtraRows(this.mLayoutManager, relativeRange);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ PinchRange[] lambda$createDataItems$0(int i2) {
        return new PinchRange[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ PinchRange[] lambda$createDividerItems$2(int i2) {
        return new PinchRange[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDividerItems$3(RelativeRange relativeRange, ArrayList arrayList, Integer num) {
        for (int i2 = 0; i2 < relativeRange.getRowCount(); i2++) {
            int row = getRow(relativeRange.getRow(i2), num.intValue(), false);
            createDividerItem(arrayList, num.intValue(), row, findViewPosition(num.intValue(), row, 0));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$createDividerItems$4(PinchItem pinchItem) {
        if (pinchItem.getViewType() != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHeaderViewRect$5(PinchItem pinchItem, float f, Integer num) {
        if (pinchItem.getRect(num.intValue()) == null) {
            RectF rectF = new RectF(findTopRect(num));
            rectF.offset(-rectF.left, -f);
            pinchItem.setRect(num.intValue(), rectF);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateHeaderViewRect$6(PinchItem pinchItem) {
        float f;
        RectF anyValidRectF = pinchItem.getAnyValidRectF();
        if (anyValidRectF != null) {
            f = anyValidRectF.height();
        } else {
            f = 0.0f;
        }
        this.mPinchAnimInfo.keySet().iterator().forEachRemaining(new b(this, pinchItem, f));
    }

    private void updateBaseGrid() {
        if (this.mBaseGrid < 0) {
            this.mBaseGrid = this.mPinchAnimInfo.values().stream().map(new a(22)).mapToInt(new C0545b(10)).min().orElse(3);
        }
    }

    private void updateFakeView(int i2, int i7, PinchItem pinchItem) {
        if (i2 == getCurrentSpanCount()) {
            pinchItem.setFakeView(isFakeView(i7, getStartOffset(i2)));
        }
    }

    private void updateHeaderViewRect(ArrayList<PinchItem> arrayList) {
        arrayList.stream().filter(new C0571z(23)).forEach(new E9.a(29, this));
    }

    private void updateViewHolder(PinchItem pinchItem, int i2, int i7) {
        if (isRecyclerViewItem(pinchItem, i2)) {
            ListViewHolder childViewHolder = getChildViewHolder(i7);
            if (childViewHolder != null) {
                pinchItem.setViewHolder(childViewHolder);
            } else {
                pinchItem.setFakeView(true);
            }
        }
    }

    private void updateViewType(PinchItem pinchItem, int i2, int i7) {
        if (i7 > -1 && pinchItem.getViewType() == null) {
            pinchItem.setViewType(this.mLayoutManager.getHintItemViewType(i7, i2));
        }
    }

    public PinchItemBuilder base(int i2) {
        this.mBaseGrid = i2;
        return this;
    }

    public ArrayList<PinchItem> build() {
        ArrayList<PinchItem> arrayList = new ArrayList<>();
        updateBaseGrid();
        arrayList.addAll(createDataItems(this.bitmapCache));
        arrayList.addAll(createDividerItems());
        return arrayList;
    }

    public PinchItem createDivider(int i2) {
        if (!isHeaderPosition(i2)) {
            return new DividerItem();
        }
        if (skipHeaderItem()) {
            return null;
        }
        return new HeaderItem();
    }

    public PinchItemBuilder dividerGroup(SparseArray<Integer> sparseArray) {
        this.mDividerGroup = sparseArray;
        return this;
    }

    public int findDataPosition(int i2, int i7) {
        if (i7 > 0) {
            return this.mLayoutManager.getHintDataPosition(i7, i2);
        }
        return -1;
    }

    public RectF findRect(int i2, int i7, int i8) {
        PinchAnimInfo pinchAnimInfo = this.mPinchAnimInfo.get(Integer.valueOf(i8));
        if (pinchAnimInfo != null) {
            return pinchAnimInfo.getRectMap().get(i2, i7);
        }
        return null;
    }

    public int findViewPosition(int i2, int i7, int i8) {
        PinchAnimInfo pinchAnimInfo = this.mPinchAnimInfo.get(Integer.valueOf(i2));
        if (pinchAnimInfo != null) {
            return pinchAnimInfo.getRange().findViewPosition(i7, i8);
        }
        return -1;
    }

    public int getStartOffset(int i2) {
        PinchAnimInfo pinchAnimInfo = this.mPinchAnimInfo.get(Integer.valueOf(i2));
        if (pinchAnimInfo != null) {
            return pinchAnimInfo.getStartOffset();
        }
        return 0;
    }

    public boolean isRecyclerViewItem(PinchItem pinchItem, int i2) {
        if (getCurrentSpanCount() != i2 || pinchItem.isFakeView()) {
            return false;
        }
        return true;
    }

    public boolean isShowing(int i2, int i7, int i8, int i10) {
        PinchRange pinchRange;
        PinchAnimInfo pinchAnimInfo = this.mPinchAnimInfo.get(Integer.valueOf(i10));
        if (pinchAnimInfo != null) {
            pinchRange = pinchAnimInfo.getRange();
        } else {
            pinchRange = null;
        }
        if (pinchRange == null || i2 < pinchRange.getStartRow() || i2 > pinchRange.getEndRow() || i7 < i8 || pinchRange.getColumnCount(i2) <= i7) {
            return false;
        }
        return true;
    }

    public PinchItemBuilder setBitmapCache(BitmapCache bitmapCache2) {
        this.bitmapCache = bitmapCache2;
        return this;
    }

    public boolean skipHeaderItem() {
        return false;
    }

    public void updateDataItem(PinchItem pinchItem, int i2, int i7, int i8) {
        int i10;
        int startOffset = getStartOffset(i2);
        if (i8 >= startOffset) {
            i10 = findViewPosition(i2, i7, i8 - startOffset);
        } else {
            i10 = -1;
        }
        pinchItem.setViewPosition(i2, i10);
        pinchItem.setDataPosition(i2, findDataPosition(i2, i10));
        pinchItem.setRect(i2, findRect(i7, i8, i2));
        boolean isShowing = isShowing(i7, i8, startOffset, i2);
        pinchItem.setShowing(i2, isShowing);
        if (isShowing) {
            updateViewHolder(pinchItem, i2, i10);
            updateViewType(pinchItem, i2, i10);
        }
    }

    public void updateDividerItem(PinchItem pinchItem, int i2, int i7, int i8) {
        boolean isShowing = isShowing(i7, 0, 0, i2);
        pinchItem.setShowing(i2, isShowing);
        if (isShowing) {
            updateViewType(pinchItem, i2, i8);
            updateViewHolder(pinchItem, i2, i8);
        }
        pinchItem.setDataPosition(i2, -1);
        pinchItem.setViewPosition(i2, i8);
        pinchItem.setRect(i2, findRect(i7, 0, i2));
    }
}
