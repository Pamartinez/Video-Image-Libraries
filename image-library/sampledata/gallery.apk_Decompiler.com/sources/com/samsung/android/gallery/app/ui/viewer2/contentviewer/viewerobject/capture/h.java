package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ ViewerCapture d;

    public /* synthetic */ h(ViewerCapture viewerCapture) {
        this.d = viewerCapture;
    }

    public final void accept(Object obj) {
        this.d.handleEvent((EventMessage) obj);
    }
}
