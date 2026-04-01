package q2;

import android.view.View;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class g implements C0268b {
    private final u floatingGroupLayout;

    public g(u uVar) {
        this.floatingGroupLayout = uVar;
    }

    public View getReferenceView(C0267a aVar) {
        u uVar;
        j.e(aVar, "type");
        if (f.f1878a[aVar.ordinal()] != 1 || (uVar = this.floatingGroupLayout) == null) {
            return null;
        }
        return uVar.getChildAt(1);
    }
}
