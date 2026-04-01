package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleDurationViewHolder;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.story.StoryUriBuilder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.widget.details.DetailsLayoutManager;
import com.samsung.android.gallery.widget.details.DetailsListAdapter;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemStory extends DetailsListItem<ImageTitleDurationViewHolder, GridLayoutManager> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StoryAdapter extends DetailsListAdapter<ImageTitleDurationViewHolder> {
        public StoryAdapter(RecyclerView recyclerView) {
            super(recyclerView);
        }

        public void onBindViewHolder(ImageTitleDurationViewHolder imageTitleDurationViewHolder, int i2) {
            super.onBindViewHolder(imageTitleDurationViewHolder, i2);
            MediaItem mediaItem = getMediaItem(i2);
            imageTitleDurationViewHolder.bind(mediaItem);
            bindThumbnail(imageTitleDurationViewHolder, mediaItem);
        }

        public ImageTitleDurationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            View inflate = getLayoutInflater(viewGroup.getContext()).inflate(R.layout.recycler_item_details_memories_layout, viewGroup, false);
            inflate.setClipToOutline(true);
            ImageTitleDurationViewHolder imageTitleDurationViewHolder = new ImageTitleDurationViewHolder(inflate, i2);
            imageTitleDurationViewHolder.setThumbnailShape(1, viewGroup.getResources().getDimension(R.dimen.moreinfo_item_image_corner_radius_oneui30));
            return imageTitleDurationViewHolder;
        }
    }

    public DetailsItemStory(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    private int getSpanCount() {
        if (supportLargeScreenHorizontalGui()) {
            return 2;
        }
        return 1;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLayout$0(GridLayoutManager gridLayoutManager) {
        gridLayoutManager.setSpanCount(getSpanCount());
    }

    private void moveToRecap(MediaItem mediaItem) {
        if (mediaItem.isBroken()) {
            Log.e(this.TAG, "fail start");
            return;
        }
        this.mBlackboard.post("command://MoveURL", new StoryUriBuilder(this.mBlackboard, mediaItem, true, "location://recap").withFromLocation("location://story/albums").appendArg("media_item", BlackboardUtils.publishMediaItem(this.mBlackboard, mediaItem)).build());
    }

    private void moveToStoryHighlight(MediaItem mediaItem) {
        StoryUriBuilder appendArg = new StoryUriBuilder(this.mBlackboard, (ThumbnailInterface) mediaItem).appendArg("details", Boolean.TRUE);
        if (LocationKey.isStoryHighlight(LocationKey.getStoryPicturesAliasKey())) {
            appendArg.withBlurBitmap();
        }
        this.mBlackboard.post("command://MoveURL", appendArg.build());
    }

    /* access modifiers changed from: private */
    /* renamed from: moveUrl */
    public void lambda$onItemClick$1(MediaItem mediaItem) {
        if (MediaItemStory.isRecap(mediaItem)) {
            moveToRecap(mediaItem);
        } else {
            moveToStoryHighlight(mediaItem);
        }
    }

    public DetailsListAdapter<ImageTitleDurationViewHolder> createAdapter(RecyclerView recyclerView) {
        return new StoryAdapter(recyclerView);
    }

    public int getLayoutId() {
        return R.id.moreinfo_memory;
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        super.onItemClick(listViewHolder, i2, mediaItem, i7);
        SimpleThreadPool.getInstance().execute(new C0447a(5, this, mediaItem));
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_SELECT_STORY);
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.STORY, this);
    }

    public boolean supportItem(MediaItem mediaItem) {
        return !DetailsData.of(mediaItem).getStory().isEmpty();
    }

    public void updateLayout() {
        super.updateLayout();
        Optional.ofNullable((GridLayoutManager) this.mLayoutManager).ifPresent(new r(this, 1));
    }

    public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        getAdapter().setData(DetailsData.of(mediaItem).getStory());
    }

    public GridLayoutManager createLayoutManager(RecyclerView recyclerView) {
        return DetailsLayoutManager.createGridLayoutManager(recyclerView, supportLargeScreenHorizontalGui() ? 2 : 1);
    }
}
