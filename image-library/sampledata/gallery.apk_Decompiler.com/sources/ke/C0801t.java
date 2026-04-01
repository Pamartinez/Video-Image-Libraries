package Ke;

import Ae.a;
import Af.p;
import Df.l;
import He.t;
import L1.d;
import Qe.C;
import Qe.C0816f;
import Qe.C0821k;
import Qe.C0833x;
import Tf.n;
import Ve.b;
import Ve.e;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import kf.C1116b;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;
import qf.C1235b;

/* renamed from: Ke.t  reason: case insensitive filesystem */
public final class C0801t implements a {
    public final /* synthetic */ int d;
    public final B e;

    public /* synthetic */ C0801t(B b, int i2) {
        this.d = i2;
        this.e = b;
    }

    /* JADX WARNING: type inference failed for: r7v2, types: [me.f, java.lang.Object] */
    public final Object invoke() {
        C0816f fVar;
        int i2;
        int i7 = this.d;
        C1116b bVar = null;
        B b = this.e;
        switch (i7) {
            case 0:
                return new C0805x(b);
            case 1:
                int i8 = B.g;
                C1235b y = b.y();
                Class cls = b.e;
                x0 x0Var = ((C0805x) b.f.getValue()).f3486a;
                t tVar = C.b[0];
                Object invoke = x0Var.invoke();
                j.d(invoke, "getValue(...)");
                e eVar = (e) invoke;
                l lVar = eVar.f3832a;
                C c5 = lVar.b;
                if (!y.f5034c || !cls.isAnnotationPresent(Metadata.class)) {
                    fVar = C0833x.d(c5, y);
                } else {
                    fVar = lVar.b(y);
                }
                if (fVar != null) {
                    return fVar;
                }
                if (cls.isSynthetic()) {
                    return B.x(y, eVar);
                }
                b e7 = d.e(cls);
                if (e7 != null) {
                    bVar = (C1116b) e7.b.f4277c;
                }
                if (bVar == null) {
                    i2 = -1;
                } else {
                    i2 = C0806y.f3519a[bVar.ordinal()];
                }
                switch (i2) {
                    case -1:
                    case 6:
                        throw new v0("Unresolved class: " + cls + " (kind = " + bVar + ')', 0);
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        return B.x(y, eVar);
                    case 5:
                        throw new v0("Unknown class: " + cls + " (kind = " + bVar + ')', 0);
                    default:
                        throw new RuntimeException();
                }
            case 2:
                return b.p(b.z().i().A(), D.DECLARED);
            case 3:
                p c02 = b.z().c0();
                j.d(c02, "getStaticScope(...)");
                return b.p(c02, D.DECLARED);
            case 4:
                return b.p(b.z().i().A(), D.INHERITED);
            case 5:
                p c03 = b.z().c0();
                j.d(c03, "getStaticScope(...)");
                return b.p(c03, D.INHERITED);
            case 6:
                Class cls2 = b.e;
                if (cls2.isAnonymousClass()) {
                    return null;
                }
                C1235b y8 = b.y();
                if (y8.f5034c) {
                    String simpleName = cls2.getSimpleName();
                    Method enclosingMethod = cls2.getEnclosingMethod();
                    if (enclosingMethod != null) {
                        return n.N0(simpleName, enclosingMethod.getName() + '$');
                    }
                    Constructor<?> enclosingConstructor = cls2.getEnclosingConstructor();
                    if (enclosingConstructor == null) {
                        return n.M0('$', simpleName, simpleName);
                    }
                    return n.N0(simpleName, enclosingConstructor.getName() + '$');
                }
                String b5 = y8.f().b();
                j.d(b5, "asString(...)");
                return b5;
            case 7:
                if (b.e.isAnonymousClass()) {
                    return null;
                }
                C1235b y9 = b.y();
                if (y9.f5034c) {
                    return null;
                }
                return y9.a().b();
            default:
                Iterable<C0821k> i10 = b.i();
                ArrayList arrayList = new ArrayList(C1196n.w0(i10, 10));
                for (C0821k h5 : i10) {
                    arrayList.add(new H(b, h5));
                }
                return arrayList;
        }
    }

    public C0801t(B b, C0805x xVar) {
        this.d = 6;
        this.e = b;
    }
}
