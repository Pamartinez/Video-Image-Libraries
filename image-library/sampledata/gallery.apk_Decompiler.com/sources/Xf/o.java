package Xf;

import Qe.B;
import Vf.E0;
import i.C0212a;
import kotlin.jvm.internal.v;
import me.x;
import qe.C1227c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends e {
    public final a n;

    public o(int i2, a aVar) {
        super(i2);
        this.n = aVar;
        if (aVar == a.SUSPEND) {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + v.f4727a.b(e.class).o() + " instead").toString());
        } else if (i2 < 1) {
            throw new IllegalArgumentException(C0212a.j(i2, "Buffered channel capacity must be at least 1, but ", " was specified").toString());
        }
    }

    public final Object H(Object obj, boolean z) {
        E0 e02;
        a aVar = this.n;
        a aVar2 = a.DROP_LATEST;
        x xVar = x.f4917a;
        if (aVar == aVar2) {
            Object e = super.e(obj);
            if (!(e instanceof k) || (e instanceof j)) {
                return e;
            }
            return xVar;
        }
        B b = g.d;
        m mVar = (m) e.f3900i.get(this);
        while (true) {
            long andIncrement = e.e.getAndIncrement(this);
            long j2 = 1152921504606846975L & andIncrement;
            boolean t = t(andIncrement, false);
            int i2 = g.b;
            long j3 = (long) i2;
            long j8 = j2 / j3;
            int i7 = (int) (j2 % j3);
            if (mVar.f4032c != j8) {
                m b5 = e.b(this, j8, mVar);
                if (b5 != null) {
                    mVar = b5;
                } else if (t) {
                    return new j(q());
                }
            }
            int i8 = i7;
            int d = e.d(this, mVar, i8, obj, j2, b, t);
            if (d == 0) {
                mVar.b();
                return xVar;
            } else if (d == 1) {
                break;
            } else if (d != 2) {
                if (d == 3) {
                    throw new IllegalStateException("unexpected");
                } else if (d == 4) {
                    if (j2 < e.f.get(this)) {
                        mVar.b();
                    }
                    return new j(q());
                } else if (d == 5) {
                    mVar.b();
                }
            } else if (t) {
                mVar.i();
                return new j(q());
            } else {
                if (b instanceof E0) {
                    e02 = (E0) b;
                } else {
                    e02 = null;
                }
                if (e02 != null) {
                    e02.a(mVar, i8 + i2);
                }
                m((mVar.f4032c * j3) + ((long) i8));
            }
        }
        return xVar;
    }

    public final Object e(Object obj) {
        return H(obj, false);
    }

    public final Object i(Object obj, C1227c cVar) {
        if (!(H(obj, true) instanceof j)) {
            return x.f4917a;
        }
        throw q();
    }

    public final boolean v() {
        if (this.n == a.DROP_OLDEST) {
            return true;
        }
        return false;
    }
}
