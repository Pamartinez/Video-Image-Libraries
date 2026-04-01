package com.samsung.android.gallery.app.ui.tipcard;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.tipcard.abstraction.TipCardView;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animations.TipCardDismissAnimation;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbsTipCard implements TipCardView {
    protected final String TAG = getClass().getSimpleName();
    private TipCardViewHolder mTipCardViewHolder;
    IBaseListView mView;

    private void setButtonTextColor(TextView textView, int i2) {
        if (i2 != -1) {
            textView.setTextColor(i2);
        }
    }

    private void setTextAndVisibility(TextView textView, String str) {
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
            textView.setVisibility(0);
            return;
        }
        textView.setVisibility(8);
    }

    public final View createTipCardView(IBaseListView iBaseListView) {
        this.mView = iBaseListView;
        if (this.mTipCardViewHolder == null && isTipCardAvailable()) {
            Context context = iBaseListView.getContext();
            TipCardViewHolder tipCardViewHolder = new TipCardViewHolder(LayoutInflater.from(context).inflate(R.layout.tip_card_layout_pictures, (ViewGroup) null, false));
            this.mTipCardViewHolder = tipCardViewHolder;
            initializeView(context, tipCardViewHolder);
            postPresenceLog(AnalyticsEventId.EVENT_TIP_CARD_PRESENCE, getTipCardCreationLogDetail());
        }
        TipCardViewHolder tipCardViewHolder2 = this.mTipCardViewHolder;
        if (tipCardViewHolder2 != null) {
            return tipCardViewHolder2.getItemView();
        }
        return null;
    }

    public void dismissTipCard(TipCardViewHolder tipCardViewHolder) {
        View itemView = tipCardViewHolder.getItemView();
        new TipCardDismissAnimation().start(itemView, new b(1, itemView));
    }

    public void dismissTipCardWithDelayed(TipCardViewHolder tipCardViewHolder) {
        ThreadUtil.postOnUiThreadDelayed(new b(0, tipCardViewHolder), 300);
    }

    public int getButtonColor(Context context) {
        return -1;
    }

    public String getCancelBtnString(Context context) {
        return null;
    }

    public abstract String getDescription(Context context);

    public String getDoneBtnString(Context context) {
        return null;
    }

    public abstract String getTag();

    public String getTipCardCreationLogDetail() {
        return null;
    }

    public String getTitle(Context context) {
        return null;
    }

    public void initializeView(Context context, TipCardViewHolder tipCardViewHolder) {
        tipCardViewHolder.getItemView().setTag(getTag());
        setTextAndVisibility(tipCardViewHolder.getTitleView(), getTitle(context));
        setTextAndVisibility(tipCardViewHolder.getContentsView(), getDescription(context));
        setButtonProperty(context, tipCardViewHolder.getDoneView(), getDoneBtnString(context), new a(this, context, tipCardViewHolder, 0));
        setButtonProperty(context, tipCardViewHolder.getCancelView(), getCancelBtnString(context), new a(this, context, tipCardViewHolder, 1));
        adjustMarginIfNeeded(tipCardViewHolder);
    }

    public boolean isTipCardAvailable() {
        return true;
    }

    public void postPresenceLog(AnalyticsEventId analyticsEventId) {
        this.mView.postAnalyticsLog(analyticsEventId);
    }

    public void setButtonProperty(Context context, TextView textView, String str, View.OnClickListener onClickListener) {
        setTextAndVisibility(textView, str);
        setButtonTextColor(textView, getButtonColor(context));
        textView.setOnClickListener(onClickListener);
        textView.setContentDescription(textView.getText() + ArcCommonLog.TAG_COMMA + context.getString(R.string.speak_button));
        SeApiCompat.setButtonShapeEnabled(textView);
    }

    public void postPresenceLog(AnalyticsEventId analyticsEventId, String str) {
        this.mView.postAnalyticsLog(analyticsEventId, str);
    }

    public void adjustMarginIfNeeded(TipCardViewHolder tipCardViewHolder) {
    }

    /* renamed from: onCancelBtnClicked */
    public void lambda$initializeView$3(Context context, View view, TipCardViewHolder tipCardViewHolder) {
    }

    /* renamed from: onDoneBtnClicked */
    public void lambda$initializeView$2(Context context, View view, TipCardViewHolder tipCardViewHolder) {
    }
}
