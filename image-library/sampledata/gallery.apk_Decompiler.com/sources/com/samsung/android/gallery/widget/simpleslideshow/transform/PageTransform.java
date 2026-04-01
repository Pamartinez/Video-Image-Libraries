package com.samsung.android.gallery.widget.simpleslideshow.transform;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PageTransform implements ViewPager2.PageTransformer {
    protected float mDelayProgress = 0.0f;
    protected float mDuration = 1.0f;
    protected final AtomicBoolean mInit = new AtomicBoolean(false);
    protected float mInitialValue = 1.0f;
    protected final int mTarget;
    protected float mTargetValue = 1.0f;

    public PageTransform(int i2) {
        this.mTarget = i2;
    }

    private float getDuration() {
        return Math.min(this.mDuration, 1.0f - this.mDelayProgress);
    }

    private int getPageType(float f) {
        if (f <= 0.0f) {
            return 0;
        }
        return 1;
    }

    private float getProgress(float f) {
        if (this.mTarget == 0) {
            return Math.abs(f);
        }
        return 1.0f - Math.abs(f);
    }

    public final float getProgressDelta(float f) {
        return Math.min((f - this.mDelayProgress) / getDuration(), 1.0f);
    }

    public final float getValueDelta(float f) {
        return getProgressDelta(f) * (this.mTargetValue - this.mInitialValue);
    }

    public PageTransform setDelay(float f) {
        this.mDelayProgress = f;
        return this;
    }

    public PageTransform setDuration(float f) {
        this.mDuration = f;
        return this;
    }

    public PageTransform setInitialValue(float f) {
        this.mInitialValue = f;
        return this;
    }

    public PageTransform setTargetValue(float f) {
        this.mTargetValue = f;
        return this;
    }

    public void transformComplete(View view) {
        if (this.mTarget == 0 && view.isPivotSet()) {
            view.resetPivot();
        }
    }

    public void transformPage(View view, float f) {
        if (this.mTarget == getPageType(f)) {
            try {
                float progress = getProgress(f);
                if (!this.mInit.getAndSet(true)) {
                    transformStart(view, progress);
                    return;
                }
                transformPageInternal(view, progress);
                if (progress >= 1.0f) {
                    transformComplete(view);
                }
            } catch (Exception e) {
                String simpleName = getClass().getSimpleName();
                Log.e(simpleName, "transformPage failed. e=" + e.getMessage());
                new InternalException("transformPage failed e=" + e.getMessage()).post();
            }
        }
    }

    public abstract void transformPageInternal(View view, float f);

    public abstract void transformStart(View view, float f);

    public void onCancel() {
    }
}
