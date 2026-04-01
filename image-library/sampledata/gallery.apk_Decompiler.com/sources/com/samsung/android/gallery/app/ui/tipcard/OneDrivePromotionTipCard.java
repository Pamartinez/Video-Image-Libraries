package com.samsung.android.gallery.app.ui.tipcard;

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
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDrivePromotionTipCard extends AbsTipCard {
    private void disableTipCard() {
        PreferenceCache.OneDrivePromoTipCardCount.incrementAndGet();
        PreferenceCache.OneDrivePromoTipCardTime.setLong(System.currentTimeMillis());
        PreferenceCache.OneDrivePromoTipCard.setBoolean(false);
    }

    private boolean isDisplayPeriodRefreshed() {
        if (PreferenceCache.OneDrivePromoTipCardTime.getLong() < TimeUtil.getDaysAgo(90)) {
            return true;
        }
        return false;
    }

    private boolean isRefreshable() {
        if (PreferenceCache.OneDrivePromoTipCardCount.getInt() < 3) {
            return true;
        }
        return false;
    }

    private boolean isTipCardEnabled() {
        if (PreferenceCache.OneDrivePromoTipCard.getBoolean()) {
            return true;
        }
        if (!isRefreshable() || !isDisplayPeriodRefreshed()) {
            return false;
        }
        return true;
    }

    public boolean checkTipCard(Context context) {
        if (Features.isEnabled(Features.IS_MUM_MODE)) {
            Log.d(this.TAG, "skip for guest mode");
            return false;
        } else if (Features.isEnabled(Features.IS_CHINESE_DEVICE) || Features.isEnabled(Features.IS_VERIZON_DEVICE)) {
            Log.d(this.TAG, "not support in this device");
            return false;
        } else if (Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES)) {
            Log.d(this.TAG, "skip for tablet");
            return false;
        } else if (!isTipCardEnabled() || !CloudStateCompat.isLegacyServiceStatusRequired() || !CloudStateCompat.loadTrialStatus()) {
            return false;
        } else {
            return true;
        }
    }

    public int getButtonColor(Context context) {
        return context.getColor(R.color.one_drive_tip_card_button_color);
    }

    public String getCancelBtnString(Context context) {
        return context.getString(R.string.not_now);
    }

    public String getDescription(Context context) {
        return context.getString(R.string.one_drive_promotion_tip_card_body);
    }

    public String getDoneBtnString(Context context) {
        return context.getString(R.string.one_drive_promotion_tip_card_get_bonus_space);
    }

    public String getTag() {
        return "OneDrivePromotionTipCard";
    }

    public String getTipCardCreationLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_ONE_DRIVE_PROMOTION.toString();
    }

    public String getTitle(Context context) {
        return context.getString(R.string.one_drive_promotion_tip_card_title);
    }

    public void onCancelBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        disableTipCard();
        dismissTipCard(tipCardViewHolder);
        postPresenceLog(AnalyticsEventId.EVENT_TIP_CARD_ONE_DRIVE_NOT_NOW);
    }

    public void onDoneBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        dismissTipCardWithDelayed(tipCardViewHolder);
        OneDriveManager.getInstance().startBonusSpaceActivity((Activity) context);
        postPresenceLog(AnalyticsEventId.EVENT_TIP_CARD_ONE_DRIVE_UPGRADE, AnalyticsDetail.EVENT_DETAIL_ONE_DRIVE_BONUS_SPACE.toString());
    }
}
