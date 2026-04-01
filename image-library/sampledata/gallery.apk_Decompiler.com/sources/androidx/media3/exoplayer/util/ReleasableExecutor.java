package androidx.media3.exoplayer.util;

import androidx.media3.common.util.Consumer;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ReleasableExecutor extends Executor {
    static <T extends Executor> ReleasableExecutor from(final T t, final Consumer<T> consumer) {
        return new ReleasableExecutor() {
            public void execute(Runnable runnable) {
                t.execute(runnable);
            }

            public void release() {
                consumer.accept(t);
            }
        };
    }

    void release();
}
