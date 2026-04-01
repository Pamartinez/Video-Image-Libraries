package com.samsung.android.sdk.mobileservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import c0.C0086a;
import com.samsung.android.sdk.mobileservice.SeMobileServiceBindManager;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.auth.IMobileServiceAuth;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import com.samsung.android.sdk.mobileservice.place.IMobileServicePlace;
import com.samsung.android.sdk.mobileservice.profile.IMobileServiceProfile;
import com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial;
import com.samsung.android.sdk.mobileservice.util.SdkLog;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeMobileServiceSessionImpl implements SeMobileServiceSession, BindChangeListener {
    private static final int CONNECTION_TIMEOUT = 20000;
    private static final int MSG_FAILURE = 2;
    private static final int MSG_SUCCESS = 1;
    private static final int MSG_TIMEOUT = 100;
    private static final String TAG = "SeMobileServiceSession";
    /* access modifiers changed from: private */
    public HashSet<String> mAddedServices = new HashSet<>();
    private String mAppId;
    /* access modifiers changed from: private */
    public SeMobileServiceBindManager mBindManager;
    private Handler mConnectionCallbackHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            Message obtain = Message.obtain(message);
            int i2 = obtain.what;
            boolean z = true;
            if (i2 != 1) {
                if (i2 == 2) {
                    int i7 = obtain.arg1;
                    if (SeMobileServiceSessionImpl.this.mConnectionResultCallback != null) {
                        SeMobileServiceSessionImpl.this.mConnectionResultCallback.onFailure(i7);
                    }
                } else if (i2 == 100) {
                    SeMobileServiceSessionImpl.this.onConnectFail(SessionErrorCode.CAUSE_CONNECT_TIMEOUT.getValue());
                }
            } else if (SeMobileServiceSessionImpl.this.mConnectionResultCallback != null) {
                SessionErrorCode agentStatus = SeMobileServiceSessionImpl.this.mBindManager.getAgentStatus();
                if (agentStatus == SessionErrorCode.NO_PROBLEM) {
                    HashMap hashMap = new HashMap();
                    Iterator it = SeMobileServiceSessionImpl.this.mAddedServices.iterator();
                    while (it.hasNext()) {
                        String str = (String) it.next();
                        int serviceStatus = SeMobileServiceSessionImpl.this.getVersionExchangeInfoOnSession().getServiceStatus(str);
                        hashMap.put(str, Integer.valueOf(serviceStatus));
                        if (serviceStatus != 0) {
                            z = false;
                        }
                    }
                    synchronized (this) {
                        SeMobileServiceSessionImpl.this.mConnectionResultCallback.onSuccess(hashMap, z);
                    }
                    return;
                }
                SeMobileServiceSessionImpl.this.onConnectFail(agentStatus.getValue());
            }
        }
    };
    /* access modifiers changed from: private */
    public SeMobileServiceSession.ConnectionResultCallback mConnectionResultCallback;
    private Context mContext;
    private ExecutorService mExecutorService;
    private boolean mIsMobileServiceAgentEnable;
    private boolean mIsMobileServiceAgentInstalled;
    /* access modifiers changed from: private */
    public boolean mIsStandAloneSA;
    private SeMobileServiceSession.OnAgentUpdatedListener mOnAgentUpdatedListener = null;
    private BroadcastReceiver mReceiver = null;
    /* access modifiers changed from: private */
    public SeMobileServiceSession.ServiceConnectionListener mServiceConnectionListener = null;
    private VersionExchangeInfo mVersionExchangeInfo = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SesPackageEventReceiver extends BroadcastReceiver {
        private SesPackageEventReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            SdkLog.i(SeMobileServiceSessionImpl.TAG, "onReceive - receive " + intent.getAction());
            Uri data = intent.getData();
            if (data != null) {
                String schemeSpecificPart = data.getSchemeSpecificPart();
                if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
                    if (CommonUtils.MOBILE_SERVICE_PACKAGE_NAME.equals(schemeSpecificPart) || (SeMobileServiceSessionImpl.this.mIsStandAloneSA && CommonUtils.SAMSUNG_ACCOUNT_PACKAGE_NAME.equals(schemeSpecificPart))) {
                        SeMobileServiceSessionImpl.this.handlePackageAdded(context, schemeSpecificPart);
                    }
                } else if ("android.intent.action.PACKAGE_CHANGED".equals(intent.getAction()) && CommonUtils.MOBILE_SERVICE_PACKAGE_NAME.equals(schemeSpecificPart)) {
                    SeMobileServiceSessionImpl.this.handlePackageChanged(schemeSpecificPart);
                }
            }
        }
    }

    public SeMobileServiceSessionImpl(Context context, HashSet<String> hashSet, String str, SeMobileServiceSession.ConnectionResultCallback connectionResultCallback) {
        this.mContext = context.getApplicationContext();
        this.mConnectionResultCallback = connectionResultCallback;
        this.mAddedServices.addAll(hashSet);
        this.mAppId = str;
        this.mIsStandAloneSA = CommonUtils.isStandAloneSamsungAccountSupported(this.mContext);
        this.mIsMobileServiceAgentInstalled = SeMobileService.isAgentInstalled(getContext());
        boolean isAgentEnabled = SeMobileService.isAgentEnabled(getContext());
        this.mIsMobileServiceAgentEnable = isAgentEnabled;
        this.mBindManager = SeMobileServiceBindManager.get(this.mAppId, this.mIsStandAloneSA, this.mIsMobileServiceAgentInstalled, isAgentEnabled);
        this.mExecutorService = Executors.newCachedThreadPool();
    }

    private synchronized void connectInternal() {
        SdkLog.i(TAG, "connectInternal" + SdkLog.getReference(this));
        setCallbackTimeout();
        this.mBindManager.addBindChangeListener(this);
        if (this.mBindManager.needExchangeInfoInit()) {
            SdkLog.i(TAG, "needExchangeInfoInit : true on connectInternal");
            Set<String> commonServices = getCommonServices();
            if (this.mBindManager.bindServices(this.mContext, commonServices, this).size() != commonServices.size()) {
                onConnectFail(SessionErrorCode.CAUSE_AGENT_NOT_AVAILABLE.getValue());
                return;
            }
        }
        this.mBindManager.register(this.mAddedServices, this);
        if (isConnectedAll()) {
            onConnectComplete();
        } else {
            this.mBindManager.bindServices(this.mContext, this.mAddedServices, this);
        }
    }

    private synchronized void disconnectInternal() {
        SdkLog.d(TAG, "disconnectInternal " + SdkLog.getReference(this));
        this.mBindManager.removeBindChangeListener(this);
        HashSet hashSet = new HashSet(getCommonServices());
        hashSet.addAll(this.mAddedServices);
        this.mBindManager.unbindServices(this.mContext, hashSet, this);
    }

    private Set<String> getCommonServices() {
        HashSet hashSet = new HashSet();
        if (this.mIsMobileServiceAgentInstalled && this.mIsMobileServiceAgentEnable) {
            hashSet.add(CommonConstants.SERVICE_NAME_COMMON);
        }
        if (this.mIsStandAloneSA) {
            hashSet.add(CommonConstants.SERVICE_NAME_SA_COMMON);
        }
        if (hashSet.isEmpty()) {
            SdkLog.i(TAG, "getCommonServices is empty" + SdkLog.getReference(this));
        }
        return hashSet;
    }

    /* access modifiers changed from: private */
    public VersionExchangeInfo getVersionExchangeInfoOnSession() {
        VersionExchangeInfo versionExchangeInfo = this.mVersionExchangeInfo;
        if (versionExchangeInfo != null) {
            return versionExchangeInfo;
        }
        SdkLog.i(TAG, "getVersionExchangeInfoOnSession: mVersionExchangeInfo is null");
        return this.mBindManager.mEmptyVersionExchangeInfo;
    }

    /* access modifiers changed from: private */
    public void handlePackageAdded(Context context, String str) {
        SdkLog.i(TAG, "handlePackageAdded - package is " + str);
        SeMobileServiceSession.OnAgentUpdatedListener onAgentUpdatedListener = this.mOnAgentUpdatedListener;
        if (onAgentUpdatedListener != null) {
            onAgentUpdatedListener.onAgentUpdated();
        }
        if (CommonUtils.MOBILE_SERVICE_PACKAGE_NAME.equals(str)) {
            this.mIsMobileServiceAgentInstalled = SeMobileService.isAgentInstalled(getContext());
            this.mIsMobileServiceAgentEnable = SeMobileService.isAgentEnabled(getContext());
        }
        boolean isStandAloneSamsungAccountSupported = CommonUtils.isStandAloneSamsungAccountSupported(context);
        this.mIsStandAloneSA = isStandAloneSamsungAccountSupported;
        this.mBindManager.reset(isStandAloneSamsungAccountSupported, this.mIsMobileServiceAgentInstalled, this.mIsMobileServiceAgentEnable);
    }

    /* access modifiers changed from: private */
    public void handlePackageChanged(String str) {
        SdkLog.i(TAG, "handlePackageChanged - package is " + str);
        boolean isAgentEnabled = SeMobileService.isAgentEnabled(getContext());
        if (isAgentEnabled != this.mIsMobileServiceAgentEnable) {
            this.mIsMobileServiceAgentEnable = isAgentEnabled;
            this.mBindManager.reset(this.mIsStandAloneSA, this.mIsMobileServiceAgentInstalled, isAgentEnabled);
        }
    }

    private SessionErrorCode isAgentAvailable() {
        this.mIsMobileServiceAgentInstalled = SeMobileService.isAgentInstalled(getContext());
        this.mIsMobileServiceAgentEnable = SeMobileService.isAgentEnabled(getContext());
        SdkLog.d(TAG, "SESInstalled:" + this.mIsMobileServiceAgentInstalled + ", SESEnabled:" + this.mIsMobileServiceAgentEnable);
        if (this.mIsStandAloneSA) {
            if (!SeMobileService.isSaAgentInstalled(getContext())) {
                return SessionErrorCode.CAUSE_AGENT_NOT_INSTALLED;
            }
        } else if (!this.mIsMobileServiceAgentInstalled) {
            return SessionErrorCode.CAUSE_AGENT_NOT_INSTALLED;
        } else {
            if (!this.mIsMobileServiceAgentEnable) {
                return SessionErrorCode.CAUSE_AGENT_DISABLED;
            }
        }
        return SessionErrorCode.NO_PROBLEM;
    }

    private boolean isConnectedAll() {
        return this.mBindManager.isConnectedAll(this.mAddedServices, this);
    }

    private void onAddedSvcBindChanged(final String str, SeMobileServiceBindManager.BindState bindState, SeMobileServiceBindManager.BindState bindState2) {
        final int i2;
        SeMobileServiceBindManager.BindState bindState3 = SeMobileServiceBindManager.BindState.BINDING;
        if ((bindState != bindState3 || bindState2 != SeMobileServiceBindManager.BindState.BOUND) && bindState2 != bindState3) {
            if (bindState2 == SeMobileServiceBindManager.BindState.BOUND) {
                i2 = 1;
            } else {
                i2 = -1;
            }
            this.mExecutorService.execute(new Runnable() {
                public void run() {
                    if (SeMobileServiceSessionImpl.this.mServiceConnectionListener != null) {
                        SeMobileServiceSessionImpl.this.mServiceConnectionListener.onChanged(i2, str);
                    }
                }
            });
        }
    }

    private void onConnectComplete() {
        if (isConnectedAll()) {
            if (this.mBindManager.needExchangeInfoInit()) {
                SdkLog.i(TAG, "onConnectComplete : need exchange info " + SdkLog.getReference(this));
                return;
            }
            SdkLog.i(TAG, "onConnectComplete" + SdkLog.getReference(this));
            this.mVersionExchangeInfo = this.mBindManager.getVersionExchangeInfo();
            sendSuccessCallback();
        }
    }

    /* access modifiers changed from: private */
    public void onConnectFail(int i2) {
        StringBuilder o2 = C0086a.o(i2, "onConnectFail : ", NumericEnum.SEP);
        o2.append(SdkLog.getReference(this));
        SdkLog.i(TAG, o2.toString());
        disconnectInternal();
        sendErrorCallback(i2);
    }

    private void registerAgentUpdateReceiver() {
        if (this.mReceiver == null) {
            SdkLog.d(TAG, "registerReceiver");
            this.mReceiver = new SesPackageEventReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addDataScheme("package");
            intentFilter.addDataSchemeSpecificPart(CommonUtils.MOBILE_SERVICE_PACKAGE_NAME, 0);
            intentFilter.addDataSchemeSpecificPart(CommonUtils.SAMSUNG_ACCOUNT_PACKAGE_NAME, 0);
            this.mContext.registerReceiver(this.mReceiver, intentFilter);
        }
    }

    private void sendErrorCallback(int i2) {
        this.mConnectionCallbackHandler.removeMessages(100);
        Handler handler = this.mConnectionCallbackHandler;
        handler.sendMessage(handler.obtainMessage(2, i2, 0));
    }

    private void sendSuccessCallback() {
        if (this.mConnectionCallbackHandler.hasMessages(100)) {
            this.mConnectionCallbackHandler.removeMessages(100);
            Handler handler = this.mConnectionCallbackHandler;
            handler.sendMessage(handler.obtainMessage(1));
        }
    }

    private void setCallbackTimeout() {
        this.mConnectionCallbackHandler.removeMessages(100);
        Handler handler = this.mConnectionCallbackHandler;
        handler.sendMessageDelayed(handler.obtainMessage(100), 20000);
    }

    private void unregisterAgentUpdateReceiver() {
        if (this.mReceiver != null) {
            SdkLog.i(TAG, "unregisterReceiver Receiver");
            this.mContext.unregisterReceiver(this.mReceiver);
            this.mReceiver = null;
        }
    }

    public void connect() {
        SdkLog.i(TAG, "connect " + SdkLog.getReference(this));
        SessionErrorCode isAgentAvailable = isAgentAvailable();
        if (isAgentAvailable != SessionErrorCode.NO_PROBLEM) {
            sendErrorCallback(isAgentAvailable.getValue());
            SdkLog.i(TAG, "MobileService agent is not installed, errorCode:" + isAgentAvailable.getValue() + " : " + SdkLog.getReference(this));
            return;
        }
        registerAgentUpdateReceiver();
        connectInternal();
    }

    public void disconnect() {
        SdkLog.i(TAG, "disconnect " + SdkLog.getReference(this));
        try {
            unregisterAgentUpdateReceiver();
        } catch (Exception unused) {
            SdkLog.i(TAG, "receiver is not registered. " + SdkLog.getReference(this));
        }
        this.mVersionExchangeInfo = null;
        disconnectInternal();
        SdkLog.i(TAG, "disconnect done " + SdkLog.getReference(this));
    }

    public void finalize() {
        super.finalize();
    }

    public String getAppId() {
        return this.mAppId;
    }

    public IMobileServiceAuth getAuthService() {
        return this.mBindManager.getAuthServiceHandler().getService(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        com.samsung.android.sdk.mobileservice.util.SdkLog.i(TAG, "getSocialService() return null! " + com.samsung.android.sdk.mobileservice.util.SdkLog.getReference(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0061, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0078, code lost:
        com.samsung.android.sdk.mobileservice.util.SdkLog.s(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0021, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x004e */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0021 A[Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }, ExcHandler: RemoteException | NotConnectedException | NullPointerException (r1v3 'e' java.lang.Exception A[CUSTOM_DECLARE, Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }]), Splitter:B:1:0x0007] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getAuthorized() {
        /*
            r5 = this;
            java.lang.String r0 = "SeMobileServiceSession"
            java.lang.String r1 = "getSocialService() return null! "
            java.lang.String r2 = "getAuthService() return null! "
            r3 = 0
            com.samsung.android.sdk.mobileservice.auth.IMobileServiceAuth r4 = r5.getAuthService()     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            if (r4 != 0) goto L_0x0023
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            r1.<init>(r2)     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            java.lang.String r2 = com.samsung.android.sdk.mobileservice.util.SdkLog.getReference(r5)     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            r1.append(r2)     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            java.lang.String r1 = r1.toString()     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            com.samsung.android.sdk.mobileservice.util.SdkLog.i(r0, r1)     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            return r3
        L_0x0021:
            r1 = move-exception
            goto L_0x0078
        L_0x0023:
            com.samsung.android.sdk.mobileservice.auth.IMobileServiceAuth r2 = r5.getAuthService()     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            android.os.Bundle r2 = r2.getAuthInfoCached()     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            if (r2 == 0) goto L_0x007b
            boolean r2 = r2.isEmpty()     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            if (r2 != 0) goto L_0x007b
            android.content.Context r2 = r5.mContext     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            boolean r2 = com.samsung.android.sdk.mobileservice.common.CommonUtils.isStandAloneSamsungAccountSupported(r2)     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            r4 = 1
            if (r2 == 0) goto L_0x0064
            boolean r2 = r5.mIsMobileServiceAgentInstalled     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            if (r2 == 0) goto L_0x0062
            boolean r2 = r5.mIsMobileServiceAgentEnable     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            if (r2 != 0) goto L_0x0045
            goto L_0x0062
        L_0x0045:
            com.samsung.android.sdk.mobileservice.social.IMobileServiceSocial r2 = r5.getSocialService()     // Catch:{ NotConnectedException -> 0x004e, RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            android.os.Bundle r1 = r2.getDeviceAuthInfoCached()     // Catch:{ NotConnectedException -> 0x004e, RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            goto L_0x006c
        L_0x004e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            r2.<init>(r1)     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            java.lang.String r1 = com.samsung.android.sdk.mobileservice.util.SdkLog.getReference(r5)     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            r2.append(r1)     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            java.lang.String r1 = r2.toString()     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            com.samsung.android.sdk.mobileservice.util.SdkLog.i(r0, r1)     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            return r4
        L_0x0062:
            r5 = 2
            return r5
        L_0x0064:
            com.samsung.android.sdk.mobileservice.auth.IMobileServiceAuth r1 = r5.getAuthService()     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            android.os.Bundle r1 = r1.getDeviceAuthInfoCached()     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
        L_0x006c:
            if (r1 == 0) goto L_0x0076
            boolean r1 = r1.isEmpty()     // Catch:{ RemoteException | NotConnectedException | NullPointerException -> 0x0021 }
            if (r1 != 0) goto L_0x0076
            r3 = 3
            goto L_0x007b
        L_0x0076:
            r3 = r4
            goto L_0x007b
        L_0x0078:
            com.samsung.android.sdk.mobileservice.util.SdkLog.s(r1)
        L_0x007b:
            java.lang.String r1 = "getAuthorized:"
            java.lang.String r2 = ":"
            java.lang.StringBuilder r1 = c0.C0086a.o(r3, r1, r2)
            java.lang.String r5 = com.samsung.android.sdk.mobileservice.util.SdkLog.getReference(r5)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            com.samsung.android.sdk.mobileservice.util.SdkLog.i(r0, r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.SeMobileServiceSessionImpl.getAuthorized():int");
    }

    public Context getContext() {
        return this.mContext;
    }

    public long getLatestAgentVersionInGalaxyApps() {
        SdkLog.i(TAG, "getLatestSaAgentVersionInGalaxyApps");
        return getVersionExchangeInfoOnSession().getAgentLastestVersionInGalaxyApps();
    }

    public long getLatestSaAgentVersionInGalaxyApps() {
        SdkLog.i(TAG, "getLatestSaAgentVersionInGalaxyApps");
        return getVersionExchangeInfoOnSession().getSaAgentLastestVersionInGalaxyApps();
    }

    public IMobileServicePlace getPlaceService() {
        return this.mBindManager.getPlaceServiceHandler().getService(this);
    }

    public IMobileServiceProfile getProfileService() {
        return this.mBindManager.getProfileServiceHandler().getService(this);
    }

    public long getSamsungAccountAgentVersion() {
        SdkLog.i(TAG, "getSamsungAccountAgentVersion");
        return (long) getVersionExchangeInfoOnSession().getSaAgentVersion();
    }

    public long getSamsungExperienceServiceAgentVersion() {
        SdkLog.i(TAG, "getSamsungExperienceServiceAgentVersion");
        return (long) getVersionExchangeInfoOnSession().getSesVersion();
    }

    public int getSeMobileServiceSupportApiVersion(String str) {
        return getVersionExchangeInfoOnSession().getApiVersion(str);
    }

    public int getServiceStatus(String str) {
        return getVersionExchangeInfoOnSession().getServiceStatus(str);
    }

    public IMobileServiceSocial getSocialService() {
        return this.mBindManager.getSocialServiceHandler().getService(this);
    }

    public boolean isAddedService(String str) {
        return this.mAddedServices.contains(str);
    }

    public boolean isAgentEnabled() {
        return this.mIsMobileServiceAgentEnable;
    }

    public boolean isAgentInstalled() {
        return this.mIsMobileServiceAgentInstalled;
    }

    public boolean isNoMoreSupportedSemsAgentVersion(int i2) {
        boolean z;
        SdkLog.d(TAG, "isNoMoreSupportedSemsAgentVersion");
        if (getVersionExchangeInfoOnSession().getSesVersion() >= i2) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            SdkLog.d(TAG, "isNoMoreSupportedSemsAgentVersion: not support version");
        }
        return z;
    }

    public boolean isServiceConnected(String str) {
        return this.mBindManager.getServiceHandler(str).isBound(this);
    }

    public boolean isSessionConnected() {
        return isConnectedAll();
    }

    public boolean isSupportedApi(String str) {
        if (getSeMobileServiceSupportApiVersion(str) > 0) {
            return true;
        }
        return false;
    }

    public boolean isSupportedSaAgentVersion(int i2) {
        boolean z;
        SdkLog.d(TAG, "isSupportedSaAgentVersion");
        if (getVersionExchangeInfoOnSession().getSaAgentVersion() >= i2) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            SdkLog.d(TAG, "isSupportedSaAgentVersion: not support version");
        }
        return z;
    }

    public boolean isSupportedSemsAgentVersionBetween(int i2, int i7) {
        boolean z;
        SdkLog.d(TAG, "isSupportedSemsAgentVersionBetween - more than : " + i2 + ", less than : " + i7);
        int sesVersion = getVersionExchangeInfoOnSession().getSesVersion();
        if (sesVersion < i2 || sesVersion >= i7) {
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            SdkLog.d(TAG, "isSupportedSemsAgentVersionBetween: not between version : " + i2 + ", less than : " + i7);
        }
        return z;
    }

    public boolean isSupportedSemsAgentVersionMoreThan(int i2) {
        boolean z;
        SdkLog.d(TAG, "isSupportedSemsAgentVersionMoreThan - more than : " + i2);
        if (getVersionExchangeInfoOnSession().getSesVersion() >= i2) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            SdkLog.d(TAG, "isSupportedSemsAgentVersionMoreThan: not more version : " + i2);
        }
        return z;
    }

    public void onBindChanged(String str, SeMobileServiceBindManager.BindState bindState, SeMobileServiceBindManager.BindState bindState2) {
        if (this.mAddedServices.contains(str)) {
            onAddedSvcBindChanged(str, bindState, bindState2);
        }
        if (bindState2 == SeMobileServiceBindManager.BindState.BOUND) {
            onConnectComplete();
        }
    }

    public void reconnect() {
        SdkLog.i(TAG, "reconnect " + SdkLog.getReference(this));
        if (isConnectedAll()) {
            SdkLog.i(TAG, "reconnect : already connected" + SdkLog.getReference(this));
            return;
        }
        connectInternal();
    }

    public void setOnAgentUpdatedListener(SeMobileServiceSession.OnAgentUpdatedListener onAgentUpdatedListener) {
        this.mOnAgentUpdatedListener = onAgentUpdatedListener;
    }

    public void setSessionListener(SeMobileServiceSession.ServiceConnectionListener serviceConnectionListener) {
        this.mServiceConnectionListener = serviceConnectionListener;
    }
}
