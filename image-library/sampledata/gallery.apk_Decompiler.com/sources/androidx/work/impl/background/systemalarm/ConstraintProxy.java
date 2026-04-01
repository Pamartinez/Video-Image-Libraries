package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.Constraints;
import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.model.WorkSpec;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ConstraintProxy extends BroadcastReceiver {
    private static final String TAG = Logger.tagWithPrefix("ConstraintProxy");

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BatteryChargingProxy extends ConstraintProxy {
        public /* bridge */ /* synthetic */ void onReceive(Context context, Intent intent) {
            ConstraintProxy.super.onReceive(context, intent);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BatteryNotLowProxy extends ConstraintProxy {
        public /* bridge */ /* synthetic */ void onReceive(Context context, Intent intent) {
            ConstraintProxy.super.onReceive(context, intent);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NetworkStateProxy extends ConstraintProxy {
        public /* bridge */ /* synthetic */ void onReceive(Context context, Intent intent) {
            ConstraintProxy.super.onReceive(context, intent);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StorageNotLowProxy extends ConstraintProxy {
        public /* bridge */ /* synthetic */ void onReceive(Context context, Intent intent) {
            ConstraintProxy.super.onReceive(context, intent);
        }
    }

    public static void updateAll(Context context, List<WorkSpec> list) {
        boolean z;
        boolean z3 = false;
        boolean z7 = false;
        boolean z9 = false;
        boolean z10 = false;
        for (WorkSpec workSpec : list) {
            Constraints constraints = workSpec.constraints;
            z3 |= constraints.requiresBatteryNotLow();
            z7 |= constraints.requiresCharging();
            z9 |= constraints.requiresStorageNotLow();
            if (constraints.getRequiredNetworkType() != NetworkType.NOT_REQUIRED) {
                z = true;
            } else {
                z = false;
            }
            z10 |= z;
            if (z3 && z7 && z9 && z10) {
                break;
            }
        }
        context.sendBroadcast(ConstraintProxyUpdateReceiver.newConstraintProxyUpdateIntent(context, z3, z7, z9, z10));
    }

    public void onReceive(Context context, Intent intent) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "onReceive : " + intent);
        context.startService(CommandHandler.createConstraintsChangedIntent(context));
    }
}
