package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions;
import com.samsung.android.gallery.widget.search.searchbar.autocomplete.FilterAutoCompleteView;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LegacySearchPicturesBehavior extends BaseSearchToolbarBehavior {
    private final SearchToolbarCommonBehavior mCommonBehavior;
    private final SearchFiltersDelegate mFiltersDelegate;
    private final boolean mIsPickMode;
    private SearchSelectedFiltersDelegate mSelectedFiltersDelegate;
    private final IBaseListView mView;

    public LegacySearchPicturesBehavior(IBaseListView iBaseListView, boolean z) {
        super(iBaseListView);
        this.mView = iBaseListView;
        this.mIsPickMode = z;
        this.mFiltersDelegate = new SearchFiltersDelegate(iBaseListView, true).controlVisibilityWithRootView();
        this.mCommonBehavior = new SearchToolbarCommonBehavior(iBaseListView, z);
    }

    private void hideSearchToolbarOnEnterSelectionMode(SearchToolbar searchToolbar) {
        if (!this.mIsPickMode) {
            searchToolbar.setVisibility(8);
        }
    }

    private void onSelectedFilterViewInflated(SearchSelectedFiltersView searchSelectedFiltersView, View view) {
        this.mSelectedFiltersDelegate = new SearchSelectedFiltersDelegate(this.mView, searchSelectedFiltersView, view);
    }

    private void updateToolbarTitle(SearchToolbar searchToolbar, String str) {
        GalleryToolbar toolbar;
        searchToolbar.setQuery(str, false);
        if (!this.mIsPickMode && (toolbar = this.mView.getToolbar()) != null) {
            toolbar.setTitle((CharSequence) null);
            toolbar.setSubtitle((CharSequence) null);
        }
    }

    public boolean handleEvent(SearchToolbar searchToolbar, EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 8013) {
            this.mFiltersDelegate.resetOnlyThemView();
        } else if (i2 != 8513) {
            if (i2 == 8516) {
                this.mFiltersDelegate.setView(eventMessage.obj);
                this.mFiltersDelegate.updateView();
            } else if (i2 == 8523) {
                SearchSelectedFiltersDelegate searchSelectedFiltersDelegate = this.mSelectedFiltersDelegate;
                if (searchSelectedFiltersDelegate != null) {
                    Object[] objArr = (Object[]) eventMessage.obj;
                    searchSelectedFiltersDelegate.addMainFilter((Bitmap) objArr[0], (String) objArr[1]);
                }
            } else if (i2 != 8016 && i2 != 8017) {
                return false;
            } else {
                if (this.mView.isViewResumed()) {
                    searchToolbar.updateFilterView((FilterResultsEntity) eventMessage.obj, ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "sub"));
                }
            }
        } else if (this.mView.isViewResumed()) {
            this.mFiltersDelegate.updateData(eventMessage.obj);
        }
        return true;
    }

    public boolean handleInternalEvent(SearchToolbar searchToolbar, SearchToolbarEvent searchToolbarEvent) {
        int i2 = searchToolbarEvent.what;
        if (i2 == 3) {
            updateToolbarTitle(searchToolbar, (String) searchToolbarEvent.obj);
            return true;
        }
        boolean z = false;
        if (i2 == 13) {
            IBaseListView iBaseListView = this.mView;
            if (iBaseListView.isSelectionMode() || searchToolbar.getVisibility() != 0) {
                z = true;
            }
            iBaseListView.setOptionsMenu(z);
            return true;
        } else if (i2 == 8) {
            hideSearchToolbarOnEnterSelectionMode(searchToolbar);
            return true;
        } else if (i2 == 20) {
            SearchSelectedFiltersDelegate searchSelectedFiltersDelegate = this.mSelectedFiltersDelegate;
            if (searchSelectedFiltersDelegate != null) {
                searchSelectedFiltersDelegate.replaceMainFilter((String) searchToolbarEvent.obj);
            }
            return true;
        } else if (i2 != 21) {
            return false;
        } else {
            SearchSelectedFiltersDelegate searchSelectedFiltersDelegate2 = this.mSelectedFiltersDelegate;
            if (searchSelectedFiltersDelegate2 != null) {
                searchSelectedFiltersDelegate2.updateFadingEdge();
            }
            return true;
        }
    }

    public void initMenu(SearchToolbar searchToolbar) {
        searchToolbar.createPopupMenu();
    }

    public void onAttached(SearchToolbar searchToolbar, SearchToolbarOptions searchToolbarOptions) {
        SearchSelectedFiltersView searchSelectedFiltersView;
        if (searchToolbarOptions.isEnableFiltersView()) {
            if (searchToolbarOptions.supportMultiKeywordSearch()) {
                searchSelectedFiltersView = searchToolbar.inflateSelectedFiltersEditableView(new FilterAutoCompleteView(this.mView.getBlackboard()));
            } else {
                searchSelectedFiltersView = searchToolbar.inflateSelectedFiltersView(this.mView.getBlackboard());
            }
            onSelectedFilterViewInflated(searchSelectedFiltersView, searchToolbar.getSearchTextView());
        }
    }

    public void onClickCloseButton(SearchToolbar searchToolbar) {
        this.mCommonBehavior.onClickCloseButton(searchToolbar);
    }

    public void onDestroy(SearchToolbar searchToolbar) {
        SearchSelectedFiltersDelegate searchSelectedFiltersDelegate = this.mSelectedFiltersDelegate;
        if (searchSelectedFiltersDelegate != null) {
            searchSelectedFiltersDelegate.onDestroy();
        }
    }

    public void onFocusChanged(SearchToolbar searchToolbar, boolean z) {
        this.mCommonBehavior.onFocusChanged(searchToolbar, z);
    }

    public void onHiddenChange(SearchToolbar searchToolbar, boolean z) {
        if (!z) {
            searchToolbar.setVisibility(0);
        }
    }

    public void updateToolbar(SearchToolbar searchToolbar, String str) {
        int i2;
        updateToolbarTitle(searchToolbar, str);
        GalleryToolbar toolbar = this.mView.getToolbar();
        if (toolbar != null) {
            toolbar.setNavigationIcon((Drawable) null);
            String string = AppResources.getString(R.string.search);
            if (this.mView.isSelectionMode()) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            searchToolbar.setVisibility(i2);
            GalleryAppBarLayout appbarLayout = this.mView.getAppbarLayout();
            if (appbarLayout != null) {
                appbarLayout.setTitle((CharSequence) string);
            }
        }
    }
}
