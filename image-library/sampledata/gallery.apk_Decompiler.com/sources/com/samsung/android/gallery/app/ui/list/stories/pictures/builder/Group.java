package com.samsung.android.gallery.app.ui.list.stories.pictures.builder;

import android.widget.RelativeLayout;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Group implements LayoutBuilder {
    protected DimenValues mDimenValues;
    protected int mSpace;

    private float getHeightRatio() {
        return ((float) this.mSpace) / ((float) this.mDimenValues.mItemBaseWidth);
    }

    private void resetLayout(RelativeLayout.LayoutParams layoutParams) {
        layoutParams.removeRule(20);
        layoutParams.removeRule(21);
        layoutParams.removeRule(12);
        layoutParams.removeRule(13);
        layoutParams.setMargins(0, 0, 0, 0);
        layoutParams.setMarginStart(0);
        layoutParams.setMarginEnd(0);
    }

    public void buildLayout(LayoutInfo layoutInfo, int i2, RelativeLayout.LayoutParams layoutParams) {
        this.mSpace = i2;
        resetLayout(layoutParams);
    }

    public abstract int getItemViewWidth(LayoutInfo layoutInfo);

    public final int getSizeByHeightRatio(int i2) {
        return (int) (((float) i2) * getHeightRatio());
    }

    public final int getSizeByWidthRatio(int i2) {
        return (int) (((float) i2) * getWidthRatio());
    }

    public int getTimeViewSpace(int i2) {
        this.mSpace = i2;
        return (i2 - getSizeByWidthRatio(this.mDimenValues.mImage2ByLargeWidth)) - (getSizeByWidthRatio(this.mDimenValues.mItemHMargin) * 2);
    }

    public final float getVerticalGapRatio() {
        DimenValues dimenValues = this.mDimenValues;
        return ((float) dimenValues.mItemVGap) / ((float) dimenValues.mItemBaseHeight);
    }

    public final float getWidthRatio(LayoutInfo layoutInfo) {
        return ((float) getItemViewWidth(layoutInfo)) / ((float) this.mDimenValues.mItemBaseWidth);
    }

    public void init(DimenValues dimenValues) {
        this.mDimenValues = dimenValues;
        this.mSpace = dimenValues.mItemBaseWidth;
    }

    private float getWidthRatio() {
        return ((float) this.mSpace) / ((float) this.mDimenValues.mItemBaseWidth);
    }
}
