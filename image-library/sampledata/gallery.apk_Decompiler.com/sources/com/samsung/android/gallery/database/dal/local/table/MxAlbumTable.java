package com.samsung.android.gallery.database.dal.local.table;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumTable extends LocalTable {
    public MxAlbumTable() {
        super("mxalbum");
    }

    public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS mxalbum (_id INTEGER PRIMARY KEY AUTOINCREMENT, __bucketID INTEGER UNIQUE NOT NULL, __absPath TEXT, __Title TEXT, folder_id INTEGER, folder_name TEXT, default_cover_path TEXT, cover_path TEXT, cover_rect TEXT, album_order INTEGER, album_count INTEGER, __ishide INTEGER, __sefFileType INTEGER, __isDrm INTEGER,__dateModified INTEGER, __albumLevel INTEGER, __albumType INTEGER, __volumeName TEXT, album_sync_status INTEGER DEFAULT 0, essential_album_order INTEGER, __albumShowInfo INTEGER, __albumDeleted INTEGER DEFAULT 0, __coverSyncRecordId TEXT, __coverSyncData TEXT, __coverSyncDirty INTEGER);");
    }

    public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        if (needUpgrade(i2, i7, 47)) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS mxalbum (_id INTEGER PRIMARY KEY AUTOINCREMENT, __bucketID INTEGER UNIQUE NOT NULL, __absPath TEXT, __Title TEXT, folder_id INTEGER, folder_name TEXT, default_cover_path TEXT, cover_path TEXT, cover_rect TEXT, album_order INTEGER, album_count INTEGER, __ishide INTEGER, __sefFileType INTEGER, __isDrm INTEGER,__dateModified INTEGER, __albumLevel INTEGER, __albumType INTEGER, __volumeName TEXT, album_sync_status INTEGER DEFAULT 0, essential_album_order INTEGER, __albumShowInfo INTEGER, __albumDeleted INTEGER DEFAULT 0, __coverSyncRecordId TEXT, __coverSyncData TEXT, __coverSyncDirty INTEGER);");
            new MxAlbumUpdateHelper(sQLiteDatabase).migrationAlbumData();
            dumpLog(sQLiteDatabase, "version [" + i2 + "] upgrade [47] add new column");
        }
        if (needUpgrade(i2, i7, 48)) {
            addColumnIfAbsent(sQLiteDatabase, "mxalbum", "essential_album_order", " INTEGER ");
            dumpLog(sQLiteDatabase, "version [" + i2 + "] upgrade [48] add new column");
        }
        if (needUpgrade(i2, i7, 50)) {
            addColumnIfAbsent(sQLiteDatabase, "mxalbum", "__albumShowInfo", " INTEGER ");
            dumpLog(sQLiteDatabase, "version [" + i2 + "] upgrade [50] add new column");
        }
        if (needUpgrade(i2, i7, 54)) {
            addColumnIfAbsent(sQLiteDatabase, "mxalbum", "__albumDeleted", " INTEGER DEFAULT 0");
            dumpLog(sQLiteDatabase, "version [" + i2 + "] upgrade [54] add new column");
        }
        if (needUpgrade(i2, i7, 57)) {
            addColumnIfAbsent(sQLiteDatabase, "mxalbum", "__coverSyncRecordId", " TEXT ");
            addColumnIfAbsent(sQLiteDatabase, "mxalbum", "__coverSyncData", " TEXT ");
            addColumnIfAbsent(sQLiteDatabase, "mxalbum", "__coverSyncDirty", " INTEGER ");
            dumpLog(sQLiteDatabase, "version [" + i2 + "] upgrade [57] add new column");
        }
    }
}
