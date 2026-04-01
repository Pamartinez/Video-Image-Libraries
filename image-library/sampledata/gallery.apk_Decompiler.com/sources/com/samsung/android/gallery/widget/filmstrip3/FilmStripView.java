package com.samsung.android.gallery.widget.filmstrip3;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripSnapHelper;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import i.C0212a;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripView extends RecyclerView {
    private final boolean IS_RTL = Features.isEnabled(Features.IS_RTL);
    private int mCenterX = 0;
    private int mItemGap;
    protected final FilmStripLayoutManager mLayoutManager = new FilmStripLayoutManager(this, 0, false);
    private final RecyclerView.OnItemTouchListener mOnItemTouchListener = new RecyclerView.OnItemTouchListener() {
        public boolean isCenterViewTouch(int i2) {
            View view;
            FilmStripViewHolder centerViewHolder = FilmStripView.this.getCenterViewHolder();
            if (centerViewHolder == null || !centerViewHolder.isExpanded() || (view = centerViewHolder.itemView) == null) {
                return false;
            }
            float f = (float) i2;
            if (f <= view.getX() || f >= centerViewHolder.itemView.getX() + ((float) centerViewHolder.itemView.getWidth())) {
                return false;
            }
            return true;
        }

        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0) {
                return false;
            }
            FilmStripView.this.setSeekerMode(isCenterViewTouch((int) motionEvent.getX()));
            FilmStripView.this.stopScroll();
            return false;
        }

        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        }
    };
    private int mPrevWidth = -1;
    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        final Handler reset = new Handler();

        /* access modifiers changed from: private */
        /* renamed from: finishSmoothScroll */
        public void lambda$onScrollStateChanged$0() {
            if (FilmStripView.this.getScrollState() == 0) {
                FilmStripView.this.mLayoutManager.setSmoothScroll(-1);
                FilmStripView.this.adjustEdgePos();
                return;
            }
            this.reset.postDelayed(new a(this, 1), 50);
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (i2 == 0) {
                this.reset.removeCallbacksAndMessages((Object) null);
                this.reset.postDelayed(new a(this, 0), 50);
            } else if (i2 == 1) {
                FilmStripView.this.mLayoutManager.setSmoothScroll(-1);
            } else if (i2 == 2) {
                this.reset.removeCallbacksAndMessages((Object) null);
            }
        }
    };
    private FilmStripSnapHelper mSnapHelper;
    private int mVideIndexWidth;

    public FilmStripView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    /* access modifiers changed from: private */
    public void adjustEdgePos() {
        FilmStripViewHolder centerViewHolder = getCenterViewHolder();
        if (centerViewHolder != null && !centerViewHolder.isExpanded()) {
            int viewHolderPosition = centerViewHolder.getViewHolderPosition();
            if ((viewHolderPosition == 0 || viewHolderPosition == getAdapter().getItemCount() - 1) && centerViewHolder.itemView.getWidth() != centerViewHolder.getMaxWidth()) {
                scrollToPosition(centerViewHolder.getViewHolderPosition());
            }
        }
    }

    private boolean checkViewHolder(FilmStripViewHolder<?> filmStripViewHolder, FilmStripViewHolder<?> filmStripViewHolder2) {
        boolean z;
        boolean z3 = false;
        if (filmStripViewHolder != null && filmStripViewHolder2 != null) {
            return false;
        }
        if (filmStripViewHolder == null) {
            z = true;
        } else {
            z = false;
        }
        Boolean valueOf = Boolean.valueOf(z);
        if (filmStripViewHolder2 == null) {
            z3 = true;
        }
        Log.d("FilmStripView", "VP onPageScrolled skip. null vh", valueOf, Boolean.valueOf(z3));
        return true;
    }

    private int gapBetweenContainerAndFocusedVh() {
        return ResourceCompat.getDimensionPixelSize(getContext(), R$dimen.film_strip3_view_height, 0) - ResourceCompat.getDimensionPixelSize(getContext(), R$dimen.film_strip_focused_height, 0);
    }

    private int getExpandedViewPosition(View view) {
        if (this.IS_RTL) {
            return (view.getWidth() + ((int) view.getX())) - ((this.mItemGap + this.mVideIndexWidth) / 2);
        }
        return ((this.mItemGap + this.mVideIndexWidth) / 2) + ((int) view.getX());
    }

    private void initSnapHelper() {
        if (this.mSnapHelper == null) {
            FilmStripSnapHelper filmStripSnapHelper = new FilmStripSnapHelper(this);
            this.mSnapHelper = filmStripSnapHelper;
            filmStripSnapHelper.attachToRecyclerView(this);
        }
    }

    private void initView(Context context) {
        this.mItemGap = context.getResources().getDimensionPixelSize(R$dimen.film_strip_item_gap);
        this.mVideIndexWidth = context.getResources().getDimensionPixelSize(R$dimen.film_strip3_video_index_width);
        setLayoutManager(this.mLayoutManager);
        initSnapHelper();
        setClipToPadding(false);
        addOnItemTouchListener(this.mOnItemTouchListener);
        if (PocFeatures.FILM_SMOOTH_SCROLL) {
            addOnScrollListener(this.mScrollListener);
        }
        seslSetPenSelectionEnabled(false);
        setItemAnimator((RecyclerView.ItemAnimator) null);
        setPadding(getPaddingLeft(), gapBetweenContainerAndFocusedVh() / 2, getPaddingRight(), gapBetweenContainerAndFocusedVh() / 2);
    }

    private boolean isAnimating(FilmStripViewHolder<?> filmStripViewHolder, FilmStripViewHolder<?> filmStripViewHolder2) {
        if (!filmStripViewHolder.isAnimating() && !filmStripViewHolder2.isAnimating()) {
            return false;
        }
        Log.d("FilmStripView", "VP vh is Animating skip", Boolean.valueOf(filmStripViewHolder.isAnimating()), Boolean.valueOf(filmStripViewHolder2.isAnimating()));
        return true;
    }

    private boolean isSupportSmoothScroll() {
        if (PocFeatures.FILM_SMOOTH_SCROLL) {
            return this.mLayoutManager.findExpandedView().isEmpty();
        }
        return false;
    }

    private void setCenterPadding() {
        int width = this.mLayoutManager.getWidth();
        if (width == 0) {
            width = ViewUtils.getExpectedMaxWidth(this);
        }
        if (width != this.mPrevWidth) {
            int i2 = (width - this.mItemGap) / 2;
            this.mPrevWidth = width;
            setPadding(i2, getPaddingTop(), i2, getPaddingBottom());
        }
    }

    public boolean fling(int i2, int i7) {
        return super.fling((int) (((float) i2) * 1.0f), i7);
    }

    public FilmStripViewHolder getCenterViewHolder() {
        View centerView = this.mLayoutManager.getCenterView();
        if (centerView != null) {
            try {
                return (FilmStripViewHolder) getChildViewHolder(centerView);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public int getCenterX() {
        return this.mCenterX;
    }

    public int getDefaultWidth(FilmStripViewHolder filmStripViewHolder) {
        return filmStripViewHolder.getDefaultWidth();
    }

    public int getMaxWidth(FilmStripViewHolder filmStripViewHolder) {
        return filmStripViewHolder.getMaxWidth();
    }

    public int getScrollDistance(FilmStripViewHolder filmStripViewHolder, FilmStripViewHolder filmStripViewHolder2) {
        if (!this.IS_RTL) {
            int maxWidth = ((filmStripViewHolder.getMaxWidth() / 2) + ((int) filmStripViewHolder.itemView.getX())) - getCenterX();
            if (filmStripViewHolder.getViewHolderPosition() > filmStripViewHolder2.getViewHolderPosition()) {
                return maxWidth - (getMaxWidth(filmStripViewHolder2) - getDefaultWidth(filmStripViewHolder2));
            }
            return maxWidth - (getMaxWidth(filmStripViewHolder) - getDefaultWidth(filmStripViewHolder));
        }
        int defaultWidth = ((filmStripViewHolder.getDefaultWidth() / 2) + ((int) filmStripViewHolder.itemView.getX())) - getCenterX();
        if (filmStripViewHolder.getViewHolderPosition() > filmStripViewHolder2.getViewHolderPosition()) {
            return (getMaxWidth(filmStripViewHolder2) - getDefaultWidth(filmStripViewHolder2)) + defaultWidth;
        }
        return defaultWidth;
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
        accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        if (z) {
            this.mCenterX = (i8 / 2) + i2;
        }
        this.mLayoutManager.setOnLayout(true);
        super.onLayout(z, i2, i7, i8, i10);
        this.mLayoutManager.setOnLayout(false);
    }

    public void onMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
        setCenterPadding();
    }

    public void onScrolled(int i2, int i7) {
        super.onScrolled(i2, i7);
        requestLayout();
    }

    public void restoreExpandedView() {
        restoreExpandedView(true);
    }

    public void scrollBy(int i2, int i7) {
        super.scrollBy(i2, i7);
    }

    public void scrollOffset(int i2, float f) {
        if (getScrollState() != 1) {
            this.mLayoutManager.setSmoothScroll(-1);
            FilmStripViewHolder filmStripViewHolder = (FilmStripViewHolder) findViewHolderForAdapterPosition(i2);
            FilmStripViewHolder filmStripViewHolder2 = (FilmStripViewHolder) findViewHolderForAdapterPosition(i2 + 1);
            if (!checkViewHolder(filmStripViewHolder, filmStripViewHolder2)) {
                if (isAnimating(filmStripViewHolder, filmStripViewHolder2)) {
                    requestLayout();
                    return;
                }
                float centerX = (float) (getCenterX() - (filmStripViewHolder.getMaxWidth() / 2));
                float centerX2 = centerX - ((centerX - ((float) ((getCenterX() - (filmStripViewHolder2.getMaxWidth() / 2)) - filmStripViewHolder.getDefaultWidth()))) * f);
                if (this.IS_RTL) {
                    float defaultWidth = (float) (filmStripViewHolder.getDefaultWidth() + (filmStripViewHolder2.getMaxWidth() / 2) + getCenterX());
                    float maxWidth = (float) ((filmStripViewHolder.getMaxWidth() / 2) + getCenterX());
                    centerX2 = C0212a.a(defaultWidth, maxWidth, f, maxWidth);
                }
                this.mLayoutManager.setViewHolderPosition(filmStripViewHolder, centerX2);
                requestLayout();
            }
        }
    }

    public void scrollToPosition(int i2) {
        this.mLayoutManager.setCenterPosition(i2);
        super.scrollToPosition(i2);
    }

    public void setAnchorViewPos(int i2) {
        Reflector.setField(LinearLayoutManager.class, this.mLayoutManager, "mPendingScrollPosition", Integer.valueOf(i2));
    }

    public void setEnableSnapHelper(boolean z) {
        if (z) {
            this.mSnapHelper.attachToRecyclerView(this);
        } else {
            this.mSnapHelper.attachToRecyclerView((RecyclerView) null);
        }
    }

    public void setOnFindTargetSnapPosition(FilmStripSnapHelper.OnFindTargetSnapPosition onFindTargetSnapPosition) {
        this.mSnapHelper.setOnFindTargetSnapPosition(onFindTargetSnapPosition);
    }

    public void setProgress(int i2, int i7) {
        FilmStripViewHolder centerViewHolder = getCenterViewHolder();
        if (centerViewHolder == null || !centerViewHolder.isExpanded()) {
            Log.d("FilmStripView", "ignore seek");
            return;
        }
        if (i7 > i2) {
            i7 = i2;
        }
        int centerX = getCenterX() - getExpandedViewPosition(centerViewHolder.itemView);
        int width = (int) ((((float) ((centerViewHolder.itemView.getWidth() - this.mItemGap) - this.mVideIndexWidth)) / ((float) i2)) * ((float) i7));
        int i8 = width - centerX;
        if (this.IS_RTL) {
            i8 = (width + centerX) * -1;
        }
        scrollBy(i8, 0);
    }

    public void setScrolledPosition(int i2) {
        this.mLayoutManager.setCenterPosition(i2);
    }

    public void setSeekerMode(boolean z) {
        this.mLayoutManager.setSeekerMode(z);
    }

    public void smoothScrollToPosition(int i2) {
        if (!isSupportSmoothScroll()) {
            scrollToPosition(i2);
            return;
        }
        FilmStripViewHolder centerViewHolder = getCenterViewHolder();
        FilmStripViewHolder filmStripViewHolder = (FilmStripViewHolder) findViewHolderForAdapterPosition(i2);
        if (centerViewHolder == null || filmStripViewHolder == null) {
            scrollToPosition(i2);
            return;
        }
        this.mLayoutManager.setSmoothScroll(i2);
        smoothScrollBy(getScrollDistance(filmStripViewHolder, centerViewHolder), 0, PathInterpolator.create(0.22f, 0.25f, 0.0f, 1.0f));
    }

    public void restoreExpandedView(boolean z) {
        Iterator<FilmStripViewHolder> it = this.mLayoutManager.findExpandedView().iterator();
        while (it.hasNext()) {
            it.next().restoreFrameView(this, z);
        }
    }
}
