package com.samsung.android.sesl.visualeffect.surfaceeffects.turbulencenoise;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 $2\u00020\u0001:\u0001$B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0015¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R*\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006@\u0006X\u000e¢\u0006\u0018\n\u0004\b\u0014\u0010\u0015\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R*\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u000e¢\u0006\u0018\n\u0004\b\u001d\u0010\u001e\u0012\u0004\b#\u0010\u001b\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006%"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/surfaceeffects/turbulencenoise/TurbulenceNoiseView;", "Landroid/view/View;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Landroid/graphics/Canvas;", "canvas", "Lme/x;", "onDraw", "(Landroid/graphics/Canvas;)V", "Lcom/samsung/android/sesl/visualeffect/surfaceeffects/turbulencenoise/TurbulenceNoiseShader;", "turbulenceNoiseShader", "Lcom/samsung/android/sesl/visualeffect/surfaceeffects/turbulencenoise/TurbulenceNoiseShader;", "Landroid/graphics/Paint;", "paint", "Landroid/graphics/Paint;", "Landroid/animation/ValueAnimator;", "currentAnimator", "Landroid/animation/ValueAnimator;", "getCurrentAnimator", "()Landroid/animation/ValueAnimator;", "setCurrentAnimator", "(Landroid/animation/ValueAnimator;)V", "getCurrentAnimator$annotations", "()V", "Lcom/samsung/android/sesl/visualeffect/surfaceeffects/turbulencenoise/TurbulenceNoiseAnimationConfig;", "noiseConfig", "Lcom/samsung/android/sesl/visualeffect/surfaceeffects/turbulencenoise/TurbulenceNoiseAnimationConfig;", "getNoiseConfig", "()Lcom/samsung/android/sesl/visualeffect/surfaceeffects/turbulencenoise/TurbulenceNoiseAnimationConfig;", "setNoiseConfig", "(Lcom/samsung/android/sesl/visualeffect/surfaceeffects/turbulencenoise/TurbulenceNoiseAnimationConfig;)V", "getNoiseConfig$annotations", "Companion", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TurbulenceNoiseView extends View {
    public static final Companion Companion = new Companion((e) null);
    private ValueAnimator currentAnimator;
    private final Paint paint;
    private final TurbulenceNoiseShader turbulenceNoiseShader;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/surfaceeffects/turbulencenoise/TurbulenceNoiseView$Companion;", "", "<init>", "()V", "MS_TO_SEC", "", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TurbulenceNoiseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.e(context, "context");
        TurbulenceNoiseShader turbulenceNoiseShader2 = new TurbulenceNoiseShader(false, 1, (e) null);
        this.turbulenceNoiseShader = turbulenceNoiseShader2;
        Paint paint2 = new Paint();
        paint2.setShader(turbulenceNoiseShader2);
        this.paint = paint2;
    }

    public final ValueAnimator getCurrentAnimator() {
        return this.currentAnimator;
    }

    public final TurbulenceNoiseAnimationConfig getNoiseConfig() {
        return null;
    }

    public void onDraw(Canvas canvas) {
        j.e(canvas, "canvas");
        if (canvas.isHardwareAccelerated()) {
            canvas.drawPaint(this.paint);
        }
    }

    public final void setCurrentAnimator(ValueAnimator valueAnimator) {
        this.currentAnimator = valueAnimator;
    }

    public static /* synthetic */ void getCurrentAnimator$annotations() {
    }

    public static /* synthetic */ void getNoiseConfig$annotations() {
    }

    public final void setNoiseConfig(TurbulenceNoiseAnimationConfig turbulenceNoiseAnimationConfig) {
    }
}
