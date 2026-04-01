package ge;

import java.util.ArrayList;
import q2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s1 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final Object f;

    public /* synthetic */ s1(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    /* JADX WARNING: type inference failed for: r5v8, types: [android.os.IInterface] */
    /* JADX WARNING: type inference failed for: r5v9, types: [E1.a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r7 = this;
            int r0 = r7.d
            r1 = 0
            java.lang.String r2 = "GoogleApiManager"
            r3 = 1
            r4 = 0
            java.lang.Object r5 = r7.f
            java.lang.Object r7 = r7.e
            switch(r0) {
                case 0: goto L_0x017e;
                case 1: goto L_0x0170;
                case 2: goto L_0x011f;
                case 3: goto L_0x00f3;
                case 4: goto L_0x0098;
                default: goto L_0x000e;
            }
        L_0x000e:
            v1.r r7 = (v1.r) r7
            O1.e r5 = (O1.e) r5
            t1.a r0 = r5.e
            int r1 = r0.e
            if (r1 != 0) goto L_0x008d
            w1.n r0 = r5.f
            w1.r.b(r0)
            t1.a r1 = r0.f
            int r5 = r1.e
            if (r5 != 0) goto L_0x006e
            Yd.b r1 = r7.f1978h
            android.os.IBinder r0 = r0.e
            if (r0 != 0) goto L_0x002a
            goto L_0x0040
        L_0x002a:
            int r4 = w1.C0314a.b
            java.lang.String r4 = "com.google.android.gms.common.internal.IAccountAccessor"
            android.os.IInterface r5 = r0.queryLocalInterface(r4)
            boolean r6 = r5 instanceof w1.e
            if (r6 == 0) goto L_0x003a
            r4 = r5
            w1.e r4 = (w1.e) r4
            goto L_0x0040
        L_0x003a:
            w1.C r5 = new w1.C
            r5.<init>(r0, r4, r3)
            r4 = r5
        L_0x0040:
            java.util.Set r0 = r7.e
            r1.getClass()
            if (r4 == 0) goto L_0x005a
            if (r0 != 0) goto L_0x004a
            goto L_0x005a
        L_0x004a:
            r1.d = r4
            r1.e = r0
            boolean r2 = r1.f3914a
            if (r2 == 0) goto L_0x0092
            java.lang.Object r1 = r1.b
            u1.c r1 = (u1.C0285c) r1
            r1.d(r4, r0)
            goto L_0x0092
        L_0x005a:
            java.lang.Exception r0 = new java.lang.Exception
            r0.<init>()
            java.lang.String r3 = "Received null response from onSignInSuccess"
            android.util.Log.wtf(r2, r3, r0)
            t1.a r0 = new t1.a
            r2 = 4
            r0.<init>(r2)
            r1.b(r0)
            goto L_0x0092
        L_0x006e:
            java.lang.String r0 = java.lang.String.valueOf(r1)
            java.lang.Exception r2 = new java.lang.Exception
            r2.<init>()
            java.lang.String r3 = "SignInCoordinator"
            java.lang.String r4 = "Sign-in succeeded with resolve account failure: "
            java.lang.String r0 = r4.concat(r0)
            android.util.Log.wtf(r3, r0, r2)
            Yd.b r0 = r7.f1978h
            r0.b(r1)
            O1.a r7 = r7.g
            r7.disconnect()
            goto L_0x0097
        L_0x008d:
            Yd.b r1 = r7.f1978h
            r1.b(r0)
        L_0x0092:
            O1.a r7 = r7.g
            r7.disconnect()
        L_0x0097:
            return
        L_0x0098:
            t1.a r5 = (t1.C0276a) r5
            Yd.b r7 = (Yd.b) r7
            java.lang.Object r0 = r7.b
            u1.c r0 = (u1.C0285c) r0
            java.lang.Object r1 = r7.f
            v1.c r1 = (v1.C0298c) r1
            java.util.concurrent.ConcurrentHashMap r1 = r1.f1962j
            java.lang.Object r6 = r7.f3915c
            v1.a r6 = (v1.C0296a) r6
            java.lang.Object r1 = r1.get(r6)
            v1.k r1 = (v1.k) r1
            if (r1 != 0) goto L_0x00b3
            goto L_0x00f2
        L_0x00b3:
            int r6 = r5.e
            if (r6 != 0) goto L_0x00ef
            r7.f3914a = r3
            boolean r3 = r0.e()
            if (r3 == 0) goto L_0x00d1
            boolean r1 = r7.f3914a
            if (r1 == 0) goto L_0x00f2
            java.lang.Object r1 = r7.d
            w1.e r1 = (w1.e) r1
            if (r1 == 0) goto L_0x00f2
            java.lang.Object r7 = r7.e
            java.util.Set r7 = (java.util.Set) r7
            r0.d(r1, r7)
            goto L_0x00f2
        L_0x00d1:
            java.util.Set r7 = r0.f()     // Catch:{ SecurityException -> 0x00d9 }
            r0.d(r4, r7)     // Catch:{ SecurityException -> 0x00d9 }
            goto L_0x00f2
        L_0x00d9:
            r7 = move-exception
            java.lang.String r3 = "Failed to get service from broker. "
            android.util.Log.e(r2, r3, r7)
            java.lang.String r7 = "Failed to get service from broker."
            r0.a(r7)
            t1.a r7 = new t1.a
            r0 = 10
            r7.<init>(r0)
            r1.n(r7, r4)
            goto L_0x00f2
        L_0x00ef:
            r1.n(r5, r4)
        L_0x00f2:
            return
        L_0x00f3:
            q2.u r5 = (q2.u) r5
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "onPreDraw position Change invalidateBlurTargetView "
            r0.<init>(r2)
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            androidx.core.oneui.common.internal.log.LogTagHelperKt.debug(r5, r0)
            java.util.Iterator r7 = r7.iterator()
        L_0x010c:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x011c
            java.lang.Object r0 = r7.next()
            android.view.View r0 = (android.view.View) r0
            r0.invalidate()
            goto L_0x010c
        L_0x011c:
            r5.f1892C = r1
            return
        L_0x011f:
            k2.j r5 = (k2.j) r5
            k2.m r7 = (k2.m) r7
            androidx.appcompat.view.menu.MenuBuilder r0 = r7.d
            if (r0 == 0) goto L_0x012a
            r0.changeMenuMode()
        L_0x012a:
            k2.h r0 = r7.e
            if (r0 == 0) goto L_0x016d
            android.content.Context r0 = r7.f1832h
            android.content.res.Resources r0 = r0.getResources()
            r2 = 2131167963(0x7f070adb, float:1.7950214E38)
            int r0 = r0.getDimensionPixelSize(r2)
            k2.h r2 = r7.e
            k2.d r2 = r2.L
            int r2 = androidx.core.view.ViewCompat.getLayoutDirection(r2)
            if (r2 != r3) goto L_0x0146
            goto L_0x0147
        L_0x0146:
            r3 = r1
        L_0x0147:
            k2.h r2 = r7.e
            boolean r2 = r2.V
            if (r2 == 0) goto L_0x0159
            android.content.Context r1 = r7.f1832h
            android.content.res.Resources r1 = r1.getResources()
            int r2 = androidx.appcompat.R$dimen.sesl_menu_popup_offset_horizontal
            int r1 = r1.getDimensionPixelSize(r2)
        L_0x0159:
            k2.h r2 = r7.e
            android.os.IBinder r2 = r2.getWindowToken()
            if (r2 == 0) goto L_0x016d
            if (r3 == 0) goto L_0x0164
            int r1 = -r1
        L_0x0164:
            int r0 = -r0
            boolean r0 = r5.tryShow(r1, r0)
            if (r0 == 0) goto L_0x016d
            r7.k = r5
        L_0x016d:
            r7.f1833i = r4
            return
        L_0x0170:
            ge.W0 r7 = (ge.W0) r7
            java.lang.Object r7 = r7.f
            ge.t0 r7 = (ge.C1057t0) r7
            ge.t r7 = r7.f4560x
            fe.i r5 = (fe.i) r5
            r7.h0(r5)
            return
        L_0x017e:
            ge.W0 r7 = (ge.W0) r7
            java.lang.Object r7 = r7.f
            ge.t0 r7 = (ge.C1057t0) r7
            ge.t r7 = r7.f4560x
            ee.M r5 = (ee.M) r5
            r7.z(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.s1.run():void");
    }

    public s1(u uVar, ArrayList arrayList) {
        this.d = 3;
        this.f = uVar;
        this.e = arrayList;
    }
}
