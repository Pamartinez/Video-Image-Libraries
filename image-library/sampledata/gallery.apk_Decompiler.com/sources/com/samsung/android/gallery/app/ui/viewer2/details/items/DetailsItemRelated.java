package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.animations.SimpleAutoScroller;
import com.samsung.android.gallery.widget.details.DetailsListAdapter;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemRelated extends DetailsListItem<PreviewViewHolder, StaggeredGridLayoutManager> {
    private String mCreatedKey;
    private InstantSubscriberListener mSubscriber;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RelatedAdapter extends DetailsListAdapter<PreviewViewHolder> {
        public RelatedAdapter(RecyclerView recyclerView) {
            super(recyclerView);
        }

        public void onBindViewHolder(PreviewViewHolder previewViewHolder, int i2) {
            super.onBindViewHolder(previewViewHolder, i2);
            MediaItem mediaItem = getMediaItem(i2);
            previewViewHolder.bind(mediaItem);
            bindThumbnail(previewViewHolder, mediaItem);
        }

        public PreviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            View inflate = getLayoutInflater(viewGroup.getContext()).inflate(R.layout.recycler_item_details_related_layout, viewGroup, false);
            PreviewViewHolder previewViewHolder = new PreviewViewHolder(inflate, i2);
            previewViewHolder.setThumbnailShape(1, inflate.getResources().getDimension(R.dimen.moreinfo_item_image_corner_radius_oneui30));
            return previewViewHolder;
        }
    }

    public DetailsItemRelated(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    private void addSharedElement(ArrayList<MediaItem> arrayList, int i2) {
        ListViewHolder listViewHolder;
        RecyclerView recyclerView = this.mListView;
        if (recyclerView != null && (listViewHolder = (ListViewHolder) recyclerView.findViewHolderForAdapterPosition(i2)) != null) {
            MediaItem mediaItem = arrayList.get(i2);
            SharedTransition.setTransitionName(listViewHolder.getImage(), SharedTransition.getTransitionName(mediaItem));
            SharedTransition.addSharedElement(this.mBlackboard, listViewHolder.getImage(), SharedTransition.getTransitionName(mediaItem), new TransitionInfo(mediaItem, listViewHolder.getBitmap()));
        }
    }

    private int getSpanCount() {
        if (supportLargeScreenHorizontalGui()) {
            return 4;
        }
        return 2;
    }

    private String getTempListLocationKey(MediaItem mediaItem) {
        return "location://tempList/" + mediaItem.getSimpleHashCode() + "/" + mediaItem.getSubCategory();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$subscribeForReturnTransition$0(Object obj, Bundle bundle) {
        ThreadUtil.postOnUiThread(new A(this, 0));
        this.mCreatedKey = null;
        this.mSubscriber = null;
        new SimpleAutoScroller(this.mBlackboard, this.mListView, SharedTransition.getReturnPosition(this.mBlackboard)).setAlphaWeight(0.2f).setStartDelay(60).start();
    }

    private void subscribeForReturnTransition() {
        this.mSubscriber = new z(this);
        String str = (String) this.mEventContext.getEventContextData("view_recreated_key");
        this.mCreatedKey = str;
        this.mBlackboard.subscribeOnCurrent(str, this.mSubscriber);
    }

    /* access modifiers changed from: private */
    public void unSubscribeReturnTransition() {
        InstantSubscriberListener instantSubscriberListener;
        String str = this.mCreatedKey;
        if (str != null && (instantSubscriberListener = this.mSubscriber) != null) {
            this.mBlackboard.unsubscribe(str, instantSubscriberListener);
            this.mCreatedKey = null;
            this.mSubscriber = null;
        }
    }

    public DetailsListAdapter<PreviewViewHolder> createAdapter(RecyclerView recyclerView) {
        return new RelatedAdapter(recyclerView);
    }

    public int getLayoutId() {
        return R.id.moreinfo_related;
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        super.onItemClick(listViewHolder, i2, mediaItem, i7);
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (SharedTransition.isInReturnTransition(this.mBlackboard) || currentItem == null) {
            Log.w((CharSequence) this.TAG, "failed to click item: ", MediaItemUtil.getSimpleLog(currentItem));
            return;
        }
        ArrayList<MediaItem> data = getAdapter().getData();
        for (int i8 = 0; i8 < 4; i8++) {
            if (i8 != i2) {
                addSharedElement(data, i8);
            }
        }
        addSharedElement(data, i2);
        new VuLauncher(this.mBlackboard).publishData().disableTimeline().addTrueArgument("returnAutoScrollTransition").launch(new UriBuilder(getTempListLocationKey(currentItem)).appendArg("related_from_id", currentItem.getFileId()).build(), i2, (MediaItem[]) data.toArray(new MediaItem[0]));
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_RELATED_CLICK);
        subscribeForReturnTransition();
    }

    public void onRecycled() {
        unSubscribeReturnTransition();
        super.onRecycled();
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.RELATED, this);
    }

    public boolean supportItem(MediaItem mediaItem) {
        return !DetailsData.of(mediaItem).getRelated().isEmpty();
    }

    public void updateLayout() {
        super.updateLayout();
        LM lm = this.mLayoutManager;
        if (lm != null) {
            ((StaggeredGridLayoutManager) lm).setSpanCount(getSpanCount());
        }
    }

    public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        getAdapter().setData(DetailsData.of(mediaItem).getRelated());
        setTextAndVisibility((int) R.id.moreinfo_item_title, (CharSequence) DetailsData.of(mediaItem).getRelatedTitle());
    }

    public StaggeredGridLayoutManager createLayoutManager(RecyclerView recyclerView) {
        return new StaggeredGridLayoutManager(getSpanCount(), 1);
    }
}
