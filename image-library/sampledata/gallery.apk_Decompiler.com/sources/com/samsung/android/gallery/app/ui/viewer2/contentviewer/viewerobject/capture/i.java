package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ ViewerMediaPlayerCapture d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ String g;

    public /* synthetic */ i(ViewerMediaPlayerCapture viewerMediaPlayerCapture, boolean z, MediaItem mediaItem, String str) {
        this.d = viewerMediaPlayerCapture;
        this.e = z;
        this.f = mediaItem;
        this.g = str;
    }

    public final void run() {
        this.d.lambda$onQuickCropPreviewClicked$2(this.e, this.f, this.g);
    }
}
