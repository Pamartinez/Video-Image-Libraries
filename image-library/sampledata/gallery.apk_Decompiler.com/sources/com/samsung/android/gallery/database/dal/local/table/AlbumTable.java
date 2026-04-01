package com.samsung.android.gallery.database.dal.local.table;

import android.database.sqlite.SQLiteDatabase;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumTable extends LocalTable {
    public AlbumTable() {
        super("album");
    }

    public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS album (_id INTEGER PRIMARY KEY AUTOINCREMENT, __bucketID INTEGER UNIQUE NOT NULL, __absPath TEXT, __Title TEXT, folder_id INTEGER, folder_name TEXT, default_cover_path TEXT, cover_path TEXT, cover_rect TEXT, album_order INTEGER, album_count INTEGER, __ishide INTEGER, __sefFileType INTEGER, __isDrm INTEGER,__dateModified INTEGER, __volumeName TEXT, album_sync_status INTEGER DEFAULT 0, __coverSyncRecordId TEXT, __coverSyncData TEXT, __coverSyncDirty INTEGER);");
    }

    public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        if (needUpgrade(i2, i7, 13)) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS album (_id INTEGER PRIMARY KEY AUTOINCREMENT, __bucketID INTEGER UNIQUE NOT NULL, __absPath TEXT, __Title TEXT, folder_id INTEGER, folder_name TEXT, default_cover_path TEXT, cover_path TEXT, cover_rect TEXT, album_order INTEGER, album_count INTEGER, __ishide INTEGER, __sefFileType INTEGER, __isDrm INTEGER,__dateModified INTEGER, __volumeName TEXT, album_sync_status INTEGER DEFAULT 0, __coverSyncRecordId TEXT, __coverSyncData TEXT, __coverSyncDirty INTEGER);");
        }
        if (needUpgrade(i2, i7, 16)) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS album (_id INTEGER PRIMARY KEY AUTOINCREMENT, __bucketID INTEGER UNIQUE NOT NULL, __absPath TEXT, __Title TEXT, folder_id INTEGER, folder_name TEXT, default_cover_path TEXT, cover_path TEXT, cover_rect TEXT, album_order INTEGER, album_count INTEGER, __ishide INTEGER, __sefFileType INTEGER, __isDrm INTEGER,__dateModified INTEGER, __volumeName TEXT, album_sync_status INTEGER DEFAULT 0, __coverSyncRecordId TEXT, __coverSyncData TEXT, __coverSyncDirty INTEGER);");
        }
        if (needUpgrade(i2, i7, 17)) {
            addColumnIfAbsent(sQLiteDatabase, "album", "__ishide", " INTEGER  DEFAULT -1");
        }
        if (needUpgrade(i2, i7, 19)) {
            addColumnIfAbsent(sQLiteDatabase, "album", "__sefFileType", " INTEGER  DEFAULT 0");
            addColumnIfAbsent(sQLiteDatabase, "album", "__isDrm", " INTEGER  DEFAULT 0");
        }
        if (needUpgrade(i2, i7, 23)) {
            addColumnIfAbsent(sQLiteDatabase, "album", "__dateModified", " INTEGER  DEFAULT 0");
        }
        if (needUpgrade(i2, i7, 25)) {
            addColumnIfAbsent(sQLiteDatabase, "album", "__volumeName", " TEXT ");
            int[] updateAlbumVolumeData = new AlbumUpdateHelper(sQLiteDatabase).updateAlbumVolumeData();
            StringBuilder o2 = C0086a.o(i2, "version [", "] upgrade [25] count [");
            o2.append(updateAlbumVolumeData[0]);
            o2.append("][");
            o2.append(updateAlbumVolumeData[1]);
            o2.append("]");
            dumpLog(sQLiteDatabase, o2.toString());
        }
        if (needUpgrade(i2, i7, 27)) {
            int[] updateAlbumVolumeData2 = new AlbumUpdateHelper(sQLiteDatabase).updateAlbumVolumeData();
            StringBuilder o3 = C0086a.o(i2, "version [", "] upgrade [27] count [");
            o3.append(updateAlbumVolumeData2[0]);
            o3.append("][");
            o3.append(updateAlbumVolumeData2[1]);
            o3.append("]");
            dumpLog(sQLiteDatabase, o3.toString());
        }
        if (needUpgrade(i2, i7, 31)) {
            int[] updateAlbumPathData = new AlbumUpdateHelper(sQLiteDatabase).updateAlbumPathData();
            StringBuilder o9 = C0086a.o(i2, "version [", "] upgrade [31] count [");
            o9.append(updateAlbumPathData[0]);
            o9.append("][");
            o9.append(updateAlbumPathData[1]);
            o9.append("]");
            dumpLog(sQLiteDatabase, o9.toString());
            AlbumMigrationHelper.getInstance().backupDB(sQLiteDatabase);
        }
        if (needUpgrade(i2, i7, 35)) {
            addColumnIfAbsent(sQLiteDatabase, "album", "album_sync_status", " INTEGER DEFAULT 0");
            dumpLog(sQLiteDatabase, "version [" + i2 + "] upgrade [35] add new column");
        }
        if (needUpgrade(i2, i7, 57)) {
            addColumnIfAbsent(sQLiteDatabase, "album", "__coverSyncRecordId", " TEXT ");
            addColumnIfAbsent(sQLiteDatabase, "album", "__coverSyncData", " TEXT ");
            addColumnIfAbsent(sQLiteDatabase, "album", "__coverSyncDirty", " INTEGER ");
            dumpLog(sQLiteDatabase, "version [" + i2 + "] upgrade [57] add new column");
        }
    }
}
