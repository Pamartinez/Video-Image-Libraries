package com.samsung.android.gallery.database.dal.local.table;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestsTable extends LocalTable {
    public SuggestsTable() {
        super("suggested");
    }

    public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS suggested (_id INTEGER PRIMARY KEY AUTOINCREMENT, __Type INTEGER, __Title TEXT, __absID INTEGER, __mediaId INTEGER DEFAULT -1, __data TEXT, __width INTEGER, __height INTEGER, __orientation INTEGER, __rect TEXT, __mediaType INTEGER, __storageType INTEGER, extra INTEGER, extraValue TEXT, extraData TEXT, createdTime INTEGER, modifiedTime INTEGER, accessedTime INTEGER, __deleteType INTEGER, video_thumb_start_time INTEGER); ");
    }

    public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        if (needUpgrade(i2, i7, 21)) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS suggested (_id INTEGER PRIMARY KEY AUTOINCREMENT, __Type INTEGER, __Title TEXT, __absID INTEGER, __mediaId INTEGER DEFAULT -1, __data TEXT, __width INTEGER, __height INTEGER, __orientation INTEGER, __rect TEXT, __mediaType INTEGER, __storageType INTEGER, extra INTEGER, extraValue TEXT, extraData TEXT, createdTime INTEGER, modifiedTime INTEGER, accessedTime INTEGER, __deleteType INTEGER, video_thumb_start_time INTEGER); ");
        }
        if (needUpgrade(i2, i7, 40)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS suggested");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS suggested (_id INTEGER PRIMARY KEY AUTOINCREMENT, __Type INTEGER, __Title TEXT, __absID INTEGER, __mediaId INTEGER DEFAULT -1, __data TEXT, __width INTEGER, __height INTEGER, __orientation INTEGER, __rect TEXT, __mediaType INTEGER, __storageType INTEGER, extra INTEGER, extraValue TEXT, extraData TEXT, createdTime INTEGER, modifiedTime INTEGER, accessedTime INTEGER, __deleteType INTEGER, video_thumb_start_time INTEGER); ");
        }
        if (needUpgrade(i2, i7, 44)) {
            addColumnIfAbsent(sQLiteDatabase, "suggested", "video_thumb_start_time", " INTEGER DEFAULT 0");
            dumpLog(sQLiteDatabase, "version [" + i2 + "] upgrade [44] add new column");
        }
    }
}
