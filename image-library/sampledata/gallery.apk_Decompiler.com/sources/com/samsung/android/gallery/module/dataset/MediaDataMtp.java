package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataMtp extends MediaDataCursor {
    public MediaDataMtp(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    public boolean isListeningEvent(EventMessage eventMessage) {
        if (eventMessage.what == 105) {
            return true;
        }
        return false;
    }
}
