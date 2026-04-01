package com.samsung.scsp.media.api.database.url;

import Ud.a;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.samsung.scsp.media.api.database.url.OneDriveUrlReaderContract;
import i.C0212a;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDriveUrlDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "OneDriveUploadUrl.db";
    public static final int DATABASE_VERSION = 2;

    public OneDriveUrlDbHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 2);
    }

    /* access modifiers changed from: private */
    public void createAllTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(getCreateSQL(OneDriveUrlReaderContract.Entry.UPLOAD_TABLE_NAME));
        sQLiteDatabase.execSQL(getCreateSQL(OneDriveUrlReaderContract.Entry.UPDATE_TABLE_NAME));
    }

    private void doTransaction(SQLiteDatabase sQLiteDatabase, Consumer<SQLiteDatabase> consumer) {
        sQLiteDatabase.beginTransaction();
        try {
            consumer.accept(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void dropAllTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(getDropSQL(OneDriveUrlReaderContract.Entry.UPLOAD_TABLE_NAME));
        sQLiteDatabase.execSQL(getDropSQL(OneDriveUrlReaderContract.Entry.UPDATE_TABLE_NAME));
    }

    private String getCreateSQL(String str) {
        return C0212a.m("CREATE TABLE ", str, " (_id INTEGER PRIMARY KEY,user_id TEXT,hash TEXT,url TEXT,expiration_date INTEGER,nde_original_hash TEXT,nde_original_url TEXT,nde_original_expiration_date INTEGER)");
    }

    public static String getDropSQL(String str) {
        return C0212a.m("DROP TABLE IF EXISTS ", str, ";");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onUpgrade$0(SQLiteDatabase sQLiteDatabase) {
        dropAllTable(sQLiteDatabase);
        createAllTable(sQLiteDatabase);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        doTransaction(sQLiteDatabase, new a(this, 0));
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        doTransaction(sQLiteDatabase, new a(this, 1));
    }
}
