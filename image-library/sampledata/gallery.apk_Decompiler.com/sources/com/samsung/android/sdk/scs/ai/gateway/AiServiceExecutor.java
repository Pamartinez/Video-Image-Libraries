package com.samsung.android.sdk.scs.ai.gateway;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutorFactory;
import com.samsung.android.sdk.scs.base.connection.ServiceExecutor;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiServiceExecutor<T extends IInterface> extends ServiceExecutor {
    private static final String TAG = "AiServiceExecutor";
    protected final Context context;
    private final Function<IBinder, T> creator;
    /* access modifiers changed from: private */
    public final IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        public void binderDied() {
            Log.d(AiServiceExecutor.TAG, "binderDied deathRecipient callback");
            AiServiceExecutor.this.service.asBinder().unlinkToDeath(AiServiceExecutor.this.deathRecipient, 0);
        }
    };
    /* access modifiers changed from: private */
    public T service;
    private final String serviceAction;
    private final String servicePackage;
    private final AiServiceExecutorFactory.ServiceType serviceType;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AiServiceExecutor(android.content.Context r9, com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutorFactory.ServiceType r10, java.util.function.Function<android.os.IBinder, T> r11, java.lang.String r12, java.lang.String r13) {
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
            com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutor$1 r8 = new com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutor$1
            r8.<init>()
            r0.deathRecipient = r8
            android.content.Context r8 = r1.getApplicationContext()
            r0.context = r8
            r0.serviceType = r10
            r0.creator = r11
            r0.serviceAction = r12
            r0.servicePackage = r13
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutor.<init>(android.content.Context, com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutorFactory$ServiceType, java.util.function.Function, java.lang.String, java.lang.String):void");
    }

    public T getService() {
        return this.service;
    }

    public Intent getServiceIntent() {
        Intent intent = new Intent(this.serviceAction);
        intent.setPackage(this.servicePackage);
        return intent;
    }

    public String getServicePackage() {
        return this.servicePackage;
    }

    public AiServiceExecutorFactory.ServiceType getServiceType() {
        return this.serviceType;
    }

    public void onConnected(ComponentName componentName, IBinder iBinder) {
        Log.d(TAG, "onServiceConnected");
        T t = (IInterface) new AiServiceFactory(this.creator).createService(iBinder);
        this.service = t;
        try {
            t.asBinder().linkToDeath(this.deathRecipient, 0);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException");
            e.printStackTrace();
        }
    }

    public void onDisconnected(ComponentName componentName) {
        this.service = null;
    }
}
