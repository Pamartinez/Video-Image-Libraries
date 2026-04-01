package com.samsung.android.gallery.app.ui.list.stories.category.categorylist;

import Ad.C0720a;
import E5.b;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.category.categorylist.IStoriesTripListView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesTripListAdapter<V extends IStoriesTripListView> extends StoriesPinchViewAdapter<V> {
    private HashMap<Integer, ArrayList<MediaItem>> mFilteredData = new HashMap<>();
    private Integer mSelectedYear = 0;

    public StoriesTripListAdapter(V v, String str, View view) {
        super(v, str, view);
        update();
    }

    private MediaItem getFilteredMediaItem(int i2) {
        if (!this.mFilteredData.containsKey(this.mSelectedYear) || !isData(i2)) {
            return null;
        }
        ArrayList arrayList = this.mFilteredData.get(this.mSelectedYear);
        Objects.requireNonNull(arrayList);
        return (MediaItem) arrayList.get(getMediaItemPosition(i2));
    }

    /* access modifiers changed from: private */
    public int getStartYear(MediaItem mediaItem) {
        return TimeUtil.getYearInt(MediaItemStory.getStoryStartTime(mediaItem));
    }

    private boolean isShowAll() {
        if (this.mSelectedYear.intValue() == 0) {
            return true;
        }
        return false;
    }

    public boolean bindViewHolderOnScrollIdle(ListViewHolder listViewHolder, int i2, int i7) {
        if (isShowAll()) {
            return super.bindViewHolderOnScrollIdle(listViewHolder, i2, i7);
        }
        MediaItem mediaItemSync = getMediaItemSync(i2);
        if (mediaItemSync == null) {
            return false;
        }
        listViewHolder.bind(mediaItemSync);
        listViewHolder.setImageUid(mediaItemSync.getPath());
        return bindImageOnScrollIdle(listViewHolder, mediaItemSync);
    }

    public int getItemCount() {
        if (isShowAll()) {
            return super.getItemCount();
        }
        ArrayList orDefault = this.mFilteredData.getOrDefault(this.mSelectedYear, new ArrayList());
        Objects.requireNonNull(orDefault);
        return orDefault.size() + 1;
    }

    public MediaItem getMediaItemFromCache(int i2) {
        return isShowAll() ? super.getMediaItemFromCache(i2) : getFilteredMediaItem(i2);
    }

    public int getMediaItemPosition(int i2) {
        return i2 - 1;
    }

    public MediaItem getMediaItemSync(int i2) {
        if (isShowAll()) {
            return super.getMediaItemSync(i2);
        }
        return getFilteredMediaItem(i2);
    }

    public void setSelectedYear(int i2) {
        this.mSelectedYear = Integer.valueOf(i2);
    }

    public void update() {
        this.mFilteredData.clear();
        ArrayList<MediaItem> allData = this.mMediaData.getAllData();
        if (allData != null) {
            allData.sort(Comparator.comparingLong(new b(8)).reversed());
            HashMap<Integer, ArrayList<MediaItem>> hashMap = (HashMap) allData.stream().collect(Collectors.groupingBy(new a(this), new C0720a(10), Collectors.toCollection(new C0720a(1))));
            this.mFilteredData = hashMap;
            if (!hashMap.containsKey(this.mSelectedYear)) {
                ((IStoriesTripListView) this.mView).resetYearFilter();
            }
        }
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        return isShowAll() ? super.getMediaItemFromCache(i2, i7) : getFilteredMediaItem(i2);
    }
}
