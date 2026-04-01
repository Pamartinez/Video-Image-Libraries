package Yf;

import Ae.e;
import me.c;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n implements g {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3925a = 1;
    public final /* synthetic */ g b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ c f3926c;

    public n(g gVar, Ae.c cVar) {
        this.b = gVar;
        this.f3926c = (i) cVar;
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [Ae.c, se.i] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object collect(Yf.h r12, qe.C1227c r13) {
        /*
            r11 = this;
            int r0 = r11.f3925a
            switch(r0) {
                case 0: goto L_0x001c;
                default: goto L_0x0005;
            }
        L_0x0005:
            Yf.e r0 = new Yf.e
            me.c r1 = r11.f3926c
            se.i r1 = (se.i) r1
            r0.<init>((Yf.h) r12, (Ae.c) r1)
            Yf.g r11 = r11.b
            java.lang.Object r11 = r11.collect(r0, r13)
            re.a r12 = re.C1245a.COROUTINE_SUSPENDED
            if (r11 != r12) goto L_0x0019
            goto L_0x001b
        L_0x0019:
            me.x r11 = me.x.f4917a
        L_0x001b:
            return r11
        L_0x001c:
            boolean r0 = r13 instanceof Yf.m
            if (r0 == 0) goto L_0x002f
            r0 = r13
            Yf.m r0 = (Yf.m) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x002f
            int r1 = r1 - r2
            r0.e = r1
            goto L_0x0034
        L_0x002f:
            Yf.m r0 = new Yf.m
            r0.<init>(r11, r13)
        L_0x0034:
            java.lang.Object r13 = r0.d
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.e
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0062
            if (r2 == r4) goto L_0x0056
            if (r2 != r3) goto L_0x004e
            long r11 = r0.f3924j
            java.lang.Throwable r2 = r0.f3923i
            Yf.h r5 = r0.f3922h
            Yf.n r6 = r0.g
            L2.a.A(r13)
            goto L_0x009e
        L_0x004e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0056:
            long r11 = r0.f3924j
            Yf.h r2 = r0.f3922h
            Yf.n r5 = r0.g
            L2.a.A(r13)
            r6 = r5
            r5 = r2
            goto L_0x007f
        L_0x0062:
            L2.a.A(r13)
            r5 = 0
        L_0x0067:
            Yf.g r13 = r11.b
            r0.g = r11
            r0.f3922h = r12
            r2 = 0
            r0.f3923i = r2
            r0.f3924j = r5
            r0.e = r4
            java.io.Serializable r13 = L2.a.g(r13, r12, r0)
            if (r13 != r1) goto L_0x007b
            goto L_0x00b6
        L_0x007b:
            r9 = r5
            r6 = r11
            r5 = r12
            r11 = r9
        L_0x007f:
            r2 = r13
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            if (r2 == 0) goto L_0x00b0
            me.c r13 = r6.f3926c
            Ae.e r13 = (Ae.e) r13
            java.lang.Long r7 = new java.lang.Long
            r7.<init>(r11)
            r0.g = r6
            r0.f3922h = r5
            r0.f3923i = r2
            r0.f3924j = r11
            r0.e = r3
            java.lang.Object r13 = r13.invoke(r5, r2, r7, r0)
            if (r13 != r1) goto L_0x009e
            goto L_0x00b6
        L_0x009e:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x00af
            r7 = 1
            long r11 = r11 + r7
            r13 = r4
        L_0x00aa:
            r9 = r11
            r12 = r5
            r11 = r6
            r5 = r9
            goto L_0x00b2
        L_0x00af:
            throw r2
        L_0x00b0:
            r13 = 0
            goto L_0x00aa
        L_0x00b2:
            if (r13 != 0) goto L_0x0067
            me.x r1 = me.x.f4917a
        L_0x00b6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: Yf.n.collect(Yf.h, qe.c):java.lang.Object");
    }

    public n(g gVar, e eVar) {
        this.b = gVar;
        this.f3926c = eVar;
    }
}
