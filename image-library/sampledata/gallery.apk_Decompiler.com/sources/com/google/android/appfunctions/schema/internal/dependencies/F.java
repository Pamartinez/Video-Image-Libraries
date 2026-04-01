package com.google.android.appfunctions.schema.internal.dependencies;

import A.a;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class F implements M {

    /* renamed from: a  reason: collision with root package name */
    public final A f1203a;
    public final P b;

    /* renamed from: c  reason: collision with root package name */
    public final C0094d f1204c;

    public F(P p6, C0094d dVar, A a7) {
        this.b = p6;
        this.f1204c = dVar;
        this.f1203a = a7;
    }

    public final void a(Object obj, C0114y yVar) {
        this.f1204c.getClass();
        a.t(obj);
        throw null;
    }

    public final int b(C0102l lVar) {
        this.b.getClass();
        O o2 = lVar.unknownFields;
        int i2 = o2.d;
        if (i2 != -1) {
            return i2;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < o2.f1213a; i8++) {
            int l0 = h0.l0(8);
            int l02 = h0.l0(o2.b[i8] >>> 3) + h0.l0(16);
            int l03 = h0.l0(24);
            int r = ((f0) o2.f1214c[i8]).r();
            i7 += l0 + l0 + l02 + C0086a.a(r, r, l03);
        }
        o2.d = i7;
        return i7;
    }

    public final int c(C0102l lVar) {
        this.b.getClass();
        return lVar.unknownFields.hashCode();
    }

    public final void d(Object obj, Object obj2) {
        N.d(this.b, obj, obj2);
    }

    public final boolean e(Object obj) {
        this.f1204c.getClass();
        a.t(obj);
        throw null;
    }

    public final void f(Object obj) {
        this.b.getClass();
        O o2 = ((C0102l) obj).unknownFields;
        if (o2.e) {
            o2.e = false;
        }
        obj.getClass();
        throw new ClassCastException();
    }

    public final boolean g(C0102l lVar, C0102l lVar2) {
        this.b.getClass();
        if (!lVar.unknownFields.equals(lVar2.unknownFields)) {
            return false;
        }
        return true;
    }

    public final C0102l h() {
        A a7 = this.f1203a;
        if (a7 instanceof C0102l) {
            return (C0102l) ((C0102l) a7).b(C0101k.zzd);
        }
        return ((C0099i) ((C0102l) a7).b(C0101k.zze)).b();
    }
}
