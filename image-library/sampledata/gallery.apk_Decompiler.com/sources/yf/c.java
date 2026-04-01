package Yf;

import Xf.a;
import Zf.b;
import qe.C1232h;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends b {
    public final i d;
    public final i e;

    public c(Ae.c cVar, C1232h hVar, int i2, a aVar) {
        super(hVar, i2, aVar);
        i iVar = (i) cVar;
        this.d = iVar;
        this.e = iVar;
    }

    /* JADX WARNING: type inference failed for: r5v4, types: [Ae.c, se.i] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0053 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object a(Xf.r r6, qe.C1227c r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof Yf.b
            if (r0 == 0) goto L_0x0013
            r0 = r7
            Yf.b r0 = (Yf.b) r0
            int r1 = r0.g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.g = r1
            goto L_0x001a
        L_0x0013:
            Yf.b r0 = new Yf.b
            se.c r7 = (se.C1271c) r7
            r0.<init>(r5, r7)
        L_0x001a:
            java.lang.Object r7 = r0.e
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.g
            me.x r3 = me.x.f4917a
            r4 = 1
            if (r2 == 0) goto L_0x0035
            if (r2 != r4) goto L_0x002d
            Xf.r r6 = r0.d
            L2.a.A(r7)
            goto L_0x0049
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            L2.a.A(r7)
            r0.d = r6
            r0.g = r4
            se.i r5 = r5.d
            java.lang.Object r5 = r5.invoke(r6, r0)
            if (r5 != r1) goto L_0x0045
            goto L_0x0046
        L_0x0045:
            r5 = r3
        L_0x0046:
            if (r5 != r1) goto L_0x0049
            return r1
        L_0x0049:
            Xf.q r6 = (Xf.q) r6
            Xf.e r5 = r6.g
            boolean r5 = r5.u()
            if (r5 == 0) goto L_0x0054
            return r3
        L_0x0054:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "'awaitClose { yourCallbackOrListener.cancel() }' should be used in the end of callbackFlow block.\nOtherwise, a callback/listener may leak in case of external cancellation.\nSee callbackFlow API documentation for the details."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: Yf.c.a(Xf.r, qe.c):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [Ae.c, se.i] */
    public final b b(C1232h hVar, int i2, a aVar) {
        return new c(this.e, hVar, i2, aVar);
    }

    public final String toString() {
        return "block[" + this.d + "] -> " + super.toString();
    }
}
