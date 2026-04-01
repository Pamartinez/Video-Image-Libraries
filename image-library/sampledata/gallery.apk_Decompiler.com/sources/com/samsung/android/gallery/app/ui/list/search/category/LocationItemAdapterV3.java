package com.samsung.android.gallery.app.ui.list.search.category;

import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.search.ISearchView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocationItemAdapterV3 extends LocationItemAdapterV2 {
    private ListViewHolder.OnItemClickListener mHeaderItemClickListener;
    private final boolean mPickerMode;
    private final MediaItem mRecentMapHeaderItem;
    private ArrayList<Integer> mViewPositions;

    public LocationItemAdapterV3(ISearchView iSearchView, String str, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper) {
        super(iSearchView, str, galleryListView, categoryPropertyHelper);
        this.mPickerMode = PickerUtil.isPickerMode(iSearchView.getBlackboard());
        MediaItem mediaItem = new MediaItem();
        this.mRecentMapHeaderItem = mediaItem;
        mediaItem.setTitle(getContext().getString(R.string.view_all));
        mediaItem.setCategory("Location");
    }

    private boolean isHeaderItemType(ListViewHolder listViewHolder) {
        return listViewHolder != null && listViewHolder.getItemViewType() == -3;
    }

    public void bindThumbnail(ListViewHolder listViewHolder, int i2, int i7, MediaItem mediaItem) {
        if (!isHeaderItemType(listViewHolder)) {
            super.bindThumbnail(listViewHolder, i2, i7, mediaItem);
        }
    }

    public boolean bindViewHolderOnScrollIdle(ListViewHolder listViewHolder, int i2, int i7) {
        MediaItem mediaItemSync;
        if (!this.mGalleryListView.isScrollIdle() || ViewHolderValue.isDivider(listViewHolder.getViewType()) || (mediaItemSync = getMediaItemSync(i2)) == null) {
            return false;
        }
        listViewHolder.bind(mediaItemSync);
        listViewHolder.setImageUid(mediaItemSync.getPath());
        return bindImageOnScrollIdle(listViewHolder, mediaItemSync);
    }

    public int getItemCount() {
        int i2 = this.mDataCount;
        if (i2 == 0) {
            return 0;
        }
        return i2 + 1;
    }

    public int getItemViewType(int i2) {
        if (i2 == 0) {
            return -3;
        }
        return super.getItemViewType(i2);
    }

    public MediaItem getMediaItemFromCache(int i2) {
        return isHeaderItemType(i2) ? this.mRecentMapHeaderItem : super.getMediaItemFromCache(i2);
    }

    public int getMediaItemPosition(int i2) {
        return this.mViewPositions.get(i2).intValue();
    }

    public MediaItem getMediaItemSync(int i2) {
        if (isHeaderItemType(i2)) {
            return this.mRecentMapHeaderItem;
        }
        return super.getMediaItemSync(i2);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        if (this.mPickerMode && listViewHolder.getViewType() == -3) {
            listViewHolder.setEnable(false);
        }
    }

    public void onDataChanged() {
        updateViewPositionsWithHeaderItem();
        this.mRecentMapHeaderItem.setTag("HeaderExpireTime", 0L);
        super.onDataChanged();
    }

    public void setHeaderItemClickListener(ListViewHolder.OnItemClickListener onItemClickListener) {
        this.mHeaderItemClickListener = onItemClickListener;
    }

    public void setListeners(ListViewHolder listViewHolder) {
        super.setListeners(listViewHolder);
        if (this.mHeaderItemClickListener != null && isHeaderItemType(listViewHolder)) {
            listViewHolder.setOnItemClickListener(this.mHeaderItemClickListener);
        }
    }

    public void updateViewPositionsWithHeaderItem() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null && mediaData.getCount() != 0) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            this.mViewPositions = arrayList;
            arrayList.add(0);
            for (int i2 = 0; i2 < this.mMediaData.getCount(); i2++) {
                if (this.mMediaData.read(i2) != null) {
                    this.mViewPositions.add(Integer.valueOf(i2));
                }
            }
        }
    }

    private boolean isHeaderItemType(int i2) {
        return getItemViewType(i2) == -3;
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        return getMediaItemFromCache(i2);
    }

    public int getMediaItemPosition(int i2, int i7) {
        return getMediaItemPosition(i2);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == -3) {
            return this.mViewHolderFactory.createLocationCarouselHeaderViewHolder(viewGroup, i2);
        }
        return super.onCreateViewHolder(viewGroup, i2);
    }
}
