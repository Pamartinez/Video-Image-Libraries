package androidx.core.view.insets;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ SystemBarStateMonitor d;

    public /* synthetic */ b(SystemBarStateMonitor systemBarStateMonitor) {
        this.d = systemBarStateMonitor;
    }

    public final void run() {
        this.d.lambda$detachFromWindow$1();
    }
}
