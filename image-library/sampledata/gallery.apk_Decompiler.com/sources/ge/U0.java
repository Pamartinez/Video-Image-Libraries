package ge;

import A0.l;
import ee.C0975h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class U0 {

    /* renamed from: a  reason: collision with root package name */
    public final E0 f4481a;
    public C0975h b;

    /* renamed from: c  reason: collision with root package name */
    public final l f4482c;
    public boolean d = false;

    public U0(E0 e02, C0975h hVar, l lVar) {
        this.f4481a = e02;
        this.b = hVar;
        this.f4482c = lVar;
    }

    public static void a(U0 u02, C0975h hVar) {
        u02.b = hVar;
        if (hVar == C0975h.READY || hVar == C0975h.TRANSIENT_FAILURE) {
            u02.d = true;
        } else if (hVar == C0975h.IDLE) {
            u02.d = false;
        }
    }
}
