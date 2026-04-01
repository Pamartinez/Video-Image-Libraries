package com.samsung.android.gallery.app.ui.list.stories.pictures;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailPreview;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryPicturesBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryCoverViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryPicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.pinch.IPinchViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class StoryPicturesViewAdapter<V extends IStoryPicturesView> extends StoryPicturesBaseViewAdapter<V> {
    private View mFooterView;
    private boolean mLocationAuthEnabled;
    private StoryLayoutBuilder mStoryLayoutBuilder;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MemoryThumbnailPreview extends ThumbnailPreview<IStoryPicturesView> {
        public MemoryThumbnailPreview(IStoryPicturesView iStoryPicturesView) {
            super(iStoryPicturesView);
        }

        public int getViewHolderPosition(PreviewViewHolder previewViewHolder) {
            if (((IStoryPicturesView) this.mView).isDestroyed()) {
                return 0;
            }
            if (!(previewViewHolder instanceof StoryCoverViewHolder)) {
                return super.getViewHolderPosition(previewViewHolder);
            }
            return (((IStoryPicturesView) this.mView).getAppbarLayout().getHeight() / 2) + previewViewHolder.itemView.getTop();
        }

        public boolean limitDuration() {
            return true;
        }

        public List<PreviewViewHolder> listOf() {
            GalleryAppBarLayout appbarLayout;
            ArrayList arrayList = new ArrayList();
            if (!((IStoryPicturesView) this.mView).isDestroyed() && (appbarLayout = ((IStoryPicturesView) this.mView).getAppbarLayout()) != null) {
                if (!appbarLayout.seslIsCollapsed()) {
                    arrayList.add(((IStoryPicturesView) this.mView).getPreviewableViewHolder());
                } else if (PreferenceFeatures.OneUi40.SUPPORT_STORY_COVER_SLIDESHOW) {
                    arrayList.addAll(super.listOf());
                }
            }
            if (!PreferenceFeatures.OneUi40.SUPPORT_STORY_COVER_SLIDESHOW) {
                arrayList.addAll(super.listOf());
            }
            return arrayList;
        }
    }

    public StoryPicturesViewAdapter(V v, String str, View view, boolean z) {
        super(v, str, view, z);
        checkLocationAuthChanged();
    }

    private boolean checkLocationAuthChanged() {
        boolean z = this.mLocationAuthEnabled;
        boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth);
        this.mLocationAuthEnabled = isEnabled;
        if (z != isEnabled) {
            return true;
        }
        return false;
    }

    private StoryLayoutBuilder getMemoryLayoutBuilder() {
        if (this.mStoryLayoutBuilder == null) {
            this.mStoryLayoutBuilder = new StoryLayoutBuilder(getContext());
        }
        return this.mStoryLayoutBuilder;
    }

    private MediaItem loadEffectMediaItem(MediaItem mediaItem) {
        MediaItem mediaItem2;
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.SuggestedEffectOnStory) || (mediaItem2 = (MediaItem) MediaItemStory.getEffectItem(mediaItem)) == null) {
            return mediaItem;
        }
        return mediaItem2;
    }

    private void updateLocationInfo() {
        String str = this.TAG;
        Integer valueOf = Integer.valueOf(getItemCount());
        Log.d(str, "updateLocationInfo", 0, valueOf, "story_picture_location", "enable=" + this.mLocationAuthEnabled);
        notifyItemRangeChanged(0, getItemCount(), "story_picture_location");
    }

    public boolean bindViewHolderOnScrollIdle(ListViewHolder listViewHolder, int i2, int i7) {
        MediaItem loadEffectMediaItem;
        if (!this.mGalleryListView.isScrollIdle() || ViewHolderValue.isDivider(listViewHolder.getViewType()) || (loadEffectMediaItem = loadEffectMediaItem(loadMediaItemSync(getMediaItemPosition(i2, i7)))) == null) {
            return super.bindViewHolderOnScrollIdle(listViewHolder, i2, i7);
        }
        listViewHolder.bind(loadEffectMediaItem);
        listViewHolder.setImageUid(loadEffectMediaItem.getPath());
        return bindImageOnScrollIdle(listViewHolder, loadEffectMediaItem);
    }

    public ThumbnailPreview<?> createThumbnailPreview() {
        return new MemoryThumbnailPreview((IStoryPicturesView) this.mView);
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new StoryPicturesViewHolderFactory(context);
    }

    public int getFooterViewHeight() {
        if (!ViewUtils.isVisible(this.mFooterView)) {
            return 0;
        }
        int height = this.mFooterView.getHeight();
        if (height > 0) {
            return height;
        }
        if (this.mFooterView.getMeasuredHeight() <= 0) {
            this.mFooterView.measure(View.MeasureSpec.makeMeasureSpec(this.mGalleryListView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        }
        return this.mFooterView.getMeasuredHeight();
    }

    public int getFooterViewPosition() {
        return getItemCount() - 1;
    }

    public int getItemHeight(int i2) {
        if (isFooter(i2)) {
            return getFooterViewHeight();
        }
        return super.getItemHeight(i2);
    }

    public int getItemViewType(int i2) {
        if (!hasFooter() || i2 != getItemCount() - 1) {
            return super.getItemViewType(i2);
        }
        return -4;
    }

    public LayoutInfo getLayoutInfo(int i2) {
        return ((StoryChapterClusterAdapter) this.mMultiClusterAdapter).getLayoutInfo(i2);
    }

    public int getMaxScroll() {
        int maxScroll = super.getMaxScroll();
        if (hasFooter()) {
            return maxScroll + getFooterViewHeight();
        }
        return maxScroll;
    }

    public MediaItem getMediaItemFromCache(int i2) {
        return loadEffectMediaItem(super.getMediaItemFromCache(i2));
    }

    public int getNextRowIndex(int i2, int i7) {
        for (int i8 = i2 + 1; i8 < getItemCount(); i8++) {
            if (this.mMultiClusterAdapter.getStartSpan(i8, getGridSize()) == 0) {
                return i8;
            }
        }
        return super.getNextRowIndex(i2, i7);
    }

    public int getPrevRowIndex(int i2) {
        for (int i7 = i2 - 1; i7 >= 0; i7--) {
            if (this.mMultiClusterAdapter.getStartSpan(i7, getGridSize()) == 0) {
                return i7;
            }
        }
        return super.getPrevRowIndex(i2);
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    public boolean hasFooter() {
        return true;
    }

    public void initData() {
        this.mMultiClusterAdapter = new StoryChapterClusterAdapter(this.mGalleryListView, this.mMediaData, getMemoryLayoutBuilder());
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (i7 == 1001) {
            ((IStoryPicturesView) this.mView).onTagViewSelected(mediaItem);
        } else if (i7 == 1002) {
            ((IStoryPicturesView) this.mView).onOptionShareSelected(listViewHolder);
        } else if (i7 == 1003) {
            ((IStoryPicturesView) this.mView).onOptionSaveSelected(listViewHolder);
        } else if (!((IStoryPicturesView) this.mView).onHandleOptionMenu(listViewHolder, i7)) {
            super.onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
        }
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (((IStoryPicturesView) this.mView).isOptionMenuOpened()) {
            return true;
        }
        return super.onListItemLongClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    public void recalculatePosition(int i2) {
        RecyclerView.LayoutManager layoutManager = this.mGalleryListView.getLayoutManager();
        if (layoutManager instanceof GalleryGridLayoutManager) {
            this.mMultiClusterAdapter.recalculateDayPosition(i2 - ((GalleryGridLayoutManager) layoutManager).getHintHorizontalPadding(1));
        }
    }

    public void resume() {
        super.resume();
        if (checkLocationAuthChanged()) {
            updateLocationInfo();
        }
    }

    public void setFooterView(View view) {
        ViewUtils.removeSelf(this.mFooterView);
        this.mFooterView = view;
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        return loadEffectMediaItem(super.getMediaItemFromCache(i2, i7));
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("story_picture_location")) {
            listViewHolder.updateDecoration(1024, Boolean.valueOf(this.mLocationAuthEnabled));
            return;
        }
        super.onBindViewHolder(listViewHolder, i2, list);
        if (isFooter(i2)) {
            ViewGroup viewGroup = (ViewGroup) listViewHolder.getRootView();
            View view = this.mFooterView;
            if (view != null && view.getParent() != viewGroup) {
                ViewUtils.removeSelf(this.mFooterView);
                ViewUtils.addView(viewGroup, this.mFooterView);
            }
        }
    }

    public void syncClusterDivider(int i2) {
    }

    public void setViewHolderMargin(IPinchViewHolder iPinchViewHolder, int i2) {
    }

    public void enableLocationText(ListViewHolder listViewHolder, boolean z, boolean z3) {
    }
}
