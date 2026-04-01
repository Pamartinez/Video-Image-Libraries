package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements View.OnClickListener {
    public final /* synthetic */ ViewerCapture d;
    public final /* synthetic */ MediaItem e;

    public /* synthetic */ d(ViewerCapture viewerCapture, MediaItem mediaItem) {
        this.d = viewerCapture;
        this.e = mediaItem;
    }

    public final void onClick(View view) {
        this.d.lambda$completeCapture$7(this.e, view);
    }
}
