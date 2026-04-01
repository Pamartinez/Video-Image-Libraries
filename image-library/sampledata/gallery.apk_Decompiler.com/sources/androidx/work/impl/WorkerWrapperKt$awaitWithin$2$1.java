package androidx.work.impl;

import Ae.b;
import androidx.work.ListenableWorker;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.k;
import me.x;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "", "it", "Lme/x;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkerWrapperKt$awaitWithin$2$1 extends k implements b {
    final /* synthetic */ ListenableFuture $this_awaitWithin;
    final /* synthetic */ ListenableWorker $worker;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WorkerWrapperKt$awaitWithin$2$1(ListenableWorker listenableWorker, ListenableFuture listenableFuture) {
        super(1);
        this.$worker = listenableWorker;
        this.$this_awaitWithin = listenableFuture;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return x.f4917a;
    }

    public final void invoke(Throwable th) {
        if (th instanceof WorkerStoppedException) {
            this.$worker.stop(((WorkerStoppedException) th).getReason());
        }
        this.$this_awaitWithin.cancel(false);
    }
}
