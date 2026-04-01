package androidx.room;

import Ae.b;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RoomDatabase$beginTransaction$1 extends k implements b {
    final /* synthetic */ RoomDatabase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RoomDatabase$beginTransaction$1(RoomDatabase roomDatabase) {
        super(1);
        this.this$0 = roomDatabase;
    }

    public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
        j.e(supportSQLiteDatabase, "it");
        this.this$0.internalBeginTransaction();
        return null;
    }
}
