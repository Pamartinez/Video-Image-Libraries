package com.samsung.android.sdk.mobileservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.text.TextUtils;
import com.samsung.android.sdk.mobileservice.auth.IMobileServiceAuth;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import com.samsung.android.sdk.mobileservice.common.ICommonService;
import com.samsung.android.sdk.mobileservice.common.exception.NotConnectedException;
import com.samsung.android.sdk.mobileservice.place.IMobileServicePlace;
import com.samsung.android.sdk.mobileservice.profile.IMobileServiceProfile;
import com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial;
import com.samsung.android.sdk.mobileservice.util.SdkLog;
import i.C0212a;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SeMobileServiceBindManager implements BindChangeListener {
    private static final String BIND_CLASS_NAME_AUTH = "com.osp.app.signin.service.SemsAidlService";
    private static final String BIND_CLASS_NAME_COMMON = "com.samsung.android.mobileservice.commonservice.CommonService";
    private static final String BIND_CLASS_NAME_COMMON_FOR_SA = "com.samsung.android.samsungaccount.mobileservice.commonservice.CommonService";
    private static final String BIND_CLASS_NAME_SOCIAL = "com.samsung.android.mobileservice.social.MobileServiceSocialService";
    private static final String BIND_PACKAGE_NAME = "com.samsung.android.mobileservice";
    private static final String BIND_REQUEST_SERVICE_AUTH = "com.samsung.android.mobileservice.auth.REQUEST_SERVICE";
    private static final String BIND_REQUEST_SERVICE_COMMON = "com.samsung.android.mobileservice.common.REQUEST_SERVICE";
    private static final String BIND_REQUEST_SERVICE_PLACE = "com.samsung.android.mobileservice.place.REQUEST_SERVICE";
    private static final String BIND_REQUEST_SERVICE_PROFILE = "com.samsung.android.mobileservice.profile.REQUEST_SERVICE";
    private static final String EXTRA_APP_ID = "app_id";
    private static final long MIN_AGENT_SUPPORT_VERSION = 0;
    private static final String TAG = "SeMobileServiceBindManager";
    private static Map<String, SeMobileServiceBindManager> sMap = new HashMap();
    private Set<BindChangeListener> bindChangeListeners = new CopyOnWriteArraySet();
    private final String mAppId;
    private ServiceHandler<IMobileServiceAuth> mAuthServiceHandler = null;
    private ServiceHandler<ICommonService> mCommonServiceHandler = null;
    final VersionExchangeInfo mEmptyVersionExchangeInfo = new VersionExchangeInfo();
    private boolean mIsMobileServiceAgentEnabled;
    private boolean mIsMobileServiceAgentInstalled;
    private boolean mIsStandAloneSA;
    private ServiceHandler<IMobileServicePlace> mPlaceServiceHandler = null;
    private ServiceHandler<IMobileServiceProfile> mProfileServiceHandler = null;
    private ServiceHandler<ICommonService> mSaCommonServiceHandler = null;
    private ServiceHandler<IMobileServiceSocial> mSocialServiceHandler = null;
    private HashSet<String> mSupportedServices = new HashSet<>();
    private ServiceHandler<IMobileServiceSocial> mUnknownServiceHandler = new ServiceHandler<IMobileServiceSocial>() {
        public IMobileServiceSocial asInterface(IBinder iBinder) {
            return null;
        }
    };
    private VersionExchangeInfo mVersionExchangeInfo = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum BindState {
        UNBOUND,
        BINDING,
        BOUND;

        public boolean isBound() {
            if (this == BOUND) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class RequesterServiceHandler<T extends IInterface> extends ServiceHandler<T> {
        public T getService(SeMobileServiceSession seMobileServiceSession) {
            if (this.service != null && this.bindRequestSet.contains(seMobileServiceSession)) {
                return this.service;
            }
            throw new NotConnectedException(C0212a.p(new StringBuilder(), this.serviceName, " is not connected"));
        }

        public boolean isBound(SeMobileServiceSession seMobileServiceSession) {
            if (!super.isBound(seMobileServiceSession) || !this.bindRequestSet.contains(seMobileServiceSession)) {
                return false;
            }
            return true;
        }

        public boolean unbindable(SeMobileServiceSession seMobileServiceSession) {
            if (!super.unbindable(seMobileServiceSession) || !this.bindRequestSet.contains(seMobileServiceSession)) {
                return false;
            }
            return true;
        }

        private RequesterServiceHandler(String str, String str2, String str3, String str4, String str5, BindChangeListener bindChangeListener) {
            super(str, str2, str3, str4, str5, bindChangeListener);
        }
    }

    private SeMobileServiceBindManager(String str, boolean z, boolean z3, boolean z7) {
        this.mAppId = str;
        this.mIsStandAloneSA = z;
        this.mIsMobileServiceAgentInstalled = z3;
        this.mIsMobileServiceAgentEnabled = z7;
    }

    private SessionErrorCode checkExchangeInfo(String str, int i2, long j2) {
        SdkLog.d(TAG, str + " version:" + j2);
        if (i2 == 3) {
            SdkLog.i(TAG, "Force update is needed for old agent");
            return SessionErrorCode.CAUSE_AGENT_FORCE_UPDATE_REQUIRED;
        } else if (i2 == 2) {
            SdkLog.i(TAG, "SDK version " + SeMobileService.getSdkVersionCode() + " is lower than min " + str + " required SDK version");
            return SessionErrorCode.CAUSE_SDK_OLD_VERSION;
        } else if (j2 < 0) {
            SdkLog.i(TAG, str + " version " + j2 + " is lower than min required version 0");
            return SessionErrorCode.CAUSE_AGENT_OLD_VERSION;
        } else if (i2 == 4) {
            SdkLog.i(TAG, "Agent is not available");
            return SessionErrorCode.CAUSE_AGENT_NOT_AVAILABLE;
        } else if (i2 == 0 || i2 == 1) {
            return SessionErrorCode.NO_PROBLEM;
        } else {
            SdkLog.i(TAG, "Agent error status is not defined.");
            return SessionErrorCode.CAUSE_UNDEFINED;
        }
    }

    private void exchangeConfiguration() {
        SdkLog.d(TAG, "exchangeConfiguration - sesAgentInstalled:" + this.mIsMobileServiceAgentInstalled + ", sesAgentEnabled:" + this.mIsMobileServiceAgentEnabled + ", saStandAlone:" + this.mIsStandAloneSA);
        if (!needExchangeInfoInit()) {
            SdkLog.i(TAG, "exchangeConfiguration : already set");
        } else if ((!this.mIsMobileServiceAgentInstalled || !this.mIsMobileServiceAgentEnabled || getCommonServiceHandler().isBound((SeMobileServiceSession) null)) && (!this.mIsStandAloneSA || getSaCommonServiceHandler().isBound((SeMobileServiceSession) null))) {
            VersionExchangeInfo versionExchangeInfo = new VersionExchangeInfo();
            versionExchangeInfo.setSdkVersion(SeMobileService.getSdkVersionCode());
            versionExchangeInfo.setAppId(this.mAppId);
            versionExchangeInfo.addService(getAllServiceNames());
            Bundle bundle = versionExchangeInfo.toBundle();
            try {
                if (this.mIsMobileServiceAgentInstalled && this.mIsMobileServiceAgentEnabled) {
                    versionExchangeInfo.put(getCommonServiceHandler().getService((SeMobileServiceSession) null).exchangeConfiguration(bundle), this.mIsStandAloneSA, new String[0]);
                }
                if (this.mIsStandAloneSA) {
                    versionExchangeInfo.put(getSaCommonServiceHandler().getService((SeMobileServiceSession) null).exchangeConfiguration(bundle), true, getSocialServiceNames());
                }
                for (String str : getAllServiceNames()) {
                    int serviceVersion = versionExchangeInfo.getServiceVersion(str);
                    int serviceStatus = versionExchangeInfo.getServiceStatus(str);
                    if (serviceVersion == 0 || serviceStatus != 0) {
                        StringBuilder u = C0212a.u("requested service:", str, "[", serviceVersion, "], status: ");
                        u.append(serviceStatus);
                        SdkLog.i(TAG, u.toString());
                    } else {
                        this.mSupportedServices.add(str);
                    }
                }
                this.mVersionExchangeInfo = versionExchangeInfo;
            } catch (Exception unused) {
                getVersionExchangeInfo().clear();
                SdkLog.i(TAG, "error during version exchange.");
            }
        } else {
            SdkLog.d(TAG, "exchangeConfiguration : common service not ready");
        }
    }

    public static synchronized SeMobileServiceBindManager get(String str, boolean z, boolean z3, boolean z7) {
        SeMobileServiceBindManager seMobileServiceBindManager;
        synchronized (SeMobileServiceBindManager.class) {
            seMobileServiceBindManager = sMap.get(str);
            if (seMobileServiceBindManager == null) {
                seMobileServiceBindManager = new SeMobileServiceBindManager(str, z, z3, z7);
                Map<String, SeMobileServiceBindManager> map = sMap;
                map.put("" + str, seMobileServiceBindManager);
            }
        }
        return seMobileServiceBindManager;
    }

    private String[] getAllServiceNames() {
        return new String[]{"AuthService", "ProfileService", "PlaceService", "SocialService"};
    }

    private synchronized ServiceHandler<ICommonService> getCommonServiceHandler() {
        SeMobileServiceBindManager seMobileServiceBindManager;
        try {
            if (this.mCommonServiceHandler == null) {
                seMobileServiceBindManager = this;
                seMobileServiceBindManager.mCommonServiceHandler = new ServiceHandler<ICommonService>(this.mAppId, CommonConstants.SERVICE_NAME_COMMON, "com.samsung.android.mobileservice", BIND_CLASS_NAME_COMMON, BIND_REQUEST_SERVICE_COMMON, this) {
                    public ICommonService asInterface(IBinder iBinder) {
                        return ICommonService.Stub.asInterface(iBinder);
                    }
                };
            } else {
                seMobileServiceBindManager = this;
            }
            ServiceHandler<ICommonService> serviceHandler = seMobileServiceBindManager.mCommonServiceHandler;
            return serviceHandler;
        } catch (Throwable th) {
            th = th;
            Throwable th2 = th;
            throw th2;
        }
    }

    private synchronized ServiceHandler<ICommonService> getSaCommonServiceHandler() {
        SeMobileServiceBindManager seMobileServiceBindManager;
        try {
            if (this.mSaCommonServiceHandler == null) {
                seMobileServiceBindManager = this;
                seMobileServiceBindManager.mSaCommonServiceHandler = new ServiceHandler<ICommonService>(this.mAppId, CommonConstants.SERVICE_NAME_SA_COMMON, CommonUtils.SAMSUNG_ACCOUNT_PACKAGE_NAME, BIND_CLASS_NAME_COMMON_FOR_SA, BIND_REQUEST_SERVICE_COMMON, this) {
                    public ICommonService asInterface(IBinder iBinder) {
                        return ICommonService.Stub.asInterface(iBinder);
                    }
                };
            } else {
                seMobileServiceBindManager = this;
            }
            ServiceHandler<ICommonService> serviceHandler = seMobileServiceBindManager.mSaCommonServiceHandler;
            return serviceHandler;
        } catch (Throwable th) {
            th = th;
            Throwable th2 = th;
            throw th2;
        }
    }

    private String[] getSaServiceNames() {
        return new String[]{"AuthService", "ProfileService", "PlaceService"};
    }

    private String getSamsungAccountPackageName() {
        if (this.mIsStandAloneSA) {
            return CommonUtils.SAMSUNG_ACCOUNT_PACKAGE_NAME;
        }
        return "com.samsung.android.mobileservice";
    }

    private String[] getSocialServiceNames() {
        return new String[]{"SocialService"};
    }

    private void onCommonSvcBound(ServiceHandler<? extends ICommonService> serviceHandler) {
        try {
            SdkLog.d(TAG, "do migration as first connection : " + serviceHandler.getServiceName());
            boolean doMigration = ((ICommonService) serviceHandler.getService((SeMobileServiceSession) null)).doMigration();
            SdkLog.d(TAG, "migration result : " + doMigration);
            exchangeConfiguration();
        } catch (Exception e) {
            SdkLog.s(e);
        }
    }

    public synchronized void addBindChangeListener(BindChangeListener bindChangeListener) {
        if (bindChangeListener != null) {
            this.bindChangeListeners.add(bindChangeListener);
        }
    }

    public Set<String> bindServices(Context context, Collection<String> collection, SeMobileServiceSession seMobileServiceSession) {
        HashSet hashSet = new HashSet();
        if (collection != null) {
            for (String next : collection) {
                ServiceHandler<?> serviceHandler = getServiceHandler(next);
                if (serviceHandler == null) {
                    SdkLog.i(TAG, "unknown service name : " + next);
                } else if (serviceHandler.bindService(context, seMobileServiceSession)) {
                    hashSet.add(next);
                }
            }
        }
        return hashSet;
    }

    public SessionErrorCode getAgentStatus() {
        boolean z;
        SessionErrorCode checkExchangeInfo;
        VersionExchangeInfo versionExchangeInfo = this.mVersionExchangeInfo;
        if (versionExchangeInfo == null || (!(z = this.mIsMobileServiceAgentInstalled) && !this.mIsMobileServiceAgentEnabled && !this.mIsStandAloneSA)) {
            return SessionErrorCode.CAUSE_AGENT_NOT_AVAILABLE;
        }
        if (z && this.mIsMobileServiceAgentEnabled && (checkExchangeInfo = checkExchangeInfo("SES", versionExchangeInfo.getAgentStatus(), (long) versionExchangeInfo.getSesVersion())) != SessionErrorCode.NO_PROBLEM) {
            return checkExchangeInfo;
        }
        if (this.mIsStandAloneSA) {
            return checkExchangeInfo("SA", versionExchangeInfo.getSaAgentStatus(), (long) versionExchangeInfo.getSaAgentVersion());
        }
        return SessionErrorCode.NO_PROBLEM;
    }

    public synchronized ServiceHandler<IMobileServiceAuth> getAuthServiceHandler() {
        SeMobileServiceBindManager seMobileServiceBindManager;
        try {
            if (this.mAuthServiceHandler == null) {
                seMobileServiceBindManager = this;
                seMobileServiceBindManager.mAuthServiceHandler = new RequesterServiceHandler<IMobileServiceAuth>(this.mAppId, "AuthService", getSamsungAccountPackageName(), BIND_CLASS_NAME_AUTH, BIND_REQUEST_SERVICE_AUTH, this) {
                    public IMobileServiceAuth asInterface(IBinder iBinder) {
                        return IMobileServiceAuth.Stub.asInterface(iBinder);
                    }
                };
            } else {
                seMobileServiceBindManager = this;
            }
            ServiceHandler<IMobileServiceAuth> serviceHandler = seMobileServiceBindManager.mAuthServiceHandler;
            return serviceHandler;
        } catch (Throwable th) {
            th = th;
            Throwable th2 = th;
            throw th2;
        }
    }

    public synchronized ServiceHandler<IMobileServicePlace> getPlaceServiceHandler() {
        SeMobileServiceBindManager seMobileServiceBindManager;
        try {
            if (this.mPlaceServiceHandler == null) {
                seMobileServiceBindManager = this;
                seMobileServiceBindManager.mPlaceServiceHandler = new RequesterServiceHandler<IMobileServicePlace>(this.mAppId, "PlaceService", getSamsungAccountPackageName(), BIND_CLASS_NAME_AUTH, BIND_REQUEST_SERVICE_PLACE, this) {
                    public IMobileServicePlace asInterface(IBinder iBinder) {
                        return IMobileServicePlace.Stub.asInterface(iBinder);
                    }
                };
            } else {
                seMobileServiceBindManager = this;
            }
            ServiceHandler<IMobileServicePlace> serviceHandler = seMobileServiceBindManager.mPlaceServiceHandler;
            return serviceHandler;
        } catch (Throwable th) {
            th = th;
            Throwable th2 = th;
            throw th2;
        }
    }

    public synchronized ServiceHandler<IMobileServiceProfile> getProfileServiceHandler() {
        SeMobileServiceBindManager seMobileServiceBindManager;
        try {
            if (this.mProfileServiceHandler == null) {
                seMobileServiceBindManager = this;
                seMobileServiceBindManager.mProfileServiceHandler = new RequesterServiceHandler<IMobileServiceProfile>(this.mAppId, "ProfileService", getSamsungAccountPackageName(), BIND_CLASS_NAME_AUTH, BIND_REQUEST_SERVICE_PROFILE, this) {
                    public IMobileServiceProfile asInterface(IBinder iBinder) {
                        return IMobileServiceProfile.Stub.asInterface(iBinder);
                    }
                };
            } else {
                seMobileServiceBindManager = this;
            }
            ServiceHandler<IMobileServiceProfile> serviceHandler = seMobileServiceBindManager.mProfileServiceHandler;
            return serviceHandler;
        } catch (Throwable th) {
            th = th;
            Throwable th2 = th;
            throw th2;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager.ServiceHandler<?> getServiceHandler(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 != 0) goto L_0x0008
            java.lang.String r2 = ""
            goto L_0x0008
        L_0x0006:
            r2 = move-exception
            goto L_0x0068
        L_0x0008:
            int r0 = r2.hashCode()     // Catch:{ all -> 0x0006 }
            switch(r0) {
                case -1348797491: goto L_0x0056;
                case -207817778: goto L_0x0048;
                case 153034988: goto L_0x003a;
                case 836900345: goto L_0x002c;
                case 1266397800: goto L_0x001e;
                case 2024019467: goto L_0x0010;
                default: goto L_0x000f;
            }     // Catch:{ all -> 0x0006 }
        L_0x000f:
            goto L_0x0064
        L_0x0010:
            java.lang.String r0 = "Common"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0006 }
            if (r2 == 0) goto L_0x0064
            com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$ServiceHandler r2 = r1.getCommonServiceHandler()     // Catch:{ all -> 0x0006 }
            monitor-exit(r1)
            return r2
        L_0x001e:
            java.lang.String r0 = "SocialService"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0006 }
            if (r2 == 0) goto L_0x0064
            com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$ServiceHandler r2 = r1.getSocialServiceHandler()     // Catch:{ all -> 0x0006 }
            monitor-exit(r1)
            return r2
        L_0x002c:
            java.lang.String r0 = "SaCommon"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0006 }
            if (r2 == 0) goto L_0x0064
            com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$ServiceHandler r2 = r1.getSaCommonServiceHandler()     // Catch:{ all -> 0x0006 }
            monitor-exit(r1)
            return r2
        L_0x003a:
            java.lang.String r0 = "ProfileService"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0006 }
            if (r2 == 0) goto L_0x0064
            com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$ServiceHandler r2 = r1.getProfileServiceHandler()     // Catch:{ all -> 0x0006 }
            monitor-exit(r1)
            return r2
        L_0x0048:
            java.lang.String r0 = "PlaceService"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0006 }
            if (r2 == 0) goto L_0x0064
            com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$ServiceHandler r2 = r1.getPlaceServiceHandler()     // Catch:{ all -> 0x0006 }
            monitor-exit(r1)
            return r2
        L_0x0056:
            java.lang.String r0 = "AuthService"
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0006 }
            if (r2 == 0) goto L_0x0064
            com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$ServiceHandler r2 = r1.getAuthServiceHandler()     // Catch:{ all -> 0x0006 }
            monitor-exit(r1)
            return r2
        L_0x0064:
            com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$ServiceHandler<com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial> r2 = r1.mUnknownServiceHandler     // Catch:{ all -> 0x0006 }
            monitor-exit(r1)
            return r2
        L_0x0068:
            monitor-exit(r1)     // Catch:{ all -> 0x0006 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager.getServiceHandler(java.lang.String):com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$ServiceHandler");
    }

    public synchronized ServiceHandler<IMobileServiceSocial> getSocialServiceHandler() {
        SeMobileServiceBindManager seMobileServiceBindManager;
        try {
            if (this.mSocialServiceHandler == null) {
                seMobileServiceBindManager = this;
                seMobileServiceBindManager.mSocialServiceHandler = new RequesterServiceHandler<IMobileServiceSocial>(this.mAppId, "SocialService", "com.samsung.android.mobileservice", BIND_CLASS_NAME_SOCIAL, (String) null, this) {
                    public IMobileServiceSocial asInterface(IBinder iBinder) {
                        return IMobileServiceSocial.Stub.asInterface(iBinder);
                    }
                };
            } else {
                seMobileServiceBindManager = this;
            }
            ServiceHandler<IMobileServiceSocial> serviceHandler = seMobileServiceBindManager.mSocialServiceHandler;
            return serviceHandler;
        } catch (Throwable th) {
            th = th;
            Throwable th2 = th;
            throw th2;
        }
    }

    public Set<String> getUnsupportedServices() {
        return this.mSupportedServices;
    }

    public VersionExchangeInfo getVersionExchangeInfo() {
        VersionExchangeInfo versionExchangeInfo = this.mVersionExchangeInfo;
        if (versionExchangeInfo == null) {
            return this.mEmptyVersionExchangeInfo;
        }
        return versionExchangeInfo;
    }

    public boolean isConnectedAll(Collection<String> collection, SeMobileServiceSession seMobileServiceSession) {
        if (needExchangeInfoInit()) {
            SdkLog.i(TAG, "isConnectedAll : needExchangeInfoInit");
            return false;
        }
        for (String next : collection) {
            if (TextUtils.isEmpty(next) || !this.mSupportedServices.contains(next)) {
                SdkLog.i(TAG, "isConnectedAll : not support service : " + next);
            } else if (!getServiceHandler(next).isBound(seMobileServiceSession)) {
                SdkLog.i(TAG, "isConnectedAll : is not bound " + next);
                return false;
            }
        }
        return true;
    }

    public boolean needExchangeInfoInit() {
        if (this.mVersionExchangeInfo == null) {
            return true;
        }
        return false;
    }

    public void onBindChanged(String str, BindState bindState, BindState bindState2) {
        if ((TextUtils.equals(CommonConstants.SERVICE_NAME_COMMON, str) || TextUtils.equals(CommonConstants.SERVICE_NAME_SA_COMMON, str)) && bindState2 == BindState.BOUND) {
            onCommonSvcBound(getServiceHandler(str));
        }
        for (BindChangeListener next : this.bindChangeListeners) {
            if (next != null) {
                next.onBindChanged(str, bindState, bindState2);
            }
        }
    }

    public void register(Collection<String> collection, SeMobileServiceSession seMobileServiceSession) {
        if (collection != null) {
            for (String serviceHandler : collection) {
                getServiceHandler(serviceHandler).register(seMobileServiceSession);
            }
        }
    }

    public synchronized void removeBindChangeListener(BindChangeListener bindChangeListener) {
        if (bindChangeListener != null) {
            this.bindChangeListeners.remove(bindChangeListener);
        }
    }

    public void reset(boolean z, boolean z3, boolean z7) {
        SdkLog.i(TAG, "reset");
        this.mIsStandAloneSA = z;
        this.mIsMobileServiceAgentInstalled = z3;
        this.mIsMobileServiceAgentEnabled = z7;
        this.mVersionExchangeInfo = null;
        this.mSupportedServices.clear();
    }

    public void unbindServices(Context context, Collection<String> collection, SeMobileServiceSession seMobileServiceSession) {
        if (collection != null) {
            for (String next : collection) {
                ServiceHandler<?> serviceHandler = getServiceHandler(next);
                if (serviceHandler == null) {
                    SdkLog.i(TAG, "unbindServices : unknown service name : " + next);
                } else if (serviceHandler.unbindable(seMobileServiceSession)) {
                    serviceHandler.unbindService(context, seMobileServiceSession);
                }
            }
            ServiceHandler[] serviceHandlerArr = {this.mAuthServiceHandler, this.mPlaceServiceHandler, this.mProfileServiceHandler, this.mSocialServiceHandler, this.mCommonServiceHandler, this.mSaCommonServiceHandler};
            int i2 = 0;
            while (i2 < 6) {
                ServiceHandler serviceHandler2 = serviceHandlerArr[i2];
                if (serviceHandler2 == null || serviceHandler2.bindState == BindState.UNBOUND) {
                    i2++;
                } else {
                    return;
                }
            }
            SdkLog.d(TAG, "all services unbound");
            reset(this.mIsStandAloneSA, this.mIsMobileServiceAgentInstalled, this.mIsMobileServiceAgentEnabled);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class ServiceHandler<T extends IInterface> implements ServiceConnection {
        private static final String TAG = "ServiceHandler";
        private final String action;
        private final String appId;
        Set<SeMobileServiceSession> bindRequestSet;
        /* access modifiers changed from: private */
        public BindState bindState;
        private final BindChangeListener listener;
        private boolean needUnbindBeforeBinding;
        private final String packageName;
        T service;
        private final String serviceClassName;
        final String serviceName;

        private void log(String str) {
            StringBuilder t = C0212a.t(str, " : ");
            t.append(this.serviceName);
            SdkLog.i(TAG, t.toString());
        }

        private void setBindState(BindState bindState2) {
            log("setBindState : " + this.bindState + " to " + bindState2);
            if (bindState2 == BindState.UNBOUND) {
                this.service = null;
            }
            BindState bindState3 = this.bindState;
            this.bindState = bindState2;
            BindChangeListener bindChangeListener = this.listener;
            if (bindChangeListener != null) {
                bindChangeListener.onBindChanged(this.serviceName, bindState3, bindState2);
            }
        }

        public abstract T asInterface(IBinder iBinder);

        /* JADX WARNING: Code restructure failed: missing block: B:25:0x004c, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a2, code lost:
            return r6;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized boolean bindService(android.content.Context r6, com.samsung.android.sdk.mobileservice.SeMobileServiceSession r7) {
            /*
                r5 = this;
                java.lang.String r0 = "bindService : already requested : "
                monitor-enter(r5)
                r1 = 0
                if (r6 != 0) goto L_0x0010
                java.lang.String r6 = "bindService fail : context is null"
                r5.log(r6)     // Catch:{ all -> 0x000d }
                monitor-exit(r5)
                return r1
            L_0x000d:
                r6 = move-exception
                goto L_0x00a3
            L_0x0010:
                java.lang.String r2 = r5.packageName     // Catch:{ all -> 0x000d }
                boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x000d }
                if (r2 == 0) goto L_0x001f
                java.lang.String r6 = "bindService fail : unknown service"
                r5.log(r6)     // Catch:{ all -> 0x000d }
                monitor-exit(r5)
                return r1
            L_0x001f:
                r5.register(r7)     // Catch:{ all -> 0x000d }
                com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$BindState r2 = r5.bindState     // Catch:{ all -> 0x000d }
                com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$BindState r3 = com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager.BindState.UNBOUND     // Catch:{ all -> 0x000d }
                r4 = 1
                if (r2 == r3) goto L_0x004d
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x000d }
                r2.<init>(r0)     // Catch:{ all -> 0x000d }
                com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$BindState r0 = r5.bindState     // Catch:{ all -> 0x000d }
                r2.append(r0)     // Catch:{ all -> 0x000d }
                java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x000d }
                r5.log(r0)     // Catch:{ all -> 0x000d }
                com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$BindState r0 = r5.bindState     // Catch:{ all -> 0x000d }
                com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$BindState r2 = com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager.BindState.BOUND     // Catch:{ all -> 0x000d }
                if (r0 != r2) goto L_0x004b
                T r0 = r5.service     // Catch:{ all -> 0x000d }
                if (r0 != 0) goto L_0x004b
                java.lang.String r0 = "state is bound but service is null."
                r5.log(r0)     // Catch:{ all -> 0x000d }
                goto L_0x004d
            L_0x004b:
                monitor-exit(r5)
                return r4
            L_0x004d:
                java.lang.String r0 = "bindService"
                r5.log(r0)     // Catch:{ all -> 0x000d }
                boolean r0 = r5.needUnbindBeforeBinding     // Catch:{ all -> 0x000d }
                if (r0 == 0) goto L_0x006c
                java.lang.String r0 = "unbind first because of garbage connection"
                r5.log(r0)     // Catch:{ all -> 0x000d }
                r6.unbindService(r5)     // Catch:{ Exception -> 0x0060 }
                goto L_0x006a
            L_0x0060:
                r0 = move-exception
                java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x000d }
                java.lang.String r2 = "ServiceHandler"
                com.samsung.android.sdk.mobileservice.util.SdkLog.e(r2, r0)     // Catch:{ all -> 0x000d }
            L_0x006a:
                r5.needUnbindBeforeBinding = r1     // Catch:{ all -> 0x000d }
            L_0x006c:
                com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager$BindState r0 = com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager.BindState.BINDING     // Catch:{ all -> 0x000d }
                r5.setBindState(r0)     // Catch:{ all -> 0x000d }
                android.content.Intent r0 = new android.content.Intent     // Catch:{ all -> 0x000d }
                r0.<init>()     // Catch:{ all -> 0x000d }
                java.lang.String r1 = r5.action     // Catch:{ all -> 0x000d }
                boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x000d }
                if (r1 != 0) goto L_0x0083
                java.lang.String r1 = r5.action     // Catch:{ all -> 0x000d }
                r0.setAction(r1)     // Catch:{ all -> 0x000d }
            L_0x0083:
                java.lang.String r1 = r5.appId     // Catch:{ all -> 0x000d }
                java.lang.String r2 = "app_id"
                r0.putExtra(r2, r1)     // Catch:{ all -> 0x000d }
                java.lang.String r1 = r5.packageName     // Catch:{ all -> 0x000d }
                java.lang.String r2 = r5.serviceClassName     // Catch:{ all -> 0x000d }
                r0.setClassName(r1, r2)     // Catch:{ all -> 0x000d }
                boolean r6 = r6.bindService(r0, r5, r4)     // Catch:{ all -> 0x000d }
                if (r6 != 0) goto L_0x00a1
                java.lang.String r0 = "bindService : request fail"
                r5.log(r0)     // Catch:{ all -> 0x000d }
                java.util.Set<com.samsung.android.sdk.mobileservice.SeMobileServiceSession> r0 = r5.bindRequestSet     // Catch:{ all -> 0x000d }
                r0.remove(r7)     // Catch:{ all -> 0x000d }
            L_0x00a1:
                monitor-exit(r5)
                return r6
            L_0x00a3:
                monitor-exit(r5)     // Catch:{ all -> 0x000d }
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager.ServiceHandler.bindService(android.content.Context, com.samsung.android.sdk.mobileservice.SeMobileServiceSession):boolean");
        }

        public T getService(SeMobileServiceSession seMobileServiceSession) {
            T t = this.service;
            if (t != null) {
                return t;
            }
            throw new NotConnectedException(C0212a.p(new StringBuilder(), this.serviceName, " is not connected"));
        }

        public String getServiceName() {
            return this.serviceName;
        }

        public boolean isBound(SeMobileServiceSession seMobileServiceSession) {
            if (!this.bindState.isBound() || this.service == null) {
                return false;
            }
            return true;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            log("onServiceConnected");
            if (iBinder == null) {
                log("IBinder is null");
                setBindState(BindState.UNBOUND);
            } else if (this.bindState == BindState.UNBOUND) {
                log("service state is unbound.");
            } else {
                this.service = asInterface(iBinder);
                setBindState(BindState.BOUND);
                this.needUnbindBeforeBinding = false;
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            log("onServiceDisconnected");
            this.needUnbindBeforeBinding = true;
            setBindState(BindState.UNBOUND);
        }

        public void register(SeMobileServiceSession seMobileServiceSession) {
            this.bindRequestSet.add(seMobileServiceSession);
        }

        public synchronized void unbindService(Context context, SeMobileServiceSession seMobileServiceSession) {
            if (context == null) {
                log("unbindService fail : context is null");
            } else if (TextUtils.isEmpty(this.packageName)) {
                log("unbindService fail : unknown service");
            } else {
                this.bindRequestSet.remove(seMobileServiceSession);
                if (this.bindState == BindState.UNBOUND) {
                    log("unbindService : not bound");
                } else if (!this.bindRequestSet.isEmpty()) {
                    log("request unbindService but other sessions are still remain. Don't unbind.");
                } else {
                    log("unbindService");
                    try {
                        context.unbindService(this);
                    } catch (Exception e) {
                        SdkLog.e(TAG, e.getMessage());
                    }
                    setBindState(BindState.UNBOUND);
                }
            }
        }

        public boolean unbindable(SeMobileServiceSession seMobileServiceSession) {
            if (this.bindState != BindState.UNBOUND) {
                return true;
            }
            return false;
        }

        private ServiceHandler() {
            this("", "unknown", "", "", "", (BindChangeListener) null);
        }

        private ServiceHandler(String str, String str2, String str3, String str4, String str5, BindChangeListener bindChangeListener) {
            this.service = null;
            this.bindState = BindState.UNBOUND;
            this.needUnbindBeforeBinding = false;
            this.bindRequestSet = new HashSet();
            this.appId = str;
            this.serviceName = str2;
            this.packageName = str3;
            this.serviceClassName = str4;
            this.action = str5;
            this.listener = bindChangeListener;
        }
    }
}
