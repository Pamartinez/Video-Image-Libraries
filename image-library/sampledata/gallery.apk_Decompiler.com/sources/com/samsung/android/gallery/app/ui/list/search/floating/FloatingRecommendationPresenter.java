package com.samsung.android.gallery.app.ui.list.search.floating;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegate;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegateV2;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory;
import com.samsung.android.gallery.module.search.abstraction.FragmentVolatileStatus;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import o6.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingRecommendationPresenter<V extends IMvpBaseView> extends MvpBasePresenter<V> {
    private float mInitialY;
    protected FloatingRecommendationDelegate mRecommendationDelegate;
    private ViewGroup mRecommendationLayout;
    private final AtomicBoolean mRequestFocusOnResume = new AtomicBoolean();
    private SearchToolbarDelegate mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildEmpty();

    public FloatingRecommendationPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        if (PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V3) {
            this.mRecommendationDelegate = new FloatingRecommendationDelegateV2(v);
        } else if (PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V2) {
            this.mRecommendationDelegate = new FloatingRecommendationDelegate(v);
        }
    }

    private boolean handleRecommendationDelegateEvent(EventMessage eventMessage) {
        FloatingRecommendationDelegate floatingRecommendationDelegate = this.mRecommendationDelegate;
        if (floatingRecommendationDelegate == null || !floatingRecommendationDelegate.handleEvent(eventMessage)) {
            return false;
        }
        return true;
    }

    private boolean handleSearchToolbarEvent(EventMessage eventMessage) {
        return this.mSearchToolbarDelegate.handleEvent(eventMessage);
    }

    private ViewGroup initRecommendationLayout(View view) {
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.floating_recommendation_background_layout);
        this.mRecommendationLayout = viewGroup;
        ViewUtils.setMainLayoutFlexibleSideSpacing(viewGroup);
        return this.mRecommendationLayout;
    }

    /* access modifiers changed from: private */
    public boolean onIntercept(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mInitialY = motionEvent.getY();
        }
        if (!this.mSearchToolbarDelegate.hasFocus() || Math.abs(this.mInitialY - motionEvent.getY()) <= 16.0f) {
            return false;
        }
        this.mSearchToolbarDelegate.clearFocus();
        return false;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 8524) {
            this.mRequestFocusOnResume.set(true);
            return true;
        }
        if (i2 == 8515) {
            requestSuggestionData(true);
        }
        if (handleSearchToolbarEvent(eventMessage) || handleRecommendationDelegateEvent(eventMessage) || super.handleEvent(eventMessage)) {
            return true;
        }
        return false;
    }

    public void handleResolutionChange() {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(26));
        updateViews();
    }

    public boolean isMenuInitRequired() {
        return false;
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        this.mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildForFloatingRecommendation(this);
        FloatingRecommendationDelegate floatingRecommendationDelegate = this.mRecommendationDelegate;
        if (floatingRecommendationDelegate != null) {
            floatingRecommendationDelegate.onAttached(initRecommendationLayout(view));
            this.mRecommendationDelegate.setToolbarDimAreaHeight(this.mSearchToolbarDelegate.getDimAreaHeight());
            this.mRecommendationDelegate.setOnInterceptTouchListener(new p(9, this));
        }
        requestSuggestionData(false);
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        this.mSearchToolbarDelegate.onDestroy();
        FloatingRecommendationDelegate floatingRecommendationDelegate = this.mRecommendationDelegate;
        if (floatingRecommendationDelegate != null) {
            floatingRecommendationDelegate.onDestroy();
        }
    }

    public void onViewPause() {
        super.onViewPause();
        if (this.mSearchToolbarDelegate.hasFocus()) {
            this.mRequestFocusOnResume.set(true);
        }
    }

    public void onViewResume() {
        super.onViewResume();
        this.mSearchToolbarDelegate.onResume();
        if (this.mRequestFocusOnResume.getAndSet(false)) {
            this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(27));
        }
        FragmentVolatileStatus.resetVolatile();
    }

    public void requestSuggestionData(boolean z) {
        this.mBlackboard.publish(CommandKey.DATA_REQUEST("location://search/fileList/Recommendation"), new Object[]{Boolean.valueOf(z), Boolean.valueOf(PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V3)});
    }

    public void updateViews() {
        ViewUtils.setMainLayoutFlexibleSideSpacing(this.mRecommendationLayout);
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(22));
        FloatingRecommendationDelegate floatingRecommendationDelegate = this.mRecommendationDelegate;
        if (floatingRecommendationDelegate != null) {
            floatingRecommendationDelegate.updateSuggestionTitleTopMargin();
            this.mRecommendationDelegate.handleConfigurationChanged();
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }
}
