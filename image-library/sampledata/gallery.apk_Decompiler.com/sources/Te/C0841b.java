package Te;

import Af.u;
import Gf.h;
import Gf.i;
import Gf.m;
import Gf.p;
import Hf.B;
import Hf.T;
import Hf.X;
import If.f;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0822l;
import Qe.C0824n;
import java.util.Collections;
import java.util.List;
import qf.C1240g;
import tf.C1301e;
import xf.C1353d;

/* renamed from: Te.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0841b extends y {
    public final C1240g d;
    public final i e;
    public final i f;
    public final i g;

    /* JADX WARNING: type inference failed for: r0v2, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r0v4, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r0v6, types: [Gf.h, Gf.i] */
    public C0841b(p pVar, C1240g gVar) {
        if (pVar == null) {
            L(0);
            throw null;
        } else if (gVar != null) {
            this.d = gVar;
            m mVar = (m) pVar;
            this.e = new h(mVar, new C0840a(this, 0));
            this.f = new h(mVar, new C0840a(this, 1));
            this.g = new h(mVar, new C0840a(this, 2));
        } else {
            L(1);
            throw null;
        }
    }

    public static /* synthetic */ void L(int i2) {
        String str;
        int i7;
        Throwable th;
        int i8 = i2;
        if (i8 == 2 || i8 == 3 || i8 == 4 || i8 == 5 || i8 == 6 || i8 == 9 || i8 == 12 || i8 == 14 || i8 == 16 || i8 == 17 || i8 == 19 || i8 == 20) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i8 == 2 || i8 == 3 || i8 == 4 || i8 == 5 || i8 == 6 || i8 == 9 || i8 == 12 || i8 == 14 || i8 == 16 || i8 == 17 || i8 == 19 || i8 == 20) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i8) {
            case 1:
                objArr[0] = "name";
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            case 17:
            case 19:
            case 20:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractClassDescriptor";
                break;
            case 7:
            case 13:
                objArr[0] = "typeArguments";
                break;
            case 8:
            case 11:
                objArr[0] = "kotlinTypeRefiner";
                break;
            case 10:
            case 15:
                objArr[0] = "typeSubstitution";
                break;
            case 18:
                objArr[0] = "substitutor";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        if (i8 == 2) {
            objArr[1] = "getName";
        } else if (i8 == 3) {
            objArr[1] = "getOriginal";
        } else if (i8 == 4) {
            objArr[1] = "getUnsubstitutedInnerClassesScope";
        } else if (i8 == 5) {
            objArr[1] = "getThisAsReceiverParameter";
        } else if (i8 == 6) {
            objArr[1] = "getContextReceivers";
        } else if (i8 == 9 || i8 == 12 || i8 == 14 || i8 == 16) {
            objArr[1] = "getMemberScope";
        } else if (i8 == 17) {
            objArr[1] = "getUnsubstitutedMemberScope";
        } else if (i8 == 19) {
            objArr[1] = "substitute";
        } else if (i8 != 20) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractClassDescriptor";
        } else {
            objArr[1] = "getDefaultType";
        }
        switch (i8) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 12:
            case 14:
            case 16:
            case 17:
            case 19:
            case 20:
                break;
            case 7:
            case 8:
            case 10:
            case 11:
            case 13:
            case 15:
                objArr[2] = "getMemberScope";
                break;
            case 18:
                objArr[2] = "substitute";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i8 == 2 || i8 == 3 || i8 == 4 || i8 == 5 || i8 == 6 || i8 == 9 || i8 == 12 || i8 == 14 || i8 == 16 || i8 == 17 || i8 == 19 || i8 == 20) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public Af.p M() {
        Af.p pVar = (Af.p) this.f.invoke();
        if (pVar != null) {
            return pVar;
        }
        L(4);
        throw null;
    }

    /* renamed from: O */
    public C0816f c(X x9) {
        if (x9 == null) {
            L(18);
            throw null;
        } else if (x9.f3438a.e()) {
            return this;
        } else {
            return new x(this, x9);
        }
    }

    public Af.p P() {
        C1353d.i(C1301e.d(this));
        Af.p K6 = K(f.f3461a);
        if (K6 != null) {
            return K6;
        }
        L(17);
        throw null;
    }

    public List R() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        L(6);
        throw null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public final C0819i m53a() {
        return this;
    }

    public final Af.p a0(T t) {
        C1353d.i(C1301e.d(this));
        Af.p n = n(t, f.f3461a);
        if (n != null) {
            return n;
        }
        L(16);
        throw null;
    }

    public final C1240g getName() {
        C1240g gVar = this.d;
        if (gVar != null) {
            return gVar;
        }
        L(2);
        throw null;
    }

    public final B i() {
        B b = (B) this.e.invoke();
        if (b != null) {
            return b;
        }
        L(20);
        throw null;
    }

    public Af.p n(T t, f fVar) {
        if (t.e()) {
            Af.p K6 = K(fVar);
            if (K6 != null) {
                return K6;
            }
            L(12);
            throw null;
        }
        return new u(K(fVar), new X(t));
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.f(this, obj);
    }

    public final u v0() {
        u uVar = (u) this.g.invoke();
        if (uVar != null) {
            return uVar;
        }
        L(5);
        throw null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public final C0822l m54a() {
        return this;
    }

    public final C0816f a() {
        return this;
    }
}
