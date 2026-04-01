package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.controller.internals.StartSettingsCmd;
import com.samsung.android.gallery.module.settings.CmhProviderPermission;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.helper.PopoverUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhProviderPermissionTipCard extends AbsTipCard {
    private void disableTipCard() {
        PreferenceCache.CmhPermissionTipCardCount.incrementAndGet();
        PreferenceCache.CmhPermissionTipCardTime.setLong(System.currentTimeMillis());
        PreferenceCache.CmhPermissionTipCard.setBoolean(false);
    }

    private boolean isDisplayPeriodRefreshed() {
        if (PreferenceCache.CmhPermissionTipCardTime.getLong() < TimeUtil.getDaysAgo(7)) {
            return true;
        }
        return false;
    }

    private boolean isRefreshable() {
        if (PreferenceCache.CmhPermissionTipCardCount.getInt() < 3) {
            return true;
        }
        return false;
    }

    private boolean isTipCardEnabled() {
        if (!PreferenceCache.CmhPermissionTipCardAllowed.getBoolean()) {
            return false;
        }
        if (PreferenceCache.CmhPermissionTipCard.getBoolean()) {
            return true;
        }
        if (!isRefreshable() || !isDisplayPeriodRefreshed()) {
            return false;
        }
        return true;
    }

    public boolean checkTipCard(Context context) {
        if (!isTipCardEnabled()) {
            return false;
        }
        boolean z = CmhProviderPermission.get();
        if (z) {
            PreferenceCache.CmhPermissionTipCardAllowed.setBoolean(false);
        }
        return !z;
    }

    public String getCancelBtnString(Context context) {
        return context.getString(R.string.not_now);
    }

    public String getDescription(Context context) {
        return SeApiCompat.naturalizeText(context.getString(R.string.cmh_provider_permission_tip_card_description, new Object[]{context.getString(R.string.cmh_provider_permission_title)}));
    }

    public String getDoneBtnString(Context context) {
        return context.getString(R.string.tip_card_settings);
    }

    public String getTag() {
        return getClass().getSimpleName();
    }

    public String getTipCardCreationLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_CMH_PROVIDER_PERMISSION.toString();
    }

    public void onCancelBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        disableTipCard();
        dismissTipCard(tipCardViewHolder);
        postPresenceLog(AnalyticsEventId.EVENT_TIP_CARD_CMH_PROVIDER_PERMISSION_NOT_NOW);
    }

    public void onDoneBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        disableTipCard();
        dismissTipCardWithDelayed(tipCardViewHolder);
        new StartSettingsCmd().execute(this.mView.getEventContext(), SettingPreference.CmhProvider.key, PopoverUtils.Anchor.TOP_END);
        postPresenceLog(AnalyticsEventId.EVENT_TIP_CARD_CMH_PROVIDER_PERMISSION_SETTINGS);
    }
}
