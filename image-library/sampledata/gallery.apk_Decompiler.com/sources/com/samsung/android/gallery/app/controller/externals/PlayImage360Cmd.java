package com.samsung.android.gallery.app.controller.externals;

import android.content.Intent;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayImage360Cmd extends AbstractPlayCmd {
    public Intent createIntent(MediaItem mediaItem) {
        boolean z;
        if (mediaItem.getSefFileType() == 2640) {
            z = true;
        } else {
            z = false;
        }
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.image360.activity.Image360Activity");
        intent.setData(ContentUri.getUri(mediaItem));
        intent.putExtra("mediaItemPath", mediaItem.getPath());
        intent.putExtra("mediaItemWidth", mediaItem.getWidth());
        intent.putExtra("mediaItemHeight", mediaItem.getHeight());
        intent.putExtra("sef360Image", z);
        setIntentWithCommonExtra(intent);
        return intent;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_360_PHOTO.toString();
    }

    public void startActivity(Intent intent) {
        this.mActivity.startActivityForResult(intent, 791);
    }
}
