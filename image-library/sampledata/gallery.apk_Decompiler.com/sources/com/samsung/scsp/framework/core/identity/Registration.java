package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.error.Result;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.ScspException;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Registration {
    private final Logger logger = Logger.get("Registration");
    private final RegistrationApiImpl registrationApi;

    public Registration(RegistrationApiImpl registrationApiImpl) {
        this.registrationApi = registrationApiImpl;
    }

    private void handle(FaultBarrier.ThrowableRunnable throwableRunnable) {
        Result run = FaultBarrier.run(throwableRunnable);
        if (run.rcode == 40100001) {
            ScspIdentity.onUnauthenticatedForSA();
        }
        if (!run.success) {
            throw new ScspException(run.rcode, run.rmsg);
        }
    }

    private void verify(boolean z) {
        if (z) {
            AccountInfoSupplier accountInfoSupplier = SContext.getInstance().getAccountInfoSupplier();
            if (accountInfoSupplier == null || !accountInfoSupplier.has()) {
                throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "Not support your feature when accountInfo is not valid");
            } else if (!SContext.getInstance().getAccountInfoSupplier().has()) {
                ScspCorePreferences.get().isAccountRegistered.remove();
                ScspCorePreferences.get().hashedUid.remove();
            }
        }
    }

    public void deregister(String str) {
        this.logger.i("deregister");
        this.registrationApi.deregister(str);
    }

    public void register(boolean z) {
        this.logger.i("register");
        verify(z);
        if (z) {
            if (ScspCorePreferences.get().isAccountRegistered.get().booleanValue()) {
                this.logger.i("Already user registered.");
                return;
            }
        } else if (ScspCorePreferences.get().isDeviceRegistered.get().booleanValue()) {
            this.logger.i("Already device registered.");
            return;
        }
        RegistrationApiImpl registrationApiImpl = this.registrationApi;
        Objects.requireNonNull(registrationApiImpl);
        handle(new j(registrationApiImpl));
    }
}
