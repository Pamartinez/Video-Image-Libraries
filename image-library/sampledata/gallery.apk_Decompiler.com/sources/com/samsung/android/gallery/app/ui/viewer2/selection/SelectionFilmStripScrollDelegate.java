package com.samsung.android.gallery.app.ui.viewer2.selection;

import A4.C0368c;
import android.os.Handler;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectionFilmStripScrollDelegate extends RecyclerView.OnScrollListener {
    private final Handler dragHandler = new Handler();
    private final FilmStripView mFilmStripView;
    private boolean mIsFilmDragging = false;
    private final ViewPager2 mViewPager;

    public SelectionFilmStripScrollDelegate(ViewPager2 viewPager2, FilmStripView filmStripView) {
        this.mViewPager = viewPager2;
        this.mFilmStripView = filmStripView;
    }

    /* access modifiers changed from: private */
    /* renamed from: setDragState */
    public void lambda$onScrollStateChanged$0(int i2) {
        if (i2 == 0) {
            this.mIsFilmDragging = false;
        }
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
        this.dragHandler.removeCallbacksAndMessages((Object) null);
        if (this.mIsFilmDragging) {
            this.dragHandler.postDelayed(new C0368c(this, i2, 18), 50);
            return;
        }
        boolean z = true;
        if (i2 != 1) {
            z = false;
        }
        this.mIsFilmDragging = z;
    }

    public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
        int bindingAdapterPosition;
        if (this.mViewPager.getScrollState() == 0) {
            FilmStripViewHolder centerViewHolder = this.mFilmStripView.getCenterViewHolder();
            if (this.mIsFilmDragging && centerViewHolder != null && this.mViewPager.getCurrentItem() != (bindingAdapterPosition = centerViewHolder.getBindingAdapterPosition())) {
                this.mViewPager.setCurrentItem(bindingAdapterPosition, false);
            }
        }
    }
}
