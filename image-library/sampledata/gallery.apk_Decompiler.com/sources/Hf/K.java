package Hf;

import Kf.d;
import Kf.e;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class K extends C0754c {
    public static final K b = new K(0);

    /* renamed from: c  reason: collision with root package name */
    public static final K f3429c = new K(1);
    public static final K d = new K(2);

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3430a;

    public /* synthetic */ K(int i2) {
        this.f3430a = i2;
    }

    public final e D(L l, d dVar) {
        switch (this.f3430a) {
            case 0:
                j.e(l, "state");
                j.e(dVar, "type");
                return l.f3432c.o0(dVar);
            case 1:
                j.e(l, "state");
                j.e(dVar, "type");
                throw new UnsupportedOperationException("Should not be called");
            default:
                j.e(l, "state");
                j.e(dVar, "type");
                return l.f3432c.f(dVar);
        }
    }
}
