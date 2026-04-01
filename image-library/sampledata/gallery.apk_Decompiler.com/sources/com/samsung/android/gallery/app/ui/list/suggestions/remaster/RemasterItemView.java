package com.samsung.android.gallery.app.ui.list.suggestions.remaster;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterItemView {
    /* access modifiers changed from: private */
    public final RemasterItemListAdapter mAdapter;
    private final MediaData.SimpleDataChangeListener mDataChangeListener;
    private RemasterItemListLayoutManager mLayoutManager;
    private MediaData mMediaData;
    private RecyclerView mRecyclerView;

    public RemasterItemView(Context context, MediaData mediaData) {
        AnonymousClass1 r0 = new MediaData.SimpleDataChangeListener() {
            public void onDataChanged() {
                RemasterItemView.this.mAdapter.notifyDataSetChanged();
            }
        };
        this.mDataChangeListener = r0;
        this.mMediaData = mediaData;
        this.mAdapter = new RemasterItemListAdapter(context, mediaData);
        this.mMediaData.register(r0);
    }

    public void bind(RecyclerView recyclerView) {
        if (recyclerView != null) {
            this.mRecyclerView = recyclerView;
            recyclerView.setNestedScrollingEnabled(false);
            this.mLayoutManager = new RemasterItemListLayoutManager(recyclerView.getContext(), 1);
            recyclerView.setAdapter(this.mAdapter);
            recyclerView.setLayoutManager(this.mLayoutManager);
        }
    }

    public void destroy() {
        this.mAdapter.release();
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.close();
            this.mMediaData.unregister(this.mDataChangeListener);
            this.mMediaData = null;
        }
        this.mRecyclerView = null;
    }

    public void setOnItemClickListener(ListViewHolder.OnItemClickListener onItemClickListener) {
        RemasterItemListAdapter remasterItemListAdapter = this.mAdapter;
        if (remasterItemListAdapter != null) {
            remasterItemListAdapter.setOnItemClickListener(onItemClickListener);
        }
    }

    public void updateLayout(Context context) {
        this.mLayoutManager.updateLayout(context);
        this.mAdapter.notifyDataSetChanged();
    }
}
