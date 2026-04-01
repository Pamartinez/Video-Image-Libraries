package com.samsung.android.gallery.app.ui.list.stories.category.categorylist;

import com.samsung.android.gallery.app.ui.list.stories.category.categorylist.IStoriesTripListView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesTripListPresenter<V extends IStoriesTripListView> extends StoriesCategoryListPresenter<V> {
    public StoriesTripListPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private int getDataPosition(int i2, MediaItem mediaItem) {
        ArrayList<MediaItem> arrayList = null;
        MediaData mediaData = ((IStoriesTripListView) this.mView).getMediaData((String) null);
        if (mediaData != null) {
            arrayList = mediaData.getAllData();
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                if (MediaItemStory.getStoryId(mediaItem) == MediaItemStory.getStoryId(arrayList.get(i7))) {
                    return i7;
                }
            }
        }
        return i2;
    }

    public boolean isStoryTripInYear() {
        return !((IStoriesTripListView) this.mView).isShowAll();
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        if (!((IStoriesTripListView) this.mView).isShowAll()) {
            i2 = getDataPosition(i2, mediaItem);
        }
        super.onListItemClickInternal(i2, mediaItem);
    }

    public boolean showDeleteAllWarning() {
        if (!((IStoriesTripListView) this.mView).isShowAll() || !isSelectAll()) {
            return false;
        }
        return true;
    }
}
