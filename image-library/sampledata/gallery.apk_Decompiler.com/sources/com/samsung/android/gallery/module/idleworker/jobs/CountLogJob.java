package com.samsung.android.gallery.module.idleworker.jobs;

import android.content.Context;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CountLogJob extends IdleWorkerJob {
    public CountLogJob(int i2, IdleWorkerJob.Type type) {
        super(i2, type);
    }

    public void execute(Context context) {
        insertLog(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void insertLog(android.content.Context r9) {
        /*
            r8 = this;
            java.lang.String r8 = "CountLogger"
            java.lang.String r0 = "insertLog failed. e="
            java.lang.String r1 = "inserted : "
            java.lang.String r2 = "delete from count_history where _id<"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            long r4 = java.lang.System.currentTimeMillis()
            java.lang.String r4 = com.samsung.android.gallery.support.utils.TimeUtil.getIsoLocalDateTime(r4)
            r3.append(r4)
            java.lang.String r4 = "\n"
            r3.append(r4)
            com.samsung.android.gallery.support.config.SdkConfig$GED r5 = com.samsung.android.gallery.support.config.SdkConfig.GED.Q
            boolean r5 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.GED) r5)
            r6 = 0
            if (r5 == 0) goto L_0x003b
            java.lang.String r5 = "secMp\n"
            r3.append(r5)
            android.database.Cursor r5 = com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper.getCountCursorSecMp()
            java.lang.String r5 = com.samsung.android.gallery.support.helper.CursorHelper.dumpCursorAndClose(r5, r6)
            r3.append(r5)
            r3.append(r4)
            goto L_0x004b
        L_0x003b:
            java.lang.String r4 = "mp\n"
            r3.append(r4)
            android.database.Cursor r4 = com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper.getCountCursorNewMp()
            java.lang.String r4 = com.samsung.android.gallery.support.helper.CursorHelper.dumpCursorAndClose(r4, r6)
            r3.append(r4)
        L_0x004b:
            android.content.ContentValues r4 = new android.content.ContentValues
            r4.<init>()
            long r5 = java.lang.System.currentTimeMillis()
            java.lang.String r5 = com.samsung.android.gallery.support.utils.TimeUtil.getIsoLocalDate(r5)
            java.lang.String r6 = "date_added"
            r4.put(r6, r5)
            java.lang.String r5 = "__log"
            java.lang.String r3 = r3.toString()
            r4.put(r5, r3)
            r3 = 0
            com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper r9 = com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper.getInstance(r9)     // Catch:{ Exception -> 0x00b1 }
            android.database.sqlite.SQLiteDatabase r9 = r9.getWritableDatabase()     // Catch:{ Exception -> 0x00b1 }
            r9.beginTransaction()     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            java.lang.String r5 = "count_history"
            r6 = 4
            long r3 = r9.insertWithOnConflict(r5, r3, r4, r6)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            r5 = 0
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x0099
            r5 = 30
            long r5 = r3 - r5
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            r7.<init>(r2)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            r7.append(r5)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            java.lang.String r2 = r7.toString()     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            r9.execSQL(r2)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            goto L_0x0099
        L_0x0093:
            r8 = move-exception
            r3 = r9
            goto L_0x00cb
        L_0x0096:
            r1 = move-exception
            r3 = r9
            goto L_0x00b2
        L_0x0099:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            r2.append(r3)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            com.samsung.android.gallery.support.utils.Log.d(r8, r1)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            r9.setTransactionSuccessful()     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            r9.endTransaction()
            return
        L_0x00af:
            r8 = move-exception
            goto L_0x00cb
        L_0x00b1:
            r1 = move-exception
        L_0x00b2:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00af }
            r9.<init>(r0)     // Catch:{ all -> 0x00af }
            java.lang.String r0 = r1.getMessage()     // Catch:{ all -> 0x00af }
            r9.append(r0)     // Catch:{ all -> 0x00af }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00af }
            com.samsung.android.gallery.support.utils.Log.e(r8, r9)     // Catch:{ all -> 0x00af }
            if (r3 == 0) goto L_0x00ca
            r3.endTransaction()
        L_0x00ca:
            return
        L_0x00cb:
            if (r3 == 0) goto L_0x00d0
            r3.endTransaction()
        L_0x00d0:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.idleworker.jobs.CountLogJob.insertLog(android.content.Context):void");
    }
}
