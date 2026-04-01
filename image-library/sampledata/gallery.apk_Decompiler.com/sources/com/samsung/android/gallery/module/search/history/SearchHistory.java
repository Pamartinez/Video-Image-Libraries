package com.samsung.android.gallery.module.search.history;

import E5.a;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchHistory {
    private static volatile SearchHistory sInstance;
    private final LocalDatabaseHelper mLocalDatabaseHelper = LocalDatabaseHelper.getInstance(AppResources.getAppContext());

    private SearchHistory() {
    }

    private String buildPrimaryKey(String str) {
        return ArgumentsUtil.getArgValue(str, "term") + "/" + ArgumentsUtil.getArgValue(str, "sub");
    }

    private void delete(String str, String[] strArr) {
        ThreadPool.getInstance().submit(new a(this, str, strArr, 6));
    }

    public static SearchHistory getInstance() {
        if (sInstance == null) {
            synchronized (SearchHistory.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new SearchHistory();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private SQLiteDatabase getReadableDatabase() {
        return this.mLocalDatabaseHelper.getReadableDatabase();
    }

    private SQLiteDatabase getWritableDatabase() {
        return this.mLocalDatabaseHelper.getWritableDatabase();
    }

    private void insert(String str, long j2) {
        ThreadPool.getInstance().submit(new Y9.a(this, str, j2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$delete$1(String str, String[] strArr, ThreadPool.JobContext jobContext) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            int delete = getWritableDatabase().delete("search_history", str, strArr);
            Log.s("SearchHistory", "delete {" + delete + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (SQLException | NullPointerException e) {
            Log.se("SearchHistory", "delete failed" + e);
        }
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$insert$0(String str, long j2, ThreadPool.JobContext jobContext) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", buildPrimaryKey(str));
            contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("search_target", str);
            contentValues.put("cover_id", Long.valueOf(j2));
            long insertWithOnConflict = getWritableDatabase().insertWithOnConflict("search_history", (String) null, contentValues, 5);
            Log.s("SearchHistory", "insert {" + insertWithOnConflict + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (SQLException | NullPointerException e) {
            Log.se("SearchHistory", "insert failed" + e);
        }
        return Boolean.TRUE;
    }

    public void deleteAllHistory() {
        delete((String) null, (String[]) null);
    }

    public void deleteHistory(String str) {
        delete("title = ? ", new String[]{buildPrimaryKey(str)});
    }

    public void deleteHistoryBefore(long j2) {
        delete("date_added < ? ", new String[]{String.valueOf(j2)});
    }

    public void deleteHistoryWithPrimaryKey(String str) {
        delete("title = ? ", new String[]{str});
    }

    public void filterKeyword() {
        delete("title not like ?", new String[]{"key_word%"});
    }

    public Cursor getCursor() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase != null) {
            return readableDatabase.query("search_history", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, "rowid DESC LIMIT 10");
        }
        return null;
    }

    public ArrayList<HistoryItem> getHistoryItemList(Cursor cursor) {
        ArrayList<HistoryItem> arrayList = new ArrayList<>();
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        arrayList.add(new HistoryItem(cursor.getString(cursor.getColumnIndex("search_target")), cursor.getLong(cursor.getColumnIndex("cover_id")), cursor.getLong(cursor.getColumnIndex("date_added"))));
                    } while (cursor.moveToNext());
                }
            } catch (IllegalStateException e) {
                Log.se("SearchHistory", "getHistoryItemList failed" + e);
                try {
                    if (cursor.getColumnIndex("search_target") == -1) {
                        this.mLocalDatabaseHelper.recreateTable("search_history");
                    }
                } catch (Exception e7) {
                    Log.se("SearchHistory", "getHistoryItemList#recreateTable failed" + e7);
                }
            }
        }
        return arrayList;
    }

    public boolean insertHistory(String str, long j2, boolean z) {
        SearchWordCollector.Type type;
        if (!ArgumentsUtil.getArgValue(str, "subCategoryRemovable", true) || ArgumentsUtil.getArgValue(str, "search_skip_save_history", false)) {
            return false;
        }
        String argValue = ArgumentsUtil.getArgValue(str, "collect_type");
        if (TextUtils.isEmpty(argValue) || (type = SearchWordCollector.getType(argValue)) == null || !type.supportHistory(z) || ArgumentsUtil.getArgValue(str, "SelectedFilter") != null) {
            return false;
        }
        String argValue2 = ArgumentsUtil.getArgValue(str, "title");
        String argValue3 = ArgumentsUtil.getArgValue(str, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
        if (TextUtils.isEmpty(argValue2) && !"People".equals(argValue3) && !"Pet".equals(argValue3)) {
            return false;
        }
        insert(str, j2);
        return true;
    }

    public void deleteHistoryWithPrimaryKey(String[] strArr) {
        delete("title in ? ", strArr);
    }
}
