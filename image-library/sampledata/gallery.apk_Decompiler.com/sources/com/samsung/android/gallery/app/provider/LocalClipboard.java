package com.samsung.android.gallery.app.provider;

import A.a;
import android.content.ContentProvider;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.local.CacheProviderHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LocalClipboard {
    private static long mLastFilterUpdatedTime = -1;

    private static void assertArgument(String str) {
        if (!"filter".equals(str)) {
            throw new IllegalArgumentException(C0212a.l("clipboard failed with unknown argument ", str));
        }
    }

    public static Bundle clear(ContentProvider contentProvider, String str, Bundle bundle) {
        assertArgument(str);
        long currentTimeMillis = System.currentTimeMillis();
        int delete = CacheProviderHelper.delete("clipboard://" + str);
        a.A(new Object[]{str, Integer.valueOf(delete), Long.valueOf(currentTimeMillis)}, new StringBuilder(MediaApiContract.Parameter.CLEAR), "LocalClipboard");
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isFilterAvailable() {
        /*
            long r0 = mLastFilterUpdatedTime
            r2 = -1
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 0
            if (r0 != 0) goto L_0x004c
            java.lang.String r0 = "clipboard://filter"
            android.database.Cursor r0 = com.samsung.android.gallery.database.dal.local.CacheProviderHelper.query((java.lang.String) r0)
            if (r0 == 0) goto L_0x0039
            boolean r3 = r0.moveToFirst()     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x0039
            java.lang.String r3 = "__data"
            int r3 = r0.getColumnIndex(r3)     // Catch:{ all -> 0x0037 }
            java.lang.String r3 = r0.getString(r3)     // Catch:{ all -> 0x0037 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x002a
            r3 = r1
            goto L_0x0034
        L_0x002a:
            java.lang.String r3 = "__dateModified"
            int r3 = r0.getColumnIndex(r3)     // Catch:{ all -> 0x0037 }
            long r3 = r0.getLong(r3)     // Catch:{ all -> 0x0037 }
        L_0x0034:
            mLastFilterUpdatedTime = r3     // Catch:{ all -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r1 = move-exception
            goto L_0x0041
        L_0x0039:
            mLastFilterUpdatedTime = r1     // Catch:{ all -> 0x0037 }
        L_0x003b:
            if (r0 == 0) goto L_0x004c
            r0.close()
            goto L_0x004c
        L_0x0041:
            if (r0 == 0) goto L_0x004b
            r0.close()     // Catch:{ all -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x004b:
            throw r1
        L_0x004c:
            long r3 = mLastFilterUpdatedTime
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x0062
            long r0 = java.lang.System.currentTimeMillis()
            long r2 = mLastFilterUpdatedTime
            long r0 = r0 - r2
            r2 = 3600000(0x36ee80, double:1.7786363E-317)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x0062
            r0 = 1
            return r0
        L_0x0062:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.provider.LocalClipboard.isFilterAvailable():boolean");
    }

    public static Bundle load(ContentProvider contentProvider, String str, Bundle bundle) {
        boolean z;
        assertArgument(str);
        long currentTimeMillis = System.currentTimeMillis();
        Bundle bundle2 = new Bundle();
        Cursor query = CacheProviderHelper.query("clipboard://" + str);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    long j2 = query.getLong(query.getColumnIndex("__dateModified"));
                    String string = query.getString(query.getColumnIndex("__data"));
                    if (currentTimeMillis - j2 > 3600000) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        bundle2.putInt("result", 2);
                    } else if (!TextUtils.isEmpty(string)) {
                        bundle2.putInt("result", 0);
                        bundle2.putString("data", string);
                    } else {
                        bundle2.putInt("result", 3);
                    }
                    Log.d("LocalClipboard", "load" + Logger.vt(str, Boolean.valueOf(z), Boolean.valueOf(TextUtils.isEmpty(string)), Long.valueOf(currentTimeMillis)) + "");
                    query.close();
                    return bundle2;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query != null) {
            query.close();
        }
        a.A(new Object[]{str, Long.valueOf(currentTimeMillis)}, new StringBuilder("load failed"), "LocalClipboard");
        bundle2.putInt("result", 3);
        return bundle2;
        throw th;
    }

    public static Bundle update(ContentProvider contentProvider, String str, Bundle bundle) {
        String str2;
        int i2;
        assertArgument(str);
        if (bundle != null) {
            str2 = bundle.getString("data", (String) null);
        } else {
            str2 = null;
        }
        if (!TextUtils.isEmpty(str2)) {
            mLastFilterUpdatedTime = System.currentTimeMillis();
            int update = CacheProviderHelper.update("clipboard://" + str, str2, (byte[]) null);
            Log.d("LocalClipboard", "update" + Logger.vt(str, Integer.valueOf(update), Integer.valueOf(str2.length()), Long.valueOf(mLastFilterUpdatedTime)) + "");
            Bundle bundle2 = new Bundle();
            if (update > 0) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            bundle2.putInt("result", i2);
            return bundle2;
        }
        throw new IllegalArgumentException("clipboard update failed with null data");
    }
}
