package com.samsung.android.gallery.widget.simpleslideshow;

import Ba.p;
import Lb.a;
import W8.C0579a;
import Yb.b;
import Yb.c;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.simpleslideshow.transform.PageTransform;
import com.samsung.android.sum.core.Def;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleSlideShowPageTransformer implements ViewPager2.PageTransformer {
    private Animator mAnimator;
    private final ScheduleAnimatorCreator mAnimatorCreator = new ScheduleAnimatorCreator(0);
    private long mDurationTime = Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS;
    private final ViewPager2.PageTransformer mKeepCenterTransform = new a(this, 1);
    /* access modifiers changed from: private */
    public TransformListener mListener;
    private float mPageInOutDelay = 1.0f;
    /* access modifiers changed from: private */
    public final AtomicBoolean mPageInOutStarted = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final List<PageTransform> mPageTransforms = new ArrayList();

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

    /* access modifiers changed from: private */
    public float adjustRtlPosition(float f) {
        if (Features.isEnabled(Features.IS_RTL)) {
            return -f;
        }
        return f;
    }

    private boolean isPageInOutPosition(float f) {
        if (f >= 0.0f || this.mPageInOutDelay >= Math.abs(f) || this.mPageInOutStarted.get()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view, float f) {
        view.setTranslationX(adjustRtlPosition(((float) view.getWidth()) * (-f)));
        view.setTranslationY(0.0f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$transformVelocityAboutDuration$1(ViewPager2 viewPager2, AtomicReference atomicReference, ValueAnimator valueAnimator) {
        Float f = (Float) valueAnimator.getAnimatedValue();
        try {
            viewPager2.fakeDragBy(adjustRtlPosition(f.floatValue() - ((Float) atomicReference.get()).floatValue()));
            atomicReference.set(f);
        } catch (Exception e) {
            ThreadUtil.postOnUiThread(new p(valueAnimator, 1));
            A.a.s(e, new StringBuilder("fail transform = "), "SimpleSlideShowPageTransformer");
        }
    }

    public void cancel() {
        Animator animator = this.mAnimator;
        if (animator != null) {
            animator.cancel();
            this.mAnimator.removeAllListeners();
            this.mAnimator = null;
        }
    }

    public boolean isPageInOutStarted() {
        return this.mPageInOutStarted.get();
    }

    public void pause() {
        Animator animator = this.mAnimator;
        if (animator != null) {
            animator.pause();
        }
    }

    public void resume() {
        Animator animator = this.mAnimator;
        if (animator != null) {
            animator.resume();
        }
    }

    public SimpleSlideShowPageTransformer setTransformBuilder(TransformBuilder transformBuilder) {
        this.mPageTransforms.clear();
        this.mPageTransforms.addAll(transformBuilder.buildNext());
        this.mPageInOutDelay = transformBuilder.getPageInOutDelay();
        this.mDurationTime = (long) transformBuilder.getDurationTime();
        return this;
    }

    public void setTransformListener(TransformListener transformListener) {
        this.mListener = transformListener;
    }

    public void transform(ViewPager2 viewPager2) {
        this.mAnimator = transformVelocityAboutDuration(viewPager2, this.mDurationTime);
    }

    public void transformPage(View view, float f) {
        if (f >= -1.0f && 1.0f >= f) {
            this.mKeepCenterTransform.transformPage(view, f);
            this.mPageTransforms.forEach(new c(view, f, 0));
            if (this.mListener != null && isPageInOutPosition(f)) {
                this.mPageInOutStarted.set(true);
                this.mListener.onPageInOutStarted();
            }
        }
    }

    public final Animator transformVelocityAboutDuration(final ViewPager2 viewPager2, long j2) {
        final AtomicReference atomicReference = new AtomicReference(Float.valueOf((float) viewPager2.getWidth()));
        ValueAnimator a7 = this.mAnimatorCreator.create(viewPager2);
        a7.setDuration(j2);
        a7.setRepeatCount(0);
        a7.addUpdateListener(new b(this, viewPager2, atomicReference, 0));
        a7.addListener(new SimpleAnimatorListener() {
            public void onAnimationCancel(Animator animator) {
                SimpleSlideShowPageTransformer.this.mPageTransforms.forEach(new C0579a(3));
                ViewPager2 viewPager2 = viewPager2;
                viewPager2.fakeDragBy(SimpleSlideShowPageTransformer.this.adjustRtlPosition(((float) viewPager2.getWidth()) - ((Float) atomicReference.get()).floatValue()));
                onAnimationEnd(animator);
            }

            public void onAnimationEnd(Animator animator) {
                SimpleSlideShowPageTransformer.this.mPageTransforms.clear();
                SimpleSlideShowPageTransformer.this.mPageInOutStarted.set(false);
                viewPager2.endFakeDrag();
                if (SimpleSlideShowPageTransformer.this.mListener != null) {
                    SimpleSlideShowPageTransformer.this.mListener.onTransformEnd();
                }
            }

            public void onAnimationStart(Animator animator) {
                viewPager2.beginFakeDrag();
                SimpleSlideShowPageTransformer.this.mPageInOutStarted.set(false);
                if (SimpleSlideShowPageTransformer.this.mListener != null) {
                    SimpleSlideShowPageTransformer.this.mListener.onTransformStarted();
                }
            }
        });
        a7.start();
        return a7;
    }
}
