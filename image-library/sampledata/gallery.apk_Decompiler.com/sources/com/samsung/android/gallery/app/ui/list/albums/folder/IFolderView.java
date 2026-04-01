package com.samsung.android.gallery.app.ui.list.albums.folder;

import com.samsung.android.gallery.app.ui.list.albums.drag.IAlbumsDragView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IFolderView extends IAlbumsDragView {
    String getFolderLocationKey();

    void onAlbumsAdded(int[] iArr);

    void refreshFolder(String str, int i2);
}
