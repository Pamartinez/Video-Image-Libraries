package androidx.work;

import androidx.lifecycle.LiveData;
import androidx.work.Operation;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\nR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/work/OperationImpl;", "Landroidx/work/Operation;", "Landroidx/lifecycle/LiveData;", "Landroidx/work/Operation$State;", "state", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/work/Operation$State$SUCCESS;", "future", "<init>", "(Landroidx/lifecycle/LiveData;Lcom/google/common/util/concurrent/ListenableFuture;)V", "Landroidx/lifecycle/LiveData;", "Lcom/google/common/util/concurrent/ListenableFuture;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class OperationImpl implements Operation {
    private final ListenableFuture future;
    private final LiveData<Operation.State> state;

    public OperationImpl(LiveData<Operation.State> liveData, ListenableFuture listenableFuture) {
        j.e(liveData, "state");
        j.e(listenableFuture, "future");
        this.state = liveData;
        this.future = listenableFuture;
    }
}
