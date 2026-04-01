package com.samsung.android.sdk.moneta.event.entity;

import gg.a;
import gg.g;
import ig.f;
import jg.b;
import jg.c;
import jg.d;
import kg.A;
import kg.C1125f;
import kg.L;
import kg.Q;
import kg.T;
import kg.a0;
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/event/entity/When.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/event/entity/When;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/event/entity/When;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/event/entity/When;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class When$$serializer implements A {
    public static final When$$serializer INSTANCE;
    private static final f descriptor;

    static {
        When$$serializer when$$serializer = new When$$serializer();
        INSTANCE = when$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.event.entity.When", when$$serializer, 6);
        t.k("startTime", false);
        t.k("endTime", false);
        t.k("sourcePackage", false);
        t.k("sourceUri", false);
        t.k("timezone", true);
        t.k("isLunar", true);
        descriptor = t;
    }

    private When$$serializer() {
    }

    public final a[] childSerializers() {
        L l = L.f4673a;
        e0 e0Var = e0.f4693a;
        return new a[]{l, l, e0Var, e0Var, e0Var, C1125f.f4694a};
    }

    public final When deserialize(c cVar) {
        c cVar2 = cVar;
        j.e(cVar2, "decoder");
        f fVar = descriptor;
        jg.a a7 = cVar2.a(fVar);
        int i2 = 0;
        boolean z = false;
        long j2 = 0;
        long j3 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        boolean z3 = true;
        while (z3) {
            int d = a7.d(fVar);
            switch (d) {
                case -1:
                    z3 = false;
                    break;
                case 0:
                    j2 = a7.t(fVar, 0);
                    i2 |= 1;
                    break;
                case 1:
                    j3 = a7.t(fVar, 1);
                    i2 |= 2;
                    break;
                case 2:
                    str = a7.l(fVar, 2);
                    i2 |= 4;
                    break;
                case 3:
                    str2 = a7.l(fVar, 3);
                    i2 |= 8;
                    break;
                case 4:
                    str3 = a7.l(fVar, 4);
                    i2 |= 16;
                    break;
                case 5:
                    z = a7.r(fVar, 5);
                    i2 |= 32;
                    break;
                default:
                    throw new g(d);
            }
        }
        a7.b(fVar);
        return new When(i2, j2, j3, str, str2, str3, z, (a0) null);
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, When when) {
        j.e(dVar, "encoder");
        j.e(when, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        When.write$Self$pde_sdk_1_0_40_release(when, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
