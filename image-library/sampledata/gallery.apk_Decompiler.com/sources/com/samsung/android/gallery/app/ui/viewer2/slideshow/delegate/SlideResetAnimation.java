package com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate;

import A.a;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideResetAnimation extends Animation implements Animation.AnimationListener {
    private Runnable mAnimEndRunnable;
    private float mCurrentOffset;
    private final float mInitOffset;
    private final ViewPager2 mViewPager;

    public SlideResetAnimation(ViewPager2 viewPager2, float f, Runnable runnable) {
        this.mViewPager = viewPager2;
        this.mInitOffset = f;
        this.mCurrentOffset = f;
        this.mAnimEndRunnable = runnable;
        setDuration(200);
        setInterpolator(new AccelerateInterpolator());
        setAnimationListener(this);
    }

    public void applyTransformation(float f, Transformation transformation) {
        ViewPager2 viewPager2 = this.mViewPager;
        if (viewPager2 == null || !viewPager2.isFakeDragging()) {
            Log.e("SlideResetAnimation", "mViewerPager is null or not in fake dragging");
            return;
        }
        float f5 = (1.0f - f) * this.mInitOffset;
        float f8 = this.mCurrentOffset - f5;
        try {
            ViewPager2 viewPager22 = this.mViewPager;
            if (Features.isEnabled(Features.IS_RTL)) {
                f8 *= -1.0f;
            }
            viewPager22.fakeDragBy(f8);
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to fake drag by e="), "SlideResetAnimation");
        }
        this.mCurrentOffset = f5;
    }

    public void onAnimationEnd(Animation animation) {
        this.mAnimEndRunnable.run();
        this.mAnimEndRunnable = null;
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
