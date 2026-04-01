package Te;

import Hf.C0774x;
import Hf.T;
import Qe.A;
import Qe.C0813c;
import Qe.C0822l;
import Qe.C0826p;
import Qe.C0830u;
import Qe.C0831v;
import Re.h;
import bf.g;
import java.util.LinkedHashMap;
import java.util.List;
import ne.C1202t;
import qf.C1240g;

/* renamed from: Te.s  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0857s implements C0830u {

    /* renamed from: A  reason: collision with root package name */
    public final /* synthetic */ t f3788A;
    public T d;
    public C0822l e;
    public A f;
    public C0826p g;

    /* renamed from: h  reason: collision with root package name */
    public C0831v f3789h;

    /* renamed from: i  reason: collision with root package name */
    public C0813c f3790i;

    /* renamed from: j  reason: collision with root package name */
    public List f3791j;
    public final List k;
    public u l;
    public u m;
    public C0774x n;

    /* renamed from: o  reason: collision with root package name */
    public C1240g f3792o;

    /* renamed from: p  reason: collision with root package name */
    public boolean f3793p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public C1202t u;
    public h v;
    public boolean w;

    /* renamed from: x  reason: collision with root package name */
    public final LinkedHashMap f3794x;
    public Boolean y;
    public boolean z;

    public C0857s(t tVar, T t3, C0822l lVar, A a7, C0826p pVar, C0813c cVar, List list, List list2, u uVar, C0774x xVar) {
        if (t3 == null) {
            c(0);
            throw null;
        } else if (lVar == null) {
            c(1);
            throw null;
        } else if (a7 == null) {
            c(2);
            throw null;
        } else if (pVar == null) {
            c(3);
            throw null;
        } else if (cVar == null) {
            c(4);
            throw null;
        } else if (list == null) {
            c(5);
            throw null;
        } else if (list2 == null) {
            c(6);
            throw null;
        } else if (xVar != null) {
            this.f3788A = tVar;
            this.f3789h = null;
            this.m = tVar.n;
            this.f3793p = true;
            this.q = false;
            this.r = false;
            this.s = false;
            this.t = tVar.w;
            this.u = null;
            this.v = null;
            this.w = tVar.f3803x;
            this.f3794x = new LinkedHashMap();
            this.y = null;
            this.z = false;
            this.d = t3;
            this.e = lVar;
            this.f = a7;
            this.g = pVar;
            this.f3790i = cVar;
            this.f3791j = list;
            this.k = list2;
            this.l = uVar;
            this.n = xVar;
            this.f3792o = null;
        } else {
            c(7);
            throw null;
        }
    }

    public static /* synthetic */ void c(int i2) {
        String str;
        int i7;
        Throwable th;
        switch (i2) {
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 40:
            case 41:
            case 42:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i2) {
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 40:
            case 41:
            case 42:
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
                objArr[0] = "newOwner";
                break;
            case 2:
                objArr[0] = "newModality";
                break;
            case 3:
                objArr[0] = "newVisibility";
                break;
            case 4:
            case 14:
                objArr[0] = "kind";
                break;
            case 5:
                objArr[0] = "newValueParameterDescriptors";
                break;
            case 6:
                objArr[0] = "newContextReceiverParameters";
                break;
            case 7:
                objArr[0] = "newReturnType";
                break;
            case 8:
                objArr[0] = "owner";
                break;
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 40:
            case 41:
            case 42:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl$CopyConfiguration";
                break;
            case 10:
                objArr[0] = "modality";
                break;
            case 12:
                objArr[0] = "visibility";
                break;
            case 17:
                objArr[0] = "name";
                break;
            case 19:
            case 21:
                objArr[0] = "parameters";
                break;
            case 23:
                objArr[0] = "type";
                break;
            case 25:
                objArr[0] = "contextReceiverParameters";
                break;
            case 35:
                objArr[0] = "additionalAnnotations";
                break;
            case 39:
                objArr[0] = "userDataKey";
                break;
            default:
                objArr[0] = "substitution";
                break;
        }
        switch (i2) {
            case 9:
                objArr[1] = "setOwner";
                break;
            case 11:
                objArr[1] = "setModality";
                break;
            case 13:
                objArr[1] = "setVisibility";
                break;
            case 15:
                objArr[1] = "setKind";
                break;
            case 16:
                objArr[1] = "setCopyOverrides";
                break;
            case 18:
                objArr[1] = "setName";
                break;
            case 20:
                objArr[1] = "setValueParameters";
                break;
            case 22:
                objArr[1] = "setTypeParameters";
                break;
            case 24:
                objArr[1] = "setReturnType";
                break;
            case 26:
                objArr[1] = "setContextReceiverParameters";
                break;
            case 27:
                objArr[1] = "setExtensionReceiverParameter";
                break;
            case 28:
                objArr[1] = "setDispatchReceiverParameter";
                break;
            case 29:
                objArr[1] = "setOriginal";
                break;
            case 30:
                objArr[1] = "setSignatureChange";
                break;
            case 31:
                objArr[1] = "setPreserveSourceElement";
                break;
            case 32:
                objArr[1] = "setDropOriginalInContainingParts";
                break;
            case 33:
                objArr[1] = "setHiddenToOvercomeSignatureClash";
                break;
            case 34:
                objArr[1] = "setHiddenForResolutionEverywhereBesideSupercalls";
                break;
            case 36:
                objArr[1] = "setAdditionalAnnotations";
                break;
            case 38:
                objArr[1] = "setSubstitution";
                break;
            case 40:
                objArr[1] = "putUserData";
                break;
            case 41:
                objArr[1] = "getSubstitution";
                break;
            case 42:
                objArr[1] = "setJustForTypeSubstitution";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl$CopyConfiguration";
                break;
        }
        switch (i2) {
            case 8:
                objArr[2] = "setOwner";
                break;
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 40:
            case 41:
            case 42:
                break;
            case 10:
                objArr[2] = "setModality";
                break;
            case 12:
                objArr[2] = "setVisibility";
                break;
            case 14:
                objArr[2] = "setKind";
                break;
            case 17:
                objArr[2] = "setName";
                break;
            case 19:
                objArr[2] = "setValueParameters";
                break;
            case 21:
                objArr[2] = "setTypeParameters";
                break;
            case 23:
                objArr[2] = "setReturnType";
                break;
            case 25:
                objArr[2] = "setContextReceiverParameters";
                break;
            case 35:
                objArr[2] = "setAdditionalAnnotations";
                break;
            case 37:
                objArr[2] = "setSubstitution";
                break;
            case 39:
                objArr[2] = "putUserData";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 9:
            case 11:
            case 13:
            case 15:
            case 16:
            case 18:
            case 20:
            case 22:
            case 24:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 38:
            case 40:
            case 41:
            case 42:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public final C0830u A(C1240g gVar) {
        if (gVar != null) {
            this.f3792o = gVar;
            return this;
        }
        c(17);
        throw null;
    }

    public final C0830u B() {
        this.f3794x.put(g.f4002K, Boolean.TRUE);
        return this;
    }

    public final C0830u C(u uVar) {
        this.m = uVar;
        return this;
    }

    public final C0830u F(C0774x xVar) {
        if (xVar != null) {
            this.n = xVar;
            return this;
        }
        c(23);
        throw null;
    }

    public final C0830u H(A a7) {
        if (a7 != null) {
            this.f = a7;
            return this;
        }
        c(10);
        throw null;
    }

    public final C0830u J() {
        this.q = true;
        return this;
    }

    public final C0830u a(List list) {
        this.f3791j = list;
        return this;
    }

    public final C0830u b(C0822l lVar) {
        if (lVar != null) {
            this.e = lVar;
            return this;
        }
        c(8);
        throw null;
    }

    public final C0831v build() {
        return this.f3788A.H0(this);
    }

    public final C0830u i() {
        this.w = true;
        return this;
    }

    public final C0830u j(h hVar) {
        if (hVar != null) {
            this.v = hVar;
            return this;
        }
        c(35);
        throw null;
    }

    public final C0830u k() {
        this.f3793p = false;
        return this;
    }

    public final C0830u m() {
        this.u = C1202t.d;
        return this;
    }

    public final C0830u q() {
        this.t = true;
        return this;
    }

    public final C0830u t(C0826p pVar) {
        if (pVar != null) {
            this.g = pVar;
            return this;
        }
        c(12);
        throw null;
    }

    public final C0830u u(C0813c cVar) {
        if (cVar != null) {
            this.f3790i = cVar;
            return this;
        }
        c(14);
        throw null;
    }

    public final C0830u z() {
        this.r = true;
        return this;
    }
}
