package Te;

import Gf.p;
import Qe.C0822l;
import Qe.Q;
import qf.C1240g;

/* renamed from: Te.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0849j extends C0841b {

    /* renamed from: h  reason: collision with root package name */
    public final C0822l f3780h;

    /* renamed from: i  reason: collision with root package name */
    public final Q f3781i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0849j(p pVar, C0822l lVar, C1240g gVar, Q q) {
        super(pVar, gVar);
        if (pVar == null) {
            L(0);
            throw null;
        } else if (lVar == null) {
            L(1);
            throw null;
        } else if (gVar != null) {
            this.f3780h = lVar;
            this.f3781i = q;
        } else {
            L(2);
            throw null;
        }
    }

    public static /* synthetic */ void L(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 4 || i2 == 5) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 4 || i2 == 5) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        if (i2 == 1) {
            objArr[0] = "containingDeclaration";
        } else if (i2 == 2) {
            objArr[0] = "name";
        } else if (i2 == 3) {
            objArr[0] = "source";
        } else if (i2 == 4 || i2 == 5) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorBase";
        } else {
            objArr[0] = "storageManager";
        }
        if (i2 == 4) {
            objArr[1] = "getContainingDeclaration";
        } else if (i2 != 5) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorBase";
        } else {
            objArr[1] = "getSource";
        }
        if (!(i2 == 4 || i2 == 5)) {
            objArr[2] = "<init>";
        }
        String format = String.format(str, objArr);
        if (i2 == 4 || i2 == 5) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final C0822l g() {
        C0822l lVar = this.f3780h;
        if (lVar != null) {
            return lVar;
        }
        L(4);
        throw null;
    }

    public final Q getSource() {
        Q q = this.f3781i;
        if (q != null) {
            return q;
        }
        L(5);
        throw null;
    }

    public boolean isExternal() {
        return false;
    }
}
