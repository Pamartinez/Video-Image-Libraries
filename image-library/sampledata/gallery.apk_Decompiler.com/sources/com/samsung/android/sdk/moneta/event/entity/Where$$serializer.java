package com.samsung.android.sdk.moneta.event.entity;

import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import gg.a;
import gg.g;
import ig.f;
import java.util.List;
import jg.b;
import jg.c;
import jg.d;
import kg.A;
import kg.C1125f;
import kg.C1136q;
import kg.Q;
import kg.T;
import kg.a0;
import kg.e0;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"com/samsung/android/sdk/moneta/event/entity/Where.$serializer", "Lkg/A;", "Lcom/samsung/android/sdk/moneta/event/entity/Where;", "<init>", "()V", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Lcom/samsung/android/sdk/moneta/event/entity/Where;)V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Lcom/samsung/android/sdk/moneta/event/entity/Where;", "", "Lgg/a;", "childSerializers", "()[Lgg/a;", "Lig/f;", "descriptor", "Lig/f;", "getDescriptor", "()Lig/f;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class Where$$serializer implements A {
    public static final Where$$serializer INSTANCE;
    private static final f descriptor;

    static {
        Where$$serializer where$$serializer = new Where$$serializer();
        INSTANCE = where$$serializer;
        T t = new T("com.samsung.android.sdk.moneta.event.entity.Where", where$$serializer, 12);
        t.k("placeName", false);
        t.k("address", false);
        t.k("poi", false);
        t.k(BuddyContract.Address.COUNTRY, false);
        t.k(BuddyContract.Address.CITY, false);
        t.k("postalCode", false);
        t.k("sourcePackage", false);
        t.k("sourceUri", false);
        t.k("lng", true);
        t.k("lat", true);
        t.k("augmented", true);
        t.k("tags", true);
        descriptor = t;
    }

    private Where$$serializer() {
    }

    public final a[] childSerializers() {
        a aVar = Where.$childSerializers[11];
        e0 e0Var = e0.f4693a;
        C1136q qVar = C1136q.f4714a;
        return new a[]{e0Var, e0Var, e0Var, e0Var, e0Var, e0Var, e0Var, e0Var, qVar, qVar, C1125f.f4694a, aVar};
    }

    public final Where deserialize(c cVar) {
        c cVar2 = cVar;
        j.e(cVar2, "decoder");
        f fVar = descriptor;
        jg.a a7 = cVar2.a(fVar);
        a[] access$get$childSerializers$cp = Where.$childSerializers;
        List list = null;
        int i2 = 0;
        boolean z = false;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        double d = 0.0d;
        double d2 = 0.0d;
        boolean z3 = true;
        while (z3) {
            int d3 = a7.d(fVar);
            switch (d3) {
                case -1:
                    z3 = false;
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
                    d = a7.m(fVar, 8);
                    i2 |= 256;
                    break;
                case 9:
                    d2 = a7.m(fVar, 9);
                    i2 |= 512;
                    break;
                case 10:
                    z = a7.r(fVar, 10);
                    i2 |= 1024;
                    break;
                case 11:
                    list = (List) a7.n(fVar, 11, access$get$childSerializers$cp[11], list);
                    i2 |= 2048;
                    break;
                default:
                    throw new g(d3);
            }
        }
        a7.b(fVar);
        return new Where(i2, str, str2, str3, str4, str5, str6, str7, str8, d, d2, z, list, (a0) null);
    }

    public final f getDescriptor() {
        return descriptor;
    }

    public final void serialize(d dVar, Where where) {
        j.e(dVar, "encoder");
        j.e(where, "value");
        f fVar = descriptor;
        b a7 = dVar.a(fVar);
        Where.write$Self$pde_sdk_1_0_40_release(where, a7, fVar);
        a7.b(fVar);
    }

    public a[] typeParametersSerializers() {
        return Q.b;
    }
}
