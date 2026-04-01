package com.samsung.android.gallery.widget.smartalbum;

import android.content.Context;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SmartAlbumRecentlyItem extends SmartAlbumItem {
    public SmartAlbumRecentlyItem(Context context) {
        super(context);
    }

    public String getAnalyticsId() {
        return AnalyticsEventId.EVENT_SMART_ALBUM_RECENTLY.toString();
    }

    public int getDrawableId() {
        return R$drawable.gallery_ic_search_suggested_recent;
    }

    public int getTitleStringId() {
        return R$string.recently_added;
    }

    public int getType() {
        return 2;
    }

    public void handleOnClick(boolean z) {
        String str = "location://virtual/album/recently/fileList";
        if (z) {
            str = PickerUtil.appendPickerArgs(this.mBlackboard, str);
        }
        moveTo(str);
    }
}
