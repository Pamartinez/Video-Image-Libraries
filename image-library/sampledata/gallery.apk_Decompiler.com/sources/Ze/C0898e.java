package Ze;

import Qe.C0831v;
import Te.C0852m;
import kotlin.jvm.internal.j;
import qf.C1240g;
import xf.C1353d;

/* renamed from: Ze.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0898e extends H {
    public static final /* synthetic */ int l = 0;

    public static final C0831v a(C0831v vVar) {
        j.e(vVar, "functionDescriptor");
        C1240g name = ((C0852m) vVar).getName();
        j.d(name, "getName(...)");
        if (!b(name)) {
            return null;
        }
        return (C0831v) C1353d.b(vVar, C0897d.e);
    }

    public static boolean b(C1240g gVar) {
        j.e(gVar, "<this>");
        return H.e.contains(gVar);
    }
}
