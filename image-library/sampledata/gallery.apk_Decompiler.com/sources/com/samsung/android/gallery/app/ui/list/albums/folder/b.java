package com.samsung.android.gallery.app.ui.list.albums.folder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ FolderViewPresenter d;

    public /* synthetic */ b(FolderViewPresenter folderViewPresenter) {
        this.d = folderViewPresenter;
    }

    public final void run() {
        this.d.forceReloadData();
    }
}
