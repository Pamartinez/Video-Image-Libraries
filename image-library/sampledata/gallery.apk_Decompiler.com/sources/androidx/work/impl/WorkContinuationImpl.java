package androidx.work.impl;

import Sf.q;
import android.text.TextUtils;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.OperationKt;
import androidx.work.Tracer;
import androidx.work.WorkContinuation;
import androidx.work.WorkRequest;
import androidx.work.impl.utils.EnqueueRunnable;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WorkContinuationImpl extends WorkContinuation {
    private static final String TAG = Logger.tagWithPrefix("WorkContinuationImpl");
    private final List<String> mAllIds;
    private boolean mEnqueued;
    private final ExistingWorkPolicy mExistingWorkPolicy;
    private final List<String> mIds;
    private final String mName;
    private Operation mOperation;
    private final List<WorkContinuationImpl> mParents;
    private final List<? extends WorkRequest> mWork;
    private final WorkManagerImpl mWorkManagerImpl;

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl, List<? extends WorkRequest> list) {
        this(workManagerImpl, (String) null, ExistingWorkPolicy.KEEP, list, (List<WorkContinuationImpl>) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ x lambda$enqueue$0() {
        EnqueueRunnable.enqueue(this);
        return x.f4917a;
    }

    public static Set<String> prerequisitesFor(WorkContinuationImpl workContinuationImpl) {
        HashSet hashSet = new HashSet();
        List<WorkContinuationImpl> parents = workContinuationImpl.getParents();
        if (parents != null && !parents.isEmpty()) {
            for (WorkContinuationImpl ids : parents) {
                hashSet.addAll(ids.getIds());
            }
        }
        return hashSet;
    }

    public Operation enqueue() {
        if (!this.mEnqueued) {
            Tracer tracer = this.mWorkManagerImpl.getConfiguration().getTracer();
            this.mOperation = OperationKt.launchOperation(tracer, "EnqueueRunnable_" + getExistingWorkPolicy().name(), this.mWorkManagerImpl.getWorkTaskExecutor().getSerialTaskExecutor(), new q(19, this));
        } else {
            Logger logger = Logger.get();
            String str = TAG;
            logger.warning(str, "Already enqueued work ids (" + TextUtils.join(ArcCommonLog.TAG_COMMA, this.mIds) + ")");
        }
        return this.mOperation;
    }

    public ExistingWorkPolicy getExistingWorkPolicy() {
        return this.mExistingWorkPolicy;
    }

    public List<String> getIds() {
        return this.mIds;
    }

    public String getName() {
        return this.mName;
    }

    public List<WorkContinuationImpl> getParents() {
        return this.mParents;
    }

    public List<? extends WorkRequest> getWork() {
        return this.mWork;
    }

    public WorkManagerImpl getWorkManagerImpl() {
        return this.mWorkManagerImpl;
    }

    public boolean hasCycles() {
        return hasCycles(this, new HashSet());
    }

    public boolean isEnqueued() {
        return this.mEnqueued;
    }

    public void markEnqueued() {
        this.mEnqueued = true;
    }

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl, String str, ExistingWorkPolicy existingWorkPolicy, List<? extends WorkRequest> list) {
        this(workManagerImpl, str, existingWorkPolicy, list, (List<WorkContinuationImpl>) null);
    }

    private static boolean hasCycles(WorkContinuationImpl workContinuationImpl, Set<String> set) {
        set.addAll(workContinuationImpl.getIds());
        Set<String> prerequisitesFor = prerequisitesFor(workContinuationImpl);
        for (String contains : set) {
            if (prerequisitesFor.contains(contains)) {
                return true;
            }
        }
        List<WorkContinuationImpl> parents = workContinuationImpl.getParents();
        if (parents != null && !parents.isEmpty()) {
            for (WorkContinuationImpl hasCycles : parents) {
                if (hasCycles(hasCycles, set)) {
                    return true;
                }
            }
        }
        set.removeAll(workContinuationImpl.getIds());
        return false;
    }

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl, String str, ExistingWorkPolicy existingWorkPolicy, List<? extends WorkRequest> list, List<WorkContinuationImpl> list2) {
        this.mWorkManagerImpl = workManagerImpl;
        this.mName = str;
        this.mExistingWorkPolicy = existingWorkPolicy;
        this.mWork = list;
        this.mParents = list2;
        this.mIds = new ArrayList(list.size());
        this.mAllIds = new ArrayList();
        if (list2 != null) {
            for (WorkContinuationImpl workContinuationImpl : list2) {
                this.mAllIds.addAll(workContinuationImpl.mAllIds);
            }
        }
        int i2 = 0;
        while (i2 < list.size()) {
            if (existingWorkPolicy != ExistingWorkPolicy.REPLACE || ((WorkRequest) list.get(i2)).getWorkSpec().getNextScheduleTimeOverride() == Long.MAX_VALUE) {
                String stringId = ((WorkRequest) list.get(i2)).getStringId();
                this.mIds.add(stringId);
                this.mAllIds.add(stringId);
                i2++;
            } else {
                throw new IllegalArgumentException("Next Schedule Time Override must be used with ExistingPeriodicWorkPolicyUPDATE (preferably) or KEEP");
            }
        }
    }
}
