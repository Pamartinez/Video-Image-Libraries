package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* renamed from: com.google.common.util.concurrent.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0127f extends C0122a {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f1566a;
    public final AtomicReferenceFieldUpdater b;

    /* renamed from: c  reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f1567c;
    public final AtomicReferenceFieldUpdater d;
    public final AtomicReferenceFieldUpdater e;

    public C0127f(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.f1566a = atomicReferenceFieldUpdater;
        this.b = atomicReferenceFieldUpdater2;
        this.f1567c = atomicReferenceFieldUpdater3;
        this.d = atomicReferenceFieldUpdater4;
        this.e = atomicReferenceFieldUpdater5;
    }

    public final boolean a(q qVar, C0126e eVar, C0126e eVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.d;
            if (atomicReferenceFieldUpdater.compareAndSet(qVar, eVar, eVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(qVar) == eVar);
        return false;
    }

    public final boolean b(q qVar, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.e;
            if (atomicReferenceFieldUpdater.compareAndSet(qVar, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(qVar) == obj);
        return false;
    }

    public final boolean c(q qVar, p pVar, p pVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f1567c;
            if (atomicReferenceFieldUpdater.compareAndSet(qVar, pVar, pVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(qVar) == pVar);
        return false;
    }

    public final C0126e d(q qVar) {
        return (C0126e) this.d.getAndSet(qVar, C0126e.d);
    }

    public final p e(q qVar) {
        return (p) this.f1567c.getAndSet(qVar, p.f1570c);
    }

    public final void f(p pVar, p pVar2) {
        this.b.lazySet(pVar, pVar2);
    }

    public final void g(p pVar, Thread thread) {
        this.f1566a.lazySet(pVar, thread);
    }
}
