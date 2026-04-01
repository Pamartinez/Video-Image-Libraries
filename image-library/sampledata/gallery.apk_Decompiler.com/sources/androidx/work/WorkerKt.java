package androidx.work;

import Ae.a;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import m0.C0232a;
import m0.b;
import me.x;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a-\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0002¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"T", "Ljava/util/concurrent/Executor;", "Lkotlin/Function0;", "block", "Lcom/google/common/util/concurrent/ListenableFuture;", "future", "(Ljava/util/concurrent/Executor;LAe/a;)Lcom/google/common/util/concurrent/ListenableFuture;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WorkerKt {
    /* access modifiers changed from: private */
    public static final <T> ListenableFuture future(Executor executor, a aVar) {
        ListenableFuture future = CallbackToFutureAdapter.getFuture(new l6.a(1, executor, aVar));
        j.d(future, "getFuture {\n        val …        }\n        }\n    }");
        return future;
    }

    /* access modifiers changed from: private */
    public static final x future$lambda$2(Executor executor, a aVar, CallbackToFutureAdapter.Completer completer) {
        j.e(completer, "it");
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        completer.addCancellationListener(new C0232a(atomicBoolean, 1), DirectExecutor.INSTANCE);
        executor.execute(new b(atomicBoolean, completer, aVar, 1));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final void future$lambda$2$lambda$0(AtomicBoolean atomicBoolean) {
        atomicBoolean.set(true);
    }

    /* access modifiers changed from: private */
    public static final void future$lambda$2$lambda$1(AtomicBoolean atomicBoolean, CallbackToFutureAdapter.Completer completer, a aVar) {
        if (!atomicBoolean.get()) {
            try {
                completer.set(aVar.invoke());
            } catch (Throwable th) {
                completer.setException(th);
            }
        }
    }
}
