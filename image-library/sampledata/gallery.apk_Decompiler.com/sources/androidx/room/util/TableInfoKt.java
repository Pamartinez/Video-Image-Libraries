package androidx.room.util;

import B1.a;
import android.database.Cursor;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1203u;
import o1.C0246a;
import oe.C1214c;
import oe.C1217f;
import oe.C1220i;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0002\u001a\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0002\u001a\"\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a \u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0002\u001a\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0000¨\u0006\u0017"}, d2 = {"readColumns", "", "", "Landroidx/room/util/TableInfo$Column;", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "tableName", "readForeignKeyFieldMappings", "", "Landroidx/room/util/TableInfo$ForeignKeyWithSequence;", "cursor", "Landroid/database/Cursor;", "readForeignKeys", "", "Landroidx/room/util/TableInfo$ForeignKey;", "readIndex", "Landroidx/room/util/TableInfo$Index;", "name", "unique", "", "readIndices", "readTableInfo", "Landroidx/room/util/TableInfo;", "room-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TableInfoKt {
    private static final Map<String, TableInfo.Column> readColumns(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Throwable th;
        boolean z;
        Closeable query = supportSQLiteDatabase.query("PRAGMA table_info(`" + str + "`)");
        try {
            Cursor cursor = (Cursor) query;
            if (cursor.getColumnCount() <= 0) {
                C1203u uVar = C1203u.d;
                a.g(query, (Throwable) null);
                return uVar;
            }
            int columnIndex = cursor.getColumnIndex("name");
            int columnIndex2 = cursor.getColumnIndex("type");
            int columnIndex3 = cursor.getColumnIndex("notnull");
            int columnIndex4 = cursor.getColumnIndex("pk");
            int columnIndex5 = cursor.getColumnIndex("dflt_value");
            C1217f fVar = new C1217f();
            while (cursor.moveToNext()) {
                String string = cursor.getString(columnIndex);
                String string2 = cursor.getString(columnIndex2);
                if (cursor.getInt(columnIndex3) != 0) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z3 = z;
                int i2 = cursor.getInt(columnIndex4);
                String string3 = cursor.getString(columnIndex5);
                j.d(string, "name");
                j.d(string2, "type");
                fVar.put(string, new TableInfo.Column(string, string2, z3, i2, string3, 2));
            }
            C1217f b = fVar.b();
            a.g(query, (Throwable) null);
            return b;
        } catch (Throwable th2) {
            a.g(query, th);
            throw th2;
        }
    }

    private static final List<TableInfo.ForeignKeyWithSequence> readForeignKeyFieldMappings(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("id");
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex("from");
        int columnIndex4 = cursor.getColumnIndex("to");
        C1214c R = C0246a.R();
        while (cursor.moveToNext()) {
            int i2 = cursor.getInt(columnIndex);
            int i7 = cursor.getInt(columnIndex2);
            String string = cursor.getString(columnIndex3);
            j.d(string, "cursor.getString(fromColumnIndex)");
            String string2 = cursor.getString(columnIndex4);
            j.d(string2, "cursor.getString(toColumnIndex)");
            R.add(new TableInfo.ForeignKeyWithSequence(i2, i7, string, string2));
        }
        return C1194l.f1(C0246a.K(R));
    }

    private static final Set<TableInfo.ForeignKey> readForeignKeys(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Throwable th;
        Closeable query = supportSQLiteDatabase.query("PRAGMA foreign_key_list(`" + str + "`)");
        try {
            Cursor cursor = (Cursor) query;
            int columnIndex = cursor.getColumnIndex("id");
            int columnIndex2 = cursor.getColumnIndex("seq");
            int columnIndex3 = cursor.getColumnIndex("table");
            int columnIndex4 = cursor.getColumnIndex("on_delete");
            int columnIndex5 = cursor.getColumnIndex("on_update");
            List<TableInfo.ForeignKeyWithSequence> readForeignKeyFieldMappings = readForeignKeyFieldMappings(cursor);
            cursor.moveToPosition(-1);
            C1220i iVar = new C1220i();
            while (cursor.moveToNext()) {
                if (cursor.getInt(columnIndex2) == 0) {
                    int i2 = cursor.getInt(columnIndex);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    for (Object next : readForeignKeyFieldMappings) {
                        if (((TableInfo.ForeignKeyWithSequence) next).getId() == i2) {
                            arrayList3.add(next);
                        }
                    }
                    Iterator it = arrayList3.iterator();
                    while (it.hasNext()) {
                        TableInfo.ForeignKeyWithSequence foreignKeyWithSequence = (TableInfo.ForeignKeyWithSequence) it.next();
                        arrayList.add(foreignKeyWithSequence.getFrom());
                        arrayList2.add(foreignKeyWithSequence.getTo());
                    }
                    String string = cursor.getString(columnIndex3);
                    j.d(string, "cursor.getString(tableColumnIndex)");
                    String string2 = cursor.getString(columnIndex4);
                    j.d(string2, "cursor.getString(onDeleteColumnIndex)");
                    String string3 = cursor.getString(columnIndex5);
                    j.d(string3, "cursor.getString(onUpdateColumnIndex)");
                    iVar.add(new TableInfo.ForeignKey(string, string2, string3, arrayList, arrayList2));
                }
            }
            C1220i d = a.d(iVar);
            a.g(query, (Throwable) null);
            return d;
        } catch (Throwable th2) {
            a.g(query, th);
            throw th2;
        }
    }

    private static final TableInfo.Index readIndex(SupportSQLiteDatabase supportSQLiteDatabase, String str, boolean z) {
        String str2;
        Closeable query = supportSQLiteDatabase.query("PRAGMA index_xinfo(`" + str + "`)");
        try {
            Cursor cursor = (Cursor) query;
            int columnIndex = cursor.getColumnIndex("seqno");
            int columnIndex2 = cursor.getColumnIndex("cid");
            int columnIndex3 = cursor.getColumnIndex("name");
            int columnIndex4 = cursor.getColumnIndex("desc");
            if (!(columnIndex == -1 || columnIndex2 == -1 || columnIndex3 == -1)) {
                if (columnIndex4 != -1) {
                    TreeMap treeMap = new TreeMap();
                    TreeMap treeMap2 = new TreeMap();
                    while (cursor.moveToNext()) {
                        if (cursor.getInt(columnIndex2) >= 0) {
                            int i2 = cursor.getInt(columnIndex);
                            String string = cursor.getString(columnIndex3);
                            if (cursor.getInt(columnIndex4) > 0) {
                                str2 = "DESC";
                            } else {
                                str2 = "ASC";
                            }
                            Integer valueOf = Integer.valueOf(i2);
                            j.d(string, "columnName");
                            treeMap.put(valueOf, string);
                            treeMap2.put(Integer.valueOf(i2), str2);
                        }
                    }
                    Collection values = treeMap.values();
                    j.d(values, "columnsMap.values");
                    List k12 = C1194l.k1(values);
                    Collection values2 = treeMap2.values();
                    j.d(values2, "ordersMap.values");
                    TableInfo.Index index = new TableInfo.Index(str, z, k12, C1194l.k1(values2));
                    a.g(query, (Throwable) null);
                    return index;
                }
            }
            a.g(query, (Throwable) null);
            return null;
        } catch (Throwable th) {
            a.g(query, th);
            throw th;
        }
    }

    private static final Set<TableInfo.Index> readIndices(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Closeable query = supportSQLiteDatabase.query("PRAGMA index_list(`" + str + "`)");
        try {
            Cursor cursor = (Cursor) query;
            int columnIndex = cursor.getColumnIndex("name");
            int columnIndex2 = cursor.getColumnIndex("origin");
            int columnIndex3 = cursor.getColumnIndex("unique");
            if (!(columnIndex == -1 || columnIndex2 == -1)) {
                if (columnIndex3 != -1) {
                    C1220i iVar = new C1220i();
                    while (cursor.moveToNext()) {
                        if ("c".equals(cursor.getString(columnIndex2))) {
                            String string = cursor.getString(columnIndex);
                            boolean z = true;
                            if (cursor.getInt(columnIndex3) != 1) {
                                z = false;
                            }
                            j.d(string, "name");
                            TableInfo.Index readIndex = readIndex(supportSQLiteDatabase, string, z);
                            if (readIndex == null) {
                                a.g(query, (Throwable) null);
                                return null;
                            }
                            iVar.add(readIndex);
                        }
                    }
                    C1220i d = a.d(iVar);
                    a.g(query, (Throwable) null);
                    return d;
                }
            }
            a.g(query, (Throwable) null);
            return null;
        } catch (Throwable th) {
            a.g(query, th);
            throw th;
        }
    }

    public static final TableInfo readTableInfo(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        j.e(supportSQLiteDatabase, "database");
        j.e(str, "tableName");
        return new TableInfo(str, readColumns(supportSQLiteDatabase, str), readForeignKeys(supportSQLiteDatabase, str), readIndices(supportSQLiteDatabase, str));
    }
}
