package com.samsung.android.gallery.module.myquery;

import A4.A;
import A4.S;
import J9.a;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper;
import com.samsung.android.gallery.database.dal.local.table.MyQueryTable;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import i.C0212a;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchMyQuery {
    private static final SearchMyQuery sInstance = new SearchMyQuery();
    private final LocalDatabaseHelper mLocalDatabaseHelper = LocalDatabaseHelper.getInstance(AppResources.getAppContext());

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum CREATE_TYPE {
        MY_QUERY,
        TOP5
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MyQueryData {
        String mainCategory;
        String mainFilterTitle;
        String onlyThem;
        String selectedFilter;
        String subCategory;
        String term;

        public MyQueryData(String str, String str2, String str3, String str4, String str5, String str6) {
            this.mainFilterTitle = str;
            this.mainCategory = str2;
            this.subCategory = str3;
            this.selectedFilter = str4;
            this.term = str5;
            this.onlyThem = str6;
        }

        public String getMainCategory() {
            return this.mainCategory;
        }

        public String getMainFilterTitle() {
            return this.mainFilterTitle;
        }

        public String getSelectedFilter() {
            return this.selectedFilter;
        }

        public String getSubCategory() {
            return this.subCategory;
        }

        public String getTerm() {
            return this.term;
        }

        public String onlyThem() {
            return this.onlyThem;
        }
    }

    private SearchMyQuery() {
    }

    private void delete(String str, String[] strArr) {
        try {
            Optional.ofNullable(getWritableDatabase()).ifPresent(new A(23, (Object) str, (Object) strArr));
        } catch (SQLException e) {
            Log.se("SearchMyQuery", "delete failed" + e);
        }
    }

    public static SearchMyQuery getInstance() {
        return sInstance;
    }

    private SQLiteDatabase getReadableDatabase() {
        return this.mLocalDatabaseHelper.getReadableDatabase();
    }

    private SQLiteDatabase getWritableDatabase() {
        return this.mLocalDatabaseHelper.getWritableDatabase();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$delete$2(String str, String[] strArr, SQLiteDatabase sQLiteDatabase) {
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder o2 = C0086a.o(sQLiteDatabase.delete("my_query", str, strArr), "delete {", "} +");
        o2.append(System.currentTimeMillis() - currentTimeMillis);
        Log.s("SearchMyQuery", o2.toString());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$insert$0(String str, String str2, int i2, CREATE_TYPE create_type, MediaItem mediaItem, AtomicLong atomicLong, SQLiteDatabase sQLiteDatabase) {
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put("__myQueryName", str);
        contentValues.put("__filterData", str2);
        contentValues.put("__count", Integer.valueOf(i2));
        contentValues.put("__createType", Integer.valueOf(create_type.ordinal()));
        contentValues.put("__dateAdded", Long.valueOf(currentTimeMillis));
        contentValues.put("__dateModified", Long.valueOf(currentTimeMillis));
        if (mediaItem != null) {
            contentValues.put("__secMediaId", Long.valueOf(mediaItem.getFileId()));
            contentValues.put("__data", mediaItem.getPath());
            contentValues.put("__dateTaken", Long.valueOf(mediaItem.getDateTaken()));
            contentValues.put("__rect", RectUtils.toString(mediaItem.getCropRectRatio()));
            contentValues.put("__mediaType", Integer.valueOf(mediaItem.getMediaType().toInt()));
            contentValues.put("__width", Integer.valueOf(mediaItem.getWidth()));
            contentValues.put("__height", Integer.valueOf(mediaItem.getHeight()));
            contentValues.put("__orientation", Integer.valueOf(mediaItem.getOrientation()));
            contentValues.put("__orientationTag", Integer.valueOf(mediaItem.getOrientationTag()));
            contentValues.put("__storageType", Integer.valueOf(mediaItem.getStorageType().toInt()));
            contentValues.put("__size", Long.valueOf(mediaItem.getFileSize()));
            contentValues.put(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA, "");
        }
        atomicLong.set(sQLiteDatabase.insertWithOnConflict("my_query", (String) null, contentValues, 5));
        Log.s("SearchMyQuery", "insert {" + atomicLong + "} +" + (System.currentTimeMillis() - currentTimeMillis));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$update$1(AtomicInteger atomicInteger, ContentValues contentValues, String str, String[] strArr, SQLiteDatabase sQLiteDatabase) {
        long currentTimeMillis = System.currentTimeMillis();
        atomicInteger.set(sQLiteDatabase.update("my_query", contentValues, str, strArr));
        Log.s("SearchMyQuery", "update {" + atomicInteger + "} +" + (System.currentTimeMillis() - currentTimeMillis));
    }

    private boolean update(ContentValues contentValues, String str, String[] strArr) {
        try {
            AtomicInteger atomicInteger = new AtomicInteger();
            Optional.ofNullable(getWritableDatabase()).ifPresent(new S(atomicInteger, contentValues, str, strArr, 2));
            if (atomicInteger.get() > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            Log.se("SearchMyQuery", "update failed" + e);
            return false;
        }
    }

    public void deleteByCreateType(int i2) {
        delete(C0212a.p(new StringBuilder(), MyQueryTable.CREATE_TYPE_ENTRY, " = ?"), new String[]{String.valueOf(i2)});
    }

    public void deleteById(int i2) {
        delete("_id = ?", new String[]{String.valueOf(i2)});
    }

    public Cursor getCursorWithUnifiedId(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase == null) {
            return null;
        }
        long unifiedIdentityId = IdentityCreatureUtil.getUnifiedIdentityId(str);
        return readableDatabase.rawQuery("SELECT * FROM my_query WHERE __filterData LIKE '%" + str + "%' OR __filterData LIKE '%recommended_id = ? \\\",\\\"selection_args\\\":[\\\"" + unifiedIdentityId + "%'", (String[]) null);
    }

    public Cursor getMyQueryCursor() {
        return getMyQueryCursor((Integer) null);
    }

    public Cursor getTop5Cursor() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase != null) {
            return readableDatabase.query("my_query", (String[]) null, C0212a.p(new StringBuilder(), MyQueryTable.CREATE_TYPE_ENTRY, " = ?"), new String[]{String.valueOf(CREATE_TYPE.TOP5.ordinal())}, (String) null, (String) null, "__dateAdded DESC");
        }
        return null;
    }

    public boolean insert(String str, int i2, String str2, CREATE_TYPE create_type, MediaItem mediaItem) {
        try {
            AtomicLong atomicLong = new AtomicLong();
            Optional.ofNullable(getWritableDatabase()).ifPresent(new a(str, str2, i2, create_type, mediaItem, atomicLong));
            if (atomicLong.get() > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            Log.se("SearchMyQuery", "insert failed" + e);
            return false;
        }
    }

    public void updateFilterData(int i2, String str) {
        update(C0086a.c("__filterData", str), C0086a.i(i2, "_id = "), (String[]) null);
    }

    public boolean updateMyQuery(int i2, String str, int i7) {
        ContentValues contentValues = new ContentValues();
        long currentTimeMillis = System.currentTimeMillis();
        contentValues.put("__myQueryName", str);
        contentValues.put("__count", Integer.valueOf(i7));
        contentValues.put("__dateAdded", Long.valueOf(currentTimeMillis));
        contentValues.put("__dateModified", Long.valueOf(currentTimeMillis));
        return update(contentValues, "_id = ?", new String[]{String.valueOf(i2)});
    }

    public void updateTop5(String str) {
        ContentValues c5 = C0086a.c("__filterData", str);
        update(c5, MyQueryTable.CREATE_TYPE_ENTRY + " = " + CREATE_TYPE.TOP5.ordinal(), (String[]) null);
    }

    public Cursor getMyQueryCursor(Integer num) {
        String str;
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase == null) {
            return null;
        }
        String p6 = C0212a.p(new StringBuilder(), MyQueryTable.CREATE_TYPE_ENTRY, " = ? ");
        String[] strArr = {String.valueOf(CREATE_TYPE.MY_QUERY.ordinal())};
        StringBuilder sb2 = new StringBuilder("__dateAdded DESC");
        if (num != null) {
            str = " LIMIT " + num;
        } else {
            str = "";
        }
        sb2.append(str);
        return readableDatabase.query("my_query", (String[]) null, p6, strArr, (String) null, (String) null, sb2.toString());
    }
}
