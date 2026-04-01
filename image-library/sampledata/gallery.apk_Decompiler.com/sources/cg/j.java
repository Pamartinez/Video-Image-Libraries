package cg;

import Vf.D;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class j {
    public static final /* synthetic */ AtomicReferenceFieldUpdater d;
    public static final /* synthetic */ AtomicReferenceFieldUpdater e;
    public static final /* synthetic */ AtomicReferenceFieldUpdater f;
    private volatile /* synthetic */ Object _next$volatile = this;
    private volatile /* synthetic */ Object _prev$volatile = this;
    private volatile /* synthetic */ Object _removedRef$volatile;

    static {
        Class<j> cls = j.class;
        Class<Object> cls2 = Object.class;
        d = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_next$volatile");
        e = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_prev$volatile");
        f = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_removedRef$volatile");
    }

    public final boolean d(j jVar, int i2) {
        while (true) {
            j e7 = e();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
            if (e7 == null) {
                Object obj = atomicReferenceFieldUpdater.get(this);
                while (true) {
                    e7 = (j) obj;
                    if (!e7.h()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(e7);
                }
            }
            if (!(e7 instanceof h)) {
                atomicReferenceFieldUpdater.set(jVar, e7);
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = d;
                atomicReferenceFieldUpdater2.set(jVar, this);
                while (true) {
                    if (atomicReferenceFieldUpdater2.compareAndSet(e7, this, jVar)) {
                        jVar.f(this);
                        return true;
                    } else if (atomicReferenceFieldUpdater2.get(e7) != this) {
                    }
                }
            } else if ((((h) e7).g & i2) != 0 || !e7.d(jVar, i2)) {
                return false;
            } else {
                return true;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: cg.j} */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        r6 = ((cg.p) r6).f4031a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
        if (r5.compareAndSet(r4, r3, r6) == false) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0041, code lost:
        if (r5.get(r4) == r3) goto L_0x0035;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final cg.j e() {
        /*
            r9 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = e
            java.lang.Object r1 = r0.get(r9)
            cg.j r1 = (cg.j) r1
            r2 = 0
            r3 = r1
        L_0x000a:
            r4 = r2
        L_0x000b:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = d
            java.lang.Object r6 = r5.get(r3)
            if (r6 != r9) goto L_0x0024
            if (r1 != r3) goto L_0x0016
            return r3
        L_0x0016:
            boolean r2 = r0.compareAndSet(r9, r1, r3)
            if (r2 == 0) goto L_0x001d
            return r3
        L_0x001d:
            java.lang.Object r2 = r0.get(r9)
            if (r2 == r1) goto L_0x0016
            goto L_0x0000
        L_0x0024:
            boolean r7 = r9.h()
            if (r7 == 0) goto L_0x002b
            return r2
        L_0x002b:
            boolean r7 = r6 instanceof cg.p
            if (r7 == 0) goto L_0x004b
            if (r4 == 0) goto L_0x0044
            cg.p r6 = (cg.p) r6
            cg.j r6 = r6.f4031a
        L_0x0035:
            boolean r7 = r5.compareAndSet(r4, r3, r6)
            if (r7 == 0) goto L_0x003d
            r3 = r4
            goto L_0x000a
        L_0x003d:
            java.lang.Object r7 = r5.get(r4)
            if (r7 == r3) goto L_0x0035
            goto L_0x0000
        L_0x0044:
            java.lang.Object r3 = r0.get(r3)
            cg.j r3 = (cg.j) r3
            goto L_0x000b
        L_0x004b:
            java.lang.String r4 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode"
            kotlin.jvm.internal.j.c(r6, r4)
            r4 = r6
            cg.j r4 = (cg.j) r4
            r8 = r4
            r4 = r3
            r3 = r8
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: cg.j.e():cg.j");
    }

    public final void f(j jVar) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
            j jVar2 = (j) atomicReferenceFieldUpdater.get(jVar);
            if (d.get(this) == jVar) {
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(jVar, jVar2, this)) {
                        if (h()) {
                            jVar.e();
                            return;
                        }
                        return;
                    } else if (atomicReferenceFieldUpdater.get(jVar) != jVar2) {
                    }
                }
            } else {
                return;
            }
        }
    }

    public final j g() {
        p pVar;
        j jVar;
        Object obj = d.get(this);
        if (obj instanceof p) {
            pVar = (p) obj;
        } else {
            pVar = null;
        }
        if (pVar != null && (jVar = pVar.f4031a) != null) {
            return jVar;
        }
        kotlin.jvm.internal.j.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
        return (j) obj;
    }

    public boolean h() {
        return d.get(this) instanceof p;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(new q(this, D.class, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", 1));
        sb2.append('@');
        sb2.append(D.j(this));
        return sb2.toString();
    }
}
