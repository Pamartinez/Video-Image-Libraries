package com.samsung.android.gallery.widget.listview.pinch.v3;

import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchRange;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchAnimInfo {
    /* access modifiers changed from: private */
    public IFocused mFocused;
    /* access modifiers changed from: private */
    public PinchRange mPinchRange;
    /* access modifiers changed from: private */
    public PinchRectMap mPinchRectMap;
    /* access modifiers changed from: private */
    public int mStartOffset;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        private int baseGap;
        private final IFocused focused;
        private int itemGap;
        private final PinchLayoutManager layoutManager;
        private int[] pivotInfo;
        private final AnimPositionCache positionCache;
        private boolean shiftToTop;
        private final GridInfoSupplier supplier;

        public Builder(PinchLayoutManager pinchLayoutManager, AnimPositionCache animPositionCache, IFocused iFocused, GridInfoSupplier gridInfoSupplier) {
            this.focused = iFocused;
            this.layoutManager = pinchLayoutManager;
            this.positionCache = animPositionCache;
            this.supplier = gridInfoSupplier;
        }

        private int calculateStartOffset() {
            int[] iArr = this.pivotInfo;
            if (iArr == null) {
                return 0;
            }
            int max = Math.max(iArr[0], GridValue.revert(this.focused.getGridSize()));
            int[] iArr2 = this.pivotInfo;
            return Math.max(0, Math.min(iArr2[1] - max, iArr2[2] - (max / 2)));
        }

        private PinchRange createPinchRange() {
            return new PinchRange.Builder(this.layoutManager, this.positionCache).refer(this.focused).shiftToTop(this.shiftToTop).build();
        }

        private PinchRectMap createPinchRectMap(PinchRange pinchRange, int i2) {
            int i7;
            int gridSize = this.focused.getGridSize();
            PinchRectMap pinchRectMap = new PinchRectMap(this.layoutManager, this.positionCache, pinchRange, gridSize, this.supplier);
            if (!this.supplier.useConcatThumbnail(gridSize) && (i7 = this.itemGap) > 0) {
                pinchRectMap.setItemGap(i7, this.baseGap);
            }
            pinchRectMap.setStartOffset(i2);
            pinchRectMap.fillDefaultRange();
            return pinchRectMap;
        }

        public PinchAnimInfo build() {
            PinchAnimInfo pinchAnimInfo = new PinchAnimInfo();
            pinchAnimInfo.mFocused = this.focused;
            pinchAnimInfo.mStartOffset = calculateStartOffset();
            pinchAnimInfo.mPinchRange = createPinchRange();
            pinchAnimInfo.mPinchRectMap = createPinchRectMap(pinchAnimInfo.mPinchRange, pinchAnimInfo.mStartOffset);
            pinchAnimInfo.mPinchRange.setStartOffset(pinchAnimInfo.mStartOffset);
            return pinchAnimInfo;
        }

        public Builder itemGap(int i2, int i7) {
            this.itemGap = i2;
            this.baseGap = i7;
            return this;
        }

        public Builder pivotInfo(int[] iArr) {
            this.pivotInfo = iArr;
            return this;
        }

        public Builder shiftToTop(boolean z) {
            this.shiftToTop = z;
            return this;
        }
    }

    public IFocused getFocused() {
        return this.mFocused;
    }

    public PinchRange getRange() {
        return this.mPinchRange;
    }

    public PinchRectMap getRectMap() {
        return this.mPinchRectMap;
    }

    public int getStartOffset() {
        return this.mStartOffset;
    }
}
