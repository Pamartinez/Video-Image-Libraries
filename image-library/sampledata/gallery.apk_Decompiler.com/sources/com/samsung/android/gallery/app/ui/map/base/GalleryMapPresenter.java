package com.samsung.android.gallery.app.ui.map.base;

import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.map.base.IGalleryMapView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryMapPresenter<V extends IGalleryMapView> extends MvpBasePresenter<V> {
    private long mEntryItemKey = -1;

    public GalleryMapPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public MapItem createClusterItem(MediaItem mediaItem) {
        return new MapItem(mediaItem, mediaItem.getLatitude(), mediaItem.getLongitude(), isEntryItem(mediaItem));
    }

    public long getEntryItemKey() {
        return this.mEntryItemKey;
    }

    public boolean isEntryItem(MediaItem mediaItem) {
        long j2 = this.mEntryItemKey;
        if (j2 == -1 || j2 != mediaItem.getFileId()) {
            return false;
        }
        return true;
    }

    public void setEntryItemKey(long j2) {
        this.mEntryItemKey = j2;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }
}
