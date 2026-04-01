package com.samsung.android.gallery.support.trace;

import com.samsung.android.gallery.support.trace.Trace;
import java.util.Stack;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StringBuilder e;

    public /* synthetic */ c(StringBuilder sb2, int i2) {
        this.d = i2;
        this.e = sb2;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        StringBuilder sb2 = this.e;
        switch (i2) {
            case 0:
                sb2.append((Trace.TraceLog.TraceData) obj);
                return;
            default:
                Trace.TraceLog.lambda$toString$4(sb2, (Stack) obj);
                return;
        }
    }
}
