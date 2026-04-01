package com.samsung.android.gallery.support.trace;

import com.samsung.android.gallery.support.trace.Trace;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ HashMap d;
    public final /* synthetic */ HashMap e;
    public final /* synthetic */ HashMap f;

    public /* synthetic */ a(HashMap hashMap, HashMap hashMap2, HashMap hashMap3) {
        this.d = hashMap;
        this.e = hashMap2;
        this.f = hashMap3;
    }

    public final void accept(Object obj) {
        Trace.TraceLog.lambda$toString$3(this.d, this.e, this.f, (Object[]) obj);
    }
}
