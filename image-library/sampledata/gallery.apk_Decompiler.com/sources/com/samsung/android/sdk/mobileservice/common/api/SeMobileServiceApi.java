package com.samsung.android.sdk.mobileservice.common.api;

import android.content.Context;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl;
import com.samsung.android.sdk.mobileservice.auth.IMobileServiceAuth;
import com.samsung.android.sdk.mobileservice.common.exception.NotAuthorizedException;
import com.samsung.android.sdk.mobileservice.common.exception.NotConnectedException;
import com.samsung.android.sdk.mobileservice.common.exception.NotSupportedApiException;
import com.samsung.android.sdk.mobileservice.place.IMobileServicePlace;
import com.samsung.android.sdk.mobileservice.profile.IMobileServiceProfile;
import com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial;
import com.samsung.android.sdk.mobileservice.util.SdkLog;
import i.C0212a;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeMobileServiceApi {
    private final String mApiName;
    private final String mReference;
    protected final SeMobileServiceSessionImpl mSessionInstance;

    public SeMobileServiceApi(SeMobileServiceSession seMobileServiceSession, String str) {
        if (seMobileServiceSession instanceof SeMobileServiceSessionImpl) {
            SeMobileServiceSessionImpl seMobileServiceSessionImpl = (SeMobileServiceSessionImpl) seMobileServiceSession;
            this.mSessionInstance = seMobileServiceSessionImpl;
            this.mApiName = str;
            this.mReference = SdkLog.getReference(seMobileServiceSessionImpl);
            debugLog(str);
            String[] essentialServiceNames = getEssentialServiceNames();
            int length = essentialServiceNames.length;
            int i2 = 0;
            while (i2 < length) {
                String str2 = essentialServiceNames[i2];
                if (seMobileServiceSessionImpl.isAddedService(str2)) {
                    i2++;
                } else {
                    SdkLog.i(str, "Not added service " + SdkLog.getReference(seMobileServiceSession));
                    throw new NotAuthorizedException(C0212a.A(str2, " is not added service. Before new this api class, you must add this service name on session!"));
                }
            }
            if (!seMobileServiceSessionImpl.isSessionConnected()) {
                SdkLog.i(str, "Session is not connected " + SdkLog.getReference(seMobileServiceSession));
                throw new NotConnectedException("Session is not connected! After connection callback called, new this api class!");
            } else if (!seMobileServiceSessionImpl.isSupportedApi(str)) {
                SdkLog.i(str, "Api component is not supported. " + SdkLog.getReference(seMobileServiceSession));
                throw new NotSupportedApiException(C0212a.A(str, " is not supported"));
            }
        } else {
            SdkLog.i(str, "Session is invalid " + SdkLog.getReference(seMobileServiceSession));
            throw new NotConnectedException("Session is not connected! After connection callback called, new this api class!");
        }
    }

    public void checkAuthorized(int... iArr) {
        HashSet hashSet = new HashSet();
        for (int valueOf : iArr) {
            hashSet.add(Integer.valueOf(valueOf));
        }
        int authorized = this.mSessionInstance.getAuthorized();
        if (hashSet.contains(Integer.valueOf(authorized))) {
            if (authorized == 0) {
                debugLog("Account not authorized ");
                throw new NotAuthorizedException("Account is not authorized! you need sign-in");
            } else if (authorized == 1) {
                debugLog("Device not authorized ");
                throw new NotAuthorizedException("Device is not authorized! you need to authorize device");
            } else if (authorized == 2) {
                debugLog("MobileService Agent is not installed ");
                throw new NotAuthorizedException("MobileService Agent is not installed you need to install MobileService Agent");
            }
        }
    }

    public void debugLog(String str) {
        String str2 = this.mApiName;
        StringBuilder t = C0212a.t(str, " ");
        t.append(this.mReference);
        SdkLog.i(str2, t.toString());
    }

    public String getAppId() {
        return this.mSessionInstance.getAppId();
    }

    public IMobileServiceAuth getAuthService() {
        return this.mSessionInstance.getAuthService();
    }

    public Context getContext() {
        return this.mSessionInstance.getContext();
    }

    public abstract String[] getEssentialServiceNames();

    public IMobileServicePlace getPlaceService() {
        return this.mSessionInstance.getPlaceService();
    }

    public IMobileServiceProfile getProfileService() {
        return this.mSessionInstance.getProfileService();
    }

    public String getReference() {
        return this.mReference;
    }

    public long getSemsAgentVersion() {
        return this.mSessionInstance.getSamsungExperienceServiceAgentVersion();
    }

    public IMobileServiceSocial getSocialService() {
        return this.mSessionInstance.getSocialService();
    }

    public String getTag() {
        return this.mApiName;
    }

    public void infoLog(String str) {
        String str2 = this.mApiName;
        StringBuilder t = C0212a.t(str, " ");
        t.append(this.mReference);
        SdkLog.i(str2, t.toString());
    }

    public boolean isNoMoreSupportedSemsAgentVersion(int i2) {
        return this.mSessionInstance.isNoMoreSupportedSemsAgentVersion(i2);
    }

    public boolean isSupportedSaAgentVersion(int i2) {
        return this.mSessionInstance.isSupportedSaAgentVersion(i2);
    }

    public boolean isSupportedSemsAgentVersionBetween(int i2, int i7) {
        return this.mSessionInstance.isSupportedSemsAgentVersionBetween(i2, i7);
    }

    public boolean isSupportedSemsAgentVersionMoreThan(int i2) {
        return this.mSessionInstance.isSupportedSemsAgentVersionMoreThan(i2);
    }

    public void secureLog(Exception exc) {
        SdkLog.s(exc);
    }

    public void verboseLog(String str) {
        String str2 = this.mApiName;
        StringBuilder t = C0212a.t(str, " ");
        t.append(this.mReference);
        SdkLog.v(str2, t.toString());
    }

    public void secureLog(String str, String... strArr) {
        SdkLog.s(this.mApiName, str, strArr);
    }
}
