package androidx.work;

import Tf.n;
import androidx.work.impl.model.WorkSpec;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;
import ne.z;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\r\b&\u0018\u0000 \u00142\u00020\u0001:\u0002\u0013\u0014B%\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\b8G¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Landroidx/work/WorkRequest;", "", "id", "Ljava/util/UUID;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "tags", "", "", "(Ljava/util/UUID;Landroidx/work/impl/model/WorkSpec;Ljava/util/Set;)V", "getId", "()Ljava/util/UUID;", "stringId", "getStringId", "()Ljava/lang/String;", "getTags", "()Ljava/util/Set;", "getWorkSpec", "()Landroidx/work/impl/model/WorkSpec;", "Builder", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WorkRequest {
    public static final Companion Companion = new Companion((e) null);
    private final UUID id;
    private final Set<String> tags;
    private final WorkSpec workSpec;

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010#\n\u0002\b\b\b&\u0018\u0000*\u0012\b\u0000\u0010\u0001*\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0000*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0004B\u0019\b\u0000\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0016¢\u0006\u0004\b\u001b\u0010\u0019J\r\u0010\u001c\u001a\u00028\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00028\u0001H ¢\u0006\u0004\b\u001e\u0010\u001dR\"\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00058\u0000X\u0004¢\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b!\u0010\"R\"\u0010$\u001a\u00020#8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\"\u0010\u000b\u001a\u00020\n8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\"\u00100\u001a\u00020/8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R \u00107\u001a\b\u0012\u0004\u0012\u00020\u0016068\u0000X\u0004¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:R\u0014\u0010=\u001a\u00028\u00008 X \u0004¢\u0006\u0006\u001a\u0004\b;\u0010<¨\u0006>"}, d2 = {"Landroidx/work/WorkRequest$Builder;", "B", "Landroidx/work/WorkRequest;", "W", "", "Ljava/lang/Class;", "Landroidx/work/ListenableWorker;", "workerClass", "<init>", "(Ljava/lang/Class;)V", "Ljava/util/UUID;", "id", "setId", "(Ljava/util/UUID;)Landroidx/work/WorkRequest$Builder;", "Landroidx/work/Constraints;", "constraints", "setConstraints", "(Landroidx/work/Constraints;)Landroidx/work/WorkRequest$Builder;", "Landroidx/work/Data;", "inputData", "setInputData", "(Landroidx/work/Data;)Landroidx/work/WorkRequest$Builder;", "", "tag", "addTag", "(Ljava/lang/String;)Landroidx/work/WorkRequest$Builder;", "traceTag", "setTraceTag", "build", "()Landroidx/work/WorkRequest;", "buildInternal$work_runtime_release", "buildInternal", "Ljava/lang/Class;", "getWorkerClass$work_runtime_release", "()Ljava/lang/Class;", "", "backoffCriteriaSet", "Z", "getBackoffCriteriaSet$work_runtime_release", "()Z", "setBackoffCriteriaSet$work_runtime_release", "(Z)V", "Ljava/util/UUID;", "getId$work_runtime_release", "()Ljava/util/UUID;", "setId$work_runtime_release", "(Ljava/util/UUID;)V", "Landroidx/work/impl/model/WorkSpec;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "getWorkSpec$work_runtime_release", "()Landroidx/work/impl/model/WorkSpec;", "setWorkSpec$work_runtime_release", "(Landroidx/work/impl/model/WorkSpec;)V", "", "tags", "Ljava/util/Set;", "getTags$work_runtime_release", "()Ljava/util/Set;", "getThisObject$work_runtime_release", "()Landroidx/work/WorkRequest$Builder;", "thisObject", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Builder<B extends Builder<B, ?>, W extends WorkRequest> {
        private boolean backoffCriteriaSet;
        private UUID id;
        private final Set<String> tags;
        private WorkSpec workSpec;
        private final Class<? extends ListenableWorker> workerClass;

        public Builder(Class<? extends ListenableWorker> cls) {
            j.e(cls, "workerClass");
            this.workerClass = cls;
            UUID randomUUID = UUID.randomUUID();
            j.d(randomUUID, "randomUUID()");
            this.id = randomUUID;
            String uuid = this.id.toString();
            j.d(uuid, "id.toString()");
            this.workSpec = new WorkSpec(uuid, cls.getName());
            LinkedHashSet linkedHashSet = new LinkedHashSet(z.Z(1));
            C1192j.w0(new String[]{cls.getName()}, linkedHashSet);
            this.tags = linkedHashSet;
        }

        public final B addTag(String str) {
            j.e(str, "tag");
            this.tags.add(str);
            return getThisObject$work_runtime_release();
        }

        public final W build() {
            boolean z;
            W buildInternal$work_runtime_release = buildInternal$work_runtime_release();
            Constraints constraints = this.workSpec.constraints;
            if (constraints.hasContentUriTriggers() || constraints.requiresBatteryNotLow() || constraints.requiresCharging() || constraints.requiresDeviceIdle()) {
                z = true;
            } else {
                z = false;
            }
            WorkSpec workSpec2 = this.workSpec;
            if (workSpec2.expedited) {
                if (z) {
                    throw new IllegalArgumentException("Expedited jobs only support network and storage constraints");
                } else if (workSpec2.initialDelay > 0) {
                    throw new IllegalArgumentException("Expedited jobs cannot be delayed");
                }
            }
            if (workSpec2.getTraceTag() == null) {
                WorkSpec workSpec3 = this.workSpec;
                workSpec3.setTraceTag(WorkRequest.Companion.deriveTraceTagFromClassName(workSpec3.workerClassName));
            }
            UUID randomUUID = UUID.randomUUID();
            j.d(randomUUID, "randomUUID()");
            setId(randomUUID);
            return buildInternal$work_runtime_release;
        }

        public abstract W buildInternal$work_runtime_release();

        public final boolean getBackoffCriteriaSet$work_runtime_release() {
            return this.backoffCriteriaSet;
        }

        public final UUID getId$work_runtime_release() {
            return this.id;
        }

        public final Set<String> getTags$work_runtime_release() {
            return this.tags;
        }

        public abstract B getThisObject$work_runtime_release();

        public final WorkSpec getWorkSpec$work_runtime_release() {
            return this.workSpec;
        }

        public final B setConstraints(Constraints constraints) {
            j.e(constraints, "constraints");
            this.workSpec.constraints = constraints;
            return getThisObject$work_runtime_release();
        }

        public final B setId(UUID uuid) {
            j.e(uuid, "id");
            this.id = uuid;
            String uuid2 = uuid.toString();
            j.d(uuid2, "id.toString()");
            this.workSpec = new WorkSpec(uuid2, this.workSpec);
            return getThisObject$work_runtime_release();
        }

        public final B setInputData(Data data) {
            j.e(data, "inputData");
            this.workSpec.input = data;
            return getThisObject$work_runtime_release();
        }

        public final B setTraceTag(String str) {
            j.e(str, "traceTag");
            this.workSpec.setTraceTag(str);
            return getThisObject$work_runtime_release();
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/work/WorkRequest$Companion;", "", "()V", "DEFAULT_BACKOFF_DELAY_MILLIS", "", "MAX_BACKOFF_MILLIS", "MAX_TRACE_SPAN_LENGTH", "", "MIN_BACKOFF_MILLIS", "deriveTraceTagFromClassName", "", "workerClassName", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final String deriveTraceTagFromClassName(String str) {
            String str2;
            List K02 = n.K0(str, new String[]{"."});
            if (K02.size() == 1) {
                str2 = (String) K02.get(0);
            } else {
                str2 = (String) C1194l.T0(K02);
            }
            int i2 = 127;
            if (str2.length() <= 127) {
                return str2;
            }
            int length = str2.length();
            if (127 > length) {
                i2 = length;
            }
            String substring = str2.substring(0, i2);
            j.d(substring, "substring(...)");
            return substring;
        }

        private Companion() {
        }
    }

    public WorkRequest(UUID uuid, WorkSpec workSpec2, Set<String> set) {
        j.e(uuid, "id");
        j.e(workSpec2, "workSpec");
        j.e(set, "tags");
        this.id = uuid;
        this.workSpec = workSpec2;
        this.tags = set;
    }

    public UUID getId() {
        return this.id;
    }

    public final String getStringId() {
        String uuid = getId().toString();
        j.d(uuid, "id.toString()");
        return uuid;
    }

    public final Set<String> getTags() {
        return this.tags;
    }

    public final WorkSpec getWorkSpec() {
        return this.workSpec;
    }
}
