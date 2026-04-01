package com.samsung.android.gallery.widget.smartalbum;

import android.content.Context;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SmartAlbumFavoriteItem extends SmartAlbumItem {
    public SmartAlbumFavoriteItem(Context context) {
        super(context);
    }

    public String getAnalyticsId() {
        return AnalyticsEventId.EVENT_SMART_ALBUM_FAVORITE.toString();
    }

    public int getDrawableId() {
        return R$drawable.gallery_ic_smart_nopic_favorite;
    }

    public int getTitleStringId() {
        return R$string.smart_album_favorites;
    }

    public int getType() {
        return 1;
    }

    public void handleOnClick(boolean z) {
        String str;
        if (!z) {
            str = new UriBuilder("location://virtual/album/favorite/fileList").appendArg("with_group", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE).build();
        } else {
            str = PickerUtil.appendPickerArgs(this.mBlackboard, "location://virtual/album/favorite/fileList");
        }
        moveTo(appendAlbumId(str, "Virtual/Favourites"));
    }
}
