package com.samsung.android.gallery.app.ui.viewer2.details.items;

import M7.a;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import i.C0212a;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsItem {
    protected final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    protected final Blackboard mBlackboard;
    protected final DetailsView mDetailsView;
    protected final EventContext mEventContext;
    protected View mItemView;
    private final SparseArray<View> mViewList = new SparseArray<>();
    protected final HashMap<DetailsUpdateKey, DetailsViewUpdater> mViewUpdaterMap = new HashMap<>();

    public DetailsItem(DetailsView detailsView, EventContext eventContext) {
        this.mDetailsView = detailsView;
        View findViewById = detailsView.findViewById(getLayoutId());
        this.mItemView = findViewById;
        if (findViewById instanceof ViewStub) {
            ((ViewStub) findViewById).setOnInflateListener(new a(this));
        }
        this.mEventContext = eventContext;
        this.mBlackboard = eventContext.getBlackboard();
        registerViewUpdater();
    }

    public abstract int getLayoutId();

    public TextView getTextView(int i2, boolean z) {
        TextView textView = (TextView) getView(i2, false);
        if (textView != null || !z) {
            return textView;
        }
        return (TextView) getView(i2, true);
    }

    public final <T extends View> T getView(int i2) {
        return getView(i2, true);
    }

    public final View getViewOrViewStub(int i2) {
        return this.mItemView.findViewById(i2);
    }

    public void hide() {
        View view = this.mItemView;
        if (!(view instanceof ViewStub)) {
            ViewUtils.setVisibility(view, 8);
        }
    }

    public void onRecycled() {
        hide();
    }

    public void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_DETAILS_NORMAL.toString(), analyticsEventId.toString());
    }

    public final void refineData(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        DetailsViewUpdater detailsViewUpdater = this.mViewUpdaterMap.get(detailsLoadResult.key);
        if (detailsViewUpdater != null && mediaItem != null && supportItem(mediaItem)) {
            detailsViewUpdater.refinedItemInternal(mediaItem);
        }
    }

    public abstract void registerViewUpdater();

    public final boolean setInputBlocked(int i2) {
        Boolean bool = (Boolean) this.mEventContext.getEventContextData("is_input_blocked");
        if (bool == null || !bool.booleanValue()) {
            this.mBlackboard.post("command:///SetInputBlock", new Object[]{this.TAG.toString(), Integer.valueOf(i2)});
            return true;
        }
        Log.w(this.TAG, "input blocked");
        return false;
    }

    public final void setMultiColumnTextView(int i2, List<String> list) {
        setMultiColumnTextView(i2, -1, list);
    }

    public void setTag(String str) {
        this.TAG.setTag(str);
    }

    public final void setTextAndVisibility(TextView textView, String str) {
        if (textView != null && !TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        ViewUtils.setVisibility(textView, TextUtils.isEmpty(str) ? 8 : 0);
    }

    public void setVisibleView(int i2, boolean z) {
        ViewUtils.setVisibleOrGone(getView(i2, z), z);
    }

    public void show() {
        View view = this.mItemView;
        if (!(view instanceof ViewStub)) {
            ViewUtils.setVisibility(view, 0);
        }
    }

    public abstract boolean supportItem(MediaItem mediaItem);

    public final boolean supportLargeScreenHorizontalGui() {
        return this.mDetailsView.supportLargeScreenHorizontalGui();
    }

    public final void updateView(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        DetailsViewUpdater detailsViewUpdater = this.mViewUpdaterMap.get(detailsLoadResult.key);
        if (detailsViewUpdater != null) {
            if (mediaItem == null || !supportItem(mediaItem)) {
                hide();
                return;
            }
            detailsViewUpdater.updateViewInternal(mediaItem, detailsLoadResult);
            show();
        }
    }

    public final <T extends View> T getView(View view, int i2) {
        return getView(view, i2, true);
    }

    public void postAnalyticsLog(AnalyticsEventId analyticsEventId, AnalyticsDetail analyticsDetail) {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_DETAILS_NORMAL.toString(), analyticsEventId.toString(), analyticsDetail.toString());
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setMultiColumnTextView(int r3, int r4, java.util.List<java.lang.String> r5) {
        /*
            r2 = this;
            r0 = 0
            android.view.View r0 = r2.getView((int) r3, (boolean) r0)
            com.samsung.android.gallery.widget.MultiColumnTextView r0 = (com.samsung.android.gallery.widget.MultiColumnTextView) r0
            if (r0 != 0) goto L_0x0012
            if (r5 == 0) goto L_0x0011
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L_0x0012
        L_0x0011:
            return
        L_0x0012:
            if (r0 != 0) goto L_0x001c
            r0 = 1
            android.view.View r2 = r2.getView((int) r3, (boolean) r0)
            r0 = r2
            com.samsung.android.gallery.widget.MultiColumnTextView r0 = (com.samsung.android.gallery.widget.MultiColumnTextView) r0
        L_0x001c:
            if (r4 <= 0) goto L_0x0022
            r0.setData(r5, r4)
            return
        L_0x0022:
            r0.setData(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItem.setMultiColumnTextView(int, int, java.util.List):void");
    }

    public final <T extends View> T getView(int i2, boolean z) {
        return getView(this.mItemView, i2, z);
    }

    public final <T extends View> T getView(View view, int i2, boolean z) {
        T t = (View) this.mViewList.get(i2);
        if (t != null || !z) {
            return t;
        }
        if (view instanceof ViewStub) {
            View inflate = ((ViewStub) view).inflate();
            if (view == this.mItemView) {
                this.mItemView = inflate;
            }
            view = inflate;
        }
        T findViewById = view.findViewById(i2);
        if (findViewById != null) {
            this.mViewList.put(i2, findViewById);
            return findViewById;
        }
        throw new IllegalStateException(C0212a.j(i2, "id (", ") is not found in view"));
    }

    public final void setTextAndVisibility(int i2, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            ViewUtils.setVisibility(getViewOrViewStub(i2), 8);
            return;
        }
        TextView textView = getTextView(i2, true);
        ViewUtils.setVisibility(textView, 0);
        textView.setText(charSequence);
    }

    public final void setTextAndVisibility(int i2, int i7) {
        TextView textView = getTextView(i2, true);
        ViewUtils.setVisibility(textView, 0);
        textView.setText(i7);
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void updateLayout() {
    }

    public void onViewInflate(ViewStub viewStub, View view) {
    }
}
