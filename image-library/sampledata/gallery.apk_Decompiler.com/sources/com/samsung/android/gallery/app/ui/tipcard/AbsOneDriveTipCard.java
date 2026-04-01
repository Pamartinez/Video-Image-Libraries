package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbsOneDriveTipCard extends AbsTipCard {
    public boolean checkTipCard(Context context) {
        if (Features.isEnabled(Features.IS_VERIZON_DEVICE)) {
            Log.d(this.TAG, "skip for vendor");
            return false;
        } else if (!Features.isEnabled(Features.IS_MUM_MODE)) {
            return CloudStateCompat.isLegacyServiceStatusRequired();
        } else {
            Log.d(this.TAG, "skip for guest mode");
            return false;
        }
    }

    public abstract void disableTipCard();

    public int getButtonColor(Context context) {
        return context.getColor(R.color.one_drive_tip_card_button_color);
    }

    public abstract String getTipCardDoneClickLogDetail();

    public void onCancelBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        disableTipCard();
        dismissTipCard(tipCardViewHolder);
        postPresenceLog(AnalyticsEventId.EVENT_TIP_CARD_ONE_DRIVE_NOT_NOW);
    }

    public void onDoneBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        disableTipCard();
        dismissTipCardWithDelayed(tipCardViewHolder);
        startActivity(context);
        postPresenceLog(AnalyticsEventId.EVENT_TIP_CARD_ONE_DRIVE_GET_STARTED, getTipCardDoneClickLogDetail());
    }

    public abstract void startActivity(Context context);
}
