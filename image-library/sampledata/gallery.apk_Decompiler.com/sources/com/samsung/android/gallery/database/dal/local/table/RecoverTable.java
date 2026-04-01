package com.samsung.android.gallery.database.dal.local.table;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecoverTable extends LocalTable {
    public RecoverTable() {
        super("recover");
    }

    public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS recover (_id INTEGER PRIMARY KEY AUTOINCREMENT, __absPath TEXT UNIQUE NOT NULL, __recoverType INTEGER DEFAULT 0, __recoverDirty INTEGER DEFAULT 0, __timestamp INTEGER, extra TEXT); ");
    }

    public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        if (needUpgrade(i2, i7, 58)) {
            createTablesOnTransaction(sQLiteDatabase);
        }
    }
}
