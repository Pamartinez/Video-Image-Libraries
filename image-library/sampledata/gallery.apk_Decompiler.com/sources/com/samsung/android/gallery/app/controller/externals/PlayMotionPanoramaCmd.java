package com.samsung.android.gallery.app.controller.externals;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.DelegateHelper;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayMotionPanoramaCmd extends AbstractPlayCmd {
    public Intent createIntent(MediaItem mediaItem) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName("com.samsung.android.app.motionpanoramaviewer", "com.samsung.android.app.motionpanoramaviewer.ViewerActivity"));
        intent.setData(ContentUri.getUri(mediaItem));
        setIntentWithCommonExtra(intent);
        return intent;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_MOTION_PANORAMA.toString();
    }

    public void handleFail() {
        if (isPackageDisabled("com.samsung.android.app.motionpanoramaviewer")) {
            DelegateHelper.startAppInfo(getHandler(), getContext().getString(R.string.motion_panorama_viewer), "com.samsung.android.app.motionpanoramaviewer");
        } else {
            super.handleFail();
        }
    }
}
