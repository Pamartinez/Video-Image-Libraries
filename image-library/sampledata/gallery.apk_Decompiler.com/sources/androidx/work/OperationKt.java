package androidx.work;

import Ae.a;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Operation;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import m0.c;
import me.x;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a5\u0010\n\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0000¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/work/Tracer;", "tracer", "", "label", "Ljava/util/concurrent/Executor;", "executor", "Lkotlin/Function0;", "Lme/x;", "block", "Landroidx/work/Operation;", "launchOperation", "(Landroidx/work/Tracer;Ljava/lang/String;Ljava/util/concurrent/Executor;LAe/a;)Landroidx/work/Operation;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class OperationKt {
    public static final Operation launchOperation(Tracer tracer, String str, Executor executor, a aVar) {
        j.e(tracer, "tracer");
        j.e(str, "label");
        j.e(executor, "executor");
        j.e(aVar, "block");
        MutableLiveData mutableLiveData = new MutableLiveData(Operation.IN_PROGRESS);
        ListenableFuture future = CallbackToFutureAdapter.getFuture(new c(executor, tracer, str, aVar, mutableLiveData));
        j.d(future, "getFuture { completer ->…}\n            }\n        }");
        return new OperationImpl(mutableLiveData, future);
    }

    /* access modifiers changed from: private */
    public static final x launchOperation$lambda$2(Executor executor, Tracer tracer, String str, a aVar, MutableLiveData mutableLiveData, CallbackToFutureAdapter.Completer completer) {
        j.e(completer, "completer");
        executor.execute(new B5.c(tracer, str, aVar, mutableLiveData, completer, 26));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final void launchOperation$lambda$2$lambda$1(Tracer tracer, String str, a aVar, MutableLiveData mutableLiveData, CallbackToFutureAdapter.Completer completer) {
        boolean isEnabled = tracer.isEnabled();
        if (isEnabled) {
            try {
                tracer.beginSection(str);
            } catch (Throwable th) {
                if (isEnabled) {
                    tracer.endSection();
                }
                throw th;
            }
        }
        aVar.invoke();
        Operation.State.SUCCESS success = Operation.SUCCESS;
        mutableLiveData.postValue(success);
        completer.set(success);
        if (isEnabled) {
            tracer.endSection();
        }
    }
}
