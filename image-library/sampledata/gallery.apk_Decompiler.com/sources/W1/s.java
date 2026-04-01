package w1;

import E1.e;
import android.os.Looper;
import com.google.android.gms.common.internal.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s extends e {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ a f2016a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public s(a aVar, Looper looper) {
        super(looper, 1);
        this.f2016a = aVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: android.app.PendingIntent} */
    /* JADX WARNING: type inference failed for: r10v7, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleMessage(android.os.Message r10) {
        /*
            r9 = this;
            com.google.android.gms.common.internal.a r0 = r9.f2016a
            java.util.concurrent.atomic.AtomicInteger r0 = r0.v
            int r0 = r0.get()
            int r1 = r10.arg1
            r2 = 7
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L_0x0024
            int r9 = r10.what
            if (r9 == r3) goto L_0x0019
            if (r9 == r4) goto L_0x0019
            if (r9 != r2) goto L_0x0018
            goto L_0x0019
        L_0x0018:
            return
        L_0x0019:
            java.lang.Object r9 = r10.obj
            w1.o r9 = (w1.o) r9
            r9.getClass()
            r9.c()
            return
        L_0x0024:
            int r0 = r10.what
            r1 = 4
            r5 = 5
            if (r0 == r4) goto L_0x0031
            if (r0 == r2) goto L_0x0031
            if (r0 != r1) goto L_0x002f
            goto L_0x0031
        L_0x002f:
            if (r0 != r5) goto L_0x0039
        L_0x0031:
            com.google.android.gms.common.internal.a r0 = r9.f2016a
            boolean r0 = r0.b()
            if (r0 == 0) goto L_0x017a
        L_0x0039:
            int r0 = r10.what
            r6 = 8
            r7 = 3
            r8 = 0
            if (r0 != r1) goto L_0x008a
            com.google.android.gms.common.internal.a r0 = r9.f2016a
            t1.a r1 = new t1.a
            int r10 = r10.arg2
            r1.<init>(r10)
            r0.s = r1
            boolean r10 = r0.t
            if (r10 == 0) goto L_0x0051
            goto L_0x0075
        L_0x0051:
            java.lang.String r10 = r0.q()
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 == 0) goto L_0x005c
            goto L_0x0075
        L_0x005c:
            boolean r10 = android.text.TextUtils.isEmpty(r8)
            if (r10 == 0) goto L_0x0063
            goto L_0x0075
        L_0x0063:
            java.lang.String r10 = r0.q()     // Catch:{ ClassNotFoundException -> 0x0075 }
            java.lang.Class.forName(r10)     // Catch:{ ClassNotFoundException -> 0x0075 }
            com.google.android.gms.common.internal.a r10 = r9.f2016a
            boolean r0 = r10.t
            if (r0 == 0) goto L_0x0071
            goto L_0x0075
        L_0x0071:
            r10.v(r7, r8)
            return
        L_0x0075:
            com.google.android.gms.common.internal.a r9 = r9.f2016a
            t1.a r10 = r9.s
            if (r10 == 0) goto L_0x007c
            goto L_0x0081
        L_0x007c:
            t1.a r10 = new t1.a
            r10.<init>(r6)
        L_0x0081:
            w1.b r9 = r9.f1341i
            r9.a(r10)
            java.lang.System.currentTimeMillis()
            return
        L_0x008a:
            if (r0 != r5) goto L_0x00a1
            com.google.android.gms.common.internal.a r9 = r9.f2016a
            t1.a r10 = r9.s
            if (r10 == 0) goto L_0x0093
            goto L_0x0098
        L_0x0093:
            t1.a r10 = new t1.a
            r10.<init>(r6)
        L_0x0098:
            w1.b r9 = r9.f1341i
            r9.a(r10)
            java.lang.System.currentTimeMillis()
            return
        L_0x00a1:
            if (r0 != r7) goto L_0x00be
            java.lang.Object r0 = r10.obj
            boolean r1 = r0 instanceof android.app.PendingIntent
            if (r1 == 0) goto L_0x00ac
            r8 = r0
            android.app.PendingIntent r8 = (android.app.PendingIntent) r8
        L_0x00ac:
            t1.a r0 = new t1.a
            int r10 = r10.arg2
            r0.<init>(r10, r8)
            com.google.android.gms.common.internal.a r9 = r9.f2016a
            w1.b r9 = r9.f1341i
            r9.a(r0)
            java.lang.System.currentTimeMillis()
            return
        L_0x00be:
            r1 = 6
            if (r0 != r1) goto L_0x00e0
            com.google.android.gms.common.internal.a r0 = r9.f2016a
            r0.v(r5, r8)
            com.google.android.gms.common.internal.a r0 = r9.f2016a
            w1.g r0 = r0.n
            if (r0 == 0) goto L_0x00d5
            int r10 = r10.arg2
            java.lang.Object r0 = r0.f2007a
            u1.g r0 = (u1.g) r0
            r0.b(r10)
        L_0x00d5:
            com.google.android.gms.common.internal.a r10 = r9.f2016a
            r10.t()
            com.google.android.gms.common.internal.a r9 = r9.f2016a
            com.google.android.gms.common.internal.a.u(r9, r5, r4, r8)
            return
        L_0x00e0:
            if (r0 != r3) goto L_0x00f6
            com.google.android.gms.common.internal.a r9 = r9.f2016a
            boolean r9 = r9.g()
            if (r9 == 0) goto L_0x00eb
            goto L_0x00f6
        L_0x00eb:
            java.lang.Object r9 = r10.obj
            w1.o r9 = (w1.o) r9
            r9.getClass()
            r9.c()
            return
        L_0x00f6:
            int r9 = r10.what
            if (r9 == r3) goto L_0x0110
            if (r9 == r4) goto L_0x0110
            if (r9 != r2) goto L_0x00ff
            goto L_0x0110
        L_0x00ff:
            java.lang.String r10 = "Don't know how to handle message: "
            java.lang.String r9 = c0.C0086a.i(r9, r10)
            java.lang.Exception r10 = new java.lang.Exception
            r10.<init>()
            java.lang.String r0 = "GmsClient"
            android.util.Log.wtf(r0, r9, r10)
            return
        L_0x0110:
            java.lang.Object r9 = r10.obj
            w1.o r9 = (w1.o) r9
            java.lang.String r10 = "Callback proxy "
            monitor-enter(r9)
            java.lang.Boolean r0 = r9.f2011a     // Catch:{ all -> 0x0138 }
            boolean r1 = r9.b     // Catch:{ all -> 0x0138 }
            if (r1 == 0) goto L_0x013a
            java.lang.String r1 = "GmsClient"
            java.lang.String r2 = r9.toString()     // Catch:{ all -> 0x0138 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0138 }
            r3.<init>(r10)     // Catch:{ all -> 0x0138 }
            r3.append(r2)     // Catch:{ all -> 0x0138 }
            java.lang.String r10 = " being reused. This is not safe."
            r3.append(r10)     // Catch:{ all -> 0x0138 }
            java.lang.String r10 = r3.toString()     // Catch:{ all -> 0x0138 }
            android.util.Log.w(r1, r10)     // Catch:{ all -> 0x0138 }
            goto L_0x013a
        L_0x0138:
            r10 = move-exception
            goto L_0x0178
        L_0x013a:
            monitor-exit(r9)     // Catch:{ all -> 0x0138 }
            if (r0 == 0) goto L_0x016d
            com.google.android.gms.common.internal.a r10 = r9.f
            int r0 = r9.d
            if (r0 != 0) goto L_0x0155
            boolean r0 = r9.b()
            if (r0 != 0) goto L_0x016d
            r10.v(r4, r8)
            t1.a r10 = new t1.a
            r10.<init>(r6, r8)
            r9.a(r10)
            goto L_0x016d
        L_0x0155:
            r10.v(r4, r8)
            android.os.Bundle r10 = r9.e
            if (r10 == 0) goto L_0x0165
            java.lang.String r1 = "pendingIntent"
            android.os.Parcelable r10 = r10.getParcelable(r1)
            r8 = r10
            android.app.PendingIntent r8 = (android.app.PendingIntent) r8
        L_0x0165:
            t1.a r10 = new t1.a
            r10.<init>(r0, r8)
            r9.a(r10)
        L_0x016d:
            monitor-enter(r9)
            r9.b = r4     // Catch:{ all -> 0x0175 }
            monitor-exit(r9)     // Catch:{ all -> 0x0175 }
            r9.c()
            return
        L_0x0175:
            r10 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x0175 }
            throw r10
        L_0x0178:
            monitor-exit(r9)     // Catch:{ all -> 0x0138 }
            throw r10
        L_0x017a:
            java.lang.Object r9 = r10.obj
            w1.o r9 = (w1.o) r9
            r9.getClass()
            r9.c()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: w1.s.handleMessage(android.os.Message):void");
    }
}
