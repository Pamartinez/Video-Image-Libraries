package com.samsung.android.gallery.plugins.portrait;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Runnable {
    public final /* synthetic */ LiveEffectDelegate d;
    public final /* synthetic */ Context e;
    public final /* synthetic */ Runnable f;
    public final /* synthetic */ int g;

    public /* synthetic */ o(LiveEffectDelegate liveEffectDelegate, Context context, Runnable runnable, int i2) {
        this.d = liveEffectDelegate;
        this.e = context;
        this.f = runnable;
        this.g = i2;
    }

    public final void run() {
        this.d.lambda$showDialog$20(this.e, this.f, this.g);
    }
}
