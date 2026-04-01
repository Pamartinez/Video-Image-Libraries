package com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate;

import A.a;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sum.core.Def;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideTransitionAnimation extends Animation implements Animation.AnimationListener {
    private float mCurrentOffset;
    private int mMaxWidth;
    private final ViewPager2 mViewPager;

    public SlideTransitionAnimation(ViewPager2 viewPager2) {
        this.mViewPager = viewPager2;
        setDuration(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
        setInterpolator(new AccelerateInterpolator());
        setRepeatCount(0);
        setRepeatMode(1);
        setAnimationListener(this);
    }

    private void startFakeDrag() {
        try {
            if (!this.mViewPager.isFakeDragging()) {
                this.mViewPager.beginFakeDrag();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to start fake drag e="), "SlideTransitionAnimation");
        }
    }

    private void stopFakeDrag() {
        try {
            if (this.mViewPager.isFakeDragging()) {
                this.mViewPager.endFakeDrag();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to stop fake drag e="), "SlideTransitionAnimation");
        }
    }

    public void applyTransformation(float f, Transformation transformation) {
        ViewPager2 viewPager2 = this.mViewPager;
        if (viewPager2 != null && viewPager2.isFakeDragging()) {
            float offscreenPageLimit = (float) (this.mViewPager.getOffscreenPageLimit() + this.mViewPager.getWidth());
            if (((float) this.mMaxWidth) != offscreenPageLimit) {
                cancel();
                return;
            }
            float f5 = f * offscreenPageLimit;
            float f8 = (f5 - this.mCurrentOffset) * -1.0f;
            try {
                ViewPager2 viewPager22 = this.mViewPager;
                if (Features.isEnabled(Features.IS_RTL)) {
                    f8 *= -1.0f;
                }
                viewPager22.fakeDragBy(f8);
            } catch (Exception e) {
                a.s(e, new StringBuilder("applyTransformation failed. e="), "SlideTransitionAnimation");
            }
            this.mCurrentOffset = f5;
        }
    }

    public float getCurrentOffset() {
        return this.mCurrentOffset;
    }

    public void onAnimationEnd(Animation animation) {
        if (this.mViewPager != null) {
            stopFakeDrag();
            this.mViewPager.clearAnimation();
            this.mCurrentOffset = 0.0f;
        }
    }

    public void onAnimationStart(Animation animation) {
        ViewPager2 viewPager2 = this.mViewPager;
        if (viewPager2 != null) {
            this.mMaxWidth = this.mViewPager.getOffscreenPageLimit() + viewPager2.getWidth();
            startFakeDrag();
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
