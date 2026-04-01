package ge;

import A0.l;
import D0.e;
import He.F;
import ee.C0964A;
import ee.C0965B;
import ee.C0969b;
import ee.C0975h;
import ee.C0984q;
import ee.a0;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* renamed from: ge.a1  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1008a1 extends C0984q {
    public final e d;
    public E0 e;
    public C0975h f = C0975h.IDLE;

    public C1008a1(e eVar) {
        this.d = eVar;
    }

    public final a0 a(C0965B b) {
        Boolean bool;
        List<Object> list = b.f4268a;
        if (list.isEmpty()) {
            a0 a0Var = a0.f4289o;
            a0 g = a0Var.g("NameResolver returned no usable address. addrs=" + list + ", attrs=" + b.b);
            e(g);
            return g;
        }
        Object obj = b.f4269c;
        if ((obj instanceof Y0) && (bool = ((Y0) obj).f4488a) != null && bool.booleanValue()) {
            ArrayList arrayList = new ArrayList(list);
            Collections.shuffle(arrayList, new Random());
            list = arrayList;
        }
        E0 e02 = this.e;
        if (e02 == null) {
            C0969b bVar = C0969b.b;
            int[] iArr = new int[2];
            iArr[1] = 2;
            iArr[0] = 0;
            F.i("addrs is empty", !list.isEmpty());
            l lVar = new l(Collections.unmodifiableList(new ArrayList(list)), bVar, (Object[][]) Array.newInstance(Object.class, iArr));
            e eVar = this.d;
            F0 f02 = (F0) eVar.f;
            f02.m.d();
            F.t(true ^ f02.f4408H, "Channel is being terminated");
            E0 e03 = new E0(f02, lVar);
            e03.c(new W0(0, (Object) this, (Object) e03));
            this.e = e03;
            C0975h hVar = C0975h.CONNECTING;
            Z0 z02 = new Z0(new C0964A(e03, a0.e, false));
            this.f = hVar;
            eVar.e0(hVar, z02);
            e03.a();
        } else {
            e02.f4395j.m.d();
            e02.e = list;
            C1031i0 i0Var = e02.f;
            i0Var.getClass();
            for (Object n : list) {
                F.n(n, "newAddressGroups contains null entry");
            }
            F.i("newAddressGroups is empty", !list.isEmpty());
            i0Var.k.execute(new P1.e(22, i0Var, Collections.unmodifiableList(new ArrayList(list))));
        }
        return a0.e;
    }

    public final void e(a0 a0Var) {
        E0 e02 = this.e;
        if (e02 != null) {
            e02.b();
            this.e = null;
        }
        C0975h hVar = C0975h.TRANSIENT_FAILURE;
        Z0 z02 = new Z0(C0964A.a(a0Var));
        this.f = hVar;
        this.d.e0(hVar, z02);
    }

    public final void j() {
        E0 e02 = this.e;
        if (e02 != null) {
            e02.b();
        }
    }
}
