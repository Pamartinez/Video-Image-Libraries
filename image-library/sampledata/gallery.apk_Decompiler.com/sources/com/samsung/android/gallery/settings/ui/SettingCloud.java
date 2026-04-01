package com.samsung.android.gallery.settings.ui;

import android.accounts.Account;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.authentication.TwoFactorAuth;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.OneDriveManager;
import com.samsung.android.gallery.module.cloud.SamsungCloudManager;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.settings.ui.abstaction.ISettingView;
import com.samsung.android.gallery.settings.ui.delegate.NavigateAppCmd;
import com.samsung.android.gallery.settings.widget.SwitchExSummaryPreference;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Subscriber;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class SettingCloud {
    final SettingPreference CLOUD_CATEGORY = SettingPreference.CloudCategory;
    final boolean SUPPORT_CLOUD = (!Features.isEnabled(Features.IS_CHINESE_DEVICE));
    protected final String TAG = getClass().getSimpleName();
    private final SamsungAccountManager.OnAccountUpdatedListener mAccountListener = new J(this);
    private Dialog mCloudSyncProgDialog;
    Subscriber mSubscriber;
    final ISettingView mView;

    public SettingCloud(ISettingView iSettingView) {
        this.mView = iSettingView;
    }

    private String getCloudSummary(Context context, Account account) {
        int i2;
        if (account == null) {
            if (isGlobalAutoSyncOn()) {
                i2 = R$string.cloud_sync_description;
            } else {
                i2 = R$string.auto_sync_disabled;
            }
            return context.getString(i2);
        } else if (!CloudStateCompat.isEnabled()) {
            return context.getString(R$string.cloud_sync_description);
        } else {
            if (CloudStateCompat.isGracePeriod()) {
                return context.getString(R$string.new_scloud_tip_card_description_one_drive_ending_soon, new Object[]{TimeUtil.getLocalizedDate(CloudStateCompat.getOneDriveEndDate())});
            }
            return context.getString(R$string.sync_with_onedrive_description);
        }
    }

    /* JADX WARNING: type inference failed for: r3v3, types: [java.lang.Object, java.lang.Runnable] */
    private void handle2FaResponse(int i2) {
        if (i2 != 403) {
            show2FaProgress(false);
        }
        if (i2 == 200) {
            handleCloudSyncOn();
        } else if (i2 == 301) {
            Optional.ofNullable(this.mView.getContext()).ifPresent(new C0657l(2));
        } else if (i2 == 400) {
            lambda$update2FaStatus$23("not_supported");
        } else if (i2 != 403) {
            Log.e((CharSequence) this.TAG, "handle2FaResponse failed", Integer.valueOf(i2));
        } else {
            SimpleThreadPool.getInstance().execute(new Object());
        }
    }

    private void hideDialogSyncProgress() {
        Dialog dialog = this.mCloudSyncProgDialog;
        if (dialog != null) {
            try {
                if (dialog.isShowing()) {
                    this.mCloudSyncProgDialog.dismiss();
                }
            } catch (Exception unused) {
            }
            this.mCloudSyncProgDialog = null;
        }
    }

    private boolean initBackUpSdCard(PreferenceCategory preferenceCategory, Account account) {
        int i2;
        SettingPreference settingPreference = SettingPreference.SdCardBackupCloud;
        Preference findPreference = preferenceCategory.findPreference(settingPreference.key);
        boolean z = false;
        if (findPreference == null) {
            return false;
        }
        if (!supportCloudSync() || !settingPreference.support(findPreference.getContext())) {
            findPreference.setVisible(false);
            return false;
        }
        if (account != null && CloudStateCompat.isEnabled()) {
            z = true;
        }
        if (z) {
            i2 = R$string.back_up_sd_card_to_one_drive_description;
        } else {
            i2 = R$string.back_up_sd_card_to_one_drive_dimmed_description;
        }
        findPreference.setSummary(i2);
        findPreference.setEnabled(z);
        findPreference.setOnPreferenceClickListener(new H(this, 5));
        findPreference.setVisible(true);
        return true;
    }

    private void initCloudCategory(Account account, boolean z) {
        PreferenceCategory preferenceCategory = (PreferenceCategory) this.mView.findPreference(this.CLOUD_CATEGORY.key);
        if (preferenceCategory != null) {
            if (showNoticeIfUnlinked(preferenceCategory.getContext(), account)) {
                Log.d(this.TAG, "initCloudCategory invisible. unlinked");
                preferenceCategory.setVisible(false);
                return;
            }
            if (SettingPreference.CloudSync.support(preferenceCategory.getContext())) {
                initCloudSync(preferenceCategory, account, z);
                initBackUpSdCard(preferenceCategory, account);
            }
            preferenceCategory.setVisible(hasChildVisible(preferenceCategory));
        }
    }

    private boolean initCloudSync(PreferenceCategory preferenceCategory, Account account, boolean z) {
        boolean z3;
        SettingPreference settingPreference = SettingPreference.CloudSync;
        SwitchExSummaryPreference switchExSummaryPreference = (SwitchExSummaryPreference) preferenceCategory.findPreference(settingPreference.key);
        boolean z7 = false;
        if (switchExSummaryPreference == null) {
            Log.d(this.TAG, "initCloudSync skip. not available");
            return false;
        } else if (!supportCloudSync()) {
            if (CloudStateCompat.isLegacyServiceStatusRequired()) {
                Log.d(this.TAG, "initCloudSync skip. not supported {migrationSupported=" + CloudStateCompat.isMigrationSupported() + ", enabled=" + CloudStateCompat.isEnabled() + "}");
            } else {
                Log.d(this.TAG, "initCloudSync skip. invalid state", CloudStateCompat.getServiceName(CloudStateCompat.getService()));
            }
            switchExSummaryPreference.setVisible(false);
            return false;
        } else {
            switchExSummaryPreference.setVisible(true);
            boolean isSyncSwitchVisible = isSyncSwitchVisible(account, CloudStateCompat.isEnabled());
            Log.d(this.TAG, "initCloudSync", Boolean.valueOf(z), Boolean.valueOf(isSyncSwitchVisible), Logger.toString(account));
            switchExSummaryPreference.setSummary((CharSequence) getCloudSummary(switchExSummaryPreference.getContext(), account));
            if (account == null || !CloudStateCompat.isEnabled()) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (switchExSummaryPreference.setExtraSummaryVisible(z3)) {
                String cloudExtraSummary = getCloudExtraSummary(switchExSummaryPreference.getContext(), account, isSyncSwitchVisible, z);
                switchExSummaryPreference.setExtraSummary(cloudExtraSummary);
                switchExSummaryPreference.setExtraSummaryColor(getExtraSummaryColor(switchExSummaryPreference.getContext(), cloudExtraSummary.equals(account.name)));
            }
            switchExSummaryPreference.setOnPreferenceChangeListener(new H(this, 3));
            switchExSummaryPreference.setOnPreferenceClickListener(new H(this, 4));
            switchExSummaryPreference.showSwitch(isSyncSwitchVisible);
            if (isSyncSwitchVisible) {
                hideDialogSyncProgress();
                switchExSummaryPreference.setChecked(z);
            }
            if (isSyncSwitchVisible && z) {
                z7 = true;
            }
            settingPreference.setEnabled(z7);
            updateOneDriveBadge();
            return true;
        }
    }

    private void initVendorCloud() {
        PreferenceCategory preferenceCategory = (PreferenceCategory) this.mView.findPreference(this.CLOUD_CATEGORY.key);
        if (preferenceCategory != null) {
            computePreference(preferenceCategory, SettingPreference.AttCloud, new G(this, 0));
            computePreference(preferenceCategory, SettingPreference.BaiduCloud, new G(this, 1));
            computePreference(preferenceCategory, SettingPreference.TencentCloud, new G(this, 2));
            preferenceCategory.setVisible(hasChildVisible(preferenceCategory));
        }
    }

    private boolean isSyncSwitchVisible(Account account, boolean z) {
        if (account == null || CloudStateCompat.isPermissionRequired() || !isGlobalAutoSyncOn()) {
            return false;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$1(Object obj, Bundle bundle) {
        updateCloudCategories(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$2(Object obj, Bundle bundle) {
        updateCloudCategories(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$3(Object obj, Bundle bundle) {
        handle2FaResponse(((Integer) obj).intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initBackUpSdCard$12(Preference preference) {
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_BACK_UP_SD_CARD_TO_ONE_DRIVE.toString());
        return OneDriveManager.getInstance().startBackUpSdCard(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initCloudSync$10(Preference preference, Object obj) {
        return onCloudSyncSwitchChanged(((Boolean) obj).booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initCloudSync$11(Preference preference) {
        new NavigateAppCmd((IBaseView) this.mView).startOneDrive(preference.getContext());
        removeOneDriveBadge();
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initVendorCloud$4(Preference preference) {
        return new NavigateAppCmd((IBaseView) this.mView).startAttCloud(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initVendorCloud$5(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new H(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initVendorCloud$6(Preference preference) {
        return new NavigateAppCmd((IBaseView) this.mView).startBaiduCloud(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initVendorCloud$7(Preference preference, SettingPreference settingPreference) {
        preference.setSummary((CharSequence) preference.getContext().getString(R$string.service_is_provided_by, new Object[]{preference.getContext().getString(R$string.baidu)}));
        preference.setOnPreferenceClickListener(new H(this, 0));
        preference.setVisible(settingPreference.isEnabled());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initVendorCloud$8(Preference preference) {
        return new NavigateAppCmd((IBaseView) this.mView).startTencentCloud(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initVendorCloud$9(Preference preference, SettingPreference settingPreference) {
        preference.setSummary((CharSequence) preference.getContext().getString(R$string.service_is_provided_by, new Object[]{preference.getContext().getString(R$string.tencent)}));
        preference.setOnPreferenceClickListener(new H(this, 2));
        preference.setVisible(settingPreference.isEnabled());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAccount$15(Consumer consumer, SamsungAccountManager samsungAccountManager) {
        if (this.mView.getActivity() == null || this.mView.isDestroyed()) {
            Log.w(this.TAG, "loadAccount but already destroyed");
        } else {
            consumer.accept(samsungAccountManager);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAccount$16(boolean z, Consumer consumer) {
        if (z) {
            CloudStateCompat.load(true);
        }
        ThreadUtil.postOnUiThread(new C0647b(this, consumer, SamsungAccountManager.getInstance().reload(), 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAccount$17(Consumer consumer) {
        if (this.mView.getActivity() == null || this.mView.isDestroyed()) {
            Log.w(this.TAG, "loadAccount but already destroyed");
        } else {
            consumer.accept(SamsungAccountManager.getInstance());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onResume$0(SamsungAccountManager samsungAccountManager) {
        initCloudCategories(samsungAccountManager.getAccount(), samsungAccountManager.isSyncOn());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDialog2Fa$22(String str, DialogInterface dialogInterface, int i2) {
        TwoFactorAuth.getInstance().startVerification((Fragment) this.mView, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDialogSyncOff$19(DialogInterface dialogInterface, int i2) {
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_CLOUD_SYNC_OFF_DIALOG_CANCEL.toString());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDialogSyncOff$20(Context context, Consumer consumer, DialogInterface dialogInterface, int i2) {
        AnalyticsEventId analyticsEventId;
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_CLOUD_SYNC_OFF_DIALOG_TURN_OFF.toString());
        ISettingView iSettingView = this.mView;
        if (CloudStateCompat.isNewGalleryAvailable()) {
            analyticsEventId = AnalyticsEventId.EVENT_SETTING_SAMSUNG_CLOUD_SWITCH;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SETTING_CLOUD_SYNC_SWITCH;
        }
        iSettingView.postAnalyticsLog(analyticsEventId.toString(), false);
        SamsungCloudManager.getInstance().setSyncOff(context);
        hideDialogSyncProgress();
        consumer.accept(Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$update2FaStatus$24() {
        show2FaProgress(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$update2FaStatus$25() {
        String queryStatus = TwoFactorAuth.getInstance().queryStatus();
        if (!TextUtils.isEmpty(queryStatus)) {
            queryStatus.getClass();
            char c5 = 65535;
            switch (queryStatus.hashCode()) {
                case -267956670:
                    if (queryStatus.equals("not_supported")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case 3551:
                    if (queryStatus.equals("on")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 109935:
                    if (queryStatus.equals("off")) {
                        c5 = 2;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    ThreadUtil.postOnUiThread(new D(this, 3));
                    TwoFactorAuth.getInstance().checkVerification(false);
                    return;
                case 1:
                    ThreadUtil.postOnUiThread(new D(this, 1));
                    return;
                case 2:
                    ThreadUtil.postOnUiThread(new C0653h(3, this, queryStatus));
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$update2FaStatusOn$26() {
        if ("on".equals(TwoFactorAuth.getInstance().queryStatus())) {
            ThreadUtil.postOnUiThread(new D(this, 1));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCloudCategories$13(SamsungAccountManager samsungAccountManager) {
        initCloudCategories(samsungAccountManager.getAccount(), samsungAccountManager.isSyncOn());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateOneDriveBadge$18(SwitchExSummaryPreference switchExSummaryPreference) {
        if ((!CloudStateCompat.isGracePeriod() && (!switchExSummaryPreference.isSwitchShowing() || !switchExSummaryPreference.isChecked())) || !removeOneDriveBadge()) {
            switchExSummaryPreference.setDotVisibility(PreferenceCache.OneDriveBadge.getBoolean());
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateCloudSyncOnStatus(int i2) {
        if (i2 == 0) {
            showDialogSyncProgress(this.mView.getContext());
        } else if (i2 == 1) {
            hideDialogSyncProgress();
            updateCloudCategories(true);
        } else if (i2 == 2) {
            showCloudSyncOnError(this.mView.getContext());
        }
    }

    private boolean removeOneDriveBadge() {
        if (!PreferenceCache.OneDriveBadge.compareAndSet(true, false)) {
            return false;
        }
        updateOneDriveBadge();
        return true;
    }

    private void show2FaProgress(boolean z) {
        Activity activity = this.mView.getActivity();
        if (activity != null) {
            if (z) {
                activity.getWindow().addFlags(16);
            } else {
                activity.getWindow().clearFlags(16);
            }
            Optional.ofNullable((SwitchExSummaryPreference) this.mView.findPreference(SettingPreference.CloudSync.key)).ifPresent(new L(z));
            this.mView.setPreferenceScreenEnabled(!z);
        }
    }

    private void showCloudSyncOnError(Context context) {
        if (context != null) {
            Log.d(this.TAG, "showCloudSyncOnError: check your network");
            Toast.makeText(context, context.getString(R$string.cannot_connect_to_cloud, new Object[]{StringResources.getCloudBrand()}), 0).show();
            return;
        }
        Log.w(this.TAG, "showCloudSyncOnError skip. null context");
    }

    /* access modifiers changed from: private */
    /* renamed from: showDialog2Fa */
    public void lambda$update2FaStatus$23(String str) {
        Context context = this.mView.getContext();
        if (context == null) {
            Log.e(this.TAG, "showDialog2Fa failed. null context");
        } else {
            new AlertDialog.Builder(context).setTitle(R$string.two_step_verification_dialog_title).setMessage(R$string.two_step_verification_dialog_description).setPositiveButton(R$string.two_step_verification_dialog_button, (DialogInterface.OnClickListener) new C0660o(1, this, str)).create().show();
        }
    }

    private void showDialogSyncOff(Context context, Consumer<Boolean> consumer) {
        new AlertDialog.Builder(context).setTitle((CharSequence) context.getString(R$string.cloud_turn_off_dialog_title, new Object[]{StringResources.getCloudBrand()})).setMessage(R$string.cloud_turn_off_dialog_message).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) new C0654i(2, this)).setPositiveButton(R$string.turn_off, (DialogInterface.OnClickListener) new C0652g(this, context, (Consumer) consumer)).create().show();
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [android.content.DialogInterface$OnCancelListener, java.lang.Object] */
    private void showDialogSyncProgress(Context context) {
        if (context != null) {
            if (this.mCloudSyncProgDialog == null) {
                this.mCloudSyncProgDialog = new ProgressCircleBuilder(context).setCancelListener(new Object()).create();
            }
            this.mCloudSyncProgDialog.show();
            return;
        }
        Log.w(this.TAG, "showDialogSyncProgress skip. null context");
    }

    private boolean showNoticeIfUnlinked(Context context, Account account) {
        boolean z = true;
        if (account != null) {
            PreferenceCache preferenceCache = PreferenceCache.OneDriveUnlinkedPopupShown;
            if (!preferenceCache.getBoolean() && CloudStateCompat.isLegacyServiceStatusRequired()) {
                if (CloudStateCompat.isMigrationSupported() || !CloudStateCompat.isUnlinked()) {
                    return false;
                }
                new AlertDialog.Builder(context).setTitle(R$string.sync_with_one_drive_removed).setMessage((CharSequence) String.format(context.getString(R$string.sync_with_one_drive_removed_description), new Object[]{account.name})).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) null).create().show();
                preferenceCache.setBoolean(true);
                return true;
            }
        }
        String str = this.TAG;
        if (account == null) {
            z = false;
        }
        Log.d(str, "showNoticeIfUnlinked skip", Boolean.valueOf(z));
        return false;
    }

    private boolean supportCloudSync() {
        if (CloudStateCompat.isLegacyServiceStatusRequired()) {
            if (CloudStateCompat.isMigrationSupported() || CloudStateCompat.isEnabled()) {
                return true;
            }
            return false;
        } else if (CloudStateCompat.isMigrationAvailable() || CloudStateCompat.isOneDriveAvailable() || CloudStateCompat.isOneDriveLinkRequired()) {
            return true;
        } else {
            return false;
        }
    }

    private void update2FaStatus() {
        SimpleThreadPool.getInstance().execute(new D(this, 2));
    }

    private void update2FaStatusOn() {
        SimpleThreadPool.getInstance().execute(new D(this, 0));
    }

    /* access modifiers changed from: private */
    public void updateCloudCategories(boolean z) {
        loadAccount(z, false, new K(this, 3));
    }

    private void updateOneDriveBadge() {
        Optional.ofNullable((SwitchExSummaryPreference) this.mView.findPreference(SettingPreference.CloudSync.key)).ifPresent(new K(this, 0));
    }

    public final void computePreference(PreferenceCategory preferenceCategory, SettingPreference settingPreference, BiConsumer<Preference, SettingPreference> biConsumer) {
        Preference preference;
        if (preferenceCategory == null) {
            preference = null;
        } else {
            preference = preferenceCategory.findPreference(settingPreference.key);
        }
        if (preference == null) {
            return;
        }
        if (settingPreference.support(preferenceCategory.getContext())) {
            biConsumer.accept(preference, settingPreference);
            preference.setVisible(true);
            return;
        }
        preferenceCategory.removePreference(preference);
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://event/globalAutoSyncChanged", new M(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://event/cloud/service/changed", new M(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/twoStepVerification", new M(this, 2)).setWorkingOnUI());
    }

    public final String getCloudExtraSummary(Context context, Account account, boolean z, boolean z3) {
        int i2;
        if (CloudStateCompat.isPermissionRequired()) {
            if (SdkConfig.atLeast(SdkConfig.GED.T)) {
                i2 = R$string.permissions_photos_and_videos;
            } else {
                i2 = R$string.permissions_files_and_media;
            }
            return context.getString(R$string.cloud_permission_description, new Object[]{context.getString(i2)});
        } else if (!z || !z3) {
            return context.getString(R$string.auto_sync_disabled);
        } else {
            return account.name;
        }
    }

    public final int getExtraSummaryColor(Context context, boolean z) {
        if (z) {
            return context.getResources().getColor(R$color.settings_value_text_color, (Resources.Theme) null);
        }
        return Utils.getTextColorFromTextColorAttr(context, 16842808);
    }

    public void handleCloudSyncOn() {
        AnalyticsEventId analyticsEventId;
        Context context = this.mView.getContext();
        if (context != null) {
            SamsungCloudManager.getInstance().setSyncOn(context, new H(this, 6));
            if (!CloudStateCompat.isNewGalleryAvailable()) {
                removeOneDriveBadge();
                PreferenceCache.OneDriveTipCard.compareAndSet(true, false);
            }
        }
        ISettingView iSettingView = this.mView;
        if (CloudStateCompat.isNewGalleryAvailable()) {
            analyticsEventId = AnalyticsEventId.EVENT_SETTING_SAMSUNG_CLOUD_SWITCH;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SETTING_CLOUD_SYNC_SWITCH;
        }
        iSettingView.postAnalyticsLog(analyticsEventId.toString(), true);
    }

    public final boolean hasChildVisible(Preference preference) {
        if (preference instanceof PreferenceGroup) {
            PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
            int preferenceCount = preferenceGroup.getPreferenceCount();
            for (int i2 = 0; i2 < preferenceCount; i2++) {
                if (preferenceGroup.getPreference(i2).isVisible()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void initCloudCategories(Account account, boolean z) {
        if (this.SUPPORT_CLOUD) {
            initCloudCategory(account, z);
        } else {
            Optional.ofNullable(this.mView.findPreference(SettingPreference.CloudSync.key)).ifPresent(new C0657l(3));
        }
    }

    public void initPreference() {
        SamsungAccountManager instance = SamsungAccountManager.getInstance();
        initVendorCloud();
        initCloudCategories(instance.getAccount(), instance.isSyncOn());
    }

    public final boolean isGlobalAutoSyncOn() {
        try {
            if (ContentResolver.getMasterSyncAutomatically()) {
                return true;
            }
            Log.d(this.TAG, "ignore global auto-sync");
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public final void loadAccount(boolean z, boolean z3, Consumer<SamsungAccountManager> consumer) {
        if (z) {
            SimpleThreadPool.getInstance().execute(new F(this, z3, consumer));
        } else {
            ThreadUtil.runOnUiThread(new C0653h(2, this, consumer));
        }
    }

    public void onActivityResult(int i2, int i7, Intent intent) {
        if (i2 == 2319) {
            update2FaStatusOn();
        }
    }

    public final boolean onCloudSyncSwitchChanged(boolean z) {
        Context context = this.mView.getContext();
        if (context != null) {
            ISettingView iSettingView = this.mView;
            if (iSettingView.setInputBlock(this.TAG + "_onCloudSyncSwitchChanged", 500)) {
                if (!NetworkUtils.isNetworkConnected(context)) {
                    Toast.makeText(context, context.getString(R$string.cannot_connect_to_cloud, new Object[]{StringResources.getCloudBrand()}), 0).show();
                    return false;
                } else if (z) {
                    update2FaStatus();
                    return false;
                } else {
                    showDialogSyncOff(context, new K(this, 1));
                }
            }
        }
        return false;
    }

    public void onCreate() {
        AnonymousClass1 r0 = new Subscriber(this.mView.getBlackboard()) {
            public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
                SettingCloud settingCloud = SettingCloud.this;
                if (settingCloud.SUPPORT_CLOUD) {
                    settingCloud.createGlobalSubscriberList(arrayList);
                }
            }

            public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
            }
        };
        this.mSubscriber = r0;
        r0.onCreate();
        SamsungAccountManager.getInstance().addListener(this.mAccountListener);
    }

    public void onDestroy() {
        this.mSubscriber.onDestroy();
        this.mSubscriber = null;
        SamsungAccountManager.getInstance().removeListener(this.mAccountListener);
        SamsungCloudManager.getInstance().removeListener();
    }

    public void onResume() {
        if (this.SUPPORT_CLOUD && SdkConfig.atLeast(SdkConfig.GED.P)) {
            loadAccount(true, true, new K(this, 2));
        }
    }

    public final void setTopPaddingVisible(PreferenceCategory preferenceCategory, boolean z) {
        Preference findPreference = preferenceCategory.findPreference("top_padding");
        if (findPreference != null) {
            findPreference.setVisible(z);
        } else if (z) {
            PreferenceCategory preferenceCategory2 = new PreferenceCategory(preferenceCategory.getContext());
            preferenceCategory2.setKey("top_padding");
            preferenceCategory2.setOrder(-1);
            preferenceCategory.addPreference(preferenceCategory2);
        }
    }

    public void updateBackUpSdCard(Account account) {
        PreferenceCategory preferenceCategory = (PreferenceCategory) this.mView.findPreference(this.CLOUD_CATEGORY.key);
        if (preferenceCategory != null) {
            initBackUpSdCard(preferenceCategory, account);
        }
    }
}
