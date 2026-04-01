package com.samsung.android.gallery.app.ui.list.stories.viewholder;

import android.content.res.Resources;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.DimenHelper;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DimenHelperV2 extends DimenHelper {
    public DimenHelperV2(Resources resources) {
        super(resources);
    }

    public int getBorderRadius(int i2, boolean z) {
        Integer num;
        if (this.mBorderRadius == null) {
            DimenHelper.DimenValue<Integer> dimenValue = new DimenHelper.DimenValue<>();
            this.mBorderRadius = dimenValue;
            dimenValue.mDrawerOpenValue = Integer.valueOf(getDimen(R.dimen.stories_item_round_radius_1_depth));
            this.mBorderRadius.mPinchDepthValue = new Integer[]{Integer.valueOf(getDimen(R.dimen.stories_item_round_radius_1_depth)), Integer.valueOf(getDimen(R.dimen.stories_item_round_radius_2_depth))};
        }
        DimenHelper.DimenValue<Integer> dimenValue2 = this.mBorderRadius;
        if (z) {
            num = (Integer) dimenValue2.mDrawerOpenValue;
        } else {
            num = ((Integer[]) dimenValue2.mPinchDepthValue)[i2];
        }
        return num.intValue();
    }

    public int getSideGap(int i2, boolean z) {
        Integer num;
        if (this.mSideGap == null) {
            DimenHelper.DimenValue<Integer> dimenValue = new DimenHelper.DimenValue<>();
            this.mSideGap = dimenValue;
            dimenValue.mDrawerOpenValue = Integer.valueOf(getDimen(R.dimen.stories_item_padding));
            this.mSideGap.mPinchDepthValue = new Integer[]{Integer.valueOf(getDimen(R.dimen.stories_item_padding)), Integer.valueOf(getDimen(R.dimen.stories_item_padding))};
        }
        DimenHelper.DimenValue<Integer> dimenValue2 = this.mSideGap;
        if (z) {
            num = (Integer) dimenValue2.mDrawerOpenValue;
        } else {
            num = ((Integer[]) dimenValue2.mPinchDepthValue)[i2];
        }
        return num.intValue();
    }

    public int getSubTitleTextSize(int i2, boolean z) {
        Integer num;
        if (this.mSubTitleTextSize == null) {
            DimenHelper.DimenValue<Integer> dimenValue = new DimenHelper.DimenValue<>();
            this.mSubTitleTextSize = dimenValue;
            dimenValue.mDrawerOpenValue = Integer.valueOf(getDimen(R.dimen.stories_view_duration_text_size_drawer_60));
            this.mSubTitleTextSize.mPinchDepthValue = new Integer[]{Integer.valueOf(getDimen(R.dimen.stories_view_duration_text_size_1_depth_60)), Integer.valueOf(getDimen(R.dimen.stories_view_duration_text_size_2_depth_60))};
        }
        DimenHelper.DimenValue<Integer> dimenValue2 = this.mSubTitleTextSize;
        if (z) {
            num = (Integer) dimenValue2.mDrawerOpenValue;
        } else {
            num = ((Integer[]) dimenValue2.mPinchDepthValue)[i2];
        }
        return num.intValue();
    }

    public float getTitleBottomMargin(int i2, boolean z) {
        Float f;
        if (this.mTitleBottomMargin == null) {
            DimenHelper.DimenValue<Float> dimenValue = new DimenHelper.DimenValue<>();
            this.mTitleBottomMargin = dimenValue;
            dimenValue.mDrawerOpenValue = Float.valueOf(getRatioDimen(R.dimen.stories_item_title_con_bottom_margin_ratio_drawer_60));
            this.mTitleBottomMargin.mPinchDepthValue = new Float[]{Float.valueOf(getRatioDimen(R.dimen.stories_item_title_con_bottom_margin_ratio_1_depth_60)), Float.valueOf(getRatioDimen(R.dimen.stories_item_title_con_bottom_margin_ratio_2_depth_60))};
        }
        DimenHelper.DimenValue<Float> dimenValue2 = this.mTitleBottomMargin;
        if (z) {
            f = (Float) dimenValue2.mDrawerOpenValue;
        } else {
            f = ((Float[]) dimenValue2.mPinchDepthValue)[i2];
        }
        return f.floatValue();
    }

    public float getTitleHorizontalMargin(int i2, boolean z) {
        Float f;
        if (this.mTitleHorizontalMargin == null) {
            DimenHelper.DimenValue<Float> dimenValue = new DimenHelper.DimenValue<>();
            this.mTitleHorizontalMargin = dimenValue;
            dimenValue.mDrawerOpenValue = Float.valueOf(getRatioDimen(R.dimen.stories_item_title_con_horizontal_margin_ratio_drawer_60));
            this.mTitleHorizontalMargin.mPinchDepthValue = new Float[]{Float.valueOf(getRatioDimen(R.dimen.stories_item_title_con_horizontal_margin_ratio_1_depth_60)), Float.valueOf(getRatioDimen(R.dimen.stories_item_title_con_horizontal_margin_ratio_2_depth_60))};
        }
        DimenHelper.DimenValue<Float> dimenValue2 = this.mTitleHorizontalMargin;
        if (z) {
            f = (Float) dimenValue2.mDrawerOpenValue;
        } else {
            f = ((Float[]) dimenValue2.mPinchDepthValue)[i2];
        }
        return f.floatValue();
    }

    public int getTitleTextSize(int i2, boolean z) {
        Integer num;
        if (this.mTitleTextSize == null) {
            DimenHelper.DimenValue<Integer> dimenValue = new DimenHelper.DimenValue<>();
            this.mTitleTextSize = dimenValue;
            dimenValue.mDrawerOpenValue = Integer.valueOf(getDimen(R.dimen.stories_view_title_text_size_drawer_60));
            this.mTitleTextSize.mPinchDepthValue = new Integer[]{Integer.valueOf(getDimen(R.dimen.stories_view_title_text_size_1_depth_60)), Integer.valueOf(getDimen(R.dimen.stories_view_title_text_size_2_depth_60))};
        }
        DimenHelper.DimenValue<Integer> dimenValue2 = this.mTitleTextSize;
        if (z) {
            num = (Integer) dimenValue2.mDrawerOpenValue;
        } else {
            num = ((Integer[]) dimenValue2.mPinchDepthValue)[i2];
        }
        return num.intValue();
    }
}
