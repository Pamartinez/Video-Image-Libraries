package Hf;

import Gf.p;
import Qe.C0816f;
import Qe.S;
import Te.y;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import qf.C1238e;
import tf.C1301e;

/* renamed from: Hf.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0761j extends C0753b {

    /* renamed from: c  reason: collision with root package name */
    public final y f3447c;
    public final List d;
    public final Collection e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0761j(y yVar, List list, Collection collection, p pVar) {
        super(pVar);
        if (list == null) {
            l(1);
            throw null;
        } else if (collection == null) {
            l(2);
            throw null;
        } else if (pVar != null) {
            this.f3447c = yVar;
            this.d = Collections.unmodifiableList(new ArrayList(list));
            this.e = Collections.unmodifiableCollection(collection);
        } else {
            l(3);
            throw null;
        }
    }

    public static /* synthetic */ void l(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 4 || i2 == 5 || i2 == 6 || i2 == 7) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 4 || i2 == 5 || i2 == 6 || i2 == 7) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
                objArr[0] = "parameters";
                break;
            case 2:
                objArr[0] = "supertypes";
                break;
            case 3:
                objArr[0] = "storageManager";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/ClassTypeConstructorImpl";
                break;
            default:
                objArr[0] = "classDescriptor";
                break;
        }
        if (i2 == 4) {
            objArr[1] = "getParameters";
        } else if (i2 == 5) {
            objArr[1] = "getDeclarationDescriptor";
        } else if (i2 == 6) {
            objArr[1] = "computeSupertypes";
        } else if (i2 != 7) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/ClassTypeConstructorImpl";
        } else {
            objArr[1] = "getSupertypeLoopChecker";
        }
        if (!(i2 == 4 || i2 == 5 || i2 == 6 || i2 == 7)) {
            objArr[2] = "<init>";
        }
        String format = String.format(str, objArr);
        if (i2 == 4 || i2 == 5 || i2 == 6 || i2 == 7) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final Collection b() {
        Collection collection = this.e;
        if (collection != null) {
            return collection;
        }
        l(6);
        throw null;
    }

    public final S d() {
        return S.f;
    }

    public final List getParameters() {
        List list = this.d;
        if (list != null) {
            return list;
        }
        l(4);
        throw null;
    }

    public final boolean i() {
        return true;
    }

    public final C0816f m() {
        y yVar = this.f3447c;
        if (yVar != null) {
            return yVar;
        }
        l(5);
        throw null;
    }

    public final String toString() {
        String str = C1301e.g(this.f3447c).f5037a;
        if (str != null) {
            return str;
        }
        C1238e.a(4);
        throw null;
    }
}
