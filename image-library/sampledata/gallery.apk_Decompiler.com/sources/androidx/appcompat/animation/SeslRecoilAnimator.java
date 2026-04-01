package androidx.appcompat.animation;

import B2.h;
import W8.C0579a;
import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import androidx.appcompat.R$anim;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslRecoilAnimator {
    private static final int PRESS_INTERPOLATR = R$anim.sesl_recoil_pressed;
    private static final int RELEASE_INTERPOLATR = R$anim.sesl_recoil_released;
    private static TimeInterpolator sPressInterpolator;
    private static TimeInterpolator sReleaseInterpolator;
    /* access modifiers changed from: private */
    public final ValueAnimator mAnimator;
    private Context mContext;
    private boolean mIsPressed = false;
    private boolean mIsScaleOnlyChildren = false;
    private final long mPressDuration = 100;
    private final long mReleaseDuration = 350;
    private float mScaleRatio;
    private final int mScaleSizeDp = 3;
    /* access modifiers changed from: private */
    public View mTarget;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Holder {
        ArrayList<SeslRecoilAnimator> mAnimators = new ArrayList<>();
        private final Context mContext;

        public Holder(Context context) {
            this.mContext = context;
        }

        private SeslRecoilAnimator createOrReuseAnimator(View view) {
            Iterator<SeslRecoilAnimator> it = this.mAnimators.iterator();
            while (it.hasNext()) {
                SeslRecoilAnimator next = it.next();
                if (next.mTarget == view) {
                    return next;
                }
            }
            Iterator<SeslRecoilAnimator> it2 = this.mAnimators.iterator();
            while (it2.hasNext()) {
                SeslRecoilAnimator next2 = it2.next();
                if (!next2.isActive()) {
                    View unused = next2.mTarget = view;
                    return next2;
                }
            }
            SeslRecoilAnimator seslRecoilAnimator = new SeslRecoilAnimator(view, this.mContext);
            this.mAnimators.add(seslRecoilAnimator);
            return seslRecoilAnimator;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$setRelease$0(SeslRecoilAnimator seslRecoilAnimator) {
            if (seslRecoilAnimator.isActive()) {
                seslRecoilAnimator.setRelease();
            }
        }

        public void removeAllUpdateListeners() {
            Iterator<SeslRecoilAnimator> it = this.mAnimators.iterator();
            while (it.hasNext()) {
                SeslRecoilAnimator next = it.next();
                if (next.isActive()) {
                    next.mAnimator.end();
                }
                next.mAnimator.removeAllUpdateListeners();
            }
            this.mAnimators.clear();
        }

        public void setPress(View view) {
            if (view.isClickable()) {
                SeslRecoilAnimator createOrReuseAnimator = createOrReuseAnimator(view);
                createOrReuseAnimator.setScaleOnlyChildren(true);
                createOrReuseAnimator.setPress();
            }
        }

        public void setRelease() {
            this.mAnimators.forEach(new C0579a(11));
        }
    }

    public SeslRecoilAnimator(View view, Context context) {
        this.mTarget = view;
        this.mContext = context;
        setScaleOnlyChildren(true);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f});
        this.mAnimator = ofFloat;
        if (sPressInterpolator == null) {
            sPressInterpolator = AnimationUtils.loadInterpolator(this.mContext, PRESS_INTERPOLATR);
        }
        if (sReleaseInterpolator == null) {
            sReleaseInterpolator = AnimationUtils.loadInterpolator(this.mContext, RELEASE_INTERPOLATR);
        }
        ofFloat.addUpdateListener(new h(6, this));
        ofFloat.addListener(new Animator.AnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                SeslRecoilAnimator.this.animateValue(((Float) ((ValueAnimator) animator).getAnimatedValue()).floatValue());
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void animateValue(float f) {
        if (!this.mIsScaleOnlyChildren || !(this.mTarget instanceof ViewGroup)) {
            setScale(f);
        } else {
            setScaleChildren(f);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(ValueAnimator valueAnimator) {
        animateValue(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    private void setScale(float f) {
        this.mTarget.setScaleX(f);
        this.mTarget.setScaleY(f);
    }

    private void setScaleChildren(float f) {
        ViewGroup viewGroup = (ViewGroup) this.mTarget;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            Matrix matrix = new Matrix();
            float width = (((float) this.mTarget.getWidth()) / 2.0f) - ((float) childAt.getLeft());
            float height = (((float) this.mTarget.getHeight()) / 2.0f) - ((float) childAt.getTop());
            matrix.setTranslate(-width, -height);
            matrix.postScale(f, f);
            matrix.postTranslate(width, height);
            childAt.setAnimationMatrix(matrix);
        }
    }

    private void setScaleRatioBySize() {
        float width = (float) this.mTarget.getWidth();
        this.mScaleRatio = (width - (this.mContext.getResources().getDisplayMetrics().density * 3.0f)) / width;
    }

    public boolean isActive() {
        if (this.mIsPressed || this.mAnimator.isRunning()) {
            return true;
        }
        return false;
    }

    public void setPress() {
        setScaleRatioBySize();
        if (!this.mIsPressed) {
            this.mIsPressed = true;
            if (this.mAnimator.isRunning()) {
                this.mAnimator.cancel();
            }
            float floatValue = ((Float) this.mAnimator.getAnimatedValue()).floatValue();
            ValueAnimator valueAnimator = this.mAnimator;
            if (floatValue == 0.0f) {
                floatValue = 1.0f;
            }
            valueAnimator.setFloatValues(new float[]{floatValue, this.mScaleRatio});
            this.mAnimator.setDuration(100);
            this.mAnimator.setInterpolator(sPressInterpolator);
            this.mAnimator.start();
        }
    }

    public void setRelease() {
        if (this.mIsPressed) {
            this.mIsPressed = false;
            if (this.mAnimator.isRunning()) {
                this.mAnimator.cancel();
            }
            ValueAnimator valueAnimator = this.mAnimator;
            valueAnimator.setFloatValues(new float[]{((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f});
            this.mAnimator.setDuration(350);
            this.mAnimator.setInterpolator(sReleaseInterpolator);
            this.mAnimator.start();
        }
    }

    public void setScaleOnlyChildren(boolean z) {
        if (this.mTarget instanceof ViewGroup) {
            this.mIsScaleOnlyChildren = z;
        } else {
            this.mIsScaleOnlyChildren = false;
        }
    }
}
