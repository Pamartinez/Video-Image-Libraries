package com.samsung.android.gallery.widget.story.transitory;

import W8.C0579a;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import bc.C0585b;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.listview.InterceptDispatchTouchListener;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.abstraction.IDuration;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPlayableViewPager extends StoriesViewPager {
    private final ScheduleAnimatorCreator mAnimatorCreator = new ScheduleAnimatorCreator(0);
    protected final IDuration mDurationMeasure;
    private final AtomicReference<View> mRecapItemView = new AtomicReference<>();
    private ValueAnimator mScheduleAnimator;
    private float mTouchX = Float.MIN_VALUE;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ScheduleAnimatorCreator {
        private ValueAnimator mBaseAnimator;

        public /* synthetic */ ScheduleAnimatorCreator(int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public ValueAnimator create() {
            if (this.mBaseAnimator == null) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
                this.mBaseAnimator = ofFloat;
                SimpleAnimator.ignoreDurationScaleIfDisabled(ofFloat);
            }
            return this.mBaseAnimator.clone();
        }

        private ScheduleAnimatorCreator() {
        }
    }

    public StoriesPlayableViewPager(ViewGroup viewGroup, IDuration iDuration) {
        super(viewGroup);
        this.mDurationMeasure = iDuration;
    }

    private void captureMotionEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & ScoverState.TYPE_NFC_SMART_COVER;
        if (action != 0) {
            if (!(action == 1 || action == 3)) {
                if (action != 5) {
                    if (action != 6) {
                        return;
                    }
                }
            }
            this.mTouchX = Float.MIN_VALUE;
            this.mRecapItemView.set((Object) null);
            return;
        }
        this.mTouchX = motionEvent.getX();
    }

    private void captureRecapSettling() {
        ListViewHolder currentViewHolder = getCurrentViewHolder();
        if (currentViewHolder == null) {
            return;
        }
        if (findChildViewPager(currentViewHolder.itemView) == null || !isScrollSettling()) {
            this.mRecapItemView.set((Object) null);
            return;
        }
        this.mRecapItemView.set(currentViewHolder.itemView);
        Log.d(this.TAG, "captureSettling", Integer.valueOf(this.mViewPager.getCurrentItem()));
    }

    private void clearTimer() {
        Optional.ofNullable(this.mScheduleAnimator).ifPresent(new C0579a(19));
        this.mScheduleAnimator = null;
    }

    private ValueAnimator createScheduleAnimator(int i2) {
        ValueAnimator duration = this.mAnimatorCreator.create().setDuration((long) i2);
        duration.addListener(new SimpleAnimatorListener() {
            boolean cancel;

            public void onAnimationCancel(Animator animator) {
                this.cancel = true;
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.cancel) {
                    StoriesPlayableViewPager.this.onNextAlarm();
                }
            }
        });
        return duration;
    }

    private ViewGroup findChildTouchLayer(View view) {
        ViewPager2 findChildViewPager = findChildViewPager(view);
        if (findChildViewPager != null) {
            return (ViewGroup) findChildViewPager.getParent();
        }
        return null;
    }

    private ViewPager2 findChildViewPager(View view) {
        if (view instanceof ViewPager2) {
            return (ViewPager2) view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            ViewPager2 findChildViewPager = findChildViewPager(viewGroup.getChildAt(i2));
            if (findChildViewPager != null) {
                return findChildViewPager;
            }
        }
        return null;
    }

    private LinearSmoothScroller getScroller(int i2) {
        AnonymousClass1 r0 = new LinearSmoothScroller(this.mViewPager.getContext()) {
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return (450.0f / (((float) displayMetrics.widthPixels) / 160.0f)) / ((float) displayMetrics.densityDpi);
            }
        };
        r0.setTargetPosition(i2);
        return r0;
    }

    private boolean handleNextOnChild(int i2) {
        ListViewHolder viewHolder = getViewHolder(i2);
        if (!(viewHolder instanceof IRecapContent) || !((IRecapContent) viewHolder).moveToNext()) {
            return false;
        }
        return true;
    }

    private boolean isPlayable() {
        StoriesViewPagerAdapter storiesViewPagerAdapter;
        ViewPagerListener viewPagerListener = this.mCallback;
        if (viewPagerListener == null || !viewPagerListener.isPlayable() || (storiesViewPagerAdapter = this.mAdapter) == null || storiesViewPagerAdapter.getItemCount() <= 1 || this.mAccessibilityState.isVoiceServiceEnabled()) {
            return false;
        }
        return true;
    }

    private boolean isScrollSettling() {
        if (this.mViewPager.getScrollState() == 2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetViewProperty$0() {
        this.mViewPager.requestTransform();
    }

    private void moveToNext(int i2, boolean z) {
        resetViewProperty(i2, z);
        RecyclerView.LayoutManager layoutManager = getRecyclerView().getLayoutManager();
        if (layoutManager == null || !z) {
            setCurrentItem(i2, false, 0);
        } else {
            layoutManager.startSmoothScroll(getScroller(i2));
        }
    }

    /* access modifiers changed from: private */
    public boolean onInterceptDispatchTouchByChild(MotionEvent motionEvent) {
        View view = this.mRecapItemView.get();
        captureMotionEvent(motionEvent);
        if (view == null) {
            return false;
        }
        ViewGroup findChildTouchLayer = findChildTouchLayer(view);
        if (findChildTouchLayer == null) {
            return true;
        }
        findChildTouchLayer.dispatchTouchEvent(motionEvent);
        return true;
    }

    /* access modifiers changed from: private */
    public void onNextAlarm() {
        int i2;
        int currentItem = getCurrentItem();
        if (!handleNextOnChild(currentItem)) {
            boolean z = true;
            if (this.mAdapter.isLast(currentItem)) {
                i2 = 0;
            } else {
                i2 = currentItem + 1;
            }
            if (i2 == 0) {
                z = false;
            }
            moveToNext(i2, z);
        }
        this.mScheduleAnimator = null;
        start(StatusCodes.INPUT_MISSING);
    }

    private void resetViewProperty(int i2, boolean z) {
        if (!z) {
            restoreViewProperty(i2);
            ThreadUtil.postOnUiThread(new C0585b(this, 0));
        }
    }

    private void restoreViewProperty(int i2) {
        Optional.ofNullable(getViewHolder(i2)).ifPresent(new C0579a(20));
    }

    private void startInternal() {
        ValueAnimator valueAnimator = this.mScheduleAnimator;
        if (valueAnimator == null) {
            ValueAnimator createScheduleAnimator = createScheduleAnimator(this.mDurationMeasure.getDuration(getMediaItem(this.mViewPager.getCurrentItem())));
            this.mScheduleAnimator = createScheduleAnimator;
            createScheduleAnimator.start();
        } else if (valueAnimator.isPaused()) {
            this.mScheduleAnimator.resume();
        }
    }

    public InterceptDispatchTouchListener createTouchInterceptor() {
        return new InterceptDispatchTouchListener() {
            public boolean onInterceptDispatchTouchEvent(MotionEvent motionEvent) {
                Supplier<Boolean> supplier = StoriesPlayableViewPager.this.mTouchable;
                if (supplier != null && !supplier.get().booleanValue()) {
                    return true;
                }
                ViewPagerTouchDelegate viewPagerTouchDelegate = StoriesPlayableViewPager.this.mTouchDelegate;
                if ((viewPagerTouchDelegate == null || !viewPagerTouchDelegate.onInterceptDispatchTouchEvent(motionEvent)) && !StoriesPlayableViewPager.this.onInterceptDispatchTouchByChild(motionEvent)) {
                    return StoriesPlayableViewPager.this.mViewPager.dispatchTouchEvent(motionEvent);
                }
                return true;
            }

            public boolean supportHover() {
                return StoriesPlayableViewPager.this.mAccessibilityState.isVoiceServiceEnabled();
            }
        };
    }

    public void destroy() {
        clearTimer();
        super.destroy();
    }

    public ListViewHolder getPreviewableViewHolder() {
        ListViewHolder currentViewHolder = getCurrentViewHolder();
        if (currentViewHolder instanceof IRecapContent) {
            return ((IRecapContent) currentViewHolder).getPreviewableViewHolder();
        }
        return super.getPreviewableViewHolder();
    }

    public PageTransformer getTransformer() {
        if (this.mPageTransformer == null) {
            StoriesPageTransformer storiesPageTransformer = new StoriesPageTransformer();
            this.mPageTransformer = storiesPageTransformer;
            storiesPageTransformer.setTransformListener(this.mCallback);
        }
        return this.mPageTransformer;
    }

    public void onAccessibilityStateChanged(boolean z) {
        if (!z) {
            start();
        }
    }

    public void onPageSelected(int i2) {
        super.onPageSelected(i2);
        captureRecapSettling();
    }

    public void onTouchCancel() {
        start();
    }

    public void onTouchUp() {
        start();
    }

    public void pause() {
        Optional.ofNullable(this.mScheduleAnimator).ifPresent(new C0579a(18));
    }

    public void resume() {
        start();
    }

    public void setPageViewInfo(int i2, int i7, int i8, int i10) {
        super.setPageViewInfo(i2, i7, i8, i10);
        PageTransformer pageTransformer = this.mPageTransformer;
        if (pageTransformer == null) {
            PageTransformer transformer = getTransformer();
            this.mPageTransformer.setPageMargin(this.mSideMargin, i8, i10);
            this.mViewPager.setPageTransformer(transformer);
            return;
        }
        pageTransformer.setPageMargin(this.mSideMargin, i8, i10);
        this.mViewPager.requestTransform();
    }

    public void start() {
        if (isPlayable()) {
            startInternal();
        }
    }

    public void stop() {
        clearTimer();
    }

    public void swipe() {
        clearTimer();
    }

    public void start(int i2) {
        ThreadUtil.postOnUiThreadDelayed(new C0585b(this, 1), (long) i2);
    }
}
