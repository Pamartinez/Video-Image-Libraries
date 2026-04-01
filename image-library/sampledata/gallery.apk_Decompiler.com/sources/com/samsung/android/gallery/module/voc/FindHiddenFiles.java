package com.samsung.android.gallery.module.voc;

import A.a;
import C3.i;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FindHiddenFiles {
    private static long sLastUpdated;

    public static void execute() {
        if (!PocFeatures.isEnabled(PocFeatures.GmpAll)) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - sLastUpdated > 3600000) {
                sLastUpdated = currentTimeMillis;
                SimpleThreadPool.getInstance().execute(new i(28));
            }
        }
    }

    private long getLastScannedId() {
        return PreferenceCache.LastHiddenScannedId.getLong();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$execute$0() {
        try {
            new FindHiddenFiles().patch();
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("FindHiddenFile failed. e="), "FindHiddenFiles");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00e4 A[SYNTHETIC, Splitter:B:28:0x00e4] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void patch() {
        /*
            r17 = this;
            r0 = r17
            long r1 = java.lang.System.currentTimeMillis()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r4 = new com.samsung.android.gallery.database.dal.abstraction.query.QueryParams
            java.lang.String r5 = "mp://hiddenFiles"
            r4.<init>((java.lang.String) r5)
            long r5 = r0.getLastScannedId()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r4 = r4.setMinFileId(r5)
            android.database.Cursor r4 = com.samsung.android.gallery.database.dal.DbCompat.query((com.samsung.android.gallery.database.dal.abstraction.query.QueryParams) r4)
            r5 = 1
            java.lang.String r6 = "FindHiddenFiles"
            r7 = 0
            r8 = 0
            if (r4 == 0) goto L_0x007d
            boolean r10 = r4.moveToFirst()     // Catch:{ all -> 0x0046 }
            if (r10 == 0) goto L_0x007d
            int r10 = r4.getCount()     // Catch:{ all -> 0x0046 }
        L_0x0030:
            long r11 = r4.getLong(r7)     // Catch:{ all -> 0x0046 }
            java.lang.String r13 = r4.getString(r5)     // Catch:{ all -> 0x0046 }
            boolean r14 = r0.checkScanRequired(r13)     // Catch:{ all -> 0x0046 }
            if (r14 == 0) goto L_0x0049
            r3.add(r13)     // Catch:{ all -> 0x0046 }
            long r8 = java.lang.Math.max(r11, r8)     // Catch:{ all -> 0x0046 }
            goto L_0x006d
        L_0x0046:
            r0 = move-exception
            r1 = r0
            goto L_0x0074
        L_0x0049:
            if (r13 == 0) goto L_0x006d
            java.lang.String r14 = "scan skip"
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x0046 }
            boolean r12 = com.samsung.android.gallery.support.utils.FileUtils.exists(r13)     // Catch:{ all -> 0x0046 }
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)     // Catch:{ all -> 0x0046 }
            long r15 = com.samsung.android.gallery.support.utils.FileUtils.length(r13)     // Catch:{ all -> 0x0046 }
            java.lang.Long r15 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x0046 }
            java.lang.String r13 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r13)     // Catch:{ all -> 0x0046 }
            java.lang.Object[] r11 = new java.lang.Object[]{r11, r12, r15, r13}     // Catch:{ all -> 0x0046 }
            com.samsung.android.gallery.support.utils.Log.d(r6, r14, r11)     // Catch:{ all -> 0x0046 }
        L_0x006d:
            boolean r11 = r4.moveToNext()     // Catch:{ all -> 0x0046 }
            if (r11 != 0) goto L_0x0030
            goto L_0x007e
        L_0x0074:
            r4.close()     // Catch:{ all -> 0x0078 }
            goto L_0x007c
        L_0x0078:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x007c:
            throw r1
        L_0x007d:
            r10 = r7
        L_0x007e:
            if (r4 == 0) goto L_0x0083
            r4.close()
        L_0x0083:
            boolean r4 = r3.isEmpty()
            if (r4 != 0) goto L_0x00cf
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r11 = "update hidden file {"
            r4.<init>(r11)
            r4.append(r10)
            r10 = 44
            r4.append(r10)
            int r11 = r3.size()
            r4.append(r11)
            r4.append(r10)
            java.lang.Object r10 = r3.get(r7)
            java.lang.String r10 = (java.lang.String) r10
            java.lang.String r10 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r10)
            r4.append(r10)
            java.lang.String r10 = "} +"
            r4.append(r10)
            long r10 = java.lang.System.currentTimeMillis()
            long r10 = r10 - r1
            r4.append(r10)
            java.lang.String r1 = r4.toString()
            com.samsung.android.gallery.support.utils.Log.w(r6, r1)
            com.samsung.android.gallery.support.utils.Log.majorEvent(r6, r1)
            r1 = 0
            com.samsung.android.gallery.support.utils.MediaScanner.scanFiles((java.util.Collection<java.lang.String>) r3, (com.samsung.android.gallery.support.utils.MediaScannerListener) r1)
            r0.saveLastScannedId(r8)
        L_0x00cf:
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r1 = new com.samsung.android.gallery.database.dal.abstraction.query.QueryParams
            java.lang.String r2 = "mp:///WrongDateTime"
            r1.<init>((java.lang.String) r2)
            long r2 = r0.getLastScannedId()
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r0 = r1.setMinFileId(r2)
            android.database.Cursor r1 = com.samsung.android.gallery.database.dal.DbCompat.query((com.samsung.android.gallery.database.dal.abstraction.query.QueryParams) r0)
            if (r1 == 0) goto L_0x0154
            boolean r0 = r1.moveToFirst()     // Catch:{ all -> 0x0138 }
            if (r0 == 0) goto L_0x0154
        L_0x00ea:
            long r2 = r1.getLong(r7)     // Catch:{ all -> 0x0138 }
            java.lang.String r0 = r1.getString(r5)     // Catch:{ all -> 0x0138 }
            android.content.ContentValues r4 = new android.content.ContentValues     // Catch:{ all -> 0x0138 }
            r4.<init>()     // Catch:{ all -> 0x0138 }
            java.lang.String r8 = "datetaken"
            long r9 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0138 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x0138 }
            r4.put(r8, r9)     // Catch:{ all -> 0x0138 }
            android.content.Context r8 = com.samsung.android.gallery.support.utils.AppResources.getAppContext()     // Catch:{ all -> 0x0138 }
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch:{ all -> 0x0138 }
            android.net.Uri r9 = com.samsung.android.gallery.support.providers.MediaUri.getSecMediaUri()     // Catch:{ all -> 0x0138 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0138 }
            r10.<init>()     // Catch:{ all -> 0x0138 }
            java.lang.String r11 = "_id="
            r10.append(r11)     // Catch:{ all -> 0x0138 }
            r10.append(r2)     // Catch:{ all -> 0x0138 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0138 }
            java.lang.String[] r11 = new java.lang.String[r7]     // Catch:{ all -> 0x0138 }
            int r4 = r8.update(r9, r4, r10, r11)     // Catch:{ all -> 0x0138 }
            java.lang.String r8 = "update date taken to now"
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0138 }
            java.lang.String r0 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r0)     // Catch:{ all -> 0x0138 }
            if (r4 != r5) goto L_0x013b
            java.lang.String r3 = "success"
            goto L_0x013d
        L_0x0138:
            r0 = move-exception
            r2 = r0
            goto L_0x014b
        L_0x013b:
            java.lang.String r3 = "failed"
        L_0x013d:
            java.lang.Object[] r0 = new java.lang.Object[]{r2, r0, r3}     // Catch:{ all -> 0x0138 }
            com.samsung.android.gallery.support.utils.Log.v(r6, r8, r0)     // Catch:{ all -> 0x0138 }
            boolean r0 = r1.moveToNext()     // Catch:{ all -> 0x0138 }
            if (r0 != 0) goto L_0x00ea
            goto L_0x0154
        L_0x014b:
            r1.close()     // Catch:{ all -> 0x014f }
            goto L_0x0153
        L_0x014f:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x0153:
            throw r2
        L_0x0154:
            if (r1 == 0) goto L_0x0159
            r1.close()
        L_0x0159:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.voc.FindHiddenFiles.patch():void");
    }

    private void saveLastScannedId(long j2) {
        PreferenceCache.LastHiddenScannedId.setLong(j2);
    }

    public boolean checkScanRequired(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String startsWith : str.split(File.separator)) {
            if (startsWith.startsWith(".")) {
                return false;
            }
        }
        if (!FileUtils.exists(str) || FileUtils.length(str) <= 0) {
            return false;
        }
        return true;
    }
}
