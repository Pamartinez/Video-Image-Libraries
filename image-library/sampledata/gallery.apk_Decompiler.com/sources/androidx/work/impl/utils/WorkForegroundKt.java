package androidx.work.impl.utils;

import Vf.D;
import android.content.Context;
import android.os.Build;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.x;
import qe.C1227c;
import re.C1245a;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a8\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH@¢\u0006\u0004\b\u000b\u0010\f\"\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroid/content/Context;", "context", "Landroidx/work/impl/model/WorkSpec;", "spec", "Landroidx/work/ListenableWorker;", "worker", "Landroidx/work/ForegroundUpdater;", "foregroundUpdater", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "taskExecutor", "Lme/x;", "workForeground", "(Landroid/content/Context;Landroidx/work/impl/model/WorkSpec;Landroidx/work/ListenableWorker;Landroidx/work/ForegroundUpdater;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Lqe/c;)Ljava/lang/Object;", "", "TAG", "Ljava/lang/String;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WorkForegroundKt {
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("WorkForegroundRunnable");
        j.d(tagWithPrefix, "tagWithPrefix(\"WorkForegroundRunnable\")");
        TAG = tagWithPrefix;
    }

    public static final Object workForeground(Context context, WorkSpec workSpec, ListenableWorker listenableWorker, ForegroundUpdater foregroundUpdater, TaskExecutor taskExecutor, C1227c cVar) {
        boolean z = workSpec.expedited;
        x xVar = x.f4917a;
        if (z && Build.VERSION.SDK_INT < 31) {
            Executor mainThreadExecutor = taskExecutor.getMainThreadExecutor();
            j.d(mainThreadExecutor, "taskExecutor.mainThreadExecutor");
            ListenableWorker listenableWorker2 = listenableWorker;
            Object w = D.w(D.h(mainThreadExecutor), new WorkForegroundKt$workForeground$2(listenableWorker2, workSpec, foregroundUpdater, context, (C1227c) null), cVar);
            if (w == C1245a.COROUTINE_SUSPENDED) {
                return w;
            }
        }
        return xVar;
    }
}
