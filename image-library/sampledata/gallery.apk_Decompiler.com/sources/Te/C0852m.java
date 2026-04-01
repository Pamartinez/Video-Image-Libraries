package Te;

import Bf.a;
import Qe.C0822l;
import Re.h;
import com.samsung.android.sdk.scs.base.utils.Log;
import qf.C1240g;
import sf.C1283j;

/* renamed from: Te.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0852m extends a implements C0822l {
    public final C1240g f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0852m(h hVar, C1240g gVar) {
        super(hVar);
        if (hVar == null) {
            w0(0);
            throw null;
        } else if (gVar != null) {
            this.f = gVar;
        } else {
            w0(1);
            throw null;
        }
    }

    public static String C0(C0822l lVar) {
        try {
            String str = C1283j.e.w(lVar) + "[" + lVar.getClass().getSimpleName() + Log.TAG_SEPARATOR + Integer.toHexString(System.identityHashCode(lVar)) + "]";
            if (str != null) {
                return str;
            }
            w0(5);
            throw null;
        } catch (Throwable unused) {
            String str2 = lVar.getClass().getSimpleName() + " " + lVar.getName();
            if (str2 != null) {
                return str2;
            }
            w0(6);
            throw null;
        }
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 2 || i2 == 3 || i2 == 5 || i2 == 6) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 2 || i2 == 3 || i2 == 5 || i2 == 6) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
                objArr[0] = "name";
                break;
            case 2:
            case 3:
            case 5:
            case 6:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorImpl";
                break;
            case 4:
                objArr[0] = "descriptor";
                break;
            default:
                objArr[0] = "annotations";
                break;
        }
        if (i2 == 2) {
            objArr[1] = "getName";
        } else if (i2 == 3) {
            objArr[1] = "getOriginal";
        } else if (i2 == 5 || i2 == 6) {
            objArr[1] = "toString";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorImpl";
        }
        if (!(i2 == 2 || i2 == 3)) {
            if (i2 == 4) {
                objArr[2] = "toString";
            } else if (!(i2 == 5 || i2 == 6)) {
                objArr[2] = "<init>";
            }
        }
        String format = String.format(str, objArr);
        if (i2 == 2 || i2 == 3 || i2 == 5 || i2 == 6) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final C1240g getName() {
        C1240g gVar = this.f;
        if (gVar != null) {
            return gVar;
        }
        w0(2);
        throw null;
    }

    public String toString() {
        return C0(this);
    }

    public C0822l a() {
        return this;
    }
}
