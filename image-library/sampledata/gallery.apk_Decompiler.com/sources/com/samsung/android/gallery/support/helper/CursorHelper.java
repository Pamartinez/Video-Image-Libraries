package com.samsung.android.gallery.support.helper;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.File;
import java.util.Collection;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CursorHelper {
    public static String dumpCursor(Cursor cursor) {
        return dumpCursor(cursor, true);
    }

    public static String dumpCursorAndClose(Cursor cursor, boolean z) {
        if (cursor == null || cursor.isClosed()) {
            return "";
        }
        String dumpCursor = dumpCursor(cursor, z);
        cursor.close();
        return dumpCursor;
    }

    public static void dumpCursorToFile(Cursor cursor, Cursor cursor2, String str, String str2, String str3) {
        if (cursor2 == null) {
            Log.e("CursorHelper", "dumpCursorToFile failed. null cursor");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = getDefaultPath();
        }
        String str4 = "dumpTableToFile {" + str + str3 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + cursor2.getCount() + "}";
        Log.d("CursorHelper", str4);
        Blackboard.getApplicationInstance().publish("debug://DumpDisplayMessage", str4);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append("/");
        sb2.append(str);
        String p6 = C0212a.p(sb2, str3, ".sql");
        if (str3.contains("ged")) {
            p6 = C0212a.A(p6, "_ged");
        }
        writeSql(cursor, cursor2, str, p6);
    }

    public static void dumpTableToFile(Context context, Uri uri, String str, String str2, String str3) {
        dumpTableToFile(context, uri, str, str2, str3, (Cursor) null);
    }

    private static String getColumnValue(Cursor cursor, boolean z, String str, String str2) {
        String str3;
        int columnIndex = cursor.getColumnIndex(str);
        int type = cursor.getType(columnIndex);
        if (type == 2) {
            str3 = String.valueOf(cursor.getDouble(columnIndex));
        } else if (type == 4) {
            str3 = null;
        } else {
            str3 = cursor.getString(columnIndex);
        }
        if (str3 == null) {
            return str2;
        }
        if (!z || !str3.startsWith("/")) {
            return str3;
        }
        return Logger.getEncodedString(str3);
    }

    private static String getDefaultPath() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(StorageInfo.getDefault().download);
        return C0212a.p(sb2, File.separator, "SamsungGallery");
    }

    public static long getQueryTime(Cursor[] cursorArr) {
        for (Cursor cursor : cursorArr) {
            if (cursor != null) {
                return getQueryTime(cursor);
            }
        }
        return 0;
    }

    public static String joinIds(Collection<?> collection) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "(", ")");
        for (Object valueOf : collection) {
            stringJoiner.add(String.valueOf(valueOf));
        }
        return stringJoiner.toString();
    }

    public static void setQueryTime(Cursor cursor, long j2) {
        if (cursor != null) {
            Bundle extras = cursor.getExtras();
            if (extras == Bundle.EMPTY) {
                extras = new Bundle();
            }
            extras.putLong("query_time", j2);
            cursor.setExtras(extras);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0202, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0205, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x020c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0219, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:?, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:73:0x01f6, B:84:0x0208] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:75:0x01f9, B:92:0x0215] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f5 A[Catch:{ all -> 0x01ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeSql(android.database.Cursor r26, android.database.Cursor r27, java.lang.String r28, java.lang.String r29) {
        /*
            r0 = r26
            r1 = r28
            r2 = r29
            java.lang.String r3 = ")"
            java.lang.String r4 = "("
            java.lang.String r5 = ","
            java.lang.String r6 = "'"
            java.lang.String r7 = "CREATE INDEX `index_trashes__data` ON `trashes` (`_data`);"
            java.lang.String r8 = "drop table if exists "
            java.lang.String r9 = "delete from "
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0245 }
            r10.<init>()     // Catch:{ Exception -> 0x0245 }
            java.lang.String r11 = com.samsung.android.gallery.support.utils.FileUtils.getBucketNameFromPath(r2)     // Catch:{ Exception -> 0x0245 }
            r10.append(r11)     // Catch:{ Exception -> 0x0245 }
            java.lang.String r11 = "/"
            r10.append(r11)     // Catch:{ Exception -> 0x0245 }
            java.lang.String r11 = com.samsung.android.gallery.support.utils.FileUtils.getNameFromPath(r2)     // Catch:{ Exception -> 0x0245 }
            r10.append(r11)     // Catch:{ Exception -> 0x0245 }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x0245 }
            java.io.FileWriter r11 = new java.io.FileWriter     // Catch:{ IOException -> 0x0200 }
            r12 = 0
            r11.<init>(r2, r12)     // Catch:{ IOException -> 0x0200 }
            java.io.BufferedWriter r13 = new java.io.BufferedWriter     // Catch:{ all -> 0x021e }
            r13.<init>(r11)     // Catch:{ all -> 0x021e }
            java.io.PrintWriter r14 = new java.io.PrintWriter     // Catch:{ all -> 0x0211 }
            r14.<init>(r13)     // Catch:{ all -> 0x0211 }
            java.lang.String r15 = "_ged"
            boolean r15 = r2.endsWith(r15)     // Catch:{ all -> 0x01ef }
            java.lang.String r12 = ";"
            if (r15 == 0) goto L_0x0063
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r8.<init>(r9)     // Catch:{ all -> 0x005d }
            r8.append(r1)     // Catch:{ all -> 0x005d }
            r8.append(r12)     // Catch:{ all -> 0x005d }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x005d }
            r14.println(r8)     // Catch:{ all -> 0x005d }
            goto L_0x0075
        L_0x005d:
            r0 = move-exception
            r1 = r0
            r17 = r11
            goto L_0x0208
        L_0x0063:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ef }
            r9.<init>(r8)     // Catch:{ all -> 0x01ef }
            r9.append(r1)     // Catch:{ all -> 0x01ef }
            r9.append(r12)     // Catch:{ all -> 0x01ef }
            java.lang.String r8 = r9.toString()     // Catch:{ all -> 0x01ef }
            r14.println(r8)     // Catch:{ all -> 0x01ef }
        L_0x0075:
            if (r0 == 0) goto L_0x00a1
            boolean r8 = r0.moveToFirst()     // Catch:{ all -> 0x005d }
            if (r8 == 0) goto L_0x00a1
        L_0x007d:
            java.lang.String r7 = "sql"
            int r7 = r0.getColumnIndex(r7)     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r8.<init>()     // Catch:{ all -> 0x005d }
            java.lang.String r7 = r0.getString(r7)     // Catch:{ all -> 0x005d }
            r8.append(r7)     // Catch:{ all -> 0x005d }
            r8.append(r12)     // Catch:{ all -> 0x005d }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x005d }
            r14.println(r7)     // Catch:{ all -> 0x005d }
            boolean r7 = r0.moveToNext()     // Catch:{ all -> 0x005d }
            if (r7 != 0) goto L_0x007d
            goto L_0x00ea
        L_0x00a1:
            java.lang.String r0 = "trashes"
            boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x01ef }
            if (r0 == 0) goto L_0x00ea
            java.lang.String r0 = ""
            com.samsung.android.gallery.support.config.SdkConfig$SEM r8 = com.samsung.android.gallery.support.config.SdkConfig.SEM.B_MR5     // Catch:{ all -> 0x005d }
            boolean r8 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.SEM) r8)     // Catch:{ all -> 0x005d }
            if (r8 == 0) goto L_0x00b7
            java.lang.String r0 = "CREATE TABLE `trashes` (`_data` TEXT NOT NULL, `original_path` TEXT NOT NULL, `_id` INTEGER PRIMARY KEY AUTOINCREMENT, `media_id` INTEGER, `sec_media_id` INTEGER, `is_cloud` INTEGER, `parent` INTEGER NOT NULL, `volume_name` TEXT, `title` TEXT, `title_hash` TEXT, `_display_name` TEXT, `_size` INTEGER, `mime_type` TEXT, `media_type` INTEGER NOT NULL, `datetaken` INTEGER, `date_expires` INTEGER NOT NULL, `date_deleted` INTEGER NOT NULL, `generation_modified` INTEGER, `owner_package_name` TEXT, `width` INTEGER, `height` INTEGER, `duration` INTEGER, `orientation_tag` INTEGER, `orientation` INTEGER, `recordingtype` INTEGER, `is_drm` INTEGER, `device_unique_id` TEXT, `user_id` INTEGER, `delete_package_name` TEXT, `cloud_server_id` TEXT, `extra` TEXT, `original_file_hash` TEXT, timestamp INTEGER, hash TEXT, cloud_server_path TEXT, cloud_original_size INTEGER, cloud_original_binary_hash TEXT, cloud_original_binary_size INTEGER);"
            goto L_0x00cc
        L_0x00b7:
            com.samsung.android.gallery.support.config.SdkConfig$SEM r8 = com.samsung.android.gallery.support.config.SdkConfig.SEM.B     // Catch:{ all -> 0x005d }
            boolean r8 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.SEM) r8)     // Catch:{ all -> 0x005d }
            if (r8 == 0) goto L_0x00c2
            java.lang.String r0 = "CREATE TABLE `trashes` (`_data` TEXT NOT NULL, `original_path` TEXT NOT NULL, `_id` INTEGER PRIMARY KEY AUTOINCREMENT, `media_id` INTEGER, `sec_media_id` INTEGER, `is_cloud` INTEGER, `parent` INTEGER NOT NULL, `volume_name` TEXT, `title` TEXT, `title_hash` TEXT, `_display_name` TEXT, `_size` INTEGER, `mime_type` TEXT, `media_type` INTEGER NOT NULL, `datetaken` INTEGER, `date_expires` INTEGER NOT NULL, `date_deleted` INTEGER NOT NULL, `generation_modified` INTEGER, `owner_package_name` TEXT, `width` INTEGER, `height` INTEGER, `duration` INTEGER, `orientation_tag` INTEGER, `orientation` INTEGER, `recordingtype` INTEGER, `is_drm` INTEGER, `device_unique_id` TEXT, `user_id` INTEGER, `delete_package_name` TEXT, `cloud_server_id` TEXT, `extra` TEXT, `original_file_hash` TEXT);"
            goto L_0x00cc
        L_0x00c2:
            com.samsung.android.gallery.support.config.SdkConfig$SEM r8 = com.samsung.android.gallery.support.config.SdkConfig.SEM.U     // Catch:{ all -> 0x005d }
            boolean r8 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.SEM) r8)     // Catch:{ all -> 0x005d }
            if (r8 == 0) goto L_0x00cc
            java.lang.String r0 = "CREATE TABLE `trashes` (`_data` TEXT NOT NULL, `original_path` TEXT NOT NULL, `_id` INTEGER PRIMARY KEY AUTOINCREMENT, `media_id` INTEGER, `sec_media_id` INTEGER, `is_cloud` INTEGER, `parent` INTEGER NOT NULL, `volume_name` TEXT, `title` TEXT, `title_hash` TEXT, `_display_name` TEXT, `_size` INTEGER, `mime_type` TEXT, `media_type` INTEGER NOT NULL, `datetaken` INTEGER, `date_expires` INTEGER NOT NULL, `date_deleted` INTEGER NOT NULL, `generation_modified` INTEGER, `owner_package_name` TEXT, `width` INTEGER, `height` INTEGER, `duration` INTEGER, `orientation_tag` INTEGER, `orientation` INTEGER, `recordingtype` INTEGER, `is_drm` INTEGER, `device_unique_id` TEXT, `user_id` INTEGER, `delete_package_name` TEXT, `cloud_server_id` TEXT, `extra` TEXT);"
        L_0x00cc:
            boolean r8 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x005d }
            if (r8 != 0) goto L_0x00ea
            java.lang.String r0 = r0.concat(r7)     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r7.<init>()     // Catch:{ all -> 0x005d }
            r7.append(r0)     // Catch:{ all -> 0x005d }
            java.lang.String r0 = "CREATE UNIQUE INDEX `index_trashes_media_id` ON `trashes` (`media_id`);"
            r7.append(r0)     // Catch:{ all -> 0x005d }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x005d }
            r14.println(r0)     // Catch:{ all -> 0x005d }
        L_0x00ea:
            int r0 = r27.getCount()     // Catch:{ all -> 0x01ef }
            long r7 = (long) r0     // Catch:{ all -> 0x01ef }
            boolean r0 = r27.moveToFirst()     // Catch:{ all -> 0x01ef }
            if (r0 == 0) goto L_0x01f4
            java.lang.String[] r0 = r27.getColumnNames()     // Catch:{ all -> 0x01ef }
            java.util.StringJoiner r9 = new java.util.StringJoiner     // Catch:{ all -> 0x01ef }
            r9.<init>(r5, r4, r3)     // Catch:{ all -> 0x01ef }
            int r15 = r0.length     // Catch:{ all -> 0x01ef }
            r17 = r11
            r11 = 0
        L_0x0102:
            if (r11 >= r15) goto L_0x0112
            r26 = r11
            r11 = r0[r26]     // Catch:{ all -> 0x010e }
            r9.add(r11)     // Catch:{ all -> 0x010e }
            int r11 = r26 + 1
            goto L_0x0102
        L_0x010e:
            r0 = move-exception
        L_0x010f:
            r1 = r0
            goto L_0x0208
        L_0x0112:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x010e }
            r11.<init>()     // Catch:{ all -> 0x010e }
            java.lang.String r15 = "insert into "
            r11.append(r15)     // Catch:{ all -> 0x010e }
            r11.append(r1)     // Catch:{ all -> 0x010e }
            r11.append(r9)     // Catch:{ all -> 0x010e }
            java.lang.String r1 = r11.toString()     // Catch:{ all -> 0x010e }
            r18 = 0
            r20 = r18
        L_0x012a:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x010e }
            r9.<init>()     // Catch:{ all -> 0x010e }
            r9.append(r1)     // Catch:{ all -> 0x010e }
            java.lang.String r11 = " VALUES "
            r9.append(r11)     // Catch:{ all -> 0x010e }
            java.util.StringJoiner r11 = new java.util.StringJoiner     // Catch:{ all -> 0x010e }
            r11.<init>(r5, r4, r3)     // Catch:{ all -> 0x010e }
            int r15 = r0.length     // Catch:{ all -> 0x010e }
            r26 = r0
            r0 = 0
        L_0x0140:
            if (r0 >= r15) goto L_0x0180
            r28 = r0
            r0 = r26[r28]     // Catch:{ all -> 0x010e }
            r22 = r1
            r1 = 0
            r23 = r3
            r16 = r4
            r4 = 0
            r3 = r27
            java.lang.String r0 = getColumnValue(r3, r4, r0, r1)     // Catch:{ all -> 0x010e }
            if (r0 != 0) goto L_0x015c
            java.lang.String r0 = "null"
            r11.add(r0)     // Catch:{ all -> 0x010e }
            goto L_0x0177
        L_0x015c:
            java.lang.String r1 = "''"
            java.lang.String r0 = r0.replaceAll(r6, r1)     // Catch:{ all -> 0x010e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x010e }
            r1.<init>()     // Catch:{ all -> 0x010e }
            r1.append(r6)     // Catch:{ all -> 0x010e }
            r1.append(r0)     // Catch:{ all -> 0x010e }
            r1.append(r6)     // Catch:{ all -> 0x010e }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x010e }
            r11.add(r0)     // Catch:{ all -> 0x010e }
        L_0x0177:
            int r0 = r28 + 1
            r4 = r16
            r1 = r22
            r3 = r23
            goto L_0x0140
        L_0x0180:
            r22 = r1
            r23 = r3
            r16 = r4
            r4 = 0
            r3 = r27
            r9.append(r11)     // Catch:{ all -> 0x010e }
            r9.append(r12)     // Catch:{ all -> 0x010e }
            r14.println(r9)     // Catch:{ all -> 0x010e }
            r0 = 1
            long r0 = r20 + r0
            r20 = 1000(0x3e8, double:4.94E-321)
            long r20 = r0 % r20
            int r9 = (r20 > r18 ? 1 : (r20 == r18 ? 0 : -1))
            if (r9 != 0) goto L_0x01d6
            com.samsung.android.gallery.support.blackboard.Blackboard r9 = com.samsung.android.gallery.support.blackboard.Blackboard.getApplicationInstance()     // Catch:{ all -> 0x010e }
            java.lang.String r11 = "debug://DumpDisplayMessage"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x010e }
            r15.<init>()     // Catch:{ all -> 0x010e }
            r15.append(r10)     // Catch:{ all -> 0x010e }
            java.lang.String r4 = " : "
            r15.append(r4)     // Catch:{ all -> 0x010e }
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ all -> 0x010e }
            java.lang.String r3 = "%3.1f%%"
            r21 = r5
            float r5 = (float) r0     // Catch:{ all -> 0x010e }
            r24 = r0
            float r0 = (float) r7     // Catch:{ all -> 0x010e }
            float r5 = r5 / r0
            r0 = 1120403456(0x42c80000, float:100.0)
            float r5 = r5 * r0
            java.lang.Float r0 = java.lang.Float.valueOf(r5)     // Catch:{ all -> 0x010e }
            java.lang.Object[] r0 = new java.lang.Object[]{r0}     // Catch:{ all -> 0x010e }
            java.lang.String r0 = java.lang.String.format(r4, r3, r0)     // Catch:{ all -> 0x010e }
            r15.append(r0)     // Catch:{ all -> 0x010e }
            java.lang.String r0 = r15.toString()     // Catch:{ all -> 0x010e }
            r9.publish(r11, r0)     // Catch:{ all -> 0x010e }
            goto L_0x01da
        L_0x01d6:
            r24 = r0
            r21 = r5
        L_0x01da:
            boolean r0 = r27.moveToNext()     // Catch:{ all -> 0x010e }
            if (r0 != 0) goto L_0x01e1
            goto L_0x01f6
        L_0x01e1:
            r0 = r26
            r4 = r16
            r5 = r21
            r1 = r22
            r3 = r23
            r20 = r24
            goto L_0x012a
        L_0x01ef:
            r0 = move-exception
            r17 = r11
            goto L_0x010f
        L_0x01f4:
            r17 = r11
        L_0x01f6:
            r14.close()     // Catch:{ all -> 0x0205 }
            r13.close()     // Catch:{ all -> 0x0202 }
            r17.close()     // Catch:{ IOException -> 0x0200 }
            goto L_0x0249
        L_0x0200:
            r0 = move-exception
            goto L_0x022b
        L_0x0202:
            r0 = move-exception
        L_0x0203:
            r1 = r0
            goto L_0x0222
        L_0x0205:
            r0 = move-exception
        L_0x0206:
            r1 = r0
            goto L_0x0215
        L_0x0208:
            r14.close()     // Catch:{ all -> 0x020c }
            goto L_0x0210
        L_0x020c:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x0205 }
        L_0x0210:
            throw r1     // Catch:{ all -> 0x0205 }
        L_0x0211:
            r0 = move-exception
            r17 = r11
            goto L_0x0206
        L_0x0215:
            r13.close()     // Catch:{ all -> 0x0219 }
            goto L_0x021d
        L_0x0219:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x0202 }
        L_0x021d:
            throw r1     // Catch:{ all -> 0x0202 }
        L_0x021e:
            r0 = move-exception
            r17 = r11
            goto L_0x0203
        L_0x0222:
            r17.close()     // Catch:{ all -> 0x0226 }
            goto L_0x022a
        L_0x0226:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ IOException -> 0x0200 }
        L_0x022a:
            throw r1     // Catch:{ IOException -> 0x0200 }
        L_0x022b:
            java.lang.String r1 = "CursorHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0245 }
            r3.<init>()     // Catch:{ Exception -> 0x0245 }
            java.lang.String r4 = "appendString fail : "
            r3.append(r4)     // Catch:{ Exception -> 0x0245 }
            r3.append(r2)     // Catch:{ Exception -> 0x0245 }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x0245 }
            com.samsung.android.gallery.support.utils.Log.e(r1, r2)     // Catch:{ Exception -> 0x0245 }
            r0.printStackTrace()     // Catch:{ Exception -> 0x0245 }
            goto L_0x0249
        L_0x0245:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0249:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.helper.CursorHelper.writeSql(android.database.Cursor, android.database.Cursor, java.lang.String, java.lang.String):void");
    }

    public static String dumpCursor(Cursor cursor, boolean z) {
        if (cursor == null) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder();
        try {
            if (cursor.moveToFirst()) {
                String[] columnNames = cursor.getColumnNames();
                for (String append : columnNames) {
                    sb2.append(append);
                    sb2.append("\t");
                }
                sb2.deleteCharAt(sb2.length() - 1);
                sb2.append("\n");
                do {
                    for (String columnValue : columnNames) {
                        sb2.append(getColumnValue(cursor, z, columnValue, "(null)"));
                        sb2.append("\t");
                    }
                    sb2.deleteCharAt(sb2.length() - 1);
                    sb2.append("\n");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb2.toString();
    }

    public static void dumpTableToFile(Context context, Uri uri, String str, String str2, String str3, Cursor cursor) {
        Cursor query;
        Throwable th;
        String str4 = str;
        String str5 = str3;
        if (context == null) {
            Log.e("CursorHelper", "dumpTableToFile failed {" + str4 + str5 + "} null context");
            return;
        }
        String defaultPath = TextUtils.isEmpty(str2) ? getDefaultPath() : str2;
        Blackboard applicationInstance = Blackboard.getApplicationInstance();
        applicationInstance.publish("debug://DumpDisplayMessage", "dumpTableToFile :" + str4);
        try {
            query = context.getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
            dumpCursorToFile(cursor, query, str4, defaultPath, str5);
            if (query != null) {
                query.close();
                return;
            }
            return;
        } catch (Error | Exception e) {
            Throwable th2 = e;
            Log.e((CharSequence) "CursorHelper", "dumpTableToFile failed {" + str4 + "} ", th2);
            return;
        } catch (Throwable th3) {
            th.addSuppressed(th3);
        }
        throw th;
    }

    public static long getQueryTime(Cursor cursor) {
        Bundle extras;
        if (cursor == null || (extras = cursor.getExtras()) == Bundle.EMPTY) {
            return 0;
        }
        return extras.getLong("query_time", 0);
    }
}
