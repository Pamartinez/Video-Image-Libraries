package androidx.appcompat.app;

import androidx.appcompat.app.AppCompatDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ AppCompatDelegate.SerialExecutor d;
    public final /* synthetic */ Runnable e;

    public /* synthetic */ c(AppCompatDelegate.SerialExecutor serialExecutor, Runnable runnable) {
        this.d = serialExecutor;
        this.e = runnable;
    }

    public final void run() {
        this.d.lambda$execute$0(this.e);
    }
}
