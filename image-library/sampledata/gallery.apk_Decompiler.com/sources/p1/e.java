package P1;

import A0.l;
import B1.a;
import E2.k;
import He.F;
import com.google.common.util.concurrent.s;
import ee.C0971d;
import ge.P;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e implements Runnable {
    public final /* synthetic */ int d;
    public Object e;
    public final Object f;

    public /* synthetic */ e(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.f = obj;
        this.e = obj2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0054, code lost:
        if (r3 != false) goto L_0x0056;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void a() {
        /*
            r15 = this;
            java.lang.Object r0 = r15.f
            P1.e r0 = (P1.e) r0
            java.lang.Object r0 = r0.f
            ge.t0 r0 = (ge.C1057t0) r0
            java.lang.Object r1 = r0.l
            monitor-enter(r1)
            java.lang.Object r0 = r15.f     // Catch:{ all -> 0x0069 }
            P1.e r0 = (P1.e) r0     // Catch:{ all -> 0x0069 }
            java.lang.Object r2 = r0.e     // Catch:{ all -> 0x0069 }
            Kd.a r2 = (Kd.a) r2     // Catch:{ all -> 0x0069 }
            boolean r2 = r2.d     // Catch:{ all -> 0x0069 }
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x001b
            goto L_0x009e
        L_0x001b:
            java.lang.Object r0 = r0.f     // Catch:{ all -> 0x0069 }
            ge.t0 r0 = (ge.C1057t0) r0     // Catch:{ all -> 0x0069 }
            ge.r1 r2 = r0.r     // Catch:{ all -> 0x0069 }
            java.lang.Object r5 = r15.e     // Catch:{ all -> 0x0069 }
            ge.v1 r5 = (ge.v1) r5     // Catch:{ all -> 0x0069 }
            ge.r1 r2 = r2.a(r5)     // Catch:{ all -> 0x0069 }
            r0.r = r2     // Catch:{ all -> 0x0069 }
            java.lang.Object r0 = r15.f     // Catch:{ all -> 0x0069 }
            P1.e r0 = (P1.e) r0     // Catch:{ all -> 0x0069 }
            java.lang.Object r0 = r0.f     // Catch:{ all -> 0x0069 }
            ge.t0 r0 = (ge.C1057t0) r0     // Catch:{ all -> 0x0069 }
            ge.r1 r2 = r0.r     // Catch:{ all -> 0x0069 }
            boolean r0 = r0.n(r2)     // Catch:{ all -> 0x0069 }
            r2 = 0
            if (r0 == 0) goto L_0x006d
            java.lang.Object r0 = r15.f     // Catch:{ all -> 0x0069 }
            P1.e r0 = (P1.e) r0     // Catch:{ all -> 0x0069 }
            java.lang.Object r0 = r0.f     // Catch:{ all -> 0x0069 }
            ge.t0 r0 = (ge.C1057t0) r0     // Catch:{ all -> 0x0069 }
            ge.w1 r0 = r0.f4559p     // Catch:{ all -> 0x0069 }
            if (r0 == 0) goto L_0x0056
            java.util.concurrent.atomic.AtomicInteger r5 = r0.d     // Catch:{ all -> 0x0069 }
            int r5 = r5.get()     // Catch:{ all -> 0x0069 }
            int r0 = r0.b     // Catch:{ all -> 0x0069 }
            if (r5 <= r0) goto L_0x0053
            goto L_0x0054
        L_0x0053:
            r3 = r2
        L_0x0054:
            if (r3 == 0) goto L_0x006d
        L_0x0056:
            java.lang.Object r0 = r15.f     // Catch:{ all -> 0x0069 }
            P1.e r0 = (P1.e) r0     // Catch:{ all -> 0x0069 }
            java.lang.Object r0 = r0.f     // Catch:{ all -> 0x0069 }
            ge.t0 r0 = (ge.C1057t0) r0     // Catch:{ all -> 0x0069 }
            Kd.a r4 = new Kd.a     // Catch:{ all -> 0x0069 }
            java.lang.Object r3 = r0.l     // Catch:{ all -> 0x0069 }
            r4.<init>(r3)     // Catch:{ all -> 0x0069 }
            r0.z = r4     // Catch:{ all -> 0x0069 }
        L_0x0067:
            r3 = r2
            goto L_0x009e
        L_0x0069:
            r0 = move-exception
            r15 = r0
            goto L_0x00fa
        L_0x006d:
            java.lang.Object r0 = r15.f     // Catch:{ all -> 0x0069 }
            P1.e r0 = (P1.e) r0     // Catch:{ all -> 0x0069 }
            java.lang.Object r0 = r0.f     // Catch:{ all -> 0x0069 }
            ge.t0 r0 = (ge.C1057t0) r0     // Catch:{ all -> 0x0069 }
            ge.r1 r3 = r0.r     // Catch:{ all -> 0x0069 }
            boolean r5 = r3.f4545h     // Catch:{ all -> 0x0069 }
            if (r5 == 0) goto L_0x007c
            goto L_0x0091
        L_0x007c:
            ge.r1 r6 = new ge.r1     // Catch:{ all -> 0x0069 }
            java.util.List r7 = r3.b     // Catch:{ all -> 0x0069 }
            java.util.Collection r8 = r3.f4544c     // Catch:{ all -> 0x0069 }
            java.util.Collection r9 = r3.d     // Catch:{ all -> 0x0069 }
            ge.v1 r10 = r3.f     // Catch:{ all -> 0x0069 }
            boolean r11 = r3.g     // Catch:{ all -> 0x0069 }
            boolean r12 = r3.f4543a     // Catch:{ all -> 0x0069 }
            int r14 = r3.e     // Catch:{ all -> 0x0069 }
            r13 = 1
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x0069 }
            r3 = r6
        L_0x0091:
            r0.r = r3     // Catch:{ all -> 0x0069 }
            java.lang.Object r0 = r15.f     // Catch:{ all -> 0x0069 }
            P1.e r0 = (P1.e) r0     // Catch:{ all -> 0x0069 }
            java.lang.Object r0 = r0.f     // Catch:{ all -> 0x0069 }
            ge.t0 r0 = (ge.C1057t0) r0     // Catch:{ all -> 0x0069 }
            r0.z = r4     // Catch:{ all -> 0x0069 }
            goto L_0x0067
        L_0x009e:
            monitor-exit(r1)     // Catch:{ all -> 0x0069 }
            if (r3 == 0) goto L_0x00ca
            java.lang.Object r0 = r15.e
            ge.v1 r0 = (ge.v1) r0
            ge.r r1 = r0.f4567a
            ge.W0 r2 = new ge.W0
            java.lang.Object r3 = r15.f
            P1.e r3 = (P1.e) r3
            java.lang.Object r3 = r3.f
            ge.t0 r3 = (ge.C1057t0) r3
            r4 = 1
            r2.<init>((int) r4, (java.lang.Object) r3, (java.lang.Object) r0)
            r1.k(r2)
            java.lang.Object r15 = r15.e
            ge.v1 r15 = (ge.v1) r15
            ge.r r15 = r15.f4567a
            ee.a0 r0 = ee.a0.f
            java.lang.String r1 = "Unneeded hedging"
            ee.a0 r0 = r0.g(r1)
            r15.v(r0)
            return
        L_0x00ca:
            if (r4 == 0) goto L_0x00ea
            java.lang.Object r0 = r15.f
            P1.e r0 = (P1.e) r0
            java.lang.Object r0 = r0.f
            ge.t0 r0 = (ge.C1057t0) r0
            java.util.concurrent.ScheduledExecutorService r1 = r0.g
            P1.e r2 = new P1.e
            r3 = 29
            r2.<init>(r3, r0, r4)
            ge.a0 r0 = r0.f4557j
            long r5 = r0.b
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            java.util.concurrent.ScheduledFuture r0 = r1.schedule(r2, r5, r0)
            r4.f(r0)
        L_0x00ea:
            java.lang.Object r0 = r15.f
            P1.e r0 = (P1.e) r0
            java.lang.Object r0 = r0.f
            ge.t0 r0 = (ge.C1057t0) r0
            java.lang.Object r15 = r15.e
            ge.v1 r15 = (ge.v1) r15
            r0.l(r15)
            return
        L_0x00fa:
            monitor-exit(r1)     // Catch:{ all -> 0x0069 }
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: P1.e.a():void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: A0.l} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: A0.l} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: A0.l} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: A0.l} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: ee.Q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: A0.l} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: A0.l} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: A0.l} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: A0.l} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: ee.Q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: A0.l} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: A0.l} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: ee.Q} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r0v103, types: [ee.Q] */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: type inference failed for: r2v23, types: [int] */
    /* JADX WARNING: type inference failed for: r6v16, types: [ee.d, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v63 */
    /* JADX WARNING: type inference failed for: r2v64 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r14 = this;
            int r0 = r14.d
            r1 = 0
            r2 = 0
            r3 = 1
            switch(r0) {
                case 0: goto L_0x08c2;
                case 1: goto L_0x089f;
                case 2: goto L_0x087f;
                case 3: goto L_0x0873;
                case 4: goto L_0x0867;
                case 5: goto L_0x083b;
                case 6: goto L_0x080e;
                case 7: goto L_0x07d3;
                case 8: goto L_0x07bd;
                case 9: goto L_0x07ab;
                case 10: goto L_0x079f;
                case 11: goto L_0x0791;
                case 12: goto L_0x0785;
                case 13: goto L_0x076f;
                case 14: goto L_0x0761;
                case 15: goto L_0x0753;
                case 16: goto L_0x0745;
                case 17: goto L_0x0737;
                case 18: goto L_0x0729;
                case 19: goto L_0x071b;
                case 20: goto L_0x070d;
                case 21: goto L_0x061e;
                case 22: goto L_0x051a;
                case 23: goto L_0x03d1;
                case 24: goto L_0x0381;
                case 25: goto L_0x0078;
                case 26: goto L_0x0048;
                case 27: goto L_0x002c;
                case 28: goto L_0x0028;
                default: goto L_0x0008;
            }
        L_0x0008:
            java.lang.Object r0 = r14.f
            ge.t0 r0 = (ge.C1057t0) r0
            ge.r1 r1 = r0.r
            int r1 = r1.e
            ge.v1 r0 = r0.d(r1, r2)
            if (r0 != 0) goto L_0x0017
            goto L_0x0027
        L_0x0017:
            java.lang.Object r1 = r14.f
            ge.t0 r1 = (ge.C1057t0) r1
            java.util.concurrent.Executor r1 = r1.e
            P1.e r2 = new P1.e
            r3 = 28
            r2.<init>(r3, r14, r0)
            r1.execute(r2)
        L_0x0027:
            return
        L_0x0028:
            r14.a()
            return
        L_0x002c:
            java.lang.Object r0 = r14.e
            ge.n r0 = (ge.C1045n) r0
            r0.run()
            java.lang.Object r14 = r14.f
            ge.A0 r14 = (ge.A0) r14
            ge.B0 r0 = r14.r
            ge.F0 r0 = r0.d
            ee.e0 r0 = r0.m
            B2.A r1 = new B2.A
            r2 = 9
            r1.<init>((int) r2, (java.lang.Object) r14)
            r0.execute(r1)
            return
        L_0x0048:
            java.lang.Object r0 = r14.e
            ge.A0 r0 = (ge.A0) r0
            java.lang.Object r14 = r14.f
            ge.B0 r14 = (ge.B0) r14
            ge.F0 r1 = r14.d
            java.util.concurrent.atomic.AtomicReference r14 = r14.f4382a
            java.lang.Object r14 = r14.get()
            ge.p0 r2 = ge.F0.f4402i0
            if (r14 != r2) goto L_0x0074
            java.util.LinkedHashSet r14 = r1.B
            if (r14 != 0) goto L_0x006e
            java.util.LinkedHashSet r14 = new java.util.LinkedHashSet
            r14.<init>()
            r1.B = r14
            ge.b0 r14 = r1.Z
            java.lang.Object r2 = r1.f4405C
            r14.A0(r2, r3)
        L_0x006e:
            java.util.LinkedHashSet r14 = r1.B
            r14.add(r0)
            goto L_0x0077
        L_0x0074:
            r0.p()
        L_0x0077:
            return
        L_0x0078:
            ee.a r2 = ee.C0988v.f4313a
            java.lang.Object r0 = r14.f
            ge.x0 r0 = (ge.C1065x0) r0
            ge.F0 r4 = r0.e
            ge.A1 r5 = r4.v
            ee.q r0 = r0.d
            if (r5 == r0) goto L_0x0088
            goto L_0x0380
        L_0x0088:
            java.lang.Object r0 = r14.e
            ee.S r0 = (ee.S) r0
            java.util.List r5 = r0.f4280a
            ge.h r4 = r4.f4413N
            ee.c r6 = ee.C0970c.DEBUG
            java.lang.String r7 = "Resolved address: {0}, config={1}"
            ee.b r0 = r0.b
            java.lang.Object[] r0 = new java.lang.Object[]{r5, r0}
            r4.c(r6, r7, r0)
            java.lang.Object r0 = r14.f
            ge.x0 r0 = (ge.C1065x0) r0
            ge.F0 r0 = r0.e
            ge.C0 r4 = r0.Q
            ge.C0 r7 = ge.C0.SUCCESS
            if (r4 == r7) goto L_0x00be
            ge.h r0 = r0.f4413N
            ee.c r4 = ee.C0970c.INFO
            java.lang.String r8 = "Address resolved: {0}"
            java.lang.Object[] r9 = new java.lang.Object[]{r5}
            r0.c(r4, r8, r9)
            java.lang.Object r0 = r14.f
            ge.x0 r0 = (ge.C1065x0) r0
            ge.F0 r0 = r0.e
            r0.Q = r7
        L_0x00be:
            java.lang.Object r0 = r14.e
            ee.S r0 = (ee.S) r0
            ee.Q r4 = r0.f4281c
            ee.b r0 = r0.b
            ee.a r7 = ge.A1.g
            java.util.IdentityHashMap r0 = r0.f4292a
            java.lang.Object r0 = r0.get(r7)
            r7 = r0
            ge.y1 r7 = (ge.y1) r7
            java.lang.Object r0 = r14.e
            ee.S r0 = (ee.S) r0
            ee.b r0 = r0.b
            java.util.IdentityHashMap r0 = r0.f4292a
            java.lang.Object r0 = r0.get(r2)
            ee.v r0 = (ee.C0988v) r0
            if (r4 == 0) goto L_0x00e8
            java.lang.Object r8 = r4.b
            if (r8 == 0) goto L_0x00e8
            ge.N0 r8 = (ge.N0) r8
            goto L_0x00e9
        L_0x00e8:
            r8 = r1
        L_0x00e9:
            if (r4 == 0) goto L_0x00ee
            ee.a0 r9 = r4.f4279a
            goto L_0x00ef
        L_0x00ee:
            r9 = r1
        L_0x00ef:
            java.lang.Object r10 = r14.f
            ge.x0 r10 = (ge.C1065x0) r10
            ge.F0 r10 = r10.e
            boolean r11 = r10.T
            r12 = 15
            r13 = 18
            if (r11 != 0) goto L_0x012c
            if (r8 == 0) goto L_0x0108
            ge.h r3 = r10.f4413N
            ee.c r4 = ee.C0970c.INFO
            java.lang.String r6 = "Service config from name resolver discarded by channel settings"
            r3.b(r4, r6)
        L_0x0108:
            java.lang.Object r3 = r14.f
            ge.x0 r3 = (ge.C1065x0) r3
            ge.F0 r3 = r3.e
            ge.N0 r4 = ge.F0.h0
            if (r0 == 0) goto L_0x011b
            ge.h r0 = r3.f4413N
            ee.c r3 = ee.C0970c.INFO
            java.lang.String r6 = "Config selector from name resolver discarded by channel settings"
            r0.b(r3, r6)
        L_0x011b:
            java.lang.Object r0 = r14.f
            ge.x0 r0 = (ge.C1065x0) r0
            ge.F0 r0 = r0.e
            ge.B0 r0 = r0.f4415P
            ge.M0 r3 = r4.b()
            r0.i(r3)
            goto L_0x0201
        L_0x012c:
            if (r8 == 0) goto L_0x0153
            if (r0 == 0) goto L_0x0149
            ge.B0 r4 = r10.f4415P
            r4.i(r0)
            ge.M0 r0 = r8.b()
            if (r0 == 0) goto L_0x019c
            java.lang.Object r0 = r14.f
            ge.x0 r0 = (ge.C1065x0) r0
            ge.F0 r0 = r0.e
            ge.h r0 = r0.f4413N
            java.lang.String r4 = "Method configs in service config will be discarded due to presence ofconfig-selector"
            r0.b(r6, r4)
            goto L_0x019c
        L_0x0149:
            ge.B0 r0 = r10.f4415P
            ge.M0 r4 = r8.b()
            r0.i(r4)
            goto L_0x019c
        L_0x0153:
            if (r9 == 0) goto L_0x0195
            boolean r0 = r10.S
            if (r0 != 0) goto L_0x0192
            ge.h r0 = r10.f4413N
            ee.c r1 = ee.C0970c.INFO
            java.lang.String r2 = "Fallback to error due to invalid first service config without default config"
            r0.b(r1, r2)
            java.lang.Object r14 = r14.f
            ge.x0 r14 = (ge.C1065x0) r14
            ee.a0 r0 = r4.f4279a
            r14.e(r0)
            if (r7 == 0) goto L_0x0380
            ee.a0 r14 = r4.f4279a
            ge.A1 r0 = r7.f4576a
            ge.d r1 = r0.e
            boolean r14 = r14.e()
            if (r14 == 0) goto L_0x0188
            ee.e0 r14 = r1.b
            r14.d()
            e5.a r0 = new e5.a
            r0.<init>(r13, r1)
            r14.execute(r0)
            goto L_0x0380
        L_0x0188:
            B2.A r14 = new B2.A
            r14.<init>((int) r12, (java.lang.Object) r0)
            r1.a(r14)
            goto L_0x0380
        L_0x0192:
            ge.N0 r8 = r10.R
            goto L_0x019c
        L_0x0195:
            ge.N0 r8 = ge.F0.h0
            ge.B0 r0 = r10.f4415P
            r0.i(r1)
        L_0x019c:
            java.lang.Object r0 = r14.f
            ge.x0 r0 = (ge.C1065x0) r0
            ge.F0 r0 = r0.e
            ge.N0 r0 = r0.R
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01d4
            java.lang.Object r0 = r14.f
            ge.x0 r0 = (ge.C1065x0) r0
            ge.F0 r0 = r0.e
            ge.h r0 = r0.f4413N
            ee.c r4 = ee.C0970c.INFO
            java.lang.String r6 = "Service config changed{0}"
            ge.N0 r9 = ge.F0.h0
            if (r8 != r9) goto L_0x01bd
            java.lang.String r9 = " to empty"
            goto L_0x01bf
        L_0x01bd:
            java.lang.String r9 = ""
        L_0x01bf:
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            r0.c(r4, r6, r9)
            java.lang.Object r0 = r14.f
            ge.x0 r0 = (ge.C1065x0) r0
            ge.F0 r0 = r0.e
            r0.R = r8
            ge.u0 r0 = r0.a0
            ge.w1 r4 = r8.d
            r0.f4561a = r4
        L_0x01d4:
            java.lang.Object r0 = r14.f     // Catch:{ RuntimeException -> 0x01dd }
            ge.x0 r0 = (ge.C1065x0) r0     // Catch:{ RuntimeException -> 0x01dd }
            ge.F0 r0 = r0.e     // Catch:{ RuntimeException -> 0x01dd }
            r0.S = r3     // Catch:{ RuntimeException -> 0x01dd }
            goto L_0x0200
        L_0x01dd:
            r0 = move-exception
            java.util.logging.Logger r3 = ge.F0.f4398c0
            java.util.logging.Level r4 = java.util.logging.Level.WARNING
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r9 = "["
            r6.<init>(r9)
            java.lang.Object r9 = r14.f
            ge.x0 r9 = (ge.C1065x0) r9
            ge.F0 r9 = r9.e
            ee.x r9 = r9.f4418a
            r6.append(r9)
            java.lang.String r9 = "] Unexpected exception from parsing service config"
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            r3.log(r4, r6, r0)
        L_0x0200:
            r4 = r8
        L_0x0201:
            java.lang.Object r0 = r14.e
            ee.S r0 = (ee.S) r0
            ee.b r0 = r0.b
            java.lang.Object r3 = r14.f
            ge.x0 r3 = (ge.C1065x0) r3
            D0.e r6 = r3.f4571c
            ge.F0 r3 = r3.e
            D0.e r3 = r3.f4425x
            if (r6 != r3) goto L_0x0380
            r0.getClass()
            D0.e r3 = new D0.e
            r3.<init>((ee.C0969b) r0)
            java.util.IdentityHashMap r0 = r0.f4292a
            boolean r0 = r0.containsKey(r2)
            if (r0 == 0) goto L_0x0238
            java.util.IdentityHashMap r0 = new java.util.IdentityHashMap
            java.lang.Object r6 = r3.e
            ee.b r6 = (ee.C0969b) r6
            java.util.IdentityHashMap r6 = r6.f4292a
            r0.<init>(r6)
            r0.remove(r2)
            ee.b r6 = new ee.b
            r6.<init>(r0)
            r3.e = r6
        L_0x0238:
            java.lang.Object r0 = r3.f
            java.util.IdentityHashMap r0 = (java.util.IdentityHashMap) r0
            if (r0 == 0) goto L_0x0241
            r0.remove(r2)
        L_0x0241:
            java.util.Map r0 = r4.f
            if (r0 == 0) goto L_0x024d
            ee.a r2 = ee.C0984q.f4306a
            r3.X(r2, r0)
            r3.A()
        L_0x024d:
            ee.b r0 = r3.A()
            java.lang.Object r14 = r14.f
            ge.x0 r14 = (ge.C1065x0) r14
            D0.e r14 = r14.f4571c
            java.lang.Object r14 = r14.e
            D0.f r14 = (D0.f) r14
            ee.b r2 = ee.C0969b.b
            java.lang.Object r2 = r4.e
            java.util.ArrayList r3 = new java.util.ArrayList
            java.lang.String r4 = "addresses"
            He.F.n(r5, r4)
            java.util.Collection r5 = (java.util.Collection) r5
            r3.<init>(r5)
            java.util.List r3 = java.util.Collections.unmodifiableList(r3)
            java.lang.String r4 = "attributes"
            He.F.n(r0, r4)
            java.lang.Object r4 = r14.e
            D0.e r4 = (D0.e) r4
            ge.H1 r2 = (ge.H1) r2
            if (r2 != 0) goto L_0x02d7
            java.lang.Object r2 = r14.f106h     // Catch:{ c -> 0x02ae }
            D0.e r2 = (D0.e) r2     // Catch:{ c -> 0x02ae }
            java.lang.Object r5 = r2.f     // Catch:{ c -> 0x02ae }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ c -> 0x02ae }
            java.lang.Object r2 = r2.e     // Catch:{ c -> 0x02ae }
            ee.E r2 = (ee.E) r2     // Catch:{ c -> 0x02ae }
            ee.D r2 = r2.a(r5)     // Catch:{ c -> 0x02ae }
            if (r2 == 0) goto L_0x0295
            ge.H1 r5 = new ge.H1
            r5.<init>(r2, r1)
            r2 = r5
            goto L_0x02d7
        L_0x0295:
            ge.c r0 = new ge.c     // Catch:{ c -> 0x02ae }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ c -> 0x02ae }
            java.lang.String r3 = "Trying to load '"
            r2.<init>(r3)     // Catch:{ c -> 0x02ae }
            r2.append(r5)     // Catch:{ c -> 0x02ae }
            java.lang.String r3 = "' because using default policy, but it's unavailable"
            r2.append(r3)     // Catch:{ c -> 0x02ae }
            java.lang.String r2 = r2.toString()     // Catch:{ c -> 0x02ae }
            r0.<init>(r2)     // Catch:{ c -> 0x02ae }
            throw r0     // Catch:{ c -> 0x02ae }
        L_0x02ae:
            r0 = move-exception
            ee.a0 r2 = ee.a0.n
            java.lang.String r0 = r0.getMessage()
            ee.a0 r0 = r2.g(r0)
            ee.h r2 = ee.C0975h.TRANSIENT_FAILURE
            ge.S0 r3 = new ge.S0
            r3.<init>((ee.a0) r0)
            r4.e0(r2, r3)
            java.lang.Object r0 = r14.f
            ee.q r0 = (ee.C0984q) r0
            r0.j()
            r14.g = r1
            ge.b r0 = new ge.b
            r0.<init>()
            r14.f = r0
            ee.a0 r14 = ee.a0.e
            goto L_0x035e
        L_0x02d7:
            ee.D r1 = r2.f4435a
            java.lang.Object r5 = r14.g
            ee.D r5 = (ee.C0967D) r5
            if (r5 == 0) goto L_0x02ea
            r1.getClass()
            java.lang.Object r1 = r14.g
            ee.D r1 = (ee.C0967D) r1
            r1.getClass()
            goto L_0x033c
        L_0x02ea:
            ee.h r5 = ee.C0975h.CONNECTING
            ge.a r6 = new ge.a
            r6.<init>()
            r4.e0(r5, r6)
            java.lang.Object r5 = r14.f
            ee.q r5 = (ee.C0984q) r5
            r5.j()
            r14.g = r1
            java.lang.Object r5 = r14.f
            ee.q r5 = (ee.C0984q) r5
            ge.b1 r1 = (ge.C1011b1) r1
            r1.getClass()
            boolean r1 = ge.C1011b1.f4497a
            if (r1 == 0) goto L_0x0310
            ge.V0 r1 = new ge.V0
            r1.<init>(r4)
            goto L_0x0315
        L_0x0310:
            ge.a1 r1 = new ge.a1
            r1.<init>(r4)
        L_0x0315:
            r14.f = r1
            java.lang.Object r1 = r4.f
            ge.F0 r1 = (ge.F0) r1
            ge.h r1 = r1.f4413N
            ee.c r6 = ee.C0970c.INFO
            java.lang.String r8 = "Load balancer changed from {0} to {1}"
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getSimpleName()
            java.lang.Object r9 = r14.f
            ee.q r9 = (ee.C0984q) r9
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getSimpleName()
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r9}
            r1.c(r6, r8, r5)
        L_0x033c:
            java.lang.Object r1 = r2.b
            if (r1 == 0) goto L_0x0351
            java.lang.Object r2 = r4.f
            ge.F0 r2 = (ge.F0) r2
            ge.h r2 = r2.f4413N
            ee.c r4 = ee.C0970c.DEBUG
            java.lang.String r5 = "Load-balancing config: {0}"
            java.lang.Object[] r6 = new java.lang.Object[]{r1}
            r2.c(r4, r5, r6)
        L_0x0351:
            java.lang.Object r14 = r14.f
            ee.q r14 = (ee.C0984q) r14
            ee.B r2 = new ee.B
            r2.<init>(r3, r0, r1)
            ee.a0 r14 = r14.a(r2)
        L_0x035e:
            if (r7 == 0) goto L_0x0380
            ge.A1 r0 = r7.f4576a
            ge.d r1 = r0.e
            boolean r14 = r14.e()
            if (r14 == 0) goto L_0x0378
            ee.e0 r14 = r1.b
            r14.d()
            e5.a r0 = new e5.a
            r0.<init>(r13, r1)
            r14.execute(r0)
            goto L_0x0380
        L_0x0378:
            B2.A r14 = new B2.A
            r14.<init>((int) r12, (java.lang.Object) r0)
            r1.a(r14)
        L_0x0380:
            return
        L_0x0381:
            java.lang.Object r0 = r14.f
            ge.x0 r0 = (ge.C1065x0) r0
            java.lang.Object r14 = r14.e
            ee.a0 r14 = (ee.a0) r14
            java.util.logging.Logger r2 = ge.F0.f4398c0
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            java.lang.String r4 = "[{0}] Failed to resolve name. status={1}"
            ge.F0 r5 = r0.e
            ee.x r6 = r5.f4418a
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r14}
            r2.log(r3, r4, r6)
            ge.B0 r2 = r5.f4415P
            java.util.concurrent.atomic.AtomicReference r3 = r2.f4382a
            java.lang.Object r3 = r3.get()
            ge.p0 r4 = ge.F0.f4402i0
            if (r3 != r4) goto L_0x03a9
            r2.i(r1)
        L_0x03a9:
            ge.C0 r1 = r5.Q
            ge.C0 r2 = ge.C0.ERROR
            if (r1 == r2) goto L_0x03be
            ge.h r1 = r5.f4413N
            ee.c r3 = ee.C0970c.WARNING
            java.lang.String r4 = "Failed to resolve name: {0}"
            java.lang.Object[] r6 = new java.lang.Object[]{r14}
            r1.c(r3, r4, r6)
            r5.Q = r2
        L_0x03be:
            D0.e r0 = r0.f4571c
            D0.e r1 = r5.f4425x
            if (r0 == r1) goto L_0x03c5
            goto L_0x03d0
        L_0x03c5:
            java.lang.Object r0 = r0.e
            D0.f r0 = (D0.f) r0
            java.lang.Object r0 = r0.f
            ee.q r0 = (ee.C0984q) r0
            r0.e(r14)
        L_0x03d0:
            return
        L_0x03d1:
            java.lang.Object r0 = r14.f
            Kd.a r0 = (Kd.a) r0
            java.lang.Object r0 = r0.f
            ge.i0 r0 = (ge.C1031i0) r0
            ee.i r0 = r0.w
            ee.h r0 = r0.f4298a
            ee.h r4 = ee.C0975h.SHUTDOWN
            if (r0 != r4) goto L_0x03e3
            goto L_0x0519
        L_0x03e3:
            java.lang.Object r0 = r14.f
            Kd.a r0 = (Kd.a) r0
            java.lang.Object r0 = r0.f
            ge.i0 r0 = (ge.C1031i0) r0
            ge.e0 r0 = r0.v
            java.lang.Object r4 = r14.f
            Kd.a r4 = (Kd.a) r4
            java.lang.Object r5 = r4.e
            ge.e0 r5 = (ge.C1019e0) r5
            if (r0 != r5) goto L_0x0419
            java.lang.Object r0 = r4.f
            ge.i0 r0 = (ge.C1031i0) r0
            r0.v = r1
            java.lang.Object r0 = r14.f
            Kd.a r0 = (Kd.a) r0
            java.lang.Object r0 = r0.f
            ge.i0 r0 = (ge.C1031i0) r0
            ge.f0 r0 = r0.l
            r0.d()
            java.lang.Object r14 = r14.f
            Kd.a r14 = (Kd.a) r14
            java.lang.Object r14 = r14.f
            ge.i0 r14 = (ge.C1031i0) r14
            ee.h r0 = ee.C0975h.IDLE
            ge.C1031i0.f(r14, r0)
            goto L_0x0519
        L_0x0419:
            java.lang.Object r0 = r4.f
            ge.i0 r0 = (ge.C1031i0) r0
            ge.e0 r4 = r0.u
            if (r4 != r5) goto L_0x0519
            ee.i r0 = r0.w
            ee.h r0 = r0.f4298a
            ee.h r4 = ee.C0975h.CONNECTING
            if (r0 != r4) goto L_0x042b
            r0 = r3
            goto L_0x042c
        L_0x042b:
            r0 = r2
        L_0x042c:
            java.lang.String r4 = "Expected state is CONNECTING, actual state is %s"
            java.lang.Object r5 = r14.f
            Kd.a r5 = (Kd.a) r5
            java.lang.Object r5 = r5.f
            ge.i0 r5 = (ge.C1031i0) r5
            ee.i r5 = r5.w
            ee.h r5 = r5.f4298a
            He.F.q(r4, r5, r0)
            java.lang.Object r0 = r14.f
            Kd.a r0 = (Kd.a) r0
            java.lang.Object r0 = r0.f
            ge.i0 r0 = (ge.C1031i0) r0
            ge.f0 r0 = r0.l
            java.util.List r4 = r0.f4509a
            int r5 = r0.b
            java.lang.Object r4 = r4.get(r5)
            ee.o r4 = (ee.C0982o) r4
            int r5 = r0.f4510c
            int r5 = r5 + r3
            r0.f4510c = r5
            java.util.List r4 = r4.f4304a
            int r4 = r4.size()
            if (r5 < r4) goto L_0x0465
            int r4 = r0.b
            int r4 = r4 + r3
            r0.b = r4
            r0.f4510c = r2
        L_0x0465:
            java.lang.Object r0 = r14.f
            Kd.a r0 = (Kd.a) r0
            java.lang.Object r0 = r0.f
            ge.i0 r0 = (ge.C1031i0) r0
            ge.f0 r0 = r0.l
            int r4 = r0.b
            java.util.List r0 = r0.f4509a
            int r0 = r0.size()
            if (r4 >= r0) goto L_0x0486
            java.lang.Object r14 = r14.f
            Kd.a r14 = (Kd.a) r14
            java.lang.Object r14 = r14.f
            ge.i0 r14 = (ge.C1031i0) r14
            ge.C1031i0.g(r14)
            goto L_0x0519
        L_0x0486:
            java.lang.Object r0 = r14.f
            Kd.a r0 = (Kd.a) r0
            java.lang.Object r0 = r0.f
            ge.i0 r0 = (ge.C1031i0) r0
            r0.u = r1
            ge.f0 r0 = r0.l
            r0.d()
            java.lang.Object r0 = r14.f
            Kd.a r0 = (Kd.a) r0
            java.lang.Object r0 = r0.f
            ge.i0 r0 = (ge.C1031i0) r0
            java.lang.Object r14 = r14.e
            ee.a0 r14 = (ee.a0) r14
            ee.e0 r1 = r0.k
            r1.d()
            boolean r1 = r14.e()
            r1 = r1 ^ r3
            java.lang.String r4 = "The error status must not be OK"
            He.F.i(r4, r1)
            ee.i r1 = new ee.i
            ee.h r4 = ee.C0975h.TRANSIENT_FAILURE
            r1.<init>(r4, r14)
            r0.h(r1)
            ge.S r1 = r0.n
            if (r1 != 0) goto L_0x04c9
            ge.Q0 r1 = r0.f4517c
            r1.getClass()
            ge.S r1 = ge.Q0.i()
            r0.n = r1
        L_0x04c9:
            ge.S r1 = r0.n
            long r4 = r1.a()
            E2.q r1 = r0.f4521o
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.NANOSECONDS
            r1.getClass()
            boolean r6 = r1.f175a
            if (r6 == 0) goto L_0x04e2
            long r6 = java.lang.System.nanoTime()
            long r8 = r1.b
            long r6 = r6 - r8
            goto L_0x04e4
        L_0x04e2:
            r6 = 0
        L_0x04e4:
            long r6 = r10.convert(r6, r10)
            long r8 = r4 - r6
            ee.d r1 = r0.f4519i
            ee.c r4 = ee.C0970c.INFO
            java.lang.String r5 = "TRANSIENT_FAILURE ({0}). Will reconnect after {1} ns"
            java.lang.String r14 = ge.C1031i0.i(r14)
            java.lang.Long r6 = java.lang.Long.valueOf(r8)
            java.lang.Object[] r14 = new java.lang.Object[]{r14, r6}
            r1.c(r4, r5, r14)
            D0.e r14 = r0.f4522p
            if (r14 != 0) goto L_0x0504
            goto L_0x0505
        L_0x0504:
            r3 = r2
        L_0x0505:
            java.lang.String r14 = "previous reconnectTask is not done"
            He.F.t(r3, r14)
            ee.e0 r6 = r0.k
            ge.c0 r7 = new ge.c0
            r7.<init>(r0, r2)
            java.util.concurrent.ScheduledExecutorService r11 = r0.f
            D0.e r14 = r6.c(r7, r8, r10, r11)
            r0.f4522p = r14
        L_0x0519:
            return
        L_0x051a:
            java.lang.Object r0 = r14.f
            ge.i0 r0 = (ge.C1031i0) r0
            ge.f0 r0 = r0.l
            java.util.List r3 = r0.f4509a
            int r4 = r0.b
            java.lang.Object r3 = r3.get(r4)
            ee.o r3 = (ee.C0982o) r3
            java.util.List r3 = r3.f4304a
            int r0 = r0.f4510c
            java.lang.Object r0 = r3.get(r0)
            java.net.SocketAddress r0 = (java.net.SocketAddress) r0
            java.lang.Object r3 = r14.f
            ge.i0 r3 = (ge.C1031i0) r3
            ge.f0 r3 = r3.l
            java.lang.Object r4 = r14.e
            java.util.List r4 = (java.util.List) r4
            r3.f4509a = r4
            r3.d()
            java.lang.Object r3 = r14.f
            ge.i0 r3 = (ge.C1031i0) r3
            java.lang.Object r4 = r14.e
            java.util.List r4 = (java.util.List) r4
            r3.m = r4
            java.lang.Object r3 = r14.f
            ge.i0 r3 = (ge.C1031i0) r3
            ee.i r3 = r3.w
            ee.h r3 = r3.f4298a
            ee.h r4 = ee.C0975h.READY
            if (r3 == r4) goto L_0x0565
            java.lang.Object r3 = r14.f
            ge.i0 r3 = (ge.C1031i0) r3
            ee.i r3 = r3.w
            ee.h r3 = r3.f4298a
            ee.h r4 = ee.C0975h.CONNECTING
            if (r3 != r4) goto L_0x05da
        L_0x0565:
            java.lang.Object r3 = r14.f
            ge.i0 r3 = (ge.C1031i0) r3
            ge.f0 r3 = r3.l
        L_0x056b:
            java.util.List r4 = r3.f4509a
            int r4 = r4.size()
            if (r2 >= r4) goto L_0x058c
            java.util.List r4 = r3.f4509a
            java.lang.Object r4 = r4.get(r2)
            ee.o r4 = (ee.C0982o) r4
            java.util.List r4 = r4.f4304a
            int r4 = r4.indexOf(r0)
            r5 = -1
            if (r4 != r5) goto L_0x0587
            int r2 = r2 + 1
            goto L_0x056b
        L_0x0587:
            r3.b = r2
            r3.f4510c = r4
            goto L_0x05da
        L_0x058c:
            java.lang.Object r0 = r14.f
            ge.i0 r0 = (ge.C1031i0) r0
            ee.i r0 = r0.w
            ee.h r0 = r0.f4298a
            ee.h r2 = ee.C0975h.READY
            if (r0 != r2) goto L_0x05b7
            java.lang.Object r0 = r14.f
            ge.i0 r0 = (ge.C1031i0) r0
            ge.e0 r0 = r0.v
            java.lang.Object r2 = r14.f
            ge.i0 r2 = (ge.C1031i0) r2
            r2.v = r1
            java.lang.Object r2 = r14.f
            ge.i0 r2 = (ge.C1031i0) r2
            ge.f0 r2 = r2.l
            r2.d()
            java.lang.Object r2 = r14.f
            ge.i0 r2 = (ge.C1031i0) r2
            ee.h r3 = ee.C0975h.IDLE
            ge.C1031i0.f(r2, r3)
            goto L_0x05db
        L_0x05b7:
            java.lang.Object r0 = r14.f
            ge.i0 r0 = (ge.C1031i0) r0
            ge.e0 r0 = r0.u
            ee.a0 r2 = ee.a0.f4289o
            java.lang.String r3 = "InternalSubchannel closed pending transport due to address change"
            ee.a0 r2 = r2.g(r3)
            r0.d(r2)
            java.lang.Object r0 = r14.f
            ge.i0 r0 = (ge.C1031i0) r0
            r0.u = r1
            ge.f0 r0 = r0.l
            r0.d()
            java.lang.Object r0 = r14.f
            ge.i0 r0 = (ge.C1031i0) r0
            ge.C1031i0.g(r0)
        L_0x05da:
            r0 = r1
        L_0x05db:
            if (r0 == 0) goto L_0x061d
            java.lang.Object r2 = r14.f
            ge.i0 r2 = (ge.C1031i0) r2
            D0.e r3 = r2.q
            if (r3 == 0) goto L_0x0603
            ge.P0 r2 = r2.r
            ee.a0 r3 = ee.a0.f4289o
            java.lang.String r4 = "InternalSubchannel closed transport early due to address change"
            ee.a0 r3 = r3.g(r4)
            r2.d(r3)
            java.lang.Object r2 = r14.f
            ge.i0 r2 = (ge.C1031i0) r2
            D0.e r2 = r2.q
            r2.B()
            java.lang.Object r2 = r14.f
            ge.i0 r2 = (ge.C1031i0) r2
            r2.q = r1
            r2.r = r1
        L_0x0603:
            java.lang.Object r1 = r14.f
            ge.i0 r1 = (ge.C1031i0) r1
            r1.r = r0
            ee.e0 r2 = r1.k
            B2.A r3 = new B2.A
            r0 = 6
            r3.<init>((int) r0, (java.lang.Object) r14)
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.ScheduledExecutorService r7 = r1.f
            r4 = 5
            D0.e r14 = r2.c(r3, r4, r6, r7)
            r1.q = r14
        L_0x061d:
            return
        L_0x061e:
            java.lang.Object r0 = r14.e
            r4 = r0
            ee.d r4 = (ee.C0971d) r4
            java.lang.Object r0 = r14.f
            ge.P r0 = (ge.P) r0
            java.lang.String r5 = r0.f4467i
            ee.e0 r6 = r0.m
            java.lang.String r7 = "Unable to resolve host "
            java.lang.String r8 = "Using proxy address "
            java.util.logging.Logger r9 = ge.P.v
            java.util.logging.Level r10 = java.util.logging.Level.FINER
            boolean r11 = r9.isLoggable(r10)
            if (r11 == 0) goto L_0x064a
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "Attempting DNS resolution of "
            r11.<init>(r12)
            r11.append(r5)
            java.lang.String r11 = r11.toString()
            r9.finer(r11)
        L_0x064a:
            int r11 = r0.f4468j     // Catch:{ IOException -> 0x067e }
            java.net.InetSocketAddress r11 = java.net.InetSocketAddress.createUnresolved(r5, r11)     // Catch:{ IOException -> 0x067e }
            ee.V r12 = r0.d     // Catch:{ IOException -> 0x067e }
            ee.r r11 = r12.a(r11)     // Catch:{ IOException -> 0x067e }
            if (r11 == 0) goto L_0x065e
            ee.o r12 = new ee.o     // Catch:{ IOException -> 0x067e }
            r12.<init>(r11)     // Catch:{ IOException -> 0x067e }
            goto L_0x065f
        L_0x065e:
            r12 = r1
        L_0x065f:
            java.util.List r11 = java.util.Collections.EMPTY_LIST     // Catch:{ IOException -> 0x067e }
            ee.b r13 = ee.C0969b.b     // Catch:{ IOException -> 0x067e }
            if (r12 == 0) goto L_0x0686
            boolean r0 = r9.isLoggable(r10)     // Catch:{ IOException -> 0x067e }
            if (r0 == 0) goto L_0x0680
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x067e }
            r0.<init>(r8)     // Catch:{ IOException -> 0x067e }
            r0.append(r12)     // Catch:{ IOException -> 0x067e }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x067e }
            r9.finer(r0)     // Catch:{ IOException -> 0x067e }
            goto L_0x0680
        L_0x067b:
            r0 = move-exception
            goto L_0x06fb
        L_0x067e:
            r0 = move-exception
            goto L_0x06d2
        L_0x0680:
            java.util.List r0 = java.util.Collections.singletonList(r12)     // Catch:{ IOException -> 0x067e }
            r8 = r1
            goto L_0x06bb
        L_0x0686:
            A0.l r8 = r0.m()     // Catch:{ IOException -> 0x067e }
            java.lang.Object r0 = r8.e     // Catch:{ IOException -> 0x06a6, all -> 0x06a3 }
            ee.a0 r0 = (ee.a0) r0     // Catch:{ IOException -> 0x06a6, all -> 0x06a3 }
            if (r0 == 0) goto L_0x06a9
            r4.e(r0)     // Catch:{ IOException -> 0x06a6, all -> 0x06a3 }
            java.lang.Object r0 = r8.e
            ee.a0 r0 = (ee.a0) r0
            if (r0 != 0) goto L_0x069a
            r2 = r3
        L_0x069a:
            ge.N r0 = new ge.N
            r0.<init>(r14, r2)
        L_0x069f:
            r6.execute(r0)
            goto L_0x06fa
        L_0x06a3:
            r0 = move-exception
            r1 = r8
            goto L_0x06fb
        L_0x06a6:
            r0 = move-exception
            r1 = r8
            goto L_0x06d2
        L_0x06a9:
            java.lang.Object r0 = r8.g     // Catch:{ IOException -> 0x06a6, all -> 0x06a3 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ IOException -> 0x06a6, all -> 0x06a3 }
            if (r0 == 0) goto L_0x06b0
            r11 = r0
        L_0x06b0:
            java.lang.Object r0 = r8.f     // Catch:{ IOException -> 0x06a6, all -> 0x06a3 }
            ee.Q r0 = (ee.Q) r0     // Catch:{ IOException -> 0x06a6, all -> 0x06a3 }
            if (r0 == 0) goto L_0x06b7
            r1 = r0
        L_0x06b7:
            r0 = r8
            r8 = r1
            r1 = r0
            r0 = r11
        L_0x06bb:
            ee.S r9 = new ee.S     // Catch:{ IOException -> 0x067e }
            r9.<init>(r0, r13, r8)     // Catch:{ IOException -> 0x067e }
            r4.i(r9)     // Catch:{ IOException -> 0x067e }
            if (r1 == 0) goto L_0x06cc
            java.lang.Object r0 = r1.e
            ee.a0 r0 = (ee.a0) r0
            if (r0 != 0) goto L_0x06cc
            r2 = r3
        L_0x06cc:
            ge.N r0 = new ge.N
            r0.<init>(r14, r2)
            goto L_0x069f
        L_0x06d2:
            ee.a0 r8 = ee.a0.f4289o     // Catch:{ all -> 0x067b }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x067b }
            r9.<init>(r7)     // Catch:{ all -> 0x067b }
            r9.append(r5)     // Catch:{ all -> 0x067b }
            java.lang.String r5 = r9.toString()     // Catch:{ all -> 0x067b }
            ee.a0 r5 = r8.g(r5)     // Catch:{ all -> 0x067b }
            ee.a0 r0 = r5.f(r0)     // Catch:{ all -> 0x067b }
            r4.e(r0)     // Catch:{ all -> 0x067b }
            if (r1 == 0) goto L_0x06f4
            java.lang.Object r0 = r1.e
            ee.a0 r0 = (ee.a0) r0
            if (r0 != 0) goto L_0x06f4
            r2 = r3
        L_0x06f4:
            ge.N r0 = new ge.N
            r0.<init>(r14, r2)
            goto L_0x069f
        L_0x06fa:
            return
        L_0x06fb:
            if (r1 == 0) goto L_0x0704
            java.lang.Object r1 = r1.e
            ee.a0 r1 = (ee.a0) r1
            if (r1 != 0) goto L_0x0704
            r2 = r3
        L_0x0704:
            ge.N r1 = new ge.N
            r1.<init>(r14, r2)
            r6.execute(r1)
            throw r0
        L_0x070d:
            java.lang.Object r0 = r14.f
            ge.K r0 = (ge.K) r0
            ge.t r0 = r0.d
            java.lang.Object r14 = r14.e
            ee.M r14 = (ee.M) r14
            r0.z(r14)
            return
        L_0x071b:
            java.lang.Object r0 = r14.f
            ge.K r0 = (ge.K) r0
            ge.t r0 = r0.d
            java.lang.Object r14 = r14.e
            fe.i r14 = (fe.i) r14
            r0.h0(r14)
            return
        L_0x0729:
            java.lang.Object r0 = r14.f
            ge.L r0 = (ge.L) r0
            ge.r r0 = r0.f
            java.lang.Object r14 = r14.e
            ee.a0 r14 = (ee.a0) r14
            r0.v(r14)
            return
        L_0x0737:
            java.lang.Object r0 = r14.f
            ge.L r0 = (ge.L) r0
            ge.r r0 = r0.f
            java.lang.Object r14 = r14.e
            he.a r14 = (he.C1076a) r14
            r0.x(r14)
            return
        L_0x0745:
            java.lang.Object r0 = r14.f
            ge.L r0 = (ge.L) r0
            ge.r r0 = r0.f
            java.lang.Object r14 = r14.e
            io.grpc.Deadline r14 = (io.grpc.Deadline) r14
            r0.u(r14)
            return
        L_0x0753:
            java.lang.Object r0 = r14.f
            ge.L r0 = (ge.L) r0
            ge.r r0 = r0.f
            java.lang.Object r14 = r14.e
            ee.n r14 = (ee.C0981n) r14
            r0.b(r14)
            return
        L_0x0761:
            java.lang.Object r0 = r14.f
            ge.L r0 = (ge.L) r0
            ge.r r0 = r0.f
            java.lang.Object r14 = r14.e
            ee.f r14 = (ee.C0973f) r14
            r0.w(r14)
            return
        L_0x076f:
            java.lang.Object r14 = r14.f
            ge.G r14 = (ge.G) r14
            G0.e r14 = r14.f4429h
            java.lang.Object r14 = r14.d
            ge.F0 r14 = (ge.F0) r14
            java.util.concurrent.atomic.AtomicBoolean r14 = r14.f4407G
            boolean r14 = r14.get()
            java.lang.String r0 = "Channel must have been shut down"
            He.F.t(r14, r0)
            return
        L_0x0785:
            java.lang.Object r0 = r14.f
            ge.C r0 = (ge.C1002C) r0
            ee.d r0 = r0.f4386c
            java.lang.Object r14 = r14.e
            r0.g(r14)
            return
        L_0x0791:
            java.lang.Object r0 = r14.f
            ge.C r0 = (ge.C1002C) r0
            ee.d r0 = r0.f4386c
            java.lang.Object r14 = r14.e
            ee.M r14 = (ee.M) r14
            r0.f(r14)
            return
        L_0x079f:
            java.lang.Object r0 = r14.f
            ge.D r0 = (ge.C1003D) r0
            ee.q r0 = r0.f4388i
            java.lang.Object r14 = r14.e
            r0.i(r14)
            return
        L_0x07ab:
            java.lang.Object r0 = r14.f
            ge.D r0 = (ge.C1003D) r0
            ee.q r0 = r0.f4388i
            java.lang.Object r14 = r14.e
            ee.a0 r14 = (ee.a0) r14
            java.lang.String r1 = r14.b
            java.lang.Throwable r14 = r14.f4291c
            r0.b(r1, r14)
            return
        L_0x07bd:
            java.lang.Object r0 = r14.f
            ge.D r0 = (ge.C1003D) r0
            ee.a0 r1 = ee.a0.f4286h
            java.lang.Object r14 = r14.e
            java.lang.StringBuilder r14 = (java.lang.StringBuilder) r14
            java.lang.String r14 = r14.toString()
            ee.a0 r14 = r1.g(r14)
            r0.m(r14, r3)
            return
        L_0x07d3:
            java.lang.Object r0 = r14.f
            r1 = r0
            com.google.common.util.concurrent.s r1 = (com.google.common.util.concurrent.s) r1
            java.lang.Object r14 = r14.e
            java.util.concurrent.Future r14 = (java.util.concurrent.Future) r14
            boolean r0 = r14 instanceof K2.a
            if (r0 == 0) goto L_0x07ed
            r0 = r14
            K2.a r0 = (K2.a) r0
            java.lang.Throwable r0 = r0.tryInternalFastPathGetFailure()
            if (r0 == 0) goto L_0x07ed
            r1.onFailure(r0)
            goto L_0x080d
        L_0x07ed:
            boolean r0 = r14.isDone()     // Catch:{ ExecutionException -> 0x0804, all -> 0x07fe }
            java.lang.String r2 = "Future was expected to be done: %s"
            He.F.q(r2, r14, r0)     // Catch:{ ExecutionException -> 0x0804, all -> 0x07fe }
            java.lang.Object r14 = o1.C0246a.Z(r14)     // Catch:{ ExecutionException -> 0x0804, all -> 0x07fe }
            r1.onSuccess(r14)
            goto L_0x080d
        L_0x07fe:
            r0 = move-exception
            r14 = r0
            r1.onFailure(r14)
            goto L_0x080d
        L_0x0804:
            r0 = move-exception
            r14 = r0
            java.lang.Throwable r14 = r14.getCause()
            r1.onFailure(r14)
        L_0x080d:
            return
        L_0x080e:
            java.lang.Object r0 = r14.f
            r4 = r0
            cg.g r4 = (cg.g) r4
            Vf.x r5 = r4.e
        L_0x0815:
            java.lang.Object r0 = r14.e     // Catch:{ all -> 0x081d }
            java.lang.Runnable r0 = (java.lang.Runnable) r0     // Catch:{ all -> 0x081d }
            r0.run()     // Catch:{ all -> 0x081d }
            goto L_0x0823
        L_0x081d:
            r0 = move-exception
            qe.i r1 = qe.C1233i.d
            Vf.D.l(r0, r1)
        L_0x0823:
            java.lang.Runnable r0 = r4.l()
            if (r0 != 0) goto L_0x082a
            goto L_0x083a
        L_0x082a:
            r14.e = r0
            int r2 = r2 + r3
            r0 = 16
            if (r2 < r0) goto L_0x0815
            boolean r0 = r5.j(r4)
            if (r0 == 0) goto L_0x0815
            r5.i(r4, r14)
        L_0x083a:
            return
        L_0x083b:
            java.lang.Object r0 = r14.f
            r2 = r0
            Vf.l r2 = (Vf.C0875l) r2
            java.lang.Object r14 = r14.e
            com.google.common.util.concurrent.ListenableFuture r14 = (com.google.common.util.concurrent.ListenableFuture) r14
            boolean r0 = r14.isCancelled()
            if (r0 == 0) goto L_0x084e
            r2.d(r1)
            goto L_0x0866
        L_0x084e:
            java.lang.Object r14 = o1.C0246a.Z(r14)     // Catch:{ ExecutionException -> 0x0856 }
            r2.resumeWith(r14)     // Catch:{ ExecutionException -> 0x0856 }
            goto L_0x0866
        L_0x0856:
            r0 = move-exception
            r14 = r0
            java.lang.Throwable r14 = r14.getCause()
            kotlin.jvm.internal.j.b(r14)
            me.j r14 = L2.a.l(r14)
            r2.resumeWith(r14)
        L_0x0866:
            return
        L_0x0867:
            java.lang.Object r0 = r14.e
            Vf.l r0 = (Vf.C0875l) r0
            java.lang.Object r14 = r14.f
            Wf.e r14 = (Wf.e) r14
            r0.B(r14)
            return
        L_0x0873:
            java.lang.Object r0 = r14.f
            Vf.l r0 = (Vf.C0875l) r0
            java.lang.Object r14 = r14.e
            Vf.Z r14 = (Vf.Z) r14
            r0.B(r14)
            return
        L_0x087f:
            java.lang.Object r0 = r14.f
            P1.f r0 = (P1.f) r0
            java.lang.Object r1 = r0.f574c
            monitor-enter(r1)
            java.lang.Object r0 = r14.f     // Catch:{ all -> 0x089b }
            P1.f r0 = (P1.f) r0     // Catch:{ all -> 0x089b }
            java.lang.Object r0 = r0.d     // Catch:{ all -> 0x089b }
            com.samsung.android.gallery.app.ui.map.picker.google.a r0 = (com.samsung.android.gallery.app.ui.map.picker.google.a) r0     // Catch:{ all -> 0x089b }
            java.lang.Object r14 = r14.e     // Catch:{ all -> 0x089b }
            P1.h r14 = (P1.h) r14     // Catch:{ all -> 0x089b }
            java.lang.Object r14 = r14.f()     // Catch:{ all -> 0x089b }
            r0.a(r14)     // Catch:{ all -> 0x089b }
            monitor-exit(r1)     // Catch:{ all -> 0x089b }
            return
        L_0x089b:
            r0 = move-exception
            r14 = r0
            monitor-exit(r1)     // Catch:{ all -> 0x089b }
            throw r14
        L_0x089f:
            java.lang.Object r0 = r14.f
            P1.f r0 = (P1.f) r0
            java.lang.Object r1 = r0.f574c
            monitor-enter(r1)
            java.lang.Object r0 = r14.f     // Catch:{ all -> 0x08be }
            P1.f r0 = (P1.f) r0     // Catch:{ all -> 0x08be }
            java.lang.Object r0 = r0.d     // Catch:{ all -> 0x08be }
            com.samsung.android.gallery.app.ui.map.picker.google.b r0 = (com.samsung.android.gallery.app.ui.map.picker.google.b) r0     // Catch:{ all -> 0x08be }
            java.lang.Object r14 = r14.e     // Catch:{ all -> 0x08be }
            P1.h r14 = (P1.h) r14     // Catch:{ all -> 0x08be }
            java.lang.Exception r14 = r14.e()     // Catch:{ all -> 0x08be }
            w1.r.b(r14)     // Catch:{ all -> 0x08be }
            r0.a(r14)     // Catch:{ all -> 0x08be }
            monitor-exit(r1)     // Catch:{ all -> 0x08be }
            return
        L_0x08be:
            r0 = move-exception
            r14 = r0
            monitor-exit(r1)     // Catch:{ all -> 0x08be }
            throw r14
        L_0x08c2:
            java.lang.Object r0 = r14.f
            P1.f r0 = (P1.f) r0
            java.lang.Object r1 = r0.f574c
            monitor-enter(r1)
            java.lang.Object r0 = r14.f     // Catch:{ all -> 0x08da }
            P1.f r0 = (P1.f) r0     // Catch:{ all -> 0x08da }
            java.lang.Object r0 = r0.d     // Catch:{ all -> 0x08da }
            P1.b r0 = (P1.b) r0     // Catch:{ all -> 0x08da }
            java.lang.Object r14 = r14.e     // Catch:{ all -> 0x08da }
            P1.h r14 = (P1.h) r14     // Catch:{ all -> 0x08da }
            r0.o(r14)     // Catch:{ all -> 0x08da }
            monitor-exit(r1)     // Catch:{ all -> 0x08da }
            return
        L_0x08da:
            r0 = move-exception
            r14 = r0
            monitor-exit(r1)     // Catch:{ all -> 0x08da }
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: P1.e.run():void");
    }

    public String toString() {
        switch (this.d) {
            case 7:
                k V = a.V(this);
                l lVar = new l(1);
                ((l) V.e).g = lVar;
                V.e = lVar;
                lVar.f = (s) this.f;
                return V.toString();
            default:
                return super.toString();
        }
    }

    public /* synthetic */ e(Object obj, Object obj2, boolean z, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public e(P p6, C0971d dVar) {
        this.d = 21;
        this.f = p6;
        F.n(dVar, "savedListener");
        this.e = dVar;
    }
}
