package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.details.DetailsLayoutManager;
import com.samsung.android.gallery.widget.details.DetailsListAdapter;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.DebugSmartCropRectInfo;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemCreatures extends DetailsListItem<ListViewHolder, LinearLayoutManager> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CreaturesAdapter extends DetailsListAdapter<ListViewHolder> {
        public CreaturesAdapter(RecyclerView recyclerView) {
            super(recyclerView);
        }

        public int getItemCount() {
            int itemCount = super.getItemCount();
            if (itemCount > 0) {
                return itemCount + 1;
            }
            return itemCount;
        }

        public int getItemViewType(int i2) {
            if (i2 >= getData().size()) {
                return -1;
            }
            return 0;
        }

        public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
            super.onBindViewHolder(listViewHolder, i2);
            MediaItem mediaItem = getMediaItem(i2);
            listViewHolder.bind(mediaItem);
            bindThumbnail(listViewHolder, mediaItem);
        }

        public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            if (i2 == -1) {
                return new ViewHolderCreaturesRemove(getLayoutInflater(viewGroup.getContext()).inflate(R.layout.recycler_item_details_people_remove_layout, viewGroup, false), i2);
            }
            return new ViewHolderCreatures(getLayoutInflater(viewGroup.getContext()).inflate(R.layout.recycler_item_details_people_layout, viewGroup, false), i2);
        }
    }

    public DetailsItemCreatures(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
        this.mDismissParentPaddingEnd = true;
    }

    private String getTargetLocationKey(MediaItem mediaItem) {
        String str;
        if (mediaItem.isPeople()) {
            str = "location://search/fileList/Category/People";
        } else {
            str = "location://search/fileList/Category/Pet";
        }
        String searchLocationKey = LocationKey.getSearchLocationKey(str, mediaItem.getSubCategory());
        MediaData find = MediaDataFactory.getInstance(this.mBlackboard).find(searchLocationKey);
        if (find != null) {
            return new UriBuilder(find.getLocationReference()).appendArg("searchToolbar", false).appendArg("title", mediaItem.getTitle()).build();
        }
        UriBuilder appendArg = new UriBuilder(searchLocationKey).appendArg(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, mediaItem.getCategory()).appendArg("sub", mediaItem.getSubCategory()).appendArg("title", mediaItem.getTitle()).appendArg("from_viewer", true).appendArg("searchToolbar", false);
        if (mediaItem.isCreature()) {
            appendArg.appendArg("isNamed", CreatureData.hasName(mediaItem));
            appendArg.appendArg("term", getTerm(mediaItem));
        }
        return appendArg.build();
    }

    private String getTerm(MediaItem mediaItem) {
        if (!mediaItem.isPeople()) {
            return "pet_recommended_id";
        }
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            return "recommended_id";
        }
        return "persontag";
    }

    private void onCreatureItemClicked(MediaItem mediaItem) {
        String targetLocationKey = getTargetLocationKey(mediaItem);
        this.mBlackboard.erase(targetLocationKey);
        this.mBlackboard.post("command://MoveURL", targetLocationKey);
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_SELECT_PEOPLE);
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.Object, com.samsung.android.gallery.app.controller.DataCollectionDelegate$OnDataCompletionListener] */
    private void onRemoveClicked() {
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_EDIT_PEOPLE);
        this.mBlackboard.publish("data://details/creature", getAdapter().getData().clone());
        DataCollectionDelegate.getInitInstance(this.mEventContext).setRequestData("data://user/dialog/RemoveCreature").setOnDataCompleteListener(new Object()).start();
    }

    public DetailsListAdapter<ListViewHolder> createAdapter(RecyclerView recyclerView) {
        return new CreaturesAdapter(recyclerView);
    }

    public int getLayoutId() {
        return R.id.moreinfo_people;
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        super.onItemClick(listViewHolder, i2, mediaItem, i7);
        if (i7 == 1001) {
            onRemoveClicked();
        } else {
            onCreatureItemClicked(mediaItem);
        }
    }

    public boolean onItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        boolean onItemLongClick = super.onItemLongClick(listViewHolder, i2, mediaItem, i7);
        if (!PocFeatures.isEnabled(PocFeatures.DebugSmartCropRectInfo)) {
            return onItemLongClick;
        }
        DebugSmartCropRectInfo.getInstance().showCropRectForThumbnail(this.mEventContext.getActivity(), mediaItem, listViewHolder.getBitmap());
        return true;
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.CREATURES, this);
    }

    public boolean supportItem(MediaItem mediaItem) {
        return !DetailsData.of(mediaItem).getCreatures().isEmpty();
    }

    public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        getAdapter().setData(DetailsData.of(mediaItem).getCreatures());
    }

    public LinearLayoutManager createLayoutManager(RecyclerView recyclerView) {
        return DetailsLayoutManager.createLinearLayoutManager(recyclerView);
    }
}
