package com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1;

import gg.a;
import ig.f;
import jg.b;
import jg.d;
import kg.A;
import kg.Q;
import kg.T;
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyPetWrapper.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyPetWrapper;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyPetWrapper;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyPetWrapper;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class MyPetWrapper$$serializer implements A {
    public static final MyPetWrapper$$serializer INSTANCE;
    private static final f descriptor;

    static {
        MyPetWrapper$$serializer myPetWrapper$$serializer = new MyPetWrapper$$serializer();
        INSTANCE = myPetWrapper$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.MyPetWrapper", myPetWrapper$$serializer, 4);
        t.k("name", true);
        t.k("petType", false);
        t.k("source", true);
        t.k("groupIds", true);
        descriptor = t;
    }

    private MyPetWrapper$$serializer() {
    }

    public final a[] childSerializers() {
        a[] access$get$childSerializers$cp = MyPetWrapper.$childSerializers;
        a aVar = access$get$childSerializers$cp[1];
        a aVar2 = access$get$childSerializers$cp[3];
        e0 e0Var = e0.f4693a;
        return new a[]{e0Var, aVar, e0Var, aVar2};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: com.samsung.android.sdk.moneta.basicdomain.entity.PetType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.MyPetWrapper deserialize(jg.c r12) {
        /*
            r11 = this;
            java.lang.String r11 = "decoder"
            kotlin.jvm.internal.j.e(r12, r11)
            ig.f r11 = descriptor
            jg.a r12 = r12.a(r11)
            gg.a[] r0 = com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.MyPetWrapper.$childSerializers
            r1 = 1
            r2 = 0
            r3 = 0
            r5 = r2
            r6 = r3
            r7 = r6
            r8 = r7
            r9 = r8
            r3 = r1
        L_0x0018:
            if (r3 == 0) goto L_0x0059
            int r4 = r12.d(r11)
            r10 = -1
            if (r4 == r10) goto L_0x0057
            if (r4 == 0) goto L_0x0050
            if (r4 == r1) goto L_0x0044
            r10 = 2
            if (r4 == r10) goto L_0x003d
            r10 = 3
            if (r4 != r10) goto L_0x0037
            r4 = r0[r10]
            java.lang.Object r4 = r12.n(r11, r10, r4, r9)
            r9 = r4
            java.util.List r9 = (java.util.List) r9
            r5 = r5 | 8
            goto L_0x0018
        L_0x0037:
            gg.g r11 = new gg.g
            r11.<init>(r4)
            throw r11
        L_0x003d:
            java.lang.String r8 = r12.l(r11, r10)
            r5 = r5 | 4
            goto L_0x0018
        L_0x0044:
            r4 = r0[r1]
            java.lang.Object r4 = r12.n(r11, r1, r4, r7)
            r7 = r4
            com.samsung.android.sdk.moneta.basicdomain.entity.PetType r7 = (com.samsung.android.sdk.moneta.basicdomain.entity.PetType) r7
            r5 = r5 | 2
            goto L_0x0018
        L_0x0050:
            java.lang.String r6 = r12.l(r11, r2)
            r5 = r5 | 1
            goto L_0x0018
        L_0x0057:
            r3 = r2
            goto L_0x0018
        L_0x0059:
            r12.b(r11)
            com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.MyPetWrapper r4 = new com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.MyPetWrapper
            r10 = 0
            r4.<init>((int) r5, (java.lang.String) r6, (com.samsung.android.sdk.moneta.basicdomain.entity.PetType) r7, (java.lang.String) r8, (java.util.List) r9, (kg.a0) r10)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.MyPetWrapper$$serializer.deserialize(jg.c):com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.MyPetWrapper");
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, MyPetWrapper myPetWrapper) {
        j.e(dVar, "encoder");
        j.e(myPetWrapper, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        MyPetWrapper.write$Self$pde_sdk_1_0_40_release(myPetWrapper, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
