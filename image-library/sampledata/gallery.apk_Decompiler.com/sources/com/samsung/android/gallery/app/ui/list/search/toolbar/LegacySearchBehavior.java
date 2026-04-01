package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.graphics.drawable.Drawable;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LegacySearchBehavior extends BaseSearchToolbarBehavior {
    private final SearchToolbarCommonBehavior mCommonBehavior;
    protected final boolean mIsPickMode;
    protected final IBaseListView mView;

    public LegacySearchBehavior(IBaseListView iBaseListView, boolean z) {
        super(iBaseListView);
        this.mView = iBaseListView;
        this.mIsPickMode = z;
        this.mCommonBehavior = new SearchToolbarCommonBehavior(iBaseListView, z);
    }

    public boolean handleInternalEvent(SearchToolbar searchToolbar, SearchToolbarEvent searchToolbarEvent) {
        return false;
    }

    public void initMenu(SearchToolbar searchToolbar) {
        boolean z = false;
        if (this.mIsPickMode) {
            GalleryToolbar toolbar = this.mView.getToolbar();
            if (toolbar != null) {
                toolbar.setTitle(PickerUtil.getUsageTitle(this.mView.getBlackboard()));
                toolbar.setSubtitle((CharSequence) null);
                toolbar.setNavigationIcon((Drawable) null);
            }
            searchToolbar.setMoreButtonVisibility(false);
            return;
        }
        searchToolbar.createPopupMenu();
        IBaseListView iBaseListView = this.mView;
        if (searchToolbar.getVisibility() != 0) {
            z = true;
        }
        iBaseListView.setOptionsMenu(z);
        searchToolbar.setMoreButtonVisibility(true);
    }

    public void onClickCloseButton(SearchToolbar searchToolbar) {
        this.mCommonBehavior.onClickCloseButton(searchToolbar);
    }

    public void onFocusChanged(SearchToolbar searchToolbar, boolean z) {
        this.mCommonBehavior.onFocusChanged(searchToolbar, z);
    }

    public void updateToolbar(SearchToolbar searchToolbar, String str) {
        Optional.ofNullable(this.mView.getToolbar()).ifPresent(new d(2));
        searchToolbar.setMoreButtonVisibility(true);
    }
}
