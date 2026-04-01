package com.samsung.android.sdk.moneta.event.entity;

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

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/event/entity/What.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/event/entity/What;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/event/entity/What;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/event/entity/What;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class What$$serializer implements A {
    public static final What$$serializer INSTANCE;
    private static final f descriptor;

    static {
        What$$serializer what$$serializer = new What$$serializer();
        INSTANCE = what$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.event.entity.What", what$$serializer, 5);
        t.k("title", false);
        t.k("sourcePackage", false);
        t.k("sourceUri", false);
        t.k("extras", true);
        t.k("tags", true);
        descriptor = t;
    }

    private What$$serializer() {
    }

    public final a[] childSerializers() {
        a aVar = What.$childSerializers[4];
        e0 e0Var = e0.f4693a;
        return new a[]{e0Var, e0Var, e0Var, e0Var, aVar};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.samsung.android.sdk.moneta.event.entity.What deserialize(jg.c r13) {
        /*
            r12 = this;
            java.lang.String r12 = "decoder"
            kotlin.jvm.internal.j.e(r13, r12)
            ig.f r12 = descriptor
            jg.a r13 = r13.a(r12)
            gg.a[] r0 = com.samsung.android.sdk.moneta.event.entity.What.$childSerializers
            r1 = 1
            r2 = 0
            r3 = 0
            r5 = r2
            r6 = r3
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r3 = r1
        L_0x0019:
            if (r3 == 0) goto L_0x005f
            int r4 = r13.d(r12)
            r11 = -1
            if (r4 == r11) goto L_0x005d
            if (r4 == 0) goto L_0x0056
            if (r4 == r1) goto L_0x004f
            r11 = 2
            if (r4 == r11) goto L_0x0048
            r11 = 3
            if (r4 == r11) goto L_0x0041
            r11 = 4
            if (r4 != r11) goto L_0x003b
            r4 = r0[r11]
            java.lang.Object r4 = r13.n(r12, r11, r4, r10)
            r10 = r4
            java.util.List r10 = (java.util.List) r10
            r5 = r5 | 16
            goto L_0x0019
        L_0x003b:
            gg.g r12 = new gg.g
            r12.<init>(r4)
            throw r12
        L_0x0041:
            java.lang.String r9 = r13.l(r12, r11)
            r5 = r5 | 8
            goto L_0x0019
        L_0x0048:
            java.lang.String r8 = r13.l(r12, r11)
            r5 = r5 | 4
            goto L_0x0019
        L_0x004f:
            java.lang.String r7 = r13.l(r12, r1)
            r5 = r5 | 2
            goto L_0x0019
        L_0x0056:
            java.lang.String r6 = r13.l(r12, r2)
            r5 = r5 | 1
            goto L_0x0019
        L_0x005d:
            r3 = r2
            goto L_0x0019
        L_0x005f:
            r13.b(r12)
            com.samsung.android.sdk.moneta.event.entity.What r4 = new com.samsung.android.sdk.moneta.event.entity.What
            r11 = 0
            r4.<init>((int) r5, (java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8, (java.lang.String) r9, (java.util.List) r10, (kg.a0) r11)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.moneta.event.entity.What$$serializer.deserialize(jg.c):com.samsung.android.sdk.moneta.event.entity.What");
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, What what) {
        j.e(dVar, "encoder");
        j.e(what, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        What.write$Self$pde_sdk_1_0_40_release(what, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
