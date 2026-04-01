package com.samsung.android.gallery.app.ui.menu.list;

import com.samsung.android.gallery.app.controller.EventContext;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ AlbumsMenuHandler d;
    public final /* synthetic */ EventContext e;

    public /* synthetic */ a(AlbumsMenuHandler albumsMenuHandler, EventContext eventContext) {
        this.d = albumsMenuHandler;
        this.e = eventContext;
    }

    public final void run() {
        this.d.lambda$checkAvailableHideAlbumNExecute$1(this.e);
    }
}
