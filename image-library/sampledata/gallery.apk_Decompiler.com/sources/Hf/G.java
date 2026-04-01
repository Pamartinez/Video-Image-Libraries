package Hf;

import Af.g;
import If.f;
import L1.d;
import Qe.V;
import kotlin.jvm.internal.j;
import me.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G extends P {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3426a = 1;
    public final Object b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f3427c;

    public G(C0774x xVar, d0 d0Var) {
        if (d0Var == null) {
            e(0);
            throw null;
        } else if (xVar != null) {
            this.b = d0Var;
            this.f3427c = xVar;
        } else {
            e(1);
            throw null;
        }
    }

    public static /* synthetic */ void e(int i2) {
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
        switch (i2) {
            case 1:
            case 2:
            case 3:
                objArr[0] = "type";
                break;
            case 4:
            case 5:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/TypeProjectionImpl";
                break;
            case 6:
                objArr[0] = "kotlinTypeRefiner";
                break;
            default:
                objArr[0] = "projection";
                break;
        }
        if (i2 == 4) {
            objArr[1] = "getProjectionKind";
        } else if (i2 != 5) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/TypeProjectionImpl";
        } else {
            objArr[1] = "getType";
        }
        if (i2 == 3) {
            objArr[2] = "replaceType";
        } else if (!(i2 == 4 || i2 == 5)) {
            if (i2 != 6) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "refine";
            }
        }
        String format = String.format(str, objArr);
        if (i2 == 4 || i2 == 5) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final d0 a() {
        switch (this.f3426a) {
            case 0:
                return d0.OUT_VARIANCE;
            default:
                d0 d0Var = (d0) this.b;
                if (d0Var != null) {
                    return d0Var;
                }
                e(4);
                throw null;
        }
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [me.f, java.lang.Object] */
    public final C0774x b() {
        switch (this.f3426a) {
            case 0:
                return (C0774x) this.f3427c.getValue();
            default:
                C0774x xVar = (C0774x) this.f3427c;
                if (xVar != null) {
                    return xVar;
                }
                e(5);
                throw null;
        }
    }

    public final boolean c() {
        switch (this.f3426a) {
            case 0:
                return true;
            default:
                return false;
        }
    }

    public final P d(f fVar) {
        switch (this.f3426a) {
            case 0:
                j.e(fVar, "kotlinTypeRefiner");
                return this;
            default:
                if (fVar != null) {
                    C0774x xVar = (C0774x) this.f3427c;
                    fVar.getClass();
                    j.e(xVar, "type");
                    return new G(xVar, (d0) this.b);
                }
                e(6);
                throw null;
        }
    }

    public G(V v) {
        j.e(v, "typeParameter");
        this.b = v;
        this.f3427c = d.p(h.PUBLICATION, new g(8, this));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public G(C0774x xVar) {
        this(xVar, d0.INVARIANT);
        if (xVar != null) {
        } else {
            e(2);
            throw null;
        }
    }
}
