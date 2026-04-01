package fe;

import D0.f;
import He.F;
import Kd.a;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo;
import ee.C0968a;
import ee.C0969b;
import ee.C0971d;
import ee.C0984q;
import ee.C0990x;
import ee.M;
import ee.O;
import ee.W;
import ee.a0;
import ee.b0;
import ge.C1064x;
import ge.I1;
import ge.K1;
import ge.L1;
import ge.M1;
import ge.O0;
import ge.T;
import ge.Z;
import ge.r;
import io.grpc.CallOptions;
import io.grpc.MethodDescriptor;
import io.grpc.binder.b;
import io.grpc.binder.e;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import o1.C0246a;
import t1.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements C1064x, IBinder.DeathRecipient {
    public static final Logger v = Logger.getLogger(c.class.getName());
    public static final C0968a w = new C0968a("internal:remote-uid");

    /* renamed from: x  reason: collision with root package name */
    public static final C0968a f4336x = new C0968a("internal:inbound-parcelable-policy");

    /* renamed from: a  reason: collision with root package name */
    public final G0.c f4337a;
    public final ScheduledExecutorService b;

    /* renamed from: c  reason: collision with root package name */
    public final C0990x f4338c;
    public final l d;
    public final ConcurrentHashMap e;
    public final LinkedHashSet f = new LinkedHashSet();
    public C0969b g;

    /* renamed from: h  reason: collision with root package name */
    public d f4339h = d.NOT_STARTED;

    /* renamed from: i  reason: collision with root package name */
    public a0 f4340i;

    /* renamed from: j  reason: collision with root package name */
    public o f4341j;
    public final g k;
    public final AtomicLong l;
    public long m;
    public final G0.c n;

    /* renamed from: o  reason: collision with root package name */
    public final Executor f4342o;

    /* renamed from: p  reason: collision with root package name */
    public final com.samsung.context.sdk.samsunganalytics.internal.sender.c f4343p;
    public final x q;
    public final AtomicInteger r;
    public final i s;
    public a t;
    public int u;

    /* JADX WARNING: type inference failed for: r13v5, types: [android.os.Binder, fe.l] */
    /* JADX WARNING: type inference failed for: r13v7, types: [fe.g, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r13v15, types: [java.lang.Object, t1.i] */
    public c(Context context, ie.c cVar, io.grpc.binder.a aVar, b bVar, Executor executor, G0.c cVar2, G0.c cVar3, com.samsung.context.sdk.samsunganalytics.internal.sender.c cVar4, e eVar, C0969b bVar2) {
        D0.e eVar2 = new D0.e(C0969b.b);
        eVar2.X(I1.f4447a, W.NONE);
        eVar2.X(I1.b, bVar2);
        int i2 = io.grpc.binder.a.e;
        eVar2.X(C0971d.b, new io.grpc.binder.a(new Intent("grpc.io.action.BIND").setComponent(new ComponentName(context, context.getClass()))));
        eVar2.X(C0971d.f4293a, aVar);
        eVar2.X(f4336x, eVar);
        C0969b A10 = eVar2.A();
        C0990x a7 = C0990x.a(c.class, context.getClass().getSimpleName() + "->" + aVar);
        this.f4337a = cVar2;
        this.g = A10;
        this.f4338c = a7;
        this.b = (ScheduledExecutorService) L1.a((K1) cVar2.e);
        ? binder = new Binder();
        binder.f4359a = this;
        this.d = binder;
        this.e = new ConcurrentHashMap();
        this.k = new Object();
        this.l = new AtomicLong();
        this.u = 1001;
        this.n = cVar3;
        this.f4343p = cVar4;
        this.f4342o = (Executor) L1.a((K1) cVar3.e);
        this.r = new AtomicInteger();
        this.s = new Object();
        Executor executor2 = executor;
        this.q = new x(executor2, context, cVar, aVar.d.cloneFilter(), bVar.f4623a, this);
    }

    public static a0 p(RemoteException remoteException) {
        if ((remoteException instanceof DeadObjectException) || (remoteException instanceof TransactionTooLargeException)) {
            return a0.f4289o.f(remoteException);
        }
        return a0.n.f(remoteException);
    }

    public final synchronized r a(MethodDescriptor methodDescriptor, M m4, CallOptions callOptions, io.grpc.a[] aVarArr) {
        Throwable th;
        c cVar;
        q qVar;
        O o2;
        a0 a0Var;
        try {
            int i2 = 0;
            if (!j(d.READY)) {
                try {
                    if (l()) {
                        a0Var = this.f4340i;
                    } else {
                        a0Var = a0.n.g("newStream() before transportReady()");
                    }
                    M1 m12 = new M1(aVarArr);
                    for (io.grpc.a aVar : aVarArr) {
                        aVar.getClass();
                    }
                    C0984q[] qVarArr = m12.f4461a;
                    int length = qVarArr.length;
                    while (i2 < length) {
                        ((io.grpc.a) qVarArr[i2]).getClass();
                        i2++;
                    }
                    return new T(a0Var, aVarArr);
                } catch (Throwable th2) {
                    th = th2;
                    cVar = this;
                    throw th;
                }
            } else {
                int i7 = this.u;
                int i8 = i7 + 1;
                this.u = i8;
                if (i8 == 16777215) {
                    this.u = 1001;
                }
                try {
                    M1 m13 = new M1(aVarArr);
                    for (io.grpc.a aVar2 : aVarArr) {
                        aVar2.getClass();
                    }
                    C0969b bVar = this.g;
                    try {
                        boolean equals = Boolean.TRUE.equals(callOptions.a(Z.f4493j));
                        i iVar = new i(this, bVar, i7, !equals);
                        if (this.e.putIfAbsent(Integer.valueOf(i7), iVar) != null) {
                            a0 g3 = a0.n.g("Clashing call IDs");
                            o(g3, true);
                            M1 m14 = new M1(aVarArr);
                            for (io.grpc.a aVar3 : aVarArr) {
                                aVar3.getClass();
                            }
                            C0984q[] qVarArr2 = m14.f4461a;
                            int length2 = qVarArr2.length;
                            while (i2 < length2) {
                                ((io.grpc.a) qVarArr2[i2]).getClass();
                                i2++;
                            }
                            return new T(g3, aVarArr);
                        }
                        if (!equals) {
                            if (this.r.getAndIncrement() == 0) {
                                this.t.g(true);
                            }
                        }
                        cVar = this;
                        MethodDescriptor methodDescriptor2 = methodDescriptor;
                        try {
                            qVar = new q(cVar, i7, methodDescriptor2, m4, m13);
                            o2 = methodDescriptor2.f4620a;
                        } catch (Throwable th3) {
                            th = th3;
                            th = th;
                            throw th;
                        }
                        try {
                            o2.getClass();
                            try {
                                if (o2 == O.UNARY || o2 == O.SERVER_STREAMING) {
                                    i2 = 1;
                                }
                                if (i2 != 0) {
                                    f fVar = new f(iVar, qVar);
                                    return fVar;
                                }
                                D0.e eVar = new D0.e(24, (Object) iVar, (Object) qVar);
                                return eVar;
                            } catch (Throwable th4) {
                                th = th4;
                                th = th;
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            th = th;
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        cVar = this;
                        th = th;
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    cVar = this;
                    th = th;
                    throw th;
                }
            }
        } catch (Throwable th8) {
            th = th8;
            cVar = this;
            th = th;
            throw th;
        }
    }

    public final synchronized void b(a0 a0Var) {
        F.n(a0Var, KeywordInfo.EXTRA_BUNDLE_KEY_REASON);
        o(a0Var, true);
    }

    public final synchronized void binderDied() {
        o(a0.f4289o.g("binderDied"), true);
    }

    public final C0990x c() {
        return this.f4338c;
    }

    public final synchronized void d(a0 a0Var) {
        F.n(a0Var, KeywordInfo.EXTRA_BUNDLE_KEY_REASON);
        o(a0Var, false);
    }

    public final synchronized Runnable e(O0 o0) {
        this.t = (a) o0;
        return new C0998a(this, 1);
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public final void f(long r8) {
        /*
            r7 = this;
            fe.g r0 = r7.k
            monitor-enter(r0)
            long r1 = r0.b     // Catch:{ all -> 0x008c }
            long r3 = r1 - r8
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 >= 0) goto L_0x000e
            goto L_0x000f
        L_0x000e:
            r8 = r1
        L_0x000f:
            r0.b = r8     // Catch:{ all -> 0x008c }
            long r1 = r0.f4347a     // Catch:{ all -> 0x008c }
            long r1 = r1 - r8
            r8 = 131072(0x20000, float:1.83671E-40)
            long r8 = (long) r8     // Catch:{ all -> 0x008c }
            int r8 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r8 >= 0) goto L_0x008e
            boolean r8 = r0.f4348c     // Catch:{ all -> 0x008c }
            if (r8 == 0) goto L_0x008e
            r8 = 0
            r0.f4348c = r8     // Catch:{ all -> 0x008c }
            monitor-exit(r0)
            java.util.logging.Logger r9 = v
            java.util.logging.Level r0 = java.util.logging.Level.FINE
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "handleAcknowledgedBytes: Transmit Window No-Longer Full. Unblock calls: "
            r1.<init>(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r9.log(r0, r1)
            java.util.LinkedHashSet r9 = r7.f
            java.util.concurrent.ConcurrentHashMap r0 = r7.e
            java.util.Set r0 = r0.keySet()
            r9.addAll(r0)
            java.util.LinkedHashSet r9 = r7.f
            java.util.Iterator r9 = r9.iterator()
        L_0x0049:
            boolean r0 = r7.k()
            if (r0 == 0) goto L_0x008b
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x008b
            java.util.concurrent.ConcurrentHashMap r0 = r7.e
            java.lang.Object r1 = r9.next()
            java.lang.Object r0 = r0.get(r1)
            fe.i r0 = (fe.i) r0
            r9.remove()
            if (r0 == 0) goto L_0x0049
            monitor-enter(r0)
            fe.q r1 = r0.d     // Catch:{ all -> 0x0088 }
            ge.t r2 = r0.f     // Catch:{ all -> 0x0088 }
            monitor-exit(r0)     // Catch:{ all -> 0x0088 }
            if (r2 == 0) goto L_0x0071
            r2.b0()
        L_0x0071:
            if (r1 == 0) goto L_0x0049
            monitor-enter(r1)     // Catch:{ b0 -> 0x007c }
            r1.c()     // Catch:{ all -> 0x0079 }
            monitor-exit(r1)     // Catch:{ all -> 0x0079 }
            goto L_0x0049
        L_0x0079:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0079 }
            throw r2     // Catch:{ b0 -> 0x007c }
        L_0x007c:
            r1 = move-exception
            monitor-enter(r0)
            ee.a0 r1 = r1.d     // Catch:{ all -> 0x0085 }
            r0.b(r1, r1, r8)     // Catch:{ all -> 0x0085 }
            monitor-exit(r0)     // Catch:{ all -> 0x0085 }
            goto L_0x0049
        L_0x0085:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0085 }
            throw r7
        L_0x0088:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0088 }
            throw r7
        L_0x008b:
            return
        L_0x008c:
            r7 = move-exception
            goto L_0x0090
        L_0x008e:
            monitor-exit(r0)
            return
        L_0x0090:
            monitor-exit(r0)     // Catch:{ all -> 0x008c }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: fe.c.f(long):void");
    }

    public final void g(Parcel parcel) {
        W w6;
        C0969b bVar = this.g;
        int callingUid = Binder.getCallingUid();
        bVar.getClass();
        D0.e eVar = new D0.e(bVar);
        eVar.X(w, Integer.valueOf(callingUid));
        if (callingUid == Process.myUid()) {
            w6 = W.PRIVACY_AND_INTEGRITY;
        } else {
            w6 = W.INTEGRITY;
        }
        eVar.X(I1.f4447a, w6);
        this.g = eVar.A();
        if (j(d.SETUP)) {
            int readInt = parcel.readInt();
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readInt != 1) {
                o(a0.f4289o.g("Wire format version mismatch"), true);
            } else if (readStrongBinder == null) {
                o(a0.f4289o.g("Malformed SETUP_TRANSPORT data"), true);
            } else {
                this.f4342o.execute(new com.samsung.o3dp.lib3dphotography.i(17, this, readStrongBinder));
            }
        }
    }

    public final synchronized C0969b getAttributes() {
        return this.g;
    }

    public final boolean h(Parcel parcel, int i2) {
        try {
            return i(parcel, i2);
        } catch (RuntimeException e7) {
            Logger logger = v;
            Level level = Level.SEVERE;
            logger.log(level, "Terminating transport for uncaught Exception in transaction " + i2, e7);
            synchronized (this) {
                o(a0.n.f(e7), true);
                return false;
            }
        }
    }

    public final boolean i(Parcel parcel, int i2) {
        G0.a f5;
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        G0.a f8;
        if (i2 < 1001) {
            synchronized (this) {
                if (i2 == 1) {
                    g(parcel);
                } else if (i2 == 2) {
                    o(a0.f4289o.g("transport shutdown by peer"), true);
                } else if (i2 == 3) {
                    f(parcel.readLong());
                } else if (i2 == 4) {
                    int readInt = parcel.readInt();
                    if (this.f4339h == d.READY) {
                        try {
                            f8 = G0.a.f();
                            f8.c().writeInt(readInt);
                            this.f4341j.a(5, f8);
                            f8.close();
                        } catch (RemoteException unused) {
                        } catch (Throwable th) {
                            th.addSuppressed(th);
                        }
                    }
                } else if (i2 != 5) {
                    try {
                        return false;
                    } catch (Throwable th2) {
                        throw th2;
                    }
                } else {
                    i iVar = this.s;
                    parcel.readInt();
                    synchronized (iVar) {
                    }
                }
            }
        } else {
            int dataSize = parcel.dataSize();
            i iVar2 = (i) this.e.get(Integer.valueOf(i2));
            if (iVar2 == null) {
                synchronized (this) {
                    if (!l()) {
                        iVar2 = null;
                    }
                }
            }
            if (iVar2 != null) {
                synchronized (iVar2) {
                    if (iVar2.n == j.CLOSED) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        try {
                            int readInt2 = parcel.readInt();
                            if ((readInt2 & 8) != 0) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if (z3) {
                                a0 d2 = a0.d((readInt2 & 16711680) >> 16);
                                if ((readInt2 & 32) != 0) {
                                    d2 = d2.g(parcel.readString());
                                }
                                iVar2.b(d2, d2, true);
                            } else {
                                int readInt3 = parcel.readInt();
                                if ((readInt2 & 1) != 0) {
                                    z7 = true;
                                } else {
                                    z7 = false;
                                }
                                if ((readInt2 & 2) != 0) {
                                    z9 = true;
                                } else {
                                    z9 = false;
                                }
                                if ((readInt2 & 4) != 0) {
                                    z10 = true;
                                } else {
                                    z10 = false;
                                }
                                if (z7) {
                                    iVar2.e(parcel);
                                    iVar2.h(j.PREFIX_DELIVERED);
                                }
                                if (z9) {
                                    iVar2.d(readInt2, readInt3, parcel);
                                }
                                if (z10) {
                                    a0 d3 = a0.d((16711680 & readInt2) >> 16);
                                    if ((readInt2 & 32) != 0) {
                                        d3 = d3.g(parcel.readString());
                                    }
                                    iVar2.t = d3;
                                    iVar2.u = C0246a.i0(parcel, iVar2.b);
                                    iVar2.l = readInt3;
                                    iVar2.k = true;
                                }
                                int i7 = iVar2.f4352h;
                                if (readInt3 == i7) {
                                    ArrayList arrayList = iVar2.f4354j;
                                    if (arrayList == null) {
                                        iVar2.f4352h = i7 + 1;
                                    } else if (!z9 && !z10) {
                                        arrayList.remove(0);
                                        iVar2.f4352h++;
                                    }
                                }
                                iVar2.j(parcel.dataSize());
                                iVar2.c();
                            }
                        } catch (b0 e7) {
                            a0 a0Var = e7.d;
                            iVar2.b(a0Var, a0Var, false);
                        }
                    }
                }
            }
            if (this.l.addAndGet((long) dataSize) - this.m > 16384) {
                synchronized (this) {
                    o oVar = this.f4341j;
                    oVar.getClass();
                    long j2 = this.l.get();
                    this.m = j2;
                    try {
                        f5 = G0.a.f();
                        f5.c().writeLong(j2);
                        oVar.a(3, f5);
                        f5.close();
                    } catch (RemoteException e8) {
                        o(p(e8), true);
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
            }
        }
        return true;
        return true;
        throw th;
        throw th;
    }

    public final boolean j(d dVar) {
        if (this.f4339h == dVar) {
            return true;
        }
        return false;
    }

    public final boolean k() {
        return !this.k.f4348c;
    }

    public final boolean l() {
        if (j(d.SHUTDOWN) || j(d.SHUTDOWN_TERMINATED)) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0041, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m(int r5, G0.a r6) {
        /*
            r4 = this;
            android.os.Parcel r0 = r6.c()
            int r0 = r0.dataSize()
            fe.o r1 = r4.f4341j     // Catch:{ RemoteException -> 0x0044 }
            r1.a(r5, r6)     // Catch:{ RemoteException -> 0x0044 }
            fe.g r5 = r4.k
            long r0 = (long) r0
            monitor-enter(r5)
            long r2 = r5.f4347a     // Catch:{ all -> 0x003e }
            long r2 = r2 + r0
            r5.f4347a = r2     // Catch:{ all -> 0x003e }
            long r0 = r5.b     // Catch:{ all -> 0x003e }
            long r2 = r2 - r0
            r6 = 131072(0x20000, float:1.83671E-40)
            long r0 = (long) r6     // Catch:{ all -> 0x003e }
            int r6 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r6 < 0) goto L_0x0040
            boolean r6 = r5.f4348c     // Catch:{ all -> 0x003e }
            if (r6 != 0) goto L_0x0040
            r6 = 1
            r5.f4348c = r6     // Catch:{ all -> 0x003e }
            monitor-exit(r5)
            java.util.logging.Logger r5 = v
            java.util.logging.Level r6 = java.util.logging.Level.FINE
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "transmit window now full "
            r0.<init>(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r5.log(r6, r4)
            return
        L_0x003e:
            r4 = move-exception
            goto L_0x0042
        L_0x0040:
            monitor-exit(r5)
            return
        L_0x0042:
            monitor-exit(r5)     // Catch:{ all -> 0x003e }
            throw r4
        L_0x0044:
            r4 = move-exception
            ee.a0 r4 = p(r4)
            ee.b0 r5 = new ee.b0
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fe.c.m(int, G0.a):void");
    }

    public final void n(d dVar) {
        d dVar2 = this.f4339h;
        int i2 = b.f4335a[dVar.ordinal()];
        boolean z = false;
        if (i2 == 1) {
            if (dVar2 == d.NOT_STARTED) {
                z = true;
            }
            F.r(z);
        } else if (i2 == 2) {
            if (dVar2 == d.NOT_STARTED || dVar2 == d.SETUP) {
                z = true;
            }
            F.r(z);
        } else if (i2 == 3) {
            if (dVar2 == d.NOT_STARTED || dVar2 == d.SETUP || dVar2 == d.READY) {
                z = true;
            }
            F.r(z);
        } else if (i2 == 4) {
            if (dVar2 == d.SHUTDOWN) {
                z = true;
            }
            F.r(z);
        } else {
            throw new AssertionError();
        }
        this.f4339h = dVar;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x005f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void o(ee.a0 r7, boolean r8) {
        /*
            r6 = this;
            boolean r0 = r6.l()
            if (r0 != 0) goto L_0x003b
            r6.f4340i = r7
            fe.d r0 = fe.d.SHUTDOWN
            r6.n(r0)
            Kd.a r0 = r6.t
            java.lang.Object r1 = r0.f
            ge.i0 r1 = (ge.C1031i0) r1
            ee.d r2 = r1.f4519i
            ee.c r3 = ee.C0970c.INFO
            java.lang.Object r4 = r0.e
            ge.e0 r4 = (ge.C1019e0) r4
            ee.x r4 = r4.c()
            java.lang.String r5 = ge.C1031i0.i(r7)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r5}
            java.lang.String r5 = "{0} SHUTDOWN with {1}"
            r2.c(r3, r5, r4)
            r2 = 1
            r0.d = r2
            ee.e0 r1 = r1.k
            P1.e r2 = new P1.e
            r3 = 23
            r2.<init>(r3, r0, r7)
            r1.execute(r2)
        L_0x003b:
            fe.d r0 = fe.d.SHUTDOWN_TERMINATED
            boolean r1 = r6.j(r0)
            if (r1 != 0) goto L_0x0096
            java.util.concurrent.ConcurrentHashMap r1 = r6.e
            if (r8 != 0) goto L_0x004d
            boolean r8 = r1.isEmpty()
            if (r8 == 0) goto L_0x0096
        L_0x004d:
            fe.l r8 = r6.d
            r2 = 0
            r8.f4359a = r2
            r6.n(r0)
            fe.o r8 = r6.f4341j
            if (r8 == 0) goto L_0x007e
            r0 = 0
            android.os.IBinder r8 = r8.f4361a     // Catch:{ NoSuchElementException -> 0x005f }
            r8.unlinkToDeath(r6, r0)     // Catch:{ NoSuchElementException -> 0x005f }
        L_0x005f:
            G0.a r8 = G0.a.f()     // Catch:{ RemoteException -> 0x007e }
            android.os.Parcel r2 = r8.c()     // Catch:{ all -> 0x0074 }
            r2.writeInt(r0)     // Catch:{ all -> 0x0074 }
            fe.o r0 = r6.f4341j     // Catch:{ all -> 0x0074 }
            r2 = 2
            r0.a(r2, r8)     // Catch:{ all -> 0x0074 }
            r8.close()     // Catch:{ RemoteException -> 0x007e }
            goto L_0x007e
        L_0x0074:
            r0 = move-exception
            r8.close()     // Catch:{ all -> 0x0079 }
            goto L_0x007d
        L_0x0079:
            r8 = move-exception
            r0.addSuppressed(r8)     // Catch:{ RemoteException -> 0x007e }
        L_0x007d:
            throw r0     // Catch:{ RemoteException -> 0x007e }
        L_0x007e:
            java.util.ArrayList r8 = new java.util.ArrayList
            java.util.Collection r0 = r1.values()
            r8.<init>(r0)
            r1.clear()
            bc.d r0 = new bc.d
            r1 = 12
            r0.<init>((java.lang.Object) r6, (java.lang.Object) r8, (java.lang.Object) r7, (int) r1)
            java.util.concurrent.ScheduledExecutorService r6 = r6.b
            r6.execute(r0)
        L_0x0096:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fe.c.o(ee.a0, boolean):void");
    }
}
