package androidx.work.impl.utils;

import androidx.core.util.Consumer;
import androidx.work.Logger;
import androidx.work.WorkerExceptionInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a'\u0010\u0006\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/core/util/Consumer;", "Landroidx/work/WorkerExceptionInfo;", "info", "", "tag", "Lme/x;", "safeAccept", "(Landroidx/core/util/Consumer;Landroidx/work/WorkerExceptionInfo;Ljava/lang/String;)V", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WorkerExceptionUtilsKt {
    public static final void safeAccept(Consumer<WorkerExceptionInfo> consumer, WorkerExceptionInfo workerExceptionInfo, String str) {
        j.e(consumer, "<this>");
        j.e(workerExceptionInfo, "info");
        j.e(str, "tag");
        try {
            consumer.accept(workerExceptionInfo);
        } catch (Throwable th) {
            Logger.get().error(str, "Exception handler threw an exception", th);
        }
    }
}
