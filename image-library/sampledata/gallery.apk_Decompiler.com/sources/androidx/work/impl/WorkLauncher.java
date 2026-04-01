package androidx.work.impl;

import androidx.work.WorkerParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0004\b\u0005\u0010\tJ\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u0006J\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH&¢\u0006\u0004\b\n\u0010\rJ\u001f\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\rø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Landroidx/work/impl/WorkLauncher;", "", "Landroidx/work/impl/StartStopToken;", "workSpecId", "Lme/x;", "startWork", "(Landroidx/work/impl/StartStopToken;)V", "Landroidx/work/WorkerParameters$RuntimeExtras;", "runtimeExtras", "(Landroidx/work/impl/StartStopToken;Landroidx/work/WorkerParameters$RuntimeExtras;)V", "stopWork", "", "reason", "(Landroidx/work/impl/StartStopToken;I)V", "stopWorkWithReason", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface WorkLauncher {
    void startWork(StartStopToken startStopToken) {
        j.e(startStopToken, "workSpecId");
        startWork(startStopToken, (WorkerParameters.RuntimeExtras) null);
    }

    void startWork(StartStopToken startStopToken, WorkerParameters.RuntimeExtras runtimeExtras);

    void stopWork(StartStopToken startStopToken) {
        j.e(startStopToken, "workSpecId");
        stopWork(startStopToken, -512);
    }

    void stopWork(StartStopToken startStopToken, int i2);

    void stopWorkWithReason(StartStopToken startStopToken, int i2) {
        j.e(startStopToken, "workSpecId");
        stopWork(startStopToken, i2);
    }
}
