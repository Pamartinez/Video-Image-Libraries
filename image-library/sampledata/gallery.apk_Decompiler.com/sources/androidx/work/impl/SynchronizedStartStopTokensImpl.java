package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\tH\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/work/impl/SynchronizedStartStopTokensImpl;", "Landroidx/work/impl/StartStopTokens;", "delegate", "(Landroidx/work/impl/StartStopTokens;)V", "lock", "", "contains", "", "id", "Landroidx/work/impl/model/WorkGenerationalId;", "remove", "Landroidx/work/impl/StartStopToken;", "", "workSpecId", "", "tokenFor", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SynchronizedStartStopTokensImpl implements StartStopTokens {
    private final StartStopTokens delegate;
    private final Object lock = new Object();

    public SynchronizedStartStopTokensImpl(StartStopTokens startStopTokens) {
        j.e(startStopTokens, "delegate");
        this.delegate = startStopTokens;
    }

    public boolean contains(WorkGenerationalId workGenerationalId) {
        boolean contains;
        j.e(workGenerationalId, "id");
        synchronized (this.lock) {
            contains = this.delegate.contains(workGenerationalId);
        }
        return contains;
    }

    public StartStopToken remove(WorkGenerationalId workGenerationalId) {
        StartStopToken remove;
        j.e(workGenerationalId, "id");
        synchronized (this.lock) {
            remove = this.delegate.remove(workGenerationalId);
        }
        return remove;
    }

    public StartStopToken tokenFor(WorkGenerationalId workGenerationalId) {
        StartStopToken startStopToken;
        j.e(workGenerationalId, "id");
        synchronized (this.lock) {
            startStopToken = this.delegate.tokenFor(workGenerationalId);
        }
        return startStopToken;
    }

    public List<StartStopToken> remove(String str) {
        List<StartStopToken> remove;
        j.e(str, "workSpecId");
        synchronized (this.lock) {
            remove = this.delegate.remove(str);
        }
        return remove;
    }
}
