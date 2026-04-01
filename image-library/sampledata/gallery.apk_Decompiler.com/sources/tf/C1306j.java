package tf;

import Qe.C0816f;
import Qe.C0821k;
import Qe.C0822l;
import Qe.C0831v;
import Qe.O;
import Qe.U;
import a.C0068a;
import java.util.Comparator;
import xf.C1353d;

/* renamed from: tf.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1306j implements Comparator {
    public static final C1306j e = new C1306j(0);
    public final /* synthetic */ int d;

    public /* synthetic */ C1306j(int i2) {
        this.d = i2;
    }

    public static int a(C0822l lVar) {
        if (C1301e.m(lVar)) {
            return 8;
        }
        if (lVar instanceof C0821k) {
            return 7;
        }
        if (lVar instanceof O) {
            if (((O) lVar).H() == null) {
                return 6;
            }
            return 5;
        } else if (lVar instanceof C0831v) {
            if (((C0831v) lVar).H() == null) {
                return 4;
            }
            return 3;
        } else if (lVar instanceof C0816f) {
            return 2;
        } else {
            if (lVar instanceof U) {
                return 1;
            }
            return 0;
        }
    }

    public final int compare(Object obj, Object obj2) {
        Integer num;
        switch (this.d) {
            case 0:
                C0822l lVar = (C0822l) obj;
                C0822l lVar2 = (C0822l) obj2;
                int a7 = a(lVar2) - a(lVar);
                if (a7 != 0) {
                    num = Integer.valueOf(a7);
                } else if (!C1301e.m(lVar) || !C1301e.m(lVar2)) {
                    int compareTo = lVar.getName().d.compareTo(lVar2.getName().d);
                    if (compareTo != 0) {
                        num = Integer.valueOf(compareTo);
                    } else {
                        num = null;
                    }
                } else {
                    num = 0;
                }
                if (num != null) {
                    return num.intValue();
                }
                return 0;
            default:
                return C0068a.l(C1353d.g((C0816f) obj).b(), C1353d.g((C0816f) obj2).b());
        }
    }
}
