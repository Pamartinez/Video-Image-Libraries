package io.grpc.stub;

import Gd.a;
import He.F;
import ee.C0984q;
import ee.M;
import ee.a0;
import ee.b0;
import ee.c0;
import io.grpc.CallOptions;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class i {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f4634a = Logger.getLogger(i.class.getName());
    public static final boolean b;

    /* renamed from: c  reason: collision with root package name */
    public static final CallOptions.Key f4635c = new CallOptions.Key("internal-stub-type");

    static {
        boolean z;
        if (a.h0(System.getenv("GRPC_CLIENT_CALL_REJECT_RUNNABLE")) || !Boolean.parseBoolean(System.getenv("GRPC_CLIENT_CALL_REJECT_RUNNABLE"))) {
            z = false;
        } else {
            z = true;
        }
        b = z;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [ee.M, java.lang.Object] */
    public static void a(C0984q qVar, Object obj, f fVar) {
        qVar.l(fVar, new Object());
        switch (fVar.f4633c) {
            case 0:
                ((d) fVar.f).f4631a.h(2);
                break;
            default:
                ((e) fVar.e).d.h(2);
                break;
        }
        try {
            qVar.i(obj);
            qVar.d();
        } catch (Error | RuntimeException e) {
            c(qVar, e);
            throw null;
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [io.grpc.stub.h, java.lang.Object, java.util.concurrent.ConcurrentLinkedQueue] */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r3.b("Thread interrupted", r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0041, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        r5 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        c(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005c, code lost:
        throw null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005d, code lost:
        if (r4 != false) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005f, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0034, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0036, code lost:
        r5 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0038, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0036 A[ExcHandler: Error | RuntimeException (e java.lang.Throwable), PHI: r4 
      PHI: (r4v5 boolean) = (r4v1 boolean), (r4v6 boolean), (r4v6 boolean), (r4v6 boolean) binds: [B:1:0x001d, B:18:0x0047, B:5:0x0030, B:6:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x001d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object b(io.grpc.Channel r3, io.grpc.MethodDescriptor r4, io.grpc.CallOptions r5, com.google.protobuf.GeneratedMessageLite r6) {
        /*
            io.grpc.stub.h r0 = new io.grpc.stub.h
            r0.<init>()
            io.grpc.CallOptions$Key r1 = f4635c
            io.grpc.stub.g r2 = io.grpc.stub.g.BLOCKING
            io.grpc.CallOptions r5 = r5.e(r1, r2)
            Df.n r5 = io.grpc.CallOptions.b(r5)
            r5.b = r0
            io.grpc.CallOptions r1 = new io.grpc.CallOptions
            r1.<init>(r5)
            ee.q r3 = r3.g(r4, r1)
            r4 = 0
            io.grpc.stub.e r5 = new io.grpc.stub.e     // Catch:{ Error | RuntimeException -> 0x0036 }
            r5.<init>(r3)     // Catch:{ Error | RuntimeException -> 0x0036 }
            io.grpc.stub.f r1 = new io.grpc.stub.f     // Catch:{ Error | RuntimeException -> 0x0036 }
            r1.<init>(r5)     // Catch:{ Error | RuntimeException -> 0x0036 }
            a(r3, r6, r1)     // Catch:{ Error | RuntimeException -> 0x0036 }
        L_0x002a:
            boolean r6 = r5.isDone()     // Catch:{ Error | RuntimeException -> 0x0036 }
            if (r6 != 0) goto L_0x0047
            r0.p()     // Catch:{ InterruptedException -> 0x0038, Error | RuntimeException -> 0x0036 }
            goto L_0x002a
        L_0x0034:
            r3 = move-exception
            goto L_0x005d
        L_0x0036:
            r5 = move-exception
            goto L_0x0058
        L_0x0038:
            r4 = move-exception
            r6 = 1
            java.lang.String r1 = "Thread interrupted"
            r3.b(r1, r4)     // Catch:{ Error | RuntimeException -> 0x0044, all -> 0x0041 }
            r4 = r6
            goto L_0x002a
        L_0x0041:
            r3 = move-exception
            r4 = r6
            goto L_0x005d
        L_0x0044:
            r5 = move-exception
            r4 = r6
            goto L_0x0058
        L_0x0047:
            r0.shutdown()     // Catch:{ Error | RuntimeException -> 0x0036 }
            java.lang.Object r3 = d(r5)     // Catch:{ Error | RuntimeException -> 0x0036 }
            if (r4 == 0) goto L_0x0057
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            r4.interrupt()
        L_0x0057:
            return r3
        L_0x0058:
            c(r3, r5)     // Catch:{ all -> 0x0034 }
            r3 = 0
            throw r3     // Catch:{ all -> 0x0034 }
        L_0x005d:
            if (r4 == 0) goto L_0x0066
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            r4.interrupt()
        L_0x0066:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.stub.i.b(io.grpc.Channel, io.grpc.MethodDescriptor, io.grpc.CallOptions, com.google.protobuf.GeneratedMessageLite):java.lang.Object");
    }

    public static void c(C0984q qVar, Throwable th) {
        try {
            qVar.b((String) null, th);
        } catch (Error | RuntimeException e) {
            f4634a.log(Level.SEVERE, "RuntimeException encountered while closing call", e);
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            throw new AssertionError(th);
        }
    }

    public static Object d(e eVar) {
        c0 a7;
        try {
            return eVar.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw a0.f.g("Thread interrupted").f(e).a();
        } catch (ExecutionException e7) {
            Throwable cause = e7.getCause();
            F.n(cause, "t");
            Throwable th = cause;
            while (true) {
                if (th != null) {
                    if (!(th instanceof b0)) {
                        if (th instanceof c0) {
                            c0 c0Var = (c0) th;
                            a7 = new c0(c0Var.d, c0Var.e);
                            break;
                        }
                        th = th.getCause();
                    } else {
                        a7 = new c0(((b0) th).d, (M) null);
                        break;
                    }
                } else {
                    a7 = a0.g.g("unexpected exception").f(cause).a();
                    break;
                }
            }
            throw a7;
        }
    }
}
