package J0;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements ThreadFactory {
    public static final AtomicInteger d = new AtomicInteger(1);

    /* renamed from: a  reason: collision with root package name */
    public final ThreadGroup f354a;
    public final AtomicInteger b = new AtomicInteger(1);

    /* renamed from: c  reason: collision with root package name */
    public final String f355c;

    public c() {
        ThreadGroup threadGroup;
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager == null) {
            threadGroup = Thread.currentThread().getThreadGroup();
        } else {
            threadGroup = securityManager.getThreadGroup();
        }
        this.f354a = threadGroup;
        this.f355c = "lottie-" + d.getAndIncrement() + "-thread-";
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.f354a, runnable, this.f355c + this.b.getAndIncrement(), 0);
        thread.setDaemon(false);
        thread.setPriority(10);
        return thread;
    }
}
