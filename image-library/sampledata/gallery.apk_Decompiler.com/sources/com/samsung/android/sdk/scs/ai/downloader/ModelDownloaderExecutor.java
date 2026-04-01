package com.samsung.android.sdk.scs.ai.downloader;

import Cd.C0727a;
import Cd.C0729c;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.samsung.android.sdk.scs.base.connection.ServiceExecutor;
import com.samsung.android.sdk.scs.base.utils.ConnectionHelper;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ModelDownloaderExecutor extends ServiceExecutor {
    private static final String TAG = "ScsApi@ModelDownloaderExecutor";
    /* access modifiers changed from: private */
    public IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        public void binderDied() {
            Log.d(ModelDownloaderExecutor.TAG, "binderDied deathRecipient callback");
            ((C0727a) ModelDownloaderExecutor.this.mDownloadService).f3323a.unlinkToDeath(ModelDownloaderExecutor.this.deathRecipient, 0);
        }
    };
    /* access modifiers changed from: private */
    public C0729c mDownloadService;

    public ModelDownloaderExecutor(Context context) {
        super(context, 1, 1, 60, TimeUnit.SECONDS, (BlockingQueue<Runnable>) new LinkedBlockingDeque());
    }

    public C0729c getDownloadService() {
        return this.mDownloadService;
    }

    public Intent getServiceIntent() {
        return ConnectionHelper.getDownloadServiceIntent();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: Cd.a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: Cd.a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: Cd.a} */
    /* JADX WARNING: type inference failed for: r3v9, types: [java.lang.Object, Cd.a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onConnected(android.content.ComponentName r3, android.os.IBinder r4) {
        /*
            r2 = this;
            java.lang.String r3 = "onServiceConnected"
            java.lang.String r0 = "ScsApi@ModelDownloaderExecutor"
            com.samsung.android.sdk.scs.base.utils.Log.d(r0, r3)
            int r3 = Cd.C0728b.f3324a
            if (r4 != 0) goto L_0x000d
            r3 = 0
            goto L_0x0023
        L_0x000d:
            java.lang.String r3 = "com.samsung.android.visual.ai.sdkcommon.cloudcore.IDownloadService"
            android.os.IInterface r3 = r4.queryLocalInterface(r3)
            if (r3 == 0) goto L_0x001c
            boolean r1 = r3 instanceof Cd.C0729c
            if (r1 == 0) goto L_0x001c
            Cd.c r3 = (Cd.C0729c) r3
            goto L_0x0023
        L_0x001c:
            Cd.a r3 = new Cd.a
            r3.<init>()
            r3.f3323a = r4
        L_0x0023:
            r2.mDownloadService = r3
            Cd.a r3 = (Cd.C0727a) r3     // Catch:{ RemoteException -> 0x0030 }
            android.os.IBinder r3 = r3.f3323a     // Catch:{ RemoteException -> 0x0030 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.downloader.ModelDownloaderExecutor.onConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public void onDisconnected(ComponentName componentName) {
        Log.d(TAG, "onServiceDisconnected " + componentName);
        this.mDownloadService = null;
    }
}
