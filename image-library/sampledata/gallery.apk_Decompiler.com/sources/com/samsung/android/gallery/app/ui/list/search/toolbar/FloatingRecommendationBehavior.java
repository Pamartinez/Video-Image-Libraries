package com.samsung.android.gallery.app.ui.list.search.toolbar;

import Ab.b;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.FloatingAutoCompleteDelegate;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegate;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingRecommendationBehavior extends BaseSearchToolbarBehavior {
    final String TAG = getClass().getSimpleName();
    protected FloatingAutoCompleteDelegate mAutoCompleteDelegate;
    protected FloatingRecommendationDelegate mRecommendationDelegate;
    protected final IMvpBaseView mView;

    public FloatingRecommendationBehavior(IMvpBaseView iMvpBaseView) {
        super(iMvpBaseView);
        this.mView = iMvpBaseView;
        if (!PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V2 && !PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V3) {
            this.mRecommendationDelegate = new FloatingRecommendationDelegate(iMvpBaseView);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDecorViewsHeight$0(int i2, SearchToolbar searchToolbar) {
        View view = this.mView.getView();
        if (view != null) {
            int height = (view.getHeight() - i2) - searchToolbar.getGradientAreaHeight();
            FloatingRecommendationDelegate floatingRecommendationDelegate = this.mRecommendationDelegate;
            if (floatingRecommendationDelegate != null) {
                floatingRecommendationDelegate.setHeight(height);
            }
            FloatingAutoCompleteDelegate autoCompleteDelegate = getAutoCompleteDelegate(searchToolbar);
            if (!this.mView.isTabletDpi() && this.mView.isLandscape()) {
                height = 0;
            }
            autoCompleteDelegate.setHeight(height);
        }
    }

    private void setDecorViewsHeight(SearchToolbar searchToolbar, int i2) {
        ViewUtils.post(this.mView.getView(), new b((Object) this, i2, (Object) searchToolbar, 19));
    }

    private void setRecommendationVisibility(SearchToolbar searchToolbar, boolean z, boolean z3) {
        this.mView.onHandleEvent(EventMessage.obtain(8018, new Object[]{Boolean.valueOf(z), searchToolbar}));
        FloatingRecommendationDelegate floatingRecommendationDelegate = this.mRecommendationDelegate;
        if (floatingRecommendationDelegate == null) {
            return;
        }
        if (z) {
            floatingRecommendationDelegate.show(searchToolbar.getView(), z3);
        } else {
            floatingRecommendationDelegate.hide(searchToolbar.hasFocus());
        }
    }

    private void updateHorizontalPadding(SearchToolbar searchToolbar) {
        ViewUtils.setMainLayoutFlexibleSideSpacing(searchToolbar.getView());
    }

    public void applyInsets(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        int systemInsetsBottom = WindowUtils.getSystemInsetsBottom(windowInsets) + WindowUtils.getIMEInsetsBottom(windowInsets);
        setDecorViewsHeight(searchToolbar, WindowUtils.getSystemInsetsTop(windowInsets) + systemInsetsBottom);
        ViewMarginUtils.setBottomMargin(searchToolbar.getView(), systemInsetsBottom);
        searchToolbar.setVisibility(0);
    }

    public FloatingAutoCompleteDelegate getAutoCompleteDelegate(SearchToolbar searchToolbar) {
        if (this.mAutoCompleteDelegate == null) {
            this.mAutoCompleteDelegate = new FloatingAutoCompleteDelegate(this.mView.getBlackboard(), searchToolbar.getView());
        }
        return this.mAutoCompleteDelegate;
    }

    public boolean handleEvent(SearchToolbar searchToolbar, EventMessage eventMessage) {
        if (eventMessage.what == 8017) {
            if (this.mView.isViewResumed()) {
                getAutoCompleteDelegate(searchToolbar).handleItemSelect(this.mView, (FilterResultsEntity) eventMessage.obj);
                searchToolbar.setQuery("", false);
            }
            return true;
        }
        FloatingRecommendationDelegate floatingRecommendationDelegate = this.mRecommendationDelegate;
        if (floatingRecommendationDelegate == null || !floatingRecommendationDelegate.handleEvent(eventMessage)) {
            return false;
        }
        return true;
    }

    public boolean handleInternalEvent(SearchToolbar searchToolbar, SearchToolbarEvent searchToolbarEvent) {
        int i2 = searchToolbarEvent.what;
        if (i2 == 22) {
            if (PreferenceFeatures.OneUi8x.COLLECTION_TAB && this.mView.isDrawerMode()) {
                return true;
            }
            updateHorizontalPadding(searchToolbar);
            return true;
        } else if (i2 != 26) {
            return false;
        } else {
            FloatingRecommendationDelegate floatingRecommendationDelegate = this.mRecommendationDelegate;
            if (floatingRecommendationDelegate != null) {
                floatingRecommendationDelegate.invalidateHistoryView();
            }
            return true;
        }
    }

    public void onApplyInsets(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        applyInsets(searchToolbar, windowInsets);
    }

    public void onAttached(SearchToolbar searchToolbar, SearchToolbarOptions searchToolbarOptions) {
        FloatingRecommendationDelegate floatingRecommendationDelegate = this.mRecommendationDelegate;
        if (floatingRecommendationDelegate != null) {
            floatingRecommendationDelegate.onAttached(searchToolbar.getView());
        }
        searchToolbar.setGradientVisibility(false);
        searchToolbar.setVisibility(8);
        updateHorizontalPadding(searchToolbar);
    }

    public void onClickCloseButton(SearchToolbar searchToolbar) {
        this.mView.postAnalyticsLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_KEYWORDS_LIST.toString(), AnalyticsEventId.EVENT_SEARCH_ACTIONBAR_DELETE_BTN);
    }

    public void onDestroy(SearchToolbar searchToolbar) {
        FloatingRecommendationDelegate floatingRecommendationDelegate = this.mRecommendationDelegate;
        if (floatingRecommendationDelegate != null) {
            floatingRecommendationDelegate.onDestroy();
        }
        FloatingAutoCompleteDelegate floatingAutoCompleteDelegate = this.mAutoCompleteDelegate;
        if (floatingAutoCompleteDelegate != null) {
            floatingAutoCompleteDelegate.onDestroy();
        }
    }

    public void onFirstDraw(SearchToolbar searchToolbar) {
        searchToolbar.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) searchToolbar.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(searchToolbar.getSearchTextView(), 0);
        }
    }

    public void onFocusChanged(SearchToolbar searchToolbar, boolean z) {
        if (!z) {
            getAutoCompleteDelegate(searchToolbar).dismissAutoCompleteView();
        } else if (TextUtils.isEmpty(searchToolbar.getQuery())) {
            setRecommendationVisibility(searchToolbar, true, !this.mView.isInMultiWindowMode());
            getAutoCompleteDelegate(searchToolbar).dismissAutoCompleteView();
        } else {
            setRecommendationVisibility(searchToolbar, false, false);
            getAutoCompleteDelegate(searchToolbar).onTextChanged((CharSequence) searchToolbar.getQuery().toString(), true);
        }
    }

    public void onInsetsProgress(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        applyInsets(searchToolbar, windowInsets);
    }

    public void onPreQuery(SearchToolbar searchToolbar) {
        searchToolbar.setQuery("", false);
        this.mView.onHandleEvent(EventMessage.obtain(8524));
    }

    public void onQueryTextChanged(SearchToolbar searchToolbar, String str) {
        if (searchToolbar.hasFocus()) {
            if (TextUtils.isEmpty(str)) {
                getAutoCompleteDelegate(searchToolbar).dismissAutoCompleteView();
                setRecommendationVisibility(searchToolbar, true, false);
                return;
            }
            getAutoCompleteDelegate(searchToolbar).onTextChanged((CharSequence) str, true);
            setRecommendationVisibility(searchToolbar, false, false);
        }
    }

    public void updateToolbar(SearchToolbar searchToolbar, String str) {
    }
}
