package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.viewer.SaveVideoCaptureCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Consumer {
    public final /* synthetic */ SaveVideoCaptureCmd d;
    public final /* synthetic */ EventContext e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ j(SaveVideoCaptureCmd saveVideoCaptureCmd, EventContext eventContext, MediaItem mediaItem) {
        this.d = saveVideoCaptureCmd;
        this.e = eventContext;
        this.f = mediaItem;
    }

    public final void accept(Object obj) {
        this.d.execute(this.e, this.f, (Bitmap) obj);
    }
}
