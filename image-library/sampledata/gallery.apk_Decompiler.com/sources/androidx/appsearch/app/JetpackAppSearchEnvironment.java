package androidx.appsearch.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JetpackAppSearchEnvironment implements AppSearchEnvironment {
    public ExecutorService createCachedThreadPoolExecutor() {
        return Executors.newCachedThreadPool();
    }
}
