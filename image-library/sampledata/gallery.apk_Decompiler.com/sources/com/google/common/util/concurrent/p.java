package com.google.common.util.concurrent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p {

    /* renamed from: c  reason: collision with root package name */
    public static final p f1570c = new Object();

    /* renamed from: a  reason: collision with root package name */
    public volatile Thread f1571a;
    public volatile p b;

    public p() {
        q.ATOMIC_HELPER.g(this, Thread.currentThread());
    }
}
