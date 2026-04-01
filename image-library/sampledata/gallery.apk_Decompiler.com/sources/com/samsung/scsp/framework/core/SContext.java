package com.samsung.scsp.framework.core;

import Ad.C0720a;
import W.a;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.identity.AccountInfoSupplier;
import com.samsung.scsp.framework.core.identity.DeviceIdSupplier;
import com.samsung.scsp.framework.core.identity.E2eeInfoSupplier;
import com.samsung.scsp.framework.core.identity.PushInfoSupplier;
import com.samsung.scsp.framework.core.network.NetworkFunction;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SContext {
    AccountInfoSupplier accountInfoSupplier;
    private String appId;
    Supplier<String> appVersionSupplier;
    DeviceIdSupplier deviceIdSupplier;
    E2eeInfoSupplier e2eeInfoSupplier;
    NetworkFunction networkFunction;
    PushInfoSupplier pushInfoSupplier;
    ScspConfig scspConfig;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final SContext scontext = new SContext();

        private LazyHolder() {
        }
    }

    public static SContext getInstance() {
        return LazyHolder.scontext;
    }

    public static void initialize(String str, ScspSuppliers scspSuppliers) {
        SContext access$000 = LazyHolder.scontext;
        access$000.appId = str;
        if (scspSuppliers != null) {
            access$000.accountInfoSupplier = scspSuppliers.accountInfoSupplier;
            access$000.pushInfoSupplier = scspSuppliers.pushInfoSupplier;
            access$000.deviceIdSupplier = scspSuppliers.deviceIdSupplier;
            access$000.e2eeInfoSupplier = scspSuppliers.e2eeInfoSupplier;
            access$000.networkFunction = scspSuppliers.networkFunction;
            access$000.scspConfig = scspSuppliers.scspConfig;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$new$1() {
        return (String) FaultBarrier.get(new a(28), "NONE").obj;
    }

    public AccountInfoSupplier getAccountInfoSupplier() {
        return this.accountInfoSupplier;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppVersion() {
        return this.appVersionSupplier.get();
    }

    public DeviceIdSupplier getDeviceIdSupplier() {
        return this.deviceIdSupplier;
    }

    public E2eeInfoSupplier getE2eeInfoSupplier() {
        return this.e2eeInfoSupplier;
    }

    public PushInfoSupplier getPushInfoSupplier() {
        return this.pushInfoSupplier;
    }

    public NetworkFunction getUrlStreamHandlerFactorySupplier() {
        return this.networkFunction;
    }

    private SContext() {
        this.appVersionSupplier = new C0720a(21);
    }
}
