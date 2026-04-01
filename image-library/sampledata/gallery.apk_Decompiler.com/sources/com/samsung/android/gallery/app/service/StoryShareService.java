package com.samsung.android.gallery.app.service;

import G6.a;
import android.content.ContentValues;
import android.content.Intent;
import bc.C0584a;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.local.LocalProviderHelper;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.service.notification.StoryNotificationHelper;
import com.samsung.android.gallery.module.service.support.StoryServiceHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.type.PendingShare;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import java.io.File;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryShareService extends StoryBaseService {
    public StoryServiceHelper.OperationType getOperationType() {
        if (this.mFromStoryPictures) {
            return StoryServiceHelper.OperationType.SHARE_CARD;
        }
        return StoryServiceHelper.OperationType.SHARE_COVER;
    }

    public String getServiceClassName() {
        return "com.samsung.android.gallery.app.service.StoryShareService";
    }

    public void onCallActivity(Intent intent) {
        Log.d(this.TAG, "call gallery activity, finish service");
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.GalleryActivity");
        intent2.addCategory("android.intent.category.LAUNCHER");
        intent2.addFlags(805306368);
        intent2.putExtra("key-pending-sharing-event", true);
        intent2.putExtra("usage_type", ConvertingUsageType.COMMON_SHARE.ordinal());
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            intent2.putExtra("key_pending_blackboard_name", blackboard.getName());
        }
        startActivity(intent2);
        onTerminateService((Intent) null);
    }

    public void onFinishedInternal(String str) {
        if (FileUtils.exists(str)) {
            savePath(str);
            updateDatabase(str);
            Optional.ofNullable(this.mBlackboard).ifPresent(new a(UriItemLoader.createUriItem(getBaseContext(), new File(str)), 9));
            return;
        }
        Log.w(this.TAG, "file not exist : share fail");
    }

    public void onTerminateService(Intent intent) {
        super.onTerminateService(intent);
        StoryNotificationHelper storyNotificationHelper = this.mNotificationHelper;
        if (storyNotificationHelper != null) {
            storyNotificationHelper.dismiss();
        }
        executeHandlePostDelay(new C0584a(10, this), 500);
    }

    public void registerServiceRunning() {
        Blackboard.getApplicationInstance().publish("data://running_story_share", Boolean.TRUE);
    }

    public void savePath(String str) {
        PendingShare.set(str);
    }

    public void unregisterServiceRunning() {
        Blackboard.getApplicationInstance().erase("data://running_story_share");
    }

    public void updateDatabase(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues c5 = C0086a.c("__absPath", str);
        c5.put("date_added", Long.valueOf(currentTimeMillis));
        new LocalProviderHelper(getContentResolver()).insertConvertedFile(c5);
        A.a.x(new StringBuilder("insert to local db +"), currentTimeMillis, this.TAG);
    }

    public void onServicePrepared(String str) {
    }
}
