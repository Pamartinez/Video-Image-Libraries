package t1;

import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class h {

    /* renamed from: a  reason: collision with root package name */
    public static final AtomicBoolean f1924a = new AtomicBoolean();
    public static final AtomicBoolean b = new AtomicBoolean();

    /* renamed from: c  reason: collision with root package name */
    public static final /* synthetic */ int f1925c = 0;

    /* JADX WARNING: type inference failed for: r9v3, types: [java.lang.Object, t1.i] */
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
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public static int a(android.content.Context r12, int r13) {
        /*
            android.content.res.Resources r0 = r12.getResources()     // Catch:{ all -> 0x000b }
            r1 = 2131886645(0x7f120235, float:1.9407875E38)
            r0.getString(r1)     // Catch:{ all -> 0x000b }
            goto L_0x0012
        L_0x000b:
            java.lang.String r0 = "GooglePlayServicesUtil"
            java.lang.String r1 = "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included."
            android.util.Log.e(r0, r1)
        L_0x0012:
            java.lang.String r0 = r12.getPackageName()
            java.lang.String r1 = "com.google.android.gms"
            boolean r0 = r1.equals(r0)
            r1 = 1
            if (r0 != 0) goto L_0x008c
            java.util.concurrent.atomic.AtomicBoolean r0 = b
            boolean r0 = r0.get()
            if (r0 == 0) goto L_0x0028
            goto L_0x008c
        L_0x0028:
            java.lang.Object r0 = w1.r.f2014a
            monitor-enter(r0)
            boolean r2 = w1.r.b     // Catch:{ all -> 0x0031 }
            if (r2 == 0) goto L_0x0033
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            goto L_0x0068
        L_0x0031:
            r12 = move-exception
            goto L_0x008a
        L_0x0033:
            w1.r.b = r1     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = r12.getPackageName()     // Catch:{ all -> 0x0031 }
            B1.b r3 = B1.c.a(r12)     // Catch:{ all -> 0x0031 }
            java.lang.Object r3 = r3.e     // Catch:{ NameNotFoundException -> 0x005f }
            android.content.Context r3 = (android.content.Context) r3     // Catch:{ NameNotFoundException -> 0x005f }
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch:{ NameNotFoundException -> 0x005f }
            r4 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r2 = r3.getApplicationInfo(r2, r4)     // Catch:{ NameNotFoundException -> 0x005f }
            android.os.Bundle r2 = r2.metaData     // Catch:{ NameNotFoundException -> 0x005f }
            if (r2 != 0) goto L_0x0051
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            goto L_0x0068
        L_0x0051:
            java.lang.String r3 = "com.google.app.id"
            r2.getString(r3)     // Catch:{ NameNotFoundException -> 0x005f }
            java.lang.String r3 = "com.google.android.gms.version"
            int r2 = r2.getInt(r3)     // Catch:{ NameNotFoundException -> 0x005f }
            w1.r.f2015c = r2     // Catch:{ NameNotFoundException -> 0x005f }
            goto L_0x0067
        L_0x005f:
            r2 = move-exception
            java.lang.String r3 = "MetadataValueReader"
            java.lang.String r4 = "This should never happen."
            android.util.Log.wtf(r3, r4, r2)     // Catch:{ all -> 0x0031 }
        L_0x0067:
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
        L_0x0068:
            int r0 = w1.r.f2015c
            if (r0 == 0) goto L_0x0084
            r2 = 12451000(0xbdfcb8, float:1.7447567E-38)
            if (r0 != r2) goto L_0x0072
            goto L_0x008c
        L_0x0072:
            com.google.android.gms.common.GooglePlayServicesIncorrectManifestValueException r12 = new com.google.android.gms.common.GooglePlayServicesIncorrectManifestValueException
            int r13 = t1.f.f1923a
            java.lang.String r1 = "The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected "
            java.lang.String r2 = " but found "
            java.lang.String r3 = ".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />"
            java.lang.String r13 = A.a.d(r13, r0, r1, r2, r3)
            r12.<init>(r13)
            throw r12
        L_0x0084:
            com.google.android.gms.common.GooglePlayServicesMissingManifestValueException r12 = new com.google.android.gms.common.GooglePlayServicesMissingManifestValueException
            r12.<init>()
            throw r12
        L_0x008a:
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            throw r12
        L_0x008c:
            boolean r0 = B1.a.H(r12)
            r2 = 0
            if (r0 != 0) goto L_0x00c2
            java.lang.Boolean r0 = B1.a.f37h
            if (r0 != 0) goto L_0x00b8
            android.content.pm.PackageManager r0 = r12.getPackageManager()
            java.lang.String r3 = "android.hardware.type.iot"
            boolean r0 = r0.hasSystemFeature(r3)
            if (r0 != 0) goto L_0x00af
            android.content.pm.PackageManager r0 = r12.getPackageManager()
            java.lang.String r3 = "android.hardware.type.embedded"
            boolean r0 = r0.hasSystemFeature(r3)
            if (r0 == 0) goto L_0x00b1
        L_0x00af:
            r0 = r1
            goto L_0x00b2
        L_0x00b1:
            r0 = r2
        L_0x00b2:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            B1.a.f37h = r0
        L_0x00b8:
            java.lang.Boolean r0 = B1.a.f37h
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x00c2
            r0 = r1
            goto L_0x00c3
        L_0x00c2:
            r0 = r2
        L_0x00c3:
            if (r13 < 0) goto L_0x01d9
            java.lang.String r3 = r12.getPackageName()
            android.content.pm.PackageManager r4 = r12.getPackageManager()
            r5 = 9
            if (r0 == 0) goto L_0x00ec
            java.lang.String r6 = "com.android.vending"
            r7 = 8256(0x2040, float:1.1569E-41)
            android.content.pm.PackageInfo r6 = r4.getPackageInfo(r6, r7)     // Catch:{ NameNotFoundException -> 0x00da }
            goto L_0x00ed
        L_0x00da:
            java.lang.String r12 = java.lang.String.valueOf(r3)
            java.lang.String r13 = " requires the Google Play Store, but it is missing."
            java.lang.String r0 = "GooglePlayServicesUtil"
            java.lang.String r12 = r12.concat(r13)
            android.util.Log.w(r0, r12)
        L_0x00e9:
            r1 = r5
            goto L_0x01d8
        L_0x00ec:
            r6 = 0
        L_0x00ed:
            java.lang.String r7 = "com.google.android.gms"
            r8 = 64
            android.content.pm.PackageInfo r7 = r4.getPackageInfo(r7, r8)     // Catch:{ NameNotFoundException -> 0x01c9 }
            java.lang.Class<t1.i> r8 = t1.i.class
            monitor-enter(r8)
            t1.i r9 = t1.i.d     // Catch:{ all -> 0x0122 }
            if (r9 != 0) goto L_0x0127
            t1.l r9 = t1.p.f1930a     // Catch:{ all -> 0x0122 }
            java.lang.Class<t1.p> r9 = t1.p.class
            monitor-enter(r9)     // Catch:{ all -> 0x0122 }
            android.content.Context r10 = t1.p.f1931c     // Catch:{ all -> 0x010d }
            if (r10 != 0) goto L_0x010f
            android.content.Context r10 = r12.getApplicationContext()     // Catch:{ all -> 0x010d }
            t1.p.f1931c = r10     // Catch:{ all -> 0x010d }
            monitor-exit(r9)     // Catch:{ all -> 0x0122 }
            goto L_0x0117
        L_0x010d:
            r12 = move-exception
            goto L_0x0125
        L_0x010f:
            java.lang.String r10 = "GoogleCertificates"
            java.lang.String r11 = "GoogleCertificates has been initialized already"
            android.util.Log.w(r10, r11)     // Catch:{ all -> 0x010d }
            monitor-exit(r9)     // Catch:{ all -> 0x0122 }
        L_0x0117:
            t1.i r9 = new t1.i     // Catch:{ all -> 0x0122 }
            r9.<init>()     // Catch:{ all -> 0x0122 }
            r12.getApplicationContext()     // Catch:{ all -> 0x0122 }
            t1.i.d = r9     // Catch:{ all -> 0x0122 }
            goto L_0x0127
        L_0x0122:
            r12 = move-exception
            goto L_0x01c7
        L_0x0125:
            monitor-exit(r9)     // Catch:{ all -> 0x010d }
            throw r12     // Catch:{ all -> 0x0122 }
        L_0x0127:
            monitor-exit(r8)     // Catch:{ all -> 0x0122 }
            boolean r12 = t1.i.g(r7)
            if (r12 != 0) goto L_0x013e
            java.lang.String r12 = java.lang.String.valueOf(r3)
            java.lang.String r13 = " requires Google Play services, but their signature is invalid."
            java.lang.String r0 = "GooglePlayServicesUtil"
            java.lang.String r12 = r12.concat(r13)
            android.util.Log.w(r0, r12)
            goto L_0x00e9
        L_0x013e:
            if (r0 == 0) goto L_0x0159
            w1.r.b(r6)
            boolean r12 = t1.i.g(r6)
            if (r12 != 0) goto L_0x0159
            java.lang.String r12 = java.lang.String.valueOf(r3)
            java.lang.String r13 = " requires Google Play Store, but its signature is invalid."
            java.lang.String r0 = "GooglePlayServicesUtil"
            java.lang.String r12 = r12.concat(r13)
            android.util.Log.w(r0, r12)
            goto L_0x00e9
        L_0x0159:
            if (r0 == 0) goto L_0x017c
            if (r6 == 0) goto L_0x017c
            android.content.pm.Signature[] r12 = r6.signatures
            r12 = r12[r2]
            android.content.pm.Signature[] r0 = r7.signatures
            r0 = r0[r2]
            boolean r12 = r12.equals(r0)
            if (r12 != 0) goto L_0x017c
            java.lang.String r12 = java.lang.String.valueOf(r3)
            java.lang.String r13 = " requires Google Play Store, but its signature doesn't match that of Google Play services."
            java.lang.String r0 = "GooglePlayServicesUtil"
            java.lang.String r12 = r12.concat(r13)
            android.util.Log.w(r0, r12)
            goto L_0x00e9
        L_0x017c:
            int r12 = r7.versionCode
            r0 = -1
            if (r12 != r0) goto L_0x0183
            r5 = r0
            goto L_0x0185
        L_0x0183:
            int r5 = r12 / 1000
        L_0x0185:
            if (r13 != r0) goto L_0x0188
            goto L_0x018a
        L_0x0188:
            int r0 = r13 / 1000
        L_0x018a:
            if (r5 >= r0) goto L_0x01a4
            java.lang.String r0 = "Google Play services out of date for "
            java.lang.String r1 = ".  Requires "
            java.lang.String r2 = " but found "
            java.lang.StringBuilder r13 = i.C0212a.u(r0, r3, r1, r13, r2)
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            java.lang.String r13 = "GooglePlayServicesUtil"
            android.util.Log.w(r13, r12)
            r1 = 2
            goto L_0x01d8
        L_0x01a4:
            android.content.pm.ApplicationInfo r12 = r7.applicationInfo
            if (r12 != 0) goto L_0x01c0
            java.lang.String r12 = "com.google.android.gms"
            android.content.pm.ApplicationInfo r12 = r4.getApplicationInfo(r12, r2)     // Catch:{ NameNotFoundException -> 0x01af }
            goto L_0x01c0
        L_0x01af:
            r12 = move-exception
            java.lang.String r13 = java.lang.String.valueOf(r3)
            java.lang.String r0 = " requires Google Play services, but they're missing when getting application info."
            java.lang.String r2 = "GooglePlayServicesUtil"
            java.lang.String r13 = r13.concat(r0)
            android.util.Log.wtf(r2, r13, r12)
            goto L_0x01d8
        L_0x01c0:
            boolean r12 = r12.enabled
            if (r12 != 0) goto L_0x01c6
            r1 = 3
            goto L_0x01d8
        L_0x01c6:
            return r2
        L_0x01c7:
            monitor-exit(r8)     // Catch:{ all -> 0x0122 }
            throw r12
        L_0x01c9:
            java.lang.String r12 = java.lang.String.valueOf(r3)
            java.lang.String r13 = " requires Google Play services, but they are missing."
            java.lang.String r0 = "GooglePlayServicesUtil"
            java.lang.String r12 = r12.concat(r13)
            android.util.Log.w(r0, r12)
        L_0x01d8:
            return r1
        L_0x01d9:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: t1.h.a(android.content.Context, int):int");
    }
}
