package x0;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* renamed from: x0.C  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0321C extends FutureTask {
    public C0322D d;

    public final void done() {
        try {
            if (isCancelled()) {
                this.d = null;
                return;
            }
            this.d.d((C0320B) get());
            this.d = null;
        } catch (InterruptedException | ExecutionException e) {
            this.d.d(new C0320B(e));
        } catch (Throwable th) {
            this.d = null;
            throw th;
        }
    }
}
