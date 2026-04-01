package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.Preference;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a#\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\b\u0010\t\u001a\u001f\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/work/impl/WorkDatabase;", "", "key", "", "nextId", "(Landroidx/work/impl/WorkDatabase;Ljava/lang/String;)I", "value", "Lme/x;", "updatePreference", "(Landroidx/work/impl/WorkDatabase;Ljava/lang/String;I)V", "Landroid/content/Context;", "context", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "sqLiteDatabase", "migrateLegacyIdGenerator", "(Landroid/content/Context;Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class IdGeneratorKt {
    public static final void migrateLegacyIdGenerator(Context context, SupportSQLiteDatabase supportSQLiteDatabase) {
        j.e(context, "context");
        j.e(supportSQLiteDatabase, "sqLiteDatabase");
        SharedPreferences sharedPreferences = context.getSharedPreferences("androidx.work.util.id", 0);
        if (sharedPreferences.contains("next_job_scheduler_id") || sharedPreferences.contains("next_job_scheduler_id")) {
            int i2 = sharedPreferences.getInt("next_job_scheduler_id", 0);
            int i7 = sharedPreferences.getInt("next_alarm_manager_id", 0);
            supportSQLiteDatabase.beginTransaction();
            try {
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"next_job_scheduler_id", Integer.valueOf(i2)});
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"next_alarm_manager_id", Integer.valueOf(i7)});
                sharedPreferences.edit().clear().apply();
                supportSQLiteDatabase.setTransactionSuccessful();
            } finally {
                supportSQLiteDatabase.endTransaction();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final int nextId(WorkDatabase workDatabase, String str) {
        int i2;
        Long longValue = workDatabase.preferenceDao().getLongValue(str);
        int i7 = 0;
        if (longValue != null) {
            i2 = (int) longValue.longValue();
        } else {
            i2 = 0;
        }
        if (i2 != Integer.MAX_VALUE) {
            i7 = i2 + 1;
        }
        updatePreference(workDatabase, str, i7);
        return i2;
    }

    /* access modifiers changed from: private */
    public static final void updatePreference(WorkDatabase workDatabase, String str, int i2) {
        workDatabase.preferenceDao().insertPreference(new Preference(str, Long.valueOf((long) i2)));
    }
}
