package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewerCapture e;

    public /* synthetic */ a(ViewerCapture viewerCapture, int i2) {
        this.d = i2;
        this.e = viewerCapture;
    }

    public final void run() {
        int i2 = this.d;
        ViewerCapture viewerCapture = this.e;
        switch (i2) {
            case 0:
                viewerCapture.endCapture();
                return;
            case 1:
                viewerCapture.updateButtonVisibility();
                return;
            case 2:
                viewerCapture.lambda$showPreview$4();
                return;
            case 3:
                viewerCapture.lambda$onQuickCropPreviewClicked$10();
                return;
            case 4:
                viewerCapture.lambda$hidePreview$5();
                return;
            case 5:
                viewerCapture.lambda$hidePreview$6();
                return;
            default:
                viewerCapture.lambda$supportCapture$2();
                return;
        }
    }
}
