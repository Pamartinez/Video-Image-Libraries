package com.samsung.scsp.framework.core.identity;

import android.content.Context;
import android.os.Build;
import com.samsung.scsp.common.ContextFactory;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.BuildConfig;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.SContextHolder;
import com.samsung.scsp.framework.core.decorator.SdkConfig;
import com.samsung.scsp.framework.core.util.DeviceUtil;
import com.samsung.scsp.framework.core.util.StringUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class IdentityImpl {
    private final DeviceInfoManager device;
    private final E2eeInfoManager e2ee;
    private final Logger logger = Logger.get("IdentityImpl");
    private final PushInfoManager push;
    private final Registration registration;
    private final Token token;

    public IdentityImpl() {
        SContextHolder sContextHolder = new SContextHolder(BuildConfig.LIBRARY_PACKAGE_NAME, "1.1.0000", false, SdkConfig.Domain.play);
        this.registration = new Registration(new RegistrationApiImpl(sContextHolder));
        this.token = new Token(new TokenApiImpl(sContextHolder));
        UpdateApiImpl updateApiImpl = new UpdateApiImpl(sContextHolder, new c(this, 3));
        this.push = new PushInfoManager(updateApiImpl);
        this.device = new DeviceInfoManager(updateApiImpl);
        this.e2ee = new E2eeInfoManager(updateApiImpl);
    }

    private void checkRegister(boolean z) {
        this.registration.register(z);
    }

    private void checkSakUid() {
        E2eeInfoSupplier e2eeInfoSupplier;
        if (ScspCorePreferences.get().sakUid.get().isEmpty() && (e2eeInfoSupplier = SContext.getInstance().getE2eeInfoSupplier()) != null) {
            FaultBarrier.run(new b(2, this, e2eeInfoSupplier));
        }
    }

    private void checkToken(boolean z) {
        this.token.get(z);
    }

    private void checkUserId(AccountInfoSupplier accountInfoSupplier) {
        if (accountInfoSupplier != null) {
            String str = (String) FaultBarrier.get(new f(0, accountInfoSupplier), "", true).obj;
            if (!ScspCorePreferences.get().hashedUid.get().equals(str)) {
                Logger logger2 = this.logger;
                logger2.e("Current uid is not same with stored uid. " + str);
                ScspCorePreferences.get().clear();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkDevice$5() {
        Context applicationContext = ContextFactory.getApplicationContext();
        this.device.accept(new DeviceInfo(DeviceUtil.getSimMcc(applicationContext), DeviceUtil.getSimMnc(applicationContext), Integer.toString(Build.VERSION.SDK_INT), DeviceUtil.getDeviceName(applicationContext), DeviceUtil.getOneUiVersion()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$checkE2eeType$2(E2eeInfoSupplier e2eeInfoSupplier) {
        return E2eeInfoSupplier.TYPES[e2eeInfoSupplier.getType()];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkE2eeType$3() {
        E2eeInfoSupplier e2eeInfoSupplier = SContext.getInstance().getE2eeInfoSupplier();
        if (e2eeInfoSupplier != null && !StringUtil.isEmpty(e2eeInfoSupplier.getSakUid())) {
            this.e2ee.accept((String) FaultBarrier.get(new e(e2eeInfoSupplier, 0), "non").obj);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$checkPush$4() {
        PushInfoSupplier pushInfoSupplier = SContext.getInstance().getPushInfoSupplier();
        AccountInfoSupplier accountInfoSupplier = SContext.getInstance().getAccountInfoSupplier();
        if (pushInfoSupplier == null || !pushInfoSupplier.has()) {
            return Boolean.FALSE;
        }
        if (accountInfoSupplier != null && accountInfoSupplier.has()) {
            return Boolean.valueOf(this.push.accept(new PushInfoList(pushInfoSupplier.getPushInfo())));
        }
        this.logger.e("Skipping push registration because there is no account information in AccountInfoSupplier");
        return Boolean.FALSE;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$checkSakUid$7(E2eeInfoSupplier e2eeInfoSupplier) {
        if (!StringUtil.isEmpty(e2eeInfoSupplier.getSakUid())) {
            this.logger.e("After successfully acquiring the sak uid, proceed to the registration.");
            ScspCorePreferences.get().clear();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signOut$0(String str) {
        this.registration.deregister(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signOut$1(String str) {
        FaultBarrier.run(new b(3, this, str));
    }

    public void checkDevice() {
        FaultBarrier.run(new c(this, 1));
    }

    public void checkE2eeType() {
        FaultBarrier.run(new c(this, 4));
    }

    public boolean checkPush() {
        return ((Boolean) FaultBarrier.get(new c(this, 2), Boolean.FALSE).obj).booleanValue();
    }

    public String getToken(boolean z) {
        String token2 = getToken(true, true);
        if (!StringUtil.isEmpty(token2)) {
            return token2;
        }
        if (!z) {
            String token3 = getToken(false, true);
            if (!StringUtil.isEmpty(token3)) {
                return token3;
            }
        }
        return getToken(z, false);
    }

    public void initialize(boolean z) {
        checkUserId(SContext.getInstance().getAccountInfoSupplier());
        checkSakUid();
        checkRegister(z);
        checkToken(z);
        checkE2eeType();
        FaultBarrier.run(new c(this, 0));
        checkDevice();
    }

    public void onRegistrationRequired() {
        ScspCorePreferences.get().clear();
        this.registration.register(false);
        this.token.get(false);
    }

    public void onUnauthenticatedForSA() {
        SContext.getInstance().getAccountInfoSupplier().onUnauthorized();
    }

    public void onUnauthenticatedForSC(String str) {
        this.token.refresh(str);
    }

    public void signOut() {
        String str = ScspCorePreferences.get().cloudToken.get();
        if (!StringUtil.isEmpty(str)) {
            new Thread(new d(this, str)).start();
            ScspCorePreferences.get().cloudToken.remove();
            ScspCorePreferences.get().cloudTokenExpiredOn.remove();
            ScspCorePreferences.get().isAccountRegistered.remove();
            ScspCorePreferences.get().hashedUid.remove();
            ScspCorePreferences.get().pushInfos.remove();
        }
    }

    private String getToken(boolean z, boolean z3) {
        if (z3) {
            return this.token.getCached(z);
        }
        return this.token.get(z);
    }
}
