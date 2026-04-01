package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FastScroller extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {
    private static final int[] EMPTY_STATE_SET = new int[0];
    private static final int[] PRESSED_STATE_SET = {16842919};
    int mAnimationState;
    private int mDragState = 0;
    private final Runnable mHideRunnable;
    float mHorizontalDragX;
    private final int[] mHorizontalRange = new int[2];
    int mHorizontalThumbCenterX;
    private final StateListDrawable mHorizontalThumbDrawable;
    private final int mHorizontalThumbHeight;
    int mHorizontalThumbWidth;
    private final Drawable mHorizontalTrackDrawable;
    private final int mHorizontalTrackHeight;
    private final int mMargin;
    private boolean mNeedHorizontalScrollbar = false;
    private boolean mNeedVerticalScrollbar = false;
    private final RecyclerView.OnScrollListener mOnScrollListener;
    private RecyclerView mRecyclerView;
    private int mRecyclerViewHeight = 0;
    private int mRecyclerViewWidth = 0;
    private final int mScrollbarMinimumRange;
    final ValueAnimator mShowHideAnimator;
    private int mState = 0;
    float mVerticalDragY;
    private final int[] mVerticalRange = new int[2];
    int mVerticalThumbCenterY;
    final StateListDrawable mVerticalThumbDrawable;
    int mVerticalThumbHeight;
    private final int mVerticalThumbWidth;
    final Drawable mVerticalTrackDrawable;
    private final int mVerticalTrackWidth;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AnimatorListener extends AnimatorListenerAdapter {
        private boolean mCanceled = false;

        public AnimatorListener() {
        }

        public void onAnimationCancel(Animator animator) {
            this.mCanceled = true;
        }

        public void onAnimationEnd(Animator animator) {
            if (this.mCanceled) {
                this.mCanceled = false;
            } else if (((Float) FastScroller.this.mShowHideAnimator.getAnimatedValue()).floatValue() == 0.0f) {
                FastScroller fastScroller = FastScroller.this;
                fastScroller.mAnimationState = 0;
                fastScroller.setState(0);
            } else {
                FastScroller fastScroller2 = FastScroller.this;
                fastScroller2.mAnimationState = 2;
                fastScroller2.requestRedraw();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AnimatorUpdater implements ValueAnimator.AnimatorUpdateListener {
        public AnimatorUpdater() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            FastScroller.this.mVerticalThumbDrawable.setAlpha(floatValue);
            FastScroller.this.mVerticalTrackDrawable.setAlpha(floatValue);
            FastScroller.this.requestRedraw();
        }
    }

    public FastScroller(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i2, int i7, int i8) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.mShowHideAnimator = ofFloat;
        this.mAnimationState = 0;
        this.mHideRunnable = new Runnable() {
            public void run() {
                FastScroller.this.hide(500);
            }
        };
        this.mOnScrollListener = new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
                FastScroller.this.updateScrollPosition(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
            }
        };
        this.mVerticalThumbDrawable = stateListDrawable;
        this.mVerticalTrackDrawable = drawable;
        this.mHorizontalThumbDrawable = stateListDrawable2;
        this.mHorizontalTrackDrawable = drawable2;
        this.mVerticalThumbWidth = Math.max(i2, stateListDrawable.getIntrinsicWidth());
        this.mVerticalTrackWidth = Math.max(i2, drawable.getIntrinsicWidth());
        this.mHorizontalThumbHeight = Math.max(i2, stateListDrawable2.getIntrinsicWidth());
        this.mHorizontalTrackHeight = Math.max(i2, drawable2.getIntrinsicWidth());
        this.mScrollbarMinimumRange = i7;
        this.mMargin = i8;
        stateListDrawable.setAlpha(ScoverState.TYPE_NFC_SMART_COVER);
        drawable.setAlpha(ScoverState.TYPE_NFC_SMART_COVER);
        ofFloat.addListener(new AnimatorListener());
        ofFloat.addUpdateListener(new AnimatorUpdater());
        attachToRecyclerView(recyclerView);
    }

    private void cancelHide() {
        this.mRecyclerView.removeCallbacks(this.mHideRunnable);
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this);
        this.mRecyclerView.removeOnScrollListener(this.mOnScrollListener);
        cancelHide();
    }

    private void drawHorizontalScrollbar(Canvas canvas) {
        int i2 = this.mRecyclerViewHeight;
        int i7 = this.mHorizontalThumbHeight;
        int i8 = i2 - i7;
        int i10 = this.mHorizontalThumbCenterX;
        int i11 = this.mHorizontalThumbWidth;
        int i12 = i10 - (i11 / 2);
        this.mHorizontalThumbDrawable.setBounds(0, 0, i11, i7);
        this.mHorizontalTrackDrawable.setBounds(0, 0, this.mRecyclerViewWidth, this.mHorizontalTrackHeight);
        canvas.translate(0.0f, (float) i8);
        this.mHorizontalTrackDrawable.draw(canvas);
        canvas.translate((float) i12, 0.0f);
        this.mHorizontalThumbDrawable.draw(canvas);
        canvas.translate((float) (-i12), (float) (-i8));
    }

    private void drawVerticalScrollbar(Canvas canvas) {
        int i2 = this.mRecyclerViewWidth;
        int i7 = this.mVerticalThumbWidth;
        int i8 = i2 - i7;
        int i10 = this.mVerticalThumbCenterY;
        int i11 = this.mVerticalThumbHeight;
        int i12 = i10 - (i11 / 2);
        this.mVerticalThumbDrawable.setBounds(0, 0, i7, i11);
        this.mVerticalTrackDrawable.setBounds(0, 0, this.mVerticalTrackWidth, this.mRecyclerViewHeight);
        if (isLayoutRTL()) {
            this.mVerticalTrackDrawable.draw(canvas);
            canvas.translate((float) this.mVerticalThumbWidth, (float) i12);
            canvas.scale(-1.0f, 1.0f);
            this.mVerticalThumbDrawable.draw(canvas);
            canvas.scale(-1.0f, 1.0f);
            canvas.translate((float) (-this.mVerticalThumbWidth), (float) (-i12));
            return;
        }
        canvas.translate((float) i8, 0.0f);
        this.mVerticalTrackDrawable.draw(canvas);
        canvas.translate(0.0f, (float) i12);
        this.mVerticalThumbDrawable.draw(canvas);
        canvas.translate((float) (-i8), (float) (-i12));
    }

    private int[] getHorizontalRange() {
        int[] iArr = this.mHorizontalRange;
        int i2 = this.mMargin;
        iArr[0] = i2;
        iArr[1] = this.mRecyclerViewWidth - i2;
        return iArr;
    }

    private int[] getVerticalRange() {
        int[] iArr = this.mVerticalRange;
        int i2 = this.mMargin;
        iArr[0] = i2;
        iArr[1] = this.mRecyclerViewHeight - i2;
        return iArr;
    }

    private void horizontalScrollTo(float f) {
        int[] horizontalRange = getHorizontalRange();
        float max = Math.max((float) horizontalRange[0], Math.min((float) horizontalRange[1], f));
        if (Math.abs(((float) this.mHorizontalThumbCenterX) - max) >= 2.0f) {
            int scrollTo = scrollTo(this.mHorizontalDragX, max, horizontalRange, this.mRecyclerView.computeHorizontalScrollRange(), this.mRecyclerView.computeHorizontalScrollOffset(), this.mRecyclerViewWidth);
            if (scrollTo != 0) {
                this.mRecyclerView.scrollBy(scrollTo, 0);
            }
            this.mHorizontalDragX = max;
        }
    }

    private boolean isLayoutRTL() {
        if (ViewCompat.getLayoutDirection(this.mRecyclerView) == 1) {
            return true;
        }
        return false;
    }

    private void resetHideDelay(int i2) {
        cancelHide();
        this.mRecyclerView.postDelayed(this.mHideRunnable, (long) i2);
    }

    private int scrollTo(float f, float f5, int[] iArr, int i2, int i7, int i8) {
        int i10 = iArr[1] - iArr[0];
        if (i10 == 0) {
            return 0;
        }
        int i11 = i2 - i8;
        int i12 = (int) (((f5 - f) / ((float) i10)) * ((float) i11));
        int i13 = i7 + i12;
        if (i13 >= i11 || i13 < 0) {
            return 0;
        }
        return i12;
    }

    private void setupCallbacks() {
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.addOnItemTouchListener(this);
        this.mRecyclerView.addOnScrollListener(this.mOnScrollListener);
    }

    private void verticalScrollTo(float f) {
        int[] verticalRange = getVerticalRange();
        float max = Math.max((float) verticalRange[0], Math.min((float) verticalRange[1], f));
        if (Math.abs(((float) this.mVerticalThumbCenterY) - max) >= 2.0f) {
            int scrollTo = scrollTo(this.mVerticalDragY, max, verticalRange, this.mRecyclerView.computeVerticalScrollRange(), this.mRecyclerView.computeVerticalScrollOffset(), this.mRecyclerViewHeight);
            if (scrollTo != 0) {
                this.mRecyclerView.scrollBy(0, scrollTo);
            }
            this.mVerticalDragY = max;
        }
    }

    public void attachToRecyclerView(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                destroyCallbacks();
            }
            this.mRecyclerView = recyclerView;
            if (recyclerView != null) {
                setupCallbacks();
            }
        }
    }

    public void hide(int i2) {
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

    public boolean isPointInsideHorizontalThumb(float f, float f5) {
        if (f5 < ((float) (this.mRecyclerViewHeight - this.mHorizontalThumbHeight))) {
            return false;
        }
        int i2 = this.mHorizontalThumbCenterX;
        int i7 = this.mHorizontalThumbWidth;
        if (f < ((float) (i2 - (i7 / 2))) || f > ((float) ((i7 / 2) + i2))) {
            return false;
        }
        return true;
    }

    public boolean isPointInsideVerticalThumb(float f, float f5) {
        if (isLayoutRTL()) {
            if (f > ((float) this.mVerticalThumbWidth)) {
                return false;
            }
        } else if (f < ((float) (this.mRecyclerViewWidth - this.mVerticalThumbWidth))) {
            return false;
        }
        int i2 = this.mVerticalThumbCenterY;
        int i7 = this.mVerticalThumbHeight;
        if (f5 < ((float) (i2 - (i7 / 2))) || f5 > ((float) ((i7 / 2) + i2))) {
            return false;
        }
        return true;
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.mRecyclerViewWidth != this.mRecyclerView.getWidth() || this.mRecyclerViewHeight != this.mRecyclerView.getHeight()) {
            this.mRecyclerViewWidth = this.mRecyclerView.getWidth();
            this.mRecyclerViewHeight = this.mRecyclerView.getHeight();
            setState(0);
        } else if (this.mAnimationState != 0) {
            if (this.mNeedVerticalScrollbar) {
                drawVerticalScrollbar(canvas);
            }
            if (this.mNeedHorizontalScrollbar) {
                drawHorizontalScrollbar(canvas);
            }
        }
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        int i2 = this.mState;
        if (i2 == 1) {
            boolean isPointInsideVerticalThumb = isPointInsideVerticalThumb(motionEvent.getX(), motionEvent.getY());
            boolean isPointInsideHorizontalThumb = isPointInsideHorizontalThumb(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0 || (!isPointInsideVerticalThumb && !isPointInsideHorizontalThumb)) {
                return false;
            }
            if (isPointInsideHorizontalThumb) {
                this.mDragState = 1;
                this.mHorizontalDragX = (float) ((int) motionEvent.getX());
            } else if (isPointInsideVerticalThumb) {
                this.mDragState = 2;
                this.mVerticalDragY = (float) ((int) motionEvent.getY());
            }
            setState(2);
            return true;
        } else if (i2 == 2) {
            return true;
        } else {
            return false;
        }
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mState != 0) {
            if (motionEvent.getAction() == 0) {
                boolean isPointInsideVerticalThumb = isPointInsideVerticalThumb(motionEvent.getX(), motionEvent.getY());
                boolean isPointInsideHorizontalThumb = isPointInsideHorizontalThumb(motionEvent.getX(), motionEvent.getY());
                if (isPointInsideVerticalThumb || isPointInsideHorizontalThumb) {
                    if (isPointInsideHorizontalThumb) {
                        this.mDragState = 1;
                        this.mHorizontalDragX = (float) ((int) motionEvent.getX());
                    } else if (isPointInsideVerticalThumb) {
                        this.mDragState = 2;
                        this.mVerticalDragY = (float) ((int) motionEvent.getY());
                    }
                    setState(2);
                }
            } else if (motionEvent.getAction() == 1 && this.mState == 2) {
                this.mVerticalDragY = 0.0f;
                this.mHorizontalDragX = 0.0f;
                setState(1);
                this.mDragState = 0;
            } else if (motionEvent.getAction() == 2 && this.mState == 2) {
                show();
                if (this.mDragState == 1) {
                    horizontalScrollTo(motionEvent.getX());
                }
                if (this.mDragState == 2) {
                    verticalScrollTo(motionEvent.getY());
                }
            }
        }
    }

    public void requestRedraw() {
        this.mRecyclerView.invalidate();
    }

    public void setState(int i2) {
        if (i2 == 2 && this.mState != 2) {
            this.mVerticalThumbDrawable.setState(PRESSED_STATE_SET);
            cancelHide();
        }
        if (i2 == 0) {
            requestRedraw();
        } else {
            show();
        }
        if (this.mState == 2 && i2 != 2) {
            this.mVerticalThumbDrawable.setState(EMPTY_STATE_SET);
            resetHideDelay(1200);
        } else if (i2 == 1) {
            resetHideDelay(1500);
        }
        this.mState = i2;
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

    public void updateScrollPosition(int i2, int i7) {
        boolean z;
        boolean z3;
        int computeVerticalScrollRange = this.mRecyclerView.computeVerticalScrollRange();
        int i8 = this.mRecyclerViewHeight;
        if (computeVerticalScrollRange - i8 <= 0 || i8 < this.mScrollbarMinimumRange) {
            z = false;
        } else {
            z = true;
        }
        this.mNeedVerticalScrollbar = z;
        int computeHorizontalScrollRange = this.mRecyclerView.computeHorizontalScrollRange();
        int i10 = this.mRecyclerViewWidth;
        if (computeHorizontalScrollRange - i10 <= 0 || i10 < this.mScrollbarMinimumRange) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.mNeedHorizontalScrollbar = z3;
        boolean z7 = this.mNeedVerticalScrollbar;
        if (z7 || z3) {
            if (z7) {
                float f = (float) i8;
                this.mVerticalThumbCenterY = (int) ((((f / 2.0f) + ((float) i7)) * f) / ((float) computeVerticalScrollRange));
                this.mVerticalThumbHeight = Math.min(i8, (i8 * i8) / computeVerticalScrollRange);
            }
            if (this.mNeedHorizontalScrollbar) {
                float f5 = (float) i10;
                this.mHorizontalThumbCenterX = (int) ((((f5 / 2.0f) + ((float) i2)) * f5) / ((float) computeHorizontalScrollRange));
                this.mHorizontalThumbWidth = Math.min(i10, (i10 * i10) / computeHorizontalScrollRange);
            }
            int i11 = this.mState;
            if (i11 == 0 || i11 == 1) {
                setState(1);
            }
        } else if (this.mState != 0) {
            setState(0);
        }
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }
}
