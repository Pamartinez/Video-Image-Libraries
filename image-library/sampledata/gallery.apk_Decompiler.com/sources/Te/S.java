package Te;

import Hf.C0774x;
import Qe.C0822l;
import Qe.Q;
import Qe.Y;
import Re.h;
import java.util.Collections;
import java.util.List;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class S extends C0853n implements Y {

    /* renamed from: i  reason: collision with root package name */
    public C0774x f3772i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public S(C0822l lVar, h hVar, C1240g gVar, C0774x xVar, Q q) {
        super(lVar, hVar, gVar, q);
        if (lVar == null) {
            w0(0);
            throw null;
        } else if (hVar == null) {
            w0(1);
            throw null;
        } else if (gVar == null) {
            w0(2);
            throw null;
        } else if (q != null) {
            this.f3772i = xVar;
        } else {
            w0(3);
            throw null;
        }
    }

    public static /* synthetic */ void w0(int i2) {
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
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
                objArr[0] = "annotations";
                break;
            case 2:
                objArr[0] = "name";
                break;
            case 3:
                objArr[0] = "source";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/VariableDescriptorImpl";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i2) {
            case 4:
                objArr[1] = "getType";
                break;
            case 5:
                objArr[1] = "getOriginal";
                break;
            case 6:
                objArr[1] = "getValueParameters";
                break;
            case 7:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 8:
                objArr[1] = "getTypeParameters";
                break;
            case 9:
                objArr[1] = "getContextReceiverParameters";
                break;
            case 10:
                objArr[1] = "getReturnType";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/VariableDescriptorImpl";
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
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public final List B() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        w0(6);
        throw null;
    }

    public u E() {
        return null;
    }

    public u H() {
        return null;
    }

    public boolean Z() {
        return false;
    }

    public C0774x getReturnType() {
        C0774x type = getType();
        if (type != null) {
            return type;
        }
        w0(10);
        throw null;
    }

    public final C0774x getType() {
        C0774x xVar = this.f3772i;
        if (xVar != null) {
            return xVar;
        }
        w0(4);
        throw null;
    }

    public List getTypeParameters() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        w0(8);
        throw null;
    }
}
