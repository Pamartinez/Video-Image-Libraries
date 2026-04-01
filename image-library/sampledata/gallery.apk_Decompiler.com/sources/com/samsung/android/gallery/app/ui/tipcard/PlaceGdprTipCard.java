package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.controller.internals.StartSettingsCmd;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.helper.PopoverUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlaceGdprTipCard extends AbsTipCard {
    private static final PreferenceName PREFERENCE_NAME;

    static {
        PreferenceName preferenceName;
        if (Features.isEnabled(Features.SUPPORT_SAMSUNG_PLACE)) {
            preferenceName = PreferenceName.IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP_V2;
        } else {
            preferenceName = PreferenceName.IS_NEED_TO_SHOW_LOCATION_GDPR_POPUP;
        }
        PREFERENCE_NAME = preferenceName;
    }

    private void disableTipCard() {
        GalleryPreference.getInstance().saveState(PREFERENCE_NAME, false);
    }

    public boolean checkTipCard(Context context) {
        return GalleryPreference.getInstance().loadBoolean(PREFERENCE_NAME, true);
    }

    public String getCancelBtnString(Context context) {
        return context.getString(R.string.not_now);
    }

    public String getDescription(Context context) {
        return context.getString(R.string.foursquare_tip_card_description);
    }

    public String getDoneBtnString(Context context) {
        return context.getString(R.string.turn_on);
    }

    public String getTag() {
        return getClass().getSimpleName();
    }

    public String getTipCardCreationLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_PLACE_GDPR.toString();
    }

    public void onCancelBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        disableTipCard();
        dismissTipCard(tipCardViewHolder);
    }

    public void onDoneBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        disableTipCard();
        dismissTipCardWithDelayed(tipCardViewHolder);
        new StartSettingsCmd().execute(this.mView.getEventContext(), SettingPreference.LocationAuth.key, PopoverUtils.Anchor.TOP_END);
    }
}
