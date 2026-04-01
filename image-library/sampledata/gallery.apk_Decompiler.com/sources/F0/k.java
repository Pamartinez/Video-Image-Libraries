package F0;

import B0.a;
import C0.f;
import E0.s;
import G0.e;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import x0.C0332j;
import x0.w;
import z0.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends c {
    public final d D;
    public final e E;

    public k(w wVar, i iVar, e eVar, C0332j jVar) {
        super(wVar, iVar);
        this.E = eVar;
        d dVar = new d(wVar, this, new s("__container", iVar.f203a, false), jVar);
        this.D = dVar;
        List list = Collections.EMPTY_LIST;
        dVar.b(list, list);
    }

    public final void e(RectF rectF, Matrix matrix, boolean z) {
        super.e(rectF, matrix, z);
        this.D.e(rectF, this.n, z);
    }

    public final void i(Canvas canvas, Matrix matrix, int i2) {
        this.D.g(canvas, matrix, i2);
    }

    public final e j() {
        e eVar = this.f189p.w;
        if (eVar != null) {
            return eVar;
        }
        return this.E.f189p.w;
    }

    public final a k() {
        a aVar = this.f189p.f210x;
        if (aVar != null) {
            return aVar;
        }
        return this.E.f189p.f210x;
    }

    public final void o(f fVar, int i2, ArrayList arrayList, f fVar2) {
        this.D.c(fVar, i2, arrayList, fVar2);
    }
}
