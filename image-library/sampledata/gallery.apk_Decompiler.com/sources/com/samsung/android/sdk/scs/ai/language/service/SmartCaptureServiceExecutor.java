package com.samsung.android.sdk.scs.ai.language.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.samsung.android.sdk.scs.base.connection.ServiceExecutor;
import com.samsung.android.sdk.scs.base.utils.BaseConstants;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.I;
import com.samsung.android.sivs.ai.sdkcommon.language.K;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SmartCaptureServiceExecutor extends ServiceExecutor {
    private static final String TAG = "SmartCaptureServiceExecutor";
    public final Context context;
    /* access modifiers changed from: private */
    public final IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        public void binderDied() {
            Log.d(SmartCaptureServiceExecutor.TAG, "binderDied deathRecipient callback");
            ((I) SmartCaptureServiceExecutor.this.service).f1683a.unlinkToDeath(SmartCaptureServiceExecutor.this.deathRecipient, 0);
        }
    };
    /* access modifiers changed from: private */
    public K service;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SmartCaptureServiceExecutor(android.content.Context r9) {
        /*
            r8 = this;
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.LinkedBlockingDeque r7 = new java.util.concurrent.LinkedBlockingDeque
            r7.<init>()
            r2 = 1
            r3 = 1
            r4 = 60
            r0 = r8
            r1 = r9
            r0.<init>((android.content.Context) r1, (int) r2, (int) r3, (long) r4, (java.util.concurrent.TimeUnit) r6, (java.util.concurrent.BlockingQueue<java.lang.Runnable>) r7)
            com.samsung.android.sdk.scs.ai.language.service.SmartCaptureServiceExecutor$1 r8 = new com.samsung.android.sdk.scs.ai.language.service.SmartCaptureServiceExecutor$1
            r8.<init>()
            r0.deathRecipient = r8
            android.content.Context r8 = r1.getApplicationContext()
            r0.context = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.language.service.SmartCaptureServiceExecutor.<init>(android.content.Context):void");
    }

    public K getService() {
        return this.service;
    }

    public Intent getServiceIntent() {
        return C0280e.a("android.intellivoiceservice.SmartCaptureService", BaseConstants.PACKAGE_INFO.PACKAGE_SIVS_SERVICES);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: com.samsung.android.sivs.ai.sdkcommon.language.I} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: com.samsung.android.sivs.ai.sdkcommon.language.I} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: com.samsung.android.sivs.ai.sdkcommon.language.I} */
    /* JADX WARNING: type inference failed for: r3v9, types: [com.samsung.android.sivs.ai.sdkcommon.language.I, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onConnected(android.content.ComponentName r3, android.os.IBinder r4) {
        /*
            r2 = this;
            java.lang.String r3 = "onServiceConnected"
            java.lang.String r0 = "SmartCaptureServiceExecutor"
            com.samsung.android.sdk.scs.base.utils.Log.d(r0, r3)
            int r3 = com.samsung.android.sivs.ai.sdkcommon.language.J.f1684a
            if (r4 != 0) goto L_0x000d
            r3 = 0
            goto L_0x0023
        L_0x000d:
            java.lang.String r3 = "com.samsung.android.sivs.ai.sdkcommon.language.ISmartCaptureService"
            android.os.IInterface r3 = r4.queryLocalInterface(r3)
            if (r3 == 0) goto L_0x001c
            boolean r1 = r3 instanceof com.samsung.android.sivs.ai.sdkcommon.language.K
            if (r1 == 0) goto L_0x001c
            com.samsung.android.sivs.ai.sdkcommon.language.K r3 = (com.samsung.android.sivs.ai.sdkcommon.language.K) r3
            goto L_0x0023
        L_0x001c:
            com.samsung.android.sivs.ai.sdkcommon.language.I r3 = new com.samsung.android.sivs.ai.sdkcommon.language.I
            r3.<init>()
            r3.f1683a = r4
        L_0x0023:
            r2.service = r3
            com.samsung.android.sivs.ai.sdkcommon.language.I r3 = (com.samsung.android.sivs.ai.sdkcommon.language.I) r3     // Catch:{ RemoteException -> 0x0030 }
            android.os.IBinder r3 = r3.f1683a     // Catch:{ RemoteException -> 0x0030 }
            android.os.IBinder$DeathRecipient r2 = r2.deathRecipient     // Catch:{ RemoteException -> 0x0030 }
            r4 = 0
            r3.linkToDeath(r2, r4)     // Catch:{ RemoteException -> 0x0030 }
            return
        L_0x0030:
            r2 = move-exception
            java.lang.String r3 = "RemoteException"
            com.samsung.android.sdk.scs.base.utils.Log.e(r0, r3)
            r2.printStackTrace()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.language.service.SmartCaptureServiceExecutor.onConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public void onDisconnected(ComponentName componentName) {
        this.service = null;
    }
}
