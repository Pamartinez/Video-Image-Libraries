package com.samsung.android.gallery.widget.listview.scroller;

import Kb.a;
import Kb.b;
import Kb.c;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.BoosterCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastScroller extends Scroller {
    private static final boolean ADAPTIVE_FAST_SCROLL = PocFeatures.isEnabled(PocFeatures.AdaptiveFastScroll);
    private static final boolean INSENSITIVE_SCROLL = PocFeatures.isEnabled(PocFeatures.InsensitiveFastScroll);
    private static final boolean SUPPORT_FAST_VIBRATE_INDEX = PreferenceFeatures.OneUi7x.IS_ONE_UI_70;
    private int mAccMoveCount;
    private final BoosterCompat mBoosterCompat;
    private int mDefaultMaxScroll = -1;
    private int mDragState = 0;
    private int mFastVibrateIndex = 26;
    private int mGoToPosition = 0;
    private final ValueAnimator.AnimatorUpdateListener mGoToPositionAnimationListener = new c(this, 1);
    private final ValueAnimator mGoToPositionAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
    private int mGoToPositionScroll = 0;
    private final ValueAnimator.AnimatorUpdateListener mGoToTopAnimationListener = new c(this, 0);
    private final ValueAnimator mGoToTopAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
    private int mGoTopBaseScroll = 0;
    private final AccelerateInterpolator mInterpolator = new AccelerateInterpolator(2.0f);
    private int mLastDensity = Integer.MIN_VALUE;
    private int mLastResolution = Integer.MIN_VALUE;
    private float mLastY;
    LayoutInformer mLayoutInformer;
    private final int mMaxScrollThreshold;
    private int mOldScrollAmount = 0;
    private int mPosition = 0;
    private final String mScreenId;
    private int mScrollAmount = 0;
    /* access modifiers changed from: private */
    public TextView mScrollPopUpDate;
    /* access modifiers changed from: private */
    public TextView mScrollPopUpLocation;
    /* access modifiers changed from: private */
    public View mScrollPopUpView;
    private final int mScrollPopupHorizontalPadding;
    private int mTopMargin;
    private final int mTouchAreaWidth;
    private VelocityTracker mVelocityTracker;
    private YearScrollTag mYearScrollTag;
    private String previousDate;
    private final int thumbnailDefaultColor;
    private final int thumbnailTapColor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface LayoutInformer {
        ArrayList<Pair<String, Integer>> getDividerList();

        int getFirstVisibleIndex();

        int getItemCount();

        int getMaxScroll();

        int getNextRowIndex(int i2, int i7);

        int getPrevRowIndex(int i2);

        int getRowHeight(int i2);

        int getScrollStart();

        boolean isHideScroller();

        boolean isQuickScrollRunning();

        void scrollToIndexWithOffset(int i2, int i7);

        void setQuickScrollState(boolean z);
    }

    public FastScroller(RecyclerView recyclerView, View view, String str, int i2, int i7, int i8) {
        super(recyclerView, i2);
        Context context = recyclerView.getContext();
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R$dimen.fast_scroll_popup_padding);
        setSidePadding(dimensionPixelOffset);
        this.mTouchAreaWidth = getTouchArea(context, dimensionPixelOffset);
        this.mMaxScrollThreshold = context.getResources().getDimensionPixelOffset(R$dimen.fast_scroll_scroll_threshold);
        this.mBoosterCompat = SeApiCompat.getBoosterCompat(context.getApplicationContext());
        this.mScrollPopUpView = view;
        getTopAndBottomMargin();
        initYearScrollTag();
        this.mScreenId = str;
        if (useCustomScrollbar()) {
            this.mVerticalThumbHeight = this.mVerticalThumbDrawable.getIntrinsicHeight();
        } else {
            setSidePadding(0);
        }
        this.thumbnailDefaultColor = i7;
        this.thumbnailTapColor = i8;
        this.mScrollPopupHorizontalPadding = recyclerView.getContext().getResources().getDimensionPixelOffset(R$dimen.fast_scroll_popup_text_horizontal_padding);
        recyclerView.post(new a(this, 1));
    }

    private void calculateScroll() {
        int i2;
        RecyclerView.Adapter adapter = this.mRecyclerView.getAdapter();
        Objects.requireNonNull(adapter);
        if (adapter.getItemCount() == 0) {
            this.mScrollAmount = 0;
            return;
        }
        LayoutInformer layoutInformer = this.mLayoutInformer;
        if (layoutInformer == null) {
            Log.e(this.TAG, "calculateScroll failed");
            return;
        }
        int firstVisibleIndex = layoutInformer.getFirstVisibleIndex();
        if (firstVisibleIndex < 0) {
            A.a.B(firstVisibleIndex, "calculateScroll failed ", this.TAG);
            return;
        }
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        Objects.requireNonNull(layoutManager);
        View findViewByPosition = layoutManager.findViewByPosition(firstVisibleIndex);
        if (findViewByPosition != null) {
            i2 = (int) findViewByPosition.getY();
        } else {
            i2 = 0;
        }
        int rowHeight = layoutInformer.getRowHeight(firstVisibleIndex) + i2;
        this.mScrollAmount = 0;
        int itemCount = layoutInformer.getItemCount();
        int i7 = 0;
        while (true) {
            if (i7 > firstVisibleIndex) {
                break;
            }
            try {
                this.mScrollAmount += layoutInformer.getRowHeight(i7);
                int nextRowIndex = layoutInformer.getNextRowIndex(i7, itemCount);
                if (nextRowIndex >= 0 && nextRowIndex >= i7) {
                    if (i7 == nextRowIndex) {
                        this.mScrollAmount -= layoutInformer.getRowHeight(i7);
                        break;
                    }
                    i7 = nextRowIndex;
                } else {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        int i8 = this.mScrollAmount - rowHeight;
        this.mScrollAmount = i8;
        if (i8 < 0) {
            this.mScrollAmount = 0;
        }
    }

    private int calculateScrollAmount(LayoutInformer layoutInformer, int i2, boolean z) {
        if (layoutInformer == null) {
            return -1;
        }
        try {
            int itemCount = layoutInformer.getItemCount();
            int i7 = 0;
            int i8 = 0;
            while (true) {
                if (i7 > i2) {
                    break;
                }
                i8 += layoutInformer.getRowHeight(i7);
                int nextRowIndex = layoutInformer.getNextRowIndex(i7, itemCount);
                if (nextRowIndex < 0) {
                    return -1;
                }
                if (i7 == nextRowIndex) {
                    i8 -= layoutInformer.getRowHeight(i7);
                    break;
                }
                i7 = nextRowIndex;
            }
            if (z) {
                return i8 - layoutInformer.getRowHeight(i2);
            }
            return layoutInformer.getRowHeight(i2) + (i8 - this.mRecyclerViewHeight);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void calculateYearScrollTag() {
        if (this.mScrollPopUpView != null && this.mLayoutInformer != null && this.mYearScrollTag.calculate(this, getScrollerScrollable())) {
            this.mScrollPopUpView.bringToFront();
        }
    }

    private Animator createScrollPopupViewAlphaScaleAnimator(boolean z) {
        float f;
        float f5;
        float f8;
        long j2;
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        if (z) {
            f5 = 1.0f;
        } else {
            f5 = 0.0f;
        }
        float f10 = 0.3f;
        if (z) {
            f8 = 0.3f;
        } else {
            f8 = 1.0f;
        }
        if (z) {
            f10 = 1.0f;
        }
        if (z) {
            j2 = 300;
        } else {
            j2 = 200;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mScrollPopUpView, "alpha", new float[]{f, f5});
        ofFloat.setDuration(j2);
        ofFloat.setInterpolator(PathInterpolator.create(0.22f, 0.25f, 0.0f, 1.0f));
        ViewGroup.LayoutParams layoutParams = this.mScrollPopUpView.getLayoutParams();
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{f8, f10});
        ofFloat2.addUpdateListener(new b(0, this, layoutParams));
        ofFloat2.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                ViewUtils.setWidth(FastScroller.this.mScrollPopUpView, -2);
            }
        });
        if (z) {
            ofFloat2.setInterpolator(PathInterpolator.create(0.0f, 1.39f, 0.45f, 1.09f));
        } else {
            ofFloat2.setInterpolator(PathInterpolator.create(0.22f, 0.25f, 0.0f, 1.0f));
        }
        ofFloat2.setDuration(j2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        return animatorSet;
    }

    private void forceScrollToPositionWithOffset(int i2, int i7) {
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).scrollToPositionWithOffset(i2, i7);
        }
    }

    private float getCalibrationRatio(float f, int i2) {
        if (!ADAPTIVE_FAST_SCROLL) {
            return 1.0f;
        }
        float f5 = ((float) i2) * 0.85f;
        if (f <= f5) {
            return this.mInterpolator.getInterpolation(f / f5);
        }
        return 1.0f;
    }

    private int getGoToTopDuration() {
        int i2;
        if (needToJump()) {
            i2 = this.mRecyclerViewHeight * 2;
        } else {
            i2 = this.mScrollAmount;
        }
        return Math.min((int) ((((((float) i2) / this.mRecyclerView.getResources().getDisplayMetrics().density) * 2.0E-4f) + 0.45f) * 1000.0f), 800);
    }

    private int getJumpPosition(LayoutInformer layoutInformer) {
        if (layoutInformer == null) {
            return -1;
        }
        int i2 = this.mRecyclerViewHeight * 2;
        try {
            int itemCount = layoutInformer.getItemCount();
            int i7 = 0;
            int i8 = 0;
            while (true) {
                if (i7 > i2) {
                    break;
                }
                int nextRowIndex = layoutInformer.getNextRowIndex(i8, itemCount);
                if (nextRowIndex < 0) {
                    break;
                }
                i7 += layoutInformer.getRowHeight(i8);
                i8 = nextRowIndex;
            }
            this.mScrollAmount = i7;
            return i8;
        } catch (Exception unused) {
            return -1;
        }
    }

    private ScrollText getScrollText(int i2) {
        int childCount = this.mRecyclerView.getChildCount();
        View childAt = this.mRecyclerView.getChildAt(0);
        if (childAt == null) {
            return null;
        }
        float y = childAt.getY();
        ScrollText scrollText = (ScrollText) childAt.getTag();
        for (int i7 = 1; i7 < childCount; i7++) {
            View childAt2 = this.mRecyclerView.getChildAt(i7);
            if (childAt2.getY() <= ((float) i2) && childAt2.getY() > y && childAt2.getTag() != null) {
                float y8 = childAt2.getY();
                y = y8;
                scrollText = (ScrollText) childAt2.getTag();
            }
        }
        return scrollText;
    }

    private int getScrollerScrollable() {
        return this.mLayoutInformer.getMaxScroll() - this.mRecyclerView.getPaddingBottom();
    }

    private void getTopAndBottomMargin() {
        int dimensionPixelOffset = this.mRecyclerView.getResources().getDimensionPixelOffset(R$dimen.fast_scroll_year_popup_vertical_margin);
        this.mBottomMargin = dimensionPixelOffset;
        this.mTopMargin = dimensionPixelOffset;
    }

    /* access modifiers changed from: private */
    public void initYearScrollTag() {
        YearScrollTag yearScrollTag = this.mYearScrollTag;
        if (yearScrollTag != null) {
            yearScrollTag.destroy();
        }
        this.mYearScrollTag = new YearScrollTag(this.mRecyclerView, this.mTopMargin, this.mBottomMargin);
    }

    private void initializeScrollPopupView(View view) {
        int extraMargin;
        if (view != null) {
            this.mScrollPopUpView = view;
            this.mScrollPopUpDate = (TextView) view.findViewById(R$id.date);
            this.mScrollPopUpLocation = (TextView) this.mScrollPopUpView.findViewById(R$id.location);
            View view2 = this.mScrollPopUpView;
            if (isLayoutRTL()) {
                extraMargin = getExtraMargin();
            } else {
                extraMargin = getExtraMargin() + (this.mRecyclerViewWidth - this.mScrollPopUpView.getWidth());
            }
            view2.setX((float) extraMargin);
        }
    }

    private boolean isDensityOrResolutionChanged(Configuration configuration) {
        if (configuration.densityDpi == this.mLastDensity && this.mLastResolution == configuration.screenWidthDp * configuration.screenHeightDp) {
            return false;
        }
        return true;
    }

    private boolean isPointInsideScrollBar(float f, float f5) {
        if (!isLayoutRTL()) {
            int i2 = this.mRecyclerViewWidth;
            int i7 = this.mExtraDisplayPadding;
            if (f < ((float) ((i2 - this.mTouchAreaWidth) - i7)) || f > ((float) (i2 - i7))) {
                return false;
            }
        } else if (f > ((float) this.mTouchAreaWidth) || f < ((float) this.mExtraDisplayPadding)) {
            return false;
        }
        int i8 = this.mVerticalThumbTop;
        if (f5 < ((float) i8) || f5 > ((float) (i8 + this.mVerticalThumbHeight))) {
            return false;
        }
        return true;
    }

    private boolean isQuickScrolling(LayoutInformer layoutInformer) {
        if (layoutInformer == null || !layoutInformer.isQuickScrollRunning()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createScrollPopupViewAlphaScaleAnimator$1(ViewGroup.LayoutParams layoutParams, ValueAnimator valueAnimator) {
        layoutParams.width = (int) ((Math.max(this.mScrollPopUpDate.getPaint().measureText(String.valueOf(this.mScrollPopUpDate.getText())), this.mScrollPopUpLocation.getPaint().measureText(String.valueOf(this.mScrollPopUpLocation.getText()))) + ((float) (this.mScrollPopupHorizontalPadding * 2))) * ((Float) valueAnimator.getAnimatedValue()).floatValue());
        this.mScrollPopUpView.setLayoutParams(layoutParams);
        View view = this.mScrollPopUpView;
        view.setPivotX((float) view.getWidth());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        this.mVerticalThumbDrawable.setTint(this.thumbnailDefaultColor);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3(ValueAnimator valueAnimator) {
        LayoutInformer layoutInformer = this.mLayoutInformer;
        if (layoutInformer != null) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            if (animatedFraction == 1.0f) {
                verticalScrollBy(layoutInformer, -this.mScrollAmount);
                setQuickScrollState(layoutInformer, false);
                this.mGoTopBaseScroll = 0;
                this.mScrollAmount = 0;
                forceScrollToPositionWithOffset(0, 0);
                return;
            }
            verticalScrollBy(layoutInformer, ((int) ((1.0f - animatedFraction) * ((float) this.mGoTopBaseScroll))) - this.mScrollAmount);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$4(ValueAnimator valueAnimator) {
        LayoutInformer layoutInformer = this.mLayoutInformer;
        if (layoutInformer != null) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            if (animatedFraction == 1.0f) {
                verticalScrollBy(layoutInformer, this.mGoToPositionScroll - this.mScrollAmount);
                setQuickScrollState(layoutInformer, false);
                this.mGoTopBaseScroll = 0;
                this.mScrollAmount = this.mGoToPositionScroll;
                forceScrollToPositionWithOffset(this.mGoToPosition, 0);
                return;
            }
            verticalScrollBy(layoutInformer, ((int) ((((float) this.mGoToPositionScroll) * animatedFraction) + ((1.0f - animatedFraction) * ((float) this.mGoTopBaseScroll)))) - this.mScrollAmount);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startHapticFeedbackOnBg$2() {
        SeApiCompat.performHapticFeedback(this.mRecyclerView.getContext().getApplicationContext(), this.mFastVibrateIndex);
    }

    private boolean needToJump() {
        if (this.mRecyclerViewHeight * 2 < this.mScrollAmount) {
            return true;
        }
        return false;
    }

    private void onDraggingEnded() {
        startScrollPopupViewShowAnimation(false);
        this.mOldScrollAmount = 0;
        this.mPosition = 0;
        this.mBoosterCompat.releaseScroll();
        setQuickScrollState(this.mLayoutInformer, false);
        updateScrollbarState(false);
        Log.majorEvent("FastScrollEnd");
    }

    private void onDraggingStarted() {
        if (!isQuickScrolling(this.mLayoutInformer)) {
            Log.majorEvent("FastScrollStart");
        }
        this.mBoosterCompat.acquireScroll();
        calculateYearScrollTag();
        setQuickScrollState(this.mLayoutInformer, true);
        startHapticFeedbackOnBg();
        updateScrollbarState(true);
    }

    private void scrollDown(LayoutInformer layoutInformer) {
        int i2;
        int i7;
        int itemCount = layoutInformer.getItemCount();
        int i8 = this.mScrollAmount;
        int maxScroll = layoutInformer.getMaxScroll();
        int i10 = this.mRecyclerViewHeight;
        if (i8 == maxScroll - i10) {
            int i11 = itemCount - 1;
            layoutInformer.scrollToIndexWithOffset(i11, Math.min(i10 - layoutInformer.getRowHeight(i11), 0));
            this.mPosition = 0;
            this.mOldScrollAmount = 0;
            return;
        }
        int i12 = this.mPosition;
        int i13 = this.mScrollAmount;
        int i14 = i13 - this.mOldScrollAmount;
        if (i14 < 0) {
            this.mPosition = 0;
            this.mOldScrollAmount = 0;
            i12 = 0;
            i2 = 0;
        } else {
            i13 = i14;
            i2 = 0;
        }
        while (true) {
            if (i13 <= 0) {
                break;
            }
            try {
                int nextRowIndex = layoutInformer.getNextRowIndex(i12, itemCount);
                try {
                    i2 = layoutInformer.getRowHeight(i12);
                    i13 -= i2;
                    if (i13 < 0) {
                        i7 = i2 + i13;
                    } else if (nextRowIndex == i12 || nextRowIndex == -1) {
                        String str = this.TAG;
                        StringBuilder h5 = A.a.h(nextRowIndex, i12, "scrollDown position : ", ", oldPosition : ", ", count : ");
                        h5.append(itemCount);
                        Log.e(str, h5.toString());
                        i12 = nextRowIndex;
                    } else {
                        this.mPosition = nextRowIndex;
                        this.mOldScrollAmount += i2;
                        i7 = 0;
                        i12 = nextRowIndex;
                    }
                } catch (Exception e) {
                    e = e;
                    i12 = nextRowIndex;
                    e.printStackTrace();
                    layoutInformer.scrollToIndexWithOffset(i12, -i2);
                }
            } catch (Exception e7) {
                e = e7;
                e.printStackTrace();
                layoutInformer.scrollToIndexWithOffset(i12, -i2);
            }
        }
        layoutInformer.scrollToIndexWithOffset(i12, -i2);
    }

    private double scrollTo(float f, float f5, int i2, int i7) {
        float f8 = f5 - ((((float) this.mVerticalThumbHeight) / 2.0f) + ((float) this.mRecyclerViewStart));
        int verticalVisibleLength = getVerticalVisibleLength();
        if (verticalVisibleLength <= 0) {
            return MapUtil.INVALID_LOCATION;
        }
        int i8 = (int) ((f8 / ((float) verticalVisibleLength)) * ((float) i2));
        if (i8 > i2) {
            i8 = i2;
        } else if (i8 < 0) {
            i8 = 0;
        }
        int i10 = i8 - i7;
        if (i8 > i2 || i8 < 0) {
            return MapUtil.INVALID_LOCATION;
        }
        return (double) ((int) (((float) i10) * getCalibrationRatio(f, this.mRecyclerViewWidth)));
    }

    private void scrollUp(LayoutInformer layoutInformer) {
        int i2 = this.mScrollAmount;
        if (i2 == 0) {
            layoutInformer.scrollToIndexWithOffset(0, 0);
            this.mPosition = 0;
            this.mOldScrollAmount = 0;
            return;
        }
        int i7 = this.mPosition;
        int i8 = i2 - this.mOldScrollAmount;
        if (i8 > 0) {
            scrollDown(layoutInformer);
            return;
        }
        int i10 = 0;
        while (i8 < 0) {
            try {
                int prevRowIndex = layoutInformer.getPrevRowIndex(i7);
                if (prevRowIndex >= 0) {
                    try {
                        int rowHeight = layoutInformer.getRowHeight(prevRowIndex);
                        i8 += rowHeight;
                        if (i8 > 0) {
                            i10 = i8 - rowHeight;
                        } else {
                            this.mPosition = prevRowIndex;
                            this.mOldScrollAmount -= rowHeight;
                            i10 = 0;
                            i7 = prevRowIndex;
                        }
                    } catch (Exception e) {
                        e = e;
                        i7 = prevRowIndex;
                        e.printStackTrace();
                        layoutInformer.scrollToIndexWithOffset(i7, -i10);
                    }
                } else {
                    return;
                }
            } catch (Exception e7) {
                e = e7;
                e.printStackTrace();
                layoutInformer.scrollToIndexWithOffset(i7, -i10);
            }
        }
        layoutInformer.scrollToIndexWithOffset(i7, -i10);
    }

    private void setQuickScrollState(LayoutInformer layoutInformer, boolean z) {
        if (layoutInformer != null) {
            layoutInformer.setQuickScrollState(z);
        }
    }

    private void startHapticFeedbackOnBg() {
        if (Features.isEnabled(Features.SUPPORT_DC_HAPTIC)) {
            ThreadUtil.runOnBgThread(new a(this, 0));
        }
    }

    private void startScrollPopupViewShowAnimation(final boolean z) {
        if (this.mScrollPopUpView != null) {
            Animator createScrollPopupViewAlphaScaleAnimator = createScrollPopupViewAlphaScaleAnimator(z);
            Animator createTagViewAlphaAnimator = this.mYearScrollTag.createTagViewAlphaAnimator(z);
            final AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator, boolean z) {
                    FastScroller.this.mScrollPopUpView.resetPivot();
                    animatorSet.removeAllListeners();
                    if (!z) {
                        FastScroller.this.hidePopup();
                        ViewUtils.setVisibleOrInvisible(FastScroller.this.mScrollPopUpDate, true);
                        ViewUtils.setVisibleOrInvisible(FastScroller.this.mScrollPopUpLocation, true);
                    }
                }

                public void onAnimationStart(Animator animator) {
                    FastScroller.this.mScrollPopUpView.setPivotX((float) FastScroller.this.mScrollPopUpView.getWidth());
                    if (!z) {
                        ViewUtils.setVisibleOrInvisible(FastScroller.this.mScrollPopUpDate, false);
                        ViewUtils.setVisibleOrInvisible(FastScroller.this.mScrollPopUpLocation, false);
                    }
                }
            });
            animatorSet.playTogether(new Animator[]{createScrollPopupViewAlphaScaleAnimator, createTagViewAlphaAnimator});
            animatorSet.start();
        }
    }

    private void updateScrollPosition(int i2, boolean z, int i7) {
        if (z) {
            calculateScroll();
            this.mDefaultMaxScroll = i7;
        }
        if (i7 == -1) {
            super.updateScrollPosition(i2);
            return;
        }
        int height = i7 - this.mRecyclerView.getHeight();
        int i8 = 0;
        if (height < 0) {
            height = 0;
        }
        if (!z) {
            this.mScrollAmount += i2;
        }
        int i10 = this.mScrollAmount;
        if (i10 < 0) {
            this.mScrollAmount = 0;
        } else if (i10 > height) {
            this.mScrollAmount = height;
        }
        int i11 = this.mRecyclerViewStart;
        if (height != 0) {
            i8 = (int) ((((double) getVerticalVisibleLength()) * ((double) this.mScrollAmount)) / ((double) height));
        }
        this.mVerticalThumbTop = i11 + i8;
        if ((i2 != 0 && !isQuickScrolling(this.mLayoutInformer)) || this.mGoToTopAnimator.isRunning()) {
            setState(1);
        }
    }

    private void verticalScrollBy(LayoutInformer layoutInformer, int i2) {
        int i7;
        int maxScroll = layoutInformer.getMaxScroll();
        if (maxScroll == -1) {
            i7 = this.mRecyclerView.computeVerticalScrollRange();
        } else {
            i7 = maxScroll - this.mRecyclerViewHeight;
        }
        if (this.mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
            this.mScrollAmount += i2;
            if (i2 >= 0) {
                scrollDown(layoutInformer);
            } else {
                scrollUp(layoutInformer);
            }
        } else {
            this.mRecyclerView.scrollBy(0, i2);
        }
        int i8 = this.mScrollAmount;
        if (i8 > i7) {
            this.mScrollAmount = i7;
        } else if (i8 < 0) {
            this.mScrollAmount = 0;
        }
        updateScrollPosition(0);
    }

    private void verticalScrollTo(float f, float f5) {
        int i2;
        int i7;
        LayoutInformer layoutInformer = this.mLayoutInformer;
        if (layoutInformer != null) {
            float min = Math.min((float) this.mRecyclerViewHeight, f5);
            if (INSENSITIVE_SCROLL) {
                if (Math.abs(this.mLastY - min) < ((float) Math.min((this.mAccMoveCount / 20) + 1, this.mMaxScrollThreshold))) {
                    updateScrollPosition(0);
                    return;
                }
                this.mLastY = min;
            }
            int maxScroll = layoutInformer.getMaxScroll();
            if (maxScroll == -1) {
                i7 = this.mRecyclerView.computeVerticalScrollRange();
                i2 = this.mRecyclerView.computeVerticalScrollOffset();
            } else {
                i7 = maxScroll - this.mRecyclerViewHeight;
                i2 = this.mScrollAmount;
            }
            int scrollTo = (int) scrollTo(f, min, i7, i2);
            if (this.mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                this.mScrollAmount += scrollTo;
                if (scrollTo >= 0) {
                    scrollDown(layoutInformer);
                } else {
                    scrollUp(layoutInformer);
                }
            } else {
                this.mRecyclerView.scrollBy(0, scrollTo);
            }
            int i8 = this.mScrollAmount;
            if (i8 > i7) {
                this.mScrollAmount = i7;
            } else if (i8 < 0) {
                this.mScrollAmount = 0;
            }
            updateScrollPosition(0);
        }
    }

    public void addScrollBarUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        super.addScrollBarUpdateListener(animatorUpdateListener);
        this.mGoToTopAnimator.removeAllUpdateListeners();
        this.mGoToTopAnimator.addUpdateListener(this.mGoToTopAnimationListener);
        this.mGoToTopAnimator.setInterpolator(PathInterpolator.create(0.22f, 0.5f, 0.0f, 1.0f));
        this.mGoToPositionAnimator.removeAllUpdateListeners();
        this.mGoToPositionAnimator.addUpdateListener(this.mGoToPositionAnimationListener);
    }

    public int calculateScrollbarHeight() {
        return (int) getHeight(0.0f, (float) this.mRecyclerView.computeVerticalScrollRange(), (float) this.mRecyclerView.getHeight());
    }

    public void clearAnimation() {
        super.clearAnimation();
        if (this.mGoToTopAnimator.isRunning()) {
            this.mGoToTopAnimator.cancel();
        }
    }

    public void destroy() {
        this.mLayoutInformer = null;
        this.mYearScrollTag.destroy();
    }

    public int drawVerticalScrollbar(Canvas canvas) {
        int drawVerticalScrollbar = super.drawVerticalScrollbar(canvas);
        if (this.mScrollPopUpView != null && this.mState == 2 && drawVerticalScrollbar >= this.mRecyclerViewStart) {
            ScrollText scrollText = getScrollText(drawVerticalScrollbar);
            if (scrollText == null) {
                this.mScrollPopUpView.setVisibility(4);
                return drawVerticalScrollbar;
            }
            if (!TextUtils.equals(this.previousDate, scrollText.getDate())) {
                startHapticFeedbackOnBg();
                this.previousDate = scrollText.getDate();
            }
            this.mScrollPopUpDate.setText(this.previousDate);
            String location = scrollText.getLocation();
            if (location == null || location.isEmpty() || !PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
                this.mScrollPopUpLocation.setTextSize(0.0f);
                this.mScrollPopUpView.setBackground(this.mRecyclerView.getContext().getDrawable(R$drawable.fast_scroll_label_single_line));
            } else {
                this.mScrollPopUpLocation.setTextSize(0, this.mScrollPopUpDate.getTextSize());
                this.mScrollPopUpLocation.setText(location);
                this.mScrollPopUpView.setBackground(this.mRecyclerView.getContext().getDrawable(R$drawable.fast_scroll_label_multi_line));
            }
            View view = this.mScrollPopUpView;
            view.setY((((float) (this.mVerticalThumbHeight - view.getHeight())) / 2.0f) + ((float) drawVerticalScrollbar));
            if (!ViewUtils.isVisible(this.mScrollPopUpView)) {
                ViewUtils.setVisibleOrInvisible(this.mScrollPopUpView, true);
                this.mYearScrollTag.draw(this.mScrollPopUpView.getVisibility());
                startScrollPopupViewShowAnimation(true);
            }
        }
        return drawVerticalScrollbar;
    }

    public int getExtraMargin() {
        if (isLayoutRTL()) {
            return this.mRecyclerView.getContext().getResources().getDimensionPixelSize(R$dimen.fast_scroll_popup_text_side_margin) + this.mVerticalThumbWidth + this.mSidePadding + this.mExtraDisplayPadding;
        }
        return -(this.mRecyclerView.getContext().getResources().getDimensionPixelSize(R$dimen.fast_scroll_popup_text_side_margin) + this.mVerticalThumbWidth + this.mSidePadding + this.mExtraDisplayPadding);
    }

    public int getItemCount() {
        if (this.mRecyclerView.getAdapter() != null) {
            return this.mRecyclerView.getAdapter().getItemCount();
        }
        return 0;
    }

    public LayoutInformer getLayoutInformer() {
        return this.mLayoutInformer;
    }

    public int getRecyclerViewHeight() {
        return getVerticalVisibleLength() + this.mVerticalThumbHeight + this.mTopMargin + this.mBottomMargin;
    }

    public int getRecyclerViewWidth() {
        return this.mRecyclerViewWidth;
    }

    public int getScrollOffset() {
        return this.mScrollAmount;
    }

    public int getThumbnailHeight() {
        return this.mVerticalThumbDrawable.getIntrinsicHeight();
    }

    public int getTouchArea(Context context, int i2) {
        return context.getResources().getDimensionPixelOffset(R$dimen.fast_scroll_popup_additional_touch_area) + this.mVerticalThumbWidth + i2;
    }

    public int getTouchOffset() {
        return 0;
    }

    public int getVerticalThumbHeight() {
        return this.mVerticalThumbHeight;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void goToPosition(int r6) {
        /*
            r5 = this;
            android.animation.ValueAnimator r0 = r5.mGoToPositionAnimator
            boolean r0 = r0.isRunning()
            if (r0 != 0) goto L_0x005a
            androidx.recyclerview.widget.RecyclerView r0 = r5.mRecyclerView
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            boolean r0 = r0 instanceof androidx.recyclerview.widget.LinearLayoutManager
            r1 = 1
            if (r0 == 0) goto L_0x002d
            androidx.recyclerview.widget.RecyclerView r0 = r5.mRecyclerView
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0
            int r2 = r0.findFirstCompletelyVisibleItemPosition()
            int r0 = r0.findLastCompletelyVisibleItemPosition()
            if (r6 < r2) goto L_0x0028
            if (r6 > r0) goto L_0x0028
            goto L_0x005a
        L_0x0028:
            if (r6 >= r2) goto L_0x002b
            goto L_0x002d
        L_0x002b:
            r0 = 0
            goto L_0x002e
        L_0x002d:
            r0 = r1
        L_0x002e:
            android.animation.ValueAnimator r2 = r5.mGoToPositionAnimator
            r3 = 500(0x1f4, double:2.47E-321)
            r2.setDuration(r3)
            androidx.recyclerview.widget.RecyclerView r2 = r5.mRecyclerView
            r5.setScrollbarHeight(r2)
            int r2 = r5.mRecyclerViewStart
            r3 = -1
            if (r2 != r3) goto L_0x0042
            r5.initializeRecyclerViewStart()
        L_0x0042:
            com.samsung.android.gallery.widget.listview.scroller.FastScroller$LayoutInformer r2 = r5.mLayoutInformer
            r5.setQuickScrollState(r2, r1)
            int r1 = r5.mScrollAmount
            r5.mGoTopBaseScroll = r1
            com.samsung.android.gallery.widget.listview.scroller.FastScroller$LayoutInformer r1 = r5.mLayoutInformer
            int r0 = r5.calculateScrollAmount(r1, r6, r0)
            r5.mGoToPositionScroll = r0
            r5.mGoToPosition = r6
            android.animation.ValueAnimator r5 = r5.mGoToPositionAnimator
            r5.start()
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.listview.scroller.FastScroller.goToPosition(int):void");
    }

    public void goToTop() {
        int jumpPosition;
        if (!this.mGoToTopAnimator.isRunning()) {
            int goToTopDuration = getGoToTopDuration();
            if (needToJump() && (jumpPosition = getJumpPosition(this.mLayoutInformer)) > 0) {
                this.mRecyclerView.scrollToPosition(jumpPosition);
            }
            this.mGoToTopAnimator.setDuration((long) goToTopDuration);
            setQuickScrollState(this.mLayoutInformer, true);
            setScrollbarHeight(this.mRecyclerView);
            if (this.mRecyclerViewStart == -1) {
                initializeRecyclerViewStart();
            }
            this.mGoTopBaseScroll = this.mScrollAmount;
            this.mGoToTopAnimator.start();
        }
    }

    public void hidePopup() {
        ViewUtils.setVisibleOrInvisible(this.mScrollPopUpView, false);
        this.mYearScrollTag.draw(4);
    }

    public void initializeRecyclerViewStart() {
        if (this.mLayoutInformer == null) {
            this.mLayoutInformer = (LayoutInformer) this.mRecyclerView.getAdapter();
        }
        LayoutInformer layoutInformer = this.mLayoutInformer;
        if (layoutInformer != null) {
            this.mRecyclerViewStart = layoutInformer.getScrollStart() + this.mTopMargin;
        }
    }

    public boolean isTouchedOnViewRectRange(MotionEvent motionEvent, int i2) {
        if (!isScrollVisible()) {
            return false;
        }
        return isPointInsideScrollBar((float) ((int) motionEvent.getX()), (float) (((int) motionEvent.getY()) - i2));
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (isDensityOrResolutionChanged(configuration)) {
            this.mLastDensity = configuration.densityDpi;
            this.mLastResolution = configuration.screenWidthDp * configuration.screenHeightDp;
            getTopAndBottomMargin();
            ThreadUtil.postOnUiThread(new a(this, 2));
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        LayoutInformer layoutInformer = this.mLayoutInformer;
        if (layoutInformer == null || !layoutInformer.isHideScroller()) {
            super.onDrawOver(canvas, recyclerView, state);
        } else {
            hide();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0031, code lost:
        if (r5 == 2) goto L_0x002f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(androidx.recyclerview.widget.RecyclerView r5, android.view.MotionEvent r6) {
        /*
            r4 = this;
            int r5 = r4.mState
            r0 = 0
            r1 = 2
            r2 = 1
            if (r5 != r2) goto L_0x0031
            float r5 = r6.getX()
            float r3 = r6.getY()
            boolean r5 = r4.isPointInsideScrollBar(r5, r3)
            int r6 = r6.getAction()
            if (r6 != 0) goto L_0x0034
            if (r5 == 0) goto L_0x0034
            r4.mDragState = r1
            r4.setState(r1)
            com.samsung.android.gallery.module.logger.AnalyticsLogger r5 = com.samsung.android.gallery.module.logger.AnalyticsLogger.getInstance()
            java.lang.String r6 = r4.mScreenId
            com.samsung.android.gallery.support.analytics.AnalyticsEventId r0 = com.samsung.android.gallery.support.analytics.AnalyticsEventId.EVENT_TOUCH_FAST_SCROLL
            java.lang.String r0 = r0.toString()
            r5.postLog(r6, r0)
        L_0x002f:
            r0 = r2
            goto L_0x0034
        L_0x0031:
            if (r5 != r1) goto L_0x0034
            goto L_0x002f
        L_0x0034:
            if (r0 == 0) goto L_0x003d
            java.lang.String r4 = r4.TAG
            java.lang.String r5 = "onInterceptTouchEvent"
            com.samsung.android.gallery.support.utils.Log.d(r4, r5)
        L_0x003d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.listview.scroller.FastScroller.onInterceptTouchEvent(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent):boolean");
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i2;
        if (this.mState != 0) {
            boolean z = SUPPORT_FAST_VIBRATE_INDEX;
            if (z) {
                if (this.mVelocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                }
                this.mVelocityTracker.addMovement(motionEvent);
            }
            if (motionEvent.getAction() == 0) {
                if (isPointInsideScrollBar(motionEvent.getX(), motionEvent.getY())) {
                    this.mDragState = 2;
                    setState(2);
                }
                this.mAccMoveCount = 0;
                this.mLastY = motionEvent.getY();
            } else if ((motionEvent.getAction() == 1 || motionEvent.getAction() == 3) && this.mState == 2) {
                setState(1);
                this.mDragState = 0;
                if (z) {
                    this.mVelocityTracker.clear();
                }
            } else if (motionEvent.getAction() == 2 && this.mState == 2) {
                show();
                if (this.mDragState == 2) {
                    this.mAccMoveCount++;
                    verticalScrollTo(motionEvent.getX(), motionEvent.getY() + ((float) getTouchOffset()));
                }
                if (z) {
                    this.mVelocityTracker.computeCurrentVelocity(1000);
                    if (Math.abs(this.mVelocityTracker.getYVelocity()) > 1000.0f) {
                        i2 = 24;
                    } else {
                        i2 = 26;
                    }
                    this.mFastVibrateIndex = i2;
                }
            }
        }
    }

    public void resetDefaultMaxScroll() {
        this.mDefaultMaxScroll = -1;
    }

    public void resetScrollerHeight() {
        this.mYearScrollTag.resetScrollHeight();
    }

    public void setExtraDisplayPadding(int i2) {
        super.setExtraDisplayPadding(i2);
        initializeScrollPopupView(this.mScrollPopUpView);
    }

    public void setScrollbarHeight(RecyclerView recyclerView) {
        Drawable drawable;
        if (this.mVerticalThumbHeight != -1) {
            return;
        }
        if (!useCustomScrollbar() || (drawable = this.mVerticalThumbDrawable) == null) {
            this.mVerticalThumbHeight = calculateScrollbarHeight();
        } else {
            this.mVerticalThumbHeight = drawable.getIntrinsicHeight();
        }
    }

    public void setState(int i2) {
        if (i2 == 2) {
            onDraggingStarted();
        } else if (this.mState == 2) {
            onDraggingEnded();
        } else if (i2 == 0) {
            hidePopup();
        }
        super.setState(i2);
    }

    public void updateScrollOffset(int i2) {
        this.mScrollAmount = i2;
    }

    public void updateScrollView() {
        super.updateScrollView();
        setScrollbarHeight(this.mRecyclerView);
        initializeScrollPopupView(this.mScrollPopUpView);
        updateScrollPosition(0);
    }

    public void updateScrollbarState(boolean z) {
        int i2;
        Drawable drawable = this.mVerticalThumbDrawable;
        if (z) {
            i2 = this.thumbnailTapColor;
        } else {
            i2 = this.thumbnailDefaultColor;
        }
        drawable.setTint(i2);
    }

    public void updateScrollPosition(int i2, boolean z) {
        if (this.mLayoutInformer == null) {
            this.mLayoutInformer = (LayoutInformer) this.mRecyclerView.getAdapter();
        }
        LayoutInformer layoutInformer = this.mLayoutInformer;
        if (layoutInformer != null) {
            updateScrollPosition(i2, z, layoutInformer.getMaxScroll());
        }
    }

    public void updateScrollPosition(int i2) {
        if (this.mLayoutInformer == null) {
            this.mLayoutInformer = (LayoutInformer) this.mRecyclerView.getAdapter();
        }
        LayoutInformer layoutInformer = this.mLayoutInformer;
        if (layoutInformer != null) {
            int maxScroll = layoutInformer.getMaxScroll();
            updateScrollPosition(i2, maxScroll != this.mDefaultMaxScroll, maxScroll);
        }
    }
}
