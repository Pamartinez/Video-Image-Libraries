package Te;

import Bf.a;
import Bf.d;
import Bf.e;
import Hf.C0774x;
import Hf.X;
import Hf.d0;
import Qe.C0812b;
import Qe.C0816f;
import Qe.C0822l;
import Qe.C0824n;
import Qe.C0826p;
import Qe.C0827q;
import Qe.M;
import Qe.Q;
import Re.g;
import Re.h;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import qf.C1240g;
import qf.C1242i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u extends C0852m implements M {
    public final /* synthetic */ int g = 0;

    /* renamed from: h  reason: collision with root package name */
    public final C0822l f3804h;

    /* renamed from: i  reason: collision with root package name */
    public final e f3805i;

    public u(C0816f fVar) {
        super(g.f3692a, C1242i.d);
        this.f3804h = fVar;
        this.f3805i = new d(fVar);
    }

    public static /* synthetic */ void D0(int i2) {
        String str;
        int i7;
        Throwable th;
        switch (i2) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i2) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 2:
                objArr[0] = "name";
                break;
            case 3:
                objArr[0] = "substitutor";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractReceiverParameterDescriptor";
                break;
            default:
                objArr[0] = "annotations";
                break;
        }
        switch (i2) {
            case 4:
                objArr[1] = "getContextReceiverParameters";
                break;
            case 5:
                objArr[1] = "getTypeParameters";
                break;
            case 6:
                objArr[1] = "getType";
                break;
            case 7:
                objArr[1] = "getValueParameters";
                break;
            case 8:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 9:
                objArr[1] = "getVisibility";
                break;
            case 10:
                objArr[1] = "getOriginal";
                break;
            case 11:
                objArr[1] = "getSource";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractReceiverParameterDescriptor";
                break;
        }
        switch (i2) {
            case 3:
                objArr[2] = "substitute";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 1 || i2 == 2) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 1 || i2 == 2) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        if (i2 == 1 || i2 == 2) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazyClassReceiverParameterDescriptor";
        } else if (i2 != 3) {
            objArr[0] = "descriptor";
        } else {
            objArr[0] = "newOwner";
        }
        if (i2 == 1) {
            objArr[1] = "getValue";
        } else if (i2 != 2) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazyClassReceiverParameterDescriptor";
        } else {
            objArr[1] = "getContainingDeclaration";
        }
        if (!(i2 == 1 || i2 == 2)) {
            if (i2 != 3) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "copy";
            }
        }
        String format = String.format(str, objArr);
        if (i2 == 1 || i2 == 2) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public static /* synthetic */ void x0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 7 || i2 == 8) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 7 || i2 == 8) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 4:
                objArr[0] = "value";
                break;
            case 2:
            case 5:
                objArr[0] = "annotations";
                break;
            case 6:
                objArr[0] = "name";
                break;
            case 7:
            case 8:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ReceiverParameterDescriptorImpl";
                break;
            case 9:
                objArr[0] = "newOwner";
                break;
            case 10:
                objArr[0] = "outType";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i2 == 7) {
            objArr[1] = "getValue";
        } else if (i2 != 8) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ReceiverParameterDescriptorImpl";
        } else {
            objArr[1] = "getContainingDeclaration";
        }
        switch (i2) {
            case 7:
            case 8:
                break;
            case 9:
                objArr[2] = "copy";
                break;
            case 10:
                objArr[2] = "setOutType";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i2 == 7 || i2 == 8) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final List B() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        D0(7);
        throw null;
    }

    public final u E() {
        return null;
    }

    public final e E0() {
        switch (this.g) {
            case 0:
                d dVar = (d) this.f3805i;
                if (dVar != null) {
                    return dVar;
                }
                w0(1);
                throw null;
            default:
                a aVar = (a) this.f3805i;
                if (aVar != null) {
                    return aVar;
                }
                x0(7);
                throw null;
        }
    }

    /* renamed from: F0 */
    public final u c(X x9) {
        C0774x xVar;
        if (x9 != null) {
            if (!x9.f3438a.e()) {
                if (g() instanceof C0816f) {
                    xVar = x9.i(getType(), d0.OUT_VARIANCE);
                } else {
                    xVar = x9.i(getType(), d0.INVARIANT);
                }
                if (xVar == null) {
                    return null;
                }
                if (xVar != getType()) {
                    return new u(g(), new a(xVar), getAnnotations());
                }
            }
            return this;
        }
        D0(3);
        throw null;
    }

    public final u H() {
        return null;
    }

    public final boolean Z() {
        return false;
    }

    public final C0812b a() {
        return this;
    }

    public final C0822l g() {
        switch (this.g) {
            case 0:
                C0816f fVar = (C0816f) this.f3804h;
                if (fVar != null) {
                    return fVar;
                }
                w0(2);
                throw null;
            default:
                C0822l lVar = this.f3804h;
                if (lVar != null) {
                    return lVar;
                }
                x0(8);
                throw null;
        }
    }

    public final C0774x getReturnType() {
        return getType();
    }

    public final Q getSource() {
        return Q.f3662a;
    }

    public final C0774x getType() {
        C0774x type = E0().getType();
        if (type != null) {
            return type;
        }
        D0(6);
        throw null;
    }

    public final List getTypeParameters() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        D0(5);
        throw null;
    }

    public final C0826p getVisibility() {
        C0826p pVar = C0827q.f;
        if (pVar != null) {
            return pVar;
        }
        D0(9);
        throw null;
    }

    public final Collection h() {
        Set set = Collections.EMPTY_SET;
        if (set != null) {
            return set;
        }
        D0(8);
        throw null;
    }

    public String toString() {
        switch (this.g) {
            case 0:
                return "class " + ((C0816f) this.f3804h).getName() + "::this";
            default:
                return super.toString();
        }
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.l(this, obj);
    }

    /* renamed from: a  reason: collision with other method in class */
    public final C0822l m58a() {
        return this;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public u(C0822l lVar, a aVar, h hVar) {
        this(lVar, aVar, hVar, C1242i.d);
        if (lVar == null) {
            x0(0);
            throw null;
        } else if (hVar != null) {
        } else {
            x0(2);
            throw null;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public u(C0822l lVar, a aVar, h hVar, C1240g gVar) {
        super(hVar, gVar);
        if (lVar == null) {
            x0(3);
            throw null;
        } else if (hVar == null) {
            x0(5);
            throw null;
        } else if (gVar != null) {
            this.f3804h = lVar;
            this.f3805i = aVar;
        } else {
            x0(6);
            throw null;
        }
    }
}
