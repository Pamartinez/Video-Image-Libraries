package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.error.FaultBarrier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements FaultBarrier.ThrowableSupplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ E2eeInfoSupplier e;

    public /* synthetic */ e(E2eeInfoSupplier e2eeInfoSupplier, int i2) {
        this.d = i2;
        this.e = e2eeInfoSupplier;
    }

    public final Object get() {
        int i2 = this.d;
        E2eeInfoSupplier e2eeInfoSupplier = this.e;
        switch (i2) {
            case 0:
                return IdentityImpl.lambda$checkE2eeType$2(e2eeInfoSupplier);
            default:
                return RegistrationApiImpl.lambda$makePayload$6(e2eeInfoSupplier);
        }
    }
}
