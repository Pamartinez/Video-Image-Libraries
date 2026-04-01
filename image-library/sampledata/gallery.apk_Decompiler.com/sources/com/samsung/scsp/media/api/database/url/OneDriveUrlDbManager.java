package com.samsung.scsp.media.api.database.url;

import J5.c;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.util.StringUtil;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.samsung.scsp.media.api.database.OneDriveDatabase;
import com.samsung.scsp.media.api.database.url.OneDriveUrlReaderContract;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class OneDriveUrlDbManager implements OneDriveDatabase {
    private static final Logger logger = Logger.get("OneDriveUrlDbManager");
    private final OneDriveUrlDbHelper dbHelper;

    public OneDriveUrlDbManager(Context context) {
        this.dbHelper = new OneDriveUrlDbHelper(context);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getUrlForNDEType$0(Pair pair) {
        return "getUrlForNDEType: " + pair;
    }

    public void clear() {
        this.dbHelper.getWritableDatabase().delete(getTableName(), (String) null, (String[]) null);
    }

    public void clearExpiredInfo(long j2) {
        this.dbHelper.getWritableDatabase().delete(getTableName(), "expiration_date <= ? OR (nde_original_hash IS NOT NULL AND nde_original_expiration_date <= ?)", new String[]{String.valueOf(j2), String.valueOf(j2)});
    }

    public void deleteDefaultUrlInfo(String str) {
        this.dbHelper.getWritableDatabase().delete(getTableName(), "hash = ? AND nde_original_hash IS NULL", new String[]{str});
    }

    public void deleteNDEUrlInfo(String str, String str2) {
        this.dbHelper.getWritableDatabase().delete(getTableName(), "hash = ? AND nde_original_hash = ?", new String[]{str, str2});
    }

    public abstract String getTableName();

    public Pair<OneDriveUrlInfo, OneDriveUrlInfo> getUrlForNDEType(String str, String str2, String str3) {
        long j2;
        String str4;
        long j3;
        Exception exc;
        String str5;
        if (StringUtil.isEmpty(str3)) {
            return null;
        }
        Cursor query = this.dbHelper.getReadableDatabase().query(getTableName(), new String[]{"url", OneDriveUrlReaderContract.Entry.COLUMN_NAME_EXPIRATION_DATE, OneDriveUrlReaderContract.Entry.COLUMN_NAME_NDE_ORG_URL, OneDriveUrlReaderContract.Entry.COLUMN_NAME_NDE_ORG_EXPIRATION_DATE}, "hash=? AND nde_original_hash=? AND user_id=?", new String[]{str, str2, str3}, (String) null, (String) null, (String) null);
        long j8 = -1;
        String str6 = "";
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndexOrThrow("url"));
                    try {
                        j3 = query.getLong(query.getColumnIndexOrThrow(OneDriveUrlReaderContract.Entry.COLUMN_NAME_EXPIRATION_DATE));
                    } catch (Exception e) {
                        exc = e;
                        j3 = -1;
                        str5 = str6;
                        str6 = string;
                        try {
                            exc.printStackTrace();
                            logger.i("getUrl db read erorr. URL will be emtpy");
                            query.close();
                            j2 = -1;
                            j8 = j3;
                            Pair<OneDriveUrlInfo, OneDriveUrlInfo> pair = new Pair<>(new OneDriveUrlInfo(str, str6, j8), new OneDriveUrlInfo(str2, str4, j2));
                            logger.d(new c(8, pair));
                            return pair;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            query.close();
                            throw th2;
                        }
                    }
                    try {
                        str4 = query.getString(query.getColumnIndexOrThrow(OneDriveUrlReaderContract.Entry.COLUMN_NAME_NDE_ORG_URL));
                        j2 = query.getLong(query.getColumnIndexOrThrow(OneDriveUrlReaderContract.Entry.COLUMN_NAME_NDE_ORG_EXPIRATION_DATE));
                        str6 = string;
                        j8 = j3;
                    } catch (Exception e7) {
                        exc = e7;
                        str5 = str6;
                        str6 = string;
                        exc.printStackTrace();
                        logger.i("getUrl db read erorr. URL will be emtpy");
                        query.close();
                        j2 = -1;
                        j8 = j3;
                        Pair<OneDriveUrlInfo, OneDriveUrlInfo> pair2 = new Pair<>(new OneDriveUrlInfo(str, str6, j8), new OneDriveUrlInfo(str2, str4, j2));
                        logger.d(new c(8, pair2));
                        return pair2;
                    }
                } else {
                    str4 = str6;
                    j2 = -1;
                }
                query.close();
            } catch (Exception e8) {
                exc = e8;
                j3 = -1;
                str5 = str6;
                exc.printStackTrace();
                logger.i("getUrl db read erorr. URL will be emtpy");
                query.close();
                j2 = -1;
                j8 = j3;
                Pair<OneDriveUrlInfo, OneDriveUrlInfo> pair22 = new Pair<>(new OneDriveUrlInfo(str, str6, j8), new OneDriveUrlInfo(str2, str4, j2));
                logger.d(new c(8, pair22));
                return pair22;
            }
        } else {
            str4 = str6;
            j2 = -1;
        }
        Pair<OneDriveUrlInfo, OneDriveUrlInfo> pair222 = new Pair<>(new OneDriveUrlInfo(str, str6, j8), new OneDriveUrlInfo(str2, str4, j2));
        logger.d(new c(8, pair222));
        return pair222;
    }

    public OneDriveUrlInfo getUrlInfoForDefaultType(String str, String str2) {
        if (StringUtil.isEmpty(str2)) {
            return null;
        }
        Cursor query = this.dbHelper.getReadableDatabase().query(getTableName(), new String[]{"url", OneDriveUrlReaderContract.Entry.COLUMN_NAME_EXPIRATION_DATE}, "hash=? AND nde_original_hash IS NULL  AND user_id=?", new String[]{str, str2}, (String) null, (String) null, (String) null);
        String str3 = "";
        long j2 = -1;
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    str3 = query.getString(query.getColumnIndexOrThrow("url"));
                    j2 = query.getLong(query.getColumnIndexOrThrow(OneDriveUrlReaderContract.Entry.COLUMN_NAME_EXPIRATION_DATE));
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.i("getUrl db read erorr. URL will be emtpy");
            } catch (Throwable th) {
                Throwable th2 = th;
                query.close();
                throw th2;
            }
            query.close();
        }
        return new OneDriveUrlInfo(str, str3, j2);
    }

    public void writeDefaultUrlInfo(String str, String str2, String str3) {
        SQLiteDatabase writableDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hash", str);
        contentValues.put("url", str2);
        contentValues.put(OneDriveUrlReaderContract.Entry.COLUMN_NAME_EXPIRATION_DATE, Long.valueOf(System.currentTimeMillis() + MediaApiContract.ONEDRIVE_UPLOAD_URL_EXPIRATION_DATE_PERIOD));
        contentValues.put(OneDriveUrlReaderContract.Entry.COLUMN_NAME_USER_ID, str3);
        writableDatabase.insert(getTableName(), (String) null, contentValues);
    }

    public void writeNDEUrlInfo(String str, String str2, String str3, String str4, String str5) {
        SQLiteDatabase writableDatabase = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hash", str);
        contentValues.put("url", str2);
        contentValues.put(OneDriveUrlReaderContract.Entry.COLUMN_NAME_EXPIRATION_DATE, Long.valueOf(System.currentTimeMillis() + MediaApiContract.ONEDRIVE_UPLOAD_URL_EXPIRATION_DATE_PERIOD));
        contentValues.put(OneDriveUrlReaderContract.Entry.COLUMN_NAME_NDE_ORG_HASH, str3);
        contentValues.put(OneDriveUrlReaderContract.Entry.COLUMN_NAME_NDE_ORG_URL, str4);
        contentValues.put(OneDriveUrlReaderContract.Entry.COLUMN_NAME_NDE_ORG_EXPIRATION_DATE, Long.valueOf(System.currentTimeMillis() + MediaApiContract.ONEDRIVE_UPLOAD_URL_EXPIRATION_DATE_PERIOD));
        contentValues.put(OneDriveUrlReaderContract.Entry.COLUMN_NAME_USER_ID, str5);
        writableDatabase.insert(getTableName(), (String) null, contentValues);
    }
}
