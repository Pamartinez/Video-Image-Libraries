package Te;

import Af.o;
import Gf.i;
import Gf.n;
import Gf.p;
import Hf.B;
import Hf.C0761j;
import Hf.M;
import If.f;
import Qe.A;
import Qe.C0816f;
import Qe.C0817g;
import Qe.C0826p;
import Qe.C0827q;
import Qe.Q;
import Qe.W;
import Re.h;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import qf.C1240g;

/* renamed from: Te.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0856q extends C0849j {

    /* renamed from: j  reason: collision with root package name */
    public final C0761j f3787j;
    public final C0855p k;
    public final n l;
    public final h m;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0856q(p pVar, C0816f fVar, B b, C1240g gVar, n nVar, h hVar, Q q) {
        super(pVar, fVar, gVar, q);
        if (pVar == null) {
            L(6);
            throw null;
        } else if (fVar == null) {
            L(7);
            throw null;
        } else if (b == null) {
            L(8);
            throw null;
        } else if (gVar == null) {
            L(9);
            throw null;
        } else if (nVar != null) {
            this.m = hVar;
            this.f3787j = new C0761j(this, Collections.EMPTY_LIST, Collections.singleton(b), pVar);
            this.k = new C0855p(this, pVar);
            this.l = nVar;
        } else {
            L(10);
            throw null;
        }
    }

    public static /* synthetic */ void L(int i2) {
        String str;
        int i7;
        Throwable th;
        switch (i2) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i2) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
                objArr[0] = "enumClass";
                break;
            case 2:
            case 9:
                objArr[0] = "name";
                break;
            case 3:
            case 10:
                objArr[0] = "enumMemberNames";
                break;
            case 4:
            case 11:
                objArr[0] = "annotations";
                break;
            case 5:
            case 12:
                objArr[0] = "source";
                break;
            case 7:
                objArr[0] = "containingClass";
                break;
            case 8:
                objArr[0] = "supertype";
                break;
            case 13:
                objArr[0] = "kotlinTypeRefiner";
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        switch (i2) {
            case 14:
                objArr[1] = "getUnsubstitutedMemberScope";
                break;
            case 15:
                objArr[1] = "getStaticScope";
                break;
            case 16:
                objArr[1] = "getConstructors";
                break;
            case 17:
                objArr[1] = "getTypeConstructor";
                break;
            case 18:
                objArr[1] = "getKind";
                break;
            case 19:
                objArr[1] = "getModality";
                break;
            case 20:
                objArr[1] = "getVisibility";
                break;
            case 21:
                objArr[1] = "getAnnotations";
                break;
            case 22:
                objArr[1] = "getDeclaredTypeParameters";
                break;
            case 23:
                objArr[1] = "getSealedSubclasses";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor";
                break;
        }
        switch (i2) {
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                objArr[2] = "<init>";
                break;
            case 13:
                objArr[2] = "getUnsubstitutedMemberScope";
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                break;
            default:
                objArr[2] = "create";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public static C0856q e0(p pVar, C0816f fVar, C1240g gVar, i iVar, h hVar, Q q) {
        if (pVar == null) {
            L(0);
            throw null;
        } else if (fVar == null) {
            L(1);
            throw null;
        } else if (gVar == null) {
            L(2);
            throw null;
        } else if (iVar != null) {
            return new C0856q(pVar, fVar, fVar.i(), gVar, iVar, hVar, q);
        } else {
            L(3);
            throw null;
        }
    }

    public final Af.p K(f fVar) {
        C0855p pVar = this.k;
        if (pVar != null) {
            return pVar;
        }
        L(14);
        throw null;
    }

    public final W N() {
        return null;
    }

    public final boolean Q() {
        return false;
    }

    public final boolean T() {
        return false;
    }

    public final boolean X() {
        return false;
    }

    public final C0817g b() {
        C0817g gVar = C0817g.ENUM_ENTRY;
        if (gVar != null) {
            return gVar;
        }
        L(18);
        throw null;
    }

    public final boolean b0() {
        return false;
    }

    public final Af.p c0() {
        return o.b;
    }

    public final Collection d() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        L(16);
        throw null;
    }

    public final Collection e() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        L(23);
        throw null;
    }

    public final h getAnnotations() {
        h hVar = this.m;
        if (hVar != null) {
            return hVar;
        }
        L(21);
        throw null;
    }

    public final C0826p getVisibility() {
        C0826p pVar = C0827q.e;
        if (pVar != null) {
            return pVar;
        }
        L(20);
        throw null;
    }

    public final boolean isInline() {
        return false;
    }

    public final List j() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        L(22);
        throw null;
    }

    public final A k() {
        A a7 = A.FINAL;
        if (a7 != null) {
            return a7;
        }
        L(19);
        throw null;
    }

    public final boolean l() {
        return false;
    }

    public final M q() {
        C0761j jVar = this.f3787j;
        if (jVar != null) {
            return jVar;
        }
        L(17);
        throw null;
    }

    public final boolean s() {
        return false;
    }

    public final boolean t0() {
        return false;
    }

    public final String toString() {
        return "enum entry " + getName();
    }

    public final C0848i y() {
        return null;
    }
}
