package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaPlayerViewImp.AnonymousClass1 e;
    public final /* synthetic */ Object f;

    public /* synthetic */ a(MediaPlayerViewImp.AnonymousClass1 r1, Object obj, int i2) {
        this.d = i2;
        this.e = r1;
        this.f = obj;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onInitializeAccessibilityNodeInfo$0((AccessibilityNodeInfo) this.f);
                return;
            default:
                this.e.lambda$onPopulateAccessibilityEvent$1((View) this.f);
                return;
        }
    }
}
