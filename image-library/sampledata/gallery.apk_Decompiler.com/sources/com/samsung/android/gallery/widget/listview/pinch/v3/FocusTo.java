package com.samsung.android.gallery.widget.listview.pinch.v3;

import com.samsung.android.gallery.widget.listview.PinchLayoutManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FocusTo implements IFocused {
    /* access modifiers changed from: private */
    public int mGridSize;
    /* access modifiers changed from: private */
    public float mScrollOffset;
    /* access modifiers changed from: private */
    public int mViewPosition;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Finder {
        private final IFocused baseFocused;
        private int gridSize;
        private final PinchLayoutManager layoutManager;
        private boolean useConcatThumbnail;

        public Finder(PinchLayoutManager pinchLayoutManager, IFocused iFocused) {
            this.layoutManager = pinchLayoutManager;
            this.baseFocused = iFocused;
        }

        private int findViewPosition() {
            if (this.gridSize == this.baseFocused.getGridSize()) {
                return this.baseFocused.getViewPosition();
            }
            return this.layoutManager.getHintViewPosition(getDataPosition(), this.gridSize);
        }

        private int getDataPosition() {
            if (this.useConcatThumbnail) {
                return getDataPositionInThumbnail();
            }
            return getDataPositionInGridView();
        }

        private int getDataPositionInGridView() {
            return this.layoutManager.getHintDataPosition(this.baseFocused.getViewPosition(), this.baseFocused.getGridSize());
        }

        private int getDataPositionInThumbnail() {
            return this.layoutManager.getHintDataPosition(this.baseFocused.getViewPosition(), this.baseFocused.getFocusedX(), this.baseFocused.getFocusedYInView(), this.baseFocused.getGridSize());
        }

        private float getScrollOffset() {
            if (!this.useConcatThumbnail || this.baseFocused.getScrollOffset() >= 0.0f) {
                return this.baseFocused.getScrollOffset();
            }
            return this.baseFocused.getFocusedY();
        }

        public FocusTo find() {
            FocusTo focusTo = new FocusTo();
            focusTo.mGridSize = this.gridSize;
            focusTo.mViewPosition = findViewPosition();
            focusTo.mScrollOffset = getScrollOffset();
            return focusTo;
        }

        public Finder target(int i2) {
            this.gridSize = i2;
            return this;
        }

        public Finder useConcatThumbnail(boolean z) {
            this.useConcatThumbnail = z;
            return this;
        }
    }

    public int getGridSize() {
        return this.mGridSize;
    }

    public float getScrollOffset() {
        return this.mScrollOffset;
    }

    public int getViewPosition() {
        return this.mViewPosition;
    }
}
