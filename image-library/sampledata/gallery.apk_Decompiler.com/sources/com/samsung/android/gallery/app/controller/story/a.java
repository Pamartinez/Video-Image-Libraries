package com.samsung.android.gallery.app.controller.story;

import com.samsung.android.gallery.app.controller.story.CollageVideoSaveCmd;
import com.samsung.android.gallery.module.story.transcode.HighlightEncoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ CollageVideoSaveCmd.AnonymousClass1 d;
    public final /* synthetic */ HighlightEncoder e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ a(CollageVideoSaveCmd.AnonymousClass1 r1, HighlightEncoder highlightEncoder, boolean z) {
        this.d = r1;
        this.e = highlightEncoder;
        this.f = z;
    }

    public final void run() {
        this.d.lambda$onCompleted$0(this.e, this.f);
    }
}
