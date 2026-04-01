package com.samsung.scsp.framework.core;

import com.samsung.scsp.framework.core.identity.AccountInfoSupplier;
import com.samsung.scsp.framework.core.identity.DeviceIdSupplier;
import com.samsung.scsp.framework.core.identity.E2eeInfoSupplier;
import com.samsung.scsp.framework.core.identity.PushInfoSupplier;
import com.samsung.scsp.framework.core.network.NetworkFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScspSuppliers {
    final AccountInfoSupplier accountInfoSupplier;
    final DeviceIdSupplier deviceIdSupplier;
    final E2eeInfoSupplier e2eeInfoSupplier;
    final NetworkFunction networkFunction;
    final PushInfoSupplier pushInfoSupplier;
    final ScspConfig scspConfig;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public AccountInfoSupplier accountInfoSupplier;
        /* access modifiers changed from: private */
        public DeviceIdSupplier deviceIdSupplier;
        /* access modifiers changed from: private */
        public E2eeInfoSupplier e2eeInfoSupplier;
        /* access modifiers changed from: private */
        public NetworkFunction networkFunction;
        /* access modifiers changed from: private */
        public PushInfoSupplier pushInfoSupplier;
        /* access modifiers changed from: private */
        public ScspConfig scspConfig;

        public ScspSuppliers build() {
            return new ScspSuppliers(this);
        }

        public Builder with(AccountInfoSupplier accountInfoSupplier2) {
            this.accountInfoSupplier = accountInfoSupplier2;
            return this;
        }

        public Builder with(PushInfoSupplier pushInfoSupplier2) {
            this.pushInfoSupplier = pushInfoSupplier2;
            return this;
        }

        public Builder with(DeviceIdSupplier deviceIdSupplier2) {
            this.deviceIdSupplier = deviceIdSupplier2;
            return this;
        }

        public Builder with(E2eeInfoSupplier e2eeInfoSupplier2) {
            this.e2eeInfoSupplier = e2eeInfoSupplier2;
            return this;
        }

        public Builder with(NetworkFunction networkFunction2) {
            this.networkFunction = networkFunction2;
            return this;
        }

        public Builder with(ScspConfig scspConfig2) {
            this.scspConfig = scspConfig2;
            return this;
        }
    }

    private ScspSuppliers(Builder builder) {
        this.accountInfoSupplier = builder.accountInfoSupplier;
        this.pushInfoSupplier = builder.pushInfoSupplier;
        this.deviceIdSupplier = builder.deviceIdSupplier;
        this.e2eeInfoSupplier = builder.e2eeInfoSupplier;
        this.networkFunction = builder.networkFunction;
        this.scspConfig = builder.scspConfig;
    }
}
