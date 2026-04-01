package com.samsung.android.gallery.widget.listview.pinch.v3;

import android.view.View;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FocusFrom implements IFocused {
    private float mFocusedX;
    private float mFocusedY;
    private float mFocusedYInView;
    private final int mGridSize;
    private float mScrollOffset;
    private int mViewPosition;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Finder {
        private final PinchLayoutManager mLayoutManager;

        public Finder(PinchLayoutManager pinchLayoutManager) {
            this.mLayoutManager = pinchLayoutManager;
        }

        private FocusFrom findFirstData(int i2) {
            FocusFrom focusFrom = new FocusFrom(i2, 0);
            int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
            while (findFirstVisibleItemPosition <= this.mLayoutManager.findLastVisibleItemPosition()) {
                View findViewByPosition = this.mLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
                if (findViewByPosition == null || !isData(this.mLayoutManager.getHintItemViewType(findFirstVisibleItemPosition, i2))) {
                    findFirstVisibleItemPosition++;
                } else {
                    View view = findViewByPosition;
                    View view2 = view;
                    focusFrom.update(findFirstVisibleItemPosition, view.getY(), view2.getX(), view2.getY(), 0.0f);
                    return focusFrom;
                }
            }
            return focusFrom;
        }

        private FocusFrom findNearestData(float[] fArr, int i2) {
            float[] fArr2 = fArr;
            int i7 = i2;
            FocusFrom focusFrom = new FocusFrom(i7, 0);
            double d = Double.MAX_VALUE;
            for (int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= this.mLayoutManager.findLastVisibleItemPosition(); findFirstVisibleItemPosition++) {
                View findViewByPosition = this.mLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
                int hintItemViewType = this.mLayoutManager.getHintItemViewType(findFirstVisibleItemPosition, i7);
                if (findViewByPosition != null && !ViewHolderValue.isDivider(hintItemViewType)) {
                    double distance = getDistance(fArr2, findViewByPosition);
                    if (distance < d) {
                        focusFrom.update(findFirstVisibleItemPosition, findViewByPosition.getY(), fArr2[0], Math.min((float) findViewByPosition.getBottom(), fArr2[1]), fArr2[1] - findViewByPosition.getY());
                        if (hasFocusedPoint(findViewByPosition, fArr2)) {
                            break;
                        } else if (ViewHolderValue.isHeader(hintItemViewType)) {
                            d = Double.MAX_VALUE;
                        } else {
                            d = distance;
                        }
                    } else {
                        continue;
                    }
                }
            }
            return focusFrom;
        }

        private double getDistance(float[] fArr, View view) {
            float width = (((float) view.getWidth()) / 2.0f) + view.getX();
            float height = (((float) view.getHeight()) / 2.0f) + view.getY();
            return Math.sqrt(Math.pow((double) (height - fArr[1]), 2.0d) + Math.pow((double) (width - fArr[0]), 2.0d));
        }

        private boolean hasFocusedPoint(View view, float[] fArr) {
            if (view.getX() >= fArr[0] || view.getX() + ((float) view.getWidth()) <= fArr[0] || view.getY() >= fArr[1] || view.getY() + ((float) view.getHeight()) <= fArr[1]) {
                return false;
            }
            return true;
        }

        private boolean isData(int i2) {
            if (i2 < 0 || i2 > 4) {
                return false;
            }
            return true;
        }

        private boolean needToSelectFirstItem(float[] fArr) {
            if (this.mLayoutManager.findFirstVisibleItemPosition() == 0 || fArr == null) {
                return true;
            }
            return false;
        }

        public FocusFrom find(float[] fArr, int i2) {
            if (needToSelectFirstItem(fArr)) {
                return findFirstData(i2);
            }
            return findNearestData(fArr, i2);
        }
    }

    public /* synthetic */ FocusFrom(int i2, int i7) {
        this(i2);
    }

    public float getFocusedX() {
        return this.mFocusedX;
    }

    public float getFocusedY() {
        return this.mFocusedY;
    }

    public float getFocusedYInView() {
        return this.mFocusedYInView;
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

    public void setFocusedX(float f) {
        this.mFocusedX = f;
    }

    public void update(int i2, float f, float f5, float f8, float f10) {
        this.mViewPosition = i2;
        this.mScrollOffset = f;
        this.mFocusedX = f5;
        this.mFocusedY = f8;
        this.mFocusedYInView = f10;
    }

    private FocusFrom(int i2) {
        this.mGridSize = i2;
    }
}
