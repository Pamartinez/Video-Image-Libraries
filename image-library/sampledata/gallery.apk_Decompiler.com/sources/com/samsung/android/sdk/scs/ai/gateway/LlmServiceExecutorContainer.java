package com.samsung.android.sdk.scs.ai.gateway;

import android.content.Context;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutorFactory;
import com.samsung.android.sdk.scs.ai.gateway.executor.VoidContainer;
import com.samsung.android.sdk.scs.ai.language.AppInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LlmServiceExecutorContainer extends AiServiceExecutorContainer<AppInfo> implements VoidContainer {

    /* renamed from: com.samsung.android.sdk.scs.ai.gateway.LlmServiceExecutorContainer$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$scs$ai$language$AppInfo$RequestType;

        static {
            int[] iArr = new int[AppInfo.RequestType.values().length];
            $SwitchMap$com$samsung$android$sdk$scs$ai$language$AppInfo$RequestType = iArr;
            try {
                iArr[AppInfo.RequestType.ONDEVICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public LlmServiceExecutorContainer(Context context) {
        super(context);
    }

    public void release() {
        releaseExecutors(this.mServiceExecutorMap);
    }

    public AiServiceExecutorFactory.ServiceType getServiceType(AppInfo appInfo) {
        if (appInfo == null) {
            return AiServiceExecutorFactory.ServiceType.SIVS;
        }
        if (AnonymousClass1.$SwitchMap$com$samsung$android$sdk$scs$ai$language$AppInfo$RequestType[appInfo.getRequestType().ordinal()] != 1) {
            return AiServiceExecutorFactory.ServiceType.SIVS;
        }
        return AiServiceExecutorFactory.ServiceType.AI_CORE;
    }
}
