package com.samsung.android.gallery.widget.listview;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LimitedLinearLayoutManager extends LinearLayoutManager {
    private final int mMaxCount;

    private int getMaxHeight() {
        View childAt;
        if (getChildCount() == 0 || this.mMaxCount <= 0 || (childAt = getChildAt(0)) == null) {
            return 0;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
        int height = childAt.getHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
        int paddingTop = getPaddingTop();
        return getPaddingBottom() + (height * this.mMaxCount) + paddingTop;
    }

    public void setMeasuredDimension(int i2, int i7) {
        int maxHeight = getMaxHeight();
        if (maxHeight <= 0 || maxHeight >= i7) {
            super.setMeasuredDimension(i2, i7);
        } else {
            super.setMeasuredDimension(i2, maxHeight);
        }
    }
}
