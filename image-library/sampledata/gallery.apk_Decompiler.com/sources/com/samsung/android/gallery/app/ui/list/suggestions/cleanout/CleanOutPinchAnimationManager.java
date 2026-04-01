package com.samsung.android.gallery.app.ui.list.suggestions.cleanout;

import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManagerV2;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.CleanOutPinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.PinchFakeLayoutManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CleanOutPinchAnimationManager extends PicturesPinchAnimationManagerV2 {
    public CleanOutPinchAnimationManager(PinchLayoutManager pinchLayoutManager, GridInfo.ClusterInfo clusterInfo, boolean z) {
        super(pinchLayoutManager, clusterInfo, z);
    }

    public PinchFakeLayoutManager createFakeLayoutManager(ViewGroup viewGroup) {
        return new CleanOutPinchFakeLayoutManager(this.mLayoutManager, getRecyclerView(), viewGroup, this.mPositionCache);
    }

    public boolean withRealRatio() {
        return false;
    }
}
