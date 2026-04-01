package androidx.work.impl;

import android.content.Context;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.impl.utils.IdGeneratorKt;
import androidx.work.impl.utils.PreferenceUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/work/impl/WorkMigration9To10;", "Landroidx/room/migration/Migration;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "db", "Lme/x;", "migrate", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "Landroid/content/Context;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkMigration9To10 extends Migration {
    private final Context context;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WorkMigration9To10(Context context2) {
        super(9, 10);
        j.e(context2, "context");
        this.context = context2;
    }

    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        j.e(supportSQLiteDatabase, "db");
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
        PreferenceUtils.migrateLegacyPreferences(this.context, supportSQLiteDatabase);
        IdGeneratorKt.migrateLegacyIdGenerator(this.context, supportSQLiteDatabase);
    }
}
