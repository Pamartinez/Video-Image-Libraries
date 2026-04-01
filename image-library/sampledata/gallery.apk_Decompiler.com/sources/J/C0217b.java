package j;

import androidx.arch.core.executor.ArchTaskExecutor;
import java.util.concurrent.Executor;

/* renamed from: j.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0217b implements Executor {
    public final /* synthetic */ int d;

    public /* synthetic */ C0217b(int i2) {
        this.d = i2;
    }

    public final void execute(Runnable runnable) {
        switch (this.d) {
            case 0:
                runnable.run();
                return;
            case 1:
                ArchTaskExecutor.getInstance().postToMainThread(runnable);
                return;
            default:
                ArchTaskExecutor.getInstance().executeOnDiskIO(runnable);
                return;
        }
    }
}
