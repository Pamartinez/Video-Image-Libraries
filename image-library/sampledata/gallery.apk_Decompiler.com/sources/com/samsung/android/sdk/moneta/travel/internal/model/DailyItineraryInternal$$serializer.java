package com.samsung.android.sdk.moneta.travel.internal.model;

import gg.a;
import ig.f;
import jg.b;
import jg.d;
import kg.A;
import kg.H;
import kg.Q;
import kg.T;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/travel/internal/model/DailyItineraryInternal.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/DailyItineraryInternal;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/travel/internal/model/DailyItineraryInternal;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/travel/internal/model/DailyItineraryInternal;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class DailyItineraryInternal$$serializer implements A {
    public static final DailyItineraryInternal$$serializer INSTANCE;
    private static final f descriptor;

    static {
        DailyItineraryInternal$$serializer dailyItineraryInternal$$serializer = new DailyItineraryInternal$$serializer();
        INSTANCE = dailyItineraryInternal$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.travel.internal.model.DailyItineraryInternal", dailyItineraryInternal$$serializer, 6);
        t.k("dayIndex", false);
        t.k("transportationList", false);
        t.k("reservationList", false);
        t.k("accommodation", true);
        t.k("accommodationList", true);
        t.k("userPlanList", true);
        descriptor = t;
    }

    private DailyItineraryInternal$$serializer() {
    }

    public final a[] childSerializers() {
        a[] access$get$childSerializers$cp = DailyItineraryInternal.$childSerializers;
        return new a[]{H.f4669a, access$get$childSerializers$cp[1], access$get$childSerializers$cp[2], access$get$childSerializers$cp[3], access$get$childSerializers$cp[4], access$get$childSerializers$cp[5]};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.sdk.moneta.travel.internal.model.DailyItineraryInternal deserialize(jg.c r14) {
        /*
            r13 = this;
            java.lang.String r13 = "decoder"
            kotlin.jvm.internal.j.e(r14, r13)
            ig.f r13 = descriptor
            jg.a r14 = r14.a(r13)
            gg.a[] r0 = com.samsung.android.sdk.moneta.travel.internal.model.DailyItineraryInternal.$childSerializers
            r1 = 1
            r2 = 0
            r3 = 0
            r5 = r2
            r6 = r5
            r7 = r3
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r3 = r1
        L_0x001a:
            if (r3 == 0) goto L_0x0072
            int r4 = r14.d(r13)
            switch(r4) {
                case -1: goto L_0x0070;
                case 0: goto L_0x0069;
                case 1: goto L_0x005d;
                case 2: goto L_0x0050;
                case 3: goto L_0x0043;
                case 4: goto L_0x0036;
                case 5: goto L_0x0029;
                default: goto L_0x0023;
            }
        L_0x0023:
            gg.g r13 = new gg.g
            r13.<init>(r4)
            throw r13
        L_0x0029:
            r4 = 5
            r12 = r0[r4]
            java.lang.Object r4 = r14.n(r13, r4, r12, r11)
            r11 = r4
            java.util.List r11 = (java.util.List) r11
            r5 = r5 | 32
            goto L_0x001a
        L_0x0036:
            r4 = 4
            r12 = r0[r4]
            java.lang.Object r4 = r14.n(r13, r4, r12, r10)
            r10 = r4
            java.util.List r10 = (java.util.List) r10
            r5 = r5 | 16
            goto L_0x001a
        L_0x0043:
            r4 = 3
            r12 = r0[r4]
            java.lang.Object r4 = r14.n(r13, r4, r12, r9)
            r9 = r4
            java.util.List r9 = (java.util.List) r9
            r5 = r5 | 8
            goto L_0x001a
        L_0x0050:
            r4 = 2
            r12 = r0[r4]
            java.lang.Object r4 = r14.n(r13, r4, r12, r8)
            r8 = r4
            java.util.List r8 = (java.util.List) r8
            r5 = r5 | 4
            goto L_0x001a
        L_0x005d:
            r4 = r0[r1]
            java.lang.Object r4 = r14.n(r13, r1, r4, r7)
            r7 = r4
            java.util.List r7 = (java.util.List) r7
            r5 = r5 | 2
            goto L_0x001a
        L_0x0069:
            int r6 = r14.x(r13, r2)
            r5 = r5 | 1
            goto L_0x001a
        L_0x0070:
            r3 = r2
            goto L_0x001a
        L_0x0072:
            r14.b(r13)
            com.samsung.android.sdk.moneta.travel.internal.model.DailyItineraryInternal r4 = new com.samsung.android.sdk.moneta.travel.internal.model.DailyItineraryInternal
            r12 = 0
            r4.<init>((int) r5, (int) r6, (java.util.List) r7, (java.util.List) r8, (java.util.List) r9, (java.util.List) r10, (java.util.List) r11, (kg.a0) r12)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.travel.internal.model.DailyItineraryInternal$$serializer.deserialize(jg.c):com.samsung.android.sdk.moneta.travel.internal.model.DailyItineraryInternal");
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, DailyItineraryInternal dailyItineraryInternal) {
        j.e(dVar, "encoder");
        j.e(dailyItineraryInternal, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        DailyItineraryInternal.write$Self$pde_sdk_1_0_40_release(dailyItineraryInternal, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
