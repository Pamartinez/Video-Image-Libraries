package com.samsung.android.gallery.app.ui.list.albums.mx.header;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMxAlbumsHeaderView {
    String getLocationKey();

    void onDataChanged();

    void refreshHeader(boolean z, boolean z3);

    boolean updateHeaderView(MediaItem mediaItem);
}
