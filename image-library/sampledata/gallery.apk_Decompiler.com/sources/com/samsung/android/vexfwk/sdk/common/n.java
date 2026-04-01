package com.samsung.android.vexfwk.sdk.common;

import Ae.c;
import java.util.Map;
import kotlin.jvm.internal.u;
import me.x;
import qe.C1227c;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n extends i implements c {
    public h d;
    public u e;
    public u f;
    public u g;

    /* renamed from: h  reason: collision with root package name */
    public int f4166h;

    /* renamed from: i  reason: collision with root package name */
    public /* synthetic */ Object f4167i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ VexFwkHelperConfigurationFuture f4168j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public n(VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture, C1227c cVar) {
        super(2, cVar);
        this.f4168j = vexFwkHelperConfigurationFuture;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        n nVar = new n(this.f4168j, cVar);
        nVar.f4167i = obj;
        return nVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((n) create((Map.Entry) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: com.samsung.android.vexfwk.session.VexFwkUsecase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.samsung.android.vexfwk.sdk.common.h} */
    /* JADX WARNING: type inference failed for: r8v18, types: [kotlin.jvm.internal.u, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v5, types: [kotlin.jvm.internal.u, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            re.a r0 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r7.f4166h
            r2 = 1
            if (r1 == 0) goto L_0x0023
            if (r1 != r2) goto L_0x001b
            kotlin.jvm.internal.u r0 = r7.g
            kotlin.jvm.internal.u r1 = r7.f
            kotlin.jvm.internal.u r2 = r7.e
            com.samsung.android.vexfwk.sdk.common.h r3 = r7.d
            java.lang.Object r4 = r7.f4167i
            com.samsung.android.vexfwk.session.VexFwkUsecase r4 = (com.samsung.android.vexfwk.session.VexFwkUsecase) r4
            L2.a.A(r8)     // Catch:{ all -> 0x0019 }
            goto L_0x0061
        L_0x0019:
            r7 = move-exception
            goto L_0x008a
        L_0x001b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0023:
            L2.a.A(r8)
            java.lang.Object r8 = r7.f4167i
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            java.lang.Object r1 = r8.getKey()
            r4 = r1
            com.samsung.android.vexfwk.session.VexFwkUsecase r4 = (com.samsung.android.vexfwk.session.VexFwkUsecase) r4
            java.lang.Object r8 = r8.getValue()
            r3 = r8
            com.samsung.android.vexfwk.sdk.common.h r3 = (com.samsung.android.vexfwk.sdk.common.h) r3
            kotlin.jvm.internal.u r8 = new kotlin.jvm.internal.u
            r8.<init>()
            kotlin.jvm.internal.u r1 = new kotlin.jvm.internal.u
            r1.<init>()
            com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture r5 = r7.f4168j
            qe.h r6 = r7.getContext()     // Catch:{ all -> 0x0088 }
            Vf.D.f(r6)     // Catch:{ all -> 0x0088 }
            r7.f4167i = r4     // Catch:{ all -> 0x0088 }
            r7.d = r3     // Catch:{ all -> 0x0088 }
            r7.e = r8     // Catch:{ all -> 0x0088 }
            r7.f = r1     // Catch:{ all -> 0x0088 }
            r7.g = r8     // Catch:{ all -> 0x0088 }
            r7.f4166h = r2     // Catch:{ all -> 0x0088 }
            java.lang.Object r2 = r5.createSessionConfig(r3, r7)     // Catch:{ all -> 0x0088 }
            if (r2 != r0) goto L_0x005e
            return r0
        L_0x005e:
            r0 = r8
            r8 = r2
            r2 = r0
        L_0x0061:
            r0.d = r8     // Catch:{ all -> 0x0019 }
            qe.h r8 = r7.getContext()     // Catch:{ all -> 0x0019 }
            Vf.D.f(r8)     // Catch:{ all -> 0x0019 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r8 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession     // Catch:{ all -> 0x0019 }
            r8.<init>(r4)     // Catch:{ all -> 0x0019 }
            r1.d = r8     // Catch:{ all -> 0x0019 }
            qe.h r7 = r7.getContext()     // Catch:{ all -> 0x0019 }
            Vf.D.f(r7)     // Catch:{ all -> 0x0019 }
            java.lang.Object r7 = r1.d     // Catch:{ all -> 0x0019 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r7 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession) r7     // Catch:{ all -> 0x0019 }
            java.lang.Object r8 = r2.d     // Catch:{ all -> 0x0019 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest r8 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest) r8     // Catch:{ all -> 0x0019 }
            r7.configure(r8)     // Catch:{ all -> 0x0019 }
            java.lang.Object r7 = r1.d     // Catch:{ all -> 0x0019 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r7 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession) r7     // Catch:{ all -> 0x0019 }
            goto L_0x008e
        L_0x0088:
            r7 = move-exception
            r2 = r8
        L_0x008a:
            me.j r7 = L2.a.l(r7)
        L_0x008e:
            boolean r8 = r7 instanceof me.j
            if (r8 != 0) goto L_0x009a
            r8 = r7
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r8 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession) r8
            Vf.r r0 = r3.f
            r0.H(r8)
        L_0x009a:
            java.lang.Throwable r8 = me.k.a(r7)
            if (r8 == 0) goto L_0x00ef
            boolean r0 = r8 instanceof java.util.concurrent.CancellationException
            if (r0 == 0) goto L_0x00ba
            java.lang.String r0 = com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture.TAG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Cancelled while configuring session for usecase "
            r5.<init>(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            android.util.Log.i(r0, r4)
            goto L_0x00cf
        L_0x00ba:
            java.lang.String r0 = com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture.TAG
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Failed to configure session for usecase "
            r5.<init>(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            android.util.Log.e(r0, r4, r8)
        L_0x00cf:
            Vf.r r0 = r3.f
            r0.getClass()
            Vf.u r3 = new Vf.u
            r4 = 0
            r3.<init>(r8, r4)
            r0.H(r3)
            java.lang.Object r8 = r2.d
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest r8 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest) r8
            if (r8 == 0) goto L_0x00e6
            r8.close()
        L_0x00e6:
            java.lang.Object r8 = r1.d
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r8 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession) r8
            if (r8 == 0) goto L_0x00ef
            r8.close()
        L_0x00ef:
            me.k r8 = new me.k
            r8.<init>(r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.common.n.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
