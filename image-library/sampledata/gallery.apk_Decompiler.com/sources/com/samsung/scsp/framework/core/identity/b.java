package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.SContextHolder;
import java.io.InputStream;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements FaultBarrier.ThrowableSupplier, FaultBarrier.ThrowableRunnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ b(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public Object get() {
        switch (this.d) {
            case 0:
                return ((IdentityHeader) this.e).lambda$get$0((SContextHolder) this.f);
            default:
                return ((DeviceId) this.e).lambda$generateStrongDeviceIDHash$0((String) this.f);
        }
    }

    public void run() {
        switch (this.d) {
            case 1:
                IdentityHeader.lambda$get$1((E2eeInfoSupplier) this.e, (HashMap) this.f);
                return;
            case 2:
                ((IdentityImpl) this.e).lambda$checkSakUid$7((E2eeInfoSupplier) this.f);
                return;
            case 3:
                ((IdentityImpl) this.e).lambda$signOut$0((String) this.f);
                return;
            default:
                ((RegistrationApiImpl) this.e).lambda$register$2((InputStream) this.f);
                return;
        }
    }
}
