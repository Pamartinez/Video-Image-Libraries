package com.samsung.android.gallery.database.dal.local.table;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CacheTable extends LocalTable {
    public CacheTable() {
        super("cache");
    }

    public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS cache (_id INTEGER PRIMARY KEY AUTOINCREMENT, __key INTEGER UNIQUE NOT NULL, __Title TEXT, __property INTEGER DEFAULT 0, __data TEXT, __stream BLOB DEFAULT NULL, __misc TEXT, __dateModified INTEGER);");
    }

    public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        if (needUpgrade(i2, i7, 46)) {
            createTablesOnTransaction(sQLiteDatabase);
        }
    }
}
