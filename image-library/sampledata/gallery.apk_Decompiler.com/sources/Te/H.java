package Te;

import Ae.a;
import Gf.h;
import Hf.C0774x;
import Hf.T;
import Hf.X;
import Qe.A;
import Qe.C0811a;
import Qe.C0813c;
import Qe.C0822l;
import Qe.C0824n;
import Qe.C0826p;
import Qe.C0831v;
import Qe.N;
import Qe.O;
import Qe.Q;
import Re.g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import qf.C1240g;
import vf.C1327g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class H extends S implements O {

    /* renamed from: A  reason: collision with root package name */
    public I f3756A;
    public J B;

    /* renamed from: C  reason: collision with root package name */
    public r f3757C;
    public r D;

    /* renamed from: j  reason: collision with root package name */
    public final boolean f3758j;
    public h k;
    public a l;
    public final A m;
    public C0826p n;

    /* renamed from: o  reason: collision with root package name */
    public Collection f3759o;

    /* renamed from: p  reason: collision with root package name */
    public final O f3760p;
    public final C0813c q;
    public final boolean r;
    public final boolean s;
    public final boolean t;
    public final boolean u;
    public final boolean v;
    public List w;

    /* renamed from: x  reason: collision with root package name */
    public u f3761x;
    public u y;
    public ArrayList z;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public H(C0822l lVar, O o2, Re.h hVar, A a7, C0826p pVar, boolean z3, C1240g gVar, C0813c cVar, Q q10, boolean z7, boolean z9, boolean z10, boolean z11, boolean z12) {
        super(lVar, hVar, gVar, (C0774x) null, q10);
        C0813c cVar2 = cVar;
        if (lVar == null) {
            w0(0);
            throw null;
        } else if (hVar == null) {
            w0(1);
            throw null;
        } else if (a7 == null) {
            w0(2);
            throw null;
        } else if (pVar == null) {
            w0(3);
            throw null;
        } else if (gVar == null) {
            w0(4);
            throw null;
        } else if (cVar2 == null) {
            w0(5);
            throw null;
        } else if (q10 != null) {
            this.f3758j = z3;
            this.f3759o = null;
            this.w = Collections.EMPTY_LIST;
            this.m = a7;
            this.n = pVar;
            this.f3760p = o2 == null ? this : o2;
            this.q = cVar2;
            this.r = z7;
            this.s = z9;
            this.t = z10;
            this.u = z11;
            this.v = z12;
        } else {
            w0(6);
            throw null;
        }
    }

    public static H F0(C0822l lVar, A a7, C0826p pVar, boolean z3, C1240g gVar, C0813c cVar, Q q10) {
        if (lVar == null) {
            w0(7);
            throw null;
        } else if (a7 == null) {
            w0(9);
            throw null;
        } else if (pVar == null) {
            w0(10);
            throw null;
        } else if (gVar == null) {
            w0(11);
            throw null;
        } else if (cVar == null) {
            w0(12);
            throw null;
        } else if (q10 != null) {
            return new H(lVar, (O) null, g.f3692a, a7, pVar, z3, gVar, cVar, q10, false, false, false, false, false);
        } else {
            w0(13);
            throw null;
        }
    }

    public static C0831v H0(X x9, N n3) {
        if (n3 != null) {
            C0831v vVar = ((F) n3).f3750p;
            if (vVar != null) {
                return vVar.c(x9);
            }
            return null;
        }
        w0(31);
        throw null;
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (!(i2 == 28 || i2 == 38 || i2 == 39 || i2 == 41 || i2 == 42)) {
            switch (i2) {
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        }
        str = "@NotNull method %s.%s must not return null";
        if (!(i2 == 28 || i2 == 38 || i2 == 39 || i2 == 41 || i2 == 42)) {
            switch (i2) {
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    break;
                default:
                    i7 = 3;
                    break;
            }
        }
        i7 = 2;
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 8:
                objArr[0] = "annotations";
                break;
            case 2:
            case 9:
                objArr[0] = "modality";
                break;
            case 3:
            case 10:
            case 20:
                objArr[0] = "visibility";
                break;
            case 4:
            case 11:
                objArr[0] = "name";
                break;
            case 5:
            case 12:
            case 35:
                objArr[0] = "kind";
                break;
            case 6:
            case 13:
            case 37:
                objArr[0] = "source";
                break;
            case 14:
                objArr[0] = "inType";
                break;
            case 15:
            case 17:
                objArr[0] = "outType";
                break;
            case 16:
            case 18:
                objArr[0] = "typeParameters";
                break;
            case 19:
                objArr[0] = "contextReceiverParameters";
                break;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 28:
            case 38:
            case 39:
            case 41:
            case 42:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl";
                break;
            case 27:
                objArr[0] = "originalSubstitutor";
                break;
            case 29:
                objArr[0] = "copyConfiguration";
                break;
            case 30:
                objArr[0] = "substitutor";
                break;
            case 31:
                objArr[0] = "accessorDescriptor";
                break;
            case 32:
                objArr[0] = "newOwner";
                break;
            case 33:
                objArr[0] = "newModality";
                break;
            case 34:
                objArr[0] = "newVisibility";
                break;
            case 36:
                objArr[0] = "newName";
                break;
            case 40:
                objArr[0] = "overriddenDescriptors";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i2 == 28) {
            objArr[1] = "getSourceToUseForCopy";
        } else if (i2 == 38) {
            objArr[1] = "getOriginal";
        } else if (i2 == 39) {
            objArr[1] = "getKind";
        } else if (i2 == 41) {
            objArr[1] = "getOverriddenDescriptors";
        } else if (i2 != 42) {
            switch (i2) {
                case 21:
                    objArr[1] = "getTypeParameters";
                    break;
                case 22:
                    objArr[1] = "getContextReceiverParameters";
                    break;
                case 23:
                    objArr[1] = "getReturnType";
                    break;
                case 24:
                    objArr[1] = "getModality";
                    break;
                case 25:
                    objArr[1] = "getVisibility";
                    break;
                case 26:
                    objArr[1] = "getAccessors";
                    break;
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl";
                    break;
            }
        } else {
            objArr[1] = "copy";
        }
        switch (i2) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                objArr[2] = "create";
                break;
            case 14:
                objArr[2] = "setInType";
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                objArr[2] = "setType";
                break;
            case 20:
                objArr[2] = "setVisibility";
                break;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 28:
            case 38:
            case 39:
            case 41:
            case 42:
                break;
            case 27:
                objArr[2] = "substitute";
                break;
            case 29:
                objArr[2] = "doSubstitute";
                break;
            case 30:
            case 31:
                objArr[2] = "getSubstitutedInitialSignatureDescriptor";
                break;
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 40:
                objArr[2] = "setOverriddenDescriptors";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (!(i2 == 28 || i2 == 38 || i2 == 39 || i2 == 41 || i2 == 42)) {
            switch (i2) {
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    break;
                default:
                    th = new IllegalArgumentException(format);
                    break;
            }
        }
        th = new IllegalStateException(format);
        throw th;
    }

    public final u E() {
        return this.f3761x;
    }

    /* renamed from: E0 */
    public final H S(C0822l lVar, A a7, C0826p pVar, C0813c cVar) {
        G g = new G(this);
        if (lVar != null) {
            g.b = lVar;
            g.e = null;
            g.f3752c = a7;
            if (pVar != null) {
                g.d = pVar;
                if (cVar != null) {
                    g.f = cVar;
                    g.f3751a = false;
                    H d = g.d();
                    if (d != null) {
                        return d;
                    }
                    w0(42);
                    throw null;
                }
                G.a(10);
                throw null;
            }
            G.a(8);
            throw null;
        }
        G.a(0);
        throw null;
    }

    public final boolean G() {
        return this.f3758j;
    }

    public H G0(C0822l lVar, A a7, C0826p pVar, O o2, C0813c cVar, C1240g gVar) {
        if (lVar == null) {
            w0(32);
            throw null;
        } else if (a7 == null) {
            w0(33);
            throw null;
        } else if (pVar == null) {
            w0(34);
            throw null;
        } else if (cVar == null) {
            w0(35);
            throw null;
        } else if (gVar != null) {
            return new H(lVar, o2, getAnnotations(), a7, pVar, this.f3758j, gVar, cVar, Q.f3662a, this.r, U(), this.t, isExternal(), this.v);
        } else {
            w0(36);
            throw null;
        }
    }

    public final u H() {
        return this.y;
    }

    public final r I() {
        return this.D;
    }

    public final void I0(I i2, J j2, r rVar, r rVar2) {
        this.f3756A = i2;
        this.B = j2;
        this.f3757C = rVar;
        this.D = rVar2;
    }

    public final void J0(h hVar, a aVar) {
        if (aVar != null) {
            this.l = aVar;
            if (hVar == null) {
                hVar = (h) aVar.invoke();
            }
            this.k = hVar;
            return;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", new Object[]{"compileTimeInitializerFactory", "kotlin/reflect/jvm/internal/impl/descriptors/impl/VariableDescriptorWithInitializerImpl", "setCompileTimeInitializer"}));
    }

    public final void L0(C0774x xVar, List list, u uVar, u uVar2, List list2) {
        if (xVar == null) {
            w0(17);
            throw null;
        } else if (list == null) {
            w0(18);
            throw null;
        } else if (list2 != null) {
            this.f3772i = xVar;
            this.z = new ArrayList(list);
            this.y = uVar2;
            this.f3761x = uVar;
            this.w = list2;
        } else {
            w0(19);
            throw null;
        }
    }

    public final boolean Q() {
        return false;
    }

    public boolean U() {
        return this.s;
    }

    public final C0813c b() {
        C0813c cVar = this.q;
        if (cVar != null) {
            return cVar;
        }
        w0(39);
        throw null;
    }

    public final boolean b0() {
        return this.t;
    }

    public final C1327g d0() {
        h hVar = this.k;
        if (hVar != null) {
            return (C1327g) hVar.invoke();
        }
        return null;
    }

    public final I getGetter() {
        return this.f3756A;
    }

    public final C0774x getReturnType() {
        C0774x type = getType();
        if (type != null) {
            return type;
        }
        w0(23);
        throw null;
    }

    public final J getSetter() {
        return this.B;
    }

    public final List getTypeParameters() {
        ArrayList arrayList = this.z;
        if (arrayList != null) {
            return arrayList;
        }
        throw new IllegalStateException("typeParameters == null for " + this);
    }

    public final C0826p getVisibility() {
        C0826p pVar = this.n;
        if (pVar != null) {
            return pVar;
        }
        w0(25);
        throw null;
    }

    public final Collection h() {
        Collection collection = this.f3759o;
        if (collection == null) {
            collection = Collections.EMPTY_LIST;
        }
        if (collection != null) {
            return collection;
        }
        w0(41);
        throw null;
    }

    public final r h0() {
        return this.f3757C;
    }

    public final List i0() {
        List list = this.w;
        if (list != null) {
            return list;
        }
        w0(22);
        throw null;
    }

    public boolean isExternal() {
        return this.u;
    }

    public final boolean j0() {
        return this.r;
    }

    public final A k() {
        A a7 = this.m;
        if (a7 != null) {
            return a7;
        }
        w0(24);
        throw null;
    }

    public final void m0(Collection collection) {
        if (collection != null) {
            this.f3759o = collection;
        } else {
            w0(40);
            throw null;
        }
    }

    public final ArrayList o() {
        ArrayList arrayList = new ArrayList(2);
        I i2 = this.f3756A;
        if (i2 != null) {
            arrayList.add(i2);
        }
        J j2 = this.B;
        if (j2 != null) {
            arrayList.add(j2);
        }
        return arrayList;
    }

    public Object r0(C0811a aVar) {
        return null;
    }

    public final boolean u() {
        return this.v;
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.e(this, obj);
    }

    public final O c(X x9) {
        if (x9 == null) {
            w0(27);
            throw null;
        } else if (x9.f3438a.e()) {
            return this;
        } else {
            G g = new G(this);
            T f = x9.f();
            if (f != null) {
                g.g = f;
                g.e = a();
                return g.d();
            }
            G.a(15);
            throw null;
        }
    }

    public final O a() {
        O o2 = this.f3760p;
        this = this;
        if (o2 != this) {
            this = o2.a();
        }
        if (this != null) {
            return this;
        }
        w0(38);
        throw null;
    }

    public void K0(C0774x xVar) {
    }
}
