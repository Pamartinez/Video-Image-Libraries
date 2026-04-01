package com.samsung.android.gallery.widget.listview.scroller;

import H3.l;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.MotionEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Scroller extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {
    protected final String TAG;
    /* access modifiers changed from: private */
    public int mAnimationState;
    protected int mBottomMargin;
    protected int mExtraDisplayPadding;
    private final Runnable mHideRunnable;
    private final RecyclerView.OnScrollListener mOnScrollListener;
    protected final RecyclerView mRecyclerView;
    protected int mRecyclerViewHeight;
    protected int mRecyclerViewStart;
    protected int mRecyclerViewWidth;
    private int mScrollDrawableId;
    private int mScrollState;
    private Consumer<Integer> mScrollerListener;
    /* access modifiers changed from: private */
    public final ValueAnimator mShowHideAnimator;
    protected int mSidePadding;
    int mState;
    private final boolean mUseCustomScrollbar;
    protected Drawable mVerticalThumbDrawable;
    protected int mVerticalThumbHeight;
    protected int mVerticalThumbTop;
    protected int mVerticalThumbWidth;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AnimatorListener extends AnimatorListenerAdapter {
        private boolean mCanceled;

        public /* synthetic */ AnimatorListener(Scroller scroller, int i2) {
            this();
        }

        public void onAnimationCancel(Animator animator) {
            this.mCanceled = true;
        }

        public void onAnimationEnd(Animator animator) {
            if (this.mCanceled) {
                this.mCanceled = false;
            } else if (((Float) Scroller.this.mShowHideAnimator.getAnimatedValue()).floatValue() == 0.0f) {
                Scroller.this.mAnimationState = 0;
                Scroller.this.setState(0);
            } else {
                Scroller.this.mAnimationState = 2;
                Scroller.this.requestRedraw();
            }
        }

        private AnimatorListener() {
            this.mCanceled = false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AnimatorUpdater implements ValueAnimator.AnimatorUpdateListener {
        public /* synthetic */ AnimatorUpdater(Scroller scroller, int i2) {
            this();
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Scroller.this.mVerticalThumbDrawable.setAlpha((int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f));
            Scroller.this.requestRedraw();
        }

        private AnimatorUpdater() {
        }
    }

    public Scroller(RecyclerView recyclerView) {
        this(recyclerView, -1);
    }

    private void cancelHide() {
        this.mRecyclerView.removeCallbacks(this.mHideRunnable);
    }

    private int getMaxTop() {
        return getVerticalVisibleLength() + this.mRecyclerViewStart;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        hide(500);
    }

    private void notifyScrollerListener(int i2) {
        Consumer<Integer> consumer = this.mScrollerListener;
        if (consumer != null && this.mScrollState != i2) {
            this.mScrollState = i2;
            consumer.accept(Integer.valueOf(i2));
        }
    }

    /* access modifiers changed from: private */
    public void requestRedraw() {
        this.mRecyclerView.invalidate();
    }

    private void resetHideDelay(int i2) {
        cancelHide();
        this.mRecyclerView.postDelayed(this.mHideRunnable, (long) i2);
    }

    public void addScrollBarUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.mShowHideAnimator.addUpdateListener(animatorUpdateListener);
    }

    public void clearAnimation() {
        if (this.mShowHideAnimator.isRunning()) {
            this.mShowHideAnimator.cancel();
        }
    }

    public void destroy() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this);
        this.mRecyclerView.removeOnScrollListener(this.mOnScrollListener);
    }

    public int drawVerticalScrollbar(Canvas canvas) {
        int verticalScrollbarStart = getVerticalScrollbarStart();
        int max = Math.max(0, Math.min(this.mVerticalThumbTop, getMaxTop()));
        Drawable drawable = this.mVerticalThumbDrawable;
        int i2 = this.mSidePadding;
        int i7 = this.mExtraDisplayPadding;
        drawable.setBounds(-(i2 + i7), 0, (this.mVerticalThumbWidth - i2) - i7, this.mVerticalThumbHeight);
        canvas.translate((float) verticalScrollbarStart, (float) max);
        if (isLayoutRTL()) {
            canvas.scale(-1.0f, 1.0f);
            this.mVerticalThumbDrawable.draw(canvas);
            canvas.scale(1.0f, 1.0f);
        } else {
            this.mVerticalThumbDrawable.draw(canvas);
        }
        canvas.translate((float) (-verticalScrollbarStart), (float) (-max));
        return max;
    }

    public int getBaseScrollbarDrawable(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16842853, typedValue, true);
        return typedValue.resourceId;
    }

    public float getHeight(float f, float f5, float f8) {
        float f10 = f5 - f;
        float f11 = 2.0f * f8;
        if (f10 > f11) {
            return 207.0f;
        }
        float f12 = (207.0f - f8) / f11;
        if (f10 > 0.0f) {
            return (f12 * f10) + f8;
        }
        return 0.0f;
    }

    public Drawable getScrollDrawable(Context context) {
        return context.getDrawable(this.mScrollDrawableId);
    }

    public int getScrollDrawableWidth(Drawable drawable) {
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return 0;
    }

    public int getScrollOffset() {
        return -1;
    }

    public int getVerticalScrollbarStart() {
        if (isLayoutRTL()) {
            return this.mVerticalThumbWidth;
        }
        return this.mRecyclerViewWidth - this.mVerticalThumbWidth;
    }

    public int getVerticalVisibleLength() {
        int i2 = this.mRecyclerViewStart;
        return ((this.mRecyclerView.getHeight() - i2) - (this.mRecyclerView.getPaddingBottom() + this.mBottomMargin)) - this.mVerticalThumbHeight;
    }

    public void goToPosition(int i2) {
        if (this.mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mRecyclerView.getLayoutManager();
            int findFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            int findLastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            if (i2 >= findFirstCompletelyVisibleItemPosition && i2 <= findLastCompletelyVisibleItemPosition) {
                return;
            }
        }
        this.mRecyclerView.smoothScrollToPosition(i2);
    }

    public void goToTop() {
        this.mRecyclerView.smoothScrollToPosition(0);
    }

    public void hide() {
        hide(0);
    }

    public void initializeRecyclerViewStart() {
        this.mRecyclerViewStart = 0;
    }

    public boolean isHidden() {
        if (this.mState == 0) {
            return true;
        }
        return false;
    }

    public boolean isLayoutRTL() {
        return Features.isEnabled(Features.IS_RTL);
    }

    public boolean isScrollVisible() {
        if (this.mAnimationState != 0) {
            return true;
        }
        return false;
    }

    public boolean isTouchedOnViewRectRange(MotionEvent motionEvent, int i2) {
        return false;
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int height = (this.mRecyclerView.getHeight() - this.mRecyclerView.getPaddingBottom()) - this.mBottomMargin;
        int i2 = this.mRecyclerViewStart;
        initializeRecyclerViewStart();
        if (this.mRecyclerViewWidth == this.mRecyclerView.getWidth() && this.mRecyclerViewHeight == height) {
            if (i2 != this.mRecyclerViewStart) {
                updateScrollView();
                setState(0);
                resetHideDelay(500);
            }
            if (this.mRecyclerView.isAnimating()) {
                hide();
            } else if (this.mAnimationState != 0) {
                drawVerticalScrollbar(canvas);
            }
        } else {
            this.mRecyclerViewWidth = this.mRecyclerView.getWidth();
            this.mRecyclerViewHeight = height;
            updateScrollView();
            setState(0);
        }
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        return false;
    }

    public void resetScrollbarHeight() {
        this.mVerticalThumbHeight = -1;
    }

    public void setExtraDisplayPadding(int i2) {
        this.mExtraDisplayPadding = i2;
    }

    public void setScrollbarHeight(RecyclerView recyclerView) {
        if (this.mVerticalThumbHeight == -1) {
            this.mVerticalThumbHeight = (int) getHeight(0.0f, (float) recyclerView.computeVerticalScrollRange(), (float) recyclerView.getHeight());
        }
    }

    public void setScrollerListener(Consumer<Integer> consumer) {
        this.mScrollerListener = consumer;
    }

    public void setSidePadding(int i2) {
        this.mSidePadding = i2;
    }

    public void setState(int i2) {
        if (i2 == 2 && this.mState != 2) {
            cancelHide();
        }
        if (i2 == 0) {
            requestRedraw();
        } else {
            show();
        }
        if (this.mState == 2 && i2 != 2) {
            resetHideDelay(1200);
        } else if (i2 == 1) {
            resetHideDelay(1500);
        }
        this.mState = i2;
        notifyScrollerListener(i2);
    }

    public void show() {
        int i2 = this.mAnimationState;
        if (i2 != 0) {
            if (i2 == 3) {
                this.mShowHideAnimator.cancel();
            } else {
                return;
            }
        }
        this.mAnimationState = 1;
        ValueAnimator valueAnimator = this.mShowHideAnimator;
        valueAnimator.setFloatValues(new float[]{((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f});
        this.mShowHideAnimator.setDuration(500);
        this.mShowHideAnimator.setStartDelay(0);
        this.mShowHideAnimator.start();
    }

    public void updateScrollPosition(int i2) {
        this.mVerticalThumbTop = (int) ((((float) getVerticalVisibleLength()) * ((float) this.mRecyclerView.computeVerticalScrollOffset())) / ((float) (this.mRecyclerView.computeVerticalScrollRange() - this.mRecyclerView.getHeight())));
        if (i2 != 0) {
            setState(1);
        }
    }

    public void updateScrollView() {
        Drawable mutate = getScrollDrawable(this.mRecyclerView.getContext()).mutate();
        this.mVerticalThumbDrawable = mutate;
        this.mVerticalThumbWidth = getScrollDrawableWidth(mutate);
        resetScrollbarHeight();
        this.mVerticalThumbDrawable.setAlpha(ScoverState.TYPE_NFC_SMART_COVER);
    }

    public boolean useCustomScrollbar() {
        return this.mUseCustomScrollbar;
    }

    public Scroller(RecyclerView recyclerView, int i2) {
        this.TAG = getClass().getSimpleName();
        this.mState = 0;
        this.mHideRunnable = new l(20, this);
        this.mVerticalThumbHeight = -1;
        this.mRecyclerViewWidth = 0;
        this.mRecyclerViewHeight = 0;
        this.mRecyclerViewStart = -1;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.mShowHideAnimator = ofFloat;
        this.mAnimationState = 0;
        this.mExtraDisplayPadding = 0;
        this.mScrollState = 0;
        AnonymousClass1 r32 = new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
                Scroller.this.setScrollbarHeight(recyclerView);
                Scroller scroller = Scroller.this;
                if (scroller.mRecyclerViewStart == -1) {
                    scroller.initializeRecyclerViewStart();
                }
                Scroller.this.updateScrollPosition(i7);
            }
        };
        this.mOnScrollListener = r32;
        boolean z = i2 != -1;
        this.mUseCustomScrollbar = z;
        Context context = recyclerView.getContext();
        this.mScrollDrawableId = !z ? getBaseScrollbarDrawable(context) : i2;
        Drawable scrollDrawable = getScrollDrawable(context);
        if (scrollDrawable != null) {
            Drawable mutate = scrollDrawable.mutate();
            this.mVerticalThumbDrawable = mutate;
            this.mVerticalThumbWidth = getScrollDrawableWidth(mutate);
            this.mVerticalThumbDrawable.setAlpha(ScoverState.TYPE_NFC_SMART_COVER);
        }
        ofFloat.addListener(new AnimatorListener(this, 0));
        ofFloat.addUpdateListener(new AnimatorUpdater(this, 0));
        this.mBottomMargin = 0;
        this.mRecyclerView = recyclerView;
        recyclerView.addItemDecoration(this);
        recyclerView.addOnItemTouchListener(this);
        recyclerView.addOnScrollListener(r32);
    }

    private void hide(int i2) {
        int i7 = this.mAnimationState;
        if (i7 == 1) {
            this.mShowHideAnimator.cancel();
        } else if (i7 != 2) {
            return;
        }
        this.mAnimationState = 3;
        ValueAnimator valueAnimator = this.mShowHideAnimator;
        valueAnimator.setFloatValues(new float[]{((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f});
        this.mShowHideAnimator.setDuration((long) i2);
        this.mShowHideAnimator.start();
    }

    public void updateScrollPosition(int i2, boolean z) {
        updateScrollPosition(i2);
    }

    public void hidePopup() {
    }

    public void resetDefaultMaxScroll() {
    }

    public void resetScrollerHeight() {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    public void updateScrollOffset(int i2) {
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }
}
