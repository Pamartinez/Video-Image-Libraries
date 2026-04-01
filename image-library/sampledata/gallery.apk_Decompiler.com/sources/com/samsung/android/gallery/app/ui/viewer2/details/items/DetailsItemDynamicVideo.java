package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.details.DetailsLayoutManager;
import com.samsung.android.gallery.widget.details.DetailsListAdapter;
import com.samsung.android.gallery.widget.details.DetailsListVideoHelper;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemDynamicVideo extends DetailsListItem<ViewHolderDynamicVideo, LinearLayoutManager> {
    private final DetailsListVideoHelper mVideoHelper = new DetailsListVideoHelper();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DynamicAdapter extends DetailsListAdapter<ViewHolderDynamicVideo> {
        private final VideoCompleteCallback mVideoCompleteCallback;

        public DynamicAdapter(RecyclerView recyclerView, VideoCompleteCallback videoCompleteCallback) {
            super(recyclerView);
            this.mVideoCompleteCallback = videoCompleteCallback;
        }

        public void onBindViewHolder(ViewHolderDynamicVideo viewHolderDynamicVideo, int i2) {
            super.onBindViewHolder(viewHolderDynamicVideo, i2);
            MediaItem mediaItem = getMediaItem(i2);
            viewHolderDynamicVideo.bind(mediaItem);
            bindThumbnail(viewHolderDynamicVideo, mediaItem);
            viewHolderDynamicVideo.setVideoCompleteListener(this.mVideoCompleteCallback);
        }

        public ViewHolderDynamicVideo onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new ViewHolderDynamicVideo(getLayoutInflater(viewGroup.getContext()).inflate(R.layout.recycler_item_details_dynamic_view_layout, viewGroup, false), i2);
        }

        public void onViewDetachedFromWindow(ViewHolderDynamicVideo viewHolderDynamicVideo) {
            super.onViewDetachedFromWindow(viewHolderDynamicVideo);
            viewHolderDynamicVideo.stop();
        }
    }

    public DetailsItemDynamicVideo(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
        this.mDismissParentPaddingEnd = true;
    }

    private void addSharedElement(ArrayList<MediaItem> arrayList, int i2) {
        ListViewHolder listViewHolder;
        RecyclerView recyclerView = this.mListView;
        if (recyclerView != null && (listViewHolder = (ListViewHolder) recyclerView.findViewHolderForAdapterPosition(i2)) != null) {
            MediaItem mediaItem = arrayList.get(i2);
            SharedTransition.setTransitionName(listViewHolder.getImage(), mediaItem.getTitle());
            SharedTransition.addSharedElement(this.mBlackboard, listViewHolder.getImage(), mediaItem.getTitle(), new TransitionInfo(mediaItem, listViewHolder.getBitmap(), mediaItem.getTitle()));
        }
    }

    private boolean isPlayable() {
        return !getAdapter().isDataEmpty();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onItemClick$1(DynamicViewPlayInfo dynamicViewPlayInfo) {
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_SELECT_DYNAMIC_VIEW, dynamicViewPlayInfo.getAnalyticsDetail());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onRecyclerViewInited$0(RecyclerView recyclerView) {
        this.mVideoHelper.init(recyclerView, new p(this, 0));
    }

    /* access modifiers changed from: private */
    public void onCompletedVideo(MediaItem mediaItem) {
        if (isPlayable()) {
            this.mVideoHelper.onCompletedVideo(mediaItem);
        }
    }

    private void playVideo() {
        if (isPlayable()) {
            this.mVideoHelper.playVideo();
        }
    }

    private void stopVideo() {
        if (isPlayable()) {
            this.mVideoHelper.stopVideo();
        }
    }

    public DetailsListAdapter<ViewHolderDynamicVideo> createAdapter(RecyclerView recyclerView) {
        return new DynamicAdapter(recyclerView, new q(this, 0));
    }

    public int getLayoutId() {
        return R.id.moreinfo_dynamicview;
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        super.onItemClick(listViewHolder, i2, mediaItem, i7);
        stopVideo();
        ArrayList<MediaItem> data = getAdapter().getData();
        for (int i8 = 0; i8 < data.size(); i8++) {
            if (i8 != i2) {
                addSharedElement(data, i8);
            }
        }
        addSharedElement(data, i2);
        new VuLauncher(this.mBlackboard).publishData().setIsTemp().disableTimeline().addTrueArgument("returnTransition").launch("location://dynamicViewList", i2, (MediaItem[]) data.toArray(new MediaItem[0]));
        Optional.ofNullable(DynamicViewData.of(mediaItem).dynamicViewPlayInfo).ifPresent(new r(this, 0));
    }

    public void onPause() {
        super.onPause();
        stopVideo();
    }

    public void onRecycled() {
        stopVideo();
        RecyclerView recyclerView = this.mListView;
        if (recyclerView != null) {
            recyclerView.setScrollX(0);
        }
        super.onRecycled();
    }

    public void onRecyclerViewInited(RecyclerView recyclerView) {
        super.onRecyclerViewInited(recyclerView);
        ThreadUtil.postOnUiThread(new C0447a(4, this, recyclerView));
    }

    public void onResume() {
        super.onResume();
        playVideo();
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.DYNAMIC_VIDEO, this);
    }

    public boolean supportItem(MediaItem mediaItem) {
        if (!mediaItem.isLocal() || !mediaItem.isVideo() || DetailsData.of(mediaItem).getDynamic().isEmpty()) {
            return false;
        }
        return true;
    }

    public void updateLayout() {
        super.updateLayout();
        if (isPlayable()) {
            this.mVideoHelper.stopVideo();
            this.mVideoHelper.playVideo();
        }
    }

    public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        getAdapter().setData(DetailsData.of(mediaItem).getDynamic());
        this.mListView.scrollToPosition(0);
        playVideo();
    }

    public LinearLayoutManager createLayoutManager(RecyclerView recyclerView) {
        return DetailsLayoutManager.createLinearLayoutManager(recyclerView);
    }
}
