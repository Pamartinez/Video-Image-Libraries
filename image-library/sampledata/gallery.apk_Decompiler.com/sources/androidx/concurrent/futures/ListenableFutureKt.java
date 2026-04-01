package androidx.concurrent.futures;

import L1.d;
import Vf.C0875l;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import qe.C1227c;
import re.C1245a;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u001a \u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H@¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0013\u0010\u0006\u001a\u00020\u0005*\u00020\u0004H\u0000¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"T", "Lcom/google/common/util/concurrent/ListenableFuture;", "await", "(Lcom/google/common/util/concurrent/ListenableFuture;Lqe/c;)Ljava/lang/Object;", "Ljava/util/concurrent/ExecutionException;", "", "nonNullCause", "(Ljava/util/concurrent/ExecutionException;)Ljava/lang/Throwable;", "concurrent-futures-ktx"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ListenableFutureKt {
    public static final <T> Object await(ListenableFuture listenableFuture, C1227c cVar) {
        try {
            if (listenableFuture.isDone()) {
                return AbstractResolvableFuture.getUninterruptibly(listenableFuture);
            }
            C0875l lVar = new C0875l(1, d.m(cVar));
            lVar.r();
            listenableFuture.addListener(new ToContinuation(listenableFuture, lVar), DirectExecutor.INSTANCE);
            lVar.t(new ListenableFutureKt$await$2$1(listenableFuture));
            Object q = lVar.q();
            C1245a aVar = C1245a.COROUTINE_SUSPENDED;
            return q;
        } catch (ExecutionException e) {
            throw nonNullCause(e);
        }
    }

    public static final Throwable nonNullCause(ExecutionException executionException) {
        Throwable cause = executionException.getCause();
        j.b(cause);
        return cause;
    }
}
