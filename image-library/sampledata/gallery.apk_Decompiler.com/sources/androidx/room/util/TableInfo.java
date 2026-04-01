package androidx.room.util;

import Tf.n;
import Tf.v;
import androidx.room.Index$Order;
import androidx.sqlite.db.SupportSQLiteDatabase;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0005\u001b\u001a\u001c\u001d\u001eBC\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0017R \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0018R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0019R\u001c\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00078\u0006X\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u0019¨\u0006\u001f"}, d2 = {"Landroidx/room/util/TableInfo;", "", "", "name", "", "Landroidx/room/util/TableInfo$Column;", "columns", "", "Landroidx/room/util/TableInfo$ForeignKey;", "foreignKeys", "Landroidx/room/util/TableInfo$Index;", "indices", "<init>", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Set;Ljava/util/Set;)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "Ljava/util/Map;", "Ljava/util/Set;", "Companion", "Column", "ForeignKey", "ForeignKeyWithSequence", "Index", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TableInfo {
    public static final Companion Companion = new Companion((e) null);
    public final Map<String, Column> columns;
    public final Set<ForeignKey> foreignKeys;
    public final Set<Index> indices;
    public final String name;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\r\u001a\u00020\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0010\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u0014\u0010\u0004\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0016R\u0014\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0017R\u0014\u0010\b\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0018R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006X\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0016R\u0014\u0010\n\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u00078\u0006X\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u0018\u0012\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"Landroidx/room/util/TableInfo$Column;", "", "", "name", "type", "", "notNull", "", "primaryKeyPosition", "defaultValue", "createdFrom", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/String;I)V", "findAffinity", "(Ljava/lang/String;)I", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "Z", "I", "affinity", "getAffinity$annotations", "()V", "Companion", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Column {
        public static final Companion Companion = new Companion((e) null);
        public final int affinity;
        public final int createdFrom;
        public final String defaultValue;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001a\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\t"}, d2 = {"Landroidx/room/util/TableInfo$Column$Companion;", "", "()V", "containsSurroundingParenthesis", "", "current", "", "defaultValueEquals", "other", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            private final boolean containsSurroundingParenthesis(String str) {
                if (str.length() == 0) {
                    return false;
                }
                int i2 = 0;
                int i7 = 0;
                int i8 = 0;
                while (i2 < str.length()) {
                    char charAt = str.charAt(i2);
                    int i10 = i8 + 1;
                    if (i8 == 0 && charAt != '(') {
                        return false;
                    }
                    if (charAt == '(') {
                        i7++;
                    } else if (charAt == ')' && i7 - 1 == 0 && i8 != str.length() - 1) {
                        return false;
                    }
                    i2++;
                    i8 = i10;
                }
                if (i7 == 0) {
                    return true;
                }
                return false;
            }

            public final boolean defaultValueEquals(String str, String str2) {
                j.e(str, "current");
                if (str.equals(str2)) {
                    return true;
                }
                if (!containsSurroundingParenthesis(str)) {
                    return false;
                }
                String substring = str.substring(1, str.length() - 1);
                j.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                return j.a(n.R0(substring).toString(), str2);
            }

            private Companion() {
            }
        }

        public Column(String str, String str2, boolean z, int i2, String str3, int i7) {
            j.e(str, "name");
            j.e(str2, "type");
            this.name = str;
            this.type = str2;
            this.notNull = z;
            this.primaryKeyPosition = i2;
            this.defaultValue = str3;
            this.createdFrom = i7;
            this.affinity = findAffinity(str2);
        }

        private final int findAffinity(String str) {
            if (str == null) {
                return 5;
            }
            Locale locale = Locale.US;
            j.d(locale, "US");
            String upperCase = str.toUpperCase(locale);
            j.d(upperCase, "this as java.lang.String).toUpperCase(locale)");
            if (n.u0(upperCase, "INT")) {
                return 3;
            }
            if (n.u0(upperCase, "CHAR") || n.u0(upperCase, "CLOB") || n.u0(upperCase, "TEXT")) {
                return 2;
            }
            if (n.u0(upperCase, "BLOB")) {
                return 5;
            }
            if (n.u0(upperCase, "REAL") || n.u0(upperCase, "FLOA") || n.u0(upperCase, "DOUB")) {
                return 4;
            }
            return 1;
        }

        public boolean equals(Object obj) {
            String str;
            String str2;
            String str3;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Column) || this.primaryKeyPosition != ((Column) obj).primaryKeyPosition) {
                return false;
            }
            Column column = (Column) obj;
            if (!j.a(this.name, column.name) || this.notNull != column.notNull) {
                return false;
            }
            if (this.createdFrom == 1 && column.createdFrom == 2 && (str3 = this.defaultValue) != null && !Companion.defaultValueEquals(str3, column.defaultValue)) {
                return false;
            }
            if (this.createdFrom == 2 && column.createdFrom == 1 && (str2 = column.defaultValue) != null && !Companion.defaultValueEquals(str2, this.defaultValue)) {
                return false;
            }
            int i2 = this.createdFrom;
            if ((i2 == 0 || i2 != column.createdFrom || ((str = this.defaultValue) == null ? column.defaultValue == null : Companion.defaultValueEquals(str, column.defaultValue))) && this.affinity == column.affinity) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i2;
            int hashCode = ((this.name.hashCode() * 31) + this.affinity) * 31;
            if (this.notNull) {
                i2 = 1231;
            } else {
                i2 = 1237;
            }
            return ((hashCode + i2) * 31) + this.primaryKeyPosition;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Column{name='");
            sb2.append(this.name);
            sb2.append("', type='");
            sb2.append(this.type);
            sb2.append("', affinity='");
            sb2.append(this.affinity);
            sb2.append("', notNull=");
            sb2.append(this.notNull);
            sb2.append(", primaryKeyPosition=");
            sb2.append(this.primaryKeyPosition);
            sb2.append(", defaultValue='");
            String str = this.defaultValue;
            if (str == null) {
                str = "undefined";
            }
            return C0212a.p(sb2, str, "'}");
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/room/util/TableInfo$Companion;", "", "()V", "CREATED_FROM_DATABASE", "", "CREATED_FROM_ENTITY", "CREATED_FROM_UNKNOWN", "read", "Landroidx/room/util/TableInfo;", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "tableName", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final TableInfo read(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
            j.e(supportSQLiteDatabase, "database");
            j.e(str, "tableName");
            return TableInfoKt.readTableInfo(supportSQLiteDatabase, str);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00078\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00078\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/room/util/TableInfo$ForeignKey;", "", "referenceTable", "", "onDelete", "onUpdate", "columnNames", "", "referenceColumnNames", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "equals", "", "other", "hashCode", "", "toString", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ForeignKey {
        public final List<String> columnNames;
        public final String onDelete;
        public final String onUpdate;
        public final List<String> referenceColumnNames;
        public final String referenceTable;

        public ForeignKey(String str, String str2, String str3, List<String> list, List<String> list2) {
            j.e(str, "referenceTable");
            j.e(str2, "onDelete");
            j.e(str3, "onUpdate");
            j.e(list, "columnNames");
            j.e(list2, "referenceColumnNames");
            this.referenceTable = str;
            this.onDelete = str2;
            this.onUpdate = str3;
            this.columnNames = list;
            this.referenceColumnNames = list2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ForeignKey)) {
                return false;
            }
            ForeignKey foreignKey = (ForeignKey) obj;
            if (j.a(this.referenceTable, foreignKey.referenceTable) && j.a(this.onDelete, foreignKey.onDelete) && j.a(this.onUpdate, foreignKey.onUpdate) && j.a(this.columnNames, foreignKey.columnNames)) {
                return j.a(this.referenceColumnNames, foreignKey.referenceColumnNames);
            }
            return false;
        }

        public int hashCode() {
            return this.referenceColumnNames.hashCode() + C0212a.f(this.columnNames, C0212a.d(C0212a.d(this.referenceTable.hashCode() * 31, 31, this.onDelete), 31, this.onUpdate), 31);
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("ForeignKey{referenceTable='");
            sb2.append(this.referenceTable);
            sb2.append("', onDelete='");
            sb2.append(this.onDelete);
            sb2.append(" +', onUpdate='");
            sb2.append(this.onUpdate);
            sb2.append("', columnNames=");
            sb2.append(this.columnNames);
            sb2.append(", referenceColumnNames=");
            return C0212a.r(sb2, this.referenceColumnNames, '}');
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\r\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0011\u001a\u0004\b\u0014\u0010\u0013¨\u0006\u0015"}, d2 = {"Landroidx/room/util/TableInfo$ForeignKeyWithSequence;", "", "", "id", "sequence", "", "from", "to", "<init>", "(IILjava/lang/String;Ljava/lang/String;)V", "other", "compareTo", "(Landroidx/room/util/TableInfo$ForeignKeyWithSequence;)I", "I", "getId", "()I", "getSequence", "Ljava/lang/String;", "getFrom", "()Ljava/lang/String;", "getTo", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ForeignKeyWithSequence implements Comparable<ForeignKeyWithSequence> {
        private final String from;
        private final int id;
        private final int sequence;
        private final String to;

        public ForeignKeyWithSequence(int i2, int i7, String str, String str2) {
            j.e(str, "from");
            j.e(str2, "to");
            this.id = i2;
            this.sequence = i7;
            this.from = str;
            this.to = str2;
        }

        public final String getFrom() {
            return this.from;
        }

        public final int getId() {
            return this.id;
        }

        public final String getTo() {
            return this.to;
        }

        public int compareTo(ForeignKeyWithSequence foreignKeyWithSequence) {
            j.e(foreignKeyWithSequence, "other");
            int i2 = this.id - foreignKeyWithSequence.id;
            return i2 == 0 ? this.sequence - foreignKeyWithSequence.sequence : i2;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\t\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\f\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0013R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0014R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00068\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0015R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0006\n\u0004\b\b\u0010\u0015¨\u0006\u0017"}, d2 = {"Landroidx/room/util/TableInfo$Index;", "", "", "name", "", "unique", "", "columns", "orders", "<init>", "(Ljava/lang/String;ZLjava/util/List;Ljava/util/List;)V", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "Z", "Ljava/util/List;", "Companion", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Index {
        public static final Companion Companion = new Companion((e) null);
        public final List<String> columns;
        public final String name;
        public List<String> orders;
        public final boolean unique;

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/room/util/TableInfo$Index$Companion;", "", "()V", "DEFAULT_PREFIX", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Companion {
            public /* synthetic */ Companion(e eVar) {
                this();
            }

            private Companion() {
            }
        }

        public Index(String str, boolean z, List<String> list, List<String> list2) {
            j.e(str, "name");
            j.e(list, "columns");
            j.e(list2, "orders");
            this.name = str;
            this.unique = z;
            this.columns = list;
            this.orders = list2;
            Collection collection = list2;
            boolean isEmpty = collection.isEmpty();
            ArrayList arrayList = collection;
            if (isEmpty) {
                int size = list.size();
                ArrayList arrayList2 = new ArrayList(size);
                for (int i2 = 0; i2 < size; i2++) {
                    arrayList2.add(Index$Order.ASC.name());
                }
                arrayList = arrayList2;
            }
            this.orders = (List) arrayList;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Index)) {
                return false;
            }
            Index index = (Index) obj;
            if (this.unique != index.unique || !j.a(this.columns, index.columns) || !j.a(this.orders, index.orders)) {
                return false;
            }
            if (v.t0(this.name, "index_")) {
                return v.t0(index.name, "index_");
            }
            return j.a(this.name, index.name);
        }

        public int hashCode() {
            int i2;
            if (v.t0(this.name, "index_")) {
                i2 = -1184239155;
            } else {
                i2 = this.name.hashCode();
            }
            return this.orders.hashCode() + C0212a.f(this.columns, ((i2 * 31) + (this.unique ? 1 : 0)) * 31, 31);
        }

        public String toString() {
            return "Index{name='" + this.name + "', unique=" + this.unique + ", columns=" + this.columns + ", orders=" + this.orders + "'}";
        }
    }

    public TableInfo(String str, Map<String, Column> map, Set<ForeignKey> set, Set<Index> set2) {
        j.e(str, "name");
        j.e(map, "columns");
        j.e(set, "foreignKeys");
        this.name = str;
        this.columns = map;
        this.foreignKeys = set;
        this.indices = set2;
    }

    public static final TableInfo read(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        return Companion.read(supportSQLiteDatabase, str);
    }

    public boolean equals(Object obj) {
        Set<Index> set;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TableInfo)) {
            return false;
        }
        TableInfo tableInfo = (TableInfo) obj;
        if (!j.a(this.name, tableInfo.name) || !j.a(this.columns, tableInfo.columns) || !j.a(this.foreignKeys, tableInfo.foreignKeys)) {
            return false;
        }
        Set<Index> set2 = this.indices;
        if (set2 == null || (set = tableInfo.indices) == null) {
            return true;
        }
        return j.a(set2, set);
    }

    public int hashCode() {
        int hashCode = this.columns.hashCode();
        return this.foreignKeys.hashCode() + ((hashCode + (this.name.hashCode() * 31)) * 31);
    }

    public String toString() {
        return "TableInfo{name='" + this.name + "', columns=" + this.columns + ", foreignKeys=" + this.foreignKeys + ", indices=" + this.indices + '}';
    }
}
