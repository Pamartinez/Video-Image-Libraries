package com.samsung.android.vexfwk.sdk.common;

import Ae.c;
import Vf.A;
import fg.a;
import me.x;
import qe.C1227c;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends i implements c {
    public a d;
    public VexFwkHelperBase e;
    public int f;
    public /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ VexFwkHelperBase f4147h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(VexFwkHelperBase vexFwkHelperBase, C1227c cVar) {
        super(2, cVar);
        this.f4147h = vexFwkHelperBase;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        b bVar = new b(this.f4147h, cVar);
        bVar.g = obj;
        return bVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((b) create((A) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x008e A[Catch:{ all -> 0x00ae }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            re.a r0 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r8.f
            r2 = 3
            r3 = 2
            r4 = 1
            me.x r5 = me.x.f4917a
            r6 = 0
            if (r1 == 0) goto L_0x0040
            if (r1 == r4) goto L_0x0033
            if (r1 == r3) goto L_0x0026
            if (r1 != r2) goto L_0x001e
            java.lang.Object r8 = r8.g
            fg.a r8 = (fg.a) r8
            L2.a.A(r9)     // Catch:{ all -> 0x001b }
            goto L_0x00a6
        L_0x001b:
            r9 = move-exception
            goto L_0x00b9
        L_0x001e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0026:
            fg.a r1 = r8.d
            java.lang.Object r3 = r8.g
            Vf.A r3 = (Vf.A) r3
            L2.a.A(r9)     // Catch:{ all -> 0x0030 }
            goto L_0x007d
        L_0x0030:
            r9 = move-exception
            r4 = r1
            goto L_0x0083
        L_0x0033:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase r1 = r8.e
            fg.a r4 = r8.d
            java.lang.Object r7 = r8.g
            Vf.A r7 = (Vf.A) r7
            L2.a.A(r9)
            r9 = r7
            goto L_0x005f
        L_0x0040:
            L2.a.A(r9)
            java.lang.Object r9 = r8.g
            Vf.A r9 = (Vf.A) r9
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase r1 = r8.f4147h
            fg.a r7 = r1.configureFutureMutex
            r8.g = r9
            r8.d = r7
            r8.e = r1
            r8.f = r4
            fg.c r7 = (fg.c) r7
            java.lang.Object r4 = r7.c(r8)
            if (r4 != r0) goto L_0x005e
            goto L_0x00a4
        L_0x005e:
            r4 = r7
        L_0x005f:
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture r1 = r1.configureFuture     // Catch:{ all -> 0x00b7 }
            if (r1 != 0) goto L_0x006b
            fg.c r4 = (fg.c) r4
            r4.d(r6)
            return r5
        L_0x006b:
            r8.g = r9     // Catch:{ all -> 0x0080 }
            r8.d = r4     // Catch:{ all -> 0x0080 }
            r8.e = r6     // Catch:{ all -> 0x0080 }
            r8.f = r3     // Catch:{ all -> 0x0080 }
            java.lang.Object r1 = r1.getAllSessions(r8)     // Catch:{ all -> 0x0080 }
            if (r1 != r0) goto L_0x007a
            goto L_0x00a4
        L_0x007a:
            r3 = r9
            r9 = r1
            r1 = r4
        L_0x007d:
            java.util.List r9 = (java.util.List) r9     // Catch:{ all -> 0x0030 }
            goto L_0x0088
        L_0x0080:
            r1 = move-exception
            r3 = r9
            r9 = r1
        L_0x0083:
            me.j r9 = L2.a.l(r9)     // Catch:{ all -> 0x00b7 }
            r1 = r4
        L_0x0088:
            java.lang.Throwable r4 = me.k.a(r9)     // Catch:{ all -> 0x00ae }
            if (r4 != 0) goto L_0x00b1
            java.util.List r9 = (java.util.List) r9     // Catch:{ all -> 0x00ae }
            com.samsung.android.vexfwk.sdk.common.a r4 = new com.samsung.android.vexfwk.sdk.common.a     // Catch:{ all -> 0x00ae }
            r7 = 0
            r4.<init>(r3, r6, r7)     // Catch:{ all -> 0x00ae }
            r8.g = r1     // Catch:{ all -> 0x00ae }
            r8.d = r6     // Catch:{ all -> 0x00ae }
            r8.e = r6     // Catch:{ all -> 0x00ae }
            r8.f = r2     // Catch:{ all -> 0x00ae }
            java.lang.Object r9 = com.samsung.android.vexfwk.extensions.CollectionsKt.launchEach(r9, (Ae.c) r4, (qe.C1227c) r8)     // Catch:{ all -> 0x00ae }
            if (r9 != r0) goto L_0x00a5
        L_0x00a4:
            return r0
        L_0x00a5:
            r8 = r1
        L_0x00a6:
            java.util.List r9 = (java.util.List) r9     // Catch:{ all -> 0x001b }
            fg.c r8 = (fg.c) r8
            r8.d(r6)
            return r5
        L_0x00ae:
            r9 = move-exception
            r8 = r1
            goto L_0x00b9
        L_0x00b1:
            fg.c r1 = (fg.c) r1
            r1.d(r6)
            return r5
        L_0x00b7:
            r9 = move-exception
            r8 = r4
        L_0x00b9:
            fg.c r8 = (fg.c) r8
            r8.d(r6)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.b.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
