package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.SharingItemLoader;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataMdeGroupMember extends AbstractMediaDataMde {
    public MediaDataMdeGroupMember(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    public int getListeningEventKey() {
        return 1062;
    }

    public MediaItem loadMediaItem(Cursor cursor) {
        return SharingItemLoader.loadGroupMember(cursor);
    }
}
