package com.samsung.android.sdk.scs.ai.image;

import Bc.a;
import Bc.c;
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
public class ImageServiceExecutor extends ServiceExecutor {
    private static final String TAG = "ScsApi@ImageServiceExecutor";
    /* access modifiers changed from: private */
    public IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        public void binderDied() {
            Log.d(ImageServiceExecutor.TAG, "binderDied deathRecipient callback");
            ((a) ImageServiceExecutor.this.mImageService).f2785a.unlinkToDeath(ImageServiceExecutor.this.deathRecipient, 0);
        }
    };
    /* access modifiers changed from: private */
    public c mImageService;

    public ImageServiceExecutor(Context context) {
        super(context, 1, 1, 60, TimeUnit.SECONDS, (BlockingQueue<Runnable>) new LinkedBlockingDeque());
    }

    public c getImageService() {
        return this.mImageService;
    }

    public Intent getServiceIntent() {
        return ConnectionHelper.getVisionServiceIntent();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: Bc.a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: Bc.a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: Bc.a} */
    /* JADX WARNING: type inference failed for: r3v9, types: [java.lang.Object, Bc.a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onConnected(android.content.ComponentName r3, android.os.IBinder r4) {
        /*
            r2 = this;
            java.lang.String r3 = "onServiceConnected"
            java.lang.String r0 = "ScsApi@ImageServiceExecutor"
            com.samsung.android.sdk.scs.base.utils.Log.d(r0, r3)
            int r3 = Bc.b.f2786a
            if (r4 != 0) goto L_0x000d
            r3 = 0
            goto L_0x0023
        L_0x000d:
            java.lang.String r3 = "com.samsung.android.scs.ai.sdkcommon.image.IImageService"
            android.os.IInterface r3 = r4.queryLocalInterface(r3)
            if (r3 == 0) goto L_0x001c
            boolean r1 = r3 instanceof Bc.c
            if (r1 == 0) goto L_0x001c
            Bc.c r3 = (Bc.c) r3
            goto L_0x0023
        L_0x001c:
            Bc.a r3 = new Bc.a
            r3.<init>()
            r3.f2785a = r4
        L_0x0023:
            r2.mImageService = r3
            Bc.a r3 = (Bc.a) r3     // Catch:{ RemoteException -> 0x0030 }
            android.os.IBinder r3 = r3.f2785a     // Catch:{ RemoteException -> 0x0030 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.image.ImageServiceExecutor.onConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public void onDisconnected(ComponentName componentName) {
        Log.d(TAG, "onServiceDisconnected " + componentName);
        this.mImageService = null;
    }
}
