package com.samsung.android.sdk.scs.ai.gateway;

import W.a;
import android.content.Context;
import android.os.IInterface;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutorFactory;
import com.samsung.android.sdk.scs.base.utils.BaseConstants;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LvmServiceExecutorFactory extends AiServiceExecutorFactory implements ILvmServiceExecutorFactory {

    /* renamed from: com.samsung.android.sdk.scs.ai.gateway.LvmServiceExecutorFactory$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$scs$ai$gateway$AiServiceExecutorFactory$ServiceType;

        static {
            int[] iArr = new int[AiServiceExecutorFactory.ServiceType.values().length];
            $SwitchMap$com$samsung$android$sdk$scs$ai$gateway$AiServiceExecutorFactory$ServiceType = iArr;
            try {
                iArr[AiServiceExecutorFactory.ServiceType.AI_CORE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public LvmServiceExecutorFactory(Context context) {
        super(context);
    }

    public AiServiceExecutor<? extends IInterface> createImageEditor(AiServiceExecutorFactory.ServiceType serviceType, String str) {
        return createService(serviceType, str, BaseConstants.SERVICE_ACTION.ACTION_ON_DEVICE_SERVICE, new a(21), BaseConstants.SERVICE_ACTION.ACTION_IMAGE_EDITOR_SERVICE, new a(22));
    }

    public <T extends IInterface> AiServiceExecutor<T> createService(AiServiceExecutorFactory.ServiceType serviceType, String str, String str2, AiServiceExecutorFactory.ServiceStubFactory<T> serviceStubFactory, String str3, AiServiceExecutorFactory.ServiceStubFactory<T> serviceStubFactory2) {
        AiServiceExecutorFactory.ServiceStubFactory<T> serviceStubFactory3 = serviceStubFactory;
        String str4 = str3;
        String servicePackage = getServicePackage(serviceType);
        if (AiServiceExecutorFactory.ServiceType.AI_CORE.equals(serviceType)) {
            AiServiceExecutorFactory.ServiceStubFactory<T> serviceStubFactory4 = serviceStubFactory3;
            AiServiceExecutorFactory.ServiceType serviceType2 = serviceType;
            Context context = this.context;
            Objects.requireNonNull(serviceStubFactory4);
            AiServiceExecutorFactory.ServiceStubFactory<T> serviceStubFactory5 = serviceStubFactory4;
            return new AiServiceExecutor<>(context, serviceType2, new b(2, serviceStubFactory5), str2, servicePackage);
        }
        AiServiceExecutorFactory.ServiceType serviceType3 = serviceType;
        Context context2 = this.context;
        Objects.requireNonNull(serviceStubFactory2);
        return new AiServiceExecutor<>(context2, serviceType3, new b(2, serviceStubFactory2), str4, servicePackage);
    }

    public String getServicePackage(AiServiceExecutorFactory.ServiceType serviceType) {
        if (AnonymousClass1.$SwitchMap$com$samsung$android$sdk$scs$ai$gateway$AiServiceExecutorFactory$ServiceType[serviceType.ordinal()] != 1) {
            return BaseConstants.PACKAGE_INFO.PACKAGE_VISUAL_CLOUD_CORE_SERVICES;
        }
        return BaseConstants.PACKAGE_INFO.PACKAGE_AICORE_SERVICES;
    }
}
