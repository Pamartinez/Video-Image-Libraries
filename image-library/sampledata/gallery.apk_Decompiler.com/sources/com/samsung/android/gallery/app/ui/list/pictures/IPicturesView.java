package com.samsung.android.gallery.app.ui.list.pictures;

import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IPicturesView extends IBaseListView {
    PicturesViewAdapter getAdapter();

    int getCluster(int i2);

    PicturesLayoutManager getLayoutManager();

    void handleInitializeScroll();

    boolean hasCustomCover();

    boolean isCheckingTargetCluster() {
        return false;
    }

    boolean isPicker() {
        return false;
    }

    void onExpandItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem);

    void onFooterItemClick();

    void onLocationItemClick(String str);

    boolean supportDeleteAnimation();

    boolean supportTimeline();

    boolean updateCustomCover(int i2, Object obj);

    void resetScreenShotFilter() {
    }

    void updateFilterView() {
    }
}
