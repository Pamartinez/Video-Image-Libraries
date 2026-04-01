package com.google.common.util.concurrent;

import java.util.concurrent.locks.AbstractOwnableSynchronizer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v extends AbstractOwnableSynchronizer implements Runnable {
    public final F d;

    public v(F f) {
        this.d = f;
    }

    public static void a(v vVar, Thread thread) {
        vVar.setExclusiveOwnerThread(thread);
    }

    public final String toString() {
        return this.d.toString();
    }

    public final void run() {
    }
}
