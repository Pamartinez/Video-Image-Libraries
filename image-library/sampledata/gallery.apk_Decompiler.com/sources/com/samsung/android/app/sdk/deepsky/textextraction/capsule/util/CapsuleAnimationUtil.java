package com.samsung.android.app.sdk.deepsky.textextraction.capsule.util;

import E7.b;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J<\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J<\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J:\u0010\u0013\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J:\u0010\u0017\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J4\u0010\u0018\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00162\b\b\u0002\u0010\u001b\u001a\u00020\u000e2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0011J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/util/CapsuleAnimationUtil;", "", "<init>", "()V", "pathInterpolator", "Landroid/view/animation/PathInterpolator;", "getValueAnimatorAlongWidth", "Landroid/animation/ValueAnimator;", "targetView", "Landroid/view/View;", "startValue", "", "endValue", "animationDuration", "", "delay", "interpolatorType", "Landroid/view/animation/Interpolator;", "getValueAnimatorAlongHeight", "getTextViewAlphaAnimator", "Landroid/animation/ObjectAnimator;", "Landroid/widget/TextView;", "", "getViewAlphaAnimator", "getMoveXByAnimator", "startTranslation", "endTranslation", "duration", "interpolator", "isRemoveAnimationOn", "", "context", "Landroid/content/Context;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CapsuleAnimationUtil {
    private final PathInterpolator pathInterpolator = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);

    public static /* synthetic */ ObjectAnimator getMoveXByAnimator$default(CapsuleAnimationUtil capsuleAnimationUtil, View view, float f, float f5, long j2, Interpolator interpolator, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 8) != 0) {
                j2 = 0;
            }
            long j3 = j2;
            if ((i2 & 16) != 0) {
                interpolator = null;
            }
            return capsuleAnimationUtil.getMoveXByAnimator(view, f, f5, j3, interpolator);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getMoveXByAnimator");
    }

    public static /* synthetic */ ObjectAnimator getTextViewAlphaAnimator$default(CapsuleAnimationUtil capsuleAnimationUtil, TextView textView, float f, float f5, long j2, long j3, Interpolator interpolator, int i2, Object obj) {
        long j8;
        LinearInterpolator linearInterpolator;
        if (obj == null) {
            if ((i2 & 16) != 0) {
                j8 = 0;
            } else {
                j8 = j3;
            }
            if ((i2 & 32) != 0) {
                linearInterpolator = new LinearInterpolator();
            } else {
                linearInterpolator = interpolator;
            }
            return capsuleAnimationUtil.getTextViewAlphaAnimator(textView, f, f5, j2, j8, linearInterpolator);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTextViewAlphaAnimator");
    }

    public static /* synthetic */ ValueAnimator getValueAnimatorAlongHeight$default(CapsuleAnimationUtil capsuleAnimationUtil, View view, int i2, int i7, long j2, long j3, Interpolator interpolator, int i8, Object obj) {
        long j8;
        PathInterpolator pathInterpolator2;
        if (obj == null) {
            if ((i8 & 16) != 0) {
                j8 = 0;
            } else {
                j8 = j3;
            }
            if ((i8 & 32) != 0) {
                pathInterpolator2 = capsuleAnimationUtil.pathInterpolator;
            } else {
                pathInterpolator2 = interpolator;
            }
            return capsuleAnimationUtil.getValueAnimatorAlongHeight(view, i2, i7, j2, j8, pathInterpolator2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getValueAnimatorAlongHeight");
    }

    /* access modifiers changed from: private */
    public static final void getValueAnimatorAlongHeight$lambda$3$lambda$2(View view, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "animation");
        Object animatedValue = valueAnimator.getAnimatedValue();
        j.c(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        view.getLayoutParams().height = ((Integer) animatedValue).intValue();
        view.requestLayout();
    }

    public static /* synthetic */ ValueAnimator getValueAnimatorAlongWidth$default(CapsuleAnimationUtil capsuleAnimationUtil, View view, int i2, int i7, long j2, long j3, Interpolator interpolator, int i8, Object obj) {
        long j8;
        PathInterpolator pathInterpolator2;
        if (obj == null) {
            if ((i8 & 16) != 0) {
                j8 = 0;
            } else {
                j8 = j3;
            }
            if ((i8 & 32) != 0) {
                pathInterpolator2 = capsuleAnimationUtil.pathInterpolator;
            } else {
                pathInterpolator2 = interpolator;
            }
            return capsuleAnimationUtil.getValueAnimatorAlongWidth(view, i2, i7, j2, j8, pathInterpolator2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getValueAnimatorAlongWidth");
    }

    /* access modifiers changed from: private */
    public static final void getValueAnimatorAlongWidth$lambda$1$lambda$0(View view, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "animation");
        Object animatedValue = valueAnimator.getAnimatedValue();
        j.c(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        view.getLayoutParams().width = ((Integer) animatedValue).intValue();
        view.requestLayout();
    }

    public static /* synthetic */ ObjectAnimator getViewAlphaAnimator$default(CapsuleAnimationUtil capsuleAnimationUtil, View view, float f, float f5, long j2, long j3, Interpolator interpolator, int i2, Object obj) {
        long j8;
        LinearInterpolator linearInterpolator;
        if (obj == null) {
            if ((i2 & 16) != 0) {
                j8 = 0;
            } else {
                j8 = j3;
            }
            if ((i2 & 32) != 0) {
                linearInterpolator = new LinearInterpolator();
            } else {
                linearInterpolator = interpolator;
            }
            return capsuleAnimationUtil.getViewAlphaAnimator(view, f, f5, j2, j8, linearInterpolator);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getViewAlphaAnimator");
    }

    public final ObjectAnimator getMoveXByAnimator(View view, float f, float f5, long j2, Interpolator interpolator) {
        j.e(view, "targetView");
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", new float[]{f, f5});
        ofFloat.setDuration(j2);
        ofFloat.setInterpolator(interpolator);
        return ofFloat;
    }

    public final ObjectAnimator getTextViewAlphaAnimator(TextView textView, float f, float f5, long j2, long j3, Interpolator interpolator) {
        j.e(textView, "targetView");
        j.e(interpolator, "interpolatorType");
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, "alpha", new float[]{f, f5});
        ofFloat.setDuration(j2);
        ofFloat.setInterpolator(interpolator);
        ofFloat.setStartDelay(j3);
        return ofFloat;
    }

    public final ValueAnimator getValueAnimatorAlongHeight(View view, int i2, int i7, long j2, long j3, Interpolator interpolator) {
        j.e(view, "targetView");
        j.e(interpolator, "interpolatorType");
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i2, i7});
        ofInt.setDuration(j2);
        ofInt.setInterpolator(interpolator);
        ofInt.setStartDelay(j3);
        ofInt.addUpdateListener(new b(view, 3));
        return ofInt;
    }

    public final ValueAnimator getValueAnimatorAlongWidth(View view, int i2, int i7, long j2, long j3, Interpolator interpolator) {
        j.e(view, "targetView");
        j.e(interpolator, "interpolatorType");
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i2, i7});
        ofInt.setDuration(j2);
        ofInt.setInterpolator(interpolator);
        ofInt.setStartDelay(j3);
        ofInt.addUpdateListener(new b(view, 4));
        return ofInt;
    }

    public final ObjectAnimator getViewAlphaAnimator(View view, float f, float f5, long j2, long j3, Interpolator interpolator) {
        j.e(view, "targetView");
        j.e(interpolator, "interpolatorType");
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", new float[]{f, f5});
        ofFloat.setDuration(j2);
        ofFloat.setInterpolator(interpolator);
        ofFloat.setStartDelay(j3);
        return ofFloat;
    }

    public final boolean isRemoveAnimationOn(Context context) {
        j.e(context, "context");
        if (Settings.Global.getInt(context.getContentResolver(), "remove_animations", 0) == 1) {
            return true;
        }
        return false;
    }
}
