package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ListViewBottomPaddingControl {
    private View mFilterView;
    private final boolean mIsPickMode;
    private ValueAnimator mRemovePaddingAnim;
    private SearchToolbar mSearchToolbar;
    private final IBaseListView mView;

    public ListViewBottomPaddingControl(IBaseListView iBaseListView, boolean z) {
        this.mView = iBaseListView;
        this.mIsPickMode = z;
    }

    private void cancelRemovePaddingAnim() {
        ValueAnimator valueAnimator = this.mRemovePaddingAnim;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mRemovePaddingAnim = null;
        }
    }

    private int getSearchToolbarHeight() {
        if (ViewUtils.isShown(this.mSearchToolbar)) {
            return this.mSearchToolbar.getGradientAreaHeight();
        }
        return 0;
    }

    private void setBottomPadding(int i2) {
        GalleryListView listView = this.mView.getListView();
        ViewMarginUtils.setBottomPadding(listView, listView.getResources().getDimensionPixelOffset(R.dimen.list_view_default_bottom_padding) + WindowUtils.getSystemInsetsBottom(listView.getRootWindowInsets()) + this.mView.getBottomTabHeight() + i2);
        if (this.mIsPickMode) {
            listView.updateGoToTopBottomPadding((float) listView.getResources().getDimensionPixelOffset(R.dimen.gototop_adjust_bottom_padding));
        }
        listView.setClipToPadding(false);
    }

    public void onFirstDraw(SearchToolbar searchToolbar) {
        this.mSearchToolbar = searchToolbar;
        if (!this.mView.onBottomSearchToolbarChanged(searchToolbar)) {
            setBottomPadding(getSearchToolbarHeight());
        }
    }

    public void onFirstDrawWithFilter(View view) {
        if (ViewUtils.isShown(this.mSearchToolbar)) {
            this.mFilterView = view;
            if (!this.mView.onBottomSearchToolbarChanged(view)) {
                int searchToolbarHeight = getSearchToolbarHeight();
                if (searchToolbarHeight == 0 && view != null) {
                    searchToolbarHeight = ((ViewGroup) view.getParent()).getHeight();
                }
                setBottomPadding(searchToolbarHeight);
            }
        }
    }

    public void removePaddingWithAnim() {
        final GalleryListView listView = this.mView.getListView();
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{listView.getPaddingBottom(), listView.getResources().getDimensionPixelOffset(R.dimen.list_view_default_bottom_padding) + this.mView.getBottomBarHeight() + WindowUtils.getSystemInsetsBottom(listView.getRootWindowInsets())});
        this.mRemovePaddingAnim = ofInt;
        ofInt.setDuration((long) AppResources.getInteger(R.integer.bottom_bar_slide_up_duration));
        this.mRemovePaddingAnim.setInterpolator(new DecelerateInterpolator());
        this.mRemovePaddingAnim.addUpdateListener(new f(listView));
        this.mRemovePaddingAnim.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                listView.setClipToPadding(false);
            }
        });
        this.mRemovePaddingAnim.start();
    }

    public void updateBottomPadding(boolean z) {
        if (z) {
            cancelRemovePaddingAnim();
            View view = this.mFilterView;
            if (view != null) {
                onFirstDrawWithFilter(view);
            } else {
                setBottomPadding(getSearchToolbarHeight());
            }
        } else {
            setBottomPadding(0);
        }
    }
}
