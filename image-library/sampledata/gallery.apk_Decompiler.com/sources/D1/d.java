package D1;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.util.Log;
import t1.f;
import t1.i;
import x2.C0338e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d {
    public static final C0338e b = new Object();

    /* renamed from: c  reason: collision with root package name */
    public static Boolean f114c = null;
    public static String d = null;
    public static boolean e = false;
    public static int f = -1;
    public static Boolean g;

    /* renamed from: h  reason: collision with root package name */
    public static final ThreadLocal f115h = new ThreadLocal();

    /* renamed from: i  reason: collision with root package name */
    public static final g f116i = new g(0);

    /* renamed from: j  reason: collision with root package name */
    public static final i f117j = new Object();
    public static j k;
    public static k l;

    /* renamed from: a  reason: collision with root package name */
    public final Context f118a;

    public d(Context context) {
        this.f118a = context;
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [D1.h, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02c5  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x02cb  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x02d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static D1.d a(android.content.Context r22, x2.C0338e r23, java.lang.String r24) {
        /*
            r1 = r22
            r2 = r23
            r3 = r24
            java.lang.String r0 = "No acceptable module "
            java.lang.String r4 = "VersionPolicy returned invalid code:"
            java.lang.String r5 = "Selected remote version of "
            java.lang.String r6 = "Selected remote version of "
            java.lang.String r7 = "Considering local module "
            android.content.Context r8 = r1.getApplicationContext()
            if (r8 == 0) goto L_0x02dd
            java.lang.ThreadLocal r9 = f115h
            java.lang.Object r10 = r9.get()
            D1.h r10 = (D1.h) r10
            D1.h r11 = new D1.h
            r11.<init>()
            r9.set(r11)
            D1.g r12 = f116i
            java.lang.Object r13 = r12.get()
            java.lang.Long r13 = (java.lang.Long) r13
            long r14 = r13.longValue()
            r16 = 0
            long r18 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x02be }
            r20 = r9
            java.lang.Long r9 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x02be }
            r12.set(r9)     // Catch:{ all -> 0x02be }
            t1.i r9 = f117j     // Catch:{ all -> 0x02be }
            D1.c r9 = r2.i(r1, r3, r9)     // Catch:{ all -> 0x02be }
            java.lang.String r12 = "DynamiteModule"
            r18 = r14
            int r14 = r9.f112a     // Catch:{ all -> 0x0085 }
            int r15 = r9.b     // Catch:{ all -> 0x0085 }
            r21 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0085 }
            r0.<init>(r7)     // Catch:{ all -> 0x0085 }
            r0.append(r3)     // Catch:{ all -> 0x0085 }
            java.lang.String r7 = ":"
            r0.append(r7)     // Catch:{ all -> 0x0085 }
            r0.append(r14)     // Catch:{ all -> 0x0085 }
            java.lang.String r7 = " and remote module "
            r0.append(r7)     // Catch:{ all -> 0x0085 }
            r0.append(r3)     // Catch:{ all -> 0x0085 }
            java.lang.String r7 = ":"
            r0.append(r7)     // Catch:{ all -> 0x0085 }
            r0.append(r15)     // Catch:{ all -> 0x0085 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0085 }
            android.util.Log.i(r12, r0)     // Catch:{ all -> 0x0085 }
            int r0 = r9.f113c     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x0291
            r7 = -1
            if (r0 != r7) goto L_0x0088
            int r0 = r9.f112a     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x0291
            r0 = r7
            goto L_0x0088
        L_0x0085:
            r0 = move-exception
            goto L_0x02c1
        L_0x0088:
            r12 = 1
            if (r0 != r12) goto L_0x008f
            int r14 = r9.b     // Catch:{ all -> 0x0085 }
            if (r14 == 0) goto L_0x0291
        L_0x008f:
            if (r0 != r7) goto L_0x00a3
            java.lang.String r0 = "Selected local version of "
            java.lang.String r1 = "DynamiteModule"
            java.lang.String r0 = r0.concat(r3)     // Catch:{ all -> 0x0085 }
            android.util.Log.i(r1, r0)     // Catch:{ all -> 0x0085 }
            D1.d r0 = new D1.d     // Catch:{ all -> 0x0085 }
            r0.<init>(r8)     // Catch:{ all -> 0x0085 }
            goto L_0x025b
        L_0x00a3:
            if (r0 != r12) goto L_0x027f
            int r0 = r9.b     // Catch:{ a -> 0x0215 }
            java.lang.Class<D1.d> r4 = D1.d.class
            monitor-enter(r4)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            boolean r14 = d(r1)     // Catch:{ all -> 0x0201 }
            if (r14 == 0) goto L_0x0203
            java.lang.Boolean r14 = f114c     // Catch:{ all -> 0x0201 }
            monitor-exit(r4)     // Catch:{ all -> 0x0201 }
            if (r14 == 0) goto L_0x01f9
            boolean r4 = r14.booleanValue()     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r14 = 2
            if (r4 == 0) goto L_0x0164
            java.lang.String r4 = "DynamiteModule"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r5.<init>(r6)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r5.append(r3)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.String r6 = ", version >= "
            r5.append(r6)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r5.append(r0)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.String r5 = r5.toString()     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            android.util.Log.i(r4, r5)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.Class<D1.d> r4 = D1.d.class
            monitor-enter(r4)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            D1.k r5 = l     // Catch:{ all -> 0x0161 }
            monitor-exit(r4)     // Catch:{ all -> 0x0161 }
            if (r5 == 0) goto L_0x0159
            java.lang.Object r4 = r20.get()     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            D1.h r4 = (D1.h) r4     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            if (r4 == 0) goto L_0x0151
            android.database.Cursor r6 = r4.f122a     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            if (r6 == 0) goto L_0x0151
            android.content.Context r6 = r1.getApplicationContext()     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            android.database.Cursor r4 = r4.f122a     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.b r15 = new C1.b     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r12 = 0
            r15.<init>(r12)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.Class<D1.d> r12 = D1.d.class
            monitor-enter(r12)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            int r15 = f     // Catch:{ all -> 0x014e }
            if (r15 < r14) goto L_0x00fe
            r14 = 1
            goto L_0x00ff
        L_0x00fe:
            r14 = 0
        L_0x00ff:
            monitor-exit(r12)     // Catch:{ all -> 0x014e }
            if (r14 == 0) goto L_0x0121
            java.lang.String r12 = "DynamiteModule"
            java.lang.String r14 = "Dynamite loader version >= 2, using loadModule2NoCrashUtils"
            android.util.Log.v(r12, r14)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.b r12 = new C1.b     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r12.<init>(r6)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.b r6 = new C1.b     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r6.<init>(r4)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.a r0 = r5.f(r12, r3, r0, r6)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            goto L_0x0136
        L_0x0118:
            r0 = move-exception
            goto L_0x020d
        L_0x011b:
            r0 = move-exception
            goto L_0x0217
        L_0x011e:
            r0 = move-exception
            goto L_0x0218
        L_0x0121:
            java.lang.String r12 = "DynamiteModule"
            java.lang.String r14 = "Dynamite loader version < 2, falling back to loadModule2"
            android.util.Log.w(r12, r14)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.b r12 = new C1.b     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r12.<init>(r6)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.b r6 = new C1.b     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r6.<init>(r4)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.a r0 = r5.e(r12, r3, r0, r6)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x0136:
            java.lang.Object r0 = C1.b.e(r0)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            if (r0 == 0) goto L_0x0146
            D1.d r4 = new D1.d     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r4.<init>(r0)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x0143:
            r0 = r4
            goto L_0x025b
        L_0x0146:
            D1.a r0 = new D1.a     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.String r4 = "Failed to get module context"
            r0.<init>(r4)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            throw r0     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x014e:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x014e }
            throw r0     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x0151:
            D1.a r0 = new D1.a     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.String r4 = "No result cursor"
            r0.<init>(r4)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            throw r0     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x0159:
            D1.a r0 = new D1.a     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.String r4 = "DynamiteLoaderV2 was not cached."
            r0.<init>(r4)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            throw r0     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x0161:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0161 }
            throw r0     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x0164:
            java.lang.String r4 = "DynamiteModule"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r6.<init>(r5)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r6.append(r3)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.String r5 = ", version >= "
            r6.append(r5)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r6.append(r0)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.String r5 = r6.toString()     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            android.util.Log.i(r4, r5)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            D1.j r4 = e(r1)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            if (r4 == 0) goto L_0x01f1
            android.os.Parcel r5 = r4.c()     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r6 = 6
            android.os.Parcel r5 = r4.a(r5, r6)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            int r6 = r5.readInt()     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r5.recycle()     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r5 = 3
            if (r6 < r5) goto L_0x01b7
            java.lang.Object r5 = r20.get()     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            D1.h r5 = (D1.h) r5     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            if (r5 == 0) goto L_0x01af
            C1.b r6 = new C1.b     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r6.<init>(r1)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            android.database.Cursor r5 = r5.f122a     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.b r12 = new C1.b     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r12.<init>(r5)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.a r0 = r4.f(r6, r3, r0, r12)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            goto L_0x01da
        L_0x01af:
            D1.a r0 = new D1.a     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.String r4 = "No cached result cursor holder"
            r0.<init>(r4)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            throw r0     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x01b7:
            if (r6 != r14) goto L_0x01ca
            java.lang.String r5 = "DynamiteModule"
            java.lang.String r6 = "IDynamite loader version = 2"
            android.util.Log.w(r5, r6)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.b r5 = new C1.b     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r5.<init>(r1)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.a r0 = r4.g(r5, r3, r0)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            goto L_0x01da
        L_0x01ca:
            java.lang.String r5 = "DynamiteModule"
            java.lang.String r6 = "Dynamite loader version < 2, falling back to createModuleContext"
            android.util.Log.w(r5, r6)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.b r5 = new C1.b     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r5.<init>(r1)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            C1.a r0 = r4.e(r5, r3, r0)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x01da:
            java.lang.Object r0 = C1.b.e(r0)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            if (r0 == 0) goto L_0x01e9
            D1.d r4 = new D1.d     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            r4.<init>(r0)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            goto L_0x0143
        L_0x01e9:
            D1.a r0 = new D1.a     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.String r4 = "Failed to load remote module."
            r0.<init>(r4)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            throw r0     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x01f1:
            D1.a r0 = new D1.a     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.String r4 = "Failed to create IDynamiteLoader."
            r0.<init>(r4)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            throw r0     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x01f9:
            D1.a r0 = new D1.a     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            java.lang.String r4 = "Failed to determine which loading route to use."
            r0.<init>(r4)     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
            throw r0     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x0201:
            r0 = move-exception
            goto L_0x020b
        L_0x0203:
            D1.a r0 = new D1.a     // Catch:{ all -> 0x0201 }
            java.lang.String r5 = "Remote loading disabled"
            r0.<init>(r5)     // Catch:{ all -> 0x0201 }
            throw r0     // Catch:{ all -> 0x0201 }
        L_0x020b:
            monitor-exit(r4)     // Catch:{ all -> 0x0201 }
            throw r0     // Catch:{ RemoteException -> 0x011e, a -> 0x011b, all -> 0x0118 }
        L_0x020d:
            D1.a r4 = new D1.a     // Catch:{ a -> 0x0215 }
            java.lang.String r5 = "Failed to load remote module."
            r4.<init>(r5, r0)     // Catch:{ a -> 0x0215 }
            throw r4     // Catch:{ a -> 0x0215 }
        L_0x0215:
            r0 = move-exception
            goto L_0x0220
        L_0x0217:
            throw r0     // Catch:{ a -> 0x0215 }
        L_0x0218:
            D1.a r4 = new D1.a     // Catch:{ a -> 0x0215 }
            java.lang.String r5 = "Failed to load remote module."
            r4.<init>(r5, r0)     // Catch:{ a -> 0x0215 }
            throw r4     // Catch:{ a -> 0x0215 }
        L_0x0220:
            java.lang.String r4 = "DynamiteModule"
            java.lang.String r5 = r0.getMessage()     // Catch:{ all -> 0x0085 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0085 }
            r6.<init>()     // Catch:{ all -> 0x0085 }
            java.lang.String r12 = "Failed to load remote module: "
            r6.append(r12)     // Catch:{ all -> 0x0085 }
            r6.append(r5)     // Catch:{ all -> 0x0085 }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x0085 }
            android.util.Log.w(r4, r5)     // Catch:{ all -> 0x0085 }
            int r4 = r9.f112a     // Catch:{ all -> 0x0085 }
            if (r4 == 0) goto L_0x0277
            D1.i r5 = new D1.i     // Catch:{ all -> 0x0085 }
            r5.<init>(r4)     // Catch:{ all -> 0x0085 }
            D1.c r1 = r2.i(r1, r3, r5)     // Catch:{ all -> 0x0085 }
            int r1 = r1.f113c     // Catch:{ all -> 0x0085 }
            if (r1 != r7) goto L_0x0277
            java.lang.String r0 = "Selected local version of "
            java.lang.String r1 = "DynamiteModule"
            java.lang.String r0 = r0.concat(r3)     // Catch:{ all -> 0x0085 }
            android.util.Log.i(r1, r0)     // Catch:{ all -> 0x0085 }
            D1.d r0 = new D1.d     // Catch:{ all -> 0x0085 }
            r0.<init>(r8)     // Catch:{ all -> 0x0085 }
        L_0x025b:
            int r1 = (r18 > r16 ? 1 : (r18 == r16 ? 0 : -1))
            if (r1 != 0) goto L_0x0265
            D1.g r1 = f116i
            r1.remove()
            goto L_0x026a
        L_0x0265:
            D1.g r1 = f116i
            r1.set(r13)
        L_0x026a:
            android.database.Cursor r1 = r11.f122a
            if (r1 == 0) goto L_0x0271
            r1.close()
        L_0x0271:
            java.lang.ThreadLocal r1 = f115h
            r1.set(r10)
            return r0
        L_0x0277:
            D1.a r1 = new D1.a     // Catch:{ all -> 0x0085 }
            java.lang.String r2 = "Remote load failed. No local fallback found."
            r1.<init>(r2, r0)     // Catch:{ all -> 0x0085 }
            throw r1     // Catch:{ all -> 0x0085 }
        L_0x027f:
            D1.a r1 = new D1.a     // Catch:{ all -> 0x0085 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0085 }
            r2.<init>(r4)     // Catch:{ all -> 0x0085 }
            r2.append(r0)     // Catch:{ all -> 0x0085 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0085 }
            r1.<init>(r0)     // Catch:{ all -> 0x0085 }
            throw r1     // Catch:{ all -> 0x0085 }
        L_0x0291:
            D1.a r0 = new D1.a     // Catch:{ all -> 0x0085 }
            int r1 = r9.f112a     // Catch:{ all -> 0x0085 }
            int r2 = r9.b     // Catch:{ all -> 0x0085 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0085 }
            r5 = r21
            r4.<init>(r5)     // Catch:{ all -> 0x0085 }
            r4.append(r3)     // Catch:{ all -> 0x0085 }
            java.lang.String r3 = " found. Local version is "
            r4.append(r3)     // Catch:{ all -> 0x0085 }
            r4.append(r1)     // Catch:{ all -> 0x0085 }
            java.lang.String r1 = " and remote version is "
            r4.append(r1)     // Catch:{ all -> 0x0085 }
            r4.append(r2)     // Catch:{ all -> 0x0085 }
            java.lang.String r1 = "."
            r4.append(r1)     // Catch:{ all -> 0x0085 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x0085 }
            r0.<init>(r1)     // Catch:{ all -> 0x0085 }
            throw r0     // Catch:{ all -> 0x0085 }
        L_0x02be:
            r0 = move-exception
            r18 = r14
        L_0x02c1:
            int r1 = (r18 > r16 ? 1 : (r18 == r16 ? 0 : -1))
            if (r1 != 0) goto L_0x02cb
            D1.g r1 = f116i
            r1.remove()
            goto L_0x02d0
        L_0x02cb:
            D1.g r1 = f116i
            r1.set(r13)
        L_0x02d0:
            android.database.Cursor r1 = r11.f122a
            if (r1 == 0) goto L_0x02d7
            r1.close()
        L_0x02d7:
            java.lang.ThreadLocal r1 = f115h
            r1.set(r10)
            throw r0
        L_0x02dd:
            D1.a r0 = new D1.a
            java.lang.String r1 = "null application Context"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: D1.d.a(android.content.Context, x2.e, java.lang.String):D1.d");
    }

    /* JADX WARNING: Removed duplicated region for block: B:74:0x00f9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int b(android.content.Context r9, java.lang.String r10, boolean r11, boolean r12) {
        /*
            r1 = 0
            android.content.ContentResolver r2 = r9.getContentResolver()     // Catch:{ Exception -> 0x00ba }
            D1.g r9 = f116i     // Catch:{ Exception -> 0x00ba }
            java.lang.Object r9 = r9.get()     // Catch:{ Exception -> 0x00ba }
            java.lang.Long r9 = (java.lang.Long) r9     // Catch:{ Exception -> 0x00ba }
            long r3 = r9.longValue()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r9 = "api_force_staging"
            java.lang.String r0 = "api"
            r8 = 1
            if (r8 == r11) goto L_0x0019
            r9 = r0
        L_0x0019:
            android.net.Uri$Builder r11 = new android.net.Uri$Builder     // Catch:{ Exception -> 0x00ba }
            r11.<init>()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r0 = "content"
            android.net.Uri$Builder r11 = r11.scheme(r0)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r0 = "com.google.android.gms.chimera"
            android.net.Uri$Builder r11 = r11.authority(r0)     // Catch:{ Exception -> 0x00ba }
            android.net.Uri$Builder r9 = r11.path(r9)     // Catch:{ Exception -> 0x00ba }
            android.net.Uri$Builder r9 = r9.appendPath(r10)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r10 = "requestStartTime"
            java.lang.String r11 = java.lang.String.valueOf(r3)     // Catch:{ Exception -> 0x00ba }
            android.net.Uri$Builder r9 = r9.appendQueryParameter(r10, r11)     // Catch:{ Exception -> 0x00ba }
            android.net.Uri r3 = r9.build()     // Catch:{ Exception -> 0x00ba }
            r6 = 0
            r7 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x00ba }
            if (r9 == 0) goto L_0x00c3
            boolean r10 = r9.moveToFirst()     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            if (r10 == 0) goto L_0x00c3
            r10 = 0
            int r11 = r9.getInt(r10)     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            if (r11 <= 0) goto L_0x00a9
            java.lang.Class<D1.d> r2 = D1.d.class
            monitor-enter(r2)     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            r0 = 2
            java.lang.String r0 = r9.getString(r0)     // Catch:{ all -> 0x0070 }
            d = r0     // Catch:{ all -> 0x0070 }
            java.lang.String r0 = "loaderVersion"
            int r0 = r9.getColumnIndex(r0)     // Catch:{ all -> 0x0070 }
            if (r0 < 0) goto L_0x0073
            int r0 = r9.getInt(r0)     // Catch:{ all -> 0x0070 }
            f = r0     // Catch:{ all -> 0x0070 }
            goto L_0x0073
        L_0x0070:
            r0 = move-exception
            r10 = r0
            goto L_0x00a1
        L_0x0073:
            java.lang.String r0 = "disableStandaloneDynamiteLoader2"
            int r0 = r9.getColumnIndex(r0)     // Catch:{ all -> 0x0070 }
            if (r0 < 0) goto L_0x0087
            int r0 = r9.getInt(r0)     // Catch:{ all -> 0x0070 }
            if (r0 == 0) goto L_0x0083
            r0 = r8
            goto L_0x0084
        L_0x0083:
            r0 = r10
        L_0x0084:
            e = r0     // Catch:{ all -> 0x0070 }
            goto L_0x0088
        L_0x0087:
            r0 = r10
        L_0x0088:
            monitor-exit(r2)     // Catch:{ all -> 0x0070 }
            java.lang.ThreadLocal r2 = f115h     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            java.lang.Object r2 = r2.get()     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            D1.h r2 = (D1.h) r2     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            if (r2 == 0) goto L_0x009a
            android.database.Cursor r3 = r2.f122a     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            if (r3 != 0) goto L_0x009a
            r2.f122a = r9     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            goto L_0x009b
        L_0x009a:
            r8 = r10
        L_0x009b:
            if (r8 == 0) goto L_0x009f
        L_0x009d:
            r10 = r0
            goto L_0x00aa
        L_0x009f:
            r1 = r9
            goto L_0x009d
        L_0x00a1:
            monitor-exit(r2)     // Catch:{ all -> 0x0070 }
            throw r10     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
        L_0x00a3:
            r0 = move-exception
            r10 = r0
            goto L_0x00d2
        L_0x00a6:
            r0 = move-exception
            r10 = r0
            goto L_0x00d5
        L_0x00a9:
            r1 = r9
        L_0x00aa:
            if (r12 == 0) goto L_0x00bd
            if (r10 != 0) goto L_0x00af
            goto L_0x00bd
        L_0x00af:
            D1.a r9 = new D1.a     // Catch:{ Exception -> 0x00ba }
            java.lang.String r10 = "forcing fallback to container DynamiteLoader impl"
            r9.<init>(r10)     // Catch:{ Exception -> 0x00ba }
            throw r9     // Catch:{ Exception -> 0x00ba }
        L_0x00b7:
            r0 = move-exception
            r9 = r0
            goto L_0x00f7
        L_0x00ba:
            r0 = move-exception
            r9 = r0
            goto L_0x00d7
        L_0x00bd:
            if (r1 == 0) goto L_0x00c2
            r1.close()
        L_0x00c2:
            return r11
        L_0x00c3:
            java.lang.String r10 = "DynamiteModule"
            java.lang.String r11 = "Failed to retrieve remote module version."
            android.util.Log.w(r10, r11)     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            D1.a r10 = new D1.a     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            java.lang.String r11 = "Failed to connect to dynamite module ContentResolver."
            r10.<init>(r11)     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
            throw r10     // Catch:{ Exception -> 0x00a6, all -> 0x00a3 }
        L_0x00d2:
            r1 = r9
            r9 = r10
            goto L_0x00f7
        L_0x00d5:
            r1 = r9
            r9 = r10
        L_0x00d7:
            boolean r10 = r9 instanceof D1.a     // Catch:{ all -> 0x00b7 }
            if (r10 == 0) goto L_0x00dc
            throw r9     // Catch:{ all -> 0x00b7 }
        L_0x00dc:
            D1.a r10 = new D1.a     // Catch:{ all -> 0x00b7 }
            java.lang.String r11 = r9.getMessage()     // Catch:{ all -> 0x00b7 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b7 }
            r12.<init>()     // Catch:{ all -> 0x00b7 }
            java.lang.String r0 = "V2 version check failed: "
            r12.append(r0)     // Catch:{ all -> 0x00b7 }
            r12.append(r11)     // Catch:{ all -> 0x00b7 }
            java.lang.String r11 = r12.toString()     // Catch:{ all -> 0x00b7 }
            r10.<init>(r11, r9)     // Catch:{ all -> 0x00b7 }
            throw r10     // Catch:{ all -> 0x00b7 }
        L_0x00f7:
            if (r1 == 0) goto L_0x00fc
            r1.close()
        L_0x00fc:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: D1.d.b(android.content.Context, java.lang.String, boolean, boolean):int");
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [E1.a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(java.lang.ClassLoader r3) {
        /*
            java.lang.String r0 = "com.google.android.gms.dynamite.IDynamiteLoaderV2"
            java.lang.String r1 = "com.google.android.gms.dynamiteloader.DynamiteLoaderV2"
            java.lang.Class r3 = r3.loadClass(r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x002a }
            r1 = 0
            java.lang.reflect.Constructor r3 = r3.getConstructor(r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x002a }
            java.lang.Object r3 = r3.newInstance(r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x002a }
            android.os.IBinder r3 = (android.os.IBinder) r3     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x002a }
            if (r3 != 0) goto L_0x0016
            goto L_0x0027
        L_0x0016:
            android.os.IInterface r1 = r3.queryLocalInterface(r0)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x002a }
            boolean r2 = r1 instanceof D1.k     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x002a }
            if (r2 == 0) goto L_0x0021
            D1.k r1 = (D1.k) r1     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x002a }
            goto L_0x0027
        L_0x0021:
            D1.k r1 = new D1.k     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x002a }
            r2 = 1
            r1.<init>(r3, r0, r2)     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x002a }
        L_0x0027:
            l = r1     // Catch:{ ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException -> 0x002a }
            return
        L_0x002a:
            r3 = move-exception
            D1.a r0 = new D1.a
            java.lang.String r1 = "Failed to instantiate dynamite loader"
            r0.<init>(r1, r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: D1.d.c(java.lang.ClassLoader):void");
    }

    public static boolean d(Context context) {
        ApplicationInfo applicationInfo;
        Boolean bool = Boolean.TRUE;
        if (bool.equals((Object) null) || bool.equals(g)) {
            return true;
        }
        boolean z = false;
        if (g == null) {
            ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", 0);
            if (f.b.b(context, 10000000) == 0 && resolveContentProvider != null && "com.google.android.gms".equals(resolveContentProvider.packageName)) {
                z = true;
            }
            g = Boolean.valueOf(z);
            if (z && (applicationInfo = resolveContentProvider.applicationInfo) != null && (applicationInfo.flags & 129) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                e = true;
            }
        }
        if (!z) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r3v6, types: [E1.a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static D1.j e(android.content.Context r6) {
        /*
            java.lang.String r0 = "Failed to load IDynamiteLoader from GmsCore: "
            java.lang.Class<D1.d> r1 = D1.d.class
            monitor-enter(r1)
            D1.j r2 = k     // Catch:{ all -> 0x000b }
            if (r2 == 0) goto L_0x000d
            monitor-exit(r1)     // Catch:{ all -> 0x000b }
            return r2
        L_0x000b:
            r6 = move-exception
            goto L_0x005d
        L_0x000d:
            r2 = 0
            java.lang.String r3 = "com.google.android.gms"
            r4 = 3
            android.content.Context r6 = r6.createPackageContext(r3, r4)     // Catch:{ Exception -> 0x0036 }
            java.lang.ClassLoader r6 = r6.getClassLoader()     // Catch:{ Exception -> 0x0036 }
            java.lang.String r3 = "com.google.android.gms.chimera.container.DynamiteLoaderImpl"
            java.lang.Class r6 = r6.loadClass(r3)     // Catch:{ Exception -> 0x0036 }
            java.lang.Object r6 = r6.newInstance()     // Catch:{ Exception -> 0x0036 }
            android.os.IBinder r6 = (android.os.IBinder) r6     // Catch:{ Exception -> 0x0036 }
            if (r6 != 0) goto L_0x0029
            r3 = r2
            goto L_0x0040
        L_0x0029:
            java.lang.String r3 = "com.google.android.gms.dynamite.IDynamiteLoader"
            android.os.IInterface r3 = r6.queryLocalInterface(r3)     // Catch:{ Exception -> 0x0036 }
            boolean r4 = r3 instanceof D1.j     // Catch:{ Exception -> 0x0036 }
            if (r4 == 0) goto L_0x0038
            D1.j r3 = (D1.j) r3     // Catch:{ Exception -> 0x0036 }
            goto L_0x0040
        L_0x0036:
            r6 = move-exception
            goto L_0x0046
        L_0x0038:
            D1.j r3 = new D1.j     // Catch:{ Exception -> 0x0036 }
            java.lang.String r4 = "com.google.android.gms.dynamite.IDynamiteLoader"
            r5 = 1
            r3.<init>(r6, r4, r5)     // Catch:{ Exception -> 0x0036 }
        L_0x0040:
            if (r3 == 0) goto L_0x005b
            k = r3     // Catch:{ Exception -> 0x0036 }
            monitor-exit(r1)     // Catch:{ all -> 0x000b }
            return r3
        L_0x0046:
            java.lang.String r3 = "DynamiteModule"
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x000b }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x000b }
            r4.<init>(r0)     // Catch:{ all -> 0x000b }
            r4.append(r6)     // Catch:{ all -> 0x000b }
            java.lang.String r6 = r4.toString()     // Catch:{ all -> 0x000b }
            android.util.Log.e(r3, r6)     // Catch:{ all -> 0x000b }
        L_0x005b:
            monitor-exit(r1)     // Catch:{ all -> 0x000b }
            return r2
        L_0x005d:
            monitor-exit(r1)     // Catch:{ all -> 0x000b }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: D1.d.e(android.content.Context):D1.j");
    }
}
