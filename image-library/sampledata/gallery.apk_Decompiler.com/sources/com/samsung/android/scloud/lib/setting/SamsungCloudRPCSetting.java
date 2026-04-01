package com.samsung.android.scloud.lib.setting;

import android.content.Context;
import com.samsung.android.scloud.rpc.SamsungCloudRPCStatus;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SamsungCloudRPCSetting {
    private ISetting iSetting;

    public SamsungCloudRPCSetting(Context context, SamsungCloudRPCStatus samsungCloudRPCStatus) {
        if ("SYNC".equals(samsungCloudRPCStatus.getServiceType())) {
            this.iSetting = new SyncSetting(context, "[scsetting][2.0.18.0]", samsungCloudRPCStatus);
        }
    }

    public void showSetting(String str) {
        this.iSetting.showSetting(str);
    }
}
