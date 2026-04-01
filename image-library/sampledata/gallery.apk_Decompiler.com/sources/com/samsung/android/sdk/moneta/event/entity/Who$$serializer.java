package com.samsung.android.sdk.moneta.event.entity;

import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.PersonBundleWrapper;
import gg.a;
import gg.g;
import ig.f;
import java.util.List;
import jg.b;
import jg.c;
import jg.d;
import kg.A;
import kg.C1125f;
import kg.Q;
import kg.T;
import kg.a0;
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/event/entity/Who.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/event/entity/Who;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/event/entity/Who;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/event/entity/Who;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class Who$$serializer implements A {
    public static final Who$$serializer INSTANCE;
    private static final f descriptor;

    static {
        Who$$serializer who$$serializer = new Who$$serializer();
        INSTANCE = who$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.event.entity.Who", who$$serializer, 13);
        t.k("name", false);
        t.k("phoneNumber", false);
        t.k(PersonBundleWrapper.BUNDLE_KEY_CONTACT_ID, false);
        t.k("email", false);
        t.k("groupName", false);
        t.k("nickName", false);
        t.k("snsName", false);
        t.k("relation", false);
        t.k("isContributor", false);
        t.k("sourcePackage", false);
        t.k("sourceUri", false);
        t.k("augmented", true);
        t.k("tags", true);
        descriptor = t;
    }

    private Who$$serializer() {
    }

    public final a[] childSerializers() {
        a aVar = Who.$childSerializers[12];
        e0 e0Var = e0.f4693a;
        C1125f fVar = C1125f.f4694a;
        return new a[]{e0Var, e0Var, e0Var, e0Var, e0Var, e0Var, e0Var, e0Var, fVar, e0Var, e0Var, fVar, aVar};
    }

    public final Who deserialize(c cVar) {
        c cVar2 = cVar;
        j.e(cVar2, "decoder");
        f fVar = descriptor;
        jg.a a7 = cVar2.a(fVar);
        a[] access$get$childSerializers$cp = Who.$childSerializers;
        List list = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        boolean z = true;
        int i2 = 0;
        boolean z3 = false;
        boolean z7 = false;
        while (z) {
            int d = a7.d(fVar);
            switch (d) {
                case -1:
                    z = false;
                    break;
                case 0:
                    str = a7.l(fVar, 0);
                    i2 |= 1;
                    break;
                case 1:
                    str2 = a7.l(fVar, 1);
                    i2 |= 2;
                    break;
                case 2:
                    str3 = a7.l(fVar, 2);
                    i2 |= 4;
                    break;
                case 3:
                    str4 = a7.l(fVar, 3);
                    i2 |= 8;
                    break;
                case 4:
                    str5 = a7.l(fVar, 4);
                    i2 |= 16;
                    break;
                case 5:
                    str6 = a7.l(fVar, 5);
                    i2 |= 32;
                    break;
                case 6:
                    str7 = a7.l(fVar, 6);
                    i2 |= 64;
                    break;
                case 7:
                    str8 = a7.l(fVar, 7);
                    i2 |= 128;
                    break;
                case 8:
                    z3 = a7.r(fVar, 8);
                    i2 |= 256;
                    break;
                case 9:
                    str9 = a7.l(fVar, 9);
                    i2 |= 512;
                    break;
                case 10:
                    str10 = a7.l(fVar, 10);
                    i2 |= 1024;
                    break;
                case 11:
                    z7 = a7.r(fVar, 11);
                    i2 |= 2048;
                    break;
                case 12:
                    list = (List) a7.n(fVar, 12, access$get$childSerializers$cp[12], list);
                    i2 |= 4096;
                    break;
                default:
                    throw new g(d);
            }
        }
        a7.b(fVar);
        return new Who(i2, str, str2, str3, str4, str5, str6, str7, str8, z3, str9, str10, z7, list, (a0) null);
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, Who who) {
        j.e(dVar, "encoder");
        j.e(who, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        Who.write$Self$pde_sdk_1_0_40_release(who, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
