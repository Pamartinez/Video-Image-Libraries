package com.samsung.android.gallery.module.lottie.recorder;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ BgmMixer d;
    public final /* synthetic */ Context e;

    public /* synthetic */ b(BgmMixer bgmMixer, Context context) {
        this.d = bgmMixer;
        this.e = context;
    }

    public final void run() {
        this.d.lambda$mix$0(this.e);
    }
}
