package com.samsung.android.gallery.app.controller.externals;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayMotionPhotoCmd extends AbstractPlayCmd {
    private boolean hasPrivilegedViewerApp() {
        if (getActivity().getPackageManager().checkPermission("com.sec.android.app.apex.permission.ACCESS_APEX_SERVICE", "com.samsung.android.motionphoto.viewer") == 0) {
            return true;
        }
        return false;
    }

    public Intent createIntent(MediaItem mediaItem) {
        String str;
        String str2;
        String str3;
        if (!SdkConfig.atLeast(SdkConfig.GED.Q) || !hasPrivilegedViewerApp()) {
            str2 = "com.sec.android.app.apex";
            str = "com.sec.android.app.apex.player.PlayerActivity";
        } else {
            Log.d(this.TAG, "hasPrivilegedViewerApp");
            str2 = "com.samsung.android.motionphoto.viewer";
            str = "com.samsung.android.motionphoto.viewer.PlayerActivity";
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(str2, str));
        intent.setFlags(131072);
        if (mediaItem.isSharing()) {
            str3 = MediaItemMde.getDownloadMotionPhotoPath(mediaItem);
        } else {
            str3 = mediaItem.getPath();
        }
        intent.putExtra("content_path", str3);
        intent.putExtra("is_shared", mediaItem.isSharing());
        intent.setData(ContentUri.getUri(mediaItem));
        setIntentWithCommonExtra(intent);
        return intent;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_MOTION_PHOTO.toString();
    }

    public void startActivity(Intent intent) {
        this.mActivity.startActivityForResult(intent, 787);
    }
}
