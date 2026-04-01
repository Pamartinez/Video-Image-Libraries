package com.samsung.android.gallery.app.controller.internals;

import N2.j;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.OneDriveManager;
import com.samsung.android.gallery.module.cloud.SamsungCloudManager;
import com.samsung.android.gallery.module.data.CloudData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartCloudCmd extends BaseCommand {
    private String mAnalyticsDetail;
    private String mLocationKey;

    private boolean isAlbumSyncOn(MediaItem mediaItem) {
        if (CloudData.of(mediaItem).albumSyncStatus != 0) {
            return true;
        }
        return false;
    }

    private boolean isCloudSyncOn() {
        return CloudStateCompat.isSyncOnInPref();
    }

    private void startCloudAlbumSync(Activity activity, MediaItem mediaItem) {
        try {
            Intent intent = new Intent("com.samsung.android.scloud.GALLERY_CLOUD_SYNC_ALBUM");
            intent.putExtra("bucket_id", mediaItem.getBucketID());
            intent.putExtra("bucket_display_name", mediaItem.getDisplayName());
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            j.p(e, new StringBuilder("startCloudAlbumSync failed e="), this.TAG);
        }
    }

    private void startCloudFromAlbum(Activity activity, MediaItem mediaItem) {
        String str;
        AnalyticsDetail analyticsDetail;
        if (mediaItem == null) {
            Log.e(this.TAG, "album item is null");
            return;
        }
        boolean isCloudSyncOn = isCloudSyncOn();
        boolean isAlbumSyncOn = isAlbumSyncOn(mediaItem);
        if (!isCloudSyncOn || isAlbumSyncOn) {
            SamsungCloudManager.getInstance().startSetting(activity);
        } else {
            startCloudAlbumSync(activity, mediaItem);
        }
        if (isCloudSyncOn) {
            if (isAlbumSyncOn) {
                analyticsDetail = AnalyticsDetail.EVENT_DETAIL_MENU_ALBUM_CLOUD_SYNC_ON;
            } else {
                analyticsDetail = AnalyticsDetail.EVENT_DETAIL_MENU_ALBUM_CLOUD_SYNC_OFF;
            }
            str = analyticsDetail.toString();
        } else {
            str = AnalyticsDetail.EVENT_DETAIL_MENU_CLOUD_SYNC_OFF.toString();
        }
        this.mAnalyticsDetail = str;
    }

    private void startCloudFromTimeline(Activity activity) {
        String str;
        SamsungCloudManager.getInstance().startSetting(activity);
        if (isCloudSyncOn()) {
            str = AnalyticsDetail.EVENT_DETAIL_MENU_CLOUD_SYNC_ON.toString();
        } else {
            str = AnalyticsDetail.EVENT_DETAIL_MENU_CLOUD_SYNC_OFF.toString();
        }
        this.mAnalyticsDetail = str;
    }

    public String getAnalyticsDetail() {
        return this.mAnalyticsDetail;
    }

    public String getEventId() {
        if (LocationKey.isTimeline(this.mLocationKey)) {
            return AnalyticsEventId.EVENT_MENU_CLOUD_SYNC_TIMELINE.toString();
        }
        if (LocationKey.isAlbumPictures(this.mLocationKey)) {
            return AnalyticsEventId.EVENT_MENU_CLOUD_SYNC_ALBUM_PICTURES.toString();
        }
        return super.getEventId();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Activity activity = eventContext.getActivity();
        if (activity == null) {
            Log.e(this.TAG, "activity is null");
            return;
        }
        this.mLocationKey = eventContext.getLocationKey();
        if (CloudStateCompat.isPermissionRequired()) {
            SamsungCloudManager.getInstance().startPermissionSetting(getContext());
        } else if (!CloudStateCompat.isEnabled()) {
            OneDriveManager.getInstance().startMigration(activity);
            this.mAnalyticsDetail = AnalyticsDetail.EVENT_DETAIL_MENU_ONE_DRIVE_NOT_LINKED.toString();
        } else if (LocationKey.isTimeline(this.mLocationKey)) {
            startCloudFromTimeline(activity);
        } else if (LocationKey.isAlbumPictures(this.mLocationKey)) {
            startCloudFromAlbum(activity, objArr[0]);
        }
        postAnalyticsLog();
    }
}
