package hf;

import Hf.C0774x;
import Jf.l;
import Qe.C0816f;
import Qe.Q;
import Re.b;
import java.util.Map;
import qf.C1236c;
import xf.C1353d;

/* renamed from: hf.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1080b implements b {

    /* renamed from: a  reason: collision with root package name */
    public static final C1080b f4585a = new Object();

    public final Map a() {
        throw new IllegalStateException("No methods should be called on this descriptor. Only its presence matters");
    }

    public final C1236c b() {
        C0816f d = C1353d.d(this);
        if (d != null) {
            if (l.f(d)) {
                d = null;
            }
            if (d != null) {
                return C1353d.c(d);
            }
        }
        return null;
    }

    public final Q getSource() {
        throw new IllegalStateException("No methods should be called on this descriptor. Only its presence matters");
    }

    public final C0774x getType() {
        throw new IllegalStateException("No methods should be called on this descriptor. Only its presence matters");
    }

    public final String toString() {
        return "[EnhancedType]";
    }
}
