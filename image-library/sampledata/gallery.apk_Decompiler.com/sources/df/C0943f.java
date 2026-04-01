package df;

import B0.a;
import Gf.h;
import Gf.m;
import He.t;
import Hf.B;
import Hf.C0754c;
import Hf.C0774x;
import Hf.P;
import Hf.Y;
import Hf.d0;
import Jf.k;
import Jf.l;
import Qe.C0816f;
import Qe.C0819i;
import Qe.V;
import Re.d;
import Te.Q;
import Te.z;
import Ve.f;
import We.C0892d;
import We.C0893e;
import We.E;
import We.g;
import We.q;
import Ze.x;
import a.C0068a;
import bf.i;
import cf.C0922a;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import gf.C1070a;
import gf.C1073d;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import ne.C1194l;
import ne.C1196n;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import sf.C1283j;
import vf.C1327g;
import vf.C1328h;
import vf.C1329i;
import vf.C1338r;
import vf.C1341u;
import vf.C1346z;
import xf.C1353d;

/* renamed from: df.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0943f implements i {

    /* renamed from: h  reason: collision with root package name */
    public static final /* synthetic */ t[] f4244h;

    /* renamed from: a  reason: collision with root package name */
    public final a f4245a;
    public final C0893e b;

    /* renamed from: c  reason: collision with root package name */
    public final h f4246c;
    public final Gf.i d;
    public final f e;
    public final Gf.i f;
    public final boolean g;

    static {
        w wVar = v.f4727a;
        Class<C0943f> cls = C0943f.class;
        f4244h = new t[]{wVar.f(new p(wVar.b(cls), "fqName", "getFqName()Lorg/jetbrains/kotlin/name/FqName;")), wVar.f(new p(wVar.b(cls), "type", "getType()Lorg/jetbrains/kotlin/types/SimpleType;")), wVar.f(new p(wVar.b(cls), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r6v2, types: [Gf.h, Gf.i] */
    public C0943f(a aVar, C0893e eVar, boolean z) {
        j.e(aVar, "c");
        j.e(eVar, "javaAnnotation");
        this.f4245a = aVar;
        this.b = eVar;
        C0922a aVar2 = (C0922a) aVar.d;
        Gf.p pVar = aVar2.f4006a;
        C0942e eVar2 = new C0942e(this, 0);
        m mVar = (m) pVar;
        mVar.getClass();
        this.f4246c = new h(mVar, eVar2);
        C0942e eVar3 = new C0942e(this, 1);
        m mVar2 = (m) pVar;
        mVar2.getClass();
        this.d = new h(mVar2, eVar3);
        this.e = aVar2.f4010j.b(eVar);
        C0942e eVar4 = new C0942e(this, 2);
        m mVar3 = (m) pVar;
        mVar3.getClass();
        this.f = new h(mVar3, eVar4);
        this.g = z;
    }

    public final Map a() {
        return (Map) D1.f.y(this.f, f4244h[2]);
    }

    public final C1236c b() {
        t tVar = f4244h[0];
        h hVar = this.f4246c;
        j.e(hVar, "<this>");
        j.e(tVar, "p");
        return (C1236c) hVar.invoke();
    }

    public final C1327g c(C1070a aVar) {
        C1073d dVar;
        C0774x xVar;
        if (aVar instanceof We.v) {
            return C1328h.b((z) null, ((We.v) aVar).b);
        }
        if (aVar instanceof We.t) {
            Enum enumR = ((We.t) aVar).b;
            Class<?> cls = enumR.getClass();
            if (!cls.isEnum()) {
                cls = cls.getEnclosingClass();
            }
            j.b(cls);
            return new C1329i(C0892d.a(cls), C1240g.e(enumR.name()));
        }
        boolean z = aVar instanceof We.h;
        a aVar2 = this.f4245a;
        if (z) {
            We.h hVar = (We.h) aVar;
            C1240g gVar = hVar.f3888a;
            if (gVar == null) {
                gVar = x.b;
            }
            j.b(gVar);
            ArrayList a7 = hVar.a();
            if (!C0754c.k((B) D1.f.y(this.d, f4244h[1]))) {
                C0816f d2 = C1353d.d(this);
                j.b(d2);
                Q x9 = c.x(gVar, d2);
                if (x9 == null || (xVar = x9.getType()) == null) {
                    xVar = ((C0922a) aVar2.d).f4011o.f().h(d0.INVARIANT, l.c(k.UNKNOWN_ARRAY_ELEMENT_TYPE_OF_ANNOTATION_ARGUMENT, new String[0]));
                }
                ArrayList arrayList = new ArrayList(C1196n.w0(a7, 10));
                Iterator it = a7.iterator();
                while (it.hasNext()) {
                    C1327g c5 = c((C1070a) it.next());
                    if (c5 == null) {
                        c5 = new C1327g((Object) null);
                    }
                    arrayList.add(c5);
                }
                return new C1346z(arrayList, xVar);
            }
        } else if (aVar instanceof g) {
            return new C1327g(new C0943f(aVar2, new C0893e(((g) aVar).b), false));
        } else {
            if (aVar instanceof We.p) {
                Class cls2 = ((We.p) aVar).b;
                if (cls2.isPrimitive()) {
                    dVar = new We.z(cls2);
                } else if ((cls2 instanceof GenericArrayType) || cls2.isArray()) {
                    dVar = new We.i(cls2);
                } else if (cls2 instanceof WildcardType) {
                    dVar = new E((WildcardType) cls2);
                } else {
                    dVar = new q(cls2);
                }
                C0774x p6 = ((A0.l) aVar2.f34h).p(dVar, C0068a.Y(Y.COMMON, false, (C0937F) null, 7));
                if (!C0754c.k(p6)) {
                    C0774x xVar2 = p6;
                    int i2 = 0;
                    while (Ne.i.y(xVar2)) {
                        xVar2 = ((P) C1194l.b1(xVar2.e0())).b();
                        i2++;
                    }
                    C0819i g3 = xVar2.s0().g();
                    if (g3 instanceof C0816f) {
                        C1235b f5 = C1353d.f(g3);
                        if (f5 == null) {
                            return new C1327g(new C1338r(p6));
                        }
                        return new C1341u(f5, i2);
                    } else if (g3 instanceof V) {
                        C1236c g10 = Ne.p.f3565a.g();
                        C1236c e7 = g10.e();
                        C1240g f8 = g10.f();
                        j.d(f8, "shortName(...)");
                        return new C1341u(new C1235b(e7, f8), 0);
                    }
                }
            }
        }
        return null;
    }

    public final Qe.Q getSource() {
        return this.e;
    }

    public final C0774x getType() {
        return (B) D1.f.y(this.d, f4244h[1]);
    }

    public final String toString() {
        return C1283j.f5083c.x(this, (d) null);
    }
}
