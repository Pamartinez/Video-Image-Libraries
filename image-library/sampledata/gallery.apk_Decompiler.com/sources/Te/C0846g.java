package Te;

import Gf.p;
import Hf.C0758g;
import Hf.C0774x;
import Jf.k;
import Jf.l;
import Ne.i;
import Qe.C0819i;
import Qe.S;
import Qe.V;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import tf.C1297a;
import tf.C1299c;
import xf.C1353d;

/* renamed from: Te.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0846g extends C0758g {

    /* renamed from: c  reason: collision with root package name */
    public final S f3776c;
    public final /* synthetic */ C0847h d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0846g(C0847h hVar, p pVar, S s) {
        super(pVar);
        if (pVar != null) {
            this.d = hVar;
            this.f3776c = s;
            return;
        }
        l(0);
        throw null;
    }

    public static /* synthetic */ void l(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5 || i2 == 8) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5 || i2 == 8) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor$TypeParameterTypeConstructor";
                break;
            case 6:
                objArr[0] = "type";
                break;
            case 7:
                objArr[0] = "supertypes";
                break;
            case 9:
                objArr[0] = "classifier";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        if (i2 == 1) {
            objArr[1] = "computeSupertypes";
        } else if (i2 == 2) {
            objArr[1] = "getParameters";
        } else if (i2 == 3) {
            objArr[1] = "getDeclarationDescriptor";
        } else if (i2 == 4) {
            objArr[1] = "getBuiltIns";
        } else if (i2 == 5) {
            objArr[1] = "getSupertypeLoopChecker";
        } else if (i2 != 8) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor$TypeParameterTypeConstructor";
        } else {
            objArr[1] = "processSupertypesWithoutCycles";
        }
        switch (i2) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
                break;
            case 6:
                objArr[2] = "reportSupertypeLoopError";
                break;
            case 7:
                objArr[2] = "processSupertypesWithoutCycles";
                break;
            case 9:
                objArr[2] = "isSameClassifier";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5 || i2 == 8) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final Collection b() {
        List F02 = this.d.F0();
        if (F02 != null) {
            return F02;
        }
        l(1);
        throw null;
    }

    public final C0774x c() {
        return l.c(k.CYCLIC_UPPER_BOUNDS, new String[0]);
    }

    public final S d() {
        S s = this.f3776c;
        if (s != null) {
            return s;
        }
        l(5);
        throw null;
    }

    public final i f() {
        i e = C1353d.e(this.d);
        if (e != null) {
            return e;
        }
        l(4);
        throw null;
    }

    public final C0819i g() {
        C0847h hVar = this.d;
        if (hVar != null) {
            return hVar;
        }
        l(3);
        throw null;
    }

    public final List getParameters() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        l(2);
        throw null;
    }

    public final boolean i() {
        return true;
    }

    public final boolean j(C0819i iVar) {
        if (!(iVar instanceof V)) {
            return false;
        }
        C1297a aVar = C1297a.d;
        if (C1299c.d.d(this.d, (V) iVar, true, aVar)) {
            return true;
        }
        return false;
    }

    public final List k(List list) {
        List E02 = this.d.E0(list);
        if (E02 != null) {
            return E02;
        }
        l(8);
        throw null;
    }

    public final String toString() {
        return this.d.getName().d;
    }
}
