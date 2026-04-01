package Sf;

import Ae.c;
import L1.d;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import se.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o implements k {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3738a;
    public final Object b;

    public /* synthetic */ o(int i2, Object obj) {
        this.f3738a = i2;
        this.b = obj;
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [Ae.c, se.h] */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.util.Iterator, Sf.l, java.lang.Object, qe.c] */
    public final Iterator iterator() {
        switch (this.f3738a) {
            case 0:
                ? obj = new Object();
                obj.g = d.f((h) this.b, obj, obj);
                return obj;
            case 1:
                return (Iterator) this.b;
            case 2:
                return new i(this);
            case 3:
                return j.g((Object[]) this.b);
            default:
                return ((Iterable) this.b).iterator();
        }
    }

    public o(c cVar) {
        this.f3738a = 0;
        this.b = (h) cVar;
    }
}
