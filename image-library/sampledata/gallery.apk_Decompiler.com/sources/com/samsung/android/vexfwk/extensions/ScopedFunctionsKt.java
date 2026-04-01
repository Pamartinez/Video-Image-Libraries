package com.samsung.android.vexfwk.extensions;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a>\u0010\u0006\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00028\u00002\"\u0010\u0005\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0001H@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"T", "Lkotlin/Function2;", "Lqe/c;", "Lme/x;", "", "block", "suspendAlso", "(Ljava/lang/Object;LAe/c;Lqe/c;)Ljava/lang/Object;", "VexFrameworkSDK_forInternalRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ScopedFunctionsKt {
    /* JADX WARNING: type inference failed for: r0v2, types: [se.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object suspendAlso(T r4, Ae.c r5, qe.C1227c r6) {
        /*
            boolean r0 = r6 instanceof jd.C1100c
            if (r0 == 0) goto L_0x0013
            r0 = r6
            jd.c r0 = (jd.C1100c) r0
            int r1 = r0.f
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f = r1
            goto L_0x0018
        L_0x0013:
            jd.c r0 = new jd.c
            r0.<init>(r6)
        L_0x0018:
            java.lang.Object r6 = r0.e
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.f
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            java.lang.Object r4 = r0.d
            L2.a.A(r6)
            return r4
        L_0x0029:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0031:
            L2.a.A(r6)
            r0.d = r4
            r0.f = r3
            java.lang.Object r5 = r5.invoke(r4, r0)
            if (r5 != r1) goto L_0x003f
            return r1
        L_0x003f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.extensions.ScopedFunctionsKt.suspendAlso(java.lang.Object, Ae.c, qe.c):java.lang.Object");
    }
}
