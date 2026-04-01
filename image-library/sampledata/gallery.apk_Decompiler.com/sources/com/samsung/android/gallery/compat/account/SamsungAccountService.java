package com.samsung.android.gallery.compat.account;

import N2.j;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.msc.sa.aidl.ISACallback;
import com.msc.sa.aidl.ISAService;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.auth.AuthConstants;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import com.samsung.scsp.media.api.database.url.OneDriveUrlReaderContract;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SamsungAccountService {
    protected final Object LOCK = new Object();
    protected final String TAG = getClass().getSimpleName();
    private volatile Bundle mBundle;
    String mRegCode;
    ISAService mService;
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(SamsungAccountService.this.TAG, "onServiceConnected");
            SamsungAccountService.this.onServiceConnected(componentName, iBinder);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            boolean z;
            String str = SamsungAccountService.this.TAG;
            StringBuilder sb2 = new StringBuilder("onServiceDisconnected {");
            if (SamsungAccountService.this.mService != null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            sb2.append("}");
            Log.d(str, sb2.toString());
            SamsungAccountService.this.mService = null;
        }
    };

    private boolean hasAccount(Context context) {
        try {
            Account[] accountsByType = AccountManager.get(context).getAccountsByType(CommonUtils.SAMSUNG_ACCOUNT_PACKAGE_NAME);
            if (accountsByType == null || accountsByType.length <= 0) {
                return false;
            }
            return true;
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("hasAccount failed. e="), this.TAG);
            return false;
        }
    }

    public boolean bindService(Context context) {
        return bindService(context, this.mServiceConnection);
    }

    public Bundle buildReqBundle() {
        Bundle bundle = new Bundle();
        bundle.putStringArray("additional", new String[]{OneDriveUrlReaderContract.Entry.COLUMN_NAME_USER_ID, "cc", AuthConstants.EXTRA_API_SERVER_URL});
        return bundle;
    }

    public long getTimeoutValue() {
        return 1200;
    }

    public boolean isAccountServiceAlive() {
        if (this.mService == null || this.mRegCode == null) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0025 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle loadBundle(android.content.Context r8) {
        /*
            r7 = this;
            long r0 = java.lang.System.currentTimeMillis()
            boolean r2 = r7.hasAccount(r8)
            if (r2 == 0) goto L_0x0033
            boolean r3 = r7.isAccountServiceAlive()
            if (r3 != 0) goto L_0x0033
            boolean r3 = r7.bindService(r8)
            if (r3 == 0) goto L_0x0033
            java.lang.Object r3 = r7.LOCK
            monitor-enter(r3)
            java.lang.Object r4 = r7.LOCK     // Catch:{ InterruptedException -> 0x0025 }
            long r5 = r7.getTimeoutValue()     // Catch:{ InterruptedException -> 0x0025 }
            r4.wait(r5)     // Catch:{ InterruptedException -> 0x0025 }
            goto L_0x002c
        L_0x0023:
            r7 = move-exception
            goto L_0x0031
        L_0x0025:
            java.lang.String r4 = r7.TAG     // Catch:{ all -> 0x0023 }
            java.lang.String r5 = "loadBundle#wait interrupted"
            android.util.Log.d(r4, r5)     // Catch:{ all -> 0x0023 }
        L_0x002c:
            monitor-exit(r3)     // Catch:{ all -> 0x0023 }
            r7.unbindService(r8)
            goto L_0x0033
        L_0x0031:
            monitor-exit(r3)     // Catch:{ all -> 0x0023 }
            throw r7
        L_0x0033:
            java.lang.String r8 = r7.TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "loadBundle {"
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r2 = ", "
            r3.append(r2)
            boolean r2 = r7.isAccountServiceAlive()
            r3.append(r2)
            java.lang.String r2 = ", "
            r3.append(r2)
            r3.append(r7)
            java.lang.String r2 = "} +"
            r3.append(r2)
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r0
            r3.append(r4)
            java.lang.String r0 = r3.toString()
            android.util.Log.d(r8, r0)
            android.os.Bundle r7 = r7.mBundle
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.compat.account.SamsungAccountService.loadBundle(android.content.Context):android.os.Bundle");
    }

    public void onReceiveAccessToken(int i2, boolean z, Bundle bundle) {
        boolean z3;
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("onReceiveAccessToken#");
        sb2.append(i2);
        sb2.append(" {");
        sb2.append(z);
        sb2.append(',');
        if (bundle != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        sb2.append(z3);
        sb2.append("}");
        Log.d(str, sb2.toString());
        if (z) {
            this.mBundle = bundle;
        }
        terminate();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        boolean z;
        try {
            this.mService = ISAService.Stub.asInterface(iBinder);
            String registerCallback = this.mService.registerCallback("22n6hzkam0", "", "com.sec.android.gallery3d", new ISACallback.Stub() {
                public void onReceiveAccessToken(int i2, boolean z, Bundle bundle) {
                    SamsungAccountService.this.onReceiveAccessToken(i2, z, bundle);
                }

                public void onReceiveRequiredConsent(int i2, boolean z, Bundle bundle) {
                    SamsungAccountService.this.onReceiveRequiredConsent(i2, z, bundle);
                }

                public void onReceiveAuthCode(int i2, boolean z, Bundle bundle) {
                }

                public void onReceiveChecklistValidation(int i2, boolean z, Bundle bundle) {
                }

                public void onReceiveClearConsentData(int i2, boolean z, Bundle bundle) {
                }

                public void onReceiveDisclaimerAgreement(int i2, boolean z, Bundle bundle) {
                }

                public void onReceivePasswordConfirmation(int i2, boolean z, Bundle bundle) {
                }

                public void onReceiveRLControlFMM(int i2, boolean z, Bundle bundle) {
                }

                public void onReceiveRubinRequest(int i2, boolean z, Bundle bundle) {
                }

                public void onReceiveSCloudAccessToken(int i2, boolean z, Bundle bundle) {
                }
            });
            this.mRegCode = registerCallback;
            if (registerCallback == null || !requestAccessToken()) {
                String str = this.TAG;
                StringBuilder sb2 = new StringBuilder("onServiceConnected#request failed {");
                if (this.mRegCode != null) {
                    z = true;
                } else {
                    z = false;
                }
                sb2.append(z);
                sb2.append("}");
                Log.d(str, sb2.toString());
                terminate();
            }
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("onServiceConnected#request failed. e="), this.TAG);
        }
    }

    public boolean requestAccessToken() {
        boolean z;
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("requestAccessToken {");
        if (this.mRegCode != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append("}");
        Log.d(str, sb2.toString());
        try {
            return this.mService.requestAccessToken(hashCode(), this.mRegCode, buildReqBundle());
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("requestAccessToken failed. e="), this.TAG);
            return false;
        }
    }

    public void terminate() {
        synchronized (this.LOCK) {
            this.LOCK.notify();
        }
    }

    public String toSecured(String str, int i2) {
        int i7;
        if (str != null) {
            i7 = str.length();
        } else {
            i7 = 0;
        }
        int min = Math.min(i7, i2);
        if (min <= 0) {
            return "n/a";
        }
        return str.substring(0, min) + "..";
    }

    public String toString() {
        Bundle bundle = this.mBundle;
        if (bundle == null) {
            return "SAS[null]";
        }
        return "SAS[" + toSecured(bundle.getString(AuthConstants.EXTRA_ACCESS_TOKEN), 4) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + toSecured(bundle.getString("cc"), 2) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + toSecured(bundle.getString(OneDriveUrlReaderContract.Entry.COLUMN_NAME_USER_ID), 3) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + toSecured(bundle.getString(AuthConstants.EXTRA_API_SERVER_URL), 3) + "]";
    }

    public void unbindService(Context context) {
        boolean z;
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("unbindService {");
        boolean z3 = false;
        if (this.mService != null) {
            z = true;
        } else {
            z = false;
        }
        sb2.append(z);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.mRegCode != null) {
            z3 = true;
        }
        sb2.append(z3);
        sb2.append("}");
        Log.d(str, sb2.toString());
        if (isAccountServiceAlive()) {
            try {
                this.mService.unregisterCallback(this.mRegCode);
            } catch (Exception e) {
                j.D(e, new StringBuilder("unbindService#unregisterCallback failed. e="), this.TAG);
            }
        }
        this.mService = null;
        this.mRegCode = null;
        try {
            context.unbindService(this.mServiceConnection);
        } catch (Error | Exception e7) {
            C0212a.y(e7, new StringBuilder("unbindService failed. e="), this.TAG);
        }
    }

    public boolean bindService(Context context, ServiceConnection serviceConnection) {
        Log.d(this.TAG, "bindService");
        try {
            Intent intent = new Intent("com.msc.action.samsungaccount.REQUEST_SERVICE");
            intent.setClassName(CommonUtils.SAMSUNG_ACCOUNT_PACKAGE_NAME, "com.msc.sa.service.RequestService");
            if (context.bindService(intent, serviceConnection, 1)) {
                return true;
            }
            Log.w(this.TAG, "bindService failed");
            return false;
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("bindService failed. e="), this.TAG);
            return false;
        }
    }

    public void onReceiveRequiredConsent(int i2, boolean z, Bundle bundle) {
    }
}
