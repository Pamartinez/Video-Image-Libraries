package com.samsung.android.gallery.widget.listview.pinch.v3;

import android.graphics.RectF;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleGridItemBuilder extends PinchItemBuilder {
    private BitmapCache bitmapCache;
    private boolean mIsConcat;
    private boolean mIsFakeView;
    private final PinchAnimInfo mPinchAnimInfo;
    private boolean mSkipHeader;

    public SingleGridItemBuilder(PinchLayoutManager pinchLayoutManager, IPinchRecyclerView iPinchRecyclerView, PinchAnimInfo pinchAnimInfo) {
        super(pinchLayoutManager, iPinchRecyclerView, (HashMap<Integer, PinchAnimInfo>) null);
        this.mPinchAnimInfo = pinchAnimInfo;
    }

    private void addDividerItem(ArrayList<PinchItem> arrayList, int i2) {
        int gridSize = this.mPinchAnimInfo.getFocused().getGridSize();
        int findViewPosition = findViewPosition(gridSize, i2, 0);
        PinchItem createDivider = createDivider(findViewPosition);
        if (createDivider != null) {
            createDivider.setFakeView(this.mIsFakeView);
            updateDividerItem(createDivider, gridSize, i2, findViewPosition);
            arrayList.add(createDivider);
        }
    }

    private PinchItem createDataItem(int i2, int i7) {
        PinchItem pinchItem;
        int gridSize = this.mPinchAnimInfo.getFocused().getGridSize();
        if (this.mIsConcat) {
            pinchItem = new ConcatItem();
        } else {
            pinchItem = new DataItem(this.bitmapCache, gridSize);
        }
        pinchItem.setFakeView(this.mIsFakeView);
        updateDataItem(pinchItem, gridSize, i2, i7);
        return pinchItem;
    }

    public SingleGridItemBuilder bitmapCache(BitmapCache bitmapCache2) {
        this.bitmapCache = bitmapCache2;
        return this;
    }

    public ArrayList<PinchItem> build() {
        ArrayList<PinchItem> arrayList = new ArrayList<>();
        PinchRange range = this.mPinchAnimInfo.getRange();
        for (int startRow = range.getStartRow(); startRow <= range.getEndRow(); startRow++) {
            if (ViewHolderValue.isData(range.getViewType(startRow))) {
                for (int i2 = 0; i2 < range.getColumnCount(startRow); i2++) {
                    arrayList.add(createDataItem(startRow, i2));
                }
            } else {
                addDividerItem(arrayList, startRow);
            }
        }
        return arrayList;
    }

    public SingleGridItemBuilder concat(boolean z) {
        this.mIsConcat = z;
        return this;
    }

    public SingleGridItemBuilder fake(boolean z) {
        this.mIsFakeView = z;
        return this;
    }

    public RectF findRect(int i2, int i7, int i8) {
        return this.mPinchAnimInfo.getRectMap().get(i2, i7);
    }

    public int findViewPosition(int i2, int i7, int i8) {
        return this.mPinchAnimInfo.getRange().findViewPosition(i7, i8);
    }

    public int getStartOffset(int i2) {
        return 0;
    }

    public boolean isRecyclerViewItem(PinchItem pinchItem, int i2) {
        return !pinchItem.isFakeView();
    }

    public boolean isShowing(int i2, int i7, int i8, int i10) {
        return true;
    }

    public SingleGridItemBuilder skipHeader(boolean z) {
        this.mSkipHeader = z;
        return this;
    }

    public boolean skipHeaderItem() {
        return this.mSkipHeader;
    }
}
