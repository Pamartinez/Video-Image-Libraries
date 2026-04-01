package com.samsung.android.gallery.app.ui.viewer2.selection;

import V3.b;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripLayoutManager;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectionPageChangeDelegate extends ViewPager2.OnPageChangeCallback {
    private FilmStripLayoutManager mFilmStripLayoutManager;
    private FilmStripView mFilmStripView;
    private final Runnable mOnPageIdle = new b(3, this);
    private int mPosition = -1;
    private int mState;
    private final ISelectionView mView;
    private final ViewPager2 mViewPager;

    public SelectionPageChangeDelegate(ISelectionView iSelectionView, ViewPager2 viewPager2, FilmStripView filmStripView) {
        this.mView = iSelectionView;
        this.mViewPager = viewPager2;
        setFilmStripView(filmStripView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPageIdle$0(SelectionViewAdapter selectionViewAdapter, SelectionViewHolder selectionViewHolder) {
        selectionViewAdapter.loadOnIdle(selectionViewHolder, this.mPosition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPageIdle$1(SelectionViewAdapter selectionViewAdapter, SelectionViewHolder selectionViewHolder) {
        selectionViewAdapter.loadOnIdle(selectionViewHolder, this.mPosition + 1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPageIdle$2(SelectionViewAdapter selectionViewAdapter, SelectionViewHolder selectionViewHolder) {
        selectionViewAdapter.loadOnIdle(selectionViewHolder, this.mPosition - 1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPageIdle$3(SelectionViewAdapter selectionViewAdapter) {
        Optional.ofNullable(this.mView.findViewHolder(this.mPosition)).ifPresent(new b(0, this, selectionViewAdapter));
        Optional.ofNullable(this.mView.findViewHolder(this.mPosition + 1)).ifPresent(new b(1, this, selectionViewAdapter));
        Optional.ofNullable(this.mView.findViewHolder(this.mPosition - 1)).ifPresent(new b(2, this, selectionViewAdapter));
    }

    /* access modifiers changed from: private */
    public void onPageIdle() {
        if (this.mFilmStripView.getScrollState() != 0) {
            this.mViewPager.postDelayed(this.mOnPageIdle, 100);
        } else if (this.mState == 0) {
            Optional.ofNullable((SelectionViewAdapter) this.mViewPager.getAdapter()).ifPresent(new a(0, this));
        }
    }

    public boolean isScrolling() {
        if (this.mState > 0) {
            return true;
        }
        return false;
    }

    public void onPageScrollStateChanged(int i2) {
        this.mViewPager.removeCallbacks(this.mOnPageIdle);
        if (i2 == 0) {
            this.mFilmStripLayoutManager.setViewHolderPosition((FilmStripViewHolder<?>) null, 0.0f);
            this.mViewPager.postDelayed(this.mOnPageIdle, 10);
        }
        this.mState = i2;
    }

    public void onPageScrolled(int i2, float f, int i7) {
        if (isScrolling()) {
            this.mFilmStripView.scrollOffset(i2, f);
        }
    }

    public void onPageSelected(int i2) {
        if (!isScrolling()) {
            this.mViewPager.removeCallbacks(this.mOnPageIdle);
            this.mViewPager.postDelayed(this.mOnPageIdle, 100);
        }
        this.mPosition = i2;
        this.mView.setLastFocusedPosition(i2);
    }

    public void setFilmStripView(FilmStripView filmStripView) {
        this.mFilmStripView = filmStripView;
        this.mFilmStripLayoutManager = (FilmStripLayoutManager) filmStripView.getLayoutManager();
    }
}
