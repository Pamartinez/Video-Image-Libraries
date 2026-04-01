package com.samsung.android.sdk.mobileservice.auth;

import N2.j;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.samsung.android.sdk.mobileservice.SeMobileService;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.auth.IAccessTokenResultCallback;
import com.samsung.android.sdk.mobileservice.auth.IAuthCodeResultCallback;
import com.samsung.android.sdk.mobileservice.auth.IAuthResultCallback;
import com.samsung.android.sdk.mobileservice.auth.IDisclaimerAgreementForThirdPartyResultCallback;
import com.samsung.android.sdk.mobileservice.auth.IValidationResultCallback;
import com.samsung.android.sdk.mobileservice.auth.result.AccessTokenInfoResult;
import com.samsung.android.sdk.mobileservice.auth.result.AuthInfoResult;
import com.samsung.android.sdk.mobileservice.auth.result.DeviceAuthInfoResult;
import com.samsung.android.sdk.mobileservice.auth.result.ResultTokenInfo;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.mobileservice.common.api.SeMobileServiceApi;
import com.samsung.android.sdk.mobileservice.common.exception.NotConnectedException;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import com.samsung.scsp.framework.core.network.HeaderSetup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AuthApi extends SeMobileServiceApi {
    public static final String API_NAME = "AuthApi";
    public static final int SERVICE_ID_GALLERY_CONTENT_SHARING = 32;
    public static final int SERVICE_ID_PROFILE_SHARING = 0;
    public static final String SERVICE_NAME = "AuthService";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AccessTokenResultCallback {
        void onResult(AccessTokenInfoResult accessTokenInfoResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AuthCodeResultCallback {
        void onResult(ResultTokenInfo resultTokenInfo);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AuthResultCallback {
        void onResult(AuthInfoResult authInfoResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BundleKey {
        public static final String AGREEMENT_PROCEDURE = "AgreementProcedure";
        public static final String ENFORCE_FTU = "enforceFtu";
        public static final String IS_LEGAL_POPUP_SUPPORTED = "isLegalPopupSupported";
        public static final String IS_NEEDED = "isNeeded";
        public static final String LEGACY_LEGAL_POPUP = "LegacyLegalPopup";
        public static final String SOCIAL_ABOUT_PAGE = "SocialAboutPage";
        public static final String SOCIAL_DISCLAIMER = "SocialDisclaimer";
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DisclaimerAgreementResultCallback {
        void onResult(BooleanResult booleanResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ValidationResultCallback {
        void onResult(BooleanResult booleanResult, boolean z, boolean z3, boolean z7, boolean z9);
    }

    public AuthApi(SeMobileServiceSession seMobileServiceSession) {
        super(seMobileServiceSession, "AuthApi");
    }

    private Intent getIntentToDisplay(String str) {
        return getIntentToDisplay(str, new Bundle());
    }

    private boolean isAppIdNullOrEmpty() {
        if (!TextUtils.isEmpty(getAppId())) {
            return false;
        }
        debugLog("appId is null or empty");
        return true;
    }

    private boolean isAvailableSocialService() {
        try {
            if (this.mSessionInstance.isAgentInstalled() && this.mSessionInstance.isAgentEnabled() && getSocialService() != null) {
                return true;
            }
            debugLog("isAvailableSocialService is false - AgentInstalled : " + this.mSessionInstance.isAgentInstalled() + " AgentEnabled : " + this.mSessionInstance.isAgentEnabled());
            return false;
        } catch (Exception unused) {
            debugLog("isAvailableSocialService is exception - AgentInstalled : " + this.mSessionInstance.isAgentInstalled() + " AgentEnabled : " + this.mSessionInstance.isAgentEnabled());
            return false;
        }
    }

    private Bundle isSomethingNeeded(String str) {
        if (!isAvailableSocialService()) {
            return null;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("what", str);
            return getSocialService().isSomethingNeeded(bundle);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    public BooleanResult getAccountValidation() {
        if (isAppIdNullOrEmpty()) {
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
        try {
            boolean accountValidation = getAuthService().getAccountValidation();
            debugLog("isAccountValid " + accountValidation + " ");
            return new BooleanResult(new CommonResultStatus(1), accountValidation);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new BooleanResult(new CommonResultStatus(-1), false);
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
    }

    public AuthInfoResult getAuthInfo() {
        AuthInfo authInfo;
        debugLog("getAuthInfo ");
        if (isAppIdNullOrEmpty()) {
            return new AuthInfoResult(new CommonResultStatus(-1, "appId is null or empty", ""), (AuthInfo) null);
        }
        try {
            if (getAuthService() == null) {
                debugLog("auth service is null ");
                return new AuthInfoResult(new CommonResultStatus(-1), (AuthInfo) null);
            }
            Bundle authInfoCached = getAuthService().getAuthInfoCached();
            if (authInfoCached != null) {
                if (authInfoCached.containsKey("account_id")) {
                    authInfo = new AuthInfo();
                    authInfo.setAccountId(authInfoCached.getString("account_id"));
                } else {
                    authInfo = null;
                }
                if (authInfoCached.containsKey("guid")) {
                    if (authInfo == null) {
                        authInfo = new AuthInfo();
                    }
                    authInfo.setGuid(authInfoCached.getString("guid"));
                }
                if (authInfoCached.containsKey("country_code")) {
                    if (authInfo == null) {
                        authInfo = new AuthInfo();
                    }
                    authInfo.setCountryCode(authInfoCached.getString("country_code"));
                }
                if (authInfoCached.containsKey(HeaderSetup.Key.MCC)) {
                    if (authInfo == null) {
                        authInfo = new AuthInfo();
                    }
                    authInfo.setMobileCountryCode(authInfoCached.getString(HeaderSetup.Key.MCC));
                }
                if (authInfoCached.containsKey("device_physical_address")) {
                    if (authInfo == null) {
                        authInfo = new AuthInfo();
                    }
                    authInfo.setDevicePhysicalAddress(authInfoCached.getString("device_physical_address"));
                }
                if (authInfoCached.containsKey("is_email_authenticated")) {
                    if (authInfo == null) {
                        authInfo = new AuthInfo();
                    }
                    authInfo.setEmailAddressAuthenticated(authInfoCached.getBoolean("is_email_authenticated"));
                }
                if (authInfoCached.containsKey("is_name_authenticated")) {
                    if (authInfo == null) {
                        authInfo = new AuthInfo();
                    }
                    authInfo.setRealNameAuthenticated(authInfoCached.getBoolean("is_name_authenticated"));
                }
                if (authInfoCached.containsKey("is_account_disclaimer_agreed")) {
                    if (authInfo == null) {
                        authInfo = new AuthInfo();
                    }
                    authInfo.setAccountDisclaimerAgreed(authInfoCached.getBoolean("is_account_disclaimer_agreed"));
                }
            } else {
                authInfo = null;
            }
            if (authInfo != null) {
                return new AuthInfoResult(new CommonResultStatus(1), authInfo);
            }
            debugLog("getAuthInfo is null");
            return new AuthInfoResult(new CommonResultStatus(1), (AuthInfo) null);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new AuthInfoResult(new CommonResultStatus(-1), (AuthInfo) null);
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return new AuthInfoResult(new CommonResultStatus(-1), (AuthInfo) null);
        }
    }

    @Deprecated
    public DeviceAuthInfoResult getDeviceAuthInfo() {
        Bundle bundle;
        DeviceAuthInfo deviceAuthInfo;
        debugLog("getDeviceAuthInfo ");
        if (isAppIdNullOrEmpty()) {
            return new DeviceAuthInfoResult(new CommonResultStatus(-1, "appId is null or empty", ""), (DeviceAuthInfo) null);
        }
        try {
            if (CommonUtils.isStandAloneSamsungAccountSupported(getContext())) {
                if (isAvailableSocialService()) {
                    bundle = getSocialService().getDeviceAuthInfoCached();
                } else {
                    bundle = null;
                }
            } else if (getAuthService() == null) {
                debugLog("auth service is null ");
                return new DeviceAuthInfoResult(new CommonResultStatus(-1), (DeviceAuthInfo) null);
            } else {
                bundle = getAuthService().getDeviceAuthInfoCached();
            }
            if (bundle != null) {
                if (bundle.containsKey("imsi")) {
                    deviceAuthInfo = new DeviceAuthInfo();
                    deviceAuthInfo.setImsi(bundle.getString("imsi"));
                } else {
                    deviceAuthInfo = null;
                }
                if (bundle.containsKey("msisdn")) {
                    if (deviceAuthInfo == null) {
                        deviceAuthInfo = new DeviceAuthInfo();
                    }
                    deviceAuthInfo.setMsisdn(bundle.getString("msisdn"));
                }
            } else {
                deviceAuthInfo = null;
            }
            if (deviceAuthInfo != null) {
                return new DeviceAuthInfoResult(new CommonResultStatus(1), deviceAuthInfo);
            }
            debugLog("getDeviceAuthInfo is null");
            return new DeviceAuthInfoResult(new CommonResultStatus(1), (DeviceAuthInfo) null);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new DeviceAuthInfoResult(new CommonResultStatus(-1), (DeviceAuthInfo) null);
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return new DeviceAuthInfoResult(new CommonResultStatus(-1), (DeviceAuthInfo) null);
        }
    }

    @Deprecated
    public BooleanResult getDisclaimerAgreementForSocial() {
        boolean disclaimerAgreementForSocial;
        boolean z = false;
        if (isAppIdNullOrEmpty()) {
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
        try {
            if (CommonUtils.isStandAloneSamsungAccountSupported(getContext())) {
                if (isAvailableSocialService()) {
                    disclaimerAgreementForSocial = getSocialService().getDisclaimerAgreementForSocial();
                }
                debugLog("getDisclaimerAgreementForSocial " + z + " ");
                return new BooleanResult(new CommonResultStatus(1), z);
            }
            disclaimerAgreementForSocial = getAuthService().getDisclaimerAgreementForSocial();
            z = disclaimerAgreementForSocial;
            debugLog("getDisclaimerAgreementForSocial " + z + " ");
            return new BooleanResult(new CommonResultStatus(1), z);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new BooleanResult(new CommonResultStatus(-1), false);
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
    }

    public String[] getEssentialServiceNames() {
        if (!CommonUtils.isStandAloneSamsungAccountSupported(getContext()) || !this.mSessionInstance.isAgentInstalled() || !this.mSessionInstance.isAgentEnabled()) {
            return new String[]{"AuthService"};
        }
        return new String[]{"AuthService", "SocialService"};
    }

    public Intent getIntentForAccountAccessTokenRequest(String str) {
        debugLog("getIntentForAccountDisclaimerAgreement : accountAppId=[" + getAppId() + "], expiredToken=[" + str + "] ");
        if (isAppIdNullOrEmpty()) {
            return null;
        }
        return getIntentForAccountAccessTokenRequest(getAppId(), str);
    }

    public Intent getIntentForAccountDisclaimerAgreement(boolean z) {
        debugLog("getIntentForAccountDisclaimerAgreement : accountAppId=[" + getAppId() + "], forThirdParty=[" + z + "] ");
        if (isAppIdNullOrEmpty()) {
            return null;
        }
        return getIntentForAccountDisclaimerAgreement(getAppId(), z);
    }

    public Intent getIntentForAccountPasswordConfirmation() {
        debugLog("getIntentForAccountPasswordConfirmation : accountAppId=[" + getAppId() + "] ");
        if (isAppIdNullOrEmpty()) {
            return null;
        }
        return getIntentForAccountPasswordConfirmation(getAppId());
    }

    public Intent getIntentForAccountPasswordConfirmationPopup() {
        debugLog("getIntentForAccountPasswordConfirmationPopup : accountAppId=[" + getAppId() + "] ");
        if (isAppIdNullOrEmpty()) {
            return null;
        }
        return getIntentForAccountPasswordConfirmationPopup(getAppId());
    }

    public Intent getIntentForAccountProfileModification() {
        debugLog("getIntentForAccountProfileModification : accountAppId=[" + getAppId() + "] ");
        if (isAppIdNullOrEmpty()) {
            return null;
        }
        return getIntentForAccountProfileModification(getAppId());
    }

    public Intent getIntentForAccountSetupWizard(boolean z) {
        debugLog("getIntentForAccountSetupWizard : isReactivationLockSupported=[" + z + "]");
        if (isAppIdNullOrEmpty()) {
            return null;
        }
        try {
            return getAuthService().getIntentForAccountSetupWizard(z);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    public Intent getIntentForAccountSignIn() {
        debugLog("getIntentForAccountSignIn");
        if (isAppIdNullOrEmpty()) {
            return null;
        }
        try {
            return getAuthService().getIntentForAccountSignIn();
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    public Intent getIntentForAccountSignInPopup() {
        debugLog("getIntentForAccountSignInPopup ");
        if (isAppIdNullOrEmpty()) {
            return null;
        }
        try {
            return getAuthService().getIntentForAccountSignInPopup();
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    public Intent getIntentForAccountValidationRequest(boolean z, boolean z3) {
        debugLog("getIntentForAccountDisclaimerAgreement : accountAppId=[" + getAppId() + "], realNameCheck=[" + z + "], validationResultOnly=[" + z3 + "] ");
        if (isAppIdNullOrEmpty()) {
            return null;
        }
        return getIntentForAccountValidationRequest(getAppId(), z, z3);
    }

    public Intent getIntentForSocialDisclaimerAgreement(boolean z, boolean z3) {
        debugLog("getIntentForSocialDisclaimerAgreement : isServiceOnRequired=[" + z + "], isSyncNowRequired=[" + z3 + "] ");
        if (isAppIdNullOrEmpty()) {
            return null;
        }
        try {
            if (CommonUtils.isStandAloneSamsungAccountSupported(getContext())) {
                return getIntentToDisplay(BundleKey.LEGACY_LEGAL_POPUP);
            }
            return getAuthService().getIntentForSocialDisclaimerAgreement(z, z3);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    public Intent getIntentForSocialDisclaimerDisplay() {
        debugLog("getIntentForSocialDisclaimerDisplay ");
        if (isAppIdNullOrEmpty() || !isSupportedSaAgentVersion(CommonConstants.SupportedApiMinVersion.VERSION_4_1)) {
            return null;
        }
        try {
            if (CommonUtils.isStandAloneSamsungAccountSupported(getContext())) {
                return getIntentToDisplay("SocialDisclaimer");
            }
            return getAuthService().getIntentForSocialTnC();
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    public Intent getIntentForSocialRegistrationInformation() {
        debugLog("getIntentForSocialRegistrationInformation ");
        if (isAppIdNullOrEmpty()) {
            return null;
        }
        try {
            if (CommonUtils.isStandAloneSamsungAccountSupported(getContext())) {
                return getIntentToDisplay(BundleKey.SOCIAL_ABOUT_PAGE);
            }
            return getAuthService().getIntentForSocialRegistrationInformation();
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    public Intent getIntentForSocialSignUp() {
        debugLog("getIntentForSocialSignUp ");
        if (isAppIdNullOrEmpty()) {
            return null;
        }
        try {
            if (CommonUtils.isStandAloneSamsungAccountSupported(getContext())) {
                return getIntentToDisplay(BundleKey.LEGACY_LEGAL_POPUP);
            }
            return getAuthService().getIntentForSocialSignUp();
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    @Deprecated
    public BooleanResult isServiceRegistered(int i2) {
        boolean z;
        boolean z3 = false;
        if (isAppIdNullOrEmpty()) {
            return new BooleanResult(new CommonResultStatus(-1, "appId is null or empty", ""), false);
        }
        try {
            if (CommonUtils.isStandAloneSamsungAccountSupported(getContext())) {
                Bundle bundle = new Bundle();
                bundle.putInt(GroupContract.Group.SERVICE_ID, i2);
                if (isAvailableSocialService()) {
                    z = getSocialService().isServiceRegistered(bundle);
                } else {
                    z = false;
                }
            } else {
                z = getAuthService().isServiceRegistered(i2);
            }
            if (SeMobileService.getAgentVersion(getContext()) >= 420000000 || i2 != 32) {
                z3 = z;
            } else if (z && getDeviceAuthInfo().getResult() != null) {
                z3 = true;
            }
            debugLog("isServiceRegistered serviceId=[" + i2 + "] " + z3 + " ");
            return new BooleanResult(new CommonResultStatus(1), z3);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new BooleanResult(new CommonResultStatus(-1), false);
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
    }

    public BooleanResult isSocialDisclaimerDisplayNeeded() {
        boolean z;
        debugLog("isSocialDisclaimerDisplayNeeded ");
        if (isAppIdNullOrEmpty()) {
            return new BooleanResult(new CommonResultStatus(-1, "appId is null or empty", ""), false);
        }
        if (!isSupportedSaAgentVersion(CommonConstants.SupportedApiMinVersion.VERSION_4_1)) {
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
        try {
            if (CommonUtils.isStandAloneSamsungAccountSupported(getContext())) {
                Bundle isSomethingNeeded = isSomethingNeeded("SocialDisclaimer");
                if (isSomethingNeeded != null) {
                    z = isSomethingNeeded.getBoolean("isNeeded", true);
                } else {
                    z = true;
                }
            } else {
                z = getAuthService().getNeedToShowSocialTncPopup();
            }
            return new BooleanResult(new CommonResultStatus(1), z);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new BooleanResult(new CommonResultStatus(-1), false);
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
    }

    public int requestAccessTokenForAccount(final AccessTokenResultCallback accessTokenResultCallback) {
        debugLog("requestAccessTokenForAccount : accountAppId=[" + getAppId() + "] ");
        if (isAppIdNullOrEmpty()) {
            return -1;
        }
        try {
            getAuthService().requestAccessTokenForAccount(getAppId(), getContext().getPackageName(), (Bundle) null, new IAccessTokenResultCallback.Stub() {
                public void onFailure(String str, String str2) {
                    AuthApi.this.debugLog(j.d("requestAccessTokenForAccount onFailure : code=[", str, "], message=[", str2, "] "));
                    if (accessTokenResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(str);
                        AuthApi authApi = AuthApi.this;
                        authApi.debugLog("converted error code = " + convertErrorcode + " - requestAccessTokenForAccount");
                        accessTokenResultCallback.onResult(new AccessTokenInfoResult(new CommonResultStatus(convertErrorcode, str2, str), (TokenInfo) null));
                    }
                }

                public void onSuccess(Bundle bundle, Bundle bundle2) {
                    TokenInfo tokenInfo;
                    AuthApi.this.debugLog("requestAccessTokenForAccount onSuccess ");
                    if (accessTokenResultCallback != null) {
                        if (bundle != null) {
                            tokenInfo = new TokenInfo();
                            if (bundle.containsKey("token")) {
                                tokenInfo.setToken(bundle.getString("token"));
                            }
                            if (bundle.containsKey("token_type")) {
                                tokenInfo.setTokenType(bundle.getString("token_type"));
                            }
                            if (bundle.containsKey(AuthConstants.EXTRA_API_SERVER_URL)) {
                                tokenInfo.setApiServerUrl(bundle.getString(AuthConstants.EXTRA_API_SERVER_URL));
                            }
                            if (bundle.containsKey(AuthConstants.EXTRA_AUTH_SERVER_URL)) {
                                tokenInfo.setAuthServerUrl(bundle.getString(AuthConstants.EXTRA_AUTH_SERVER_URL));
                            }
                            if (bundle.containsKey("token_issued_time")) {
                                tokenInfo.setTokenIssuedTime(bundle.getLong("token_issued_time"));
                            }
                            if (bundle.containsKey("token_validity_period")) {
                                tokenInfo.setTokenValidityPeriod(bundle.getLong("token_validity_period"));
                            }
                        } else {
                            tokenInfo = null;
                        }
                        accessTokenResultCallback.onResult(new AccessTokenInfoResult(new CommonResultStatus(1), tokenInfo));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return -1;
        }
    }

    public int requestAccountValidation(final ValidationResultCallback validationResultCallback) {
        debugLog("requestAccountValidation : accountAppId=[" + getAppId() + "] ");
        if (isAppIdNullOrEmpty()) {
            return -1;
        }
        try {
            getAuthService().requestValidation(getAppId(), getContext().getPackageName(), (Bundle) null, new IValidationResultCallback.Stub() {
                public void onFailure(String str, String str2, boolean z, boolean z3, boolean z7, boolean z9) {
                    AuthApi.this.debugLog(j.d("requestAccountValidation onFailure : code=[", str, "], message=[", str2, "] "));
                    if (validationResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(str);
                        AuthApi authApi = AuthApi.this;
                        authApi.debugLog("converted error code = " + convertErrorcode + " - requestAccountValidation");
                        validationResultCallback.onResult(new BooleanResult(new CommonResultStatus(convertErrorcode, str2, str), false), z, z3, z7, z9);
                    }
                }

                public void onSuccess() {
                    AuthApi.this.debugLog("requestAccountValidation : onSuccess ");
                    ValidationResultCallback validationResultCallback = validationResultCallback;
                    if (validationResultCallback != null) {
                        validationResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true), false, false, false, false);
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return -1;
        }
    }

    public int requestAuthCodeForAccount(final AuthCodeResultCallback authCodeResultCallback) {
        debugLog("requestAuthCodeForAccount : accountAppId=[" + getAppId() + "] ");
        if (isAppIdNullOrEmpty()) {
            return -1;
        }
        try {
            getAuthService().requestAuthCode(getAppId(), getContext().getPackageName(), (Bundle) null, new IAuthCodeResultCallback.Stub() {
                public void onFailure(String str, String str2) {
                    AuthApi.this.debugLog(j.d("requestAuthCodeForAccount onFailure : code=[", str, "], message=[", str2, "] "));
                    if (authCodeResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(str);
                        AuthApi authApi = AuthApi.this;
                        authApi.debugLog("converted error code = " + convertErrorcode + " - requestAuthCode");
                        authCodeResultCallback.onResult(new ResultTokenInfo(new CommonResultStatus(convertErrorcode, str2, str), (TokenInfo) null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    TokenInfo tokenInfo;
                    AuthApi.this.debugLog("requestAuthCodeForAccount onSuccess ");
                    if (authCodeResultCallback != null) {
                        if (bundle != null) {
                            tokenInfo = new TokenInfo();
                            if (bundle.containsKey("token")) {
                                tokenInfo.setToken(bundle.getString("token"));
                            }
                            if (bundle.containsKey("token_type")) {
                                tokenInfo.setTokenType(bundle.getString("token_type"));
                            }
                            if (bundle.containsKey(AuthConstants.EXTRA_API_SERVER_URL)) {
                                tokenInfo.setApiServerUrl(bundle.getString(AuthConstants.EXTRA_API_SERVER_URL));
                            }
                            if (bundle.containsKey(AuthConstants.EXTRA_AUTH_SERVER_URL)) {
                                tokenInfo.setAuthServerUrl(bundle.getString(AuthConstants.EXTRA_AUTH_SERVER_URL));
                            }
                            if (bundle.containsKey("token_issued_time")) {
                                tokenInfo.setTokenIssuedTime(bundle.getLong("token_issued_time"));
                            }
                            if (bundle.containsKey("token_validity_period")) {
                                tokenInfo.setTokenValidityPeriod(bundle.getLong("token_validity_period"));
                            }
                        } else {
                            tokenInfo = null;
                        }
                        authCodeResultCallback.onResult(new ResultTokenInfo(new CommonResultStatus(1), tokenInfo));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return -1;
        }
    }

    public int requestAuthInfo(final AuthResultCallback authResultCallback) {
        debugLog("requestAuthInfo : accountAppId=[" + getAppId() + "] ");
        if (isAppIdNullOrEmpty()) {
            return -1;
        }
        try {
            getAuthService().requestAuthInfo(getAppId(), getContext().getPackageName(), (Bundle) null, new IAuthResultCallback.Stub() {
                public void onFailure(String str, String str2) {
                    AuthApi.this.debugLog(j.d("requestAuthInfo onFailure : code=[", str, "], message=[", str2, "] "));
                    if (authResultCallback != null) {
                        authResultCallback.onResult(new AuthInfoResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(str), str2, str), (AuthInfo) null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    AuthApi.this.debugLog("requestAuthInfo onSuccess ");
                    if (authResultCallback != null) {
                        AuthInfo authInfo = null;
                        if (bundle != null) {
                            if (bundle.containsKey("account_id")) {
                                authInfo = new AuthInfo();
                                authInfo.setAccountId(bundle.getString("account_id"));
                            }
                            if (bundle.containsKey("guid")) {
                                if (authInfo == null) {
                                    authInfo = new AuthInfo();
                                }
                                authInfo.setGuid(bundle.getString("guid"));
                            }
                            if (bundle.containsKey("country_code")) {
                                if (authInfo == null) {
                                    authInfo = new AuthInfo();
                                }
                                authInfo.setCountryCode(bundle.getString("country_code"));
                            }
                            if (bundle.containsKey(HeaderSetup.Key.MCC)) {
                                if (authInfo == null) {
                                    authInfo = new AuthInfo();
                                }
                                authInfo.setMobileCountryCode(bundle.getString(HeaderSetup.Key.MCC));
                            }
                            if (bundle.containsKey("device_physical_address")) {
                                if (authInfo == null) {
                                    authInfo = new AuthInfo();
                                }
                                authInfo.setDevicePhysicalAddress(bundle.getString("device_physical_address"));
                            }
                            if (bundle.containsKey("is_email_authenticated")) {
                                if (authInfo == null) {
                                    authInfo = new AuthInfo();
                                }
                                authInfo.setEmailAddressAuthenticated(bundle.getBoolean("is_email_authenticated"));
                            }
                            if (bundle.containsKey("is_name_authenticated")) {
                                if (authInfo == null) {
                                    authInfo = new AuthInfo();
                                }
                                authInfo.setRealNameAuthenticated(bundle.getBoolean("is_name_authenticated"));
                            }
                            if (bundle.containsKey("is_account_disclaimer_agreed")) {
                                if (authInfo == null) {
                                    authInfo = new AuthInfo();
                                }
                                authInfo.setAccountDisclaimerAgreed(bundle.getBoolean("is_account_disclaimer_agreed"));
                            }
                        }
                        authResultCallback.onResult(new AuthInfoResult(new CommonResultStatus(1), authInfo));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return -1;
        }
    }

    public int requestDisclaimerAgreementForThirdParty(final DisclaimerAgreementResultCallback disclaimerAgreementResultCallback) {
        debugLog("requestDisclaimerAgreement : accountAppId=[" + getAppId() + "]");
        if (isAppIdNullOrEmpty()) {
            return -1;
        }
        try {
            getAuthService().requestDisclaimerAgreementForThirdParty(getAppId(), getContext().getPackageName(), (Bundle) null, new IDisclaimerAgreementForThirdPartyResultCallback.Stub() {
                public void onFailure(String str, String str2) {
                    AuthApi.this.debugLog(j.d("requestDisclaimerAgreementForThirdParty onFailure : code=[", str, "], message=[", str2, "] "));
                    if (disclaimerAgreementResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(str);
                        AuthApi authApi = AuthApi.this;
                        authApi.debugLog("converted error code = " + convertErrorcode + " - requestDisclaimerAgreement");
                        disclaimerAgreementResultCallback.onResult(new BooleanResult(new CommonResultStatus(convertErrorcode, str2, str), false));
                    }
                }

                public void onSuccess(boolean z) {
                    AuthApi.this.debugLog("requestDisclaimerAgreement onSuccess ");
                    DisclaimerAgreementResultCallback disclaimerAgreementResultCallback = disclaimerAgreementResultCallback;
                    if (disclaimerAgreementResultCallback != null) {
                        disclaimerAgreementResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), z));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return -1;
        }
    }

    public int requestRenewAccessTokenForAccount(String str, final AccessTokenResultCallback accessTokenResultCallback) {
        debugLog("requestRenewAccessTokenForAccount : accountAppId=[" + getAppId() + "], expiredAccessToken=[" + str + "] ");
        if (isAppIdNullOrEmpty()) {
            return -1;
        }
        try {
            getAuthService().requestRenewAccessTokenForAccount(getAppId(), getContext().getPackageName(), (Bundle) null, str, new IAccessTokenResultCallback.Stub() {
                public void onFailure(String str, String str2) {
                    AuthApi.this.debugLog(j.d("requestRenewAccessTokenForAccount onFailure : code=[", str, "], message=[", str2, "] "));
                    if (accessTokenResultCallback != null) {
                        int convertErrorcode = ErrorCodeConvertor.convertErrorcode(str);
                        AuthApi authApi = AuthApi.this;
                        authApi.debugLog("converted error code = " + convertErrorcode + " - requestRenewAccessTokenForAccount");
                        accessTokenResultCallback.onResult(new AccessTokenInfoResult(new CommonResultStatus(convertErrorcode, str2, str), (TokenInfo) null));
                    }
                }

                public void onSuccess(Bundle bundle, Bundle bundle2) {
                    TokenInfo tokenInfo;
                    AuthApi.this.debugLog("requestRenewAccessTokenForAccount onSuccess ");
                    if (accessTokenResultCallback != null) {
                        if (bundle != null) {
                            tokenInfo = new TokenInfo();
                            if (bundle.containsKey("token")) {
                                tokenInfo.setToken(bundle.getString("token"));
                            }
                            if (bundle.containsKey("token_type")) {
                                tokenInfo.setTokenType(bundle.getString("token_type"));
                            }
                            if (bundle.containsKey(AuthConstants.EXTRA_API_SERVER_URL)) {
                                tokenInfo.setApiServerUrl(bundle.getString(AuthConstants.EXTRA_API_SERVER_URL));
                            }
                            if (bundle.containsKey(AuthConstants.EXTRA_AUTH_SERVER_URL)) {
                                tokenInfo.setAuthServerUrl(bundle.getString(AuthConstants.EXTRA_AUTH_SERVER_URL));
                            }
                            if (bundle.containsKey("token_issued_time")) {
                                tokenInfo.setTokenIssuedTime(bundle.getLong("token_issued_time"));
                            }
                            if (bundle.containsKey("token_validity_period")) {
                                tokenInfo.setTokenValidityPeriod(bundle.getLong("token_validity_period"));
                            }
                        } else {
                            tokenInfo = null;
                        }
                        accessTokenResultCallback.onResult(new AccessTokenInfoResult(new CommonResultStatus(1), tokenInfo));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return -1;
        }
    }

    public int setDisclaimerAgreementForSocial(boolean z) {
        debugLog("setDisclaimerAgreementForSocial ");
        if (isAppIdNullOrEmpty()) {
            return -1;
        }
        if (!isSupportedSaAgentVersion(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            debugLog("Agent version is under ver. Need to use with Agent 10.0.00.00 or higher.");
            return -7;
        }
        try {
            if (CommonUtils.isStandAloneSamsungAccountSupported(getContext()) && !isAvailableSocialService()) {
                return -1;
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("agreed", z);
            if (CommonUtils.isStandAloneSamsungAccountSupported(getContext())) {
                if (getSocialService().setDisclaimerAgreementForSocial(bundle)) {
                    return 1;
                }
            } else if (getAuthService().setDisclaimerAgreementForSocial(z)) {
                return 1;
            }
            return -1;
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return -1;
        }
    }

    private Intent getIntentToDisplay(String str, Bundle bundle) {
        if (bundle != null && isAvailableSocialService()) {
            bundle.putString("what", str);
            try {
                return getSocialService().getIntentToDisplay(bundle);
            } catch (RemoteException | NotConnectedException | NullPointerException e) {
                secureLog(e);
            } catch (OutOfMemoryError unused) {
                verboseLog("OutOfMemoryError occurred");
                return null;
            }
        }
        return null;
    }

    private Intent getIntentForAccountAccessTokenRequest(String str, String str2) {
        try {
            return getAuthService().getIntentForAccountAccessTokenRequest(str, str2);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    private Intent getIntentForAccountDisclaimerAgreement(String str, boolean z) {
        try {
            return getAuthService().getIntentForAccountDisclaimerAgreement(str, z);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    private Intent getIntentForAccountPasswordConfirmation(String str) {
        debugLog("getIntentForAccountPasswordConfirmation : accountAppId=[" + str + "] ");
        try {
            return getAuthService().getIntentForAccountPasswordConfirmation(str);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    private Intent getIntentForAccountPasswordConfirmationPopup(String str) {
        debugLog("getIntentForAccountPasswordConfirmationPopup : accountAppId=[" + str + "] ");
        try {
            return getAuthService().getIntentForAccountPasswordConfirmationPopup(str);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    private Intent getIntentForAccountProfileModification(String str) {
        try {
            return getAuthService().getIntentForAccountProfileModification(str);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    private Intent getIntentForAccountValidationRequest(String str, boolean z, boolean z3) {
        try {
            return getAuthService().getIntentForAccountValidationRequest(str, z, z3);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return null;
        }
    }

    public BooleanResult getDisclaimerAgreementForSocial(int i2) {
        boolean disclaimerAgreementForService;
        boolean z = false;
        if (isAppIdNullOrEmpty()) {
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
        if (!isSupportedSaAgentVersion(CommonConstants.SupportedApiMinVersion.VERSION_4_1)) {
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
        try {
            if (CommonUtils.isStandAloneSamsungAccountSupported(getContext())) {
                if (isAvailableSocialService()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(GroupContract.Group.SERVICE_ID, i2);
                    disclaimerAgreementForService = getSocialService().getDisclaimerAgreementForService(bundle);
                }
                debugLog("getDisclaimerAgreementForSocial " + z + " ");
                return new BooleanResult(new CommonResultStatus(1), z);
            }
            disclaimerAgreementForService = getAuthService().getDisclaimerAgreementForService(i2);
            z = disclaimerAgreementForService;
            debugLog("getDisclaimerAgreementForSocial " + z + " ");
            return new BooleanResult(new CommonResultStatus(1), z);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new BooleanResult(new CommonResultStatus(-1), false);
        } catch (OutOfMemoryError unused) {
            verboseLog("OutOfMemoryError occurred");
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
    }
}
