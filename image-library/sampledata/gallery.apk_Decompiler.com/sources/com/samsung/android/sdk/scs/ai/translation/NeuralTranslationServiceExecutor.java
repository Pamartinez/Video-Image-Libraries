package com.samsung.android.sdk.scs.ai.translation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.samsung.android.sdk.scs.base.connection.ServiceExecutor;
import com.samsung.android.sdk.scs.base.utils.ConnectionHelper;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.translation.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class NeuralTranslationServiceExecutor extends ServiceExecutor {
    private static final String TAG = "ScsApi@TranslationServiceExecutor";
    Context context;
    /* access modifiers changed from: private */
    public final IBinder.DeathRecipient deathRecipient;
    private final boolean isPublic;
    h translationService;

    public NeuralTranslationServiceExecutor(Context context2) {
        this(context2, false);
    }

    public Intent getServiceIntent() {
        if (this.isPublic) {
            return ConnectionHelper.getTranslationServiceIntentForExternal();
        }
        return ConnectionHelper.getTranslationServiceIntent();
    }

    public h getTranslationService() {
        return this.translationService;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: com.samsung.android.sivs.ai.sdkcommon.translation.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: com.samsung.android.sivs.ai.sdkcommon.translation.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: com.samsung.android.sivs.ai.sdkcommon.translation.f} */
    /* JADX WARNING: type inference failed for: r2v8, types: [com.samsung.android.sivs.ai.sdkcommon.translation.f, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onConnected(android.content.ComponentName r2, android.os.IBinder r3) {
        /*
            r1 = this;
            int r2 = com.samsung.android.sivs.ai.sdkcommon.translation.g.f1724a     // Catch:{ RemoteException -> 0x0029 }
            if (r3 != 0) goto L_0x0006
            r2 = 0
            goto L_0x001c
        L_0x0006:
            java.lang.String r2 = "com.samsung.android.sivs.ai.sdkcommon.translation.INeuralTranslationService"
            android.os.IInterface r2 = r3.queryLocalInterface(r2)     // Catch:{ RemoteException -> 0x0029 }
            if (r2 == 0) goto L_0x0015
            boolean r0 = r2 instanceof com.samsung.android.sivs.ai.sdkcommon.translation.h     // Catch:{ RemoteException -> 0x0029 }
            if (r0 == 0) goto L_0x0015
            com.samsung.android.sivs.ai.sdkcommon.translation.h r2 = (com.samsung.android.sivs.ai.sdkcommon.translation.h) r2     // Catch:{ RemoteException -> 0x0029 }
            goto L_0x001c
        L_0x0015:
            com.samsung.android.sivs.ai.sdkcommon.translation.f r2 = new com.samsung.android.sivs.ai.sdkcommon.translation.f     // Catch:{ RemoteException -> 0x0029 }
            r2.<init>()     // Catch:{ RemoteException -> 0x0029 }
            r2.f1723a = r3     // Catch:{ RemoteException -> 0x0029 }
        L_0x001c:
            r1.translationService = r2     // Catch:{ RemoteException -> 0x0029 }
            com.samsung.android.sivs.ai.sdkcommon.translation.f r2 = (com.samsung.android.sivs.ai.sdkcommon.translation.f) r2     // Catch:{ RemoteException -> 0x0029 }
            android.os.IBinder r2 = r2.f1723a     // Catch:{ RemoteException -> 0x0029 }
            android.os.IBinder$DeathRecipient r1 = r1.deathRecipient     // Catch:{ RemoteException -> 0x0029 }
            r3 = 0
            r2.linkToDeath(r1, r3)     // Catch:{ RemoteException -> 0x0029 }
            return
        L_0x0029:
            r1 = move-exception
            java.lang.String r2 = "ScsApi@TranslationServiceExecutor"
            java.lang.String r3 = "RemoteException"
            com.samsung.android.sdk.scs.base.utils.Log.e(r2, r3)
            r1.printStackTrace()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.translation.NeuralTranslationServiceExecutor.onConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public void onDisconnected(ComponentName componentName) {
        Log.d(TAG, "onServiceDisconnected " + componentName);
        this.translationService = null;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NeuralTranslationServiceExecutor(android.content.Context r9, boolean r10) {
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
            com.samsung.android.sdk.scs.ai.translation.NeuralTranslationServiceExecutor$1 r8 = new com.samsung.android.sdk.scs.ai.translation.NeuralTranslationServiceExecutor$1
            r8.<init>()
            r0.deathRecipient = r8
            android.content.Context r8 = r1.getApplicationContext()
            r0.context = r8
            r0.isPublic = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.translation.NeuralTranslationServiceExecutor.<init>(android.content.Context, boolean):void");
    }
}
