package androidx.room;

import Ae.b;
import android.content.ContentValues;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$update$1 extends k implements b {
    final /* synthetic */ int $conflictAlgorithm;
    final /* synthetic */ String $table;
    final /* synthetic */ ContentValues $values;
    final /* synthetic */ Object[] $whereArgs;
    final /* synthetic */ String $whereClause;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$update$1(String str, int i2, ContentValues contentValues, String str2, Object[] objArr) {
        super(1);
        this.$table = str;
        this.$conflictAlgorithm = i2;
        this.$values = contentValues;
        this.$whereClause = str2;
        this.$whereArgs = objArr;
    }

    public final Integer invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
        j.e(supportSQLiteDatabase, "db");
        return Integer.valueOf(supportSQLiteDatabase.update(this.$table, this.$conflictAlgorithm, this.$values, this.$whereClause, this.$whereArgs));
    }
}
