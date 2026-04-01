package androidx.media3.common.util;

import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BackgroundExecutor {
    private static Executor staticInstance;

    public static synchronized Executor get() {
        Executor executor;
        synchronized (BackgroundExecutor.class) {
            try {
                if (staticInstance == null) {
                    staticInstance = Util.newSingleThreadExecutor("ExoPlayer:BackgroundExecutor");
                }
                executor = staticInstance;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return executor;
    }
}
