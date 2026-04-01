package Yf;

import Ae.c;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p implements g {

    /* renamed from: a  reason: collision with root package name */
    public final i f3928a;

    public p(c cVar) {
        this.f3928a = (i) cVar;
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [Ae.c, se.i] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object collect(Yf.h r6, qe.C1227c r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof Yf.a
            if (r0 == 0) goto L_0x0013
            r0 = r7
            Yf.a r0 = (Yf.a) r0
            int r1 = r0.g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.g = r1
            goto L_0x0018
        L_0x0013:
            Yf.a r0 = new Yf.a
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.e
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.g
            me.x r3 = me.x.f4917a
            r4 = 1
            if (r2 == 0) goto L_0x0035
            if (r2 != r4) goto L_0x002d
            Zf.l r5 = r0.d
            L2.a.A(r7)     // Catch:{ all -> 0x002b }
            goto L_0x0053
        L_0x002b:
            r6 = move-exception
            goto L_0x005d
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            L2.a.A(r7)
            Zf.l r7 = new Zf.l
            qe.h r2 = r0.getContext()
            r7.<init>(r6, r2)
            r0.d = r7     // Catch:{ all -> 0x005b }
            r0.g = r4     // Catch:{ all -> 0x005b }
            se.i r5 = r5.f3928a     // Catch:{ all -> 0x0057 }
            java.lang.Object r5 = r5.invoke(r7, r0)     // Catch:{ all -> 0x0057 }
            if (r5 != r1) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r5 = r3
        L_0x004f:
            if (r5 != r1) goto L_0x0052
            return r1
        L_0x0052:
            r5 = r7
        L_0x0053:
            r5.releaseIntercepted()
            return r3
        L_0x0057:
            r5 = move-exception
            r6 = r5
        L_0x0059:
            r5 = r7
            goto L_0x005d
        L_0x005b:
            r6 = move-exception
            goto L_0x0059
        L_0x005d:
            r5.releaseIntercepted()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: Yf.p.collect(Yf.h, qe.c):java.lang.Object");
    }
}
