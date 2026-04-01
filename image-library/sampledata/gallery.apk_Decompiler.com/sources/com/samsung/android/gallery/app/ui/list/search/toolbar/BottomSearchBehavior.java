package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BottomSearchBehavior extends BaseSearchToolbarBehavior {
    final String TAG = getClass().getSimpleName();
    private FloatingRecommendationLauncher mFloatingRecommendationLauncher;
    protected final boolean mIsPickMode;
    protected final IBaseListView mView;

    public BottomSearchBehavior(IBaseListView iBaseListView, boolean z) {
        super(iBaseListView);
        this.mView = iBaseListView;
        this.mIsPickMode = z;
        if (!z) {
            this.mFloatingRecommendationLauncher = new FloatingRecommendationLauncher(iBaseListView);
        }
    }

    private void applyInsets(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        int iMEInsetsBottom = WindowUtils.getIMEInsetsBottom(windowInsets);
        int i2 = 0;
        if (iMEInsetsBottom > 0) {
            i2 = Math.max(0, iMEInsetsBottom - getBottomTabHeight());
        }
        ViewMarginUtils.setBottomMargin(searchToolbar.getView(), WindowUtils.getSystemInsetsBottom(windowInsets) + getBottomTabHeight() + i2);
    }

    private int getBottomTabHeight() {
        return this.mView.getBottomTabHeight();
    }

    public boolean handleInternalEvent(SearchToolbar searchToolbar, SearchToolbarEvent searchToolbarEvent) {
        int i2 = searchToolbarEvent.what;
        if (i2 == 17) {
            View view = (View) searchToolbarEvent.obj;
            if (ViewUtils.isVisible(view)) {
                ViewMarginUtils.setBottomMargin(view.findViewById(R.id.no_item_view_container), searchToolbar.getGradientAreaHeight());
            }
            return true;
        } else if (i2 != 22) {
            return false;
        } else {
            ViewMarginUtils.setHorizontalPadding(searchToolbar.getView(), ((Integer) searchToolbarEvent.obj).intValue());
            return true;
        }
    }

    public void onApplyInsets(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        applyInsets(searchToolbar, windowInsets);
    }

    public void onAttached(SearchToolbar searchToolbar, SearchToolbarOptions searchToolbarOptions) {
        FloatingRecommendationLauncher floatingRecommendationLauncher = this.mFloatingRecommendationLauncher;
        if (floatingRecommendationLauncher != null) {
            floatingRecommendationLauncher.setTouchListener(searchToolbar);
        }
    }

    public void onHiddenChange(SearchToolbar searchToolbar, boolean z) {
        int i2;
        if (z) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        searchToolbar.setVisibility(i2);
    }

    public void onInsetsProgress(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        applyInsets(searchToolbar, windowInsets);
    }

    public void onPreQuery(SearchToolbar searchToolbar) {
        searchToolbar.setQuery("", false);
    }

    public void updateToolbar(SearchToolbar searchToolbar, String str) {
        GalleryToolbar toolbar = this.mView.getToolbar();
        if (toolbar != null) {
            toolbar.setTitle((CharSequence) null);
            if (PreferenceFeatures.OneUi8x.COLLECTION_TAB || this.mIsPickMode) {
                toolbar.setNavigationIcon((Drawable) null);
            } else {
                this.mView.getPresenter().setNavigationUpButton(toolbar);
            }
        }
    }
}
