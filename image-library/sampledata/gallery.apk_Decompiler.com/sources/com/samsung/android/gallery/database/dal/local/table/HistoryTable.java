package com.samsung.android.gallery.database.dal.local.table;

import android.database.sqlite.SQLiteDatabase;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import java.util.function.Function;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HistoryTable extends LocalTable {
    public HistoryTable() {
        super("count_history");
    }

    public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS count_history (_id INTEGER PRIMARY KEY AUTOINCREMENT, date_added TEXT UNIQUE,__log TEXT); ");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS operation_history (_id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp TEXT ,action TEXT ,media_id TEXT ,__log TEXT); ");
        sQLiteDatabase.execSQL("CREATE TRIGGER IF NOT EXISTS  op_history_trigger AFTER INSERT ON operation_history WHEN (SELECT COUNT(*) FROM operation_history) > 1000 BEGIN DELETE FROM operation_history WHERE _id = (SELECT MIN(_id) FROM operation_history); END");
    }

    public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        if (needUpgrade(i2, i7, 29)) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS count_history (_id INTEGER PRIMARY KEY AUTOINCREMENT, date_added TEXT UNIQUE,__log TEXT); ");
        }
        if (needUpgrade(i2, i7, 33)) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS operation_history (_id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp TEXT ,action TEXT ,media_id TEXT ,__log TEXT); ");
            sQLiteDatabase.execSQL("CREATE TRIGGER IF NOT EXISTS  op_history_trigger AFTER INSERT ON operation_history WHEN (SELECT COUNT(*) FROM operation_history) > 1000 BEGIN DELETE FROM operation_history WHERE _id = (SELECT MIN(_id) FROM operation_history); END");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ActionKeyword {
        EDIT("E"),
        SHARE("S"),
        CREATE_GIF("CG"),
        CREATE_COLLAGE("CC"),
        CREATE_MOVIE("CM"),
        PICKER("P"),
        SHARE_SHARED("SS", new e(26)),
        VIEW_STORY("VS", new e(27)),
        VIEW("V", new e(26));
        
        private final Function<FileItemInterface, Long> mConsumer;
        private String mKey;

        private ActionKeyword(String str) {
            this.mKey = str;
            this.mConsumer = new e(28);
        }

        public Function<FileItemInterface, Long> getConsumer() {
            return this.mConsumer;
        }

        public String toString() {
            return this.mKey;
        }

        private ActionKeyword(String str, Function<FileItemInterface, Long> function) {
            this.mKey = str;
            this.mConsumer = function;
        }
    }
}
