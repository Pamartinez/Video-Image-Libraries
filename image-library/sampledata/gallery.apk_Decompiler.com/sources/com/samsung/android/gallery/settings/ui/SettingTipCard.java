package com.samsung.android.gallery.settings.ui;

import A.a;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.OneDriveManager;
import com.samsung.android.gallery.module.settings.MarketUpgradeManager;
import com.samsung.android.gallery.module.store.MarketHelper;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.abstaction.ISettingView;
import com.samsung.android.gallery.settings.widget.TipCardPreference;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SettingTipCard {
    final List<TipCard> mList = List.of(new AppUpgrade(), new OneDrive());
    final ISettingView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AppUpgrade implements TipCard {
        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$build$0(ISettingView iSettingView, View view) {
            iSettingView.postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_UPDATE_APP_CARD_UPDATE.toString());
            MarketHelper.startGalaxyApps(view.getContext());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$build$1(ISettingView iSettingView, PreferenceCategory preferenceCategory, View view) {
            iSettingView.postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_UPDATE_APP_CARD_NOT_NOW.toString());
            MarketHelper.setCheckedVersion(MarketHelper.getApkVersion());
            preferenceCategory.setVisible(false);
        }

        public Preference build(ISettingView iSettingView, PreferenceCategory preferenceCategory) {
            Context context = preferenceCategory.getContext();
            TipCardPreference negativeButton = new TipCardPreference(context).setButtonTextColor(R$color.update_app_card_view_text_color).setPositiveButton(R$string.update, new O(0, iSettingView)).setNegativeButton(R$string.not_now, new P(0, iSettingView, preferenceCategory));
            negativeButton.setTitle(R$string.update_app);
            negativeButton.setSummary((CharSequence) context.getString(R$string.new_version_of_is_available, new Object[]{context.getString(R$string.app_name)}));
            return negativeButton;
        }

        public boolean support() {
            if (!MarketUpgradeManager.getInstance().isUpgradeAvailable() || MarketUpgradeManager.getInstance().isUpgradeIgnored()) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OneDrive implements TipCard {
        volatile Float quotaUsagePercent;

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$build$0(ISettingView iSettingView, PreferenceCategory preferenceCategory, Activity activity) {
            iSettingView.postAnalyticsLog(AnalyticsEventId.EVENT_TIP_CARD_ONE_DRIVE_UPGRADE.toString());
            OneDriveManager.getInstance().startOneDriveQuotaUpgradeActivity(activity, -1);
            preferenceCategory.setVisible(false);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$build$2(ISettingView iSettingView, PreferenceCategory preferenceCategory, View view) {
            PreferenceCache.OneDriveQuotaFullTipCardTimeInSetting.setLong(System.currentTimeMillis());
            iSettingView.postAnalyticsLog(AnalyticsEventId.EVENT_TIP_CARD_ONE_DRIVE_NOT_NOW.toString());
            preferenceCategory.setVisible(false);
        }

        public Preference build(ISettingView iSettingView, PreferenceCategory preferenceCategory) {
            TipCardPreference negativeButton = new TipCardPreference(preferenceCategory.getContext()).setButtonTextColor(R$color.one_drive_tip_card_button_color).setPositiveButton(R$string.upgrade, new P(1, iSettingView, preferenceCategory)).setNegativeButton(R$string.not_now, new P(2, iSettingView, preferenceCategory));
            negativeButton.setTitle(R$string.one_drive_is_full);
            negativeButton.setSummary(R$string.one_drive_is_full_description);
            return negativeButton;
        }

        public float loadCloudQuota() {
            if (this.quotaUsagePercent == null) {
                this.quotaUsagePercent = Float.valueOf(CloudStateCompat.loadQuota());
            }
            return this.quotaUsagePercent.floatValue();
        }

        public boolean support() {
            if (Features.isEnabled(Features.IS_CHINESE_DEVICE) || !CloudStateCompat.isMigrated() || PreferenceCache.OneDriveQuotaFullTipCardCount.getInt() < 3 || !OneDriveManager.getInstance().isQuotaFullDisplayPeriodRefreshedForSetting() || loadCloudQuota() < 0.99f) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TipCard {
        Preference build(ISettingView iSettingView, PreferenceCategory preferenceCategory);

        boolean support();
    }

    public SettingTipCard(ISettingView iSettingView) {
        this.mView = iSettingView;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreference$0(TimeTickLog timeTickLog, TipCard tipCard, PreferenceCategory preferenceCategory) {
        String str;
        timeTickLog.tick();
        StringBuilder sb2 = new StringBuilder("init");
        if (tipCard == null) {
            str = "n/a";
        } else {
            str = tipCard.getClass().getSimpleName();
        }
        a.A(new Object[]{str, timeTickLog}, sb2, "SettingTipCard");
        if (this.mView.isDestroyed()) {
            Log.d("SettingTipCard", "init skip. view already destroyed");
        } else if (tipCard == null) {
            preferenceCategory.setVisible(false);
        } else {
            Preference findPreference = preferenceCategory.findPreference("setting_tip_card");
            if (findPreference != null) {
                preferenceCategory.removePreference(findPreference);
            }
            Preference build = tipCard.build(this.mView, preferenceCategory);
            build.setKey("setting_tip_card");
            preferenceCategory.addPreference(build);
            preferenceCategory.setVisible(true);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.util.function.Predicate, java.lang.Object] */
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreference$1(TimeTickLog timeTickLog, PreferenceCategory preferenceCategory) {
        timeTickLog.tick();
        ThreadUtil.runOnUiThread(new C0655j(this, timeTickLog, this.mList.stream().filter(new Object()).findFirst().orElse((Object) null), preferenceCategory, 1));
    }

    public void initPreference() {
        PreferenceCategory preferenceCategory = (PreferenceCategory) this.mView.findPreference("setting_tip_card_category");
        if (preferenceCategory != null) {
            initPreference(preferenceCategory);
        }
    }

    public void initPreference(PreferenceCategory preferenceCategory) {
        SimpleThreadPool.getInstance().execute(new C0647b(this, new TimeTickLog(), preferenceCategory, 3));
    }
}
