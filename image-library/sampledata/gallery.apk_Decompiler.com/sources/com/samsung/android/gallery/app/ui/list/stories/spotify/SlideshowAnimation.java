package com.samsung.android.gallery.app.ui.list.stories.spotify;

import A.a;
import N2.j;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import androidx.viewpager2.widget.ViewPager2;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SlideshowAnimation extends Animation implements Animation.AnimationListener {
    private float mCurrentOffset;
    private int mDirection = 0;
    private final boolean mRtl = Features.isEnabled(Features.IS_RTL);
    private final WeakReference<ViewPager2> mViewPager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FadeInOutTransformer implements ViewPager2.PageTransformer {
        private final boolean mRtl = Features.isEnabled(Features.IS_RTL);

        public void transformPage(View view, float f) {
            if (Math.abs(f) > 0.9f) {
                float f5 = f * 60.0f;
                view.setAlpha(0.0f);
                if (this.mRtl) {
                    f5 = -f5;
                }
                view.setTranslationX(f5);
            } else if (f == 0.0f) {
                view.setAlpha(1.0f);
                view.setTranslationX(0.0f);
            } else {
                view.setAlpha(Math.max(0.0f, 1.0f - (Math.abs(f) / 0.9f)));
                float width = (float) view.getWidth();
                if (!this.mRtl) {
                    f = -f;
                }
                view.setTranslationX(width * f);
            }
        }
    }

    public SlideshowAnimation(ViewPager2 viewPager2) {
        this.mViewPager = new WeakReference<>(viewPager2);
        setDuration(400);
        setInterpolator(new AccelerateInterpolator());
        setRepeatCount(0);
        setRepeatMode(1);
        setAnimationListener(this);
    }

    private ViewPager2 getViewPager() {
        return this.mViewPager.get();
    }

    private void moveTo(int i2, boolean z) {
        try {
            getViewPager().setCurrentItem(i2, z);
        } catch (IllegalStateException | NullPointerException e) {
            j.u(e, C0086a.o(i2, "moveTo#", " failed. e="), "SlideshowAnimation");
        }
    }

    private Animation setDirection(int i2) {
        this.mDirection = i2;
        return this;
    }

    public void applyTransformation(float f, Transformation transformation) {
        float f5;
        ViewPager2 viewPager = getViewPager();
        if (viewPager != null && viewPager.isFakeDragging()) {
            float width = f * ((float) viewPager.getWidth());
            try {
                if (this.mDirection == 0) {
                    f5 = this.mCurrentOffset - width;
                } else {
                    f5 = width - this.mCurrentOffset;
                }
                if (this.mRtl) {
                    f5 = -f5;
                }
                viewPager.fakeDragBy(f5);
            } catch (Exception e) {
                a.s(e, new StringBuilder("applyTransformation failed. e="), "SlideshowAnimation");
            }
            this.mCurrentOffset = width;
        } else if (viewPager == null) {
            Log.e("SlideshowAnimation", "applyTransformation failed with null");
        }
    }

    public void moveNext(boolean z) {
        ViewPager2 viewPager = getViewPager();
        if (viewPager == null) {
            return;
        }
        if (z) {
            viewPager.setPageTransformer(new FadeInOutTransformer());
            viewPager.startAnimation(setDirection(0));
            return;
        }
        moveTo(viewPager.getCurrentItem() + 1, false);
    }

    public void movePrev(boolean z) {
        ViewPager2 viewPager = getViewPager();
        if (viewPager == null) {
            return;
        }
        if (z) {
            viewPager.setPageTransformer(new FadeInOutTransformer());
            viewPager.startAnimation(setDirection(1));
            return;
        }
        moveTo(viewPager.getCurrentItem() - 1, false);
    }

    public void onAnimationEnd(Animation animation) {
        ViewPager2 viewPager = getViewPager();
        if (viewPager != null) {
            try {
                if (viewPager.isFakeDragging()) {
                    viewPager.endFakeDrag();
                }
            } catch (Exception e) {
                a.s(e, new StringBuilder("onAnimationEnd failed e="), "SlideshowAnimation");
            }
            viewPager.clearAnimation();
        }
        this.mCurrentOffset = 0.0f;
    }

    public void onAnimationStart(Animation animation) {
        ViewPager2 viewPager = getViewPager();
        if (viewPager != null) {
            try {
                if (!viewPager.isFakeDragging()) {
                    viewPager.beginFakeDrag();
                }
            } catch (Exception e) {
                Log.e("SlideshowAnimation", "onAnimationStart failed e=" + e.getMessage());
                cancel();
            }
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
