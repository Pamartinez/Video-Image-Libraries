package androidx.sqlite.db;

import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000fR \u0010\u0006\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Landroidx/sqlite/db/SimpleSQLiteQuery;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "", "query", "", "", "bindArgs", "<init>", "(Ljava/lang/String;[Ljava/lang/Object;)V", "(Ljava/lang/String;)V", "Landroidx/sqlite/db/SupportSQLiteProgram;", "statement", "Lme/x;", "bindTo", "(Landroidx/sqlite/db/SupportSQLiteProgram;)V", "Ljava/lang/String;", "[Ljava/lang/Object;", "getSql", "()Ljava/lang/String;", "sql", "Companion", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SimpleSQLiteQuery implements SupportSQLiteQuery {
    public static final Companion Companion = new Companion((e) null);
    private final Object[] bindArgs;
    private final String query;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\n\u0010\u000bJ+\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\r\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010\fH\u0007¢\u0006\u0004\b\n\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/sqlite/db/SimpleSQLiteQuery$Companion;", "", "<init>", "()V", "Landroidx/sqlite/db/SupportSQLiteProgram;", "statement", "", "index", "arg", "Lme/x;", "bind", "(Landroidx/sqlite/db/SupportSQLiteProgram;ILjava/lang/Object;)V", "", "bindArgs", "(Landroidx/sqlite/db/SupportSQLiteProgram;[Ljava/lang/Object;)V", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final void bind(SupportSQLiteProgram supportSQLiteProgram, Object[] objArr) {
            j.e(supportSQLiteProgram, "statement");
            if (objArr != null) {
                int length = objArr.length;
                int i2 = 0;
                while (i2 < length) {
                    Object obj = objArr[i2];
                    i2++;
                    bind(supportSQLiteProgram, i2, obj);
                }
            }
        }

        private Companion() {
        }

        private final void bind(SupportSQLiteProgram supportSQLiteProgram, int i2, Object obj) {
            if (obj == null) {
                supportSQLiteProgram.bindNull(i2);
            } else if (obj instanceof byte[]) {
                supportSQLiteProgram.bindBlob(i2, (byte[]) obj);
            } else if (obj instanceof Float) {
                supportSQLiteProgram.bindDouble(i2, (double) ((Number) obj).floatValue());
            } else if (obj instanceof Double) {
                supportSQLiteProgram.bindDouble(i2, ((Number) obj).doubleValue());
            } else if (obj instanceof Long) {
                supportSQLiteProgram.bindLong(i2, ((Number) obj).longValue());
            } else if (obj instanceof Integer) {
                supportSQLiteProgram.bindLong(i2, (long) ((Number) obj).intValue());
            } else if (obj instanceof Short) {
                supportSQLiteProgram.bindLong(i2, (long) ((Number) obj).shortValue());
            } else if (obj instanceof Byte) {
                supportSQLiteProgram.bindLong(i2, (long) ((Number) obj).byteValue());
            } else if (obj instanceof String) {
                supportSQLiteProgram.bindString(i2, (String) obj);
            } else if (obj instanceof Boolean) {
                supportSQLiteProgram.bindLong(i2, ((Boolean) obj).booleanValue() ? 1 : 0);
            } else {
                throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i2 + " Supported types: Null, ByteArray, Float, Double, Long, Int, Short, Byte, String");
            }
        }
    }

    public SimpleSQLiteQuery(String str, Object[] objArr) {
        j.e(str, Contract.QUERY);
        this.query = str;
        this.bindArgs = objArr;
    }

    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        j.e(supportSQLiteProgram, "statement");
        Companion.bind(supportSQLiteProgram, this.bindArgs);
    }

    public String getSql() {
        return this.query;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SimpleSQLiteQuery(String str) {
        this(str, (Object[]) null);
        j.e(str, Contract.QUERY);
    }
}
