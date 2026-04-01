package bf;

import B1.a;
import Hf.C0774x;
import Hf.X;
import Nf.d;
import Nf.e;
import Nf.q;
import Qe.A;
import Qe.C0811a;
import Qe.C0813c;
import Qe.C0822l;
import Qe.C0826p;
import Qe.C0831v;
import Qe.Q;
import Re.h;
import Te.C0857s;
import Te.K;
import Te.t;
import Te.u;
import Tf.m;
import Ve.f;
import cf.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import kotlin.jvm.internal.j;
import me.i;
import qf.C1240g;
import tf.C1312p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends K implements C0916a {

    /* renamed from: J  reason: collision with root package name */
    public static final C0920e f4001J = new Object();

    /* renamed from: K  reason: collision with root package name */
    public static final C0920e f4002K = new Object();

    /* renamed from: H  reason: collision with root package name */
    public C0921f f4003H;

    /* renamed from: I  reason: collision with root package name */
    public final boolean f4004I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(C0822l lVar, K k, h hVar, C1240g gVar, C0813c cVar, Q q, boolean z) {
        super(lVar, k, hVar, gVar, cVar, q);
        if (lVar == null) {
            w0(0);
            throw null;
        } else if (hVar == null) {
            w0(1);
            throw null;
        } else if (gVar == null) {
            w0(2);
            throw null;
        } else if (cVar != null) {
            this.f4003H = null;
            this.f4004I = z;
        } else {
            w0(3);
            throw null;
        }
    }

    public static g T0(C0822l lVar, c cVar, C1240g gVar, f fVar, boolean z) {
        if (lVar == null) {
            w0(5);
            throw null;
        } else if (gVar != null) {
            return new g(lVar, (K) null, cVar, gVar, C0813c.DECLARATION, fVar, z);
        } else {
            w0(7);
            throw null;
        }
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 13 || i2 == 18 || i2 == 21) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 13 || i2 == 18 || i2 == 21) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 6:
            case 16:
                objArr[0] = "annotations";
                break;
            case 2:
            case 7:
                objArr[0] = "name";
                break;
            case 3:
            case 15:
                objArr[0] = "kind";
                break;
            case 4:
            case 8:
            case 17:
                objArr[0] = "source";
                break;
            case 9:
                objArr[0] = "contextReceiverParameters";
                break;
            case 10:
                objArr[0] = "typeParameters";
                break;
            case 11:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 12:
                objArr[0] = "visibility";
                break;
            case 13:
            case 18:
            case 21:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor";
                break;
            case 14:
                objArr[0] = "newOwner";
                break;
            case 19:
                objArr[0] = "enhancedValueParameterTypes";
                break;
            case 20:
                objArr[0] = "enhancedReturnType";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i2 == 13) {
            objArr[1] = "initialize";
        } else if (i2 == 18) {
            objArr[1] = "createSubstitutedCopy";
        } else if (i2 != 21) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor";
        } else {
            objArr[1] = "enhance";
        }
        switch (i2) {
            case 5:
            case 6:
            case 7:
            case 8:
                objArr[2] = "createJavaMethod";
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                objArr[2] = "initialize";
                break;
            case 13:
            case 18:
            case 21:
                break;
            case 14:
            case 15:
            case 16:
            case 17:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 19:
            case 20:
                objArr[2] = "enhance";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i2 == 13 || i2 == 18 || i2 == 21) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final t G0(C0813c cVar, C0822l lVar, C0831v vVar, Q q, h hVar, C1240g gVar) {
        if (lVar == null) {
            w0(14);
            throw null;
        } else if (cVar == null) {
            w0(15);
            throw null;
        } else if (hVar != null) {
            K k = (K) vVar;
            if (gVar == null) {
                gVar = getName();
            }
            C0822l lVar2 = lVar;
            h hVar2 = hVar;
            g gVar2 = new g(lVar2, k, hVar2, gVar, cVar, q, this.f4004I);
            C0921f fVar = this.f4003H;
            gVar2.U0(fVar.isStable, fVar.isSynthesized);
            return gVar2;
        } else {
            w0(16);
            throw null;
        }
    }

    public final K S0(u uVar, u uVar2, List list, List list2, List list3, C0774x xVar, A a7, C0826p pVar, Map map) {
        Nf.f fVar;
        Nf.h hVar;
        if (list == null) {
            w0(9);
            throw null;
        } else if (list2 == null) {
            w0(10);
            throw null;
        } else if (list3 == null) {
            w0(11);
            throw null;
        } else if (pVar != null) {
            super.S0(uVar, uVar2, list, list2, list3, xVar, a7, pVar, map);
            Iterator it = q.f3594a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    fVar = e.e;
                    break;
                }
                hVar = (Nf.h) it.next();
                m mVar = hVar.b;
                C1240g gVar = hVar.f3587a;
                if (gVar == null || j.a(getName(), gVar)) {
                    if (mVar != null) {
                        String b = getName().b();
                        j.d(b, "asString(...)");
                        if (!((Pattern) mVar.e).matcher(b).matches()) {
                            continue;
                        }
                    }
                    Collection collection = hVar.f3588c;
                    if (collection == null || collection.contains(getName())) {
                        d[] dVarArr = hVar.e;
                        int length = dVarArr.length;
                        int i2 = 0;
                    }
                }
            }
            d[] dVarArr2 = hVar.e;
            int length2 = dVarArr2.length;
            int i22 = 0;
            while (true) {
                if (i22 < length2) {
                    if (dVarArr2[i22].b(this) != null) {
                        fVar = new Nf.f(false);
                        break;
                    }
                    i22++;
                } else if (((String) hVar.d.invoke(this)) != null) {
                    fVar = new Nf.f(false);
                } else {
                    fVar = e.f;
                }
            }
            this.q = fVar.d;
            return this;
        } else {
            w0(12);
            throw null;
        }
    }

    public final void U0(boolean z, boolean z3) {
        C0921f fVar;
        if (z) {
            if (z3) {
                fVar = C0921f.STABLE_SYNTHESIZED;
            } else {
                fVar = C0921f.STABLE_DECLARED;
            }
        } else if (z3) {
            fVar = C0921f.NON_STABLE_SYNTHESIZED;
        } else {
            fVar = C0921f.NON_STABLE_DECLARED;
        }
        if (fVar != null) {
            this.f4003H = fVar;
            return;
        }
        throw new IllegalStateException("@NotNull method kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor$ParameterNamesStatus.get must not return null");
    }

    public final boolean Z() {
        return this.f4003H.isSynthesized;
    }

    public final C0916a p(C0774x xVar, ArrayList arrayList, C0774x xVar2, i iVar) {
        u uVar;
        ArrayList p6 = a.p(arrayList, B(), this);
        if (xVar == null) {
            uVar = null;
        } else {
            uVar = C1312p.k(this, xVar, Re.g.f3692a);
        }
        C0857s K02 = K0(X.b);
        K02.f3791j = p6;
        K02.n = xVar2;
        K02.l = uVar;
        K02.s = true;
        K02.r = true;
        g gVar = (g) K02.f3788A.H0(K02);
        if (iVar != null) {
            gVar.L0((C0811a) iVar.d, iVar.e);
        }
        if (gVar != null) {
            return gVar;
        }
        w0(21);
        throw null;
    }
}
