package androidx.room.migration;

import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\fR\u0014\u0010\u0004\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\f¨\u0006\r"}, d2 = {"Landroidx/room/migration/Migration;", "", "", "startVersion", "endVersion", "<init>", "(II)V", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "db", "Lme/x;", "migrate", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "I", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Migration {
    public final int endVersion;
    public final int startVersion;

    public Migration(int i2, int i7) {
        this.startVersion = i2;
        this.endVersion = i7;
    }

    public abstract void migrate(SupportSQLiteDatabase supportSQLiteDatabase);
}
