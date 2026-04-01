package com.samsung.android.sdk.mobileservice.social;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.mobileservice.common.api.SeMobileServiceApi;
import com.samsung.android.sdk.mobileservice.common.exception.NotAuthorizedException;
import com.samsung.android.sdk.mobileservice.common.exception.NotConnectedException;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.common.result.CommonResult;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.social.buddy.IServiceActivationResultCallback;
import com.samsung.android.sdk.mobileservice.social.buddy.IServiceDeactivationResultCallback;
import com.samsung.android.sdk.mobileservice.social.common.IBundleResultCallback;
import com.samsung.android.sdk.mobileservice.social.social.BundleKey;
import com.samsung.android.sdk.mobileservice.social.social.result.ServiceNumber;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SocialApi extends SeMobileServiceApi {
    public static final String API_NAME = "SocialApi";
    public static final int CONTACT_UPLOAD_BASE_SERVICE = 2;
    public static final int COUNTRY_TYPE_CHINA = 3;
    public static final int COUNTRY_TYPE_EUROPE = 2;
    public static final int COUNTRY_TYPE_GLOBAL = 0;
    public static final int COUNTRY_TYPE_KOREA = 1;
    public static final int DEVICE_AUTH_BASE_SERVICE = 1;
    public static final int SERVICE_ID_CERTIFICATE_SHARING = 1;
    public static final int SERVICE_ID_CONTACT_UPLOAD = 101;
    public static final int SERVICE_ID_PROFILE_CARD_SHARING = 2;
    public static final int SERVICE_ID_PROFILE_SHARING = 0;
    public static final String SERVICE_NAME = "SocialService";
    public static final int SOCIAL_AGREEMENT_BASE_SERVICE = 0;
    private final String EXTRA_RESULT = "result";
    private final String EXTRA_SCOPE = "scope";
    private final String EXTRA_SERVICE_ID = "service_id";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum DisclosureScope {
        NOTHING(-1),
        EVERYONE(0),
        CONTACT_ONLY(1);
        
        private final int mIndex;

        private DisclosureScope(int i2) {
            this.mIndex = i2;
        }

        public static DisclosureScope fromIndex(int i2) {
            DisclosureScope disclosureScope = EVERYONE;
            if (i2 == disclosureScope.getIndex()) {
                return disclosureScope;
            }
            DisclosureScope disclosureScope2 = CONTACT_ONLY;
            if (i2 == disclosureScope2.getIndex()) {
                return disclosureScope2;
            }
            return NOTHING;
        }

        public int getIndex() {
            return this.mIndex;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DisclosureScopeResultCallback {
        void onResult(CommonResult<DisclosureScope> commonResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ServiceActivationResultCallback {
        void onResult(BooleanResult booleanResult, int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ServiceDeactivationResultCallback {
        void onResult(BooleanResult booleanResult, int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ServiceNumberResultCallback {
        void onResult(CommonResult<ServiceNumber> commonResult);
    }

    public SocialApi(SeMobileServiceSession seMobileServiceSession) {
        super(seMobileServiceSession, "SocialApi");
    }

    private SocialServiceState bundleToSocialServiceState(Bundle bundle) {
        if (bundle != null) {
            return new SocialServiceState(bundle.getInt("state", 1), (Intent) bundle.getParcelable("intent"));
        }
        debugLog("bundle is null : bundleToSocialServiceState");
        return null;
    }

    private Intent getIntentToDisplay(String str) {
        return getIntentToDisplay(str, new Bundle());
    }

    private boolean isAuthorized() {
        try {
            checkAuthorized(0, 2, 1);
            return true;
        } catch (NotAuthorizedException unused) {
            return false;
        }
    }

    private Bundle isSomethingNeeded(String str) {
        return isSomethingNeeded(str, new Bundle());
    }

    public int getCountryTypeForAgreement() {
        debugLog("getCountryTypeForAgreement ");
        try {
            return getSocialService().getCountryTypeForAgreement();
        } catch (Exception e) {
            secureLog(e);
            return -1;
        }
    }

    public CommonResult<DisclosureScope> getDisclosureScope(int i2) {
        debugLog("getDisclosureScope. serviceId: " + i2);
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_6_50)) {
            return new CommonResult<>(new CommonResultStatus(-7), null);
        }
        if (!isAuthorized()) {
            secureLog("getDisclosureScope. not authorized. return here.", new String[0]);
            return new CommonResult<>(new CommonResultStatus(-1), null);
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("service_id", i2);
            DisclosureScope fromIndex = DisclosureScope.fromIndex(getSocialService().getDisclosureScope(bundle));
            debugLog("getDisclosureScope serviceId=[" + i2 + "], result=[" + fromIndex.name() + "]");
            return new CommonResult<>(new CommonResultStatus(1), fromIndex);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new CommonResult<>(new CommonResultStatus(-1), null);
        }
    }

    public String[] getEssentialServiceNames() {
        return new String[]{"SocialService"};
    }

    public Intent getIntentForAgreementProcedure() {
        debugLog("getIntentForAgreementProcedure ");
        Bundle bundle = new Bundle();
        bundle.putBoolean("isLegalPopupSupported", true);
        return getIntentToDisplay("AgreementProcedure", bundle);
    }

    public Intent getIntentForForceUpdate() {
        return getIntentToDisplay(BundleKey.FORCE_UPDATE);
    }

    public Intent getIntentForGdprErrorPopup() {
        debugLog("getIntentForGdprErrorPopup ");
        return getIntentToDisplay(BundleKey.GDPR);
    }

    public Intent getIntentForIntroductionDisplay() {
        debugLog("getIntentForIntroductionDisplay ");
        return getIntentToDisplay(BundleKey.INTRODUCTION);
    }

    public Intent getIntentForPersonalInformationCollectionAgreementDisplay() {
        debugLog("getIntentForPersonalInformationCollectionAgreementDisplay ");
        return getIntentToDisplay(BundleKey.PERSONAL_INFORMATION_COLLECTION_AGREEMENT);
    }

    public Intent getIntentForSocialDisclaimerDisplay() {
        debugLog("getIntentForSocialDisclaimerDisplay ");
        return getIntentToDisplay("SocialDisclaimer");
    }

    public Intent getIntentForTermsAndConditionDisplay() {
        debugLog("getIntentForTermsAndConditionDisplay ");
        return getIntentToDisplay(BundleKey.TERMS_AND_CONDITION);
    }

    public SocialServiceState getServiceState() {
        debugLog("getServiceState ");
        try {
            return bundleToSocialServiceState(getSocialService().getServiceState());
        } catch (Exception e) {
            secureLog(e);
            return null;
        }
    }

    public boolean isAgreementProcedureNeeded() {
        debugLog("isAgreementProcedureNeeded ");
        Bundle isSomethingNeeded = isSomethingNeeded("AgreementProcedure");
        boolean z = true;
        if (isSomethingNeeded != null) {
            z = isSomethingNeeded.getBoolean("isNeeded", true);
        }
        debugLog("isAgreementProcedureNeeded " + z + ArcCommonLog.TAG_COMMA);
        return z;
    }

    public BooleanResult isPermissionGranted(String str) {
        debugLog("isPermissionGranted ");
        if (!isSupportedSemsAgentVersionBetween(CommonConstants.SupportedApiMinVersion.VERSION_10_8_6_1, CommonConstants.SupportedApiMinVersion.VERSION_11_0) && !isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_0_1_28)) {
            return new BooleanResult(new CommonResultStatus(-1), true);
        }
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.API_NAME, str);
        try {
            return new BooleanResult(new CommonResultStatus(1), getSocialService().isPermissionGranted(bundle).getBoolean(BundleKey.IS_PERMISSION_GRANTED, false));
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
    }

    public BooleanResult isServiceActivated(int i2) {
        if (!isAuthorized()) {
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
        try {
            if (getSocialService().isServiceActivated(i2) == 1) {
                debugLog("isServiceActivated serviceId=[" + i2 + "] true ");
                return new BooleanResult(new CommonResultStatus(1), true);
            }
            debugLog("isServiceActivated serviceId=[" + i2 + "] false ");
            return new BooleanResult(new CommonResultStatus(1), false);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return new BooleanResult(new CommonResultStatus(-1), false);
        }
    }

    public int requestDisclosureScope(int i2, final DisclosureScopeResultCallback disclosureScopeResultCallback) {
        boolean z;
        StringBuilder o2 = C0086a.o(i2, "requestDisclosureScope. serviceId: ", ", callback exist: ");
        if (disclosureScopeResultCallback != null) {
            z = true;
        } else {
            z = false;
        }
        o2.append(z);
        debugLog(o2.toString());
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_6_50)) {
            return -7;
        }
        if (!isAuthorized()) {
            secureLog("requestDisclosureScope. not authorized. return here.", new String[0]);
            return -1;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("service_id", i2);
            return getSocialService().requestDisclosureScope(bundle, new IBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    SocialApi socialApi = SocialApi.this;
                    socialApi.debugLog("requestDisclosureScope. fail. code: " + j2 + ", message: " + str);
                    DisclosureScopeResultCallback disclosureScopeResultCallback = disclosureScopeResultCallback;
                    if (disclosureScopeResultCallback != null) {
                        disclosureScopeResultCallback.onResult(new CommonResult(new CommonResultStatus(-1, str, Long.toString(j2)), null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    if (disclosureScopeResultCallback != null) {
                        DisclosureScope fromIndex = DisclosureScope.fromIndex(bundle.getInt("result"));
                        SocialApi socialApi = SocialApi.this;
                        socialApi.debugLog("requestDisclosureScope. success. result: " + fromIndex.name());
                        disclosureScopeResultCallback.onResult(new CommonResult(new CommonResultStatus(1), fromIndex));
                    }
                }
            });
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        }
    }

    public int requestDisclosureScopeUpdate(int i2, DisclosureScope disclosureScope, final DisclosureScopeResultCallback disclosureScopeResultCallback) {
        boolean z;
        StringBuilder o2 = C0086a.o(i2, "requestDisclosureScopeUpdate. serviceId: ", ", scope: ");
        o2.append(disclosureScope.name());
        o2.append(", callback exist: ");
        if (disclosureScopeResultCallback != null) {
            z = true;
        } else {
            z = false;
        }
        o2.append(z);
        debugLog(o2.toString());
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_6_50)) {
            return -7;
        }
        if (!isAuthorized()) {
            secureLog("requestDisclosureScopeUpdate. not authorized. return here.", new String[0]);
            return -1;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("service_id", i2);
            bundle.putInt("scope", disclosureScope.getIndex());
            return getSocialService().requestDisclosureScopeUpdate(bundle, new IBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    SocialApi socialApi = SocialApi.this;
                    socialApi.debugLog("requestDisclosureScopeUpdate. fail. code: " + j2 + ", message: " + str);
                    DisclosureScopeResultCallback disclosureScopeResultCallback = disclosureScopeResultCallback;
                    if (disclosureScopeResultCallback != null) {
                        disclosureScopeResultCallback.onResult(new CommonResult(new CommonResultStatus(-1, str, Long.toString(j2)), null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    if (disclosureScopeResultCallback != null) {
                        DisclosureScope fromIndex = DisclosureScope.fromIndex(bundle.getInt("result"));
                        SocialApi socialApi = SocialApi.this;
                        socialApi.debugLog("requestDisclosureScopeUpdate. success. result: " + fromIndex.name());
                        disclosureScopeResultCallback.onResult(new CommonResult(new CommonResultStatus(1), fromIndex));
                    }
                }
            });
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        }
    }

    public int requestServiceActivation(int i2, final ServiceActivationResultCallback serviceActivationResultCallback) {
        debugLog("requestServiceActivation serviceId=[" + i2 + "] ");
        if (!isAuthorized()) {
            return -1;
        }
        try {
            getSocialService().requestServiceActivation(i2, new IServiceActivationResultCallback.Stub() {
                public void onFailure(int i2, String str) {
                    SocialApi socialApi = SocialApi.this;
                    socialApi.debugLog("requestServiceActivation onFailure : code=[" + i2 + "], message=[" + str + "] ");
                    if (serviceActivationResultCallback != null) {
                        serviceActivationResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(i2), str, Integer.toString(i2)), false), -1);
                    }
                }

                public void onSuccess(int i2) {
                    SocialApi socialApi = SocialApi.this;
                    socialApi.debugLog("requestServiceActivation serviceId=[" + i2 + "] onSuccess ");
                    ServiceActivationResultCallback serviceActivationResultCallback = serviceActivationResultCallback;
                    if (serviceActivationResultCallback != null) {
                        serviceActivationResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true), i2);
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        }
    }

    public int requestServiceDeactivation(int i2, final ServiceDeactivationResultCallback serviceDeactivationResultCallback) {
        debugLog("requestServiceDeactivation serviceId=[" + i2 + "] ");
        if (!isAuthorized()) {
            return -1;
        }
        try {
            getSocialService().requestServiceDeactivation(i2, new IServiceDeactivationResultCallback.Stub() {
                public void onFailure(int i2, String str) {
                    SocialApi socialApi = SocialApi.this;
                    socialApi.debugLog("requestServiceDeactivation onFailure : code=[" + i2 + "], message=[" + str + "] ");
                    if (serviceDeactivationResultCallback != null) {
                        serviceDeactivationResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(i2), str, Integer.toString(i2)), false), -1);
                    }
                }

                public void onSuccess(int i2) {
                    SocialApi socialApi = SocialApi.this;
                    socialApi.debugLog("requestServiceDeactivation serviceId=[" + i2 + "] onSuccess ");
                    ServiceDeactivationResultCallback serviceDeactivationResultCallback = serviceDeactivationResultCallback;
                    if (serviceDeactivationResultCallback != null) {
                        serviceDeactivationResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true), i2);
                    }
                }
            });
            return 1;
        } catch (Exception e) {
            secureLog(e);
            return -1;
        }
    }

    public int requestServiceNumber(final ServiceNumberResultCallback serviceNumberResultCallback) {
        debugLog("requestServiceNumberInfo");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_12_1)) {
            return -7;
        }
        try {
            getSocialService().requestServiceNumber(new IBundleResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    SocialApi socialApi = SocialApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestServiceNumber onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    socialApi.debugLog(p6.toString());
                    CommonResultStatus commonResultStatus = new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, String.valueOf(j2));
                    ServiceNumberResultCallback serviceNumberResultCallback = serviceNumberResultCallback;
                    if (serviceNumberResultCallback != null) {
                        serviceNumberResultCallback.onResult(new CommonResult(commonResultStatus, null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    SocialApi.this.debugLog("requestServiceNumber onSuccess ");
                    if (serviceNumberResultCallback != null) {
                        serviceNumberResultCallback.onResult(new CommonResult(new CommonResultStatus(1), new ServiceNumber(bundle.getInt(BundleKey.SERVICE_NUMBER_CNT, 0), bundle.getBoolean(BundleKey.CONTAINS_MY_NUMBER, false), bundle.getStringArrayList(BundleKey.SERVICE_NUMBER_LiST), bundle.getString("my_phone_number"))));
                    }
                }
            });
            return 1;
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return -1;
        }
    }

    private Intent getIntentToDisplay(String str, Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        bundle.putString("what", str);
        try {
            return getSocialService().getIntentToDisplay(bundle);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        }
    }

    private Bundle isSomethingNeeded(String str, Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        try {
            bundle.putString("what", str);
            return getSocialService().isSomethingNeeded(bundle);
        } catch (RemoteException | NotConnectedException | NullPointerException e) {
            secureLog(e);
            return null;
        }
    }

    public Intent getIntentForAgreementProcedure(int i2) {
        debugLog("getIntentForAgreementProcedure ");
        Bundle bundle = new Bundle();
        bundle.putBoolean("isLegalPopupSupported", true);
        bundle.putInt(BundleKey.SERVICE_TYPE, i2);
        return getIntentToDisplay("AgreementProcedure", bundle);
    }

    public boolean isAgreementProcedureNeeded(int i2) {
        debugLog("isAgreementProcedureNeeded ");
        Bundle bundle = new Bundle();
        bundle.putInt(BundleKey.SERVICE_TYPE, i2);
        Bundle isSomethingNeeded = isSomethingNeeded("AgreementProcedure", bundle);
        boolean z = true;
        if (isSomethingNeeded != null) {
            z = isSomethingNeeded.getBoolean("isNeeded", true);
        }
        debugLog("isAgreementProcedureNeeded " + z);
        return z;
    }
}
