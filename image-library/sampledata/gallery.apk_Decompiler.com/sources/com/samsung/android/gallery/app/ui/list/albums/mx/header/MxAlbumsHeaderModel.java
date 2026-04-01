package com.samsung.android.gallery.app.ui.list.albums.mx.header;

import A4.C0384t;
import Gb.a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsHeaderModel {
    private final String mLocationKey;
    private MediaData mMediaData;

    public MxAlbumsHeaderModel(String str) {
        this.mLocationKey = str;
    }

    private MediaData getInvitationData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            return mediaData.getChildMediaData("location://sharing/albums/invitations");
        }
        return null;
    }

    public void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.close();
            this.mMediaData = null;
        }
    }

    public int getInvitationItemCount() {
        if (this.mMediaData != null) {
            return ((Integer) Optional.ofNullable(getInvitationData()).map(new C0384t(22)).orElse(0)).intValue();
        }
        return 0;
    }

    public MediaItem getLatestInvitation() {
        if (this.mMediaData == null || getInvitationItemCount() <= 0) {
            return null;
        }
        return (MediaItem) Optional.ofNullable(getInvitationData()).map(new a(24)).orElse((Object) null);
    }

    public void openMediaData(Blackboard blackboard) {
        if (this.mMediaData == null) {
            this.mMediaData = MediaDataFactory.getInstance(blackboard).open(this.mLocationKey);
        }
    }

    public void registerDataChangeListener(MediaData.OnDataChangeListener onDataChangeListener) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.register(onDataChangeListener);
        }
    }

    public void unregisterDataChangeListener(MediaData.OnDataChangeListener onDataChangeListener) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(onDataChangeListener);
        }
    }
}
