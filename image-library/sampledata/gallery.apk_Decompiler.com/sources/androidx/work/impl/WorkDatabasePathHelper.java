package androidx.work.impl;

import android.content.Context;
import androidx.work.Logger;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;
import ne.z;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\n\u0010\u000bJ!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\f2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u000bJ\u0015\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0010\u0010\u000b¨\u0006\u0011"}, d2 = {"Landroidx/work/impl/WorkDatabasePathHelper;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Lme/x;", "migrateDatabase", "(Landroid/content/Context;)V", "Ljava/io/File;", "getNoBackupPath", "(Landroid/content/Context;)Ljava/io/File;", "", "migrationPaths", "(Landroid/content/Context;)Ljava/util/Map;", "getDefaultDatabasePath", "getDatabasePath", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkDatabasePathHelper {
    public static final WorkDatabasePathHelper INSTANCE = new WorkDatabasePathHelper();

    private WorkDatabasePathHelper() {
    }

    private final File getNoBackupPath(Context context) {
        return new File(Api21Impl.INSTANCE.getNoBackupFilesDir(context), "androidx.work.workdb");
    }

    public static final void migrateDatabase(Context context) {
        String str;
        j.e(context, "context");
        WorkDatabasePathHelper workDatabasePathHelper = INSTANCE;
        if (workDatabasePathHelper.getDefaultDatabasePath(context).exists()) {
            Logger.get().debug(WorkDatabasePathHelperKt.TAG, "Migrating WorkDatabase to the no-backup directory");
            for (Map.Entry next : workDatabasePathHelper.migrationPaths(context).entrySet()) {
                File file = (File) next.getKey();
                File file2 = (File) next.getValue();
                if (file.exists()) {
                    if (file2.exists()) {
                        Logger.get().warning(WorkDatabasePathHelperKt.TAG, "Over-writing contents of " + file2);
                    }
                    if (file.renameTo(file2)) {
                        str = "Migrated " + file + "to " + file2;
                    } else {
                        str = "Renaming " + file + " to " + file2 + " failed";
                    }
                    Logger.get().debug(WorkDatabasePathHelperKt.TAG, str);
                }
            }
        }
    }

    public final File getDatabasePath(Context context) {
        j.e(context, "context");
        return getNoBackupPath(context);
    }

    public final File getDefaultDatabasePath(Context context) {
        j.e(context, "context");
        File databasePath = context.getDatabasePath("androidx.work.workdb");
        j.d(databasePath, "context.getDatabasePath(WORK_DATABASE_NAME)");
        return databasePath;
    }

    public final Map<File, File> migrationPaths(Context context) {
        j.e(context, "context");
        File defaultDatabasePath = getDefaultDatabasePath(context);
        File databasePath = getDatabasePath(context);
        String[] access$getDATABASE_EXTRA_FILES$p = WorkDatabasePathHelperKt.DATABASE_EXTRA_FILES;
        int Z = z.Z(access$getDATABASE_EXTRA_FILES$p.length);
        if (Z < 16) {
            Z = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
        for (String str : access$getDATABASE_EXTRA_FILES$p) {
            linkedHashMap.put(new File(defaultDatabasePath.getPath() + str), new File(databasePath.getPath() + str));
        }
        i iVar = new i(defaultDatabasePath, databasePath);
        if (linkedHashMap.isEmpty()) {
            return z.a0(iVar);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put(defaultDatabasePath, databasePath);
        return linkedHashMap2;
    }
}
