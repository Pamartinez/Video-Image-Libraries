package androidx.work;

import A4.Q;
import Ae.a;
import Ae.c;
import Vf.C;
import Vf.C0867e0;
import Vf.C0886x;
import Vf.C0887y;
import Vf.D;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import m0.C0232a;
import m0.b;
import qe.C1227c;
import qe.C1232h;
import qe.C1233i;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aS\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\n\"\u0004\b\u0000\u0010\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\"\u0010\t\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005H\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a5\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\n\"\u0004\b\u0000\u0010\r*\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0000¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"T", "Lqe/h;", "context", "LVf/C;", "start", "Lkotlin/Function2;", "LVf/A;", "Lqe/c;", "", "block", "Lcom/google/common/util/concurrent/ListenableFuture;", "launchFuture", "(Lqe/h;LVf/C;LAe/c;)Lcom/google/common/util/concurrent/ListenableFuture;", "V", "Ljava/util/concurrent/Executor;", "", "debugTag", "Lkotlin/Function0;", "executeAsync", "(Ljava/util/concurrent/Executor;Ljava/lang/String;LAe/a;)Lcom/google/common/util/concurrent/ListenableFuture;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ListenableFutureKt {
    public static final <V> ListenableFuture executeAsync(Executor executor, String str, a aVar) {
        j.e(executor, "<this>");
        j.e(str, "debugTag");
        j.e(aVar, "block");
        ListenableFuture future = CallbackToFutureAdapter.getFuture(new Q((Object) executor, (Object) str, (Object) aVar, 22));
        j.d(future, "getFuture { completer ->… }\n        debugTag\n    }");
        return future;
    }

    /* access modifiers changed from: private */
    public static final Object executeAsync$lambda$4(Executor executor, String str, a aVar, CallbackToFutureAdapter.Completer completer) {
        j.e(completer, "completer");
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        completer.addCancellationListener(new C0232a(atomicBoolean, 0), DirectExecutor.INSTANCE);
        executor.execute(new b(atomicBoolean, completer, aVar, 0));
        return str;
    }

    /* access modifiers changed from: private */
    public static final void executeAsync$lambda$4$lambda$2(AtomicBoolean atomicBoolean) {
        atomicBoolean.set(true);
    }

    /* access modifiers changed from: private */
    public static final void executeAsync$lambda$4$lambda$3(AtomicBoolean atomicBoolean, CallbackToFutureAdapter.Completer completer, a aVar) {
        if (!atomicBoolean.get()) {
            try {
                completer.set(aVar.invoke());
            } catch (Throwable th) {
                completer.setException(th);
            }
        }
    }

    public static final <T> ListenableFuture launchFuture(C1232h hVar, C c5, c cVar) {
        j.e(hVar, "context");
        j.e(c5, "start");
        j.e(cVar, "block");
        ListenableFuture future = CallbackToFutureAdapter.getFuture(new Q((Object) hVar, (Object) c5, (Object) cVar, 21));
        j.d(future, "getFuture { completer ->…owable)\n        }\n    }\n}");
        return future;
    }

    public static /* synthetic */ ListenableFuture launchFuture$default(C1232h hVar, C c5, c cVar, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            hVar = C1233i.d;
        }
        if ((i2 & 2) != 0) {
            c5 = C.DEFAULT;
        }
        return launchFuture(hVar, c5, cVar);
    }

    /* access modifiers changed from: private */
    public static final Object launchFuture$lambda$1(C1232h hVar, C c5, c cVar, CallbackToFutureAdapter.Completer completer) {
        j.e(completer, "completer");
        completer.addCancellationListener(new k6.b(8, (C0867e0) hVar.get(C0887y.e)), DirectExecutor.INSTANCE);
        return D.n(D.a(hVar), (C0886x) null, c5, new ListenableFutureKt$launchFuture$1$2(cVar, completer, (C1227c) null), 1);
    }

    /* access modifiers changed from: private */
    public static final void launchFuture$lambda$1$lambda$0(C0867e0 e0Var) {
        if (e0Var != null) {
            e0Var.a((CancellationException) null);
        }
    }
}
