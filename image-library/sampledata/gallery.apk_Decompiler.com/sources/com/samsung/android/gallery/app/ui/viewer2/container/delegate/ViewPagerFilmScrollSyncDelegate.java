package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ForceSwipeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripLayoutManager;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.OnFilmStripItemClickListener;
import com.sec.android.gallery3d.R;
import k7.s;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPagerFilmScrollSyncDelegate extends AbsVuDelegate<IVuContainerView> implements OnFilmStripItemClickListener {
    private boolean mEnableResetPosition = false;
    private FilmStripDelegate mFilmStripDelegate;
    /* access modifiers changed from: private */
    public FilmStripLayoutManager mFilmStripLayoutManager;
    private final RecyclerView.OnScrollListener mFilmStripScrollListener = new RecyclerView.OnScrollListener() {
        final Handler dragHandler = new Handler(Looper.getMainLooper());

        /* access modifiers changed from: private */
        /* renamed from: setDragState */
        public void lambda$onScrollStateChanged$0(int i2) {
            if (i2 == 0) {
                ViewPagerFilmScrollSyncDelegate.this.mIsFilmDragging = false;
            }
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            this.dragHandler.removeCallbacksAndMessages((Object) null);
            if (ViewPagerFilmScrollSyncDelegate.this.mIsFilmDragging) {
                this.dragHandler.postDelayed(new c(this, i2), 50);
                return;
            }
            ViewPagerFilmScrollSyncDelegate viewPagerFilmScrollSyncDelegate = ViewPagerFilmScrollSyncDelegate.this;
            boolean z = true;
            if (i2 != 1) {
                z = false;
            }
            viewPagerFilmScrollSyncDelegate.mIsFilmDragging = z;
        }

        public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
            int bindingAdapterPosition;
            if (ViewPagerFilmScrollSyncDelegate.this.mViewPager.getScrollState() == 0 && !ViewPagerFilmScrollSyncDelegate.this.mFilmStripLayoutManager.isSeekerMode()) {
                FilmStripViewHolder centerViewHolder = ViewPagerFilmScrollSyncDelegate.this.mFilmStripView.getCenterViewHolder();
                if (ViewPagerFilmScrollSyncDelegate.this.mIsFilmDragging && centerViewHolder != null && ViewPagerFilmScrollSyncDelegate.this.mViewPager.getCurrentItem() != (bindingAdapterPosition = centerViewHolder.getBindingAdapterPosition())) {
                    ViewPagerFilmScrollSyncDelegate.this.mViewPager.setCurrentItem(bindingAdapterPosition, false);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public FilmStripView mFilmStripView;
    /* access modifiers changed from: private */
    public boolean mIsFilmDragging = false;
    /* access modifiers changed from: private */
    public ViewPager2 mViewPager;

    public ViewPagerFilmScrollSyncDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void bindFilmStripView(View view) {
        FilmStripView filmStripView = (FilmStripView) view.findViewById(R.id.film_strip_view);
        this.mFilmStripView = filmStripView;
        filmStripView.setEnableSnapHelper(true);
        this.mFilmStripView.addOnScrollListener(this.mFilmStripScrollListener);
        this.mFilmStripLayoutManager = (FilmStripLayoutManager) this.mFilmStripView.getLayoutManager();
    }

    private boolean isForceSwipe() {
        ForceSwipeDelegate forceSwipeDelegate = (ForceSwipeDelegate) getDelegate(ForceSwipeDelegate.class);
        if (forceSwipeDelegate == null || !forceSwipeDelegate.isWorking()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        bindFilmStripView(objArr[0]);
    }

    /* access modifiers changed from: private */
    public void onPageScrollDone(Object... objArr) {
        this.mFilmStripLayoutManager.setViewHolderPosition((FilmStripViewHolder<?>) null, 0.0f);
        if (!isForceSwipe()) {
            int currentItem = this.mViewPager.getCurrentItem();
            FilmStripViewHolder centerViewHolder = this.mFilmStripView.getCenterViewHolder();
            if (centerViewHolder != null && centerViewHolder.getViewHolderPosition() != currentItem) {
                Log.d(this.TAG, "FilmStripView scroll by page changed");
                this.mFilmStripView.scrollToPosition(currentItem);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onPageScrolled(Object... objArr) {
        int intValue = objArr[0].intValue();
        float floatValue = objArr[1].floatValue();
        if (this.mFilmStripView == null) {
            String str = this.TAG;
            Log.w(str, "VP[" + intValue + "] onPageScrolled skip. null view");
        } else if (this.mViewPager.getScrollState() != 0) {
            this.mFilmStripView.scrollOffset(intValue, floatValue);
        }
    }

    private void restoreCenterMotionPhotoVh() {
        MediaItem mediaItem;
        FilmStripViewHolder centerViewHolder = this.mFilmStripView.getCenterViewHolder();
        if (centerViewHolder != null && centerViewHolder.isExpanded() && (mediaItem = centerViewHolder.getMediaItem()) != null && mediaItem.isMotionPhoto()) {
            centerViewHolder.restoreFrameView(this.mFilmStripView, false);
        }
    }

    public void onBindView(View view) {
        this.mViewPager = ((ContainerModel) this.mModel).getViewPager();
        bindFilmStripView(view);
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        restoreCenterMotionPhotoVh();
        this.mFilmStripView.scrollToPosition(this.mViewPager.getCurrentItem());
    }

    public void onCreate(Bundle bundle) {
        this.mFilmStripDelegate = (FilmStripDelegate) getDelegate(FilmStripDelegate.class);
    }

    public void onDestroy() {
        this.mFilmStripView.removeOnScrollListener(this.mFilmStripScrollListener);
        FilmStripDelegate filmStripDelegate = this.mFilmStripDelegate;
        if (filmStripDelegate != null) {
            filmStripDelegate.removeFilmStripItemListener(this);
        }
        this.mFilmStripView = null;
    }

    public void onItemClick(int i2, FilmStripViewHolder filmStripViewHolder) {
        if (!this.mViewPager.isFakeDragging()) {
            if (!this.mFilmStripDelegate.isFilmStripScrollStateIdle() || !this.mFilmStripDelegate.isViewPagerScrollStateIdle()) {
                String str = this.TAG;
                Log.d(str, "mFilmStripDelegate.isFilmStripScrollStateIdle " + this.mFilmStripDelegate.isFilmStripScrollStateIdle());
                String str2 = this.TAG;
                Log.d(str2, "mFilmStripDelegate.isViewPagerScrollStateIdle " + this.mFilmStripDelegate.isViewPagerScrollStateIdle());
                return;
            }
            this.mViewPager.setCurrentItem(i2, false);
            if (!filmStripViewHolder.isCenter()) {
                this.mFilmStripView.smoothScrollToPosition(i2);
            }
            AnalyticsLogger.getInstance().postLog(((IVuContainerView) this.mView).getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_FILM_STRIP.toString(), AnalyticsDetail.FILM_STRIP_TAP.toString());
        }
    }

    public void onPause() {
        this.mEnableResetPosition = true;
    }

    public void onResume() {
        if (this.mEnableResetPosition) {
            this.mFilmStripView.scrollToPosition(this.mViewPager.getCurrentItem());
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        FilmStripDelegate filmStripDelegate = this.mFilmStripDelegate;
        if (filmStripDelegate != null) {
            filmStripDelegate.addFilmStripItemListener(this);
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.FILM_STRIP_LAYOUT_REPLACED, new s(this, 0));
        actionInvoker.add(ViewerAction.VIEW_PAGER_PAGE_SCROLLED, new s(this, 1));
        actionInvoker.add(ViewerAction.VIEW_PAGER_PAGE_SCROLL_DONE, new s(this, 2));
    }
}
