package com.samsung.android.gallery.app.controller.internals;

import A4.C0387w;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteAlbumCmd extends DeleteCmd {
    private boolean isValidAlbum(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return true;
        }
        if (mediaItem.isVirtualAlbum() || BucketUtils.isVirtualAlbum(mediaItem.getAlbumID())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getValidItems$0(int i2) {
        return new MediaItem[i2];
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_ALBUM_DELETE.toString();
    }

    public String getEventIdOfDelete(boolean z, boolean z3) {
        boolean z7;
        if (z || !z3) {
            z7 = false;
        } else {
            z7 = true;
        }
        return getEventIdOfDelete(z7);
    }

    public MediaItem[] getValidItems(MediaItem[] mediaItemArr) {
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            if (isValidAlbum(mediaItem)) {
                arrayList.add(mediaItem);
            }
        }
        return (MediaItem[]) arrayList.stream().toArray(new C0387w(24));
    }
}
