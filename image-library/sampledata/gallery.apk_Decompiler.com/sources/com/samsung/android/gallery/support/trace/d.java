package com.samsung.android.gallery.support.trace;

import com.samsung.android.gallery.support.trace.Trace;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3156a;

    public /* synthetic */ d(int i2) {
        this.f3156a = i2;
    }

    public final Object apply(Object obj) {
        Long l = (Long) obj;
        switch (this.f3156a) {
            case 0:
                return Trace.TraceLog.lambda$toString$0(l);
            case 1:
                return Trace.TraceLog.lambda$toString$1(l);
            default:
                return Trace.TraceLog.lambda$toString$2(l);
        }
    }
}
