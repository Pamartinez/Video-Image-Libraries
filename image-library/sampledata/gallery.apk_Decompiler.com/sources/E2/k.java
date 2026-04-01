package E2;

import A0.l;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f168a = 1;
    public boolean b;

    /* renamed from: c  reason: collision with root package name */
    public Object f169c;
    public Object d;
    public Object e;

    public /* synthetic */ k() {
    }

    public void a(Object obj, String str) {
        l lVar = new l(1);
        ((l) this.e).g = lVar;
        this.e = lVar;
        lVar.f = obj;
        lVar.e = str;
    }

    public void b(String str, long j2) {
        d(str, String.valueOf(j2));
    }

    public void c(String str, boolean z) {
        d(str, String.valueOf(z));
    }

    public void d(String str, String str2) {
        l lVar = new l(1);
        ((l) this.e).g = lVar;
        this.e = lVar;
        lVar.f = str2;
        lVar.e = str;
    }

    public boolean e() {
        og.k.m("AsConnectionManager", "isServiceConnected = ", Boolean.valueOf(this.b));
        return this.b;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: c3.a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: c3.a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v20, resolved type: c3.a} */
    /* JADX WARNING: type inference failed for: r4v11, types: [java.lang.Object, c3.a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void f(int r5, android.content.ComponentName r6, android.os.IBinder r7) {
        /*
            r4 = this;
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "AsConnectionManager"
            java.lang.String r2 = "notifyServiceConnection"
            og.k.m(r1, r2, r0)
            java.lang.Object r0 = r4.f169c
            B1.b r0 = (B1.b) r0
            if (r0 == 0) goto L_0x0099
            java.lang.Object r0 = r0.e
            Fd.a r0 = (Fd.C0744a) r0
            java.lang.String r1 = "AsVisionServiceConnection"
            r2 = 0
            r3 = 1
            if (r5 == r3) goto L_0x0050
            r7 = 2
            if (r5 == r7) goto L_0x0030
            r6 = 3
            if (r5 == r6) goto L_0x0026
            goto L_0x0099
        L_0x0026:
            r4.b = r2
            java.lang.Object r4 = r0.g
            S1.n r4 = (S1.n) r4
            r4.sendEmptyMessage(r6)
            return
        L_0x0030:
            r4.b = r2
            r0.getClass()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "onServiceDisconnected "
            r4.<init>(r5)
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            java.lang.Object[] r5 = new java.lang.Object[r2]
            og.k.m(r1, r4, r5)
            java.lang.Object r4 = r0.g
            S1.n r4 = (S1.n) r4
            r4.sendEmptyMessage(r7)
            return
        L_0x0050:
            r4.b = r3
            r0.getClass()
            java.lang.String r4 = "onServiceConnected"
            java.lang.Object[] r5 = new java.lang.Object[r2]
            og.k.m(r1, r4, r5)
            int r4 = c3.b.f1097a
            if (r7 != 0) goto L_0x0062
            r4 = 0
            goto L_0x0078
        L_0x0062:
            java.lang.String r4 = "com.samsung.android.alive.service.sdk.common.image.IImageService"
            android.os.IInterface r4 = r7.queryLocalInterface(r4)
            if (r4 == 0) goto L_0x0071
            boolean r5 = r4 instanceof c3.c
            if (r5 == 0) goto L_0x0071
            c3.c r4 = (c3.c) r4
            goto L_0x0078
        L_0x0071:
            c3.a r4 = new c3.a
            r4.<init>()
            r4.f1096a = r7
        L_0x0078:
            r0.f3375h = r4
            c3.a r4 = (c3.a) r4     // Catch:{ RemoteException -> 0x0086 }
            android.os.IBinder r4 = r4.f1096a     // Catch:{ RemoteException -> 0x0086 }
            java.lang.Object r5 = r0.f3376i     // Catch:{ RemoteException -> 0x0086 }
            b3.b r5 = (b3.C0084b) r5     // Catch:{ RemoteException -> 0x0086 }
            r4.linkToDeath(r5, r2)     // Catch:{ RemoteException -> 0x0086 }
            goto L_0x0092
        L_0x0086:
            r4 = move-exception
            java.lang.String r5 = "RemoteException"
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r5 = og.k.j(r5, r6)
            android.util.Log.e(r1, r5, r4)
        L_0x0092:
            java.lang.Object r4 = r0.g
            S1.n r4 = (S1.n) r4
            r4.sendEmptyMessage(r3)
        L_0x0099:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: E2.k.f(int, android.content.ComponentName, android.os.IBinder):void");
    }

    public String toString() {
        switch (this.f168a) {
            case 0:
                boolean z = this.b;
                StringBuilder sb2 = new StringBuilder(32);
                sb2.append((String) this.f169c);
                sb2.append('{');
                String str = "";
                for (l lVar = (l) ((l) this.d).g; lVar != null; lVar = (l) lVar.g) {
                    Object obj = lVar.f;
                    if ((lVar instanceof j) || obj != null || !z) {
                        sb2.append(str);
                        String str2 = (String) lVar.e;
                        if (str2 != null) {
                            sb2.append(str2);
                            sb2.append('=');
                        }
                        if (obj == null || !obj.getClass().isArray()) {
                            sb2.append(obj);
                        } else {
                            String deepToString = Arrays.deepToString(new Object[]{obj});
                            sb2.append(deepToString, 1, deepToString.length() - 1);
                        }
                        str = ArcCommonLog.TAG_COMMA;
                    }
                }
                sb2.append('}');
                return sb2.toString();
            default:
                return super.toString();
        }
    }

    public k(String str) {
        l lVar = new l(1);
        this.d = lVar;
        this.e = lVar;
        this.b = false;
        this.f169c = str;
    }
}
