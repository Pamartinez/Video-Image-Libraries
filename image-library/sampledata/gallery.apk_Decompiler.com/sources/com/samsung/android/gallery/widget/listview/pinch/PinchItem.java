package com.samsung.android.gallery.widget.listview.pinch;

import Ib.b;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchItem {
    private RectF mFromRect;
    private final int mFromViewPosition;
    private final GridInfo mGridInfo;
    private int mItemViewType;
    private int mToHeight;
    private RectF mToRect;
    private int mToSpanIndex;
    private int mToSpanSize;
    private int mToViewPosition;
    private int mToWidth;
    private final View mView;

    public PinchItem(View view, int i2, GridInfo gridInfo) {
        this.mItemViewType = -1;
        this.mToSpanIndex = 0;
        this.mToSpanSize = -1;
        this.mToWidth = -1;
        this.mToHeight = -1;
        this.mView = view;
        this.mGridInfo = gridInfo;
        this.mFromViewPosition = i2;
        this.mToViewPosition = i2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$isReverse$0(int[] iArr, int i2) {
        if (iArr[i2] == this.mGridInfo.from()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$isReverse$1(int[] iArr, int i2) {
        if (iArr[i2] == this.mGridInfo.to()) {
            return true;
        }
        return false;
    }

    public void calculateFromRect() {
        float f;
        float f5;
        float f8;
        float f10;
        View view = this.mView;
        if (view != null) {
            f10 = view.getX();
            f8 = this.mView.getY();
            f5 = (float) this.mView.getWidth();
            f = (float) this.mView.getHeight();
        } else {
            f10 = 0.0f;
            f8 = 0.0f;
            f5 = 0.0f;
            f = 0.0f;
        }
        this.mFromRect = new RectF(f10, f8, f5 + f10, f + f8);
    }

    public void calculateTo(PinchLayoutManager pinchLayoutManager, AnimPositionCache animPositionCache, View view) {
        int hintSpanCount = animPositionCache.getHintSpanCount(pinchLayoutManager, this.mGridInfo.to());
        if (ViewHolderValue.isData(this.mItemViewType)) {
            int hintViewPosition = animPositionCache.getHintViewPosition(pinchLayoutManager, this.mFromViewPosition, this.mGridInfo.from(), this.mGridInfo.to());
            this.mToViewPosition = hintViewPosition;
            this.mToSpanIndex = animPositionCache.getHintStartSpan(pinchLayoutManager, hintViewPosition, this.mGridInfo.to());
            this.mToSpanSize = animPositionCache.getHintColumnSpan(pinchLayoutManager, this.mToViewPosition, this.mGridInfo.to());
            if (view != null) {
                this.mToHeight = view.getHeight();
                this.mToWidth = view.getWidth();
                return;
            }
            this.mToHeight = animPositionCache.getHintViewHeight(pinchLayoutManager, this.mToViewPosition, this.mGridInfo.to());
            this.mToWidth = (int) ((((float) this.mToSpanSize) / ((float) hintSpanCount)) * ((float) pinchLayoutManager.getHintWidthSpace(this.mGridInfo.to())));
            return;
        }
        this.mToSpanSize = hintSpanCount;
        if (view != null) {
            this.mToHeight = view.getHeight();
            this.mToWidth = view.getWidth();
        }
    }

    public void calculateToPosition(PinchLayoutManager pinchLayoutManager, AnimPositionCache animPositionCache) {
        if (ViewHolderValue.isData(this.mItemViewType)) {
            this.mToViewPosition = animPositionCache.getHintViewPosition(pinchLayoutManager, this.mFromViewPosition, this.mGridInfo.from(), this.mGridInfo.to());
        }
    }

    public void calculateToRect(float f, float f5) {
        this.mToRect = new RectF(f, f5, ((float) this.mToWidth) + f, ((float) this.mToHeight) + f5);
    }

    public RectF getFromRect() {
        return this.mFromRect;
    }

    public int getFromViewPosition() {
        return this.mFromViewPosition;
    }

    public int getItemViewType() {
        return this.mItemViewType;
    }

    public int getToHeight() {
        return this.mToHeight;
    }

    public RectF getToRect() {
        return this.mToRect;
    }

    public int getToSpanIndex() {
        return this.mToSpanIndex;
    }

    public int getToSpanSize() {
        return this.mToSpanSize;
    }

    public int getToViewPosition() {
        return this.mToViewPosition;
    }

    public View getView() {
        return this.mView;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [int[], java.io.Serializable] */
    public boolean isReverse() {
        ? activeColumns = this.mGridInfo.getActiveColumns();
        if (IntStream.range(0, activeColumns.length).filter(new b(this, activeColumns, 0)).findFirst().orElse(-1) > IntStream.range(0, activeColumns.length).filter(new b(this, activeColumns, 1)).findFirst().orElse(-1)) {
            return true;
        }
        return false;
    }

    public void setItemViewType(int i2) {
        this.mItemViewType = i2;
    }

    public void calculateToRect(float f, float f5, float f8) {
        this.mToRect = new RectF(f, f5, ((float) this.mToWidth) + f, f8 + f5 + ((float) this.mToHeight));
    }

    public PinchItem(View view, int i2, int i7, GridInfo gridInfo) {
        this(view, i2, gridInfo);
        this.mToViewPosition = i7;
    }
}
