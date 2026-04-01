package com.samsung.android.gallery.database.dal.local.table;

import A.a;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumMigrationHelper {
    private static final String FILE_PATH = ("/data/sec/." + SeApiCompat.getMyUserId() + "_migration_");
    private static final int[] MIGRATION_STEP = {0, 1, 2, 3};
    private static final AlbumMigrationHelper sInstance = new AlbumMigrationHelper();

    private void dumpLog(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("__category", 3);
            contentValues.put("__timestamp", TimeUtil.getIsoLocalDateTime(System.currentTimeMillis()));
            contentValues.put("__log", str);
            sQLiteDatabase.insert("log", (String) null, contentValues);
        } catch (Exception e) {
            a.s(e, new StringBuilder("dumpLog failed e="), "AlbumMigrationHelper");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0070  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.ArrayList<com.samsung.android.gallery.database.dal.local.table.AlbumEntry> getEntries(android.database.sqlite.SQLiteDatabase r20) {
        /*
            r19 = this;
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r9 = "folder_name"
            java.lang.String r10 = "album_order"
            java.lang.String r2 = "__Title"
            java.lang.String r3 = "__absPath"
            java.lang.String r4 = "__bucketID"
            java.lang.String r5 = "cover_path"
            java.lang.String r6 = "default_cover_path"
            java.lang.String r7 = "cover_rect"
            java.lang.String r8 = "folder_id"
            java.lang.String[] r13 = new java.lang.String[]{r2, r3, r4, r5, r6, r7, r8, r9, r10}
            r2 = 0
            java.lang.String r12 = "album"
            r17 = 0
            r18 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r11 = r20
            android.database.Cursor r3 = r11.query(r12, r13, r14, r15, r16, r17, r18)     // Catch:{ Exception -> 0x006a }
            if (r3 == 0) goto L_0x0064
            boolean r0 = r3.moveToFirst()     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x0064
            r4 = r2
        L_0x0035:
            com.samsung.android.gallery.database.dal.local.table.AlbumEntry r0 = new com.samsung.android.gallery.database.dal.local.table.AlbumEntry     // Catch:{ all -> 0x0048 }
            r0.<init>(r3)     // Catch:{ all -> 0x0048 }
            r1.add(r0)     // Catch:{ all -> 0x0048 }
            if (r4 != 0) goto L_0x004b
            boolean r0 = r0.needToBackup()     // Catch:{ all -> 0x0048 }
            if (r0 == 0) goto L_0x0046
            goto L_0x004b
        L_0x0046:
            r4 = r2
            goto L_0x004d
        L_0x0048:
            r0 = move-exception
        L_0x0049:
            r2 = r0
            goto L_0x0058
        L_0x004b:
            r0 = 1
            r4 = r0
        L_0x004d:
            boolean r0 = r3.moveToNext()     // Catch:{ all -> 0x0048 }
            if (r0 != 0) goto L_0x0035
            r2 = r4
            goto L_0x0064
        L_0x0055:
            r0 = move-exception
            r4 = r2
            goto L_0x0049
        L_0x0058:
            r3.close()     // Catch:{ all -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ Exception -> 0x0061 }
        L_0x0060:
            throw r2     // Catch:{ Exception -> 0x0061 }
        L_0x0061:
            r0 = move-exception
            r2 = r4
            goto L_0x006b
        L_0x0064:
            if (r3 == 0) goto L_0x006e
            r3.close()     // Catch:{ Exception -> 0x006a }
            goto L_0x006e
        L_0x006a:
            r0 = move-exception
        L_0x006b:
            r0.printStackTrace()
        L_0x006e:
            if (r2 != 0) goto L_0x0073
            r1.clear()
        L_0x0073:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.local.table.AlbumMigrationHelper.getEntries(android.database.sqlite.SQLiteDatabase):java.util.ArrayList");
    }

    public static AlbumMigrationHelper getInstance() {
        return sInstance;
    }

    private boolean isPOS() {
        if (SeApiCompat.getSystemPropertiesInt("ro.product.first_api_level", 0) == 28) {
            return true;
        }
        return false;
    }

    private void setAlbumState(int i2) {
        PreferenceCache.AlbumDbMigration.setInt(i2);
    }

    public boolean backupDB(SQLiteDatabase sQLiteDatabase) {
        if (isPOS()) {
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList<AlbumEntry> entries = getEntries(sQLiteDatabase);
            if (entries.isEmpty()) {
                dumpLog(sQLiteDatabase, "[BackupAlbum] no need for backup and restore");
                return false;
            } else if (AlbumEntryJsonUtil.saveJsonForAlbumDb(entries, FileUtils.getFilesDir(), "BACKUP_ALBUM_DB.txt")) {
                setAlbumState(1);
                dumpLog(sQLiteDatabase, "[BackupAlbum] backup successful [" + (System.currentTimeMillis() - currentTimeMillis) + "]");
                return true;
            } else {
                setAlbumState(-1);
                dumpLog(sQLiteDatabase, "[BackupAlbum] backup failed [" + (System.currentTimeMillis() - currentTimeMillis) + "]");
                return false;
            }
        } else {
            try {
                dumpLog(sQLiteDatabase, "[BackupAlbum] no need to backup (not POS), first sdk version = " + Build.VERSION.SEM_FIRST_SDK_INT);
            } catch (Error | Exception unused) {
            }
            return false;
        }
    }
}
