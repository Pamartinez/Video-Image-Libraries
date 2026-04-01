package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.error.FaultBarrier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements FaultBarrier.ThrowableRunnable, FaultBarrier.ThrowableSupplier, FaultBarrier.ThrowableFunction {
    public final /* synthetic */ int d;
    public final /* synthetic */ IdentityImpl e;

    public /* synthetic */ c(IdentityImpl identityImpl, int i2) {
        this.d = i2;
        this.e = identityImpl;
    }

    public Object apply(Object obj) {
        return this.e.getToken(((Boolean) obj).booleanValue());
    }

    public Object get() {
        return this.e.lambda$checkPush$4();
    }

    public void run() {
        int i2 = this.d;
        IdentityImpl identityImpl = this.e;
        switch (i2) {
            case 0:
                identityImpl.checkPush();
                return;
            case 1:
                identityImpl.lambda$checkDevice$5();
                return;
            case 4:
                identityImpl.lambda$checkE2eeType$3();
                return;
            default:
                identityImpl.checkDevice();
                return;
        }
    }
}
