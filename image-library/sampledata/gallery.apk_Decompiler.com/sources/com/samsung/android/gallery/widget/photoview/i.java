package com.samsung.android.gallery.widget.photoview;

import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Consumer {
    public final /* synthetic */ DebugDelegate d;
    public final /* synthetic */ StringBuilder e;
    public final /* synthetic */ int f;

    public /* synthetic */ i(DebugDelegate debugDelegate, StringBuilder sb2, int i2) {
        this.d = debugDelegate;
        this.e = sb2;
        this.f = i2;
    }

    public final void accept(Object obj) {
        this.d.lambda$drawDebugInfo$3(this.e, this.f, (Map.Entry) obj);
    }
}
