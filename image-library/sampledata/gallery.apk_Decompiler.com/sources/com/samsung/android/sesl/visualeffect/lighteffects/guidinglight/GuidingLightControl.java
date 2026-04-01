package com.samsung.android.sesl.visualeffect.lighteffects.guidinglight;

import Ae.b;
import android.util.Log;
import bd.h;
import c0.C0086a;
import com.samsung.android.sesl.visualeffect.lighteffects.common.config.AnimatableAttribute;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.IColorEffect;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.IColorEffectKt;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.VibeEffectBase;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffect;
import com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.GuidingLightConfig;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;
import me.x;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J+\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\u00072\u0006\u0010\u0004\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightControl;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/control/VibeEffectBase;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightRenderEffect;", "config", "<init>", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;)V", "Lme/i;", "", "Landroid/animation/ValueAnimator;", "build", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;)Lme/i;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/control/IColorEffect;", "colorEffect", "Lme/x;", "setColorEffect", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/common/control/IColorEffect;)V", "applyConfig", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GuidingLightControl extends VibeEffectBase<GuidingLightConfig, GuidingLightRenderEffect> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GuidingLightControl(GuidingLightConfig guidingLightConfig) {
        super(guidingLightConfig);
        j.e(guidingLightConfig, "config");
    }

    /* access modifiers changed from: private */
    public static final x setColorEffect$lambda$1(VibeRenderEffectBase vibeRenderEffectBase, GuidingLightControl guidingLightControl, IColorEffect iColorEffect, boolean z) {
        if (vibeRenderEffectBase.getShaderUpdated()) {
            GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) guidingLightControl.getRenderEffect();
            if (guidingLightRenderEffect != null) {
                guidingLightRenderEffect.setTintShader(vibeRenderEffectBase.getShader(), IColorEffectKt.toPointF(iColorEffect.getDrawingBufferSize()));
            }
            vibeRenderEffectBase.setShaderUpdated$sesl_visualeffect_INTERNAL_2_1_6_release(false);
        }
        return x.f4917a;
    }

    public final void applyConfig(GuidingLightConfig guidingLightConfig) {
        GuidingLightConfig guidingLightConfig2 = guidingLightConfig;
        j.e(guidingLightConfig2, "config");
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) getRenderEffect();
        if (guidingLightRenderEffect != null) {
            guidingLightRenderEffect.setLightPos(guidingLightConfig2.getLightPos());
            guidingLightRenderEffect.setLightRadius(guidingLightConfig2.getLightRadius());
            guidingLightRenderEffect.setLightIntensity(guidingLightConfig2.getLightIntensity());
            guidingLightRenderEffect.setGlowRadius(guidingLightConfig2.getGlowRadius());
            guidingLightRenderEffect.setGlowIntensity(guidingLightConfig2.getGlowIntensity());
            guidingLightRenderEffect.setGlowSharpness(guidingLightConfig2.getGlowSharpness());
            guidingLightRenderEffect.setReflLightRadius(guidingLightConfig2.getReflLightRadius());
            guidingLightRenderEffect.setReflLightIntensity(guidingLightConfig2.getReflLightIntensity());
            guidingLightRenderEffect.setReflAlbedo(guidingLightConfig2.getReflAlbedo());
            guidingLightRenderEffect.setFrameRate(guidingLightConfig2.getFrameRate());
            guidingLightRenderEffect.setGlobalLuminance(guidingLightConfig2.getGlobalLuminance());
            guidingLightRenderEffect.setDitherVariation(guidingLightConfig2.getDitherVariation());
            guidingLightRenderEffect.setSaturation(guidingLightConfig2.getSaturation());
            guidingLightRenderEffect.setStretch(guidingLightConfig2.getStretch());
            guidingLightRenderEffect.setOuterSaturation(guidingLightConfig2.getOuterSaturation());
            guidingLightRenderEffect.setStretchDirLit(guidingLightConfig2.getStretchDirLit());
            guidingLightRenderEffect.setOutlineThickness(guidingLightConfig2.getOutlineThickness());
            guidingLightRenderEffect.setBoundarySmoothWidth(guidingLightConfig2.getBoundarySmoothWidth());
            GuidingLightConfig.Shape shape = guidingLightConfig2.getShape();
            GuidingLightConfig.ShaderPrecision shaderPrecision = guidingLightConfig2.getShaderPrecision();
            float lightRadius = guidingLightConfig2.getLightRadius();
            float lightIntensity = guidingLightConfig2.getLightIntensity();
            float frameRate = guidingLightConfig2.getFrameRate();
            float glowIntensity = guidingLightConfig2.getGlowIntensity();
            float glowRadius = guidingLightConfig2.getGlowRadius();
            float glowSharpness = guidingLightConfig2.getGlowSharpness();
            float reflLightIntensity = guidingLightConfig2.getReflLightIntensity();
            float reflLightRadius = guidingLightConfig2.getReflLightRadius();
            float reflAlbedo = guidingLightConfig2.getReflAlbedo();
            float globalLuminance = guidingLightConfig2.getGlobalLuminance();
            float saturation = guidingLightConfig2.getSaturation();
            float outerSaturation = guidingLightConfig2.getOuterSaturation();
            float stretch = guidingLightConfig2.getStretch();
            float stretchDirLit = guidingLightConfig2.getStretchDirLit();
            StringBuilder sb2 = new StringBuilder("GuidingLightConfig shape:");
            sb2.append(shape);
            sb2.append(" precision:");
            sb2.append(shaderPrecision);
            sb2.append(" radius:");
            C0086a.y(sb2, lightRadius, " intensity:", lightIntensity, " frameRate:");
            C0086a.y(sb2, frameRate, " glowIntensity:", glowIntensity, " glowRadius:");
            C0086a.y(sb2, glowRadius, " glowSharpness:", glowSharpness, " refIntensity:");
            C0086a.y(sb2, reflLightIntensity, " refRadius:", reflLightRadius, " refAlbedo: ");
            C0086a.y(sb2, reflAlbedo, "  gLuminance:", globalLuminance, " saturation:");
            C0086a.y(sb2, saturation, " outerSaturation:", outerSaturation, " stretch:");
            sb2.append(stretch);
            sb2.append(" stretchDirLit: ");
            sb2.append(stretchDirLit);
            Log.i("GuidingLightConfig", sb2.toString());
        }
    }

    public final void setColorEffect(IColorEffect iColorEffect) {
        ArrayList<b> onUpdateListeners;
        j.e(iColorEffect, "colorEffect");
        VibeRenderEffect colorRenderEffect = iColorEffect.getColorRenderEffect();
        j.c(colorRenderEffect, "null cannot be cast to non-null type com.samsung.android.sesl.visualeffect.lighteffects.common.runtimshader.VibeRenderEffectBase");
        VibeRenderEffectBase vibeRenderEffectBase = (VibeRenderEffectBase) colorRenderEffect;
        GuidingLightRenderEffect guidingLightRenderEffect = (GuidingLightRenderEffect) getRenderEffect();
        if (guidingLightRenderEffect != null) {
            guidingLightRenderEffect.setTintShader(vibeRenderEffectBase.getShader(), IColorEffectKt.toPointF(iColorEffect.getDrawingBufferSize()));
        }
        GuidingLightRenderEffect guidingLightRenderEffect2 = (GuidingLightRenderEffect) getRenderEffect();
        if (guidingLightRenderEffect2 != null && (onUpdateListeners = guidingLightRenderEffect2.getOnUpdateListeners()) != null) {
            onUpdateListeners.add(new h(vibeRenderEffectBase, this, iColorEffect, 0));
        }
    }

    public i build(GuidingLightConfig guidingLightConfig) {
        j.e(guidingLightConfig, "config");
        GuidingLightRenderEffect guidingLightRenderEffect = new GuidingLightRenderEffect(guidingLightConfig.getShape() == GuidingLightConfig.Shape.RoundRect, guidingLightConfig.getShaderPrecision());
        getVibeRenderEffect().add(guidingLightRenderEffect);
        applyConfig(guidingLightConfig);
        ArrayList arrayList = new ArrayList();
        for (AnimatableAttribute build$sesl_visualeffect_INTERNAL_2_1_6_release : guidingLightConfig.getAnimatableAttribute$sesl_visualeffect_INTERNAL_2_1_6_release()) {
            arrayList.add(build$sesl_visualeffect_INTERNAL_2_1_6_release.build$sesl_visualeffect_INTERNAL_2_1_6_release(this));
        }
        return new i(guidingLightRenderEffect, arrayList);
    }
}
