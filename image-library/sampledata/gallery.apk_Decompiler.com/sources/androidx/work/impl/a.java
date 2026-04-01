package androidx.work.impl;

import androidx.work.impl.WorkerWrapper;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ WorkerWrapper.Resolution f1040a;
    public final /* synthetic */ WorkerWrapper b;

    public /* synthetic */ a(WorkerWrapper.Resolution resolution, WorkerWrapper workerWrapper) {
        this.f1040a = resolution;
        this.b = workerWrapper;
    }

    public final Object call() {
        return WorkerWrapper$launch$1.invokeSuspend$lambda$1(this.f1040a, this.b);
    }
}
