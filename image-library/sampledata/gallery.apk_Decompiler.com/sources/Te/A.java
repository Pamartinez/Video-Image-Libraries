package Te;

import Gf.p;
import Hf.C0761j;
import Hf.M;
import If.f;
import Pe.o;
import Qe.C0817g;
import Qe.C0826p;
import Qe.Q;
import Qe.W;
import Re.g;
import Re.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A extends C0849j {

    /* renamed from: j  reason: collision with root package name */
    public final C0817g f3740j;
    public Qe.A k;
    public C0826p l;
    public C0761j m;
    public ArrayList n;

    /* renamed from: o  reason: collision with root package name */
    public final ArrayList f3741o;

    /* renamed from: p  reason: collision with root package name */
    public final p f3742p;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public A(o oVar, C0817g gVar, C1240g gVar2, p pVar) {
        super(pVar, oVar, gVar2, Q.f3662a);
        if (gVar == null) {
            L(1);
            throw null;
        } else if (gVar2 == null) {
            L(2);
            throw null;
        } else if (pVar != null) {
            this.f3741o = new ArrayList();
            this.f3742p = pVar;
            this.f3740j = gVar;
        } else {
            L(4);
            throw null;
        }
    }

    public static /* synthetic */ void L(int i2) {
        String str;
        int i7;
        Throwable th;
        switch (i2) {
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
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
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
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
                objArr[0] = "kind";
                break;
            case 2:
                objArr[0] = "name";
                break;
            case 3:
                objArr[0] = "source";
                break;
            case 4:
                objArr[0] = "storageManager";
                break;
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 19:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/MutableClassDescriptor";
                break;
            case 6:
                objArr[0] = "modality";
                break;
            case 9:
                objArr[0] = "visibility";
                break;
            case 12:
                objArr[0] = "supertype";
                break;
            case 14:
                objArr[0] = "typeParameters";
                break;
            case 16:
                objArr[0] = "kotlinTypeRefiner";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i2) {
            case 5:
                objArr[1] = "getAnnotations";
                break;
            case 7:
                objArr[1] = "getModality";
                break;
            case 8:
                objArr[1] = "getKind";
                break;
            case 10:
                objArr[1] = "getVisibility";
                break;
            case 11:
                objArr[1] = "getTypeConstructor";
                break;
            case 13:
                objArr[1] = "getConstructors";
                break;
            case 15:
                objArr[1] = "getDeclaredTypeParameters";
                break;
            case 17:
                objArr[1] = "getUnsubstitutedMemberScope";
                break;
            case 18:
                objArr[1] = "getStaticScope";
                break;
            case 19:
                objArr[1] = "getSealedSubclasses";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/MutableClassDescriptor";
                break;
        }
        switch (i2) {
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
            case 17:
            case 18:
            case 19:
                break;
            case 6:
                objArr[2] = "setModality";
                break;
            case 9:
                objArr[2] = "setVisibility";
                break;
            case 12:
                objArr[2] = "addSupertype";
                break;
            case 14:
                objArr[2] = "setTypeParameterDescriptors";
                break;
            case 16:
                objArr[2] = "getUnsubstitutedMemberScope";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
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

    public final Af.p K(f fVar) {
        return Af.o.b;
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
        C0817g gVar = this.f3740j;
        if (gVar != null) {
            return gVar;
        }
        L(8);
        throw null;
    }

    public final boolean b0() {
        return false;
    }

    public final Af.p c0() {
        return Af.o.b;
    }

    public final Collection d() {
        Set set = Collections.EMPTY_SET;
        if (set != null) {
            return set;
        }
        L(13);
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

    public final h getAnnotations() {
        return g.f3692a;
    }

    public final C0826p getVisibility() {
        C0826p pVar = this.l;
        if (pVar != null) {
            return pVar;
        }
        L(10);
        throw null;
    }

    public final boolean isInline() {
        return false;
    }

    public final List j() {
        ArrayList arrayList = this.n;
        if (arrayList != null) {
            return arrayList;
        }
        L(15);
        throw null;
    }

    public final Qe.A k() {
        Qe.A a7 = this.k;
        if (a7 != null) {
            return a7;
        }
        L(7);
        throw null;
    }

    public final boolean l() {
        return false;
    }

    public final M q() {
        C0761j jVar = this.m;
        if (jVar != null) {
            return jVar;
        }
        L(11);
        throw null;
    }

    public final boolean s() {
        return false;
    }

    public final boolean t0() {
        return false;
    }

    public final String toString() {
        return C0852m.C0(this);
    }

    public final C0848i y() {
        return null;
    }
}
