package n0;

import androidx.work.impl.WorkerWrapper;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1842a;
    public final /* synthetic */ WorkerWrapper b;

    public /* synthetic */ d(WorkerWrapper workerWrapper, int i2) {
        this.f1842a = i2;
        this.b = workerWrapper;
    }

    public final Object call() {
        int i2 = this.f1842a;
        WorkerWrapper workerWrapper = this.b;
        switch (i2) {
            case 0:
                return WorkerWrapper.runWorker$lambda$1(workerWrapper);
            default:
                return WorkerWrapper.trySetRunning$lambda$11(workerWrapper);
        }
    }
}
