package com.samsung.android.gallery.database.dal.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CursorCache {
    private final WeakReference<Context> mContext;
    private volatile LocalDatabaseHelper mDatabaseOpenHelper;

    public CursorCache(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    private ContentValues createContentValues(Cursor cursor) {
        ContentValues contentValues = new ContentValues();
        int columnCount = cursor.getColumnCount();
        for (int i2 = 0; i2 < columnCount; i2++) {
            int type = cursor.getType(i2);
            String columnName = cursor.getColumnName(i2);
            if (!columnName.equals("_id")) {
                if (type == 1) {
                    contentValues.put(columnName, Long.valueOf(cursor.getLong(i2)));
                } else if (type == 2) {
                    contentValues.put(columnName, Double.valueOf(cursor.getDouble(i2)));
                } else {
                    contentValues.put(columnName, cursor.getString(i2));
                }
            }
        }
        return contentValues;
    }

    private LocalDatabaseHelper getDataBaseHelper() {
        Context context;
        if (this.mDatabaseOpenHelper == null) {
            synchronized (CursorCache.class) {
                try {
                    if (this.mDatabaseOpenHelper == null && (context = this.mContext.get()) != null) {
                        this.mDatabaseOpenHelper = LocalDatabaseHelper.getInstance(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.mDatabaseOpenHelper;
    }

    private SQLiteDatabase getWritableDatabase() {
        LocalDatabaseHelper dataBaseHelper = getDataBaseHelper();
        if (dataBaseHelper != null) {
            return dataBaseHelper.getWritableDatabase();
        }
        return null;
    }

    public Cursor getCache(String str) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            return writableDatabase.rawQuery("select * from " + str, (String[]) null);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0126  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean syncCache(java.lang.String r13, android.database.Cursor r14) {
        /*
            r12 = this;
            java.lang.String r0 = "delete from "
            java.lang.String r1 = "CREATE TABLE "
            java.lang.String r2 = "drop table if exists "
            com.samsung.android.gallery.support.utils.TimeTickLog r3 = new com.samsung.android.gallery.support.utils.TimeTickLog
            java.lang.String r4 = "CursorCache sync "
            java.lang.String r4 = i.C0212a.l(r4, r13)
            r3.<init>(r4)
            r4 = 0
            r5 = 0
            android.database.sqlite.SQLiteDatabase r6 = r12.getWritableDatabase()     // Catch:{ Exception -> 0x011a }
            if (r6 != 0) goto L_0x001f
            if (r6 == 0) goto L_0x001e
            r6.endTransaction()
        L_0x001e:
            return r4
        L_0x001f:
            r6.beginTransaction()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            boolean r7 = r14.moveToFirst()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r8 = 1
            if (r7 == 0) goto L_0x00fd
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r0.append(r13)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r6.execSQL(r0)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r0.append(r13)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r1 = " (dummy INTEGER)"
            r0.append(r1)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r6.execSQL(r0)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            int r0 = r14.getColumnCount()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r1 = r4
        L_0x0051:
            if (r1 >= r0) goto L_0x00d1
            int r2 = r14.getType(r1)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r7 = r14.getColumnName(r1)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r9 = "_id"
            boolean r9 = r7.equals(r9)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            if (r9 == 0) goto L_0x0064
            goto L_0x00ce
        L_0x0064:
            java.lang.String r9 = " add column "
            java.lang.String r10 = "alter table "
            if (r2 != r8) goto L_0x0090
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.<init>()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r10)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r13)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r9)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r7)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r7 = " INTEGER"
            r2.append(r7)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r6.execSQL(r2)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            goto L_0x00ce
        L_0x0088:
            r12 = move-exception
            r5 = r6
            goto L_0x0124
        L_0x008c:
            r12 = move-exception
            r5 = r6
            goto L_0x011b
        L_0x0090:
            r11 = 2
            if (r2 != r11) goto L_0x00b1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.<init>()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r10)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r13)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r9)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r7)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r7 = " REAL"
            r2.append(r7)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r6.execSQL(r2)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            goto L_0x00ce
        L_0x00b1:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.<init>()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r10)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r13)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r9)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r7)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r7 = " TEXT"
            r2.append(r7)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r6.execSQL(r2)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
        L_0x00ce:
            int r1 = r1 + 1
            goto L_0x0051
        L_0x00d1:
            r0 = r4
        L_0x00d2:
            android.content.ContentValues r1 = r12.createContentValues(r14)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r6.insert(r13, r5, r1)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r1 = "CursorCache"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.<init>()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r2.append(r13)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r7 = "sync success : "
            r2.append(r7)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            int r7 = r0 + 1
            r2.append(r0)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            com.samsung.android.gallery.support.utils.Log.d(r1, r0)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            boolean r0 = r14.moveToNext()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            if (r0 != 0) goto L_0x00fb
            goto L_0x010c
        L_0x00fb:
            r0 = r7
            goto L_0x00d2
        L_0x00fd:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r12.<init>(r0)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r12.append(r13)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r6.execSQL(r12)     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
        L_0x010c:
            r6.setTransactionSuccessful()     // Catch:{ Exception -> 0x008c, all -> 0x0088 }
            r6.endTransaction()
            r12 = 0
            r3.tock(r12)
            return r8
        L_0x0118:
            r12 = move-exception
            goto L_0x0124
        L_0x011a:
            r12 = move-exception
        L_0x011b:
            r12.printStackTrace()     // Catch:{ all -> 0x0118 }
            if (r5 == 0) goto L_0x0123
            r5.endTransaction()
        L_0x0123:
            return r4
        L_0x0124:
            if (r5 == 0) goto L_0x0129
            r5.endTransaction()
        L_0x0129:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.database.dal.local.CursorCache.syncCache(java.lang.String, android.database.Cursor):boolean");
    }

    public Cursor getCache(String str, int i2) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            return writableDatabase.rawQuery("select * from " + str + " limit " + i2, (String[]) null);
        } catch (Exception unused) {
            return null;
        }
    }
}
