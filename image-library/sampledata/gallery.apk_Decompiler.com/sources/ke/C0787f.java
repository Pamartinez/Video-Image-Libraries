package Ke;

import He.m;
import Qe.C0826p;
import Qe.C0827q;
import a.C0068a;
import java.lang.reflect.Method;
import java.util.Comparator;

/* renamed from: Ke.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0787f implements Comparator {
    public final /* synthetic */ int d;

    public /* synthetic */ C0787f(int i2) {
        this.d = i2;
    }

    public final int compare(Object obj, Object obj2) {
        switch (this.d) {
            case 0:
                return C0068a.l(((Method) obj).getName(), ((Method) obj2).getName());
            case 1:
                return C0068a.l(((X) ((m) obj)).getName(), ((X) ((m) obj2)).getName());
            default:
                Tf.m mVar = F.d;
                Integer b = C0827q.b((C0826p) obj, (C0826p) obj2);
                if (b != null) {
                    return b.intValue();
                }
                return 0;
        }
    }
}
