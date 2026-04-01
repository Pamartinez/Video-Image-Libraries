package A0;

import D0.f;
import Dd.C0732c;
import He.F;
import Hf.B;
import Hf.C0754c;
import Hf.C0774x;
import Hf.Y;
import Jf.k;
import L1.g;
import Qe.C0820j;
import R2.p;
import We.E;
import We.q;
import We.z;
import Xd.a;
import a.C0068a;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SharedMemory;
import android.os.Trace;
import android.util.Log;
import b3.C0083a;
import be.C0913a;
import be.C0915c;
import cf.C0922a;
import cf.e;
import com.samsung.android.liveeffect.service.IHeifDecodingListener;
import com.samsung.android.liveeffect.service.c;
import com.samsung.android.liveeffect.service.d;
import ee.C0966C;
import ee.C0969b;
import ee.C0971d;
import ee.C0975h;
import ee.C0976i;
import ee.M;
import ee.O;
import ee.a0;
import ef.C0993a;
import ef.C0994b;
import fe.i;
import ge.C1039l;
import ge.C1045n;
import ge.C1051q;
import ge.C1054s;
import ge.C1056t;
import ge.U0;
import ge.V0;
import gf.C1073d;
import ie.C1097a;
import ie.b;
import io.grpc.ServiceDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import jf.C1107g;
import kotlin.jvm.internal.j;
import o1.C0246a;
import w1.r;
import yf.C1359c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class l implements a, C1056t, C0966C {

    /* renamed from: h  reason: collision with root package name */
    public static String f12h;
    public final /* synthetic */ int d;
    public Object e;
    public Object f;
    public Object g;

    public /* synthetic */ l(int i2) {
        this.d = i2;
    }

    public static int e(String str, String str2) {
        int i2;
        int i7;
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int max = Math.max(split.length, split2.length);
        for (int i8 = 0; i8 < max; i8++) {
            if (i8 < split.length) {
                i2 = Integer.parseInt(split[i8]);
            } else {
                i2 = 0;
            }
            if (i8 < split2.length) {
                i7 = Integer.parseInt(split2[i8]);
            } else {
                i7 = 0;
            }
            if (i2 < i7) {
                return -1;
            }
            if (i2 > i7) {
                return 1;
            }
        }
        return 0;
    }

    public void a(C0976i iVar) {
        V0.m.log(Level.FINE, "Received health status {0} for subchannel {1}", new Object[]{iVar, ((U0) this.f).f4481a});
        this.e = iVar;
        V0 v02 = (V0) this.g;
        if (v02.f.c() && ((U0) v02.e.get(v02.f.a())).f4482c == this) {
            v02.r((U0) this.f);
        }
    }

    /* JADX INFO: finally extract failed */
    public String b(Context context, Bitmap bitmap, int i2) {
        F.K("LiveEffectServiceConnector", "detect() - E");
        c(context);
        try {
            String a7 = ((c) d.a((IBinder) this.e)).a(bitmap, i2);
            q(context);
            F.K("LiveEffectServiceConnector", "detect() - X");
            return a7;
        } catch (Throwable th) {
            q(context);
            throw th;
        }
    }

    public void b0() {
        C1051q qVar = (C1051q) this.g;
        O o2 = qVar.d.f4620a;
        o2.getClass();
        if (o2 != O.UNARY && o2 != O.SERVER_STREAMING) {
            b.b();
            try {
                b.a();
                C1097a aVar = b.f4600a;
                aVar.getClass();
                qVar.e.execute(new C1045n(this));
                aVar.getClass();
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            return;
        }
        throw th;
    }

    public void c(Context context) {
        F.K("LiveEffectServiceConnector", "bind() - E, Version Info: 8.5.50");
        Intent intent = new Intent();
        intent.setClassName("com.samsung.android.liveeffectservice", "com.samsung.android.liveeffect.service.LiveEffectService");
        intent.setAction("com.samsung.android.liveeffectservice.REQUEST_SERVICE");
        if (context.getPackageManager().resolveService(intent, 0) != null) {
            this.f = new CountDownLatch(1);
            try {
                if (context.bindService(intent, 1, Executors.newSingleThreadExecutor(), (C0083a) this.g)) {
                    try {
                        if (((CountDownLatch) this.f).await(20, TimeUnit.SECONDS)) {
                            F.K("LiveEffectServiceConnector", "bind() - X");
                        } else {
                            F.D("LiveEffectServiceConnector", "Binding timeout after 20 seconds");
                            throw new RemoteException("Binding time out after 20 seconds");
                        }
                    } catch (InterruptedException e7) {
                        F.D("LiveEffectServiceConnector", "Timeout waiting for onServiceConnected()");
                        throw new RemoteException(e7.toString());
                    }
                } else {
                    F.D("LiveEffectServiceConnector", "bindService returned false, failed to bind to LiveEffectService");
                    throw new RemoteException("Failed to bind to liveEffectService");
                }
            } catch (SecurityException e8) {
                Log.w("LE-".concat("LiveEffectServiceConnector"), "SecurityException while binding to LiveEffectService. This is expected if the service is a system service not available to this app. " + e8.getMessage());
                throw new RemoteException("SecurityException: Not allowed to bind to LiveEffectService. " + e8.getMessage());
            }
        } else {
            F.D("LiveEffectServiceConnector", "LiveEffectService is not installed or not available");
            throw new RemoteException("LiveEffectService is not installed or not available");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: ee.M} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: ee.M} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: ee.M} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: ee.M} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: ee.M} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d(ee.a0 r5, ee.M r6) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.g
            ge.q r0 = (ge.C1051q) r0
            io.grpc.CallOptions r1 = r0.k
            io.grpc.Deadline r1 = r1.f4612a
            ee.l r2 = r0.f4536h
            r2.getClass()
            if (r1 != 0) goto L_0x0010
            r1 = 0
        L_0x0010:
            ee.Y r2 = r5.f4290a
            ee.Y r3 = ee.Y.CANCELLED
            if (r2 != r3) goto L_0x0043
            if (r1 == 0) goto L_0x0043
            boolean r1 = r1.a()
            if (r1 == 0) goto L_0x0043
            G0.c r5 = new G0.c
            r6 = 11
            r5.<init>(r6)
            ge.r r6 = r0.l
            r6.e(r5)
            ee.a0 r6 = ee.a0.f4286h
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "ClientCall was cancelled at or after deadline. "
            r1.<init>(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            ee.a0 r5 = r6.b(r5)
            ee.M r6 = new ee.M
            r6.<init>()
        L_0x0043:
            ie.a r1 = ie.b.f4600a
            r1.getClass()
            java.util.concurrent.Executor r0 = r0.e
            ge.m r1 = new ge.m
            r1.<init>(r4, r5, r6)
            r0.execute(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: A0.l.d(ee.a0, ee.M):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: Re.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: Re.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: Re.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v17, resolved type: Re.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v14, resolved type: java.util.ArrayList} */
    /* JADX WARNING: type inference failed for: r4v19, types: [Hf.M] */
    /* JADX WARNING: type inference failed for: r11v18, types: [me.f, java.lang.Object] */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0136, code lost:
        if (r7 != Hf.d0.OUT_VARIANCE) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0210, code lost:
        if (r2.isEmpty() == false) goto L_0x0215;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Hf.B f(We.q r23, ef.C0993a r24, Hf.B r25) {
        /*
            r22 = this;
            r1 = r22
            r5 = r23
            r0 = r24
            r2 = r25
            Hf.Y r3 = r0.f4317a
            ef.b r4 = r0.b
            boolean r6 = r0.d
            java.lang.Object r7 = r1.e
            B0.a r7 = (B0.a) r7
            java.lang.Object r8 = r7.d
            cf.a r8 = (cf.C0922a) r8
            r9 = 0
            if (r2 == 0) goto L_0x001f
            Hf.I r10 = r2.p0()
            if (r10 != 0) goto L_0x0028
        L_0x001f:
            cf.c r10 = new cf.c
            r10.<init>(r7, r5, r9)
            Hf.I r10 = Hf.C0754c.C(r10)
        L_0x0028:
            We.s r11 = r5.b
            java.lang.String r12 = "Type not found: "
            if (r11 == 0) goto L_0x041d
            boolean r13 = r11 instanceof We.o
            java.lang.Class<java.lang.Object> r14 = java.lang.Object.class
            java.lang.String r15 = "getUpperBounds(...)"
            r16 = r9
            java.lang.String r9 = "getParameters(...)"
            r17 = r6
            r18 = 1
            if (r13 == 0) goto L_0x01a4
            r13 = r11
            We.o r13 = (We.o) r13
            r19 = 0
            qf.c r6 = r13.c()
            if (r6 == 0) goto L_0x0190
            if (r17 == 0) goto L_0x00a8
            qf.c r11 = ef.C0996d.f4320a
            boolean r11 = r6.equals(r11)
            if (r11 == 0) goto L_0x00a8
            Ne.o r6 = r8.f4012p
            Ne.n r11 = r6.f3550c
            He.t[] r20 = Ne.o.e
            r21 = r11
            r11 = r20[r16]
            r21.getClass()
            r20 = r10
            java.lang.String r10 = "property"
            kotlin.jvm.internal.j.e(r11, r10)
            java.lang.String r10 = r11.getName()
            java.lang.String r10 = B1.a.e(r10)
            qf.g r10 = qf.C1240g.e(r10)
            java.lang.Object r11 = r6.b
            java.lang.Object r11 = r11.getValue()
            Af.p r11 = (Af.p) r11
            r21 = r7
            Ye.c r7 = Ye.c.FROM_REFLECTION
            Qe.i r7 = r11.c(r10, r7)
            boolean r11 = r7 instanceof Qe.C0816f
            if (r11 == 0) goto L_0x008a
            Qe.f r7 = (Qe.C0816f) r7
            goto L_0x008c
        L_0x008a:
            r7 = r19
        L_0x008c:
            if (r7 != 0) goto L_0x00a5
            D0.f r6 = r6.f3549a
            qf.b r7 = new qf.b
            qf.c r11 = Ne.q.f3577i
            r7.<init>(r11, r10)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r18)
            java.util.List r10 = o1.C0246a.e0(r10)
            Qe.f r6 = r6.E(r7, r10)
            goto L_0x0156
        L_0x00a5:
            r6 = r7
            goto L_0x0156
        L_0x00a8:
            r21 = r7
            r20 = r10
            Qe.C r7 = r8.f4011o
            Ne.i r7 = r7.f()
            Qe.f r6 = Pe.e.c(r6, r7)
            if (r6 != 0) goto L_0x00bc
            r6 = r19
            goto L_0x0156
        L_0x00bc:
            java.lang.String r7 = Pe.d.f3633a
            qf.e r7 = tf.C1301e.g(r6)
            java.util.HashMap r10 = Pe.d.k
            boolean r7 = r10.containsKey(r7)
            if (r7 == 0) goto L_0x0156
            ef.b r7 = ef.C0994b.FLEXIBLE_LOWER_BOUND
            if (r4 == r7) goto L_0x0152
            Hf.Y r7 = Hf.Y.SUPERTYPE
            if (r3 == r7) goto L_0x0152
            java.util.ArrayList r7 = r5.c()
            java.lang.Object r7 = ne.C1194l.U0(r7)
            gf.d r7 = (gf.C1073d) r7
            boolean r11 = r7 instanceof We.E
            if (r11 == 0) goto L_0x00e3
            We.E r7 = (We.E) r7
            goto L_0x00e5
        L_0x00e3:
            r7 = r19
        L_0x00e5:
            if (r7 == 0) goto L_0x0156
            We.B r11 = r7.c()
            if (r11 == 0) goto L_0x0156
            java.lang.reflect.WildcardType r7 = r7.f3881a
            java.lang.reflect.Type[] r7 = r7.getUpperBounds()
            kotlin.jvm.internal.j.d(r7, r15)
            java.lang.Object r7 = ne.C1192j.n0(r7)
            boolean r7 = kotlin.jvm.internal.j.a(r7, r14)
            if (r7 == 0) goto L_0x0156
            qf.e r7 = tf.C1301e.g(r6)
            java.lang.String r11 = Pe.d.f3633a
            java.lang.Object r7 = r10.get(r7)
            qf.c r7 = (qf.C1236c) r7
            if (r7 == 0) goto L_0x0139
            Ne.i r10 = xf.C1353d.e(r6)
            Qe.f r7 = r10.i(r7)
            java.lang.String r10 = "getBuiltInClassByFqName(...)"
            kotlin.jvm.internal.j.d(r7, r10)
            Hf.M r7 = r7.q()
            java.util.List r7 = r7.getParameters()
            kotlin.jvm.internal.j.d(r7, r9)
            java.lang.Object r7 = ne.C1194l.U0(r7)
            Qe.V r7 = (Qe.V) r7
            if (r7 == 0) goto L_0x0156
            Hf.d0 r7 = r7.t()
            if (r7 == 0) goto L_0x0156
            Hf.d0 r10 = Hf.d0.OUT_VARIANCE
            if (r7 == r10) goto L_0x0156
            goto L_0x0152
        L_0x0139:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Given class "
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r2 = " is not a read-only collection"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0152:
            Qe.f r6 = Pe.e.b(r6)
        L_0x0156:
            if (r6 != 0) goto L_0x016e
            B1.b r6 = r8.k
            r6.getClass()
            java.lang.Object r6 = r6.e
            G0.c r6 = (G0.c) r6
            if (r6 == 0) goto L_0x0168
            Qe.f r6 = r6.A(r13)
            goto L_0x016e
        L_0x0168:
            java.lang.String r0 = "resolver"
            kotlin.jvm.internal.j.k(r0)
            throw r19
        L_0x016e:
            if (r6 == 0) goto L_0x0177
            Hf.M r6 = r6.q()
            if (r6 == 0) goto L_0x0177
            goto L_0x01c1
        L_0x0177:
            qf.c r0 = new qf.c
            r5.getClass()
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r12)
            java.lang.reflect.Type r2 = r5.f3892a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0190:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Class type should have a FQ name: "
            r0.<init>(r1)
            r0.append(r11)
            java.lang.String r0 = r0.toString()
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>(r0)
            throw r1
        L_0x01a4:
            r21 = r7
            r20 = r10
            r19 = 0
            boolean r6 = r11 instanceof We.C
            if (r6 == 0) goto L_0x0409
            java.lang.Object r6 = r1.f
            cf.e r6 = (cf.e) r6
            We.C r11 = (We.C) r11
            Qe.V r6 = r6.a(r11)
            if (r6 == 0) goto L_0x01bf
            Hf.M r6 = r6.q()
            goto L_0x01c1
        L_0x01bf:
            r6 = r19
        L_0x01c1:
            if (r6 != 0) goto L_0x01c4
            return r19
        L_0x01c4:
            ef.b r7 = ef.C0994b.FLEXIBLE_LOWER_BOUND
            if (r4 != r7) goto L_0x01cb
        L_0x01c8:
            r7 = r16
            goto L_0x01d3
        L_0x01cb:
            if (r17 != 0) goto L_0x01c8
            Hf.Y r4 = Hf.Y.SUPERTYPE
            if (r3 == r4) goto L_0x01c8
            r7 = r18
        L_0x01d3:
            if (r2 == 0) goto L_0x01da
            Hf.M r3 = r2.s0()
            goto L_0x01dc
        L_0x01da:
            r3 = r19
        L_0x01dc:
            boolean r3 = kotlin.jvm.internal.j.a(r3, r6)
            if (r3 == 0) goto L_0x01f1
            boolean r3 = r5.d()
            if (r3 != 0) goto L_0x01f1
            if (r7 == 0) goto L_0x01f1
            r3 = r18
            Hf.B r0 = r2.y0(r3)
            return r0
        L_0x01f1:
            r3 = r18
            boolean r2 = r5.d()
            if (r2 != 0) goto L_0x0215
            java.util.ArrayList r2 = r5.c()
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0213
            java.util.List r2 = r6.getParameters()
            kotlin.jvm.internal.j.d(r2, r9)
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x0213
            goto L_0x0215
        L_0x0213:
            r3 = r16
        L_0x0215:
            java.util.List r2 = r6.getParameters()
            kotlin.jvm.internal.j.d(r2, r9)
            r4 = 10
            if (r3 == 0) goto L_0x0287
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r9 = new java.util.ArrayList
            int r3 = ne.C1196n.w0(r2, r4)
            r9.<init>(r3)
            java.util.Iterator r10 = r2.iterator()
        L_0x022f:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L_0x0282
            java.lang.Object r2 = r10.next()
            Qe.V r2 = (Qe.V) r2
            java.util.Set r3 = r0.e
            r4 = r19
            boolean r3 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.H(r2, r4, r3)
            if (r3 == 0) goto L_0x024d
            Hf.P r2 = Hf.a0.k(r2, r0)
            r13 = r5
            r14 = r6
            r6 = r1
            goto L_0x0277
        L_0x024d:
            Hf.z r11 = new Hf.z
            Gf.p r12 = r8.f4006a
            ef.c r0 = new ef.c
            r3 = r24
            r4 = r6
            r0.<init>(r1, r2, r3, r4, r5)
            r6 = r1
            r15 = r2
            r14 = r4
            r13 = r5
            r11.<init>(r12, r0)
            boolean r2 = r13.d()
            r4 = 0
            r5 = 59
            r1 = 0
            r3 = 0
            r0 = r24
            ef.a r1 = ef.C0993a.a(r0, r1, r2, r3, r4, r5)
            java.lang.Object r0 = r6.g
            D0.e r0 = (D0.e) r0
            Hf.P r2 = ef.f.a(r15, r1, r0, r11)
        L_0x0277:
            r9.add(r2)
            r0 = r24
            r1 = r6
            r5 = r13
            r6 = r14
            r19 = 0
            goto L_0x022f
        L_0x0282:
            r0 = r6
        L_0x0283:
            r10 = r20
            goto L_0x0404
        L_0x0287:
            r13 = r5
            r0 = r6
            r6 = r1
            int r1 = r2.size()
            java.util.ArrayList r3 = r13.c()
            int r3 = r3.size()
            if (r1 == r3) goto L_0x02d3
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r1 = new java.util.ArrayList
            int r3 = ne.C1196n.w0(r2, r4)
            r1.<init>(r3)
            java.util.Iterator r2 = r2.iterator()
        L_0x02a7:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x02ce
            java.lang.Object r3 = r2.next()
            Qe.V r3 = (Qe.V) r3
            Hf.G r4 = new Hf.G
            Jf.k r5 = Jf.k.MISSED_TYPE_ARGUMENT_FOR_TYPE_PARAMETER
            qf.g r3 = r3.getName()
            java.lang.String r3 = r3.b()
            java.lang.String[] r3 = new java.lang.String[]{r3}
            Jf.i r3 = Jf.l.c(r5, r3)
            r4.<init>((Hf.C0774x) r3)
            r1.add(r4)
            goto L_0x02a7
        L_0x02ce:
            java.util.List r9 = ne.C1194l.k1(r1)
            goto L_0x0283
        L_0x02d3:
            java.util.ArrayList r1 = r13.c()
            Sf.r r1 = ne.C1194l.q1(r1)
            java.util.ArrayList r3 = new java.util.ArrayList
            int r4 = ne.C1196n.w0(r1, r4)
            r3.<init>(r4)
            java.util.Iterator r1 = r1.iterator()
        L_0x02e8:
            r4 = r1
            Sf.b r4 = (Sf.b) r4
            java.util.Iterator r5 = r4.e
            boolean r5 = r5.hasNext()
            if (r5 == 0) goto L_0x03fe
            java.lang.Object r4 = r4.next()
            ne.x r4 = (ne.x) r4
            int r5 = r4.f4950a
            java.lang.Object r4 = r4.b
            gf.d r4 = (gf.C1073d) r4
            r2.size()
            java.lang.Object r5 = r2.get(r5)
            Qe.V r5 = (Qe.V) r5
            Hf.Y r8 = Hf.Y.COMMON
            r9 = 7
            r10 = r16
            r11 = 0
            ef.a r8 = a.C0068a.Y(r8, r10, r11, r9)
            kotlin.jvm.internal.j.b(r5)
            boolean r10 = r4 instanceof We.E
            if (r10 == 0) goto L_0x03de
            We.E r4 = (We.E) r4
            We.B r10 = r4.c()
            java.lang.reflect.WildcardType r11 = r4.f3881a
            java.lang.reflect.Type[] r11 = r11.getUpperBounds()
            kotlin.jvm.internal.j.d(r11, r15)
            java.lang.Object r11 = ne.C1192j.n0(r11)
            boolean r11 = kotlin.jvm.internal.j.a(r11, r14)
            if (r11 != 0) goto L_0x0335
            Hf.d0 r11 = Hf.d0.OUT_VARIANCE
            goto L_0x0337
        L_0x0335:
            Hf.d0 r11 = Hf.d0.IN_VARIANCE
        L_0x0337:
            if (r10 == 0) goto L_0x0348
            Hf.d0 r12 = r5.t()
            Hf.d0 r13 = Hf.d0.INVARIANT
            if (r12 != r13) goto L_0x0342
            goto L_0x0352
        L_0x0342:
            Hf.d0 r12 = r5.t()
            if (r11 == r12) goto L_0x0352
        L_0x0348:
            r24 = r1
            r25 = r2
            r12 = r21
            r9 = 0
            r13 = 0
            goto L_0x03d9
        L_0x0352:
            We.B r8 = r4.c()
            if (r8 == 0) goto L_0x03d1
            cf.c r8 = new cf.c
            r12 = r21
            r13 = 0
            r8.<init>(r12, r4, r13)
            java.util.Iterator r4 = r8.iterator()
        L_0x0364:
            r8 = r4
            Sf.f r8 = (Sf.f) r8
            boolean r13 = r8.hasNext()
            if (r13 == 0) goto L_0x039b
            java.lang.Object r8 = r8.next()
            r13 = r8
            Re.b r13 = (Re.b) r13
            qf.c[] r9 = Ze.q.b
            r24 = r1
            int r1 = r9.length
            r25 = r2
            r2 = 0
        L_0x037c:
            if (r2 >= r1) goto L_0x0395
            r17 = r1
            r1 = r9[r2]
            r18 = r2
            qf.c r2 = r13.b()
            boolean r1 = kotlin.jvm.internal.j.a(r2, r1)
            if (r1 == 0) goto L_0x0390
            r4 = r8
            goto L_0x03a0
        L_0x0390:
            int r2 = r18 + 1
            r1 = r17
            goto L_0x037c
        L_0x0395:
            r1 = r24
            r2 = r25
            r9 = 7
            goto L_0x0364
        L_0x039b:
            r24 = r1
            r25 = r2
            r4 = 0
        L_0x03a0:
            Re.b r4 = (Re.b) r4
            Hf.Y r1 = Hf.Y.COMMON
            r2 = 7
            r9 = 0
            r13 = 0
            ef.a r1 = a.C0068a.Y(r1, r13, r9, r2)
            Hf.x r1 = r6.p(r10, r1)
            if (r4 == 0) goto L_0x03cc
            Re.h r2 = r1.getAnnotations()
            java.util.ArrayList r2 = ne.C1194l.W0(r2, r4)
            boolean r4 = r2.isEmpty()
            if (r4 == 0) goto L_0x03c2
            Re.f r2 = Re.g.f3692a
            goto L_0x03c8
        L_0x03c2:
            Re.i r4 = new Re.i
            r4.<init>(r13, r2)
            r2 = r4
        L_0x03c8:
            Hf.x r1 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.L(r1, r2)
        L_0x03cc:
            Hf.G r1 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.p(r1, r11, r5)
            goto L_0x03f1
        L_0x03d1:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Nullability annotations on unbounded wildcards aren't supported"
            r0.<init>(r1)
            throw r0
        L_0x03d9:
            Hf.P r1 = Hf.a0.k(r5, r8)
            goto L_0x03f1
        L_0x03de:
            r24 = r1
            r25 = r2
            r12 = r21
            r9 = 0
            r13 = 0
            Hf.G r1 = new Hf.G
            Hf.d0 r2 = Hf.d0.INVARIANT
            Hf.x r4 = r6.p(r4, r8)
            r1.<init>(r4, r2)
        L_0x03f1:
            r3.add(r1)
            r1 = r24
            r2 = r25
            r21 = r12
            r16 = r13
            goto L_0x02e8
        L_0x03fe:
            java.util.List r9 = ne.C1194l.k1(r3)
            goto L_0x0283
        L_0x0404:
            Hf.B r0 = Hf.C0754c.u(r10, r0, r9, r7)
            return r0
        L_0x0409:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unknown classifier kind: "
            r1.<init>(r2)
            r1.append(r11)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x041d:
            r13 = r5
            qf.c r0 = new qf.c
            r13.getClass()
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r12)
            java.lang.reflect.Type r2 = r13.f3892a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: A0.l.f(We.q, ef.a, Hf.B):Hf.B");
    }

    /* JADX INFO: finally extract failed */
    public void g(Context context, SharedMemory sharedMemory, IHeifDecodingListener iHeifDecodingListener) {
        F.K("LiveEffectServiceConnector", "decodeHeif3d() - E");
        if (!n(context, "8.5.0.0")) {
            F.D("LiveEffectServiceConnector", "Exception at decodeMesh: HEIF support is not available in current version. Minimum required version: 8.5.0.0");
            F.K("LiveEffectServiceConnector", "decodeHeif3d() - X");
            return;
        }
        c(context);
        try {
            ((c) d.a((IBinder) this.e)).c(sharedMemory, iHeifDecodingListener);
            q(context);
            F.K("LiveEffectServiceConnector", "decodeHeif3d() - X");
        } catch (Throwable th) {
            q(context);
            throw th;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: Ed.b} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.os.SharedMemory} */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x008e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Ed.b h(android.content.Context r8, byte[] r9) {
        /*
            r7 = this;
            java.lang.String r0 = "Unable to setup SharedMemory segment for decoding: "
            java.lang.String r1 = "decodeMesh() - E"
            java.lang.String r2 = "LiveEffectServiceConnector"
            He.F.K(r2, r1)
            r7.c(r8)
            r1 = 0
            int r3 = r9.length     // Catch:{ ErrnoException -> 0x004a, all -> 0x0047 }
            if (r3 <= 0) goto L_0x0054
            java.lang.Object r3 = r7.e     // Catch:{ ErrnoException -> 0x0051, all -> 0x004e }
            android.os.IBinder r3 = (android.os.IBinder) r3     // Catch:{ ErrnoException -> 0x0051, all -> 0x004e }
            com.samsung.android.liveeffect.service.e r3 = com.samsung.android.liveeffect.service.d.a(r3)     // Catch:{ ErrnoException -> 0x004a, all -> 0x0047 }
            java.lang.String r4 = "CompressedMesh"
            int r5 = r9.length     // Catch:{ ErrnoException -> 0x004a, all -> 0x0047 }
            android.os.SharedMemory r4 = android.os.SharedMemory.create(r4, r5)     // Catch:{ ErrnoException -> 0x004a, all -> 0x0047 }
            java.nio.ByteBuffer r5 = r4.mapReadWrite()     // Catch:{ ErrnoException -> 0x0045, all -> 0x0043 }
            r5.put(r9)     // Catch:{ ErrnoException -> 0x0045, all -> 0x0043 }
            android.os.SharedMemory.unmap(r5)     // Catch:{ ErrnoException -> 0x0045, all -> 0x0043 }
            com.samsung.android.liveeffect.service.c r3 = (com.samsung.android.liveeffect.service.c) r3     // Catch:{ ErrnoException -> 0x003f, all -> 0x003c }
            android.os.SharedMemory r9 = r3.d(r4)     // Catch:{ ErrnoException -> 0x003f, all -> 0x003c }
            Ed.b r1 = Ed.b.t(r9)     // Catch:{ ErrnoException -> 0x003a }
            r6 = r1
            r1 = r9
            r9 = r6
            goto L_0x0056
        L_0x0037:
            r0 = move-exception
            r1 = r9
            goto L_0x0087
        L_0x003a:
            r3 = move-exception
            goto L_0x0065
        L_0x003c:
            r9 = move-exception
            r0 = r9
            goto L_0x0087
        L_0x003f:
            r9 = move-exception
            r3 = r9
        L_0x0041:
            r9 = r1
            goto L_0x0065
        L_0x0043:
            r0 = move-exception
            goto L_0x0087
        L_0x0045:
            r3 = move-exception
            goto L_0x0041
        L_0x0047:
            r0 = move-exception
        L_0x0048:
            r4 = r1
            goto L_0x0087
        L_0x004a:
            r3 = move-exception
        L_0x004b:
            r9 = r1
            r4 = r9
            goto L_0x0065
        L_0x004e:
            r9 = move-exception
            r0 = r9
            goto L_0x0048
        L_0x0051:
            r9 = move-exception
            r3 = r9
            goto L_0x004b
        L_0x0054:
            r9 = r1
            r4 = r9
        L_0x0056:
            if (r1 == 0) goto L_0x005b
            r1.close()
        L_0x005b:
            if (r4 == 0) goto L_0x0060
            r4.close()
        L_0x0060:
            r7.q(r8)
            r1 = r9
            goto L_0x0081
        L_0x0065:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0037 }
            r5.<init>(r0)     // Catch:{ all -> 0x0037 }
            r5.append(r3)     // Catch:{ all -> 0x0037 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0037 }
            He.F.D(r2, r0)     // Catch:{ all -> 0x0037 }
            if (r9 == 0) goto L_0x0079
            r9.close()
        L_0x0079:
            if (r4 == 0) goto L_0x007e
            r4.close()
        L_0x007e:
            r7.q(r8)
        L_0x0081:
            java.lang.String r7 = "decodeMesh() - X"
            He.F.K(r2, r7)
            return r1
        L_0x0087:
            if (r1 == 0) goto L_0x008c
            r1.close()
        L_0x008c:
            if (r4 == 0) goto L_0x0091
            r4.close()
        L_0x0091:
            r7.q(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: A0.l.h(android.content.Context, byte[]):Ed.b");
    }

    public void h0(i iVar) {
        C1051q qVar = (C1051q) this.g;
        b.b();
        try {
            b.a();
            C1097a aVar = b.f4600a;
            aVar.getClass();
            qVar.e.execute(new C1039l(this, iVar));
            aVar.getClass();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        if (r5 != null) goto L_0x003b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.SharedMemory i(android.content.Context r11, android.graphics.Bitmap r12, android.os.SharedMemory r13, byte[] r14) {
        /*
            r10 = this;
            java.lang.String r0 = "encodeBitmapToHeif3d() - E"
            java.lang.String r1 = "LiveEffectServiceConnector"
            He.F.K(r1, r0)
            java.lang.String r0 = "8.5.0.0"
            boolean r0 = r10.n(r11, r0)
            java.lang.String r2 = "encodeBitmapToHeif3d() - X"
            r3 = 0
            if (r0 != 0) goto L_0x001b
            java.lang.String r10 = "Exception at encodeBitmapToHeif3d: HEIF support is not available in current version. Minimum required version: 8.5.0.0"
            He.F.D(r1, r10)
            He.F.K(r1, r2)
            return r3
        L_0x001b:
            r10.c(r11)
            java.lang.Object r0 = r10.e     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            android.os.IBinder r0 = (android.os.IBinder) r0     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            com.samsung.android.liveeffect.service.e r0 = com.samsung.android.liveeffect.service.d.a(r0)     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            android.os.SharedMemory r5 = He.F.A(r12)     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            int r6 = r12.getWidth()     // Catch:{ Exception -> 0x0047 }
            int r7 = r12.getHeight()     // Catch:{ Exception -> 0x0047 }
            r4 = r0
            com.samsung.android.liveeffect.service.c r4 = (com.samsung.android.liveeffect.service.c) r4     // Catch:{ Exception -> 0x0047 }
            r8 = r13
            r9 = r14
            android.os.SharedMemory r3 = r4.e(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0047 }
        L_0x003b:
            r5.close()
        L_0x003e:
            r10.q(r11)
            goto L_0x005e
        L_0x0042:
            r3 = r5
            goto L_0x0062
        L_0x0044:
            r0 = move-exception
            r12 = r0
            goto L_0x0042
        L_0x0047:
            r0 = move-exception
            r12 = r0
            goto L_0x0050
        L_0x004a:
            r0 = move-exception
            r12 = r0
            goto L_0x0062
        L_0x004d:
            r0 = move-exception
            r12 = r0
            r5 = r3
        L_0x0050:
            java.lang.String r13 = "Exception at encodeBitmapToHeif3d"
            java.lang.String r14 = "LE-"
            java.lang.String r14 = r14.concat(r1)     // Catch:{ all -> 0x0044 }
            android.util.Log.e(r14, r13, r12)     // Catch:{ all -> 0x0044 }
            if (r5 == 0) goto L_0x003e
            goto L_0x003b
        L_0x005e:
            He.F.K(r1, r2)
            return r3
        L_0x0062:
            if (r3 == 0) goto L_0x0067
            r3.close()
        L_0x0067:
            r10.q(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: A0.l.i(android.content.Context, android.graphics.Bitmap, android.os.SharedMemory, byte[]):android.os.SharedMemory");
    }

    public SharedMemory j(Context context, SharedMemory sharedMemory, SharedMemory sharedMemory2, byte[] bArr) {
        F.K("LiveEffectServiceConnector", "encodeHeif3d() - E");
        SharedMemory sharedMemory3 = null;
        if (!n(context, "8.5.0.0")) {
            F.D("LiveEffectServiceConnector", "Exception at encodeMesh: HEIF support is not available in current version. Minimum required version: 8.5.0.0");
            F.K("LiveEffectServiceConnector", "encodeHeif3d() - X");
            return null;
        }
        c(context);
        try {
            sharedMemory3 = ((c) d.a((IBinder) this.e)).f(sharedMemory, sharedMemory2, bArr);
        } catch (Exception e7) {
            Log.e("LE-".concat("LiveEffectServiceConnector"), "Exception at encodeMesh", e7);
        } catch (Throwable th) {
            q(context);
            throw th;
        }
        q(context);
        F.K("LiveEffectServiceConnector", "encodeHeif3d() - X");
        return sharedMemory3;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: android.os.SharedMemory} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: android.os.SharedMemory} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] k(android.content.Context r8, Ed.b r9) {
        /*
            r7 = this;
            java.lang.String r0 = "Unable to setup SharedMemory segment for encoding: "
            java.lang.String r1 = "encodeMesh() - E"
            java.lang.String r2 = "LiveEffectServiceConnector"
            He.F.K(r2, r1)
            r7.c(r8)
            r1 = 0
            java.lang.Object r3 = r7.e     // Catch:{ ErrnoException -> 0x0054, all -> 0x0051 }
            android.os.IBinder r3 = (android.os.IBinder) r3     // Catch:{ ErrnoException -> 0x0054, all -> 0x0051 }
            com.samsung.android.liveeffect.service.e r3 = com.samsung.android.liveeffect.service.d.a(r3)     // Catch:{ ErrnoException -> 0x004d, all -> 0x004a }
            android.os.SharedMemory r9 = r9.A()     // Catch:{ ErrnoException -> 0x004d, all -> 0x004a }
            com.samsung.android.liveeffect.service.c r3 = (com.samsung.android.liveeffect.service.c) r3     // Catch:{ ErrnoException -> 0x0046 }
            android.os.SharedMemory r3 = r3.g(r9)     // Catch:{ ErrnoException -> 0x0046 }
            if (r3 == 0) goto L_0x003a
            java.nio.ByteBuffer r4 = r3.mapReadOnly()     // Catch:{ ErrnoException -> 0x0035, all -> 0x0032 }
            int r5 = r3.getSize()     // Catch:{ ErrnoException -> 0x0035, all -> 0x0032 }
            byte[] r1 = new byte[r5]     // Catch:{ ErrnoException -> 0x0035, all -> 0x0032 }
            r4.get(r1)     // Catch:{ ErrnoException -> 0x0035, all -> 0x0032 }
            android.os.SharedMemory.unmap(r4)     // Catch:{ ErrnoException -> 0x0035, all -> 0x0032 }
            goto L_0x003a
        L_0x0032:
            r0 = move-exception
            r1 = r3
            goto L_0x007b
        L_0x0035:
            r4 = move-exception
            r6 = r3
            r3 = r1
            r1 = r6
            goto L_0x0057
        L_0x003a:
            if (r3 == 0) goto L_0x003f
            r3.close()
        L_0x003f:
            r9.close()
            r7.q(r8)
            goto L_0x0074
        L_0x0046:
            r3 = move-exception
            r4 = r3
            r3 = r1
            goto L_0x0057
        L_0x004a:
            r0 = move-exception
        L_0x004b:
            r9 = r1
            goto L_0x007b
        L_0x004d:
            r4 = move-exception
        L_0x004e:
            r9 = r1
            r3 = r9
            goto L_0x0057
        L_0x0051:
            r9 = move-exception
            r0 = r9
            goto L_0x004b
        L_0x0054:
            r9 = move-exception
            r4 = r9
            goto L_0x004e
        L_0x0057:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x007a }
            r5.<init>(r0)     // Catch:{ all -> 0x007a }
            r5.append(r4)     // Catch:{ all -> 0x007a }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x007a }
            He.F.D(r2, r0)     // Catch:{ all -> 0x007a }
            if (r1 == 0) goto L_0x006b
            r1.close()
        L_0x006b:
            if (r9 == 0) goto L_0x0070
            r9.close()
        L_0x0070:
            r7.q(r8)
            r1 = r3
        L_0x0074:
            java.lang.String r7 = "encodeMesh() - X"
            He.F.K(r2, r7)
            return r1
        L_0x007a:
            r0 = move-exception
        L_0x007b:
            if (r1 == 0) goto L_0x0080
            r1.close()
        L_0x0080:
            if (r9 == 0) goto L_0x0085
            r9.close()
        L_0x0085:
            r7.q(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: A0.l.k(android.content.Context, Ed.b):byte[]");
    }

    public void l(Y6.a aVar) {
        try {
            g gVar = (g) this.f;
            K1.i iVar = new K1.i(aVar);
            Parcel c5 = gVar.c();
            H1.d.d(c5, iVar);
            gVar.d(c5, 9);
        } catch (RemoteException e7) {
            throw new RuntimeException(e7);
        }
    }

    /* JADX INFO: finally extract failed */
    public Bitmap m(Context context, Bitmap bitmap, boolean z) {
        F.K("LiveEffectServiceConnector", "inpaint() - E");
        if (!n(context, "8.5.0.12")) {
            F.D("LiveEffectServiceConnector", "Exception at inpaint: Inpaint with options support is not available in current version. Minimum required version: 8.5.0.12");
            F.K("LiveEffectServiceConnector", "inpaint() - X");
            return null;
        }
        c(context);
        try {
            Bitmap i2 = ((c) d.a((IBinder) this.e)).i(bitmap, z);
            q(context);
            F.K("LiveEffectServiceConnector", "inpaint() - X");
            return i2;
        } catch (Throwable th) {
            q(context);
            throw th;
        }
    }

    public boolean n(Context context, String str) {
        Parcel obtain;
        Parcel obtain2;
        if (f12h == null) {
            F.K("LiveEffectServiceConnector", "getVersion() - E");
            c(context);
            try {
                c cVar = (c) d.a((IBinder) this.e);
                cVar.getClass();
                obtain = Parcel.obtain();
                obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("com.samsung.android.liveeffect.service.ILiveEffectService");
                cVar.f3233a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                q(context);
                if (e(readString, "7.0.0.24") < 0) {
                    F.D("LiveEffectServiceConnector", "The current version of the service is wrong - ".concat(readString));
                }
                F.K("LiveEffectServiceConnector", "getVersion() - X");
                f12h = readString;
            } catch (Throwable th) {
                q(context);
                throw th;
            }
        }
        try {
            if (e(f12h, str) >= 0) {
                return true;
            }
            return false;
        } catch (NumberFormatException unused) {
            F.D("LiveEffectServiceConnector", "Invalid version format:".concat(str));
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0070  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Hf.c0 o(We.i r8, ef.C0993a r9, boolean r10) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.e
            B0.a r0 = (B0.a) r0
            java.lang.Object r1 = r0.d
            cf.a r1 = (cf.C0922a) r1
            java.lang.String r2 = "arrayType"
            kotlin.jvm.internal.j.e(r8, r2)
            boolean r9 = r9.d
            We.B r2 = r8.b
            boolean r3 = r2 instanceof We.z
            r4 = 0
            if (r3 == 0) goto L_0x001a
            r3 = r2
            We.z r3 = (We.z) r3
            goto L_0x001b
        L_0x001a:
            r3 = r4
        L_0x001b:
            if (r3 == 0) goto L_0x0035
            java.lang.Class r3 = r3.f3897a
            java.lang.Class r5 = java.lang.Void.TYPE
            boolean r5 = kotlin.jvm.internal.j.a(r3, r5)
            if (r5 == 0) goto L_0x0028
            goto L_0x0035
        L_0x0028:
            java.lang.String r3 = r3.getName()
            yf.c r3 = yf.C1359c.c(r3)
            Ne.l r3 = r3.f()
            goto L_0x0036
        L_0x0035:
            r3 = r4
        L_0x0036:
            cf.c r5 = new cf.c
            r6 = 1
            r5.<init>(r0, r8, r6)
            if (r3 == 0) goto L_0x0070
            Qe.C r7 = r1.f4011o
            Ne.i r7 = r7.f()
            Hf.B r7 = r7.q(r3)
            Re.i r8 = new Re.i
            Re.h r10 = r7.getAnnotations()
            r0 = 2
            Re.h[] r0 = new Re.h[r0]
            r1 = 0
            r0[r1] = r10
            r0[r6] = r5
            r8.<init>((Re.h[]) r0)
            Hf.x r7 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.L(r7, r8)
            java.lang.String r8 = "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType"
            kotlin.jvm.internal.j.c(r7, r8)
            Hf.B r7 = (Hf.B) r7
            if (r9 == 0) goto L_0x0067
            return r7
        L_0x0067:
            Hf.B r8 = r7.y0(r6)
            Hf.c0 r7 = Hf.C0754c.f(r7, r8)
            return r7
        L_0x0070:
            Hf.Y r8 = Hf.Y.COMMON
            r0 = 6
            ef.a r8 = a.C0068a.Y(r8, r9, r4, r0)
            Hf.x r7 = r7.p(r2, r8)
            if (r9 == 0) goto L_0x008f
            if (r10 == 0) goto L_0x0082
            Hf.d0 r8 = Hf.d0.OUT_VARIANCE
            goto L_0x0084
        L_0x0082:
            Hf.d0 r8 = Hf.d0.INVARIANT
        L_0x0084:
            Qe.C r9 = r1.f4011o
            Ne.i r9 = r9.f()
            Hf.B r7 = r9.g(r8, r7, r5)
            return r7
        L_0x008f:
            Qe.C r8 = r1.f4011o
            Ne.i r8 = r8.f()
            Hf.d0 r9 = Hf.d0.INVARIANT
            Hf.B r8 = r8.g(r9, r7, r5)
            Qe.C r9 = r1.f4011o
            Ne.i r9 = r9.f()
            Hf.d0 r10 = Hf.d0.OUT_VARIANCE
            Hf.B r7 = r9.g(r10, r7, r5)
            Hf.B r7 = r7.y0(r6)
            Hf.c0 r7 = Hf.C0754c.f(r8, r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: A0.l.o(We.i, ef.a, boolean):Hf.c0");
    }

    public int onFinish() {
        switch (this.d) {
            case 2:
                return 0;
            default:
                return 0;
        }
    }

    public C0774x p(C1073d dVar, C0993a aVar) {
        C0922a aVar2 = (C0922a) ((B0.a) this.e).d;
        Ne.l lVar = null;
        if (dVar instanceof z) {
            Class cls = ((z) dVar).f3897a;
            if (!j.a(cls, Void.TYPE)) {
                lVar = C1359c.c(cls.getName()).f();
            }
            if (lVar != null) {
                return aVar2.f4011o.f().s(lVar);
            }
            return aVar2.f4011o.f().w();
        }
        boolean z = false;
        if (dVar instanceof q) {
            q qVar = (q) dVar;
            if (!aVar.d && aVar.f4317a != Y.SUPERTYPE) {
                z = true;
            }
            Type type = qVar.f3892a;
            boolean d2 = qVar.d();
            if (d2 || z) {
                B f5 = f(qVar, aVar.b(C0994b.FLEXIBLE_LOWER_BOUND), (B) null);
                if (f5 == null) {
                    return Jf.l.c(k.UNRESOLVED_JAVA_CLASS, type.toString());
                }
                B f8 = f(qVar, aVar.b(C0994b.FLEXIBLE_UPPER_BOUND), f5);
                if (f8 == null) {
                    return Jf.l.c(k.UNRESOLVED_JAVA_CLASS, type.toString());
                }
                if (d2) {
                    return new ef.i(f5, f8);
                }
                return C0754c.f(f5, f8);
            }
            B f10 = f(qVar, aVar, (B) null);
            if (f10 != null) {
                return f10;
            }
            return Jf.l.c(k.UNRESOLVED_JAVA_CLASS, type.toString());
        } else if (dVar instanceof We.i) {
            return o((We.i) dVar, aVar, false);
        } else {
            if (dVar instanceof E) {
                We.B c5 = ((E) dVar).c();
                if (c5 != null) {
                    return p(c5, aVar);
                }
                return aVar2.f4011o.f().m();
            } else if (dVar == null) {
                return aVar2.f4011o.f().m();
            } else {
                throw new UnsupportedOperationException("Unsupported type: " + dVar);
            }
        }
    }

    public void q(Context context) {
        F.C("LiveEffectServiceConnector", "unbind() - E");
        try {
            context.unbindService((C0083a) this.g);
        } catch (Exception e7) {
            F.D("LiveEffectServiceConnector", "Exception at unbind()" + e7);
        }
        F.C("LiveEffectServiceConnector", "unbind() - X");
    }

    public void run() {
        switch (this.d) {
            case 2:
                C0732c cVar = (C0732c) this.e;
                Ed.b bVar = (Ed.b) this.g;
                Context context = (Context) bVar.e;
                if (Ed.b.b(bVar)) {
                    if (!C0246a.a0(context, "appVersionForInit")) {
                        C0246a.l0(context, cVar);
                        C0246a.k0(context, cVar);
                    }
                    Application application = (Application) this.f;
                    Nd.c cVar2 = Nd.c.SEND_PREVIOUS_REGISTRATION_INFO;
                    Trace.beginSection("RegisterLogSender sendLog");
                    f fVar = new f(application, cVar, cVar2);
                    for (Map.Entry next : application.getSharedPreferences(com.samsung.context.sdk.samsunganalytics.internal.sender.c.D("SATerms"), 0).getAll().entrySet()) {
                        String str = (String) next.getKey();
                        long longValue = ((Long) next.getValue()).longValue();
                        C0068a.b("Send previous agreement, timestamp : " + longValue);
                        t1.i e7 = t1.i.e();
                        Nd.b bVar2 = new Nd.b(cVar.f3331a, str, longValue, new Nd.a(fVar, str, longValue));
                        e7.getClass();
                        t1.i.d(bVar2);
                    }
                    Nd.c cVar3 = (Nd.c) fVar.f106h;
                    if (cVar3 != Nd.c.SEND_PREVIOUS_REGISTRATION_INFO) {
                        if (og.k.f >= 2) {
                            String a7 = cVar3.a();
                            String str2 = cVar.f3331a;
                            C0068a.b("Send broadcast for " + a7 + ", tid : " + str2);
                            Intent intent = new Intent();
                            intent.setPackage("com.sec.android.diagmonagent");
                            intent.setAction(cVar3.a());
                            intent.putExtra("tid", cVar.f3331a);
                            intent.putExtra("agree", false);
                            if (cVar3 == Nd.c.DELETE_SENSITIVE_APP_DATA) {
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(10);
                                arrayList.add(11);
                                intent.putIntegerArrayListExtra("event_type", arrayList);
                            }
                            application.sendBroadcast(intent);
                            if (og.k.f == 2) {
                                ((Jd.c) com.samsung.context.sdk.samsunganalytics.internal.sender.c.w(application, 2, cVar)).f = true;
                            }
                        }
                        long currentTimeMillis = System.currentTimeMillis();
                        C0068a.b("Send agreement, timestamp : " + currentTimeMillis);
                        t1.i e8 = t1.i.e();
                        String str3 = cVar.f3331a;
                        String str4 = (String) fVar.g;
                        Nd.b bVar3 = new Nd.b(str3, str4, currentTimeMillis, new Nd.a(fVar, str4, currentTimeMillis));
                        e8.getClass();
                        t1.i.d(bVar3);
                    }
                    Trace.endSection();
                    return;
                }
                return;
            default:
                com.samsung.context.sdk.samsunganalytics.internal.sender.d dVar = (com.samsung.context.sdk.samsunganalytics.internal.sender.d) this.e;
                C0732c cVar4 = (C0732c) this.g;
                try {
                    cVar4.getClass();
                    String str5 = cVar4.f3331a;
                    String a10 = dVar.d.a();
                    ((C0913a) ((C0915c) this.f)).b(dVar.b, str5, a10, dVar.f4199c);
                    return;
                } catch (Exception e9) {
                    Exception exc = e9;
                    C0068a.K("failed to send log" + exc.getMessage());
                    return;
                }
        }
    }

    public String toString() {
        switch (this.d) {
            case 11:
                E2.k V = B1.a.V(this);
                V.a((List) this.g, "addrs");
                V.a((C0969b) this.e, "attrs");
                V.a(Arrays.deepToString((Object[][]) this.f), "customOptions");
                return V.toString();
            default:
                return super.toString();
        }
    }

    public void u0(a0 a0Var, C1054s sVar, M m) {
        b.b();
        try {
            b.a();
            d(a0Var, m);
            b.f4600a.getClass();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void z(M m) {
        C1051q qVar = (C1051q) this.g;
        b.b();
        try {
            b.a();
            C1097a aVar = b.f4600a;
            aVar.getClass();
            qVar.e.execute(new C1039l(this, m));
            aVar.getClass();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public /* synthetic */ l(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.g = obj;
        this.e = obj2;
        this.f = obj3;
    }

    public /* synthetic */ l(Object obj, Object obj2, Object obj3, boolean z, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public l(K1.f fVar, g gVar) {
        this.d = 4;
        this.f = gVar;
        r.b(fVar);
        this.e = fVar;
    }

    public l(List list) {
        this.d = 0;
        this.g = list;
        this.e = new ArrayList(list.size());
        this.f = new ArrayList(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((ArrayList) this.e).add(new o((List) ((E0.j) list.get(i2)).b.e));
            ((ArrayList) this.f).add(((E0.j) list.get(i2)).f137c.p0());
        }
    }

    public l(C1107g gVar, B1.b bVar) {
        this.d = 10;
        this.e = gVar;
        this.f = bVar;
        this.g = new ConcurrentHashMap();
    }

    public l() {
        this.d = 20;
        this.f = new CountDownLatch(1);
        this.g = new C0083a(1, this);
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [ef.f, java.lang.Object] */
    public l(B0.a aVar, e eVar) {
        this.d = 13;
        j.e(eVar, "typeParameterResolver");
        this.e = aVar;
        this.f = eVar;
        this.g = new D0.e((ef.f) new Object());
    }

    public l(l lVar) {
        this.d = 6;
        R2.b bVar = (R2.b) lVar.e;
        int g3 = bVar.g();
        this.e = new N2.e[g3];
        this.f = new N2.e[g3];
        this.g = new N2.e[g3];
        int length = bVar.e.length;
        for (int i2 = 0; i2 < length; i2++) {
            R2.a aVar = (R2.a) bVar.d(i2);
            R2.g gVar = aVar.b;
            int i7 = aVar.f645a;
            ((N2.e[]) this.e)[i7] = new N2.l(((R2.f) gVar.d(0)).e);
            p pVar = gVar.g().e;
            ((N2.e[]) this.f)[i7] = new N2.l(pVar);
            ((N2.e[]) this.g)[i7] = new N2.l(pVar);
        }
    }

    public l(R2.b bVar) {
        this.d = 9;
        this.e = bVar;
        this.f = null;
        this.g = null;
    }

    public l(l lVar, int[] iArr, l lVar2) {
        this.d = 7;
        if (iArr == null) {
            throw new NullPointerException("order == null");
        } else if (lVar2 != null) {
            this.e = lVar;
            this.f = iArr;
            this.g = lVar2;
        } else {
            throw new NullPointerException("addresses == null");
        }
    }

    public l(C0820j jVar, List list, l lVar) {
        this.d = 8;
        j.e(jVar, "classifierDescriptor");
        j.e(list, "arguments");
        this.e = jVar;
        this.g = list;
        this.f = lVar;
    }

    public l(Ge.e eVar, List[] listArr, Method method) {
        this.d = 5;
        j.e(eVar, "argumentRange");
        this.e = eVar;
        this.f = listArr;
        this.g = method;
    }

    public l(ServiceDescriptor serviceDescriptor) {
        this.d = 12;
        this.g = new HashMap();
        F.n(serviceDescriptor, "serviceDescriptor");
        this.f = serviceDescriptor;
        this.e = serviceDescriptor.f4622a;
    }

    public l(V0 v02) {
        this.d = 16;
        this.g = v02;
        this.e = C0976i.a(C0975h.IDLE);
    }

    public l(C1051q qVar, C0971d dVar) {
        this.d = 14;
        this.g = qVar;
        this.e = dVar;
    }

    public l(List list, C0969b bVar, Object[][] objArr) {
        this.d = 11;
        F.n(list, "addresses are not set");
        this.g = list;
        F.n(bVar, "attrs");
        this.e = bVar;
        F.n(objArr, "customOptions");
        this.f = objArr;
    }
}
