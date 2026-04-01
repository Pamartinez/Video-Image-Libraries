package ge;

import G0.e;
import ee.C0971d;
import ee.C0990x;
import ee.M;
import ee.a0;
import ee.e0;
import io.grpc.CallOptions;
import io.grpc.MethodDescriptor;
import io.grpc.a;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G implements P0 {

    /* renamed from: a  reason: collision with root package name */
    public final C0990x f4427a = C0990x.a(G.class, (String) null);
    public final Object b = new Object();

    /* renamed from: c  reason: collision with root package name */
    public final Executor f4428c;
    public final e0 d;
    public C1004E e;
    public C1004E f;
    public C1004E g;

    /* renamed from: h  reason: collision with root package name */
    public e f4429h;

    /* renamed from: i  reason: collision with root package name */
    public Collection f4430i = new LinkedHashSet();

    /* renamed from: j  reason: collision with root package name */
    public a0 f4431j;
    public C0971d k;
    public long l;

    public G(Executor executor, e0 e0Var) {
        this.f4428c = executor;
        this.d = e0Var;
    }

    public final r a(MethodDescriptor methodDescriptor, M m, CallOptions callOptions, a[] aVarArr) {
        r t;
        try {
            C1014c1 c1Var = new C1014c1(methodDescriptor, m, callOptions);
            C0971d dVar = null;
            long j2 = -1;
            while (true) {
                synchronized (this.b) {
                    a0 a0Var = this.f4431j;
                    if (a0Var == null) {
                        C0971d dVar2 = this.k;
                        if (dVar2 != null) {
                            if (dVar != null && j2 == this.l) {
                                t = f(c1Var, aVarArr);
                                break;
                            }
                            j2 = this.l;
                            C1058u d2 = Z.d(dVar2.j(), Boolean.TRUE.equals(callOptions.g));
                            if (d2 != null) {
                                t = d2.a(c1Var.f4499c, c1Var.b, c1Var.f4498a, aVarArr);
                                break;
                            }
                            dVar = dVar2;
                        } else {
                            t = f(c1Var, aVarArr);
                            break;
                        }
                    } else {
                        t = new T(a0Var, C1054s.PROCESSED, aVarArr);
                        break;
                    }
                }
            }
            this.d.a();
            return t;
        } catch (Throwable th) {
            this.d.a();
            throw th;
        }
    }

    public final void b(a0 a0Var) {
        Collection<C1005F> collection;
        C1004E e7;
        d(a0Var);
        synchronized (this.b) {
            try {
                collection = this.f4430i;
                e7 = this.g;
                this.g = null;
                if (!collection.isEmpty()) {
                    this.f4430i = Collections.EMPTY_LIST;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (e7 != null) {
            for (C1005F f5 : collection) {
                I l8 = f5.l(new T(a0Var, C1054s.REFUSED, f5.f4396o));
                if (l8 != null) {
                    l8.run();
                }
            }
            this.d.execute(e7);
        }
    }

    public final C0990x c() {
        return this.f4427a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        r4.d.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void d(ee.a0 r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.b
            monitor-enter(r0)
            ee.a0 r1 = r4.f4431j     // Catch:{ all -> 0x0009 }
            if (r1 == 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            return
        L_0x0009:
            r4 = move-exception
            goto L_0x0032
        L_0x000b:
            r4.f4431j = r5     // Catch:{ all -> 0x0009 }
            ee.e0 r1 = r4.d     // Catch:{ all -> 0x0009 }
            P1.e r2 = new P1.e     // Catch:{ all -> 0x0009 }
            r3 = 13
            r2.<init>(r3, r4, r5)     // Catch:{ all -> 0x0009 }
            r1.b(r2)     // Catch:{ all -> 0x0009 }
            boolean r5 = r4.g()     // Catch:{ all -> 0x0009 }
            if (r5 != 0) goto L_0x002b
            ge.E r5 = r4.g     // Catch:{ all -> 0x0009 }
            if (r5 == 0) goto L_0x002b
            ee.e0 r1 = r4.d     // Catch:{ all -> 0x0009 }
            r1.b(r5)     // Catch:{ all -> 0x0009 }
            r5 = 0
            r4.g = r5     // Catch:{ all -> 0x0009 }
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            ee.e0 r4 = r4.d
            r4.a()
            return
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.G.d(ee.a0):void");
    }

    public final Runnable e(O0 o0) {
        e eVar = (e) o0;
        this.f4429h = eVar;
        this.e = new C1004E(eVar, 0);
        this.f = new C1004E(eVar, 1);
        this.g = new C1004E(eVar, 2);
        return null;
    }

    public final C1005F f(C1014c1 c1Var, a[] aVarArr) {
        int size;
        C1005F f5 = new C1005F(this, c1Var, aVarArr);
        this.f4430i.add(f5);
        synchronized (this.b) {
            size = this.f4430i.size();
        }
        if (size == 1) {
            this.d.b(this.e);
        }
        for (a aVar : aVarArr) {
            aVar.getClass();
        }
        return f5;
    }

    public final boolean g() {
        boolean z;
        synchronized (this.b) {
            z = !this.f4430i.isEmpty();
        }
        return z;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        r0 = new java.util.ArrayList();
        r1 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        if (r1.hasNext() == false) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r2 = (ge.C1005F) r1.next();
        r3 = r2.m;
        r3 = r12.j();
        r4 = r2.m.f4498a;
        r3 = ge.Z.d(r3, java.lang.Boolean.TRUE.equals(r4.g));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        if (r3 == null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
        r5 = r11.f4428c;
        r4 = r4.b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        if (r4 == null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        r4 = r2.n;
        r6 = r4.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r7 = r2.m;
        r3 = r3.a(r7.f4499c, r7.b, r7.f4498a, r2.f4396o);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0066, code lost:
        r4.c(r6);
        r3 = r2.l(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006d, code lost:
        if (r3 == null) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006f, code lost:
        r5.execute(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0072, code lost:
        r0.add(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0076, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0077, code lost:
        r4.c(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007a, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007b, code lost:
        r12 = r11.b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007d, code lost:
        monitor-enter(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0082, code lost:
        if (g() != false) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0084, code lost:
        monitor-exit(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0085, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0086, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0088, code lost:
        r11.f4430i.removeAll(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0093, code lost:
        if (r11.f4430i.isEmpty() == false) goto L_0x009c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0095, code lost:
        r11.f4430i = new java.util.LinkedHashSet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a0, code lost:
        if (g() != false) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a2, code lost:
        r11.d.b(r11.f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ab, code lost:
        if (r11.f4431j == null) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ad, code lost:
        r0 = r11.g;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00af, code lost:
        if (r0 == null) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b1, code lost:
        r11.d.b(r0);
        r11.g = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b9, code lost:
        monitor-exit(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ba, code lost:
        r11.d.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00bf, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c1, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void h(ee.C0971d r12) {
        /*
            r11 = this;
            java.lang.Object r0 = r11.b
            monitor-enter(r0)
            r11.k = r12     // Catch:{ all -> 0x00c2 }
            long r1 = r11.l     // Catch:{ all -> 0x00c2 }
            r3 = 1
            long r1 = r1 + r3
            r11.l = r1     // Catch:{ all -> 0x00c2 }
            if (r12 == 0) goto L_0x00c4
            boolean r1 = r11.g()     // Catch:{ all -> 0x00c2 }
            if (r1 != 0) goto L_0x0016
            goto L_0x00c4
        L_0x0016:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x00c2 }
            java.util.Collection r2 = r11.f4430i     // Catch:{ all -> 0x00c2 }
            r1.<init>(r2)     // Catch:{ all -> 0x00c2 }
            monitor-exit(r0)     // Catch:{ all -> 0x00c2 }
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x0027:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x007b
            java.lang.Object r2 = r1.next()
            ge.F r2 = (ge.C1005F) r2
            ge.c1 r3 = r2.m
            ee.A r3 = r12.j()
            ge.c1 r4 = r2.m
            io.grpc.CallOptions r4 = r4.f4498a
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            java.lang.Boolean r6 = r4.g
            boolean r5 = r5.equals(r6)
            ge.u r3 = ge.Z.d(r3, r5)
            if (r3 == 0) goto L_0x0027
            java.util.concurrent.Executor r5 = r11.f4428c
            java.util.concurrent.Executor r4 = r4.b
            if (r4 == 0) goto L_0x0052
            r5 = r4
        L_0x0052:
            ee.l r4 = r2.n
            ee.l r6 = r4.a()
            ge.c1 r7 = r2.m     // Catch:{ all -> 0x0076 }
            io.grpc.MethodDescriptor r8 = r7.f4499c     // Catch:{ all -> 0x0076 }
            ee.M r9 = r7.b     // Catch:{ all -> 0x0076 }
            io.grpc.CallOptions r7 = r7.f4498a     // Catch:{ all -> 0x0076 }
            io.grpc.a[] r10 = r2.f4396o     // Catch:{ all -> 0x0076 }
            ge.r r3 = r3.a(r8, r9, r7, r10)     // Catch:{ all -> 0x0076 }
            r4.c(r6)
            ge.I r3 = r2.l(r3)
            if (r3 == 0) goto L_0x0072
            r5.execute(r3)
        L_0x0072:
            r0.add(r2)
            goto L_0x0027
        L_0x0076:
            r11 = move-exception
            r4.c(r6)
            throw r11
        L_0x007b:
            java.lang.Object r12 = r11.b
            monitor-enter(r12)
            boolean r1 = r11.g()     // Catch:{ all -> 0x0086 }
            if (r1 != 0) goto L_0x0088
            monitor-exit(r12)     // Catch:{ all -> 0x0086 }
            return
        L_0x0086:
            r11 = move-exception
            goto L_0x00c0
        L_0x0088:
            java.util.Collection r1 = r11.f4430i     // Catch:{ all -> 0x0086 }
            r1.removeAll(r0)     // Catch:{ all -> 0x0086 }
            java.util.Collection r0 = r11.f4430i     // Catch:{ all -> 0x0086 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0086 }
            if (r0 == 0) goto L_0x009c
            java.util.LinkedHashSet r0 = new java.util.LinkedHashSet     // Catch:{ all -> 0x0086 }
            r0.<init>()     // Catch:{ all -> 0x0086 }
            r11.f4430i = r0     // Catch:{ all -> 0x0086 }
        L_0x009c:
            boolean r0 = r11.g()     // Catch:{ all -> 0x0086 }
            if (r0 != 0) goto L_0x00b9
            ee.e0 r0 = r11.d     // Catch:{ all -> 0x0086 }
            ge.E r1 = r11.f     // Catch:{ all -> 0x0086 }
            r0.b(r1)     // Catch:{ all -> 0x0086 }
            ee.a0 r0 = r11.f4431j     // Catch:{ all -> 0x0086 }
            if (r0 == 0) goto L_0x00b9
            ge.E r0 = r11.g     // Catch:{ all -> 0x0086 }
            if (r0 == 0) goto L_0x00b9
            ee.e0 r1 = r11.d     // Catch:{ all -> 0x0086 }
            r1.b(r0)     // Catch:{ all -> 0x0086 }
            r0 = 0
            r11.g = r0     // Catch:{ all -> 0x0086 }
        L_0x00b9:
            monitor-exit(r12)     // Catch:{ all -> 0x0086 }
            ee.e0 r11 = r11.d
            r11.a()
            return
        L_0x00c0:
            monitor-exit(r12)     // Catch:{ all -> 0x0086 }
            throw r11
        L_0x00c2:
            r11 = move-exception
            goto L_0x00c6
        L_0x00c4:
            monitor-exit(r0)     // Catch:{ all -> 0x00c2 }
            return
        L_0x00c6:
            monitor-exit(r0)     // Catch:{ all -> 0x00c2 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.G.h(ee.d):void");
    }
}
