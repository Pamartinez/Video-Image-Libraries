package com.samsung.android.gallery.app.ui.list.search.category;

import Ba.g;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.search.ISearchView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocationItemAdapterV2 extends CategoryItemAdapterV2 {
    private ListViewHolder.OnItemClickListener mListener;

    public LocationItemAdapterV2(ISearchView iSearchView, String str, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper) {
        super(iSearchView, str, galleryListView, categoryPropertyHelper, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setListeners$0(ListViewHolder listViewHolder, View view) {
        this.mListener.onItemClick((ListViewHolder) null, 0, listViewHolder.getMediaItem(), 0);
    }

    public void onViewRecycled(ListViewHolder listViewHolder) {
        super.onViewRecycled(listViewHolder);
        ViewUtils.setOnClickListener(listViewHolder.getTitleView(), (View.OnClickListener) null);
    }

    public void setListeners(ListViewHolder listViewHolder) {
        super.setListeners(listViewHolder);
        if (this.mListener != null) {
            ViewUtils.setOnClickListener(listViewHolder.getTitleView(), new g(23, this, listViewHolder));
        }
    }

    public void setOnLocationTitleClickedListener(ListViewHolder.OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mViewHolderFactory.createLocationCarouselItemViewHolder(viewGroup, i2);
    }
}
