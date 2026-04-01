package jf;

import D1.f;
import Ff.l;
import Ff.m;
import Tf.n;
import Ve.b;
import We.C0892d;
import ee.P;
import kf.C1116b;
import kotlin.jvm.internal.j;
import lf.C;
import of.k;
import pf.g;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import rf.C1266p;
import yf.C1358b;

/* renamed from: jf.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1109i implements m {
    public final C1358b d;
    public final C1358b e;
    public final b f;

    public C1109i(b bVar, C c5, g gVar, l lVar) {
        j.e(bVar, "kotlinClass");
        j.e(c5, "packageProto");
        j.e(gVar, "nameResolver");
        j.e(lVar, "abiStability");
        C1358b bVar2 = new C1358b(C1358b.e(C0892d.a(bVar.f3829a)));
        P p6 = bVar.b;
        C1358b bVar3 = null;
        String str = ((C1116b) p6.f4277c) != C1116b.MULTIFILE_CLASS_PART ? null : (String) p6.f4278h;
        if (str != null && str.length() > 0) {
            bVar3 = C1358b.c(str);
        }
        this.d = bVar2;
        this.e = bVar3;
        this.f = bVar;
        C1266p pVar = k.m;
        j.d(pVar, "packageModuleName");
        Integer num = (Integer) f.q(c5, pVar);
        if (num != null) {
            gVar.getString(num.intValue());
        }
    }

    public final String a() {
        return "Class '" + b().a().b() + '\'';
    }

    public final C1235b b() {
        C1236c cVar;
        C1358b bVar = this.d;
        String str = bVar.f5168a;
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf == -1) {
            cVar = C1236c.f5035c;
            if (cVar == null) {
                C1358b.a(9);
                throw null;
            }
        } else {
            cVar = new C1236c(str.substring(0, lastIndexOf).replace('/', '.'));
        }
        String d2 = bVar.d();
        j.d(d2, "getInternalName(...)");
        return new C1235b(cVar, C1240g.e(n.O0(d2, '/')));
    }

    public final String toString() {
        return C1109i.class.getSimpleName() + ": " + this.d;
    }
}
