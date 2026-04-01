package df;

import Ae.b;
import Af.p;
import He.t;
import Hf.C0774x;
import Qe.C0816f;
import Qe.C0819i;
import Te.K;
import We.w;
import java.lang.reflect.Modifier;
import kotlin.jvm.internal.j;

/* renamed from: df.l  reason: case insensitive filesystem */
public final class C0949l implements b {
    public static final C0949l e = new C0949l(0);
    public static final C0949l f = new C0949l(1);
    public static final C0949l g = new C0949l(2);

    /* renamed from: h  reason: collision with root package name */
    public static final C0949l f4251h = new C0949l(3);

    /* renamed from: i  reason: collision with root package name */
    public static final C0949l f4252i = new C0949l(4);
    public final /* synthetic */ int d;

    public /* synthetic */ C0949l(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                w wVar = (w) obj;
                int i2 = C0951n.v;
                j.e(wVar, "it");
                return Boolean.valueOf(!Modifier.isStatic(wVar.b().getModifiers()));
            case 1:
                K k = (K) obj;
                t[] tVarArr = C0932A.m;
                j.e(k, "$this$selectMostSpecificInEachOverridableGroup");
                return k;
            case 2:
                w wVar2 = (w) obj;
                int i7 = C0935D.f4236p;
                j.e(wVar2, "it");
                return Boolean.valueOf(Modifier.isStatic(wVar2.b().getModifiers()));
            case 3:
                p pVar = (p) obj;
                int i8 = C0935D.f4236p;
                j.e(pVar, "it");
                return pVar.g();
            default:
                int i10 = C0935D.f4236p;
                C0819i g3 = ((C0774x) obj).s0().g();
                if (g3 instanceof C0816f) {
                    return (C0816f) g3;
                }
                return null;
        }
    }
}
