package androidx.room;

import android.content.Context;
import android.content.Intent;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0016\u0018\u00002\u00020\u0001BÏ\u0001\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0011\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0016\u001a\u00020\r\u0012\u0006\u0010\u0017\u001a\u00020\r\u0012\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e\u0012\b\u0010\"\u001a\u0004\u0018\u00010!\u0012\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\n\u0012\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\n¢\u0006\u0004\b&\u0010'J\u001f\u0010*\u001a\u00020\r2\u0006\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u0019H\u0016¢\u0006\u0004\b*\u0010+R\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010,R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010-R\u0014\u0010\u0007\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010.R\u0014\u0010\t\u001a\u00020\b8\u0006X\u0004¢\u0006\u0006\n\u0004\b\t\u0010/R\u001c\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0006X\u0004¢\u0006\u0006\n\u0004\b\f\u00100R\u0014\u0010\u000e\u001a\u00020\r8\u0006X\u0004¢\u0006\u0006\n\u0004\b\u000e\u00101R\u0014\u0010\u0010\u001a\u00020\u000f8\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0010\u00102R\u0014\u0010\u0012\u001a\u00020\u00118\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0012\u00103R\u0014\u0010\u0013\u001a\u00020\u00118\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0013\u00103R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0015\u00104R\u0014\u0010\u0016\u001a\u00020\r8\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0016\u00101R\u0014\u0010\u0017\u001a\u00020\r8\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0017\u00101R\u001c\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00188\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001a\u00105R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010-R\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0006X\u0004¢\u0006\u0006\n\u0004\b\u001d\u00106R\u001c\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e8\u0006X\u0004¢\u0006\u0006\n\u0004\b \u00107R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\n8\u0006X\u0004¢\u0006\u0006\n\u0004\b#\u00100R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\n8\u0006X\u0004¢\u0006\u0006\n\u0004\b%\u00100R\u0014\u00108\u001a\u00020\r8\u0006X\u0004¢\u0006\u0006\n\u0004\b8\u00101¨\u00069"}, d2 = {"Landroidx/room/DatabaseConfiguration;", "", "Landroid/content/Context;", "context", "", "name", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "sqliteOpenHelperFactory", "Landroidx/room/RoomDatabase$MigrationContainer;", "migrationContainer", "", "Landroidx/room/RoomDatabase$Callback;", "callbacks", "", "allowMainThreadQueries", "Landroidx/room/RoomDatabase$JournalMode;", "journalMode", "Ljava/util/concurrent/Executor;", "queryExecutor", "transactionExecutor", "Landroid/content/Intent;", "multiInstanceInvalidationServiceIntent", "requireMigration", "allowDestructiveMigrationOnDowngrade", "", "", "migrationNotRequiredFrom", "copyFromAssetPath", "Ljava/io/File;", "copyFromFile", "Ljava/util/concurrent/Callable;", "Ljava/io/InputStream;", "copyFromInputStream", "Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;", "prepackagedDatabaseCallback", "typeConverters", "Landroidx/room/migration/AutoMigrationSpec;", "autoMigrationSpecs", "<init>", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/room/RoomDatabase$MigrationContainer;Ljava/util/List;ZLandroidx/room/RoomDatabase$JournalMode;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Landroid/content/Intent;ZZLjava/util/Set;Ljava/lang/String;Ljava/io/File;Ljava/util/concurrent/Callable;Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;Ljava/util/List;Ljava/util/List;)V", "fromVersion", "toVersion", "isMigrationRequired", "(II)Z", "Landroid/content/Context;", "Ljava/lang/String;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "Landroidx/room/RoomDatabase$MigrationContainer;", "Ljava/util/List;", "Z", "Landroidx/room/RoomDatabase$JournalMode;", "Ljava/util/concurrent/Executor;", "Landroid/content/Intent;", "Ljava/util/Set;", "Ljava/io/File;", "Ljava/util/concurrent/Callable;", "multiInstanceInvalidation", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DatabaseConfiguration {
    public final boolean allowDestructiveMigrationOnDowngrade;
    public final boolean allowMainThreadQueries;
    public final List<AutoMigrationSpec> autoMigrationSpecs;
    public final List<RoomDatabase.Callback> callbacks;
    public final Context context;
    public final String copyFromAssetPath;
    public final File copyFromFile;
    public final Callable<InputStream> copyFromInputStream;
    public final RoomDatabase.JournalMode journalMode;
    public final RoomDatabase.MigrationContainer migrationContainer;
    private final Set<Integer> migrationNotRequiredFrom;
    public final boolean multiInstanceInvalidation;
    public final Intent multiInstanceInvalidationServiceIntent;
    public final String name;
    public final Executor queryExecutor;
    public final boolean requireMigration;
    public final SupportSQLiteOpenHelper.Factory sqliteOpenHelperFactory;
    public final Executor transactionExecutor;
    public final List<Object> typeConverters;

    public DatabaseConfiguration(Context context2, String str, SupportSQLiteOpenHelper.Factory factory, RoomDatabase.MigrationContainer migrationContainer2, List<? extends RoomDatabase.Callback> list, boolean z, RoomDatabase.JournalMode journalMode2, Executor executor, Executor executor2, Intent intent, boolean z3, boolean z7, Set<Integer> set, String str2, File file, Callable<InputStream> callable, RoomDatabase.PrepackagedDatabaseCallback prepackagedDatabaseCallback, List<? extends Object> list2, List<? extends AutoMigrationSpec> list3) {
        Intent intent2 = intent;
        List<? extends Object> list4 = list2;
        List<? extends AutoMigrationSpec> list5 = list3;
        j.e(context2, "context");
        j.e(factory, "sqliteOpenHelperFactory");
        j.e(migrationContainer2, "migrationContainer");
        j.e(journalMode2, "journalMode");
        j.e(executor, "queryExecutor");
        j.e(executor2, "transactionExecutor");
        j.e(list4, "typeConverters");
        j.e(list5, "autoMigrationSpecs");
        this.context = context2;
        this.name = str;
        this.sqliteOpenHelperFactory = factory;
        this.migrationContainer = migrationContainer2;
        this.callbacks = list;
        this.allowMainThreadQueries = z;
        this.journalMode = journalMode2;
        this.queryExecutor = executor;
        this.transactionExecutor = executor2;
        this.multiInstanceInvalidationServiceIntent = intent2;
        this.requireMigration = z3;
        this.allowDestructiveMigrationOnDowngrade = z7;
        this.migrationNotRequiredFrom = set;
        this.copyFromAssetPath = str2;
        this.copyFromFile = file;
        this.copyFromInputStream = callable;
        this.typeConverters = list4;
        this.autoMigrationSpecs = list5;
        this.multiInstanceInvalidation = intent2 != null;
    }

    public boolean isMigrationRequired(int i2, int i7) {
        Set<Integer> set;
        if ((i2 <= i7 || !this.allowDestructiveMigrationOnDowngrade) && this.requireMigration && ((set = this.migrationNotRequiredFrom) == null || !set.contains(Integer.valueOf(i2)))) {
            return true;
        }
        return false;
    }
}
