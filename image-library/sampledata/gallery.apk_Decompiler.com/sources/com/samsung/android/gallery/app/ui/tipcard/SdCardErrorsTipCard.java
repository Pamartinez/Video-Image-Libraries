package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.android.gallery3d.R;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SdCardErrorsTipCard extends AbsTipCard {
    private void checkSdCardName(String str) {
        PreferenceCache preferenceCache = PreferenceCache.SdCardErrorTipCardCount;
        String string = preferenceCache.getString();
        if (!string.isEmpty() && !str.equals(string.split(NumericEnum.SEP)[0])) {
            preferenceCache.clear();
            PreferenceCache.SdCardErrorTipCardTime.clear();
        }
    }

    private void disableTipCard() {
        String cardId = StorageInfo.getRemovable().getCardId();
        int tipCardDisplayedCount = getTipCardDisplayedCount() + 1;
        long currentTimeMillis = System.currentTimeMillis();
        PreferenceCache preferenceCache = PreferenceCache.SdCardErrorTipCardCount;
        preferenceCache.setString(cardId + NumericEnum.SEP + tipCardDisplayedCount);
        PreferenceCache preferenceCache2 = PreferenceCache.SdCardErrorTipCardTime;
        StringBuilder t = C0212a.t(cardId, NumericEnum.SEP);
        t.append(System.currentTimeMillis());
        preferenceCache2.setString(t.toString());
        Log.d(this.TAG, "update tip card state", Logger.getEncodedString(cardId), Integer.valueOf(tipCardDisplayedCount), Long.valueOf(currentTimeMillis));
    }

    private int getTipCardDisplayedCount() {
        String string = PreferenceCache.SdCardErrorTipCardCount.getString();
        if (string.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(string.split(NumericEnum.SEP)[1]);
    }

    private boolean isDisplayedPeriodRefreshed() {
        long daysAgo = TimeUtil.getDaysAgo(7);
        String string = PreferenceCache.SdCardErrorTipCardTime.getString();
        if (string.isEmpty() || Long.parseLong(string.split(NumericEnum.SEP)[1]) < daysAgo) {
            return true;
        }
        return false;
    }

    public void adjustMarginIfNeeded(TipCardViewHolder tipCardViewHolder) {
        ViewMarginUtils.setTopMargin(tipCardViewHolder.getContentsView(), 0);
    }

    public boolean checkTipCard(Context context) {
        String cardId;
        if (FileUtils.hasSdcardVolume() && (cardId = StorageInfo.getRemovable().getCardId()) != null && !cardId.isEmpty()) {
            checkSdCardName(cardId);
            if (getTipCardDisplayedCount() < 2 && isDisplayedPeriodRefreshed()) {
                return !SeApiCompat.isSdcardHealthy(context);
            }
        }
        return false;
    }

    public String getCancelBtnString(Context context) {
        return context.getString(R.string.not_now);
    }

    public String getDescription(Context context) {
        return context.getResources().getString(R.string.sd_card_errors);
    }

    public String getTag() {
        return "SdCardErrorsTipCard";
    }

    public String getTipCardCreationLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_SD_CARD_ERRORS.toString();
    }

    public void onCancelBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        disableTipCard();
        dismissTipCard(tipCardViewHolder);
    }
}
