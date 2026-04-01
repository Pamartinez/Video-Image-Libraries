package com.samsung.scsp.framework.core.decorator;

import A4.Q;
import Ad.C0720a;
import O3.b;
import android.content.Context;
import android.util.Pair;
import c0.C0086a;
import com.google.gson.JsonObject;
import com.google.gson.internal.c;
import com.samsung.android.sdk.scs.ai.language.a;
import com.samsung.scsp.common.Invoker;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.SContextHolder;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.ApiControl;
import com.samsung.scsp.framework.core.decorator.SdkConfig;
import com.samsung.scsp.framework.core.identity.ScspIdentity;
import com.samsung.scsp.framework.core.listeners.ListenersHolder;
import com.samsung.scsp.framework.core.network.Network;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AbstractDecorator {
    private final Map<String, Object> FEATURES = new HashMap();
    protected ApiControl apiControl;
    private final Supplier<Context> context;
    protected Network network;
    protected SContextHolder scontextHolder;

    public AbstractDecorator(Class<? extends ApiControl> cls, SdkFeature... sdkFeatureArr) {
        boolean z;
        String str;
        String str2;
        SdkConfig.Domain domain;
        SdkConfig sdkConfig = (SdkConfig) getClass().getAnnotation(SdkConfig.class);
        SdkConfig.Domain domain2 = SdkConfig.Domain.play;
        sdkConfig = sdkConfig == null ? (SdkConfig) FaultBarrier.get(new a(12, this), null).obj : sdkConfig;
        if (sdkConfig != null) {
            str2 = sdkConfig.version();
            str = sdkConfig.name();
            z = sdkConfig.accountRequired();
            domain = sdkConfig.domain();
        } else {
            str = "none";
            z = true;
            SdkConfig.Domain domain3 = domain2;
            str2 = "0";
            domain = domain3;
        }
        Logger logger = Logger.get(getClass().getSimpleName());
        StringBuilder q = C0086a.q("name: ", str, ", version : ", str2, ", isAccountRequiredFeature = ");
        q.append(z);
        logger.i(q.toString());
        ScspIdentity.initialize(z);
        SContextHolder sContextHolder = new SContextHolder(str, str2, z, domain);
        this.scontextHolder = sContextHolder;
        this.network = sContextHolder.network;
        this.context = new C0720a(22);
        setupFeatures(sdkFeatureArr);
        if (cls != null) {
            FaultBarrier.run(new b(24, this, cls));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$getFeature$3(String str, Object obj) {
        Object obj2 = this.FEATURES.get(str);
        if (obj2 == null) {
            return obj;
        }
        return obj2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ SdkConfig lambda$new$0() {
        return (SdkConfig) getClass().getSuperclass().getAnnotation(SdkConfig.class);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ApiControl lambda$new$1(Constructor constructor) {
        return (ApiControl) constructor.newInstance((Object[]) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(Class cls) {
        Constructor declaredConstructor = cls.getDeclaredConstructor((Class[]) null);
        declaredConstructor.setAccessible(true);
        Logger.get(getClass().getSimpleName()).i(declaredConstructor.toString());
        this.apiControl = (ApiControl) FaultBarrier.get(new c(declaredConstructor), null).obj;
    }

    private void setupFeatures(SdkFeature... sdkFeatureArr) {
        if (sdkFeatureArr != null) {
            for (SdkFeature sdkFeature : sdkFeatureArr) {
                this.FEATURES.put(sdkFeature.name, sdkFeature.value);
            }
        }
    }

    public void close() {
        Network network2 = this.network;
        if (network2 != null) {
            network2.close();
        }
    }

    @SafeVarargs
    public final ApiContext createApiContext(String str, JsonObject jsonObject, String str2, Invoker invoker, Pair<String, Object>... pairArr) {
        ApiContext create = ApiContext.create(this.scontextHolder, str);
        if (pairArr != null) {
            for (Pair<String, Object> pair : pairArr) {
                Object obj = pair.second;
                if (obj != null) {
                    create.parameters.put((String) pair.first, obj);
                }
            }
        }
        Pair[] commonParameter = getCommonParameter();
        if (commonParameter != null) {
            for (Pair pair2 : commonParameter) {
                Object obj2 = pair2.second;
                if (obj2 != null) {
                    create.parameters.put((String) pair2.first, obj2);
                }
            }
        }
        if (jsonObject != null) {
            create.payload = jsonObject.toString();
        }
        if (str2 != null) {
            create.etag = str2;
        }
        if (invoker != null) {
            SContextHolder sContextHolder = this.scontextHolder;
            invoker.set(sContextHolder.applicationId, sContextHolder.version);
            create.invoker = invoker.toString();
        }
        return create;
    }

    @SafeVarargs
    public final <T> T execute(String str, JsonObject jsonObject, String str2, Pair<String, Object>... pairArr) {
        return C0086a.g(ListenersHolder.create(), this.apiControl, createApiContext(str, jsonObject, str2, (Invoker) null, pairArr));
    }

    public Pair<String, Object>[] getCommonParameter() {
        return null;
    }

    public Context getContext() {
        return this.context.get();
    }

    public <T> T getFeature(String str, T t) {
        return FaultBarrier.get(new Q((Object) this, (Object) str, (Object) t, 17), t, true).obj;
    }

    public void close(int i2) {
        Network network2 = this.network;
        if (network2 != null) {
            network2.close(i2);
        }
    }

    @SafeVarargs
    public final <T> T execute(String str, JsonObject jsonObject, String str2, Invoker invoker, Pair<String, Object>... pairArr) {
        return C0086a.g(ListenersHolder.create(), this.apiControl, createApiContext(str, jsonObject, str2, invoker, pairArr));
    }
}
