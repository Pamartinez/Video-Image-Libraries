package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import t0.a;
import t0.b;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/work/impl/utils/IdGenerator;", "", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "(Landroidx/work/impl/WorkDatabase;)V", "nextAlarmManagerId", "", "nextJobSchedulerIdWithRange", "minInclusive", "maxInclusive", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class IdGenerator {
    private final WorkDatabase workDatabase;

    public IdGenerator(WorkDatabase workDatabase2) {
        j.e(workDatabase2, "workDatabase");
        this.workDatabase = workDatabase2;
    }

    /* access modifiers changed from: private */
    public static final Integer nextAlarmManagerId$lambda$1(IdGenerator idGenerator) {
        return Integer.valueOf(IdGeneratorKt.nextId(idGenerator.workDatabase, "next_alarm_manager_id"));
    }

    /* access modifiers changed from: private */
    public static final Integer nextJobSchedulerIdWithRange$lambda$0(IdGenerator idGenerator, int i2, int i7) {
        int access$nextId = IdGeneratorKt.nextId(idGenerator.workDatabase, "next_job_scheduler_id");
        if (i2 > access$nextId || access$nextId > i7) {
            IdGeneratorKt.updatePreference(idGenerator.workDatabase, "next_job_scheduler_id", i2 + 1);
        } else {
            i2 = access$nextId;
        }
        return Integer.valueOf(i2);
    }

    public final int nextAlarmManagerId() {
        Object runInTransaction = this.workDatabase.runInTransaction(new a(0, this));
        j.d(runInTransaction, "workDatabase.runInTransa…NAGER_ID_KEY) }\n        )");
        return ((Number) runInTransaction).intValue();
    }

    public final int nextJobSchedulerIdWithRange(int i2, int i7) {
        Object runInTransaction = this.workDatabase.runInTransaction(new b(this, i2, i7));
        j.d(runInTransaction, "workDatabase.runInTransa…d\n            }\n        )");
        return ((Number) runInTransaction).intValue();
    }
}
