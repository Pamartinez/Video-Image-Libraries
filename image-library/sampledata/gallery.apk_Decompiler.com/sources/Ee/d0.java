package ee;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d0 implements Runnable {
    public final Runnable d;
    public boolean e;
    public boolean f;

    public d0(Runnable runnable) {
        this.d = runnable;
    }

    public final void run() {
        if (!this.e) {
            this.f = true;
            this.d.run();
        }
    }
}
