package com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager;

import android.view.ViewGroup;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumPinchFakeLayoutManager extends PinchFakeLayoutManager {
    public AlbumPinchFakeLayoutManager(PinchLayoutManager pinchLayoutManager, IPinchRecyclerView iPinchRecyclerView, ViewGroup viewGroup, AnimPositionCache animPositionCache) {
        super(pinchLayoutManager, iPinchRecyclerView, viewGroup, animPositionCache);
    }

    public void putDataHolder(int i2, int i7) {
        ListViewHolder createFakeViewHolder = createFakeViewHolder(i2, i7);
        if (isGridData(this.mLayoutManager.getHintItemViewType(i2, i7))) {
            ViewUtils.setText(createFakeViewHolder.getTitleView(), " ");
            ViewUtils.setText(createFakeViewHolder.getCountView(), " ");
        }
        this.mRefHolders.put(2, createFakeViewHolder);
    }
}
