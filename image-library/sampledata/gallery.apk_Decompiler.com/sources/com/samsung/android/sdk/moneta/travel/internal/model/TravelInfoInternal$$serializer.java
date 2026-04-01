package com.samsung.android.sdk.moneta.travel.internal.model;

import gg.a;
import ig.f;
import jg.b;
import jg.d;
import kg.A;
import kg.C1125f;
import kg.H;
import kg.L;
import kg.Q;
import kg.T;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/travel/internal/model/TravelInfoInternal;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class TravelInfoInternal$$serializer implements A {
    public static final TravelInfoInternal$$serializer INSTANCE;
    private static final f descriptor;

    static {
        TravelInfoInternal$$serializer travelInfoInternal$$serializer = new TravelInfoInternal$$serializer();
        INSTANCE = travelInfoInternal$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.travel.internal.model.TravelInfoInternal", travelInfoInternal$$serializer, 5);
        t.k("isDomestic", false);
        t.k("startTime", false);
        t.k("endTime", false);
        t.k("travelDuration", false);
        t.k("isAugmented", false);
        descriptor = t;
    }

    private TravelInfoInternal$$serializer() {
    }

    public final a[] childSerializers() {
        C1125f fVar = C1125f.f4694a;
        L l = L.f4673a;
        return new a[]{D1.f.t(fVar), l, l, H.f4669a, fVar};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.Boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.sdk.moneta.travel.internal.model.TravelInfoInternal deserialize(jg.c r18) {
        /*
            r17 = this;
            r0 = r18
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.j.e(r0, r1)
            ig.f r1 = descriptor
            jg.a r0 = r0.a(r1)
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 0
            r8 = r3
            r14 = r8
            r15 = r14
            r9 = r4
            r10 = r5
            r12 = r10
            r4 = r2
        L_0x0019:
            if (r4 == 0) goto L_0x005f
            int r5 = r0.d(r1)
            r6 = -1
            if (r5 == r6) goto L_0x005d
            if (r5 == 0) goto L_0x0051
            if (r5 == r2) goto L_0x004a
            r6 = 2
            if (r5 == r6) goto L_0x0043
            r6 = 3
            if (r5 == r6) goto L_0x003c
            r6 = 4
            if (r5 != r6) goto L_0x0036
            boolean r15 = r0.r(r1, r6)
            r8 = r8 | 16
            goto L_0x0019
        L_0x0036:
            gg.g r0 = new gg.g
            r0.<init>(r5)
            throw r0
        L_0x003c:
            int r14 = r0.x(r1, r6)
            r8 = r8 | 8
            goto L_0x0019
        L_0x0043:
            long r12 = r0.t(r1, r6)
            r8 = r8 | 4
            goto L_0x0019
        L_0x004a:
            long r10 = r0.t(r1, r2)
            r8 = r8 | 2
            goto L_0x0019
        L_0x0051:
            kg.f r5 = kg.C1125f.f4694a
            java.lang.Object r5 = r0.q(r1, r3, r5, r9)
            r9 = r5
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            r8 = r8 | 1
            goto L_0x0019
        L_0x005d:
            r4 = r3
            goto L_0x0019
        L_0x005f:
            r0.b(r1)
            com.samsung.android.sdk.moneta.travel.internal.model.TravelInfoInternal r7 = new com.samsung.android.sdk.moneta.travel.internal.model.TravelInfoInternal
            r16 = 0
            r7.<init>(r8, r9, r10, r12, r14, r15, r16)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.travel.internal.model.TravelInfoInternal$$serializer.deserialize(jg.c):com.samsung.android.sdk.moneta.travel.internal.model.TravelInfoInternal");
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, TravelInfoInternal travelInfoInternal) {
        j.e(dVar, "encoder");
        j.e(travelInfoInternal, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        TravelInfoInternal.write$Self$pde_sdk_1_0_40_release(travelInfoInternal, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
