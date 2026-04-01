package of;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1267q;
import rf.u;
import rf.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends C1262l implements y {
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public Object f4997h;

    /* renamed from: i  reason: collision with root package name */
    public h f4998i;

    /* renamed from: j  reason: collision with root package name */
    public List f4999j;
    public List k;

    /* JADX WARNING: type inference failed for: r0v0, types: [of.g, rf.l] */
    public static g e() {
        ? lVar = new C1262l();
        lVar.f = 1;
        lVar.f4997h = "";
        lVar.f4998i = h.NONE;
        List list = Collections.EMPTY_LIST;
        lVar.f4999j = list;
        lVar.k = list;
        return lVar;
    }

    public final C1252b a() {
        i d = d();
        d.isInitialized();
        return d;
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        i iVar;
        i iVar2 = null;
        try {
            i.q.getClass();
            f(new i(fVar));
            return this;
        } catch (u e7) {
            iVar = (i) e7.d;
            throw e7;
        } catch (Throwable th) {
            th = th;
            iVar2 = iVar;
        }
        if (iVar2 != null) {
            f(iVar2);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        f((i) qVar);
        return this;
    }

    public final Object clone() {
        g e7 = e();
        e7.f(d());
        return e7;
    }

    public final i d() {
        i iVar = new i(this);
        int i2 = this.e;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        iVar.f = this.f;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        iVar.g = this.g;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        iVar.f5001h = this.f4997h;
        if ((i2 & 8) == 8) {
            i7 |= 8;
        }
        iVar.f5002i = this.f4998i;
        if ((i2 & 16) == 16) {
            this.f4999j = Collections.unmodifiableList(this.f4999j);
            this.e &= -17;
        }
        iVar.f5003j = this.f4999j;
        if ((this.e & 32) == 32) {
            this.k = Collections.unmodifiableList(this.k);
            this.e &= -33;
        }
        iVar.l = this.k;
        iVar.e = i7;
        return iVar;
    }

    public final void f(i iVar) {
        if (iVar != i.f5000p) {
            int i2 = iVar.e;
            if ((i2 & 1) == 1) {
                int i7 = iVar.f;
                this.e = 1 | this.e;
                this.f = i7;
            }
            if ((i2 & 2) == 2) {
                int i8 = iVar.g;
                this.e = 2 | this.e;
                this.g = i8;
            }
            if ((i2 & 4) == 4) {
                this.e |= 4;
                this.f4997h = iVar.f5001h;
            }
            if ((i2 & 8) == 8) {
                h hVar = iVar.f5002i;
                hVar.getClass();
                this.e = 8 | this.e;
                this.f4998i = hVar;
            }
            if (!iVar.f5003j.isEmpty()) {
                if (this.f4999j.isEmpty()) {
                    this.f4999j = iVar.f5003j;
                    this.e &= -17;
                } else {
                    if ((this.e & 16) != 16) {
                        this.f4999j = new ArrayList(this.f4999j);
                        this.e |= 16;
                    }
                    this.f4999j.addAll(iVar.f5003j);
                }
            }
            if (!iVar.l.isEmpty()) {
                if (this.k.isEmpty()) {
                    this.k = iVar.l;
                    this.e &= -33;
                } else {
                    if ((this.e & 32) != 32) {
                        this.k = new ArrayList(this.k);
                        this.e |= 32;
                    }
                    this.k.addAll(iVar.l);
                }
            }
            this.d = this.d.p(iVar.d);
        }
    }
}
