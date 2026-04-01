package com.samsung.android.gallery.database.dal.local.table;

import android.database.sqlite.SQLiteDatabase;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchTable extends LocalTable {
    int databaseVersion;

    public SearchTable(int i2) {
        super("search_history");
        this.databaseVersion = i2;
    }

    private String getSearchHistoryTableCreate() {
        if (this.databaseVersion < 59) {
            return "search_history (title TEXT UNIQUE NOT NULL, date_added INTEGER, search_target TEXT);";
        }
        return "search_history (title TEXT UNIQUE NOT NULL, date_added INTEGER, cover_id INTEGER, search_target TEXT);";
    }

    private void upgradeToV18(SQLiteDatabase sQLiteDatabase) {
        if (containsColumn(sQLiteDatabase, "search_history", "search_target")) {
            Log.w("SearchTable", "add search_target to search_history, but exist.");
            return;
        }
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS search_history");
        sQLiteDatabase.execSQL("CREATE TABLE search_history (title TEXT UNIQUE NOT NULL, date_added INTEGER, search_target TEXT);");
    }

    private void upgradeToV32(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS search_collect (title TEXT, date_added INTEGER, search_type INTEGER, existed INTEGER DEFAULT 0, clicked INTEGER DEFAULT 0);");
    }

    private void upgradeToV41(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        addColumnIfAbsent(sQLiteDatabase, "search_collect", "existed", " INTEGER DEFAULT 0");
        addColumnIfAbsent(sQLiteDatabase, "search_collect", "clicked", " INTEGER DEFAULT 0");
        dumpLog(sQLiteDatabase, "version [" + i2 + "] upgrade [41] add new column");
    }

    private void upgradeToV59(SQLiteDatabase sQLiteDatabase) {
        recreateTableOnTransaction(sQLiteDatabase, "search_history");
    }

    public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + getSearchHistoryTableCreate());
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS search_collect (title TEXT, date_added INTEGER, search_type INTEGER, existed INTEGER DEFAULT 0, clicked INTEGER DEFAULT 0);");
    }

    public void recreateTableOnTransaction(SQLiteDatabase sQLiteDatabase, String str) {
        if ("search_history".equals(str)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS search_history");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + getSearchHistoryTableCreate());
        } else if ("search_collect".equals(str)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS search_collect");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS search_collect (title TEXT, date_added INTEGER, search_type INTEGER, existed INTEGER DEFAULT 0, clicked INTEGER DEFAULT 0);");
        }
    }

    public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        if (needUpgrade(i2, i7, 18)) {
            upgradeToV18(sQLiteDatabase);
        }
        if (needUpgrade(i2, i7, 32)) {
            upgradeToV32(sQLiteDatabase);
        }
        if (needUpgrade(i2, i7, 41)) {
            upgradeToV41(sQLiteDatabase, i2, i7);
        }
        if (needUpgrade(i2, i7, 59)) {
            upgradeToV59(sQLiteDatabase);
        }
    }
}
