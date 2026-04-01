package com.samsung.android.gallery.widget.filmstrip3;

import A.a;
import A4.C0367b;
import A4.C0372g;
import android.content.res.Resources;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.recyclerview.RecyclableLayoutManager;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripLayoutManager extends LinearLayoutManager implements RecyclableLayoutManager {
    private final boolean IS_RTL = Features.isEnabled(Features.IS_RTL);
    private FilmStripViewHolder mAdjustVh;
    private float mAdjustVhPos;
    private int mCenterPosition = -1;
    private View mCenterView = null;
    boolean mEnableCenterOnMeasure = true;
    private final FilmStripView mFilmStripView;
    private View mHoldView;
    private boolean mIsOnViewLayout = false;
    boolean mIsSeekerMode = false;
    private final int mItemGap;
    private final int mItemHeight;
    private final int mItemMaxHeight;
    int mOnMeasureDelta;
    int mOnMeasuredCenter = -1;
    private final Handler mOverScrollHandler = new Handler();
    private RecyclerView.Recycler mRecycler;
    private final int mResizeArea;
    final ArrayList<View> mScrapViewList = new ArrayList<>();
    private final int mSizeDiff;
    private int mSoothScrollTarget = -1;
    private final int mVideIndexWidth;

    public FilmStripLayoutManager(FilmStripView filmStripView, int i2, boolean z) {
        super(filmStripView.getContext(), i2, z);
        Resources resources = filmStripView.getContext().getResources();
        this.mFilmStripView = filmStripView;
        this.mVideIndexWidth = resources.getDimensionPixelSize(R$dimen.film_strip3_video_index_width);
        this.mResizeArea = resources.getDimensionPixelSize(R$dimen.film_strip_resize_area) / 2;
        this.mItemGap = resources.getDimensionPixelSize(R$dimen.film_strip_item_gap);
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.film_strip_item_height);
        this.mItemHeight = dimensionPixelSize;
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.film_strip_focused_height);
        this.mItemMaxHeight = dimensionPixelSize2;
        this.mSizeDiff = dimensionPixelSize2 - dimensionPixelSize;
    }

    private int calculateDelta() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            FilmStripViewHolder filmStripViewHolder = (FilmStripViewHolder) this.mFilmStripView.getChildViewHolder(childAt);
            if (filmStripViewHolder != null && !filmStripViewHolder.isExpanded() && !filmStripViewHolder.isAnimating() && filmStripViewHolder.getLayoutPosition() == this.mCenterPosition) {
                return (int) (childAt.getX() - ((float) (getMidPoint() - (filmStripViewHolder.getMaxWidth() / 2))));
            }
        }
        return 0;
    }

    private void centerAdjustment(RecyclerView.Recycler recycler, RecyclerView.State state) {
        float x9;
        int i2;
        if (this.mAdjustVh == null && this.mCenterPosition >= 0) {
            int i7 = 0;
            while (i7 < getChildCount()) {
                View childAt = getChildAt(i7);
                FilmStripViewHolder filmStripViewHolder = (FilmStripViewHolder) this.mFilmStripView.getChildViewHolder(getChildAt(i7));
                if (filmStripViewHolder == null || filmStripViewHolder.isExpanded() || filmStripViewHolder.isAnimating() || filmStripViewHolder.getLayoutPosition() != this.mCenterPosition) {
                    i7++;
                } else if (((int) childAt.getX()) != getMidPoint() - (filmStripViewHolder.getMaxWidth() / 2)) {
                    ViewUtils.setViewSize(childAt, Integer.valueOf(filmStripViewHolder.getMaxWidth()), Integer.valueOf(filmStripViewHolder.getMaxHeight()));
                    if (this.IS_RTL) {
                        x9 = childAt.getX() + ((float) childAt.getWidth());
                    } else {
                        x9 = childAt.getX();
                    }
                    int i8 = (int) x9;
                    if (this.IS_RTL) {
                        i2 = (filmStripViewHolder.getMaxWidth() / 2) + getMidPoint();
                    } else {
                        i2 = getMidPoint() - (filmStripViewHolder.getMaxWidth() / 2);
                    }
                    scrollHorizontallyBy(i8 - i2, recycler, state);
                    this.mCenterPosition = -1;
                    return;
                } else {
                    return;
                }
            }
        }
    }

    private void centerAdjustmentOnMeasure() {
        if (this.mEnableCenterOnMeasure && this.mAdjustVh == null && this.mCenterPosition >= 0) {
            int calculateDelta = calculateDelta();
            this.mOnMeasureDelta = calculateDelta;
            if (calculateDelta != 0) {
                forEachChildView(new C0367b(14, this));
                this.mOnMeasuredCenter = this.mCenterPosition;
                this.mCenterPosition = -1;
                this.mEnableCenterOnMeasure = false;
            }
        }
    }

    private void fitSizeForExpanded(FilmStripViewHolder filmStripViewHolder) {
        if (filmStripViewHolder.itemView.getHeight() == this.mItemMaxHeight) {
            if (!isCenterView(filmStripViewHolder.itemView).booleanValue()) {
                filmStripViewHolder.setHeight(this.mItemHeight);
            }
        } else if (filmStripViewHolder.itemView.getHeight() == this.mItemHeight && isCenterView(filmStripViewHolder.itemView).booleanValue()) {
            filmStripViewHolder.setHeight(this.mItemMaxHeight);
        }
    }

    private void forEachChildView(Consumer<View> consumer) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null) {
                consumer.accept(childAt);
            }
        }
    }

    private Boolean isCenterView(View view) {
        boolean z;
        if (view.getX() > ((float) getMidPoint()) || view.getX() + ((float) view.getWidth()) < ((float) getMidPoint())) {
            z = false;
        } else {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    private boolean isResizedArea(View view) {
        int midPoint = getMidPoint();
        int width = (view.getWidth() / 2) + ((int) view.getX());
        int i2 = this.mResizeArea;
        if (width > midPoint - i2 && width < midPoint + i2) {
            return true;
        }
        return false;
    }

    private boolean isSmoothScrolled(FilmStripViewHolder filmStripViewHolder) {
        if (this.mSoothScrollTarget < 0) {
            return false;
        }
        if (filmStripViewHolder.getViewHolderPosition() == this.mSoothScrollTarget) {
            filmStripViewHolder.setHeight(this.mItemMaxHeight);
            return true;
        }
        filmStripViewHolder.setHeight(this.mItemHeight);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$centerAdjustmentOnMeasure$0(View view) {
        view.setX(view.getX() - ((float) this.mOnMeasureDelta));
        this.mScrapViewList.add(view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$stopOverScroll$1() {
        FilmStripViewHolder centerViewHolder;
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null && filmStripView.getScrollState() == 2 && (centerViewHolder = this.mFilmStripView.getCenterViewHolder()) != null && centerViewHolder.isExpanded() && centerViewHolder.isFocused()) {
            this.mFilmStripView.stopScroll();
        }
    }

    private boolean layoutExpandedSeekerView(RecyclerView.Recycler recycler, RecyclerView.State state) {
        View view;
        if (this.mIsSeekerMode && (view = this.mHoldView) != null) {
            int centerX = this.mFilmStripView.getCenterX();
            int x9 = (int) view.getX();
            int i2 = (this.mItemGap + this.mVideIndexWidth) / 2;
            int width = (view.getWidth() + x9) - i2;
            if (centerX > width) {
                scrollHorizontallyBy(width - centerX, recycler, state);
                stopOverScroll();
                return false;
            }
            int i7 = x9 + i2;
            if (centerX < i7) {
                scrollHorizontallyBy(i7 - centerX, recycler, state);
                stopOverScroll();
                return false;
            }
        }
        this.mOverScrollHandler.removeCallbacksAndMessages((Object) null);
        return true;
    }

    private void layoutItemWidth() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null) {
                getCalculateItemWidth(childAt);
            }
        }
    }

    private void recoverFromOnMeasuredCenter() {
        int i2 = this.mOnMeasuredCenter;
        if (i2 != -1) {
            this.mCenterPosition = i2;
            Iterator<View> it = this.mScrapViewList.iterator();
            while (it.hasNext()) {
                View next = it.next();
                next.setX(next.getX() + ((float) this.mOnMeasureDelta));
            }
            this.mScrapViewList.clear();
            this.mOnMeasuredCenter = -1;
            this.mOnMeasureDelta = 0;
        }
    }

    private void scrollAdjustment(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mAdjustVh != null) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                if (childAt == this.mAdjustVh.itemView) {
                    if (this.IS_RTL) {
                        scrollHorizontallyBy((int) ((childAt.getX() + ((float) childAt.getWidth())) - this.mAdjustVhPos), recycler, state);
                    } else {
                        scrollHorizontallyBy((int) (childAt.getX() - this.mAdjustVhPos), recycler, state);
                    }
                }
            }
        }
    }

    private void setCenterView() {
        this.mCenterView = null;
        float width = ((float) getWidth()) / 2.0f;
        int i2 = 0;
        while (i2 < getChildCount()) {
            View childAt = getChildAt(i2);
            if (childAt == null || width < ((float) getDecoratedLeft(childAt)) || width > ((float) getDecoratedRight(childAt))) {
                i2++;
            } else {
                this.mCenterView = childAt;
                return;
            }
        }
    }

    private void stopOverScroll() {
        if (this.mFilmStripView.getScrollState() == 2) {
            this.mOverScrollHandler.postDelayed(new C0372g(13, this), 300);
        }
    }

    public ArrayList<FilmStripViewHolder> findExpandedView() {
        FilmStripViewHolder filmStripViewHolder;
        ArrayList<FilmStripViewHolder> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (!(childAt == null || (filmStripViewHolder = (FilmStripViewHolder) this.mFilmStripView.getChildViewHolder(childAt)) == null || !filmStripViewHolder.isExpanded())) {
                arrayList.add(filmStripViewHolder);
            }
        }
        return arrayList;
    }

    public void getCalculateItemWidth(View view) {
        FilmStripViewHolder filmStripViewHolder = (FilmStripViewHolder) this.mFilmStripView.getChildViewHolder(view);
        if (filmStripViewHolder != null && !isSmoothScrolled(filmStripViewHolder)) {
            if (filmStripViewHolder.isExpanded()) {
                fitSizeForExpanded(filmStripViewHolder);
            } else if (isResizedArea(view)) {
                int abs = Math.abs(getMidPoint() - ((view.getWidth() / 2) + ((int) view.getX())));
                filmStripViewHolder.setHeight(this.mItemMaxHeight - ((int) (((float) (this.mSizeDiff * abs)) / ((float) this.mResizeArea))));
            } else {
                filmStripViewHolder.setHeight(this.mItemHeight);
            }
        }
    }

    public View getCenterView() {
        return this.mCenterView;
    }

    public int getMidPoint() {
        return this.mFilmStripView.getCenterX();
    }

    public boolean isSeekerMode() {
        return this.mIsSeekerMode;
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        scrollAdjustment(recycler, state);
        super.onLayoutChildren(recycler, state);
        if (this.mIsOnViewLayout) {
            recoverFromOnMeasuredCenter();
            centerAdjustment(recycler, state);
        } else if (!state.didStructureChange()) {
            centerAdjustmentOnMeasure();
        }
        setCenterView();
    }

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i2, int i7) {
        this.mRecycler = recycler;
        if (layoutExpandedSeekerView(recycler, state)) {
            layoutItemWidth();
        }
        super.onMeasure(recycler, state, i2, i7);
    }

    public void recycleAllViews() {
        RecyclerView.Recycler recycler = this.mRecycler;
        if (recycler != null) {
            removeAndRecycleAllViews(recycler);
        }
    }

    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (i2 == 0) {
            return 0;
        }
        try {
            return super.scrollHorizontallyBy(i2, recycler, state);
        } catch (IndexOutOfBoundsException unused) {
            Log.e("FilmStripLayoutManager", "scrollHorizontallyBy failed");
            return 0;
        }
    }

    public void scrollToPosition(int i2) {
        setCenterPosition(i2);
        super.scrollToPosition(i2);
    }

    public void setCenterPosition(int i2) {
        a.k(i2, "set center view pos = ", "FilmStripLayoutManager");
        this.mCenterPosition = i2;
    }

    public void setOnLayout(boolean z) {
        this.mIsOnViewLayout = z;
    }

    public void setSeekerMode(boolean z) {
        this.mIsSeekerMode = z;
        this.mHoldView = getCenterView();
    }

    public void setSmoothScroll(int i2) {
        this.mSoothScrollTarget = i2;
    }

    public void setViewHolderPosition(FilmStripViewHolder<?> filmStripViewHolder, float f) {
        this.mAdjustVh = filmStripViewHolder;
        this.mAdjustVhPos = f;
        this.mCenterPosition = -1;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        AnonymousClass1 r22 = new LinearSmoothScroller(recyclerView.getContext()) {
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 100.0f / ((float) displayMetrics.densityDpi);
            }
        };
        r22.setTargetPosition(i2);
        startSmoothScroll(r22);
    }

    public boolean supportsPredictiveItemAnimations() {
        return false;
    }
}
