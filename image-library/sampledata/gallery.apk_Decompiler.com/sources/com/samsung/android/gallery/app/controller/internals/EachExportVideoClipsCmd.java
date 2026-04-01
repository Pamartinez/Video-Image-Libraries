package com.samsung.android.gallery.app.controller.internals;

import M9.o;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EachExportVideoClipsCmd extends BaseCommand {
    private boolean isServiceRunning() {
        return !Blackboard.getApplicationInstance().isEmpty("data://running_motion_photo_clip_service");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0() {
        Utils.showToast(getContext(), (int) R.string.wait_for_other_video_finish_saved);
    }

    private void startEachExportService(MediaItem[] mediaItemArr) {
        getBlackboard().publish("data://user/selected", mediaItemArr);
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.MotionPhotoClipService");
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("motion_photo_operation_type", 1);
        getContext().startService(intent);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MOTION_PHOTO_EXPORT_VIDEO.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!isLowStorage()) {
            if (isServiceRunning()) {
                Log.w(this.TAG, "service is running");
                ThreadUtil.runOnUiThread(new o(10, this));
                return;
            }
            getBlackboard().postEvent(EventMessage.obtain(1003));
            startEachExportService(objArr[0]);
        }
    }
}
