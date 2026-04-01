package com.samsung.android.gallery.widget.smartalbum;

import android.content.Context;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SmartAlbumVideoItem extends SmartAlbumItem {
    public SmartAlbumVideoItem(Context context) {
        super(context);
    }

    public String getAnalyticsId() {
        return AnalyticsEventId.EVENT_SMART_ALBUM_VIDEO.toString();
    }

    public int getDrawableId() {
        return R$drawable.gallery_ic_smart_nopic_video;
    }

    public int getTitleStringId() {
        return R$string.smart_album_videos;
    }

    public int getType() {
        return 0;
    }

    public void handleOnClick(boolean z) {
        String str = "location://virtual/album/video/fileList";
        if (z) {
            str = PickerUtil.appendPickerArgs(this.mBlackboard, str);
        }
        moveTo(appendAlbumId(str, "Virtual/Video"));
    }

    public boolean isEnableInPickMode() {
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent");
        if (launchIntent == null || !MediaFilterType.ALL.equals(PickerUtil.getFilterMediaType(launchIntent))) {
            return false;
        }
        return true;
    }
}
