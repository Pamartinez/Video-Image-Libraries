package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ EventContext e;
    public final /* synthetic */ AbsShotModeHandler f;
    public final /* synthetic */ Object g;

    public /* synthetic */ a(AbsShotModeHandler absShotModeHandler, EventContext eventContext, Object obj, int i2) {
        this.d = i2;
        this.f = absShotModeHandler;
        this.e = eventContext;
        this.g = obj;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((DualShotHandler) this.f).lambda$executeInternal$0(this.e, (MediaItem) this.g);
                return;
            default:
                ((PanoramaShotHandler) this.f).lambda$executeInternal$0(this.e, (String) this.g);
                return;
        }
    }
}
