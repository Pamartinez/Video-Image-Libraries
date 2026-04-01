package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataCleanOut extends MediaDataTimeline {
    public MediaDataCleanOut(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    public boolean isFilteredEvent(EventMessage eventMessage) {
        return false;
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        if (eventMessage.what == 102) {
            return true;
        }
        if (!LocationKey.isCleanOutMotionPhoto(this.mLocationKey) || eventMessage.what != 101) {
            return false;
        }
        return true;
    }
}
