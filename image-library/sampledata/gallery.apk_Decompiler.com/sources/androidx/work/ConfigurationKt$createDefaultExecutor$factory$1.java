package androidx.work;

import i.C0212a;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"androidx/work/ConfigurationKt$createDefaultExecutor$factory$1", "Ljava/util/concurrent/ThreadFactory;", "threadCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "newThread", "Ljava/lang/Thread;", "runnable", "Ljava/lang/Runnable;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ConfigurationKt$createDefaultExecutor$factory$1 implements ThreadFactory {
    final /* synthetic */ boolean $isTaskExecutor;
    private final AtomicInteger threadCount = new AtomicInteger(0);

    public ConfigurationKt$createDefaultExecutor$factory$1(boolean z) {
        this.$isTaskExecutor = z;
    }

    public Thread newThread(Runnable runnable) {
        String str;
        j.e(runnable, "runnable");
        if (this.$isTaskExecutor) {
            str = "WM.task-";
        } else {
            str = "androidx.work-";
        }
        StringBuilder s = C0212a.s(str);
        s.append(this.threadCount.incrementAndGet());
        return new Thread(runnable, s.toString());
    }
}
