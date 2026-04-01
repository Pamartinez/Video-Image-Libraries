package com.samsung.android.gallery.module.c2pa;

import com.samsung.android.gallery.module.c2pa.C2paWrapper;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ C2paWrapper d;
    public final /* synthetic */ int e;
    public final /* synthetic */ C2paWrapper.Reference f;

    public /* synthetic */ a(C2paWrapper c2paWrapper, int i2, C2paWrapper.Reference reference) {
        this.d = c2paWrapper;
        this.e = i2;
        this.f = reference;
    }

    public final void accept(Object obj) {
        this.d.lambda$extract$2(this.e, this.f, (C2paInfo) obj);
    }
}
