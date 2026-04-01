package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewerCapture e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ Object g;

    public /* synthetic */ c(ViewerCapture viewerCapture, Bitmap bitmap, MediaItem mediaItem) {
        this.d = 0;
        this.e = viewerCapture;
        this.g = bitmap;
        this.f = mediaItem;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                MediaItem mediaItem = this.f;
                this.e.lambda$onCaptureProcessing$3((Bitmap) this.g, mediaItem);
                return;
            case 1:
                this.e.lambda$completeCapture$8(this.f, (String) this.g);
                return;
            default:
                ((ViewerMediaPlayerCapture) this.e).lambda$onQuickCropPreviewClicked$3(this.f, (String) this.g);
                return;
        }
    }

    public /* synthetic */ c(ViewerCapture viewerCapture, MediaItem mediaItem, String str, int i2) {
        this.d = i2;
        this.e = viewerCapture;
        this.f = mediaItem;
        this.g = str;
    }
}
