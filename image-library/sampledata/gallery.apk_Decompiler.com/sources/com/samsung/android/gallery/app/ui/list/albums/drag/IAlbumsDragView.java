package com.samsung.android.gallery.app.ui.list.albums.drag;

import com.samsung.android.gallery.app.ui.list.albums.abstraction.IAlbumsBaseView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IAlbumsDragView extends IAlbumsBaseView {
    AlbumsDragAdapter getAdapter();

    boolean onFolderCreatedFromDrag(int i2, String str);

    void onFolderCreationFailed(boolean z);
}
