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

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/travel/internal/model/AccommodationInternal.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/AccommodationInternal;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/travel/internal/model/AccommodationInternal;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/travel/internal/model/AccommodationInternal;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class AccommodationInternal$$serializer implements A {
    public static final AccommodationInternal$$serializer INSTANCE;
    private static final f descriptor;

    static {
        AccommodationInternal$$serializer accommodationInternal$$serializer = new AccommodationInternal$$serializer();
        INSTANCE = accommodationInternal$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.travel.internal.model.AccommodationInternal", accommodationInternal$$serializer, 8);
        t.k("reserveNumber", false);
        t.k("checkInTime", false);
        t.k("checkOutTime", false);
        t.k("title", false);
        t.k("phone", false);
        t.k("place", false);
        t.k("sourcePackage", false);
        t.k("isAugmented", false);
        descriptor = t;
    }

    private AccommodationInternal$$serializer() {
    }

    public final a[] childSerializers() {
        e0 e0Var = e0.f4693a;
        a t = D1.f.t(e0Var);
        ZonedDateTimeSerializer zonedDateTimeSerializer = ZonedDateTimeSerializer.INSTANCE;
        return new a[]{e0Var, zonedDateTimeSerializer, zonedDateTimeSerializer, e0Var, t, PlaceInternal$$serializer.INSTANCE, e0Var, C1125f.f4694a};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.time.ZonedDateTime} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.time.ZonedDateTime} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.sdk.moneta.travel.internal.model.AccommodationInternal deserialize(jg.c r15) {
        /*
            r14 = this;
            java.lang.String r14 = "decoder"
            kotlin.jvm.internal.j.e(r15, r14)
            ig.f r14 = descriptor
            jg.a r15 = r15.a(r14)
            r0 = 1
            r1 = 0
            r2 = 0
            r4 = r1
            r12 = r4
            r5 = r2
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r2 = r0
        L_0x0018:
            if (r2 == 0) goto L_0x007b
            int r3 = r15.d(r14)
            switch(r3) {
                case -1: goto L_0x0079;
                case 0: goto L_0x0072;
                case 1: goto L_0x0066;
                case 2: goto L_0x0059;
                case 3: goto L_0x0051;
                case 4: goto L_0x0044;
                case 5: goto L_0x0037;
                case 6: goto L_0x002f;
                case 7: goto L_0x0027;
                default: goto L_0x0021;
            }
        L_0x0021:
            gg.g r14 = new gg.g
            r14.<init>(r3)
            throw r14
        L_0x0027:
            r3 = 7
            boolean r12 = r15.r(r14, r3)
            r4 = r4 | 128(0x80, float:1.794E-43)
            goto L_0x0018
        L_0x002f:
            r3 = 6
            java.lang.String r11 = r15.l(r14, r3)
            r4 = r4 | 64
            goto L_0x0018
        L_0x0037:
            com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal$$serializer r3 = com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal$$serializer.INSTANCE
            r13 = 5
            java.lang.Object r3 = r15.n(r14, r13, r3, r10)
            r10 = r3
            com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal r10 = (com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal) r10
            r4 = r4 | 32
            goto L_0x0018
        L_0x0044:
            kg.e0 r3 = kg.e0.f4693a
            r13 = 4
            java.lang.Object r3 = r15.q(r14, r13, r3, r9)
            r9 = r3
            java.lang.String r9 = (java.lang.String) r9
            r4 = r4 | 16
            goto L_0x0018
        L_0x0051:
            r3 = 3
            java.lang.String r8 = r15.l(r14, r3)
            r4 = r4 | 8
            goto L_0x0018
        L_0x0059:
            com.samsung.android.sdk.moneta.travel.internal.ZonedDateTimeSerializer r3 = com.samsung.android.sdk.moneta.travel.internal.ZonedDateTimeSerializer.INSTANCE
            r13 = 2
            java.lang.Object r3 = r15.n(r14, r13, r3, r7)
            r7 = r3
            java.time.ZonedDateTime r7 = (java.time.ZonedDateTime) r7
            r4 = r4 | 4
            goto L_0x0018
        L_0x0066:
            com.samsung.android.sdk.moneta.travel.internal.ZonedDateTimeSerializer r3 = com.samsung.android.sdk.moneta.travel.internal.ZonedDateTimeSerializer.INSTANCE
            java.lang.Object r3 = r15.n(r14, r0, r3, r6)
            r6 = r3
            java.time.ZonedDateTime r6 = (java.time.ZonedDateTime) r6
            r4 = r4 | 2
            goto L_0x0018
        L_0x0072:
            java.lang.String r5 = r15.l(r14, r1)
            r4 = r4 | 1
            goto L_0x0018
        L_0x0079:
            r2 = r1
            goto L_0x0018
        L_0x007b:
            r15.b(r14)
            com.samsung.android.sdk.moneta.travel.internal.model.AccommodationInternal r3 = new com.samsung.android.sdk.moneta.travel.internal.model.AccommodationInternal
            r13 = 0
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.travel.internal.model.AccommodationInternal$$serializer.deserialize(jg.c):com.samsung.android.sdk.moneta.travel.internal.model.AccommodationInternal");
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, AccommodationInternal accommodationInternal) {
        j.e(dVar, "encoder");
        j.e(accommodationInternal, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        AccommodationInternal.write$Self$pde_sdk_1_0_40_release(accommodationInternal, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
