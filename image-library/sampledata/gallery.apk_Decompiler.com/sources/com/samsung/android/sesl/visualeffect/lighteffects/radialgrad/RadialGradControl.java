package com.samsung.android.sesl.visualeffect.lighteffects.radialgrad;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.Size;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.IColorEffect;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.VibeEffectBase;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffect;
import dd.a;
import dd.b;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;
import me.x;
import ne.C1195m;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J%\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u000b\u0010\fJ+\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\r2\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u000fR*\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00108\u0016@VX\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u001b\u001a\u00020\u00188VX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradControl;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/control/VibeEffectBase;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradConfig;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradRenderEffect;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/control/IColorEffect;", "config", "<init>", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradConfig;)V", "effect", "", "Landroid/animation/ValueAnimator;", "buildAnimations", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradConfig;Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradRenderEffect;)Ljava/util/List;", "Lme/i;", "build", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradConfig;)Lme/i;", "Landroid/util/Size;", "value", "drawingBufferSize", "Landroid/util/Size;", "getDrawingBufferSize", "()Landroid/util/Size;", "setDrawingBufferSize", "(Landroid/util/Size;)V", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffect;", "getColorRenderEffect", "()Lcom/samsung/android/sesl/visualeffect/lighteffects/common/runtimshader/VibeRenderEffect;", "colorRenderEffect", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RadialGradControl extends VibeEffectBase<RadialGradConfig, RadialGradRenderEffect> implements IColorEffect {
    private Size drawingBufferSize;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RadialGradControl(RadialGradConfig radialGradConfig) {
        super(radialGradConfig);
        j.e(radialGradConfig, "config");
        Size drawingBufferSize2 = radialGradConfig.getDrawingBufferSize();
        this.drawingBufferSize = drawingBufferSize2 == null ? new Size(0, 0) : drawingBufferSize2;
    }

    private final List<ValueAnimator> buildAnimations(RadialGradConfig radialGradConfig, RadialGradRenderEffect radialGradRenderEffect) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (T next : radialGradConfig.getSpotConfigs()) {
            int i7 = i2 + 1;
            if (i2 >= 0) {
                RadialGradControl radialGradControl = this;
                arrayList.addAll(buildAnimations$lambda$10$lambda$9$buildWiggle$default((SpotConfig) next, radialGradRenderEffect, i2, radialGradControl, false, 16, (Object) null));
                this = radialGradControl;
                i2 = i7;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        return arrayList;
    }

    private static final List<ValueAnimator> buildAnimations$lambda$10$lambda$9$buildWiggle(SpotConfig spotConfig, RadialGradRenderEffect radialGradRenderEffect, int i2, RadialGradControl radialGradControl, boolean z) {
        a aVar;
        RadialGradRenderEffect radialGradRenderEffect2 = radialGradRenderEffect;
        int i7 = i2;
        WiggleAnimationConfig wiggle = spotConfig.getWiggle();
        if (wiggle != null) {
            PointF position = spotConfig.getPosition();
            float scale = spotConfig.getScale();
            int color = spotConfig.getColor();
            WiggleAnimationConfig wiggleAnimationConfig = wiggle;
            int spotColor = radialGradRenderEffect.getSpotColor(i2);
            PointF spotPosition = radialGradRenderEffect.getSpotPosition(i2);
            float spotScale = radialGradRenderEffect.getSpotScale(i2);
            if (z) {
                aVar = null;
            } else {
                aVar = new a(radialGradRenderEffect2, i7, 0);
            }
            a aVar2 = aVar;
            PointF pointF = position;
            a aVar3 = new a(radialGradRenderEffect2, i7, 1);
            float f = scale;
            a aVar4 = new a(radialGradRenderEffect2, i7, 2);
            b bVar = new b(radialGradControl, spotConfig, radialGradRenderEffect2, i7, 0);
            PointF pointF2 = pointF;
            float f5 = f;
            List<ValueAnimator> build$sesl_visualeffect_INTERNAL_2_1_6_release = wiggleAnimationConfig.build$sesl_visualeffect_INTERNAL_2_1_6_release(pointF2, f5, color, spotPosition, spotScale, spotColor, aVar3, aVar4, aVar2, bVar);
            if (build$sesl_visualeffect_INTERNAL_2_1_6_release != null) {
                return build$sesl_visualeffect_INTERNAL_2_1_6_release;
            }
        }
        return new ArrayList();
    }

    public static /* synthetic */ List buildAnimations$lambda$10$lambda$9$buildWiggle$default(SpotConfig spotConfig, RadialGradRenderEffect radialGradRenderEffect, int i2, RadialGradControl radialGradControl, boolean z, int i7, Object obj) {
        if ((i7 & 16) != 0) {
            z = false;
        }
        return buildAnimations$lambda$10$lambda$9$buildWiggle(spotConfig, radialGradRenderEffect, i2, radialGradControl, z);
    }

    /* access modifiers changed from: private */
    public static final x buildAnimations$lambda$10$lambda$9$buildWiggle$lambda$4(RadialGradRenderEffect radialGradRenderEffect, int i2, int i7) {
        radialGradRenderEffect.setSpotColor(i2, i7);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x buildAnimations$lambda$10$lambda$9$buildWiggle$lambda$5(RadialGradRenderEffect radialGradRenderEffect, int i2, PointF pointF) {
        j.e(pointF, "p");
        radialGradRenderEffect.setSpotPosition(i2, pointF);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x buildAnimations$lambda$10$lambda$9$buildWiggle$lambda$6(RadialGradRenderEffect radialGradRenderEffect, int i2, float f) {
        radialGradRenderEffect.setSpotScale(i2, f);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x buildAnimations$lambda$10$lambda$9$buildWiggle$lambda$8(RadialGradControl radialGradControl, SpotConfig spotConfig, RadialGradRenderEffect radialGradRenderEffect, int i2, ValueAnimator valueAnimator) {
        j.e(valueAnimator, "anim");
        radialGradControl.getAnimators$sesl_visualeffect_INTERNAL_2_1_6_release().remove(valueAnimator);
        List<ValueAnimator> buildAnimations$lambda$10$lambda$9$buildWiggle = buildAnimations$lambda$10$lambda$9$buildWiggle(spotConfig, radialGradRenderEffect, i2, radialGradControl, true);
        for (ValueAnimator start : buildAnimations$lambda$10$lambda$9$buildWiggle) {
            start.start();
        }
        radialGradControl.getAnimators$sesl_visualeffect_INTERNAL_2_1_6_release().addAll(buildAnimations$lambda$10$lambda$9$buildWiggle);
        return x.f4917a;
    }

    public VibeRenderEffect getColorRenderEffect() {
        VibeRenderEffect renderEffect = getRenderEffect();
        j.b(renderEffect);
        return renderEffect;
    }

    public Size getDrawingBufferSize() {
        return this.drawingBufferSize;
    }

    public i build(RadialGradConfig radialGradConfig) {
        j.e(radialGradConfig, "config");
        RadialGradRenderEffect radialGradRenderEffect = new RadialGradRenderEffect();
        radialGradRenderEffect.setBlurRadius(radialGradConfig.getBlurRadius());
        Bitmap radialMap = radialGradConfig.getRadialMap();
        if (radialMap != null) {
            radialGradRenderEffect.setLightMap(radialMap);
        }
        Size drawingBufferSize2 = radialGradConfig.getDrawingBufferSize();
        if (drawingBufferSize2 != null) {
            radialGradRenderEffect.setSize(drawingBufferSize2.getWidth(), drawingBufferSize2.getHeight());
        }
        radialGradRenderEffect.setBackgroundColor(radialGradConfig.getBackgroundColor());
        radialGradRenderEffect.setSpotCount(radialGradConfig.getSpotConfigs().size());
        int i2 = 0;
        for (T next : radialGradConfig.getSpotConfigs()) {
            int i7 = i2 + 1;
            if (i2 >= 0) {
                SpotConfig spotConfig = (SpotConfig) next;
                radialGradRenderEffect.setSpotEnabled(i2, spotConfig.getEnabled());
                radialGradRenderEffect.setSpotColor(i2, spotConfig.getColor());
                radialGradRenderEffect.setSpotScale(i2, spotConfig.getScale());
                radialGradRenderEffect.setSpotPosition(i2, spotConfig.getPosition());
                i2 = i7;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        return new i(radialGradRenderEffect, buildAnimations(radialGradConfig, radialGradRenderEffect));
    }
}
