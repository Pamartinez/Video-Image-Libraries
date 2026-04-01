package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.IStoriesCategoryView;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.ItemProperty;
import com.samsung.android.gallery.app.ui.list.stories.header.SelectionHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesCatItemAdapter extends BaseListViewAdapter<IStoriesCategoryView> {
    private final GalleryListView mList;
    protected int mMaxDisplayCount;
    private final SelectionHelper<Integer> mSelectionHelper;
    private final boolean mSupportSelection = true;

    public StoriesCatItemAdapter(IStoriesCategoryView iStoriesCategoryView, String str, GalleryListView galleryListView) {
        super(iStoriesCategoryView, str);
        this.mList = galleryListView;
        this.mMaxDisplayCount = getMaxDisplayCount();
        this.mSelectionHelper = iStoriesCategoryView.getHeaderSelectionHelper();
    }

    private ThumbKind getDefaultThumbKind() {
        if (this.mLocationKey.startsWith("location://stories/category/trip")) {
            return ThumbKind.LARGE_KIND;
        }
        return ThumbKind.MEDIUM_KIND;
    }

    private int getMaxDisplayCount() {
        String str;
        String str2 = this.mLocationKey;
        switch (str2.hashCode()) {
            case -1761929745:
                str = "location://stories/category/creation";
                break;
            case 199731027:
                if (str2.equals("location://stories/category/transitory")) {
                    return 1;
                }
                return -1;
            case 1664805477:
                str = "location://stories/category/more";
                break;
            case 1665016629:
                str = "location://stories/category/trip";
                break;
            default:
                return -1;
        }
        str2.equals(str);
        return -1;
    }

    private void refreshBadgeView(ListViewHolder listViewHolder) {
        View decoView = listViewHolder.getDecoView(1);
        boolean isVisible = ViewUtils.isVisible(decoView);
        boolean isNewStory = StoryHelper.isNewStory(MediaItemStory.getStoryNotifyStatus(listViewHolder.getMediaItem()));
        if (isVisible != isNewStory) {
            ViewUtils.setVisibleOrGone(decoView, isNewStory);
        }
    }

    private void setChecked(ListViewHolder listViewHolder, int i2, boolean z) {
        if (this.mSelectionHelper != null) {
            listViewHolder.setCheckboxEnabled(getDecoItemViewType(), z);
            listViewHolder.setChecked(this.mSelectionHelper.isSelected(Integer.valueOf(MediaItemStory.getStoryId(listViewHolder.getMediaItem()))));
        }
    }

    public int getDataCount() {
        int i2 = this.mMaxDisplayCount;
        int count = this.mMediaData.getCount();
        if (i2 == -1) {
            return count;
        }
        return Math.min(i2, count);
    }

    public int getGridSize() {
        return 1;
    }

    public int getItemCount() {
        return getDataCount();
    }

    public int getItemHeight(int i2) {
        RecyclerView.ViewHolder findViewHolderForLayoutPosition = this.mList.findViewHolderForLayoutPosition(i2);
        if (findViewHolderForLayoutPosition != null) {
            return findViewHolderForLayoutPosition.itemView.getHeight();
        }
        return ItemProperty.getItemSize(getContext(), this.mLocationKey)[1];
    }

    public int getSpanSize(int i2) {
        return 1;
    }

    public ThumbKind getThumbnailKind() {
        return getDefaultThumbKind();
    }

    public void handleItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (((IStoriesCategoryView) this.mView).isSelectionMode()) {
            ((IStoriesCategoryView) this.mView).onListItemClick(listViewHolder, i2, mediaItem, i7);
        } else {
            super.handleItemClick(listViewHolder, i2, mediaItem, i7);
        }
    }

    public boolean isSelectionMode() {
        return ((IStoriesCategoryView) this.mView).isSelectionMode();
    }

    public void notifySelectedItemChanged(int i2, int i7) {
        notifyItemRangeChanged(0, getItemCount(), "select_mode");
    }

    public GalleryListAdapter.SelectableType selectItem(int i2, boolean z, boolean z3) {
        ListViewHolder listViewHolder = (ListViewHolder) this.mList.findViewHolderForAdapterPosition(i2);
        if (!(listViewHolder == null || this.mSelectionHelper == null)) {
            int storyId = MediaItemStory.getStoryId(listViewHolder.getMediaItem());
            if (this.mSelectionHelper.isSelected(Integer.valueOf(storyId))) {
                this.mSelectionHelper.remove(Integer.valueOf(storyId));
            } else {
                this.mSelectionHelper.add(Integer.valueOf(storyId));
            }
            ((IStoriesCategoryView) this.mView).updateSelectionToolBar();
        }
        return GalleryListAdapter.SelectableType.SUPPORT;
    }

    public boolean startSelect(int i2) {
        ((IStoriesCategoryView) this.mView).enterSelectionMode(0);
        return true;
    }

    public boolean supportHighQuality() {
        return this.mLocationKey.startsWith("location://stories/category/trip");
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("PAYLOAD_UPDATE_BADGE")) {
            refreshBadgeView(listViewHolder);
        } else if (list.contains("select_mode")) {
            setChecked(listViewHolder, i2, ((IStoriesCategoryView) this.mView).isSelectionMode());
        } else {
            super.onBindViewHolder(listViewHolder, i2, list);
            setChecked(listViewHolder, i2, ((IStoriesCategoryView) this.mView).isSelectionMode());
        }
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return StoriesCatItemViewHolderFactory.createItemViewHolder(viewGroup, i2, this.mLocationKey);
    }

    public void setThumbnailKind() {
    }
}
