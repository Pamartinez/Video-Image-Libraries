package Te;

import Df.E;
import Ff.q;
import Gf.i;
import Gf.m;
import Gf.p;
import Hf.B;
import Hf.M;
import Hf.d0;
import Qe.C0819i;
import Qe.C0822l;
import Qe.C0823m;
import Qe.C0824n;
import Qe.Q;
import Qe.S;
import Qe.V;
import Re.h;
import java.util.List;
import qf.C1240g;

/* renamed from: Te.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0847h extends C0853n implements V {

    /* renamed from: i  reason: collision with root package name */
    public final d0 f3777i;

    /* renamed from: j  reason: collision with root package name */
    public final boolean f3778j;
    public final int k;
    public final i l;
    public final i m;
    public final m n;

    /* JADX WARNING: type inference failed for: r4v1, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r4v3, types: [Gf.h, Gf.i] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0847h(p pVar, C0822l lVar, h hVar, C1240g gVar, d0 d0Var, boolean z, int i2, S s) {
        super(lVar, hVar, gVar, Q.f3662a);
        if (pVar == null) {
            w0(0);
            throw null;
        } else if (lVar == null) {
            w0(1);
            throw null;
        } else if (hVar == null) {
            w0(2);
            throw null;
        } else if (gVar == null) {
            w0(3);
            throw null;
        } else if (d0Var == null) {
            w0(4);
            throw null;
        } else if (s != null) {
            this.f3777i = d0Var;
            this.f3778j = z;
            this.k = i2;
            q qVar = new q(this, pVar, s);
            m mVar = (m) pVar;
            this.l = new Gf.h(mVar, qVar);
            this.m = new Gf.h(mVar, new E((C0853n) this, (Object) gVar, 11));
            this.n = mVar;
        } else {
            w0(6);
            throw null;
        }
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        switch (i2) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i2) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
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
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor";
                break;
            case 12:
                objArr[0] = "bounds";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        switch (i2) {
            case 7:
                objArr[1] = "getVariance";
                break;
            case 8:
                objArr[1] = "getUpperBounds";
                break;
            case 9:
                objArr[1] = "getTypeConstructor";
                break;
            case 10:
                objArr[1] = "getDefaultType";
                break;
            case 11:
                objArr[1] = "getOriginal";
                break;
            case 13:
                objArr[1] = "processBoundsWithoutCycles";
                break;
            case 14:
                objArr[1] = "getStorageManager";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor";
                break;
        }
        switch (i2) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                break;
            case 12:
                objArr[2] = "processBoundsWithoutCycles";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public final p F() {
        m mVar = this.n;
        if (mVar != null) {
            return mVar;
        }
        w0(14);
        throw null;
    }

    public abstract List F0();

    public final boolean J() {
        return false;
    }

    /* renamed from: a  reason: collision with other method in class */
    public final C0819i m56a() {
        return this;
    }

    public final int getIndex() {
        return this.k;
    }

    public final List getUpperBounds() {
        List e = ((C0846g) q()).h();
        if (e != null) {
            return e;
        }
        w0(8);
        throw null;
    }

    public final B i() {
        B b = (B) this.m.invoke();
        if (b != null) {
            return b;
        }
        w0(10);
        throw null;
    }

    public final M q() {
        M m4 = (M) this.l.invoke();
        if (m4 != null) {
            return m4;
        }
        w0(9);
        throw null;
    }

    public final boolean r() {
        return this.f3778j;
    }

    public final d0 t() {
        d0 d0Var = this.f3777i;
        if (d0Var != null) {
            return d0Var;
        }
        w0(7);
        throw null;
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.v(this, obj);
    }

    /* renamed from: a  reason: collision with other method in class */
    public final C0822l m57a() {
        return this;
    }

    public final V a() {
        return this;
    }

    public final C0823m D0() {
        return this;
    }

    public List E0(List list) {
        return list;
    }
}
