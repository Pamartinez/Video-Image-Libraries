package com.samsung.android.gallery.database.dal.local.table;

import C3.o;
import F9.d;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import n5.e;
import o8.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LocalTable {
    protected final String TAG = getClass().getSimpleName();
    final ConcurrentHashMap<String, HashSet<String>> columnsMap = new ConcurrentHashMap<>();
    public final String tableName;

    public LocalTable(String str) {
        this.tableName = str;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ HashSet lambda$computeColumnNames$0(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        return new HashSet(getColumnNames(sQLiteDatabase, str));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$restore$4(Set set, String str) {
        return !set.contains(str);
    }

    public final void addColumnIfAbsent(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        if (containsColumn(sQLiteDatabase, str, str2)) {
            String str4 = this.TAG;
            Log.w(str4, "skip add " + str2 + " to " + str);
            return;
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE " + str + " ADD COLUMN " + str2 + " " + str3);
        } catch (SQLiteException e) {
            String str5 = this.TAG;
            Log.e(str5, "addColumnIfEmpty failed. e=" + e.getMessage());
        }
    }

    public final boolean backup(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        SQLiteDatabase openDatabase;
        String str4;
        long currentTimeMillis = System.currentTimeMillis();
        SQLiteDatabase.OpenParams build = new SQLiteDatabase.OpenParams.Builder().setOpenFlags(268435456).setJournalMode("OFF").build();
        File file = new File(str2);
        try {
            openDatabase = SQLiteDatabase.openDatabase(file, build);
            createTablesOnTransaction(openDatabase);
            listOfIndex(openDatabase, str).forEach(new a(openDatabase, 1));
            String join = String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, getColumnNames(sQLiteDatabase, str));
            sQLiteDatabase.execSQL("ATTACH DATABASE '" + str2 + "' AS backup");
            StringBuilder sb2 = new StringBuilder("INSERT INTO backup.");
            sb2.append(str);
            sb2.append(" (");
            sb2.append(join);
            sb2.append(") SELECT ");
            sb2.append(join);
            sb2.append(" FROM ");
            sb2.append(str);
            if ("image/*".equalsIgnoreCase(str3)) {
                str4 = " where media_type=1";
            } else if ("video/*".equalsIgnoreCase(str3)) {
                str4 = " where media_type=3";
            } else {
                str4 = "";
            }
            sb2.append(str4);
            sQLiteDatabase.execSQL(sb2.toString());
            sQLiteDatabase.execSQL("DETACH DATABASE backup");
            String str5 = this.TAG;
            Log.d(str5, "backup" + Logger.vt(Logger.getEncodedString(file.getName()), Integer.valueOf(count(sQLiteDatabase, str)), Long.valueOf(currentTimeMillis)) + ((String) Optional.ofNullable(buildBnRDebugMessage(sQLiteDatabase, openDatabase)).map(new e(29)).orElse("")));
            if (openDatabase == null) {
                return true;
            }
            openDatabase.close();
            return true;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("backup failed. e="), this.TAG);
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String buildBnRDebugMessage(SQLiteDatabase sQLiteDatabase, SQLiteDatabase sQLiteDatabase2) {
        return null;
    }

    public final Set<String> computeColumnNames(SQLiteDatabase sQLiteDatabase, String str) {
        return this.columnsMap.computeIfAbsent(str, new d(this, sQLiteDatabase, str, 5));
    }

    public final boolean containsColumn(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        return computeColumnNames(sQLiteDatabase, str).contains(str2);
    }

    public final int count(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("select count(_id) from " + str, (String[]) null);
        try {
            if (rawQuery.moveToFirst()) {
                int i2 = rawQuery.getInt(0);
                rawQuery.close();
                return i2;
            }
            rawQuery.close();
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String createIndexSql(String str, String str2, String str3) {
        return C0212a.p(C0086a.q("CREATE INDEX IF NOT EXISTS ", str, " ON ", str2, "("), str3, ");");
    }

    public abstract void createTablesOnTransaction(SQLiteDatabase sQLiteDatabase);

    public final void dumpLog(SQLiteDatabase sQLiteDatabase, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("__category", 3);
        contentValues.put("__timestamp", TimeUtil.getIsoLocalDateTime(System.currentTimeMillis()));
        contentValues.put("__log", str);
        sQLiteDatabase.insert("log", (String) null, contentValues);
    }

    public final List<String> getColumnNames(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery;
        ArrayList arrayList = new ArrayList();
        try {
            rawQuery = sQLiteDatabase.rawQuery("pragma table_info(" + str + ")", (String[]) null);
            if (rawQuery.moveToFirst()) {
                int columnIndex = rawQuery.getColumnIndex("name");
                do {
                    arrayList.add(rawQuery.getString(columnIndex));
                } while (rawQuery.moveToNext());
            }
            rawQuery.close();
            return arrayList;
        } catch (Exception e) {
            String str2 = this.TAG;
            Log.e(str2, "getColumnNames" + Logger.v(str) + " failed. e=" + e.getMessage());
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public List<String> listOfIndex(SQLiteDatabase sQLiteDatabase, String str) {
        String m = C0212a.m("select name from sqlite_master where type='index' and tbl_name='", str, "' and name not like 'sqlite_%'");
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = sQLiteDatabase.rawQuery(m, (String[]) null);
        try {
            if (rawQuery.moveToFirst()) {
                do {
                    arrayList.add(rawQuery.getString(0));
                } while (rawQuery.moveToNext());
            }
            rawQuery.close();
            return arrayList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public final boolean needUpgrade(int i2, int i7, int i8) {
        if (i2 >= i8 || i7 < i8) {
            return false;
        }
        return true;
    }

    public final boolean restore(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        SQLiteDatabase openDatabase;
        Throwable th;
        String str4;
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        String str5 = str;
        String str6 = str2;
        String str7 = str3;
        long currentTimeMillis = System.currentTimeMillis();
        SQLiteDatabase.OpenParams build = new SQLiteDatabase.OpenParams.Builder().setOpenFlags(1).setJournalMode("OFF").build();
        File file = new File(str6);
        try {
            openDatabase = SQLiteDatabase.openDatabase(file, build);
            List<String> columnNames = getColumnNames(openDatabase, str5);
            List<String> columnNames2 = getColumnNames(sQLiteDatabase, str);
            columnNames2.remove("_id");
            long j2 = currentTimeMillis;
            columnNames.removeIf(new o(new HashSet(columnNames2), 2));
            if (!columnNames.isEmpty()) {
                int count = count(sQLiteDatabase, str);
                String join = String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, columnNames);
                sQLiteDatabase2.execSQL("ATTACH DATABASE '" + str6 + "' AS backup");
                StringBuilder sb2 = new StringBuilder("INSERT OR IGNORE INTO ");
                sb2.append(str5);
                sb2.append(" (");
                sb2.append(join);
                sb2.append(") SELECT ");
                sb2.append(join);
                sb2.append(" FROM backup.");
                sb2.append(str5);
                if ("image/*".equalsIgnoreCase(str7)) {
                    str4 = " where media_type=1";
                } else if ("video/*".equalsIgnoreCase(str7)) {
                    str4 = " where media_type=3";
                } else {
                    str4 = "";
                }
                sb2.append(str4);
                sQLiteDatabase2.execSQL(sb2.toString());
                sQLiteDatabase2.execSQL("DETACH DATABASE backup");
                String str8 = this.TAG;
                Log.d(str8, "restore" + Logger.vt(Logger.getEncodedString(file.getName()), Integer.valueOf(count), Integer.valueOf(count(sQLiteDatabase, str)), Long.valueOf(j2)) + ((String) Optional.ofNullable(buildBnRDebugMessage(openDatabase, sQLiteDatabase2)).map(new q8.a(0)).orElse("")) + "\nsrc=" + Logger.getEncodedString((Object) columnNames) + "\ndst=" + Logger.getEncodedString((Object) columnNames2));
                if (openDatabase == null) {
                    return true;
                }
                openDatabase.close();
                return true;
            }
            String str9 = this.TAG;
            Log.e(str9, "restore skip. invalid column " + Logger.getEncodedString((Object) columnNames2));
            if (openDatabase == null) {
                return false;
            }
            openDatabase.close();
            return false;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("restore failed. e="), this.TAG);
            return false;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public abstract void upgradeTablesOnTransaction(SQLiteDatabase sQLiteDatabase, int i2, int i7);

    public void recreateTableOnTransaction(SQLiteDatabase sQLiteDatabase, String str) {
    }
}
