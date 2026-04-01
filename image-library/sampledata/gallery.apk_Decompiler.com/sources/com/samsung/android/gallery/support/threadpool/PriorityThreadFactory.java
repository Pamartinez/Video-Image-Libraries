package com.samsung.android.gallery.support.threadpool;

import android.os.Looper;
import android.os.Process;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PriorityThreadFactory implements ThreadFactory {
    private final String mName;
    private final AtomicInteger mNumber = new AtomicInteger();
    /* access modifiers changed from: private */
    public final int mPriority;

    public PriorityThreadFactory(String str, int i2) {
        this.mName = str;
        this.mPriority = i2;
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, this.mName + '-' + this.mNumber.getAndIncrement()) {
            public void run() {
                Process.setThreadPriority(PriorityThreadFactory.this.mPriority);
                Looper.prepare();
                super.run();
            }
        };
    }
}
