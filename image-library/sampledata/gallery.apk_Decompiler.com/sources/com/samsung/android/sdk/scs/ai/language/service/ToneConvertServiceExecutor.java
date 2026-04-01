package com.samsung.android.sdk.scs.ai.language.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.samsung.android.sdk.scs.base.connection.ServiceExecutor;
import com.samsung.android.sdk.scs.base.utils.BaseConstants;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.c0;
import com.samsung.android.sivs.ai.sdkcommon.language.e0;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ToneConvertServiceExecutor extends ServiceExecutor {
    private static final String TAG = "ToneConvertServiceExecutor";
    public final Context context;
    /* access modifiers changed from: private */
    public final IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        public void binderDied() {
            Log.d(ToneConvertServiceExecutor.TAG, "binderDied deathRecipient callback");
            ((c0) ToneConvertServiceExecutor.this.toneConvertService).f1699a.unlinkToDeath(ToneConvertServiceExecutor.this.deathRecipient, 0);
        }
    };
    /* access modifiers changed from: private */
    public e0 toneConvertService;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ToneConvertServiceExecutor(android.content.Context r9) {
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
            com.samsung.android.sdk.scs.ai.language.service.ToneConvertServiceExecutor$1 r8 = new com.samsung.android.sdk.scs.ai.language.service.ToneConvertServiceExecutor$1
            r8.<init>()
            r0.deathRecipient = r8
            android.content.Context r8 = r1.getApplicationContext()
            r0.context = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.language.service.ToneConvertServiceExecutor.<init>(android.content.Context):void");
    }

    public e0 getService() {
        return this.toneConvertService;
    }

    public Intent getServiceIntent() {
        return C0280e.a(BaseConstants.SERVICE_ACTION.ACTION_TONE_CONVERT_SERVICE, BaseConstants.PACKAGE_INFO.PACKAGE_SIVS_SERVICES);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: com.samsung.android.sivs.ai.sdkcommon.language.c0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: com.samsung.android.sivs.ai.sdkcommon.language.c0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: com.samsung.android.sivs.ai.sdkcommon.language.c0} */
    /* JADX WARNING: type inference failed for: r3v9, types: [java.lang.Object, com.samsung.android.sivs.ai.sdkcommon.language.c0] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onConnected(android.content.ComponentName r3, android.os.IBinder r4) {
        /*
            r2 = this;
            java.lang.String r3 = "onServiceConnected"
            java.lang.String r0 = "ToneConvertServiceExecutor"
            com.samsung.android.sdk.scs.base.utils.Log.d(r0, r3)
            int r3 = com.samsung.android.sivs.ai.sdkcommon.language.d0.f1700a
            if (r4 != 0) goto L_0x000d
            r3 = 0
            goto L_0x0023
        L_0x000d:
            java.lang.String r3 = "com.samsung.android.sivs.ai.sdkcommon.language.IToneConvertService"
            android.os.IInterface r3 = r4.queryLocalInterface(r3)
            if (r3 == 0) goto L_0x001c
            boolean r1 = r3 instanceof com.samsung.android.sivs.ai.sdkcommon.language.e0
            if (r1 == 0) goto L_0x001c
            com.samsung.android.sivs.ai.sdkcommon.language.e0 r3 = (com.samsung.android.sivs.ai.sdkcommon.language.e0) r3
            goto L_0x0023
        L_0x001c:
            com.samsung.android.sivs.ai.sdkcommon.language.c0 r3 = new com.samsung.android.sivs.ai.sdkcommon.language.c0
            r3.<init>()
            r3.f1699a = r4
        L_0x0023:
            r2.toneConvertService = r3
            com.samsung.android.sivs.ai.sdkcommon.language.c0 r3 = (com.samsung.android.sivs.ai.sdkcommon.language.c0) r3     // Catch:{ RemoteException -> 0x0030 }
            android.os.IBinder r3 = r3.f1699a     // Catch:{ RemoteException -> 0x0030 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.language.service.ToneConvertServiceExecutor.onConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public void onDisconnected(ComponentName componentName) {
        this.toneConvertService = null;
    }
}
