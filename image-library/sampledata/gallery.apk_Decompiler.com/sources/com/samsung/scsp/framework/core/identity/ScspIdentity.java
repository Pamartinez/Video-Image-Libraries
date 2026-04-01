package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.common.ContextFactory;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.util.StringUtil;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScspIdentity {
    private static boolean initialized = false;
    private static final Object lock = new Object();
    private static final Logger logger = Logger.get("ScspIdentity");

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final IdentityImpl identityImpl = new IdentityImpl();

        private LazyHolder() {
        }
    }

    private static void checkSetUp() {
        if (ContextFactory.getApplicationContext() == null || StringUtil.isEmpty(SContext.getInstance().getAppId())) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "SDK is not set up. please check if Scsp.setUp has been called.");
        }
    }

    public static String getToken(boolean z) {
        String token;
        checkSetUp();
        if (!initialized) {
            initialize(z);
        }
        synchronized (lock) {
            token = LazyHolder.identityImpl.getToken(z);
        }
        return token;
    }

    public static void initialize(boolean z) {
        checkSetUp();
        synchronized (lock) {
            if (z) {
                try {
                    AccountInfoSupplier accountInfoSupplier = SContext.getInstance().getAccountInfoSupplier();
                    if (accountInfoSupplier == null || !accountInfoSupplier.has()) {
                        throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "AccountInfoSupplier is null or doesn't have information.");
                    }
                } catch (ScspException e) {
                    if (e.rcode == 40100001) {
                        LazyHolder.identityImpl.initialize(z);
                        initialized = true;
                    } else {
                        throw e;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            LazyHolder.identityImpl.initialize(z);
            initialized = true;
        }
    }

    public static void onRegistrationRequired() {
        checkSetUp();
        synchronized (lock) {
            LazyHolder.identityImpl.onRegistrationRequired();
        }
    }

    public static void onUnauthenticatedForSA() {
        checkSetUp();
        synchronized (lock) {
            LazyHolder.identityImpl.onUnauthenticatedForSA();
        }
    }

    public static void onUnauthenticatedForSC(String str) {
        logger.i("onUnauthenticatedForSC");
        checkSetUp();
        if (!initialized) {
            initialize(true);
            return;
        }
        synchronized (lock) {
            LazyHolder.identityImpl.onUnauthenticatedForSC(str);
        }
    }

    public static void signOut() {
        initialized = false;
        LazyHolder.identityImpl.signOut();
    }

    public static void transaction(Runnable runnable) {
        synchronized (lock) {
            runnable.run();
        }
    }

    public static void updateDeviceInfo() {
        checkSetUp();
        synchronized (lock) {
            try {
                if (initialized) {
                    IdentityImpl access$000 = LazyHolder.identityImpl;
                    Objects.requireNonNull(access$000);
                    FaultBarrier.run(new c(access$000, 5));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void updatePush() {
        checkSetUp();
        if (SContext.getInstance().getPushInfoSupplier() == null) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "PushInfoSupplier is null.");
        } else if (SContext.getInstance().getAccountInfoSupplier() == null || !SContext.getInstance().getAccountInfoSupplier().has()) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "getAccountInfoSupplier is null or there is no account information.");
        } else {
            synchronized (lock) {
                try {
                    if (!initialized) {
                        initialize(true);
                    } else {
                        LazyHolder.identityImpl.checkPush();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
