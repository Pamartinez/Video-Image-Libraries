package com.samsung.android.sdk.moneta.travel.internal.model;

import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import gg.a;
import ig.f;
import jg.b;
import jg.d;
import kg.A;
import kg.C1136q;
import kg.Q;
import kg.T;
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/travel/internal/model/PlaceInternal;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class PlaceInternal$$serializer implements A {
    public static final PlaceInternal$$serializer INSTANCE;
    private static final f descriptor;

    static {
        PlaceInternal$$serializer placeInternal$$serializer = new PlaceInternal$$serializer();
        INSTANCE = placeInternal$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal", placeInternal$$serializer, 8);
        t.k(BuddyContract.Address.CITY, false);
        t.k(BuddyContract.Address.COUNTRY, false);
        t.k("name", false);
        t.k("address", false);
        t.k("code", false);
        t.k("types", false);
        t.k("lat", false);
        t.k("lng", false);
        descriptor = t;
    }

    private PlaceInternal$$serializer() {
    }

    public final a[] childSerializers() {
        a[] access$get$childSerializers$cp = PlaceInternal.$childSerializers;
        e0 e0Var = e0.f4693a;
        a t = D1.f.t(e0Var);
        a t3 = D1.f.t(e0Var);
        a t5 = D1.f.t(e0Var);
        a t6 = D1.f.t(e0Var);
        a t7 = D1.f.t(access$get$childSerializers$cp[5]);
        C1136q qVar = C1136q.f4714a;
        return new a[]{t, t3, e0Var, t5, t6, t7, D1.f.t(qVar), D1.f.t(qVar)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: java.lang.Double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: java.lang.Double} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal deserialize(jg.c r18) {
        /*
            r17 = this;
            r0 = r18
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.j.e(r0, r1)
            ig.f r1 = descriptor
            jg.a r0 = r0.a(r1)
            gg.a[] r2 = com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal.$childSerializers
            r3 = 1
            r5 = 0
            r8 = r5
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r7 = 0
            r5 = r3
        L_0x001d:
            if (r5 == 0) goto L_0x0091
            int r6 = r0.d(r1)
            switch(r6) {
                case -1: goto L_0x008e;
                case 0: goto L_0x0081;
                case 1: goto L_0x0075;
                case 2: goto L_0x006d;
                case 3: goto L_0x0060;
                case 4: goto L_0x0053;
                case 5: goto L_0x0046;
                case 6: goto L_0x0039;
                case 7: goto L_0x002c;
                default: goto L_0x0026;
            }
        L_0x0026:
            gg.g r0 = new gg.g
            r0.<init>(r6)
            throw r0
        L_0x002c:
            kg.q r6 = kg.C1136q.f4714a
            r4 = 7
            java.lang.Object r4 = r0.q(r1, r4, r6, r15)
            r15 = r4
            java.lang.Double r15 = (java.lang.Double) r15
            r7 = r7 | 128(0x80, float:1.794E-43)
            goto L_0x001d
        L_0x0039:
            kg.q r4 = kg.C1136q.f4714a
            r6 = 6
            java.lang.Object r4 = r0.q(r1, r6, r4, r14)
            r14 = r4
            java.lang.Double r14 = (java.lang.Double) r14
            r7 = r7 | 64
            goto L_0x001d
        L_0x0046:
            r4 = 5
            r6 = r2[r4]
            java.lang.Object r4 = r0.q(r1, r4, r6, r13)
            r13 = r4
            java.util.List r13 = (java.util.List) r13
            r7 = r7 | 32
            goto L_0x001d
        L_0x0053:
            kg.e0 r4 = kg.e0.f4693a
            r6 = 4
            java.lang.Object r4 = r0.q(r1, r6, r4, r12)
            r12 = r4
            java.lang.String r12 = (java.lang.String) r12
            r7 = r7 | 16
            goto L_0x001d
        L_0x0060:
            kg.e0 r4 = kg.e0.f4693a
            r6 = 3
            java.lang.Object r4 = r0.q(r1, r6, r4, r11)
            r11 = r4
            java.lang.String r11 = (java.lang.String) r11
            r7 = r7 | 8
            goto L_0x001d
        L_0x006d:
            r4 = 2
            java.lang.String r10 = r0.l(r1, r4)
            r7 = r7 | 4
            goto L_0x001d
        L_0x0075:
            kg.e0 r4 = kg.e0.f4693a
            java.lang.Object r4 = r0.q(r1, r3, r4, r9)
            r9 = r4
            java.lang.String r9 = (java.lang.String) r9
            r7 = r7 | 2
            goto L_0x001d
        L_0x0081:
            kg.e0 r4 = kg.e0.f4693a
            r6 = 0
            java.lang.Object r4 = r0.q(r1, r6, r4, r8)
            r8 = r4
            java.lang.String r8 = (java.lang.String) r8
            r7 = r7 | 1
            goto L_0x001d
        L_0x008e:
            r6 = 0
            r5 = r6
            goto L_0x001d
        L_0x0091:
            r0.b(r1)
            com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal r6 = new com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal
            r16 = 0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal$$serializer.deserialize(jg.c):com.samsung.android.sdk.moneta.travel.internal.model.PlaceInternal");
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, PlaceInternal placeInternal) {
        j.e(dVar, "encoder");
        j.e(placeInternal, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        PlaceInternal.write$Self$pde_sdk_1_0_40_release(placeInternal, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
