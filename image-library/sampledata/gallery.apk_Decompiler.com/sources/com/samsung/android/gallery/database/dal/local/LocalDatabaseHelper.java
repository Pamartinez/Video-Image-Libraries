package com.samsung.android.gallery.database.dal.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.samsung.android.gallery.database.dal.local.table.AlbumCoverHistoryTable;
import com.samsung.android.gallery.database.dal.local.table.AlbumTable;
import com.samsung.android.gallery.database.dal.local.table.CacheTable;
import com.samsung.android.gallery.database.dal.local.table.HistoryTable;
import com.samsung.android.gallery.database.dal.local.table.LocalTable;
import com.samsung.android.gallery.database.dal.local.table.LogTable;
import com.samsung.android.gallery.database.dal.local.table.MxAlbumTable;
import com.samsung.android.gallery.database.dal.local.table.MyQueryTable;
import com.samsung.android.gallery.database.dal.local.table.RecoverTable;
import com.samsung.android.gallery.database.dal.local.table.SearchTable;
import com.samsung.android.gallery.database.dal.local.table.ShareTable;
import com.samsung.android.gallery.database.dal.local.table.SuggestsTable;
import com.samsung.android.gallery.database.dal.local.table.TrashTable;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import n4.C0491c;
import o8.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocalDatabaseHelper extends SQLiteOpenHelper {
    private static volatile LocalDatabaseHelper sInstance;
    private static final ArrayList<LocalTable> sTables = new ArrayList<LocalTable>() {
        {
            add(new SearchTable(60));
            add(new ShareTable());
            add(new TrashTable());
            add(new AlbumTable());
            add(new MxAlbumTable());
            add(new SuggestsTable());
            add(new LogTable());
            add(new HistoryTable());
            add(new CacheTable());
            add(new AlbumCoverHistoryTable());
            add(new RecoverTable());
            add(new MyQueryTable());
        }
    };

    private LocalDatabaseHelper(Context context) {
        super(context, "local.db", (SQLiteDatabase.CursorFactory) null, 60);
    }

    public static LocalDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LocalDatabaseHelper.class) {
                try {
                    if (sInstance == null) {
                        Trace.beginSection("create LocalDatabaseHelper");
                        sInstance = new LocalDatabaseHelper(context);
                        Trace.endSection();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public void close() {
        Log.e("LocalDatabaseHelper", "close (not required)");
    }

    public int getDatabaseVersion() {
        return 60;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Log.d("LocalDatabaseHelper", "onCreate");
        try {
            sQLiteDatabase.beginTransaction();
            sTables.forEach(new a(sQLiteDatabase, 0));
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e("LocalDatabaseHelper", "onCreate failed. e=" + e.getMessage());
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        Log.d("LocalDatabaseHelper", A.a.d(i2, i7, "onDowngrade Version{old=", ",new=", "}"));
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS album");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS search_history");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS trash");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS share");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS suggested");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS search_collect");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cache");
            onCreate(sQLiteDatabase);
        } catch (SQLiteException e) {
            Log.e("LocalDatabaseHelper", "onDowngrade failed. e=" + e.getMessage());
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        Log.d("LocalDatabaseHelper", A.a.d(i2, i7, "onUpgrade Version{old=", ",new=", "}"));
        try {
            sQLiteDatabase.beginTransaction();
            sTables.forEach(new K7.a(sQLiteDatabase, i2, i7, 3));
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e((CharSequence) "LocalDatabaseHelper", "onUpgrade failed", (Throwable) e);
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void recreateTable(String str) {
        Log.e("LocalDatabaseHelper", "recreateTable table {" + str + "}");
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            sTables.forEach(new C0491c(9, writableDatabase, str));
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
        } catch (SQLiteException e) {
            Log.e((CharSequence) "LocalDatabaseHelper", "recreateTable failed", (Throwable) e);
            writableDatabase.endTransaction();
        } catch (IllegalStateException | NullPointerException e7) {
            Log.e((CharSequence) "LocalDatabaseHelper", "recreateTable failed", e7);
            writableDatabase.endTransaction();
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }
}
