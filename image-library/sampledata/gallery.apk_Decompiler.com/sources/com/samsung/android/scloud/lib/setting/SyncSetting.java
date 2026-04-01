package com.samsung.android.scloud.lib.setting;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.samsung.android.scloud.rpc.ISamsungCloudRPC;
import com.samsung.android.scloud.rpc.SamsungCloudRPCStatus;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SyncSetting implements ISetting {
    private static String TAG;
    private String ACTION_NAME = "com.samsung.android.scloud.RPC_SYNC_SETTING";
    private String CLASS_NAME = "com.samsung.android.scloud.app.service.RPCSyncService";
    private String PACKAGE_NAME = "com.samsung.android.scloud";
    /* access modifiers changed from: private */
    public ISamsungCloudRPC iSamsungCloudRPC;
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ISamsungCloudRPC unused = SyncSetting.this.iSamsungCloudRPC = ISamsungCloudRPC.Stub.asInterface(iBinder);
            SyncSetting.this.samsungCloudRPCStatus.onBind(true);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            ISamsungCloudRPC unused = SyncSetting.this.iSamsungCloudRPC = null;
        }
    };
    /* access modifiers changed from: private */
    public SamsungCloudRPCStatus samsungCloudRPCStatus;

    public SyncSetting(Context context, String str, SamsungCloudRPCStatus samsungCloudRPCStatus2) {
        TAG = str;
        this.samsungCloudRPCStatus = samsungCloudRPCStatus2;
        Intent intent = new Intent(this.ACTION_NAME);
        intent.setClassName(this.PACKAGE_NAME, this.CLASS_NAME);
        if (context.bindService(intent, this.mConnection, 1)) {
            Log.i(TAG, "binding success");
            return;
        }
        samsungCloudRPCStatus2.onBind(false);
        Log.i(TAG, "binding failure");
    }

    public void showSetting(String str) {
        try {
            Log.i(TAG, "showSetting");
            this.iSamsungCloudRPC.showSetting(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
