package com.samsung.android.gallery.plugins.portrait;

import com.samsung.android.gallery.plugins.portrait.LiveEffectDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s implements Runnable {
    public final /* synthetic */ LiveEffectDelegate.AnonymousClass2 d;
    public final /* synthetic */ float e;

    public /* synthetic */ s(LiveEffectDelegate.AnonymousClass2 r1, float f) {
        this.d = r1;
        this.e = f;
    }

    public final void run() {
        this.d.lambda$onProgress$3(this.e);
    }
}
