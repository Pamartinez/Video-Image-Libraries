package com.samsung.android.sesl.visualeffect.lighteffects.processinglight;

import E7.c;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;
import ba.C0582a;
import com.samsung.android.sesl.visualeffect.lighteffects.common.config.AnimatableAttribute;
import com.samsung.android.sesl.visualeffect.lighteffects.common.control.VibeEffectBase;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightRenderEffect;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0007\b\u0001\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J+\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\b2\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u0007R*\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0006@FX\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R*\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00178\u0006@FX\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR*\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0006@FX\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0012\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0016R.\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010\u0010\u001a\u0004\u0018\u00010!8\u0006@FX\u000e¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006("}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightControl;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/control/VibeEffectBase;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightRenderEffect;", "", "config", "<init>", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig;)V", "Lme/i;", "", "Landroid/animation/ValueAnimator;", "build", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/processinglight/ProcessingLightConfig;)Lme/i;", "Lme/x;", "buildAnimation", "", "value", "lightScale", "F", "getLightScale", "()F", "setLightScale", "(F)V", "Landroid/graphics/PointF;", "lightPosition", "Landroid/graphics/PointF;", "getLightPosition", "()Landroid/graphics/PointF;", "setLightPosition", "(Landroid/graphics/PointF;)V", "lightIntensity", "getLightIntensity", "setLightIntensity", "", "domainColor", "Ljava/lang/Integer;", "getDomainColor", "()Ljava/lang/Integer;", "setDomainColor", "(Ljava/lang/Integer;)V", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ProcessingLightControl extends VibeEffectBase<ProcessingLightConfig, ProcessingLightRenderEffect> {
    private Integer domainColor = 0;
    private float lightIntensity = 1.0f;
    private PointF lightPosition = ProcessingLightRenderEffect.Companion.Default.INSTANCE.getLightPos();
    private float lightScale = 2.05f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProcessingLightControl(ProcessingLightConfig processingLightConfig) {
        super(processingLightConfig);
        j.e(processingLightConfig, "config");
    }

    /* access modifiers changed from: private */
    public static final void buildAnimation$lambda$6(ProcessingLightControl processingLightControl, View view) {
        j.e(view, "it");
        ProcessingLightRenderEffect processingLightRenderEffect = (ProcessingLightRenderEffect) processingLightControl.getRenderEffect();
        if (processingLightRenderEffect != null) {
            processingLightRenderEffect.remove(view);
        }
    }

    /* access modifiers changed from: private */
    public static final void buildAnimation$lambda$7(ProcessingLightControl processingLightControl, boolean z, View view) {
        ProcessingLightRenderEffect processingLightRenderEffect;
        j.e(view, "it");
        ProcessingLightRenderEffect processingLightRenderEffect2 = (ProcessingLightRenderEffect) processingLightControl.getRenderEffect();
        if (processingLightRenderEffect2 != null) {
            processingLightRenderEffect2.add(view);
        }
        if (z && (processingLightRenderEffect = (ProcessingLightRenderEffect) processingLightControl.getRenderEffect()) != null) {
            processingLightRenderEffect.tryStart();
        }
    }

    public final void buildAnimation(ProcessingLightConfig processingLightConfig) {
        j.e(processingLightConfig, "config");
        ArrayList<ValueAnimator> animators$sesl_visualeffect_INTERNAL_2_1_6_release = getAnimators$sesl_visualeffect_INTERNAL_2_1_6_release();
        boolean z = false;
        if (animators$sesl_visualeffect_INTERNAL_2_1_6_release == null || !animators$sesl_visualeffect_INTERNAL_2_1_6_release.isEmpty()) {
            Iterator<T> it = animators$sesl_visualeffect_INTERNAL_2_1_6_release.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((ValueAnimator) it.next()).isRunning()) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        for (ValueAnimator cancel : getAnimators$sesl_visualeffect_INTERNAL_2_1_6_release()) {
            cancel.cancel();
        }
        getAnimators$sesl_visualeffect_INTERNAL_2_1_6_release().clear();
        getViews().forEachAlive(new C0582a(6, this));
        getViews().forEachAlive(new c(this, z, 11));
        for (AnimatableAttribute build$sesl_visualeffect_INTERNAL_2_1_6_release : processingLightConfig.getAnimatableAttribute$sesl_visualeffect_INTERNAL_2_1_6_release()) {
            getAnimators$sesl_visualeffect_INTERNAL_2_1_6_release().add(build$sesl_visualeffect_INTERNAL_2_1_6_release.build$sesl_visualeffect_INTERNAL_2_1_6_release(this));
        }
    }

    public final void setLightPosition(PointF pointF) {
        j.e(pointF, "value");
        this.lightPosition = pointF;
        ProcessingLightRenderEffect processingLightRenderEffect = (ProcessingLightRenderEffect) getRenderEffect();
        if (processingLightRenderEffect != null) {
            processingLightRenderEffect.setLightPosition(this.lightPosition);
        }
    }

    public final void setLightScale(float f) {
        this.lightScale = f;
        ProcessingLightRenderEffect processingLightRenderEffect = (ProcessingLightRenderEffect) getRenderEffect();
        if (processingLightRenderEffect != null) {
            processingLightRenderEffect.setLightScale(this.lightScale);
        }
    }

    public i build(ProcessingLightConfig processingLightConfig) {
        j.e(processingLightConfig, "config");
        ProcessingLightRenderEffect processingLightRenderEffect = new ProcessingLightRenderEffect(processingLightConfig.getUseLightnessCalibration(), processingLightConfig.getBlendMode());
        processingLightRenderEffect.setLightColor(processingLightConfig.getLightColor());
        processingLightRenderEffect.setLightPosition(processingLightConfig.getLightPos());
        processingLightRenderEffect.setLightRotation(processingLightConfig.getLightAngle());
        processingLightRenderEffect.setLightScale(processingLightConfig.getLightScale());
        processingLightRenderEffect.setLightIntensity(processingLightConfig.getLightIntensity());
        Integer domainColor2 = processingLightConfig.getDomainColor();
        if (domainColor2 != null) {
            processingLightRenderEffect.setDomainColor(domainColor2.intValue());
        }
        processingLightRenderEffect.setDomainStrength(processingLightConfig.getDomainStrength());
        processingLightRenderEffect.setDomainDeltaRatio(processingLightConfig.getDomainDeltaRatio());
        processingLightRenderEffect.setStretch(processingLightConfig.getLightStretch());
        processingLightRenderEffect.setFrameRate(processingLightConfig.getFrameRate());
        return new i(processingLightRenderEffect, (Object) null);
    }
}
