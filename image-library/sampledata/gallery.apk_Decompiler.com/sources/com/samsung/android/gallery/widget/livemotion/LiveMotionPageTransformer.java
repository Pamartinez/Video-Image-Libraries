package com.samsung.android.gallery.widget.livemotion;

import Ba.p;
import I4.b;
import I4.g;
import Lb.a;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.abstraction.TransformBuilder;
import com.samsung.android.gallery.widget.livemotion.abstraction.TransformListener;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransform;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransformAlpha;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransformSwipe;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.Def;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LiveMotionPageTransformer extends SimpleAnimatorListener implements ViewPager2.PageTransformer, ValueAnimator.AnimatorUpdateListener {
    protected Animator mAnimator;
    private final ScheduleAnimatorCreator mAnimatorCreator = new ScheduleAnimatorCreator(0);
    private long mDurationTime = Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS;
    protected Interpolator mInterpolator;
    private boolean mIsSimpleSlide;
    protected final ViewPager2.PageTransformer mKeepCenterTransform = new a(this, 0);
    private TransformListener mListener;
    protected MODE mMode = MODE.SLIDESHOW;
    private float mPageInOutDelay = 1.0f;
    private final AtomicBoolean mPageInOutStarted = new AtomicBoolean(false);
    protected final List<PageTransform> mPageTransforms = new ArrayList();
    protected final AtomicReference<Float> mProgress = new AtomicReference<>(Float.valueOf(0.0f));
    private final ViewPager2.PageTransformer mRestoreTransform = new PageTransformSwipe(0);
    private final ViewPager2.PageTransformer mSwipeTransform;
    protected ViewPager2 mViewPager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MODE {
        SLIDESHOW,
        SWIPE,
        RESTORE;

        public boolean isRestoreMode() {
            if (this == RESTORE) {
                return true;
            }
            return false;
        }

        public boolean isSlideShow() {
            if (this == SLIDESHOW) {
                return true;
            }
            return false;
        }

        public boolean isSwipeMode() {
            if (this == SWIPE) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ScheduleAnimatorCreator {
        private ValueAnimator mBaseAnimator;
        private int mViewWidth;

        public /* synthetic */ ScheduleAnimatorCreator(int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public ValueAnimator create(ViewPager2 viewPager2) {
            if (this.mBaseAnimator == null || this.mViewWidth != viewPager2.getWidth()) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) viewPager2.getWidth(), 0.0f});
                this.mBaseAnimator = ofFloat;
                SimpleAnimator.ignoreDurationScaleIfDisabled(ofFloat);
                this.mViewWidth = viewPager2.getWidth();
            }
            return this.mBaseAnimator.clone();
        }

        private ScheduleAnimatorCreator() {
        }
    }

    public LiveMotionPageTransformer(Context context) {
        this.mSwipeTransform = new PageTransformSwipe(context.getResources().getDimensionPixelOffset(R$dimen.story_highlight_viewpager_gap));
    }

    private boolean isPageInOutPosition(float f) {
        if (f >= 0.0f || this.mPageInOutDelay > Math.abs(f) || this.mPageInOutStarted.get()) {
            return false;
        }
        return true;
    }

    private boolean isRestoreMode() {
        return this.mMode.isRestoreMode();
    }

    private void keepGoing(ViewPager2 viewPager2) {
        float f = -this.mProgress.get().floatValue();
        Log.d("LiveMotionPageTransformer", "keepGoing : " + f);
        clearWithoutCancel();
        runKeepGoingAnimation(viewPager2);
        viewPager2.fakeDragBy(adjustRtlPosition(f));
        viewPager2.endFakeDrag();
        setPageInOut(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view, float f) {
        if (f == 0.0f || f == -1.0f) {
            view.setTranslationX(0.0f);
        } else {
            view.setTranslationX(adjustRtlPosition(((float) view.getWidth()) * (-f)));
        }
        view.setTranslationY(0.0f);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$transformPageInternal$2(View view, float f, ListViewHolder listViewHolder, PageTransform pageTransform) {
        if ((pageTransform instanceof PageTransformAlpha) || pageTransform.hasTargetView()) {
            pageTransform.transformPage(view, f);
        } else if (listViewHolder != null) {
            pageTransform.transformPage(listViewHolder.getDecoView(35), f);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$transformSwipeInternal$1(ListViewHolder listViewHolder, PageTransform pageTransform) {
        if (pageTransform.hasTargetView()) {
            ViewUtils.setScale(listViewHolder.getDecoView(36), 1.0f, 1.0f);
        }
    }

    private boolean outOfRangePage(float f) {
        if (f < -1.0f || 1.0f < f) {
            return true;
        }
        return false;
    }

    private void restore(ViewPager2 viewPager2, MODE mode) {
        if (mode == MODE.SLIDESHOW) {
            float width = ((float) viewPager2.getWidth()) - this.mProgress.get().floatValue();
            Log.d("LiveMotionPageTransformer", "restore : " + width);
            viewPager2.fakeDragBy(adjustRtlPosition(width));
        }
    }

    private void runKeepGoingAnimation(ViewPager2 viewPager2) {
        AnimatorSet animatorSet = new AnimatorSet();
        int currentItem = viewPager2.getCurrentItem();
        ArrayList arrayList = new ArrayList();
        int i2 = currentItem + 1;
        for (int i7 = currentItem - 1; i7 <= i2; i7++) {
            View itemView = getItemView(viewPager2, i7);
            if (!(itemView == null || itemView.getAlpha() == 1.0f)) {
                arrayList.add(new AlphaAnimator(itemView, Math.max(itemView.getAlpha(), 0.7f), 1.0f));
            }
        }
        if (!arrayList.isEmpty()) {
            animatorSet.playTogether(arrayList);
            animatorSet.setDuration(250).start();
        }
    }

    public final float adjustRtlPosition(float f) {
        if (Features.isEnabled(Features.IS_RTL)) {
            return -f;
        }
        return f;
    }

    public void cancel() {
        Animator animator = this.mAnimator;
        if (animator != null) {
            animator.cancel();
            this.mAnimator.removeAllListeners();
            this.mAnimator = null;
        }
        this.mInterpolator = null;
        this.mPageTransforms.clear();
        this.mPageInOutStarted.set(false);
    }

    public void clearWithoutCancel() {
        Animator animator = this.mAnimator;
        if (animator != null) {
            animator.pause();
            this.mAnimator.removeAllListeners();
            this.mAnimator = null;
        }
        this.mInterpolator = null;
        this.mPageTransforms.clear();
        this.mPageInOutStarted.set(false);
    }

    public PageTransform getCurrentPageTransform() {
        if (this.mPageTransforms.isEmpty()) {
            return null;
        }
        return this.mPageTransforms.get(0);
    }

    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    public View getItemView(ViewPager2 viewPager2, int i2) {
        try {
            RecyclerView.ViewHolder viewHolder = getViewHolder(viewPager2, i2);
            if (viewHolder != null) {
                return viewHolder.itemView;
            }
            return null;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("fail to find itemView="), "LiveMotionPageTransformer");
            return null;
        }
    }

    public RecyclerView.ViewHolder getViewHolder(ViewPager2 viewPager2, int i2) {
        try {
            return ((RecyclerView) viewPager2.getChildAt(0)).findViewHolderForLayoutPosition(i2);
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("fail to find ViewHolder="), "LiveMotionPageTransformer");
            return null;
        }
    }

    public boolean isPageInOutStarted() {
        return this.mPageInOutStarted.get();
    }

    public boolean isPaused() {
        Animator animator = this.mAnimator;
        if (animator == null || !animator.isPaused()) {
            return false;
        }
        return true;
    }

    public boolean isPlaying() {
        Animator animator = this.mAnimator;
        if (animator == null || animator.isPaused()) {
            return false;
        }
        return true;
    }

    public boolean isSlideShowEnabled() {
        if (!this.mMode.isSlideShow() || this.mAnimator == null) {
            return false;
        }
        return true;
    }

    public boolean isSwipeMode() {
        return this.mMode.isSwipeMode();
    }

    public void onAnimationCancel(Animator animator) {
        this.mPageTransforms.forEach(new b(19));
        ViewPager2 viewPager2 = this.mViewPager;
        viewPager2.fakeDragBy(adjustRtlPosition(((float) viewPager2.getWidth()) - this.mProgress.get().floatValue()));
        ViewPager2 viewPager22 = this.mViewPager;
        ListViewHolder listViewHolder = (ListViewHolder) getViewHolder(viewPager22, viewPager22.getCurrentItem());
        if (listViewHolder != null) {
            listViewHolder.resetViewProperty();
        }
    }

    public void onAnimationEnd(Animator animator) {
        setPageInOut(false);
        this.mPageTransforms.clear();
        this.mViewPager.endFakeDrag();
        TransformListener transformListener = this.mListener;
        if (transformListener != null) {
            transformListener.onTransformEnd();
        }
    }

    public void onAnimationStart(Animator animator) {
        this.mViewPager.beginFakeDrag();
        setPageInOut(false);
        TransformListener transformListener = this.mListener;
        if (transformListener != null) {
            transformListener.onTransformStart();
        }
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        try {
            if (!this.mViewPager.isFakeDragging()) {
                this.mViewPager.beginFakeDrag();
                boolean fakeDragBy = this.mViewPager.fakeDragBy(adjustRtlPosition(this.mProgress.get().floatValue() - ((float) this.mViewPager.getWidth())));
                Log.d("LiveMotionPageTransformer", "onAnimationUpdate fakeDrag = " + fakeDragBy);
            }
            Float f = (Float) valueAnimator.getAnimatedValue();
            this.mViewPager.fakeDragBy(adjustRtlPosition(f.floatValue() - this.mProgress.get().floatValue()));
            this.mProgress.set(f);
        } catch (Exception e) {
            Objects.requireNonNull(valueAnimator);
            ThreadUtil.postOnUiThread(new p(valueAnimator, 1));
            A.a.s(e, new StringBuilder("fail transform = "), "LiveMotionPageTransformer");
        }
    }

    public void pause() {
        Animator animator = this.mAnimator;
        if (animator != null) {
            animator.pause();
        }
    }

    public void preparePause(ViewPager2 viewPager2) {
        if (this.mAnimator != null && isPageInOutStarted()) {
            this.mMode = MODE.RESTORE;
            keepGoing(viewPager2);
            switchMode(true);
        }
    }

    public void prepareSwipe(ViewPager2 viewPager2) {
        MODE mode = this.mMode;
        this.mMode = MODE.RESTORE;
        if (this.mAnimator != null) {
            if (isPageInOutStarted()) {
                keepGoing(viewPager2);
            } else {
                restore(viewPager2, mode);
            }
        }
        switchMode(false);
    }

    public void resume() {
        Animator animator = this.mAnimator;
        if (animator != null) {
            animator.resume();
        }
    }

    public final void setPageInOut(boolean z) {
        this.mPageInOutStarted.set(z);
    }

    public LiveMotionPageTransformer setTransform(TransformBuilder transformBuilder) {
        this.mPageTransforms.clear();
        this.mPageTransforms.addAll(transformBuilder.buildNext(this.mViewPager));
        this.mInterpolator = transformBuilder.getInterpolator();
        this.mPageInOutDelay = transformBuilder.getPageInOutDelay();
        this.mDurationTime = (long) transformBuilder.getDuration();
        this.mIsSimpleSlide = transformBuilder.isSimpleSlide();
        return this;
    }

    public void setTransformListener(TransformListener transformListener) {
        this.mListener = transformListener;
    }

    public void setViewPager(ViewPager2 viewPager2) {
        this.mViewPager = viewPager2;
    }

    public void switchMode(boolean z) {
        MODE mode;
        if (z) {
            mode = MODE.SLIDESHOW;
        } else {
            mode = MODE.SWIPE;
        }
        this.mMode = mode;
    }

    public void transform(ViewPager2 viewPager2) {
        this.mAnimator = transformVelocityAboutDuration(viewPager2, this.mDurationTime);
    }

    public void transformPage(View view, float f) {
        if (isRestoreMode()) {
            this.mRestoreTransform.transformPage(view, f);
        } else if (isSwipeMode()) {
            this.mSwipeTransform.transformPage(view, f);
            transformSwipeInternal(view, f);
        } else {
            ViewUtils.setTranslationX(view, 0.0f);
            ViewUtils.setTranslationY(view, 0.0f);
            if (!outOfRangePage(f) && isSlideShowEnabled()) {
                transformPageInternal(view, f);
                if (this.mListener != null && isPageInOutPosition(f)) {
                    setPageInOut(true);
                }
            }
        }
    }

    public void transformPageInternal(View view, float f) {
        if (!this.mPageTransforms.isEmpty() && isSlideShowEnabled()) {
            this.mKeepCenterTransform.transformPage(view, f);
            this.mPageTransforms.forEach(new Jb.b(view, f, getViewHolder(view)));
        }
    }

    public void transformSwipeInternal(View view, float f) {
        ListViewHolder viewHolder = getViewHolder(view);
        this.mPageTransforms.forEach(new g(viewHolder, 1));
        if (this.mIsSimpleSlide) {
            ViewUtils.setTranslationX(viewHolder.getDecoView(35), 1.0f);
            ViewUtils.setTranslationY(viewHolder.getDecoView(35), 1.0f);
        }
    }

    public final Animator transformVelocityAboutDuration(ViewPager2 viewPager2, long j2) {
        this.mProgress.set(Float.valueOf((float) viewPager2.getWidth()));
        ValueAnimator a7 = this.mAnimatorCreator.create(viewPager2);
        a7.setDuration(j2);
        a7.setInterpolator(this.mInterpolator);
        a7.setRepeatCount(0);
        a7.addUpdateListener(this);
        a7.addListener(this);
        a7.start();
        return a7;
    }

    private ListViewHolder getViewHolder(View view) {
        return (ListViewHolder) ((RecyclerView) this.mViewPager.getChildAt(0)).findContainingViewHolder(view);
    }
}
