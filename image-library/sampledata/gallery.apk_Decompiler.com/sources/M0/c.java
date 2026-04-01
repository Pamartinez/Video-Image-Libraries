package m0;

import Ae.a;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.work.OperationKt;
import androidx.work.Tracer;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ Executor d;
    public final /* synthetic */ Tracer e;
    public final /* synthetic */ String f;
    public final /* synthetic */ a g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ MutableLiveData f1838h;

    public /* synthetic */ c(Executor executor, Tracer tracer, String str, a aVar, MutableLiveData mutableLiveData) {
        this.d = executor;
        this.e = tracer;
        this.f = str;
        this.g = aVar;
        this.f1838h = mutableLiveData;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return OperationKt.launchOperation$lambda$2(this.d, this.e, this.f, this.g, this.f1838h, completer);
    }
}
