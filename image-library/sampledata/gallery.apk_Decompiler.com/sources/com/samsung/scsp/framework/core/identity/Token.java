package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.error.Response;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.util.StringUtil;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Token {
    private final Logger logger = Logger.get("Token");
    private final TokenApiImpl tokenApi;

    public Token(TokenApiImpl tokenApiImpl) {
        this.tokenApi = tokenApiImpl;
    }

    private void clearToken(String str) {
        if (StringUtil.equals(str, ScspCorePreferences.get().cloudToken.get())) {
            ScspCorePreferences.get().cloudToken.remove();
            ScspCorePreferences.get().cloudTokenExpiredOn.remove();
            this.logger.i("Remove cloudToken");
        } else if (StringUtil.equals(str, ScspCorePreferences.get().deviceToken.get())) {
            ScspCorePreferences.get().deviceToken.remove();
            ScspCorePreferences.get().deviceTokenExpiredOn.remove();
            this.logger.i("Remove deviceToken");
        }
    }

    private String handle(FaultBarrier.ThrowableSupplier<String> throwableSupplier) {
        return handle(throwableSupplier, false);
    }

    private void verify(boolean z) {
        if (z) {
            AccountInfoSupplier accountInfoSupplier = SContext.getInstance().getAccountInfoSupplier();
            if (accountInfoSupplier == null || !accountInfoSupplier.has()) {
                throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "Not support your feature when accountInfo is not valid");
            } else if (!SContext.getInstance().getAccountInfoSupplier().has()) {
                ScspCorePreferences.get().cloudToken.remove();
                ScspCorePreferences.get().cloudTokenExpiredOn.remove();
                ScspCorePreferences.get().hashedUid.remove();
            }
        }
    }

    public String get(boolean z) {
        this.logger.i("Get token");
        verify(z);
        String cached = getCached(z);
        if (!StringUtil.isEmpty(cached)) {
            return cached;
        }
        TokenApiImpl tokenApiImpl = this.tokenApi;
        Objects.requireNonNull(tokenApiImpl);
        return handle(new f(2, tokenApiImpl));
    }

    public String getCached(boolean z) {
        String str = ScspCorePreferences.get().cloudToken.get();
        if (!StringUtil.isEmpty(str)) {
            if (ScspCorePreferences.get().cloudTokenExpiredOn.get().longValue() > System.currentTimeMillis()) {
                this.logger.i("CloudToken is valid");
                return str;
            }
            ScspCorePreferences.get().cloudToken.remove();
            ScspCorePreferences.get().cloudTokenExpiredOn.remove();
            this.logger.i("CloudToken is expired");
        }
        String str2 = ScspCorePreferences.get().deviceToken.get();
        if (StringUtil.isEmpty(str2) || z) {
            return null;
        }
        if (ScspCorePreferences.get().deviceTokenExpiredOn.get().longValue() > System.currentTimeMillis()) {
            this.logger.i("DeviceToken is valid");
            return str2;
        }
        ScspCorePreferences.get().deviceToken.remove();
        ScspCorePreferences.get().deviceTokenExpiredOn.remove();
        this.logger.i("DeviceToken is expired");
        return null;
    }

    public void refresh(String str) {
        this.logger.i("Refresh token");
        clearToken(str);
        TokenApiImpl tokenApiImpl = this.tokenApi;
        Objects.requireNonNull(tokenApiImpl);
        handle(new f(2, tokenApiImpl));
    }

    public void updateAccount() {
        this.logger.i("AccountInfo updated - issue token");
        verify(true);
        TokenApiImpl tokenApiImpl = this.tokenApi;
        Objects.requireNonNull(tokenApiImpl);
        handle(new f(2, tokenApiImpl), true);
    }

    private String handle(FaultBarrier.ThrowableSupplier<String> throwableSupplier, boolean z) {
        Response<String> response = FaultBarrier.get(throwableSupplier, null);
        if (!z && response.rcode == 40100001) {
            ScspIdentity.onUnauthenticatedForSA();
            updateAccount();
        }
        if (response.success) {
            return (String) response.obj;
        }
        throw new ScspException(response.rcode, response.rmsg);
    }
}
