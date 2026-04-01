package Te;

import Gf.p;
import Hf.d0;
import Qe.C0822l;
import Qe.S;
import Re.h;
import qf.C1240g;

/* renamed from: Te.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0842c extends C0847h {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0842c(p pVar, C0822l lVar, h hVar, C1240g gVar, d0 d0Var, boolean z, int i2, S s) {
        super(pVar, lVar, hVar, gVar, d0Var, z, i2, s);
        if (pVar == null) {
            w0(0);
            throw null;
        } else if (lVar == null) {
            w0(1);
            throw null;
        } else if (d0Var == null) {
            w0(4);
            throw null;
        } else if (s != null) {
        } else {
            w0(6);
            throw null;
        }
    }

    public static /* synthetic */ void w0(int i2) {
        Object[] objArr = new Object[3];
        switch (i2) {
            case 1:
                objArr[0] = "containingDeclaration";
                break;
            case 2:
                objArr[0] = "annotations";
                break;
            case 3:
                objArr[0] = "name";
                break;
            case 4:
                objArr[0] = "variance";
                break;
            case 5:
                objArr[0] = "source";
                break;
            case 6:
                objArr[0] = "supertypeLoopChecker";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractLazyTypeParameterDescriptor";
        objArr[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public final String toString() {
        String str;
        String str2 = "";
        if (this.f3778j) {
            str = "reified ";
        } else {
            str = str2;
        }
        if (t() != d0.INVARIANT) {
            str2 = t() + " ";
        }
        return str + str2 + getName();
    }
}
