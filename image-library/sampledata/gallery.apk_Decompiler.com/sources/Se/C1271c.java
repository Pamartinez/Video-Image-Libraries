package se;

import kotlin.jvm.internal.j;
import qe.C1227c;
import qe.C1228d;
import qe.C1229e;
import qe.C1230f;
import qe.C1232h;

/* renamed from: se.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1271c extends C1269a {
    private final C1232h _context;
    private transient C1227c intercepted;

    public C1271c(C1227c cVar, C1232h hVar) {
        super(cVar);
        this._context = hVar;
    }

    public C1232h getContext() {
        C1232h hVar = this._context;
        j.b(hVar);
        return hVar;
    }

    public final C1227c intercepted() {
        C1227c cVar = this.intercepted;
        if (cVar == null) {
            C1229e eVar = (C1229e) getContext().get(C1228d.d);
            if (eVar == null || (cVar = eVar.interceptContinuation(this)) == null) {
                cVar = this;
            }
            this.intercepted = cVar;
        }
        return cVar;
    }

    public void releaseIntercepted() {
        C1227c cVar = this.intercepted;
        if (!(cVar == null || cVar == this)) {
            C1230f fVar = getContext().get(C1228d.d);
            j.b(fVar);
            ((C1229e) fVar).releaseInterceptedContinuation(cVar);
        }
        this.intercepted = C1270b.d;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C1271c(C1227c cVar) {
        this(cVar, cVar != null ? cVar.getContext() : null);
    }
}
