package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import com.samsung.android.gallery.app.controller.internals.StartSettingsCmd;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.helper.PopoverUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDriveTurnOnSyncTipCard extends AbsOneDriveTipCard {
    private boolean isTipCardDisabled() {
        return !PreferenceCache.OneDriveTipCard.getBoolean();
    }

    public boolean checkTipCard(Context context) {
        if (super.checkTipCard(context) && !isTipCardDisabled()) {
            if (!CloudStateCompat.isLoadingCompleted()) {
                Log.d(this.TAG, "OneDrive load is not completed yet");
                return false;
            } else if (CloudStateCompat.isMigrationSupported() && CloudStateCompat.isEnabled()) {
                if (CloudStateCompat.isSyncOnInPref()) {
                    disableTipCard();
                    Log.d(this.TAG, "Disable tipCard, OneDrive sync on");
                    return false;
                }
                Log.d(this.TAG, "Show tipCard, OneDrive sync off");
                return true;
            }
        }
        return false;
    }

    public void disableTipCard() {
        PreferenceCache.OneDriveTipCard.setBoolean(false);
    }

    public String getCancelBtnString(Context context) {
        return context.getString(R.string.not_now);
    }

    public String getDescription(Context context) {
        return context.getString(R.string.one_drive_turn_on_sync_tip_card_body);
    }

    public String getDoneBtnString(Context context) {
        return context.getString(R.string.tip_card_settings);
    }

    public String getTag() {
        return "OneDriveTurnOnSyncTipCard";
    }

    public String getTipCardCreationLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_ONE_DRIVE_TURN_ON_SYNC.toString();
    }

    public String getTipCardDoneClickLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_ONE_DRIVE_TURN_ON_SYNC.toString();
    }

    public String getTitle(Context context) {
        return context.getString(R.string.one_drive_turn_on_sync_tip_card_title);
    }

    public void startActivity(Context context) {
        new StartSettingsCmd().execute(this.mView.getEventContext(), SettingPreference.CloudSync.key, PopoverUtils.Anchor.TOP_END);
    }
}
