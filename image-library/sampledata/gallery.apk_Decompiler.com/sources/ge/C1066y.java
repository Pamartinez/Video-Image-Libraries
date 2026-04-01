package ge;

import He.F;
import ee.C0975h;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: ge.y  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1066y {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList f4574a;
    public volatile C0975h b;

    public final void a(C0975h hVar) {
        F.n(hVar, "newState");
        if (this.b != hVar && this.b != C0975h.SHUTDOWN) {
            this.b = hVar;
            if (!this.f4574a.isEmpty()) {
                ArrayList arrayList = this.f4574a;
                this.f4574a = new ArrayList();
                Iterator it = arrayList.iterator();
                if (it.hasNext()) {
                    throw C0212a.h(it);
                }
            }
        }
    }
}
