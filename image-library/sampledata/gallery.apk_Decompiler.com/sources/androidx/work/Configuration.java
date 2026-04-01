package androidx.work;

import Vf.D;
import Vf.M;
import androidx.core.util.Consumer;
import androidx.work.impl.DefaultRunnableScheduler;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import qe.C1232h;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 Q2\u00020\u0001:\u0002RQB\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0010\u0010\b\u001a\u0004\b\u0011\u0010\nR\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001d\u001a\u00020\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\"\u001a\u00020!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u001f\u0010(\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&8\u0006¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u001f\u0010,\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&8\u0006¢\u0006\f\n\u0004\b,\u0010)\u001a\u0004\b-\u0010+R\u001f\u0010/\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010&8\u0006¢\u0006\f\n\u0004\b/\u0010)\u001a\u0004\b0\u0010+R\u001f\u00101\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010&8\u0006¢\u0006\f\n\u0004\b1\u0010)\u001a\u0004\b2\u0010+R\u0019\u00104\u001a\u0004\u0018\u0001038\u0006¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R\u0017\u00109\u001a\u0002088G¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<R\u0017\u0010=\u001a\u0002088\u0006¢\u0006\f\n\u0004\b=\u0010:\u001a\u0004\b>\u0010<R\u0017\u0010?\u001a\u0002088\u0006¢\u0006\f\n\u0004\b?\u0010:\u001a\u0004\b@\u0010<R\u0017\u0010A\u001a\u0002088\u0006¢\u0006\f\n\u0004\bA\u0010:\u001a\u0004\bB\u0010<R\u0017\u0010C\u001a\u0002088G¢\u0006\f\n\u0004\bC\u0010:\u001a\u0004\bD\u0010<R\u0017\u0010F\u001a\u00020E8G¢\u0006\f\n\u0004\bF\u0010G\u001a\u0004\bF\u0010HR \u0010I\u001a\u00020E8GX\u0004¢\u0006\u0012\n\u0004\bI\u0010G\u0012\u0004\bJ\u0010K\u001a\u0004\bI\u0010HR\u0017\u0010M\u001a\u00020L8G¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u0010P¨\u0006S"}, d2 = {"Landroidx/work/Configuration;", "", "Landroidx/work/Configuration$Builder;", "builder", "<init>", "(Landroidx/work/Configuration$Builder;)V", "Ljava/util/concurrent/Executor;", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "Lqe/h;", "workerCoroutineContext", "Lqe/h;", "getWorkerCoroutineContext", "()Lqe/h;", "taskExecutor", "getTaskExecutor", "Landroidx/work/Clock;", "clock", "Landroidx/work/Clock;", "getClock", "()Landroidx/work/Clock;", "Landroidx/work/WorkerFactory;", "workerFactory", "Landroidx/work/WorkerFactory;", "getWorkerFactory", "()Landroidx/work/WorkerFactory;", "Landroidx/work/InputMergerFactory;", "inputMergerFactory", "Landroidx/work/InputMergerFactory;", "getInputMergerFactory", "()Landroidx/work/InputMergerFactory;", "Landroidx/work/RunnableScheduler;", "runnableScheduler", "Landroidx/work/RunnableScheduler;", "getRunnableScheduler", "()Landroidx/work/RunnableScheduler;", "Landroidx/core/util/Consumer;", "", "initializationExceptionHandler", "Landroidx/core/util/Consumer;", "getInitializationExceptionHandler", "()Landroidx/core/util/Consumer;", "schedulingExceptionHandler", "getSchedulingExceptionHandler", "Landroidx/work/WorkerExceptionInfo;", "workerInitializationExceptionHandler", "getWorkerInitializationExceptionHandler", "workerExecutionExceptionHandler", "getWorkerExecutionExceptionHandler", "", "defaultProcessName", "Ljava/lang/String;", "getDefaultProcessName", "()Ljava/lang/String;", "", "minimumLoggingLevel", "I", "getMinimumLoggingLevel", "()I", "minJobSchedulerId", "getMinJobSchedulerId", "maxJobSchedulerId", "getMaxJobSchedulerId", "contentUriTriggerWorkersLimit", "getContentUriTriggerWorkersLimit", "maxSchedulerLimit", "getMaxSchedulerLimit", "", "isUsingDefaultTaskExecutor", "Z", "()Z", "isMarkingJobsAsImportantWhileForeground", "isMarkingJobsAsImportantWhileForeground$annotations", "()V", "Landroidx/work/Tracer;", "tracer", "Landroidx/work/Tracer;", "getTracer", "()Landroidx/work/Tracer;", "Companion", "Builder", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Configuration {
    public static final Companion Companion = new Companion((e) null);
    private final Clock clock;
    private final int contentUriTriggerWorkersLimit;
    private final String defaultProcessName;
    private final Executor executor;
    private final Consumer<Throwable> initializationExceptionHandler;
    private final InputMergerFactory inputMergerFactory;
    private final boolean isMarkingJobsAsImportantWhileForeground;
    private final boolean isUsingDefaultTaskExecutor;
    private final int maxJobSchedulerId;
    private final int maxSchedulerLimit;
    private final int minJobSchedulerId;
    private final int minimumLoggingLevel;
    private final RunnableScheduler runnableScheduler;
    private final Consumer<Throwable> schedulingExceptionHandler;
    private final Executor taskExecutor;
    private final Tracer tracer;
    private final C1232h workerCoroutineContext;
    private final Consumer<WorkerExceptionInfo> workerExecutionExceptionHandler;
    private final WorkerFactory workerFactory;
    private final Consumer<WorkerExceptionInfo> workerInitializationExceptionHandler;

    @Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010#\u001a\u0004\u0018\u00010\u00078\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b#\u0010\t\u001a\u0004\b$\u0010\u000b\"\u0004\b%\u0010\rR$\u0010'\u001a\u0004\u0018\u00010&8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R$\u0010.\u001a\u0004\u0018\u00010-8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R*\u00106\u001a\n\u0012\u0004\u0012\u000205\u0018\u0001048\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R*\u0010<\u001a\n\u0012\u0004\u0012\u000205\u0018\u0001048\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b<\u00107\u001a\u0004\b=\u00109\"\u0004\b>\u0010;R*\u0010@\u001a\n\u0012\u0004\u0012\u00020?\u0018\u0001048\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b@\u00107\u001a\u0004\bA\u00109\"\u0004\bB\u0010;R*\u0010C\u001a\n\u0012\u0004\u0012\u00020?\u0018\u0001048\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bC\u00107\u001a\u0004\bD\u00109\"\u0004\bE\u0010;R$\u0010G\u001a\u0004\u0018\u00010F8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bG\u0010H\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\"\u0010N\u001a\u00020M8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bN\u0010O\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\"\u0010T\u001a\u00020M8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bT\u0010O\u001a\u0004\bU\u0010Q\"\u0004\bV\u0010SR\"\u0010W\u001a\u00020M8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bW\u0010O\u001a\u0004\bX\u0010Q\"\u0004\bY\u0010SR\"\u0010Z\u001a\u00020M8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bZ\u0010O\u001a\u0004\b[\u0010Q\"\u0004\b\\\u0010SR\"\u0010]\u001a\u00020M8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b]\u0010O\u001a\u0004\b^\u0010Q\"\u0004\b_\u0010SR\"\u0010a\u001a\u00020`8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\ba\u0010b\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR$\u0010h\u001a\u0004\u0018\u00010g8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bh\u0010i\u001a\u0004\bj\u0010k\"\u0004\bl\u0010m¨\u0006n"}, d2 = {"Landroidx/work/Configuration$Builder;", "", "<init>", "()V", "Landroidx/work/Configuration;", "build", "()Landroidx/work/Configuration;", "Ljava/util/concurrent/Executor;", "executor", "Ljava/util/concurrent/Executor;", "getExecutor$work_runtime_release", "()Ljava/util/concurrent/Executor;", "setExecutor$work_runtime_release", "(Ljava/util/concurrent/Executor;)V", "Lqe/h;", "workerContext", "Lqe/h;", "getWorkerContext$work_runtime_release", "()Lqe/h;", "setWorkerContext$work_runtime_release", "(Lqe/h;)V", "Landroidx/work/WorkerFactory;", "workerFactory", "Landroidx/work/WorkerFactory;", "getWorkerFactory$work_runtime_release", "()Landroidx/work/WorkerFactory;", "setWorkerFactory$work_runtime_release", "(Landroidx/work/WorkerFactory;)V", "Landroidx/work/InputMergerFactory;", "inputMergerFactory", "Landroidx/work/InputMergerFactory;", "getInputMergerFactory$work_runtime_release", "()Landroidx/work/InputMergerFactory;", "setInputMergerFactory$work_runtime_release", "(Landroidx/work/InputMergerFactory;)V", "taskExecutor", "getTaskExecutor$work_runtime_release", "setTaskExecutor$work_runtime_release", "Landroidx/work/Clock;", "clock", "Landroidx/work/Clock;", "getClock$work_runtime_release", "()Landroidx/work/Clock;", "setClock$work_runtime_release", "(Landroidx/work/Clock;)V", "Landroidx/work/RunnableScheduler;", "runnableScheduler", "Landroidx/work/RunnableScheduler;", "getRunnableScheduler$work_runtime_release", "()Landroidx/work/RunnableScheduler;", "setRunnableScheduler$work_runtime_release", "(Landroidx/work/RunnableScheduler;)V", "Landroidx/core/util/Consumer;", "", "initializationExceptionHandler", "Landroidx/core/util/Consumer;", "getInitializationExceptionHandler$work_runtime_release", "()Landroidx/core/util/Consumer;", "setInitializationExceptionHandler$work_runtime_release", "(Landroidx/core/util/Consumer;)V", "schedulingExceptionHandler", "getSchedulingExceptionHandler$work_runtime_release", "setSchedulingExceptionHandler$work_runtime_release", "Landroidx/work/WorkerExceptionInfo;", "workerInitializationExceptionHandler", "getWorkerInitializationExceptionHandler$work_runtime_release", "setWorkerInitializationExceptionHandler$work_runtime_release", "workerExecutionExceptionHandler", "getWorkerExecutionExceptionHandler$work_runtime_release", "setWorkerExecutionExceptionHandler$work_runtime_release", "", "defaultProcessName", "Ljava/lang/String;", "getDefaultProcessName$work_runtime_release", "()Ljava/lang/String;", "setDefaultProcessName$work_runtime_release", "(Ljava/lang/String;)V", "", "loggingLevel", "I", "getLoggingLevel$work_runtime_release", "()I", "setLoggingLevel$work_runtime_release", "(I)V", "minJobSchedulerId", "getMinJobSchedulerId$work_runtime_release", "setMinJobSchedulerId$work_runtime_release", "maxJobSchedulerId", "getMaxJobSchedulerId$work_runtime_release", "setMaxJobSchedulerId$work_runtime_release", "maxSchedulerLimit", "getMaxSchedulerLimit$work_runtime_release", "setMaxSchedulerLimit$work_runtime_release", "contentUriTriggerWorkersLimit", "getContentUriTriggerWorkersLimit$work_runtime_release", "setContentUriTriggerWorkersLimit$work_runtime_release", "", "markJobsAsImportantWhileForeground", "Z", "getMarkJobsAsImportantWhileForeground$work_runtime_release", "()Z", "setMarkJobsAsImportantWhileForeground$work_runtime_release", "(Z)V", "Landroidx/work/Tracer;", "tracer", "Landroidx/work/Tracer;", "getTracer$work_runtime_release", "()Landroidx/work/Tracer;", "setTracer$work_runtime_release", "(Landroidx/work/Tracer;)V", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private Clock clock;
        private int contentUriTriggerWorkersLimit = 8;
        private String defaultProcessName;
        private Executor executor;
        private Consumer<Throwable> initializationExceptionHandler;
        private InputMergerFactory inputMergerFactory;
        private int loggingLevel = 4;
        private boolean markJobsAsImportantWhileForeground = true;
        private int maxJobSchedulerId = Integer.MAX_VALUE;
        private int maxSchedulerLimit = 20;
        private int minJobSchedulerId;
        private RunnableScheduler runnableScheduler;
        private Consumer<Throwable> schedulingExceptionHandler;
        private Executor taskExecutor;
        private Tracer tracer;
        private C1232h workerContext;
        private Consumer<WorkerExceptionInfo> workerExecutionExceptionHandler;
        private WorkerFactory workerFactory;
        private Consumer<WorkerExceptionInfo> workerInitializationExceptionHandler;

        public final Configuration build() {
            return new Configuration(this);
        }

        public final Clock getClock$work_runtime_release() {
            return this.clock;
        }

        public final int getContentUriTriggerWorkersLimit$work_runtime_release() {
            return this.contentUriTriggerWorkersLimit;
        }

        public final String getDefaultProcessName$work_runtime_release() {
            return this.defaultProcessName;
        }

        public final Executor getExecutor$work_runtime_release() {
            return this.executor;
        }

        public final Consumer<Throwable> getInitializationExceptionHandler$work_runtime_release() {
            return this.initializationExceptionHandler;
        }

        public final InputMergerFactory getInputMergerFactory$work_runtime_release() {
            return this.inputMergerFactory;
        }

        public final int getLoggingLevel$work_runtime_release() {
            return this.loggingLevel;
        }

        public final boolean getMarkJobsAsImportantWhileForeground$work_runtime_release() {
            return this.markJobsAsImportantWhileForeground;
        }

        public final int getMaxJobSchedulerId$work_runtime_release() {
            return this.maxJobSchedulerId;
        }

        public final int getMaxSchedulerLimit$work_runtime_release() {
            return this.maxSchedulerLimit;
        }

        public final int getMinJobSchedulerId$work_runtime_release() {
            return this.minJobSchedulerId;
        }

        public final RunnableScheduler getRunnableScheduler$work_runtime_release() {
            return this.runnableScheduler;
        }

        public final Consumer<Throwable> getSchedulingExceptionHandler$work_runtime_release() {
            return this.schedulingExceptionHandler;
        }

        public final Executor getTaskExecutor$work_runtime_release() {
            return this.taskExecutor;
        }

        public final Tracer getTracer$work_runtime_release() {
            return this.tracer;
        }

        public final C1232h getWorkerContext$work_runtime_release() {
            return this.workerContext;
        }

        public final Consumer<WorkerExceptionInfo> getWorkerExecutionExceptionHandler$work_runtime_release() {
            return this.workerExecutionExceptionHandler;
        }

        public final WorkerFactory getWorkerFactory$work_runtime_release() {
            return this.workerFactory;
        }

        public final Consumer<WorkerExceptionInfo> getWorkerInitializationExceptionHandler$work_runtime_release() {
            return this.workerInitializationExceptionHandler;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/work/Configuration$Companion;", "", "()V", "MIN_SCHEDULER_LIMIT", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public Configuration(Builder builder) {
        j.e(builder, "builder");
        C1232h workerContext$work_runtime_release = builder.getWorkerContext$work_runtime_release();
        Executor executor$work_runtime_release = builder.getExecutor$work_runtime_release();
        boolean z = false;
        if (executor$work_runtime_release == null) {
            if (workerContext$work_runtime_release != null) {
                executor$work_runtime_release = ConfigurationKt.asExecutor(workerContext$work_runtime_release);
            } else {
                executor$work_runtime_release = null;
            }
            if (executor$work_runtime_release == null) {
                executor$work_runtime_release = ConfigurationKt.createDefaultExecutor(false);
            }
        }
        this.executor = executor$work_runtime_release;
        if (workerContext$work_runtime_release == null) {
            if (builder.getExecutor$work_runtime_release() != null) {
                workerContext$work_runtime_release = D.h(executor$work_runtime_release);
            } else {
                workerContext$work_runtime_release = M.f3843a;
            }
        }
        this.workerCoroutineContext = workerContext$work_runtime_release;
        this.isUsingDefaultTaskExecutor = builder.getTaskExecutor$work_runtime_release() == null ? true : z;
        Executor taskExecutor$work_runtime_release = builder.getTaskExecutor$work_runtime_release();
        this.taskExecutor = taskExecutor$work_runtime_release == null ? ConfigurationKt.createDefaultExecutor(true) : taskExecutor$work_runtime_release;
        Clock clock$work_runtime_release = builder.getClock$work_runtime_release();
        this.clock = clock$work_runtime_release == null ? new SystemClock() : clock$work_runtime_release;
        WorkerFactory workerFactory$work_runtime_release = builder.getWorkerFactory$work_runtime_release();
        this.workerFactory = workerFactory$work_runtime_release == null ? DefaultWorkerFactory.INSTANCE : workerFactory$work_runtime_release;
        InputMergerFactory inputMergerFactory$work_runtime_release = builder.getInputMergerFactory$work_runtime_release();
        this.inputMergerFactory = inputMergerFactory$work_runtime_release == null ? NoOpInputMergerFactory.INSTANCE : inputMergerFactory$work_runtime_release;
        RunnableScheduler runnableScheduler$work_runtime_release = builder.getRunnableScheduler$work_runtime_release();
        this.runnableScheduler = runnableScheduler$work_runtime_release == null ? new DefaultRunnableScheduler() : runnableScheduler$work_runtime_release;
        this.minimumLoggingLevel = builder.getLoggingLevel$work_runtime_release();
        this.minJobSchedulerId = builder.getMinJobSchedulerId$work_runtime_release();
        this.maxJobSchedulerId = builder.getMaxJobSchedulerId$work_runtime_release();
        this.maxSchedulerLimit = builder.getMaxSchedulerLimit$work_runtime_release();
        this.initializationExceptionHandler = builder.getInitializationExceptionHandler$work_runtime_release();
        this.schedulingExceptionHandler = builder.getSchedulingExceptionHandler$work_runtime_release();
        this.workerInitializationExceptionHandler = builder.getWorkerInitializationExceptionHandler$work_runtime_release();
        this.workerExecutionExceptionHandler = builder.getWorkerExecutionExceptionHandler$work_runtime_release();
        this.defaultProcessName = builder.getDefaultProcessName$work_runtime_release();
        this.contentUriTriggerWorkersLimit = builder.getContentUriTriggerWorkersLimit$work_runtime_release();
        this.isMarkingJobsAsImportantWhileForeground = builder.getMarkJobsAsImportantWhileForeground$work_runtime_release();
        Tracer tracer$work_runtime_release = builder.getTracer$work_runtime_release();
        this.tracer = tracer$work_runtime_release == null ? ConfigurationKt.createDefaultTracer() : tracer$work_runtime_release;
    }

    public final Clock getClock() {
        return this.clock;
    }

    public final int getContentUriTriggerWorkersLimit() {
        return this.contentUriTriggerWorkersLimit;
    }

    public final String getDefaultProcessName() {
        return this.defaultProcessName;
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final Consumer<Throwable> getInitializationExceptionHandler() {
        return this.initializationExceptionHandler;
    }

    public final InputMergerFactory getInputMergerFactory() {
        return this.inputMergerFactory;
    }

    public final int getMaxJobSchedulerId() {
        return this.maxJobSchedulerId;
    }

    public final int getMaxSchedulerLimit() {
        return this.maxSchedulerLimit;
    }

    public final int getMinJobSchedulerId() {
        return this.minJobSchedulerId;
    }

    public final int getMinimumLoggingLevel() {
        return this.minimumLoggingLevel;
    }

    public final RunnableScheduler getRunnableScheduler() {
        return this.runnableScheduler;
    }

    public final Consumer<Throwable> getSchedulingExceptionHandler() {
        return this.schedulingExceptionHandler;
    }

    public final Executor getTaskExecutor() {
        return this.taskExecutor;
    }

    public final Tracer getTracer() {
        return this.tracer;
    }

    public final C1232h getWorkerCoroutineContext() {
        return this.workerCoroutineContext;
    }

    public final Consumer<WorkerExceptionInfo> getWorkerExecutionExceptionHandler() {
        return this.workerExecutionExceptionHandler;
    }

    public final WorkerFactory getWorkerFactory() {
        return this.workerFactory;
    }

    public final Consumer<WorkerExceptionInfo> getWorkerInitializationExceptionHandler() {
        return this.workerInitializationExceptionHandler;
    }

    public final boolean isMarkingJobsAsImportantWhileForeground() {
        return this.isMarkingJobsAsImportantWhileForeground;
    }
}
