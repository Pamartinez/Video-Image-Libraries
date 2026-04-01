package com.samsung.android.gallery.app.ui.list.search.toolbar;

import J5.c;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.search.root.FilterOnlyThem;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.filter.SearchFiltersViewContainer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchFiltersDelegate {
    private boolean mControlVisibilityWithRootView;
    private FilterOnlyThem mFilterOnlyThem;
    private FilterResultsEntry mFilterResultsEntry;
    private SearchFiltersViewContainer mFilterView;
    private final boolean mHasFuzzyTextView;
    private final IBaseListView mView;

    public SearchFiltersDelegate(IBaseListView iBaseListView, boolean z) {
        this.mView = iBaseListView;
        this.mHasFuzzyTextView = z;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ FilterResultsEntry lambda$updateData$0() {
        return this.mFilterResultsEntry;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateView$1(boolean z) {
        if (this.mFilterView != null) {
            String argValue = ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "fuzzyKeyword");
            setVisibility(z);
            if (this.mHasFuzzyTextView) {
                this.mFilterView.setRelatedKeywords(this.mFilterResultsEntry, argValue, this.mFilterOnlyThem, this.mView.getLocationKey());
                return;
            }
            this.mView.getBlackboard().postEvent(EventMessage.obtain(8518, new Object[]{argValue, Boolean.FALSE}));
            this.mFilterView.setRelatedKeywords(this.mFilterResultsEntry, (String) null, this.mFilterOnlyThem, this.mView.getLocationKey());
        }
    }

    private void postResultForKeywordSearch() {
        if (LocationKey.isSearchKeyword(this.mView.getLocationKey())) {
            this.mView.getBlackboard().postEvent(EventMessage.obtain(1065, this.mFilterResultsEntry));
        }
    }

    public SearchFiltersDelegate controlVisibilityWithRootView() {
        this.mControlVisibilityWithRootView = true;
        return this;
    }

    public View getView() {
        SearchFiltersViewContainer searchFiltersViewContainer = this.mFilterView;
        if (searchFiltersViewContainer != null) {
            return searchFiltersViewContainer.getView();
        }
        return null;
    }

    public boolean hasView() {
        if (this.mFilterView != null) {
            return true;
        }
        return false;
    }

    public void onDestroy() {
        this.mView.getBlackboard().erase("data://user/SearchFilterResultsEntry");
    }

    public void resetOnlyThemView() {
        SearchFiltersViewContainer searchFiltersViewContainer = this.mFilterView;
        if (searchFiltersViewContainer != null) {
            searchFiltersViewContainer.resetOnlyThemView();
        }
    }

    public void setView(Object obj) {
        this.mFilterView = (SearchFiltersViewContainer) obj;
    }

    public void setVisibility(boolean z) {
        SearchFiltersViewContainer searchFiltersViewContainer = this.mFilterView;
        if (searchFiltersViewContainer != null) {
            int i2 = 8;
            if (this.mControlVisibilityWithRootView) {
                if (searchFiltersViewContainer.getRootView() != null) {
                    ViewGroup rootView = this.mFilterView.getRootView();
                    if (z) {
                        i2 = 0;
                    }
                    rootView.setVisibility(i2);
                }
            } else if (searchFiltersViewContainer.getView() != null) {
                View view = this.mFilterView.getView();
                if (z) {
                    i2 = 0;
                }
                view.setVisibility(i2);
            }
        }
    }

    public boolean updateData(Object obj) {
        return updateData(obj, true);
    }

    public void updateView() {
        updateView(true);
    }

    public boolean updateData(Object obj, boolean z) {
        Object[] objArr = (Object[]) obj;
        FilterResultsEntry filterResultsEntry = (FilterResultsEntry) objArr[0];
        Object obj2 = objArr[1];
        FilterOnlyThem filterOnlyThem = obj2 != null ? (FilterOnlyThem) obj2 : null;
        StringBuilder sb2 = new StringBuilder("onFilterResultsPublished");
        sb2.append(Logger.v(Integer.valueOf(filterResultsEntry.getCount()), "FOT=".concat(filterOnlyThem == null ? "0" : "1")));
        Log.s("SearchFilterDelegate", sb2.toString());
        this.mFilterResultsEntry = filterResultsEntry;
        this.mFilterOnlyThem = filterOnlyThem;
        updateView(z);
        postResultForKeywordSearch();
        this.mView.getBlackboard().publish("data://user/SearchFilterResultsEntry", new c(0, this));
        return !this.mFilterResultsEntry.isEmpty();
    }

    public void updateView(boolean z) {
        ThreadUtil.runOnUiThread(new B4.c((Object) this, z, 12));
    }
}
