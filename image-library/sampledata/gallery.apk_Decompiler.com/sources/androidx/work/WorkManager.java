package androidx.work;

import android.content.Context;
import androidx.work.impl.WorkManagerImpl;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import o1.C0246a;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b'\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bB\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u0007\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\tH&¢\u0006\u0004\b\u0007\u0010\u000bJ'\u0010\u0011\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0010H&¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0013H&¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\fH&¢\u0006\u0004\b\u0018\u0010\u0019¨\u0006\u001c"}, d2 = {"Landroidx/work/WorkManager;", "", "<init>", "()V", "Landroidx/work/WorkRequest;", "request", "Landroidx/work/Operation;", "enqueue", "(Landroidx/work/WorkRequest;)Landroidx/work/Operation;", "", "requests", "(Ljava/util/List;)Landroidx/work/Operation;", "", "uniqueWorkName", "Landroidx/work/ExistingPeriodicWorkPolicy;", "existingPeriodicWorkPolicy", "Landroidx/work/PeriodicWorkRequest;", "enqueueUniquePeriodicWork", "(Ljava/lang/String;Landroidx/work/ExistingPeriodicWorkPolicy;Landroidx/work/PeriodicWorkRequest;)Landroidx/work/Operation;", "Ljava/util/UUID;", "id", "cancelWorkById", "(Ljava/util/UUID;)Landroidx/work/Operation;", "tag", "cancelAllWorkByTag", "(Ljava/lang/String;)Landroidx/work/Operation;", "Companion", "UpdateResult", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WorkManager {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0017¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0017¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/work/WorkManager$Companion;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Landroidx/work/WorkManager;", "getInstance", "(Landroid/content/Context;)Landroidx/work/WorkManager;", "Landroidx/work/Configuration;", "configuration", "Lme/x;", "initialize", "(Landroid/content/Context;Landroidx/work/Configuration;)V", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public WorkManager getInstance(Context context) {
            j.e(context, "context");
            WorkManagerImpl instance = WorkManagerImpl.getInstance(context);
            j.d(instance, "getInstance(context)");
            return instance;
        }

        public void initialize(Context context, Configuration configuration) {
            j.e(context, "context");
            j.e(configuration, "configuration");
            WorkManagerImpl.initialize(context, configuration);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/work/WorkManager$UpdateResult;", "", "(Ljava/lang/String;I)V", "NOT_APPLIED", "APPLIED_IMMEDIATELY", "APPLIED_FOR_NEXT_RUN", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum UpdateResult {
        NOT_APPLIED,
        APPLIED_IMMEDIATELY,
        APPLIED_FOR_NEXT_RUN
    }

    public static WorkManager getInstance(Context context) {
        return Companion.getInstance(context);
    }

    public static void initialize(Context context, Configuration configuration) {
        Companion.initialize(context, configuration);
    }

    public abstract Operation cancelAllWorkByTag(String str);

    public abstract Operation cancelWorkById(UUID uuid);

    public final Operation enqueue(WorkRequest workRequest) {
        j.e(workRequest, "request");
        return enqueue((List<? extends WorkRequest>) C0246a.e0(workRequest));
    }

    public abstract Operation enqueue(List<? extends WorkRequest> list);

    public abstract Operation enqueueUniquePeriodicWork(String str, ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy, PeriodicWorkRequest periodicWorkRequest);
}
