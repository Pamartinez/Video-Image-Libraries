package com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1;

import gg.a;
import ig.f;
import jg.b;
import jg.d;
import kg.A;
import kg.C1136q;
import kg.H;
import kg.L;
import kg.Q;
import kg.T;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonPreference.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonPreference;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonPreference;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonPreference;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class PersonPreference$$serializer implements A {
    public static final PersonPreference$$serializer INSTANCE;
    private static final f descriptor;

    static {
        PersonPreference$$serializer personPreference$$serializer = new PersonPreference$$serializer();
        INSTANCE = personPreference$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference", personPreference$$serializer, 13);
        t.k("latestTimestamp", true);
        t.k("startTimestamp", true);
        t.k("endTimestamp", true);
        t.k("eventTimestamp", true);
        t.k("lastContactTimestamp", true);
        t.k("daysOfContact", true);
        t.k("numOfContact", true);
        t.k("periodOfContact", true);
        t.k("requestedNumOfDays", true);
        t.k("preferenceLevel", true);
        t.k("numOfIncoming", true);
        t.k("numOfOutgoing", true);
        t.k("outgoingRate", true);
        descriptor = t;
    }

    private PersonPreference$$serializer() {
    }

    public final a[] childSerializers() {
        a[] access$get$childSerializers$cp = PersonPreference.$childSerializers;
        L l = L.f4673a;
        a t = D1.f.t(l);
        a t3 = D1.f.t(l);
        a t5 = D1.f.t(l);
        a t6 = D1.f.t(l);
        a t7 = D1.f.t(l);
        H h5 = H.f4669a;
        a t10 = D1.f.t(h5);
        a t11 = D1.f.t(h5);
        C1136q qVar = C1136q.f4714a;
        return new a[]{t, t3, t5, t6, t7, t10, t11, D1.f.t(qVar), D1.f.t(h5), D1.f.t(access$get$childSerializers$cp[9]), D1.f.t(h5), D1.f.t(h5), D1.f.t(qVar)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v24, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: java.lang.Double} */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0107, code lost:
        r3 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0042, code lost:
        r8 = r18;
        r9 = r19;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference deserialize(jg.c r23) {
        /*
            r22 = this;
            r0 = r23
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.j.e(r0, r1)
            ig.f r1 = descriptor
            jg.a r0 = r0.a(r1)
            gg.a[] r2 = com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference.$childSerializers
            r5 = 0
            r17 = r2
            r3 = r5
            r4 = r3
            r6 = r4
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r2 = 0
            r16 = 1
        L_0x0023:
            if (r16 == 0) goto L_0x0118
            r18 = r8
            int r8 = r0.d(r1)
            switch(r8) {
                case -1: goto L_0x010b;
                case 0: goto L_0x00f4;
                case 1: goto L_0x00df;
                case 2: goto L_0x00cf;
                case 3: goto L_0x00bf;
                case 4: goto L_0x00b0;
                case 5: goto L_0x00a1;
                case 6: goto L_0x0092;
                case 7: goto L_0x0083;
                case 8: goto L_0x0074;
                case 9: goto L_0x0065;
                case 10: goto L_0x0056;
                case 11: goto L_0x0047;
                case 12: goto L_0x0034;
                default: goto L_0x002e;
            }
        L_0x002e:
            gg.g r0 = new gg.g
            r0.<init>(r8)
            throw r0
        L_0x0034:
            kg.q r8 = kg.C1136q.f4714a
            r19 = r9
            r9 = 12
            java.lang.Object r3 = r0.q(r1, r9, r8, r3)
            java.lang.Double r3 = (java.lang.Double) r3
            r2 = r2 | 4096(0x1000, float:5.74E-42)
        L_0x0042:
            r8 = r18
            r9 = r19
            goto L_0x0023
        L_0x0047:
            r19 = r9
            kg.H r8 = kg.H.f4669a
            r9 = 11
            java.lang.Object r4 = r0.q(r1, r9, r8, r4)
            java.lang.Integer r4 = (java.lang.Integer) r4
            r2 = r2 | 2048(0x800, float:2.87E-42)
            goto L_0x0042
        L_0x0056:
            r19 = r9
            kg.H r8 = kg.H.f4669a
            r9 = 10
            java.lang.Object r7 = r0.q(r1, r9, r8, r7)
            java.lang.Integer r7 = (java.lang.Integer) r7
            r2 = r2 | 1024(0x400, float:1.435E-42)
            goto L_0x0042
        L_0x0065:
            r19 = r9
            r8 = 9
            r9 = r17[r8]
            java.lang.Object r6 = r0.q(r1, r8, r9, r6)
            com.samsung.android.sdk.moneta.lifestyle.social.entity.PreferenceLevel r6 = (com.samsung.android.sdk.moneta.lifestyle.social.entity.PreferenceLevel) r6
            r2 = r2 | 512(0x200, float:7.175E-43)
            goto L_0x0042
        L_0x0074:
            r19 = r9
            kg.H r8 = kg.H.f4669a
            r9 = 8
            java.lang.Object r5 = r0.q(r1, r9, r8, r5)
            java.lang.Integer r5 = (java.lang.Integer) r5
            r2 = r2 | 256(0x100, float:3.59E-43)
            goto L_0x0042
        L_0x0083:
            r19 = r9
            kg.q r8 = kg.C1136q.f4714a
            r9 = 7
            java.lang.Object r8 = r0.q(r1, r9, r8, r15)
            r15 = r8
            java.lang.Double r15 = (java.lang.Double) r15
            r2 = r2 | 128(0x80, float:1.794E-43)
            goto L_0x0042
        L_0x0092:
            r19 = r9
            kg.H r8 = kg.H.f4669a
            r9 = 6
            java.lang.Object r8 = r0.q(r1, r9, r8, r14)
            r14 = r8
            java.lang.Integer r14 = (java.lang.Integer) r14
            r2 = r2 | 64
            goto L_0x0042
        L_0x00a1:
            r19 = r9
            kg.H r8 = kg.H.f4669a
            r9 = 5
            java.lang.Object r8 = r0.q(r1, r9, r8, r13)
            r13 = r8
            java.lang.Integer r13 = (java.lang.Integer) r13
            r2 = r2 | 32
            goto L_0x0042
        L_0x00b0:
            r19 = r9
            kg.L r8 = kg.L.f4673a
            r9 = 4
            java.lang.Object r8 = r0.q(r1, r9, r8, r12)
            r12 = r8
            java.lang.Long r12 = (java.lang.Long) r12
            r2 = r2 | 16
            goto L_0x0042
        L_0x00bf:
            r19 = r9
            kg.L r8 = kg.L.f4673a
            r9 = 3
            java.lang.Object r8 = r0.q(r1, r9, r8, r11)
            r11 = r8
            java.lang.Long r11 = (java.lang.Long) r11
            r2 = r2 | 8
            goto L_0x0042
        L_0x00cf:
            r19 = r9
            kg.L r8 = kg.L.f4673a
            r9 = 2
            java.lang.Object r8 = r0.q(r1, r9, r8, r10)
            r10 = r8
            java.lang.Long r10 = (java.lang.Long) r10
            r2 = r2 | 4
            goto L_0x0042
        L_0x00df:
            r19 = r9
            kg.L r8 = kg.L.f4673a
            r19 = r2
            r2 = 1
            java.lang.Object r8 = r0.q(r1, r2, r8, r9)
            r9 = r8
            java.lang.Long r9 = (java.lang.Long) r9
            r8 = r19 | 2
            r2 = r8
            r8 = r18
            goto L_0x0023
        L_0x00f4:
            r19 = r2
            r2 = 1
            kg.L r8 = kg.L.f4673a
            r20 = r3
            r2 = r18
            r3 = 0
            java.lang.Object r2 = r0.q(r1, r3, r8, r2)
            r8 = r2
            java.lang.Long r8 = (java.lang.Long) r8
            r2 = r19 | 1
        L_0x0107:
            r3 = r20
            goto L_0x0023
        L_0x010b:
            r19 = r2
            r20 = r3
            r2 = r18
            r3 = 0
            r8 = r2
            r16 = r3
            r2 = r19
            goto L_0x0107
        L_0x0118:
            r19 = r2
            r20 = r3
            r2 = r8
            r0.b(r1)
            r17 = r6
            com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference r6 = new com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference
            r21 = 0
            r16 = r5
            r18 = r7
            r7 = r19
            r19 = r4
            r6.<init>((int) r7, (java.lang.Long) r8, (java.lang.Long) r9, (java.lang.Long) r10, (java.lang.Long) r11, (java.lang.Long) r12, (java.lang.Integer) r13, (java.lang.Integer) r14, (java.lang.Double) r15, (java.lang.Integer) r16, (com.samsung.android.sdk.moneta.lifestyle.social.entity.PreferenceLevel) r17, (java.lang.Integer) r18, (java.lang.Integer) r19, (java.lang.Double) r20, (kg.a0) r21)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference$$serializer.deserialize(jg.c):com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1.PersonPreference");
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, PersonPreference personPreference) {
        j.e(dVar, "encoder");
        j.e(personPreference, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        PersonPreference.write$Self$pde_sdk_1_0_40_release(personPreference, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
