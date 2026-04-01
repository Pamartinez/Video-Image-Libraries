package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ ViewerCapture d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ int f;

    public /* synthetic */ g(ViewerCapture viewerCapture, boolean z, int i2) {
        this.d = viewerCapture;
        this.e = z;
        this.f = i2;
    }

    public final void run() {
        this.d.lambda$setButtonVisibility$1(this.e, this.f);
    }
}
