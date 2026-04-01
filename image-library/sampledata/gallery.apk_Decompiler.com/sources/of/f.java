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
public final class f extends C1262l implements y {
    public int e;
    public List f;
    public List g;

    public final C1252b a() {
        j d = d();
        d.isInitialized();
        return d;
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        j jVar;
        j jVar2 = null;
        try {
            j.k.getClass();
            e(new j(fVar, hVar));
            return this;
        } catch (u e7) {
            jVar = (j) e7.d;
            throw e7;
        } catch (Throwable th) {
            th = th;
            jVar2 = jVar;
        }
        if (jVar2 != null) {
            e(jVar2);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        e((j) qVar);
        return this;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.l, java.lang.Object, of.f] */
    public final Object clone() {
        ? lVar = new C1262l();
        List list = Collections.EMPTY_LIST;
        lVar.f = list;
        lVar.g = list;
        lVar.e(d());
        return lVar;
    }

    public final j d() {
        j jVar = new j(this);
        if ((this.e & 1) == 1) {
            this.f = Collections.unmodifiableList(this.f);
            this.e &= -2;
        }
        jVar.e = this.f;
        if ((this.e & 2) == 2) {
            this.g = Collections.unmodifiableList(this.g);
            this.e &= -3;
        }
        jVar.f = this.g;
        return jVar;
    }

    public final void e(j jVar) {
        if (jVar != j.f5005j) {
            if (!jVar.e.isEmpty()) {
                if (this.f.isEmpty()) {
                    this.f = jVar.e;
                    this.e &= -2;
                } else {
                    if ((this.e & 1) != 1) {
                        this.f = new ArrayList(this.f);
                        this.e |= 1;
                    }
                    this.f.addAll(jVar.e);
                }
            }
            if (!jVar.f.isEmpty()) {
                if (this.g.isEmpty()) {
                    this.g = jVar.f;
                    this.e &= -3;
                } else {
                    if ((this.e & 2) != 2) {
                        this.g = new ArrayList(this.g);
                        this.e |= 2;
                    }
                    this.g.addAll(jVar.f);
                }
            }
            this.d = this.d.p(jVar.d);
        }
    }
}
