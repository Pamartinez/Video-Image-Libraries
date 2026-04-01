package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ ViewerCapture d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f2577h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ String f2578i;

    public /* synthetic */ b(ViewerCapture viewerCapture, MediaItem mediaItem, MediaItem mediaItem2, boolean z, String str, String str2) {
        this.d = viewerCapture;
        this.e = mediaItem;
        this.f = mediaItem2;
        this.g = z;
        this.f2577h = str;
        this.f2578i = str2;
    }

    public final void run() {
        this.d.lambda$onQuickCropPreviewClicked$9(this.e, this.f, this.g, this.f2577h, this.f2578i);
    }
}
