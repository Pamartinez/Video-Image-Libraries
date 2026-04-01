package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import e5.C0451a;
import e6.C0453a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoAlbumTipCard extends AbsTipCard {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setRoleDescription(AppResources.getString(R.string.speak_button));
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        }
    };

    private void disableTipCard() {
        PreferenceCache.VideoAlbumTipCard.setBoolean(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDoneBtnClicked$1() {
        Optional.ofNullable(this.mView.getEventContext()).ifPresent(new C0453a(7));
    }

    public boolean checkTipCard(Context context) {
        if (!Features.isEnabled(Features.SUPPORT_MULTI_VIDEO_EDIT) || !PreferenceCache.VideoAlbumTipCard.getBoolean()) {
            return false;
        }
        return true;
    }

    public String getCancelBtnString(Context context) {
        return context.getString(R.string.not_now);
    }

    public String getDescription(Context context) {
        return context.getString(R.string.virtual_album_tip_card_description);
    }

    public String getDoneBtnString(Context context) {
        return context.getString(R.string.create_dialog_title);
    }

    public String getTag() {
        return getClass().getSimpleName();
    }

    public void initializeView(Context context, TipCardViewHolder tipCardViewHolder) {
        super.initializeView(context, tipCardViewHolder);
        tipCardViewHolder.getDoneView().setAccessibilityDelegate(this.mAccessibilityDelegate);
        tipCardViewHolder.getCancelView().setAccessibilityDelegate(this.mAccessibilityDelegate);
    }

    public void onCancelBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        disableTipCard();
        dismissTipCard(tipCardViewHolder);
        postPresenceLog(AnalyticsEventId.EVENT_CREATE_MOVIE_TIP_CARD_NOT_NOW);
    }

    public void onDoneBtnClicked(Context context, View view, TipCardViewHolder tipCardViewHolder) {
        disableTipCard();
        dismissTipCard(tipCardViewHolder);
        ThreadUtil.postOnUiThreadDelayed(new C0451a(12, this), 300);
    }
}
