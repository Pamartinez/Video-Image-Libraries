package com.samsung.android.gallery.module.secured;

import A.a;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.samsung.android.gallery.database.dal.local.table.LocalTable;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import i.C0212a;
import java.io.File;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PrivateDatabaseHelper extends SQLiteOpenHelper {
    static volatile PrivateDatabaseHelper instance;
    final FilesTable filesTable = new FilesTable();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FilesTable extends LocalTable {
        public FilesTable() {
            this("files");
        }

        public String buildBnRDebugMessage(SQLiteDatabase sQLiteDatabase, SQLiteDatabase sQLiteDatabase2) {
            return "info=[src=" + queryCount(sQLiteDatabase) + ",dst=" + queryCount(sQLiteDatabase2) + "]";
        }

        public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + this.tableName + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,_data TEXT UNIQUE COLLATE NOCASE,_size INTEGER,mime_type TEXT,media_type INTEGER,orientation INTEGER,orientation_tag INTEGER,width INTEGER,height INTEGER,resolution TEXT,group_type INTEGER,group_id INTEGER,burst_group_id INTEGER DEFAULT 0,best_image INTEGER,_display_name TEXT,bucket_id TEXT,bucket_display_name TEXT,date_added INTEGER,date_modified INTEGER,datetime INTEGER,sef_file_type INTEGER,sef_file_sub_type INTEGER,sef_file_types TEXT,smartcrop_rect TEXT,smartcrop_rect_ratio TEXT,latitude DOUBLE,longitude DOUBLE,addr TEXT,is_360_video INTEGER,is_hdr10_video INTEGER,video_codec_info TEXT,audio_codec_info TEXT,color_transfer INTEGER,recordingtype INTEGER,recording_mode INTEGER,capture_framerate REAL DEFAULT NULL,duration INTEGER,captured_url TEXT,captured_app TEXT,captured_original_path TEXT,original_path TEXT,cam_model TEXT,user_tag TEXT,motionphoto_viewmode INTEGER,ai_cache BLOB,date_deleted INTEGER,is_trashed INTEGER,original_file_hash TEXT,is_favorite INTEGER,is_pending INTEGER DEFAULT 0,owner_package_name TEXT);");
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS datetime_idx ON " + this.tableName + "(media_type, datetime, _id);");
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS group_idx ON " + this.tableName + "(group_type, burst_group_id, bucket_id);");
        }

        public void downgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
            Log.d(this.TAG, "downgradeTablesOnTransaction skip. nothing changed");
        }

        public HashMap<Integer, Integer> queryCount(SQLiteDatabase sQLiteDatabase) {
            Cursor rawQuery;
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            try {
                rawQuery = sQLiteDatabase.rawQuery("select is_pending, count(*) from files group by is_pending", (String[]) null);
                while (rawQuery.moveToNext()) {
                    hashMap.put(Integer.valueOf(rawQuery.getInt(0)), Integer.valueOf(rawQuery.getInt(1)));
                }
                rawQuery.close();
                return hashMap;
            } catch (Exception e) {
                a.s(e, new StringBuilder("queryCount failed. e="), this.TAG);
                return hashMap;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
            if (i7 == 2) {
                sQLiteDatabase.execSQL("alter table " + this.tableName + " add column is_trashed INTEGER");
                sQLiteDatabase.execSQL("alter table " + this.tableName + " add column is_favorite INTEGER");
                sQLiteDatabase.execSQL("alter table " + this.tableName + " add column owner_package_name TEXT");
            }
            if (i7 == 3) {
                sQLiteDatabase.execSQL("alter table " + this.tableName + " add column is_pending INTEGER DEFAULT 0");
            }
            if (i7 == 4) {
                sQLiteDatabase.execSQL("alter table " + this.tableName + " add column date_deleted INTEGER");
            }
            if (needUpgrade(i2, i7, 5)) {
                addColumnIfAbsent(sQLiteDatabase, this.tableName, "user_tag", "TEXT");
                addColumnIfAbsent(sQLiteDatabase, this.tableName, "motionphoto_viewmode", "TEXT");
                addColumnIfAbsent(sQLiteDatabase, this.tableName, "ai_cache", "BLOB");
            }
            if (needUpgrade(i2, i7, 6)) {
                addColumnIfAbsent(sQLiteDatabase, this.tableName, "original_file_hash", "TEXT");
            }
            if (needUpgrade(i2, i7, 7)) {
                addColumnIfAbsent(sQLiteDatabase, this.tableName, "captured_original_path", "TEXT");
            }
        }

        public FilesTable(String str) {
            super(str);
        }
    }

    static {
        if (!FileUtils.makeDirectoryIfAbsent("/data/sec/gallery/secured")) {
            Log.e("PrivateDatabaseHelper", "root creation failed");
        }
    }

    public PrivateDatabaseHelper(Context context) {
        super(context, name(context), (SQLiteDatabase.CursorFactory) null, 7);
        setWriteAheadLoggingEnabled(true);
    }

    public static String getDir(Context context) {
        return "/data/sec/gallery/secured";
    }

    public static PrivateDatabaseHelper getInstance() {
        if (instance == null) {
            synchronized (PrivateDatabaseHelper.class) {
                try {
                    if (instance == null) {
                        instance = new PrivateDatabaseHelper(AppResources.getAppContext());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return instance;
    }

    public static String name(Context context) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getDir(context));
        String str = File.separator;
        return C0212a.q(sb2, str, "databases", str, "secured.db");
    }

    public boolean backup(String str, String str2) {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = getWritableDatabase();
            FilesTable filesTable2 = this.filesTable;
            boolean backup = filesTable2.backup(writableDatabase, filesTable2.tableName, str, str2);
            if (writableDatabase != null) {
                writableDatabase.close();
            }
            Log.d("PrivateDatabaseHelper", "backup", Boolean.valueOf(getWritableDatabase().isOpen()));
            return backup;
        } catch (Throwable th) {
            Log.d("PrivateDatabaseHelper", "backup", Boolean.valueOf(getWritableDatabase().isOpen()));
            throw th;
        }
        throw th;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            this.filesTable.createTablesOnTransaction(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e("PrivateDatabaseHelper", "onCreate failed" + e.getMessage());
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        Log.d("PrivateDatabaseHelper", "onDowngrade", Integer.valueOf(i2), Integer.valueOf(i7));
        try {
            this.filesTable.downgradeTablesOnTransaction(sQLiteDatabase, i2, i7);
        } catch (SQLiteException e) {
            Log.e("PrivateDatabaseHelper", "onDowngrade failed. e=" + e.getMessage());
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        Log.d("PrivateDatabaseHelper", "onUpgrade", Integer.valueOf(i2), Integer.valueOf(i7));
        try {
            this.filesTable.upgradeTablesOnTransaction(sQLiteDatabase, i2, i7);
        } catch (SQLiteException e) {
            Log.e("PrivateDatabaseHelper", "onUpgrade failed. e=" + e.getMessage());
        }
    }

    public boolean restore(String str, String str2) {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = getWritableDatabase();
            FilesTable filesTable2 = this.filesTable;
            boolean restore = filesTable2.restore(writableDatabase, filesTable2.tableName, str, str2);
            if (writableDatabase != null) {
                writableDatabase.close();
            }
            Log.d("PrivateDatabaseHelper", "restore", Boolean.valueOf(getWritableDatabase().isOpen()));
            return restore;
        } catch (Throwable th) {
            Log.d("PrivateDatabaseHelper", "restore", Boolean.valueOf(getWritableDatabase().isOpen()));
            throw th;
        }
        throw th;
    }
}
