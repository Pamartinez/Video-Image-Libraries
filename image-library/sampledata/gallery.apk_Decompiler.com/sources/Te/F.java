package Te;

import Hf.X;
import Qe.A;
import Qe.C0811a;
import Qe.C0813c;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0826p;
import Qe.C0831v;
import Qe.N;
import Qe.O;
import Qe.Q;
import Re.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class F extends C0853n implements N {

    /* renamed from: i  reason: collision with root package name */
    public boolean f3747i;

    /* renamed from: j  reason: collision with root package name */
    public final boolean f3748j;
    public final A k;
    public final O l;
    public final boolean m;
    public final C0813c n;

    /* renamed from: o  reason: collision with root package name */
    public C0826p f3749o;

    /* renamed from: p  reason: collision with root package name */
    public C0831v f3750p;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public F(A a7, C0826p pVar, O o2, h hVar, C1240g gVar, boolean z, boolean z3, boolean z7, C0813c cVar, Q q) {
        super(o2.g(), hVar, gVar, q);
        if (a7 == null) {
            w0(0);
            throw null;
        } else if (pVar == null) {
            w0(1);
            throw null;
        } else if (hVar == null) {
            w0(3);
            throw null;
        } else if (q != null) {
            this.f3750p = null;
            this.k = a7;
            this.f3749o = pVar;
            this.l = o2;
            this.f3747i = z;
            this.f3748j = z3;
            this.m = z7;
            this.n = cVar;
        } else {
            w0(5);
            throw null;
        }
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        switch (i2) {
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i2) {
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
                objArr[0] = "visibility";
                break;
            case 2:
                objArr[0] = "correspondingProperty";
                break;
            case 3:
                objArr[0] = "annotations";
                break;
            case 4:
                objArr[0] = "name";
                break;
            case 5:
                objArr[0] = "source";
                break;
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyAccessorDescriptorImpl";
                break;
            case 7:
                objArr[0] = "substitutor";
                break;
            case 16:
                objArr[0] = "overriddenDescriptors";
                break;
            default:
                objArr[0] = "modality";
                break;
        }
        switch (i2) {
            case 6:
                objArr[1] = "getKind";
                break;
            case 8:
                objArr[1] = "substitute";
                break;
            case 9:
                objArr[1] = "getTypeParameters";
                break;
            case 10:
                objArr[1] = "getModality";
                break;
            case 11:
                objArr[1] = "getVisibility";
                break;
            case 12:
                objArr[1] = "getCorrespondingVariable";
                break;
            case 13:
                objArr[1] = "getCorrespondingProperty";
                break;
            case 14:
                objArr[1] = "getContextReceiverParameters";
                break;
            case 15:
                objArr[1] = "getOverriddenDescriptors";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyAccessorDescriptorImpl";
                break;
        }
        switch (i2) {
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                break;
            case 7:
                objArr[2] = "substitute";
                break;
            case 16:
                objArr[2] = "setOverriddenDescriptors";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 6:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public final u E() {
        return E0().E();
    }

    public final O E0() {
        O o2 = this.l;
        if (o2 != null) {
            return o2;
        }
        w0(13);
        throw null;
    }

    public final ArrayList F0(boolean z) {
        Object obj;
        ArrayList arrayList = new ArrayList(0);
        for (O o2 : E0().h()) {
            if (z) {
                obj = o2.getGetter();
            } else {
                obj = o2.getSetter();
            }
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final u H() {
        return E0().H();
    }

    public final boolean Q() {
        return false;
    }

    public final C0814d S(C0816f fVar, A a7, C0826p pVar, C0813c cVar) {
        throw new UnsupportedOperationException("Accessors must be copied by the corresponding property");
    }

    public final boolean Z() {
        return false;
    }

    public final C0813c b() {
        C0813c cVar = this.n;
        if (cVar != null) {
            return cVar;
        }
        w0(6);
        throw null;
    }

    public final boolean b0() {
        return false;
    }

    public final C0831v c(X x9) {
        if (x9 != null) {
            return this;
        }
        w0(7);
        throw null;
    }

    public final C0831v g0() {
        return this.f3750p;
    }

    public final List getTypeParameters() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        w0(9);
        throw null;
    }

    public final C0826p getVisibility() {
        C0826p pVar = this.f3749o;
        if (pVar != null) {
            return pVar;
        }
        w0(11);
        throw null;
    }

    public final List i0() {
        List i02 = E0().i0();
        if (i02 != null) {
            return i02;
        }
        w0(14);
        throw null;
    }

    public final boolean isExternal() {
        return this.f3748j;
    }

    public final boolean isInfix() {
        return false;
    }

    public final boolean isInline() {
        return this.m;
    }

    public final boolean isOperator() {
        return false;
    }

    public final boolean isSuspend() {
        return false;
    }

    public final A k() {
        A a7 = this.k;
        if (a7 != null) {
            return a7;
        }
        w0(10);
        throw null;
    }

    public final boolean l0() {
        return false;
    }

    public final void m0(Collection collection) {
        if (collection == null) {
            w0(16);
            throw null;
        }
    }

    public final boolean o0() {
        return false;
    }

    public final Object r0(C0811a aVar) {
        return null;
    }

    public final boolean w() {
        return false;
    }
}
