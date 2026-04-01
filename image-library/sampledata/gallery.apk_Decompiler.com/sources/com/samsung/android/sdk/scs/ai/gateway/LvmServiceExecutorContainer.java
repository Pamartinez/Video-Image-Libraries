package com.samsung.android.sdk.scs.ai.gateway;

import android.content.Context;
import android.os.IInterface;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutorFactory;
import com.samsung.android.sdk.scs.ai.gateway.executor.TemplateContainer;
import com.samsung.android.sdk.scs.ai.visual.RequestType;
import com.samsung.android.sdk.scs.ai.visual.VisualAppInfo;
import com.samsung.android.sdk.scs.base.tasks.Task;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LvmServiceExecutorContainer extends AiServiceExecutorContainer<VisualAppInfo> implements TemplateContainer<Boolean> {
    protected final Map<AiServiceExecutorFactory.ServiceType, AiServiceExecutor<? extends IInterface>> mCancelServiceExecutorMap = new EnumMap(AiServiceExecutorFactory.ServiceType.class);

    public LvmServiceExecutorContainer(Context context) {
        super(context);
    }

    public abstract AiServiceExecutor<? extends IInterface> createCancelServiceExecutor(AiServiceExecutorFactory.ServiceType serviceType);

    public AiServiceExecutor<? extends IInterface> getCancelServiceExecutor(AiServiceExecutorFactory.ServiceType serviceType) {
        if (serviceType != null) {
            AiServiceExecutor<? extends IInterface> aiServiceExecutor = this.mCancelServiceExecutorMap.get(serviceType);
            if (aiServiceExecutor != null) {
                return aiServiceExecutor;
            }
            AiServiceExecutor<? extends IInterface> createCancelServiceExecutor = createCancelServiceExecutor(serviceType);
            this.mCancelServiceExecutorMap.put(serviceType, createCancelServiceExecutor);
            return createCancelServiceExecutor;
        }
        throw new IllegalArgumentException("ServiceType is null.");
    }

    public Task<Boolean> release() {
        releaseExecutors(this.mServiceExecutorMap);
        releaseExecutors(this.mCancelServiceExecutorMap);
        return null;
    }

    public AiServiceExecutorFactory.ServiceType getServiceType(VisualAppInfo visualAppInfo) {
        return AiServiceExecutorFactory.ServiceType.CLOUD_CORE;
    }

    public AiServiceExecutorFactory.ServiceType getServiceType(RequestType requestType) {
        if (RequestType.ONDEVICE.equals(requestType)) {
            return AiServiceExecutorFactory.ServiceType.AI_CORE;
        }
        return AiServiceExecutorFactory.ServiceType.CLOUD_CORE;
    }
}
