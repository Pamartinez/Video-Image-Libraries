package com.samsung.android.gallery.database.dal.local;

import A.a;
import N2.j;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.CursorCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ZipCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi;
import i.C0212a;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Optional;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CacheProviderHelper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CacheReader {
        Cursor cursor;
        int indexData;
        int indexKey;
        int indexStream;

        public CacheReader(Cursor cursor2) {
            this.indexKey = cursor2.getColumnIndex("__key");
            this.indexData = cursor2.getColumnIndex("__data");
            this.indexStream = cursor2.getColumnIndex("__stream");
            this.cursor = cursor2;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ String lambda$recoverCursor$0(byte[] bArr) {
            return new String(ZipCompat.decompress(bArr), StandardCharsets.UTF_8);
        }

        public String getData() {
            return this.cursor.getString(this.indexData);
        }

        public int getKey() {
            return this.cursor.getInt(this.indexKey);
        }

        public byte[] getStream() {
            return this.cursor.getBlob(this.indexStream);
        }

        public boolean moveToNext() {
            return this.cursor.moveToNext();
        }

        public Cursor recoverCursor() {
            long currentTimeMillis = System.currentTimeMillis();
            String str = (String) Optional.ofNullable(getStream()).map(new e(9)).orElse(getData());
            if (str == null || str.isEmpty()) {
                return null;
            }
            try {
                Cursor cursor2 = CursorCompat.fromJsonString(str).toCursor();
                Log.d("CacheProviderHelper", "recoverCursor" + Logger.vt(Integer.valueOf(getKey()), Integer.valueOf(str.length()), Long.valueOf(currentTimeMillis)));
                return cursor2;
            } catch (Exception e) {
                Log.e((CharSequence) "CacheProviderHelper", "recoverCursor failed", (Throwable) e);
                return null;
            }
        }
    }

    private static int LOCATION_TO_KEY(String str) {
        return ArgumentsUtil.removeArgs(str).hashCode();
    }

    public static void cacheCursor(String str, Cursor cursor) {
        cacheCursor(str, cursor, -1);
    }

    public static int delete(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        int LOCATION_TO_KEY = LOCATION_TO_KEY(str);
        int delete = delete(LOCATION_TO_KEY);
        Log.d("CacheProviderHelper", OpenSessionApi.ITEM_LIMIT_DELETE + Logger.vt(Integer.valueOf(LOCATION_TO_KEY), Integer.valueOf(delete), Long.valueOf(currentTimeMillis)) + "");
        return delete;
    }

    public static SQLiteDatabase getReadableDatabase() {
        return LocalDatabaseHelper.getInstance(AppResources.getAppContext()).getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDatabase() {
        return LocalDatabaseHelper.getInstance(AppResources.getAppContext()).getWritableDatabase();
    }

    public static Cursor query(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        int LOCATION_TO_KEY = LOCATION_TO_KEY(str);
        Cursor query = query(LOCATION_TO_KEY);
        StringBuilder sb2 = new StringBuilder(Contract.QUERY);
        sb2.append(Logger.vt(Integer.valueOf(LOCATION_TO_KEY), query != null ? Integer.valueOf(query.getCount()) : "-", Long.valueOf(currentTimeMillis)));
        sb2.append("");
        Log.d("CacheProviderHelper", sb2.toString());
        return query;
    }

    public static int update(String str, String str2, byte[] bArr) {
        Object obj;
        long currentTimeMillis = System.currentTimeMillis();
        int LOCATION_TO_KEY = LOCATION_TO_KEY(str);
        int upsert = upsert(LOCATION_TO_KEY, str2, bArr);
        StringBuilder sb2 = new StringBuilder("update");
        Integer valueOf = Integer.valueOf(LOCATION_TO_KEY);
        Integer valueOf2 = Integer.valueOf(upsert);
        Object obj2 = "-";
        if (str2 != null) {
            obj = Integer.valueOf(str2.length());
        } else {
            obj = obj2;
        }
        if (bArr != null) {
            obj2 = Integer.valueOf(bArr.length);
        }
        sb2.append(Logger.vt(valueOf, valueOf2, obj, obj2, Long.valueOf(currentTimeMillis)));
        sb2.append("");
        Log.d("CacheProviderHelper", sb2.toString());
        return upsert;
    }

    public static int upsert(int i2, String str, byte[] bArr) {
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put("__data", str);
        contentValues.put("__stream", bArr);
        contentValues.put("__dateModified", Long.valueOf(currentTimeMillis));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            int updateWithOnConflict = writableDatabase.updateWithOnConflict("cache", contentValues, "__key=" + i2, (String[]) null, 4);
            if (updateWithOnConflict == 0) {
                contentValues.put("__key", Integer.valueOf(i2));
                if (writableDatabase.insertWithOnConflict("cache", (String) null, contentValues, 5) > 0) {
                    return 1;
                }
            }
            return updateWithOnConflict;
        } catch (SQLiteException | NullPointerException e) {
            j.u(e, new StringBuilder("upsert failed. e="), "CacheProviderHelper");
            return 0;
        }
    }

    public static void cacheCursor(String str, Cursor cursor, int i2) {
        try {
            int LOCATION_TO_KEY = LOCATION_TO_KEY(str);
            long currentTimeMillis = System.currentTimeMillis();
            String str2 = null;
            String jsonString = cursor != null ? new CursorCompat(cursor, i2).toJsonString() : null;
            byte[] archive = (jsonString == null || jsonString.length() <= 262144) ? null : ZipCompat.archive(jsonString.getBytes(StandardCharsets.UTF_8));
            if (archive == null) {
                str2 = jsonString;
            }
            upsert(LOCATION_TO_KEY, str2, archive);
            StringBuilder sb2 = new StringBuilder("cacheCursor");
            Integer valueOf = Integer.valueOf(LOCATION_TO_KEY);
            Object obj = "-";
            Object valueOf2 = jsonString != null ? Integer.valueOf(jsonString.length()) : obj;
            if (archive != null) {
                obj = Integer.valueOf(archive.length);
            }
            sb2.append(Logger.vt(valueOf, valueOf2, obj, Long.valueOf(currentTimeMillis)));
            sb2.append("");
            Log.d("CacheProviderHelper", sb2.toString());
        } catch (Exception e) {
            a.s(e, new StringBuilder("cacheCursor failed. e="), "CacheProviderHelper");
        }
    }

    public static int delete(int i2) {
        return delete(C0086a.i(i2, "__key="), (String[]) null);
    }

    public static Cursor query(Collection<String> collection) {
        long currentTimeMillis = System.currentTimeMillis();
        String joinText = StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, collection.stream().map(new e(8)).iterator());
        Cursor query = query("__key in (" + joinText + ")", (String[]) null);
        StringBuilder sb2 = new StringBuilder(Contract.QUERY);
        sb2.append(Logger.vt(C0212a.m("[", joinText, "]"), query != null ? Integer.valueOf(query.getCount()) : "-", Long.valueOf(currentTimeMillis)));
        sb2.append("");
        Log.d("CacheProviderHelper", sb2.toString());
        return query;
    }

    private static int delete(String str, String[] strArr) {
        return getWritableDatabase().delete("cache", str, strArr);
    }

    public static Cursor query(int i2) {
        return query("__key=" + i2, (String[]) null);
    }

    public static Cursor query(String str, String[] strArr) {
        String str2;
        try {
            str2 = str;
            try {
                return getReadableDatabase().query("cache", (String[]) null, str2, strArr, (String) null, (String) null, (String) null);
            } catch (SQLiteException | NullPointerException e) {
                e = e;
                j.u(e, j.k("query [", str2, "] failed. e="), "CacheProviderHelper");
                return null;
            }
        } catch (SQLiteException | NullPointerException e7) {
            e = e7;
            str2 = str;
            j.u(e, j.k("query [", str2, "] failed. e="), "CacheProviderHelper");
            return null;
        }
    }
}
