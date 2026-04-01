package androidx.sqlite.db.framework;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteStatement;
import android.os.CancellationSignal;
import android.text.TextUtils;
import android.util.Pair;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteCompat$Api16Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import j0.C0218a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0011\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 :2\u00020\u0001:\u0001:B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000f\u0010\rJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0010\u0010\rJ\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0014\u0010\u0016J\u0017\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0014\u0010\u0018J!\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0017¢\u0006\u0004\b\u0014\u0010\u001bJE\u0010%\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001f2\b\u0010!\u001a\u0004\u0018\u00010\u00062\u0012\u0010$\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010#\u0018\u00010\"H\u0016¢\u0006\u0004\b%\u0010&J\u0017\u0010'\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b'\u0010(J)\u0010'\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00062\u0010\u0010)\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010#0\"H\u0016¢\u0006\u0004\b'\u0010*J\u000f\u0010+\u001a\u00020\u000bH\u0016¢\u0006\u0004\b+\u0010\rJ\u0015\u0010-\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u0002¢\u0006\u0004\b-\u0010.R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010/R\u0014\u00100\u001a\u00020\u00118VX\u0004¢\u0006\u0006\u001a\u0004\b0\u0010\u0013R\u0016\u00103\u001a\u0004\u0018\u00010\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0014\u00104\u001a\u00020\u00118WX\u0004¢\u0006\u0006\u001a\u0004\b4\u0010\u0013R(\u00109\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000606\u0018\u0001058VX\u0004¢\u0006\u0006\u001a\u0004\b7\u00108¨\u0006;"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "Landroid/database/sqlite/SQLiteDatabase;", "delegate", "<init>", "(Landroid/database/sqlite/SQLiteDatabase;)V", "", "sql", "Landroidx/sqlite/db/SupportSQLiteStatement;", "compileStatement", "(Ljava/lang/String;)Landroidx/sqlite/db/SupportSQLiteStatement;", "Lme/x;", "beginTransaction", "()V", "beginTransactionNonExclusive", "endTransaction", "setTransactionSuccessful", "", "inTransaction", "()Z", "query", "Landroid/database/Cursor;", "(Ljava/lang/String;)Landroid/database/Cursor;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "(Landroidx/sqlite/db/SupportSQLiteQuery;)Landroid/database/Cursor;", "Landroid/os/CancellationSignal;", "cancellationSignal", "(Landroidx/sqlite/db/SupportSQLiteQuery;Landroid/os/CancellationSignal;)Landroid/database/Cursor;", "table", "", "conflictAlgorithm", "Landroid/content/ContentValues;", "values", "whereClause", "", "", "whereArgs", "update", "(Ljava/lang/String;ILandroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/Object;)I", "execSQL", "(Ljava/lang/String;)V", "bindArgs", "(Ljava/lang/String;[Ljava/lang/Object;)V", "close", "sqLiteDatabase", "isDelegate", "(Landroid/database/sqlite/SQLiteDatabase;)Z", "Landroid/database/sqlite/SQLiteDatabase;", "isOpen", "getPath", "()Ljava/lang/String;", "path", "isWriteAheadLoggingEnabled", "", "Landroid/util/Pair;", "getAttachedDbs", "()Ljava/util/List;", "attachedDbs", "Companion", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FrameworkSQLiteDatabase implements SupportSQLiteDatabase {
    private static final String[] CONFLICT_VALUES = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    public static final Companion Companion = new Companion((e) null);
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private final SQLiteDatabase delegate;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0018\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteDatabase$Companion;", "", "()V", "CONFLICT_VALUES", "", "", "[Ljava/lang/String;", "EMPTY_STRING_ARRAY", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public FrameworkSQLiteDatabase(SQLiteDatabase sQLiteDatabase) {
        j.e(sQLiteDatabase, "delegate");
        this.delegate = sQLiteDatabase;
    }

    /* access modifiers changed from: private */
    public static final Cursor query$lambda$0(Ae.e eVar, SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        j.e(eVar, "$tmp0");
        return (Cursor) eVar.invoke(sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
    }

    /* access modifiers changed from: private */
    public static final Cursor query$lambda$1(SupportSQLiteQuery supportSQLiteQuery, SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        j.e(supportSQLiteQuery, "$query");
        j.b(sQLiteQuery);
        supportSQLiteQuery.bindTo(new FrameworkSQLiteProgram(sQLiteQuery));
        return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
    }

    public void beginTransaction() {
        this.delegate.beginTransaction();
    }

    public void beginTransactionNonExclusive() {
        this.delegate.beginTransactionNonExclusive();
    }

    public void close() {
        this.delegate.close();
    }

    public SupportSQLiteStatement compileStatement(String str) {
        j.e(str, "sql");
        SQLiteStatement compileStatement = this.delegate.compileStatement(str);
        j.d(compileStatement, "delegate.compileStatement(sql)");
        return new FrameworkSQLiteStatement(compileStatement);
    }

    public void endTransaction() {
        this.delegate.endTransaction();
    }

    public void execSQL(String str) {
        j.e(str, "sql");
        this.delegate.execSQL(str);
    }

    public List<Pair<String, String>> getAttachedDbs() {
        return this.delegate.getAttachedDbs();
    }

    public String getPath() {
        return this.delegate.getPath();
    }

    public boolean inTransaction() {
        return this.delegate.inTransaction();
    }

    public final boolean isDelegate(SQLiteDatabase sQLiteDatabase) {
        j.e(sQLiteDatabase, "sqLiteDatabase");
        return j.a(this.delegate, sQLiteDatabase);
    }

    public boolean isOpen() {
        return this.delegate.isOpen();
    }

    public boolean isWriteAheadLoggingEnabled() {
        return SupportSQLiteCompat$Api16Impl.isWriteAheadLoggingEnabled(this.delegate);
    }

    public Cursor query(String str) {
        j.e(str, Contract.QUERY);
        return query((SupportSQLiteQuery) new SimpleSQLiteQuery(str));
    }

    public void setTransactionSuccessful() {
        this.delegate.setTransactionSuccessful();
    }

    public int update(String str, int i2, ContentValues contentValues, String str2, Object[] objArr) {
        int i7;
        String str3;
        j.e(str, "table");
        j.e(contentValues, StateHandler.VALUES);
        if (contentValues.size() != 0) {
            int size = contentValues.size();
            if (objArr == null) {
                i7 = size;
            } else {
                i7 = objArr.length + size;
            }
            Object[] objArr2 = new Object[i7];
            StringBuilder sb2 = new StringBuilder("UPDATE ");
            sb2.append(CONFLICT_VALUES[i2]);
            sb2.append(str);
            sb2.append(" SET ");
            int i8 = 0;
            for (String next : contentValues.keySet()) {
                if (i8 > 0) {
                    str3 = GlobalPostProcInternalPPInterface.SPLIT_REGEX;
                } else {
                    str3 = "";
                }
                sb2.append(str3);
                sb2.append(next);
                objArr2[i8] = contentValues.get(next);
                sb2.append("=?");
                i8++;
            }
            if (objArr != null) {
                for (int i10 = size; i10 < i7; i10++) {
                    objArr2[i10] = objArr[i10 - size];
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                sb2.append(" WHERE ");
                sb2.append(str2);
            }
            String sb3 = sb2.toString();
            j.d(sb3, "StringBuilder().apply(builderAction).toString()");
            SupportSQLiteStatement compileStatement = compileStatement(sb3);
            SimpleSQLiteQuery.Companion.bind(compileStatement, objArr2);
            return compileStatement.executeUpdateDelete();
        }
        throw new IllegalArgumentException("Empty values");
    }

    public void execSQL(String str, Object[] objArr) {
        j.e(str, "sql");
        j.e(objArr, "bindArgs");
        this.delegate.execSQL(str, objArr);
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery) {
        j.e(supportSQLiteQuery, Contract.QUERY);
        Cursor rawQueryWithFactory = this.delegate.rawQueryWithFactory(new C0218a(1, new FrameworkSQLiteDatabase$query$cursorFactory$1(supportSQLiteQuery)), supportSQLiteQuery.getSql(), EMPTY_STRING_ARRAY, (String) null);
        j.d(rawQueryWithFactory, "delegate.rawQueryWithFac…EMPTY_STRING_ARRAY, null)");
        return rawQueryWithFactory;
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        j.e(supportSQLiteQuery, Contract.QUERY);
        SQLiteDatabase sQLiteDatabase = this.delegate;
        String sql = supportSQLiteQuery.getSql();
        String[] strArr = EMPTY_STRING_ARRAY;
        j.b(cancellationSignal);
        return SupportSQLiteCompat$Api16Impl.rawQueryWithFactory(sQLiteDatabase, sql, strArr, (String) null, cancellationSignal, new C0218a(0, supportSQLiteQuery));
    }
}
