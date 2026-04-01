package com.samsung.android.gallery.app.ui.menu.list;

import com.samsung.android.gallery.app.controller.EventContext;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ AlbumsMenuHandler d;
    public final /* synthetic */ EventContext e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ b(AlbumsMenuHandler albumsMenuHandler, EventContext eventContext, boolean z) {
        this.d = albumsMenuHandler;
        this.e = eventContext;
        this.f = z;
    }

    public final void run() {
        this.d.lambda$checkAvailableHideAlbumNExecute$0(this.e, this.f);
    }
}
