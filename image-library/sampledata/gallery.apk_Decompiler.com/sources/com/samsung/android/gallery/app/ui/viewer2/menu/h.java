package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ OpenInOtherWindowMenuItem d;
    public final /* synthetic */ long e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ String g;

    public /* synthetic */ h(OpenInOtherWindowMenuItem openInOtherWindowMenuItem, long j2, MediaItem mediaItem, String str) {
        this.d = openInOtherWindowMenuItem;
        this.e = j2;
        this.f = mediaItem;
        this.g = str;
    }

    public final void accept(Object obj) {
        this.d.lambda$onMenuSelectInternal$4(this.e, this.f, this.g, (List) obj);
    }
}
