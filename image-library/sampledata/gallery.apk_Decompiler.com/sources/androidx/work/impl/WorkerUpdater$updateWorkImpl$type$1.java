package androidx.work.impl;

import Ae.b;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "spec", "Landroidx/work/impl/model/WorkSpec;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkerUpdater$updateWorkImpl$type$1 extends k implements b {
    public static final WorkerUpdater$updateWorkImpl$type$1 INSTANCE = new WorkerUpdater$updateWorkImpl$type$1();

    public WorkerUpdater$updateWorkImpl$type$1() {
        super(1);
    }

    public final String invoke(WorkSpec workSpec) {
        j.e(workSpec, "spec");
        return workSpec.isPeriodic() ? "Periodic" : "OneTime";
    }
}
