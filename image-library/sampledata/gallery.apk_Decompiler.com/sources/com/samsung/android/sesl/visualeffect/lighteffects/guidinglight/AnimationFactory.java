package com.samsung.android.sesl.visualeffect.lighteffects.guidinglight;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationFactory;", "", "Companion", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AnimationFactory {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0015B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007JM\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationFactory$Companion;", "", "<init>", "()V", "createSpringAnimation", "Landroidx/dynamicanimation/animation/SpringAnimation;", "listener", "Landroidx/dynamicanimation/animation/DynamicAnimation$OnAnimationUpdateListener;", "createAnimation", "Landroid/animation/ValueAnimator;", "type", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationFactory$Companion$AnimationType;", "updateListener", "Landroid/animation/ValueAnimator$AnimatorUpdateListener;", "onStart", "Ljava/lang/Runnable;", "onEnd", "onCancel", "customDuration", "", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationFactory$Companion$AnimationType;Landroid/animation/ValueAnimator$AnimatorUpdateListener;Ljava/lang/Runnable;Ljava/lang/Runnable;Ljava/lang/Runnable;Ljava/lang/Long;)Landroid/animation/ValueAnimator;", "AnimationType", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0014\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B)\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001a¨\u0006\u001b"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationFactory$Companion$AnimationType;", "", "duration", "", "interpolator", "Landroid/view/animation/Interpolator;", "from", "", "to", "<init>", "(Ljava/lang/String;IJLandroid/view/animation/Interpolator;FF)V", "getDuration", "()J", "getInterpolator", "()Landroid/view/animation/Interpolator;", "getFrom", "()F", "getTo", "SHOW_SIZE_PHASE_1", "SHOW_SIZE_PHASE_2", "SHOW_LUMINANCE", "SHOW_LUMINANCE_LONG", "HIDE_LUMINANCE", "HIDE_LUMINANCE_LONG", "SHOW_SIZE_NOW_BAR_PHASE_1", "SHOW_SIZE_NOW_BAR_PHASE_2", "HIDE_SIZE_NOW_BAR", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum AnimationType {
            SHOW_SIZE_PHASE_1(800, new PathInterpolator(0.22f, 0.35f, 0.35f, 1.0f), 0.0f, 1.25f),
            SHOW_SIZE_PHASE_2(850, new PathInterpolator(0.33f, 0.0f, 0.4f, 1.0f), 1.25f, 1.0f),
            SHOW_LUMINANCE(200, new PathInterpolator(0.0f, 0.0f, 1.0f, 1.0f), 0.0f, 1.0f),
            SHOW_LUMINANCE_LONG(1000, new PathInterpolator(0.33f, 0.0f, 0.4f, 1.0f), 0.0f, 1.0f),
            HIDE_LUMINANCE(200, new PathInterpolator(0.0f, 0.0f, 1.0f, 1.0f), 1.0f, 0.0f),
            HIDE_LUMINANCE_LONG(1000, new PathInterpolator(0.33f, 0.0f, 0.4f, 1.0f), 1.0f, 0.0f),
            SHOW_SIZE_NOW_BAR_PHASE_1(800, new PathInterpolator(0.22f, 0.35f, 0.35f, 1.0f), 0.0f, 1.25f),
            SHOW_SIZE_NOW_BAR_PHASE_2(850, new PathInterpolator(0.33f, 0.0f, 0.4f, 1.0f), 1.25f, 1.0f),
            HIDE_SIZE_NOW_BAR(850, new PathInterpolator(0.33f, 0.0f, 0.4f, 1.0f), 1.25f, 0.45f);
            
            private final long duration;
            private final float from;
            private final Interpolator interpolator;
            private final float to;

            static {
                AnimationType[] $values;
                $ENTRIES = c.t($values);
            }

            private AnimationType(long j2, Interpolator interpolator2, float f, float f5) {
                this.duration = j2;
                this.interpolator = interpolator2;
                this.from = f;
                this.to = f5;
            }

            public final long getDuration() {
                return this.duration;
            }

            public final float getFrom() {
                return this.from;
            }

            public final Interpolator getInterpolator() {
                return this.interpolator;
            }

            public final float getTo() {
                return this.to;
            }
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public static /* synthetic */ ValueAnimator createAnimation$default(Companion companion, AnimationType animationType, ValueAnimator.AnimatorUpdateListener animatorUpdateListener, Runnable runnable, Runnable runnable2, Runnable runnable3, Long l, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                runnable = null;
            }
            if ((i2 & 8) != 0) {
                runnable2 = null;
            }
            if ((i2 & 16) != 0) {
                runnable3 = null;
            }
            if ((i2 & 32) != 0) {
                l = null;
            }
            return companion.createAnimation(animationType, animatorUpdateListener, runnable, runnable2, runnable3, l);
        }

        public final ValueAnimator createAnimation(AnimationType animationType, ValueAnimator.AnimatorUpdateListener animatorUpdateListener, Runnable runnable, Runnable runnable2, Runnable runnable3, Long l) {
            long j2;
            j.e(animationType, "type");
            j.e(animatorUpdateListener, "updateListener");
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{animationType.getFrom(), animationType.getTo()});
            Handler handler = new Handler(Looper.getMainLooper());
            if (l != null) {
                j2 = l.longValue();
            } else {
                j2 = animationType.getDuration();
            }
            ofFloat.setDuration(j2);
            ofFloat.setInterpolator(animationType.getInterpolator());
            ofFloat.addUpdateListener(animatorUpdateListener);
            ofFloat.addListener(new AnimationFactory$Companion$createAnimation$1$1(handler, runnable, runnable2, runnable3));
            return ofFloat;
        }

        public final SpringAnimation createSpringAnimation(DynamicAnimation.OnAnimationUpdateListener onAnimationUpdateListener) {
            j.e(onAnimationUpdateListener, "listener");
            SpringAnimation springAnimation = new SpringAnimation(new FloatValueHolder(1.0f));
            SpringForce springForce = new SpringForce(100.0f);
            springForce.setDampingRatio(1.0f);
            springForce.setStiffness(361.0f);
            springAnimation.addUpdateListener(onAnimationUpdateListener);
            springAnimation.setSpring(springForce);
            return springAnimation;
        }

        private Companion() {
        }
    }
}
