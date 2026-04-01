package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.SharingItemLoader;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataMdeInvitations extends AbstractMediaDataMde {
    public MediaDataMdeInvitations(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    public boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || MediaItemMde.getInvitationExpiredTime(mediaItem) != MediaItemMde.getInvitationExpiredTime(mediaItem2) || MediaItemMde.getInvitationRequestedTime(mediaItem) != MediaItemMde.getInvitationRequestedTime(mediaItem2) || !TextUtils.equals(MediaItemMde.getInvitationSpaceName(mediaItem), MediaItemMde.getInvitationSpaceName(mediaItem2)) || !TextUtils.equals(MediaItemMde.getInvitationGroupId(mediaItem), MediaItemMde.getInvitationGroupId(mediaItem2)) || !TextUtils.equals(MediaItemMde.getInvitationRequesterName(mediaItem), MediaItemMde.getInvitationRequesterName(mediaItem2))) {
            return false;
        }
        return true;
    }

    public int getListeningEventKey() {
        return 108;
    }

    public MediaItem loadMediaItem(Cursor cursor) {
        return SharingItemLoader.loadInvitationListItem(cursor);
    }
}
