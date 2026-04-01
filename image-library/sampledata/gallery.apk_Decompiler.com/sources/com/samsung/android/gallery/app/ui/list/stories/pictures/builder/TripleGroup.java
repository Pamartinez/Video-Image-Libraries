package com.samsung.android.gallery.app.ui.list.stories.pictures.builder;

import android.widget.RelativeLayout;
import com.samsung.android.gallery.module.dataset.chapter.Align;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TripleGroup extends Group {
    private void setLayoutSize(RelativeLayout.LayoutParams layoutParams) {
        layoutParams.width = -1;
        layoutParams.height = getSizeByHeightRatio(this.mDimenValues.mImage3ByHeight);
    }

    private void setSideMargin(Align align, RelativeLayout.LayoutParams layoutParams) {
        int sizeByWidthRatio = getSizeByWidthRatio(this.mDimenValues.mItemHMargin);
        int sizeByWidthRatio2 = getSizeByWidthRatio(this.mDimenValues.mItem3ByGap) / 2;
        if (align.start()) {
            setSideMargin(layoutParams, sizeByWidthRatio, sizeByWidthRatio2);
        } else if (align.end()) {
            setSideMargin(layoutParams, sizeByWidthRatio2, sizeByWidthRatio);
        } else {
            setSideMargin(layoutParams, sizeByWidthRatio2, sizeByWidthRatio2);
        }
    }

    public void buildLayout(LayoutInfo layoutInfo, int i2, RelativeLayout.LayoutParams layoutParams) {
        super.buildLayout(layoutInfo, i2, layoutParams);
        setSideMargin(layoutInfo.align, layoutParams);
        setLayoutSize(layoutParams);
    }

    public int getItemViewWidth(LayoutInfo layoutInfo) {
        Align align = layoutInfo.align;
        if (align.start()) {
            return this.mDimenValues.mItem3ByStartWidth;
        }
        if (align.middle()) {
            return this.mDimenValues.mItem3ByMiddleWidth;
        }
        if (align.end()) {
            return this.mDimenValues.mItem3ByEndWidth;
        }
        return this.mDimenValues.mItem3ByMiddleWidth;
    }

    private void setSideMargin(RelativeLayout.LayoutParams layoutParams, int i2, int i7) {
        layoutParams.setMarginStart(i2);
        layoutParams.setMarginEnd(i7);
    }
}
