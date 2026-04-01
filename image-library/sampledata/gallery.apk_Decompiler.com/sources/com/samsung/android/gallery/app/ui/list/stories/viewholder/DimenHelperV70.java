package com.samsung.android.gallery.app.ui.list.stories.viewholder;

import android.content.res.Resources;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.DimenHelper;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DimenHelperV70 extends DimenHelperV2 {
    public DimenHelperV70(Resources resources) {
        super(resources);
    }

    public int getBorderRadius(int i2, boolean z) {
        Integer num;
        if (this.mBorderRadius == null) {
            DimenHelper.DimenValue<Integer> dimenValue = new DimenHelper.DimenValue<>();
            this.mBorderRadius = dimenValue;
            dimenValue.mDrawerOpenValue = Integer.valueOf(getDimen(R.dimen.stories_category_creation_item_round_radius));
            this.mBorderRadius.mPinchDepthValue = new Integer[]{Integer.valueOf(getDimen(R.dimen.stories_category_creation_item_round_radius)), Integer.valueOf(getDimen(R.dimen.stories_item_round_radius_2_depth))};
        }
        DimenHelper.DimenValue<Integer> dimenValue2 = this.mBorderRadius;
        if (z) {
            num = (Integer) dimenValue2.mDrawerOpenValue;
        } else {
            num = ((Integer[]) dimenValue2.mPinchDepthValue)[i2];
        }
        return num.intValue();
    }

    public int getSubTitleTextSize(int i2, boolean z) {
        if (this.mSubTitleTextSize == null) {
            DimenHelper.DimenValue<Integer> dimenValue = new DimenHelper.DimenValue<>();
            this.mSubTitleTextSize = dimenValue;
            dimenValue.mPinchDepthValue = new Integer[]{Integer.valueOf(getDimen(R.dimen.stories_category_creation_item_subtitle_size)), Integer.valueOf(getDimen(R.dimen.stories_view_duration_text_size_2_depth_60))};
        }
        return ((Integer[]) this.mSubTitleTextSize.mPinchDepthValue)[i2].intValue();
    }

    public int getTitleTextSize(int i2, boolean z) {
        if (this.mTitleTextSize == null) {
            DimenHelper.DimenValue<Integer> dimenValue = new DimenHelper.DimenValue<>();
            this.mTitleTextSize = dimenValue;
            dimenValue.mPinchDepthValue = new Integer[]{Integer.valueOf(getDimen(R.dimen.stories_category_creation_item_title_size)), Integer.valueOf(getDimen(R.dimen.stories_view_title_text_size_2_depth_60))};
        }
        return ((Integer[]) this.mTitleTextSize.mPinchDepthValue)[i2].intValue();
    }
}
