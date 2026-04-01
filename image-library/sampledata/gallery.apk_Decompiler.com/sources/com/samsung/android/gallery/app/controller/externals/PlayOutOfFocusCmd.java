package com.samsung.android.gallery.app.controller.externals;

import android.content.ComponentName;
import android.content.Intent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayOutOfFocusCmd extends AbstractPlayCmd {
    public Intent createIntent(MediaItem mediaItem) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.sec.android.ofviewer", "com.sec.android.ofviewer.SEFViewerActivity"));
        intent.setFlags(67108864);
        intent.putExtra("inputfile", mediaItem.getPath());
        return intent;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_OUT_OF_FOCUS.toString();
    }

    public void startActivity(Intent intent) {
        this.mActivity.startActivityForResult(intent, 788);
    }
}
