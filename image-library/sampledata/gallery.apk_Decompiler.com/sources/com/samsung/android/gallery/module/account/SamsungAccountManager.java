package com.samsung.android.gallery.module.account;

import A.a;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.mobileservice.common.CommonUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;
import x6.f;
import x7.l;
import z8.C0718a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SamsungAccountManager {
    private static final SamsungAccountManager sInstance = new SamsungAccountManager();
    private BroadcastReceiver mBroadcastReceiver;
    private FamilyMemberType mFamilyMemberType;
    private String mGUID;
    private final ArrayList<OnAccountUpdatedListener> mListener = new ArrayList<>();
    private Object mObserver;
    private Account mSamsungAccount;
    private boolean mSyncStatus;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum FamilyMemberType {
        NONE,
        ORGANIZER,
        MEMBER,
        CHILD
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnAccountUpdatedListener {
        void onSyncAccountsUpdated(Account account, boolean z);
    }

    private SamsungAccountManager() {
    }

    private static boolean equalAccount(Account account, Account account2) {
        return Objects.equals(account, account2);
    }

    private Context getAppContext() {
        return AppResources.getAppContext();
    }

    public static int getChildGraduateAge(Context context) {
        Bundle bundle;
        Bundle familyServiceInfo = getFamilyServiceInfo(context);
        if (familyServiceInfo == null || familyServiceInfo.getInt("result_code", 1) != 0 || (bundle = familyServiceInfo.getBundle("result_bundle")) == null) {
            return -1;
        }
        return bundle.getInt("childGraduateAge", -1);
    }

    private static Bundle getFamilyServiceInfo(Context context) {
        if (context != null) {
            try {
                Bundle call = context.getContentResolver().call(Uri.parse("content://com.samsung.android.samsungaccount.accountmanagerprovider"), "getFamilyServiceInfo", "22n6hzkam0", (Bundle) null);
                if (call != null) {
                    if (call.getInt("result_code", 1) == 0) {
                        return call;
                    }
                    Log.w("SamsungAccountManager", "getFamilyServiceInfo failed " + call.getString("result_message", ""));
                    return null;
                }
            } catch (Exception e) {
                a.s(e, new StringBuilder("getFamilyServiceInfo failed "), "SamsungAccountManager");
            }
        }
        return null;
    }

    public static SamsungAccountManager getInstance() {
        return sInstance;
    }

    private Account getSamsungAccount(Context context) {
        try {
            Account[] accountsByType = AccountManager.get(context).getAccountsByType(CommonUtils.SAMSUNG_ACCOUNT_PACKAGE_NAME);
            if (accountsByType.length > 0) {
                return accountsByType[0];
            }
            return null;
        } catch (Exception e) {
            a.s(e, new StringBuilder("reloadAccounts failed e="), "SamsungAccountManager");
            return null;
        }
    }

    public static boolean isChildAccount(Context context) {
        boolean z = false;
        try {
            Bundle call = context.getContentResolver().call(Uri.parse("content://com.samsung.android.samsungaccount.accountmanagerprovider"), "isChildAccount", "22n6hzkam0", (Bundle) null);
            if (call != null) {
                int i2 = call.getInt("result_code", 1);
                String string = call.getString("result_message", "");
                if (i2 == 0 && SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(string)) {
                    z = true;
                }
                Log.i("SamsungAccountManager", "isChildAccount : " + Logger.v(Boolean.valueOf(z), Integer.valueOf(i2), string));
            }
            return z;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("isChildAccount failed "), "SamsungAccountManager");
            return false;
        }
    }

    public static boolean isSamsungAiSupportAccount(Context context) {
        if (context == null) {
            return false;
        }
        if (Features.isEnabled(Features.IS_KOREAN_DEVICE) || !isChildAccount(context)) {
            return true;
        }
        return false;
    }

    public static boolean isSupportFamilyService(Context context) {
        Bundle bundle;
        Bundle familyServiceInfo = getFamilyServiceInfo(context);
        if (familyServiceInfo == null || familyServiceInfo.getInt("result_code", 1) != 0 || (bundle = familyServiceInfo.getBundle("result_bundle")) == null || !bundle.getBoolean("isSupportFamilyService", false)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getGUID$0(Function function) {
        this.mGUID = (String) function.apply(this.mSamsungAccount.name);
    }

    /* access modifiers changed from: private */
    public void notifyChange(int i2) {
        Context appContext = getAppContext();
        if (appContext == null) {
            Log.e("SamsungAccountManager", "fail notify");
            return;
        }
        Account samsungAccount = getSamsungAccount(appContext);
        boolean isSyncOn = SamsungCloudCompat.isSyncOn(appContext);
        if (!equalAccount(this.mSamsungAccount, samsungAccount) || this.mSyncStatus != isSyncOn) {
            this.mSamsungAccount = samsungAccount;
            this.mSyncStatus = isSyncOn;
            ThreadUtil.postOnUiThread(new l(13, this));
        }
    }

    private void registerObserver() {
        this.mObserver = ContentResolver.addStatusChangeListener(8, new C0718a(this));
    }

    private void unregisterObserver() {
        Object obj = this.mObserver;
        if (obj != null) {
            ContentResolver.removeStatusChangeListener(obj);
            this.mObserver = null;
        } else if (this.mBroadcastReceiver != null) {
            AndroidCompat.unregisterReceiver(getAppContext(), this.mBroadcastReceiver);
            this.mBroadcastReceiver = null;
        }
    }

    public void addListener(OnAccountUpdatedListener onAccountUpdatedListener) {
        synchronized (this.mListener) {
            try {
                this.mListener.add(onAccountUpdatedListener);
                if (this.mListener.size() == 1) {
                    registerObserver();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Account getAccount() {
        return this.mSamsungAccount;
    }

    public String getGUID(Function<String, String> function) {
        if (this.mGUID == null) {
            if (!hasAccount()) {
                this.mSamsungAccount = getSamsungAccount(getAppContext());
            }
            LatchBuilder.executeLatch(1000, new f(10, this, function));
        }
        return this.mGUID;
    }

    public boolean hasAccount() {
        if (this.mSamsungAccount != null) {
            return true;
        }
        return false;
    }

    public boolean hasSamsungAccountId(Context context) {
        try {
            Bundle call = context.getContentResolver().call(Uri.parse("content://com.samsung.android.samsungaccount.accountmanagerprovider"), "getSamsungAccountId", "22n6hzkam0", (Bundle) null);
            if (call != null) {
                int i2 = call.getInt("result_code", 1);
                String string = call.getString("result_message", "");
                if (i2 == 0) {
                    return !TextUtils.isEmpty(string);
                }
                Log.e("SamsungAccountManager", "hasSamsungAccountId: error = " + Logger.getEncodedString(string));
            }
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("hasSamsungAccountId: "), "SamsungAccountManager");
            return false;
        }
    }

    public boolean isAccountTypeB2B(Context context) {
        try {
            Bundle call = context.getContentResolver().call(Uri.parse("content://com.samsung.android.samsungaccount.accountmanagerprovider"), "getSamsungAccountType", "2uzr3g12m3", (Bundle) null);
            if (call != null) {
                int i2 = call.getInt("result_code", 1);
                String string = call.getString("result_message", "");
                if (i2 == 0 && !TextUtils.isEmpty(string)) {
                    return "b2b".equalsIgnoreCase(string);
                }
                Log.e((CharSequence) "SamsungAccountManager", C0086a.i(i2, "isAccountTypeB2B: "), Logger.getEncodedString(string));
            }
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("isAccountTypeB2B: "), "SamsungAccountManager");
            return false;
        }
    }

    public boolean isSyncOn() {
        return this.mSyncStatus;
    }

    public void loadFamilyMemberType(Context context, boolean z) {
        if (this.mFamilyMemberType == null || z) {
            String familyMemberType = FamilyGroupHelper.getFamilyMemberType(context);
            familyMemberType.getClass();
            char c5 = 65535;
            switch (familyMemberType.hashCode()) {
                case -1307370476:
                    if (familyMemberType.equals("family_organizer")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case -1077769574:
                    if (familyMemberType.equals("member")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 94631196:
                    if (familyMemberType.equals("child")) {
                        c5 = 2;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    this.mFamilyMemberType = FamilyMemberType.ORGANIZER;
                    return;
                case 1:
                    this.mFamilyMemberType = FamilyMemberType.MEMBER;
                    return;
                case 2:
                    this.mFamilyMemberType = FamilyMemberType.CHILD;
                    return;
                default:
                    this.mFamilyMemberType = FamilyMemberType.NONE;
                    return;
            }
        }
    }

    public SamsungAccountManager reload() {
        Context appContext = getAppContext();
        if (appContext != null) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mSamsungAccount = getSamsungAccount(appContext);
            this.mSyncStatus = SamsungCloudCompat.isSyncOn(appContext);
            this.mGUID = null;
            loadFamilyMemberType(appContext, true);
            a.x(new StringBuilder("reload +"), currentTimeMillis, "SamsungAccountManager");
            return this;
        }
        Log.w("SamsungAccountManager", "reload failed. context is null");
        return this;
    }

    public void removeListener(OnAccountUpdatedListener onAccountUpdatedListener) {
        synchronized (this.mListener) {
            try {
                this.mListener.remove(onAccountUpdatedListener);
                if (this.mListener.isEmpty()) {
                    unregisterObserver();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean supportCreationFamilySharedAlbum(Context context) {
        if (!isSupportFamilyService(context)) {
            return false;
        }
        loadFamilyMemberType(context, false);
        FamilyMemberType familyMemberType = this.mFamilyMemberType;
        if (familyMemberType == FamilyMemberType.ORGANIZER || familyMemberType == FamilyMemberType.NONE) {
            return true;
        }
        return false;
    }

    public void notifyChange() {
        Log.d("SamsungAccountManager", "notifyChange {" + Logger.toString(this.mSamsungAccount) + ',' + this.mSyncStatus + '}');
        synchronized (this.mListener) {
            try {
                Iterator<OnAccountUpdatedListener> it = this.mListener.iterator();
                while (it.hasNext()) {
                    it.next().onSyncAccountsUpdated(this.mSamsungAccount, this.mSyncStatus);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
