package com.samsung.android.sdk.scs.ai.gateway;

import android.content.Context;
import android.os.IInterface;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutorFactory;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AiServiceExecutorContainer<AppInfo> {
    private static final String TAG = "AiServiceExecutorContainer";
    protected final Context mContext;
    protected final Map<AiServiceExecutorFactory.ServiceType, AiServiceExecutor<? extends IInterface>> mServiceExecutorMap = new EnumMap(AiServiceExecutorFactory.ServiceType.class);

    public AiServiceExecutorContainer(Context context) {
        this.mContext = context;
    }

    public abstract AiServiceExecutor<? extends IInterface> createServiceExecutor(AiServiceExecutorFactory.ServiceType serviceType);

    public Context getContext() {
        return this.mContext;
    }

    public AiServiceExecutor<? extends IInterface> getServiceExecutor(AiServiceExecutorFactory.ServiceType serviceType) {
        if (serviceType != null) {
            AiServiceExecutor<? extends IInterface> aiServiceExecutor = this.mServiceExecutorMap.get(serviceType);
            if (aiServiceExecutor != null) {
                return aiServiceExecutor;
            }
            AiServiceExecutor<? extends IInterface> createServiceExecutor = createServiceExecutor(serviceType);
            this.mServiceExecutorMap.put(serviceType, createServiceExecutor);
            return createServiceExecutor;
        }
        throw new IllegalArgumentException("ServiceType is null.");
    }

    public abstract AiServiceExecutorFactory.ServiceType getServiceType(AppInfo appinfo);

    public void releaseExecutor(Map<AiServiceExecutorFactory.ServiceType, AiServiceExecutor<? extends IInterface>> map, AiServiceExecutorFactory.ServiceType serviceType) {
        AiServiceExecutor aiServiceExecutor = map.get(serviceType);
        if (aiServiceExecutor != null) {
            Log.i(TAG, "release: " + aiServiceExecutor.isConnected());
            aiServiceExecutor.deInit();
        }
        map.remove(serviceType);
    }

    public void releaseExecutors(Map<AiServiceExecutorFactory.ServiceType, AiServiceExecutor<? extends IInterface>> map) {
        for (AiServiceExecutorFactory.ServiceType releaseExecutor : map.keySet()) {
            releaseExecutor(map, releaseExecutor);
        }
    }
}
