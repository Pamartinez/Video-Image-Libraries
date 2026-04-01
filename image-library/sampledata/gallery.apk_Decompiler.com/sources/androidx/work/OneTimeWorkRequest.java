package androidx.work;

import androidx.work.WorkRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00062\u00020\u0001:\u0002\u0005\u0006B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0007"}, d2 = {"Landroidx/work/OneTimeWorkRequest;", "Landroidx/work/WorkRequest;", "builder", "Landroidx/work/OneTimeWorkRequest$Builder;", "(Landroidx/work/OneTimeWorkRequest$Builder;)V", "Builder", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OneTimeWorkRequest extends WorkRequest {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\n\u001a\u00020\u0002H\u0010¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\r\u001a\u00020\u00008PX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Landroidx/work/OneTimeWorkRequest$Builder;", "Landroidx/work/WorkRequest$Builder;", "Landroidx/work/OneTimeWorkRequest;", "Ljava/lang/Class;", "Landroidx/work/ListenableWorker;", "workerClass", "<init>", "(Ljava/lang/Class;)V", "buildInternal$work_runtime_release", "()Landroidx/work/OneTimeWorkRequest;", "buildInternal", "getThisObject$work_runtime_release", "()Landroidx/work/OneTimeWorkRequest$Builder;", "thisObject", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends WorkRequest.Builder<Builder, OneTimeWorkRequest> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Builder(Class<? extends ListenableWorker> cls) {
            super(cls);
            j.e(cls, "workerClass");
        }

        public Builder getThisObject$work_runtime_release() {
            return this;
        }

        public OneTimeWorkRequest buildInternal$work_runtime_release() {
            if (!getBackoffCriteriaSet$work_runtime_release() || !getWorkSpec$work_runtime_release().constraints.requiresDeviceIdle()) {
                return new OneTimeWorkRequest(this);
            }
            throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/work/OneTimeWorkRequest$Companion;", "", "<init>", "()V", "Ljava/lang/Class;", "Landroidx/work/ListenableWorker;", "workerClass", "Landroidx/work/OneTimeWorkRequest;", "from", "(Ljava/lang/Class;)Landroidx/work/OneTimeWorkRequest;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final OneTimeWorkRequest from(Class<? extends ListenableWorker> cls) {
            j.e(cls, "workerClass");
            return (OneTimeWorkRequest) new Builder(cls).build();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OneTimeWorkRequest(Builder builder) {
        super(builder.getId$work_runtime_release(), builder.getWorkSpec$work_runtime_release(), builder.getTags$work_runtime_release());
        j.e(builder, "builder");
    }

    public static final OneTimeWorkRequest from(Class<? extends ListenableWorker> cls) {
        return Companion.from(cls);
    }
}
