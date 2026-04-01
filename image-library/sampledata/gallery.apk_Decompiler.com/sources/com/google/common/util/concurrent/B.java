package com.google.common.util.concurrent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B extends j implements Runnable {
    public final Runnable d;

    public B(Runnable runnable) {
        runnable.getClass();
        this.d = runnable;
    }

    public final String pendingToString() {
        return "task=[" + this.d + "]";
    }

    public final void run() {
        try {
            this.d.run();
        } catch (Throwable th) {
            setException(th);
            throw th;
        }
    }
}
