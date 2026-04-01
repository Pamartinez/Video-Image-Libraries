package com.samsung.android.gallery.app.ui.tipcard;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SecMPPermissionTipCard extends AbsTipCard {
    public boolean checkTipCard(Context context) {
        return ((Boolean) Blackboard.getApplicationInstance().read("data://user/SecurityExceptionOnSecMp", Boolean.FALSE)).booleanValue();
    }

    public String getDescription(Context context) {
        return context.getString(R.string.sec_mp_security_allow_permission_description, new Object[]{PackageMonitorCompat.getInstance().getApplicationLabel("com.samsung.android.providers.media")});
    }

    public String getDoneBtnString(Context context) {
        return context.getString(R.string.settings);
    }

    public String getTag() {
        return getClass().getSimpleName();
    }

    public String getTipCardCreationLogDetail() {
        return AnalyticsDetail.EVENT_DETAIL_TIP_CARD_PRESENCE_SEC_MP_PRESENCE.toString();
    }

    public void onDoneBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        try {
            context.startActivity(new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION", Uri.fromParts("package", "com.samsung.android.providers.media", (String) null)).addFlags(268435456));
            postPresenceLog(AnalyticsEventId.EVENT_SEC_MP_PERMISSION_SETTINGS);
        } catch (ActivityNotFoundException unused) {
            Log.e(this.TAG, "can not find sec media provider permission activity");
        }
    }
}
