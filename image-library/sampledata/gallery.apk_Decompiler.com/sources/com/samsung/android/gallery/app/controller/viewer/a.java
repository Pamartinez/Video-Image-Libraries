package com.samsung.android.gallery.app.controller.viewer;

import android.os.Messenger;
import com.samsung.android.gallery.app.controller.viewer.CopyToClipboardCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ CopyToClipboardCmd.ClipboardService.AnonymousClass1 d;
    public final /* synthetic */ Messenger e;

    public /* synthetic */ a(CopyToClipboardCmd.ClipboardService.AnonymousClass1 r1, Messenger messenger) {
        this.d = r1;
        this.e = messenger;
    }

    public final void run() {
        this.d.lambda$onServiceConnected$0(this.e);
    }
}
