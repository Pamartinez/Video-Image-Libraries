package androidx.room;

import Ae.a;
import androidx.sqlite.db.SupportSQLiteStatement;
import kotlin.Metadata;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/sqlite/db/SupportSQLiteStatement;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SharedSQLiteStatement$stmt$2 extends k implements a {
    final /* synthetic */ SharedSQLiteStatement this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SharedSQLiteStatement$stmt$2(SharedSQLiteStatement sharedSQLiteStatement) {
        super(0);
        this.this$0 = sharedSQLiteStatement;
    }

    public final SupportSQLiteStatement invoke() {
        return this.this$0.createNewStatement();
    }
}
