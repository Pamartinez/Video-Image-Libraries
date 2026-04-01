package m0;

import Ae.a;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.work.ListenableFutureKt;
import androidx.work.WorkerKt;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AtomicBoolean e;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f;
    public final /* synthetic */ a g;

    public /* synthetic */ b(AtomicBoolean atomicBoolean, CallbackToFutureAdapter.Completer completer, a aVar, int i2) {
        this.d = i2;
        this.e = atomicBoolean;
        this.f = completer;
        this.g = aVar;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ListenableFutureKt.executeAsync$lambda$4$lambda$3(this.e, this.f, this.g);
                return;
            default:
                WorkerKt.future$lambda$2$lambda$1(this.e, this.f, this.g);
                return;
        }
    }
}
