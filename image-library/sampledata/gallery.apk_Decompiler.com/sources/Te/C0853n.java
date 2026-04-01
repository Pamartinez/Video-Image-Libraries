package Te;

import Qe.C0822l;
import Qe.C0823m;
import Qe.Q;
import Re.h;
import qf.C1240g;

/* renamed from: Te.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0853n extends C0852m implements C0823m {
    public final C0822l g;

    /* renamed from: h  reason: collision with root package name */
    public final Q f3785h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0853n(C0822l lVar, h hVar, C1240g gVar, Q q) {
        super(hVar, gVar);
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
            this.g = lVar;
            this.f3785h = q;
        } else {
            w0(3);
            throw null;
        }
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 4 || i2 == 5 || i2 == 6) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 4 || i2 == 5 || i2 == 6) {
            i7 = 2;
        } else {
            i7 = 3;
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
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorNonRootImpl";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i2 == 4) {
            objArr[1] = "getOriginal";
        } else if (i2 == 5) {
            objArr[1] = "getContainingDeclaration";
        } else if (i2 != 6) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorNonRootImpl";
        } else {
            objArr[1] = "getSource";
        }
        if (!(i2 == 4 || i2 == 5 || i2 == 6)) {
            objArr[2] = "<init>";
        }
        String format = String.format(str, objArr);
        if (i2 == 4 || i2 == 5 || i2 == 6) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public C0822l g() {
        C0822l lVar = this.g;
        if (lVar != null) {
            return lVar;
        }
        w0(5);
        throw null;
    }

    public Q getSource() {
        Q q = this.f3785h;
        if (q != null) {
            return q;
        }
        w0(6);
        throw null;
    }

    /* renamed from: D0 */
    public C0823m a() {
        return this;
    }
}
