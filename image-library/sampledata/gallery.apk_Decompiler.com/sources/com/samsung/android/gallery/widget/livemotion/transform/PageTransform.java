package com.samsung.android.gallery.widget.livemotion.transform;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.module.story.transcode.config.KenBurnsInfo;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PageTransform implements ViewPager2.PageTransformer {
    protected final String TAG;
    protected float mDelayProgress;
    protected float mDuration;
    protected final AtomicBoolean mInit;
    protected float mInitialValue;
    protected final int mTarget;
    protected float mTargetValue;
    private int mTargetViewResId;

    public PageTransform(int i2) {
        this.TAG = getClass().getSimpleName();
        this.mTargetViewResId = -1;
        this.mInit = new AtomicBoolean(false);
        this.mInitialValue = 1.0f;
        this.mTargetValue = 1.0f;
        this.mDelayProgress = 0.0f;
        this.mDuration = 1.0f;
        this.mTarget = i2;
    }

    private float getDuration() {
        return Math.min(this.mDuration, 1.0f - this.mDelayProgress);
    }

    private int getPageType(float f) {
        if (f < 0.0f) {
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

    private View getTargetView(View view) {
        View findViewById;
        if (!hasTargetView() || (findViewById = view.findViewById(this.mTargetViewResId)) == null) {
            return view;
        }
        return findViewById;
    }

    public abstract KenBurnsInfo.Property copyProperty(KenBurnsInfo.Property property);

    public float getDelay() {
        return this.mDelayProgress;
    }

    public float getPivotX() {
        return 0.0f;
    }

    public float getPivotY() {
        return 0.0f;
    }

    public final float getProgressDelta(float f) {
        return Math.min((f - this.mDelayProgress) / getDuration(), 1.0f);
    }

    public final float getValueDelta(float f) {
        return getProgressDelta(f) * (this.mTargetValue - this.mInitialValue);
    }

    public boolean hasTargetView() {
        if (this.mTargetViewResId != -1) {
            return true;
        }
        return false;
    }

    public boolean isVisiblePage() {
        if (this.mTarget == 0) {
            return true;
        }
        return false;
    }

    public void prepare(View view) {
        transformStart(view, 0.0f);
    }

    public PageTransform setDelay(float f) {
        this.mDelayProgress = f;
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
        if (this.mTarget == 0) {
            if (view.isPivotSet()) {
                view.resetPivot();
            }
            view.setTranslationX(0.0f);
        }
    }

    public void transformPage(View view, float f) {
        View targetView = getTargetView(view);
        if (this.mTarget == getPageType(f)) {
            try {
                float progress = getProgress(f);
                if (!this.mInit.getAndSet(true)) {
                    transformStart(targetView, progress);
                    return;
                }
                transformPageInternal(targetView, progress);
                if (progress >= 1.0f) {
                    transformComplete(targetView);
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

    public PageTransform(int i2, int i7) {
        this(i2);
        this.mTargetViewResId = i7;
    }

    public void onCancel() {
    }
}
