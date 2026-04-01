package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.superslow.SuperSlowUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.details.DetailsLayoutManager;
import com.samsung.android.gallery.widget.details.DetailsListAdapter;
import com.samsung.android.gallery.widget.details.DetailsListVideoHelper;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemSuperSlow extends DetailsListItem<ViewHolderSuperSlow, LinearLayoutManager> {
    private final DetailsListVideoHelper mVideoHelper = new DetailsListVideoHelper();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SuperSlowAdapter extends DetailsListAdapter<ViewHolderSuperSlow> {
        private final VideoCompleteCallback mVideoCompleteCallback;

        public SuperSlowAdapter(RecyclerView recyclerView, VideoCompleteCallback videoCompleteCallback) {
            super(recyclerView);
            this.mVideoCompleteCallback = videoCompleteCallback;
        }

        public void onBindViewHolder(ViewHolderSuperSlow viewHolderSuperSlow, int i2) {
            super.onBindViewHolder(viewHolderSuperSlow, i2);
            MediaItem mediaItem = getMediaItem(i2);
            viewHolderSuperSlow.bind(mediaItem);
            bindThumbnail(viewHolderSuperSlow, mediaItem);
            viewHolderSuperSlow.setVideoCompleteListener(this.mVideoCompleteCallback);
        }

        public ViewHolderSuperSlow onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new ViewHolderSuperSlow(getLayoutInflater(viewGroup.getContext()).inflate(R.layout.recycler_item_details_super_slow_layout, viewGroup, false), i2);
        }

        public void onViewDetachedFromWindow(ViewHolderSuperSlow viewHolderSuperSlow) {
            super.onViewDetachedFromWindow(viewHolderSuperSlow);
            viewHolderSuperSlow.stop();
        }
    }

    public DetailsItemSuperSlow(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
        this.mDismissParentPaddingEnd = true;
    }

    private boolean isPlayable() {
        return !getAdapter().isDataEmpty();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onRecyclerViewInited$0(RecyclerView recyclerView) {
        this.mVideoHelper.init(recyclerView, new p(this, 1));
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

    public DetailsListAdapter<ViewHolderSuperSlow> createAdapter(RecyclerView recyclerView) {
        return new SuperSlowAdapter(recyclerView, new q(this, 1));
    }

    public int getLayoutId() {
        return R.id.details_superslow;
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        super.onItemClick(listViewHolder, i2, mediaItem, i7);
        int playType = ((ViewHolderSuperSlow) listViewHolder).getPlayType();
        Log.d(this.TAG, "clicked playType", Integer.valueOf(playType));
        Intent intent = new Intent();
        intent.setClassName("com.samsung.android.video", "com.samsung.android.video.player.activity.SuperSlowActivity");
        intent.putExtra("superslow_video_path", mediaItem.getPath());
        intent.putExtra("superslow_effect", playType);
        try {
            this.mEventContext.getContext().startActivity(intent);
            postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_SELECT_SUPER_SLOW_CLIP);
        } catch (ActivityNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
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
        ThreadUtil.postOnUiThread(new C0447a(6, this, recyclerView));
    }

    public void onResume() {
        super.onResume();
        playVideo();
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.SUPER_SLOW, this);
    }

    public boolean supportItem(MediaItem mediaItem) {
        if (!SuperSlowUtils.isSuperSlow(mediaItem) || DetailsData.of(mediaItem).getSuperSlow().isEmpty()) {
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
        getAdapter().setData(DetailsData.of(mediaItem).getSuperSlow());
        this.mListView.scrollToPosition(0);
        playVideo();
    }

    public LinearLayoutManager createLayoutManager(RecyclerView recyclerView) {
        return DetailsLayoutManager.createLinearLayoutManager(recyclerView);
    }
}
