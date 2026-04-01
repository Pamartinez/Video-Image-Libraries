package com.samsung.android.sum.core.controller;

import com.samsung.android.vexfwk.sdk.imagetagger.VexFwkImageTagger;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ e(Object obj, int i2, Object obj2, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
        this.g = obj2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return ((MediaFilterController) this.f).lambda$sendMessage$7(this.e, (Consumer) this.g);
            default:
                return VexFwkImageTagger.detectImageTagsImpl$lambda$10(this.f, this.e, (VexFwkImageTagger) this.g);
        }
    }
}
