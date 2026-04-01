package com.samsung.android.imagetranslation;

import com.samsung.android.imagetranslation.LttEngine;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ LttEngine.AnonymousClass1 d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;

    public /* synthetic */ a(LttEngine.AnonymousClass1 r1, int i2, int i7) {
        this.d = r1;
        this.e = i2;
        this.f = i7;
    }

    public final void run() {
        this.d.lambda$onFailure$1(this.e, this.f);
    }
}
