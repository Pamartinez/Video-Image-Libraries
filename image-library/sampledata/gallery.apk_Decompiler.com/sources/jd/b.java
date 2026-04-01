package Jd;

import Df.C0736b;
import G0.c;
import G0.e;
import Hf.B;
import Hf.C0768q;
import Hf.C0774x;
import Hf.a0;
import If.g;
import Jf.i;
import Kf.d;
import Qe.C0816f;
import Qe.C0819i;
import Qe.V;
import Re.a;
import Ze.C0894a;
import Ze.C0895b;
import Ze.u;
import a.C0068a;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import be.C0915c;
import cf.C0922a;
import com.google.android.material.chip.SeslChipGroup;
import h2.h;
import hf.C1079a;
import hf.C1085g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import kotlin.jvm.internal.j;
import qf.C1238e;
import tf.C1301e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3473a;
    public boolean b;

    /* renamed from: c  reason: collision with root package name */
    public Object f3474c;
    public Object d;
    public Object e;

    public b(a aVar, boolean z, B0.a aVar2, C0894a aVar3, boolean z3) {
        j.e(aVar2, "containerContext");
        j.e(aVar3, "containerApplicabilityType");
        this.f3474c = aVar;
        this.f3473a = z;
        this.d = aVar2;
        this.e = aVar3;
        this.b = z3;
    }

    public static void c(Object obj, ArrayList arrayList, C0736b bVar) {
        arrayList.add(obj);
        Iterable<Object> iterable = (Iterable) bVar.invoke(obj);
        if (iterable != null) {
            for (Object c5 : iterable) {
                c(c5, arrayList, bVar);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00d6 A[EDGE_INSN: B:62:0x00d6->B:44:0x00d6 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static hf.C1086h d(Qe.V r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.j.e(r5, r0)
            boolean r1 = r5 instanceof df.C0937F
            if (r1 != 0) goto L_0x000b
            goto L_0x00e3
        L_0x000b:
            java.util.List r5 = r5.getUpperBounds()
            java.lang.String r1 = "getUpperBounds(...)"
            kotlin.jvm.internal.j.d(r5, r1)
            r1 = r5
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            boolean r2 = r1 instanceof java.util.Collection
            if (r2 == 0) goto L_0x0026
            r3 = r1
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0026
            goto L_0x00e3
        L_0x0026:
            java.util.Iterator r3 = r1.iterator()
        L_0x002a:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x00e3
            java.lang.Object r4 = r3.next()
            Kf.d r4 = (Kf.d) r4
            boolean r4 = If.g.B(r4)
            if (r4 != 0) goto L_0x002a
            if (r2 == 0) goto L_0x0048
            r3 = r1
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0048
            goto L_0x0060
        L_0x0048:
            java.util.Iterator r3 = r1.iterator()
        L_0x004c:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0060
            java.lang.Object r4 = r3.next()
            Kf.d r4 = (Kf.d) r4
            hf.g r4 = g(r4)
            if (r4 == 0) goto L_0x004c
            r2 = r5
            goto L_0x00ac
        L_0x0060:
            if (r2 == 0) goto L_0x006d
            r2 = r1
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x006d
            goto L_0x00e3
        L_0x006d:
            java.util.Iterator r2 = r1.iterator()
        L_0x0071:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00e3
            java.lang.Object r3 = r2.next()
            Kf.d r3 = (Kf.d) r3
            kotlin.jvm.internal.j.e(r3, r0)
            Hf.x r3 = (Hf.C0774x) r3
            Hf.x r3 = Hf.C0754c.g(r3)
            if (r3 == 0) goto L_0x0071
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x0091:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00ac
            java.lang.Object r3 = r1.next()
            Kf.d r3 = (Kf.d) r3
            kotlin.jvm.internal.j.e(r3, r0)
            Hf.x r3 = (Hf.C0774x) r3
            Hf.x r3 = Hf.C0754c.g(r3)
            if (r3 == 0) goto L_0x0091
            r2.add(r3)
            goto L_0x0091
        L_0x00ac:
            r0 = r2
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r1 = r0 instanceof java.util.Collection
            if (r1 == 0) goto L_0x00bd
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x00bd
            goto L_0x00d6
        L_0x00bd:
            java.util.Iterator r0 = r0.iterator()
        L_0x00c1:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00d6
            java.lang.Object r1 = r0.next()
            Kf.d r1 = (Kf.d) r1
            boolean r1 = If.g.H(r1)
            if (r1 != 0) goto L_0x00c1
            hf.g r0 = hf.C1085g.NOT_NULL
            goto L_0x00d8
        L_0x00d6:
            hf.g r0 = hf.C1085g.NULLABLE
        L_0x00d8:
            hf.h r1 = new hf.h
            if (r2 == r5) goto L_0x00de
            r5 = 1
            goto L_0x00df
        L_0x00de:
            r5 = 0
        L_0x00df:
            r1.<init>(r0, r5)
            return r1
        L_0x00e3:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: Jd.b.d(Qe.V):hf.h");
    }

    public static C1238e f(B b5) {
        C0816f fVar;
        i iVar = a0.f3439a;
        C0819i g = b5.s0().g();
        if (g instanceof C0816f) {
            fVar = (C0816f) g;
        } else {
            fVar = null;
        }
        if (fVar != null) {
            return C1301e.g(fVar);
        }
        return null;
    }

    public static C1085g g(d dVar) {
        B b5;
        B b8;
        j.e(dVar, "<this>");
        C0768q g = g.g(dVar);
        if (g == null || (b5 = g.N(g)) == null) {
            b5 = g.h(dVar);
            j.b(b5);
        }
        if (g.F(b5)) {
            return C1085g.NULLABLE;
        }
        C0768q g3 = g.g(dVar);
        if (g3 == null || (b8 = g.Y(g3)) == null) {
            b8 = g.h(dVar);
            j.b(b8);
        }
        if (!g.F(b8)) {
            return C1085g.NOT_NULL;
        }
        return null;
    }

    public void a() {
        if (!this.b && !this.f3473a) {
            try {
                Intent intent = new Intent();
                intent.setClassName("com.sec.android.diagmonagent", "com.sec.android.diagmonagent.sa.receiver.SALogReceiverService");
                this.b = ((Context) this.f3474c).bindService(intent, (a) this.e, 1);
                C0068a.c("DMABinder", "bind " + this.b);
            } catch (Exception e7) {
                C0068a.K("failed to bind" + e7.getMessage());
            }
        }
    }

    public boolean b(h hVar) {
        int id = hVar.getId();
        HashSet hashSet = (HashSet) this.d;
        if (hashSet.contains(Integer.valueOf(id))) {
            return false;
        }
        h hVar2 = (h) ((HashMap) this.f3474c).get(Integer.valueOf(h()));
        if (hVar2 != null) {
            l(hVar2, false);
        }
        boolean add = hashSet.add(Integer.valueOf(id));
        if (!hVar.isChecked()) {
            hVar.setChecked(true);
        }
        return add;
    }

    public ArrayList e(c2.j jVar) {
        HashSet hashSet = new HashSet((HashSet) this.d);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jVar.getChildCount(); i2++) {
            View childAt = jVar.getChildAt(i2);
            if ((childAt instanceof h) && hashSet.contains(Integer.valueOf(childAt.getId()))) {
                arrayList.add(Integer.valueOf(childAt.getId()));
            }
        }
        return arrayList;
    }

    public int h() {
        HashSet hashSet = (HashSet) this.d;
        if (!this.f3473a || hashSet.isEmpty()) {
            return -1;
        }
        return ((Integer) hashSet.iterator().next()).intValue();
    }

    public void i() {
        c cVar = (c) this.e;
        if (cVar != null) {
            new HashSet((HashSet) this.d);
            SeslChipGroup seslChipGroup = (SeslChipGroup) cVar.e;
            c2.h hVar = seslChipGroup.f1094i;
            if (hVar != null) {
                seslChipGroup.f1095j.e(seslChipGroup);
                c2.j jVar = (c2.j) ((e) hVar).d;
                if (jVar.f1095j.f3473a) {
                    jVar.getCheckedChipId();
                    throw null;
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [me.f, java.lang.Object] */
    public ArrayList j(d dVar) {
        B0.a aVar = (B0.a) this.d;
        C0895b bVar = ((C0922a) aVar.d).q;
        j.e(dVar, "<this>");
        C1079a aVar2 = new C1079a(dVar, bVar.b((u) aVar.g.getValue(), ((C0774x) dVar).getAnnotations()), (V) null);
        C0736b bVar2 = new C0736b(22, this);
        ArrayList arrayList = new ArrayList(1);
        c(aVar2, arrayList, bVar2);
        return arrayList;
    }

    public void k() {
        if (((C0915c) this.d) != null && this.b) {
            try {
                ((Context) this.f3474c).unbindService((a) this.e);
                this.b = false;
                C0068a.c("DMABinder", "unbind");
            } catch (Exception e7) {
                C0068a.K("failed to unbind" + e7.getMessage());
            }
        }
    }

    public boolean l(h hVar, boolean z) {
        int id = hVar.getId();
        HashSet hashSet = (HashSet) this.d;
        if (!hashSet.contains(Integer.valueOf(id))) {
            return false;
        }
        if (!z || hashSet.size() != 1 || !hashSet.contains(Integer.valueOf(id))) {
            boolean remove = hashSet.remove(Integer.valueOf(id));
            if (hVar.isChecked()) {
                hVar.setChecked(false);
            }
            return remove;
        }
        hVar.setChecked(true);
        return false;
    }
}
