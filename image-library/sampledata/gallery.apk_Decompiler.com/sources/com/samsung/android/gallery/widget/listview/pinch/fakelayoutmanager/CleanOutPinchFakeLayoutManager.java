package com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager;

import android.view.ViewGroup;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CleanOutPinchFakeLayoutManager extends PinchFakeLayoutManager {
    public CleanOutPinchFakeLayoutManager(PinchLayoutManager pinchLayoutManager, IPinchRecyclerView iPinchRecyclerView, ViewGroup viewGroup, AnimPositionCache animPositionCache) {
        super(pinchLayoutManager, iPinchRecyclerView, viewGroup, animPositionCache);
    }

    public boolean isRealRatio(int i2) {
        return false;
    }

    public void setViewHolderMarginBaseGrid(int i2) {
    }
}
