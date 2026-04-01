package Yf;

import Ae.c;
import java.io.Serializable;
import kotlin.jvm.internal.u;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e implements h {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ h e;
    public final /* synthetic */ Serializable f;

    public e(f fVar, u uVar, h hVar) {
        this.f = uVar;
        this.e = hVar;
    }

    /* JADX WARNING: type inference failed for: r8v9, types: [Ae.c, se.i] */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object emit(java.lang.Object r7, qe.C1227c r8) {
        /*
            r6 = this;
            int r0 = r6.d
            switch(r0) {
                case 0: goto L_0x00b3;
                case 1: goto L_0x0066;
                default: goto L_0x0005;
            }
        L_0x0005:
            boolean r0 = r8 instanceof Yf.o
            if (r0 == 0) goto L_0x0018
            r0 = r8
            Yf.o r0 = (Yf.o) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0018
            int r1 = r1 - r2
            r0.e = r1
            goto L_0x001d
        L_0x0018:
            Yf.o r0 = new Yf.o
            r0.<init>(r6, r8)
        L_0x001d:
            java.lang.Object r8 = r0.d
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.e
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003f
            if (r2 == r4) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            L2.a.A(r8)
            goto L_0x0063
        L_0x002f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0037:
            Yf.h r6 = r0.f3927h
            java.lang.Object r7 = r0.g
            L2.a.A(r8)
            goto L_0x0055
        L_0x003f:
            L2.a.A(r8)
            java.io.Serializable r8 = r6.f
            se.i r8 = (se.i) r8
            r0.g = r7
            Yf.h r6 = r6.e
            r0.f3927h = r6
            r0.e = r4
            java.lang.Object r8 = r8.invoke(r7, r0)
            if (r8 != r1) goto L_0x0055
            goto L_0x0065
        L_0x0055:
            r8 = 0
            r0.g = r8
            r0.f3927h = r8
            r0.e = r3
            java.lang.Object r6 = r6.emit(r7, r0)
            if (r6 != r1) goto L_0x0063
            goto L_0x0065
        L_0x0063:
            me.x r1 = me.x.f4917a
        L_0x0065:
            return r1
        L_0x0066:
            boolean r0 = r8 instanceof Yf.l
            if (r0 == 0) goto L_0x0079
            r0 = r8
            Yf.l r0 = (Yf.l) r0
            int r1 = r0.g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0079
            int r1 = r1 - r2
            r0.g = r1
            goto L_0x007e
        L_0x0079:
            Yf.l r0 = new Yf.l
            r0.<init>(r6, r8)
        L_0x007e:
            java.lang.Object r8 = r0.e
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.g
            r3 = 1
            if (r2 == 0) goto L_0x0099
            if (r2 != r3) goto L_0x0091
            Yf.e r6 = r0.d
            L2.a.A(r8)     // Catch:{ all -> 0x008f }
            goto L_0x00a9
        L_0x008f:
            r7 = move-exception
            goto L_0x00ac
        L_0x0091:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0099:
            L2.a.A(r8)
            Yf.h r8 = r6.e     // Catch:{ all -> 0x008f }
            r0.d = r6     // Catch:{ all -> 0x008f }
            r0.g = r3     // Catch:{ all -> 0x008f }
            java.lang.Object r6 = r8.emit(r7, r0)     // Catch:{ all -> 0x008f }
            if (r6 != r1) goto L_0x00a9
            goto L_0x00ab
        L_0x00a9:
            me.x r1 = me.x.f4917a
        L_0x00ab:
            return r1
        L_0x00ac:
            java.io.Serializable r6 = r6.f
            kotlin.jvm.internal.u r6 = (kotlin.jvm.internal.u) r6
            r6.d = r7
            throw r7
        L_0x00b3:
            java.io.Serializable r0 = r6.f
            kotlin.jvm.internal.u r0 = (kotlin.jvm.internal.u) r0
            boolean r1 = r8 instanceof Yf.d
            if (r1 == 0) goto L_0x00ca
            r1 = r8
            Yf.d r1 = (Yf.d) r1
            int r2 = r1.f
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x00ca
            int r2 = r2 - r3
            r1.f = r2
            goto L_0x00cf
        L_0x00ca:
            Yf.d r1 = new Yf.d
            r1.<init>(r6, r8)
        L_0x00cf:
            java.lang.Object r8 = r1.d
            re.a r2 = re.C1245a.COROUTINE_SUSPENDED
            int r3 = r1.f
            me.x r4 = me.x.f4917a
            r5 = 1
            if (r3 == 0) goto L_0x00e9
            if (r3 != r5) goto L_0x00e1
            L2.a.A(r8)
        L_0x00df:
            r2 = r4
            goto L_0x0104
        L_0x00e1:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x00e9:
            L2.a.A(r8)
            java.lang.Object r8 = r0.d
            Qe.B r3 = Zf.k.f3984a
            if (r8 == r3) goto L_0x00f8
            boolean r8 = kotlin.jvm.internal.j.a(r8, r7)
            if (r8 != 0) goto L_0x00df
        L_0x00f8:
            r0.d = r7
            r1.f = r5
            Yf.h r6 = r6.e
            java.lang.Object r6 = r6.emit(r7, r1)
            if (r6 != r2) goto L_0x00df
        L_0x0104:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Yf.e.emit(java.lang.Object, qe.c):java.lang.Object");
    }

    public e(h hVar, c cVar) {
        this.e = hVar;
        this.f = (i) cVar;
    }

    public e(h hVar, u uVar) {
        this.e = hVar;
        this.f = uVar;
    }
}
