package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import androidx.work.Logger;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.SystemIdInfoKt;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.utils.IdGenerator;
import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class Alarms {
    private static final String TAG = Logger.tagWithPrefix("Alarms");

    public static void cancelAlarm(Context context, WorkDatabase workDatabase, WorkGenerationalId workGenerationalId) {
        SystemIdInfoDao systemIdInfoDao = workDatabase.systemIdInfoDao();
        SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(workGenerationalId);
        if (systemIdInfo != null) {
            cancelExactAlarm(context, workGenerationalId, systemIdInfo.systemId);
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Removing SystemIdInfo for workSpecId (" + workGenerationalId + ")");
            systemIdInfoDao.removeSystemIdInfo(workGenerationalId);
        }
    }

    private static void cancelExactAlarm(Context context, WorkGenerationalId workGenerationalId, int i2) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i2, CommandHandler.createDelayMetIntent(context, workGenerationalId), 603979776);
        if (service != null && alarmManager != null) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Cancelling existing alarm with (workSpecId, systemId) (" + workGenerationalId + ArcCommonLog.TAG_COMMA + i2 + ")");
            alarmManager.cancel(service);
        }
    }

    public static void setAlarm(Context context, WorkDatabase workDatabase, WorkGenerationalId workGenerationalId, long j2) {
        SystemIdInfoDao systemIdInfoDao = workDatabase.systemIdInfoDao();
        SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(workGenerationalId);
        if (systemIdInfo != null) {
            cancelExactAlarm(context, workGenerationalId, systemIdInfo.systemId);
            setExactAlarm(context, workGenerationalId, systemIdInfo.systemId, j2);
            return;
        }
        int nextAlarmManagerId = new IdGenerator(workDatabase).nextAlarmManagerId();
        systemIdInfoDao.insertSystemIdInfo(SystemIdInfoKt.systemIdInfo(workGenerationalId, nextAlarmManagerId));
        setExactAlarm(context, workGenerationalId, nextAlarmManagerId, j2);
    }

    private static void setExactAlarm(Context context, WorkGenerationalId workGenerationalId, int i2, long j2) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i2, CommandHandler.createDelayMetIntent(context, workGenerationalId), 201326592);
        if (alarmManager != null) {
            alarmManager.setExact(0, j2, service);
        }
    }
}
