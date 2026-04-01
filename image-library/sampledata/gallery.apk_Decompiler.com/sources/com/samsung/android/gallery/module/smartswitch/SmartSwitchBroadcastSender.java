package com.samsung.android.gallery.module.smartswitch;

import android.content.Context;
import android.content.Intent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class SmartSwitchBroadcastSender {
    private static void composeRespondIntent(Intent intent, int i2, Intent intent2) {
        intent2.putExtra("RESULT", getRespondResult(i2));
        intent2.putExtra("ERR_CODE", i2);
        intent2.putExtra("REQ_SIZE", 0);
        intent2.putExtra("SOURCE", intent.getStringExtra("SOURCE"));
    }

    private static int getRespondResult(int i2) {
        if (i2 == 0) {
            return 0;
        }
        return 1;
    }

    public static void respondBackup(Context context, Intent intent, int i2) {
        Intent intent2 = new Intent("com.samsung.android.intent.action.RESPONSE_BACKUP_GALLERY_SETTING");
        composeRespondIntent(intent, i2, intent2);
        intent2.putExtra("EXPORT_SESSION_TIME", intent.getStringExtra("EXPORT_SESSION_TIME"));
        context.sendBroadcast(intent2, "com.wssnps.permission.COM_WSSNPS");
    }

    public static void respondError(Context context, Intent intent) {
        if ("com.samsung.android.intent.action.REQUEST_BACKUP_GALLERY_SETTING".equals(intent.getAction())) {
            respondBackup(context, intent, 3);
        } else if ("com.samsung.android.intent.action.REQUEST_RESTORE_GALLERY_SETTING".equals(intent.getAction())) {
            respondRestore(context, intent, 3);
        }
    }

    public static void respondRestore(Context context, Intent intent, int i2) {
        Intent intent2 = new Intent("com.samsung.android.intent.action.RESPONSE_RESTORE_GALLERY_SETTING");
        composeRespondIntent(intent, i2, intent2);
        context.sendBroadcast(intent2, "com.wssnps.permission.COM_WSSNPS");
    }
}
