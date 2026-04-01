package com.samsung.android.gallery.module.receiver;

import Ad.C0720a;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import com.adobe.internal.xmp.XMPConst;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CloudSyncStatusReceiver extends AbsBroadcastReceiver {
    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$onReceive$0(Bundle bundle, String str) {
        StringBuilder t = C0212a.t(str, "=");
        t.append(Logger.toString(bundle.get(str)));
        return t.toString();
    }

    public void handleSyncAlbumState() {
        if (CloudStateCompat.isLegacyServiceStatusRequired()) {
            SamsungCloudCompat.updateAlbumSyncData((Bundle) null);
            Blackboard.publishGlobal("album_sync_data_changed", Boolean.TRUE);
        }
    }

    public void handleSyncProc(Intent intent) {
        if (CloudStateCompat.isLegacyServiceStatusRequired()) {
            boolean equals = "com.samsung.android.media.SYNC_FINISHED".equals(intent.getAction());
            Bundle bundle = (Bundle) Optional.ofNullable(intent.getBundleExtra("result")).orElseGet(new C0720a(6));
            bundle.putBoolean("cloud_media_sync_finished", equals);
            SamsungCloudCompat.updateSyncStatus(bundle);
            Blackboard.getApplicationInstance().post("global://cloud/media/sync/status/changed", bundle);
        }
    }

    public void handleSyncState(Intent intent) {
        if (CloudStateCompat.isLegacyServiceStatusRequired()) {
            boolean equals = "com.samsung.android.media.SYNC_ON".equals(intent.getAction());
            CloudStateCompat.saveSyncOnInPref(equals);
            SamsungCloudCompat.updateCloudSyncOnCache(equals);
            Blackboard.getApplicationInstance().post("cloud/sync/on/off/changed", Boolean.valueOf(equals));
        }
    }

    public void onReceive(Context context, Intent intent) {
        String str;
        String str2;
        if (intent == null) {
            Log.e(this.TAG, "onReceive skip for null intent");
            return;
        }
        String str3 = (String) Optional.ofNullable(intent.getAction()).orElse("");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            str = (String) extras.keySet().stream().map(new b(extras)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]"));
        } else {
            str = null;
        }
        String str4 = this.TAG;
        String replaceFirst = str3.replaceFirst("com.samsung.android.media.", "");
        if (str == null) {
            str2 = XMPConst.ARRAY_ITEM_NAME;
        } else {
            str2 = Logger.getEncodedString(str);
        }
        Log.i(str4, "onReceive", replaceFirst, str2);
        char c5 = 65535;
        switch (str3.hashCode()) {
            case -2091726745:
                if (str3.equals("com.samsung.android.media.SYNC_STARTED")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1697221581:
                if (str3.equals("com.samsung.android.media.SYNC_ALBUMS_STATUS")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1135075243:
                if (str3.equals("com.samsung.android.media.SYNC_OFF")) {
                    c5 = 2;
                    break;
                }
                break;
            case 1764499993:
                if (str3.equals("com.samsung.android.media.SYNC_ON")) {
                    c5 = 3;
                    break;
                }
                break;
            case 1885484332:
                if (str3.equals("com.samsung.android.media.SYNC_FINISHED")) {
                    c5 = 4;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 4:
                handleSyncProc(intent);
                return;
            case 1:
                handleSyncAlbumState();
                return;
            case 2:
            case 3:
                handleSyncState(intent);
                return;
            default:
                return;
        }
    }

    public void registerReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.samsung.android.media.SYNC_STARTED");
        intentFilter.addAction("com.samsung.android.media.SYNC_FINISHED");
        intentFilter.addAction("com.samsung.android.media.SYNC_OFF");
        intentFilter.addAction("com.samsung.android.media.SYNC_ON");
        intentFilter.addAction("com.samsung.android.media.SYNC_ALBUMS_STATUS");
        AndroidCompat.registerReceiver(context, this, intentFilter, "com.samsung.android.scloud.sync.permission.READ", (Handler) null);
    }
}
