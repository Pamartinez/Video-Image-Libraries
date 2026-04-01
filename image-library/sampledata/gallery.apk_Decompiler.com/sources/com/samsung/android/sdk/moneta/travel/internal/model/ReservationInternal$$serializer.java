package com.samsung.android.sdk.moneta.travel.internal.model;

import com.samsung.android.sdk.moneta.travel.internal.ZonedDateTimeSerializer;
import gg.a;
import ig.f;
import jg.b;
import jg.d;
import kg.A;
import kg.C1125f;
import kg.Q;
import kg.T;
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/travel/internal/model/ReservationInternal.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/ReservationInternal;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/travel/internal/model/ReservationInternal;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/travel/internal/model/ReservationInternal;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class ReservationInternal$$serializer implements A {
    public static final ReservationInternal$$serializer INSTANCE;
    private static final f descriptor;

    static {
        ReservationInternal$$serializer reservationInternal$$serializer = new ReservationInternal$$serializer();
        INSTANCE = reservationInternal$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.travel.internal.model.ReservationInternal", reservationInternal$$serializer, 9);
        t.k("reserveNumber", false);
        t.k("startTime", false);
        t.k("endTime", false);
        t.k("title", false);
        t.k("phone", false);
        t.k("place", false);
        t.k("sourcePackage", false);
        t.k("type", false);
        t.k("isAugmented", false);
        descriptor = t;
    }

    private ReservationInternal$$serializer() {
    }

    public final a[] childSerializers() {
        e0 e0Var = e0.f4693a;
        a t = D1.f.t(e0Var);
        ZonedDateTimeSerializer zonedDateTimeSerializer = ZonedDateTimeSerializer.INSTANCE;
        return new a[]{e0Var, zonedDateTimeSerializer, zonedDateTimeSerializer, e0Var, t, PlaceInternal$$serializer.INSTANCE, e0Var, e0Var, C1125f.f4694a};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.time.ZonedDateTime} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.time.ZonedDateTime} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.sdk.moneta.travel.internal.model.ReservationInternal deserialize(jg.c r18) {
        /*
            r17 = this;
            r0 = r18
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.j.e(r0, r1)
            ig.f r1 = descriptor
            jg.a r0 = r0.a(r1)
            r2 = 1
            r4 = 0
            r7 = r4
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r6 = 0
            r15 = 0
            r4 = r2
        L_0x001a:
            if (r4 == 0) goto L_0x0088
            int r5 = r0.d(r1)
            switch(r5) {
                case -1: goto L_0x0085;
                case 0: goto L_0x007d;
                case 1: goto L_0x0071;
                case 2: goto L_0x0064;
                case 3: goto L_0x005c;
                case 4: goto L_0x004f;
                case 5: goto L_0x0042;
                case 6: goto L_0x003a;
                case 7: goto L_0x0032;
                case 8: goto L_0x0029;
                default: goto L_0x0023;
            }
        L_0x0023:
            gg.g r0 = new gg.g
            r0.<init>(r5)
            throw r0
        L_0x0029:
            r5 = 8
            boolean r15 = r0.r(r1, r5)
            r6 = r6 | 256(0x100, float:3.59E-43)
            goto L_0x001a
        L_0x0032:
            r5 = 7
            java.lang.String r14 = r0.l(r1, r5)
            r6 = r6 | 128(0x80, float:1.794E-43)
            goto L_0x001a
        L_0x003a:
            r5 = 6
            java.lang.String r13 = r0.l(r1, r5)
            r6 = r6 | 64
            goto L_0x001a
        L_0x0042:
            com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal$$serializer r5 = com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal$$serializer.INSTANCE
            r3 = 5
            java.lang.Object r3 = r0.n(r1, r3, r5, r12)
            r12 = r3
            com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal r12 = (com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal) r12
            r6 = r6 | 32
            goto L_0x001a
        L_0x004f:
            kg.e0 r3 = kg.e0.f4693a
            r5 = 4
            java.lang.Object r3 = r0.q(r1, r5, r3, r11)
            r11 = r3
            java.lang.String r11 = (java.lang.String) r11
            r6 = r6 | 16
            goto L_0x001a
        L_0x005c:
            r3 = 3
            java.lang.String r10 = r0.l(r1, r3)
            r6 = r6 | 8
            goto L_0x001a
        L_0x0064:
            com.samsung.android.sdk.moneta.travel.internal.ZonedDateTimeSerializer r3 = com.samsung.android.sdk.moneta.travel.internal.ZonedDateTimeSerializer.INSTANCE
            r5 = 2
            java.lang.Object r3 = r0.n(r1, r5, r3, r9)
            r9 = r3
            java.time.ZonedDateTime r9 = (java.time.ZonedDateTime) r9
            r6 = r6 | 4
            goto L_0x001a
        L_0x0071:
            com.samsung.android.sdk.moneta.travel.internal.ZonedDateTimeSerializer r3 = com.samsung.android.sdk.moneta.travel.internal.ZonedDateTimeSerializer.INSTANCE
            java.lang.Object r3 = r0.n(r1, r2, r3, r8)
            r8 = r3
            java.time.ZonedDateTime r8 = (java.time.ZonedDateTime) r8
            r6 = r6 | 2
            goto L_0x001a
        L_0x007d:
            r3 = 0
            java.lang.String r7 = r0.l(r1, r3)
            r6 = r6 | 1
            goto L_0x001a
        L_0x0085:
            r3 = 0
            r4 = r3
            goto L_0x001a
        L_0x0088:
            r0.b(r1)
            com.samsung.android.sdk.moneta.travel.internal.model.ReservationInternal r5 = new com.samsung.android.sdk.moneta.travel.internal.model.ReservationInternal
            r16 = 0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.travel.internal.model.ReservationInternal$$serializer.deserialize(jg.c):com.samsung.android.sdk.moneta.travel.internal.model.ReservationInternal");
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, ReservationInternal reservationInternal) {
        j.e(dVar, "encoder");
        j.e(reservationInternal, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        ReservationInternal.write$Self$pde_sdk_1_0_40_release(reservationInternal, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
