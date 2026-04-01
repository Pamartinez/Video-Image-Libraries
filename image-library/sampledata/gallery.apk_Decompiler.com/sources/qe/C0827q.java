package Qe;

import Bf.e;
import Nf.n;
import Te.M;
import Te.N;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import ne.C1192j;
import tf.C1301e;

/* renamed from: Qe.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0827q {

    /* renamed from: a  reason: collision with root package name */
    public static final C0826p f3675a;
    public static final C0826p b;

    /* renamed from: c  reason: collision with root package name */
    public static final C0826p f3676c;
    public static final C0826p d;
    public static final C0826p e;
    public static final C0826p f;
    public static final C0826p g;

    /* renamed from: h  reason: collision with root package name */
    public static final C0826p f3677h;

    /* renamed from: i  reason: collision with root package name */
    public static final C0826p f3678i;

    /* renamed from: j  reason: collision with root package name */
    public static final C0826p f3679j;
    public static final S k = new S(2);
    public static final S l = new S(3);
    public static final S m = new S(4);
    public static final n n;

    /* renamed from: o  reason: collision with root package name */
    public static final HashMap f3680o;

    static {
        n nVar;
        d0 d0Var = d0.f3667c;
        C0826p pVar = new C0826p(d0Var, 0);
        f3675a = pVar;
        e0 e0Var = e0.f3668c;
        C0826p pVar2 = new C0826p(e0Var, 1);
        b = pVar2;
        f0 f0Var = f0.f3669c;
        C0826p pVar3 = new C0826p(f0Var, 2);
        f3676c = pVar3;
        a0 a0Var = a0.f3664c;
        C0826p pVar4 = new C0826p(a0Var, 3);
        d = pVar4;
        g0 g0Var = g0.f3670c;
        C0826p pVar5 = new C0826p(g0Var, 4);
        e = pVar5;
        c0 c0Var = c0.f3666c;
        C0826p pVar6 = new C0826p(c0Var, 5);
        f = pVar6;
        Z z = Z.f3663c;
        C0826p pVar7 = new C0826p(z, 6);
        g = pVar7;
        b0 b0Var = b0.f3665c;
        C0826p pVar8 = pVar7;
        C0826p pVar9 = new C0826p(b0Var, 7);
        f3677h = pVar9;
        C0826p pVar10 = pVar9;
        h0 h0Var = h0.f3671c;
        b0 b0Var2 = b0Var;
        C0826p pVar11 = new C0826p(h0Var, 8);
        f3678i = pVar11;
        Collections.unmodifiableSet(C1192j.z0(new C0826p[]{pVar, pVar2, pVar4, pVar6}));
        C0826p pVar12 = pVar11;
        HashMap hashMap = new HashMap(6);
        hashMap.put(pVar2, 0);
        hashMap.put(pVar, 0);
        hashMap.put(pVar4, 1);
        hashMap.put(pVar3, 1);
        hashMap.put(pVar5, 2);
        Collections.unmodifiableMap(hashMap);
        f3679j = pVar5;
        try {
            Iterator it = Arrays.asList(new n[0]).iterator();
            if (it.hasNext()) {
                nVar = (n) it.next();
            } else {
                nVar = n.f3593a;
            }
            n = nVar;
            HashMap hashMap2 = new HashMap();
            f3680o = hashMap2;
            hashMap2.put(d0Var, pVar);
            hashMap2.put(e0Var, pVar2);
            hashMap2.put(f0Var, pVar3);
            hashMap2.put(a0Var, pVar4);
            hashMap2.put(g0Var, pVar5);
            hashMap2.put(c0Var, pVar6);
            hashMap2.put(z, pVar8);
            hashMap2.put(b0Var2, pVar10);
            hashMap2.put(h0Var, pVar12);
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 != 16) {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i2 != 16) {
            i7 = 3;
        } else {
            i7 = 2;
        }
        Object[] objArr = new Object[i7];
        if (!(i2 == 1 || i2 == 3 || i2 == 5 || i2 == 7)) {
            switch (i2) {
                case 9:
                    break;
                case 10:
                case 12:
                    objArr[0] = "first";
                    break;
                case 11:
                case 13:
                    objArr[0] = "second";
                    break;
                case 14:
                case 15:
                    objArr[0] = "visibility";
                    break;
                case 16:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities";
                    break;
                default:
                    objArr[0] = "what";
                    break;
            }
        }
        objArr[0] = "from";
        if (i2 != 16) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities";
        } else {
            objArr[1] = "toDescriptorVisibility";
        }
        switch (i2) {
            case 2:
            case 3:
                objArr[2] = "isVisibleIgnoringReceiver";
                break;
            case 4:
            case 5:
                objArr[2] = "isVisibleWithAnyReceiver";
                break;
            case 6:
            case 7:
                objArr[2] = "inSameFile";
                break;
            case 8:
            case 9:
                objArr[2] = "findInvisibleMember";
                break;
            case 10:
            case 11:
                objArr[2] = "compareLocal";
                break;
            case 12:
            case 13:
                objArr[2] = "compare";
                break;
            case 14:
                objArr[2] = "isPrivate";
                break;
            case 15:
                objArr[2] = "toDescriptorVisibility";
                break;
            case 16:
                break;
            default:
                objArr[2] = "isVisible";
                break;
        }
        String format = String.format(str, objArr);
        if (i2 != 16) {
            th = new IllegalArgumentException(format);
        } else {
            th = new IllegalStateException(format);
        }
        throw th;
    }

    public static Integer b(C0826p pVar, C0826p pVar2) {
        if (pVar != null) {
            j0 j0Var = pVar.f3674a;
            if (pVar2 != null) {
                j0 j0Var2 = pVar2.f3674a;
                Integer a7 = j0Var.a(j0Var2);
                if (a7 != null) {
                    return a7;
                }
                Integer a10 = j0Var2.a(j0Var);
                if (a10 != null) {
                    return Integer.valueOf(-a10.intValue());
                }
                return null;
            }
            a(13);
            throw null;
        }
        a(12);
        throw null;
    }

    public static C0825o c(e eVar, C0825o oVar, C0822l lVar) {
        C0825o c5;
        if (oVar == null) {
            a(8);
            throw null;
        } else if (lVar != null) {
            C0825o oVar2 = (C0825o) oVar.a();
            while (oVar2 != null && oVar2.getVisibility() != f) {
                if (!oVar2.getVisibility().a(eVar, oVar2, lVar)) {
                    return oVar2;
                }
                oVar2 = (C0825o) C1301e.i(oVar2, C0825o.class, true);
            }
            if (!(oVar instanceof M) || (c5 = c(eVar, ((N) ((M) oVar)).f3766J, lVar)) == null) {
                return null;
            }
            return c5;
        } else {
            a(9);
            throw null;
        }
    }

    public static boolean d(C0825o oVar, C0822l lVar) {
        if (lVar != null) {
            S f5 = C1301e.f(lVar);
            if (f5 != S.e) {
                return f5.equals(C1301e.f(oVar));
            }
            return false;
        }
        a(7);
        throw null;
    }

    public static boolean e(C0826p pVar) {
        if (pVar == null) {
            a(14);
            throw null;
        } else if (pVar == f3675a || pVar == b) {
            return true;
        } else {
            return false;
        }
    }

    public static C0826p f(j0 j0Var) {
        if (j0Var != null) {
            C0826p pVar = (C0826p) f3680o.get(j0Var);
            if (pVar != null) {
                return pVar;
            }
            throw new IllegalArgumentException("Inapplicable visibility: " + j0Var);
        }
        a(15);
        throw null;
    }
}
