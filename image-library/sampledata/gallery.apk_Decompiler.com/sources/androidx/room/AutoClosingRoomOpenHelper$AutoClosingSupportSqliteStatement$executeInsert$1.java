package androidx.room;

import Ae.b;
import androidx.sqlite.db.SupportSQLiteStatement;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "obj", "Landroidx/sqlite/db/SupportSQLiteStatement;", "invoke", "(Landroidx/sqlite/db/SupportSQLiteStatement;)Ljava/lang/Long;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1 extends k implements b {
    public static final AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1 INSTANCE = new AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1();

    public AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1() {
        super(1);
    }

    public final Long invoke(SupportSQLiteStatement supportSQLiteStatement) {
        j.e(supportSQLiteStatement, "obj");
        return Long.valueOf(supportSQLiteStatement.executeInsert());
    }
}
