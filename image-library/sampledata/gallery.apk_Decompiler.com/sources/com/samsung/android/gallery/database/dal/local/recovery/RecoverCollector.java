package com.samsung.android.gallery.database.dal.local.recovery;

import A.a;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecoverCollector {
    private static final boolean SUPPORT_RECOVERY_COLLECT = Features.isEnabled(Features.SUPPORT_RECOVER_COLLECT);
    private static final Uri contentUri = Uri.parse("content://secmedia");
    private static final RecoverCollector sInstance = new RecoverCollector();
    private final ContentResolver mContentResolver = AppResources.getAppContext().getContentResolver();

    private RecoverCollector() {
    }

    private int getErrorType(FileItemInterface fileItemInterface, boolean z) {
        if (fileItemInterface.isPostProcessing()) {
            return 4;
        }
        if (z || fileItemInterface.getFileSize() <= 0) {
            return 1;
        }
        return -1;
    }

    public static RecoverCollector getInstance() {
        return sInstance;
    }

    private boolean isValidPath(String str) {
        if (LocationKey.getCurrentLocation().startsWith("location://story/albums") || LocationKey.getCurrentLocation().startsWith("location://trash")) {
            Log.d("RecoverCollector", "skip this");
            return false;
        } else if (!str.endsWith("!$&Welcome@#Image.jpg") && !str.startsWith("/data/sec/cloud/") && !str.contains(".Trash")) {
            return true;
        } else {
            Log.w("RecoverCollector", "invalid path");
            return false;
        }
    }

    public void callRecoveryApi(String str, int i2) {
        if (SUPPORT_RECOVERY_COLLECT) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("recovery_type", i2);
                bundle.putStringArray("_data", new String[]{str});
                this.mContentResolver.call(contentUri, "gallery_recovery", (String) null, bundle);
                Log.d("RecoverCollector", "callRecoveryApi[" + Logger.getEncodedString(str) + NumericEnum.SEP + i2 + "]");
            } catch (Exception e) {
                a.s(e, new StringBuilder("callRecoveryApi:"), "RecoverCollector");
            }
        }
    }

    public void collect(FileItemInterface fileItemInterface, boolean z) {
        if (SUPPORT_RECOVERY_COLLECT && fileItemInterface != null) {
            collect(fileItemInterface, z, false);
        }
    }

    public void collectNCall(FileItemInterface fileItemInterface, boolean z) {
        if (SUPPORT_RECOVERY_COLLECT && fileItemInterface != null) {
            collect(fileItemInterface, z, true);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00aa A[Catch:{ all -> 0x0097, all -> 0x009d, Exception -> 0x00a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b1 A[ADDED_TO_REGION, Catch:{ all -> 0x0097, all -> 0x009d, Exception -> 0x00a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ca A[Catch:{ all -> 0x0097, all -> 0x009d, Exception -> 0x00a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00dc A[ADDED_TO_REGION, Catch:{ all -> 0x0097, all -> 0x009d, Exception -> 0x00a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void collect(com.samsung.android.gallery.database.dbtype.FileItemInterface r13, boolean r14, boolean r15) {
        /*
            r12 = this;
            java.lang.String r0 = r13.getPath()
            boolean r0 = r12.isValidPath(r0)
            r1 = -1
            if (r0 == 0) goto L_0x0010
            int r0 = r12.getErrorType(r13, r14)
            goto L_0x0011
        L_0x0010:
            r0 = r1
        L_0x0011:
            java.lang.String r2 = "RecoverCollector"
            if (r0 != r1) goto L_0x0042
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r15 = "non recover type ["
            r12.<init>(r15)
            r12.append(r14)
            java.lang.String r14 = "]["
            r12.append(r14)
            long r0 = r13.getFileSize()
            r12.append(r0)
            r12.append(r14)
            boolean r13 = r13.isPostProcessing()
            r12.append(r13)
            java.lang.String r13 = "]"
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            com.samsung.android.gallery.support.utils.Log.w(r2, r12)
            return
        L_0x0042:
            java.lang.String r13 = r13.getPath()
            java.lang.String r14 = "__absPath"
            android.content.ContentValues r14 = c0.C0086a.c(r14, r13)
            java.lang.String r1 = "__recoverType"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            r14.put(r1, r3)
            long r3 = java.lang.System.currentTimeMillis()
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            java.lang.String r3 = "__timestamp"
            r14.put(r3, r1)
            r1 = r15 ^ 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r3 = "__recoverDirty"
            r14.put(r3, r1)
            android.content.ContentResolver r4 = r12.mContentResolver     // Catch:{ Exception -> 0x00a3 }
            android.net.Uri r5 = com.samsung.android.gallery.support.config.LocalDatabase.RECOVERY_URI     // Catch:{ Exception -> 0x00a3 }
            java.lang.String r1 = "_id"
            java.lang.String[] r6 = new java.lang.String[]{r1, r3}     // Catch:{ Exception -> 0x00a3 }
            java.lang.String r7 = "__absPath=?"
            java.lang.String[] r8 = new java.lang.String[]{r13}     // Catch:{ Exception -> 0x00a3 }
            r9 = 0
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x00a3 }
            r6 = 0
            r4 = 0
            r8 = 1
            if (r1 == 0) goto L_0x00a6
            boolean r9 = r1.moveToFirst()     // Catch:{ all -> 0x0097 }
            if (r9 == 0) goto L_0x00a6
            long r9 = r1.getLong(r4)     // Catch:{ all -> 0x0097 }
            int r11 = r1.getInt(r8)     // Catch:{ all -> 0x0097 }
            goto L_0x00a8
        L_0x0097:
            r0 = move-exception
            r12 = r0
            r1.close()     // Catch:{ all -> 0x009d }
            goto L_0x00a2
        L_0x009d:
            r0 = move-exception
            r13 = r0
            r12.addSuppressed(r13)     // Catch:{ Exception -> 0x00a3 }
        L_0x00a2:
            throw r12     // Catch:{ Exception -> 0x00a3 }
        L_0x00a3:
            r0 = move-exception
            r12 = r0
            goto L_0x00e2
        L_0x00a6:
            r11 = r4
            r9 = r6
        L_0x00a8:
            if (r1 == 0) goto L_0x00ad
            r1.close()     // Catch:{ Exception -> 0x00a3 }
        L_0x00ad:
            int r1 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r1 <= 0) goto L_0x00ca
            if (r11 != r8) goto L_0x00da
            java.lang.Integer r1 = r14.getAsInteger(r3)     // Catch:{ Exception -> 0x00a3 }
            int r1 = r1.intValue()     // Catch:{ Exception -> 0x00a3 }
            if (r1 != 0) goto L_0x00da
            android.content.ContentResolver r1 = r12.mContentResolver     // Catch:{ Exception -> 0x00a3 }
            java.lang.String r3 = "__absPath=?"
            java.lang.String[] r4 = new java.lang.String[]{r13}     // Catch:{ Exception -> 0x00a3 }
            r1.update(r5, r14, r3, r4)     // Catch:{ Exception -> 0x00a3 }
        L_0x00c8:
            r4 = r8
            goto L_0x00da
        L_0x00ca:
            android.content.ContentResolver r1 = r12.mContentResolver     // Catch:{ Exception -> 0x00a3 }
            r1.insert(r5, r14)     // Catch:{ Exception -> 0x00a3 }
            java.lang.Integer r14 = r14.getAsInteger(r3)     // Catch:{ Exception -> 0x00a3 }
            int r14 = r14.intValue()     // Catch:{ Exception -> 0x00a3 }
            if (r14 != 0) goto L_0x00da
            goto L_0x00c8
        L_0x00da:
            if (r15 == 0) goto L_0x00e1
            if (r4 == 0) goto L_0x00e1
            r12.callRecoveryApi((java.lang.String) r13, (int) r0)     // Catch:{ Exception -> 0x00a3 }
        L_0x00e1:
            return
        L_0x00e2:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r14 = "collect: "
            r13.<init>(r14)
            N2.j.s(r12, r13, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.local.recovery.RecoverCollector.collect(com.samsung.android.gallery.database.dbtype.FileItemInterface, boolean, boolean):void");
    }

    public void callRecoveryApi(ArrayList<String> arrayList, int i2) {
        if (SUPPORT_RECOVERY_COLLECT) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("recovery_type", i2);
                bundle.putStringArray("_data", (String[]) arrayList.toArray(new String[arrayList.size()]));
                this.mContentResolver.call(contentUri, "gallery_recovery", (String) null, bundle);
            } catch (Exception e) {
                a.s(e, new StringBuilder("callRecoveryApi:"), "RecoverCollector");
            }
        }
    }
}
