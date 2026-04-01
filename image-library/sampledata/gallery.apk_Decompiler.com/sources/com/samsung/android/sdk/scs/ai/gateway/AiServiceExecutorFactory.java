package com.samsung.android.sdk.scs.ai.gateway;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AiServiceExecutorFactory {
    protected final Context context;

    @FunctionalInterface
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ServiceStubFactory<T> {
        T createStub(IBinder iBinder);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ServiceType {
        AI_CORE,
        CLOUD_CORE,
        SIVS
    }

    public AiServiceExecutorFactory(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public abstract <T extends IInterface> AiServiceExecutor<T> createService(ServiceType serviceType, String str, String str2, ServiceStubFactory<T> serviceStubFactory, String str3, ServiceStubFactory<T> serviceStubFactory2);

    public abstract String getServicePackage(ServiceType serviceType);
}
