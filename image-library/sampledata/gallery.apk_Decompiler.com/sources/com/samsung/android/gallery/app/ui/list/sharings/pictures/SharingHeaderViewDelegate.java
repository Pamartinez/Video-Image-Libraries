package com.samsung.android.gallery.app.ui.list.sharings.pictures;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SharingHeaderViewDelegate implements ISharingHeaderView {
    final String TAG = getClass().getSimpleName();
    View mHeaderView;
    ISharingPicturesView mView;

    public SharingHeaderViewDelegate(ISharingPicturesView iSharingPicturesView) {
        this.mView = iSharingPicturesView;
    }

    private void dismissTipCard(boolean z) {
        AnalyticsEventId analyticsEventId;
        ISharingPicturesView iSharingPicturesView = this.mView;
        if (z) {
            analyticsEventId = AnalyticsEventId.EVENT_SHARED_ALBUM_SUGGESTED_VIEW;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SHARED_ALBUM_SUGGESTED_NOT_NOW;
        }
        iSharingPicturesView.postAnalyticsLog(analyticsEventId);
        if (z) {
            ThreadUtil.postOnUiThreadDelayed(new f(0, this), 600);
            this.mView.getBlackboard().postEvent(EventMessage.obtain(6010, Boolean.FALSE));
            return;
        }
        updateTipCard(false, (Pair<Integer, Integer>) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$changeTipCard$0(View view, PicturesViewAdapter picturesViewAdapter) {
        if (picturesViewAdapter.setHeaderView(view)) {
            picturesViewAdapter.notifyItemChanged(0);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$composeTipCard$1(View view) {
        dismissTipCard(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$composeTipCard$2(TextView textView) {
        textView.setText(R.string.not_now);
        textView.setOnClickListener(new e(1, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$composeTipCard$3(View view) {
        dismissTipCard(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$composeTipCard$4(TextView textView) {
        textView.setText(R.string.view);
        textView.setOnClickListener(new e(0, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissTipCard$5() {
        updateTipCard(false, (Pair<Integer, Integer>) null);
    }

    public void changeTipCard(View view, boolean z) {
        Optional.ofNullable(this.mView.getAdapter()).ifPresent(new c(0, view));
    }

    public void composeTipCard(View view, Context context, Pair<Integer, Integer> pair) {
        int i2;
        if (view != null) {
            if (pair != null) {
                i2 = ((Integer) pair.second).intValue() + ((Integer) pair.first).intValue();
            } else {
                i2 = 0;
            }
            ViewUtils.setVisibleOrGone(view.findViewById(R.id.headerTitle), false);
            ((TextView) view.findViewById(R.id.headerContent)).setText(context.getResources().getQuantityString(R.plurals.n_suggested_pictures_on_family_album, i2, new Object[]{Integer.valueOf(i2)}));
            Optional.ofNullable((TextView) view.findViewById(R.id.cancelView)).ifPresent(new d(this, 0));
            Optional.ofNullable((TextView) view.findViewById(R.id.doneView)).ifPresent(new d(this, 1));
        }
    }

    public View get() {
        return this.mHeaderView;
    }

    public View inflateTipCard(Context context, boolean z, Pair<Integer, Integer> pair) {
        if (!z || context == null) {
            return null;
        }
        if (this.mHeaderView == null) {
            this.mHeaderView = LayoutInflater.from(context).inflate(R.layout.tip_card_layout_pictures, (ViewGroup) null);
        }
        composeTipCard(this.mHeaderView, context, pair);
        return this.mHeaderView;
    }

    public void recycle() {
        this.mHeaderView = null;
    }

    public void updateTipCard(boolean z, Pair<Integer, Integer> pair) {
        changeTipCard(inflateTipCard(this.mView.getContext(), z, pair), z);
    }
}
