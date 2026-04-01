package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.SharingItemLoader;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataMdeGroup extends AbstractMediaDataMde {
    public MediaDataMdeGroup(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    public boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!PreferenceFeatures.OneUi41.SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE) {
            return super.equalsItem(mediaItem, mediaItem2);
        }
        if (!super.equalsItem(mediaItem, mediaItem2) || MediaItemMde.getAlbumExpiry(mediaItem) != MediaItemMde.getAlbumExpiry(mediaItem2)) {
            return false;
        }
        return true;
    }

    public int getListeningEventKey() {
        return 107;
    }

    public MediaItem loadMediaItem(Cursor cursor) {
        return SharingItemLoader.loadGroup(cursor);
    }
}
