package com.samsung.android.gallery.app.ui.list.stories.viewholder;

import android.content.res.Resources;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.DimenHelper;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DimenHelperV85 extends DimenHelperV70 {
    public DimenHelperV85(Resources resources) {
        super(resources);
    }

    public int getBorderRadius(int i2, boolean z) {
        Integer num;
        if (this.mBorderRadius == null) {
            DimenHelper.DimenValue<Integer> dimenValue = new DimenHelper.DimenValue<>();
            this.mBorderRadius = dimenValue;
            dimenValue.mDrawerOpenValue = Integer.valueOf(getDimen(R.dimen.stories_item_round_radius_1_depth));
            this.mBorderRadius.mPinchDepthValue = new Integer[]{Integer.valueOf(getDimen(R.dimen.stories_category_creation_item_round_radius_v85)), Integer.valueOf(getDimen(R.dimen.stories_item_round_radius_1_depth))};
        }
        DimenHelper.DimenValue<Integer> dimenValue2 = this.mBorderRadius;
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
            dimenValue.mDrawerOpenValue = Float.valueOf(getRatioDimen(R.dimen.stories_item_title_con_bottom_margin_ratio_drawer_v85));
            this.mTitleBottomMargin.mPinchDepthValue = new Float[]{Float.valueOf(getRatioDimen(R.dimen.stories_item_title_con_bottom_margin_ratio_1_depth_v85)), Float.valueOf(getRatioDimen(R.dimen.stories_item_title_con_bottom_margin_ratio_2_depth_v85))};
        }
        DimenHelper.DimenValue<Float> dimenValue2 = this.mTitleBottomMargin;
        if (z) {
            f = (Float) dimenValue2.mDrawerOpenValue;
        } else {
            f = ((Float[]) dimenValue2.mPinchDepthValue)[i2];
        }
        return f.floatValue();
    }
}
