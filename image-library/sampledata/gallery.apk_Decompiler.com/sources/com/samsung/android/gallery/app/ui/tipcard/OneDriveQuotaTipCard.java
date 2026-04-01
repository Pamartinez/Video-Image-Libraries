package com.samsung.android.gallery.app.ui.tipcard;

import A.a;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.OneDriveManager;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDriveQuotaTipCard extends AbsTipCard {
    private QuotaState mQuotaState;
    private float mQuotaUsagePercent = -1.0f;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum QuotaState {
        ;

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.app.ui.tipcard.OneDriveQuotaTipCard$QuotaState$1  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass1 extends QuotaState {
            public /* synthetic */ AnonymousClass1() {
                this("FULL", 0);
            }

            private void updateQuotaFullTipCardDisplayCount() {
                a.k(PreferenceCache.OneDriveQuotaFullTipCardCount.incrementAndGet(), "updateQuotaFullTipCardDisplayCount : ", "OneDriveQuotaTipCard");
            }

            private void updateQuotaFullTipCardLastDisplayTimeMs() {
                PreferenceCache.OneDriveQuotaFullTipCardTime.setLong(System.currentTimeMillis());
            }

            public String getDescription(Context context) {
                return context.getResources().getString(R.string.one_drive_is_full_description);
            }

            public String getTipCardCreationLogDetail() {
                return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_ONE_DRIVE_QUOTA_FULL.toString();
            }

            public String getTipCardDoneClickLogDetail() {
                return AnalyticsDetail.EVENT_DETAIL_ONE_DRIVE_QUOTA_TIP_CARD_FULL.toString();
            }

            public String getTitle(Context context) {
                return context.getResources().getString(R.string.one_drive_is_full);
            }

            public void updatePreference() {
                updateQuotaFullTipCardDisplayCount();
                updateQuotaFullTipCardLastDisplayTimeMs();
            }

            private AnonymousClass1(String str, int i2) {
                super(str, i2, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.app.ui.tipcard.OneDriveQuotaTipCard$QuotaState$2  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass2 extends QuotaState {
            public /* synthetic */ AnonymousClass2() {
                this("MOST_70", 1);
            }

            public String getTipCardCreationLogDetail() {
                return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_ONE_DRIVE_QUOTA_70P.toString();
            }

            public String getTipCardDoneClickLogDetail() {
                return AnalyticsDetail.EVENT_DETAIL_ONE_DRIVE_QUOTA_TIP_CARD_70P.toString();
            }

            public void updatePreference() {
                PreferenceCache.OneDriveQuotaTipCard70.setBoolean(true);
            }

            private AnonymousClass2(String str, int i2) {
                super(str, i2, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.app.ui.tipcard.OneDriveQuotaTipCard$QuotaState$3  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass3 extends QuotaState {
            public /* synthetic */ AnonymousClass3() {
                this("MOST_80", 2);
            }

            public String getTipCardCreationLogDetail() {
                return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_ONE_DRIVE_QUOTA_80P.toString();
            }

            public String getTipCardDoneClickLogDetail() {
                return AnalyticsDetail.EVENT_DETAIL_ONE_DRIVE_QUOTA_TIP_CARD_80P.toString();
            }

            public void updatePreference() {
                PreferenceCache.OneDriveQuotaTipCard80.setBoolean(true);
            }

            private AnonymousClass3(String str, int i2) {
                super(str, i2, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.app.ui.tipcard.OneDriveQuotaTipCard$QuotaState$4  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass4 extends QuotaState {
            public /* synthetic */ AnonymousClass4() {
                this("MOST_90", 3);
            }

            public String getTipCardCreationLogDetail() {
                return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_ONE_DRIVE_QUOTA_90P.toString();
            }

            public String getTipCardDoneClickLogDetail() {
                return AnalyticsDetail.EVENT_DETAIL_ONE_DRIVE_QUOTA_TIP_CARD_90P.toString();
            }

            public void updatePreference() {
                PreferenceCache.OneDriveQuotaTipCard90.setBoolean(true);
            }

            private AnonymousClass4(String str, int i2) {
                super(str, i2, 0);
            }
        }

        public String getDescription(Context context) {
            return context.getResources().getString(R.string.one_drive_tip_quota_80p_body);
        }

        public abstract String getTipCardCreationLogDetail();

        public abstract String getTipCardDoneClickLogDetail();

        public String getTitle(Context context) {
            return context.getResources().getString(R.string.one_drive_tip_quota_80p_title);
        }

        public abstract void updatePreference();
    }

    private QuotaState getQuotaType() {
        if (isQuotaFull()) {
            return QuotaState.FULL;
        }
        if (isQuotaMost90()) {
            return QuotaState.MOST_90;
        }
        if (isQuotaMost80()) {
            return QuotaState.MOST_80;
        }
        if (isQuotaMost70()) {
            return QuotaState.MOST_70;
        }
        return null;
    }

    private float getQuotaUsagePercent() {
        if (this.mQuotaUsagePercent == -1.0f) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mQuotaUsagePercent = CloudStateCompat.loadQuota();
            String str = this.TAG;
            Log.d(str, "quotaUsagePercent=" + this.mQuotaUsagePercent + ",elapsed=" + (System.currentTimeMillis() - currentTimeMillis));
        }
        return this.mQuotaUsagePercent;
    }

    private boolean isQuotaFull() {
        if (PreferenceCache.OneDriveQuotaFullTipCardCount.getInt() >= 3 || !OneDriveManager.getInstance().isQuotaFullDisplayPeriodRefreshed() || getQuotaUsagePercent() < 0.99f) {
            return false;
        }
        return true;
    }

    private boolean isQuotaMost70() {
        if (!PreferenceCache.OneDriveQuotaTipCard70.getBoolean() && getQuotaUsagePercent() >= 0.7f && getQuotaUsagePercent() < 0.8f) {
            return true;
        }
        return false;
    }

    private boolean isQuotaMost80() {
        if (!PreferenceCache.OneDriveQuotaTipCard80.getBoolean() && getQuotaUsagePercent() >= 0.8f && getQuotaUsagePercent() < 0.9f) {
            return true;
        }
        return false;
    }

    private boolean isQuotaMost90() {
        if (!PreferenceCache.OneDriveQuotaTipCard90.getBoolean() && getQuotaUsagePercent() >= 0.9f && getQuotaUsagePercent() < 0.99f) {
            return true;
        }
        return false;
    }

    private static void startOneDriveUpgradeActivity(Activity activity) {
        OneDriveManager.getInstance().startOneDriveQuotaUpgradeActivity(activity, 1282);
    }

    public boolean checkTipCard(Context context) {
        if (Features.isEnabled(Features.IS_MUM_MODE)) {
            Log.d(this.TAG, "Guest mode");
            return false;
        } else if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
            Log.d(this.TAG, "not support in this device");
            return false;
        } else {
            if (CloudStateCompat.isLegacyServiceStatusRequired() && CloudStateCompat.isMigrated()) {
                this.mQuotaState = getQuotaType();
                String str = this.TAG;
                Log.d(str, "quotaState : " + this.mQuotaState);
                if (this.mQuotaState != null) {
                    return true;
                }
            }
            return false;
        }
    }

    public int getButtonColor(Context context) {
        return context.getColor(R.color.one_drive_tip_card_button_color);
    }

    public String getCancelBtnString(Context context) {
        return context.getString(R.string.not_now);
    }

    public String getDescription(Context context) {
        return this.mQuotaState.getDescription(context);
    }

    public String getDoneBtnString(Context context) {
        return context.getString(R.string.upgrade);
    }

    public String getTag() {
        return this.mQuotaState.name();
    }

    public String getTipCardCreationLogDetail() {
        return this.mQuotaState.getTipCardCreationLogDetail();
    }

    public String getTitle(Context context) {
        return this.mQuotaState.getTitle(context);
    }

    public void onCancelBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        this.mQuotaState.updatePreference();
        dismissTipCard(tipCardViewHolder);
        postPresenceLog(AnalyticsEventId.EVENT_TIP_CARD_ONE_DRIVE_NOT_NOW);
    }

    public void onDoneBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        this.mQuotaState.updatePreference();
        dismissTipCardWithDelayed(tipCardViewHolder);
        startOneDriveUpgradeActivity((Activity) context);
        postPresenceLog(AnalyticsEventId.EVENT_TIP_CARD_ONE_DRIVE_UPGRADE, this.mQuotaState.getTipCardDoneClickLogDetail());
    }
}
