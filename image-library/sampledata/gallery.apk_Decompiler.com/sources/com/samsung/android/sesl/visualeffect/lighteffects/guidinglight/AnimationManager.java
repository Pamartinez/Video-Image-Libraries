package com.samsung.android.sesl.visualeffect.lighteffects.guidinglight;

import L7.k;
import android.animation.ValueAnimator;
import android.util.Log;
import bd.b;
import bd.c;
import bd.d;
import bd.f;
import bd.g;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.AnimationFactory;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.y;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u0001\u0018\u0000 42\u00020\u0001:\u000245B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000f\u0010\u0010J!\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u00112\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u000e¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u000e¢\u0006\u0004\b\u0016\u0010\u0015J+\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0004\b\u0018\u0010\u0010J!\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00112\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0004\b\u0019\u0010\u0013J\u0019\u0010\u001a\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ!\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u001c2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ+\u0010!\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b!\u0010\"J!\u0010#\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u001c2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0004\b#\u0010\u001eJ'\u0010)\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010(\u001a\u00020'H\u0002¢\u0006\u0004\b)\u0010*J\u001f\u0010+\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$H\u0002¢\u0006\u0004\b+\u0010,R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010-R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010.R\u001c\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010'0/8\u0002X\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0018\u00102\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b2\u00103¨\u00066"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationManager;", "", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;", "config", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightControl;", "lightControl", "<init>", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightControl;)V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$ShowAnimationType;", "animationType", "", "customDuration", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationManager$AnimationCallback;", "callback", "Lme/x;", "showWithAnimation", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$ShowAnimationType;Ljava/lang/Long;Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationManager$AnimationCallback;)V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$HideAnimationType;", "hideWithAnimation", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$HideAnimationType;Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationManager$AnimationCallback;)V", "cancelAllAnimations", "()V", "release", "type", "runShowAnimation", "runHideAnimation", "createSizeAnimation", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationManager$AnimationCallback;)V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationFactory$Companion$AnimationType;", "createLuminanceAnimation", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationFactory$Companion$AnimationType;Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationManager$AnimationCallback;)V", "", "isShortcut", "createNowBarAnimation", "(Ljava/lang/Long;Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationManager$AnimationCallback;Z)V", "hideByLuminance", "", "litRadius", "refRadius", "Landroid/animation/ValueAnimator;", "animator", "updateSizeByAnimation", "(FFLandroid/animation/ValueAnimator;)V", "updateSize", "(FF)V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightControl;", "", "showAnimations", "[Landroid/animation/ValueAnimator;", "hideAnimation", "Landroid/animation/ValueAnimator;", "Companion", "AnimationCallback", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AnimationManager {
    public static final Companion Companion = new Companion((e) null);
    private final GuidingLightConfig config;
    private ValueAnimator hideAnimation;
    private final GuidingLightControl lightControl;
    private final ValueAnimator[] showAnimations = {null, null, null};

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H&¢\u0006\u0004\b\u0006\u0010\u0004¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationManager$AnimationCallback;", "", "Lme/x;", "onAnimationStart", "()V", "onAnimationEnd", "onAnimationCancel", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AnimationCallback {
        void onAnimationCancel();

        void onAnimationEnd();

        void onAnimationStart();
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationManager$Companion;", "", "<init>", "()V", "TAG", "", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|19|20|21|22|23|24|25|27) */
        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|19|20|21|22|23|24|25|27) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x002b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0056 */
        static {
            /*
                com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$ShowAnimationType[] r0 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.ShowAnimationType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$ShowAnimationType r2 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.ShowAnimationType.SIZE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$ShowAnimationType r3 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.ShowAnimationType.LUMINANCE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                r3 = 3
                com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$ShowAnimationType r4 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.ShowAnimationType.LUMINANCE_LONG     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r0[r4] = r3     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$ShowAnimationType r4 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.ShowAnimationType.NOW_BAR     // Catch:{ NoSuchFieldError -> 0x002b }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r5 = 4
                r0[r4] = r5     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$ShowAnimationType r4 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.ShowAnimationType.NOW_BAR_SHORTCUT     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r5 = 5
                r0[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$ShowAnimationType r4 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.ShowAnimationType.NONE     // Catch:{ NoSuchFieldError -> 0x003d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r5 = 6
                r0[r4] = r5     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                $EnumSwitchMapping$0 = r0
                com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$HideAnimationType[] r0 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.HideAnimationType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$HideAnimationType r4 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.HideAnimationType.LUMINANCE     // Catch:{ NoSuchFieldError -> 0x004e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$HideAnimationType r1 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.HideAnimationType.LUMINANCE_LONG     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect$HideAnimationType r1 = com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightEffect.HideAnimationType.NONE     // Catch:{ NoSuchFieldError -> 0x005e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                $EnumSwitchMapping$1 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.AnimationManager.WhenMappings.<clinit>():void");
        }
    }

    public AnimationManager(GuidingLightConfig guidingLightConfig, GuidingLightControl guidingLightControl) {
        j.e(guidingLightConfig, "config");
        j.e(guidingLightControl, "lightControl");
        this.config = guidingLightConfig;
        this.lightControl = guidingLightControl;
    }

    private final void createLuminanceAnimation(AnimationFactory.Companion.AnimationType animationType, AnimationCallback animationCallback) {
        float globalLuminance = this.config.getGlobalLuminance();
        AnimationFactory.Companion.AnimationType animationType2 = animationType;
        this.showAnimations[0] = AnimationFactory.Companion.createAnimation$default(AnimationFactory.Companion, animationType2, new b(this, globalLuminance, 2), (Runnable) null, new d(animationCallback, 3), new g(this, globalLuminance, animationCallback, 1), (Long) null, 36, (Object) null);
        ValueAnimator valueAnimator = this.showAnimations[0];
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    public static final void createLuminanceAnimation$lambda$10(AnimationManager animationManager, float f, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "it");
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) animationManager.lightControl.getRenderEffect();
        if (guidingLightRenderEffect != null) {
            Object animatedValue = valueAnimator.getAnimatedValue();
            j.c(animatedValue, "null cannot be cast to non-null type kotlin.Float");
            guidingLightRenderEffect.setGlobalLuminance(((Float) animatedValue).floatValue() * f);
        }
    }

    /* access modifiers changed from: private */
    public static final void createLuminanceAnimation$lambda$11(AnimationManager animationManager, float f, AnimationCallback animationCallback) {
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) animationManager.lightControl.getRenderEffect();
        if (guidingLightRenderEffect != null) {
            guidingLightRenderEffect.setGlobalLuminance(f);
        }
        if (animationCallback != null) {
            animationCallback.onAnimationCancel();
        }
    }

    /* access modifiers changed from: private */
    public static final void createLuminanceAnimation$lambda$12(AnimationCallback animationCallback) {
        if (animationCallback != null) {
            animationCallback.onAnimationEnd();
        }
    }

    private final void createNowBarAnimation(Long l, AnimationCallback animationCallback, boolean z) {
        AnimationManager animationManager = this;
        float lightRadius = animationManager.config.getLightRadius();
        float reflLightRadius = animationManager.config.getReflLightRadius();
        ValueAnimator[] valueAnimatorArr = animationManager.showAnimations;
        AnimationFactory.Companion companion = AnimationFactory.Companion;
        valueAnimatorArr[0] = AnimationFactory.Companion.createAnimation$default(companion, AnimationFactory.Companion.AnimationType.SHOW_SIZE_NOW_BAR_PHASE_1, new bd.e(animationManager, lightRadius, reflLightRadius, 0), (Runnable) null, new f(animationManager, 0), new c(animationManager, lightRadius, reflLightRadius, animationCallback, 1), l, 4, (Object) null);
        if (z) {
            ValueAnimator[] valueAnimatorArr2 = animationManager.showAnimations;
            AnimationFactory.Companion.AnimationType animationType = AnimationFactory.Companion.AnimationType.HIDE_SIZE_NOW_BAR;
            ValueAnimator[] valueAnimatorArr3 = valueAnimatorArr2;
            bd.e eVar = new bd.e(animationManager, lightRadius, reflLightRadius, 1);
            AnimationCallback animationCallback2 = animationCallback;
            c cVar = new c(animationManager, lightRadius, reflLightRadius, animationCallback2, 2);
            animationManager = this;
            valueAnimatorArr3[1] = AnimationFactory.Companion.createAnimation$default(companion, animationType, eVar, (Runnable) null, new c(animationManager, lightRadius, reflLightRadius, animationCallback2, 3), cVar, (Long) null, 36, (Object) null);
        } else {
            ValueAnimator[] valueAnimatorArr4 = animationManager.showAnimations;
            ValueAnimator[] valueAnimatorArr5 = valueAnimatorArr4;
            AnimationCallback animationCallback3 = animationCallback;
            valueAnimatorArr5[1] = AnimationFactory.Companion.createAnimation$default(companion, AnimationFactory.Companion.AnimationType.SHOW_SIZE_NOW_BAR_PHASE_2, new bd.e(animationManager, lightRadius, reflLightRadius, 2), (Runnable) null, new d(animationCallback3, 1), new c(animationManager, lightRadius, reflLightRadius, animationCallback3, 4), (Long) null, 36, (Object) null);
        }
        ValueAnimator valueAnimator = animationManager.showAnimations[0];
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    public static final void createNowBarAnimation$lambda$13(AnimationManager animationManager, float f, float f5, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "it");
        animationManager.updateSizeByAnimation(f, f5, valueAnimator);
    }

    /* access modifiers changed from: private */
    public static final void createNowBarAnimation$lambda$14(AnimationManager animationManager) {
        ValueAnimator valueAnimator = animationManager.showAnimations[1];
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    public static final void createNowBarAnimation$lambda$15(AnimationManager animationManager, float f, float f5, AnimationCallback animationCallback) {
        animationManager.updateSize(f, f5);
        if (animationCallback != null) {
            animationCallback.onAnimationCancel();
        }
    }

    /* access modifiers changed from: private */
    public static final void createNowBarAnimation$lambda$16(AnimationManager animationManager, float f, float f5, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "it");
        animationManager.updateSizeByAnimation(f, f5, valueAnimator);
    }

    /* access modifiers changed from: private */
    public static final void createNowBarAnimation$lambda$17(AnimationManager animationManager, float f, float f5, AnimationCallback animationCallback) {
        animationManager.updateSize(f, f5);
        if (animationCallback != null) {
            animationCallback.onAnimationCancel();
        }
    }

    /* access modifiers changed from: private */
    public static final void createNowBarAnimation$lambda$18(AnimationManager animationManager, float f, float f5, AnimationCallback animationCallback) {
        animationManager.updateSize(f, f5);
        if (animationCallback != null) {
            animationCallback.onAnimationEnd();
        }
    }

    /* access modifiers changed from: private */
    public static final void createNowBarAnimation$lambda$19(AnimationManager animationManager, float f, float f5, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "it");
        animationManager.updateSizeByAnimation(f, f5, valueAnimator);
    }

    /* access modifiers changed from: private */
    public static final void createNowBarAnimation$lambda$20(AnimationManager animationManager, float f, float f5, AnimationCallback animationCallback) {
        animationManager.updateSize(f, f5);
        if (animationCallback != null) {
            animationCallback.onAnimationCancel();
        }
    }

    /* access modifiers changed from: private */
    public static final void createNowBarAnimation$lambda$21(AnimationCallback animationCallback) {
        if (animationCallback != null) {
            animationCallback.onAnimationEnd();
        }
    }

    private final void createSizeAnimation(AnimationCallback animationCallback) {
        float lightRadius = this.config.getLightRadius();
        float reflLightRadius = this.config.getReflLightRadius();
        ValueAnimator[] valueAnimatorArr = this.showAnimations;
        AnimationFactory.Companion companion = AnimationFactory.Companion;
        AnimationCallback animationCallback2 = animationCallback;
        valueAnimatorArr[0] = AnimationFactory.Companion.createAnimation$default(companion, AnimationFactory.Companion.AnimationType.SHOW_SIZE_PHASE_1, new bd.e(this, lightRadius, reflLightRadius, 3), (Runnable) null, new f(this, 1), new c(this, lightRadius, reflLightRadius, animationCallback2, 5), (Long) null, 36, (Object) null);
        this.showAnimations[1] = AnimationFactory.Companion.createAnimation$default(companion, AnimationFactory.Companion.AnimationType.SHOW_SIZE_PHASE_2, new bd.e(this, lightRadius, reflLightRadius, 4), (Runnable) null, new d(animationCallback2, 0), new c(this, lightRadius, reflLightRadius, animationCallback2, 0), (Long) null, 36, (Object) null);
        float globalLuminance = this.config.getGlobalLuminance();
        this.showAnimations[2] = AnimationFactory.Companion.createAnimation$default(companion, AnimationFactory.Companion.AnimationType.SHOW_LUMINANCE, new b(this, globalLuminance, 1), (Runnable) null, (Runnable) null, new k(this, globalLuminance, 2), (Long) null, 44, (Object) null);
        ValueAnimator valueAnimator = this.showAnimations[0];
        if (valueAnimator != null) {
            valueAnimator.start();
        }
        ValueAnimator valueAnimator2 = this.showAnimations[2];
        if (valueAnimator2 != null) {
            valueAnimator2.start();
        }
    }

    /* access modifiers changed from: private */
    public static final void createSizeAnimation$lambda$2(AnimationManager animationManager, float f, float f5, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "it");
        animationManager.updateSizeByAnimation(f, f5, valueAnimator);
    }

    /* access modifiers changed from: private */
    public static final void createSizeAnimation$lambda$3(AnimationManager animationManager) {
        ValueAnimator valueAnimator = animationManager.showAnimations[1];
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    public static final void createSizeAnimation$lambda$4(AnimationManager animationManager, float f, float f5, AnimationCallback animationCallback) {
        animationManager.updateSize(f, f5);
        if (animationCallback != null) {
            animationCallback.onAnimationCancel();
        }
    }

    /* access modifiers changed from: private */
    public static final void createSizeAnimation$lambda$5(AnimationManager animationManager, float f, float f5, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "it");
        animationManager.updateSizeByAnimation(f, f5, valueAnimator);
    }

    /* access modifiers changed from: private */
    public static final void createSizeAnimation$lambda$6(AnimationManager animationManager, float f, float f5, AnimationCallback animationCallback) {
        animationManager.updateSize(f, f5);
        if (animationCallback != null) {
            animationCallback.onAnimationCancel();
        }
    }

    /* access modifiers changed from: private */
    public static final void createSizeAnimation$lambda$7(AnimationCallback animationCallback) {
        if (animationCallback != null) {
            animationCallback.onAnimationEnd();
        }
    }

    /* access modifiers changed from: private */
    public static final void createSizeAnimation$lambda$8(AnimationManager animationManager, float f, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "it");
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) animationManager.lightControl.getRenderEffect();
        if (guidingLightRenderEffect != null) {
            Object animatedValue = valueAnimator.getAnimatedValue();
            j.c(animatedValue, "null cannot be cast to non-null type kotlin.Float");
            guidingLightRenderEffect.setGlobalLuminance(((Float) animatedValue).floatValue() * f);
        }
    }

    /* access modifiers changed from: private */
    public static final void createSizeAnimation$lambda$9(AnimationManager animationManager, float f) {
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) animationManager.lightControl.getRenderEffect();
        if (guidingLightRenderEffect != null) {
            guidingLightRenderEffect.setGlobalLuminance(f);
        }
    }

    private final void hideByLuminance(AnimationFactory.Companion.AnimationType animationType, AnimationCallback animationCallback) {
        float globalLuminance = this.config.getGlobalLuminance();
        ValueAnimator createAnimation$default = AnimationFactory.Companion.createAnimation$default(AnimationFactory.Companion, animationType, new b(this, globalLuminance, 0), (Runnable) null, new g(this, globalLuminance, animationCallback, 0), new d(animationCallback, 2), (Long) null, 36, (Object) null);
        this.hideAnimation = createAnimation$default;
        if (createAnimation$default != null) {
            createAnimation$default.start();
        }
    }

    /* access modifiers changed from: private */
    public static final void hideByLuminance$lambda$22(AnimationManager animationManager, float f, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "it");
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) animationManager.lightControl.getRenderEffect();
        if (guidingLightRenderEffect != null) {
            Object animatedValue = valueAnimator.getAnimatedValue();
            j.c(animatedValue, "null cannot be cast to non-null type kotlin.Float");
            guidingLightRenderEffect.setGlobalLuminance(((Float) animatedValue).floatValue() * f);
        }
    }

    /* access modifiers changed from: private */
    public static final void hideByLuminance$lambda$23(AnimationManager animationManager, float f, AnimationCallback animationCallback) {
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) animationManager.lightControl.getRenderEffect();
        if (guidingLightRenderEffect != null) {
            guidingLightRenderEffect.setGlobalLuminance(f);
        }
        if (animationCallback != null) {
            animationCallback.onAnimationEnd();
        }
    }

    /* access modifiers changed from: private */
    public static final void hideByLuminance$lambda$24(AnimationCallback animationCallback) {
        if (animationCallback != null) {
            animationCallback.onAnimationCancel();
        }
    }

    private final void runHideAnimation(GuidingLightEffect.HideAnimationType hideAnimationType, AnimationCallback animationCallback) {
        cancelAllAnimations();
        if (animationCallback != null) {
            animationCallback.onAnimationStart();
        }
        int i2 = WhenMappings.$EnumSwitchMapping$1[hideAnimationType.ordinal()];
        if (i2 == 1) {
            hideByLuminance(AnimationFactory.Companion.AnimationType.HIDE_LUMINANCE, animationCallback);
        } else if (i2 == 2) {
            hideByLuminance(AnimationFactory.Companion.AnimationType.HIDE_LUMINANCE_LONG, animationCallback);
        } else if (i2 != 3) {
            throw new RuntimeException();
        } else if (animationCallback != null) {
            animationCallback.onAnimationEnd();
        }
    }

    private final void runShowAnimation(GuidingLightEffect.ShowAnimationType showAnimationType, Long l, AnimationCallback animationCallback) {
        cancelAllAnimations();
        if (animationCallback != null) {
            animationCallback.onAnimationStart();
        }
        switch (WhenMappings.$EnumSwitchMapping$0[showAnimationType.ordinal()]) {
            case 1:
                createSizeAnimation(animationCallback);
                return;
            case 2:
                createLuminanceAnimation(AnimationFactory.Companion.AnimationType.SHOW_LUMINANCE, animationCallback);
                return;
            case 3:
                createLuminanceAnimation(AnimationFactory.Companion.AnimationType.SHOW_LUMINANCE_LONG, animationCallback);
                return;
            case 4:
                createNowBarAnimation(l, animationCallback, false);
                return;
            case 5:
                createNowBarAnimation(l, animationCallback, true);
                return;
            case 6:
                GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) this.lightControl.getRenderEffect();
                if (guidingLightRenderEffect != null) {
                    guidingLightRenderEffect.update(true);
                }
                if (animationCallback != null) {
                    animationCallback.onAnimationEnd();
                    return;
                }
                return;
            default:
                throw new RuntimeException();
        }
    }

    private final void updateSize(float f, float f5) {
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) this.lightControl.getRenderEffect();
        if (guidingLightRenderEffect != null) {
            guidingLightRenderEffect.setLightRadius(f);
        }
        GuidingLightRenderEffect guidingLightRenderEffect2 = (GuidingLightRenderEffect) this.lightControl.getRenderEffect();
        if (guidingLightRenderEffect2 != null) {
            guidingLightRenderEffect2.setReflLightRadius(f5);
        }
    }

    private final void updateSizeByAnimation(float f, float f5, ValueAnimator valueAnimator) {
        Object animatedValue = valueAnimator.getAnimatedValue();
        j.c(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float floatValue = ((Float) animatedValue).floatValue() * f;
        Object animatedValue2 = valueAnimator.getAnimatedValue();
        j.c(animatedValue2, "null cannot be cast to non-null type kotlin.Float");
        updateSize(floatValue, ((Float) animatedValue2).floatValue() * f5);
    }

    public final void cancelAllAnimations() {
        for (ValueAnimator valueAnimator : this.showAnimations) {
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
        }
        ValueAnimator valueAnimator2 = this.hideAnimation;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
    }

    public final void hideWithAnimation(GuidingLightEffect.HideAnimationType hideAnimationType, AnimationCallback animationCallback) {
        j.e(hideAnimationType, "animationType");
        Log.i("AnimationManager", "Hide animation: " + hideAnimationType);
        runHideAnimation(hideAnimationType, animationCallback);
    }

    public final void release() {
        cancelAllAnimations();
        ValueAnimator[] valueAnimatorArr = this.showAnimations;
        j.e(valueAnimatorArr, "<this>");
        Iterator it = new Ge.c(0, valueAnimatorArr.length - 1, 1).iterator();
        while (it.hasNext()) {
            this.showAnimations[((y) it).nextInt()] = null;
        }
    }

    public final void showWithAnimation(GuidingLightEffect.ShowAnimationType showAnimationType, Long l, AnimationCallback animationCallback) {
        j.e(showAnimationType, "animationType");
        Log.i("AnimationManager", "Show animation: " + showAnimationType);
        runShowAnimation(showAnimationType, l, animationCallback);
    }
}
