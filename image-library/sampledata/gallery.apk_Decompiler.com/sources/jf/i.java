package Jf;

import Af.p;
import Hf.B;
import Hf.C0774x;
import Hf.I;
import Hf.M;
import Hf.c0;
import If.f;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends B {
    public final M e;
    public final g f;
    public final k g;

    /* renamed from: h  reason: collision with root package name */
    public final List f3477h;

    /* renamed from: i  reason: collision with root package name */
    public final boolean f3478i;

    /* renamed from: j  reason: collision with root package name */
    public final String[] f3479j;
    public final String k;

    public i(M m, g gVar, k kVar, List list, boolean z, String... strArr) {
        j.e(kVar, "kind");
        j.e(list, "arguments");
        j.e(strArr, "formatParams");
        this.e = m;
        this.f = gVar;
        this.g = kVar;
        this.f3477h = list;
        this.f3478i = z;
        this.f3479j = strArr;
        String a7 = kVar.a();
        Object[] copyOf = Arrays.copyOf(strArr, strArr.length);
        this.k = String.format(a7, Arrays.copyOf(copyOf, copyOf.length));
    }

    public final p A() {
        return this.f;
    }

    public final c0 A0(I i2) {
        j.e(i2, "newAttributes");
        return this;
    }

    public final B B0(boolean z) {
        String[] strArr = this.f3479j;
        return new i(this.e, this.f, this.g, this.f3477h, z, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public final B C0(I i2) {
        j.e(i2, "newAttributes");
        return this;
    }

    public final List e0() {
        return this.f3477h;
    }

    public final I p0() {
        I.e.getClass();
        return I.f;
    }

    public final M s0() {
        return this.e;
    }

    public final boolean u0() {
        return this.f3478i;
    }

    public final C0774x w0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        return this;
    }

    public final c0 z0(f fVar) {
        j.e(fVar, "kotlinTypeRefiner");
        return this;
    }
}
