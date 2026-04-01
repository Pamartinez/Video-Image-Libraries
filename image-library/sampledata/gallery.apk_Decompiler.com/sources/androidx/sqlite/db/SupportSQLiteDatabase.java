package androidx.sqlite.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.util.Pair;
import java.io.Closeable;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H&¢\u0006\u0004\b\n\u0010\tJ\u000f\u0010\u000b\u001a\u00020\u0007H&¢\u0006\u0004\b\u000b\u0010\tJ\u000f\u0010\f\u001a\u00020\u0007H&¢\u0006\u0004\b\f\u0010\tJ\u000f\u0010\u000e\u001a\u00020\rH&¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0002H&¢\u0006\u0004\b\u0010\u0010\u0012J\u0017\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0013H&¢\u0006\u0004\b\u0010\u0010\u0014J!\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H'¢\u0006\u0004\b\u0010\u0010\u0017JE\u0010!\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00022\u0012\u0010 \u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001f\u0018\u00010\u001eH&¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b#\u0010$J)\u0010#\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0010\u0010%\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001f0\u001eH&¢\u0006\u0004\b#\u0010&R\u0014\u0010'\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u000fR\u0016\u0010*\u001a\u0004\u0018\u00010\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020\r8gX¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\u000fR(\u00100\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020-\u0018\u00010,8fX¦\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u00061À\u0006\u0001"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteDatabase;", "Ljava/io/Closeable;", "", "sql", "Landroidx/sqlite/db/SupportSQLiteStatement;", "compileStatement", "(Ljava/lang/String;)Landroidx/sqlite/db/SupportSQLiteStatement;", "Lme/x;", "beginTransaction", "()V", "beginTransactionNonExclusive", "endTransaction", "setTransactionSuccessful", "", "inTransaction", "()Z", "query", "Landroid/database/Cursor;", "(Ljava/lang/String;)Landroid/database/Cursor;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "(Landroidx/sqlite/db/SupportSQLiteQuery;)Landroid/database/Cursor;", "Landroid/os/CancellationSignal;", "cancellationSignal", "(Landroidx/sqlite/db/SupportSQLiteQuery;Landroid/os/CancellationSignal;)Landroid/database/Cursor;", "table", "", "conflictAlgorithm", "Landroid/content/ContentValues;", "values", "whereClause", "", "", "whereArgs", "update", "(Ljava/lang/String;ILandroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/Object;)I", "execSQL", "(Ljava/lang/String;)V", "bindArgs", "(Ljava/lang/String;[Ljava/lang/Object;)V", "isOpen", "getPath", "()Ljava/lang/String;", "path", "isWriteAheadLoggingEnabled", "", "Landroid/util/Pair;", "getAttachedDbs", "()Ljava/util/List;", "attachedDbs", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SupportSQLiteDatabase extends Closeable {
    void beginTransaction();

    void beginTransactionNonExclusive();

    SupportSQLiteStatement compileStatement(String str);

    void endTransaction();

    void execSQL(String str);

    void execSQL(String str, Object[] objArr);

    List<Pair<String, String>> getAttachedDbs();

    String getPath();

    boolean inTransaction();

    boolean isOpen();

    boolean isWriteAheadLoggingEnabled();

    Cursor query(SupportSQLiteQuery supportSQLiteQuery);

    Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal);

    Cursor query(String str);

    void setTransactionSuccessful();

    int update(String str, int i2, ContentValues contentValues, String str2, Object[] objArr);
}
