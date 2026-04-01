package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IAlbumBaseViewAdapter {
    ListViewHolder createViewHolder(ViewGroup viewGroup, int i2);

    int getFooterViewHeight();

    TextView getHeaderDescriptionView();

    int getHeaderDescriptionWidthOffset();

    View getHeaderView();

    int getHintColumnSpan(int i2, int i7) {
        return 0;
    }

    int getHintItemViewHeight(int i2, int i7, int i8) {
        return 0;
    }

    int getHintItemViewType(int i2, int i7);

    int getHintSpanSize(int i2, int i7) {
        return 0;
    }

    int getHintStartSpan(int i2, int i7) {
        return 0;
    }

    int getItemViewType(int i2, int i7);

    boolean isSelectionMode();

    boolean isSingleSelectionMode();

    void onBindViewHolder(ListViewHolder listViewHolder, int i2);

    void onBindViewHolder(ListViewHolder listViewHolder, int i2, int i7);

    boolean supportHeader();

    void updateAlbumBlurInfo(ListViewHolder listViewHolder, int i2, int i7);

    void updateAlbumTypeMargin(ListViewHolder listViewHolder, int i2, boolean z);

    void updateBorders(ListViewHolder listViewHolder, int i2);

    void updateEmptyAlbumViewSize(ListViewHolder listViewHolder, int i2, boolean z);

    void updateGridSize() {
    }

    void updateAlbumSyncMargin(ListViewHolder listViewHolder, int i2, boolean z) {
    }

    void updateFolderChildViewSize(ListViewHolder[] listViewHolderArr, int i2, int i7) {
    }

    void updateFolderViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
    }

    void updateTitleContainerMargin(ListViewHolder listViewHolder, int i2, boolean z) {
    }

    void updateVirtualAlbumViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
    }
}
