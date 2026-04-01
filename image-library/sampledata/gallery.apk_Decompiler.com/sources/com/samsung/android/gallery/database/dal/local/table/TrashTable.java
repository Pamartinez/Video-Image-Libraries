package com.samsung.android.gallery.database.dal.local.table;

import android.database.sqlite.SQLiteDatabase;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashTable extends LocalTable {
    public TrashTable() {
        super("trash");
    }

    public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS trash (__absPath TEXT UNIQUE NOT NULL, __Title TEXT, __absID INTEGER, __mediaType INTEGER, __width INTEGER, __height INTEGER, __orientation INTEGER, __originPath TEXT, __originTitle TEXT, __deleteTime INTEGER, __storageType INTEGER, __burstGroupID INTEGER, __bestImage INTEGER, __cloudServerId TEXT, __cloudTP TEXT, __restoreExtra TEXT, __volumeName TEXT, __volumeValid INTEGER, __expiredPeriod INTEGER, __database_version INTEGER, __cloudOriginalBinaryHash TEXT, __cloudOriginalBinarySize INTEGER, __orientationTag Integer);");
    }

    public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        if (needUpgrade(i2, i7, 13)) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS trash (__absPath TEXT UNIQUE NOT NULL, __Title TEXT, __absID INTEGER, __mediaType INTEGER, __width INTEGER, __height INTEGER, __orientation INTEGER, __originPath TEXT, __originTitle TEXT, __deleteTime INTEGER, __storageType INTEGER, __burstGroupID INTEGER, __bestImage INTEGER, __cloudServerId TEXT, __cloudTP TEXT, __restoreExtra TEXT, __volumeName TEXT, __volumeValid INTEGER, __expiredPeriod INTEGER, __database_version INTEGER, __cloudOriginalBinaryHash TEXT, __cloudOriginalBinarySize INTEGER, __orientationTag Integer);");
        }
        if (needUpgrade(i2, i7, 14)) {
            if (containsColumn(sQLiteDatabase, "trash", "__width")) {
                Log.w("TrashTable", "add __width to trash, but exist.");
            } else {
                sQLiteDatabase.execSQL("ALTER TABLE trash ADD COLUMN __width INTEGER");
                sQLiteDatabase.execSQL("ALTER TABLE trash ADD COLUMN __height INTEGER");
            }
        }
        if (needUpgrade(i2, i7, 22)) {
            addColumnIfAbsent(sQLiteDatabase, "trash", "__expiredPeriod", " INTEGER");
        }
        if (needUpgrade(i2, i7, 24)) {
            int[] updateTrashPathData = new TrashUpdateHelper(sQLiteDatabase).updateTrashPathData();
            StringBuilder o2 = C0086a.o(i2, "version [", "] upgrade [24] count [");
            o2.append(updateTrashPathData[0]);
            o2.append("][");
            o2.append(updateTrashPathData[1]);
            o2.append("]");
            dumpLog(sQLiteDatabase, o2.toString());
        }
        if (needUpgrade(i2, i7, 42)) {
            new TrashUpdateHelper(sQLiteDatabase).updateTrashVolumeValid();
        }
        if (needUpgrade(i2, i7, 43)) {
            addColumnIfAbsent(sQLiteDatabase, "trash", "__database_version", " INTEGER DEFAULT 42");
        }
        if (needUpgrade(i2, i7, 49)) {
            sQLiteDatabase.execSQL(createIndexSql("delete_time_idx", "trash", "__deleteTime DESC"));
        }
        if (needUpgrade(i2, i7, 51)) {
            addColumnIfAbsent(sQLiteDatabase, "trash", "__cloudOriginalBinaryHash", " TEXT");
            addColumnIfAbsent(sQLiteDatabase, "trash", "__cloudOriginalBinarySize", " INTEGER  DEFAULT 0");
        }
        if (needUpgrade(i2, i7, 52)) {
            addColumnIfAbsent(sQLiteDatabase, "trash", "__orientationTag", " INTEGER");
        }
    }
}
