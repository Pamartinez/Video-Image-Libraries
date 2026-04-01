package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ OpenInOtherWindowMenuItem d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ int f;
    public final /* synthetic */ String g;

    public /* synthetic */ g(OpenInOtherWindowMenuItem openInOtherWindowMenuItem, MediaItem mediaItem, int i2, String str) {
        this.d = openInOtherWindowMenuItem;
        this.e = mediaItem;
        this.f = i2;
        this.g = str;
    }

    public final void run() {
        this.d.lambda$onMenuSelectInternal$2(this.e, this.f, this.g);
    }
}
