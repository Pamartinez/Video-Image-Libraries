package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.module.cloud.CloudLog;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.OneDriveManager;
import com.samsung.android.gallery.module.cloud.SamsungCloudManager;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDriveEosTipCard extends AbsTipCard {
    private UserType mUserType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum UserType {
        MS_LINKED,
        MS_UNLINKED
    }

    private void disableTipCard() {
        if (UserType.MS_LINKED.equals(this.mUserType)) {
            GalleryPreference.getInstanceCache().saveState("one_drive_eos_tip_card_count", GalleryPreference.getInstanceCache().loadInt("one_drive_eos_tip_card_count", 0) + 1);
            GalleryPreference.getInstanceCache().saveState("one_drive_eos_tip_card_time", System.currentTimeMillis());
            return;
        }
        GalleryPreference.getInstanceCache().saveState(getTag(), false);
    }

    private boolean isDisplayPeriodRefreshed() {
        if (GalleryPreference.getInstanceCache().loadLong("one_drive_eos_tip_card_time", -1) < TimeUtil.getDaysAgo(30)) {
            return true;
        }
        return false;
    }

    private boolean isRefreshable() {
        if (GalleryPreference.getInstanceCache().loadInt("one_drive_eos_tip_card_count", 0) < 3) {
            return true;
        }
        return false;
    }

    private boolean isTipCardEnabled() {
        if (!UserType.MS_LINKED.equals(this.mUserType)) {
            return GalleryPreference.getInstanceCache().loadBoolean(getTag(), true);
        }
        if (!isRefreshable() || !isDisplayPeriodRefreshed()) {
            return false;
        }
        return true;
    }

    private void postCloudLog(Context context, int i2, int i7) {
        String str;
        if (UserType.MS_LINKED.equals(this.mUserType)) {
            str = "gallery_od_user_tip_card";
        } else {
            str = "gallery_new_user_tip_card";
        }
        if (i2 == 0) {
            CloudLog.sendLog(context, str, i2);
        } else if (i2 == 1) {
            CloudLog.sendLog(context, str, i2, i7);
        }
    }

    private void startActivity(Context context) {
        if (UserType.MS_LINKED.equals(this.mUserType)) {
            OneDriveManager.getInstance().startEosActivity(context, "GalleryLearnMore");
        } else {
            SamsungCloudManager.getInstance().startUpselling(context);
        }
    }

    public boolean checkTipCard(Context context) {
        UserType userType;
        if (CloudStateCompat.isMigrationAvailable() || CloudStateCompat.isOneDriveAvailable()) {
            userType = UserType.MS_LINKED;
        } else if (CloudStateCompat.isSubscriptionRequiredLinkedBefore() || CloudStateCompat.isSubscriptionRequired()) {
            userType = UserType.MS_UNLINKED;
        } else {
            userType = null;
        }
        this.mUserType = userType;
        if (userType == null) {
            Log.d(this.TAG, "skip for invalid state", CloudStateCompat.getServiceName(CloudStateCompat.getService()));
        }
        if (this.mUserType == null || !CloudStateCompat.isShowTips() || !isTipCardEnabled()) {
            return false;
        }
        return true;
    }

    public String getCancelBtnString(Context context) {
        if (UserType.MS_LINKED.equals(this.mUserType)) {
            return context.getString(R.string.dismiss);
        }
        return context.getString(R.string.not_now);
    }

    public String getDescription(Context context) {
        if (UserType.MS_LINKED.equals(this.mUserType)) {
            return context.getString(R.string.new_scloud_tip_card_description_one_drive_ending_soon, new Object[]{TimeUtil.getLocalizedDate(CloudStateCompat.getOneDriveEndDate())});
        }
        return context.getString(R.string.new_scloud_tip_card_description_subscription_required);
    }

    public String getDoneBtnString(Context context) {
        if (UserType.MS_LINKED.equals(this.mUserType)) {
            return context.getString(R.string.learn_more);
        }
        return context.getString(R.string.get_started);
    }

    public String getTag() {
        return this.TAG + "#" + this.mUserType;
    }

    public String getTipCardCreationLogDetail() {
        if (UserType.MS_LINKED.equals(this.mUserType)) {
            return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_ONE_DRIVE_ENDING_SOON.toString();
        }
        return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_SAMSUNG_CLOUD_LAUNCHING.toString();
    }

    public String getTitle(Context context) {
        if (UserType.MS_LINKED.equals(this.mUserType)) {
            return context.getString(R.string.sync_with_one_drive_ending_soon);
        }
        return context.getString(R.string.a_new_way_to_protect_your_memories);
    }

    public void initializeView(Context context, TipCardViewHolder tipCardViewHolder) {
        super.initializeView(context, tipCardViewHolder);
        postCloudLog(context, 0, 0);
    }

    public void onCancelBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        disableTipCard();
        dismissTipCard(tipCardViewHolder);
        postPresenceLog(AnalyticsEventId.EVENT_TIP_CARD_ONE_DRIVE_NOT_NOW);
        postCloudLog(context, 1, 1);
    }

    public void onDoneBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        AnalyticsDetail analyticsDetail;
        disableTipCard();
        dismissTipCardWithDelayed(tipCardViewHolder);
        startActivity(context);
        AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_TIP_CARD_ONE_DRIVE_GET_STARTED;
        if (UserType.MS_LINKED.equals(this.mUserType)) {
            analyticsDetail = AnalyticsDetail.EVENT_DETAIL_ONE_DRIVE_ENDING_SOON;
        } else {
            analyticsDetail = AnalyticsDetail.EVENT_DETAIL_SAMSUNG_CLOUD_LAUNCHING;
        }
        postPresenceLog(analyticsEventId, analyticsDetail.toString());
        postCloudLog(context, 1, 2);
    }
}
