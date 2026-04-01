package com.samsung.android.gallery.module.smartswitch;

import android.content.Context;
import android.content.Intent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SmartSwitchThreadFactory {
    public static void start(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.samsung.android.intent.action.REQUEST_BACKUP_GALLERY_SETTING".equals(action)) {
            new BackupThread(context, intent).start();
        } else if ("com.samsung.android.intent.action.REQUEST_RESTORE_GALLERY_SETTING".equals(action)) {
            new RestoreThread(context, intent).start();
        }
    }
}
