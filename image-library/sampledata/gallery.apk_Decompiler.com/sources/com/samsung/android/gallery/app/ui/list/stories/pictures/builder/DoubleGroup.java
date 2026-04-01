package com.samsung.android.gallery.app.ui.list.stories.pictures.builder;

import android.widget.RelativeLayout;
import com.samsung.android.gallery.module.dataset.chapter.Layout;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DoubleGroup extends Group {
    private int getBottomMargin(Layout layout, int i2) {
        if (!layout.small()) {
            return getSizeByHeightRatio(this.mDimenValues.mItemVMargin);
        }
        return (i2 - getSizeByHeightRatio(this.mDimenValues.mImage2ByLargeHeight)) / 2;
    }

    private void setLayoutRule(RelativeLayout.LayoutParams layoutParams, LayoutInfo layoutInfo) {
        if (layoutInfo.align.start()) {
            layoutParams.addRule(20);
        } else {
            layoutParams.addRule(21);
        }
        if (layoutInfo.layoutType.small()) {
            layoutParams.addRule(12);
        }
    }

    private void setLayoutSize(RelativeLayout.LayoutParams layoutParams, Layout layout) {
        if (layout.large()) {
            layoutParams.width = getSizeByWidthRatio(this.mDimenValues.mImage2ByLargeWidth);
            layoutParams.height = getSizeByHeightRatio(this.mDimenValues.mImage2ByLargeHeight);
        } else if (layout.small()) {
            layoutParams.width = getSizeByWidthRatio(this.mDimenValues.mImage2BySmallWidth);
            layoutParams.height = getSizeByHeightRatio(this.mDimenValues.mImage2BySmallHeight);
        } else if (layout.average()) {
            layoutParams.width = getSizeByWidthRatio(this.mDimenValues.mImage2ByAvgWidth);
            layoutParams.height = getSizeByHeightRatio(this.mDimenValues.mImage2ByAvgHeight);
        }
    }

    private void setMargin(RelativeLayout.LayoutParams layoutParams, LayoutInfo layoutInfo, int i2) {
        if (layoutInfo.align.start()) {
            layoutParams.setMarginStart(getSizeByWidthRatio(this.mDimenValues.mItemHMargin));
        } else {
            layoutParams.setMarginEnd(getSizeByWidthRatio(this.mDimenValues.mItemHMargin));
        }
        layoutParams.topMargin = getSizeByHeightRatio(this.mDimenValues.mItemVMargin);
        layoutParams.bottomMargin = getBottomMargin(layoutInfo.layoutType, i2);
    }

    public void buildLayout(LayoutInfo layoutInfo, int i2, RelativeLayout.LayoutParams layoutParams) {
        super.buildLayout(layoutInfo, i2, layoutParams);
        setLayoutRule(layoutParams, layoutInfo);
        setMargin(layoutParams, layoutInfo, i2);
        setLayoutSize(layoutParams, layoutInfo.layoutType);
    }

    public int getItemViewWidth(LayoutInfo layoutInfo) {
        Layout layout = layoutInfo.layoutType;
        if (layout.average()) {
            return this.mDimenValues.mItem2ByAvgWidth;
        }
        if (layout.large()) {
            return this.mDimenValues.mItem2ByLargeWidth;
        }
        if (layout.small()) {
            return this.mDimenValues.mItem2BySmallWidth;
        }
        return this.mDimenValues.mItem2ByAvgWidth;
    }
}
