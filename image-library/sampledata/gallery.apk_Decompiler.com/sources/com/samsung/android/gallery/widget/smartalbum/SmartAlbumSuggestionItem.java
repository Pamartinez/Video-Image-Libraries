package com.samsung.android.gallery.widget.smartalbum;

import android.content.Context;
import android.widget.TextView;
import com.samsung.android.gallery.module.suggested.SuggestedManager;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SmartAlbumSuggestionItem extends SmartAlbumItem {
    public SmartAlbumSuggestionItem(Context context) {
        super(context);
    }

    public String getAnalyticsId() {
        return AnalyticsEventId.EVENT_SMART_ALBUM_FOR_YOU.toString();
    }

    public int getDrawableId() {
        return R$drawable.gallery_ic_smart_nopic_suggestion;
    }

    public int getTitleStringId() {
        return R$string.smart_album_suggested;
    }

    public int getType() {
        return 3;
    }

    public void handleOnClick(boolean z) {
        if (!z) {
            moveTo("location://suggestions");
        }
    }

    public boolean isVisibleInPickMode() {
        return false;
    }

    public void updateDetails() {
        int i2;
        TextView textView = this.mImageViewNew;
        if (SuggestedManager.getInstance().getAllEventBadgeCount(getContext()) > 0) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        textView.setVisibility(i2);
    }
}
