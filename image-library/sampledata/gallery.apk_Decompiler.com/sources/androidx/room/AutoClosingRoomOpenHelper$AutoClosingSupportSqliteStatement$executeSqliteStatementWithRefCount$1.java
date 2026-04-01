package androidx.room;

import Ae.b;
import androidx.room.AutoClosingRoomOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "T", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeSqliteStatementWithRefCount$1 extends k implements b {
    final /* synthetic */ b $block;
    final /* synthetic */ AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeSqliteStatementWithRefCount$1(AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement autoClosingSupportSqliteStatement, b bVar) {
        super(1);
        this.this$0 = autoClosingSupportSqliteStatement;
        this.$block = bVar;
    }

    public final T invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
        j.e(supportSQLiteDatabase, "db");
        SupportSQLiteStatement compileStatement = supportSQLiteDatabase.compileStatement(this.this$0.sql);
        this.this$0.doBinds(compileStatement);
        return this.$block.invoke(compileStatement);
    }
}
