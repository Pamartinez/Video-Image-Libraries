package com.samsung.android.gallery.app.receiver;

import A.a;
import Y3.C0415b;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.trash.helper.TrashUpdateTask;
import com.samsung.android.gallery.module.trash.support.TrashExternalLogger;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SystemEnvironment;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashUpdateReceiver extends BroadcastReceiver {
    private boolean checkSamsungAccountExist() {
        return SamsungAccountManager.getInstance().reload().hasAccount();
    }

    private String getReplacedString(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return str.replaceFirst("/stroage/", "");
            } catch (Exception e) {
                a.s(e, new StringBuilder("replace string failed. e="), "TrashUpdateReceiver");
            }
        }
        return str;
    }

    private void handleAccountSignOut(Context context, Bundle bundle) {
        if (bundle == null || !CommonUtils.SAMSUNG_ACCOUNT_PACKAGE_NAME.equals(bundle.getString("accountType"))) {
            Log.w("TrashUpdateReceiver", "unable to handle account sign out, type is not matched");
        } else if (checkSamsungAccountExist()) {
            Log.d("TrashUpdateReceiver", "samsung account is exist, so skip.");
        } else {
            runUpdateTask(context, TrashExternalLogger.Task.SIGNED_OUT);
        }
    }

    private void handleSDCardStatus(TrashExternalLogger.Task task, String str) {
        boolean z;
        if (str != null) {
            if (task == TrashExternalLogger.Task.MOUNTED) {
                z = true;
            } else {
                z = false;
            }
            notifySdStatusChange(z);
            return;
        }
        Log.w("TrashUpdateReceiver", "unable to handle sd card status, volume name is null");
    }

    private void handleVolumeStateChanged(Context context, Intent intent) {
        TrashExternalLogger.Task task;
        int intExtra = intent.getIntExtra("android.os.storage.extra.VOLUME_STATE", -1);
        String stringExtra = intent.getStringExtra("android.os.storage.extra.FS_UUID");
        if (intExtra == 0 || intExtra == 2) {
            FileUtils.initMountedStorageNames(context);
            Log.d("TrashUpdateReceiver", "handleVolumeStateChanged", Integer.valueOf(intExtra), stringExtra);
            if (stringExtra != null) {
                if (intExtra == 0) {
                    task = TrashExternalLogger.Task.UNMOUNTED;
                } else {
                    task = TrashExternalLogger.Task.MOUNTED;
                }
                handleSDCardStatus(task, stringExtra);
                return;
            }
            return;
        }
        Log.v("TrashUpdateReceiver", "handleVolumeStateChanged", Integer.valueOf(intExtra), stringExtra);
    }

    private void initFileUtils(Context context) {
        if (!SystemEnvironment.updateIfChanged(context)) {
            FileUtils.initMountedVolumes(context);
        }
        Blackboard.postGlobal("command://event/DataChanged", EventMessage.obtain(101, 1, 0, (Object) null));
    }

    private void notifySdStatusChange(boolean z) {
        Blackboard.getBlackboardMap().forEach(new C0415b(z, 0));
    }

    private void runUpdateTask(Context context, TrashExternalLogger.Task task) {
        try {
            new TrashUpdateTask(context, task).execute(new Void[0]);
        } catch (NullPointerException e) {
            Log.e("TrashUpdateReceiver", "execute trash update task failed. e=" + e.getMessage());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r7, android.content.Intent r8) {
        /*
            r6 = this;
            android.content.Context r7 = r7.getApplicationContext()
            java.lang.String r0 = r8.getAction()
            android.net.Uri r1 = r8.getData()
            java.lang.String r2 = "received action"
            java.lang.Object[] r3 = new java.lang.Object[]{r0, r1}
            java.lang.String r4 = "TrashUpdateReceiver"
            com.samsung.android.gallery.support.utils.Log.d(r4, r2, r3)
            if (r0 == 0) goto L_0x00b2
            if (r7 == 0) goto L_0x00b2
            if (r1 == 0) goto L_0x0022
            java.lang.String r1 = r1.getPath()
            goto L_0x0023
        L_0x0022:
            r1 = 0
        L_0x0023:
            java.lang.String r1 = r6.getReplacedString(r1)
            int r2 = r0.hashCode()
            java.lang.String r3 = "android.intent.action.USER_ADDED"
            r5 = -1
            switch(r2) {
                case -2088356897: goto L_0x0068;
                case -2061058799: goto L_0x005d;
                case -1514214344: goto L_0x0052;
                case -1410684549: goto L_0x0047;
                case -963871873: goto L_0x003c;
                case 1121780209: goto L_0x0033;
                default: goto L_0x0031;
            }
        L_0x0031:
            r2 = r5
            goto L_0x0072
        L_0x0033:
            boolean r2 = r0.equals(r3)
            if (r2 != 0) goto L_0x003a
            goto L_0x0031
        L_0x003a:
            r2 = 5
            goto L_0x0072
        L_0x003c:
            java.lang.String r2 = "android.intent.action.MEDIA_UNMOUNTED"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x0045
            goto L_0x0031
        L_0x0045:
            r2 = 4
            goto L_0x0072
        L_0x0047:
            java.lang.String r2 = "android.os.storage.action.VOLUME_STATE_CHANGED"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x0050
            goto L_0x0031
        L_0x0050:
            r2 = 3
            goto L_0x0072
        L_0x0052:
            java.lang.String r2 = "android.intent.action.MEDIA_MOUNTED"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x005b
            goto L_0x0031
        L_0x005b:
            r2 = 2
            goto L_0x0072
        L_0x005d:
            java.lang.String r2 = "android.intent.action.USER_REMOVED"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x0066
            goto L_0x0031
        L_0x0066:
            r2 = 1
            goto L_0x0072
        L_0x0068:
            java.lang.String r2 = "android.accounts.action.ACCOUNT_REMOVED"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x0071
            goto L_0x0031
        L_0x0071:
            r2 = 0
        L_0x0072:
            switch(r2) {
                case 0: goto L_0x00ab;
                case 1: goto L_0x008c;
                case 2: goto L_0x0083;
                case 3: goto L_0x007f;
                case 4: goto L_0x0076;
                case 5: goto L_0x008c;
                default: goto L_0x0075;
            }
        L_0x0075:
            goto L_0x00b2
        L_0x0076:
            r6.initFileUtils(r7)
            com.samsung.android.gallery.module.trash.support.TrashExternalLogger$Task r7 = com.samsung.android.gallery.module.trash.support.TrashExternalLogger.Task.UNMOUNTED
            r6.handleSDCardStatus(r7, r1)
            return
        L_0x007f:
            r6.handleVolumeStateChanged(r7, r8)
            return
        L_0x0083:
            r6.initFileUtils(r7)
            com.samsung.android.gallery.module.trash.support.TrashExternalLogger$Task r7 = com.samsung.android.gallery.module.trash.support.TrashExternalLogger.Task.MOUNTED
            r6.handleSDCardStatus(r7, r1)
            return
        L_0x008c:
            boolean r6 = r3.equals(r0)
            if (r6 == 0) goto L_0x0095
            java.lang.String r6 = "added"
            goto L_0x0097
        L_0x0095:
            java.lang.String r6 = "removed"
        L_0x0097:
            java.lang.String r7 = "android.intent.extra.user_handle"
            int r7 = r8.getIntExtra(r7, r5)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r7}
            java.lang.String r7 = "handleCloneUserChanged"
            com.samsung.android.gallery.support.utils.Log.d(r4, r7, r6)
            return
        L_0x00ab:
            android.os.Bundle r8 = r8.getExtras()
            r6.handleAccountSignOut(r7, r8)
        L_0x00b2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.receiver.TrashUpdateReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
