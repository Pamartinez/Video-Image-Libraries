package com.samsung.android.gallery.database.dal.local.table;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumCoverHistoryTable extends LocalTable {
    public AlbumCoverHistoryTable() {
        super("album_cover_history");
    }

    public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS album_cover_history (__absID INTEGER PRIMARY KEY AUTOINCREMENT, __bucketID INTEGER, __absPath TEXT, cover_path TEXT, cover_rect TEXT, __Title TEXT, __mediaType INTEGER, __width INTEGER, __height INTEGER, __orientation INTEGER, __storageType INTEGER );");
    }

    public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        if (needUpgrade(i2, i7, 56)) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS album_cover_history (__absID INTEGER PRIMARY KEY AUTOINCREMENT, __bucketID INTEGER, __absPath TEXT, cover_path TEXT, cover_rect TEXT, __Title TEXT, __mediaType INTEGER, __width INTEGER, __height INTEGER, __orientation INTEGER, __storageType INTEGER );");
            dumpLog(sQLiteDatabase, "version [" + i2 + "] upgrade [56] add new column");
        }
    }
}
