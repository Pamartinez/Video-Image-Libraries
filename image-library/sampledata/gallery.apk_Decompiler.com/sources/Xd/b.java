package Xd;

import java.util.concurrent.ThreadFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements ThreadFactory {
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setPriority(1);
        thread.setDaemon(true);
        return thread;
    }
}
