package androidx.work;

import android.content.Context;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\f"}, d2 = {"Landroidx/work/WorkerFactory;", "", "()V", "createWorker", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "workerClassName", "", "workerParameters", "Landroidx/work/WorkerParameters;", "createWorkerWithDefaultFallback", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WorkerFactory {
    private static final ListenableWorker createWorkerWithDefaultFallback$fallbackToReflection(Context context, String str, WorkerParameters workerParameters) {
        try {
            Object newInstance = createWorkerWithDefaultFallback$getWorkerClass(str).getDeclaredConstructor(new Class[]{Context.class, WorkerParameters.class}).newInstance(new Object[]{context, workerParameters});
            j.d(newInstance, "{\n                val co…Parameters)\n            }");
            return (ListenableWorker) newInstance;
        } catch (Throwable th) {
            Logger logger = Logger.get();
            String access$getTAG$p = WorkerFactoryKt.TAG;
            logger.error(access$getTAG$p, "Could not instantiate " + str, th);
            throw th;
        }
    }

    private static final Class<? extends ListenableWorker> createWorkerWithDefaultFallback$getWorkerClass(String str) {
        try {
            Class<? extends U> asSubclass = Class.forName(str).asSubclass(ListenableWorker.class);
            j.d(asSubclass, "{\n                Class.…class.java)\n            }");
            return asSubclass;
        } catch (Throwable th) {
            Logger logger = Logger.get();
            String access$getTAG$p = WorkerFactoryKt.TAG;
            logger.error(access$getTAG$p, "Invalid class: " + str, th);
            throw th;
        }
    }

    public abstract ListenableWorker createWorker(Context context, String str, WorkerParameters workerParameters);

    public final ListenableWorker createWorkerWithDefaultFallback(Context context, String str, WorkerParameters workerParameters) {
        j.e(context, StateHandler.KEY_APP_STATE);
        j.e(str, "workerClassName");
        j.e(workerParameters, "workerParameters");
        ListenableWorker createWorker = createWorker(context, str, workerParameters);
        if (createWorker == null) {
            createWorker = createWorkerWithDefaultFallback$fallbackToReflection(context, str, workerParameters);
        }
        if (!createWorker.isUsed()) {
            return createWorker;
        }
        throw new IllegalStateException("WorkerFactory (" + getClass().getName() + ") returned an instance of a ListenableWorker (" + str + ") which has already been invoked. createWorker() must always return a new instance of a ListenableWorker.");
    }
}
