package com.samsung.android.vexfwk.coroutines;

import Ae.c;
import Vf.C;
import Vf.G;
import kotlin.Metadata;
import qe.C1232h;
import qe.C1233i;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aU\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\"\u0010\n\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"T", "Lcom/samsung/android/vexfwk/coroutines/VexFwkBaseScope;", "Lqe/h;", "context", "LVf/C;", "start", "Lkotlin/Function2;", "LVf/A;", "Lqe/c;", "", "block", "LVf/G;", "async", "(Lcom/samsung/android/vexfwk/coroutines/VexFwkBaseScope;Lqe/h;LVf/C;LAe/c;)LVf/G;", "VexFrameworkSDK_forInternalRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkScopeExtensionsKt {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: Vf.o0} */
    /* JADX WARNING: type inference failed for: r2v2, types: [Vf.G, Vf.a] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> Vf.G async(com.samsung.android.vexfwk.coroutines.VexFwkBaseScope r1, qe.C1232h r2, Vf.C r3, Ae.c r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.j.e(r1, r0)
            java.lang.String r0 = "context"
            kotlin.jvm.internal.j.e(r2, r0)
            java.lang.String r0 = "start"
            kotlin.jvm.internal.j.e(r3, r0)
            java.lang.String r0 = "block"
            kotlin.jvm.internal.j.e(r4, r0)
            Vf.A r1 = r1.get()
            qe.h r1 = Vf.D.o(r1, r2)
            Vf.C r2 = Vf.C.LAZY
            if (r3 != r2) goto L_0x0026
            Vf.o0 r2 = new Vf.o0
            r2.<init>(r1, r4)
            goto L_0x002c
        L_0x0026:
            Vf.H r2 = new Vf.H
            r0 = 1
            r2.<init>(r1, r0)
        L_0x002c:
            r2.X(r3, r2, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.coroutines.VexFwkScopeExtensionsKt.async(com.samsung.android.vexfwk.coroutines.VexFwkBaseScope, qe.h, Vf.C, Ae.c):Vf.G");
    }

    public static /* synthetic */ G async$default(VexFwkBaseScope vexFwkBaseScope, C1232h hVar, C c5, c cVar, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            hVar = C1233i.d;
        }
        if ((i2 & 2) != 0) {
            c5 = C.DEFAULT;
        }
        return async(vexFwkBaseScope, hVar, c5, cVar);
    }
}
