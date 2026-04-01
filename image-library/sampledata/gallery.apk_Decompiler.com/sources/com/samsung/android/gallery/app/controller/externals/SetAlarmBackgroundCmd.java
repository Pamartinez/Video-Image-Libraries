package com.samsung.android.gallery.app.controller.externals;

import A.a;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SetAlarmBackgroundCmd extends BaseCommand {
    private boolean supportAlarmBackground() {
        Bundle bundle;
        try {
            PackageManager packageManager = getContext().getPackageManager();
            if (packageManager == null) {
                Log.d(this.TAG, "isPackageDisabled(), packageManager is null");
                return false;
            }
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo("com.sec.android.app.clockpackage", 128);
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null || !bundle.getBoolean("support.clock.gallery_background") || !applicationInfo.enabled) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            String str = this.TAG;
            Log.e(str, "e =" + e.getMessage());
            return false;
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        Uri uri = ContentUri.getUri(mediaItem);
        if (uri == null) {
            Log.e(this.TAG, "item or uri is null");
        } else if (supportAlarmBackground()) {
            Intent a7 = C0280e.a("com.sec.android.app.clockpackage.alarmbackground.action.OPEN_ALARM_BACKGROUND_EXTERNAL_ACTIVITY", "com.sec.android.app.clockpackage");
            a7.setDataAndType(uri, mediaItem.getMimeType());
            a7.putExtra("from", "gallery");
            a7.addFlags(268435457);
            try {
                getContext().startActivity(a7);
            } catch (Exception e) {
                a.s(e, new StringBuilder("start alarm failed e="), this.TAG);
            }
        }
    }
}
