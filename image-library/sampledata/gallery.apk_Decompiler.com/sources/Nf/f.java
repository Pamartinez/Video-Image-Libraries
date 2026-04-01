package Nf;

import U2.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class f {
    public boolean d;

    public /* synthetic */ f(boolean z) {
        this.d = z;
    }

    public void c() {
        if (!this.d) {
            throw new c("immutable instance", (Exception) null);
        }
    }
}
