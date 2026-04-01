package m0;

import androidx.work.ListenableFutureKt;
import androidx.work.WorkerKt;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: m0.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0232a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AtomicBoolean e;

    public /* synthetic */ C0232a(AtomicBoolean atomicBoolean, int i2) {
        this.d = i2;
        this.e = atomicBoolean;
    }

    public final void run() {
        int i2 = this.d;
        AtomicBoolean atomicBoolean = this.e;
        switch (i2) {
            case 0:
                ListenableFutureKt.executeAsync$lambda$4$lambda$2(atomicBoolean);
                return;
            default:
                WorkerKt.future$lambda$2$lambda$0(atomicBoolean);
                return;
        }
    }
}
