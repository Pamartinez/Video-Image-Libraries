package hf;

import Ae.b;
import Hf.C0774x;
import Hf.c0;
import Pe.d;
import Qe.C0814d;
import Qe.C0819i;
import Te.u;
import ef.i;
import kotlin.jvm.internal.j;
import me.x;
import qf.C1236c;
import qf.C1240g;
import xf.C1353d;

/* renamed from: hf.m  reason: case insensitive filesystem */
public final class C1091m implements b {
    public static final C1091m e = new C1091m(0);
    public static final C1091m f = new C1091m(1);
    public static final C1091m g = new C1091m(2);

    /* renamed from: h  reason: collision with root package name */
    public static final C1091m f4593h = new C1091m(3);
    public final /* synthetic */ int d;

    public /* synthetic */ C1091m(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        boolean z;
        switch (this.d) {
            case 0:
                C0814d dVar = (C0814d) obj;
                j.e(dVar, "it");
                u H5 = dVar.H();
                j.b(H5);
                return H5.getType();
            case 1:
                C0814d dVar2 = (C0814d) obj;
                j.e(dVar2, "it");
                C0774x returnType = dVar2.getReturnType();
                j.b(returnType);
                return returnType;
            case 2:
                c0 c0Var = (c0) obj;
                j.e(c0Var, "it");
                return Boolean.valueOf(c0Var instanceof i);
            case 3:
                C0819i g3 = ((c0) obj).s0().g();
                if (g3 == null) {
                    return Boolean.FALSE;
                }
                C1240g name = g3.getName();
                C1236c cVar = d.f;
                if (!j.a(name, cVar.f()) || !j.a(C1353d.c(g3), cVar)) {
                    z = false;
                } else {
                    z = true;
                }
                return Boolean.valueOf(z);
            default:
                C1092n nVar = (C1092n) obj;
                j.e(nVar, "$this$function");
                String concat = "java/util/".concat("Spliterator");
                C1082d dVar3 = C1089k.b;
                nVar.b(concat, dVar3, dVar3);
                return x.f4917a;
        }
    }
}
