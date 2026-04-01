package com.samsung.android.gallery.app.ui.list.stories.pictures.builder;

import android.widget.RelativeLayout;
import com.samsung.android.gallery.module.dataset.chapter.Layout;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SingleGroup extends Group {
    private int[] getFixedViewSize(LayoutInfo layoutInfo, RelativeLayout.LayoutParams layoutParams) {
        int marginStart = (this.mSpace - layoutParams.getMarginStart()) - layoutParams.getMarginEnd();
        int i2 = (this.mSpace - layoutParams.topMargin) - layoutParams.bottomMargin;
        if (((float) layoutInfo.width) / ((float) layoutInfo.height) >= 1.0f) {
            return new int[]{marginStart, (int) (((float) marginStart) * getViewRatio())};
        }
        return new int[]{(int) (((float) i2) * getViewRatio()), i2};
    }

    private float getViewRatio() {
        DimenValues dimenValues = this.mDimenValues;
        return ((float) dimenValues.mImage1ByHeight) / ((float) dimenValues.mImage1ByWidth);
    }

    private void setLayoutRule(RelativeLayout.LayoutParams layoutParams, LayoutInfo layoutInfo) {
        if (layoutInfo.layoutType.full()) {
            layoutParams.addRule(13);
        } else if (layoutInfo.align.start()) {
            layoutParams.addRule(20);
        } else if (layoutInfo.align.end()) {
            layoutParams.addRule(21);
        } else {
            layoutParams.addRule(13);
        }
    }

    private void setLayoutSize(RelativeLayout.LayoutParams layoutParams, LayoutInfo layoutInfo) {
        Layout layout = layoutInfo.layoutType;
        if (layout.large()) {
            layoutParams.width = getSizeByWidthRatio(this.mDimenValues.mImage2ByLargeWidth);
            layoutParams.height = getSizeByHeightRatio(this.mDimenValues.mImage2ByLargeHeight);
        } else if (layout.full()) {
            layoutParams.width = -1;
            layoutParams.height = -1;
        } else {
            int[] fixedViewSize = getFixedViewSize(layoutInfo, layoutParams);
            int i2 = fixedViewSize[0];
            layoutParams.width = i2;
            int i7 = fixedViewSize[1];
            layoutParams.height = i7;
            if (i2 < i7) {
                layoutParams.setMarginStart((this.mSpace - i2) / 2);
            }
        }
    }

    private void setMargin(RelativeLayout.LayoutParams layoutParams) {
        layoutParams.setMarginStart(getSizeByWidthRatio(this.mDimenValues.mItemHMargin));
        layoutParams.setMarginEnd(getSizeByWidthRatio(this.mDimenValues.mItemHMargin));
        layoutParams.topMargin = getSizeByHeightRatio(this.mDimenValues.mItemVMargin);
        layoutParams.bottomMargin = getSizeByHeightRatio(this.mDimenValues.mItemVMargin);
    }

    public void buildLayout(LayoutInfo layoutInfo, int i2, RelativeLayout.LayoutParams layoutParams) {
        super.buildLayout(layoutInfo, i2, layoutParams);
        setLayoutRule(layoutParams, layoutInfo);
        setMargin(layoutParams);
        setLayoutSize(layoutParams, layoutInfo);
    }

    public int getItemViewWidth(LayoutInfo layoutInfo) {
        return this.mDimenValues.mItemBaseWidth;
    }
}
