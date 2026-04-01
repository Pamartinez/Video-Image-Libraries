package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class L implements Runnable {
    public final /* synthetic */ SearchDataPublisher d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ AtomicReference f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Bundle f3045h;

    public /* synthetic */ L(SearchDataPublisher searchDataPublisher, boolean z, AtomicReference atomicReference, String str, Bundle bundle) {
        this.d = searchDataPublisher;
        this.e = z;
        this.f = atomicReference;
        this.g = str;
        this.f3045h = bundle;
    }

    public final void run() {
        this.d.lambda$publishWithFilterRequest$1(this.e, this.f, this.g, this.f3045h);
    }
}
