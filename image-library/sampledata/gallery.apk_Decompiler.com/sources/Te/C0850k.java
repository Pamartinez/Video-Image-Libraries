package Te;

import Af.o;
import Af.p;
import Hf.C0761j;
import Hf.M;
import If.f;
import Qe.A;
import Qe.C0817g;
import Qe.C0822l;
import Qe.C0826p;
import Qe.C0827q;
import Qe.Q;
import Qe.W;
import Re.g;
import Re.h;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import qf.C1240g;

/* renamed from: Te.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0850k extends C0849j {

    /* renamed from: j  reason: collision with root package name */
    public final A f3782j;
    public final C0817g k;
    public final C0761j l;
    public p m;
    public Set n;

    /* renamed from: o  reason: collision with root package name */
    public C0848i f3783o;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0850k(C0822l lVar, C1240g gVar, A a7, C0817g gVar2, Collection collection, Gf.p pVar) {
        super(pVar, lVar, gVar, Q.f3662a);
        if (lVar == null) {
            L(0);
            throw null;
        } else if (gVar == null) {
            L(1);
            throw null;
        } else if (a7 == null) {
            L(2);
            throw null;
        } else if (gVar2 == null) {
            L(3);
            throw null;
        } else if (pVar != null) {
            this.f3782j = a7;
            this.k = gVar2;
            this.l = new C0761j(this, Collections.EMPTY_LIST, collection, pVar);
        } else {
            L(6);
            throw null;
        }
    }

    public static /* synthetic */ void L(int i2) {
        String str;
        int i7;
        Throwable th;
        switch (i2) {
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i2) {
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
                objArr[0] = "name";
                break;
            case 2:
                objArr[0] = "modality";
                break;
            case 3:
                objArr[0] = "kind";
                break;
            case 4:
                objArr[0] = "supertypes";
                break;
            case 5:
                objArr[0] = "source";
                break;
            case 6:
                objArr[0] = "storageManager";
                break;
            case 7:
                objArr[0] = "unsubstitutedMemberScope";
                break;
            case 8:
                objArr[0] = "constructors";
                break;
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorImpl";
                break;
            case 12:
                objArr[0] = "kotlinTypeRefiner";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i2) {
            case 9:
                objArr[1] = "getAnnotations";
                break;
            case 10:
                objArr[1] = "getTypeConstructor";
                break;
            case 11:
                objArr[1] = "getConstructors";
                break;
            case 13:
                objArr[1] = "getUnsubstitutedMemberScope";
                break;
            case 14:
                objArr[1] = "getStaticScope";
                break;
            case 15:
                objArr[1] = "getKind";
                break;
            case 16:
                objArr[1] = "getModality";
                break;
            case 17:
                objArr[1] = "getVisibility";
                break;
            case 18:
                objArr[1] = "getDeclaredTypeParameters";
                break;
            case 19:
                objArr[1] = "getSealedSubclasses";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorImpl";
                break;
        }
        switch (i2) {
            case 7:
            case 8:
                objArr[2] = "initialize";
                break;
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                break;
            case 12:
                objArr[2] = "getUnsubstitutedMemberScope";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public final p K(f fVar) {
        p pVar = this.m;
        if (pVar != null) {
            return pVar;
        }
        L(13);
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
        C0817g gVar = this.k;
        if (gVar != null) {
            return gVar;
        }
        L(15);
        throw null;
    }

    public final boolean b0() {
        return false;
    }

    public final p c0() {
        return o.b;
    }

    public final Collection d() {
        Set set = this.n;
        if (set != null) {
            return set;
        }
        L(11);
        throw null;
    }

    public final Collection e() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        L(19);
        throw null;
    }

    public final void e0(p pVar, Set set, C0848i iVar) {
        this.m = pVar;
        this.n = set;
        this.f3783o = iVar;
    }

    public final h getAnnotations() {
        return g.f3692a;
    }

    public final C0826p getVisibility() {
        C0826p pVar = C0827q.e;
        if (pVar != null) {
            return pVar;
        }
        L(17);
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
        L(18);
        throw null;
    }

    public final A k() {
        A a7 = this.f3782j;
        if (a7 != null) {
            return a7;
        }
        L(16);
        throw null;
    }

    public final boolean l() {
        return false;
    }

    public final M q() {
        C0761j jVar = this.l;
        if (jVar != null) {
            return jVar;
        }
        L(10);
        throw null;
    }

    public final boolean s() {
        return false;
    }

    public final boolean t0() {
        return false;
    }

    public String toString() {
        return "class " + getName();
    }

    public final C0848i y() {
        return this.f3783o;
    }
}
