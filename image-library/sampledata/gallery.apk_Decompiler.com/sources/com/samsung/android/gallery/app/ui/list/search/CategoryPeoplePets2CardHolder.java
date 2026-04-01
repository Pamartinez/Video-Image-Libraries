package com.samsung.android.gallery.app.ui.list.search;

import android.os.Bundle;
import android.view.View;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CategoryPeoplePets2CardHolder extends CategoryPeople2CardHolder {
    public CategoryPeoplePets2CardHolder(View view, int i2) {
        super(view, i2);
    }

    public int getContentPaddingBottom(boolean z) {
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            return getDimensionPixelOffset(R.dimen.search_card_horizontal_list_margin_bottom_8x);
        }
        return super.getContentPaddingBottom(z);
    }

    public int getHiddenItemCount(Bundle bundle) {
        int hiddenItemCount = super.getHiddenItemCount(bundle);
        if (bundle != null) {
            return bundle.getInt("hiddenPetsCount", 0) + hiddenItemCount;
        }
        return hiddenItemCount;
    }
}
