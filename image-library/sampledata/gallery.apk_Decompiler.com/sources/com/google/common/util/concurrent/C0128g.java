package com.google.common.util.concurrent;

/* renamed from: com.google.common.util.concurrent.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0128g implements Runnable {
    public final q d;
    public final ListenableFuture e;

    public C0128g(q qVar, ListenableFuture listenableFuture) {
        this.d = qVar;
        this.e = listenableFuture;
    }

    public final void run() {
        q qVar = this.d;
        if (qVar.value == this) {
            if (q.ATOMIC_HELPER.b(qVar, this, q.f(this.e))) {
                q.c(qVar, false);
            }
        }
    }
}
