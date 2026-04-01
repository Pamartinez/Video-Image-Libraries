package androidx.work.impl;

import L1.d;
import Vf.C0875l;
import androidx.work.DirectExecutor;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import qe.C1227c;
import re.C1245a;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a(\u0010\u0004\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u0002H@¢\u0006\u0004\b\u0004\u0010\u0005\u001a#\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0002¢\u0006\u0004\b\t\u0010\n\u001a\u0013\u0010\r\u001a\u00020\f*\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000e\"\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"T", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/work/ListenableWorker;", "worker", "awaitWithin", "(Lcom/google/common/util/concurrent/ListenableFuture;Landroidx/work/ListenableWorker;Lqe/c;)Ljava/lang/Object;", "V", "Ljava/util/concurrent/Future;", "future", "getUninterruptibly", "(Ljava/util/concurrent/Future;)Ljava/lang/Object;", "Ljava/util/concurrent/ExecutionException;", "", "nonNullCause", "(Ljava/util/concurrent/ExecutionException;)Ljava/lang/Throwable;", "", "TAG", "Ljava/lang/String;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WorkerWrapperKt {
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("WorkerWrapper");
        j.d(tagWithPrefix, "tagWithPrefix(\"WorkerWrapper\")");
        TAG = tagWithPrefix;
    }

    public static final <T> Object awaitWithin(ListenableFuture listenableFuture, ListenableWorker listenableWorker, C1227c cVar) {
        try {
            if (listenableFuture.isDone()) {
                return getUninterruptibly(listenableFuture);
            }
            C0875l lVar = new C0875l(1, d.m(cVar));
            lVar.r();
            listenableFuture.addListener(new ToContinuation(listenableFuture, lVar), DirectExecutor.INSTANCE);
            lVar.t(new WorkerWrapperKt$awaitWithin$2$1(listenableWorker, listenableFuture));
            Object q = lVar.q();
            C1245a aVar = C1245a.COROUTINE_SUSPENDED;
            return q;
        } catch (ExecutionException e) {
            throw nonNullCause(e);
        }
    }

    /* access modifiers changed from: private */
    public static final <V> V getUninterruptibly(Future<V> future) {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    /* access modifiers changed from: private */
    public static final Throwable nonNullCause(ExecutionException executionException) {
        Throwable cause = executionException.getCause();
        j.b(cause);
        return cause;
    }
}
