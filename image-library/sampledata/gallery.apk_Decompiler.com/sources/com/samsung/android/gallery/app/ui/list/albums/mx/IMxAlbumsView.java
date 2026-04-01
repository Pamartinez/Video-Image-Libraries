package com.samsung.android.gallery.app.ui.list.albums.mx;

import com.samsung.android.gallery.app.ui.list.albums.IAlbumsView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMxAlbumsView extends IAlbumsView {
    MxAlbumsViewAdapter getAdapter();

    AlbumKind getAlbumKind(int i2);

    int getSharingLimitCount();

    boolean isEssentialViewMode();

    void updateSharingBadge();

    void inflateEmptyView() {
    }
}
