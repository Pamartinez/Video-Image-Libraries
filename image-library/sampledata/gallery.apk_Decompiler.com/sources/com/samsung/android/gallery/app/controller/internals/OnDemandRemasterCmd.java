package com.samsung.android.gallery.app.controller.internals;

import A.a;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandRemasterCmd extends BaseCommand {
    private void launchRemasterService(MediaItem mediaItem) {
        try {
            getBlackboard().publish("data://user/selected", new MediaItem[]{mediaItem});
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.RemasterService");
            intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
            intent.putExtra("blackboard_name", getBlackboard().getName());
            getContext().startService(intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("fail to startService="), this.TAG);
        }
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        if (mediaItem == null) {
            Log.e(this.TAG, "Unable to operate. item is null");
        } else if (BlackboardUtils.isRemasterServiceStarted()) {
            Log.w(this.TAG, "Remaster service is already started");
        } else {
            launchRemasterService(mediaItem);
        }
    }
}
