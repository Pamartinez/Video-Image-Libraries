package com.samsung.android.gallery.app.controller.externals;

import android.content.Intent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlaySingleOutFocusCmd extends PlayDualShotCmd {
    public Intent createIntent(MediaItem mediaItem) {
        Intent intent = new Intent("com.samsung.android.app.siofviewer");
        intent.setFlags(67108864);
        intent.putExtra("IMAGE_FILE_PATH", mediaItem.getPath());
        intent.putExtra("Portrait", this.mPortraitMode);
        return intent;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_LIVE_FOCUS.toString();
    }

    public void startActivity(Intent intent) {
        this.mActivity.startActivityForResult(intent, 788);
    }
}
