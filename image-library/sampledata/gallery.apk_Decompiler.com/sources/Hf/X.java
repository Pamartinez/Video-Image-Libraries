package Hf;

import Jf.l;
import Mf.b;
import Ne.p;
import Qe.V;
import Qf.h;
import Qf.k;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import ef.i;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.j;
import uf.C1317b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class X {
    public static final X b = new X(T.f3436a);

    /* renamed from: a  reason: collision with root package name */
    public final T f3438a;

    public X(T t) {
        this.f3438a = t;
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        if (!(i2 == 1 || i2 == 2 || i2 == 8 || i2 == 34 || i2 == 37)) {
            switch (i2) {
                case 11:
                case 12:
                case 13:
                    break;
                default:
                    switch (i2) {
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                            break;
                        default:
                            switch (i2) {
                                case 29:
                                case 30:
                                case 31:
                                case 32:
                                    break;
                                default:
                                    switch (i2) {
                                        case 40:
                                        case 41:
                                        case 42:
                                            break;
                                        default:
                                            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                                            break;
                                    }
                            }
                    }
            }
        }
        str = "@NotNull method %s.%s must not return null";
        if (!(i2 == 1 || i2 == 2 || i2 == 8 || i2 == 34 || i2 == 37)) {
            switch (i2) {
                case 11:
                case 12:
                case 13:
                    break;
                default:
                    switch (i2) {
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                            break;
                        default:
                            switch (i2) {
                                case 29:
                                case 30:
                                case 31:
                                case 32:
                                    break;
                                default:
                                    switch (i2) {
                                        case 40:
                                        case 41:
                                        case 42:
                                            break;
                                        default:
                                            i7 = 3;
                                            break;
                                    }
                            }
                    }
            }
        }
        i7 = 2;
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 2:
            case 8:
            case 11:
            case 12:
            case 13:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 29:
            case 30:
            case 31:
            case 32:
            case 34:
            case 37:
            case 40:
            case 41:
            case 42:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor";
                break;
            case 3:
                objArr[0] = "first";
                break;
            case 4:
                objArr[0] = "second";
                break;
            case 5:
                objArr[0] = "substitutionContext";
                break;
            case 6:
                objArr[0] = "context";
                break;
            case 9:
            case 14:
                objArr[0] = "type";
                break;
            case 10:
            case 15:
                objArr[0] = "howThisTypeIsUsed";
                break;
            case 16:
            case 17:
            case 36:
                objArr[0] = "typeProjection";
                break;
            case 18:
            case 28:
                objArr[0] = "originalProjection";
                break;
            case 26:
                objArr[0] = "originalType";
                break;
            case 27:
                objArr[0] = "substituted";
                break;
            case 33:
                objArr[0] = "annotations";
                break;
            case 35:
            case 38:
                objArr[0] = "typeParameterVariance";
                break;
            case 39:
                objArr[0] = "projectionKind";
                break;
            default:
                objArr[0] = "substitution";
                break;
        }
        if (i2 == 1) {
            objArr[1] = "replaceWithNonApproximatingSubstitution";
        } else if (i2 == 2) {
            objArr[1] = "replaceWithContravariantApproximatingSubstitution";
        } else if (i2 == 8) {
            objArr[1] = "getSubstitution";
        } else if (i2 != 34) {
            if (i2 != 37) {
                switch (i2) {
                    case 11:
                    case 12:
                    case 13:
                        objArr[1] = "safeSubstitute";
                        break;
                    default:
                        switch (i2) {
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                                objArr[1] = "unsafeSubstitute";
                                break;
                            default:
                                switch (i2) {
                                    case 29:
                                    case 30:
                                    case 31:
                                    case 32:
                                        objArr[1] = "projectedTypeForConflictedTypeWithUnsafeVariance";
                                        break;
                                    default:
                                        switch (i2) {
                                            case 40:
                                            case 41:
                                            case 42:
                                                break;
                                            default:
                                                objArr[1] = "kotlin/reflect/jvm/internal/impl/types/TypeSubstitutor";
                                                break;
                                        }
                                }
                        }
                }
            }
            objArr[1] = "combine";
        } else {
            objArr[1] = "filterOutUnsafeVariance";
        }
        switch (i2) {
            case 1:
            case 2:
            case 8:
            case 11:
            case 12:
            case 13:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 29:
            case 30:
            case 31:
            case 32:
            case 34:
            case 37:
            case 40:
            case 41:
            case 42:
                break;
            case 3:
            case 4:
                objArr[2] = "createChainedSubstitutor";
                break;
            case 7:
                objArr[2] = "<init>";
                break;
            case 9:
            case 10:
                objArr[2] = "safeSubstitute";
                break;
            case 14:
            case 15:
            case 16:
                objArr[2] = "substitute";
                break;
            case 17:
                objArr[2] = "substituteWithoutApproximation";
                break;
            case 18:
                objArr[2] = "unsafeSubstitute";
                break;
            case 26:
            case 27:
            case 28:
                objArr[2] = "projectedTypeForConflictedTypeWithUnsafeVariance";
                break;
            case 33:
                objArr[2] = "filterOutUnsafeVariance";
                break;
            case 35:
            case 36:
            case 38:
            case 39:
                objArr[2] = "combine";
                break;
            default:
                objArr[2] = "create";
                break;
        }
        String format = String.format(str, objArr);
        if (!(i2 == 1 || i2 == 2 || i2 == 8 || i2 == 34 || i2 == 37)) {
            switch (i2) {
                case 11:
                case 12:
                case 13:
                    break;
                default:
                    switch (i2) {
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                            break;
                        default:
                            switch (i2) {
                                case 29:
                                case 30:
                                case 31:
                                case 32:
                                    break;
                                default:
                                    switch (i2) {
                                        case 40:
                                        case 41:
                                        case 42:
                                            break;
                                        default:
                                            th = new IllegalArgumentException(format);
                                            break;
                                    }
                            }
                    }
            }
        }
        th = new IllegalStateException(format);
        throw th;
    }

    public static d0 b(d0 d0Var, d0 d0Var2) {
        if (d0Var == null) {
            a(38);
            throw null;
        } else if (d0Var2 != null) {
            d0 d0Var3 = d0.INVARIANT;
            if (d0Var == d0Var3) {
                if (d0Var2 != null) {
                    return d0Var2;
                }
                a(40);
                throw null;
            } else if (d0Var2 == d0Var3) {
                if (d0Var != null) {
                    return d0Var;
                }
                a(41);
                throw null;
            } else if (d0Var != d0Var2) {
                throw new AssertionError("Variance conflict: type parameter variance '" + d0Var + "' and projection kind '" + d0Var2 + "' cannot be combined");
            } else if (d0Var2 != null) {
                return d0Var2;
            } else {
                a(42);
                throw null;
            }
        } else {
            a(39);
            throw null;
        }
    }

    public static W c(d0 d0Var, d0 d0Var2) {
        d0 d0Var3 = d0.IN_VARIANCE;
        if (d0Var == d0Var3 && d0Var2 == d0.OUT_VARIANCE) {
            return W.OUT_IN_IN_POSITION;
        }
        if (d0Var == d0.OUT_VARIANCE && d0Var2 == d0Var3) {
            return W.IN_IN_OUT_POSITION;
        }
        return W.NO_CONFLICT;
    }

    public static X d(C0774x xVar) {
        if (xVar != null) {
            return new X(N.b.f(xVar.s0(), xVar.e0()));
        }
        a(6);
        throw null;
    }

    public static X e(T t, T t3) {
        if (t == null) {
            a(3);
            throw null;
        } else if (t3 != null) {
            if (t.e()) {
                t = t3;
            } else if (!t3.e()) {
                t = new C0767p(t, t3);
            }
            return new X(t);
        } else {
            a(4);
            throw null;
        }
    }

    public static String h(Object obj) {
        try {
            return obj.toString();
        } catch (Throwable th) {
            if (!k.i(th)) {
                return "[Exception while computing toString(): " + th + "]";
            }
            throw th;
        }
    }

    public final T f() {
        T t = this.f3438a;
        if (t != null) {
            return t;
        }
        a(8);
        throw null;
    }

    public final C0774x g(C0774x xVar, d0 d0Var) {
        if (xVar == null) {
            a(9);
            throw null;
        } else if (d0Var == null) {
            a(10);
            throw null;
        } else if (this.f3438a.e()) {
            return xVar;
        } else {
            try {
                C0774x b5 = j(new G(xVar, d0Var), (V) null, 0).b();
                if (b5 != null) {
                    return b5;
                }
                a(12);
                throw null;
            } catch (V e) {
                return l.c(Jf.k.UNABLE_TO_SUBSTITUTE_TYPE, e.getMessage());
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v6, types: [Hf.T, java.lang.Object] */
    public final C0774x i(C0774x xVar, d0 d0Var) {
        if (xVar == null) {
            a(14);
            throw null;
        } else if (d0Var != null) {
            P g = new G(f().f(xVar, d0Var), d0Var);
            T t = this.f3438a;
            if (!t.e()) {
                try {
                    g = j(g, (V) null, 0);
                } catch (V unused) {
                    g = null;
                }
            }
            if (t.a() || t.b()) {
                boolean b5 = t.b();
                if (g != null) {
                    if (!g.c()) {
                        C0774x b8 = g.b();
                        j.d(b8, "getType(...)");
                        if (a0.c(b8, b.d, (h) null)) {
                            d0 a7 = g.a();
                            j.d(a7, "getProjectionKind(...)");
                            if (a7 == d0.OUT_VARIANCE) {
                                g = new G((C0774x) og.k.g(b8).b, a7);
                            } else if (b5) {
                                g = new G((C0774x) og.k.g(b8).f3535a, a7);
                            } else {
                                ? obj = new Object();
                                X x9 = new X(obj);
                                if (!obj.e()) {
                                    try {
                                        g = x9.j(g, (V) null, 0);
                                    } catch (V unused2) {
                                    }
                                }
                            }
                        }
                    }
                }
                g = null;
            }
            if (g == null) {
                return null;
            }
            return g.b();
        } else {
            a(15);
            throw null;
        }
    }

    public final P j(P p6, V v, int i2) {
        C0752a aVar;
        B b5;
        d0 d0Var;
        X x9;
        C0762k kVar;
        C0774x xVar;
        C0762k kVar2;
        boolean z;
        V v6 = v;
        int i7 = i2;
        C0774x xVar2 = null;
        if (p6 != null) {
            T t = this.f3438a;
            if (i7 <= 100) {
                if (!p6.c()) {
                    C0774x b8 = p6.b();
                    if (b8 instanceof b0) {
                        b0 b0Var = (b0) b8;
                        c0 O4 = b0Var.O();
                        C0774x K6 = b0Var.K();
                        P j2 = j(new G(O4, p6.a()), v6, i7 + 1);
                        if (j2.c()) {
                            return j2;
                        }
                        return new G(C0754c.G(j2.b().x0(), i(K6, p6.a())), j2.a());
                    }
                    j.e(b8, "<this>");
                    b8.x0();
                    if (!(b8.x0() instanceof i)) {
                        P d = t.d(b8);
                        if (d == null) {
                            d = null;
                        } else if (b8.getAnnotations().g(p.y)) {
                            M s0 = d.b().s0();
                            if (s0 instanceof If.i) {
                                P p8 = ((If.i) s0).f3466a;
                                d0 a7 = p8.a();
                                W c5 = c(p6.a(), a7);
                                W w = W.OUT_IN_IN_POSITION;
                                if (c5 == w) {
                                    d = new G(p8.b());
                                } else if (v6 != null && c(v6.t(), a7) == w) {
                                    d = new G(p8.b());
                                }
                            }
                        }
                        d0 a10 = p6.a();
                        if (d == null && C0754c.l(b8)) {
                            c0 x02 = b8.x0();
                            if (x02 instanceof C0762k) {
                                kVar2 = (C0762k) x02;
                            } else {
                                kVar2 = null;
                            }
                            if (kVar2 != null) {
                                z = kVar2.L();
                            } else {
                                z = false;
                            }
                            if (!z) {
                                C0768q qVar = (C0768q) b8.x0();
                                B b10 = qVar.f;
                                B b11 = qVar.e;
                                int i8 = i7 + 1;
                                P j3 = j(new G(b11, a10), v6, i8);
                                P j8 = j(new G(b10, a10), v6, i8);
                                d0 a11 = j3.a();
                                if (!(j3.b() == b11 && j8.b() == b10)) {
                                    return new G(C0754c.f(C0754c.b(j3.b()), C0754c.b(j8.b())), a11);
                                }
                            }
                        }
                        if (!Ne.i.E(b8) && !C0754c.k(b8)) {
                            if (d != null) {
                                W c6 = c(a10, d.a());
                                if (!(b8.s0() instanceof C1317b)) {
                                    int i10 = U.f3437a[c6.ordinal()];
                                    if (i10 == 1) {
                                        throw new Exception("Out-projection in in-position");
                                    } else if (i10 == 2) {
                                        return new G(b8.s0().f().o(), d0.OUT_VARIANCE);
                                    }
                                }
                                c0 x03 = b8.x0();
                                if (x03 instanceof C0762k) {
                                    kVar = (C0762k) x03;
                                } else {
                                    kVar = null;
                                }
                                if (kVar == null || !kVar.L()) {
                                    kVar = null;
                                }
                                if (d.c()) {
                                    return d;
                                }
                                if (kVar != null) {
                                    xVar = kVar.n(d.b());
                                } else {
                                    xVar = a0.h(d.b(), b8.u0());
                                }
                                if (!b8.getAnnotations().isEmpty()) {
                                    Re.h c8 = t.c(b8.getAnnotations());
                                    if (c8 != null) {
                                        if (c8.g(p.y)) {
                                            c8 = new Re.l(c8, new C0771u(1));
                                        }
                                        xVar = c.L(xVar, new Re.i(new Re.h[]{xVar.getAnnotations(), c8}));
                                    } else {
                                        a(33);
                                        throw null;
                                    }
                                }
                                if (c6 == W.NO_CONFLICT) {
                                    a10 = b(a10, d.a());
                                }
                                return new G(xVar, a10);
                            }
                            C0774x b12 = p6.b();
                            d0 a12 = p6.a();
                            if (!(b12.s0().g() instanceof V)) {
                                c0 x04 = b12.x0();
                                if (x04 instanceof C0752a) {
                                    aVar = (C0752a) x04;
                                } else {
                                    aVar = null;
                                }
                                if (aVar != null) {
                                    b5 = aVar.f;
                                } else {
                                    b5 = null;
                                }
                                if (b5 != null) {
                                    if (t instanceof C0770t) {
                                        C0770t tVar = (C0770t) t;
                                        if (tVar.d) {
                                            x9 = new X(new C0770t(tVar.b, tVar.f3451c, false));
                                            xVar2 = x9.i(b5, d0.INVARIANT);
                                        }
                                    }
                                    x9 = this;
                                    xVar2 = x9.i(b5, d0.INVARIANT);
                                }
                                List parameters = b12.s0().getParameters();
                                List e02 = b12.e0();
                                ArrayList arrayList = new ArrayList(parameters.size());
                                boolean z3 = false;
                                for (int i11 = 0; i11 < parameters.size(); i11++) {
                                    V v8 = (V) parameters.get(i11);
                                    P p10 = (P) e02.get(i11);
                                    P j10 = j(p10, v8, i7 + 1);
                                    int i12 = U.f3437a[c(v8.t(), j10.a()).ordinal()];
                                    if (i12 == 1 || i12 == 2) {
                                        j10 = a0.j(v8);
                                    } else if (i12 == 3 && v8.t() != (d0Var = d0.INVARIANT) && !j10.c()) {
                                        j10 = new G(j10.b(), d0Var);
                                    }
                                    if (j10 != p10) {
                                        z3 = true;
                                    }
                                    arrayList.add(j10);
                                }
                                if (z3) {
                                    e02 = arrayList;
                                }
                                Re.h c10 = t.c(b12.getAnnotations());
                                j.e(e02, "newArguments");
                                j.e(c10, "newAnnotations");
                                C0774x q = C0754c.q(b12, e02, c10, 4);
                                if ((q instanceof B) && (xVar2 instanceof B)) {
                                    q = C0754c.F((B) q, (B) xVar2);
                                }
                                return new G(q, a12);
                            }
                        }
                    }
                }
                return p6;
            }
            throw new IllegalStateException("Recursion too deep. Most likely infinite loop while substituting " + h(p6) + "; substitution: " + h(t));
        }
        a(18);
        throw null;
    }
}
