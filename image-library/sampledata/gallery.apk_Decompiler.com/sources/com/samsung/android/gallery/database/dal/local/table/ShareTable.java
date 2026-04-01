package com.samsung.android.gallery.database.dal.local.table;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareTable extends LocalTable {
    public ShareTable() {
        super("share");
    }

    public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS share (__absPath TEXT UNIQUE NOT NULL, date_added INTEGER, meta_data TEXT, owner TEXT);");
    }

    public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        if (needUpgrade(i2, i7, 20)) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS share (__absPath TEXT UNIQUE NOT NULL, date_added INTEGER, meta_data TEXT, owner TEXT);");
        }
        if (needUpgrade(i2, i7, 55)) {
            addColumnIfAbsent(sQLiteDatabase, "share", "meta_data", "TEXT");
            addColumnIfAbsent(sQLiteDatabase, "share", "owner", "TEXT");
            dumpLog(sQLiteDatabase, "version [" + i2 + "] upgrade [55] add new column");
        }
    }
}
