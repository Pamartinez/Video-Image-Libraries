package androidx.appsearch.platformstorage.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppSearchVersionUtil {
    private static volatile long sAppSearchVersionCode = -1;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ApiHelperForQ {
        public static String getAppSearchPackageName(PackageManager packageManager) {
            return packageManager.getModuleInfo("com.android.appsearch", 1).getPackageName();
        }

        public static long getPackageInfoLongVersionCode(PackageInfo packageInfo) {
            return packageInfo.getLongVersionCode();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:10|11|12|(1:14)|16|17) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0030 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long getAppSearchVersionCode(android.content.Context r6) {
        /*
            androidx.core.util.Preconditions.checkNotNull(r6)
            long r0 = sAppSearchVersionCode
            r2 = -1
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x000e
            long r0 = sAppSearchVersionCode
            return r0
        L_0x000e:
            java.lang.Class<androidx.appsearch.platformstorage.util.AppSearchVersionUtil> r0 = androidx.appsearch.platformstorage.util.AppSearchVersionUtil.class
            monitor-enter(r0)
            long r4 = sAppSearchVersionCode     // Catch:{ all -> 0x002e }
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 != 0) goto L_0x0032
            r1 = 0
            android.content.pm.PackageManager r6 = r6.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0030 }
            java.lang.String r3 = androidx.appsearch.platformstorage.util.AppSearchVersionUtil.ApiHelperForQ.getAppSearchPackageName(r6)     // Catch:{ NameNotFoundException -> 0x0030 }
            if (r3 == 0) goto L_0x0030
            r4 = 1073741824(0x40000000, float:2.0)
            android.content.pm.PackageInfo r6 = r6.getPackageInfo(r3, r4)     // Catch:{ NameNotFoundException -> 0x0030 }
            long r1 = androidx.appsearch.platformstorage.util.AppSearchVersionUtil.ApiHelperForQ.getPackageInfoLongVersionCode(r6)     // Catch:{ NameNotFoundException -> 0x0030 }
            goto L_0x0030
        L_0x002e:
            r6 = move-exception
            goto L_0x0036
        L_0x0030:
            sAppSearchVersionCode = r1     // Catch:{ all -> 0x002e }
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            long r0 = sAppSearchVersionCode
            return r0
        L_0x0036:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appsearch.platformstorage.util.AppSearchVersionUtil.getAppSearchVersionCode(android.content.Context):long");
    }

    public static boolean isAtLeastB() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 36) {
            return true;
        }
        if (i2 < 35 || !Build.VERSION.CODENAME.equals("Baklava")) {
            return false;
        }
        return true;
    }
}
