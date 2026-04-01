package com.samsung.android.sdk.scs.ai.gateway;

import android.os.IInterface;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutorFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ILvmServiceExecutorFactory {
    AiServiceExecutor<? extends IInterface> createImageEditor(AiServiceExecutorFactory.ServiceType serviceType, String str);
}
