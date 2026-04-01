package com.samsung.android.gallery.app.ui.list.search.floating;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.search.floating.FloatingRecommendationPresenter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationBlur;
import com.samsung.android.gallery.module.search.recommendation.QuerySuggesterFactory;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingRecommendationFragment<V extends IMvpBaseView, P extends FloatingRecommendationPresenter<?>> extends MvpBaseFragment<V, P> implements IMvpBaseView {
    private final FloatingRecommendationBlur mBlur = new FloatingRecommendationBlur();
    private final AtomicBoolean mIsRecommendationVisible = new AtomicBoolean(true);

    public int getLayoutId() {
        return R.layout.fragment_search_floating_recommendation;
    }

    public String getScreenId() {
        if (this.mIsRecommendationVisible.get()) {
            return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_SUGGESTION_LIST.toString();
        }
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_KEYWORDS_LIST.toString();
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((FloatingRecommendationPresenter) p6).updateViews();
        }
        this.mBlur.updateBackgroundImageMatrix();
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((FloatingRecommendationPresenter) p6).handleResolutionChange();
        }
        this.mBlur.updateBackgroundImageMatrix();
    }

    public void initView(View view) {
        this.mBlur.initView(view, (Bitmap) this.mBlackboard.pop("data:///CapturedBitmap", null));
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        if (SdkConfig.atLeast(SdkConfig.GED.P) && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            View findViewById = view.findViewById(R.id.search_toolbar_container);
            ViewMarginUtils.setLeftMargin(findViewById, WindowUtils.getSystemInsetsLeft(rootWindowInsets));
            ViewMarginUtils.setRightMargin(findViewById, WindowUtils.getSystemInsetsRight(rootWindowInsets));
        }
        return windowInsets;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBlur.onDestroy();
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what == 8018) {
            this.mIsRecommendationVisible.set(((Boolean) ((Object[]) eventMessage.obj)[0]).booleanValue());
        }
        return super.onHandleEvent(eventMessage);
    }

    public void onPrePause(boolean z) {
        super.onPrePause(z);
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.AlwaysGetNewSuggestions)) {
            QuerySuggesterFactory.create().clear();
        }
        ((FloatingRecommendationPresenter) this.mPresenter).requestSuggestionData(false);
    }

    public void registerWindowInsetListener(List<View> list) {
        list.add(getView());
    }

    public boolean supportExitPredictiveBack() {
        return false;
    }

    public FloatingRecommendationPresenter<?> createPresenter(IMvpBaseView iMvpBaseView) {
        return new FloatingRecommendationPresenter<>(this.mBlackboard, iMvpBaseView);
    }
}
