package q2;

import Ae.a;
import android.graphics.Rect;
import java.util.List;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends k implements a {
    public final /* synthetic */ o d;
    public final /* synthetic */ k e;
    public final /* synthetic */ List f;
    public final /* synthetic */ Rect g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public m(o oVar, k kVar, List list, boolean z, Rect rect) {
        super(0);
        this.d = oVar;
        this.e = kVar;
        this.f = list;
        this.g = rect;
    }

    public final Object invoke() {
        o oVar = this.d;
        if (oVar.getSafeParentFloatingLayout() != null) {
            List list = this.f;
            Rect rect = this.g;
            k kVar = this.e;
            Rect c5 = oVar.c(kVar, list, rect);
            if (!j.a(kVar.getLastFinalRect(), c5)) {
                kVar.a(c5);
            }
        }
        return x.f4917a;
    }
}
