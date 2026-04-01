package com.samsung.android.gallery.module.list;

import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.util.concurrent.atomic.AtomicBoolean;
import n0.C0235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class YearQueryCache {
    public static final Object LOCK = new Object();
    private static final YearQueryCache sInstance = new YearQueryCache();
    private int mRetryCount = 0;
    private final AtomicBoolean mSyncing = new AtomicBoolean(false);

    public static YearQueryCache getInstance() {
        return sInstance;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$syncIfDataChanged$0(String str, Runnable runnable) {
        if (!str.equals(getDataStamp())) {
            sync(str, runnable);
        }
    }

    private void setDataStamp(String str) {
        PreferenceCache.YearCacheDataStamp.setString(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002f, code lost:
        if (r1 < 1) goto L_0x0036;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor get(boolean r4, int r5) {
        /*
            r3 = this;
            java.lang.String r0 = "year_table"
            if (r4 == 0) goto L_0x0013
            com.samsung.android.gallery.database.dal.local.CursorCache r1 = new com.samsung.android.gallery.database.dal.local.CursorCache
            android.content.Context r2 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()
            r1.<init>(r2)
            android.database.Cursor r0 = r1.getCache(r0)
            goto L_0x0020
        L_0x0013:
            com.samsung.android.gallery.database.dal.local.CursorCache r1 = new com.samsung.android.gallery.database.dal.local.CursorCache
            android.content.Context r2 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()
            r1.<init>(r2)
            android.database.Cursor r0 = r1.getCache(r0, r5)
        L_0x0020:
            if (r0 == 0) goto L_0x0036
            int r1 = r0.getCount()
            if (r1 == r5) goto L_0x0032
            int r1 = r3.mRetryCount
            int r2 = r1 + 1
            r3.mRetryCount = r2
            r2 = 1
            if (r1 >= r2) goto L_0x0032
            goto L_0x0036
        L_0x0032:
            r4 = 0
            r3.mRetryCount = r4
            return r0
        L_0x0036:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r1 = "cache#init"
            r3.<init>(r1)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            java.lang.Object[] r4 = new java.lang.Object[]{r0, r5, r4}
            java.lang.String r4 = com.samsung.android.gallery.support.utils.Logger.v(r4)
            r3.append(r4)
            java.lang.String r4 = ""
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "YearQueryCache"
            com.samsung.android.gallery.support.utils.Log.d(r4, r3)
            com.samsung.android.gallery.support.utils.Utils.closeSilently(r0)
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.list.YearQueryCache.get(boolean, int):android.database.Cursor");
    }

    public String getDataStamp() {
        return PreferenceCache.YearCacheDataStamp.getString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0062 A[SYNTHETIC, Splitter:B:17:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0089 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sync(java.lang.String r10, java.lang.Runnable r11) {
        /*
            r9 = this;
            java.lang.String r0 = "YearQueryCache"
            if (r10 == 0) goto L_0x008e
            java.util.concurrent.atomic.AtomicBoolean r1 = r9.mSyncing
            r2 = 0
            r3 = 1
            boolean r1 = r1.compareAndSet(r2, r3)
            if (r1 == 0) goto L_0x008e
            java.lang.String r1 = "syncYearCache"
            com.samsung.android.gallery.support.utils.ThreadUtil.assertBgThread(r1)
            long r4 = java.lang.System.currentTimeMillis()
            java.lang.String r6 = com.samsung.android.gallery.database.dal.abstraction.DbKey.TIMELINE_YEAR     // Catch:{ Exception -> 0x0073 }
            r6.e r7 = new r6.e     // Catch:{ Exception -> 0x0073 }
            r8 = 3
            r7.<init>(r8)     // Catch:{ Exception -> 0x0073 }
            android.database.Cursor r6 = com.samsung.android.gallery.database.dal.DbCompat.query(r6, r7)     // Catch:{ Exception -> 0x0073 }
            if (r6 == 0) goto L_0x0059
            com.samsung.android.gallery.database.dal.local.CursorCache r7 = new com.samsung.android.gallery.database.dal.local.CursorCache     // Catch:{ all -> 0x0057 }
            android.content.Context r8 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()     // Catch:{ all -> 0x0057 }
            r7.<init>(r8)     // Catch:{ all -> 0x0057 }
            java.lang.String r8 = "year_table"
            boolean r7 = r7.syncCache(r8, r6)     // Catch:{ all -> 0x0057 }
            if (r7 == 0) goto L_0x0059
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0057 }
            r7.<init>(r1)     // Catch:{ all -> 0x0057 }
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0057 }
            java.lang.Object[] r1 = new java.lang.Object[]{r10, r1}     // Catch:{ all -> 0x0057 }
            java.lang.String r1 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r1)     // Catch:{ all -> 0x0057 }
            r7.append(r1)     // Catch:{ all -> 0x0057 }
            java.lang.String r1 = r7.toString()     // Catch:{ all -> 0x0057 }
            com.samsung.android.gallery.support.utils.Log.d(r0, r1)     // Catch:{ all -> 0x0057 }
            r9.setDataStamp(r10)     // Catch:{ all -> 0x0057 }
            goto L_0x0060
        L_0x0057:
            r10 = move-exception
            goto L_0x0068
        L_0x0059:
            java.lang.String r10 = "syncYearCache failed"
            com.samsung.android.gallery.support.utils.Log.e(r0, r10)     // Catch:{ all -> 0x0057 }
            r3 = r2
        L_0x0060:
            if (r6 == 0) goto L_0x0082
            r6.close()     // Catch:{ Exception -> 0x0066 }
            goto L_0x0082
        L_0x0066:
            r10 = move-exception
            goto L_0x0077
        L_0x0068:
            if (r6 == 0) goto L_0x0076
            r6.close()     // Catch:{ all -> 0x006e }
            goto L_0x0076
        L_0x006e:
            r1 = move-exception
            r10.addSuppressed(r1)     // Catch:{ Exception -> 0x0073 }
            goto L_0x0076
        L_0x0073:
            r10 = move-exception
            r3 = r2
            goto L_0x0077
        L_0x0076:
            throw r10     // Catch:{ Exception -> 0x0073 }
        L_0x0077:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "syncYearCache failed. e="
            r1.<init>(r4)
            A.a.s(r10, r1, r0)
        L_0x0082:
            java.util.concurrent.atomic.AtomicBoolean r9 = r9.mSyncing
            r9.set(r2)
            if (r3 == 0) goto L_0x008e
            if (r11 == 0) goto L_0x008e
            r11.run()
        L_0x008e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.list.YearQueryCache.sync(java.lang.String, java.lang.Runnable):void");
    }

    public void syncIfChanged(String str, Runnable runnable) {
        if (str != null) {
            String dataStamp = getDataStamp();
            if (!str.equals(dataStamp)) {
                Log.d("YearQueryCache", "syncIfChanged", str, dataStamp);
                sync(str, runnable);
            }
        }
    }

    public boolean syncIfDataChanged(String str, Runnable runnable) {
        if (str == null) {
            return false;
        }
        String dataStamp = getDataStamp();
        if (str.equals(dataStamp)) {
            return false;
        }
        Log.d("YearQueryCache", "syncIfDataChanged", str, dataStamp);
        SimpleThreadPool.getInstance().execute(new C0235b(this, str, runnable, 18));
        return true;
    }
}
