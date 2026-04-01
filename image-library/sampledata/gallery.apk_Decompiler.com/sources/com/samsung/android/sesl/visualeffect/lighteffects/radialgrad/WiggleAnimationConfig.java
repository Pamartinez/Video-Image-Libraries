package com.samsung.android.sesl.visualeffect.lighteffects.radialgrad;

import Ae.b;
import Ee.a;
import Ee.d;
import Vf.C0874k;
import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PointF;
import android.view.animation.Interpolator;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sesl.visualeffect.lighteffects.common.config.AnimatableAttribute;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.VibeEffectControl;
import com.samsung.android.sum.core.message.Message;
import dd.g;
import dd.h;
import dd.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1195m;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\b\b\u0018\u0000 S2\u00020\u0001:\u0001SBI\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006¢\u0006\u0004\b\f\u0010\rJ\u0001\u0010\u0019\u001a6\u0012\u0004\u0012\u00020\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00100\u000e0\u0014j\u001a\u0012\u0004\u0012\u00020\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00100\u000e`\u00162\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00100\u000e2\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000eH\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0001\u0010%\u001a\b\u0012\u0004\u0012\u00020 0\"2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\t2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00100\u000e2\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000e2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00100\u000eH\u0000¢\u0006\u0004\b#\u0010$J\u0001\u0010%\u001a\b\u0012\u0004\u0012\u00020 0\"2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\t2:\u0010&\u001a6\u0012\u0004\u0012\u00020\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00100\u000e0\u0014j\u001a\u0012\u0004\u0012\u00020\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00100\u000e`\u00162\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00100\u000eH\u0001¢\u0006\u0004\b#\u0010(J\u0010\u0010)\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b)\u0010*J\u0010\u0010+\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b+\u0010,J\u001a\u0010/\u001a\u00020.2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b/\u00100J;\u00107\u001a\u0002062\u0006\u00101\u001a\u00020\u00152\u0006\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u00062\b\b\u0002\u00104\u001a\u00020\u00062\b\b\u0002\u00105\u001a\u00020\u0006H\u0002¢\u0006\u0004\b7\u00108J5\u0010?\u001a\u00020\u00102\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u0002092\u0006\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020\t2\u0006\u0010>\u001a\u00020=H\u0002¢\u0006\u0004\b?\u0010@R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010A\u001a\u0004\bB\u0010CR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010D\u001a\u0004\bE\u0010FR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010G\u001a\u0004\bH\u0010IR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010G\u001a\u0004\bJ\u0010IR\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010K\u001a\u0004\bL\u0010MR\u0017\u0010\u000b\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u000b\u0010N\u001a\u0004\bO\u0010PR\u0014\u0010R\u001a\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010P¨\u0006T"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/WiggleAnimationConfig;", "", "", "interval", "Landroid/view/animation/Interpolator;", "interpolation", "", "deltaPosition", "deltaScale", "", "changeColor", "fps", "<init>", "(JLandroid/view/animation/Interpolator;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Integer;F)V", "Lkotlin/Function1;", "Landroid/graphics/PointF;", "Lme/x;", "positionListener", "scaleListener", "colorListener", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "buildListener$sesl_visualeffect_INTERNAL_2_1_6_release", "(LAe/b;LAe/b;LAe/b;)Ljava/util/HashMap;", "buildListener", "initialPosition", "initialScale", "initialColor", "currentPosition", "currentScale", "currentColor", "Landroid/animation/ValueAnimator;", "onTransformAnimationFinish", "", "build$sesl_visualeffect_INTERNAL_2_1_6_release", "(Landroid/graphics/PointF;FILandroid/graphics/PointF;FILAe/b;LAe/b;LAe/b;LAe/b;)Ljava/util/List;", "build", "onUpdateListeners", "onAnimationFinish", "(Landroid/graphics/PointF;FILandroid/graphics/PointF;FILjava/util/HashMap;LAe/b;)Ljava/util/List;", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "keyString", "fromValue", "toValue", "fromFraction", "toFraction", "Landroid/animation/PropertyValuesHolder;", "makeKeyframe", "(Ljava/lang/String;FFFF)Landroid/animation/PropertyValuesHolder;", "", "lastTime", "index", "threshold", "Ljava/lang/Runnable;", "runnable", "invokeBasedFps", "([Ljava/lang/Long;IILjava/lang/Runnable;)V", "J", "getInterval", "()J", "Landroid/view/animation/Interpolator;", "getInterpolation", "()Landroid/view/animation/Interpolator;", "Ljava/lang/Float;", "getDeltaPosition", "()Ljava/lang/Float;", "getDeltaScale", "Ljava/lang/Integer;", "getChangeColor", "()Ljava/lang/Integer;", "F", "getFps", "()F", "getRandf", "randf", "Companion", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WiggleAnimationConfig {
    public static final Companion Companion = new Companion((e) null);
    private final Integer changeColor;
    private final Float deltaPosition;
    private final Float deltaScale;
    private final float fps;
    private final Interpolator interpolation;
    private final long interval;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/WiggleAnimationConfig$Companion;", "", "<init>", "()V", "KEY_POSITION", "", "KEY_POSITION_X", "KEY_POSITION_Y", "KEY_SCALE", "KEY_COLORINT", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public WiggleAnimationConfig(long j2, Interpolator interpolator, Float f, Float f5, Integer num, float f8) {
        j.e(interpolator, "interpolation");
        this.interval = j2;
        this.interpolation = interpolator;
        this.deltaPosition = f;
        this.deltaScale = f5;
        this.changeColor = num;
        this.fps = f8;
    }

    /* access modifiers changed from: private */
    public static final void build$lambda$13$lambda$12(WiggleAnimationConfig wiggleAnimationConfig, Long[] lArr, int i2, HashMap hashMap, ValueAnimator valueAnimator, ValueAnimator valueAnimator2) {
        j.e(valueAnimator2, "it");
        if (wiggleAnimationConfig.deltaPosition != null) {
            wiggleAnimationConfig.invokeBasedFps(lArr, 0, i2, new i(hashMap, valueAnimator, 0));
        }
        if (wiggleAnimationConfig.deltaScale != null) {
            wiggleAnimationConfig.invokeBasedFps(lArr, 1, i2, new i(hashMap, valueAnimator, 1));
        }
    }

    /* access modifiers changed from: private */
    public static final void build$lambda$13$lambda$12$lambda$11$lambda$10(HashMap hashMap, ValueAnimator valueAnimator) {
        b bVar = (b) hashMap.get("scale");
        if (bVar != null) {
            Object animatedValue = valueAnimator.getAnimatedValue("scale");
            j.c(animatedValue, "null cannot be cast to non-null type kotlin.Float");
            bVar.invoke((Float) animatedValue);
        }
    }

    /* access modifiers changed from: private */
    public static final void build$lambda$13$lambda$12$lambda$9$lambda$8(HashMap hashMap, ValueAnimator valueAnimator) {
        b bVar = (b) hashMap.get(Message.KEY_POSITION);
        if (bVar != null) {
            Object animatedValue = valueAnimator.getAnimatedValue("x");
            j.c(animatedValue, "null cannot be cast to non-null type kotlin.Float");
            float floatValue = ((Float) animatedValue).floatValue();
            Object animatedValue2 = valueAnimator.getAnimatedValue("y");
            j.c(animatedValue2, "null cannot be cast to non-null type kotlin.Float");
            bVar.invoke(new PointF(floatValue, ((Float) animatedValue2).floatValue()));
        }
    }

    /* access modifiers changed from: private */
    public static final x build$lambda$15$lambda$14(HashMap hashMap, VibeEffectControl vibeEffectControl, float f, Color color) {
        j.e(color, "animatedValue");
        b bVar = (b) hashMap.get("color");
        if (bVar != null) {
            bVar.invoke(Integer.valueOf(color.toArgb()));
        }
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x buildListener$lambda$4$lambda$0(b bVar, Object obj) {
        j.e(obj, "it");
        bVar.invoke((PointF) obj);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x buildListener$lambda$4$lambda$1(b bVar, Object obj) {
        j.e(obj, "it");
        bVar.invoke((Float) obj);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x buildListener$lambda$4$lambda$3$lambda$2(b bVar, Object obj) {
        j.e(obj, "it");
        bVar.invoke((Integer) obj);
        return x.f4917a;
    }

    private final float getRandf() {
        a aVar = d.d;
        return (d.d.d().nextFloat() - 0.5f) * 2.0f;
    }

    private final void invokeBasedFps(Long[] lArr, int i2, int i7, Runnable runnable) {
        long nanoTime = System.nanoTime();
        if (nanoTime - lArr[i2].longValue() >= ((long) i7)) {
            runnable.run();
            lArr[i2] = Long.valueOf(nanoTime);
        }
    }

    private final PropertyValuesHolder makeKeyframe(String str, float f, float f5, float f8, float f10) {
        PropertyValuesHolder ofKeyframe = PropertyValuesHolder.ofKeyframe(str, new Keyframe[]{Keyframe.ofFloat(f8, f), Keyframe.ofFloat(f10, f5)});
        j.d(ofKeyframe, "ofKeyframe(...)");
        return ofKeyframe;
    }

    public static /* synthetic */ PropertyValuesHolder makeKeyframe$default(WiggleAnimationConfig wiggleAnimationConfig, String str, float f, float f5, float f8, float f10, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            f8 = 0.0f;
        }
        float f11 = f8;
        if ((i2 & 16) != 0) {
            f10 = 1.0f;
        }
        return wiggleAnimationConfig.makeKeyframe(str, f, f5, f11, f10);
    }

    public final List<ValueAnimator> build$sesl_visualeffect_INTERNAL_2_1_6_release(PointF pointF, float f, int i2, PointF pointF2, float f5, int i7, b bVar, b bVar2, b bVar3, b bVar4) {
        j.e(pointF, "initialPosition");
        j.e(pointF2, "currentPosition");
        j.e(bVar, "positionListener");
        j.e(bVar2, "scaleListener");
        j.e(bVar4, "onTransformAnimationFinish");
        return build$sesl_visualeffect_INTERNAL_2_1_6_release(pointF, f, i2, pointF2, f5, i7, buildListener$sesl_visualeffect_INTERNAL_2_1_6_release(bVar, bVar2, bVar3), bVar4);
    }

    public final HashMap<String, b> buildListener$sesl_visualeffect_INTERNAL_2_1_6_release(b bVar, b bVar2, b bVar3) {
        j.e(bVar, "positionListener");
        j.e(bVar2, "scaleListener");
        HashMap<String, b> hashMap = new HashMap<>();
        hashMap.put(Message.KEY_POSITION, new h(0, bVar));
        hashMap.put("scale", new h(1, bVar2));
        if (bVar3 != null) {
            hashMap.put("color", new h(2, bVar3));
        }
        return hashMap;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WiggleAnimationConfig)) {
            return false;
        }
        WiggleAnimationConfig wiggleAnimationConfig = (WiggleAnimationConfig) obj;
        if (this.interval == wiggleAnimationConfig.interval && j.a(this.interpolation, wiggleAnimationConfig.interpolation) && j.a(this.deltaPosition, wiggleAnimationConfig.deltaPosition) && j.a(this.deltaScale, wiggleAnimationConfig.deltaScale) && j.a(this.changeColor, wiggleAnimationConfig.changeColor) && Float.compare(this.fps, wiggleAnimationConfig.fps) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int i7;
        int hashCode = (this.interpolation.hashCode() + (Long.hashCode(this.interval) * 31)) * 31;
        Float f = this.deltaPosition;
        int i8 = 0;
        if (f == null) {
            i2 = 0;
        } else {
            i2 = f.hashCode();
        }
        int i10 = (hashCode + i2) * 31;
        Float f5 = this.deltaScale;
        if (f5 == null) {
            i7 = 0;
        } else {
            i7 = f5.hashCode();
        }
        int i11 = (i10 + i7) * 31;
        Integer num = this.changeColor;
        if (num != null) {
            i8 = num.hashCode();
        }
        return Float.hashCode(this.fps) + ((i11 + i8) * 31);
    }

    public String toString() {
        long j2 = this.interval;
        Interpolator interpolator = this.interpolation;
        Float f = this.deltaPosition;
        Float f5 = this.deltaScale;
        Integer num = this.changeColor;
        float f8 = this.fps;
        return "WiggleAnimationConfig(interval=" + j2 + ", interpolation=" + interpolator + ", deltaPosition=" + f + ", deltaScale=" + f5 + ", changeColor=" + num + ", fps=" + f8 + ")";
    }

    public final List<ValueAnimator> build$sesl_visualeffect_INTERNAL_2_1_6_release(PointF pointF, float f, int i2, PointF pointF2, float f5, int i7, HashMap<String, b> hashMap, b bVar) {
        PropertyValuesHolder propertyValuesHolder;
        PropertyValuesHolder propertyValuesHolder2;
        PropertyValuesHolder propertyValuesHolder3;
        PointF pointF3 = pointF;
        PointF pointF4 = pointF2;
        HashMap<String, b> hashMap2 = hashMap;
        b bVar2 = bVar;
        j.e(pointF3, "initialPosition");
        j.e(pointF4, "currentPosition");
        j.e(hashMap2, "onUpdateListeners");
        j.e(bVar2, "onAnimationFinish");
        ValueAnimator valueAnimator = null;
        if (this.deltaPosition != null) {
            propertyValuesHolder = makeKeyframe$default(this, "x", pointF4.x, (this.deltaPosition.floatValue() * getRandf()) + pointF3.x, 0.0f, 0.0f, 24, (Object) null);
        } else {
            propertyValuesHolder = null;
        }
        if (this.deltaPosition != null) {
            propertyValuesHolder2 = makeKeyframe$default(this, "y", pointF4.y, (this.deltaPosition.floatValue() * getRandf()) + pointF3.y, 0.0f, 0.0f, 24, (Object) null);
        } else {
            propertyValuesHolder2 = null;
        }
        if (this.deltaScale != null) {
            propertyValuesHolder3 = makeKeyframe$default(this, "scale", f5, (this.deltaScale.floatValue() * getRandf()) + f, 0.0f, 0.0f, 24, (Object) null);
        } else {
            propertyValuesHolder3 = null;
        }
        ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(new PropertyValuesHolder[]{propertyValuesHolder, propertyValuesHolder2, propertyValuesHolder3});
        ofPropertyValuesHolder.setRepeatCount(0);
        ofPropertyValuesHolder.setRepeatMode(1);
        ofPropertyValuesHolder.setInterpolator(this.interpolation);
        ofPropertyValuesHolder.setDuration(this.interval);
        int i8 = (int) (((float) CommonConstants.SupportedApiMinVersion.VERSION_10_0) / this.fps);
        long j2 = (long) i8;
        HashMap<String, b> hashMap3 = hashMap2;
        HashMap<String, b> hashMap4 = hashMap3;
        ofPropertyValuesHolder.addUpdateListener(new g(this, new Long[]{Long.valueOf(System.nanoTime() - j2), Long.valueOf(System.nanoTime() - j2)}, i8, hashMap3, ofPropertyValuesHolder));
        ofPropertyValuesHolder.addListener(new WiggleAnimationConfig$build$xFormAnim$4$2(bVar2));
        if (!(this.changeColor == null || hashMap4.get("color") == null)) {
            long j3 = this.interval;
            Interpolator interpolator = this.interpolation;
            Color valueOf = Color.valueOf(i2);
            j.d(valueOf, "valueOf(...)");
            Color valueOf2 = Color.valueOf(this.changeColor.intValue());
            j.d(valueOf2, "valueOf(...)");
            valueAnimator = new AnimatableAttribute(j3, 0, valueOf, valueOf2, interpolator, 2, -1, (Animator.AnimatorListener) null, new C0874k(2, (Object) hashMap4), 130, (e) null).build$sesl_visualeffect_INTERNAL_2_1_6_release((VibeEffectControl) null);
        }
        ArrayList o0 = C1195m.o0(ofPropertyValuesHolder);
        if (valueAnimator != null) {
            o0.add(valueAnimator);
        }
        return o0;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ WiggleAnimationConfig(long r8, android.view.animation.Interpolator r10, java.lang.Float r11, java.lang.Float r12, java.lang.Integer r13, float r14, int r15, kotlin.jvm.internal.e r16) {
        /*
            r7 = this;
            r0 = r15 & 1
            if (r0 == 0) goto L_0x0007
            r0 = 1100(0x44c, double:5.435E-321)
            goto L_0x0008
        L_0x0007:
            r0 = r8
        L_0x0008:
            r2 = r15 & 2
            if (r2 == 0) goto L_0x0012
            android.view.animation.LinearInterpolator r2 = new android.view.animation.LinearInterpolator
            r2.<init>()
            goto L_0x0013
        L_0x0012:
            r2 = r10
        L_0x0013:
            r3 = r15 & 4
            r4 = 0
            if (r3 == 0) goto L_0x001a
            r3 = r4
            goto L_0x001b
        L_0x001a:
            r3 = r11
        L_0x001b:
            r5 = r15 & 8
            if (r5 == 0) goto L_0x0021
            r5 = r4
            goto L_0x0022
        L_0x0021:
            r5 = r12
        L_0x0022:
            r6 = r15 & 16
            if (r6 == 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r4 = r13
        L_0x0028:
            r6 = r15 & 32
            if (r6 == 0) goto L_0x0036
            r6 = 1114636288(0x42700000, float:60.0)
            r15 = r6
        L_0x002f:
            r8 = r7
            r9 = r0
            r11 = r2
            r12 = r3
            r14 = r4
            r13 = r5
            goto L_0x0038
        L_0x0036:
            r15 = r14
            goto L_0x002f
        L_0x0038:
            r8.<init>(r9, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.WiggleAnimationConfig.<init>(long, android.view.animation.Interpolator, java.lang.Float, java.lang.Float, java.lang.Integer, float, int, kotlin.jvm.internal.e):void");
    }
}
