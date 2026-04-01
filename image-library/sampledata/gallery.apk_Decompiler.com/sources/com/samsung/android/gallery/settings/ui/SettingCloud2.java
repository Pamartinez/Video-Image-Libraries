package com.samsung.android.gallery.settings.ui;

import D3.i;
import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.cloud.CloudLog;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.settings.ui.abstaction.ISettingView;
import com.samsung.android.gallery.settings.ui.delegate.NavigateAppCmd;
import com.samsung.android.gallery.settings.widget.SwitchExSummaryPreference;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SettingCloud2 extends SettingCloud {
    final SettingPreference SAMSUNG_CLOUD_CATEGORY = SettingPreference.SamsungCloudCategory;
    private boolean mIsFirstLogging = true;

    public SettingCloud2(ISettingView iSettingView) {
        super(iSettingView);
    }

    private String getCloudSummary(Context context) {
        if (CloudStateCompat.isNewGalleryAvailable()) {
            return context.getString(R$string.sync_with_samsung_cloud_summary);
        }
        if (!isGlobalAutoSyncOn()) {
            return context.getString(R$string.auto_sync_disabled);
        }
        if (CloudStateCompat.isAccountRequired()) {
            return context.getString(R$string.sync_with_cloud_account_summary);
        }
        if (CloudStateCompat.isMigrationAvailable()) {
            return context.getString(R$string.sync_with_samsung_cloud_migration_available_summary);
        }
        return context.getString(R$string.cloud_sync_description);
    }

    private void initSamsungCloudCategory(Account account, boolean z) {
        boolean z3;
        PreferenceCategory preferenceCategory = (PreferenceCategory) this.mView.findPreference(this.SAMSUNG_CLOUD_CATEGORY.key);
        if (preferenceCategory != null) {
            if (!initSamsungCloudSync(preferenceCategory, account, z) || !((Boolean) Optional.ofNullable(this.mView.findPreference(this.CLOUD_CATEGORY.key)).map(new i(25)).orElse(Boolean.FALSE)).booleanValue()) {
                z3 = false;
            } else {
                z3 = true;
            }
            setTopPaddingVisible(preferenceCategory, z3);
            preferenceCategory.setVisible(hasChildVisible(preferenceCategory));
        }
    }

    private boolean initSamsungCloudSync(PreferenceCategory preferenceCategory, Account account, boolean z) {
        boolean z3;
        int i2;
        boolean z7;
        SettingPreference settingPreference = SettingPreference.SamsungCloudSync;
        SwitchExSummaryPreference switchExSummaryPreference = (SwitchExSummaryPreference) preferenceCategory.findPreference(settingPreference.key);
        boolean z9 = false;
        if (switchExSummaryPreference == null) {
            Log.d(this.TAG, "initSamsungCloudSync skip. not available");
            return false;
        }
        Context context = preferenceCategory.getContext();
        if (!settingPreference.support(context)) {
            Log.d(this.TAG, "initSamsungCloudSync skip. not supported");
            switchExSummaryPreference.setVisible(false);
            return false;
        } else if (CloudStateCompat.isServiceNotAvailable() || CloudStateCompat.isOneDriveAvailable() || CloudStateCompat.isOneDriveLinkRequired() || CloudStateCompat.isLegacyServiceStatusRequired()) {
            Log.d(this.TAG, "initSamsungCloudSync skip. invalid state", CloudStateCompat.getServiceName(CloudStateCompat.getService()));
            switchExSummaryPreference.setVisible(false);
            return false;
        } else {
            if (account != null && this.mIsFirstLogging) {
                postCloudLog(context, 0, 0);
                this.mIsFirstLogging = false;
            }
            switchExSummaryPreference.setVisible(true);
            if (!CloudStateCompat.isNewGalleryAvailable() || CloudStateCompat.isPermissionRequired() || !isGlobalAutoSyncOn()) {
                z3 = false;
            } else {
                z3 = true;
            }
            Log.d(this.TAG, "initSamsungCloudSync", Boolean.valueOf(z), Boolean.valueOf(z3), Logger.toString(account));
            if (CloudStateCompat.isAccountRequired()) {
                i2 = R$string.sync_with_cloud_account;
            } else {
                i2 = R$string.sync_with_samsung_cloud;
            }
            switchExSummaryPreference.setTitle((CharSequence) context.getString(i2));
            switchExSummaryPreference.setSummary((CharSequence) getCloudSummary(context));
            if (account == null || !CloudStateCompat.isNewGalleryAvailable()) {
                z7 = false;
            } else {
                z7 = true;
            }
            if (switchExSummaryPreference.setExtraSummaryVisible(z7)) {
                String cloudExtraSummary = getCloudExtraSummary(context, account, z3, z);
                switchExSummaryPreference.setExtraSummary(cloudExtraSummary);
                switchExSummaryPreference.setExtraSummaryColor(getExtraSummaryColor(context, cloudExtraSummary.equals(account.name)));
            }
            switchExSummaryPreference.setOnPreferenceChangeListener(new y(3, this));
            switchExSummaryPreference.setOnPreferenceClickListener(new C0659n(2, this, context));
            switchExSummaryPreference.showSwitch(z3);
            if (z3) {
                switchExSummaryPreference.setChecked(z);
            }
            if (z3 && z) {
                z9 = true;
            }
            settingPreference.setEnabled(z9);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$0(SamsungAccountManager samsungAccountManager) {
        initSamsungCloudCategory(samsungAccountManager.getAccount(), samsungAccountManager.isSyncOn());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$1(Object obj, Bundle bundle) {
        loadAccount(true, false, new r(2, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initSamsungCloudSync$3(Preference preference, Object obj) {
        return onCloudSyncSwitchChanged(((Boolean) obj).booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initSamsungCloudSync$4(Context context, Preference preference) {
        new NavigateAppCmd((IBaseView) this.mView).startSamsungCloud(preference.getContext());
        postCloudLog(context, 1, 1);
        return true;
    }

    private void postCloudLog(Context context, int i2, int i7) {
        String str;
        if (CloudStateCompat.isMigrationAvailable()) {
            str = "gallery_od_user_sync";
        } else {
            str = "gallery_new_user_sync";
        }
        if (i2 == 0) {
            CloudLog.sendLog(context, str, i2);
        } else if (i2 == 1) {
            CloudLog.sendLog(context, str, i2, i7);
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        if (Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD)) {
            arrayList.add(new SubscriberInfo("cloud/sync/on/off/changed", new C0646a(1, this)).setWorkingOnUI());
        }
    }

    public void initCloudCategories(Account account, boolean z) {
        super.initCloudCategories(account, z);
        if (this.SUPPORT_CLOUD) {
            initSamsungCloudCategory(account, z);
        } else {
            Optional.ofNullable(this.mView.findPreference(this.SAMSUNG_CLOUD_CATEGORY.key)).ifPresent(new C0657l(4));
        }
    }
}
