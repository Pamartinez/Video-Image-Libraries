package com.samsung.android.gallery.app.ui.list.albums.folder;

import androidx.appcompat.widget.Toolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ FolderViewPresenter d;
    public final /* synthetic */ Toolbar e;

    public /* synthetic */ d(FolderViewPresenter folderViewPresenter, Toolbar toolbar) {
        this.d = folderViewPresenter;
        this.e = toolbar;
    }

    public final void run() {
        this.d.lambda$updateSubTitle$1(this.e);
    }
}
