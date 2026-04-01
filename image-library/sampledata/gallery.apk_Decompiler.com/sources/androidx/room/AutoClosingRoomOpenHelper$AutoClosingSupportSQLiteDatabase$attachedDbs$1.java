package androidx.room;

import Ae.b;
import android.util.Pair;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "Landroid/util/Pair;", "", "obj", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$attachedDbs$1 extends k implements b {
    public static final AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$attachedDbs$1 INSTANCE = new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$attachedDbs$1();

    public AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$attachedDbs$1() {
        super(1);
    }

    public final List<Pair<String, String>> invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
        j.e(supportSQLiteDatabase, "obj");
        return supportSQLiteDatabase.getAttachedDbs();
    }
}
