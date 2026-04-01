package com.samsung.android.gallery.database.dal.local.table;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MyQueryTable extends LocalTable {
    public static String CREATE_TYPE_ENTRY = "__createType";

    public MyQueryTable() {
        super("my_query");
    }

    public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS my_query (_id INTEGER PRIMARY KEY AUTOINCREMENT, __filterData TEXT NOT NULL, __createType INTEGER DEFAULT 0, __myQueryName TEXT, __dateAdded INTEGER, __dateModified INTEGER, __count INTEGER, __ishide INTEGER DEFAULT 0, __secMediaId INTEGER, __data TEXT, __dateTaken INTEGER, __rect TEXT, __mediaType INTEGER, __width INTEGER, __height INTEGER, __orientation INTEGER, __orientationTag INTEGER, __storageType INTEGER, __size INTEGER, extra TEXT)");
    }

    public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        if (needUpgrade(i2, i7, 60)) {
            createTablesOnTransaction(sQLiteDatabase);
        }
    }
}
