package t1;

import B2.A;
import D1.b;
import Xd.a;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.appfunctions.schema.internal.dependencies.C0104n;
import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import rf.C1268s;
import w1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class i implements b, C1268s, C0104n {
    public static i d;
    public static ExecutorService e;
    public static i f;

    public static void d(a aVar) {
        e.submit(new A(1, (Object) aVar));
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, t1.i] */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, java.util.concurrent.ThreadFactory] */
    public static i e() {
        if (f == null) {
            ? obj = new Object();
            e = Executors.newSingleThreadExecutor(new Object());
            f = obj;
        }
        return f;
    }

    public static final m f(PackageInfo packageInfo, m... mVarArr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr != null) {
            if (signatureArr.length != 1) {
                Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
                return null;
            }
            n nVar = new n(packageInfo.signatures[0].toByteArray());
            for (int i2 = 0; i2 < mVarArr.length; i2++) {
                if (mVarArr[i2].equals(nVar)) {
                    return mVarArr[i2];
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004b A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean g(android.content.pm.PackageInfo r4) {
        /*
            r0 = 1
            r1 = 0
            if (r4 == 0) goto L_0x002c
            java.lang.String r2 = "com.android.vending"
            java.lang.String r3 = r4.packageName
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x001c
            java.lang.String r2 = r4.packageName
            java.lang.String r3 = "com.google.android.gms"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0019
            goto L_0x001c
        L_0x0019:
            r2 = r4
        L_0x001a:
            r3 = r0
            goto L_0x002e
        L_0x001c:
            android.content.pm.ApplicationInfo r2 = r4.applicationInfo
            if (r2 != 0) goto L_0x0022
        L_0x0020:
            r2 = r1
            goto L_0x0029
        L_0x0022:
            int r2 = r2.flags
            r2 = r2 & 129(0x81, float:1.81E-43)
            if (r2 == 0) goto L_0x0020
            r2 = r0
        L_0x0029:
            r3 = r2
            r2 = r4
            goto L_0x002e
        L_0x002c:
            r2 = 0
            goto L_0x001a
        L_0x002e:
            if (r4 == 0) goto L_0x004c
            android.content.pm.Signature[] r4 = r2.signatures
            if (r4 == 0) goto L_0x004c
            if (r3 == 0) goto L_0x003d
            t1.m[] r4 = t1.o.f1929a
            t1.m r4 = f(r2, r4)
            goto L_0x0049
        L_0x003d:
            t1.m[] r4 = t1.o.f1929a
            r4 = r4[r1]
            t1.m[] r4 = new t1.m[]{r4}
            t1.m r4 = f(r2, r4)
        L_0x0049:
            if (r4 == 0) goto L_0x004c
            return r0
        L_0x004c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: t1.i.g(android.content.pm.PackageInfo):boolean");
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:49:0x008b=Splitter:B:49:0x008b, B:33:0x005c=Splitter:B:33:0x005c, B:18:0x003f=Splitter:B:18:0x003f} */
    public int a(android.content.Context r11, java.lang.String r12, boolean r13) {
        /*
            r10 = this;
            java.lang.Class<D1.d> r10 = D1.d.class
            monitor-enter(r10)     // Catch:{ all -> 0x00d1 }
            java.lang.Boolean r0 = D1.d.f114c     // Catch:{ all -> 0x004b }
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 != 0) goto L_0x00c4
            android.content.Context r0 = r11.getApplicationContext()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a5 }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a5 }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader> r4 = com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader.class
            java.lang.String r4 = r4.getName()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a5 }
            java.lang.Class r0 = r0.loadClass(r4)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a5 }
            java.lang.String r4 = "sClassLoader"
            java.lang.reflect.Field r0 = r0.getDeclaredField(r4)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a5 }
            java.lang.Class r4 = r0.getDeclaringClass()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a5 }
            monitor-enter(r4)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a5 }
            java.lang.Object r5 = r0.get(r2)     // Catch:{ all -> 0x0037 }
            java.lang.ClassLoader r5 = (java.lang.ClassLoader) r5     // Catch:{ all -> 0x0037 }
            java.lang.ClassLoader r6 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0037 }
            if (r5 != r6) goto L_0x003a
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0037 }
            goto L_0x00a1
        L_0x0037:
            r0 = move-exception
            goto L_0x00a3
        L_0x003a:
            if (r5 == 0) goto L_0x0042
            D1.d.c(r5)     // Catch:{ a -> 0x003f }
        L_0x003f:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0037 }
            goto L_0x00a1
        L_0x0042:
            boolean r5 = D1.d.d(r11)     // Catch:{ all -> 0x0037 }
            if (r5 != 0) goto L_0x004f
            monitor-exit(r4)     // Catch:{ all -> 0x0037 }
            monitor-exit(r10)     // Catch:{ all -> 0x004b }
            return r3
        L_0x004b:
            r0 = move-exception
            r12 = r0
            goto L_0x0201
        L_0x004f:
            boolean r5 = D1.d.e     // Catch:{ all -> 0x0037 }
            if (r5 != 0) goto L_0x0098
            java.lang.Boolean r5 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0037 }
            boolean r6 = r5.equals(r2)     // Catch:{ all -> 0x0037 }
            if (r6 == 0) goto L_0x005c
            goto L_0x0098
        L_0x005c:
            int r6 = D1.d.b(r11, r12, r13, r1)     // Catch:{ a -> 0x008e }
            java.lang.String r7 = D1.d.d     // Catch:{ a -> 0x008e }
            if (r7 == 0) goto L_0x008b
            boolean r7 = r7.isEmpty()     // Catch:{ a -> 0x008e }
            if (r7 == 0) goto L_0x006b
            goto L_0x008b
        L_0x006b:
            java.lang.ClassLoader r7 = D1.f.N()     // Catch:{ a -> 0x008e }
            if (r7 == 0) goto L_0x0072
            goto L_0x0080
        L_0x0072:
            dalvik.system.DelegateLastClassLoader r7 = new dalvik.system.DelegateLastClassLoader     // Catch:{ a -> 0x008e }
            java.lang.String r8 = D1.d.d     // Catch:{ a -> 0x008e }
            w1.r.b(r8)     // Catch:{ a -> 0x008e }
            java.lang.ClassLoader r9 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ a -> 0x008e }
            r7.<init>(r8, r9)     // Catch:{ a -> 0x008e }
        L_0x0080:
            D1.d.c(r7)     // Catch:{ a -> 0x008e }
            r0.set(r2, r7)     // Catch:{ a -> 0x008e }
            D1.d.f114c = r5     // Catch:{ a -> 0x008e }
            monitor-exit(r4)     // Catch:{ all -> 0x0037 }
            monitor-exit(r10)     // Catch:{ all -> 0x004b }
            return r6
        L_0x008b:
            monitor-exit(r4)     // Catch:{ all -> 0x0037 }
            monitor-exit(r10)     // Catch:{ all -> 0x004b }
            return r6
        L_0x008e:
            java.lang.ClassLoader r5 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0037 }
            r0.set(r2, r5)     // Catch:{ all -> 0x0037 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0037 }
            goto L_0x00a1
        L_0x0098:
            java.lang.ClassLoader r5 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0037 }
            r0.set(r2, r5)     // Catch:{ all -> 0x0037 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0037 }
        L_0x00a1:
            monitor-exit(r4)     // Catch:{ all -> 0x0037 }
            goto L_0x00c2
        L_0x00a3:
            monitor-exit(r4)     // Catch:{ all -> 0x0037 }
            throw r0     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a5 }
        L_0x00a5:
            r0 = move-exception
            java.lang.String r4 = "DynamiteModule"
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x004b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x004b }
            r5.<init>()     // Catch:{ all -> 0x004b }
            java.lang.String r6 = "Failed to load module via V2: "
            r5.append(r6)     // Catch:{ all -> 0x004b }
            r5.append(r0)     // Catch:{ all -> 0x004b }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x004b }
            android.util.Log.w(r4, r0)     // Catch:{ all -> 0x004b }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x004b }
        L_0x00c2:
            D1.d.f114c = r0     // Catch:{ all -> 0x004b }
        L_0x00c4:
            monitor-exit(r10)     // Catch:{ all -> 0x004b }
            boolean r10 = r0.booleanValue()     // Catch:{ all -> 0x00d1 }
            if (r10 == 0) goto L_0x00f3
            int r3 = D1.d.b(r11, r12, r13, r3)     // Catch:{ a -> 0x00d5 }
            goto L_0x01fa
        L_0x00d1:
            r0 = move-exception
            r10 = r0
            goto L_0x0203
        L_0x00d5:
            r0 = move-exception
            r10 = r0
            java.lang.String r12 = "DynamiteModule"
            java.lang.String r10 = r10.getMessage()     // Catch:{ all -> 0x00d1 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d1 }
            r13.<init>()     // Catch:{ all -> 0x00d1 }
            java.lang.String r0 = "Failed to retrieve remote module version: "
            r13.append(r0)     // Catch:{ all -> 0x00d1 }
            r13.append(r10)     // Catch:{ all -> 0x00d1 }
            java.lang.String r10 = r13.toString()     // Catch:{ all -> 0x00d1 }
            android.util.Log.w(r12, r10)     // Catch:{ all -> 0x00d1 }
            goto L_0x01fa
        L_0x00f3:
            D1.j r4 = D1.d.e(r11)     // Catch:{ all -> 0x00d1 }
            if (r4 != 0) goto L_0x00fb
            goto L_0x01fa
        L_0x00fb:
            android.os.Parcel r10 = r4.c()     // Catch:{ RemoteException -> 0x0187 }
            r0 = 6
            android.os.Parcel r10 = r4.a(r10, r0)     // Catch:{ RemoteException -> 0x0187 }
            int r0 = r10.readInt()     // Catch:{ RemoteException -> 0x0187 }
            r10.recycle()     // Catch:{ RemoteException -> 0x0187 }
            r10 = 3
            if (r0 < r10) goto L_0x018a
            java.lang.ThreadLocal r10 = D1.d.f115h     // Catch:{ RemoteException -> 0x0187 }
            java.lang.Object r0 = r10.get()     // Catch:{ RemoteException -> 0x0187 }
            D1.h r0 = (D1.h) r0     // Catch:{ RemoteException -> 0x0187 }
            if (r0 == 0) goto L_0x0122
            android.database.Cursor r0 = r0.f122a     // Catch:{ RemoteException -> 0x0187 }
            if (r0 == 0) goto L_0x0122
            int r3 = r0.getInt(r3)     // Catch:{ RemoteException -> 0x0187 }
            goto L_0x01fa
        L_0x0122:
            C1.b r5 = new C1.b     // Catch:{ RemoteException -> 0x0187 }
            r5.<init>(r11)     // Catch:{ RemoteException -> 0x0187 }
            D1.g r0 = D1.d.f116i     // Catch:{ RemoteException -> 0x0187 }
            java.lang.Object r0 = r0.get()     // Catch:{ RemoteException -> 0x0187 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ RemoteException -> 0x0187 }
            long r8 = r0.longValue()     // Catch:{ RemoteException -> 0x0187 }
            r6 = r12
            r7 = r13
            C1.a r12 = r4.h(r5, r6, r7, r8)     // Catch:{ RemoteException -> 0x0187 }
            java.lang.Object r12 = C1.b.e(r12)     // Catch:{ RemoteException -> 0x0187 }
            android.database.Cursor r12 = (android.database.Cursor) r12     // Catch:{ RemoteException -> 0x0187 }
            if (r12 == 0) goto L_0x0170
            boolean r13 = r12.moveToFirst()     // Catch:{ RemoteException -> 0x016d, all -> 0x016a }
            if (r13 != 0) goto L_0x0148
            goto L_0x0170
        L_0x0148:
            int r13 = r12.getInt(r3)     // Catch:{ RemoteException -> 0x016d, all -> 0x016a }
            if (r13 <= 0) goto L_0x0161
            java.lang.Object r10 = r10.get()     // Catch:{ RemoteException -> 0x016d, all -> 0x016a }
            D1.h r10 = (D1.h) r10     // Catch:{ RemoteException -> 0x016d, all -> 0x016a }
            if (r10 == 0) goto L_0x015d
            android.database.Cursor r0 = r10.f122a     // Catch:{ RemoteException -> 0x016d, all -> 0x016a }
            if (r0 != 0) goto L_0x015d
            r10.f122a = r12     // Catch:{ RemoteException -> 0x016d, all -> 0x016a }
            goto L_0x015e
        L_0x015d:
            r1 = r3
        L_0x015e:
            if (r1 == 0) goto L_0x0161
            goto L_0x0162
        L_0x0161:
            r2 = r12
        L_0x0162:
            if (r2 == 0) goto L_0x0167
            r2.close()     // Catch:{ all -> 0x00d1 }
        L_0x0167:
            r3 = r13
            goto L_0x01fa
        L_0x016a:
            r0 = move-exception
            r10 = r0
            goto L_0x017e
        L_0x016d:
            r0 = move-exception
            r10 = r0
            goto L_0x0181
        L_0x0170:
            java.lang.String r10 = "DynamiteModule"
            java.lang.String r13 = "Failed to retrieve remote module version."
            android.util.Log.w(r10, r13)     // Catch:{ RemoteException -> 0x016d, all -> 0x016a }
            if (r12 == 0) goto L_0x01fa
            r12.close()     // Catch:{ all -> 0x00d1 }
            goto L_0x01fa
        L_0x017e:
            r2 = r12
            goto L_0x01fb
        L_0x0181:
            r2 = r12
            goto L_0x01db
        L_0x0183:
            r0 = move-exception
            r10 = r0
            goto L_0x01fb
        L_0x0187:
            r0 = move-exception
            r10 = r0
            goto L_0x01db
        L_0x018a:
            r6 = r12
            r7 = r13
            r12 = 2
            if (r0 != r12) goto L_0x01b6
            java.lang.String r10 = "DynamiteModule"
            java.lang.String r12 = "IDynamite loader version = 2, no high precision latency measurement."
            android.util.Log.w(r10, r12)     // Catch:{ RemoteException -> 0x0187 }
            C1.b r10 = new C1.b     // Catch:{ RemoteException -> 0x0187 }
            r10.<init>(r11)     // Catch:{ RemoteException -> 0x0187 }
            android.os.Parcel r12 = r4.c()     // Catch:{ RemoteException -> 0x0187 }
            F1.b.c(r12, r10)     // Catch:{ RemoteException -> 0x0187 }
            r12.writeString(r6)     // Catch:{ RemoteException -> 0x0187 }
            r12.writeInt(r7)     // Catch:{ RemoteException -> 0x0187 }
            r10 = 5
            android.os.Parcel r10 = r4.a(r12, r10)     // Catch:{ RemoteException -> 0x0187 }
            int r12 = r10.readInt()     // Catch:{ RemoteException -> 0x0187 }
            r10.recycle()     // Catch:{ RemoteException -> 0x0187 }
        L_0x01b4:
            r3 = r12
            goto L_0x01fa
        L_0x01b6:
            java.lang.String r12 = "DynamiteModule"
            java.lang.String r13 = "IDynamite loader version < 2, falling back to getModuleVersion2"
            android.util.Log.w(r12, r13)     // Catch:{ RemoteException -> 0x0187 }
            C1.b r12 = new C1.b     // Catch:{ RemoteException -> 0x0187 }
            r12.<init>(r11)     // Catch:{ RemoteException -> 0x0187 }
            android.os.Parcel r13 = r4.c()     // Catch:{ RemoteException -> 0x0187 }
            F1.b.c(r13, r12)     // Catch:{ RemoteException -> 0x0187 }
            r13.writeString(r6)     // Catch:{ RemoteException -> 0x0187 }
            r13.writeInt(r7)     // Catch:{ RemoteException -> 0x0187 }
            android.os.Parcel r10 = r4.a(r13, r10)     // Catch:{ RemoteException -> 0x0187 }
            int r12 = r10.readInt()     // Catch:{ RemoteException -> 0x0187 }
            r10.recycle()     // Catch:{ RemoteException -> 0x0187 }
            goto L_0x01b4
        L_0x01db:
            java.lang.String r12 = "DynamiteModule"
            java.lang.String r10 = r10.getMessage()     // Catch:{ all -> 0x0183 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0183 }
            r13.<init>()     // Catch:{ all -> 0x0183 }
            java.lang.String r0 = "Failed to retrieve remote module version: "
            r13.append(r0)     // Catch:{ all -> 0x0183 }
            r13.append(r10)     // Catch:{ all -> 0x0183 }
            java.lang.String r10 = r13.toString()     // Catch:{ all -> 0x0183 }
            android.util.Log.w(r12, r10)     // Catch:{ all -> 0x0183 }
            if (r2 == 0) goto L_0x01fa
            r2.close()     // Catch:{ all -> 0x00d1 }
        L_0x01fa:
            return r3
        L_0x01fb:
            if (r2 == 0) goto L_0x0200
            r2.close()     // Catch:{ all -> 0x00d1 }
        L_0x0200:
            throw r10     // Catch:{ all -> 0x00d1 }
        L_0x0201:
            monitor-exit(r10)     // Catch:{ all -> 0x004b }
            throw r12     // Catch:{ all -> 0x00d1 }
        L_0x0203:
            w1.r.b(r11)     // Catch:{ Exception -> 0x0207 }
            goto L_0x0210
        L_0x0207:
            r0 = move-exception
            r11 = r0
            java.lang.String r12 = "CrashUtils"
            java.lang.String r13 = "Error adding exception to DropBox!"
            android.util.Log.e(r12, r13, r11)
        L_0x0210:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: t1.i.a(android.content.Context, java.lang.String, boolean):int");
    }

    public int b(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            Class<?> loadClass = classLoader.loadClass("com.google.android.gms.dynamite.descriptors." + str + ".ModuleDescriptor");
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (r.d(declaredField.get((Object) null), str)) {
                return declaredField2.getInt((Object) null);
            }
            String valueOf = String.valueOf(declaredField.get((Object) null));
            Log.e("DynamiteModule", "Module descriptor id '" + valueOf + "' didn't match expected id '" + str + "'");
            return 0;
        } catch (ClassNotFoundException unused) {
            Log.w("DynamiteModule", "Local module descriptor class for " + str + " not found.");
            return 0;
        } catch (Exception e7) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e7.getMessage())));
            return 0;
        }
    }

    public float c(float f5, float f8) {
        return 1.0f;
    }
}
