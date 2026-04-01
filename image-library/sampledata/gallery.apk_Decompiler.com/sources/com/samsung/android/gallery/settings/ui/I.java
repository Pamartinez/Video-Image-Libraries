package com.samsung.android.gallery.settings.ui;

import android.content.DialogInterface;
import com.samsung.android.gallery.module.cloud.SamsungCloudManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class I implements DialogInterface.OnCancelListener {
    public final void onCancel(DialogInterface dialogInterface) {
        SamsungCloudManager.getInstance().cancelSyncOn();
    }
}
