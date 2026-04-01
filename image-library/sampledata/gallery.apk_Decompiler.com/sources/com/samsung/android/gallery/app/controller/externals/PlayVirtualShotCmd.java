package com.samsung.android.gallery.app.controller.externals;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayVirtualShotCmd extends AbstractPlayCmd {
    private boolean supportMotionPhotoPreview() {
        return false;
    }

    public Intent createIntent(MediaItem mediaItem) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName("com.samsung.android.app.interactivepanoramaviewer", "com.samsung.android.app.interactivepanoramaviewer.ViewerActivity"));
        intent.setData(ContentUri.getUri(mediaItem));
        setIntentWithCommonExtra(intent);
        return intent;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_VIRTUAL_SHOT.toString();
    }

    public void startActivity(Intent intent) {
        if (supportMotionPhotoPreview()) {
            this.mActivity.startActivityForResult(intent, 787);
        } else {
            super.startActivity(intent);
        }
    }
}
