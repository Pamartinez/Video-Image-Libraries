package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.module.dataset.MediaData;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ OpenInOtherWindowMenuItem d;
    public final /* synthetic */ MediaData e;
    public final /* synthetic */ long f;
    public final /* synthetic */ Consumer g;

    public /* synthetic */ i(OpenInOtherWindowMenuItem openInOtherWindowMenuItem, MediaData mediaData, long j2, Consumer consumer) {
        this.d = openInOtherWindowMenuItem;
        this.e = mediaData;
        this.f = j2;
        this.g = consumer;
    }

    public final void run() {
        this.d.lambda$loadFileIds$5(this.e, this.f, this.g);
    }
}
