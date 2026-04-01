package com.samsung.android.gallery.support.trace;

import com.samsung.android.gallery.support.trace.Trace;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StringBuilder f3154a;
    public final /* synthetic */ HashMap b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ HashMap f3155c;

    public /* synthetic */ b(StringBuilder sb2, HashMap hashMap, HashMap hashMap2) {
        this.f3154a = sb2;
        this.b = hashMap;
        this.f3155c = hashMap2;
    }

    public final void accept(Object obj, Object obj2) {
        Trace.TraceLog.lambda$toString$5(this.f3154a, this.b, this.f3155c, (Long) obj, (ArrayList) obj2);
    }
}
