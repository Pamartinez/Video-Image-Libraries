package com.samsung.android.sesl.visualeffect.lighteffects.guidinglight;

import B1.a;
import B2.i;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import bd.p;
import bd.q;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0001\u0018\u0000 02\u00020\u0001:\u00010B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001aH\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0013¢\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u0010¢\u0006\u0004\b!\u0010\"R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010#R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010$R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010%R\u0018\u0010'\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0018\u0010)\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b)\u0010(R\u0016\u0010*\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u0018\u0010,\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b.\u0010/¨\u00061"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/TouchInteractionHandler;", "", "Landroid/view/View;", "view", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightRenderEffect;", "renderEffect", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;", "config", "<init>", "(Landroid/view/View;Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightRenderEffect;Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;)V", "Landroid/view/View$OnTouchListener;", "createTouchListener", "()Landroid/view/View$OnTouchListener;", "Landroid/graphics/PointF;", "from", "to", "Lme/x;", "moveToPosition", "(Landroid/graphics/PointF;Landroid/graphics/PointF;)V", "", "animateIntensity", "(FF)V", "pointA", "pointB", "getDistance", "(Landroid/graphics/PointF;Landroid/graphics/PointF;)F", "", "isEnable", "setDefaultTouchInteraction", "(Z)V", "intensity", "updateBaseIntensity", "(F)V", "destroy", "()V", "Landroid/view/View;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightRenderEffect;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightConfig;", "Landroidx/dynamicanimation/animation/SpringAnimation;", "touchAnimation", "Landroidx/dynamicanimation/animation/SpringAnimation;", "intensityAnimation", "lastTouchDownPos", "Landroid/graphics/PointF;", "useDefaultTouchListener", "Ljava/lang/Boolean;", "baseIntensity", "F", "Companion", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TouchInteractionHandler {
    private static final PointF CENTER = new PointF(0.5f, 0.5f);
    public static final Companion Companion = new Companion((e) null);
    private float baseIntensity;
    private final GuidingLightConfig config;
    private SpringAnimation intensityAnimation;
    private PointF lastTouchDownPos = new PointF(0.5f, 0.5f);
    private final GuidingLightRenderEffect renderEffect;
    private SpringAnimation touchAnimation;
    private Boolean useDefaultTouchListener;
    private final View view;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/TouchInteractionHandler$Companion;", "", "<init>", "()V", "MOVE_THRESHOLD", "", "CENTER", "Landroid/graphics/PointF;", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public TouchInteractionHandler(View view2, GuidingLightRenderEffect guidingLightRenderEffect, GuidingLightConfig guidingLightConfig) {
        j.e(view2, "view");
        j.e(guidingLightConfig, "config");
        this.view = view2;
        this.renderEffect = guidingLightRenderEffect;
        this.config = guidingLightConfig;
        this.baseIntensity = guidingLightConfig.getLightIntensity();
    }

    private final void animateIntensity(float f, float f5) {
        SpringAnimation springAnimation = this.intensityAnimation;
        if (springAnimation != null) {
            springAnimation.cancel();
        }
        SpringAnimation createSpringAnimation = AnimationFactory.Companion.createSpringAnimation(new q(f, f5, this));
        this.intensityAnimation = createSpringAnimation;
        if (createSpringAnimation != null) {
            createSpringAnimation.start();
        }
    }

    /* access modifiers changed from: private */
    public static final void animateIntensity$lambda$2(float f, float f5, TouchInteractionHandler touchInteractionHandler, DynamicAnimation dynamicAnimation, float f8, float f10) {
        float f11 = (((f5 - f) * f8) / 100.0f) + f;
        GuidingLightRenderEffect guidingLightRenderEffect = touchInteractionHandler.renderEffect;
        if (guidingLightRenderEffect != null) {
            guidingLightRenderEffect.setLightIntensity(f11);
        }
    }

    private final View.OnTouchListener createTouchListener() {
        return new i(17, this);
    }

    /* access modifiers changed from: private */
    public static final boolean createTouchListener$lambda$0(TouchInteractionHandler touchInteractionHandler, View view2, MotionEvent motionEvent) {
        PointF pointF = new PointF(a.k(motionEvent.getX() / ((float) view2.getWidth()), 0.0f, 1.0f), a.k(motionEvent.getY() / ((float) view2.getHeight()), 0.0f, 1.0f));
        int action = motionEvent.getAction();
        if (action == 0) {
            touchInteractionHandler.moveToPosition(CENTER, pointF);
            touchInteractionHandler.animateIntensity(touchInteractionHandler.baseIntensity, touchInteractionHandler.config.getTouchIntensity());
            touchInteractionHandler.lastTouchDownPos = pointF;
            return false;
        } else if (action == 1) {
            touchInteractionHandler.moveToPosition(pointF, CENTER);
            touchInteractionHandler.animateIntensity(touchInteractionHandler.config.getTouchIntensity(), touchInteractionHandler.baseIntensity);
            touchInteractionHandler.lastTouchDownPos = pointF;
            return false;
        } else if (action != 2) {
            if (action != 3) {
                return false;
            }
            touchInteractionHandler.moveToPosition(touchInteractionHandler.lastTouchDownPos, CENTER);
            touchInteractionHandler.animateIntensity(touchInteractionHandler.config.getTouchIntensity(), touchInteractionHandler.baseIntensity);
            return false;
        } else if (touchInteractionHandler.getDistance(touchInteractionHandler.lastTouchDownPos, pointF) <= 0.02f) {
            return false;
        } else {
            touchInteractionHandler.moveToPosition(touchInteractionHandler.lastTouchDownPos, pointF);
            touchInteractionHandler.lastTouchDownPos = pointF;
            return false;
        }
    }

    private final float getDistance(PointF pointF, PointF pointF2) {
        double d = (double) 2;
        return (float) Math.sqrt((double) (((float) Math.pow((double) (pointF2.x - pointF.x), d)) + ((float) Math.pow((double) (pointF2.y - pointF.y), d))));
    }

    private final void moveToPosition(PointF pointF, PointF pointF2) {
        SpringAnimation springAnimation = this.touchAnimation;
        if (springAnimation != null) {
            springAnimation.cancel();
        }
        SpringAnimation createSpringAnimation = AnimationFactory.Companion.createSpringAnimation(new p(pointF, pointF2, this));
        this.touchAnimation = createSpringAnimation;
        if (createSpringAnimation != null) {
            createSpringAnimation.start();
        }
    }

    /* access modifiers changed from: private */
    public static final void moveToPosition$lambda$1(PointF pointF, PointF pointF2, TouchInteractionHandler touchInteractionHandler, DynamicAnimation dynamicAnimation, float f, float f5) {
        float f8 = pointF.x;
        float f10 = pointF.y;
        PointF pointF3 = new PointF((((pointF2.x - f8) * f) / 100.0f) + f8, (((pointF2.y - f10) * f) / 100.0f) + f10);
        GuidingLightRenderEffect guidingLightRenderEffect = touchInteractionHandler.renderEffect;
        if (guidingLightRenderEffect != null) {
            guidingLightRenderEffect.setLightPos(pointF3);
        }
    }

    public final void destroy() {
        SpringAnimation springAnimation = this.touchAnimation;
        if (springAnimation != null) {
            springAnimation.cancel();
        }
        this.touchAnimation = null;
        SpringAnimation springAnimation2 = this.intensityAnimation;
        if (springAnimation2 != null) {
            springAnimation2.cancel();
        }
        this.intensityAnimation = null;
        if (j.a(this.useDefaultTouchListener, Boolean.TRUE)) {
            this.view.setOnTouchListener((View.OnTouchListener) null);
        }
    }

    public final void setDefaultTouchInteraction(boolean z) {
        if (z) {
            this.view.setOnTouchListener(createTouchListener());
        } else if (j.a(this.useDefaultTouchListener, Boolean.TRUE)) {
            this.view.setOnTouchListener((View.OnTouchListener) null);
        }
        this.useDefaultTouchListener = Boolean.valueOf(z);
    }

    public final void updateBaseIntensity(float f) {
        this.baseIntensity = f;
    }
}
