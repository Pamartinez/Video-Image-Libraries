package com.samsung.android.gallery.database.dal.local.table;

import android.database.sqlite.SQLiteDatabase;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LogTable extends LocalTable {
    public LogTable() {
        super("log");
    }

    private String createMaxRowLimitTriggerSql(String str, int i2, int i7) {
        StringBuilder u = C0212a.u("CREATE TRIGGER IF NOT EXISTS ", str, " AFTER INSERT ON log WHEN (SELECT COUNT(*) FROM log WHERE __category = ", i7, ") > ");
        u.append(i2);
        u.append(" BEGIN DELETE FROM log WHERE _id = (SELECT MIN(_id) FROM log WHERE __category = ");
        u.append(i7);
        u.append("); END");
        return u.toString();
    }

    public void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS log (_id INTEGER PRIMARY KEY AUTOINCREMENT, __category INTEGER NOT NULL, __timestamp TEXT, hash TEXT, volume TEXT, __log TEXT); ");
        sQLiteDatabase.execSQL(createMaxRowLimitTriggerSql("log_decoding_trigger", 100, 1));
        sQLiteDatabase.execSQL(createMaxRowLimitTriggerSql("log_delete_trigger", 500, 2));
        sQLiteDatabase.execSQL(createMaxRowLimitTriggerSql("log_version_trigger", 10, 3));
        sQLiteDatabase.execSQL(createMaxRowLimitTriggerSql("log_mde_trigger", 10, 4));
        sQLiteDatabase.execSQL(createMaxRowLimitTriggerSql("log_generic_trigger", 100, 5));
        sQLiteDatabase.execSQL(createIndexSql("hash_log_idx", "log", "hash"));
        sQLiteDatabase.execSQL(createIndexSql("volume_log_idx", "log", "volume"));
    }

    public void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7) {
        if (needUpgrade(i2, i7, 18)) {
            createTablesOnTransaction(sQLiteDatabase);
        }
        if (needUpgrade(i2, i7, 28)) {
            addColumnIfAbsent(sQLiteDatabase, "log", "volume", " TEXT");
            addColumnIfAbsent(sQLiteDatabase, "log", "hash", " TEXT");
            sQLiteDatabase.execSQL(createIndexSql("hash_log_idx", "log", "hash"));
            sQLiteDatabase.execSQL(createIndexSql("volume_log_idx", "log", "volume"));
        }
        if (needUpgrade(i2, i7, 36)) {
            sQLiteDatabase.execSQL(createMaxRowLimitTriggerSql("log_mde_trigger", 10, 4));
        }
        if (needUpgrade(i2, i7, 53)) {
            sQLiteDatabase.execSQL(createMaxRowLimitTriggerSql("log_ppp_trigger", 1000, 9));
        }
    }
}
