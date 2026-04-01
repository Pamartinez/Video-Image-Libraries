package androidx.concurrent.futures;

import L2.a;
import Vf.C0873j;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/concurrent/futures/ToContinuation;", "T", "Ljava/lang/Runnable;", "Lcom/google/common/util/concurrent/ListenableFuture;", "futureToObserve", "LVf/j;", "continuation", "<init>", "(Lcom/google/common/util/concurrent/ListenableFuture;LVf/j;)V", "Lme/x;", "run", "()V", "Lcom/google/common/util/concurrent/ListenableFuture;", "getFutureToObserve", "()Lcom/google/common/util/concurrent/ListenableFuture;", "LVf/j;", "getContinuation", "()LVf/j;", "concurrent-futures-ktx"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ToContinuation<T> implements Runnable {
    private final C0873j continuation;
    private final ListenableFuture futureToObserve;

    public ToContinuation(ListenableFuture listenableFuture, C0873j jVar) {
        this.futureToObserve = listenableFuture;
        this.continuation = jVar;
    }

    public void run() {
        if (this.futureToObserve.isCancelled()) {
            this.continuation.d((Throwable) null);
            return;
        }
        try {
            this.continuation.resumeWith(AbstractResolvableFuture.getUninterruptibly(this.futureToObserve));
        } catch (ExecutionException e) {
            this.continuation.resumeWith(a.l(ListenableFutureKt.nonNullCause(e)));
        }
    }
}
