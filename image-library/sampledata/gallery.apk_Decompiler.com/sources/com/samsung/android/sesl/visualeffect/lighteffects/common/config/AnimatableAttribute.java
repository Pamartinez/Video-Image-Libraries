package com.samsung.android.sesl.visualeffect.lighteffects.common.config;

import Ae.d;
import Yb.b;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.VibeEffectControl;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0018\b\b\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B}\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\b\b\u0002\u0010\u0007\u001a\u00028\u0000\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012$\b\u0002\u0010\u0013\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0018\u001a\u00020\u0016*\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001c\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001e\u001a\u00020\u001dHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\nHÖ\u0001¢\u0006\u0004\b \u0010!J\u001a\u0010$\u001a\u00020#2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010\u0005\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0005\u0010&\u001a\u0004\b)\u0010(R\u0017\u0010\u0006\u001a\u00028\u00008\u0006¢\u0006\f\n\u0004\b\u0006\u0010*\u001a\u0004\b+\u0010,R\u0017\u0010\u0007\u001a\u00028\u00008\u0006¢\u0006\f\n\u0004\b\u0007\u0010*\u001a\u0004\b-\u0010,R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010.\u001a\u0004\b/\u00100R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00101\u001a\u0004\b2\u0010!R\u0017\u0010\f\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\f\u00101\u001a\u0004\b3\u0010!R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u00104\u001a\u0004\b5\u00106R3\u0010\u0013\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0013\u00107\u001a\u0004\b8\u00109R\u0018\u0010:\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b:\u00104¨\u0006;"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/common/config/AnimatableAttribute;", "", "T", "", "duration", "delay", "fromValue", "toValue", "Landroid/view/animation/Interpolator;", "interpolation", "", "repeatMode", "repeatCount", "Landroid/animation/Animator$AnimatorListener;", "animationListener", "Lkotlin/Function3;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/control/VibeEffectControl;", "", "Lme/x;", "onValueUpdated", "<init>", "(JJLjava/lang/Object;Ljava/lang/Object;Landroid/view/animation/Interpolator;IILandroid/animation/Animator$AnimatorListener;LAe/d;)V", "Landroid/animation/ValueAnimator;", "effect", "applyAttrs", "(Landroid/animation/ValueAnimator;Lcom/samsung/android/sesl/visualeffect/lighteffects/common/control/VibeEffectControl;)Landroid/animation/ValueAnimator;", "build$sesl_visualeffect_INTERNAL_2_1_6_release", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/common/control/VibeEffectControl;)Landroid/animation/ValueAnimator;", "build", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "J", "getDuration", "()J", "getDelay", "Ljava/lang/Object;", "getFromValue", "()Ljava/lang/Object;", "getToValue", "Landroid/view/animation/Interpolator;", "getInterpolation", "()Landroid/view/animation/Interpolator;", "I", "getRepeatMode", "getRepeatCount", "Landroid/animation/Animator$AnimatorListener;", "getAnimationListener", "()Landroid/animation/Animator$AnimatorListener;", "LAe/d;", "getOnValueUpdated", "()LAe/d;", "animationListenerInternal", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AnimatableAttribute<T> {
    private final Animator.AnimatorListener animationListener;
    private Animator.AnimatorListener animationListenerInternal;
    private final long delay;
    private final long duration;
    private final T fromValue;
    private final Interpolator interpolation;
    private final d onValueUpdated;
    private final int repeatCount;
    private final int repeatMode;
    private final T toValue;

    public AnimatableAttribute(long j2, long j3, T t, T t3, Interpolator interpolator, int i2, int i7, Animator.AnimatorListener animatorListener, d dVar) {
        j.e(t, "fromValue");
        j.e(t3, "toValue");
        j.e(interpolator, "interpolation");
        this.duration = j2;
        this.delay = j3;
        this.fromValue = t;
        this.toValue = t3;
        this.interpolation = interpolator;
        this.repeatMode = i2;
        this.repeatCount = i7;
        this.animationListener = animatorListener;
        this.onValueUpdated = dVar;
    }

    private final ValueAnimator applyAttrs(ValueAnimator valueAnimator, VibeEffectControl vibeEffectControl) {
        valueAnimator.setDuration(this.duration);
        valueAnimator.setStartDelay(this.delay);
        valueAnimator.setRepeatMode(this.repeatMode);
        valueAnimator.setRepeatCount(this.repeatCount);
        valueAnimator.setInterpolator(this.interpolation);
        valueAnimator.addUpdateListener(new b(valueAnimator, this, vibeEffectControl, 1));
        Animator.AnimatorListener animatorListener = this.animationListener;
        if (animatorListener != null) {
            valueAnimator.addListener(animatorListener);
        }
        Animator.AnimatorListener animatorListener2 = this.animationListenerInternal;
        if (animatorListener2 != null) {
            valueAnimator.addListener(animatorListener2);
        }
        return valueAnimator;
    }

    /* access modifiers changed from: private */
    public static final void applyAttrs$lambda$3$lambda$0(ValueAnimator valueAnimator, AnimatableAttribute animatableAttribute, VibeEffectControl vibeEffectControl, ValueAnimator valueAnimator2) {
        Integer num;
        j.e(valueAnimator2, "it");
        Object animatedValue = valueAnimator.getAnimatedValue();
        T t = null;
        if (animatedValue instanceof Integer) {
            num = (Integer) animatedValue;
        } else {
            num = null;
        }
        if (num != null) {
            T t3 = animatableAttribute.fromValue;
            if (t3 instanceof Color) {
                t = t3;
            }
            if (((Color) t) != null) {
                d dVar = animatableAttribute.onValueUpdated;
                if (dVar != null) {
                    Float valueOf = Float.valueOf(valueAnimator.getAnimatedFraction());
                    Color valueOf2 = Color.valueOf(((Number) animatedValue).intValue());
                    j.c(valueOf2, "null cannot be cast to non-null type T of com.samsung.android.sesl.visualeffect.lighteffects.common.config.AnimatableAttribute");
                    dVar.invoke(vibeEffectControl, valueOf, valueOf2);
                    return;
                }
                return;
            }
        }
        d dVar2 = animatableAttribute.onValueUpdated;
        if (dVar2 != null) {
            Float valueOf3 = Float.valueOf(valueAnimator.getAnimatedFraction());
            j.c(animatedValue, "null cannot be cast to non-null type T of com.samsung.android.sesl.visualeffect.lighteffects.common.config.AnimatableAttribute");
            dVar2.invoke(vibeEffectControl, valueOf3, animatedValue);
        }
    }

    public final ValueAnimator build$sesl_visualeffect_INTERNAL_2_1_6_release(VibeEffectControl vibeEffectControl) {
        T t;
        ValueAnimator valueAnimator;
        T t3 = this.fromValue;
        T t5 = null;
        if (!(t3 instanceof Float)) {
            t = null;
        } else {
            t = t3;
        }
        if (((Float) t) != null) {
            j.c(t3, "null cannot be cast to non-null type kotlin.Float");
            float floatValue = ((Float) t3).floatValue();
            T t6 = this.toValue;
            j.c(t6, "null cannot be cast to non-null type kotlin.Float");
            valueAnimator = ValueAnimator.ofFloat(new float[]{floatValue, ((Float) t6).floatValue()});
        } else {
            if (t3 instanceof Color) {
                t5 = t3;
            }
            if (((Color) t5) != null) {
                j.c(t3, "null cannot be cast to non-null type android.graphics.Color");
                int argb = ((Color) t3).toArgb();
                T t7 = this.toValue;
                j.c(t7, "null cannot be cast to non-null type android.graphics.Color");
                valueAnimator = ValueAnimator.ofArgb(new int[]{argb, ((Color) t7).toArgb()});
            } else {
                throw new RuntimeException("RTTI Failed. Unsupported animation type used. Implement for this type on " + v.f4727a.b(AnimatableAttribute.class));
            }
        }
        j.b(valueAnimator);
        return applyAttrs(valueAnimator, vibeEffectControl);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnimatableAttribute)) {
            return false;
        }
        AnimatableAttribute animatableAttribute = (AnimatableAttribute) obj;
        if (this.duration == animatableAttribute.duration && this.delay == animatableAttribute.delay && j.a(this.fromValue, animatableAttribute.fromValue) && j.a(this.toValue, animatableAttribute.toValue) && j.a(this.interpolation, animatableAttribute.interpolation) && this.repeatMode == animatableAttribute.repeatMode && this.repeatCount == animatableAttribute.repeatCount && j.a(this.animationListener, animatableAttribute.animationListener) && j.a(this.onValueUpdated, animatableAttribute.onValueUpdated)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int c5 = C0212a.c(Long.hashCode(this.duration) * 31, 31, this.delay);
        int hashCode = this.toValue.hashCode();
        int b = C0212a.b(this.repeatCount, C0212a.b(this.repeatMode, (this.interpolation.hashCode() + ((hashCode + ((this.fromValue.hashCode() + c5) * 31)) * 31)) * 31, 31), 31);
        Animator.AnimatorListener animatorListener = this.animationListener;
        int i7 = 0;
        if (animatorListener == null) {
            i2 = 0;
        } else {
            i2 = animatorListener.hashCode();
        }
        int i8 = (b + i2) * 31;
        d dVar = this.onValueUpdated;
        if (dVar != null) {
            i7 = dVar.hashCode();
        }
        return i8 + i7;
    }

    public String toString() {
        long j2 = this.duration;
        long j3 = this.delay;
        T t = this.fromValue;
        T t3 = this.toValue;
        Interpolator interpolator = this.interpolation;
        int i2 = this.repeatMode;
        int i7 = this.repeatCount;
        Animator.AnimatorListener animatorListener = this.animationListener;
        d dVar = this.onValueUpdated;
        StringBuilder j8 = N2.j.j(j2, "AnimatableAttribute(duration=", ", delay=");
        j8.append(j3);
        j8.append(", fromValue=");
        j8.append(t);
        j8.append(", toValue=");
        j8.append(t3);
        j8.append(", interpolation=");
        j8.append(interpolator);
        j8.append(", repeatMode=");
        j8.append(i2);
        j8.append(", repeatCount=");
        j8.append(i7);
        j8.append(", animationListener=");
        j8.append(animatorListener);
        j8.append(", onValueUpdated=");
        j8.append(dVar);
        j8.append(")");
        return j8.toString();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AnimatableAttribute(long j2, long j3, Object obj, Object obj2, Interpolator interpolator, int i2, int i7, Animator.AnimatorListener animatorListener, d dVar, int i8, e eVar) {
        this((i8 & 1) != 0 ? 0 : j2, (i8 & 2) != 0 ? 0 : j3, obj, (i8 & 8) != 0 ? obj : obj2, (i8 & 16) != 0 ? new LinearInterpolator() : interpolator, (i8 & 32) != 0 ? 1 : i2, (i8 & 64) != 0 ? -1 : i7, (i8 & 128) != 0 ? null : animatorListener, (i8 & 256) != 0 ? null : dVar);
    }
}
