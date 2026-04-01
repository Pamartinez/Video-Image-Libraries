package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScreenshotCategoryTipCard extends AbsTipCard {
    public boolean checkTipCard(Context context) {
        return GalleryPreference.getInstance().loadBoolean(PreferenceName.IS_NEED_TO_SHOW_SCREENSHOT_CATEGORY_TIP_CARD, true);
    }

    public String getCancelBtnString(Context context) {
        return context.getString(R.string.close);
    }

    public String getDescription(Context context) {
        return context.getString(R.string.collection_screenshot_tip_card_description);
    }

    public String getTag() {
        return getClass().getSimpleName();
    }

    public void onCancelBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        GalleryPreference.getInstance().saveState(PreferenceName.IS_NEED_TO_SHOW_SCREENSHOT_CATEGORY_TIP_CARD, false);
        dismissTipCard(tipCardViewHolder);
    }
}
