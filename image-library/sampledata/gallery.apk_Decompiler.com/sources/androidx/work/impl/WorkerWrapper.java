package androidx.work.impl;

import Ae.b;
import Vf.C;
import Vf.C0881s;
import Vf.g0;
import Vf.n0;
import android.content.Context;
import androidx.work.Clock;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.ListenableFutureKt;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkInfo$State;
import androidx.work.WorkerParameters;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import n0.d;
import ne.C1194l;
import ne.C1195m;
import ne.C1200r;
import qe.C1227c;

@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0002TUB\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006H@¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u0012\u0010\rJ\u000f\u0010\u0013\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u001a\u0010\u0011J\u000f\u0010\u001b\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001b\u0010\u0014J\u0017\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001c\u0010\rJ\u001d\u0010\u001f\u001a\u00020\u00152\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00150\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 J\u0013\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0!¢\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b&\u0010\rR\u0017\u0010(\u001a\u00020'8\u0006¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020,8\u0002X\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010/R\u0014\u00101\u001a\u0002008\u0002X\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u0016\u00104\u001a\u0004\u0018\u0001038\u0002X\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010=\u001a\u00020<8\u0002X\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020?8\u0002X\u0004¢\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010C\u001a\u00020B8\u0002X\u0004¢\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010F\u001a\u00020E8\u0002X\u0004¢\u0006\u0006\n\u0004\bF\u0010GR\u0014\u0010I\u001a\u00020H8\u0002X\u0004¢\u0006\u0006\n\u0004\bI\u0010JR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00150\u001d8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010KR\u0014\u0010L\u001a\u00020\u00158\u0002X\u0004¢\u0006\u0006\n\u0004\bL\u0010/R\u0014\u0010N\u001a\u00020M8\u0002X\u0004¢\u0006\u0006\n\u0004\bN\u0010OR\u0011\u0010S\u001a\u00020P8F¢\u0006\u0006\u001a\u0004\bQ\u0010R¨\u0006V"}, d2 = {"Landroidx/work/impl/WorkerWrapper;", "", "Landroidx/work/impl/WorkerWrapper$Builder;", "builder", "<init>", "(Landroidx/work/impl/WorkerWrapper$Builder;)V", "Landroidx/work/impl/WorkerWrapper$Resolution;", "runWorker", "(Lqe/c;)Ljava/lang/Object;", "Landroidx/work/ListenableWorker$Result;", "result", "", "onWorkFinished", "(Landroidx/work/ListenableWorker$Result;)Z", "", "stopReason", "resetWorkerStatus", "(I)Z", "handleResult", "trySetRunning", "()Z", "", "workSpecId", "Lme/x;", "iterativelyFailWorkAndDependents", "(Ljava/lang/String;)V", "reschedule", "resetPeriodic", "setSucceeded", "", "tags", "createWorkDescription", "(Ljava/util/List;)Ljava/lang/String;", "Lcom/google/common/util/concurrent/ListenableFuture;", "launch", "()Lcom/google/common/util/concurrent/ListenableFuture;", "interrupt", "(I)V", "setFailed", "Landroidx/work/impl/model/WorkSpec;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "getWorkSpec", "()Landroidx/work/impl/model/WorkSpec;", "Landroid/content/Context;", "appContext", "Landroid/content/Context;", "Ljava/lang/String;", "Landroidx/work/WorkerParameters$RuntimeExtras;", "runtimeExtras", "Landroidx/work/WorkerParameters$RuntimeExtras;", "Landroidx/work/ListenableWorker;", "builderWorker", "Landroidx/work/ListenableWorker;", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "workTaskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "Landroidx/work/Configuration;", "configuration", "Landroidx/work/Configuration;", "Landroidx/work/Clock;", "clock", "Landroidx/work/Clock;", "Landroidx/work/impl/foreground/ForegroundProcessor;", "foregroundProcessor", "Landroidx/work/impl/foreground/ForegroundProcessor;", "Landroidx/work/impl/WorkDatabase;", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "Landroidx/work/impl/model/WorkSpecDao;", "workSpecDao", "Landroidx/work/impl/model/WorkSpecDao;", "Landroidx/work/impl/model/DependencyDao;", "dependencyDao", "Landroidx/work/impl/model/DependencyDao;", "Ljava/util/List;", "workDescription", "LVf/s;", "workerJob", "LVf/s;", "Landroidx/work/impl/model/WorkGenerationalId;", "getWorkGenerationalId", "()Landroidx/work/impl/model/WorkGenerationalId;", "workGenerationalId", "Builder", "Resolution", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkerWrapper {
    /* access modifiers changed from: private */
    public final Context appContext;
    private final ListenableWorker builderWorker;
    private final Clock clock;
    /* access modifiers changed from: private */
    public final Configuration configuration;
    private final DependencyDao dependencyDao;
    private final ForegroundProcessor foregroundProcessor;
    private final WorkerParameters.RuntimeExtras runtimeExtras;
    private final List<String> tags;
    /* access modifiers changed from: private */
    public final WorkDatabase workDatabase;
    private final String workDescription;
    private final WorkSpec workSpec;
    private final WorkSpecDao workSpecDao;
    private final String workSpecId;
    /* access modifiers changed from: private */
    public final TaskExecutor workTaskExecutor;
    /* access modifiers changed from: private */
    public final C0881s workerJob = new g0();

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001BG\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010#\u001a\u0004\b$\u0010%R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010&\u001a\u0004\b'\u0010(R\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010)\u001a\u0004\b*\u0010+R\u0017\u0010,\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R$\u00101\u001a\u0004\u0018\u0001008\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0014\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;¨\u0006<"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Builder;", "", "Landroid/content/Context;", "context", "Landroidx/work/Configuration;", "configuration", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "workTaskExecutor", "Landroidx/work/impl/foreground/ForegroundProcessor;", "foregroundProcessor", "Landroidx/work/impl/WorkDatabase;", "workDatabase", "Landroidx/work/impl/model/WorkSpec;", "workSpec", "", "", "tags", "<init>", "(Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/foreground/ForegroundProcessor;Landroidx/work/impl/WorkDatabase;Landroidx/work/impl/model/WorkSpec;Ljava/util/List;)V", "Landroidx/work/WorkerParameters$RuntimeExtras;", "runtimeExtras", "withRuntimeExtras", "(Landroidx/work/WorkerParameters$RuntimeExtras;)Landroidx/work/impl/WorkerWrapper$Builder;", "Landroidx/work/impl/WorkerWrapper;", "build", "()Landroidx/work/impl/WorkerWrapper;", "Landroidx/work/Configuration;", "getConfiguration", "()Landroidx/work/Configuration;", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "getWorkTaskExecutor", "()Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "Landroidx/work/impl/foreground/ForegroundProcessor;", "getForegroundProcessor", "()Landroidx/work/impl/foreground/ForegroundProcessor;", "Landroidx/work/impl/WorkDatabase;", "getWorkDatabase", "()Landroidx/work/impl/WorkDatabase;", "Landroidx/work/impl/model/WorkSpec;", "getWorkSpec", "()Landroidx/work/impl/model/WorkSpec;", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "Landroidx/work/ListenableWorker;", "worker", "Landroidx/work/ListenableWorker;", "getWorker", "()Landroidx/work/ListenableWorker;", "setWorker", "(Landroidx/work/ListenableWorker;)V", "Landroidx/work/WorkerParameters$RuntimeExtras;", "getRuntimeExtras", "()Landroidx/work/WorkerParameters$RuntimeExtras;", "setRuntimeExtras", "(Landroidx/work/WorkerParameters$RuntimeExtras;)V", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private final Context appContext;
        private final Configuration configuration;
        private final ForegroundProcessor foregroundProcessor;
        private WorkerParameters.RuntimeExtras runtimeExtras = new WorkerParameters.RuntimeExtras();
        private final List<String> tags;
        private final WorkDatabase workDatabase;
        private final WorkSpec workSpec;
        private final TaskExecutor workTaskExecutor;
        private ListenableWorker worker;

        public Builder(Context context, Configuration configuration2, TaskExecutor taskExecutor, ForegroundProcessor foregroundProcessor2, WorkDatabase workDatabase2, WorkSpec workSpec2, List<String> list) {
            j.e(context, "context");
            j.e(configuration2, "configuration");
            j.e(taskExecutor, "workTaskExecutor");
            j.e(foregroundProcessor2, "foregroundProcessor");
            j.e(workDatabase2, "workDatabase");
            j.e(workSpec2, "workSpec");
            j.e(list, "tags");
            this.configuration = configuration2;
            this.workTaskExecutor = taskExecutor;
            this.foregroundProcessor = foregroundProcessor2;
            this.workDatabase = workDatabase2;
            this.workSpec = workSpec2;
            this.tags = list;
            Context applicationContext = context.getApplicationContext();
            j.d(applicationContext, "context.applicationContext");
            this.appContext = applicationContext;
        }

        public final WorkerWrapper build() {
            return new WorkerWrapper(this);
        }

        public final Context getAppContext() {
            return this.appContext;
        }

        public final Configuration getConfiguration() {
            return this.configuration;
        }

        public final ForegroundProcessor getForegroundProcessor() {
            return this.foregroundProcessor;
        }

        public final WorkerParameters.RuntimeExtras getRuntimeExtras() {
            return this.runtimeExtras;
        }

        public final List<String> getTags() {
            return this.tags;
        }

        public final WorkDatabase getWorkDatabase() {
            return this.workDatabase;
        }

        public final WorkSpec getWorkSpec() {
            return this.workSpec;
        }

        public final TaskExecutor getWorkTaskExecutor() {
            return this.workTaskExecutor;
        }

        public final ListenableWorker getWorker() {
            return this.worker;
        }

        public final Builder withRuntimeExtras(WorkerParameters.RuntimeExtras runtimeExtras2) {
            if (runtimeExtras2 != null) {
                this.runtimeExtras = runtimeExtras2;
            }
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution;", "", "()V", "Failed", "Finished", "ResetWorkerStatus", "Landroidx/work/impl/WorkerWrapper$Resolution$Failed;", "Landroidx/work/impl/WorkerWrapper$Resolution$Finished;", "Landroidx/work/impl/WorkerWrapper$Resolution$ResetWorkerStatus;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Resolution {

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution$Failed;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "result", "Landroidx/work/ListenableWorker$Result;", "(Landroidx/work/ListenableWorker$Result;)V", "getResult", "()Landroidx/work/ListenableWorker$Result;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Failed extends Resolution {
            private final ListenableWorker.Result result;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Failed(ListenableWorker.Result result2) {
                super((e) null);
                j.e(result2, "result");
                this.result = result2;
            }

            public final ListenableWorker.Result getResult() {
                return this.result;
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Failed(ListenableWorker.Result result2, int i2, e eVar) {
                this((i2 & 1) != 0 ? new ListenableWorker.Result.Failure() : result2);
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution$Finished;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "result", "Landroidx/work/ListenableWorker$Result;", "(Landroidx/work/ListenableWorker$Result;)V", "getResult", "()Landroidx/work/ListenableWorker$Result;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Finished extends Resolution {
            private final ListenableWorker.Result result;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Finished(ListenableWorker.Result result2) {
                super((e) null);
                j.e(result2, "result");
                this.result = result2;
            }

            public final ListenableWorker.Result getResult() {
                return this.result;
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution$ResetWorkerStatus;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "reason", "", "(I)V", "getReason", "()I", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class ResetWorkerStatus extends Resolution {
            private final int reason;

            public ResetWorkerStatus(int i2) {
                super((e) null);
                this.reason = i2;
            }

            public final int getReason() {
                return this.reason;
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ ResetWorkerStatus(int i2, int i7, e eVar) {
                this((i7 & 1) != 0 ? -256 : i2);
            }
        }

        public /* synthetic */ Resolution(e eVar) {
            this();
        }

        private Resolution() {
        }
    }

    public WorkerWrapper(Builder builder) {
        j.e(builder, "builder");
        WorkSpec workSpec2 = builder.getWorkSpec();
        this.workSpec = workSpec2;
        this.appContext = builder.getAppContext();
        this.workSpecId = workSpec2.id;
        this.runtimeExtras = builder.getRuntimeExtras();
        this.builderWorker = builder.getWorker();
        this.workTaskExecutor = builder.getWorkTaskExecutor();
        Configuration configuration2 = builder.getConfiguration();
        this.configuration = configuration2;
        this.clock = configuration2.getClock();
        this.foregroundProcessor = builder.getForegroundProcessor();
        WorkDatabase workDatabase2 = builder.getWorkDatabase();
        this.workDatabase = workDatabase2;
        this.workSpecDao = workDatabase2.workSpecDao();
        this.dependencyDao = workDatabase2.dependencyDao();
        List<String> tags2 = builder.getTags();
        this.tags = tags2;
        this.workDescription = createWorkDescription(tags2);
    }

    private final String createWorkDescription(List<String> list) {
        StringBuilder sb2 = new StringBuilder("Work [ id=");
        sb2.append(this.workSpecId);
        sb2.append(", tags={ ");
        return C0212a.p(sb2, C1194l.R0(list, GlobalPostProcInternalPPInterface.SPLIT_REGEX, (String) null, (String) null, (b) null, 62), " } ]");
    }

    private final boolean handleResult(ListenableWorker.Result result) {
        if (result instanceof ListenableWorker.Result.Success) {
            String access$getTAG$p = WorkerWrapperKt.TAG;
            Logger logger = Logger.get();
            logger.info(access$getTAG$p, "Worker result SUCCESS for " + this.workDescription);
            if (this.workSpec.isPeriodic()) {
                return resetPeriodic();
            }
            return setSucceeded(result);
        } else if (result instanceof ListenableWorker.Result.Retry) {
            String access$getTAG$p2 = WorkerWrapperKt.TAG;
            Logger logger2 = Logger.get();
            logger2.info(access$getTAG$p2, "Worker result RETRY for " + this.workDescription);
            return reschedule(-256);
        } else {
            String access$getTAG$p3 = WorkerWrapperKt.TAG;
            Logger logger3 = Logger.get();
            logger3.info(access$getTAG$p3, "Worker result FAILURE for " + this.workDescription);
            if (this.workSpec.isPeriodic()) {
                return resetPeriodic();
            }
            if (result == null) {
                result = new ListenableWorker.Result.Failure();
            }
            return setFailed(result);
        }
    }

    private final void iterativelyFailWorkAndDependents(String str) {
        ArrayList s0 = C1195m.s0(str);
        while (!s0.isEmpty()) {
            String str2 = (String) C1200r.C0(s0);
            if (this.workSpecDao.getState(str2) != WorkInfo$State.CANCELLED) {
                this.workSpecDao.setState(WorkInfo$State.FAILED, str2);
            }
            s0.addAll(this.dependencyDao.getDependentWorkIds(str2));
        }
    }

    /* access modifiers changed from: private */
    public final boolean onWorkFinished(ListenableWorker.Result result) {
        WorkInfo$State state = this.workSpecDao.getState(this.workSpecId);
        this.workDatabase.workProgressDao().delete(this.workSpecId);
        if (state == null) {
            return false;
        }
        if (state == WorkInfo$State.RUNNING) {
            return handleResult(result);
        }
        if (!state.isFinished()) {
            return reschedule(-512);
        }
        return false;
    }

    private final boolean reschedule(int i2) {
        this.workSpecDao.setState(WorkInfo$State.ENQUEUED, this.workSpecId);
        this.workSpecDao.setLastEnqueueTime(this.workSpecId, this.clock.currentTimeMillis());
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.getNextScheduleTimeOverrideGeneration());
        this.workSpecDao.markWorkSpecScheduled(this.workSpecId, -1);
        this.workSpecDao.setStopReason(this.workSpecId, i2);
        return true;
    }

    private final boolean resetPeriodic() {
        this.workSpecDao.setLastEnqueueTime(this.workSpecId, this.clock.currentTimeMillis());
        this.workSpecDao.setState(WorkInfo$State.ENQUEUED, this.workSpecId);
        this.workSpecDao.resetWorkSpecRunAttemptCount(this.workSpecId);
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.getNextScheduleTimeOverrideGeneration());
        this.workSpecDao.incrementPeriodCount(this.workSpecId);
        this.workSpecDao.markWorkSpecScheduled(this.workSpecId, -1);
        return false;
    }

    /* access modifiers changed from: private */
    public final boolean resetWorkerStatus(int i2) {
        WorkInfo$State state = this.workSpecDao.getState(this.workSpecId);
        if (state == null || state.isFinished()) {
            String access$getTAG$p = WorkerWrapperKt.TAG;
            Logger logger = Logger.get();
            logger.debug(access$getTAG$p, "Status for " + this.workSpecId + " is " + state + " ; not doing any work");
            return false;
        }
        String access$getTAG$p2 = WorkerWrapperKt.TAG;
        Logger logger2 = Logger.get();
        logger2.debug(access$getTAG$p2, "Status for " + this.workSpecId + " is " + state + "; not doing any work and rescheduling for later execution");
        this.workSpecDao.setState(WorkInfo$State.ENQUEUED, this.workSpecId);
        this.workSpecDao.setStopReason(this.workSpecId, i2);
        this.workSpecDao.markWorkSpecScheduled(this.workSpecId, -1);
        return true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object runWorker(qe.C1227c r23) {
        /*
            r22 = this;
            r1 = r22
            r0 = r23
            boolean r2 = r0 instanceof androidx.work.impl.WorkerWrapper$runWorker$1
            if (r2 == 0) goto L_0x0017
            r2 = r0
            androidx.work.impl.WorkerWrapper$runWorker$1 r2 = (androidx.work.impl.WorkerWrapper$runWorker$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0017
            int r3 = r3 - r4
            r2.label = r3
            goto L_0x001c
        L_0x0017:
            androidx.work.impl.WorkerWrapper$runWorker$1 r2 = new androidx.work.impl.WorkerWrapper$runWorker$1
            r2.<init>(r1, r0)
        L_0x001c:
            java.lang.Object r0 = r2.result
            re.a r3 = re.C1245a.COROUTINE_SUSPENDED
            int r4 = r2.label
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x0048
            if (r4 != r5) goto L_0x0040
            java.lang.Object r1 = r2.L$1
            androidx.work.WorkerParameters r1 = (androidx.work.WorkerParameters) r1
            java.lang.Object r2 = r2.L$0
            androidx.work.impl.WorkerWrapper r2 = (androidx.work.impl.WorkerWrapper) r2
            L2.a.A(r0)     // Catch:{ CancellationException -> 0x003c, all -> 0x0037 }
            r9 = r1
            r1 = r2
            goto L_0x01f3
        L_0x0037:
            r0 = move-exception
            r9 = r1
            r1 = r2
            goto L_0x0204
        L_0x003c:
            r0 = move-exception
            r1 = r2
            goto L_0x023c
        L_0x0040:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0048:
            L2.a.A(r0)
            androidx.work.Configuration r0 = r1.configuration
            androidx.work.Tracer r0 = r0.getTracer()
            boolean r0 = r0.isEnabled()
            androidx.work.impl.model.WorkSpec r4 = r1.workSpec
            java.lang.String r4 = r4.getTraceTag()
            if (r0 == 0) goto L_0x006e
            if (r4 == 0) goto L_0x006e
            androidx.work.Configuration r7 = r1.configuration
            androidx.work.Tracer r7 = r7.getTracer()
            androidx.work.impl.model.WorkSpec r8 = r1.workSpec
            int r8 = r8.hashCode()
            r7.beginAsyncSection(r4, r8)
        L_0x006e:
            androidx.work.impl.WorkDatabase r7 = r1.workDatabase
            n0.d r8 = new n0.d
            r9 = 0
            r8.<init>(r1, r9)
            java.lang.Object r7 = r7.runInTransaction(r8)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            java.lang.String r8 = "shouldExit"
            kotlin.jvm.internal.j.d(r7, r8)
            boolean r7 = r7.booleanValue()
            r8 = 0
            if (r7 == 0) goto L_0x008e
            androidx.work.impl.WorkerWrapper$Resolution$ResetWorkerStatus r0 = new androidx.work.impl.WorkerWrapper$Resolution$ResetWorkerStatus
            r0.<init>(r8, r5, r6)
            return r0
        L_0x008e:
            androidx.work.impl.model.WorkSpec r7 = r1.workSpec
            boolean r7 = r7.isPeriodic()
            if (r7 == 0) goto L_0x009c
            androidx.work.impl.model.WorkSpec r7 = r1.workSpec
            androidx.work.Data r7 = r7.input
        L_0x009a:
            r11 = r7
            goto L_0x00ec
        L_0x009c:
            androidx.work.Configuration r7 = r1.configuration
            androidx.work.InputMergerFactory r7 = r7.getInputMergerFactory()
            androidx.work.impl.model.WorkSpec r9 = r1.workSpec
            java.lang.String r9 = r9.inputMergerClassName
            androidx.work.InputMerger r7 = r7.createInputMergerWithDefaultFallback(r9)
            if (r7 != 0) goto L_0x00cf
            java.lang.String r0 = androidx.work.impl.WorkerWrapperKt.TAG
            androidx.work.Logger r2 = androidx.work.Logger.get()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Could not create Input Merger "
            r3.<init>(r4)
            androidx.work.impl.model.WorkSpec r1 = r1.workSpec
            java.lang.String r1 = r1.inputMergerClassName
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.error(r0, r1)
            androidx.work.impl.WorkerWrapper$Resolution$Failed r0 = new androidx.work.impl.WorkerWrapper$Resolution$Failed
            r0.<init>(r6, r5, r6)
            return r0
        L_0x00cf:
            androidx.work.impl.model.WorkSpec r9 = r1.workSpec
            androidx.work.Data r9 = r9.input
            java.util.List r9 = o1.C0246a.e0(r9)
            java.util.Collection r9 = (java.util.Collection) r9
            androidx.work.impl.model.WorkSpecDao r10 = r1.workSpecDao
            java.lang.String r11 = r1.workSpecId
            java.util.List r10 = r10.getInputsFromPrerequisites(r11)
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.ArrayList r9 = ne.C1194l.X0(r10, r9)
            androidx.work.Data r7 = r7.merge(r9)
            goto L_0x009a
        L_0x00ec:
            androidx.work.WorkerParameters r9 = new androidx.work.WorkerParameters
            java.lang.String r7 = r1.workSpecId
            java.util.UUID r10 = java.util.UUID.fromString(r7)
            java.util.List<java.lang.String> r7 = r1.tags
            r12 = r7
            java.util.Collection r12 = (java.util.Collection) r12
            androidx.work.WorkerParameters$RuntimeExtras r13 = r1.runtimeExtras
            androidx.work.impl.model.WorkSpec r7 = r1.workSpec
            int r14 = r7.runAttemptCount
            int r15 = r7.getGeneration()
            androidx.work.Configuration r7 = r1.configuration
            java.util.concurrent.Executor r16 = r7.getExecutor()
            androidx.work.Configuration r7 = r1.configuration
            qe.h r17 = r7.getWorkerCoroutineContext()
            androidx.work.impl.utils.taskexecutor.TaskExecutor r7 = r1.workTaskExecutor
            androidx.work.Configuration r8 = r1.configuration
            androidx.work.WorkerFactory r19 = r8.getWorkerFactory()
            androidx.work.impl.utils.WorkProgressUpdater r8 = new androidx.work.impl.utils.WorkProgressUpdater
            androidx.work.impl.WorkDatabase r5 = r1.workDatabase
            androidx.work.impl.utils.taskexecutor.TaskExecutor r6 = r1.workTaskExecutor
            r8.<init>(r5, r6)
            androidx.work.impl.utils.WorkForegroundUpdater r5 = new androidx.work.impl.utils.WorkForegroundUpdater
            androidx.work.impl.WorkDatabase r6 = r1.workDatabase
            r18 = r7
            androidx.work.impl.foreground.ForegroundProcessor r7 = r1.foregroundProcessor
            r20 = r8
            androidx.work.impl.utils.taskexecutor.TaskExecutor r8 = r1.workTaskExecutor
            r5.<init>(r6, r7, r8)
            r21 = r5
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            androidx.work.ListenableWorker r5 = r1.builderWorker
            if (r5 != 0) goto L_0x0187
            androidx.work.Configuration r5 = r1.configuration     // Catch:{ all -> 0x0149 }
            androidx.work.WorkerFactory r5 = r5.getWorkerFactory()     // Catch:{ all -> 0x0149 }
            android.content.Context r6 = r1.appContext     // Catch:{ all -> 0x0149 }
            androidx.work.impl.model.WorkSpec r7 = r1.workSpec     // Catch:{ all -> 0x0149 }
            java.lang.String r7 = r7.workerClassName     // Catch:{ all -> 0x0149 }
            androidx.work.ListenableWorker r5 = r5.createWorkerWithDefaultFallback(r6, r7, r9)     // Catch:{ all -> 0x0149 }
            goto L_0x0187
        L_0x0149:
            r0 = move-exception
            java.lang.String r2 = androidx.work.impl.WorkerWrapperKt.TAG
            androidx.work.Logger r3 = androidx.work.Logger.get()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Could not create Worker "
            r4.<init>(r5)
            androidx.work.impl.model.WorkSpec r5 = r1.workSpec
            java.lang.String r5 = r5.workerClassName
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.error(r2, r4)
            androidx.work.Configuration r2 = r1.configuration
            androidx.core.util.Consumer r2 = r2.getWorkerInitializationExceptionHandler()
            if (r2 == 0) goto L_0x017f
            androidx.work.WorkerExceptionInfo r3 = new androidx.work.WorkerExceptionInfo
            androidx.work.impl.model.WorkSpec r1 = r1.workSpec
            java.lang.String r1 = r1.workerClassName
            r3.<init>(r1, r9, r0)
            java.lang.String r0 = androidx.work.impl.WorkerWrapperKt.TAG
            androidx.work.impl.utils.WorkerExceptionUtilsKt.safeAccept(r2, r3, r0)
        L_0x017f:
            androidx.work.impl.WorkerWrapper$Resolution$Failed r0 = new androidx.work.impl.WorkerWrapper$Resolution$Failed
            r1 = 1
            r2 = 0
            r0.<init>(r2, r1, r2)
            return r0
        L_0x0187:
            r5.setUsed()
            qe.h r6 = r2.getContext()
            Vf.y r7 = Vf.C0887y.e
            qe.f r6 = r6.get(r7)
            kotlin.jvm.internal.j.b(r6)
            Vf.e0 r6 = (Vf.C0867e0) r6
            androidx.work.impl.WorkerWrapper$runWorker$2 r7 = new androidx.work.impl.WorkerWrapper$runWorker$2
            r7.<init>(r5, r0, r4, r1)
            Vf.n0 r6 = (Vf.n0) r6
            Vf.P r0 = new Vf.P
            r4 = 1
            r0.<init>(r4, r7)
            r4 = 1
            r6.D(r4, r0)
            boolean r0 = r1.trySetRunning()
            if (r0 != 0) goto L_0x01b8
            androidx.work.impl.WorkerWrapper$Resolution$ResetWorkerStatus r0 = new androidx.work.impl.WorkerWrapper$Resolution$ResetWorkerStatus
            r7 = 0
            r8 = 0
            r0.<init>(r7, r4, r8)
            return r0
        L_0x01b8:
            r7 = 0
            r8 = 0
            boolean r0 = r6.E()
            if (r0 == 0) goto L_0x01c6
            androidx.work.impl.WorkerWrapper$Resolution$ResetWorkerStatus r0 = new androidx.work.impl.WorkerWrapper$Resolution$ResetWorkerStatus
            r0.<init>(r7, r4, r8)
            return r0
        L_0x01c6:
            androidx.work.ForegroundUpdater r0 = r9.getForegroundUpdater()
            java.lang.String r4 = "params.foregroundUpdater"
            kotlin.jvm.internal.j.d(r0, r4)
            androidx.work.impl.utils.taskexecutor.TaskExecutor r4 = r1.workTaskExecutor
            java.util.concurrent.Executor r4 = r4.getMainThreadExecutor()
            java.lang.String r6 = "workTaskExecutor.getMainThreadExecutor()"
            kotlin.jvm.internal.j.d(r4, r6)
            Vf.x r4 = Vf.D.h(r4)
            androidx.work.impl.WorkerWrapper$runWorker$result$1 r6 = new androidx.work.impl.WorkerWrapper$runWorker$result$1     // Catch:{ CancellationException -> 0x0202, all -> 0x0200 }
            r8 = 0
            r6.<init>(r1, r5, r0, r8)     // Catch:{ CancellationException -> 0x0202, all -> 0x0200 }
            r2.L$0 = r1     // Catch:{ CancellationException -> 0x0202, all -> 0x0200 }
            r2.L$1 = r9     // Catch:{ CancellationException -> 0x0202, all -> 0x0200 }
            r5 = 1
            r2.label = r5     // Catch:{ CancellationException -> 0x0202, all -> 0x0200 }
            java.lang.Object r0 = Vf.D.w(r4, r6, r2)     // Catch:{ CancellationException -> 0x0202, all -> 0x0200 }
            if (r0 != r3) goto L_0x01f3
            return r3
        L_0x01f3:
            androidx.work.ListenableWorker$Result r0 = (androidx.work.ListenableWorker.Result) r0     // Catch:{ CancellationException -> 0x0202, all -> 0x0200 }
            androidx.work.impl.WorkerWrapper$Resolution$Finished r2 = new androidx.work.impl.WorkerWrapper$Resolution$Finished     // Catch:{ CancellationException -> 0x0202, all -> 0x0200 }
            java.lang.String r3 = "result"
            kotlin.jvm.internal.j.d(r0, r3)     // Catch:{ CancellationException -> 0x0202, all -> 0x0200 }
            r2.<init>(r0)     // Catch:{ CancellationException -> 0x0202, all -> 0x0200 }
            return r2
        L_0x0200:
            r0 = move-exception
            goto L_0x0204
        L_0x0202:
            r0 = move-exception
            goto L_0x023c
        L_0x0204:
            java.lang.String r2 = androidx.work.impl.WorkerWrapperKt.TAG
            androidx.work.Logger r3 = androidx.work.Logger.get()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r1.workDescription
            java.lang.String r6 = " failed because it threw an exception/error"
            java.lang.String r4 = i.C0212a.p(r4, r5, r6)
            r3.error(r2, r4, r0)
            androidx.work.Configuration r2 = r1.configuration
            androidx.core.util.Consumer r2 = r2.getWorkerExecutionExceptionHandler()
            if (r2 == 0) goto L_0x0234
            androidx.work.WorkerExceptionInfo r3 = new androidx.work.WorkerExceptionInfo
            androidx.work.impl.model.WorkSpec r1 = r1.workSpec
            java.lang.String r1 = r1.workerClassName
            r3.<init>(r1, r9, r0)
            java.lang.String r0 = androidx.work.impl.WorkerWrapperKt.TAG
            androidx.work.impl.utils.WorkerExceptionUtilsKt.safeAccept(r2, r3, r0)
        L_0x0234:
            androidx.work.impl.WorkerWrapper$Resolution$Failed r0 = new androidx.work.impl.WorkerWrapper$Resolution$Failed
            r1 = 1
            r8 = 0
            r0.<init>(r8, r1, r8)
            return r0
        L_0x023c:
            java.lang.String r2 = androidx.work.impl.WorkerWrapperKt.TAG
            androidx.work.Logger r3 = androidx.work.Logger.get()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r1 = r1.workDescription
            java.lang.String r5 = " was cancelled"
            java.lang.String r1 = i.C0212a.p(r4, r1, r5)
            r3.info(r2, r1, r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.WorkerWrapper.runWorker(qe.c):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Boolean runWorker$lambda$1(WorkerWrapper workerWrapper) {
        WorkSpec workSpec2 = workerWrapper.workSpec;
        if (workSpec2.state != WorkInfo$State.ENQUEUED) {
            String access$getTAG$p = WorkerWrapperKt.TAG;
            Logger logger = Logger.get();
            logger.debug(access$getTAG$p, workerWrapper.workSpec.workerClassName + " is not in ENQUEUED state. Nothing more to do");
            return Boolean.TRUE;
        } else if ((!workSpec2.isPeriodic() && !workerWrapper.workSpec.isBackedOff()) || workerWrapper.clock.currentTimeMillis() >= workerWrapper.workSpec.calculateNextRunTime()) {
            return Boolean.FALSE;
        } else {
            Logger logger2 = Logger.get();
            String access$getTAG$p2 = WorkerWrapperKt.TAG;
            logger2.debug(access$getTAG$p2, "Delaying execution for " + workerWrapper.workSpec.workerClassName + " because it is being executed before schedule.");
            return Boolean.TRUE;
        }
    }

    private final boolean setSucceeded(ListenableWorker.Result result) {
        this.workSpecDao.setState(WorkInfo$State.SUCCEEDED, this.workSpecId);
        j.c(result, "null cannot be cast to non-null type androidx.work.ListenableWorker.Result.Success");
        Data outputData = ((ListenableWorker.Result.Success) result).getOutputData();
        j.d(outputData, "success.outputData");
        this.workSpecDao.setOutput(this.workSpecId, outputData);
        long currentTimeMillis = this.clock.currentTimeMillis();
        for (String next : this.dependencyDao.getDependentWorkIds(this.workSpecId)) {
            if (this.workSpecDao.getState(next) == WorkInfo$State.BLOCKED && this.dependencyDao.hasCompletedAllPrerequisites(next)) {
                String access$getTAG$p = WorkerWrapperKt.TAG;
                Logger logger = Logger.get();
                logger.info(access$getTAG$p, "Setting status to enqueued for " + next);
                this.workSpecDao.setState(WorkInfo$State.ENQUEUED, next);
                this.workSpecDao.setLastEnqueueTime(next, currentTimeMillis);
            }
        }
        return false;
    }

    private final boolean trySetRunning() {
        Object runInTransaction = this.workDatabase.runInTransaction(new d(this, 1));
        j.d(runInTransaction, "workDatabase.runInTransa…e\n            }\n        )");
        return ((Boolean) runInTransaction).booleanValue();
    }

    /* access modifiers changed from: private */
    public static final Boolean trySetRunning$lambda$11(WorkerWrapper workerWrapper) {
        boolean z;
        if (workerWrapper.workSpecDao.getState(workerWrapper.workSpecId) == WorkInfo$State.ENQUEUED) {
            workerWrapper.workSpecDao.setState(WorkInfo$State.RUNNING, workerWrapper.workSpecId);
            workerWrapper.workSpecDao.incrementWorkSpecRunAttemptCount(workerWrapper.workSpecId);
            workerWrapper.workSpecDao.setStopReason(workerWrapper.workSpecId, -256);
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public final WorkGenerationalId getWorkGenerationalId() {
        return WorkSpecKt.generationalId(this.workSpec);
    }

    public final WorkSpec getWorkSpec() {
        return this.workSpec;
    }

    public final void interrupt(int i2) {
        ((n0) this.workerJob).o(new WorkerStoppedException(i2));
    }

    public final ListenableFuture launch() {
        return ListenableFutureKt.launchFuture$default(this.workTaskExecutor.getTaskCoroutineDispatcher().plus(new g0()), (C) null, new WorkerWrapper$launch$1(this, (C1227c) null), 2, (Object) null);
    }

    public final boolean setFailed(ListenableWorker.Result result) {
        j.e(result, "result");
        iterativelyFailWorkAndDependents(this.workSpecId);
        Data outputData = ((ListenableWorker.Result.Failure) result).getOutputData();
        j.d(outputData, "failure.outputData");
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.getNextScheduleTimeOverrideGeneration());
        this.workSpecDao.setOutput(this.workSpecId, outputData);
        return false;
    }
}
