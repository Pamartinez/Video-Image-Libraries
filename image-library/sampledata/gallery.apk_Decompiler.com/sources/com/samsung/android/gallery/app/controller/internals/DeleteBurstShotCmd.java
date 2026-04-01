package com.samsung.android.gallery.app.controller.internals;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteBurstShotCmd extends DeleteGroupShotCmd {
    public int getGroupType() {
        return 0;
    }

    public void preOperateDelete() {
        long argValue = ArgumentsUtil.getArgValue(this.mLocationKey, "bestId", -1);
        for (MediaItem mediaItem : this.mItems) {
            if (mediaItem.getFileId() == argValue) {
                getBlackboard().postEvent(EventMessage.obtain(3027, new double[]{mediaItem.getLatitude(), mediaItem.getLongitude()}));
                return;
            }
        }
    }
}
