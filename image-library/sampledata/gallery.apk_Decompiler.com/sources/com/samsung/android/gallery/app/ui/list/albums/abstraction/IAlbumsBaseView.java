package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IAlbumsBaseView extends IBaseListView {
    AlbumsBaseViewAdapter getAdapter();

    AlbumsBaseLayoutManager getLayoutManager();

    boolean isAlbum() {
        return true;
    }

    boolean isSplitMode() {
        return false;
    }

    void onEnterMoveMode();

    void onExitMoveMode();

    boolean onNewItemCreated(String str, String str2, int i2, String str3, int i7);
}
