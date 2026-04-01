package N2;

import R2.l;
import R2.p;
import S2.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends l {
    public final a f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f425h;

    public f(i iVar, p pVar, l lVar, a aVar) {
        super(iVar, pVar, lVar);
        if (aVar != null) {
            this.f = aVar;
            this.g = -1;
            this.f425h = -1;
            return;
        }
        throw new NullPointerException("constant == null");
    }

    public final String a() {
        return this.f.a();
    }

    public final g g(i iVar) {
        f fVar = new f(iVar, this.f427c, this.d, this.f);
        int i2 = this.g;
        if (i2 >= 0) {
            fVar.n(i2);
        }
        int i7 = this.f425h;
        if (i7 >= 0) {
            fVar.m(i7);
        }
        return fVar;
    }

    public final g i(l lVar) {
        f fVar = new f(this.b, this.f427c, lVar, this.f);
        int i2 = this.g;
        if (i2 >= 0) {
            fVar.n(i2);
        }
        int i7 = this.f425h;
        if (i7 >= 0) {
            fVar.m(i7);
        }
        return fVar;
    }

    public final int l() {
        int i2 = this.g;
        if (i2 >= 0) {
            return i2;
        }
        throw new RuntimeException("index not yet set for " + this.f);
    }

    public final void m(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("index < 0");
        } else if (this.f425h < 0) {
            this.f425h = i2;
        } else {
            throw new RuntimeException("class index already set");
        }
    }

    public final void n(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("index < 0");
        } else if (this.g < 0) {
            this.g = i2;
        } else {
            throw new RuntimeException("index already set");
        }
    }
}
