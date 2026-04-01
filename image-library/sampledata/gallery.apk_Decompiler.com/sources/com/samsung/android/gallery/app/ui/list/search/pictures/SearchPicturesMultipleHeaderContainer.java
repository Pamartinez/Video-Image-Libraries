package com.samsung.android.gallery.app.ui.list.search.pictures;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterHeaderView;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchAnalysisTipHeaderView;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchAskScreenShotHeaderView;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeaderView;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchMapHeaderView;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchRelationshipHeaderView;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchScreenShotHeaderView;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.search.filter.SearchFiltersViewContainer;
import com.samsung.android.gallery.widget.search.filter.SearchFuzzyHeaderView;
import com.samsung.android.gallery.widget.search.pictures.SearchCountHeaderView;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import n4.C0489a;
import n4.C0491c;
import q8.a;
import r6.e;
import v5.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPicturesMultipleHeaderContainer {
    private SearchAnalysisTipHeaderView mAnalysisTipHeader;
    private final ViewGroup mAnalysisTipHeaderLayout;
    private SearchHeaderView mContentHeader;
    private final ViewGroup mContentHeaderLayout;
    private SearchHeaderView mFilterHeader;
    private final ViewGroup mFilterHeaderLayout;
    private int mFilterItemLayoutId = 0;
    private final ViewGroup mMainLayout;
    private boolean mSupportViewByDate;

    public SearchPicturesMultipleHeaderContainer(Context context) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.search_pictures_multiple_header_view, (ViewGroup) null);
        this.mMainLayout = viewGroup;
        this.mAnalysisTipHeaderLayout = (ViewGroup) viewGroup.findViewById(R.id.analysis_tip_header);
        this.mFilterHeaderLayout = (ViewGroup) viewGroup.findViewById(R.id.filter_header);
        this.mContentHeaderLayout = (ViewGroup) viewGroup.findViewById(R.id.content_header);
    }

    private boolean isAllFilterSelected() {
        SearchHeaderView searchHeaderView = this.mFilterHeader;
        if (searchHeaderView instanceof SearchFiltersViewContainer) {
            return ((SearchFiltersViewContainer) searchHeaderView).isAllFilterSelected();
        }
        return false;
    }

    private boolean isFilterHeaderVisible() {
        return ((Boolean) Optional.ofNullable(this.mFilterHeaderLayout).map(new a(15)).orElse(Boolean.FALSE)).booleanValue();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$isFilterHeaderVisible$0(ViewGroup viewGroup) {
        boolean z;
        if (viewGroup.getVisibility() == 0) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFuzzyResult$7(String str, SearchHeaderView searchHeaderView) {
        searchHeaderView.updateFuzzyResult(str);
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.NewSearchBar)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            ViewUtils.setVisibleOrGone(this.mFilterHeaderLayout, false);
        } else {
            searchHeaderView.showFuzzyTextOnly();
        }
    }

    public boolean allowEmptyView() {
        if (!isFilterHeaderVisible() || isAllFilterSelected() || Features.isEnabled(Features.SUPPORT_MULTI_KEYWORD_SEARCH)) {
            return true;
        }
        return false;
    }

    public void clearAll() {
        ViewUtils.setVisibleOrGone(this.mContentHeaderLayout, false);
        ViewUtils.setVisibleOrGone(this.mFilterHeaderLayout, false);
    }

    public void createAnalysisTipHeader(ISearchPicturesView iSearchPicturesView) {
        ViewUtils.removeAllViews(this.mAnalysisTipHeaderLayout);
        SearchAnalysisTipHeaderView searchAnalysisTipHeaderView = new SearchAnalysisTipHeaderView(iSearchPicturesView, this.mAnalysisTipHeaderLayout);
        this.mAnalysisTipHeader = searchAnalysisTipHeaderView;
        this.mAnalysisTipHeaderLayout.addView(searchAnalysisTipHeaderView.getView());
    }

    public void createContentHeader(ISearchPicturesView iSearchPicturesView, SearchHeaderView.HeaderType headerType) {
        ViewUtils.removeAllViews(this.mContentHeaderLayout);
        ViewGroup headerTargetView = iSearchPicturesView.getHeaderTargetView();
        if (headerTargetView == null) {
            Log.e(getClass().getSimpleName(), "createContentHeader skip for null target view");
            return;
        }
        if (headerType == SearchHeaderView.HeaderType.CREATURE) {
            if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
                SearchCreatureHeader2View searchCreatureHeader2View = new SearchCreatureHeader2View(headerTargetView, iSearchPicturesView);
                this.mContentHeader = searchCreatureHeader2View;
                searchCreatureHeader2View.setHeaderItemClickListener(new b(iSearchPicturesView));
            } else {
                SearchHeaderView searchHeaderView = this.mContentHeader;
                SearchCreatureHeaderView searchCreatureHeaderView = new SearchCreatureHeaderView(headerTargetView, iSearchPicturesView.getPresenter());
                this.mContentHeader = searchCreatureHeaderView;
                searchCreatureHeaderView.setHeaderItemClickListener(new b(iSearchPicturesView));
                if (searchHeaderView != null) {
                    this.mContentHeader.recover(searchHeaderView);
                }
            }
        } else if (headerType == SearchHeaderView.HeaderType.LOCATION) {
            SearchMapHeaderView searchMapHeaderView = new SearchMapHeaderView(headerTargetView, iSearchPicturesView.getChildFragmentManager(), iSearchPicturesView.getBlackboard());
            this.mContentHeader = searchMapHeaderView;
            searchMapHeaderView.setHeaderItemClickListener(new b(iSearchPicturesView));
        } else if (headerType == SearchHeaderView.HeaderType.CLUSTER_RESULT) {
            this.mContentHeader = new SearchClusterHeaderView(iSearchPicturesView, this.mSupportViewByDate);
        } else if (headerType == SearchHeaderView.HeaderType.RELATIONSHIP) {
            this.mContentHeader = new SearchRelationshipHeaderView(iSearchPicturesView, this.mSupportViewByDate);
        } else if (headerType == SearchHeaderView.HeaderType.SCREENSHOT) {
            SearchScreenShotHeaderView searchScreenShotHeaderView = new SearchScreenShotHeaderView(headerTargetView, new B5.b(iSearchPicturesView, 1));
            this.mContentHeader = searchScreenShotHeaderView;
            searchScreenShotHeaderView.setHeaderItemClickListener(new b(iSearchPicturesView));
            ViewMarginUtils.setHorizontalPadding(this.mMainLayout, this.mContentHeader.getContext().getResources().getDimensionPixelSize(R.dimen.search_header_horizontal_padding));
        } else if (headerType == SearchHeaderView.HeaderType.ASK_SCREENSHOT) {
            this.mContentHeader = new SearchAskScreenShotHeaderView(iSearchPicturesView.getHeaderTargetView());
        } else {
            this.mContentHeader = new SearchCountHeaderView(headerTargetView, this.mSupportViewByDate);
        }
        this.mContentHeader.setUiUpdateSupplier(iSearchPicturesView.getHeaderUpdateSupplierMap());
        this.mContentHeader.setItemCountLineVisibility(8);
        this.mContentHeaderLayout.addView(this.mContentHeader.getView());
        this.mContentHeaderLayout.setVisibility(0);
    }

    public void createFilterHeader(Blackboard blackboard) {
        SearchFiltersViewContainer searchFiltersViewContainer;
        SearchFuzzyHeaderView searchFuzzyHeaderView;
        Activity readActivity = BlackboardUtils.readActivity(blackboard);
        ViewUtils.removeAllViews(this.mFilterHeaderLayout);
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.NewSearchBar)) {
            SearchHeaderView searchHeaderView = this.mFilterHeader;
            if (searchHeaderView != null) {
                searchFuzzyHeaderView = SearchFuzzyHeaderView.cloneInstance(readActivity, (SearchFuzzyHeaderView) searchHeaderView);
            } else {
                searchFuzzyHeaderView = SearchFuzzyHeaderView.newInstance(readActivity, this.mFilterHeaderLayout, blackboard);
            }
            this.mFilterHeader = searchFuzzyHeaderView;
        } else {
            SearchHeaderView searchHeaderView2 = this.mFilterHeader;
            if (searchHeaderView2 != null) {
                searchFiltersViewContainer = SearchFiltersViewContainer.cloneInstance(readActivity, (SearchFiltersViewContainer) searchHeaderView2);
            } else {
                searchFiltersViewContainer = SearchFiltersViewContainer.newInstance(readActivity, this.mFilterHeaderLayout, blackboard, this.mFilterItemLayoutId);
            }
            this.mFilterHeader = searchFiltersViewContainer;
        }
        this.mFilterHeaderLayout.addView(this.mFilterHeader.getView());
        blackboard.postEvent(EventMessage.obtain(8516, this.mFilterHeader));
    }

    public void dataChanged() {
        Optional.ofNullable(this.mAnalysisTipHeader).ifPresent(new e(16));
    }

    public SearchHeaderView getContentHeader() {
        return this.mContentHeader;
    }

    public View getView() {
        return this.mMainLayout;
    }

    public void handleOrientationChange(int i2) {
        Optional.ofNullable(this.mFilterHeader).ifPresent(new C0489a(i2, 16));
        Optional.ofNullable(this.mContentHeader).ifPresent(new C0489a(i2, 17));
    }

    public void handleResolutionChanged() {
        Optional.ofNullable(this.mFilterHeader).ifPresent(new e(12));
        Optional.ofNullable(this.mContentHeader).ifPresent(new e(12));
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        SearchHeaderView searchHeaderView = this.mFilterHeader;
        if (searchHeaderView != null && searchHeaderView.isTouchedOnViewRectRange(motionEvent)) {
            return this.mFilterHeader.isVirtualCtrlKeyPressedAllowablePoint(motionEvent);
        }
        SearchHeaderView searchHeaderView2 = this.mContentHeader;
        if (searchHeaderView2 == null || !searchHeaderView2.isTouchedOnViewRectRange(motionEvent)) {
            return false;
        }
        return this.mContentHeader.isVirtualCtrlKeyPressedAllowablePoint(motionEvent);
    }

    public boolean onBackPressed() {
        return ((Boolean) Optional.ofNullable(this.mContentHeader).map(new a(16)).orElse(Boolean.FALSE)).booleanValue();
    }

    public void onDestroyView() {
        Optional.ofNullable(this.mFilterHeader).ifPresent(new e(18));
        Optional.ofNullable(this.mContentHeader).ifPresent(new e(18));
    }

    public void onPause() {
        Optional.ofNullable(this.mContentHeader).ifPresent(new e(13));
    }

    public void onResume() {
        Optional.ofNullable(this.mContentHeader).ifPresent(new e(15));
    }

    public void recycle() {
        Optional.ofNullable(this.mFilterHeader).ifPresent(new e(17));
        Optional.ofNullable(this.mContentHeader).ifPresent(new e(17));
    }

    public void resetViewBy() {
        if (this.mSupportViewByDate) {
            Optional.ofNullable(this.mContentHeader).ifPresent(new e(14));
        }
    }

    public void setEnabled(boolean z, boolean z3) {
        Optional.ofNullable(this.mFilterHeader).ifPresent(new l4.b(z, 12));
        Optional.ofNullable(this.mContentHeader).ifPresent(new l4.b(z3, 13));
    }

    public void showCountHeaderOnly(boolean z) {
        Optional.ofNullable(this.mContentHeader).ifPresent(new l4.b(z, 11));
    }

    public SearchPicturesMultipleHeaderContainer supportViewByDate() {
        this.mSupportViewByDate = PreferenceFeatures.OneUi7x.DISABLE_TIMELINE_ON_KEYWORD;
        return this;
    }

    public void updateBackground(boolean z) {
        int i2;
        ViewGroup viewGroup = this.mMainLayout;
        if (z) {
            i2 = R.color.default_fw_background;
        } else {
            i2 = R.color.default_background;
        }
        viewGroup.setBackgroundResource(i2);
    }

    public void updateCountHeader(boolean z) {
        ViewUtils.setVisibleOrGone(this.mContentHeaderLayout, z);
    }

    public void updateFuzzyResult(String str) {
        Optional.ofNullable(this.mFilterHeader).ifPresent(new C0491c(19, this, str));
    }

    public void updateItemCount(int i2) {
        Optional.ofNullable(this.mContentHeader).ifPresent(new C0489a(i2, 15));
    }
}
