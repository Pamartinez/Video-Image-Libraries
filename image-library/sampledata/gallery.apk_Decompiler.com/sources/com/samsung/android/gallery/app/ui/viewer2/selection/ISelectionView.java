package com.samsung.android.gallery.app.ui.viewer2.selection;

import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISelectionView extends IMvpBaseView {
    SelectionViewHolder findViewHolder(int i2);

    MediaItem[] getAllItems();

    MediaItem getBestItem();

    int getItemCount();

    int getLastFocusedPosition();

    MediaItem[] getSelectedItems();

    Integer[] getSelectedPositions();

    void handleShareInternal();

    void onViewHolderBound(ListViewHolder listViewHolder);

    void setLastFocusedPosition(int i2);
}
