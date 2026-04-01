package com.samsung.android.gallery.app.ui.viewer2.details.items;

import A.a;
import android.view.View;
import android.view.ViewStub;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.details.DetailsListAdapter;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class DetailsListItem<VH extends ListViewHolder, LM extends RecyclerView.LayoutManager> extends DetailsItem implements ListViewHolder.OnItemClickListener, ListViewHolder.OnItemLongClickListener, DetailsViewUpdater {
    private DetailsListAdapter<VH> mAdapter;
    protected boolean mDismissParentPaddingEnd = false;
    private boolean mIsResumed = false;
    protected LM mLayoutManager;
    protected RecyclerView mListView;

    public DetailsListItem(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    private void setItemViewMargin() {
        if (this.mDismissParentPaddingEnd) {
            ViewMarginUtils.setEndMargin(this.mItemView, ResourceCompat.getDimensionPixelOffset(this.mEventContext.getContext(), R.dimen.details_list_horizontal_margin, 0) * -1);
        }
    }

    public abstract DetailsListAdapter<VH> createAdapter(RecyclerView recyclerView);

    public abstract LM createLayoutManager(RecyclerView recyclerView);

    public final DetailsListAdapter<VH> getAdapter() {
        if (this.mAdapter == null) {
            RecyclerView recyclerView = (RecyclerView) getView((int) R.id.moreinfo_item_listview, true);
            this.mListView = recyclerView;
            DetailsListAdapter<VH> createAdapter = createAdapter(recyclerView);
            this.mAdapter = createAdapter;
            createAdapter.setItemClickListener(this);
            this.mAdapter.setItemLongClickListener(this);
            if (PocFeatures.VIEWER_DETAILS_VI) {
                this.mAdapter.setHasStableIds(true);
            }
            if (this.mListView.getAdapter() == null) {
                this.mListView.setAdapter(this.mAdapter);
            }
        }
        return this.mAdapter;
    }

    public final boolean isResumed() {
        return this.mIsResumed;
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        StringCompat stringCompat = this.TAG;
        StringBuilder h5 = a.h(i2, i7, "onListItemClick {position=", ",type=", "}");
        h5.append(MediaItemUtil.getSimpleLog(mediaItem));
        Log.d(stringCompat, h5.toString());
    }

    public boolean onItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        StringCompat stringCompat = this.TAG;
        StringBuilder h5 = a.h(i2, i7, "onItemLongClick {position=", ",type=", "}");
        h5.append(MediaItemUtil.getSimpleLog(mediaItem));
        Log.d(stringCompat, h5.toString());
        return false;
    }

    public void onPause() {
        this.mIsResumed = false;
    }

    public void onRecyclerViewInited(RecyclerView recyclerView) {
        recyclerView.setNestedScrollingEnabled(false);
        LM createLayoutManager = createLayoutManager(recyclerView);
        this.mLayoutManager = createLayoutManager;
        recyclerView.setLayoutManager(createLayoutManager);
    }

    public void onResume() {
        this.mIsResumed = true;
    }

    public void onViewInflate(ViewStub viewStub, View view) {
        onRecyclerViewInited((RecyclerView) view.findViewById(R.id.moreinfo_item_listview));
        setItemViewMargin();
    }

    public void updateLayout() {
        setItemViewMargin();
    }
}
