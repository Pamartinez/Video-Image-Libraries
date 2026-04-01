package ge;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class E1 extends I1 {
    public final boolean c(F1 f12) {
        synchronized (f12) {
            try {
                if (f12.f != 0) {
                    return false;
                }
                f12.f = -1;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void d(F1 f12) {
        synchronized (f12) {
            f12.f = 0;
        }
    }
}
